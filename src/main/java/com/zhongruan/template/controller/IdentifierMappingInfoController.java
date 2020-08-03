/**
 * @Copyright Beijing Jiangrongxin Technology Co,.Ltd 2020.
 * <p>
 * This material is the property of Beijing Jiangrongxin Technology Co,. Ltd.
 * and the information contained herein is confidential. This material,
 * either in whole or in part, must not be reproduced or disclosed to others
 * or used for purposes other than that for which it has been supplied without
 * Beijing Jiangrongxin's prior written permission,
 * or, if any part hereof is furnished by virtue of contract with a third party,
 * as expressly authorized under that contract.
 * <p>
 * *****************************************************************************
 * Date             Author      Version       Description
 * 2020/8/3         ${Author}  1.0.0         ${DESCRIPTION}
 * *****************************************************************************
 */
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
    @GetMapping("getById")
    public ResultData getById(@RequestParam("id") int id){
        IdentifierMappingInfo identifierMappingInfo = identifierMappingInfoService.getById(id);
        return ResultData.success(identifierMappingInfo.getSqlContext());
    }

/*    @PostMapping("insert"){
        public ResultData insert(@RequestBody IdentifierMappingInfo ){

    }*/
}
