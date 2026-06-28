package com.volunteer.service;

import com.volunteer.po.ServiceRecord;
import java.util.List;

public interface ServiceRecordService {
    ServiceRecord findById(Integer recordId);
    List<ServiceRecord> findByVolId(Integer volId);
    List<ServiceRecord> findAll();
    boolean addRecord(ServiceRecord serviceRecord);
    int countByVolId(Integer volId);
    Double sumHoursByVolId(Integer volId);
}
