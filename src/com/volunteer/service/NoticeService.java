package com.volunteer.service;

import com.volunteer.po.Notice;
import java.util.List;

public interface NoticeService {
    Notice findById(Integer noticeId);
    List<Notice> findAll();
    List<Notice> findPublished(Integer page, Integer pageSize);
    boolean addNotice(Notice notice);
    boolean updateNotice(Notice notice);
    boolean updateStatus(Integer noticeId, Integer noticeStatus);
    int countAll();
}
