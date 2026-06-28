package com.volunteer.controller;

import com.volunteer.po.Activity;
import com.volunteer.po.ActivityType;
import com.volunteer.service.ActivityService;
import com.volunteer.service.ActivityTypeService;
import com.volunteer.service.impl.ActivityServiceImpl;
import com.volunteer.service.impl.ActivityTypeServiceImpl;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.*;
import java.io.IOException;
import java.util.List;

@WebServlet("/ActivityServlet")
public class ActivityServlet extends HttpServlet {
    private ActivityService activityService = new ActivityServiceImpl();
    private ActivityTypeService typeService = new ActivityTypeServiceImpl();

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String typeIdStr = req.getParameter("typeId");
        String keyword = req.getParameter("keyword");
        String location = req.getParameter("location");
        Integer typeId = null;
        if (typeIdStr != null && !typeIdStr.isEmpty()) {
            typeId = Integer.parseInt(typeIdStr);
        }
        
        List<Activity> activities;
        if (typeId != null || (keyword != null && !keyword.isEmpty()) || (location != null && !location.isEmpty())) {
            activities = activityService.search(typeId, keyword, location);
        } else {
            activities = activityService.findByStatus(1); // 报名中
            List<Activity> ongoing = activityService.findByStatus(2); // 进行中
            activities.addAll(ongoing);
        }
        
        List<ActivityType> types = typeService.findActive();
        req.setAttribute("activities", activities);
        req.setAttribute("types", types);
        req.setAttribute("typeId", typeIdStr);
        req.setAttribute("keyword", keyword);
        req.setAttribute("location", location);
        req.getRequestDispatcher("/volunteer/activity_list.jsp").forward(req, resp);
    }
}
