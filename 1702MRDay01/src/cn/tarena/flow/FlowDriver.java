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
//		MR任务的一个实例
		Job job=Job.getInstance(conf);
//		设置MR任务运行的方法入口
		job.setJarByClass(FlowDriver.class);
//		获取自定义mapper组件，通过反射
		job.setMapperClass(FlowMapper.class);
		
		job.setReducerClass(FlowReducer.class);
//		设置mapper输出key value的类型
		job.setMapOutputKeyClass(Text.class);
		job.setMapOutputValueClass(Flow.class);
		
		job.setNumReduceTasks(3);
//		默认用的是HashPartitioner
		job.setPartitionerClass(FlowPartitioner.class);
//		设置reduce输出类型
		job.setOutputKeyClass(Flow.class);
		job.setOutputValueClass(NullWritable.class);
		FileInputFormat.setInputPaths(job, new Path("hdfs://192.168.174.60:9000/flow"));
		FileOutputFormat.setOutputPath(job, new Path("hdfs://192.168.174.60:9000/flow/result"));
//		提交任务
		job.waitForCompletion(true);
		
	}

}