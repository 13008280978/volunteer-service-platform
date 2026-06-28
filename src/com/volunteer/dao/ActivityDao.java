package com.volunteer.dao;

import com.volunteer.po.Activity;
import org.apache.ibatis.annotations.Param;
import java.util.List;
import java.util.Map;

public interface ActivityDao {
    Activity findById(@Param("actId") Integer actId);
    List<Activity> findAll();
    List<Activity> findByStatus(@Param("actStatus") Integer actStatus);
    List<Activity> search(@Param("typeId") Integer typeId, @Param("keyword") String keyword, @Param("location") String location);
    List<Activity> findLatest(@Param("limit") Integer limit);
    List<Activity> findByPage(@Param("offset") Integer offset, @Param("limit") Integer limit);
    int insert(Activity activity);
    int update(Activity activity);
    int updateStatus(@Param("actId") Integer actId, @Param("actStatus") Integer actStatus);
    int updateCurrentPeople(@Param("actId") Integer actId, @Param("actCurrentPeople") Integer actCurrentPeople);
    int countAll();
    int countByStatus(@Param("actStatus") Integer actStatus);
    Double sumTotalHours();
}
