package com.zhongruan.template.controller;

import com.alibaba.fastjson.JSONObject;
import com.zhongruan.template.entity.IdentifierMappingInfo;
import com.zhongruan.template.massage.ResultData;
import com.zhongruan.template.service.IdentifierMappingInfoService;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

/**
 * @author zhenxu.guan
 * @Date 2020/8/3 0:29
 */
@RestController
@RequestMapping("/identifierMappingInfo")
public class IdentifierMappingInfoController {
	@Autowired
	private IdentifierMappingInfoService identifierMappingInfoService;

	//通过标识符id获取sql
	@PostMapping("/findSQL")
	@ApiImplicitParams({@ApiImplicitParam(name = "templateId", required = true),
			@ApiImplicitParam(name = "identiferName", required = true)})
	@ApiOperation(value = "通过获取sql")
	public ResultData getByInfo(@RequestBody JSONObject params) {
		IdentifierMappingInfo identifierMappingInfo = identifierMappingInfoService.getByInfo(params.getInteger("templateId"), params.getString("identiferName"));
		return ResultData.success(identifierMappingInfo.getSqlContext());
	}

	@PostMapping("/updateSql")
	@ApiImplicitParams({@ApiImplicitParam(name = "templateId", required = true),
			@ApiImplicitParam(name = "identifierName", required = true),
			@ApiImplicitParam(name = "sqlContext", required = true)})
	@ApiOperation(value = "新增/更新 X sql ")
	public ResultData updateSql(@RequestBody IdentifierMappingInfo identifierMappingInfo) {
		int i = identifierMappingInfoService.updateSql(identifierMappingInfo);
		if(i==1){
			return ResultData.success(i);
		}else{
			return ResultData.error("sql更新失败");
		}

	}
}
