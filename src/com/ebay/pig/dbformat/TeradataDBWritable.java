package com.ebay.pig.dbformat;

import java.util.List;

import org.apache.hadoop.mapred.lib.db.DBWritable;

public interface TeradataDBWritable extends DBWritable {
	public List<String> getColumnNames();
	public List<String> getColumnTypes();
}
