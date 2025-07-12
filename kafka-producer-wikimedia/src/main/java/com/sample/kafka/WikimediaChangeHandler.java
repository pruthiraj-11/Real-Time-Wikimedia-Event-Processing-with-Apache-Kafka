package com.sample.kafka;

import org.apache.kafka.clients.producer.KafkaProducer;
import org.apache.kafka.clients.producer.ProducerRecord;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.launchdarkly.eventsource.MessageEvent;
import com.launchdarkly.eventsource.background.BackgroundEventHandler;

public class WikimediaChangeHandler implements BackgroundEventHandler{
	private static final Logger log = LoggerFactory.getLogger(WikimediaChangeHandler.class.getSimpleName());
	
	private KafkaProducer<String, String> producer;
	private String topic;

	
	public WikimediaChangeHandler(KafkaProducer<String, String> producer, String topic) {
		this.producer = producer;
		this.topic = topic;
	}
	
	
	@Override
	public void onOpen() throws Exception {
		log.info("Stream Started!");
		
	}

	@Override
	public void onClosed() throws Exception {
		producer.close();
		log.info("Stream Stoped!");
	}

	@Override
	public void onMessage(String event, MessageEvent messageEvent) throws Exception {
		log.info(messageEvent.getData());
		producer.send(new ProducerRecord<String, String>(topic, messageEvent.getData()));	
	}

	@Override
	public void onComment(String comment) throws Exception {
		// nothing
		
	}

	@Override
	public void onError(Throwable t) {
		log.error("Error occured: " + t);
		
	}

}
