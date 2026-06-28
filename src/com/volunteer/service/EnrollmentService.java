package com.volunteer.service;

import com.volunteer.po.Enrollment;
import java.util.List;

public interface EnrollmentService {
    Enrollment findById(Integer enrollId);
    Enrollment findByVolAndAct(Integer volId, Integer actId);
    List<Enrollment> findByVolId(Integer volId);
    List<Enrollment> findByActId(Integer actId);
    List<Enrollment> findAll();
    boolean enroll(Integer volId, Integer actId, String enrollReason);
    boolean auditEnroll(Integer enrollId, Integer enrollStatus, String auditRemark);
    boolean cancelEnroll(Integer enrollId);
}
