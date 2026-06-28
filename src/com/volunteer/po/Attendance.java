package com.volunteer.po;

import java.util.Date;

public class Attendance {
    private Integer attendId;
    private Integer enrollId;
    private Integer volId;
    private Integer actId;
    private Date checkinTime;
    private Date checkoutTime;
    private Double actualHours;
    private Integer checkinStatus;
    private String checkinAddress;
    private String checkinRemark;
    private Date createTime;

    // 关联字段
    private String volName;
    private String actTitle;

    public Integer getAttendId() { return attendId; }
    public void setAttendId(Integer attendId) { this.attendId = attendId; }
    public Integer getEnrollId() { return enrollId; }
    public void setEnrollId(Integer enrollId) { this.enrollId = enrollId; }
    public Integer getVolId() { return volId; }
    public void setVolId(Integer volId) { this.volId = volId; }
    public Integer getActId() { return actId; }
    public void setActId(Integer actId) { this.actId = actId; }
    public Date getCheckinTime() { return checkinTime; }
    public void setCheckinTime(Date checkinTime) { this.checkinTime = checkinTime; }
    public Date getCheckoutTime() { return checkoutTime; }
    public void setCheckoutTime(Date checkoutTime) { this.checkoutTime = checkoutTime; }
    public Double getActualHours() { return actualHours; }
    public void setActualHours(Double actualHours) { this.actualHours = actualHours; }
    public Integer getCheckinStatus() { return checkinStatus; }
    public void setCheckinStatus(Integer checkinStatus) { this.checkinStatus = checkinStatus; }
    public String getCheckinAddress() { return checkinAddress; }
    public void setCheckinAddress(String checkinAddress) { this.checkinAddress = checkinAddress; }
    public String getCheckinRemark() { return checkinRemark; }
    public void setCheckinRemark(String checkinRemark) { this.checkinRemark = checkinRemark; }
    public Date getCreateTime() { return createTime; }
    public void setCreateTime(Date createTime) { this.createTime = createTime; }
    public String getVolName() { return volName; }
    public void setVolName(String volName) { this.volName = volName; }
    public String getActTitle() { return actTitle; }
    public void setActTitle(String actTitle) { this.actTitle = actTitle; }
}
