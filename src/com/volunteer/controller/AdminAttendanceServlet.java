package com.volunteer.controller;

import com.volunteer.po.Attendance;
import com.volunteer.service.AttendanceService;
import com.volunteer.service.impl.AttendanceServiceImpl;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.*;
import java.io.IOException;
import java.util.List;

@WebServlet("/admin/AdminAttendanceServlet")
public class AdminAttendanceServlet extends HttpServlet {
    private AttendanceService attendanceService = new AttendanceServiceImpl();

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String actIdStr = req.getParameter("actId");
        List<Attendance> attendances;
        if (actIdStr != null && !actIdStr.isEmpty()) {
            attendances = attendanceService.findByActId(Integer.parseInt(actIdStr));
        } else {
            attendances = attendanceService.findAll();
        }
        req.setAttribute("attendances", attendances);
        req.getRequestDispatcher("/admin/attendance_manage.jsp").forward(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String action = req.getParameter("action");
        if ("confirm".equals(action)) {
            String attendIdStr = req.getParameter("attendId");
            attendanceService.confirmAttendance(Integer.parseInt(attendIdStr));
        }
        resp.sendRedirect(req.getContextPath() + "/admin/AdminAttendanceServlet");
    }
}
