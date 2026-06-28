package com.volunteer.service;

import com.volunteer.po.Auser;
import java.util.List;

public interface AuserService {
    Auser login(String adminAccount, String adminPassword);
    Auser findById(Integer adminId);
    List<Auser> findAll();
    boolean addAdmin(Auser auser);
    boolean updateAdmin(Auser auser);
}
