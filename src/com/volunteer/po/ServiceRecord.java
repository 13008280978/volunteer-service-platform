package com.volunteer.po;

import java.util.Date;

public class ServiceRecord {
    private Integer recordId;
    private Integer volId;
    private Integer actId;
    private Integer attendId;
    private Date serviceDate;
    private Double serviceHours;
    private String serviceContent;
    private String serviceEvaluation;
    private Integer serviceStatus;
    private Date createTime;

    // 关联字段
    private String volName;
    private String actTitle;

    public Integer getRecordId() { return recordId; }
    public void setRecordId(Integer recordId) { this.recordId = recordId; }
    public Integer getVolId() { return volId; }
    public void setVolId(Integer volId) { this.volId = volId; }
    public Integer getActId() { return actId; }
    public void setActId(Integer actId) { this.actId = actId; }
    public Integer getAttendId() { return attendId; }
    public void setAttendId(Integer attendId) { this.attendId = attendId; }
    public Date getServiceDate() { return serviceDate; }
    public void setServiceDate(Date serviceDate) { this.serviceDate = serviceDate; }
    public Double getServiceHours() { return serviceHours; }
    public void setServiceHours(Double serviceHours) { this.serviceHours = serviceHours; }
    public String getServiceContent() { return serviceContent; }
    public void setServiceContent(String serviceContent) { this.serviceContent = serviceContent; }
    public String getServiceEvaluation() { return serviceEvaluation; }
    public void setServiceEvaluation(String serviceEvaluation) { this.serviceEvaluation = serviceEvaluation; }
    public Integer getServiceStatus() { return serviceStatus; }
    public void setServiceStatus(Integer serviceStatus) { this.serviceStatus = serviceStatus; }
    public Date getCreateTime() { return createTime; }
    public void setCreateTime(Date createTime) { this.createTime = createTime; }
    public String getVolName() { return volName; }
    public void setVolName(String volName) { this.volName = volName; }
    public String getActTitle() { return actTitle; }
    public void setActTitle(String actTitle) { this.actTitle = actTitle; }
}
