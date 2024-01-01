package com.dhmas.HAS.mapper;

import com.dhmas.HAS.pojo.GeneralInfo;

import java.util.List;

public interface GeneralInfoMapper {
    List<GeneralInfo> selectAll();

    GeneralInfo selectById(String id);

    void add(GeneralInfo generalInfo);

    void update(GeneralInfo generalInfo);

    void deleteById(String id);

}
