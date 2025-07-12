package com.sample.kafka;


import java.net.URI;
import java.util.Properties;
import java.util.concurrent.TimeUnit;

import org.apache.kafka.clients.producer.KafkaProducer;
import org.apache.kafka.clients.producer.ProducerConfig;
import org.apache.kafka.common.serialization.StringSerializer;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.launchdarkly.eventsource.EventSource;
import com.launchdarkly.eventsource.background.BackgroundEventHandler;
import com.launchdarkly.eventsource.background.BackgroundEventSource;

public class WikimediaChangesProducer {
	
	private static final Logger log = LoggerFactory.getLogger(WikimediaChangesProducer.class.getSimpleName());

	public static void main(String[] args) throws InterruptedException {
		System.out.println("Wikimedia Producer!");
		
		String bootstrapServer = "127.0.0.1:9092";
		
		Properties properties = new Properties();
		
		//connect to kafka localhost
		properties.setProperty(ProducerConfig.BOOTSTRAP_SERVERS_CONFIG, "127.0.0.1:9092");
	    properties.setProperty(ProducerConfig.KEY_SERIALIZER_CLASS_CONFIG, StringSerializer.class.getName());
	    properties.setProperty(ProducerConfig.VALUE_SERIALIZER_CLASS_CONFIG, StringSerializer.class.getName());
		
		
		//create a Producer
		KafkaProducer<String, String> producer = new KafkaProducer<>(properties);
		
		
		String topic = "wikimedia.recentchange";
		BackgroundEventHandler eventHandler = new WikimediaChangeHandler(producer,topic);
		
		String url = "https://stream.wikimedia.org/v2/stream/recentchange";
		EventSource.Builder eventSourceBuilder = new EventSource.Builder(URI.create(url));
		BackgroundEventSource.Builder builder = new BackgroundEventSource.Builder(eventHandler, eventSourceBuilder);
		BackgroundEventSource eventSource = builder.build();
		 
		 
		//start the producer in another thread
		eventSource.start();
		
		
		//produce for 1 min and block program untill then
		TimeUnit.MINUTES.sleep(1);
	}

}
