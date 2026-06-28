package com.volunteer.controller;

import com.volunteer.po.Activity;
import com.volunteer.po.Enrollment;
import com.volunteer.po.Volunteer;
import com.volunteer.service.ActivityService;
import com.volunteer.service.EnrollmentService;
import com.volunteer.service.impl.ActivityServiceImpl;
import com.volunteer.service.impl.EnrollmentServiceImpl;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.*;
import java.io.IOException;

@WebServlet("/ActivityDetailServlet")
public class ActivityDetailServlet extends HttpServlet {
    private ActivityService activityService = new ActivityServiceImpl();
    private EnrollmentService enrollmentService = new EnrollmentServiceImpl();

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String actIdStr = req.getParameter("actId");
        if (actIdStr == null) {
            resp.sendRedirect(req.getContextPath() + "/ActivityServlet");
            return;
        }
        Integer actId = Integer.parseInt(actIdStr);
        Activity activity = activityService.findById(actId);
        if (activity == null) {
            req.setAttribute("error", "活动不存在");
            req.getRequestDispatcher("/volunteer/activity_list.jsp").forward(req, resp);
            return;
        }
        
        req.setAttribute("activity", activity);
        
        // Check if current user has enrolled
        HttpSession session = req.getSession();
        Volunteer vol = (Volunteer) session.getAttribute("volunteer");
        if (vol != null) {
            Enrollment enroll = enrollmentService.findByVolAndAct(vol.getVolId(), actId);
            req.setAttribute("enrollment", enroll);
        }
        
        req.getRequestDispatcher("/volunteer/activity_detail.jsp").forward(req, resp);
    }
}
