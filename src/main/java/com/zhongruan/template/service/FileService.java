package com.zhongruan.template.service;

import com.zhongruan.template.dao.IdentifierMappingInfoMapper;
import com.zhongruan.template.dao.TemplateInfoMapper;
import com.zhongruan.template.dao.TextualInfoMapper;
import com.zhongruan.template.entity.*;
import com.zhongruan.template.massage.ResultData;
import com.zhongruan.template.util.FileUtil;
import com.zhongruan.template.vo.Constant;
import freemarker.template.TemplateException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.data.annotation.Transient;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;

/**
 * @Author: aiying014
 * Created by 17578 on 20:35 2020/8/2.
 * @Description:
 */
@Service
@Slf4j
public class FileService {

	@Value("${file.path.html}")
	private String html_file_path;

	@Value("${file.path.htm}")
	private String htm_file_path;

	@Value("${file.path.word}")
	private String word_file_path;

	@Value("${file.path.xml}")
	private String xml_file_path;

	@Value("${file.path.zip}")
	private String zip_file_path;


	@Value("${file.path.ftl}")
	private String ftl_file_path;

	@Autowired
	private TemplateInfoMapper templateInfoMapper;

	@Autowired
	private IdentifierMappingInfoMapper identifierMappingInfoMapper;

	@Autowired
	private SqlExecuteService sqlExecuteService;

	@Autowired
	private TextualInfoMapper textualInfoMapper;

	@Transient
	public ResultData dealXMLFile(MultipartFile file, int templateId) {
		log.info("执行xml文件处理，文件：{}，模板id:{}", file.getName(), templateId);
		String message = Constant.FAILED;

		try {
			if (file.getSize() < 1) {
				return ResultData.error(message);
			}
			//1.保存文件
			final String xmlFilePath = FileUtil.saveFile(file, xml_file_path);

			//2.修改文件并输出到 ftl 文件夹中 生成 ftl 文件
			String ftlFilePath = ftl_file_path + System.currentTimeMillis() + Constant.SUFF_FTL;
			final int replaceRet = FileUtil.replaceTxtByStr(xmlFilePath, ftlFilePath, Constant.ASTERISK,
					Constant.FTL_REPLACE, false, null);
			if (replaceRet == 0) {
				return ResultData.error("XML 模板替换失败");
			}

			log.info("FTL 文件生成地址：{}", ftlFilePath);
			final TemplateInfo templateInfo = new TemplateInfo();
			templateInfo.setTemplateFtlUrl(ftlFilePath);
			final TemplateInfoExample templateInfoExample = new TemplateInfoExample();
			templateInfoExample.createCriteria().andIdEqualTo(templateId);
			final int effRow = templateInfoMapper.updateByExampleSelective(templateInfo, templateInfoExample);
			if (effRow == 1) {
				message = Constant.SUCCESS;
			}
		} catch (Exception e) {
			log.error("处理上传 XML 模板错误：{}", e.getMessage(), e);
		}

		return ResultData.success(message);

	}

