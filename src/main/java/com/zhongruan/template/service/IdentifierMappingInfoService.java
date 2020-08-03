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
package com.zhongruan.template.service;

import com.zhongruan.template.dao.IdentifierMappingInfoMapper;
import com.zhongruan.template.entity.IdentifierMappingInfo;
import com.zhongruan.template.entity.IdentifierMappingInfoExample;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @author zhenxu.guan
 * @Date 2020/8/3 0:35
 */
@Service
public class IdentifierMappingInfoService {
    @Autowired
    private IdentifierMappingInfoMapper identifierMappingInfoMapper;

    public IdentifierMappingInfo getById(int id) {
        IdentifierMappingInfo identifierMappingInfo =null;
        IdentifierMappingInfoExample identifierMappingInfoExample=new IdentifierMappingInfoExample();
        IdentifierMappingInfoExample.Criteria criteria = identifierMappingInfoExample.createCriteria();
        criteria.andIdEqualTo(id);
        List<IdentifierMappingInfo> identifierMappingInfos = identifierMappingInfoMapper.selectByExample(identifierMappingInfoExample);
            if(identifierMappingInfos.size()==1){
                identifierMappingInfo=identifierMappingInfos.get(0);
            }
       return identifierMappingInfo;
    }
}
