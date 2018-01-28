package cn.tarena.score;

import java.io.IOException;

import org.apache.hadoop.io.LongWritable;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapreduce.Mapper;
import org.apache.hadoop.mapreduce.lib.input.FileSplit;


public class ScoreMapper extends Mapper<LongWritable, Text, Text, Text>{
	
	@Override
	protected void map(LongWritable key, Text value, Mapper<LongWritable, Text, Text, Text>.Context context)
			throws IOException, InterruptedException {
		FileSplit split=(FileSplit) context.getInputSplit();
		String course=split.getPath().getName();
		String[] line=value.toString().split(" ");
		String name=line[1];
		String score=line[2];
		String data=course+" "+score;
		context.write(new Text(name), new Text(data));
		
	}



}
