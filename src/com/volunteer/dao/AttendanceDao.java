package com.volunteer.dao;

import com.volunteer.po.Attendance;
import org.apache.ibatis.annotations.Param;
import java.util.List;

public interface AttendanceDao {
    Attendance findById(@Param("attendId") Integer attendId);
    Attendance findByEnrollId(@Param("enrollId") Integer enrollId);
    List<Attendance> findByVolId(@Param("volId") Integer volId);
    List<Attendance> findByActId(@Param("actId") Integer actId);
    List<Attendance> findAll();
    List<Attendance> findByStatus(@Param("checkinStatus") Integer checkinStatus);
    int insert(Attendance attendance);
    int update(Attendance attendance);
    int updateCheckin(@Param("attendId") Integer attendId, @Param("checkinStatus") Integer checkinStatus);
    int updateCheckout(@Param("attendId") Integer attendId, @Param("checkoutTime") java.util.Date checkoutTime, @Param("actualHours") Double actualHours, @Param("checkinStatus") Integer checkinStatus);
    int updateConfirm(@Param("attendId") Integer attendId, @Param("checkinStatus") Integer checkinStatus);
}
