package cn.tarena.score;

import java.io.IOException;

import org.apache.hadoop.conf.Configuration;
import org.apache.hadoop.fs.Path;
import org.apache.hadoop.io.IntWritable;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapreduce.Job;
import org.apache.hadoop.mapreduce.lib.input.FileInputFormat;
import org.apache.hadoop.mapreduce.lib.output.FileOutputFormat;



public class ScoreDriver {
	public static void main(String[] args) throws IOException, ClassNotFoundException, InterruptedException {
		Configuration conf=new Configuration();
//		MR�����һ��ʵ��
		Job job=Job.getInstance(conf);
//		����MR�������еķ������
		job.setJarByClass(ScoreDriver.class);
//		��ȡ�Զ���mapper�����ͨ������
		job.setMapperClass(ScoreMapper.class);
		
		job.setReducerClass(ScoreReducer.class);
//		����mapper���key value������
		job.setMapOutputKeyClass(Text.class);
		job.setMapOutputValueClass(Text.class);
//		����reduce�������
		job.setOutputKeyClass(Text.class);
		job.setOutputValueClass(Text.class);
		FileInputFormat.setInputPaths(job, new Path("hdfs://192.168.174.60:9000/score"));
		FileOutputFormat.setOutputPath(job, new Path("hdfs://192.168.174.60:9000/score/result"));
//		�ύ����
		job.waitForCompletion(true);
		
	}
}
