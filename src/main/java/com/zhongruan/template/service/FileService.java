package com.zhongruan.template.service;

import com.zhongruan.template.dao.TemplateInfoMapper;
import com.zhongruan.template.entity.TemplateInfo;
import com.zhongruan.template.entity.TemplateInfoExample;
import com.zhongruan.template.massage.ResultData;
import com.zhongruan.template.util.FileUtil;
import com.zhongruan.template.vo.Constant;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;
import org.springframework.web.multipart.MultipartFile;

import java.util.Map;

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

	@Value("${file.path.word}")
	private String word_file_path;

	@Value("${file.path.xml}")
	private String xml_file_path;

	@Value("${file.path.zip}")
	private String zip_file_path;


	@Value("${file.path.ftl}")
	private String ftl_file_path;

	@Autowired
	TemplateInfoMapper templateInfoMapper;

	public ResultData dealXMLFile(MultipartFile file, int templateId){
		log.info("执行xml文件处理，文件：{}，模板id:{}",file.getName(),templateId);
		String message = Constant.FAILED;

		try {
			if (file.getSize() < 1){
				return ResultData.error(message);
			}
			//1.保存文件
			final String xmlFilePath = FileUtil.saveFile(file, xml_file_path);

			//2.修改文件并输出到 ftl 文件夹中 生成 ftl 文件
			String ftlFilePath = ftl_file_path + System.currentTimeMillis() + Constant.SUFF_FTL;
			final boolean replaceRet = FileUtil.replaceTxtByStr(xmlFilePath, ftlFilePath, Constant.ASTERISK,
					Constant.FTL_REPLACE, false, null);
			if (replaceRet){
				return ResultData.error("XML 模板替换失败");
			}

			log.info("FTL 文件生成地址：{}",ftlFilePath);
			final TemplateInfo templateInfo = new TemplateInfo();
			templateInfo.setTemplateFtlUrl(ftlFilePath);
			final TemplateInfoExample templateInfoExample = new TemplateInfoExample();
			templateInfoExample.createCriteria().andIdEqualTo(templateId);
			final int effRow = templateInfoMapper.updateByExampleSelective(templateInfo, templateInfoExample);
			if (effRow == 1){
				message = Constant.SUCCESS;
			}
		} catch (Exception e){
			log.error("处理上传 XML 模板错误：{}",e.getMessage(),e);
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
	 * @param templateName
	 * @return
	 */
	public ResultData dealHtmlFile(MultipartFile file,String templateName) {
		log.info("word_path:{},xml_path:{},ftl_path:{},html_path:{}", word_file_path,
				xml_file_path, ftl_file_path, html_file_path);
		String message = "";
		try {

			final String zipFilePath = FileUtil.saveFile(file, zip_file_path);
			log.info("zip file path:{},file size:{}", zipFilePath, file.getSize());

			final String htmlFilePath = FileUtil.unZipFiles(zipFilePath, html_file_path);
			if (StringUtils.isEmpty(htmlFilePath)) {
				log.error("该zip文件不含有 HTML 文件");
				return  ResultData.error("该zip文件不含有 HTML 文件");
			}

			String showHTMLFile = html_file_path + System.currentTimeMillis() + Constant.SUFF_HTML;
			final boolean byStr = FileUtil.replaceTxtByStr(htmlFilePath, showHTMLFile, Constant.ASTERISK,
					Constant.HTML_REPLACE, true, Constant.APPEND_STR);
			if (byStr){
				return ResultData.error("模板綁定 SQL 页面生成失败");
			}
			log.info("after deal html file:{}",showHTMLFile);

			final TemplateInfo templateInfo = new TemplateInfo();
			templateInfo.setTemplateName(templateName);
			templateInfo.setTemplateHtmlUrl(showHTMLFile);

			final int effortRow = templateInfoMapper.insertSelective(templateInfo);

			if (effortRow == 1){
				message = "上传模板成功";
			}
		} catch (Exception e) {
			log.error("解压 HTML 模板文件失败");
			message = "插入数据库失败";
		} finally {
			return ResultData.success(message);
		}
	}


	public static String createWord(Map<String,String> mapping,String wordPath){

		return wordPath;
	}
}
