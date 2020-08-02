package com.zhongruan.template.dao;

import com.zhongruan.template.entity.DatabaseInfo;
import com.zhongruan.template.entity.DatabaseInfoExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface DatabaseInfoMapper {
    long countByExample(DatabaseInfoExample example);

    int deleteByExample(DatabaseInfoExample example);

    int insert(DatabaseInfo record);

    int insertSelective(DatabaseInfo record);

    List<DatabaseInfo> selectByExample(DatabaseInfoExample example);

    int updateByExampleSelective(@Param("record") DatabaseInfo record, @Param("example") DatabaseInfoExample example);

    int updateByExample(@Param("record") DatabaseInfo record, @Param("example") DatabaseInfoExample example);
}