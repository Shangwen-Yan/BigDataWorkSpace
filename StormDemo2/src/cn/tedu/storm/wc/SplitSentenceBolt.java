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
	 * 来自于IBolt接口
	 * 在bolt被初始化时被调用的方法 可以用来实现初始化
	 * 
	 * conf: 当前bolt的配置信息
	 * context: 当前bolt运行的上下文对象 可以用来获取环境相关的信息 包括 任务编号 包括组件编号 输入输出信息等
	 * collector: 用来发送tuple的对象 可以通过这个对象在任意方法中发送tuple 这个对象是线程安全的 可以安全的保存在类中成为一个成员变量 方便在其他方法中使用
	 */
	@Override
	public void prepare(Map stormConf, TopologyContext context, OutputCollector collector) {
		this.collector = collector;
	}

	/**
	 * 来自于IBolt的方法
	 * 用来处理输入中的单一的tuple
	 * 可以在这个方法中队tuple进行处理
	 * 处理过后可以通过prepare方法中提供的collector将处理的结果继续向后发送
	 */
	boolean canNotPass = true;
	@Override
	public void execute(Tuple input) {
		try {
			String sentence = input.getStringByField("sentence");
			if(canNotPass && "i am so shuai".equals(sentence)){
				canNotPass = false;
				throw new RuntimeException("哈哈哈 我故意把 帅 弄没了。。");
			}
			String [] words = sentence.split(" ");
			for(String word : words){
				//--调用emit方法时 将父tuple传入 实现锚定
				collector.emit(input,new Values(word));
			}
			collector.ack(input);
		} catch (Exception e) {
			collector.fail(input);
			e.printStackTrace();
		}
	}

	/**
	 * 来自于ICompoment接口中的方法
	 * 用来声明输出tuple的格式的方法 无论是spout还是bolt如果需要输出tuple 必须声明tuple的格式
	 * declarer:用来声明tuple格式的对象 可以用来声明 流的编号 输出的tuple的格式 是否是一个执行型的流 
	 */
	@Override
	public void declareOutputFields(OutputFieldsDeclarer declarer) {
		declarer.declare(new Fields("word"));
	}
	
}
