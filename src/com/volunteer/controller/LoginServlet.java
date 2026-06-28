package com.volunteer.controller;

import com.volunteer.po.Auser;
import com.volunteer.po.Volunteer;
import com.volunteer.service.AuserService;
import com.volunteer.service.VolunteerService;
import com.volunteer.service.impl.AuserServiceImpl;
import com.volunteer.service.impl.VolunteerServiceImpl;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.*;
import java.io.IOException;

@WebServlet("/LoginServlet")
public class LoginServlet extends HttpServlet {
    private AuserService auserService = new AuserServiceImpl();
    private VolunteerService volunteerService = new VolunteerServiceImpl();

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String account = req.getParameter("account");
        String password = req.getParameter("password");
        String role = req.getParameter("role");
        
        if (role == null || account == null || password == null) {
            req.setAttribute("error", "请填写完整的登录信息");
            req.getRequestDispatcher("/login.jsp").forward(req, resp);
            return;
        }
        
        if ("admin".equals(role)) {
            Auser admin = auserService.login(account, password);
            if (admin != null) {
                HttpSession session = req.getSession();
                session.setAttribute("admin", admin);
                resp.sendRedirect(req.getContextPath() + "/admin/admin_index.jsp");
            } else {
                req.setAttribute("error", "管理员账号或密码错误");
                req.setAttribute("role", "admin");
                req.getRequestDispatcher("/login.jsp").forward(req, resp);
            }
        } else {
            Volunteer vol = volunteerService.login(account, password);
            if (vol != null) {
                HttpSession session = req.getSession();
                session.setAttribute("volunteer", vol);
                resp.sendRedirect(req.getContextPath() + "/index.jsp");
            } else {
                // Check if account exists but not approved
                Volunteer checkVol = volunteerService.findByStatus(null) != null ? null : null;
                req.setAttribute("error", "志愿者账号或密码错误，或账号尚未通过审核");
                req.setAttribute("role", "volunteer");
                req.getRequestDispatcher("/login.jsp").forward(req, resp);
            }
        }
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        doPost(req, resp);
    }
}
