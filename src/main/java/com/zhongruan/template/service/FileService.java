package com.zhongruan.template.service;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

/**
 * @Author: aiying014
 * Created by 17578 on 20:35 2020/8/2.
 * @Description:
 */
@Service

public class FileService {

	@Value("${}")
	private String html_file_path;

	@Value("${}")
	private String word_file_path;

	public void dealHtmlFile(){

	}

}
