package com.volunteer.controller;

import com.volunteer.po.Activity;
import com.volunteer.po.Volunteer;
import com.volunteer.service.ActivityService;
import com.volunteer.service.EnrollmentService;
import com.volunteer.service.impl.ActivityServiceImpl;
import com.volunteer.service.impl.EnrollmentServiceImpl;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.*;
import java.io.IOException;

@WebServlet("/EnrollServlet")
public class EnrollServlet extends HttpServlet {
    private EnrollmentService enrollmentService = new EnrollmentServiceImpl();
    private ActivityService activityService = new ActivityServiceImpl();

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String actIdStr = req.getParameter("actId");
        if (actIdStr != null) {
            Integer actId = Integer.parseInt(actIdStr);
            Activity activity = activityService.findById(actId);
            req.setAttribute("activity", activity);
            req.getRequestDispatcher("/volunteer/enroll_confirm.jsp").forward(req, resp);
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
        
        String actIdStr = req.getParameter("actId");
        String enrollReason = req.getParameter("enrollReason");
        
        if (actIdStr == null) {
            resp.sendRedirect(req.getContextPath() + "/ActivityServlet");
            return;
        }
        
        Integer actId = Integer.parseInt(actIdStr);
        boolean success = enrollmentService.enroll(vol.getVolId(), actId, enrollReason);
        
        if (success) {
            req.setAttribute("success", "报名成功！请等待管理员审核。");
        } else {
            req.setAttribute("error", "报名失败，您可能已经报名过该活动。");
        }
        
        Activity activity = activityService.findById(actId);
        req.setAttribute("activity", activity);
        req.getRequestDispatcher("/volunteer/activity_detail.jsp").forward(req, resp);
    }
}
