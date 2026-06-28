package com.volunteer.dao;

import com.volunteer.po.ServiceRecord;
import org.apache.ibatis.annotations.Param;
import java.util.List;

public interface ServiceRecordDao {
    ServiceRecord findById(@Param("recordId") Integer recordId);
    List<ServiceRecord> findByVolId(@Param("volId") Integer volId);
    List<ServiceRecord> findByActId(@Param("actId") Integer actId);
    List<ServiceRecord> findAll();
    int insert(ServiceRecord serviceRecord);
    int update(ServiceRecord serviceRecord);
    int countByVolId(@Param("volId") Integer volId);
    Double sumHoursByVolId(@Param("volId") Integer volId);
}
