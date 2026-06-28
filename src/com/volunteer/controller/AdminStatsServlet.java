package com.volunteer.controller;

import com.volunteer.po.Volunteer;
import com.volunteer.service.*;
import com.volunteer.service.impl.*;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.*;
import java.io.IOException;
import java.util.List;

@WebServlet("/admin/AdminStatsServlet")
public class AdminStatsServlet extends HttpServlet {
    private VolunteerService volunteerService = new VolunteerServiceImpl();
    private ActivityService activityService = new ActivityServiceImpl();
    private EnrollmentService enrollmentService = new EnrollmentServiceImpl();
    private ExperienceService experienceService = new ExperienceServiceImpl();
    private NoticeService noticeService = new NoticeServiceImpl();

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        // Statistics data
        int totalVolunteers = volunteerService.countAll();
        int approvedVolunteers = volunteerService.countByStatus(1);
        int pendingVolunteers = volunteerService.countByStatus(0);
        int totalActivities = activityService.countAll();
        Double totalHours = activityService.sumTotalHours();
        int totalExperiences = experienceService.countAll();
        int pendingExperiences = experienceService.countByStatus(0);
        int totalNotices = noticeService.countAll();
        
        // Points ranking
        List<Volunteer> pointsRanking = volunteerService.getPointsRanking();
        
        req.setAttribute("totalVolunteers", totalVolunteers);
        req.setAttribute("approvedVolunteers", approvedVolunteers);
        req.setAttribute("pendingVolunteers", pendingVolunteers);
        req.setAttribute("totalActivities", totalActivities);
        req.setAttribute("totalHours", totalHours);
        req.setAttribute("totalExperiences", totalExperiences);
        req.setAttribute("pendingExperiences", pendingExperiences);
        req.setAttribute("totalNotices", totalNotices);
        req.setAttribute("pointsRanking", pointsRanking);
        
        req.getRequestDispatcher("/admin/stats.jsp").forward(req, resp);
    }
}
