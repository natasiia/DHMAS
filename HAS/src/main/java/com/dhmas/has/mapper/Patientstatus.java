package com.dhmas.has.mapper;

import com.dhmas.has.pojo.StatusLocalis;

import java.util.List;

public interface Patientstatus {
    List<StatusLocalis> selectAll();

    StatusLocalis selectBypatient_id(int id);

    List<StatusLocalis> selectBystatus(String s);

    void add(StatusLocalis statusLocalis);

    void updateAll(StatusLocalis statusLocalis);

    void updateBystatus(StatusLocalis statusLocalis);

    void deleteBypatient_id(int id);

    List<StatusLocalis> selectByAbnormal();

}
