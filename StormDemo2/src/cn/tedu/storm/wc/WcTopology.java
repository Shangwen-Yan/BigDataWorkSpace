package cn.tedu.storm.wc;

import backtype.storm.Config;
import backtype.storm.LocalCluster;
import backtype.storm.generated.StormTopology;
import backtype.storm.topology.TopologyBuilder;
import backtype.storm.tuple.Fields;

public class WcTopology {
	public static void main(String[] args) throws Exception {
		//1.�����齨����
		SentenceSpout sentenceSpout = new SentenceSpout();
		SplitSentenceBolt splitBolt = new SplitSentenceBolt();
		WordCountBolt wcBolt = new WordCountBolt();
		ReportBolt reportBolt = new ReportBolt();
		
		//2.����������
		TopologyBuilder builder = new TopologyBuilder();
		
		//3.�򹹽����������˽ṹ
		builder.setSpout("Sentence_Spout", sentenceSpout);
		//--�����߳�����
		//builder.setSpout("Sentence_Spout", sentenceSpout,2);
		builder.setBolt("Split_Sentence_Bolt",splitBolt).shuffleGrouping("Sentence_Spout");
		//--����task������
		//builder.setBolt("Split_Sentence_Bolt",splitBolt).setNumTasks(3).shuffleGrouping("Sentence_Spout");
		builder.setBolt("Word_Count_Bolt", wcBolt).fieldsGrouping("Split_Sentence_Bolt", new Fields("word"));
		builder.setBolt("Report_Bolt", reportBolt).globalGrouping("Word_Count_Bolt");
		
		//4.ͨ�������ߴ�������
		StormTopology topology = builder.createTopology();
		
		//5.�������ύ��storm��Ⱥ������
		//Config conf = new Config();
		//StormSubmitter.submitTopology("Wc_Topology", conf, topology);
		
		//5.�������ύ��storm��Ⱥ������ - ����ģ��
		Config conf = new Config();
		//--����worker������ ע�� ��������ģʽ�� ����workerû���κ�Ч��
		//conf.setNumWorkers(2);
		LocalCluster cluster = new LocalCluster();
		cluster.submitTopology("Wc_Topology", conf, topology);
		
		//6.�ر����˺ͼ�Ⱥ - ����ģʽ
		Thread.sleep(10 * 1000);
		cluster.killTopology("Wc_Topology");
		cluster.shutdown();
	}
}
