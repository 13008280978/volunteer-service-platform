package com.volunteer.service;

import com.volunteer.po.Attendance;
import java.util.List;

public interface AttendanceService {
    Attendance findById(Integer attendId);
    Attendance findByEnrollId(Integer enrollId);
    List<Attendance> findByVolId(Integer volId);
    List<Attendance> findByActId(Integer actId);
    List<Attendance> findAll();
    boolean checkin(Integer enrollId, Integer volId, Integer actId, String checkinAddress);
    boolean checkout(Integer attendId, Double actualHours);
    boolean confirmAttendance(Integer attendId);
}
