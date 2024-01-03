package com.dhmas.HAS;

import static org.mockito.Mockito.*;

import com.dhmas.HAS.mapper.Patientstatus;
import com.dhmas.HAS.mapper.GeneralInfoMapper;
import com.dhmas.HAS.pojo.StatusLocalis;
import com.dhmas.HAS.pojo.GeneralInfo;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.kafka.clients.producer.KafkaProducer;
import org.apache.kafka.clients.producer.ProducerRecord;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.time.LocalDateTime;

@ExtendWith(MockitoExtension.class)
public class HasApplicationTest {
    @Mock
    private SqlSessionFactory sqlSessionFactory;
    @Mock
    private SqlSession sqlSession;
    @Mock
    private Patientstatus patientstatusMapper;
    @Mock
    private GeneralInfoMapper generalInfomapper;
    @Mock
    private KafkaProducer<String, String> kafkaProducer;

    @InjectMocks
    private HasApplication hasApplication;

    @BeforeEach
    void setUp() {
        when(sqlSessionFactory.openSession()).thenReturn(sqlSession);
        when(sqlSession.getMapper(Patientstatus.class)).thenReturn(patientstatusMapper);
        when(sqlSession.getMapper(GeneralInfoMapper.class)).thenReturn(generalInfomapper);
    }

    @Test
    public void testMessageHandle() throws Exception {

        StatusLocalis statusLocalis = new StatusLocalis();

        statusLocalis.setPatient_id(1);
        statusLocalis.setDate(LocalDateTime.now());
        statusLocalis.setHeart_rate(45.0F);
        statusLocalis.setRespiratory_rate(50);
        statusLocalis.setSpO2(89);
        statusLocalis.setTemperature(36.5F);
        statusLocalis.setStatus("Normal");


        HasApplication.messageHandle(statusLocalis);


        verify(patientstatusMapper).add(statusLocalis);
        verify(sqlSession).commit();
        verify(sqlSession).close();

        if ("Abnormal".equals(statusLocalis.getStatus())) {
            verify(kafkaProducer).send(any(ProducerRecord.class));
            verify(kafkaProducer).close();
        }
    }
}