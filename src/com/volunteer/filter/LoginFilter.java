package com.volunteer.filter;

import javax.servlet.*;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

@WebFilter(filterName = "loginFilter")
public class LoginFilter implements Filter {
    @Override
    public void init(FilterConfig filterConfig) throws ServletException {}
    
    @Override
    public void doFilter(ServletRequest req, ServletResponse resp, FilterChain chain) 
            throws IOException, ServletException {
        HttpServletRequest request = (HttpServletRequest) req;
        HttpServletResponse response = (HttpServletResponse) resp;
        String uri = request.getRequestURI();
        
        // Allow public access
        if (uri.contains("/login") || uri.contains("/register") || uri.contains("/index") 
            || uri.contains("/css/") || uri.contains("/js/") || uri.contains("/images/")
            || uri.contains("/ActivityServlet") || uri.contains("/ActivityDetailServlet")
            || uri.contains("/NoticeServlet") || uri.contains("/ExperienceServlet")
            || uri.endsWith(".css") || uri.endsWith(".js") || uri.endsWith(".png") || uri.endsWith(".jpg")) {
            chain.doFilter(request, response);
            return;
        }
        
        // Check admin login
        if (uri.contains("/admin/")) {
            HttpSession session = request.getSession();
            if (session.getAttribute("admin") == null) {
                response.sendRedirect(request.getContextPath() + "/login.jsp?role=admin");
                return;
            }
        }
        
        // Check volunteer login for protected pages
        if (uri.contains("/EnrollServlet") || uri.contains("/AttendanceServlet") 
            || uri.contains("/ServiceRecordServlet") || uri.contains("/PointsServlet")
            || uri.contains("/volunteer/")) {
            HttpSession session = request.getSession();
            if (session.getAttribute("volunteer") == null) {
                response.sendRedirect(request.getContextPath() + "/login.jsp?role=volunteer");
                return;
            }
        }
        
        chain.doFilter(request, response);
    }
    
    @Override
    public void destroy() {}
}
