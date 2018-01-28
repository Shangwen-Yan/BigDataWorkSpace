package cn.tarena.word;

import java.io.IOException;

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
public class WordCountMapper extends Mapper<LongWritable, Text, LongWritable, Text>{

	@Override
	protected void map(LongWritable key, Text value, Mapper<LongWritable, Text, LongWritable, Text>.Context context)
			throws IOException, InterruptedException {
		context.write(key, value);

	}

}
