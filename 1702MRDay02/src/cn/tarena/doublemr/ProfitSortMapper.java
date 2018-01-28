package cn.tarena.doublemr;

import java.io.IOException;

import org.apache.hadoop.io.IntWritable;
import org.apache.hadoop.io.LongWritable;
import org.apache.hadoop.io.NullWritable;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapreduce.Mapper;

public class ProfitSortMapper extends Mapper<LongWritable, Text, Profit, NullWritable>{
	
	@Override
	protected void map(LongWritable key, Text value, Mapper<LongWritable, Text, Profit, NullWritable>.Context context)
			throws IOException, InterruptedException {
		String[] line=value.toString().split("\\s");
		Profit profit=new Profit();
		profit.setName(line[0]);
		profit.setProfit(Integer.parseInt(line[1]));

		
		context.write(profit, NullWritable.get());
		
	}



}
