package com.volunteer.controller;

import com.volunteer.po.Experience;
import com.volunteer.service.ExperienceService;
import com.volunteer.service.impl.ExperienceServiceImpl;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.*;
import java.io.IOException;
import java.util.List;

@WebServlet("/admin/AdminExperienceServlet")
public class AdminExperienceServlet extends HttpServlet {
    private ExperienceService experienceService = new ExperienceServiceImpl();

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String statusStr = req.getParameter("status");
        List<Experience> experiences;
        if (statusStr != null && !statusStr.isEmpty()) {
            experiences = experienceService.findByStatus(Integer.parseInt(statusStr));
        } else {
            experiences = experienceService.findAll();
        }
        req.setAttribute("experiences", experiences);
        req.getRequestDispatcher("/admin/experience_manage.jsp").forward(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String action = req.getParameter("action");
        if ("audit".equals(action)) {
            String expIdStr = req.getParameter("expId");
            String statusStr = req.getParameter("status");
            String remark = req.getParameter("remark");
            experienceService.auditExperience(Integer.parseInt(expIdStr), Integer.parseInt(statusStr), remark);
        }
        resp.sendRedirect(req.getContextPath() + "/admin/AdminExperienceServlet");
    }
}
