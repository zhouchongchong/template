
package com.zhongruan.template.service;

import com.zhongruan.template.dao.DatabaseInfoMapper;
import com.zhongruan.template.entity.DatabaseInfo;
import com.zhongruan.template.entity.DatabaseInfoExample;
import com.zhongruan.template.vo.FieldBean;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.sql.*;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

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
        criteria.andDatabaseNameEqualTo(databaseName);
        List<DatabaseInfo> databaseInfos = null;
        try {
            databaseInfos = databaseInfoMapper.selectByExample(databaseInfoExample);
        } catch (Exception e) {

        }
        return databaseInfos.get(0);

    }


    public Map listAlltable(DatabaseInfo databaseInfo) throws Exception {
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

        Map<String,List<FieldBean>> map=new HashMap<>();
        try {
            Class.forName(driverClass);
            con = DriverManager.getConnection(url, user, pwd);
            if (con.isClosed()) {
                con = DriverManager.getConnection(url, user, pwd);
            }
            dbMetaData = con.getMetaData();
            String[] types = {"TABLE"};
            //获取一个库下所有的表名对象
            ResultSet rs = dbMetaData.getTables(catalog, schemaName, "%", types);
            String tableName =null;
            //遍历resultSet对象
            while (rs.next()) {
                List<FieldBean> list=new ArrayList<>();
                //分别获取表名
                tableName=rs.getString("TABLE_NAME");
                //获取指定表的ResultSet对象
                ResultSet resultSet = dbMetaData.getColumns(catalog, null, tableName, null);

                while (resultSet.next()) {
                    FieldBean fieldBean=new FieldBean();
                    //获取字段名称
                    String name = resultSet.getString("COLUMN_NAME");
                    String columnType = resultSet.getString("TYPE_NAME");

                    fieldBean.setFieldName(name);
                    fieldBean.setFieldType(columnType);
                    fieldBean.setFieldDes(resultSet.getString("REMARKS"));
                    list.add(fieldBean);
                }
                map.put(tableName,list);
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
        return map;
    }
}


