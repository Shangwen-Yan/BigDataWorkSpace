package cn.tarena.input;

import java.io.IOException;
import java.io.InputStream;

import org.apache.hadoop.conf.Configuration;
import org.apache.hadoop.fs.FileSystem;
import org.apache.hadoop.fs.Path;
import org.apache.hadoop.io.IntWritable;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapreduce.InputSplit;
import org.apache.hadoop.mapreduce.RecordReader;
import org.apache.hadoop.mapreduce.TaskAttemptContext;
import org.apache.hadoop.mapreduce.lib.input.FileSplit;
import org.apache.hadoop.util.LineReader;

public class AuthRecordReader extends RecordReader<IntWritable, Text>{
	private FileSplit fs;
	private IntWritable key;
	private Text value;
	private int count=0;
	private LineReader reader;
	
	
	@Override
	public void close() throws IOException {
		if(reader!=null){
			reader=null;
		}
		
	}
	
	/**
	 * 输入key的传入方法
	 * 
	 */
	@Override
	public IntWritable getCurrentKey() throws IOException, InterruptedException {
		// TODO Auto-generated method stub
		return key;
	}

	@Override
	public Text getCurrentValue() throws IOException, InterruptedException {
		// TODO Auto-generated method stub
		return value;
	}

	@Override
	public float getProgress() throws IOException, InterruptedException {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public void initialize(InputSplit split, TaskAttemptContext context) throws IOException, InterruptedException {
		fs=(FileSplit) split;
		Path path=fs.getPath();
		Configuration conf=context.getConfiguration();
		FileSystem system = path.getFileSystem(conf);
		InputStream in = system.open(path);
		reader=new LineReader(in);
		
	}

	/**
	 * return true会再被调用一次
	 * 每次被调用getCurrentKey呵呵getCurrentValue也会被联动调用
	 * 
	 */
	@Override
	public boolean nextKeyValue() throws IOException, InterruptedException {
		key=new IntWritable();
		value=new Text();
		Text tmp=new Text();
		int length =reader.readLine(tmp); //读取的字节数
		if(length==0){
			return false;
		}else{
			count++;
			key.set(count);
			value.set(tmp);
			//value.append(tmp.getBytes(), 0, tmp.getLength());
			return true;
		}
	}

}
