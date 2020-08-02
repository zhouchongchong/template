package com.zhongruan.template.service;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

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

	@Value("${file.path.ftl}")
	private String ftl_file_path;

	public void dealHtmlFile(){
		log.info("word_path:{},xml_path:{},ftl_path:{},html_path:{}",word_file_path,
				xml_file_path,ftl_file_path,html_file_path);

	}

}
