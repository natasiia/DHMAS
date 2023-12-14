package com.dhmas.has.mapper;

import com.dhmas.has.pojo.GeneralInfo;
import com.dhmas.has.pojo.StatusLocalis;

import java.util.List;

public interface GeneralInfoMapper {
    List<GeneralInfo> selectAll();

    GeneralInfo selectById(int id);

    void add(GeneralInfo generalInfo);

    void update(GeneralInfo generalInfo);

    void deleteById(int id);

}
