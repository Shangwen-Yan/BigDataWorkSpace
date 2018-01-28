package cn.tedu.storm.wc;

import java.util.Map;

import backtype.storm.task.IBolt;
import backtype.storm.task.OutputCollector;
import backtype.storm.task.TopologyContext;
import backtype.storm.topology.IComponent;
import backtype.storm.topology.OutputFieldsDeclarer;
import backtype.storm.topology.base.BaseRichBolt;
import backtype.storm.tuple.Fields;
import backtype.storm.tuple.Tuple;
import backtype.storm.tuple.Values;

public class SplitSentenceBolt extends BaseRichBolt{

	private OutputCollector collector = null;
	/**
	 * ������IBolt�ӿ�
	 * ��bolt����ʼ��ʱ�����õķ��� ��������ʵ�ֳ�ʼ��
	 * 
	 * conf: ��ǰbolt��������Ϣ
	 * context: ��ǰbolt���е������Ķ��� ����������ȡ������ص���Ϣ ���� ������ ���������� ���������Ϣ��
	 * collector: ��������tuple�Ķ��� ����ͨ��������������ⷽ���з���tuple ����������̰߳�ȫ�� ���԰�ȫ�ı��������г�Ϊһ����Ա���� ����������������ʹ��
	 */
	@Override
	public void prepare(Map stormConf, TopologyContext context, OutputCollector collector) {
		this.collector = collector;
	}

	/**
	 * ������IBolt�ķ���
	 * �������������еĵ�һ��tuple
	 * ��������������ж�tuple���д���
	 * ����������ͨ��prepare�������ṩ��collector������Ľ�����������
	 */
	boolean canNotPass = true;
	@Override
	public void execute(Tuple input) {
		try {
			String sentence = input.getStringByField("sentence");
			if(canNotPass && "i am so shuai".equals(sentence)){
				canNotPass = false;
				throw new RuntimeException("������ �ҹ���� ˧ Ūû�ˡ���");
			}
			String [] words = sentence.split(" ");
			for(String word : words){
				//--����emit����ʱ ����tuple���� ʵ��ê��
				collector.emit(input,new Values(word));
			}
			collector.ack(input);
		} catch (Exception e) {
			collector.fail(input);
			e.printStackTrace();
		}
	}

	/**
	 * ������ICompoment�ӿ��еķ���
	 * �����������tuple�ĸ�ʽ�ķ��� ������spout����bolt�����Ҫ���tuple ��������tuple�ĸ�ʽ
	 * declarer:��������tuple��ʽ�Ķ��� ������������ ���ı�� �����tuple�ĸ�ʽ �Ƿ���һ��ִ���͵��� 
	 */
	@Override
	public void declareOutputFields(OutputFieldsDeclarer declarer) {
		declarer.declare(new Fields("word"));
	}
	
}
