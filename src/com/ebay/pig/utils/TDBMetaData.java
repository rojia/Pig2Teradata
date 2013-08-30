package com.ebay.pig.utils;

import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.Map;

import org.apache.hadoop.conf.Configuration;

public class TDBMetaData {
	private String db_url = "";
	private String username = "";
	private String password = "";
	private String tablename = "";
	
	private ResultSet rs = null;
	
	private Map<String, String> typeMap = null;
	
	private int columnCount = 0;
	
	public TDBMetaData() {
		typeMap = new HashMap<String, String>();
	}
	
	public TDBMetaData(String url, String username, String password, String tablename) {
		this();
		this.db_url = url;
		this.username = username;
		this.password = password;
		this.tablename = tablename;
	}
	
	public TDBMetaData(Configuration conf) {
		this();
		this.db_url = conf.get("db_rul");
		this.username = conf.get("username");
		this.password = conf.get("password");
		this.tablename = conf.get("tablename");
	}
	
	public Map<String, String> getTypeMap() {
		return typeMap;
	}
	
	public int getColumnCount() {
		return columnCount;
	}
	
	public void getMetaData() {
		String sql_str = "select * from " + this.tablename;
		TDBQueryExecutor query = new TDBQueryExecutor(db_url, username, password);
		query.connect();
		query.executeQuery(sql_str);
		rs = query.getResultset();
		
		try {
			ResultSetMetaData metadata = rs.getMetaData();
			columnCount = metadata.getColumnCount();
			
			for (int i = 0; i < columnCount; ++i) {
				String key = metadata.getColumnName(i);
				String value = metadata.getColumnTypeName(i);
				typeMap.put(key, value);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
	
	public String getColumnNames() {
		StringBuilder sb = new StringBuilder();
		
		for (int i = 0; i < columnCount; ++i) {
			if (i > 0 && i < (columnCount - 1)) {
				sb.append(" ,");
			}
			
			sb.append(typeMap.get(i));
		}
		
		return sb.toString();
	}
}
