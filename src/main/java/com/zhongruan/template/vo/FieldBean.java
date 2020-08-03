/**
 * @Copyright Beijing Jiangrongxin Technology Co,.Ltd 2020.
 * <p>
 * This material is the property of Beijing Jiangrongxin Technology Co,. Ltd.
 * and the information contained herein is confidential. This material,
 * either in whole or in part, must not be reproduced or disclosed to others
 * or used for purposes other than that for which it has been supplied without
 * Beijing Jiangrongxin's prior written permission,
 * or, if any part hereof is furnished by virtue of contract with a third party,
 * as expressly authorized under that contract.
 * <p>
 * *****************************************************************************
 * Date             Author      Version       Description
 * 2020/8/3         ${Author}  1.0.0         ${DESCRIPTION}
 * *****************************************************************************
 */
package com.zhongruan.template.vo;

/**
 * @author zhenxu.guan
 * @Date 2020/8/3 11:37
 */
public class FieldBean {
    private String fieldName;
    private String fieldDes;
    private String fieldType;
    public String getFieldName() {
        return fieldName;
    }

    public void setFieldName(String fieldName) {
        this.fieldName = fieldName;
    }

    public String getFieldDes() {
        return fieldDes;
    }

    public void setFieldDes(String fieldDes) {
        this.fieldDes = fieldDes;
    }

    public String getFieldType() {
        return fieldType;
    }

    public void setFieldType(String fieldType) {
        this.fieldType = fieldType;
    }

    @Override
    public String toString() {
        return "FieldBean{" +
                "fieldName='" + fieldName + '\'' +
                ", fieldDes='" + fieldDes + '\'' +
                ", fieldType='" + fieldType + '\'' +
                '}';
    }
}
