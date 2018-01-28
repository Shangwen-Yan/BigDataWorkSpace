package cn.tedu.storm.wc;

import backtype.storm.Config;
import backtype.storm.LocalCluster;
import backtype.storm.generated.StormTopology;
import backtype.storm.topology.TopologyBuilder;
import backtype.storm.tuple.Fields;

public class WcTopology {
	public static void main(String[] args) throws Exception {
		//1.创建组建对象
		SentenceSpout sentenceSpout = new SentenceSpout();
		SplitSentenceBolt splitBolt = new SplitSentenceBolt();
		WordCountBolt wcBolt = new WordCountBolt();
		ReportBolt reportBolt = new ReportBolt();
		
		//2.创建构建者
		TopologyBuilder builder = new TopologyBuilder();
		
		//3.向构建者描述拓扑结构
		builder.setSpout("Sentence_Spout", sentenceSpout);
		//--增加线程数量
		//builder.setSpout("Sentence_Spout", sentenceSpout,2);
		builder.setBolt("Split_Sentence_Bolt",splitBolt).shuffleGrouping("Sentence_Spout");
		//--增加task的数量
		//builder.setBolt("Split_Sentence_Bolt",splitBolt).setNumTasks(3).shuffleGrouping("Sentence_Spout");
		builder.setBolt("Word_Count_Bolt", wcBolt).fieldsGrouping("Split_Sentence_Bolt", new Fields("word"));
		builder.setBolt("Report_Bolt", reportBolt).globalGrouping("Word_Count_Bolt");
		
		//4.通过构建者创建拓扑
		StormTopology topology = builder.createTopology();
		
		//5.将拓扑提交到storm集群中运行
		//Config conf = new Config();
		//StormSubmitter.submitTopology("Wc_Topology", conf, topology);
		
		//5.将拓扑提交到storm集群中运行 - 本地模拟
		Config conf = new Config();
		//--增加worker的数量 注意 单机测试模式下 增加worker没有任何效果
		//conf.setNumWorkers(2);
		LocalCluster cluster = new LocalCluster();
		cluster.submitTopology("Wc_Topology", conf, topology);
		
		//6.关闭拓扑和集群 - 本地模式
		Thread.sleep(10 * 1000);
		cluster.killTopology("Wc_Topology");
		cluster.shutdown();
	}
}
