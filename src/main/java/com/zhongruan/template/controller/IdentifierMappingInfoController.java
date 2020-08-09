package com.zhongruan.template.controller;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.zhongruan.template.entity.IdentifierMappingInfo;
import com.zhongruan.template.massage.ResultData;
import com.zhongruan.template.service.IdentifierMappingInfoService;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

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
		String sql = "";
		if (identifierMappingInfo != null) {
			sql = identifierMappingInfo.getSqlContext();
		}
		return ResultData.success(sql);
	}

	@PostMapping("/updateSql")
	@ApiImplicitParams({@ApiImplicitParam(name = "templateId", required = true),
			//数组名称
			@ApiImplicitParam(name = "identifierArray", required = true),
			@ApiImplicitParam(name = "sqlContext", required = true)})
	@ApiOperation(value = "新增/更新 X sql ")
	public ResultData updateSql(@RequestBody(required = true) JSONObject jsonObject) {
		final JSONArray identifierArray = jsonObject.getJSONArray("identifierArray");
		List<String> identifierList = JSONObject.parseArray(identifierArray.toJSONString(), String.class);
		final String sqlContext = jsonObject.getString("sqlContext");
		final Integer templateId = jsonObject.getInteger("templateId");
		if (identifierList != null) {
			String identifierUnion = String.valueOf(templateId);
			for (int a = 0; a < identifierList.size(); a++) {
				// 4、获取标识符名称字符串
				String add = identifierList.get(a);
				//将标识符拼接在一起形成一个大id
				identifierUnion += add;
			}
			for (int i = 0; i < identifierList.size(); i++) {
				final String identifierName = identifierList.get(i);
				try {
					identifierMappingInfoService.updateSql(identifierName, sqlContext, templateId, identifierUnion);
				} catch (Exception e) {
					return ResultData.error("操作失败");
				}
			}
		}
		return ResultData.success("sql映射成功");
	}
}
