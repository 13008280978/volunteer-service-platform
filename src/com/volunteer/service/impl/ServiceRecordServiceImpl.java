package com.volunteer.service.impl;

import com.volunteer.dao.ServiceRecordDao;
import com.volunteer.po.ServiceRecord;
import com.volunteer.service.ServiceRecordService;
import com.volunteer.util.MyBatisUtil;
import org.apache.ibatis.session.SqlSession;
import java.util.List;

public class ServiceRecordServiceImpl implements ServiceRecordService {
    @Override
    public ServiceRecord findById(Integer recordId) {
        SqlSession session = MyBatisUtil.getSession();
        try { return session.getMapper(ServiceRecordDao.class).findById(recordId); }
        finally { session.close(); }
    }
    @Override
    public List<ServiceRecord> findByVolId(Integer volId) {
        SqlSession session = MyBatisUtil.getSession();
        try { return session.getMapper(ServiceRecordDao.class).findByVolId(volId); }
        finally { session.close(); }
    }
    @Override
    public List<ServiceRecord> findAll() {
        SqlSession session = MyBatisUtil.getSession();
        try { return session.getMapper(ServiceRecordDao.class).findAll(); }
        finally { session.close(); }
    }
    @Override
    public boolean addRecord(ServiceRecord serviceRecord) {
        SqlSession session = MyBatisUtil.getSession();
        try {
            session.getMapper(ServiceRecordDao.class).insert(serviceRecord);
            session.commit(); return true;
        } catch (Exception e) { session.rollback(); return false; }
        finally { session.close(); }
    }
    @Override
    public int countByVolId(Integer volId) {
        SqlSession session = MyBatisUtil.getSession();
        try { return session.getMapper(ServiceRecordDao.class).countByVolId(volId); }
        finally { session.close(); }
    }
    @Override
    public Double sumHoursByVolId(Integer volId) {
        SqlSession session = MyBatisUtil.getSession();
        try { return session.getMapper(ServiceRecordDao.class).sumHoursByVolId(volId); }
        finally { session.close(); }
    }
}
