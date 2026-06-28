package com.volunteer.service;

import com.volunteer.po.Experience;
import java.util.List;

public interface ExperienceService {
    Experience findById(Integer expId);
    List<Experience> findByVolId(Integer volId);
    List<Experience> findAll();
    List<Experience> findByStatus(Integer expStatus);
    List<Experience> findApproved(Integer page, Integer pageSize);
    boolean addExperience(Experience experience);
    boolean auditExperience(Integer expId, Integer expStatus, String expRemark);
    void incrementViews(Integer expId);
    int countAll();
    int countByStatus(Integer expStatus);
}
