package cn.tarena.output;

import java.io.IOException;

import org.apache.hadoop.fs.FSDataOutputStream;
import org.apache.hadoop.mapreduce.RecordWriter;
import org.apache.hadoop.mapreduce.TaskAttemptContext;

public class AuthRecordWriter<K, V> extends RecordWriter<K, V>{
	private FSDataOutputStream out;
	private String keyValueSeperator;
	private String lineSeperator;

	public AuthRecordWriter(FSDataOutputStream out, String string, String string2) {
		this.out=out;
		this.keyValueSeperator=string;
		this.lineSeperator=string2;
	}

	@Override
	public void close(TaskAttemptContext arg0) throws IOException, InterruptedException {
		out.close();
		
	}

	@Override
	public void write(K key, V value) throws IOException, InterruptedException {
		out.write(key.toString().getBytes());
		out.write(keyValueSeperator.getBytes());
		out.write(value.toString().getBytes());
		out.write(lineSeperator.getBytes());
		
	}

}
