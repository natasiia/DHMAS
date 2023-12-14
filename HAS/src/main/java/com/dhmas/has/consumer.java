package com.dhmas.has;

import com.dhmas.has.pojo.AlertMessage;
import com.dhmas.has.pojo.StatusLocalis;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.apache.kafka.clients.consumer.ConsumerRecord;
import org.apache.kafka.clients.consumer.ConsumerRecords;
import org.apache.kafka.clients.consumer.KafkaConsumer;
import org.apache.kafka.clients.producer.KafkaProducer;
import org.apache.kafka.clients.producer.ProducerRecord;

import java.io.DataInput;
import java.io.IOException;
import java.time.Duration;
import java.util.Arrays;
import java.util.Properties;

public class consumer {
    public static void main(String[] args) throws IOException {
        ObjectMapper objectMapper = new ObjectMapper();
        Properties properties = new Properties();
        properties.setProperty("bootstrap.servers","localhost:9092");
        properties.setProperty("group.id","myinfo");
        properties.setProperty("enable.auto.commit","true");
        properties.setProperty("auto.commit.interval.ms","1000");
        properties.setProperty("key.deserializer","org.apache.kafka.common.serialization.StringDeserializer");
        properties.setProperty("value.deserializer","org.apache.kafka.common.serialization.StringDeserializer");
        KafkaConsumer<String, String> kafkaConsumer = new KafkaConsumer<>(properties);
        kafkaConsumer.subscribe(Arrays.asList("analysis-alert"));
        while (true){
            ConsumerRecords<String, String> records = kafkaConsumer.poll(Duration.ofSeconds(5));
            for (ConsumerRecord<String, String> c:records ) {

                System.out.println(c);

//                StatusLocalis statusLocalis = objectMapper.readValue(c.value(), StatusLocalis.class);
                AlertMessage alertMessages = objectMapper.readValue(c.value(), AlertMessage.class);
                System.out.println("--------------------------------------------");
                System.out.println(alertMessages.getGeneralInfo());
                System.out.println(alertMessages.getStatusLocalis());
//                System.out.println(statusLocalis.getSpO2());
                System.out.println(alertMessages.getStatusLocalis().getSpO2());
//                System.out.println(alertMessages.getGeneralInfo().getGender());
//
            }

            }


        }



    }


