package com.zhongruan.template.dao;

import com.zhongruan.template.entity.DatabaseInfo;
import com.zhongruan.template.entity.DatabaseInfoExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface DatabaseInfoMapper {
    long countByExample(DatabaseInfoExample example);

    int deleteByExample(DatabaseInfoExample example);

    int deleteByPrimaryKey(Integer id);

    int insert(DatabaseInfo record);

    int insertSelective(DatabaseInfo record);

    List<DatabaseInfo> selectByExample(DatabaseInfoExample example);

    DatabaseInfo selectByPrimaryKey(Integer id);

    int updateByExampleSelective(@Param("record") DatabaseInfo record, @Param("example") DatabaseInfoExample example);

    int updateByExample(@Param("record") DatabaseInfo record, @Param("example") DatabaseInfoExample example);

    int updateByPrimaryKeySelective(DatabaseInfo record);

    int updateByPrimaryKey(DatabaseInfo record);
}