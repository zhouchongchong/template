package com.zhongruan.template.service;

import com.alibaba.fastjson.JSONObject;
import com.zhongruan.template.dao.TemplateInfoMapper;
import com.zhongruan.template.entity.TemplateInfo;
import com.zhongruan.template.entity.TemplateInfoExample;
import com.zhongruan.template.massage.ResultData;
import com.zhongruan.template.util.FileUtil;
import com.zhongruan.template.vo.Constant;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestBody;

import java.io.File;
import java.util.List;

/**
 * @Author: aiying014
 * Created by 17578 on 18:18 2020/8/2.
 * @Description:
 */
@Service
@Slf4j
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
    public TemplateInfo findById(int templateId){
	    TemplateInfoExample templateInfoExample =new TemplateInfoExample();
        TemplateInfoExample.Criteria criteria = templateInfoExample.createCriteria();
        criteria.andIdEqualTo(templateId);
        List<TemplateInfo> templateInfos =null;
        try{
            templateInfos = templateInfoMapper.selectByExample(templateInfoExample);
        }catch(Exception e){

        }
        return templateInfos.get(0);
    }

    public ResultData getHTMLFileContext(int templateId){
		final TemplateInfoExample templateInfoExample = new TemplateInfoExample();
		templateInfoExample.createCriteria().andIdEqualTo(templateId);
		final List<TemplateInfo> templateInfos = templateInfoMapper.selectByExample(templateInfoExample);
		final TemplateInfo templateInfo = templateInfos.get(0);
		final String fileContent = FileUtil.readFileContent(templateInfo.getTemplateHtmlUrl());
		final JSONObject object = new JSONObject();
		object.put("html_context",fileContent);
		return ResultData.success(object);
	}


    public ResultData deleteById(int templateId){
		String message = Constant.ERROR;
		try {
			final TemplateInfoExample templateInfoExample = new TemplateInfoExample();
			templateInfoExample.createCriteria().andIdEqualTo(templateId);
			final int delete = templateInfoMapper.deleteByExample(templateInfoExample);
			if (delete != 1){
				message = "数据不存在";
			}
		}catch (Exception e){
			log.error("执行 删除 模板异常：{}",e.getMessage());
			message = e.getMessage();
		}

		return ResultData.success(message);
	}
}
