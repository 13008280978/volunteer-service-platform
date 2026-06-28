package com.volunteer.controller;
import com.volunteer.po.Volunteer; import com.volunteer.service.VolunteerService; import com.volunteer.service.impl.VolunteerServiceImpl;
import javax.servlet.ServletException; import javax.servlet.annotation.WebServlet;
import javax.servlet.http.*; import java.io.IOException;
@WebServlet("/RegisterServlet")
public class RegisterServlet extends HttpServlet {
    private VolunteerService volunteerService = new VolunteerServiceImpl();
    @Override protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String volName=req.getParameter("volName"); String volAccount=req.getParameter("volAccount");
        String volPassword=req.getParameter("volPassword"); String volConfirmPwd=req.getParameter("volConfirmPwd");
        if(!volPassword.equals(volConfirmPwd)){req.setAttribute("error","两次密码输入不一致");req.getRequestDispatcher("/register.jsp").forward(req,resp);return;}
        Volunteer volunteer=new Volunteer(); volunteer.setVolName(volName); volunteer.setVolAccount(volAccount); volunteer.setVolPassword(volPassword);
        String g=req.getParameter("volGender"); if(g!=null&&!g.isEmpty()) volunteer.setVolGender(Integer.parseInt(g));
        String a=req.getParameter("volAge"); if(a!=null&&!a.isEmpty()) volunteer.setVolAge(Integer.parseInt(a));
        volunteer.setVolPhone(req.getParameter("volPhone")); volunteer.setVolEmail(req.getParameter("volEmail"));
        volunteer.setVolIdcard(req.getParameter("volIdcard")); volunteer.setVolAddress(req.getParameter("volAddress"));
        volunteer.setVolEmergencyContact(req.getParameter("volEmergencyContact")); volunteer.setVolEmergencyPhone(req.getParameter("volEmergencyPhone"));
        volunteer.setVolSkills(req.getParameter("volSkills"));
        boolean success=volunteerService.register(volunteer);
        if(success){req.setAttribute("success","注册成功！请等待管理员审核通过后登录。");req.getRequestDispatcher("/login.jsp").forward(req,resp);}
        else{req.setAttribute("error","注册失败，账号可能已存在");req.getRequestDispatcher("/register.jsp").forward(req,resp);}
    }
}
