package com.dhmas.HAS;

import com.dhmas.HAS.mapper.GeneralInfoMapper;
import com.dhmas.HAS.mapper.Patientstatus;
import com.dhmas.HAS.pojo.AlertMessage;
import com.dhmas.HAS.pojo.GeneralInfo;
import com.dhmas.HAS.pojo.StatusLocalis;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;
import jakarta.websocket.EncodeException;
import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;
import org.apache.kafka.clients.consumer.ConsumerRecord;
import org.apache.kafka.clients.consumer.ConsumerRecords;
import org.apache.kafka.clients.consumer.KafkaConsumer;
import org.apache.kafka.clients.producer.KafkaProducer;
import org.apache.kafka.clients.producer.ProducerRecord;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.io.IOException;
import java.io.InputStream;
import java.time.Duration;
import java.util.Arrays;
import java.util.Properties;

@SpringBootApplication
public class HasApplication {
    static ObjectMapper objectMapper = new ObjectMapper().registerModule(new JavaTimeModule());
    public static void main(String[] args) throws IOException, EncodeException {
        SpringApplication.run(HasApplication.class, args);

        //Create kafka Consumer from PDCS
        Properties properties = new Properties();
        properties.setProperty("bootstrap.servers","localhost:9092");
        properties.setProperty("group.id","myinfo");
        properties.setProperty("enable.auto.commit","true");
        properties.setProperty("auto.commit.interval.ms","1000");
        properties.setProperty("key.deserializer","org.apache.kafka.common.serialization.StringDeserializer");
        properties.setProperty("value.deserializer","org.apache.kafka.common.serialization.StringDeserializer");

        KafkaConsumer<String, String> kafkaConsumer = new KafkaConsumer<>(properties);
        kafkaConsumer.subscribe(Arrays.asList("pdcs_topic"));
        while (true){
            ConsumerRecords<String, String> records = kafkaConsumer.poll(Duration.ofSeconds(5));
            for (ConsumerRecord<String, String> c:records ) {
                System.out.println(c);
                StatusLocalis statusLocalis = objectMapper.readValue(c.value(), StatusLocalis.class);
                messageHandle(statusLocalis);
            }
        }

    }
    public static void messageHandle(StatusLocalis statusLocalis) throws EncodeException, IOException {
        //health status judgement
        statusLocalis.setStatus("Normal");
        if (statusLocalis.getRespiratory_rate()>16 ||statusLocalis.getHeart_rate()<40
                ||statusLocalis.getHeart_rate()>120 ||statusLocalis.getSpO2()<90
                ||statusLocalis.getTemperature()<36 ||statusLocalis.getTemperature()>37.5){
            statusLocalis.setStatus("Abnormal");
        }
        //Create sqlSessionFactory
        String resource = "mybatis-config.xml";
        InputStream inputStream = Resources.getResourceAsStream(resource);
        SqlSessionFactory sqlSessionFactory = new SqlSessionFactoryBuilder().build(inputStream);
        System.out.println("mybatis runing!");

        //Open session in sql
        SqlSession sqlSession = sqlSessionFactory.openSession();
        //Create Mapper object in two tables
        Patientstatus patientstatusMapper = sqlSession.getMapper(Patientstatus.class);
        GeneralInfoMapper generalInfomapper = sqlSession.getMapper(GeneralInfoMapper.class);
        //Add data into table
        patientstatusMapper.add(statusLocalis);


        //produce kafka if abnormal
        if("Abnormal".equals (statusLocalis.getStatus())){
            String patientId = statusLocalis.getPatient_id();
            GeneralInfo generalInfo = generalInfomapper.selectById(patientId);
            AlertMessage alertMessage = new AlertMessage(generalInfo, statusLocalis);
            String s = objectMapper.writeValueAsString(alertMessage);

        //kafka's topic is "analysis-alert"，key is null,value is alertMessage
            ProducerRecord<String, String> stringRecord = new ProducerRecord<>("analysis-alert", null, s);

        //Create Producer
            Properties properties1 = new Properties();
            properties1.put("bootstrap.servers","localhost:9092");
            properties1.put("acks","all");
            properties1.put("key.serializer","org.apache.kafka.common.serialization.StringSerializer");
            properties1.put("value.serializer","org.apache.kafka.common.serialization.StringSerializer");
            KafkaProducer<String,String> kafkaProducer = new KafkaProducer<>(properties1);

        //Send data
            kafkaProducer.send(stringRecord);
            System.out.println("*************************");
            System.out.println(stringRecord);
            kafkaProducer.close();
        }

        sqlSession.commit();
        sqlSession.close();
    }

}
