package com.zhongruan.template.service;

import com.alibaba.fastjson.JSONObject;
import com.zhongruan.template.dao.IdentifierMappingInfoMapper;
import com.zhongruan.template.entity.DatabaseInfo;
import com.zhongruan.template.entity.IdentifierMappingInfo;
import com.zhongruan.template.entity.IdentifierMappingInfoExample;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
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
    @Autowired
	IdentifierMappingInfoMapper identifierMappingInfoMapper;

	public Map<String, Object> sqlExecute( int id, String sql, int dbSourceId) throws Exception {
		DatabaseInfo databaseInfo = databaseInfoService.findById(dbSourceId, 0).get(0);
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
		Map<String, Object> resultMap = new HashMap<>();
		try {
			Class.forName(driverClass);
			con = DriverManager.getConnection(url, user, pwd);
			if (con.isClosed()) {
				con = DriverManager.getConnection(url, user, pwd);
			}
			//获取映射表对象
			final IdentifierMappingInfo identifierMappingInfo = identifierMappingInfoMapper.selectByPrimaryKey(id);
			//通过映射表得到identifier_union
			final String identifierUnion = identifierMappingInfo.getIdentifierUnion();
             IdentifierMappingInfoExample identifierMappingInfoExample=new IdentifierMappingInfoExample();
             identifierMappingInfoExample.createCriteria().andIdentifierUnionEqualTo(identifierUnion);
             //得到当前相关联的标识符对象
			final List<IdentifierMappingInfo> identifierMappingInfos = identifierMappingInfoMapper.selectByExample(identifierMappingInfoExample);

				ps = con.prepareStatement(sql);
				result = ps.executeQuery();
				ResultSetMetaData md = result.getMetaData(); //获得结果集结构信息,元数据
				int columnCount = md.getColumnCount();   //获得列数
				while (result.next()) {
					for (int i = 1; i <= columnCount; i++) {
						final IdentifierMappingInfo identifierMappingInfo1 = identifierMappingInfos.get(i - 1);
						resultMap.put(identifierMappingInfo1.getIdentifierName(),result.getObject(i));
				}
			}
			log.info("data:{}", resultMap);
		} catch (SQLException e) {
			resultMap = null;
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
		return resultMap;
	}

	//sql测试接口
	public Map<String, Object> sqlTest(List<String> identifierList, String sql, int dbSourceId) throws Exception {
		DatabaseInfo databaseInfo = databaseInfoService.findById(dbSourceId,0).get(0);
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
		Map<String,Object> resultMap=new HashMap<>();
		try {
			Class.forName(driverClass);
			con = DriverManager.getConnection(url, user, pwd);
			if (con.isClosed()) {
				con = DriverManager.getConnection(url, user, pwd);
					}
			ps = con.prepareStatement(sql);
			//进行结果查询
			result = ps.executeQuery();
			ResultSetMetaData md = result.getMetaData(); //获得结果集结构信息
			//获得列数
			int columnCount = md.getColumnCount();
			if(identifierList.size()!=columnCount){
				log.error("sql查询结果与X下标不一致");
				throw new Exception("sql查询结果与X下标不一致");
			}

			while (result.next()) {
				for (int i = 1; i <= columnCount; i++) {
					resultMap.put(identifierList.get(i - 1), result.getObject(i));
					log.info("data:{}", resultMap);
				}
			}
		} catch (SQLException e) {
			resultMap = null;
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
		return resultMap;
	}

}
