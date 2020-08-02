package com.zhongruan.template.controller;

import com.zhongruan.template.massage.ResultData;
import io.swagger.annotations.ApiOperation;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

/**
 * @Author: aiying014
 * Created by 17578 on 17:14 2020/8/2.
 * @Description:
 */
@RestController
public class FileOperateController {
	@RequestMapping(value = "/upload_template_html",method = RequestMethod.POST)
	@ApiOperation(value = "上传html模板")
	public ResultData uploadHTMLFile(MultipartFile file){

		return ResultData.success("ok");
	}

}
