package cn.tarena.distinct;

import java.io.IOException;

import org.apache.hadoop.conf.Configuration;
import org.apache.hadoop.fs.Path;
import org.apache.hadoop.io.IntWritable;
import org.apache.hadoop.io.NullWritable;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapreduce.Job;
import org.apache.hadoop.mapreduce.lib.input.FileInputFormat;
import org.apache.hadoop.mapreduce.lib.output.FileOutputFormat;

import cn.tarena.word.WordCountDriver;
import cn.tarena.word.WordCountMapper;
import cn.tarena.word.WordCountReducer;

public class DistinctDriver {
	public static void main(String[] args) throws IOException, ClassNotFoundException, InterruptedException {
		Configuration conf=new Configuration();
//		MR�����һ��ʵ��
		Job job=Job.getInstance(conf);
//		����MR�������еķ������
		job.setJarByClass(DistinctDriver.class);
//		��ȡ�Զ���mapper�����ͨ������
		job.setMapperClass(DistinctMapper.class);
		
		job.setReducerClass(DistinctReducer.class);
//		����mapper���key value������
		job.setMapOutputKeyClass(Text.class);
		job.setMapOutputValueClass(NullWritable.class);
//		����reduce�������
		job.setOutputKeyClass(Text.class);
		job.setOutputValueClass(NullWritable.class);
		FileInputFormat.setInputPaths(job, new Path("hdfs://192.168.174.60:9000/distinct"));
		FileOutputFormat.setOutputPath(job, new Path("hdfs://192.168.174.60:9000/distinct/result"));
//		�ύ����
		job.waitForCompletion(true);
		
	}
	
}
