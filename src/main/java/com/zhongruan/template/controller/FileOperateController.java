package com.zhongruan.template.controller;

import com.zhongruan.template.massage.ResultData;
import com.zhongruan.template.service.FileService;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

/**
 * @Author: aiying014
 * Created by 17578 on 17:14 2020/8/2.
 * @Description:
 */
@RestController
public class FileOperateController {

	@Autowired
	private FileService fileService;

	@RequestMapping(value = "/upload_template_html",method = RequestMethod.POST)
	@ApiOperation(value = "上传html展示模板")
	@ResponseBody
	public ResultData uploadHTMLFile(@RequestParam MultipartFile file,@RequestParam String templateName){
		return fileService.dealHtmlFile(file, templateName);
	}

	@RequestMapping(value =  "/upload_template_xml",method = RequestMethod.POST)
	@ApiOperation(value = "上传XML 文件 word生成模板")
	@ResponseBody
	public ResultData uploadXMLFile(@RequestParam MultipartFile file,@RequestParam int templateId){
		return fileService.dealXMLFile(file, templateId);
	}

	@RequestMapping(value = "/create_word",method = RequestMethod.POST)
	@ApiOperation(value = "根据模板 ID 创建 word 模板")
	@ResponseBody
	public ResultData createWordFile(@RequestParam int templateId){

		return fileService.createWord(templateId);
	}
}
