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
	 * 来自于ISpout接口
	 * 在spout被初始化时被调用的方法 可以用来实现初始化
	 * 
	 * conf: 当前spout的配置信息
	 * context: 当前spout运行的上下文对象 可以用来获取环境相关的信息 包括 任务编号 包括组件编号 输入输出信息等
	 * collector: 用来发送tuple的对象 可以通过这个对象在任意方法中发送tuple 这个对象是线程安全的 可以安全的保存在类中成为一个成员变量 方便在其他方法中使用
	 */
	@Override
	public void open(Map conf, TopologyContext context, SpoutOutputCollector collector) {
		this.collector = collector;
	}

	/**
	 * 来自于ICompoment接口中的方法
	 * storm会不停的重复调用这个方法 来要求发送一个tuple
	 * 这个方法不能阻塞 所以如果真的 没有数据要发送 就直接返回 
	 * 这个方法 在一个单独的线程中不停的被调用 所以如果真的没有数据要发送 再返回之前 睡上一小段时间 以便于不至于浪费太多的cpu
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
	 * 来自于ICompoment接口中的方法
	 * 用来声明输出tuple的格式的方法 无论是spout还是bolt如果需要输出tuple 必须声明tuple的格式
	 * declarer:用来声明tuple格式的对象 可以用来声明 流的编号 输出的tuple的格式 是否是一个执行型的流 
	 */
	@Override
	public void declareOutputFields(OutputFieldsDeclarer declarer) {
		declarer.declare(new Fields("sentence"));
	}
	
	/**
	 * 当tuple被完整的处理成功时 此方法被调用
	 */
	@Override
	public void ack(Object msgId) {
		System.out.println("tuple处理成功了。。。。。。" + msgId);
	}
	
	/**
	 * 当tuple在任意一个环节中处理失败时 次方法被调用
	 */
	@Override
	public void fail(Object msgId) {
		try {
			Thread.sleep(1000);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		System.out.println("tuple处理失败了。。。尝试重发数据。。。" + msgId);
		collector.emit(new Values(sentences[(int) msgId]),msgId);
	}
}
