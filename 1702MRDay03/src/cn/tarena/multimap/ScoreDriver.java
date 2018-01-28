package cn.tarena.multimap;

import java.io.IOException;

import org.apache.hadoop.conf.Configuration;
import org.apache.hadoop.fs.Path;
import org.apache.hadoop.io.IntWritable;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapreduce.Job;
import org.apache.hadoop.mapreduce.lib.input.FileInputFormat;
import org.apache.hadoop.mapreduce.lib.input.MultipleInputs;
import org.apache.hadoop.mapreduce.lib.input.TextInputFormat;
import org.apache.hadoop.mapreduce.lib.output.FileOutputFormat;
import org.apache.hadoop.mapreduce.lib.output.MultipleOutputs;
import org.apache.hadoop.mapreduce.lib.output.TextOutputFormat;

import cn.tarena.inputTask.InputTaskDriver;
import cn.tarena.inputTask.InputTaskFormat;
import cn.tarena.inputTask.InputTaskMapper;
import cn.tarena.output.AuthOutputFormat;

public class ScoreDriver {
	public static void main(String[] args) throws IOException, ClassNotFoundException, InterruptedException {
		Configuration conf=new Configuration();
		Job job=Job.getInstance(conf);
		job.setJarByClass(ScoreDriver.class);
		
		MultipleInputs.addInputPath(job,  new Path("hdfs://192.168.174.60:9000/input/input.txt"), InputTaskFormat.class, ScoreMapper1.class);
		MultipleInputs.addInputPath(job,  new Path("hdfs://192.168.174.60:9000/input/part-r-00000"), TextInputFormat.class, ScoreMapper2.class);
		
		job.setMapOutputKeyClass(Text.class);
		job.setMapOutputValueClass(Text.class);
		
		MultipleOutputs.addNamedOutput(job, "tomfile", TextOutputFormat.class, Text.class, Text.class);
		MultipleOutputs.addNamedOutput(job, "rosefile", AuthOutputFormat.class, Text.class, Text.class);
		MultipleOutputs.addNamedOutput(job, "jaryfile", AuthOutputFormat.class, Text.class, Text.class);
		job.setReducerClass(ScoreReducer.class);
		FileOutputFormat.setOutputPath(job, new Path("hdfs://192.168.174.60:9000/input/result"));
		job.waitForCompletion(true);
	}
}
