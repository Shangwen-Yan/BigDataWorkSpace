package cn.tarena.output;

import java.io.IOException;

import org.apache.hadoop.conf.Configuration;
import org.apache.hadoop.fs.FSDataOutputStream;
import org.apache.hadoop.fs.FileSystem;
import org.apache.hadoop.fs.Path;
import org.apache.hadoop.mapreduce.RecordWriter;
import org.apache.hadoop.mapreduce.TaskAttemptContext;
import org.apache.hadoop.mapreduce.lib.output.FileOutputFormat;

public class AuthOutputFormat<K, V> extends FileOutputFormat<K, V>{

	@Override
	public RecordWriter<K, V> getRecordWriter(TaskAttemptContext job) throws IOException, InterruptedException {
		Path path=super.getDefaultWorkFile(job, "");
		Configuration conf=job.getConfiguration();
		FileSystem system=path.getFileSystem(conf);
		
		FSDataOutputStream out =system.create(path);
		
		return new AuthRecordWriter<K,V>(out,"￥￥￥","****");
	}

}
