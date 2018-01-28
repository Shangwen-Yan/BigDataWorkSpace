package cn.tarena.sort;

import java.io.IOException;

import org.apache.hadoop.conf.Configuration;
import org.apache.hadoop.fs.Path;
import org.apache.hadoop.io.IntWritable;
import org.apache.hadoop.io.NullWritable;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapreduce.Job;
import org.apache.hadoop.mapreduce.lib.input.FileInputFormat;
import org.apache.hadoop.mapreduce.lib.output.FileOutputFormat;




public class SortDriver {
	public static void main(String[] args) throws IOException, ClassNotFoundException, InterruptedException {
		Configuration conf=new Configuration();

		Job job=Job.getInstance(conf);

		job.setJarByClass(SortDriver.class);

		job.setMapperClass(SortMapper.class);

		job.setMapOutputKeyClass(Movie.class);
		job.setMapOutputValueClass(NullWritable.class);

		FileInputFormat.setInputPaths(job, new Path("hdfs://192.168.174.60:9000/sort"));
		FileOutputFormat.setOutputPath(job, new Path("hdfs://192.168.174.60:9000/sort/result"));

		job.waitForCompletion(true);
		
	}
}