package com.dhmas.HAS.mapper;

import com.dhmas.HAS.pojo.StatusLocalis;

import java.util.List;

public interface Patientstatus {
    List<StatusLocalis> selectAll();

    StatusLocalis selectBypatient_id(String id);

    List<StatusLocalis> selectBystatus(String s);

    void add(StatusLocalis statusLocalis);

    void updateAll(StatusLocalis statusLocalis);

    void updateBystatus(StatusLocalis statusLocalis);

    void deleteBypatient_id(String id);

    List<StatusLocalis> selectByAbnormal();

}
