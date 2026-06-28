package com.volunteer.dao;
import com.volunteer.po.Auser;
import org.apache.ibatis.annotations.Param;
import java.util.List;
public interface AuserDao {
    Auser findByAccount(@Param("adminAccount") String adminAccount);
    Auser findById(@Param("adminId") Integer adminId);
    List<Auser> findAll();
    int insert(Auser auser);
    int update(Auser auser);
    int updateStatus(@Param("adminId") Integer adminId, @Param("adminStatus") Integer adminStatus);
}
