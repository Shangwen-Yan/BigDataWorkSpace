package cn.tarena.flow;

import java.io.IOException;

import org.apache.hadoop.conf.Configuration;
import org.apache.hadoop.fs.Path;
import org.apache.hadoop.io.IntWritable;
import org.apache.hadoop.io.NullWritable;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapreduce.Job;
import org.apache.hadoop.mapreduce.lib.input.FileInputFormat;
import org.apache.hadoop.mapreduce.lib.output.FileOutputFormat;

import cn.tarena.max.MaxDriver;
import cn.tarena.max.MaxMapper;
import cn.tarena.max.MaxReducer;

public class FlowDriver {
	public static void main(String[] args) throws IOException, ClassNotFoundException, InterruptedException {
		Configuration conf=new Configuration();
//		MR�����һ��ʵ��
		Job job=Job.getInstance(conf);
//		����MR�������еķ������
		job.setJarByClass(FlowDriver.class);
//		��ȡ�Զ���mapper�����ͨ������
		job.setMapperClass(FlowMapper.class);
		
		job.setReducerClass(FlowReducer.class);
//		����mapper���key value������
		job.setMapOutputKeyClass(Text.class);
		job.setMapOutputValueClass(Flow.class);
		
		job.setNumReduceTasks(3);
//		Ĭ���õ���HashPartitioner
		job.setPartitionerClass(FlowPartitioner.class);
//		����reduce�������
		job.setOutputKeyClass(Flow.class);
		job.setOutputValueClass(NullWritable.class);
		FileInputFormat.setInputPaths(job, new Path("hdfs://192.168.174.60:9000/flow"));
		FileOutputFormat.setOutputPath(job, new Path("hdfs://192.168.174.60:9000/flow/result"));
//		�ύ����
		job.waitForCompletion(true);
		
	}

}