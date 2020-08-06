package com.zhongruan.template.service;

import com.zhongruan.template.dao.TextualInfoMapper;
import com.zhongruan.template.entity.TextualInfo;
import com.zhongruan.template.entity.TextualInfoExample;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @Author: zhou
 * Created by zhou chong on 10:57 2020/8/4.
 * @Description:
 */
@Service
@Slf4j
public class TextualInfoService {

	@Autowired
	TextualInfoMapper textualInfoMapper;

	public List<TextualInfo> getAll(){
		final TextualInfoExample templateInfoExample = new TextualInfoExample();
		//根据更新时间排序
		templateInfoExample.setOrderByClause("updated_time DESC");
		final List<TextualInfo> templateInfos = textualInfoMapper.selectByExample(templateInfoExample);
		return templateInfos;
	}
	public List<TextualInfo> findByName(String templateName){
		TextualInfoExample templateInfoExample =new TextualInfoExample();
		TextualInfoExample.Criteria criteria = templateInfoExample.createCriteria();
		criteria.andTextualNameLike("%"+templateName+"%");
		List<TextualInfo> templateInfos =null;
		try{
			templateInfos = textualInfoMapper.selectByExample(templateInfoExample);
		}catch(Exception e){
        log.error("模板信息获取失败");
		}
		return templateInfos;
	}
	public TextualInfo findById(int templateId){
		TextualInfoExample templateInfoExample =new TextualInfoExample();
		TextualInfoExample.Criteria criteria = templateInfoExample.createCriteria();
		criteria.andIdEqualTo(templateId);
		List<TextualInfo> templateInfos =null;
		try{
			templateInfos = textualInfoMapper.selectByExample(templateInfoExample);
		}catch(Exception e){

		}
		return templateInfos.get(0);
	}
}
