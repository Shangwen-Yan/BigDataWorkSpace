package cn.tarena.profit;

import java.io.IOException;

import org.apache.hadoop.conf.Configuration;
import org.apache.hadoop.fs.Path;
import org.apache.hadoop.io.IntWritable;
import org.apache.hadoop.io.NullWritable;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapreduce.Job;
import org.apache.hadoop.mapreduce.lib.input.FileInputFormat;
import org.apache.hadoop.mapreduce.lib.output.FileOutputFormat;



public class ProfitDriver {
	public static void main(String[] args) throws IOException, ClassNotFoundException, InterruptedException {
		Configuration conf=new Configuration();
//		MR�����һ��ʵ��
		Job job=Job.getInstance(conf);
//		����MR�������еķ������
		job.setJarByClass(ProfitDriver.class);
//		��ȡ�Զ���mapper�����ͨ������
		job.setMapperClass(ProfitMapper.class);
		
		job.setReducerClass(ProfitReducer.class);
//		����mapper���key value������
		job.setMapOutputKeyClass(IntWritable.class);
		job.setMapOutputValueClass(IntWritable.class);
//		����reduce�������
		job.setOutputKeyClass(IntWritable.class);
		job.setOutputValueClass(IntWritable.class);
		
		job.setNumReduceTasks(3);
		FileInputFormat.setInputPaths(job, new Path("hdfs://192.168.174.60:9000/profit"));
		FileOutputFormat.setOutputPath(job, new Path("hdfs://192.168.174.60:9000/profit/result"));
//		�ύ����
		job.waitForCompletion(true);
		
	}


}
