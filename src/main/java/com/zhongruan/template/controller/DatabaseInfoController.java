
package com.zhongruan.template.controller;

import com.alibaba.fastjson.JSONObject;
import com.zhongruan.template.entity.DatabaseInfo;
import com.zhongruan.template.massage.ResultData;
import com.zhongruan.template.service.DatabaseInfoService;
import com.zhongruan.template.vo.FieldBean;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

/**
 * @author zhenxu.guan
 * @Date 2020/8/2 18:08
 */
@RestController
@RequestMapping("/databaseInfo")
public class DatabaseInfoController {
	@Autowired
	private DatabaseInfoService databaseInfoService;


	/**
	 * 列出所有table
	 */
	@PostMapping("/listDBSourceTables")
	@ApiOperation(value = "根据是数据源id查询数据库全部表", notes = "查询数据库全部表")
	@ApiImplicitParams({@ApiImplicitParam(name = "sourceId",required = true),
			@ApiImplicitParam(name = "templateId",required = true)})
	public ResultData listDBSourceTable(@RequestBody JSONObject params) {
		try {
			List<DatabaseInfo> databaseInfos = databaseInfoService.findById(params.getInteger("sourceId"),
					params.getInteger("templateId"));
			List<String> list = databaseInfoService.listAlltable(databaseInfos.get(0));
			return ResultData.success(list);
		} catch (Exception e) {
			return ResultData.error(e.getMessage());
		}
	}

	/**
	 * 列出所有表的字段
	 */
	@PostMapping("/listTableColumn")
	@ApiOperation(value = "根据表名获取表的字段信息", notes = "根据表名获取表的字段信息")
	@ApiImplicitParams({@ApiImplicitParam(name = "tableName",required = true),@ApiImplicitParam(name = "sourceId",required = true)})

	public ResultData tableColumn(@RequestBody JSONObject params) {
		try {
			List<DatabaseInfo> databaseInfos = databaseInfoService.findById(params.getInteger("sourceId"),0);
			List<FieldBean> list =databaseInfoService.findTableColumn(params.getString("tableName"),databaseInfos.get(0));
			return ResultData.success(list);
		} catch (Exception e) {
			return ResultData.error(e.getMessage());
		}

	}

	/**
	 * 列出所有数据源
	 */
	@GetMapping("/list")
	@ApiOperation(value = "查询所有数据源", notes = "查询数据源")
	public ResultData findAll() {
		try {
			List<DatabaseInfo> databaseInfo = databaseInfoService.findById(0,0);
			return ResultData.success(databaseInfo);
		} catch (Exception e) {
			return ResultData.error(e.getMessage());
		}
	}

	@PostMapping("/add_db_source")
	@ApiOperation(value = "添加/修改数据源")
	@ApiImplicitParams({@ApiImplicitParam(name = "databaseName",required = true),
			@ApiImplicitParam(name = "databaseUrl",required = true),
			@ApiImplicitParam(name = "username",required = true),
			@ApiImplicitParam(name = "password",required = true),
			@ApiImplicitParam(name = "id")})
	public ResultData addDBSource(@RequestBody DatabaseInfo jsonObject) {
		final ResultData resultData = databaseInfoService.addDBSource(jsonObject);
		List<DatabaseInfo> databaseInfo = databaseInfoService.findById(0,0);
		resultData.setBody(databaseInfo);
		return resultData;
	}

	@PostMapping("/db_source_name_like")
	@ApiOperation(value = "通过name查询数据源")
	@ApiImplicitParams({@ApiImplicitParam(name = "sourceName",required = true)})
	public ResultData findByName(@RequestBody JSONObject params){
		return databaseInfoService.findByName(params.getString("sourceName"));
	}

	@PostMapping("/dbsource_id")
	@ApiOperation(value = "通过id查询数据源")
	@ApiImplicitParams({@ApiImplicitParam(name = "sourceId",required = true)})
	public ResultData findById(@RequestBody JSONObject sourceId) {
		try {
			List<DatabaseInfo> databaseInfo = databaseInfoService.findById(sourceId.getInteger("sourceId"),0);
			return ResultData.success(databaseInfo.get(0));
		} catch (Exception e) {
			return ResultData.error(e.getMessage());
		}
	}

	@PostMapping("/update")
	@ApiOperation(value = "修改数据源信息")
	@ApiImplicitParams({@ApiImplicitParam(name = "sourceId",required = true)})
	public ResultData update(@RequestBody DatabaseInfo jsonObject) {
		return databaseInfoService.update(jsonObject);
	}

	@PostMapping("/test_connect")
	@ApiOperation(value = "测试链接")
	@ApiImplicitParams({@ApiImplicitParam(name = "databaseName",required = true),
			@ApiImplicitParam(name = "databaseUrl",required = true),
			@ApiImplicitParam(name = "username",required = true),
			@ApiImplicitParam(name = "password",required = true),})
	public ResultData testConnect(@RequestBody DatabaseInfo databaseInfo){
		return databaseInfoService.testConnect(databaseInfo);
	}

	@PostMapping("/delete_source")
	@ApiOperation(value = "删除数据源，根据id删除")
	@ApiImplicitParams({@ApiImplicitParam(name = "DBSourceId",required = true)})
	public ResultData deleteDBSource(@RequestBody JSONObject object){
		final ResultData dbSourceId = databaseInfoService.deleteDBSource(object.getInteger("DBSourceId"));
		List<DatabaseInfo> databaseInfo = databaseInfoService.findById(0,0);
		dbSourceId.setBody(databaseInfo);
		return dbSourceId;
	}
}

