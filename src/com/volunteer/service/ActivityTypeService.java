package com.volunteer.service;

import com.volunteer.po.ActivityType;
import java.util.List;

public interface ActivityTypeService {
    List<ActivityType> findAll();
    List<ActivityType> findActive();
    ActivityType findById(Integer typeId);
    boolean addType(ActivityType activityType);
    boolean updateType(ActivityType activityType);
    boolean updateStatus(Integer typeId, Integer typeStatus);
}
