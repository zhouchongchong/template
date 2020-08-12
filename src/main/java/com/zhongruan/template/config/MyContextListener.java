package com.zhongruan.template.config;

import com.mysql.cj.jdbc.AbandonedConnectionCleanupThread;
import org.springframework.stereotype.Component;

import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;
import java.sql.DriverManager;
import java.sql.SQLException;

/**
 * @Author: zhou
 * Created by zhou chong on 11:17 2020/8/12.
 * @Description:
 */
@Component
public class MyContextListener implements ServletContextListener {
	@Override
	public void contextInitialized(ServletContextEvent arg0) {
		System.out.println("webService start");
	}

	@Override
	public void contextDestroyed(ServletContextEvent arg0) {
		System.out.println("webService stop");
		try {
			while(DriverManager.getDrivers().hasMoreElements()) {
				DriverManager.deregisterDriver(DriverManager.getDrivers().nextElement());
			}
			System.out.println("jdbc Driver close");
			AbandonedConnectionCleanupThread.checkedShutdown();
			System.out.println("clean thread success");
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
}
