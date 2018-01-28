package cn.tedu.storm.wc;

import java.util.HashMap;
import java.util.Map;

import backtype.storm.task.OutputCollector;
import backtype.storm.task.TopologyContext;
import backtype.storm.topology.OutputFieldsDeclarer;
import backtype.storm.topology.base.BaseRichBolt;
import backtype.storm.tuple.Tuple;

public class ReportBolt extends BaseRichBolt {

	private OutputCollector collector = null;
	
	@Override
	public void prepare(Map stormConf, TopologyContext context, OutputCollector collector) {
		this.collector = collector;
	}

	private Map<String,Integer> map = new HashMap<>();
	
	@Override
	public void execute(Tuple input) {
		try {
			String word = input.getStringByField("word");
			int count = input.getIntegerByField("count");
			System.out.println("--发现单词数量发生了变化，word:"+word+"-count:"+count+"--");
			map.put(word, count);
			collector.ack(input);
		} catch (Exception e) {
			collector.fail(input);
			e.printStackTrace();
		}
	}

	@Override
	public void declareOutputFields(OutputFieldsDeclarer declarer) {
		
	}
	
	@Override
	public void cleanup() {
		System.out.println("===========================================");
		for(Map.Entry<String, Integer>entry : map.entrySet()){
			String word = entry.getKey();
			int count = entry.getValue();
			System.out.println("--word:"+word+"-count:"+count+"--");
		}
		System.out.println("===========================================");
	}

}
