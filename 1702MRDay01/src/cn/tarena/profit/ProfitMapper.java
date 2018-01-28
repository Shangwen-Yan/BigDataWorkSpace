package cn.tarena.profit;

import java.io.IOException;

import org.apache.hadoop.io.IntWritable;
import org.apache.hadoop.io.LongWritable;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapreduce.Mapper;

public class ProfitMapper extends Mapper<LongWritable, Text, IntWritable, IntWritable>{
	@Override
	protected void map(LongWritable key, Text value,
			Mapper<LongWritable, Text, IntWritable, IntWritable>.Context context)
			throws IOException, InterruptedException {
		String line=value.toString();
		String[] data=line.split(" ");
		int month=Integer.parseInt(data[0]);
		int profit=Integer.parseInt(data[1]);
		context.write(new IntWritable(month), new IntWritable(profit));
	}
}
