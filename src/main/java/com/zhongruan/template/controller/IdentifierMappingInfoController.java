package com.zhongruan.template.controller;

import com.zhongruan.template.entity.IdentifierMappingInfo;
import com.zhongruan.template.massage.ResultData;
import com.zhongruan.template.service.IdentifierMappingInfoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

/**
 * @author zhenxu.guan
 * @Date 2020/8/3 0:29
 */
@RestController
@RequestMapping("identifierMappingInfo")
public class IdentifierMappingInfoController {
    @Autowired
    private IdentifierMappingInfoService identifierMappingInfoService;

    //通过标识符id获取sql
    @GetMapping("findById")
    public ResultData findById(@RequestParam("id") int id){
        IdentifierMappingInfo identifierMappingInfo = identifierMappingInfoService.getById(id);
        return ResultData.success(identifierMappingInfo.getSqlContext());
    }
        @PostMapping("/updateSql")
        public ResultData updateSql(@RequestBody IdentifierMappingInfo identifierMappingInfo){
            int i = identifierMappingInfoService.updateSql(identifierMappingInfo);
            return ResultData.success(i);
    }
}
