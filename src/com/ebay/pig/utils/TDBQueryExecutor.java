package com.ebay.pig.utils;

import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class TDBQueryExecutor {
	private String db_url = "";
	private String user_account = "";
	private String password = "";
	
	private ResultSet db_resultset = null;
	private Statement db_statement = null;
	private Connection db_connection = null;
	
	public TDBQueryExecutor(String url, String useraccount, String password) {
		this.db_url = url;
		this.user_account = useraccount;
		this.password = password;
	}
	
	public ResultSet getResultset() {
		return db_resultset;
	}
	
	public ResultSet executeQuery(final String sql) {
		try {
			db_connection.setAutoCommit(false);
			db_connection.setTransactionIsolation(Connection.TRANSACTION_SERIALIZABLE);
			
			db_statement = db_connection.createStatement(ResultSet.TYPE_FORWARD_ONLY,
					ResultSet.CONCUR_READ_ONLY);
			db_resultset = db_statement.executeQuery(sql);
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		return db_resultset;
	}
	
	public void close() throws IOException {
		try {
			if (db_connection != null) {
				db_connection.commit();
				//db_connection.close();
			}
			
			if (db_resultset != null) {
				db_resultset.close();
			}
			
			if (db_statement != null) {
				db_statement.close();
			}
		} catch (SQLException e) {
			throw new IOException(e.getMessage());
		}
	}
	
	public void connect() {
		db_connection = getConnection(db_url, user_account, password);
	}
	
	public static Connection getConnection(final String url, final String username,
			final String password) {
		Connection connection = null;
		
		try {
			Class.forName("com.teradata.jdbc.TeraDriver");
			connection = DriverManager.getConnection(url, username, password);
			connection.setAutoCommit(false);
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		return connection;
	}
}
