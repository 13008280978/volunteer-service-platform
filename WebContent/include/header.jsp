<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ page import="com.volunteer.po.*" %>
<% Volunteer headerVol = (Volunteer) session.getAttribute("volunteer"); %>
<nav class="navbar">
    <div class="container">
        <a class="navbar-brand" href="${pageContext.request.contextPath}/index.jsp">&#x1F91D; 社区志愿者服务平台</a>
        <ul class="navbar-nav">
            <li><a href="${pageContext.request.contextPath}/index.jsp">首页</a></li>
            <li><a href="${pageContext.request.contextPath}/ActivityServlet">志愿活动</a></li>
            <li><a href="${pageContext.request.contextPath}/ExperienceServlet?action=list">心得体会</a></li>
            <li><a href="${pageContext.request.contextPath}/NoticeServlet">公告通知</a></li>
            <% if (headerVol != null) { %>
            <li><a href="${pageContext.request.contextPath}/ServiceRecordServlet">服务记录</a></li>
            <li><a href="${pageContext.request.contextPath}/PointsServlet">积分中心</a></li>
            <% } %>
        </ul>
        <div class="navbar-right">
            <% if (headerVol != null) { %>
            <span class="user-info">欢迎，<%=headerVol.getVolName()%></span>
            <a href="${pageContext.request.contextPath}/VolunteerServlet?action=info" class="btn btn-sm btn-outline" style="color:#fff;border-color:rgba(255,255,255,0.5);">个人中心</a>
            <a href="${pageContext.request.contextPath}/VolunteerServlet?action=logout" class="btn btn-sm btn-danger" style="margin-left:8px;">退出</a>
            <% } else { %>
            <a href="${pageContext.request.contextPath}/login.jsp?role=volunteer" class="btn btn-sm" style="background:rgba(255,255,255,0.2);color:#fff;">登录</a>
            <a href="${pageContext.request.contextPath}/register.jsp" class="btn btn-sm btn-warning" style="margin-left:8px;">注册</a>
            <% } %>
        </div>
    </div>
</nav>
