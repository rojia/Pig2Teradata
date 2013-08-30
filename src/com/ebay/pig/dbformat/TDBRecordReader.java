package com.ebay.pig.dbformat;

import java.io.IOException;
import java.sql.ResultSet;

import org.apache.hadoop.io.LongWritable;
import org.apache.hadoop.mapreduce.InputSplit;
import org.apache.hadoop.mapreduce.RecordReader;
import org.apache.hadoop.mapreduce.TaskAttemptContext;

import com.ebay.pig.utils.TDBQueryExecutor;

public class TDBRecordReader<V extends TeradataDBWritable> extends
		RecordReader<LongWritable, V> {
	
	private TaskAttemptContext context = null;
	
	private long result_count = 0;
	private V cur_value = null;
	
	private ResultSet rs = null;
	
	private TDBQueryExecutor query_exectutor = null;

	@Override
	public void close() throws IOException {
		// TODO Auto-generated method stub
		query_exectutor.close();
	}

	@Override
	public LongWritable getCurrentKey() throws IOException,
			InterruptedException {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public V getCurrentValue() throws IOException, InterruptedException {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public float getProgress() throws IOException, InterruptedException {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public void initialize(InputSplit arg0, TaskAttemptContext arg1)
			throws IOException, InterruptedException {
		// TODO Auto-generated method stub
		
	}

	@Override
	public boolean nextKeyValue() throws IOException, InterruptedException {
		// TODO Auto-generated method stub
		return false;
	}

}
