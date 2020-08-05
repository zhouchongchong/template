
package com.zhongruan.template.service;

import com.zhongruan.template.dao.IdentifierMappingInfoMapper;
import com.zhongruan.template.entity.IdentifierMappingInfo;
import com.zhongruan.template.entity.IdentifierMappingInfoExample;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestBody;

import java.util.List;

/**
 * @author zhenxu.guan
 * @Date 2020/8/3 0:35
 */
@Service
public class IdentifierMappingInfoService {
    @Autowired
    private IdentifierMappingInfoMapper identifierMappingInfoMapper;

    public IdentifierMappingInfo getByInfo(int templateId, String identifierName) {
        IdentifierMappingInfo identifierMappingInfo =null;
        IdentifierMappingInfoExample identifierMappingInfoExample=new IdentifierMappingInfoExample();
        IdentifierMappingInfoExample.Criteria criteria = identifierMappingInfoExample.createCriteria();
        criteria.andTemplateIdEqualTo(templateId);
        criteria.andIdentifierNameEqualTo(identifierName);
        List<IdentifierMappingInfo> identifierMappingInfos = identifierMappingInfoMapper.selectByExample(identifierMappingInfoExample);
            if(identifierMappingInfos.size()==1){
                identifierMappingInfo=identifierMappingInfos.get(0);
            }
       return identifierMappingInfo;
    }

    public int updateSql(IdentifierMappingInfo identifierMappingInfo) {
        IdentifierMappingInfoExample identifierMappingInfoExample=new IdentifierMappingInfoExample();
        IdentifierMappingInfoExample.Criteria criteria = identifierMappingInfoExample.createCriteria();
        criteria.andTemplateIdEqualTo(identifierMappingInfo.getTemplateId());
        criteria.andIdentifierNameEqualTo(identifierMappingInfo.getIdentifierName());
        final List<IdentifierMappingInfo> identifierMappingInfos = identifierMappingInfoMapper.selectByExample(identifierMappingInfoExample);
        if (identifierMappingInfos.size() == 0){
            identifierMappingInfoMapper.insertSelective(identifierMappingInfo);
        }
        int i = identifierMappingInfoMapper.updateByExampleSelective(identifierMappingInfo, identifierMappingInfoExample);
        return i;
    }
}
