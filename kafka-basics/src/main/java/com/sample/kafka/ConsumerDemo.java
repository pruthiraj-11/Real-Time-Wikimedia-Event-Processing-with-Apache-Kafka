 package com.sample.kafka;

import java.time.Duration;
import java.util.Arrays;
import java.util.Properties;

import org.apache.kafka.clients.consumer.ConsumerRecord;
import org.apache.kafka.clients.consumer.ConsumerRecords;
import org.apache.kafka.clients.consumer.KafkaConsumer;
import org.apache.kafka.common.serialization.StringDeserializer;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class ConsumerDemo {
	private static final Logger log = LoggerFactory.getLogger(ConsumerDemo.class.getSimpleName());
	
	public static void main(String[] args) {
		
		String topic = "first_topic";
		String groupId = "my-first-application";
		
		
		//create a Producer Properties
		Properties properties = new Properties();
		
		//connect to kafka localhost
		properties.setProperty("bootstrap.servers", "127.0.0.1:9092");
		
		//set consumer properties
		properties.setProperty("key.deserializer", StringDeserializer.class.getName());
		properties.setProperty("value.deserializer", StringDeserializer.class.getName());
		properties.setProperty("group.id", groupId);
		properties.setProperty("auto.offset.reset", "earliest");
		
		
		//create a Producer
		KafkaConsumer<String, String> consumer = new KafkaConsumer<>(properties);
		
		//subscribe to topic
		consumer.subscribe(Arrays.asList(topic));
		
		while(true) {
			log.info("Polling");
			
			ConsumerRecords<String, String> consumerRecords = consumer.poll(Duration.ofMillis(1000));
			
			for(ConsumerRecord<String, String> consumerRecord: consumerRecords) {
				log.info("key: " + consumerRecord.key() + " | " + "value: " + consumerRecord.value());
				log.info("partition: " + consumerRecord.partition() + " | " + "offset: " + consumerRecord.offset());
			}
		}

	}

}
