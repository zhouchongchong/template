package com.zhongruan.template.dao;

import com.zhongruan.template.entity.TextualInfo;
import com.zhongruan.template.entity.TextualInfoExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface TextualInfoMapper {
    long countByExample(TextualInfoExample example);

    int deleteByExample(TextualInfoExample example);

    int insert(TextualInfo record);

    int insertSelective(TextualInfo record);

    List<TextualInfo> selectByExample(TextualInfoExample example);

    int updateByExampleSelective(@Param("record") TextualInfo record, @Param("example") TextualInfoExample example);

    int updateByExample(@Param("record") TextualInfo record, @Param("example") TextualInfoExample example);
}