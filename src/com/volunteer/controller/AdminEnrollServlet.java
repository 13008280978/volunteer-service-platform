package com.volunteer.controller;

import com.volunteer.po.Enrollment;
import com.volunteer.service.EnrollmentService;
import com.volunteer.service.impl.EnrollmentServiceImpl;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.*;
import java.io.IOException;
import java.util.List;

@WebServlet("/admin/AdminEnrollServlet")
public class AdminEnrollServlet extends HttpServlet {
    private EnrollmentService enrollmentService = new EnrollmentServiceImpl();

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String statusStr = req.getParameter("status");
        List<Enrollment> enrollments;
        if (statusStr != null && !statusStr.isEmpty()) {
            enrollments = enrollmentService.findAll();
        } else {
            enrollments = enrollmentService.findAll();
        }
        req.setAttribute("enrollments", enrollments);
        req.getRequestDispatcher("/admin/enroll_manage.jsp").forward(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String action = req.getParameter("action");
        if ("audit".equals(action)) {
            String enrollIdStr = req.getParameter("enrollId");
            String statusStr = req.getParameter("status");
            String remark = req.getParameter("remark");
            enrollmentService.auditEnroll(Integer.parseInt(enrollIdStr), Integer.parseInt(statusStr), remark);
        }
        resp.sendRedirect(req.getContextPath() + "/admin/AdminEnrollServlet");
    }
}
