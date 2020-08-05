
package com.zhongruan.template.controller;

import com.alibaba.fastjson.JSONObject;
import com.zhongruan.template.entity.DatabaseInfo;
import com.zhongruan.template.massage.ResultData;
import com.zhongruan.template.service.DatabaseInfoService;
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
	@GetMapping("/listDBSourceTables")
	@ApiOperation(value = "根据是数据源id查询数据库全部表", notes = "查询数据库全部表")
	public ResultData listDBSourceTable(int sourceId) {
		try {
			List<DatabaseInfo> databaseInfos = databaseInfoService.findById(sourceId);
			Map map = databaseInfoService.listAlltable(databaseInfos.get(0));
			return ResultData.success(map);
		} catch (Exception e) {
			return ResultData.error(e.getMessage());
		}
	}

	/**
	 * 列出所有table
	 */
	@GetMapping("/list")
	@ApiOperation(value = "查询所有数据源", notes = "查询数据库全部表")
	public ResultData findAll() {
		try {
			List<DatabaseInfo> databaseInfo = databaseInfoService.findById(0);
			return ResultData.success(databaseInfo);
		} catch (Exception e) {
			return ResultData.error(e.getMessage());
		}
	}

	@PostMapping("/add_db_source")
	@ApiOperation(value = "添加数据源")
	public ResultData addDBSource(@RequestBody DatabaseInfo jsonObject) {
		return databaseInfoService.addDBSource(jsonObject);
	}

	@PostMapping("/dbsource_id")
	@ApiOperation(value = "通过id查询数据源")
	public ResultData findById(@RequestParam int sourceId) {
		try {
			List<DatabaseInfo> databaseInfo = databaseInfoService.findById(sourceId);
			return ResultData.success(databaseInfo.get(0));
		} catch (Exception e) {
			return ResultData.error(e.getMessage());
		}
	}

	@PostMapping("/update")
	@ApiOperation(value = "修改数据源")
	public ResultData findById(@RequestBody DatabaseInfo jsonObject) {
		return databaseInfoService.update(jsonObject);
	}

	@PostMapping("/test_connect")
	@ApiOperation(value = "测试链接")
	public ResultData testConnect(@RequestParam DatabaseInfo databaseInfo){
		return databaseInfoService.testConnect(databaseInfo);
	}

	@PostMapping("/delete_source")
	@ApiOperation(value = "删除数据源，根据id删除")
	public ResultData deleteDBSource(@RequestParam int DBSourceId){

		return databaseInfoService.deleteDBSource(DBSourceId);
	}
}

