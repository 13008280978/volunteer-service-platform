package com.volunteer.controller;

import com.volunteer.po.*;
import com.volunteer.service.*;
import com.volunteer.service.impl.*;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.*;
import java.io.IOException;
import java.util.List;

@WebServlet("/AttendanceServlet")
public class AttendanceServlet extends HttpServlet {
    private AttendanceService attendanceService = new AttendanceServiceImpl();
    private EnrollmentService enrollmentService = new EnrollmentServiceImpl();

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String action = req.getParameter("action");
        HttpSession session = req.getSession();
        Volunteer vol = (Volunteer) session.getAttribute("volunteer");
        
        if (vol == null) {
            resp.sendRedirect(req.getContextPath() + "/login.jsp?role=volunteer");
            return;
        }
        
        if ("checkin".equals(action)) {
            String enrollIdStr = req.getParameter("enrollId");
            if (enrollIdStr != null) {
                Integer enrollId = Integer.parseInt(enrollIdStr);
                Enrollment enrollment = enrollmentService.findById(enrollId);
                req.setAttribute("enrollment", enrollment);
                // Check if already checked in
                Attendance attendance = attendanceService.findByEnrollId(enrollId);
                req.setAttribute("attendance", attendance);
                req.getRequestDispatcher("/volunteer/attendance_checkin.jsp").forward(req, resp);
            }
        } else {
            // List attendance records
            List<Attendance> attendances = attendanceService.findByVolId(vol.getVolId());
            req.setAttribute("attendances", attendances);
            // List enrollments that can checkin
            List<Enrollment> enrollments = enrollmentService.findByVolId(vol.getVolId());
            req.setAttribute("enrollments", enrollments);
            req.getRequestDispatcher("/volunteer/attendance_checkin.jsp").forward(req, resp);
        }
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        HttpSession session = req.getSession();
        Volunteer vol = (Volunteer) session.getAttribute("volunteer");
        if (vol == null) {
            resp.sendRedirect(req.getContextPath() + "/login.jsp?role=volunteer");
            return;
        }
        
        String action = req.getParameter("action");
        
        if ("checkin".equals(action)) {
            String enrollIdStr = req.getParameter("enrollId");
            String volIdStr = req.getParameter("volId");
            String actIdStr = req.getParameter("actId");
            String checkinAddress = req.getParameter("checkinAddress");
            
            boolean success = attendanceService.checkin(
                Integer.parseInt(enrollIdStr), 
                Integer.parseInt(volIdStr), 
                Integer.parseInt(actIdStr), 
                checkinAddress != null ? checkinAddress : "活动现场"
            );
            
            if (success) {
                req.setAttribute("success", "签到成功！时间：" + new java.text.SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(new java.util.Date()));
            } else {
                req.setAttribute("error", "签到失败，请勿重复签到。");
            }
        } else if ("checkout".equals(action)) {
            String attendIdStr = req.getParameter("attendId");
            String actualHours = req.getParameter("actualHours");
            boolean success = attendanceService.checkout(Integer.parseInt(attendIdStr), Double.parseDouble(actualHours));
            if (success) {
                req.setAttribute("success", "签退成功！");
            } else {
                req.setAttribute("error", "签退失败。");
            }
        }
        
        // Redirect to attendance page
        List<Attendance> attendances = attendanceService.findByVolId(vol.getVolId());
        req.setAttribute("attendances", attendances);
        List<Enrollment> enrollments = enrollmentService.findByVolId(vol.getVolId());
        req.setAttribute("enrollments", enrollments);
        req.getRequestDispatcher("/volunteer/attendance_checkin.jsp").forward(req, resp);
    }
}
