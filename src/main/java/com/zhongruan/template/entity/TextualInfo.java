package com.zhongruan.template.entity;

import java.util.Date;

public class TextualInfo {
    private Integer id;

    private Integer templateId;

    private String textualName;

    private String textualUrl;

    private Date createdTime;

    private Date updatedTime;

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

    public String getTextualName() {
        return textualName;
    }

    public void setTextualName(String textualName) {
        this.textualName = textualName == null ? null : textualName.trim();
    }

    public String getTextualUrl() {
        return textualUrl;
    }

    public void setTextualUrl(String textualUrl) {
        this.textualUrl = textualUrl == null ? null : textualUrl.trim();
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