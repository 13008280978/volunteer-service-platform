package com.volunteer.controller;

import com.volunteer.po.Auser;
import com.volunteer.po.Notice;
import com.volunteer.service.NoticeService;
import com.volunteer.service.impl.NoticeServiceImpl;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.*;
import java.io.IOException;
import java.util.List;

@WebServlet("/admin/AdminNoticeServlet")
public class AdminNoticeServlet extends HttpServlet {
    private NoticeService noticeService = new NoticeServiceImpl();

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        List<Notice> notices = noticeService.findAll();
        req.setAttribute("notices", notices);
        req.getRequestDispatcher("/admin/notice_manage.jsp").forward(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String action = req.getParameter("action");
        HttpSession session = req.getSession();
        Auser admin = (Auser) session.getAttribute("admin");
        
        if ("add".equals(action)) {
            Notice notice = new Notice();
            notice.setNoticeTitle(req.getParameter("noticeTitle"));
            notice.setNoticeContent(req.getParameter("noticeContent"));
            notice.setNoticeType(Integer.parseInt(req.getParameter("noticeType")));
            notice.setNoticePriority(Integer.parseInt(req.getParameter("noticePriority")));
            notice.setAdminId(admin.getAdminId());
            notice.setNoticeStatus(1);
            noticeService.addNotice(notice);
        } else if ("update".equals(action)) {
            Notice notice = new Notice();
            notice.setNoticeId(Integer.parseInt(req.getParameter("noticeId")));
            notice.setNoticeTitle(req.getParameter("noticeTitle"));
            notice.setNoticeContent(req.getParameter("noticeContent"));
            notice.setNoticeType(Integer.parseInt(req.getParameter("noticeType")));
            notice.setNoticePriority(Integer.parseInt(req.getParameter("noticePriority")));
            noticeService.updateNotice(notice);
        } else if ("status".equals(action)) {
            noticeService.updateStatus(Integer.parseInt(req.getParameter("noticeId")), Integer.parseInt(req.getParameter("noticeStatus")));
        }
        
        resp.sendRedirect(req.getContextPath() + "/admin/AdminNoticeServlet");
    }
}
