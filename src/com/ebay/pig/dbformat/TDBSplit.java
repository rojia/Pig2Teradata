package com.ebay.pig.dbformat;

import java.io.DataInput;
import java.io.DataOutput;
import java.io.IOException;
import java.sql.ResultSet;

import org.apache.hadoop.io.Text;
import org.apache.hadoop.io.Writable;
import org.apache.hadoop.mapreduce.InputSplit;

public class TDBSplit extends InputSplit implements Writable {
	
	private ResultSet resultSet = null;
	private long start_partition = 0;
	private long end_partition = 0;
	private Text logon_node = new Text("");
	
	public TDBSplit() {
	}
	
	public TDBSplit(long startPatition, long endPartition, String logNode) {
		this.start_partition = startPatition;
		this.end_partition = endPartition;
		this.logon_node = new Text(logNode);
	}

	public void readFields(DataInput arg0) throws IOException {
		// TODO Auto-generated method stub

	}

	public void write(DataOutput arg0) throws IOException {
		// TODO Auto-generated method stub

	}

	@Override
	public long getLength() throws IOException, InterruptedException {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public String[] getLocations() throws IOException, InterruptedException {
		// TODO Auto-generated method stub
		return null;
	}

}
