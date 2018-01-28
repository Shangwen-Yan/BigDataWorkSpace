package cn.tarena.sort;

import java.io.IOException;

import org.apache.hadoop.io.IntWritable;
import org.apache.hadoop.io.LongWritable;
import org.apache.hadoop.io.NullWritable;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapreduce.Mapper;
import org.mockito.internal.matchers.CompareTo;

public class SortMapper extends Mapper<LongWritable, Text, Movie, NullWritable>{

	@Override
	protected void map(LongWritable key, Text value, Mapper<LongWritable, Text, Movie, NullWritable>.Context context)
			throws IOException, InterruptedException {
		String[] data=value.toString().split(" ");
		Movie movie=new Movie();
		movie.setName(data[0]);
		movie.setHot(Integer.parseInt(data[1]));

		context.write(movie, NullWritable.get());
	}

}
