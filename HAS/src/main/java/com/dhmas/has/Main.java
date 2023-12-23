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
//        Create Sql Session object
        SqlSession sqlSession = sqlSessionFactory.openSession();
//        Mapping object for generating patientstatus table operations
        Patientstatus patientstatusMapper = sqlSession.getMapper(Patientstatus.class);
//        Mapping object for generating generalinfo table operations
        GeneralInfoMapper generalinfomapper = sqlSession.getMapper(GeneralInfoMapper.class);

//        Retrieve all records from the patientstatus table
//        List<StatusLocalis> statusLocaliss=patientstatusMapper.selectAll();
//        for (StatusLocalis s:statusLocaliss) {
//            System.out.println("Patient_id:"+s.getPatient_id()+"  status:"+s.getStatus());
//        }

//          Retrieve records from the patientstatus table by ID.
//        StatusLocalis statusLocalis = patientstatusMapper.selectBypatient_id(3);
//        System.out.println(statusLocalis);

//           Retrieve records from the patientstatus table by status
//        List<StatusLocalis> abnormal = patientstatusMapper.selectBystatus("Abnormal");
//        for (StatusLocalis s:abnormal) {
//            System.out.println("Patient_id:"+s.getPatient_id()+"  status:"+s.getStatus());
//        }


//        Add a record to the patientstatus table with a StatusLocalis object as a parameter.
//        The patient_id is not used and is auto-generated, and the data is added to the end of the table.
//            StatusLocalis statusLocalis = new StatusLocalis();
//            statusLocalis.setPatient_id(2);
//            statusLocalis.setHeart_rate(90);
//            statusLocalis.setDate(new Date());
//            statusLocalis.setSpO2(70);
//            statusLocalis.setTemperature(26);
//            statusLocalis.setRespiratory_rate(55);
//            statusLocalis.setStatus("Abnormal");
//            patientstatusMapper.add(statusLocalis);


//         Modify a record in the patientstatus table with a StatusLocalis object as a parameter.
//         The record to be modified is in the StatusLocalis object
//            StatusLocalis statusLocalis = new StatusLocalis();
//            statusLocalis.setPatient_id(15);
//            statusLocalis.setHeart_rate(55);
//            statusLocalis.setDate(new Date());
//            statusLocalis.setSpO2(99);
//            statusLocalis.setTemperature(36.5f);
//            statusLocalis.setRespiratory_rate(65);
//            statusLocalis.setStatus("Normal");
//            patientstatusMapper.updateAll(statusLocalis);

//        Only modify the 'status' value of a record in the patientstatus table
//        with a StatusLocalis object as a parameter
//            StatusLocalis statusLocalis = new StatusLocalis();
//            statusLocalis.setPatient_id(15);
//            statusLocalis.setStatus("Abnormal");
//            patientstatusMapper.updateBystatus(statusLocalis);

//        Delete one record from the patientstatus table
//            patientstatusMapper.deleteBypatient_id(18);

//        Retrieve all records from the patientstatus table where data turns to be abnormal
//        List<StatusLocalis> statusLocalis1 = patientstatusMapper.selectByAbnormal();
//        Turn its status to Abnormal
//        for (StatusLocalis s:statusLocalis1) {
//            System.out.println(s.getPatient_id());
//            s.setStatus("Abnormal");
//            patientstatusMapper.updateBystatus(s);
//        }


//        Retrieve all records from the generalinfo table
//        List<GeneralInfo> generalInfos = generalinfomapper.selectAll();
//        for (GeneralInfo g:generalInfos) {
//            System.out.println(g);
//        }

//        etrieve all records from the generalinfo table by Id
//        GeneralInfo generalInfo = generalinfomapper.selectById(10);
//        System.out.println(generalInfo);

//        Add a record to the generalInfo table with a GeneralInfo object as a parameter
        GeneralInfo generalInfo = new GeneralInfo();
        generalInfo.setPatient_id(2);
        generalInfo.setAge(31);
        generalInfo.setGender("Male");
        generalInfo.setDiagnosis("qq");
        generalInfo.setInsurance_type("rrr");
        generalInfo.setMedication("fffff");
        generalInfo.setTreatment_duration(23);
        generalinfomapper.add(generalInfo);

//        Modify one record in the generalInfo table with a generalInfo object as a parameter.
//        The record to be modified corresponds to the patient_id value in the generalInfo object.
//        GeneralInfo generalInfo = new GeneralInfo();
//        generalInfo.setPatient_id(7);
//        generalInfo.setAge(99);
//        generalInfo.setGender("99");
//        generalInfo.setDiagnosis("99");
//        generalInfo.setInsurance_type("99");
//        generalInfo.setMedication("999");
//        generalInfo.setTreatment_duration(99);
//        generalinfomapper.update(generalInfo);

//        Delete one record from the generalInfo table.
//            generalinfomapper.deleteById(10);


        sqlSession.commit();
        sqlSession.close();


    }
}