	/**
	 * 上传 html 模板，用于界面展示浏览绑定 sql
	 * 1.保存的zip文件
	 * 2.修改 htm or html 文件
	 * 3.插入数据库
	 *
	 * @param file
	 * @return
	 */
	@Transient
	public ResultData dealHtmlFile(MultipartFile file) {
		log.info("word_path:{},xml_path:{},ftl_path:{},html_path:{}", word_file_path,
				xml_file_path, ftl_file_path, html_file_path);
		String message = "";
		try {

			final String zipFilePath = FileUtil.saveFile(file, zip_file_path);
			log.info("zip file path:{},file size:{}", zipFilePath, file.getSize());

			final Map<String, String> unZipMap = FileUtil.unZipFiles(zipFilePath, htm_file_path);
			if (StringUtils.isEmpty(unZipMap.get(Constant.MAP_KEY_XML)) || StringUtils.isEmpty(unZipMap.get(Constant.MAP_KEY_HTML))) {
				log.error("该zip文件不含有 缺乏必要 文件");
				return ResultData.error("该zip文件不含有 缺乏必要 文件");
			}

			String showHTMLFile = htm_file_path + System.currentTimeMillis() + Constant.SUFF_HTML;
			final int replaceNum = FileUtil.replaceTxtByStr(unZipMap.get(Constant.MAP_KEY_HTML), showHTMLFile, Constant.ASTERISK,
					Constant.HTML_REPLACE, true, Constant.APPEND_STR);

			String ftlFilePath = ftl_file_path + System.currentTimeMillis() + Constant.SUFF_FTL;
			final int replaceRet = FileUtil.replaceTxtByStr(unZipMap.get(Constant.MAP_KEY_XML), ftlFilePath, Constant.ASTERISK,
					Constant.FTL_REPLACE, false, null);
			log.info("htmlFilepath：{},ftlfilepath:{},htm num:{},ftl num:{}",showHTMLFile,ftlFilePath,replaceNum,replaceRet);
			if (replaceNum != replaceRet) {
				return ResultData.error("XML 与 HTML 不匹配");
			}
			if (replaceRet == 0) {
				return ResultData.error("XML 模板替换失败");
			}

			if (replaceNum == 0) {
				return ResultData.error("模板綁定 SQL 页面生成失败");
			}
			log.info("after deal html file:{},需要绑定的 SQL 数量：{}", showHTMLFile, replaceNum);

			final TemplateInfo templateInfo = new TemplateInfo();
			templateInfo.setTemplateName(file.getOriginalFilename().split("\\.")[0]);
			templateInfo.setTemplateHtmlUrl(showHTMLFile);
			templateInfo.setTemplateFtlUrl(ftlFilePath);

			final int effortRow = templateInfoMapper.insertSelective(templateInfo);

			log.info("模板在数据库中的ID :{}", templateInfo.getId());

			final IdentifierMappingInfoExample infoExample = new IdentifierMappingInfoExample();
			infoExample.createCriteria().andTemplateIdEqualTo(templateInfo.getId());
			identifierMappingInfoMapper.deleteByExample(infoExample);

			for (int i = 0; i < replaceNum; i++) {
				IdentifierMappingInfo identifierMappingInfo = new IdentifierMappingInfo();
				identifierMappingInfo.setTemplateId(templateInfo.getId());
				identifierMappingInfo.setIdentifierName(String.valueOf(i));
				identifierMappingInfoMapper.insertSelective(identifierMappingInfo);
			}

			if (effortRow == 1) {
				message = "上传模板成功";
			}
			log.info(message);
		} catch (Exception e) {
			log.error("解压 HTML 模板文件失败");
			message = "插入数据库失败";
		} finally {
			return ResultData.success(message);
		}
	}


	public ResultData createWord(int templateId, int dbSourceId) {
		log.info("create word template");
		final IdentifierMappingInfoExample infoExample = new IdentifierMappingInfoExample();
		infoExample.createCriteria().andTemplateIdEqualTo(templateId);
		final List<IdentifierMappingInfo> identifierMappingInfos = identifierMappingInfoMapper.selectByExample(infoExample);
		final HashMap<String, Object> map = new HashMap<>();
		String wordPath = null;

		TemplateInfo templateInfo = templateInfoMapper.selectByPrimaryKey(templateId);

		for (IdentifierMappingInfo identifier : identifierMappingInfos) {
			if (StringUtils.isEmpty(identifier.getSqlContext()))
				continue;
			try {
				if (dbSourceId == 0) {
					dbSourceId = templateInfo.getDbSourceId();
				}
				final Map<String, Object> map3 = sqlExecuteService.sqlExecute(identifier.getId(),identifier.getSqlContext(), dbSourceId);
				if (map3 == null)
					return ResultData.error("sql 执行出错");
				final Object value = map3.get(identifier.getIdentifierName());
				map.put(String.format(Constant.MARK_REPLACE, identifier.getIdentifierName()), value);
			} catch (Exception e) {
				log.info("create word err:{}", e.getMessage());
				return ResultData.error("sql 执行出错");
			}
		}

		if (StringUtils.isEmpty(templateInfo.getTemplateFtlUrl())) {
			return ResultData.error("ftl 模板未上传");
		}
		wordPath = word_file_path + System.currentTimeMillis() + Constant.SUFF_DOC;
		try {
			FileUtil.createWord(templateInfo.getTemplateFtlUrl(), wordPath, map);
		} catch (IOException | TemplateException e) {
			log.error("create word error:{}", e.getMessage());
			return ResultData.error(e.getMessage());
		}
		final TextualInfo textualInfo = new TextualInfo();
		textualInfo.setTemplateId(templateId);
		textualInfo.setTextualName(templateInfo.getTemplateName());
		textualInfo.setTextualUrl(wordPath);
		textualInfoMapper.insertSelective(textualInfo);
		log.info("create word success,ftlPath:{}, docPath:{}", templateInfo.getTemplateFtlUrl(), wordPath);

		return ResultData.success(textualInfo);
	}
}
