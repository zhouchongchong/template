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
package com.zhongruan.template.service.impl;

import com.zhongruan.template.entity.TemplateInfo;
import com.zhongruan.template.mapper.TemplateInfoMapper;
import com.zhongruan.template.mapper.TextualInfoMapper;
import com.zhongruan.template.service.TemplateManagementService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @author zhenxu.guan
 * @Date 2020/8/2 14:45
 */
@Service
public class TemplateManagementServiceImpl implements TemplateManagementService{
    @Autowired
    TemplateInfoMapper templateInfoMapper;
    @Override
    public List<TemplateInfo> findAll() {
        return templateInfoMapper.findAll();
    }
}
