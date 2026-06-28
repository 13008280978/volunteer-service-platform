package com.volunteer.service.impl;

import com.volunteer.dao.ActivityDao;
import com.volunteer.po.Activity;
import com.volunteer.service.ActivityService;
import com.volunteer.util.MyBatisUtil;
import org.apache.ibatis.session.SqlSession;
import java.util.List;

public class ActivityServiceImpl implements ActivityService {
    @Override
    public Activity findById(Integer actId) {
        SqlSession session = MyBatisUtil.getSession();
        try { return session.getMapper(ActivityDao.class).findById(actId); }
        finally { session.close(); }
    }
    @Override
    public List<Activity> findAll() {
        SqlSession session = MyBatisUtil.getSession();
        try { return session.getMapper(ActivityDao.class).findAll(); }
        finally { session.close(); }
    }
    @Override
    public List<Activity> findByStatus(Integer actStatus) {
        SqlSession session = MyBatisUtil.getSession();
        try { return session.getMapper(ActivityDao.class).findByStatus(actStatus); }
        finally { session.close(); }
    }
    @Override
    public List<Activity> search(Integer typeId, String keyword, String location) {
        SqlSession session = MyBatisUtil.getSession();
        try { return session.getMapper(ActivityDao.class).search(typeId, keyword, location); }
        finally { session.close(); }
    }
    @Override
    public List<Activity> findLatest(Integer limit) {
        SqlSession session = MyBatisUtil.getSession();
        try { return session.getMapper(ActivityDao.class).findLatest(limit); }
        finally { session.close(); }
    }
    @Override
    public boolean addActivity(Activity activity) {
        SqlSession session = MyBatisUtil.getSession();
        try {
            session.getMapper(ActivityDao.class).insert(activity);
            session.commit(); return true;
        } catch (Exception e) { session.rollback(); return false; }
        finally { session.close(); }
    }
    @Override
    public boolean updateActivity(Activity activity) {
        SqlSession session = MyBatisUtil.getSession();
        try {
            session.getMapper(ActivityDao.class).update(activity);
            session.commit(); return true;
        } catch (Exception e) { session.rollback(); return false; }
        finally { session.close(); }
    }
    @Override
    public boolean updateStatus(Integer actId, Integer actStatus) {
        SqlSession session = MyBatisUtil.getSession();
        try {
            session.getMapper(ActivityDao.class).updateStatus(actId, actStatus);
            session.commit(); return true;
        } catch (Exception e) { session.rollback(); return false; }
        finally { session.close(); }
    }
    @Override
    public int countAll() {
        SqlSession session = MyBatisUtil.getSession();
        try { return session.getMapper(ActivityDao.class).countAll(); }
        finally { session.close(); }
    }
    @Override
    public Double sumTotalHours() {
        SqlSession session = MyBatisUtil.getSession();
        try { return session.getMapper(ActivityDao.class).sumTotalHours(); }
        finally { session.close(); }
    }
}
