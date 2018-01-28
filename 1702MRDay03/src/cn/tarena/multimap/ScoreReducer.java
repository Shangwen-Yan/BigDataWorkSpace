package cn.tarena.multimap;

import java.io.IOException;

import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapreduce.Reducer;
import org.apache.hadoop.mapreduce.lib.output.MultipleOutputs;

public class ScoreReducer extends Reducer<Text, Text, Text, Text>{
	private MultipleOutputs<Text, Text> mos;
	@Override
	protected void reduce(Text key, Iterable<Text> values, Reducer<Text, Text, Text, Text>.Context context)
			throws IOException, InterruptedException {
		for(Text value:values){
			if(key.toString().equals("tom")){
				mos.write("tomfile", key, value);
				
			}else if(key.toString().equals("rose")){
				mos.write("rosefile", key, value);
			}else{
				mos.write("jaryfile", key, value);
			}
		}
	}
	
	@Override
	protected void setup(Reducer<Text, Text, Text, Text>.Context context) throws IOException, InterruptedException {
		mos=new MultipleOutputs<>(context);
		
	}

}
