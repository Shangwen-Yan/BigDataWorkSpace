package cn.tarena.http;

import java.io.IOException;

import org.apache.hadoop.io.NullWritable;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapreduce.Reducer;


public class HttpReducer extends Reducer<Text, HttpAppHost, HttpAppHost, NullWritable>{
	@Override
	protected void reduce(Text key, Iterable<HttpAppHost> values, Reducer<Text, HttpAppHost, HttpAppHost, NullWritable>.Context context)
			throws IOException, InterruptedException {
		HttpAppHost mapHah=values.iterator().next();
		for(HttpAppHost hah:values){
			mapHah.setAccepts(mapHah.getAccepts()+hah.getAccepts());
			mapHah.setAttempts(mapHah.getAttempts()+hah.getAttempts());
			mapHah.setTrafficUL(mapHah.getTrafficUL()+hah.getTrafficUL());
			mapHah.setTrafficDL(mapHah.getTrafficDL()+hah.getTrafficDL());
			mapHah.setRetranUL(mapHah.getRetranUL()+hah.getRetranUL());
			mapHah.setRetranDL(mapHah.getRetranDL()+hah.getRetranDL());
			mapHah.setTransDelay(mapHah.getTransDelay()+hah.getTransDelay());
		}
		
		context.write(mapHah, NullWritable.get());
	}
}
