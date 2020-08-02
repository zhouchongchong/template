package com.zhongruan.template.entity;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class IdentifierMappingInfoExample {
    protected String orderByClause;

    protected boolean distinct;

    protected List<Criteria> oredCriteria;

    public IdentifierMappingInfoExample() {
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

        public Criteria andIdentifierNameIsNull() {
            addCriterion("Identifier_name is null");
            return (Criteria) this;
        }

        public Criteria andIdentifierNameIsNotNull() {
            addCriterion("Identifier_name is not null");
            return (Criteria) this;
        }

        public Criteria andIdentifierNameEqualTo(String value) {
            addCriterion("Identifier_name =", value, "identifierName");
            return (Criteria) this;
        }

        public Criteria andIdentifierNameNotEqualTo(String value) {
            addCriterion("Identifier_name <>", value, "identifierName");
            return (Criteria) this;
        }

        public Criteria andIdentifierNameGreaterThan(String value) {
            addCriterion("Identifier_name >", value, "identifierName");
            return (Criteria) this;
        }

        public Criteria andIdentifierNameGreaterThanOrEqualTo(String value) {
            addCriterion("Identifier_name >=", value, "identifierName");
            return (Criteria) this;
        }

        public Criteria andIdentifierNameLessThan(String value) {
            addCriterion("Identifier_name <", value, "identifierName");
            return (Criteria) this;
        }

        public Criteria andIdentifierNameLessThanOrEqualTo(String value) {
            addCriterion("Identifier_name <=", value, "identifierName");
            return (Criteria) this;
        }

        public Criteria andIdentifierNameLike(String value) {
            addCriterion("Identifier_name like", value, "identifierName");
            return (Criteria) this;
        }

        public Criteria andIdentifierNameNotLike(String value) {
            addCriterion("Identifier_name not like", value, "identifierName");
            return (Criteria) this;
        }

        public Criteria andIdentifierNameIn(List<String> values) {
            addCriterion("Identifier_name in", values, "identifierName");
            return (Criteria) this;
        }

        public Criteria andIdentifierNameNotIn(List<String> values) {
            addCriterion("Identifier_name not in", values, "identifierName");
            return (Criteria) this;
        }

        public Criteria andIdentifierNameBetween(String value1, String value2) {
            addCriterion("Identifier_name between", value1, value2, "identifierName");
            return (Criteria) this;
        }

        public Criteria andIdentifierNameNotBetween(String value1, String value2) {
            addCriterion("Identifier_name not between", value1, value2, "identifierName");
            return (Criteria) this;
        }

        public Criteria andSqlContextIsNull() {
            addCriterion("sql_context is null");
            return (Criteria) this;
        }

        public Criteria andSqlContextIsNotNull() {
            addCriterion("sql_context is not null");
            return (Criteria) this;
        }

        public Criteria andSqlContextEqualTo(String value) {
            addCriterion("sql_context =", value, "sqlContext");
            return (Criteria) this;
        }

        public Criteria andSqlContextNotEqualTo(String value) {
            addCriterion("sql_context <>", value, "sqlContext");
            return (Criteria) this;
        }

        public Criteria andSqlContextGreaterThan(String value) {
            addCriterion("sql_context >", value, "sqlContext");
            return (Criteria) this;
        }

        public Criteria andSqlContextGreaterThanOrEqualTo(String value) {
            addCriterion("sql_context >=", value, "sqlContext");
            return (Criteria) this;
        }

        public Criteria andSqlContextLessThan(String value) {
            addCriterion("sql_context <", value, "sqlContext");
            return (Criteria) this;
        }

        public Criteria andSqlContextLessThanOrEqualTo(String value) {
            addCriterion("sql_context <=", value, "sqlContext");
            return (Criteria) this;
        }

        public Criteria andSqlContextLike(String value) {
            addCriterion("sql_context like", value, "sqlContext");
            return (Criteria) this;
        }

        public Criteria andSqlContextNotLike(String value) {
            addCriterion("sql_context not like", value, "sqlContext");
            return (Criteria) this;
        }

        public Criteria andSqlContextIn(List<String> values) {
            addCriterion("sql_context in", values, "sqlContext");
            return (Criteria) this;
        }

        public Criteria andSqlContextNotIn(List<String> values) {
            addCriterion("sql_context not in", values, "sqlContext");
            return (Criteria) this;
        }

        public Criteria andSqlContextBetween(String value1, String value2) {
            addCriterion("sql_context between", value1, value2, "sqlContext");
            return (Criteria) this;
        }

        public Criteria andSqlContextNotBetween(String value1, String value2) {
            addCriterion("sql_context not between", value1, value2, "sqlContext");
            return (Criteria) this;
        }

        public Criteria andCreatedTimeIsNull() {
            addCriterion("created_time is null");
            return (Criteria) this;
        }

        public Criteria andCreatedTimeIsNotNull() {
            addCriterion("created_time is not null");
            return (Criteria) this;
        }

        public Criteria andCreatedTimeEqualTo(Date value) {
            addCriterion("created_time =", value, "createdTime");
            return (Criteria) this;
        }

        public Criteria andCreatedTimeNotEqualTo(Date value) {
            addCriterion("created_time <>", value, "createdTime");
            return (Criteria) this;
        }

        public Criteria andCreatedTimeGreaterThan(Date value) {
            addCriterion("created_time >", value, "createdTime");
            return (Criteria) this;
        }

        public Criteria andCreatedTimeGreaterThanOrEqualTo(Date value) {
            addCriterion("created_time >=", value, "createdTime");
            return (Criteria) this;
        }

        public Criteria andCreatedTimeLessThan(Date value) {
            addCriterion("created_time <", value, "createdTime");
            return (Criteria) this;
        }

        public Criteria andCreatedTimeLessThanOrEqualTo(Date value) {
            addCriterion("created_time <=", value, "createdTime");
            return (Criteria) this;
        }

        public Criteria andCreatedTimeIn(List<Date> values) {
            addCriterion("created_time in", values, "createdTime");
            return (Criteria) this;
        }

        public Criteria andCreatedTimeNotIn(List<Date> values) {
            addCriterion("created_time not in", values, "createdTime");
            return (Criteria) this;
        }

        public Criteria andCreatedTimeBetween(Date value1, Date value2) {
            addCriterion("created_time between", value1, value2, "createdTime");
            return (Criteria) this;
        }

        public Criteria andCreatedTimeNotBetween(Date value1, Date value2) {
            addCriterion("created_time not between", value1, value2, "createdTime");
            return (Criteria) this;
        }

        public Criteria andUpdatedTimeIsNull() {
            addCriterion("updated_time is null");
            return (Criteria) this;
        }

        public Criteria andUpdatedTimeIsNotNull() {
            addCriterion("updated_time is not null");
            return (Criteria) this;
        }

        public Criteria andUpdatedTimeEqualTo(Date value) {
            addCriterion("updated_time =", value, "updatedTime");
            return (Criteria) this;
        }

        public Criteria andUpdatedTimeNotEqualTo(Date value) {
            addCriterion("updated_time <>", value, "updatedTime");
            return (Criteria) this;
        }

        public Criteria andUpdatedTimeGreaterThan(Date value) {
            addCriterion("updated_time >", value, "updatedTime");
            return (Criteria) this;
        }

        public Criteria andUpdatedTimeGreaterThanOrEqualTo(Date value) {
            addCriterion("updated_time >=", value, "updatedTime");
            return (Criteria) this;
        }

        public Criteria andUpdatedTimeLessThan(Date value) {
            addCriterion("updated_time <", value, "updatedTime");
            return (Criteria) this;
        }

        public Criteria andUpdatedTimeLessThanOrEqualTo(Date value) {
            addCriterion("updated_time <=", value, "updatedTime");
            return (Criteria) this;
        }

        public Criteria andUpdatedTimeIn(List<Date> values) {
            addCriterion("updated_time in", values, "updatedTime");
            return (Criteria) this;
        }

        public Criteria andUpdatedTimeNotIn(List<Date> values) {
            addCriterion("updated_time not in", values, "updatedTime");
            return (Criteria) this;
        }

        public Criteria andUpdatedTimeBetween(Date value1, Date value2) {
            addCriterion("updated_time between", value1, value2, "updatedTime");
            return (Criteria) this;
        }

        public Criteria andUpdatedTimeNotBetween(Date value1, Date value2) {
            addCriterion("updated_time not between", value1, value2, "updatedTime");
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