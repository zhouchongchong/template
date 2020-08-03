package com.zhongruan.template.service;

import com.zhongruan.template.util.FileUtil;
import com.zhongruan.template.vo.Constant;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;
import org.springframework.web.multipart.MultipartFile;

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

	public void dealHtmlFile(MultipartFile file) {
		log.info("word_path:{},xml_path:{},ftl_path:{},html_path:{}", word_file_path,
				xml_file_path, ftl_file_path, html_file_path);
		try {
			final String zipFilePath = FileUtil.saveFile(file, zip_file_path);
			log.info("zip file path:{},file size:{}", zipFilePath, file.getSize());
			final String htmlFilePath = FileUtil.unZipFiles(zipFilePath, html_file_path);
			if (StringUtils.isEmpty(htmlFilePath)) {
				log.error("该zip文件不含有 HTML 文件");
			}
			String showHTMLFile = html_file_path + System.currentTimeMillis() + Constant.SUFF_HTML;
			FileUtil.replaceTxtByStr(htmlFilePath,showHTMLFile,Constant.ASTERISK,
					Constant.HTML_REPLACE,true, Constant.APPEND_STR);
			log.info("after deal html file:{}",showHTMLFile);
		} catch (Exception e) {
			log.error("解压 HTML 模板文件失败");
		}
	}

}
