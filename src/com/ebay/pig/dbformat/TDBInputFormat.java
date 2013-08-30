package com.ebay.pig.dbformat;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.Vector;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.apache.hadoop.conf.Configuration;
import org.apache.hadoop.io.LongWritable;
import org.apache.hadoop.mapreduce.InputFormat;
import org.apache.hadoop.mapreduce.InputSplit;
import org.apache.hadoop.mapreduce.JobContext;
import org.apache.hadoop.mapreduce.RecordReader;
import org.apache.hadoop.mapreduce.TaskAttemptContext;

public class TDBInputFormat<V extends TeradataDBWritable> extends
		InputFormat<LongWritable, V> {
	
	protected Log mlog = LogFactory.getLog(TDBInputFormat.class.getClass());

	@Override
	public RecordReader<LongWritable, V> createRecordReader(InputSplit split,
			TaskAttemptContext context) throws IOException, InterruptedException {
		// TODO Auto-generated method stub
		return new TDBRecordReader<V>();
	}

	@Override
	public List<InputSplit> getSplits(JobContext context) throws IOException,
			InterruptedException {
		// TODO Auto-generated method stub
		List<InputSplit> splits = new ArrayList<InputSplit>();
		
		Configuration conf = context.getConfiguration();
		int mapper_num = conf.getInt("mapred.map.tasks", 1);
		long partition_num = conf.getLong("table.partition.num", mapper_num);
		
		if (partition_num < mapper_num) {
			mlog.error("Please adjust the number (" + partition_num + "/" +
					mapper_num + ")" + 
					" of partitions or mappers for the distribution of the map tasks");
			return splits;
		}
		
		long chunk_size = (partition_num / mapper_num);
		int big_mapper_num = (int) (partition_num % mapper_num);
		
		List<String> node_list = new Vector<String>();
		node_list.add(conf.get("teradata.db.url"));
		int node_num = node_list.size();
		
		for (int i = 0; i < big_mapper_num; ++i) {
			String node_id = node_list.get(new Random().nextInt(node_num));
			splits.add(new TDBSplit((i * (1 + chunk_size)), (1+i) * (1 + chunk_size), node_id));
		}
		
		for (int i = big_mapper_num; i < mapper_num; ++i) {
			String node_id = node_list.get((new Random()).nextInt(node_num));
			splits.add(new TDBSplit((i * chunk_size), (i + 1) * chunk_size, node_id));
		}
		
		return splits;
	}
	
	

}
