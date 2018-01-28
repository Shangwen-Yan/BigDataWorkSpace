package cn.tarena.flow;

import java.io.IOException;

import org.apache.hadoop.io.IntWritable;
import org.apache.hadoop.io.LongWritable;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapreduce.Mapper;

public class FlowMapper extends Mapper<LongWritable, Text, Text, Flow>{
	@Override
	protected void map(LongWritable key, Text value, Mapper<LongWritable, Text, Text, Flow>.Context context)
			throws IOException, InterruptedException {
		String line=value.toString();
		String[] data=line.split("\\s");
		Flow flow=new Flow();
		flow.setPhone(data[0]);
		flow.setAddr(data[1]);
		flow.setName(data[2]);
		flow.setFlow(Integer.parseInt(data[3]));
		
		context.write(new Text(flow.getName()), flow);
		
		
	}
}

