package com.volunteer.service;

import com.volunteer.po.Volunteer;
import java.util.List;

public interface VolunteerService {
    Volunteer login(String volAccount, String volPassword);
    Volunteer findById(Integer volId);
    List<Volunteer> findAll();
    List<Volunteer> findByStatus(Integer volStatus);
    List<Volunteer> searchByName(String volName);
    List<Volunteer> getPointsRanking();
    boolean register(Volunteer volunteer);
    boolean updateVolunteer(Volunteer volunteer);
    boolean auditVolunteer(Integer volId, Integer volStatus, String volRemark);
    int countAll();
    int countByStatus(Integer volStatus);
}
