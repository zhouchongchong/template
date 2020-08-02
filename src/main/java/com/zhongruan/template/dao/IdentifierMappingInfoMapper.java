package com.zhongruan.template.dao;

import com.zhongruan.template.entity.IdentifierMappingInfo;
import com.zhongruan.template.entity.IdentifierMappingInfoExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface IdentifierMappingInfoMapper {
    long countByExample(IdentifierMappingInfoExample example);

    int deleteByExample(IdentifierMappingInfoExample example);

    int insert(IdentifierMappingInfo record);

    int insertSelective(IdentifierMappingInfo record);

    List<IdentifierMappingInfo> selectByExample(IdentifierMappingInfoExample example);

    int updateByExampleSelective(@Param("record") IdentifierMappingInfo record, @Param("example") IdentifierMappingInfoExample example);

    int updateByExample(@Param("record") IdentifierMappingInfo record, @Param("example") IdentifierMappingInfoExample example);
}