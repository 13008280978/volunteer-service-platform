package com.volunteer.service.impl;

import com.volunteer.dao.ActivityTypeDao;
import com.volunteer.po.ActivityType;
import com.volunteer.service.ActivityTypeService;
import com.volunteer.util.MyBatisUtil;
import org.apache.ibatis.session.SqlSession;
import java.util.List;

public class ActivityTypeServiceImpl implements ActivityTypeService {
    @Override
    public List<ActivityType> findAll() {
        SqlSession session = MyBatisUtil.getSession();
        try { return session.getMapper(ActivityTypeDao.class).findAll(); }
        finally { session.close(); }
    }
    @Override
    public List<ActivityType> findActive() {
        SqlSession session = MyBatisUtil.getSession();
        try { return session.getMapper(ActivityTypeDao.class).findActive(); }
        finally { session.close(); }
    }
    @Override
    public ActivityType findById(Integer typeId) {
        SqlSession session = MyBatisUtil.getSession();
        try { return session.getMapper(ActivityTypeDao.class).findById(typeId); }
        finally { session.close(); }
    }
    @Override
    public boolean addType(ActivityType activityType) {
        SqlSession session = MyBatisUtil.getSession();
        try {
            session.getMapper(ActivityTypeDao.class).insert(activityType);
            session.commit(); return true;
        } catch (Exception e) { session.rollback(); return false; }
        finally { session.close(); }
    }
    @Override
    public boolean updateType(ActivityType activityType) {
        SqlSession session = MyBatisUtil.getSession();
        try {
            session.getMapper(ActivityTypeDao.class).update(activityType);
            session.commit(); return true;
        } catch (Exception e) { session.rollback(); return false; }
        finally { session.close(); }
    }
    @Override
    public boolean updateStatus(Integer typeId, Integer typeStatus) {
        SqlSession session = MyBatisUtil.getSession();
        try {
            session.getMapper(ActivityTypeDao.class).updateStatus(typeId, typeStatus);
            session.commit(); return true;
        } catch (Exception e) { session.rollback(); return false; }
        finally { session.close(); }
    }
}
