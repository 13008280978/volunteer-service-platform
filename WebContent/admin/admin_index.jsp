<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ page import="com.volunteer.service.*, com.volunteer.service.impl.*, com.volunteer.po.*, java.util.List" %>
<% VolunteerService volSvc = new VolunteerServiceImpl(); ActivityService actSvc = new ActivityServiceImpl(); ExperienceService expSvc = new ExperienceServiceImpl(); List<Volunteer> ranking = volSvc.getPointsRanking(); %>
<!DOCTYPE html><html><head><meta charset="UTF-8"><title>管理后台</title><link rel="stylesheet" href="${pageContext.request.contextPath}/css/style.css"></head>
<body>
<jsp:include page="../include/admin_header.jsp"/>
<div class="admin-content">
<h2 style="margin-bottom:20px;">&#x1F3E0; 控制台概览</h2>
<div class="row">
<div class="col-3"><div class="card"><div class="card-body stat-card"><div class="stat-icon blue">&#x1F465;</div><div class="stat-number"><%=volSvc.countAll()%></div><div class="stat-label">志愿者总数</div></div></div></div>
<div class="col-3"><div class="card"><div class="card-body stat-card"><div class="stat-icon green">&#x2705;</div><div class="stat-number"><%=volSvc.countByStatus(1)%></div><div class="stat-label">已审核通过</div></div></div></div>
<div class="col-3"><div class="card"><div class="card-body stat-card"><div class="stat-icon orange">&#x1F4CB;</div><div class="stat-number"><%=actSvc.countAll()%></div><div class="stat-label">活动总数</div></div></div></div>
<div class="col-3"><div class="card"><div class="card-body stat-card"><div class="stat-icon red">&#x23F1;</div><div class="stat-number"><%=actSvc.sumTotalHours()%></div><div class="stat-label">累计服务时长(h)</div></div></div></div>
</div>
<div class="row" style="margin-top:20px;">
<div class="col-4"><div class="card"><div class="card-body stat-card"><div class="stat-icon purple">&#x23F3;</div><div class="stat-number"><%=volSvc.countByStatus(0)%></div><div class="stat-label">待审核志愿者</div></div></div></div>
<div class="col-4"><div class="card"><div class="card-body stat-card"><div class="stat-icon orange">&#x1F4DD;</div><div class="stat-number"><%=expSvc.countByStatus(0)%></div><div class="stat-label">待审核心得</div></div></div></div>
<div class="col-4"><div class="card"><div class="card-body stat-card"><div class="stat-icon blue">&#x270D;</div><div class="stat-number"><%=expSvc.countAll()%></div><div class="stat-label">心得总数</div></div></div></div>
</div>
<div class="row" style="margin-top:20px;">
<div class="col-6">
<div class="card"><div class="card-header"><h3>&#x2B50; 积分排行榜</h3></div><div class="card-body" style="padding:0;"><ul class="ranking-list">
<% if (ranking != null) { int idx = 0; for (Volunteer v : ranking) { idx++; %>
<li><span class="ranking-num <%=idx<=3?"top"+idx:""%>"><%=idx%></span><div class="ranking-info"><div class="name"><%=v.getVolName()%></div><div class="hours">服务 <%=v.getVolTotalHours()%> 小时</div></div><div class="ranking-points"><%=v.getVolTotalPoints()%> 分</div></li>
<% } } %>
</ul></div></div>
</div>
<div class="col-6">
<div class="card"><div class="card-header"><h3>&#x1F4CC; 快捷操作</h3></div><div class="card-body">
<p style="margin:10px 0;"><a href="${pageContext.request.contextPath}/admin/AdminActivityServlet?action=add" class="btn btn-primary">+ 发布新活动</a></p>
<p style="margin:10px 0;"><a href="${pageContext.request.contextPath}/admin/AdminVolunteerServlet?status=0" class="btn btn-warning">审核志愿者 (<%=volSvc.countByStatus(0)%>)</a></p>
<p style="margin:10px 0;"><a href="${pageContext.request.contextPath}/admin/AdminEnrollServlet" class="btn btn-info">管理报名</a></p>
<p style="margin:10px 0;"><a href="${pageContext.request.contextPath}/admin/AdminExperienceServlet?status=0" class="btn btn-success">审核心得 (<%=expSvc.countByStatus(0)%>)</a></p>
</div></div>
</div>
</div>
</div>
</body></html>
