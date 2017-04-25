package com.glsx.biz.access.container.queue;

import kafka.consumer.Consumer;
import kafka.consumer.ConsumerConfig;
import kafka.consumer.ConsumerIterator;
import kafka.consumer.KafkaStream;
import kafka.message.MessageAndMetadata;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * @author Cruise.Xu
 * 
 */
public abstract class MessageReceiver {

	private final static Logger logger = LoggerFactory.getLogger(MessageReceiver.class);

	protected ConsumerConfig consumerConfig;

	protected String topic;

	protected String nThreads;

	protected kafka.javaapi.consumer.ConsumerConnector connector;
	protected Map<String, Integer> topicCountMap = new HashMap<String, Integer>();

	public abstract void processMessage(String topic,byte[] message);

	protected void init() {

		connector = Consumer.createJavaConsumerConnector(consumerConfig);
		int threadCnt = 1;
		if (null != nThreads && Integer.parseInt(nThreads) > 0) {
			threadCnt = Integer.parseInt(nThreads);
		}

		String[] topics = topic.split(",");
		for (int i = 0; i < topics.length; i++) {
			topicCountMap.put(topics[i], threadCnt);
		}

		Map<String, List<KafkaStream<byte[], byte[]>>> streams = connector.createMessageStreams(topicCountMap);

		List<KafkaStream<byte[], byte[]>> list = new ArrayList<KafkaStream<byte[], byte[]>>();
		for (String ss : topics) {
			list.addAll(streams.get(ss));
		}

		final ExecutorService executor = Executors.newFixedThreadPool(threadCnt * topics.length);
		for (final KafkaStream<byte[], byte[]> kafkaStream : list) {
			executor.submit(new Runnable() {
				public void run() {
					ConsumerIterator<byte[], byte[]> it = kafkaStream.iterator();
					while (it.hasNext()) {
						MessageAndMetadata<byte[], byte[]> item = it.next();
						byte[] message = item.message();
						
						try {
							processMessage(item.topic(),message);
						} catch (Exception e) {
							logger.error(e.getMessage());
						}

					}
				}
			});
		}

		Runtime.getRuntime().addShutdownHook(new Thread(new Runnable() {
			public void run() {
				executor.shutdown();
			}
		}));
	}
	public ConsumerConfig getConsumerConfig() {
		return consumerConfig;
	}
	public void setConsumerConfig(ConsumerConfig consumerConfig) {
		this.consumerConfig = consumerConfig;
	}
	public String getTopic() {
		return topic;
	}
	public void setTopic(String topic) {
		this.topic = topic.trim().replaceAll(" ", "");
	}
	public String getnThreads() {
		return nThreads;
	}
	public void setnThreads(String nThreads) {
		this.nThreads = nThreads;
	}

	public void destroy() {
		if (null != connector)
			connector.shutdown();
	}
}
