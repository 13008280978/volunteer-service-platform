package com.volunteer.controller;

import com.volunteer.po.Volunteer;
import com.volunteer.service.VolunteerService;
import com.volunteer.service.impl.VolunteerServiceImpl;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.*;
import java.io.IOException;
import java.util.List;

@WebServlet("/admin/AdminVolunteerServlet")
public class AdminVolunteerServlet extends HttpServlet {
    private VolunteerService volunteerService = new VolunteerServiceImpl();

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String statusStr = req.getParameter("status");
        String keyword = req.getParameter("keyword");
        List<Volunteer> volunteers;
        
        if (keyword != null && !keyword.isEmpty()) {
            volunteers = volunteerService.searchByName(keyword);
        } else if (statusStr != null && !statusStr.isEmpty()) {
            volunteers = volunteerService.findByStatus(Integer.parseInt(statusStr));
        } else {
            volunteers = volunteerService.findAll();
        }
        
        req.setAttribute("volunteers", volunteers);
        req.setAttribute("keyword", keyword);
        req.getRequestDispatcher("/admin/volunteer_manage.jsp").forward(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String action = req.getParameter("action");
        
        if ("audit".equals(action)) {
            String volIdStr = req.getParameter("volId");
            String statusStr = req.getParameter("status");
            String remark = req.getParameter("remark");
            volunteerService.auditVolunteer(Integer.parseInt(volIdStr), Integer.parseInt(statusStr), remark);
        }
        
        resp.sendRedirect(req.getContextPath() + "/admin/AdminVolunteerServlet");
    }
}
