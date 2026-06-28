package com.volunteer.po;

import java.util.Date;

public class Points {
    private Integer pointId;
    private Integer volId;
    private Integer actId;
    private Integer pointType;
    private Integer pointValue;
    private String pointDesc;
    private Integer pointBalance;
    private Date createTime;

    // 关联字段
    private String volName;
    private String actTitle;

    public Integer getPointId() { return pointId; }
    public void setPointId(Integer pointId) { this.pointId = pointId; }
    public Integer getVolId() { return volId; }
    public void setVolId(Integer volId) { this.volId = volId; }
    public Integer getActId() { return actId; }
    public void setActId(Integer actId) { this.actId = actId; }
    public Integer getPointType() { return pointType; }
    public void setPointType(Integer pointType) { this.pointType = pointType; }
    public Integer getPointValue() { return pointValue; }
    public void setPointValue(Integer pointValue) { this.pointValue = pointValue; }
    public String getPointDesc() { return pointDesc; }
    public void setPointDesc(String pointDesc) { this.pointDesc = pointDesc; }
    public Integer getPointBalance() { return pointBalance; }
    public void setPointBalance(Integer pointBalance) { this.pointBalance = pointBalance; }
    public Date getCreateTime() { return createTime; }
    public void setCreateTime(Date createTime) { this.createTime = createTime; }
    public String getVolName() { return volName; }
    public void setVolName(String volName) { this.volName = volName; }
    public String getActTitle() { return actTitle; }
    public void setActTitle(String actTitle) { this.actTitle = actTitle; }
}
