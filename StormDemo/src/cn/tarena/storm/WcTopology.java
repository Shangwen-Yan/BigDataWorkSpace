package cn.tarena.storm;

import backtype.storm.Config;
import backtype.storm.LocalCluster;
import backtype.storm.StormSubmitter;
import backtype.storm.generated.AlreadyAliveException;
import backtype.storm.generated.InvalidTopologyException;
import backtype.storm.generated.StormTopology;
import backtype.storm.topology.TopologyBuilder;
import backtype.storm.tuple.Fields;

public class WcTopology {
	public static void main(String[] args) throws Exception, InvalidTopologyException {
		SentenceSpout sentenceSpout=new SentenceSpout();
		SplitSentenceBolt splitSentenceBolt = new SplitSentenceBolt();
		WordCountBolt wordCountBolt = new WordCountBolt();
		ReportBolt reportBolt = new ReportBolt();
		
		TopologyBuilder buider = new TopologyBuilder();
		buider.setSpout("sentenceSpout", sentenceSpout);
		buider.setBolt("splitSentenceBolt", splitSentenceBolt).shuffleGrouping("sentenceSpout");
		buider.setBolt("wordCountBolt", wordCountBolt).fieldsGrouping("splitSentenceBolt", new Fields("word"));
		buider.setBolt("reportBolt", reportBolt).globalGrouping("wordCountBolt");
				
		StormTopology stormTopology = buider.createTopology();
		LocalCluster cluster = new LocalCluster();
		Config conf = new Config();
		cluster.submitTopology("wcTopology", conf, stormTopology);
		
		
		/*Config conf = new Config();
		StormSubmitter.submitTopology("wcTopology", conf, stormTopology);*/
		
		Thread.sleep(10000);
		cluster.killTopology("wcTopology");
		cluster.shutdown();
	}

}
