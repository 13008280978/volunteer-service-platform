<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ page import="com.volunteer.po.*" %>
<% Auser admin = (Auser) session.getAttribute("admin"); %>
<nav class="navbar admin-navbar">
    <div style="display:flex;align-items:center;justify-content:space-between;width:100%;max-width:100%;padding:0 20px;">
        <a class="navbar-brand" href="${pageContext.request.contextPath}/admin/admin_index.jsp">&#x1F6E0; 志愿者平台管理后台</a>
        <div class="navbar-right">
            <span class="user-info">管理员：<%=admin!=null?admin.getAdminName():""%></span>
            <a href="${pageContext.request.contextPath}/admin/AdminLogoutServlet" class="btn btn-sm btn-danger">退出登录</a>
        </div>
    </div>
</nav>
<div class="admin-sidebar">
    <div class="nav-item"><a href="${pageContext.request.contextPath}/admin/admin_index.jsp">&#x1F3E0; 控制台</a></div>
    <div class="nav-item"><a href="${pageContext.request.contextPath}/admin/AdminActivityServlet">&#x1F4CB; 活动管理</a></div>
    <div class="nav-item"><a href="${pageContext.request.contextPath}/admin/AdminTypeServlet">&#x1F3F7; 类型管理</a></div>
    <div class="nav-item"><a href="${pageContext.request.contextPath}/admin/AdminVolunteerServlet">&#x1F465; 志愿者管理</a></div>
    <div class="nav-item"><a href="${pageContext.request.contextPath}/admin/AdminEnrollServlet">&#x270B; 报名管理</a></div>
    <div class="nav-item"><a href="${pageContext.request.contextPath}/admin/AdminAttendanceServlet">&#x2705; 签到管理</a></div>
    <div class="nav-item"><a href="${pageContext.request.contextPath}/admin/AdminPointsServlet">&#x2B50; 积分管理</a></div>
    <div class="nav-item"><a href="${pageContext.request.contextPath}/admin/AdminExperienceServlet">&#x270D; 心得审核</a></div>
    <div class="nav-item"><a href="${pageContext.request.contextPath}/admin/AdminNoticeServlet">&#x1F4E2; 公告管理</a></div>
    <div class="nav-item"><a href="${pageContext.request.contextPath}/admin/AdminStatsServlet">&#x1F4CA; 数据统计</a></div>
</div>
