package com.volunteer.po;

import java.util.Date;

public class Auser {
    private Integer adminId;
    private String adminName;
    private String adminAccount;
    private String adminPassword;
    private String adminPhone;
    private String adminEmail;
    private Integer adminStatus;
    private Date createTime;
    private Date updateTime;

    public Integer getAdminId() { return adminId; }
    public void setAdminId(Integer adminId) { this.adminId = adminId; }
    public String getAdminName() { return adminName; }
    public void setAdminName(String adminName) { this.adminName = adminName; }
    public String getAdminAccount() { return adminAccount; }
    public void setAdminAccount(String adminAccount) { this.adminAccount = adminAccount; }
    public String getAdminPassword() { return adminPassword; }
    public void setAdminPassword(String adminPassword) { this.adminPassword = adminPassword; }
    public String getAdminPhone() { return adminPhone; }
    public void setAdminPhone(String adminPhone) { this.adminPhone = adminPhone; }
    public String getAdminEmail() { return adminEmail; }
    public void setAdminEmail(String adminEmail) { this.adminEmail = adminEmail; }
    public Integer getAdminStatus() { return adminStatus; }
    public void setAdminStatus(Integer adminStatus) { this.adminStatus = adminStatus; }
    public Date getCreateTime() { return createTime; }
    public void setCreateTime(Date createTime) { this.createTime = createTime; }
    public Date getUpdateTime() { return updateTime; }
    public void setUpdateTime(Date updateTime) { this.updateTime = updateTime; }
}
