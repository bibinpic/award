package com.award.repository;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.Properties;

import javax.sql.DataSource;

import org.apache.commons.dbcp.DriverManagerConnectionFactory;
import org.apache.commons.dbcp.PoolableConnection;
import org.apache.commons.dbcp.PoolableConnectionFactory;
import org.apache.commons.dbcp.PoolingDataSource;
import org.apache.commons.pool.impl.GenericObjectPool;
import org.springframework.util.ResourceUtils;

public class ConnectionFactory {

	private static interface Singleton {
		final ConnectionFactory INSTANCE = new ConnectionFactory();
	}

	private final DataSource dataSource;

	private ConnectionFactory() {

		Properties application = new Properties();
		try {
			File file = ResourceUtils.getFile("classpath:application.properties");
			InputStream in = new FileInputStream(file);
			application.load(in);
		} catch (IOException e) {
		}
		Properties properties = new Properties();
		properties.setProperty("user", application.getProperty("spring.datasource.username"));
		properties.setProperty("password", application.getProperty("spring.datasource.password"));

		GenericObjectPool<PoolableConnection> pool = new GenericObjectPool<PoolableConnection>();

		DriverManagerConnectionFactory connectionFactory = new DriverManagerConnectionFactory(
				application.getProperty("spring.datasource.url"), properties);
		new PoolableConnectionFactory(connectionFactory, pool, null, "SELECT 1", 3, false, false,
				Connection.TRANSACTION_READ_COMMITTED);

		this.dataSource = new PoolingDataSource(pool);
	}

	public static Connection getDatabaseConnection() throws SQLException {
		return Singleton.INSTANCE.dataSource.getConnection();
	}

}