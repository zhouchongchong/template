package com.zhongruan.template.entity;

import java.util.Date;

public class IdentifierMappingInfo {
    private Integer id;

    private String identifierName;

    private String sqlContext;

    private Date createdTime;

    private Date updatedTime;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getIdentifierName() {
        return identifierName;
    }

    public void setIdentifierName(String identifierName) {
        this.identifierName = identifierName == null ? null : identifierName.trim();
    }

    public String getSqlContext() {
        return sqlContext;
    }

    public void setSqlContext(String sqlContext) {
        this.sqlContext = sqlContext == null ? null : sqlContext.trim();
    }

    public Date getCreatedTime() {
        return createdTime;
    }

    public void setCreatedTime(Date createdTime) {
        this.createdTime = createdTime;
    }

    public Date getUpdatedTime() {
        return updatedTime;
    }

    public void setUpdatedTime(Date updatedTime) {
        this.updatedTime = updatedTime;
    }
}