package com.volunteer.po;

import java.util.Date;

public class Notice {
    private Integer noticeId;
    private String noticeTitle;
    private String noticeContent;
    private Integer noticeType;
    private Integer noticePriority;
    private Integer adminId;
    private Integer noticeStatus;
    private Date createTime;
    private Date updateTime;

    // 关联字段
    private String adminName;

    public Integer getNoticeId() { return noticeId; }
    public void setNoticeId(Integer noticeId) { this.noticeId = noticeId; }
    public String getNoticeTitle() { return noticeTitle; }
    public void setNoticeTitle(String noticeTitle) { this.noticeTitle = noticeTitle; }
    public String getNoticeContent() { return noticeContent; }
    public void setNoticeContent(String noticeContent) { this.noticeContent = noticeContent; }
    public Integer getNoticeType() { return noticeType; }
    public void setNoticeType(Integer noticeType) { this.noticeType = noticeType; }
    public Integer getNoticePriority() { return noticePriority; }
    public void setNoticePriority(Integer noticePriority) { this.noticePriority = noticePriority; }
    public Integer getAdminId() { return adminId; }
    public void setAdminId(Integer adminId) { this.adminId = adminId; }
    public Integer getNoticeStatus() { return noticeStatus; }
    public void setNoticeStatus(Integer noticeStatus) { this.noticeStatus = noticeStatus; }
    public Date getCreateTime() { return createTime; }
    public void setCreateTime(Date createTime) { this.createTime = createTime; }
    public Date getUpdateTime() { return updateTime; }
    public void setUpdateTime(Date updateTime) { this.updateTime = updateTime; }
    public String getAdminName() { return adminName; }
    public void setAdminName(String adminName) { this.adminName = adminName; }
}
