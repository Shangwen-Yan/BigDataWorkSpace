package cn.tarena.multimap;

import java.io.IOException;

import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapreduce.Mapper;

//读取input，输入key是人名 value是成绩；自定义reader方法
public class ScoreMapper1 extends Mapper<Text, Text, Text, Text>{
	@Override
	protected void map(Text key, Text value, Mapper<Text, Text, Text, Text>.Context context)
			throws IOException, InterruptedException {
		context.write(key, value);
	}

}
