package com.volunteer.controller;

import com.volunteer.po.Volunteer;
import com.volunteer.service.VolunteerService;
import com.volunteer.service.impl.VolunteerServiceImpl;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.*;
import java.io.IOException;

@WebServlet("/VolunteerServlet")
public class VolunteerServlet extends HttpServlet {
    private VolunteerService volunteerService = new VolunteerServiceImpl();

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String action = req.getParameter("action");
        if ("info".equals(action)) {
            HttpSession session = req.getSession();
            Volunteer vol = (Volunteer) session.getAttribute("volunteer");
            if (vol != null) {
                vol = volunteerService.findById(vol.getVolId());
                session.setAttribute("volunteer", vol);
                req.setAttribute("volunteer", vol);
            }
            req.getRequestDispatcher("/volunteer/volunteer_info.jsp").forward(req, resp);
        } else if ("logout".equals(action)) {
            req.getSession().invalidate();
            resp.sendRedirect(req.getContextPath() + "/index.jsp");
        }
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String action = req.getParameter("action");
        HttpSession session = req.getSession();
        Volunteer vol = (Volunteer) session.getAttribute("volunteer");
        
        if ("update".equals(action) && vol != null) {
            vol.setVolName(req.getParameter("volName"));
            vol.setVolPhone(req.getParameter("volPhone"));
            vol.setVolEmail(req.getParameter("volEmail"));
            vol.setVolAddress(req.getParameter("volAddress"));
            vol.setVolEmergencyContact(req.getParameter("volEmergencyContact"));
            vol.setVolEmergencyPhone(req.getParameter("volEmergencyPhone"));
            vol.setVolSkills(req.getParameter("volSkills"));
            String volGender = req.getParameter("volGender");
            String volAge = req.getParameter("volAge");
            if (volGender != null && !volGender.isEmpty()) vol.setVolGender(Integer.parseInt(volGender));
            if (volAge != null && !volAge.isEmpty()) vol.setVolAge(Integer.parseInt(volAge));
            
            boolean success = volunteerService.updateVolunteer(vol);
            if (success) {
                session.setAttribute("volunteer", vol);
                req.setAttribute("success", "个人信息更新成功！");
            } else {
                req.setAttribute("error", "更新失败，请重试");
            }
            req.setAttribute("volunteer", vol);
            req.getRequestDispatcher("/volunteer/volunteer_info.jsp").forward(req, resp);
        } else if ("password".equals(action) && vol != null) {
            String oldPwd = req.getParameter("oldPassword");
            String newPwd = req.getParameter("newPassword");
            String confirmPwd = req.getParameter("confirmPassword");
            if (!vol.getVolPassword().equals(oldPwd)) {
                req.setAttribute("error", "原密码错误");
            } else if (!newPwd.equals(confirmPwd)) {
                req.setAttribute("error", "两次新密码不一致");
            } else {
                vol.setVolPassword(newPwd);
                volunteerService.updateVolunteer(vol);
                session.setAttribute("volunteer", vol);
                req.setAttribute("success", "密码修改成功！");
            }
            req.setAttribute("volunteer", vol);
            req.getRequestDispatcher("/volunteer/volunteer_info.jsp").forward(req, resp);
        }
    }
}
