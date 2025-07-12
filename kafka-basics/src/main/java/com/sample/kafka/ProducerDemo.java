package com.sample.kafka;

import java.util.Properties;

import org.apache.kafka.clients.producer.KafkaProducer;
import org.apache.kafka.clients.producer.ProducerRecord;
import org.apache.kafka.common.serialization.StringSerializer;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class ProducerDemo {
	private static final Logger log = LoggerFactory.getLogger(ProducerDemo.class.getSimpleName());
	
	public static void main(String[] args) {
		//create a Producer Properties
		Properties properties = new Properties();
		
		//connect to kafka localhost
		properties.setProperty("bootstrap.servers", "127.0.0.1:9092");
		
		//set producer properties
		properties.setProperty("key.serializer", StringSerializer.class.getName());
		properties.setProperty("value.serializer", StringSerializer.class.getName());
		
		
		//create a Producer
		KafkaProducer<String, String> producer = new KafkaProducer<>(properties);
		
		//create a Producer Record
		ProducerRecord<String, String> producerRecord = new ProducerRecord<>("first_topic", "hello world!");
		
		//send data
		producer.send(producerRecord);
		
		
		//tell producer to send all data and block until done -- synchrounous
		producer.flush();
		
		//flush and close producer
		producer.close();

	}

}
