package cn.tarena.doublemr;

import java.io.IOException;

import org.apache.hadoop.conf.Configuration;
import org.apache.hadoop.fs.Path;
import org.apache.hadoop.io.IntWritable;
import org.apache.hadoop.io.NullWritable;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapreduce.Job;
import org.apache.hadoop.mapreduce.lib.input.FileInputFormat;
import org.apache.hadoop.mapreduce.lib.output.FileOutputFormat;

import cn.tarena.score.ScoreDriver;
import cn.tarena.score.ScoreMapper;
import cn.tarena.score.ScoreReducer;

public class ProfitDriver {
	public static void main(String[] args) throws IOException, ClassNotFoundException, InterruptedException {
		Configuration conf=new Configuration();

		Job job=Job.getInstance(conf);
		job.setJarByClass(ProfitDriver.class);
		job.setMapperClass(ProfitMapper.class);	
		job.setReducerClass(ProfitReducer.class);
		job.setMapOutputKeyClass(Text.class);
		job.setMapOutputValueClass(IntWritable.class);
		job.setOutputKeyClass(Text.class);
		job.setOutputValueClass(IntWritable.class);
		FileInputFormat.setInputPaths(job, new Path("hdfs://192.168.174.60:9000/doublemr"));
		FileOutputFormat.setOutputPath(job, new Path("hdfs://192.168.174.60:9000/doublemr/result"));
		job.waitForCompletion(true);
		
		Job job2=Job.getInstance(conf);
		job2.setJarByClass(ProfitDriver.class);
		job2.setMapperClass(ProfitSortMapper.class);
		job2.setMapOutputKeyClass(Profit.class);
		job2.setMapOutputValueClass(NullWritable.class);
		FileInputFormat.setInputPaths(job2, new Path("hdfs://192.168.174.60:9000/doublemr/result"));
		FileOutputFormat.setOutputPath(job2, new Path("hdfs://192.168.174.60:9000/doublemr/finalresult"));
		job2.waitForCompletion(true);
		
		
	}
}
