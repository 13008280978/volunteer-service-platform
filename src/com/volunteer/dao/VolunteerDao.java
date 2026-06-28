package com.volunteer.dao;

import com.volunteer.po.Volunteer;
import org.apache.ibatis.annotations.Param;
import java.util.List;

public interface VolunteerDao {
    Volunteer findByAccount(@Param("volAccount") String volAccount);
    Volunteer findById(@Param("volId") Integer volId);
    List<Volunteer> findAll();
    List<Volunteer> findByStatus(@Param("volStatus") Integer volStatus);
    List<Volunteer> searchByName(@Param("volName") String volName);
    List<Volunteer> findPointsRanking();
    int insert(Volunteer volunteer);
    int update(Volunteer volunteer);
    int updateStatus(@Param("volId") Integer volId, @Param("volStatus") Integer volStatus, @Param("volRemark") String volRemark);
    int updateHours(@Param("volId") Integer volId, @Param("volTotalHours") Double volTotalHours);
    int updatePoints(@Param("volId") Integer volId, @Param("volTotalPoints") Integer volTotalPoints);
    int countAll();
    int countByStatus(@Param("volStatus") Integer volStatus);
}
