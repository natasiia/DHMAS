package com.dhmas.has;
import com.dhmas.has.pojo.StatusLocalis;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.apache.kafka.clients.producer.KafkaProducer;
import org.apache.kafka.clients.producer.ProducerRecord;

import java.util.Properties;

public class Producer {
    public static void main(String[] args) throws JsonProcessingException {
        ObjectMapper objectMapper = new ObjectMapper();
        Properties properties = new Properties();
        properties.put("bootstrap.servers","localhost:9092");
        properties.put("acks","all");
        properties.put("key.serializer","org.apache.kafka.common.serialization.StringSerializer");
        properties.put("value.serializer","org.apache.kafka.common.serialization.StringSerializer");
        KafkaProducer<String,String> kafkaProducer = new KafkaProducer<>(properties);


        for (int i = 0; i <100 ; i++) {
            StatusLocalis statusLocalis = new StatusLocalis();
            statusLocalis.setSpO2(i);
            String s = objectMapper.writeValueAsString(statusLocalis);
            kafkaProducer.send(new ProducerRecord<String,String>("analysis-alert",null,s));
            System.out.println("消息"+i);

        }
        kafkaProducer.close();
    }
}
