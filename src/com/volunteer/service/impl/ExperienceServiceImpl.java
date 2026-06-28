package com.volunteer.service.impl;

import com.volunteer.dao.ExperienceDao;
import com.volunteer.po.Experience;
import com.volunteer.service.ExperienceService;
import com.volunteer.util.MyBatisUtil;
import org.apache.ibatis.session.SqlSession;
import java.util.List;

public class ExperienceServiceImpl implements ExperienceService {
    @Override
    public Experience findById(Integer expId) {
        SqlSession session = MyBatisUtil.getSession();
        try { return session.getMapper(ExperienceDao.class).findById(expId); }
        finally { session.close(); }
    }
    @Override
    public List<Experience> findByVolId(Integer volId) {
        SqlSession session = MyBatisUtil.getSession();
        try { return session.getMapper(ExperienceDao.class).findByVolId(volId); }
        finally { session.close(); }
    }
    @Override
    public List<Experience> findAll() {
        SqlSession session = MyBatisUtil.getSession();
        try { return session.getMapper(ExperienceDao.class).findAll(); }
        finally { session.close(); }
    }
    @Override
    public List<Experience> findByStatus(Integer expStatus) {
        SqlSession session = MyBatisUtil.getSession();
        try { return session.getMapper(ExperienceDao.class).findByStatus(expStatus); }
        finally { session.close(); }
    }
    @Override
    public List<Experience> findApproved(Integer page, Integer pageSize) {
        SqlSession session = MyBatisUtil.getSession();
        try { return session.getMapper(ExperienceDao.class).findApproved((page-1)*pageSize, pageSize); }
        finally { session.close(); }
    }
    @Override
    public boolean addExperience(Experience experience) {
        SqlSession session = MyBatisUtil.getSession();
        try {
            experience.setExpStatus(0);
            session.getMapper(ExperienceDao.class).insert(experience);
            session.commit(); return true;
        } catch (Exception e) { session.rollback(); return false; }
        finally { session.close(); }
    }
    @Override
    public boolean auditExperience(Integer expId, Integer expStatus, String expRemark) {
        SqlSession session = MyBatisUtil.getSession();
        try {
            session.getMapper(ExperienceDao.class).updateStatus(expId, expStatus, expRemark);
            session.commit(); return true;
        } catch (Exception e) { session.rollback(); return false; }
        finally { session.close(); }
    }
    @Override
    public void incrementViews(Integer expId) {
        SqlSession session = MyBatisUtil.getSession();
        try {
            session.getMapper(ExperienceDao.class).incrementViews(expId);
            session.commit();
        } finally { session.close(); }
    }
    @Override
    public int countAll() {
        SqlSession session = MyBatisUtil.getSession();
        try { return session.getMapper(ExperienceDao.class).countAll(); }
        finally { session.close(); }
    }
    @Override
    public int countByStatus(Integer expStatus) {
        SqlSession session = MyBatisUtil.getSession();
        try { return session.getMapper(ExperienceDao.class).countByStatus(expStatus); }
        finally { session.close(); }
    }
}
