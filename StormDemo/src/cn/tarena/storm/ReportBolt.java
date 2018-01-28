package cn.tarena.storm;

import java.util.HashMap;
import java.util.Map;

import backtype.storm.task.OutputCollector;
import backtype.storm.task.TopologyContext;
import backtype.storm.topology.OutputFieldsDeclarer;
import backtype.storm.topology.base.BaseRichBolt;
import backtype.storm.tuple.Fields;
import backtype.storm.tuple.Tuple;
import backtype.storm.tuple.Values;

public class ReportBolt extends BaseRichBolt {
	private OutputCollector collector=null;
	@Override
	public void prepare(Map stormConf, TopologyContext context, OutputCollector collector) {
		this.collector=collector;

	}

	private Map<String,Integer> map = new HashMap<>();
	@Override
	public void execute(Tuple input) {
		String word =input.getStringByField("word");
		int count=input.getIntegerByField("count");
		System.out.println("word:"+word+"\ncount:"+count);
		map.put(word, count);
		System.out.println("now, the mapSize is:"+map.size());

	}

	@Override
	public void declareOutputFields(OutputFieldsDeclarer declarer) {
		

	}
	@Override
	public void cleanup() {
		System.out.println("final mapSize is :"+map.size());
		for(Map.Entry<String, Integer> entry:map.entrySet()){
			String word =entry.getKey();
			int count=entry.getValue();
			System.out.println("word:"+word+"\ncount:"+count);
		}
		super.cleanup();
	}

}