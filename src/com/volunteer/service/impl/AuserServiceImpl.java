package com.volunteer.service.impl;

import com.volunteer.dao.AuserDao;
import com.volunteer.po.Auser;
import com.volunteer.service.AuserService;
import com.volunteer.util.MyBatisUtil;
import org.apache.ibatis.session.SqlSession;
import java.util.List;

public class AuserServiceImpl implements AuserService {
    @Override
    public Auser login(String adminAccount, String adminPassword) {
        SqlSession session = MyBatisUtil.getSession();
        try {
            AuserDao dao = session.getMapper(AuserDao.class);
            Auser auser = dao.findByAccount(adminAccount);
            if (auser != null && auser.getAdminPassword().equals(adminPassword) && auser.getAdminStatus() == 1) {
                return auser;
            }
        } finally { session.close(); }
        return null;
    }
    @Override
    public Auser findById(Integer adminId) {
        SqlSession session = MyBatisUtil.getSession();
        try { return session.getMapper(AuserDao.class).findById(adminId); }
        finally { session.close(); }
    }
    @Override
    public List<Auser> findAll() {
        SqlSession session = MyBatisUtil.getSession();
        try { return session.getMapper(AuserDao.class).findAll(); }
        finally { session.close(); }
    }
    @Override
    public boolean addAdmin(Auser auser) {
        SqlSession session = MyBatisUtil.getSession();
        try {
            session.getMapper(AuserDao.class).insert(auser);
            session.commit(); return true;
        } catch (Exception e) { session.rollback(); return false; }
        finally { session.close(); }
    }
    @Override
    public boolean updateAdmin(Auser auser) {
        SqlSession session = MyBatisUtil.getSession();
        try {
            session.getMapper(AuserDao.class).update(auser);
            session.commit(); return true;
        } catch (Exception e) { session.rollback(); return false; }
        finally { session.close(); }
    }
}
