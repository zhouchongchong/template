
package com.zhongruan.template.controller;

import com.zhongruan.template.entity.DatabaseInfo;
import com.zhongruan.template.massage.ResultData;
import com.zhongruan.template.service.DatabaseInfoService;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.Map;

/**
 * @author zhenxu.guan
 * @Date 2020/8/2 18:08
 */
@RestController
@RequestMapping
public class DatabaseInfoController {
 @Autowired
 private DatabaseInfoService databaseInfoService;


    /**
     * 列出所有table
     */
    @GetMapping("/getAll")
    @ApiOperation(value = "查询数据库全部表",notes = "查询数据库全部表")
    public ResultData listAlltable(){
        try{
            DatabaseInfo databaseInfo = databaseInfoService.findByName("西方");
            Map map = databaseInfoService.listAlltable(databaseInfo);
            return ResultData.success(map);
        }catch (Exception e){
            return ResultData.error(e.getMessage());
        }
    }
}

