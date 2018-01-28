package cn.tarena.ssort;

import java.io.IOException;

import org.apache.hadoop.io.LongWritable;
import org.apache.hadoop.io.NullWritable;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapreduce.Mapper;

public class ProfitMapper extends Mapper<LongWritable, Text, Ssort, NullWritable>{
	@Override
	protected void map(LongWritable key, Text value, Mapper<LongWritable, Text, Ssort, NullWritable>.Context context)
			throws IOException, InterruptedException {
		String line=value.toString();
		String[] data=line.split(" ");
		Ssort s=new Ssort();
		s.setMonth(Integer.parseInt(data[0]));
		s.setName(data[1]);
		s.setMonth(Integer.parseInt(data[2]));
		context.write(s, NullWritable.get());
	}

}
