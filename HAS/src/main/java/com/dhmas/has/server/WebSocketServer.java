package com.dhmas.has.server;

import com.dhmas.has.HasApplication;
import com.dhmas.has.mapper.GeneralInfoMapper;
import com.dhmas.has.mapper.Patientstatus;
import com.dhmas.has.pojo.*;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.extern.slf4j.Slf4j;
import org.apache.ibatis.session.SqlSession;
import org.apache.kafka.clients.producer.KafkaProducer;
import org.apache.kafka.clients.producer.ProducerRecord;
import org.springframework.stereotype.Component;

import javax.websocket.*;
import javax.websocket.server.ServerEndpoint;
import java.io.IOException;
import java.util.List;
import java.util.concurrent.CopyOnWriteArraySet;

@Component
@ServerEndpoint(value = "/webSoket",decoders = {GeneralInfoDecoder.class, StatusLocalisDecoder.class},
                          encoders = {GeneralInfoEncoder.class, StatusLocalisEncoder.class})
@Slf4j
public class WebSocketServer {
    ObjectMapper objectMapper = new ObjectMapper();
    private Session session;
    private static CopyOnWriteArraySet<WebSocketServer> webSocketServers=new CopyOnWriteArraySet<>();
    @OnOpen
    public void onOpen(Session session) throws EncodeException, IOException {
        this.session=session;
        webSocketServers.add(this);
        log.info("webSocket client connected!");
//        alertMessageSend();
    }

    @OnClose
    public void onClose(Session session){
        webSocketServers.remove(this);
        log.info("webSocket client disconnect");
    }

    @OnMessage
    public void onMessage(StatusLocalis statusLocalis) throws EncodeException, IOException {
        messageHandle(statusLocalis);
        this.session.getBasicRemote().sendText("Received message!!");

    }
    public void messageHandle(StatusLocalis statusLocalis) throws EncodeException, IOException {
//        健康判断（按给定条件）
        statusLocalis.setStatus("Normal");
        if (statusLocalis.getRespiratory_rate()<35 ||statusLocalis.getHeart_rate()<40
             ||statusLocalis.getHeart_rate()>120 ||statusLocalis.getSpO2()<90
             ||statusLocalis.getTemperature()<36 ||statusLocalis.getTemperature()>37.5){
            statusLocalis.setStatus("Abnormal");
        }
//        数据库操作
        SqlSession sqlSession = HasApplication.sqlSessionFactory.openSession();
//        创建数据库2个表的操作对象
        Patientstatus patientstatusMapper = sqlSession.getMapper(Patientstatus.class);
        GeneralInfoMapper generalInfomapper = sqlSession.getMapper(GeneralInfoMapper.class);
//        判断表中有无该对象记录
        StatusLocalis statusLocalis1 = patientstatusMapper.selectBypatient_id(statusLocalis.getPatient_id());
        if(statusLocalis1==null){
//            表中无记录则添加
            patientstatusMapper.add(statusLocalis);
        }else {
//            表中有记录则修改记录
            patientstatusMapper.updateAll(statusLocalis);
        }

//        回送至PDCS
        this.session.getBasicRemote().sendObject(statusLocalis);
//        若不健康则发kafka
        if("Abnormal".equals (statusLocalis.getStatus())){
            Integer patientId = statusLocalis.getPatient_id();
            GeneralInfo generalInfo = generalInfomapper.selectById(patientId);
            AlertMessage alertMessage = new AlertMessage(generalInfo, statusLocalis);
            String s = objectMapper.writeValueAsString(alertMessage);
//            kafka的topic为"analysis-alert"，键为null,值（发送的数据）为alertMessage
            ProducerRecord<String, String> stringRecord = new ProducerRecord<>("analysis-alert", null, s);
//            创建Producer
            KafkaProducer<String,String> kafkaProducer = new KafkaProducer<>(HasApplication.properties);
//            发送数据
            kafkaProducer.send(stringRecord);
            System.out.println("*************************");
            System.out.println(stringRecord);
            kafkaProducer.close();

        }

        sqlSession.commit();
        sqlSession.close();
    }


//    将patientstatus表中所有status为"Abnormal"的对象发送（kafka）
    public void alertMessageSend() throws JsonProcessingException {
        SqlSession sqlSession = HasApplication.sqlSessionFactory.openSession();
        Patientstatus patientstatusMapper = sqlSession.getMapper(Patientstatus.class);
        GeneralInfoMapper generalInfomapper = sqlSession.getMapper(GeneralInfoMapper.class);
        List<StatusLocalis> abnormal = patientstatusMapper.selectBystatus("Abnormal");
        for (StatusLocalis s:abnormal ) {
            Integer patientId = s.getPatient_id();
            GeneralInfo generalInfo = generalInfomapper.selectById(patientId);
            AlertMessage alertMessage1 = new AlertMessage(generalInfo, s);
            String c = objectMapper.writeValueAsString(alertMessage1);
            ProducerRecord<String, String> stringRecord1 = new ProducerRecord<>("analysis-alert", null, c);
            KafkaProducer<String,String> kafkaProducer1 = new KafkaProducer<>(HasApplication.properties);
            kafkaProducer1.send(stringRecord1);
            System.out.println("*************************");
            kafkaProducer1.close();
        }
    }
}
