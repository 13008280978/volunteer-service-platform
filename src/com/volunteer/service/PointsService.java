package com.volunteer.service;

import com.volunteer.po.Points;
import java.util.List;

public interface PointsService {
    List<Points> findByVolId(Integer volId);
    List<Points> findAll();
    boolean addPoints(Points points);
    boolean adjustPoints(Integer volId, Integer actId, Integer pointType, Integer pointValue, String pointDesc);
    Integer getBalance(Integer volId);
}
