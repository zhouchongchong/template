
package com.zhongruan.template.controller;

import com.alibaba.fastjson.JSONObject;
import com.zhongruan.template.massage.ResultData;
import com.zhongruan.template.service.SqlExecuteService;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

/**
 * @author zhenxu.guan
 * @Date 2020/8/3 16:25
 */
@RestController
@RequestMapping("/sqlExecute")
public class SqlExecuteController {

	@Autowired
	private SqlExecuteService sqlExecuteService;

	@PostMapping("/sqlQuery")
	@ApiOperation("通过sql查询")
	@ApiImplicitParams({@ApiImplicitParam(name = "sql" ),
			@ApiImplicitParam(name = "dbSourceId")})
	public ResultData sqlQuery(@RequestBody(required = false) JSONObject jsonObject) {
		String sql = jsonObject.getString("sql");
		Integer dbSourceId = jsonObject.getInteger("dbSourceId");
		Object ret;
		try {
			ret = sqlExecuteService.sqlExecute(sql, dbSourceId);
		} catch (Exception e) {
			ret = e.getMessage();
		}
		return ResultData.success(ret);
	}


}
