package com.mark.test.framework.util;

import com.alibaba.fastjson.JSON;
import com.google.common.collect.Lists;
import com.mark.test.framework.api.dto.SQLConnectionDTO;
import org.apache.commons.dbcp.BasicDataSource;
import org.apache.ibatis.io.Resources;
import org.apache.ibatis.jdbc.ScriptRunner;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowCallbackHandler;
import org.springframework.jdbc.core.RowMapper;

import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.nio.charset.Charset;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;


public class DbFactory {
	private static final Logger logger = LoggerFactory.getLogger(DbFactory.class);
	private JdbcTemplate jdbcTemplate;
	private Connection connection;

	public DbFactory(SQLConnectionDTO config) {
		String url = config.getUrl();
		String userName = config.getUserName();
		String password = config.getPassword();
		String driver = config.getDriver();

		BasicDataSource dataSource = new BasicDataSource();
		dataSource.setUsername(userName);
		dataSource.setPassword(password);
		dataSource.setUrl(url);
		if (driver.contains("mysql")){
			logger.info("Init mysql instance successfully");
		}else if (driver.contains("oracle")){
			logger.info("Init oracle instance successfully");
		}
		dataSource.setDriverClassName(driver);
		dataSource.setMaxIdle(5);
		dataSource.setDefaultAutoCommit(true);
		dataSource.setMinIdle(1);
		try {
			connection = dataSource.getConnection();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		this.jdbcTemplate = new JdbcTemplate(dataSource);
	}

	/**
	 * 返回连接对象
	 * @return
     */
	public Connection getConnection(){
		return connection;
	}

	public void execute(String sql){
		logger.info("Execute sql: " + sql);
		jdbcTemplate.execute(sql);
	}

//	/**
//	 * 执行多个sql
//	 * @param sqlArray
//     */
//	public void execute(String[] sqlArray){
//		logger.info("Prepared execute sqls : {}", Arrays.toString(sqlArray));
//		jdbcTemplate.batchUpdate(sqlArray);
//	}

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

	/**
	 * 执行指定文件的sql
	 * @param filepaths
     */
	public void executeSqlFile(List<String> filepaths){
		List<String> faillist = Lists.newLinkedList();
		ScriptRunner scriptRunner = new ScriptRunner(this.getConnection());
		Resources.setCharset(Charset.forName("utf8"));
		scriptRunner.setLogWriter(null);
		scriptRunner.setAutoCommit(true);
		scriptRunner.setSendFullScript(true);
		scriptRunner.setEscapeProcessing(true);
		for (String f: filepaths) {
			try {
				logger.info("开始执行数据库文件: [{}]", f);
				File file = new File(f);
				FileReader fileReader = new FileReader(file);
				scriptRunner.runScript(fileReader);
			} catch (IOException e) {
				logger.error("读取文件异常 :{}",f);
				faillist.add(f);
				e.printStackTrace();
			}
		}
		logger.info("读取失败的文件为:{}",faillist);
		scriptRunner.closeConnection();
	}

	@Override
	public int hashCode() {
		return super.hashCode();
	}
}
