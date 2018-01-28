package cn.tarena.input;

import java.io.IOException;

import org.apache.hadoop.conf.Configuration;
import org.apache.hadoop.fs.Path;
import org.apache.hadoop.io.IntWritable;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapreduce.Job;
import org.apache.hadoop.mapreduce.lib.input.FileInputFormat;
import org.apache.hadoop.mapreduce.lib.output.FileOutputFormat;

import cn.tarena.output.AuthOutputFormat;



public class WordCountDriver {
	public static void main(String[] args) throws IOException, ClassNotFoundException, InterruptedException {
		Configuration conf=new Configuration();
		Job job=Job.getInstance(conf);
		job.setJarByClass(WordCountDriver.class);
		job.setMapperClass(WordCountMapper.class);
		job.setMapOutputKeyClass(IntWritable.class);
		job.setMapOutputValueClass(Text.class);
		
		//默认是TextInputFormat这个实现类，发挥的是记录读取器
		//输入可以是每行行首偏移量，value是每行内容
		job.setInputFormatClass(AuthInputFormat.class);
		//默认是TextOutputFormat，输出key和value之间用tab分割
		job.setOutputFormatClass(AuthOutputFormat.class);
		FileInputFormat.setInputPaths(job, new Path("hdfs://192.168.174.60:9000/word"));
		FileOutputFormat.setOutputPath(job, new Path("hdfs://192.168.174.60:9000/word/result"));
		job.waitForCompletion(true);
	}
}
