package com.volunteer.controller;

import com.volunteer.po.Notice;
import com.volunteer.service.NoticeService;
import com.volunteer.service.impl.NoticeServiceImpl;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.*;
import java.io.IOException;
import java.util.List;

@WebServlet("/NoticeServlet")
public class NoticeServlet extends HttpServlet {
    private NoticeService noticeService = new NoticeServiceImpl();

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String action = req.getParameter("action");
        
        if ("detail".equals(action)) {
            String noticeIdStr = req.getParameter("noticeId");
            if (noticeIdStr != null) {
                Notice notice = noticeService.findById(Integer.parseInt(noticeIdStr));
                req.setAttribute("notice", notice);
            }
        }
        
        String pageStr = req.getParameter("page");
        int page = pageStr != null ? Integer.parseInt(pageStr) : 1;
        List<Notice> notices = noticeService.findPublished(page, 10);
        req.setAttribute("notices", notices);
        req.setAttribute("currentPage", page);
        req.getRequestDispatcher("/volunteer/notice_list.jsp").forward(req, resp);
    }
}
