package com.volunteer.service.impl;

import com.volunteer.dao.PointsDao;
import com.volunteer.dao.VolunteerDao;
import com.volunteer.po.Points;
import com.volunteer.service.PointsService;
import com.volunteer.util.MyBatisUtil;
import org.apache.ibatis.session.SqlSession;
import java.util.List;

public class PointsServiceImpl implements PointsService {
    @Override
    public List<Points> findByVolId(Integer volId) {
        SqlSession session = MyBatisUtil.getSession();
        try { return session.getMapper(PointsDao.class).findByVolId(volId); }
        finally { session.close(); }
    }
    @Override
    public List<Points> findAll() {
        SqlSession session = MyBatisUtil.getSession();
        try { return session.getMapper(PointsDao.class).findAll(); }
        finally { session.close(); }
    }
    @Override
    public boolean addPoints(Points points) {
        SqlSession session = MyBatisUtil.getSession();
        try {
            session.getMapper(PointsDao.class).insert(points);
            session.commit(); return true;
        } catch (Exception e) { session.rollback(); return false; }
        finally { session.close(); }
    }
    @Override
    public boolean adjustPoints(Integer volId, Integer actId, Integer pointType, Integer pointValue, String pointDesc) {
        SqlSession session = MyBatisUtil.getSession();
        try {
            PointsDao dao = session.getMapper(PointsDao.class);
            int currentBalance = dao.getBalanceByVolId(volId);
            Points p = new Points();
            p.setVolId(volId);
            p.setActId(actId);
            p.setPointType(pointType);
            p.setPointValue(pointValue);
            p.setPointDesc(pointDesc);
            p.setPointBalance(currentBalance + pointValue);
            dao.insert(p);
            // Update volunteer total points
            int newTotal = dao.getBalanceByVolId(volId);
            session.getMapper(VolunteerDao.class).updatePoints(volId, newTotal);
            session.commit(); return true;
        } catch (Exception e) { session.rollback(); return false; }
        finally { session.close(); }
    }
    @Override
    public Integer getBalance(Integer volId) {
        SqlSession session = MyBatisUtil.getSession();
        try { return session.getMapper(PointsDao.class).getBalanceByVolId(volId); }
        finally { session.close(); }
    }
}
