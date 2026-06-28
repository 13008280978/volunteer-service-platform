package com.volunteer.controller;
import com.volunteer.po.ServiceRecord; import com.volunteer.po.Volunteer;
import com.volunteer.service.ServiceRecordService; import com.volunteer.service.impl.ServiceRecordServiceImpl;
import javax.servlet.ServletException; import javax.servlet.annotation.WebServlet;
import javax.servlet.http.*; import java.io.IOException; import java.util.List;
@WebServlet("/ServiceRecordServlet")
public class ServiceRecordServlet extends HttpServlet {
    private ServiceRecordService serviceRecordService = new ServiceRecordServiceImpl();
    @Override protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        HttpSession session=req.getSession(); Volunteer vol=(Volunteer)session.getAttribute("volunteer");
        if(vol==null){resp.sendRedirect(req.getContextPath()+"/login.jsp?role=volunteer");return;}
        List<ServiceRecord> records=serviceRecordService.findByVolId(vol.getVolId());
        Double totalHours=serviceRecordService.sumHoursByVolId(vol.getVolId());
        int totalCount=serviceRecordService.countByVolId(vol.getVolId());
        req.setAttribute("records",records); req.setAttribute("totalHours",totalHours); req.setAttribute("totalCount",totalCount);
        req.getRequestDispatcher("/volunteer/my_records.jsp").forward(req,resp);
    }
}
