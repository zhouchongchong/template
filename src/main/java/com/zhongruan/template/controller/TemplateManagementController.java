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

import com.zhongruan.template.massage.ResultData;
import com.zhongruan.template.service.TemplateInfoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author zhenxu.guan
 * @Date 2020/8/2 14:37
 */
@RestController
@RequestMapping("/templateInfo")
public class TemplateManagementController {
    @Autowired
    private TemplateInfoService templateInfoService;
    @ResponseBody
    @GetMapping("/findAll")
    public ResultData findAll(){


        return ResultData.success(templateInfoService.getAll());

}

}