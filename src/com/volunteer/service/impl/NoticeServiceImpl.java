package com.volunteer.service.impl;

import com.volunteer.dao.NoticeDao;
import com.volunteer.po.Notice;
import com.volunteer.service.NoticeService;
import com.volunteer.util.MyBatisUtil;
import org.apache.ibatis.session.SqlSession;
import java.util.List;

public class NoticeServiceImpl implements NoticeService {
    @Override
    public Notice findById(Integer noticeId) {
        SqlSession session = MyBatisUtil.getSession();
        try { return session.getMapper(NoticeDao.class).findById(noticeId); }
        finally { session.close(); }
    }
    @Override
    public List<Notice> findAll() {
        SqlSession session = MyBatisUtil.getSession();
        try { return session.getMapper(NoticeDao.class).findAll(); }
        finally { session.close(); }
    }
    @Override
    public List<Notice> findPublished(Integer page, Integer pageSize) {
        SqlSession session = MyBatisUtil.getSession();
        try { return session.getMapper(NoticeDao.class).findByPage((page-1)*pageSize, pageSize); }
        finally { session.close(); }
    }
    @Override
    public boolean addNotice(Notice notice) {
        SqlSession session = MyBatisUtil.getSession();
        try {
            session.getMapper(NoticeDao.class).insert(notice);
            session.commit(); return true;
        } catch (Exception e) { session.rollback(); return false; }
        finally { session.close(); }
    }
    @Override
    public boolean updateNotice(Notice notice) {
        SqlSession session = MyBatisUtil.getSession();
        try {
            session.getMapper(NoticeDao.class).update(notice);
            session.commit(); return true;
        } catch (Exception e) { session.rollback(); return false; }
        finally { session.close(); }
    }
    @Override
    public boolean updateStatus(Integer noticeId, Integer noticeStatus) {
        SqlSession session = MyBatisUtil.getSession();
        try {
            session.getMapper(NoticeDao.class).updateStatus(noticeId, noticeStatus);
            session.commit(); return true;
        } catch (Exception e) { session.rollback(); return false; }
        finally { session.close(); }
    }
    @Override
    public int countAll() {
        SqlSession session = MyBatisUtil.getSession();
        try { return session.getMapper(NoticeDao.class).countAll(); }
        finally { session.close(); }
    }
}
