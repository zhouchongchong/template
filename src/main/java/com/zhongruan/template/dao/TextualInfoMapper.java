package com.zhongruan.template.dao;

import com.zhongruan.template.entity.TextualInfo;
import com.zhongruan.template.entity.TextualInfoExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface TextualInfoMapper {
    long countByExample(TextualInfoExample example);

    int deleteByExample(TextualInfoExample example);

    int deleteByPrimaryKey(Integer id);

    int insert(TextualInfo record);

    int insertSelective(TextualInfo record);

    List<TextualInfo> selectByExample(TextualInfoExample example);

    TextualInfo selectByPrimaryKey(Integer id);

    int updateByExampleSelective(@Param("record") TextualInfo record, @Param("example") TextualInfoExample example);

    int updateByExample(@Param("record") TextualInfo record, @Param("example") TextualInfoExample example);

    int updateByPrimaryKeySelective(TextualInfo record);

    int updateByPrimaryKey(TextualInfo record);
}