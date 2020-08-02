package com.zhongruan.template.mapper;

import com.zhongruan.template.example.DatabaseInfoExample;
import com.zhongruan.template.entity.DatabaseInfo;

import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface DatabaseInfoMapper {
    int countByExample(DatabaseInfoExample example);

    int deleteByExample(DatabaseInfoExample example);

    int insert(DatabaseInfo record);

    int insertSelective(DatabaseInfo record);

    List<DatabaseInfo> selectByExample(DatabaseInfoExample example);

    int updateByExampleSelective(@Param("record") DatabaseInfo record, @Param("example") DatabaseInfoExample example);

    int updateByExample(@Param("record") DatabaseInfo record, @Param("example") DatabaseInfoExample example);
}