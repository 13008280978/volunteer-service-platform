package com.volunteer.dao;

import com.volunteer.po.Notice;
import org.apache.ibatis.annotations.Param;
import java.util.List;

public interface NoticeDao {
    Notice findById(@Param("noticeId") Integer noticeId);
    List<Notice> findAll();
    List<Notice> findPublished();
    List<Notice> findByPage(@Param("offset") Integer offset, @Param("limit") Integer limit);
    int insert(Notice notice);
    int update(Notice notice);
    int updateStatus(@Param("noticeId") Integer noticeId, @Param("noticeStatus") Integer noticeStatus);
    int countAll();
}
