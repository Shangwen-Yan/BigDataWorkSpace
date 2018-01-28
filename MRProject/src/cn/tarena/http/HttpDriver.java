package cn.tarena.http;

import java.io.IOException;

import org.apache.hadoop.conf.Configuration;
import org.apache.hadoop.fs.Path;
import org.apache.hadoop.io.NullWritable;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapreduce.Job;
import org.apache.hadoop.mapreduce.lib.input.FileInputFormat;
import org.apache.hadoop.mapreduce.lib.output.FileOutputFormat;


public class HttpDriver {
	public static void main(String[] args) throws IOException, ClassNotFoundException, InterruptedException {
		Configuration conf=new Configuration();
//		MR�����һ��ʵ��
		Job job=Job.getInstance(conf);
//		����MR�������еķ������
		job.setJarByClass(HttpDriver.class);
//		��ȡ�Զ���mapper�����ͨ������
		job.setMapperClass(HttpMapper.class);
		
		job.setReducerClass(HttpReducer.class);
//		����mapper���key value������
		job.setMapOutputKeyClass(Text.class);
		job.setMapOutputValueClass(HttpAppHost.class);

//		����reduce�������
		job.setOutputKeyClass(HttpAppHost.class);
		job.setOutputValueClass(NullWritable.class);
		FileInputFormat.setInputPaths(job, new Path("hdfs://192.168.174.60:9000/http"));
		FileOutputFormat.setOutputPath(job, new Path("hdfs://192.168.174.60:9000/http/result"));
//		�ύ����
		job.waitForCompletion(true);
		
	}

}
