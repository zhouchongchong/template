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
		DatabaseInfo databaseInfo = databaseInfoService.findById(dbSourceId).get(0);
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
		if ("dm".equals(bdtype)) {
			driverClass = "dm.jdbc.driver.DmDriver";
		}
		if ("hive".equals(bdtype)) {
			driverClass = "org.apache.hive.jdbc.HiveDriver";
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
