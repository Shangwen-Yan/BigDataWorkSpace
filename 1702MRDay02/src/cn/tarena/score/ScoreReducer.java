package cn.tarena.score;

import java.io.IOException;

import org.apache.hadoop.io.IntWritable;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapreduce.Reducer;

public class ScoreReducer extends Reducer<Text, Text, Text, Text>{
	
	@Override
	protected void reduce(Text key, Iterable<Text> values, Reducer<Text, Text, Text, Text>.Context context)
			throws IOException, InterruptedException {
		int englishScore=0;
		int englishNum=0;
		int mathScore=0;
		int mathNum=0;
		int chineseScore=0;
		int chineseNum=0;
		
		for(Text value:values){
			String[] line=value.toString().split(" ");
			String course=line[0].split(".txt")[0];
			String score=line[1];
			System.out.println(value.toString());
			if("english".equals(course)){
				englishScore+=Integer.parseInt(score);
				englishNum++;
			}else if("math".equals(course)){
				mathScore+=Integer.parseInt(score);
				mathNum++;
			}else if("chinese".equals(course)){
				chineseScore+=Integer.parseInt(score);
				chineseNum++;
			}else{
				System.out.println("error");
			}
		}
		int englishAvg=englishScore/englishNum;
		int mathAvg=mathScore/mathNum;
		int chineseAvg=chineseScore/chineseNum;
		String data="chinese:"+chineseScore+" math:"+mathScore+" english:"+englishScore;
		context.write(key, new Text(data));
	}
}