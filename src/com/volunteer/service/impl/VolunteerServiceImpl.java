package com.volunteer.service.impl;

import com.volunteer.dao.VolunteerDao;
import com.volunteer.po.Volunteer;
import com.volunteer.service.VolunteerService;
import com.volunteer.util.MyBatisUtil;
import org.apache.ibatis.session.SqlSession;
import java.util.List;

public class VolunteerServiceImpl implements VolunteerService {
    @Override
    public Volunteer login(String volAccount, String volPassword) {
        SqlSession session = MyBatisUtil.getSession();
        try {
            VolunteerDao dao = session.getMapper(VolunteerDao.class);
            Volunteer vol = dao.findByAccount(volAccount);
            if (vol != null && vol.getVolPassword().equals(volPassword) && vol.getVolStatus() == 1) {
                return vol;
            }
        } finally { session.close(); }
        return null;
    }
    @Override
    public Volunteer findById(Integer volId) {
        SqlSession session = MyBatisUtil.getSession();
        try { return session.getMapper(VolunteerDao.class).findById(volId); }
        finally { session.close(); }
    }
    @Override
    public List<Volunteer> findAll() {
        SqlSession session = MyBatisUtil.getSession();
        try { return session.getMapper(VolunteerDao.class).findAll(); }
        finally { session.close(); }
    }
    @Override
    public List<Volunteer> findByStatus(Integer volStatus) {
        SqlSession session = MyBatisUtil.getSession();
        try { return session.getMapper(VolunteerDao.class).findByStatus(volStatus); }
        finally { session.close(); }
    }
    @Override
    public List<Volunteer> searchByName(String volName) {
        SqlSession session = MyBatisUtil.getSession();
        try { return session.getMapper(VolunteerDao.class).searchByName(volName); }
        finally { session.close(); }
    }
    @Override
    public List<Volunteer> getPointsRanking() {
        SqlSession session = MyBatisUtil.getSession();
        try { return session.getMapper(VolunteerDao.class).findPointsRanking(); }
        finally { session.close(); }
    }
    @Override
    public boolean register(Volunteer volunteer) {
        SqlSession session = MyBatisUtil.getSession();
        try {
            VolunteerDao dao = session.getMapper(VolunteerDao.class);
            Volunteer existing = dao.findByAccount(volunteer.getVolAccount());
            if (existing != null) return false;
            volunteer.setVolStatus(0);
            volunteer.setVolTotalHours(0.0);
            volunteer.setVolTotalPoints(0);
            dao.insert(volunteer);
            session.commit(); return true;
        } catch (Exception e) { session.rollback(); return false; }
        finally { session.close(); }
    }
    @Override
    public boolean updateVolunteer(Volunteer volunteer) {
        SqlSession session = MyBatisUtil.getSession();
        try {
            session.getMapper(VolunteerDao.class).update(volunteer);
            session.commit(); return true;
        } catch (Exception e) { session.rollback(); return false; }
        finally { session.close(); }
    }
    @Override
    public boolean auditVolunteer(Integer volId, Integer volStatus, String volRemark) {
        SqlSession session = MyBatisUtil.getSession();
        try {
            session.getMapper(VolunteerDao.class).updateStatus(volId, volStatus, volRemark);
            session.commit(); return true;
        } catch (Exception e) { session.rollback(); return false; }
        finally { session.close(); }
    }
    @Override
    public int countAll() {
        SqlSession session = MyBatisUtil.getSession();
        try { return session.getMapper(VolunteerDao.class).countAll(); }
        finally { session.close(); }
    }
    @Override
    public int countByStatus(Integer volStatus) {
        SqlSession session = MyBatisUtil.getSession();
        try { return session.getMapper(VolunteerDao.class).countByStatus(volStatus); }
        finally { session.close(); }
    }
}
