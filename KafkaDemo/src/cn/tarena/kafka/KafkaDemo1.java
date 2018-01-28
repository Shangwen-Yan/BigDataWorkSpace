package cn.tarena.kafka;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Properties;

import org.junit.Test;

import kafka.consumer.Consumer;
import kafka.consumer.ConsumerConfig;
import kafka.consumer.ConsumerIterator;
import kafka.consumer.KafkaStream;
import kafka.javaapi.consumer.ConsumerConnector;
import kafka.javaapi.producer.Producer;
import kafka.producer.KeyedMessage;
import kafka.producer.ProducerConfig;

public class KafkaDemo1 {
		/**
		��������
	*/
	@Test
	public void ProducerSend(){
		Properties props = new Properties();
		props.put("serializer.class", "kafka.serializer.StringEncoder");
	    props.put("metadata.broker.list", "hadoop01:9092,hadoop02:9092,hadoop03:9092");
		Producer<Integer, String> producer = new Producer<Integer, String>(new ProducerConfig(props ));
		producer.send(new KeyedMessage<Integer, String>("my-replicated-topic","message~xxx123asdf"));
		producer.close();
	}
	
	/**
	��������
	*/
	@Test
	public void ConsumerReceive() throws Exception{
		Properties properties = new Properties();  
		properties.put("zookeeper.connect", "hadoop01:2181,hadoop02:2181,hadoop03:2181");//����zk  
		properties.put("group.id", "g1");// ����Ҫʹ�ñ�������ƣ� ��������ߺ������߶���ͬһ�飬���ܷ���ͬһ���ڵ�topic����  
		properties.put("auto.offset.reset", "smallest");
		//		properties.put("zookeeper.session.timeout.ms", "400");
		//		properties.put("zookeeper.sync.time.ms", "200");
		//		properties.put("auto.commit.interval.ms", "1000");
		//		properties.put("serializer.class", "kafka.serializer.StringEncoder");
		ConsumerConnector consumer = Consumer.createJavaConsumerConnector(new ConsumerConfig(properties));  
		
		Map<String, Integer> topicCountMap = new HashMap<String, Integer>();  
		topicCountMap.put("my-replicated-topic", 1); // һ�δ������л�ȡһ������  
		Map<String, List<KafkaStream<byte[], byte[]>>>  messageStreams = consumer.createMessageStreams(topicCountMap);  
		KafkaStream<byte[], byte[]> stream = messageStreams.get("my-replicated-topic").get(0);// ��ȡÿ�ν��յ����������  
		ConsumerIterator<byte[], byte[]> iterator =  stream.iterator();  
		
		while(iterator.hasNext()){
			System.out.println("receive��" + new String(iterator.next().message()));
		}
	}
}
