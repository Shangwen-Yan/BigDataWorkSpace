package cn.tarena.max;

import java.io.IOException;

import org.apache.hadoop.io.IntWritable;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapreduce.Reducer;

public class MaxReducer  extends Reducer<IntWritable, IntWritable, IntWritable, IntWritable>{
	
	@Override
	protected void reduce(IntWritable key, Iterable<IntWritable> values, Reducer<IntWritable, IntWritable, IntWritable, IntWritable>.Context context)
			throws IOException, InterruptedException {
		int max=0;
		for(IntWritable value:values){
			if(value.get()>max){
				max=value.get();
			}
		}
		context.write(key, new IntWritable(max));
	}
}