package cn.tarena.word;

import java.io.IOException;

import org.apache.hadoop.conf.Configuration;
import org.apache.hadoop.fs.Path;
import org.apache.hadoop.io.IntWritable;
import org.apache.hadoop.io.LongWritable;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapreduce.Job;
import org.apache.hadoop.mapreduce.lib.input.FileInputFormat;
import org.apache.hadoop.mapreduce.lib.output.FileOutputFormat;




public class WordCountDriver {
	public static void main(String[] args) throws IOException, ClassNotFoundException, InterruptedException {
		Configuration conf=new Configuration();
//		MR任务的一个实例
		Job job=Job.getInstance(conf);
//		设置MR任务运行的方法入口
		job.setJarByClass(WordCountDriver.class);
//		获取自定义mapper组件，通过反射
		job.setMapperClass(WordCountMapper.class);
		
		job.setReducerClass(WordCountReducer.class);
//		设置mapper输出key value的类型
		job.setMapOutputKeyClass(Text.class);
		job.setMapOutputValueClass(IntWritable.class);
//		设置reduce输出类型
		job.setOutputKeyClass(Text.class);
		job.setOutputValueClass(IntWritable.class);
		FileInputFormat.setInputPaths(job, new Path("hdfs://192.168.174.60:9000/word"));
		FileOutputFormat.setOutputPath(job, new Path("hdfs://192.168.174.60:9000/word/result"));
//		提交任务
		job.waitForCompletion(true);
		
	}
}
