package com.volunteer.po;

import java.util.Date;

public class Experience {
    private Integer expId;
    private Integer volId;
    private Integer actId;
    private String expTitle;
    private String expContent;
    private String expImages;
    private Integer expStatus;
    private Integer expViews;
    private String expRemark;
    private Date createTime;
    private Date updateTime;

    // 关联字段
    private String volName;
    private String actTitle;

    public Integer getExpId() { return expId; }
    public void setExpId(Integer expId) { this.expId = expId; }
    public Integer getVolId() { return volId; }
    public void setVolId(Integer volId) { this.volId = volId; }
    public Integer getActId() { return actId; }
    public void setActId(Integer actId) { this.actId = actId; }
    public String getExpTitle() { return expTitle; }
    public void setExpTitle(String expTitle) { this.expTitle = expTitle; }
    public String getExpContent() { return expContent; }
    public void setExpContent(String expContent) { this.expContent = expContent; }
    public String getExpImages() { return expImages; }
    public void setExpImages(String expImages) { this.expImages = expImages; }
    public Integer getExpStatus() { return expStatus; }
    public void setExpStatus(Integer expStatus) { this.expStatus = expStatus; }
    public Integer getExpViews() { return expViews; }
    public void setExpViews(Integer expViews) { this.expViews = expViews; }
    public String getExpRemark() { return expRemark; }
    public void setExpRemark(String expRemark) { this.expRemark = expRemark; }
    public Date getCreateTime() { return createTime; }
    public void setCreateTime(Date createTime) { this.createTime = createTime; }
    public Date getUpdateTime() { return updateTime; }
    public void setUpdateTime(Date updateTime) { this.updateTime = updateTime; }
    public String getVolName() { return volName; }
    public void setVolName(String volName) { this.volName = volName; }
    public String getActTitle() { return actTitle; }
    public void setActTitle(String actTitle) { this.actTitle = actTitle; }
}
