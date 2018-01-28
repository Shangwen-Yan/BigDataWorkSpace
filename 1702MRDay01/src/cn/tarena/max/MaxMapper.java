package cn.tarena.max;

import java.io.IOException;

import org.apache.hadoop.io.IntWritable;
import org.apache.hadoop.io.LongWritable;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapreduce.Mapper;
import org.junit.Test;

public class MaxMapper extends Mapper<LongWritable, Text, IntWritable, IntWritable>{
	@Override
	protected void map(LongWritable key, Text value, Mapper<LongWritable, Text, IntWritable, IntWritable>.Context context)
			throws IOException, InterruptedException {
		String year=value.toString().substring(8, 12);
		String temp=value.toString().substring(18, 22);
		context.write(new IntWritable(Integer.parseInt(year)), new IntWritable(Integer.parseInt(temp)));
		
		
	}

}
