package cn.tarena.flow;

import java.io.IOException;

import org.apache.hadoop.io.IntWritable;
import org.apache.hadoop.io.NullWritable;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapreduce.Reducer;

public class FlowReducer extends Reducer<Text, Flow, Flow, NullWritable>{
	@Override
	protected void reduce(Text key, Iterable<Flow> values, Reducer<Text, Flow, Flow, NullWritable>.Context context)
			throws IOException, InterruptedException {
		int flow=0;
		Flow out=new Flow();
		for(Flow value:values){
			out.setPhone(value.getPhone());
			out.setAddr(value.getAddr());
			out.setName(value.getName());
			flow+=value.getFlow();
		}
		out.setFlow(flow);
		context.write(out, NullWritable.get());
	}
}
