package com.zhongruan.template.dao;

import com.zhongruan.template.entity.TemplateInfo;
import com.zhongruan.template.entity.TemplateInfoExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface TemplateInfoMapper {
    long countByExample(TemplateInfoExample example);

    int deleteByExample(TemplateInfoExample example);

    int deleteByPrimaryKey(Integer id);

    int insert(TemplateInfo record);

    int insertSelective(TemplateInfo record);

    List<TemplateInfo> selectByExample(TemplateInfoExample example);

    TemplateInfo selectByPrimaryKey(Integer id);

    int updateByExampleSelective(@Param("record") TemplateInfo record, @Param("example") TemplateInfoExample example);

    int updateByExample(@Param("record") TemplateInfo record, @Param("example") TemplateInfoExample example);

    int updateByPrimaryKeySelective(TemplateInfo record);

    int updateByPrimaryKey(TemplateInfo record);
}