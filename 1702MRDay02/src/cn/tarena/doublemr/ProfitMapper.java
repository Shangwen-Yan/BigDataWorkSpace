package cn.tarena.doublemr;

import java.io.IOException;

import org.apache.hadoop.io.IntWritable;
import org.apache.hadoop.io.LongWritable;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapreduce.Mapper;
import org.apache.hadoop.mapreduce.lib.input.FileSplit;

public class ProfitMapper extends Mapper<LongWritable, Text, Text, IntWritable>{
	
	@Override
	protected void map(LongWritable key, Text value, Mapper<LongWritable, Text, Text, IntWritable>.Context context)
			throws IOException, InterruptedException {
		String[] line=value.toString().split("\\s");
		String name=line[1];
		String in=line[2];
		String out=line[3];
		int profit=Integer.parseInt(in)-Integer.parseInt(out);
		
		context.write(new Text(name), new IntWritable(profit));
		
	}



}
