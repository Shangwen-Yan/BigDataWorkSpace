package cn.tedu.storm.wc;

import java.util.Map;

import backtype.storm.spout.SpoutOutputCollector;
import backtype.storm.task.TopologyContext;
import backtype.storm.topology.OutputFieldsDeclarer;
import backtype.storm.topology.base.BaseRichSpout;
import backtype.storm.tuple.Fields;
import backtype.storm.tuple.Values;

public class SentenceSpout extends BaseRichSpout {

	private String [] sentences = {
		"i am so shuai",
		"do you like me",
		"are you sure you do not like me",
		"ok i am sure"
	};
	
	private SpoutOutputCollector collector = null;
	
	/**
	 * ������ISpout�ӿ�
	 * ��spout����ʼ��ʱ�����õķ��� ��������ʵ�ֳ�ʼ��
	 * 
	 * conf: ��ǰspout��������Ϣ
	 * context: ��ǰspout���е������Ķ��� ����������ȡ������ص���Ϣ ���� ������ ���������� ���������Ϣ��
	 * collector: ��������tuple�Ķ��� ����ͨ��������������ⷽ���з���tuple ����������̰߳�ȫ�� ���԰�ȫ�ı��������г�Ϊһ����Ա���� ����������������ʹ��
	 */
	@Override
	public void open(Map conf, TopologyContext context, SpoutOutputCollector collector) {
		this.collector = collector;
	}

	/**
	 * ������ICompoment�ӿ��еķ���
	 * storm�᲻ͣ���ظ������������ ��Ҫ����һ��tuple
	 * ��������������� ���������� û������Ҫ���� ��ֱ�ӷ��� 
	 * ������� ��һ���������߳��в�ͣ�ı����� ����������û������Ҫ���� �ٷ���֮ǰ ˯��һС��ʱ�� �Ա��ڲ������˷�̫���cpu
	 */
	private int index = 0;
	@Override
	public void nextTuple() {
		if(index<sentences.length){
			collector.emit(new Values(sentences[index]),index);
			index++;
		}else{
			try {
				Thread.sleep(1);
			} catch (InterruptedException e) {
				e.printStackTrace();
				throw new RuntimeException(e);
			}
			return;
		}
	}
	
	/**
	 * ������ICompoment�ӿ��еķ���
	 * �����������tuple�ĸ�ʽ�ķ��� ������spout����bolt�����Ҫ���tuple ��������tuple�ĸ�ʽ
	 * declarer:��������tuple��ʽ�Ķ��� ������������ ���ı�� �����tuple�ĸ�ʽ �Ƿ���һ��ִ���͵��� 
	 */
	@Override
	public void declareOutputFields(OutputFieldsDeclarer declarer) {
		declarer.declare(new Fields("sentence"));
	}
	
	/**
	 * ��tuple�������Ĵ���ɹ�ʱ �˷���������
	 */
	@Override
	public void ack(Object msgId) {
		System.out.println("tuple����ɹ��ˡ�����������" + msgId);
	}
	
	/**
	 * ��tuple������һ�������д���ʧ��ʱ �η���������
	 */
	@Override
	public void fail(Object msgId) {
		try {
			Thread.sleep(1000);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		System.out.println("tuple����ʧ���ˡ����������ط����ݡ�����" + msgId);
		collector.emit(new Values(sentences[(int) msgId]),msgId);
	}
}
