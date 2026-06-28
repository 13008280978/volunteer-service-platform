package com.volunteer.dao;

import com.volunteer.po.Points;
import org.apache.ibatis.annotations.Param;
import java.util.List;

public interface PointsDao {
    Points findById(@Param("pointId") Integer pointId);
    List<Points> findByVolId(@Param("volId") Integer volId);
    List<Points> findAll();
    int insert(Points points);
    int update(Points points);
    Integer getBalanceByVolId(@Param("volId") Integer volId);
}
