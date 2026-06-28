package com.volunteer.po;
import java.util.Date;
public class Volunteer {
    private Integer volId; private String volName; private String volAccount; private String volPassword;
    private Integer volGender; private Integer volAge; private String volPhone; private String volEmail;
    private String volIdcard; private String volAddress; private String volAvatar;
    private String volEmergencyContact; private String volEmergencyPhone; private String volSkills;
    private Double volTotalHours; private Integer volTotalPoints; private Integer volStatus;
    private String volRemark; private Date createTime; private Date updateTime;
    public Integer getVolId(){return volId;} public void setVolId(Integer v){this.volId=v;}
    public String getVolName(){return volName;} public void setVolName(String v){this.volName=v;}
    public String getVolAccount(){return volAccount;} public void setVolAccount(String v){this.volAccount=v;}
    public String getVolPassword(){return volPassword;} public void setVolPassword(String v){this.volPassword=v;}
    public Integer getVolGender(){return volGender;} public void setVolGender(Integer v){this.volGender=v;}
    public Integer getVolAge(){return volAge;} public void setVolAge(Integer v){this.volAge=v;}
    public String getVolPhone(){return volPhone;} public void setVolPhone(String v){this.volPhone=v;}
    public String getVolEmail(){return volEmail;} public void setVolEmail(String v){this.volEmail=v;}
    public String getVolIdcard(){return volIdcard;} public void setVolIdcard(String v){this.volIdcard=v;}
    public String getVolAddress(){return volAddress;} public void setVolAddress(String v){this.volAddress=v;}
    public String getVolAvatar(){return volAvatar;} public void setVolAvatar(String v){this.volAvatar=v;}
    public String getVolEmergencyContact(){return volEmergencyContact;} public void setVolEmergencyContact(String v){this.volEmergencyContact=v;}
    public String getVolEmergencyPhone(){return volEmergencyPhone;} public void setVolEmergencyPhone(String v){this.volEmergencyPhone=v;}
    public String getVolSkills(){return volSkills;} public void setVolSkills(String v){this.volSkills=v;}
    public Double getVolTotalHours(){return volTotalHours;} public void setVolTotalHours(Double v){this.volTotalHours=v;}
    public Integer getVolTotalPoints(){return volTotalPoints;} public void setVolTotalPoints(Integer v){this.volTotalPoints=v;}
    public Integer getVolStatus(){return volStatus;} public void setVolStatus(Integer v){this.volStatus=v;}
    public String getVolRemark(){return volRemark;} public void setVolRemark(String v){this.volRemark=v;}
    public Date getCreateTime(){return createTime;} public void setCreateTime(Date v){this.createTime=v;}
    public Date getUpdateTime(){return updateTime;} public void setUpdateTime(Date v){this.updateTime=v;}
}
