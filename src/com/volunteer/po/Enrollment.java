package com.volunteer.po;

import java.util.Date;

public class Enrollment {
    private Integer enrollId;
    private Integer volId;
    private Integer actId;
    private String enrollReason;
    private Integer enrollStatus;
    private Date enrollTime;
    private Date auditTime;
    private String auditRemark;

    // 关联字段
    private String volName;
    private String volPhone;
    private String actTitle;

    public Integer getEnrollId() { return enrollId; }
    public void setEnrollId(Integer enrollId) { this.enrollId = enrollId; }
    public Integer getVolId() { return volId; }
    public void setVolId(Integer volId) { this.volId = volId; }
    public Integer getActId() { return actId; }
    public void setActId(Integer actId) { this.actId = actId; }
    public String getEnrollReason() { return enrollReason; }
    public void setEnrollReason(String enrollReason) { this.enrollReason = enrollReason; }
    public Integer getEnrollStatus() { return enrollStatus; }
    public void setEnrollStatus(Integer enrollStatus) { this.enrollStatus = enrollStatus; }
    public Date getEnrollTime() { return enrollTime; }
    public void setEnrollTime(Date enrollTime) { this.enrollTime = enrollTime; }
    public Date getAuditTime() { return auditTime; }
    public void setAuditTime(Date auditTime) { this.auditTime = auditTime; }
    public String getAuditRemark() { return auditRemark; }
    public void setAuditRemark(String auditRemark) { this.auditRemark = auditRemark; }
    public String getVolName() { return volName; }
    public void setVolName(String volName) { this.volName = volName; }
    public String getVolPhone() { return volPhone; }
    public void setVolPhone(String volPhone) { this.volPhone = volPhone; }
    public String getActTitle() { return actTitle; }
    public void setActTitle(String actTitle) { this.actTitle = actTitle; }
}
