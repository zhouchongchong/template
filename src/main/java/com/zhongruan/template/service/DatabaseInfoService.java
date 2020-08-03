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
 * 2020/8/2         ${Author}  1.0.0         ${DESCRIPTION}
 * *****************************************************************************
 */
package com.zhongruan.template.service;

import com.zhongruan.template.dao.DatabaseInfoMapper;
import com.zhongruan.template.entity.DatabaseInfo;
import com.zhongruan.template.entity.DatabaseInfoExample;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

/**
 * @author zhenxu.guan
 * @Date 2020/8/2 22:22
 */
@Service
public class DatabaseInfoService {
    @Autowired
    private DatabaseInfoMapper databaseInfoMapper;

    //用于获取数据源对象
    public DatabaseInfo findByName(String databaseName) {
        DatabaseInfoExample databaseInfoExample = new DatabaseInfoExample();

        DatabaseInfoExample.Criteria criteria = databaseInfoExample.createCriteria();
        databaseInfoExample.createCriteria().andDatabaseNameEqualTo(databaseName);
        List<DatabaseInfo> databaseInfos = null;
        try {
            databaseInfos = databaseInfoMapper.selectByExample(databaseInfoExample);
        } catch (Exception e) {

        }
        return databaseInfos.get(0);

    }


    public List<String> listAlltable(DatabaseInfo databaseInfo) throws Exception {
        String url = databaseInfo.getDatabaseUrl();
        String user = databaseInfo.getUsername();
        String pwd = databaseInfo.getPassword();
        String bdtype = databaseInfo.getDatabaseType();
        String driverClass = "";
        if ("mysql".equals(bdtype)) {
            driverClass = "com.mysql.jdbc.Driver";
        }
        if ("oracle".equals(bdtype)) {
            driverClass = "oracle.jdbc.driver.OracleDriver";
        }
        // 获取连接地址
        String[] urls = StringUtils.split(StringUtils.split(url, "?")[0], "/");
        String schemaName = urls[urls.length - 1];
        Connection con = null;
        DatabaseMetaData dbMetaData = null;
        String catalog = null;
        try {
            if (url.contains("mysql")) {
                catalog = url.split(":")[3].split("/")[1].split("\\?")[0];
            }
        } catch (Exception e) {
            throw new Exception("请添加数据库");
        }

        List list1 = new ArrayList<String>();
        try {
            Class.forName(driverClass);
            con = DriverManager.getConnection(url, user, pwd);
            if (con.isClosed()) {
                con = DriverManager.getConnection(url, user, pwd);
            }
            dbMetaData = con.getMetaData();
            String[] types = {"TABLE"};

            ResultSet rs = dbMetaData.getTables(catalog, schemaName, "%", types);
            while (rs.next()) {
                String tableName = rs.getString("TABLE_NAME"); // 表名
                String tableType = rs.getString("TABLE_TYPE"); // 表类型
                String remarks = rs.getString("REMARKS"); // 表备注
                list1.add(tableName);

            }
        } catch (SQLException e) {
            throw new Exception(e.getMessage());
        } finally {
            if (con != null) {
                try {
                    con.close();

                } catch (SQLException throwables) {
                    throwables.printStackTrace();
                }
            }
        }
        return list1;
    }
}


