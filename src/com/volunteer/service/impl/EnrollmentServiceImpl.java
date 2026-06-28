package com.volunteer.service.impl;

import com.volunteer.dao.EnrollmentDao;
import com.volunteer.dao.ActivityDao;
import com.volunteer.po.Enrollment;
import com.volunteer.service.EnrollmentService;
import com.volunteer.util.MyBatisUtil;
import org.apache.ibatis.session.SqlSession;
import java.util.List;

public class EnrollmentServiceImpl implements EnrollmentService {
    @Override
    public Enrollment findById(Integer enrollId) {
        SqlSession session = MyBatisUtil.getSession();
        try { return session.getMapper(EnrollmentDao.class).findById(enrollId); }
        finally { session.close(); }
    }
    @Override
    public Enrollment findByVolAndAct(Integer volId, Integer actId) {
        SqlSession session = MyBatisUtil.getSession();
        try { return session.getMapper(EnrollmentDao.class).findByVolAndAct(volId, actId); }
        finally { session.close(); }
    }
    @Override
    public List<Enrollment> findByVolId(Integer volId) {
        SqlSession session = MyBatisUtil.getSession();
        try { return session.getMapper(EnrollmentDao.class).findByVolId(volId); }
        finally { session.close(); }
    }
    @Override
    public List<Enrollment> findByActId(Integer actId) {
        SqlSession session = MyBatisUtil.getSession();
        try { return session.getMapper(EnrollmentDao.class).findByActId(actId); }
        finally { session.close(); }
    }
    @Override
    public List<Enrollment> findAll() {
        SqlSession session = MyBatisUtil.getSession();
        try { return session.getMapper(EnrollmentDao.class).findAll(); }
        finally { session.close(); }
    }
    @Override
    public boolean enroll(Integer volId, Integer actId, String enrollReason) {
        SqlSession session = MyBatisUtil.getSession();
        try {
            EnrollmentDao dao = session.getMapper(EnrollmentDao.class);
            Enrollment existing = dao.findByVolAndAct(volId, actId);
            if (existing != null) return false;
            Enrollment enroll = new Enrollment();
            enroll.setVolId(volId);
            enroll.setActId(actId);
            enroll.setEnrollReason(enrollReason);
            enroll.setEnrollStatus(0);
            dao.insert(enroll);
            // Update current people count
            ActivityDao actDao = session.getMapper(ActivityDao.class);
            int count = dao.countByActId(actId);
            actDao.updateCurrentPeople(actId, count);
            session.commit(); return true;
        } catch (Exception e) { session.rollback(); return false; }
        finally { session.close(); }
    }
    @Override
    public boolean auditEnroll(Integer enrollId, Integer enrollStatus, String auditRemark) {
        SqlSession session = MyBatisUtil.getSession();
        try {
            session.getMapper(EnrollmentDao.class).updateStatus(enrollId, enrollStatus, auditRemark);
            session.commit(); return true;
        } catch (Exception e) { session.rollback(); return false; }
        finally { session.close(); }
    }
    @Override
    public boolean cancelEnroll(Integer enrollId) {
        return auditEnroll(enrollId, 3, "志愿者主动取消");
    }
}
