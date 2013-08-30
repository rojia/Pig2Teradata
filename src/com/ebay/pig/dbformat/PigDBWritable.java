package com.ebay.pig.dbformat;

import java.io.DataInput;
import java.io.DataOutput;
import java.io.IOException;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;
import java.util.Map;

import org.apache.hadoop.conf.Configuration;
import org.apache.hadoop.io.Writable;

import com.ebay.pig.utils.TDBMetaData;

public class PigDBWritable implements TeradataDBWritable, Writable {
	
	private String db_url = "";
	private String username = "";
	private String password ="";
	private String tablename = "";
	
	private Map<String, String> columnMap = null;
	
	public PigDBWritable(String url, String username, String password,
			String tablename) {
		super();
		this.db_url = url;
		this.username = username;
		this.password = password;
		this.tablename = tablename;
	}
	
	public PigDBWritable(Configuration conf) {
		super();
		this.db_url = conf.get("db_url");
		this.username = conf.get("username");
		this.password = conf.get("password");
		this.tablename = conf.get("tablename");
	}

	public void readFields(ResultSet arg0) throws SQLException {
		// TODO Auto-generated method stub
		TDBMetaData metadata = new TDBMetaData(db_url, username, password, tablename);
		metadata.getMetaData();
		columnMap = metadata.getTypeMap();
		
	}

	public void write(PreparedStatement arg0) throws SQLException {
		// TODO Auto-generated method stub

	}

	public void readFields(DataInput arg0) throws IOException {
		// TODO Auto-generated method stub

	}

	public void write(DataOutput arg0) throws IOException {
		// TODO Auto-generated method stub

	}

	public List<String> getColumnNames() {
		// TODO Auto-generated method stub
		return null;
	}

	public List<String> getColumnTypes() {
		// TODO Auto-generated method stub
		return null;
	}

}
