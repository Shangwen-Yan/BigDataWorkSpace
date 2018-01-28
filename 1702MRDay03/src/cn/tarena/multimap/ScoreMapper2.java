package cn.tarena.multimap;

import java.io.IOException;

import org.apache.hadoop.io.LongWritable;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapreduce.Mapper;

//使用默认格式处理,读取part
public class ScoreMapper2 extends Mapper<LongWritable, Text, Text, Text>{
	@Override
	protected void map(LongWritable key, Text value, Mapper<LongWritable, Text, Text, Text>.Context context)
			throws IOException, InterruptedException {
		String line=value.toString();
		String[] data=line.split("	");
		context.write(new Text(data[0]), new Text(data[1]));
	}

}
