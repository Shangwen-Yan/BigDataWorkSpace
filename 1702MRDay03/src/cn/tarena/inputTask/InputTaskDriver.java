package cn.tarena.inputTask;

import java.io.IOException;

import org.apache.hadoop.conf.Configuration;
import org.apache.hadoop.fs.Path;
import org.apache.hadoop.io.IntWritable;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapreduce.Job;
import org.apache.hadoop.mapreduce.lib.input.FileInputFormat;
import org.apache.hadoop.mapreduce.lib.output.FileOutputFormat;

import cn.tarena.input.AuthInputFormat;
import cn.tarena.input.WordCountDriver;
import cn.tarena.input.WordCountMapper;

public class InputTaskDriver {
	public static void main(String[] args) throws IOException, ClassNotFoundException, InterruptedException {
		Configuration conf=new Configuration();
		Job job=Job.getInstance(conf);
		job.setJarByClass(InputTaskDriver.class);
		job.setMapperClass(InputTaskMapper.class);
		job.setMapOutputKeyClass(Text.class);
		job.setMapOutputValueClass(Text.class);
		
		//默认是TextInputFormat这个实现类，发挥的是记录读取器
		//输入可以是每行行首偏移量，value是每行内容
		job.setInputFormatClass(InputTaskFormat.class);
		FileInputFormat.setInputPaths(job, new Path("hdfs://192.168.174.60:9000/input"));
		FileOutputFormat.setOutputPath(job, new Path("hdfs://192.168.174.60:9000/input/result"));
		job.waitForCompletion(true);
	}
}