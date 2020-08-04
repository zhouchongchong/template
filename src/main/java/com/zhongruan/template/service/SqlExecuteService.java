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
package com.zhongruan.template.service;

import com.zhongruan.template.entity.DatabaseInfo;
import com.zhongruan.template.vo.FieldBean;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Service;
import sun.rmi.runtime.Log;

import java.sql.*;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @author zhenxu.guan
 * @Date 2020/8/3 16:26
 */
@Service
@Slf4j
public class SqlExecuteService {
	@Autowired
	DatabaseInfoService databaseInfoService;


	public List<Map<String, Object>> sqlExecute(String sql,int dbSourceId) throws Exception {
		DatabaseInfo databaseInfo = databaseInfoService.findById(dbSourceId);
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
		Connection con = null;
		PreparedStatement ps = null;
		ResultSet result = null;
		List list = new ArrayList();


		try {
			Class.forName(driverClass);
			con = DriverManager.getConnection(url, user, pwd);
			if (con.isClosed()) {
				con = DriverManager.getConnection(url, user, pwd);
			}
			//创建prepareStatement对象，用于执行SQL
			ps = con.prepareStatement(sql);
			result = ps.executeQuery();
			ResultSetMetaData md = result.getMetaData(); //获得结果集结构信息,元数据
			int columnCount = md.getColumnCount();   //获得列数
			while (result.next()) {
				Map<String, Object> rowData = new HashMap();
				for (int i = 1; i <= columnCount; i++) {
					rowData.put(md.getColumnName(i), result.getObject(i));
				}
				list.add(rowData);
			}
			log.info("data:{}", list);
		} catch (SQLException e) {
			list = null;
			log.error("execute sql err:{}", e.getMessage());
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
		return list;
	}


}
