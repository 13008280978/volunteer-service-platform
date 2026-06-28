package com.volunteer.controller;
import com.volunteer.po.Points; import com.volunteer.po.Volunteer;
import com.volunteer.service.PointsService; import com.volunteer.service.VolunteerService;
import com.volunteer.service.impl.PointsServiceImpl; import com.volunteer.service.impl.VolunteerServiceImpl;
import javax.servlet.ServletException; import javax.servlet.annotation.WebServlet;
import javax.servlet.http.*; import java.io.IOException; import java.util.List;
@WebServlet("/PointsServlet")
public class PointsServlet extends HttpServlet {
    private PointsService pointsService = new PointsServiceImpl();
    private VolunteerService volunteerService = new VolunteerServiceImpl();
    @Override protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        HttpSession session = req.getSession(); Volunteer vol = (Volunteer) session.getAttribute("volunteer");
        if (vol == null) { resp.sendRedirect(req.getContextPath() + "/login.jsp?role=volunteer"); return; }
        vol = volunteerService.findById(vol.getVolId()); session.setAttribute("volunteer", vol);
        List<Points> pointRecords = pointsService.findByVolId(vol.getVolId());
        Integer balance = pointsService.getBalance(vol.getVolId());
        req.setAttribute("pointRecords", pointRecords); req.setAttribute("balance", balance);
        req.setAttribute("volunteer", vol); req.getRequestDispatcher("/volunteer/my_points.jsp").forward(req, resp);
    }
}
