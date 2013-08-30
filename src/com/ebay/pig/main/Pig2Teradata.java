package com.ebay.pig.main;

import java.io.IOException;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
//import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapreduce.InputFormat;
import org.apache.hadoop.mapreduce.Job;
import org.apache.hadoop.mapreduce.RecordReader;
import org.apache.pig.LoadFunc;
import org.apache.pig.backend.hadoop.executionengine.mapReduceLayer.PigSplit;
import org.apache.pig.data.Tuple;

import com.ebay.pig.dbformat.TDBRecordReader;

public class Pig2Teradata extends LoadFunc {
	
	protected final Log mLog = LogFactory.getLog(getClass());
	protected TDBRecordReader in = null;
	
	public Pig2Teradata(){
	}
	
	//public Pig2Teradata()

	@Override
	public InputFormat getInputFormat() throws IOException {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Tuple getNext() throws IOException {
		// TODO Auto-generated method stub
		/*try {
			boolean notDone = in.nextKeyValue();
			if (notDone) {
				return null;
			}
			
			Text value = (Text) in.getCurrentValue();
		}*/
		return null;
	}

	@Override
	public void prepareToRead(RecordReader reader, PigSplit split)
			throws IOException {
		// TODO Auto-generated method stub
		in = (TDBRecordReader) reader;
	}

	@Override
	public void setLocation(String location, Job job) throws IOException {
		// TODO Auto-generated method stub

	}

}
