package com.volunteer.service;

import com.volunteer.po.Activity;
import java.util.List;

public interface ActivityService {
    Activity findById(Integer actId);
    List<Activity> findAll();
    List<Activity> findByStatus(Integer actStatus);
    List<Activity> search(Integer typeId, String keyword, String location);
    List<Activity> findLatest(Integer limit);
    boolean addActivity(Activity activity);
    boolean updateActivity(Activity activity);
    boolean updateStatus(Integer actId, Integer actStatus);
    int countAll();
    Double sumTotalHours();
}
