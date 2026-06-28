package com.volunteer.po;

import java.util.Date;

public class ActivityType {
    private Integer typeId;
    private String typeName;
    private String typeDesc;
    private Integer typeSort;
    private Integer typeStatus;
    private Date createTime;

    public Integer getTypeId() { return typeId; }
    public void setTypeId(Integer typeId) { this.typeId = typeId; }
    public String getTypeName() { return typeName; }
    public void setTypeName(String typeName) { this.typeName = typeName; }
    public String getTypeDesc() { return typeDesc; }
    public void setTypeDesc(String typeDesc) { this.typeDesc = typeDesc; }
    public Integer getTypeSort() { return typeSort; }
    public void setTypeSort(Integer typeSort) { this.typeSort = typeSort; }
    public Integer getTypeStatus() { return typeStatus; }
    public void setTypeStatus(Integer typeStatus) { this.typeStatus = typeStatus; }
    public Date getCreateTime() { return createTime; }
    public void setCreateTime(Date createTime) { this.createTime = createTime; }
}
