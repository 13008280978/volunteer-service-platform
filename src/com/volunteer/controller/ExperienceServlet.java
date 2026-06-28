package com.volunteer.controller;

import com.volunteer.po.Experience;
import com.volunteer.po.Volunteer;
import com.volunteer.service.ExperienceService;
import com.volunteer.service.impl.ExperienceServiceImpl;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.*;
import java.io.IOException;
import java.util.List;

@WebServlet("/ExperienceServlet")
public class ExperienceServlet extends HttpServlet {
    private ExperienceService experienceService = new ExperienceServiceImpl();

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String action = req.getParameter("action");
        
        if ("write".equals(action)) {
            String actId = req.getParameter("actId");
            req.setAttribute("actId", actId);
            req.getRequestDispatcher("/volunteer/experience_write.jsp").forward(req, resp);
        } else if ("list".equals(action)) {
            String pageStr = req.getParameter("page");
            int page = pageStr != null ? Integer.parseInt(pageStr) : 1;
            List<Experience> experiences = experienceService.findApproved(page, 10);
            int total = experienceService.countByStatus(1);
            req.setAttribute("experiences", experiences);
            req.setAttribute("currentPage", page);
            req.setAttribute("totalPages", (total + 9) / 10);
            req.getRequestDispatcher("/volunteer/experience_list.jsp").forward(req, resp);
        } else if ("detail".equals(action)) {
            String expIdStr = req.getParameter("expId");
            if (expIdStr != null) {
                Integer expId = Integer.parseInt(expIdStr);
                Experience exp = experienceService.findById(expId);
                experienceService.incrementViews(expId);
                req.setAttribute("experience", exp);
                req.getRequestDispatcher("/volunteer/experience_list.jsp").forward(req, resp);
            }
        } else if ("my".equals(action)) {
            HttpSession session = req.getSession();
            Volunteer vol = (Volunteer) session.getAttribute("volunteer");
            if (vol == null) {
                resp.sendRedirect(req.getContextPath() + "/login.jsp?role=volunteer");
                return;
            }
            List<Experience> experiences = experienceService.findByVolId(vol.getVolId());
            req.setAttribute("experiences", experiences);
            req.setAttribute("myExperiences", true);
            req.getRequestDispatcher("/volunteer/experience_list.jsp").forward(req, resp);
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
        
        String expTitle = req.getParameter("expTitle");
        String expContent = req.getParameter("expContent");
        String actIdStr = req.getParameter("actId");
        
        Experience experience = new Experience();
        experience.setVolId(vol.getVolId());
        experience.setExpTitle(expTitle);
        experience.setExpContent(expContent);
        if (actIdStr != null && !actIdStr.isEmpty()) {
            experience.setActId(Integer.parseInt(actIdStr));
        }
        
        boolean success = experienceService.addExperience(experience);
        if (success) {
            req.setAttribute("success", "心得体会发布成功！请等待管理员审核。");
        } else {
            req.setAttribute("error", "发布失败，请重试。");
        }
        req.getRequestDispatcher("/volunteer/experience_write.jsp").forward(req, resp);
    }
}
