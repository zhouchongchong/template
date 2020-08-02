package com.zhongruan.template.mapper;

import com.zhongruan.template.entity.TemplateInfo;
import com.zhongruan.template.example.TextualInfoExample;
import com.zhongruan.template.entity.TextualInfo;

import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface TextualInfoMapper {
    int countByExample(TextualInfoExample example);

    int deleteByExample(TextualInfoExample example);

    int insert(TextualInfo record);

    int insertSelective(TextualInfo record);

    List<TextualInfo> selectByExample(TextualInfoExample example);

    int updateByExampleSelective(@Param("record") TextualInfo record, @Param("example") TextualInfoExample example);

    int updateByExample(@Param("record") TextualInfo record, @Param("example") TextualInfoExample example);

}