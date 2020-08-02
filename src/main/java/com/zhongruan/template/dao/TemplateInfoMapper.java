package com.zhongruan.template.dao;

import com.zhongruan.template.entity.TemplateInfo;
import com.zhongruan.template.entity.TemplateInfoExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface TemplateInfoMapper {
    long countByExample(TemplateInfoExample example);

    int deleteByExample(TemplateInfoExample example);

    int insert(TemplateInfo record);

    int insertSelective(TemplateInfo record);

    List<TemplateInfo> selectByExample(TemplateInfoExample example);

    int updateByExampleSelective(@Param("record") TemplateInfo record, @Param("example") TemplateInfoExample example);

    int updateByExample(@Param("record") TemplateInfo record, @Param("example") TemplateInfoExample example);
}