package com.sample.kafka;

import java.util.Properties;

import org.apache.kafka.clients.producer.Callback;
import org.apache.kafka.clients.producer.KafkaProducer;
import org.apache.kafka.clients.producer.ProducerRecord;
import org.apache.kafka.clients.producer.RecordMetadata;
import org.apache.kafka.common.serialization.StringSerializer;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class ProducerDemoWithCallBack {
	private static final Logger log = LoggerFactory.getLogger(ProducerDemoWithCallBack.class.getSimpleName());
	
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
		
		 for (int j=0; j<10; j++){
	         // create a Producer Record
			 for(int i=0; i < 5; i++) {

				 ProducerRecord<String, String> producerRecord = new ProducerRecord<>("first_topic", "hello world " + j);
	
	             // send data
	             producer.send(producerRecord, new Callback() {
	                 @Override
	                 public void onCompletion(RecordMetadata metadata, Exception e) {
	                     // executes every time a record successfully sent or an exception is thrown
	                     if (e == null) {
	                         // the record was successfully sent
	                         log.info("Received new metadata \n" +
	                                 "Topic: " + metadata.topic() + "\n" +
	                                 "Partition: " + metadata.partition() + "\n" +
	                                 "Offset: " + metadata.offset() + "\n" +
	                                 "Timestamp: " + metadata.timestamp());
	                     } else {
	                         log.error("Error while producing", e);
	                     }
	                 }
	             });
			 }
			 
			 try {
                 Thread.sleep(500);
             } catch (InterruptedException e) {
                 e.printStackTrace();
             }
		}

		
		
		//tell producer to send all data and block until done -- synchrounous
		producer.flush();
		
		//flush and close producer
		producer.close();

	}

}
