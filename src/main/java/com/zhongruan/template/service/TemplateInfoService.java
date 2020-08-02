package com.zhongruan.template.service;

import com.zhongruan.template.dao.TemplateInfoMapper;
import com.zhongruan.template.entity.TemplateInfo;
import com.zhongruan.template.entity.TemplateInfoExample;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @Author: aiying014
 * Created by 17578 on 18:18 2020/8/2.
 * @Description:
 */
@Service
public class TemplateInfoService {
	@Autowired
	private TemplateInfoMapper templateInfoMapper;

	public List<TemplateInfo> getAll(){
		final TemplateInfoExample templateInfoExample = new TemplateInfoExample();
		final List<TemplateInfo> templateInfos = templateInfoMapper.selectByExample(templateInfoExample);
		return templateInfos;
	}
	public List<TemplateInfo> findByName(String templateName){
	    TemplateInfoExample templateInfoExample =new TemplateInfoExample();
        TemplateInfoExample.Criteria criteria = templateInfoExample.createCriteria();
        criteria.andTemplateNameLike("%"+templateName+"%");
        List<TemplateInfo> templateInfos =null;
        try{
            templateInfos = templateInfoMapper.selectByExample(templateInfoExample);
        }catch(Exception e){

        }
        return templateInfos;
    }
}
