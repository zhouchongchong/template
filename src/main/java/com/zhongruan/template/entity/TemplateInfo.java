package com.zhongruan.template.entity;

import java.util.Date;

public class TemplateInfo {
    private Integer id;

    private String templateName;

    private Date uploadTime;

    private String templateHtmlUrl;

    private String templateFtlUrl;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getTemplateName() {
        return templateName;
    }

    public void setTemplateName(String templateName) {
        this.templateName = templateName == null ? null : templateName.trim();
    }

    public Date getUploadTime() {
        return uploadTime;
    }

    public void setUploadTime(Date uploadTime) {
        this.uploadTime = uploadTime;
    }

    public String getTemplateHtmlUrl() {
        return templateHtmlUrl;
    }

    public void setTemplateHtmlUrl(String templateHtmlUrl) {
        this.templateHtmlUrl = templateHtmlUrl == null ? null : templateHtmlUrl.trim();
    }

    public String getTemplateFtlUrl() {
        return templateFtlUrl;
    }

    public void setTemplateFtlUrl(String templateFtlUrl) {
        this.templateFtlUrl = templateFtlUrl == null ? null : templateFtlUrl.trim();
    }
}