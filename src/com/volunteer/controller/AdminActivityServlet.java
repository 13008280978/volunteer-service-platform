package com.volunteer.controller;

import com.volunteer.po.Activity;
import com.volunteer.po.Auser;
import com.volunteer.po.ActivityType;
import com.volunteer.service.ActivityService;
import com.volunteer.service.ActivityTypeService;
import com.volunteer.service.impl.ActivityServiceImpl;
import com.volunteer.service.impl.ActivityTypeServiceImpl;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.*;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.List;

@WebServlet("/admin/AdminActivityServlet")
public class AdminActivityServlet extends HttpServlet {
    private ActivityService activityService = new ActivityServiceImpl();
    private ActivityTypeService typeService = new ActivityTypeServiceImpl();

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String action = req.getParameter("action");
        List<ActivityType> types = typeService.findActive();
        req.setAttribute("types", types);
        
        if ("add".equals(action)) {
            req.getRequestDispatcher("/admin/activity_add.jsp").forward(req, resp);
        } else if ("edit".equals(action)) {
            String actIdStr = req.getParameter("actId");
            if (actIdStr != null) {
                Activity activity = activityService.findById(Integer.parseInt(actIdStr));
                req.setAttribute("activity", activity);
                req.getRequestDispatcher("/admin/activity_add.jsp").forward(req, resp);
            }
        } else {
            String statusStr = req.getParameter("status");
            List<Activity> activities;
            if (statusStr != null && !statusStr.isEmpty()) {
                activities = activityService.findByStatus(Integer.parseInt(statusStr));
            } else {
                activities = activityService.findAll();
            }
            req.setAttribute("activities", activities);
            req.getRequestDispatcher("/admin/activity_manage.jsp").forward(req, resp);
        }
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String action = req.getParameter("action");
        HttpSession session = req.getSession();
        Auser admin = (Auser) session.getAttribute("admin");
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm");
        
        if ("add".equals(action)) {
            Activity activity = new Activity();
            activity.setActTitle(req.getParameter("actTitle"));
            activity.setActDesc(req.getParameter("actDesc"));
            activity.setActTypeId(Integer.parseInt(req.getParameter("actTypeId")));
            activity.setActLocation(req.getParameter("actLocation"));
            try { activity.setActStartTime(sdf.parse(req.getParameter("actStartTime"))); } catch (Exception e) {}
            try { activity.setActEndTime(sdf.parse(req.getParameter("actEndTime"))); } catch (Exception e) {}
            String deadline = req.getParameter("actEnrollDeadline");
            if (deadline != null && !deadline.isEmpty()) {
                try { activity.setActEnrollDeadline(sdf.parse(deadline)); } catch (Exception e) {}
            }
            String maxPeople = req.getParameter("actMaxPeople");
            activity.setActMaxPeople(maxPeople != null && !maxPeople.isEmpty() ? Integer.parseInt(maxPeople) : 0);
            String hours = req.getParameter("actHours");
            activity.setActHours(hours != null && !hours.isEmpty() ? Double.parseDouble(hours) : 0);
            String points = req.getParameter("actPoints");
            activity.setActPoints(points != null && !points.isEmpty() ? Integer.parseInt(points) : 0);
            activity.setActContact(req.getParameter("actContact"));
            activity.setActContactPhone(req.getParameter("actContactPhone"));
            activity.setActStatus(Integer.parseInt(req.getParameter("actStatus")));
            activity.setAdminId(admin.getAdminId());
            
            activityService.addActivity(activity);
            resp.sendRedirect(req.getContextPath() + "/admin/AdminActivityServlet");
        } else if ("update".equals(action)) {
            Activity activity = new Activity();
            activity.setActId(Integer.parseInt(req.getParameter("actId")));
            activity.setActTitle(req.getParameter("actTitle"));
            activity.setActDesc(req.getParameter("actDesc"));
            activity.setActTypeId(Integer.parseInt(req.getParameter("actTypeId")));
            activity.setActLocation(req.getParameter("actLocation"));
            try { activity.setActStartTime(sdf.parse(req.getParameter("actStartTime"))); } catch (Exception e) {}
            try { activity.setActEndTime(sdf.parse(req.getParameter("actEndTime"))); } catch (Exception e) {}
            String deadline = req.getParameter("actEnrollDeadline");
            if (deadline != null && !deadline.isEmpty()) {
                try { activity.setActEnrollDeadline(sdf.parse(deadline)); } catch (Exception e) {}
            }
            String maxPeople = req.getParameter("actMaxPeople");
            activity.setActMaxPeople(maxPeople != null && !maxPeople.isEmpty() ? Integer.parseInt(maxPeople) : 0);
            String hours = req.getParameter("actHours");
            activity.setActHours(hours != null && !hours.isEmpty() ? Double.parseDouble(hours) : 0);
            String points = req.getParameter("actPoints");
            activity.setActPoints(points != null && !points.isEmpty() ? Integer.parseInt(points) : 0);
            activity.setActContact(req.getParameter("actContact"));
            activity.setActContactPhone(req.getParameter("actContactPhone"));
            activity.setActStatus(Integer.parseInt(req.getParameter("actStatus")));
            
            activityService.updateActivity(activity);
            resp.sendRedirect(req.getContextPath() + "/admin/AdminActivityServlet");
        } else if ("status".equals(action)) {
            String actIdStr = req.getParameter("actId");
            String statusStr = req.getParameter("status");
            activityService.updateStatus(Integer.parseInt(actIdStr), Integer.parseInt(statusStr));
            resp.sendRedirect(req.getContextPath() + "/admin/AdminActivityServlet");
        }
    }
}
