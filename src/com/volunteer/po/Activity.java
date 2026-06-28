package com.volunteer.po;

import java.util.Date;

public class Activity {
    private Integer actId;
    private String actTitle;
    private String actDesc;
    private Integer actTypeId;
    private String actLocation;
    private Date actStartTime;
    private Date actEndTime;
    private Date actEnrollDeadline;
    private Integer actMaxPeople;
    private Integer actCurrentPeople;
    private Double actHours;
    private Integer actPoints;
    private String actPoster;
    private String actContact;
    private String actContactPhone;
    private Integer actStatus;
    private Integer adminId;
    private Date createTime;
    private Date updateTime;

    // 关联字段
    private String typeName;
    private String adminName;

    public Integer getActId() { return actId; }
    public void setActId(Integer actId) { this.actId = actId; }
    public String getActTitle() { return actTitle; }
    public void setActTitle(String actTitle) { this.actTitle = actTitle; }
    public String getActDesc() { return actDesc; }
    public void setActDesc(String actDesc) { this.actDesc = actDesc; }
    public Integer getActTypeId() { return actTypeId; }
    public void setActTypeId(Integer actTypeId) { this.actTypeId = actTypeId; }
    public String getActLocation() { return actLocation; }
    public void setActLocation(String actLocation) { this.actLocation = actLocation; }
    public Date getActStartTime() { return actStartTime; }
    public void setActStartTime(Date actStartTime) { this.actStartTime = actStartTime; }
    public Date getActEndTime() { return actEndTime; }
    public void setActEndTime(Date actEndTime) { this.actEndTime = actEndTime; }
    public Date getActEnrollDeadline() { return actEnrollDeadline; }
    public void setActEnrollDeadline(Date actEnrollDeadline) { this.actEnrollDeadline = actEnrollDeadline; }
    public Integer getActMaxPeople() { return actMaxPeople; }
    public void setActMaxPeople(Integer actMaxPeople) { this.actMaxPeople = actMaxPeople; }
    public Integer getActCurrentPeople() { return actCurrentPeople; }
    public void setActCurrentPeople(Integer actCurrentPeople) { this.actCurrentPeople = actCurrentPeople; }
    public Double getActHours() { return actHours; }
    public void setActHours(Double actHours) { this.actHours = actHours; }
    public Integer getActPoints() { return actPoints; }
    public void setActPoints(Integer actPoints) { this.actPoints = actPoints; }
    public String getActPoster() { return actPoster; }
    public void setActPoster(String actPoster) { this.actPoster = actPoster; }
    public String getActContact() { return actContact; }
    public void setActContact(String actContact) { this.actContact = actContact; }
    public String getActContactPhone() { return actContactPhone; }
    public void setActContactPhone(String actContactPhone) { this.actContactPhone = actContactPhone; }
    public Integer getActStatus() { return actStatus; }
    public void setActStatus(Integer actStatus) { this.actStatus = actStatus; }
    public Integer getAdminId() { return adminId; }
    public void setAdminId(Integer adminId) { this.adminId = adminId; }
    public Date getCreateTime() { return createTime; }
    public void setCreateTime(Date createTime) { this.createTime = createTime; }
    public Date getUpdateTime() { return updateTime; }
    public void setUpdateTime(Date updateTime) { this.updateTime = updateTime; }
    public String getTypeName() { return typeName; }
    public void setTypeName(String typeName) { this.typeName = typeName; }
    public String getAdminName() { return adminName; }
    public void setAdminName(String adminName) { this.adminName = adminName; }
}
