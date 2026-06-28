package com.volunteer.service.impl;
import com.volunteer.dao.*; import com.volunteer.po.*;
import com.volunteer.service.AttendanceService; import com.volunteer.util.MyBatisUtil;
import org.apache.ibatis.session.SqlSession; import java.util.Date; import java.util.List;
public class AttendanceServiceImpl implements AttendanceService {
    @Override public Attendance findById(Integer attendId){SqlSession s=MyBatisUtil.getSession();try{return s.getMapper(AttendanceDao.class).findById(attendId);}finally{s.close();}}
    @Override public Attendance findByEnrollId(Integer enrollId){SqlSession s=MyBatisUtil.getSession();try{return s.getMapper(AttendanceDao.class).findByEnrollId(enrollId);}finally{s.close();}}
    @Override public List<Attendance> findByVolId(Integer volId){SqlSession s=MyBatisUtil.getSession();try{return s.getMapper(AttendanceDao.class).findByVolId(volId);}finally{s.close();}}
    @Override public List<Attendance> findByActId(Integer actId){SqlSession s=MyBatisUtil.getSession();try{return s.getMapper(AttendanceDao.class).findByActId(actId);}finally{s.close();}}
    @Override public List<Attendance> findAll(){SqlSession s=MyBatisUtil.getSession();try{return s.getMapper(AttendanceDao.class).findAll();}finally{s.close();}}
    @Override public boolean checkin(Integer enrollId,Integer volId,Integer actId,String checkinAddress){
        SqlSession s=MyBatisUtil.getSession();try{
            AttendanceDao dao=s.getMapper(AttendanceDao.class);
            if(dao.findByEnrollId(enrollId)!=null) return false;
            Attendance att=new Attendance();att.setEnrollId(enrollId);att.setVolId(volId);att.setActId(actId);att.setCheckinTime(new Date());att.setCheckinStatus(1);att.setCheckinAddress(checkinAddress);
            dao.insert(att);s.commit();return true;
        }catch(Exception e){s.rollback();return false;}finally{s.close();}
    }
    @Override public boolean checkout(Integer attendId,Double actualHours){
        SqlSession s=MyBatisUtil.getSession();try{s.getMapper(AttendanceDao.class).updateCheckout(attendId,new Date(),actualHours,2);s.commit();return true;}catch(Exception e){s.rollback();return false;}finally{s.close();}
    }
    @Override public boolean confirmAttendance(Integer attendId){
        SqlSession s=MyBatisUtil.getSession();try{
            AttendanceDao attDao=s.getMapper(AttendanceDao.class); Attendance att=attDao.findById(attendId); if(att==null) return false;
            attDao.updateConfirm(attendId,3);
            ServiceRecordDao srDao=s.getMapper(ServiceRecordDao.class);
            ServiceRecord sr=new ServiceRecord();sr.setVolId(att.getVolId());sr.setActId(att.getActId());sr.setAttendId(attendId);
            sr.setServiceDate(new java.sql.Date(System.currentTimeMillis()));sr.setServiceHours(att.getActualHours());sr.setServiceContent("参与志愿服务");sr.setServiceStatus(1);
            srDao.insert(sr);
            PointsDao pDao=s.getMapper(PointsDao.class); VolunteerDao vDao=s.getMapper(VolunteerDao.class);
            int cb=pDao.getBalanceByVolId(att.getVolId());
            Points p1=new Points();p1.setVolId(att.getVolId());p1.setActId(att.getActId());p1.setPointType(1);p1.setPointValue(10);p1.setPointDesc("志愿服务积分奖励");p1.setPointBalance(cb+10);pDao.insert(p1);
            Points p2=new Points();p2.setVolId(att.getVolId());p2.setActId(att.getActId());p2.setPointType(2);p2.setPointValue(5);p2.setPointDesc("签到奖励积分");p2.setPointBalance(cb+15);pDao.insert(p2);
            int tp=pDao.getBalanceByVolId(att.getVolId()); Double th=srDao.sumHoursByVolId(att.getVolId());
            vDao.updatePoints(att.getVolId(),tp); vDao.updateHours(att.getVolId(),th);
            s.commit();return true;
        }catch(Exception e){s.rollback();return false;}finally{s.close();}
    }
}
