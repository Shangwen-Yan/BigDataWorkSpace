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
//		MR任务的一个实例
		Job job=Job.getInstance(conf);
//		设置MR任务运行的方法入口
		job.setJarByClass(HttpDriver.class);
//		获取自定义mapper组件，通过反射
		job.setMapperClass(HttpMapper.class);
		
		job.setReducerClass(HttpReducer.class);
//		设置mapper输出key value的类型
		job.setMapOutputKeyClass(Text.class);
		job.setMapOutputValueClass(HttpAppHost.class);

//		设置reduce输出类型
		job.setOutputKeyClass(HttpAppHost.class);
		job.setOutputValueClass(NullWritable.class);
		FileInputFormat.setInputPaths(job, new Path("hdfs://192.168.174.60:9000/http"));
		FileOutputFormat.setOutputPath(job, new Path("hdfs://192.168.174.60:9000/http/result"));
//		提交任务
		job.waitForCompletion(true);
		
	}

}
