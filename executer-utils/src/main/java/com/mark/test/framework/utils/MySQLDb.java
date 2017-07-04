package com.mark.test.framework.utils;

import com.alibaba.fastjson.JSON;
import com.mark.test.framework.api.dto.SQLConnectionDTO;
import org.apache.commons.dbcp.BasicDataSource;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowCallbackHandler;
import org.springframework.jdbc.core.RowMapper;

import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;


public class MySQLDb {
	private static final Logger logger = LoggerFactory.getLogger(MySQLDb.class);
	
	private JdbcTemplate jdbcTemplate;

	public MySQLDb(SQLConnectionDTO config) {
		String url = config.getUrl();
		String userName = config.getUserName();
		String password = config.getPassword();
		String driver = config.getDriver();

		BasicDataSource dataSource = new BasicDataSource();
		dataSource.setUsername(userName);
		dataSource.setPassword(password);
		dataSource.setUrl(url);
		dataSource.setDriverClassName(driver);
		dataSource.setMaxIdle(5);
		dataSource.setDefaultAutoCommit(true);
		dataSource.setMinIdle(1);
		this.jdbcTemplate = new JdbcTemplate(dataSource);
	}

	public void execute(String sql){
		logger.info("Execute sql: " + sql);
		jdbcTemplate.execute(sql);
	}

	public Map<String, Object> queryOne(String sql) {
		List<Map<String, Object>> list = null;
		logger.info("Execute SQL to query result: " + sql);
		list = jdbcTemplate.query(sql,
				new RowMapper<Map<String, Object>>() {
					public Map<String, Object> mapRow(ResultSet resultSet, int rowIdex) throws SQLException {
						Map<String, Object> map = new HashMap<String, Object>();
						ResultSetMetaData metaData = resultSet.getMetaData();
						int colCount = metaData.getColumnCount();
						for (int index = 1; index <= colCount; index++) {
							String key = metaData.getColumnName(index);
							String value = resultSet.getString(index);
							if (value == null){
								value = "null";
							}
							map.put(key, value);
						}
						return map;
					}
				});
		
		if (list == null || list.size() == 0) {
			throw new RuntimeException("no result return from sql query");
		}
		
		if (list.size() != 1) {
			throw new RuntimeException("found multiple result from the sql query");
		}
		
		logger.info("SQL query result: " + JSON.toJSONString(list.get(0)));
		return list.get(0);
	}

	public void queryForList(String sql, Class<?> classId) {

	}

	public List<Map<String, Object>> queryForList(String sql) {
		final List<Map<String, Object>> dataList = new ArrayList<Map<String, Object>>();
		logger.info("Execute SQL to query: " + sql);
		jdbcTemplate.query(sql, new RowCallbackHandler() {
			public void processRow(ResultSet row) throws SQLException {
				Map<String, Object> rowMap = new HashMap<String, Object>();
				ResultSetMetaData metaData = row.getMetaData();
				int columnCount = metaData.getColumnCount();
				for (int index = 1; index <= columnCount; index++) {
					String key = metaData.getColumnName(index);
					String value = row.getString(index);
					if (value == null) {
						value = "null";
					}
					rowMap.put(key, value);
				}

				dataList.add(rowMap);
			}
		});
		
		logger.info("SQL Query result: " + JSON.toJSONString(dataList));
		return dataList;
	}


}
