package com.volunteer.dao;

import com.volunteer.po.ActivityType;
import org.apache.ibatis.annotations.Param;
import java.util.List;

public interface ActivityTypeDao {
    List<ActivityType> findAll();
    List<ActivityType> findActive();
    ActivityType findById(@Param("typeId") Integer typeId);
    int insert(ActivityType activityType);
    int update(ActivityType activityType);
    int updateStatus(@Param("typeId") Integer typeId, @Param("typeStatus") Integer typeStatus);
}
