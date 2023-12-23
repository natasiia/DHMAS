package com.dhmas.has;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

@SpringBootApplication
public class HasApplication {
    public static SqlSessionFactory sqlSessionFactory;
    public static Properties properties;
    public static void main(String[] args) throws IOException {
        SpringApplication.run(HasApplication.class, args);
//Create sqlSessionFactory for database operations
        String resource = "mybatis-config.xml";
        InputStream inputStream = Resources.getResourceAsStream(resource);
        sqlSessionFactory = new SqlSessionFactoryBuilder().build(inputStream);
        System.out.println("mybatis runing!");

//Define properties of kafka
        properties = new Properties();
        properties.put("bootstrap.servers","localhost:9092");
        properties.put("acks","all");
        properties.put("key.serializer","org.apache.kafka.common.serialization.StringSerializer");
        properties.put("value.serializer","org.apache.kafka.common.serialization.StringSerializer");
    }

}
