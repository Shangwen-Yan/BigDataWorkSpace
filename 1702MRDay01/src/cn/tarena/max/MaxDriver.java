package cn.tarena.max;

import java.io.IOException;

import org.apache.hadoop.conf.Configuration;
import org.apache.hadoop.fs.Path;
import org.apache.hadoop.io.IntWritable;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapreduce.Job;
import org.apache.hadoop.mapreduce.lib.input.FileInputFormat;
import org.apache.hadoop.mapreduce.lib.output.FileOutputFormat;

import cn.tarena.average.AverageDriver;
import cn.tarena.average.AverageMapper;
import cn.tarena.average.AverageReducer;

public class MaxDriver {
	public static void main(String[] args) throws IOException, ClassNotFoundException, InterruptedException {
		Configuration conf=new Configuration();
//		MR�����һ��ʵ��
		Job job=Job.getInstance(conf);
//		����MR�������еķ������
		job.setJarByClass(MaxDriver.class);
//		��ȡ�Զ���mapper�����ͨ������
		job.setMapperClass(MaxMapper.class);
		
		job.setReducerClass(MaxReducer.class);
//		����mapper���key value������
		job.setMapOutputKeyClass(IntWritable.class);
		job.setMapOutputValueClass(IntWritable.class);
//		����reduce�������
		job.setOutputKeyClass(IntWritable.class);
		job.setOutputValueClass(IntWritable.class);
		FileInputFormat.setInputPaths(job, new Path("hdfs://192.168.174.60:9000/max"));
		FileOutputFormat.setOutputPath(job, new Path("hdfs://192.168.174.60:9000/max/result"));
//		�ύ����
		job.waitForCompletion(true);
		
	}

}