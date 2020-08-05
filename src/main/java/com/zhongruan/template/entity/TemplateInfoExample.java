package com.zhongruan.template.entity;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class TemplateInfoExample {
    protected String orderByClause;

    protected boolean distinct;

    protected List<Criteria> oredCriteria;

    public TemplateInfoExample() {
        oredCriteria = new ArrayList<Criteria>();
    }

    public void setOrderByClause(String orderByClause) {
        this.orderByClause = orderByClause;
    }

    public String getOrderByClause() {
        return orderByClause;
    }

    public void setDistinct(boolean distinct) {
        this.distinct = distinct;
    }

    public boolean isDistinct() {
        return distinct;
    }

    public List<Criteria> getOredCriteria() {
        return oredCriteria;
    }

    public void or(Criteria criteria) {
        oredCriteria.add(criteria);
    }

    public Criteria or() {
        Criteria criteria = createCriteriaInternal();
        oredCriteria.add(criteria);
        return criteria;
    }

    public Criteria createCriteria() {
        Criteria criteria = createCriteriaInternal();
        if (oredCriteria.size() == 0) {
            oredCriteria.add(criteria);
        }
        return criteria;
    }

    protected Criteria createCriteriaInternal() {
        Criteria criteria = new Criteria();
        return criteria;
    }

    public void clear() {
        oredCriteria.clear();
        orderByClause = null;
        distinct = false;
    }

    protected abstract static class GeneratedCriteria {
        protected List<Criterion> criteria;

        protected GeneratedCriteria() {
            super();
            criteria = new ArrayList<Criterion>();
        }

        public boolean isValid() {
            return criteria.size() > 0;
        }

        public List<Criterion> getAllCriteria() {
            return criteria;
        }

        public List<Criterion> getCriteria() {
            return criteria;
        }

        protected void addCriterion(String condition) {
            if (condition == null) {
                throw new RuntimeException("Value for condition cannot be null");
            }
            criteria.add(new Criterion(condition));
        }

        protected void addCriterion(String condition, Object value, String property) {
            if (value == null) {
                throw new RuntimeException("Value for " + property + " cannot be null");
            }
            criteria.add(new Criterion(condition, value));
        }

        protected void addCriterion(String condition, Object value1, Object value2, String property) {
            if (value1 == null || value2 == null) {
                throw new RuntimeException("Between values for " + property + " cannot be null");
            }
            criteria.add(new Criterion(condition, value1, value2));
        }

        public Criteria andIdIsNull() {
            addCriterion("id is null");
            return (Criteria) this;
        }

        public Criteria andIdIsNotNull() {
            addCriterion("id is not null");
            return (Criteria) this;
        }

        public Criteria andIdEqualTo(Integer value) {
            addCriterion("id =", value, "id");
            return (Criteria) this;
        }

        public Criteria andIdNotEqualTo(Integer value) {
            addCriterion("id <>", value, "id");
            return (Criteria) this;
        }

        public Criteria andIdGreaterThan(Integer value) {
            addCriterion("id >", value, "id");
            return (Criteria) this;
        }

        public Criteria andIdGreaterThanOrEqualTo(Integer value) {
            addCriterion("id >=", value, "id");
            return (Criteria) this;
        }

        public Criteria andIdLessThan(Integer value) {
            addCriterion("id <", value, "id");
            return (Criteria) this;
        }

        public Criteria andIdLessThanOrEqualTo(Integer value) {
            addCriterion("id <=", value, "id");
            return (Criteria) this;
        }

        public Criteria andIdIn(List<Integer> values) {
            addCriterion("id in", values, "id");
            return (Criteria) this;
        }

        public Criteria andIdNotIn(List<Integer> values) {
            addCriterion("id not in", values, "id");
            return (Criteria) this;
        }

        public Criteria andIdBetween(Integer value1, Integer value2) {
            addCriterion("id between", value1, value2, "id");
            return (Criteria) this;
        }

        public Criteria andIdNotBetween(Integer value1, Integer value2) {
            addCriterion("id not between", value1, value2, "id");
            return (Criteria) this;
        }

        public Criteria andTemplateNameIsNull() {
            addCriterion("template_name is null");
            return (Criteria) this;
        }

        public Criteria andTemplateNameIsNotNull() {
            addCriterion("template_name is not null");
            return (Criteria) this;
        }

        public Criteria andTemplateNameEqualTo(String value) {
            addCriterion("template_name =", value, "templateName");
            return (Criteria) this;
        }

        public Criteria andTemplateNameNotEqualTo(String value) {
            addCriterion("template_name <>", value, "templateName");
            return (Criteria) this;
        }

        public Criteria andTemplateNameGreaterThan(String value) {
            addCriterion("template_name >", value, "templateName");
            return (Criteria) this;
        }

        public Criteria andTemplateNameGreaterThanOrEqualTo(String value) {
            addCriterion("template_name >=", value, "templateName");
            return (Criteria) this;
        }

        public Criteria andTemplateNameLessThan(String value) {
            addCriterion("template_name <", value, "templateName");
            return (Criteria) this;
        }

        public Criteria andTemplateNameLessThanOrEqualTo(String value) {
            addCriterion("template_name <=", value, "templateName");
            return (Criteria) this;
        }

        public Criteria andTemplateNameLike(String value) {
            addCriterion("template_name like", value, "templateName");
            return (Criteria) this;
        }

        public Criteria andTemplateNameNotLike(String value) {
            addCriterion("template_name not like", value, "templateName");
            return (Criteria) this;
        }

        public Criteria andTemplateNameIn(List<String> values) {
            addCriterion("template_name in", values, "templateName");
            return (Criteria) this;
        }

        public Criteria andTemplateNameNotIn(List<String> values) {
            addCriterion("template_name not in", values, "templateName");
            return (Criteria) this;
        }

        public Criteria andTemplateNameBetween(String value1, String value2) {
            addCriterion("template_name between", value1, value2, "templateName");
            return (Criteria) this;
        }

        public Criteria andTemplateNameNotBetween(String value1, String value2) {
            addCriterion("template_name not between", value1, value2, "templateName");
            return (Criteria) this;
        }

        public Criteria andUploadTimeIsNull() {
            addCriterion("upload_time is null");
            return (Criteria) this;
        }

        public Criteria andUploadTimeIsNotNull() {
            addCriterion("upload_time is not null");
            return (Criteria) this;
        }

        public Criteria andUploadTimeEqualTo(Date value) {
            addCriterion("upload_time =", value, "uploadTime");
            return (Criteria) this;
        }

        public Criteria andUploadTimeNotEqualTo(Date value) {
            addCriterion("upload_time <>", value, "uploadTime");
            return (Criteria) this;
        }

        public Criteria andUploadTimeGreaterThan(Date value) {
            addCriterion("upload_time >", value, "uploadTime");
            return (Criteria) this;
        }

        public Criteria andUploadTimeGreaterThanOrEqualTo(Date value) {
            addCriterion("upload_time >=", value, "uploadTime");
            return (Criteria) this;
        }

        public Criteria andUploadTimeLessThan(Date value) {
            addCriterion("upload_time <", value, "uploadTime");
            return (Criteria) this;
        }

        public Criteria andUploadTimeLessThanOrEqualTo(Date value) {
            addCriterion("upload_time <=", value, "uploadTime");
            return (Criteria) this;
        }

        public Criteria andUploadTimeIn(List<Date> values) {
            addCriterion("upload_time in", values, "uploadTime");
            return (Criteria) this;
        }

        public Criteria andUploadTimeNotIn(List<Date> values) {
            addCriterion("upload_time not in", values, "uploadTime");
            return (Criteria) this;
        }

        public Criteria andUploadTimeBetween(Date value1, Date value2) {
            addCriterion("upload_time between", value1, value2, "uploadTime");
            return (Criteria) this;
        }

        public Criteria andUploadTimeNotBetween(Date value1, Date value2) {
            addCriterion("upload_time not between", value1, value2, "uploadTime");
            return (Criteria) this;
        }

        public Criteria andTemplateHtmlUrlIsNull() {
            addCriterion("template_html_url is null");
            return (Criteria) this;
        }

        public Criteria andTemplateHtmlUrlIsNotNull() {
            addCriterion("template_html_url is not null");
            return (Criteria) this;
        }

        public Criteria andTemplateHtmlUrlEqualTo(String value) {
            addCriterion("template_html_url =", value, "templateHtmlUrl");
            return (Criteria) this;
        }

        public Criteria andTemplateHtmlUrlNotEqualTo(String value) {
            addCriterion("template_html_url <>", value, "templateHtmlUrl");
            return (Criteria) this;
        }

        public Criteria andTemplateHtmlUrlGreaterThan(String value) {
            addCriterion("template_html_url >", value, "templateHtmlUrl");
            return (Criteria) this;
        }

        public Criteria andTemplateHtmlUrlGreaterThanOrEqualTo(String value) {
            addCriterion("template_html_url >=", value, "templateHtmlUrl");
            return (Criteria) this;
        }

        public Criteria andTemplateHtmlUrlLessThan(String value) {
            addCriterion("template_html_url <", value, "templateHtmlUrl");
            return (Criteria) this;
        }

        public Criteria andTemplateHtmlUrlLessThanOrEqualTo(String value) {
            addCriterion("template_html_url <=", value, "templateHtmlUrl");
            return (Criteria) this;
        }

        public Criteria andTemplateHtmlUrlLike(String value) {
            addCriterion("template_html_url like", value, "templateHtmlUrl");
            return (Criteria) this;
        }

        public Criteria andTemplateHtmlUrlNotLike(String value) {
            addCriterion("template_html_url not like", value, "templateHtmlUrl");
            return (Criteria) this;
        }

        public Criteria andTemplateHtmlUrlIn(List<String> values) {
            addCriterion("template_html_url in", values, "templateHtmlUrl");
            return (Criteria) this;
        }

        public Criteria andTemplateHtmlUrlNotIn(List<String> values) {
            addCriterion("template_html_url not in", values, "templateHtmlUrl");
            return (Criteria) this;
        }

        public Criteria andTemplateHtmlUrlBetween(String value1, String value2) {
            addCriterion("template_html_url between", value1, value2, "templateHtmlUrl");
            return (Criteria) this;
        }

        public Criteria andTemplateHtmlUrlNotBetween(String value1, String value2) {
            addCriterion("template_html_url not between", value1, value2, "templateHtmlUrl");
            return (Criteria) this;
        }

        public Criteria andTemplateFtlUrlIsNull() {
            addCriterion("template_ftl_url is null");
            return (Criteria) this;
        }

        public Criteria andTemplateFtlUrlIsNotNull() {
            addCriterion("template_ftl_url is not null");
            return (Criteria) this;
        }

        public Criteria andTemplateFtlUrlEqualTo(String value) {
            addCriterion("template_ftl_url =", value, "templateFtlUrl");
            return (Criteria) this;
        }

        public Criteria andTemplateFtlUrlNotEqualTo(String value) {
            addCriterion("template_ftl_url <>", value, "templateFtlUrl");
            return (Criteria) this;
        }

        public Criteria andTemplateFtlUrlGreaterThan(String value) {
            addCriterion("template_ftl_url >", value, "templateFtlUrl");
            return (Criteria) this;
        }

        public Criteria andTemplateFtlUrlGreaterThanOrEqualTo(String value) {
            addCriterion("template_ftl_url >=", value, "templateFtlUrl");
            return (Criteria) this;
        }

        public Criteria andTemplateFtlUrlLessThan(String value) {
            addCriterion("template_ftl_url <", value, "templateFtlUrl");
            return (Criteria) this;
        }

        public Criteria andTemplateFtlUrlLessThanOrEqualTo(String value) {
            addCriterion("template_ftl_url <=", value, "templateFtlUrl");
            return (Criteria) this;
        }

        public Criteria andTemplateFtlUrlLike(String value) {
            addCriterion("template_ftl_url like", value, "templateFtlUrl");
            return (Criteria) this;
        }

        public Criteria andTemplateFtlUrlNotLike(String value) {
            addCriterion("template_ftl_url not like", value, "templateFtlUrl");
            return (Criteria) this;
        }

        public Criteria andTemplateFtlUrlIn(List<String> values) {
            addCriterion("template_ftl_url in", values, "templateFtlUrl");
            return (Criteria) this;
        }

        public Criteria andTemplateFtlUrlNotIn(List<String> values) {
            addCriterion("template_ftl_url not in", values, "templateFtlUrl");
            return (Criteria) this;
        }

        public Criteria andTemplateFtlUrlBetween(String value1, String value2) {
            addCriterion("template_ftl_url between", value1, value2, "templateFtlUrl");
            return (Criteria) this;
        }

        public Criteria andTemplateFtlUrlNotBetween(String value1, String value2) {
            addCriterion("template_ftl_url not between", value1, value2, "templateFtlUrl");
            return (Criteria) this;
        }

        public Criteria andDbSourceIdIsNull() {
            addCriterion("db_source_id is null");
            return (Criteria) this;
        }

        public Criteria andDbSourceIdIsNotNull() {
            addCriterion("db_source_id is not null");
            return (Criteria) this;
        }

        public Criteria andDbSourceIdEqualTo(Integer value) {
            addCriterion("db_source_id =", value, "dbSourceId");
            return (Criteria) this;
        }

        public Criteria andDbSourceIdNotEqualTo(Integer value) {
            addCriterion("db_source_id <>", value, "dbSourceId");
            return (Criteria) this;
        }

        public Criteria andDbSourceIdGreaterThan(Integer value) {
            addCriterion("db_source_id >", value, "dbSourceId");
            return (Criteria) this;
        }

        public Criteria andDbSourceIdGreaterThanOrEqualTo(Integer value) {
            addCriterion("db_source_id >=", value, "dbSourceId");
            return (Criteria) this;
        }

        public Criteria andDbSourceIdLessThan(Integer value) {
            addCriterion("db_source_id <", value, "dbSourceId");
            return (Criteria) this;
        }

        public Criteria andDbSourceIdLessThanOrEqualTo(Integer value) {
            addCriterion("db_source_id <=", value, "dbSourceId");
            return (Criteria) this;
        }

        public Criteria andDbSourceIdIn(List<Integer> values) {
            addCriterion("db_source_id in", values, "dbSourceId");
            return (Criteria) this;
        }

        public Criteria andDbSourceIdNotIn(List<Integer> values) {
            addCriterion("db_source_id not in", values, "dbSourceId");
            return (Criteria) this;
        }

        public Criteria andDbSourceIdBetween(Integer value1, Integer value2) {
            addCriterion("db_source_id between", value1, value2, "dbSourceId");
            return (Criteria) this;
        }

        public Criteria andDbSourceIdNotBetween(Integer value1, Integer value2) {
            addCriterion("db_source_id not between", value1, value2, "dbSourceId");
            return (Criteria) this;
        }
    }

    public static class Criteria extends GeneratedCriteria {

        protected Criteria() {
            super();
        }
    }

    public static class Criterion {
        private String condition;

        private Object value;

        private Object secondValue;

        private boolean noValue;

        private boolean singleValue;

        private boolean betweenValue;

        private boolean listValue;

        private String typeHandler;

        public String getCondition() {
            return condition;
        }

        public Object getValue() {
            return value;
        }

        public Object getSecondValue() {
            return secondValue;
        }

        public boolean isNoValue() {
            return noValue;
        }

        public boolean isSingleValue() {
            return singleValue;
        }

        public boolean isBetweenValue() {
            return betweenValue;
        }

        public boolean isListValue() {
            return listValue;
        }

        public String getTypeHandler() {
            return typeHandler;
        }

        protected Criterion(String condition) {
            super();
            this.condition = condition;
            this.typeHandler = null;
            this.noValue = true;
        }

        protected Criterion(String condition, Object value, String typeHandler) {
            super();
            this.condition = condition;
            this.value = value;
            this.typeHandler = typeHandler;
            if (value instanceof List<?>) {
                this.listValue = true;
            } else {
                this.singleValue = true;
            }
        }

        protected Criterion(String condition, Object value) {
            this(condition, value, null);
        }

        protected Criterion(String condition, Object value, Object secondValue, String typeHandler) {
            super();
            this.condition = condition;
            this.value = value;
            this.secondValue = secondValue;
            this.typeHandler = typeHandler;
            this.betweenValue = true;
        }

        protected Criterion(String condition, Object value, Object secondValue) {
            this(condition, value, secondValue, null);
        }
    }
}