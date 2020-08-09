
package com.zhongruan.template.controller;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.zhongruan.template.massage.ResultData;
import com.zhongruan.template.service.SqlExecuteService;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * @author zhenxu.guan
 * @Date 2020/8/3 16:25
 */
@RestController
@RequestMapping("/sqlExecute")
public class SqlExecuteController {

	@Autowired
	private SqlExecuteService sqlExecuteService;

	@PostMapping("/sqlTest")
	@ApiOperation("通过sql测试")
	@ApiImplicitParams({
			@ApiImplicitParam(name = "identifierArray"),
			@ApiImplicitParam(name = "sql" ),
			@ApiImplicitParam(name = "dbSourceId")})
	public ResultData sqlTest(@RequestBody(required = false) JSONObject jsonObject) {
		final JSONArray identifierArray = jsonObject.getJSONArray("identifierArray");
		//identifierArray转换成list
		List<String> identifierList = JSONObject.parseArray(identifierArray.toJSONString(), String.class);
		String sql = jsonObject.getString("sql");
		Integer dbSourceId = jsonObject.getInteger("dbSourceId");
		Object ret;
		try {
			ret = sqlExecuteService.sqlTest(identifierList,sql, dbSourceId);
		} catch (Exception e) {
			ret = e.getMessage();
		}
		return ResultData.success(ret);
	}
}
