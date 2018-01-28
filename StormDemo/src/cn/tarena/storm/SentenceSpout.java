package cn.tarena.storm;

import java.util.Map;

import backtype.storm.spout.ISpout;
import backtype.storm.spout.SpoutOutputCollector;
import backtype.storm.task.TopologyContext;
import backtype.storm.topology.IComponent;
import backtype.storm.topology.OutputFieldsDeclarer;
import backtype.storm.topology.base.BaseRichSpout;
import backtype.storm.tuple.Fields;
import backtype.storm.tuple.Values;

public class SentenceSpout extends BaseRichSpout{
	
	private String[] sentences={
			"i am so handsome",
			"do you love me",
			"of course not",
			"well, it is ok"
	};
	private SpoutOutputCollector collector;
	private int index= 0;
	@Override
	public void open(Map conf, TopologyContext context, SpoutOutputCollector collector) {
		this.collector=collector;
		
	}

	@Override
	public void nextTuple() {
		if(index<sentences.length){
			collector.emit(new Values(sentences[index]));
			index++;
		}else{
			try {
				Thread.sleep(1);
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			return;
		}
		
		
	}

	@Override
	public void declareOutputFields(OutputFieldsDeclarer declarer) {
		declarer.declare(new Fields("sentence"));
		
	}
}
