package cn.tarena.season;

import java.io.IOException;

import org.apache.hadoop.io.IntWritable;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapreduce.Reducer;
import org.apache.hadoop.mapreduce.lib.output.MultipleOutputs;

public class SeasonReducer extends Reducer<IntWritable, IntWritable, IntWritable, IntWritable>{

	@Override
	protected void reduce(IntWritable key, Iterable<IntWritable> values, Reducer<IntWritable, IntWritable, IntWritable, IntWritable>.Context context)
			throws IOException, InterruptedException {
		int profit=0;
		System.out.println("1stkey"+key);
		for(IntWritable value:values){
			profit+=value.get();
			System.out.println("2ndkey"+key);
		}
		System.out.println("3rdkey"+key);
		
		context.write(key, new IntWritable(profit));
	}

}