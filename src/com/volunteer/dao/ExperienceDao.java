package com.volunteer.dao;

import com.volunteer.po.Experience;
import org.apache.ibatis.annotations.Param;
import java.util.List;

public interface ExperienceDao {
    Experience findById(@Param("expId") Integer expId);
    List<Experience> findByVolId(@Param("volId") Integer volId);
    List<Experience> findAll();
    List<Experience> findByStatus(@Param("expStatus") Integer expStatus);
    List<Experience> findApproved(@Param("offset") Integer offset, @Param("limit") Integer limit);
    int insert(Experience experience);
    int update(Experience experience);
    int updateStatus(@Param("expId") Integer expId, @Param("expStatus") Integer expStatus, @Param("expRemark") String expRemark);
    int incrementViews(@Param("expId") Integer expId);
    int countAll();
    int countByStatus(@Param("expStatus") Integer expStatus);
}
