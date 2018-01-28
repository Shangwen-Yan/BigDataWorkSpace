package cn.tarena.ssort;

import java.io.IOException;

import org.apache.hadoop.conf.Configuration;
import org.apache.hadoop.fs.Path;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapreduce.Job;
import org.apache.hadoop.mapreduce.lib.input.MultipleInputs;
import org.apache.hadoop.mapreduce.lib.input.TextInputFormat;
import org.apache.hadoop.mapreduce.lib.output.FileOutputFormat;
import org.apache.hadoop.mapreduce.lib.output.MultipleOutputs;
import org.apache.hadoop.mapreduce.lib.output.TextOutputFormat;

import cn.tarena.inputTask.InputTaskFormat;
import cn.tarena.multimap.ScoreDriver;
import cn.tarena.multimap.ScoreMapper1;
import cn.tarena.multimap.ScoreMapper2;
import cn.tarena.multimap.ScoreReducer;
import cn.tarena.output.AuthOutputFormat;

public class ProfitDriver {
	public static void main(String[] args) throws IOException, ClassNotFoundException, InterruptedException {
		Configuration conf=new Configuration();
		Job job=Job.getInstance(conf);
		job.setJarByClass(ScoreDriver.class);
		job.setMapperClass(ProfitMapper.class);

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
