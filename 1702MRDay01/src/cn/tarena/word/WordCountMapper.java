package cn.tarena.word;

import java.io.IOException;

import org.apache.hadoop.io.IntWritable;
import org.apache.hadoop.io.LongWritable;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapreduce.Mapper;


/**
 * 
 * @author CGB-yz
 *text.toString()
 *new Text(string)
 *new IntWritable(1)
 */
public class WordCountMapper extends Mapper<LongWritable, Text, Text, IntWritable>{
	@Override
	protected void map(LongWritable key, Text value, Mapper<LongWritable, Text, Text, IntWritable>.Context context)
			throws IOException, InterruptedException {
		String string=value.toString();
		String[] subs=string.split("\\s");
		for(String sub:subs){
			context.write(new Text(sub), new IntWritable(1));
		}
		
	}
}
