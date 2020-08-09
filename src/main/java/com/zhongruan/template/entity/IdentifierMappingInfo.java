package com.zhongruan.template.entity;

import java.util.Date;

public class IdentifierMappingInfo {
    private Integer id;

    private Integer templateId;

    private String identifierName;

    private String sqlContext;

    private Date createdTime;

    private Date updatedTime;

    private String identifierUnion;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getTemplateId() {
        return templateId;
    }

    public void setTemplateId(Integer templateId) {
        this.templateId = templateId;
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

    public String getIdentifierUnion() {
        return identifierUnion;
    }

    public void setIdentifierUnion(String identifierUnion) {
        this.identifierUnion = identifierUnion == null ? null : identifierUnion.trim();
    }
}