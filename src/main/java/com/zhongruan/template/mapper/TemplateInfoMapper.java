package com.zhongruan.template.mapper;

import com.zhongruan.template.example.TemplateInfoExample;
import com.zhongruan.template.entity.TemplateInfo;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
   @Mapper
public interface TemplateInfoMapper {
    int countByExample(TemplateInfoExample example);

    int deleteByExample(TemplateInfoExample example);

    int insert(TemplateInfo record);

    int insertSelective(TemplateInfo record);

    List<TemplateInfo> selectByExample(TemplateInfoExample example);

       List<TemplateInfo> findAll();

    int updateByExampleSelective(@Param("record") TemplateInfo record, @Param("example") TemplateInfoExample example);

    int updateByExample(@Param("record") TemplateInfo record, @Param("example") TemplateInfoExample example);
}