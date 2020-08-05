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
 * 2020/8/2         ${Author}  1.0.0         ${DESCRIPTION}
 * *****************************************************************************
 */
package com.zhongruan.template.controller;

import com.alibaba.fastjson.JSONObject;
import com.zhongruan.template.entity.TemplateInfo;
import com.zhongruan.template.massage.ResultData;
import com.zhongruan.template.service.TemplateInfoService;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.File;
import java.util.List;

/**
 * @author zhenxu.guan
 * @Date 2020/8/2 14:37
 */
@RestController
@RequestMapping("/templateInfo")
public class TemplateInfoController {
    @Autowired
    private TemplateInfoService templateInfoService;

    @GetMapping("/findAll")
    @ApiOperation("获取所有模板信息")
    public ResultData findAll() {
        List<TemplateInfo> all = templateInfoService.getAll();
        return ResultData.success(all);
    }

    //通过名称获取模板信息
    @GetMapping("/findByName")
    @ApiOperation("通过名称获取模板信息")
    @ApiImplicitParams({@ApiImplicitParam(name = "templateName",required = true)})
    public ResultData findByName(@RequestBody JSONObject jsonObject) {
        String templateName = jsonObject.getString("templateName");
        List<TemplateInfo> templateInfos = templateInfoService.findByName(templateName);
        return ResultData.success(templateInfos);
    }


    @PostMapping("/delete")
    @ApiOperation("通过id删除模板信息")
    @ApiImplicitParams({@ApiImplicitParam(name = "templateId",required = true)})
    public ResultData deleteTemplateById(@RequestBody JSONObject jsonObject){
        Integer templateId = jsonObject.getInteger("templateId");
        final ResultData resultData = templateInfoService.deleteById(templateId);
        if (resultData.getCode() == 0){
            List<TemplateInfo> all = templateInfoService.getAll();
            return ResultData.success(all);
        }
        return ResultData.error("删除模板失败");
    }
}
