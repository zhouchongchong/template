package com.zhongruan.template.controller;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.zhongruan.template.massage.ResultData;
import com.zhongruan.template.service.FileService;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
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
@RequestMapping("/fileOperate")
public class FileOperateController {

	@Autowired
	private FileService fileService;

	@RequestMapping(value = "/upload_template_html",method = RequestMethod.POST)
	@ApiOperation(value = "上传html展示模板")
	@ApiImplicitParams({@ApiImplicitParam(name = "templateName",required = true)})
	public ResultData uploadHTMLFile(@RequestParam MultipartFile file,@RequestBody JSONObject params){
		return fileService.dealHtmlFile(file, params.getString("templateName"));
	}

	@RequestMapping(value =  "/upload_template_xml",method = RequestMethod.POST)
	@ApiOperation(value = "上传XML 文件 word生成模板")
	@ApiImplicitParams({@ApiImplicitParam(name = "templateId",required = true)})
	public ResultData uploadXMLFile(@RequestParam MultipartFile file,@RequestParam JSONObject params){
		return fileService.dealXMLFile(file, params.getInteger("templateId"));
	}

	@RequestMapping(value = "/create_word",method = RequestMethod.POST)
	@ApiOperation(value = "根据模板 ID 创建 word 模板")
	@ApiImplicitParams({@ApiImplicitParam(name = "templateId",required = true),
			@ApiImplicitParam(name = "dbSource_id",required = true)})
	public ResultData createWordFile(@RequestBody JSONObject params){

		return fileService.createWord(params.getInteger("templateId"),params.getInteger("dbSource_id"));
	}
}
