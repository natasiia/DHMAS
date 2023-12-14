package com.dhmas.has;

import com.dhmas.has.mapper.GeneralInfoMapper;
import com.dhmas.has.mapper.Patientstatus;
import com.dhmas.has.pojo.GeneralInfo;
import com.dhmas.has.pojo.StatusLocalis;
import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;

import java.io.IOException;
import java.io.InputStream;
import java.lang.reflect.MalformedParametersException;
import java.util.Date;
import java.util.List;

public class Main {
    public static void main(String[] args) throws IOException {
        String resource = "mybatis-config.xml";
        InputStream inputStream = Resources.getResourceAsStream(resource);
        SqlSessionFactory sqlSessionFactory = new SqlSessionFactoryBuilder().build(inputStream);
//        生成Session对象
        SqlSession sqlSession = sqlSessionFactory.openSession();
//        生成patientstatus表操作的映射对象
        Patientstatus patientstatusMapper = sqlSession.getMapper(Patientstatus.class);
//        生成generalinfo表操作的映射对象
        GeneralInfoMapper generalinfomapper = sqlSession.getMapper(GeneralInfoMapper.class);

//        查找patientstatus表所有记录
        List<StatusLocalis> statusLocaliss=patientstatusMapper.selectAll();
        for (StatusLocalis s:statusLocaliss) {
            System.out.println("Patient_id:"+s.getPatient_id()+"  status:"+s.getStatus());
        }

//          按id查找patientstatus表中记录
//        StatusLocalis statusLocalis = patientstatusMapper.selectBypatient_id(3);
//        System.out.println(statusLocalis);

//           按给定的status的值查找patientstatus表中记录
//        List<StatusLocalis> abnormal = patientstatusMapper.selectBystatus("Abnormal");
//        for (StatusLocalis s:abnormal) {
//            System.out.println("Patient_id:"+s.getPatient_id()+"  status:"+s.getStatus());
//        }


//        向patientstatus表中添加一条记录，参数为StatusLocalis对象，
//        patient_id不起作用，patient_id自动生成，数据添加到表尾
//            StatusLocalis statusLocalis = new StatusLocalis();
//            statusLocalis.setPatient_id(6);
//            statusLocalis.setHeart_rate(70);
//            statusLocalis.setDate(new Date());
//            statusLocalis.setSpO2(90);
//            statusLocalis.setTemperature(36);
//            statusLocalis.setRespiratory_rate(65);
//            statusLocalis.setStatus("Abnormal");
//            patientstatusMapper.add(statusLocalis);


//        修改patientstatus表一条记录，参数为StatusLocalis对象，修改的记录是StatusLocalis对象中
//          patient_id值对应的记录，如下：修改了表中Patient_id值为15的记录
//            StatusLocalis statusLocalis = new StatusLocalis();
//            statusLocalis.setPatient_id(15);
//            statusLocalis.setHeart_rate(55);
//            statusLocalis.setDate(new Date());
//            statusLocalis.setSpO2(99);
//            statusLocalis.setTemperature(36.5f);
//            statusLocalis.setRespiratory_rate(65);
//            statusLocalis.setStatus("Normal");
//            patientstatusMapper.updateAll(statusLocalis);

//        只修改patientstatus表记录的status值，参数为StatusLocalis对象
//            StatusLocalis statusLocalis = new StatusLocalis();
//            statusLocalis.setPatient_id(15);
//            statusLocalis.setStatus("Abnormal");
//            patientstatusMapper.updateBystatus(statusLocalis);

//        删除patientstatus表一条记录
//            patientstatusMapper.deleteBypatient_id(18);

//  查找 patientstatus表中heart rate >120 or <40, respiratory rate <35,
//  SpO2 <90,temperature <36°C or >37.5°C.的所有记录
//        List<StatusLocalis> statusLocalis1 = patientstatusMapper.selectByAbnormal();
//        将其status值设为Abnormal
//        for (StatusLocalis s:statusLocalis1) {
//            System.out.println(s.getPatient_id());
//            s.setStatus("Abnormal");
//            patientstatusMapper.updateBystatus(s);
//        }


//        查找generalinfo表所有记录
//        List<GeneralInfo> generalInfos = generalinfomapper.selectAll();
//        for (GeneralInfo g:generalInfos) {
//            System.out.println(g);
//        }

//        按id查找generalinfo表中记录
//        GeneralInfo generalInfo = generalinfomapper.selectById(10);
//        System.out.println(generalInfo);

//        向generalInfo表中添加一条记录，参数为GeneralInfo对象，
//        GeneralInfo generalInfo = new GeneralInfo();
//        generalInfo.setPatient_id(10);
//        generalInfo.setAge(21);
//        generalInfo.setGender("Male");
//        generalInfo.setDiagnosis("qq");
//        generalInfo.setInsurance_type("rrr");
//        generalInfo.setMedication("fffff");
//        generalInfo.setTreatment_duration(23);
//        generalinfomapper.add(generalInfo);

//        修改generalInfo表一条记录，参数为generalInfo对象，修改的记录是generalInfo对象中
//                patient_id值对应的记录
//        GeneralInfo generalInfo = new GeneralInfo();
//        generalInfo.setPatient_id(7);
//        generalInfo.setAge(99);
//        generalInfo.setGender("99");
//        generalInfo.setDiagnosis("99");
//        generalInfo.setInsurance_type("99");
//        generalInfo.setMedication("999");
//        generalInfo.setTreatment_duration(99);
//        generalinfomapper.update(generalInfo);

//        删除generalInfo表一条记录
//            generalinfomapper.deleteById(10);


        sqlSession.commit();
        sqlSession.close();


    }
}
