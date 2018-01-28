package cn.tarena.flow;

import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapreduce.Partitioner;

//<>��Ӧmapper��Ӧ��key��value

public class FlowPartitioner extends Partitioner<Text, Flow>{


	@Override
	public int getPartition(Text key, Flow value, int n) {
		if(value.getAddr().equals("bj")){
			return 0;
		}else if(value.getAddr().equals("sh")){
			return 1;
		}else{
			return 2;
		}
	}

}
