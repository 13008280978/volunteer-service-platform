package com.volunteer.controller;

import com.volunteer.po.ActivityType;
import com.volunteer.service.ActivityTypeService;
import com.volunteer.service.impl.ActivityTypeServiceImpl;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.*;
import java.io.IOException;
import java.util.List;

@WebServlet("/admin/AdminTypeServlet")
public class AdminTypeServlet extends HttpServlet {
    private ActivityTypeService typeService = new ActivityTypeServiceImpl();

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        List<ActivityType> types = typeService.findAll();
        req.setAttribute("types", types);
        req.getRequestDispatcher("/admin/type_manage.jsp").forward(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String action = req.getParameter("action");
        
        if ("add".equals(action)) {
            ActivityType type = new ActivityType();
            type.setTypeName(req.getParameter("typeName"));
            type.setTypeDesc(req.getParameter("typeDesc"));
            String sort = req.getParameter("typeSort");
            type.setTypeSort(sort != null && !sort.isEmpty() ? Integer.parseInt(sort) : 0);
            typeService.addType(type);
        } else if ("update".equals(action)) {
            ActivityType type = new ActivityType();
            type.setTypeId(Integer.parseInt(req.getParameter("typeId")));
            type.setTypeName(req.getParameter("typeName"));
            type.setTypeDesc(req.getParameter("typeDesc"));
            String sort = req.getParameter("typeSort");
            type.setTypeSort(sort != null && !sort.isEmpty() ? Integer.parseInt(sort) : 0);
            typeService.updateType(type);
        } else if ("status".equals(action)) {
            typeService.updateStatus(Integer.parseInt(req.getParameter("typeId")), Integer.parseInt(req.getParameter("typeStatus")));
        }
        
        resp.sendRedirect(req.getContextPath() + "/admin/AdminTypeServlet");
    }
}
