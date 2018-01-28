package cn.tarena.season;

import java.io.IOException;

import org.apache.hadoop.conf.Configuration;
import org.apache.hadoop.fs.Path;
import org.apache.hadoop.io.IntWritable;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapreduce.Job;
import org.apache.hadoop.mapreduce.lib.input.FileInputFormat;
import org.apache.hadoop.mapreduce.lib.input.MultipleInputs;
import org.apache.hadoop.mapreduce.lib.input.TextInputFormat;
import org.apache.hadoop.mapreduce.lib.output.FileOutputFormat;
import org.apache.hadoop.mapreduce.lib.output.MultipleOutputs;
import org.apache.hadoop.mapreduce.lib.output.TextOutputFormat;

import cn.tarena.inputTask.InputTaskFormat;

import cn.tarena.output.AuthOutputFormat;

public class SeasonDriver {
	public static void main(String[] args) throws IOException, ClassNotFoundException, InterruptedException {
		Configuration conf=new Configuration();
		Job job=Job.getInstance(conf);
		job.setJarByClass(SeasonDriver.class);
		
		job.setMapperClass(SeasonMapper.class);
		job.setMapOutputKeyClass(IntWritable.class);
		job.setMapOutputValueClass(IntWritable.class);
		
		job.setReducerClass(SeasonReducer.class);
		job.setOutputKeyClass(IntWritable.class);
		job.setOutputValueClass(IntWritable.class);
		job.setGroupingComparatorClass(SeasonGroup.class);
		FileInputFormat.setInputPaths(job, new Path("hdfs://192.168.174.60:9000/season"));
		FileOutputFormat.setOutputPath(job, new Path("hdfs://192.168.174.60:9000/season/result"));
		job.waitForCompletion(true);
	}
}
