package com.volunteer.dao;

import com.volunteer.po.Enrollment;
import org.apache.ibatis.annotations.Param;
import java.util.List;

public interface EnrollmentDao {
    Enrollment findById(@Param("enrollId") Integer enrollId);
    Enrollment findByVolAndAct(@Param("volId") Integer volId, @Param("actId") Integer actId);
    List<Enrollment> findByVolId(@Param("volId") Integer volId);
    List<Enrollment> findByActId(@Param("actId") Integer actId);
    List<Enrollment> findAll();
    List<Enrollment> findByStatus(@Param("enrollStatus") Integer enrollStatus);
    int insert(Enrollment enrollment);
    int update(Enrollment enrollment);
    int updateStatus(@Param("enrollId") Integer enrollId, @Param("enrollStatus") Integer enrollStatus, @Param("auditRemark") String auditRemark);
    int countByActId(@Param("actId") Integer actId);
    int countByVolId(@Param("volId") Integer volId);
}
