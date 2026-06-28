package com.volunteer.controller;

import com.volunteer.po.Points;
import com.volunteer.service.PointsService;
import com.volunteer.service.impl.PointsServiceImpl;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.*;
import java.io.IOException;
import java.util.List;

@WebServlet("/admin/AdminPointsServlet")
public class AdminPointsServlet extends HttpServlet {
    private PointsService pointsService = new PointsServiceImpl();

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String volIdStr = req.getParameter("volId");
        List<Points> points;
        if (volIdStr != null && !volIdStr.isEmpty()) {
            points = pointsService.findByVolId(Integer.parseInt(volIdStr));
        } else {
            points = pointsService.findAll();
        }
        req.setAttribute("points", points);
        req.getRequestDispatcher("/admin/points_manage.jsp").forward(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String action = req.getParameter("action");
        if ("adjust".equals(action)) {
            String volIdStr = req.getParameter("volId");
            String pointType = req.getParameter("pointType");
            String pointValue = req.getParameter("pointValue");
            String pointDesc = req.getParameter("pointDesc");
            pointsService.adjustPoints(
                Integer.parseInt(volIdStr), null,
                Integer.parseInt(pointType), Integer.parseInt(pointValue), pointDesc
            );
        }
        resp.sendRedirect(req.getContextPath() + "/admin/AdminPointsServlet");
    }
}
