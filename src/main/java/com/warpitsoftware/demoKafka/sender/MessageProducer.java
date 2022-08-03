package com.warpitsoftware.demoKafka.sender;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.kafka.support.SendResult;
import org.springframework.messaging.handler.annotation.Payload;
import org.springframework.stereotype.Component;
import org.springframework.util.concurrent.ListenableFuture;

/**
 * In order to produce the messages and send to the Topic
 * @author absab
 *
 */
@Component
public class MessageProducer {

	private static final Logger log =LoggerFactory.getLogger(MessageProducer.class);

    @Autowired 
    private KafkaTemplate<String, String> kafkaTemplate;

    @Value("${myapp.kafka.topic}")
    private String topic;

    public void sendMessage(String message) {
       log.info("MESSAGE SENT FROM PRODUCER END -> {}" , message);
       kafkaTemplate.send(topic, message);
     //  ListenableFuture<SendResult<String, Payload>> sr = kafkaTemplate.send(topic, payload);
       kafkaTemplate.flush();
    }
	
}

//If you are using KafkaProducer for configuring Kafka, You can close the channel as below :

//Producer<String, String> producer = new KafkaProducer<>(props);
//  producer.send(new ProducerRecord<String, String>("my-topic", 
//  Integer.toString("any Data"), Integer.toString(i)));

//  producer.close();

//If you are using KafkaTemplate of async calls, You can do it as :
//     ListenableFuture<SendResult<String, Payload>> sr = kafkaTemplate.send(topic, payload);
 //   kafkaTemplate.flush();
