
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

	//通过标识符id获取sql
	public IdentifierMappingInfo getByInfo(int templateId, String identifierName) {
		IdentifierMappingInfo identifierMappingInfo = null;
		IdentifierMappingInfoExample identifierMappingInfoExample = new IdentifierMappingInfoExample();
		IdentifierMappingInfoExample.Criteria criteria = identifierMappingInfoExample.createCriteria();
		//将sql拼接模板id为查询条件
		criteria.andTemplateIdEqualTo(templateId);
		//将标识符名称作为
		criteria.andIdentifierNameEqualTo(identifierName);
		List<IdentifierMappingInfo> identifierMappingInfos = identifierMappingInfoMapper.selectByExample(identifierMappingInfoExample);
		if (identifierMappingInfos.size() == 1) {
			identifierMappingInfo = identifierMappingInfos.get(0);
		}
		return identifierMappingInfo;
	}

	//新增/更新 X sql
	public int updateSql(String identifierName, String sqlContext, int templateId, String identifierUnion) {
		IdentifierMappingInfoExample identifierMappingInfoExample = new IdentifierMappingInfoExample();
		IdentifierMappingInfoExample.Criteria criteria = identifierMappingInfoExample.createCriteria();
		//将sql拼接模板id为查询条件
		criteria.andTemplateIdEqualTo(templateId);
		//将sql拼接标识符名称为查询条件
		criteria.andIdentifierNameEqualTo(identifierName);
		final List<IdentifierMappingInfo> identifierMappingInfos = identifierMappingInfoMapper.selectByExample(identifierMappingInfoExample);
		IdentifierMappingInfo identifierMappingInfo = new IdentifierMappingInfo();
		identifierMappingInfo.setIdentifierName(identifierName);
		identifierMappingInfo.setSqlContext(sqlContext);
		identifierMappingInfo.setTemplateId(templateId);
		identifierMappingInfo.setIdentifierUnion(identifierUnion);
		//如果没有则新增
		final int i;
		if (identifierMappingInfos.size() == 0) {
			i = identifierMappingInfoMapper.insertSelective(identifierMappingInfo);
			//如果有则更新
		} else {
			i = identifierMappingInfoMapper.updateByExampleSelective(identifierMappingInfo, identifierMappingInfoExample);
		}
		return i;
	}
}
