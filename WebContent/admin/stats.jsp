<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ page import="java.util.List, com.volunteer.po.*" %>
<% Integer tv=(Integer)request.getAttribute("totalVolunteers"); Integer av=(Integer)request.getAttribute("approvedVolunteers"); Integer pv=(Integer)request.getAttribute("pendingVolunteers"); Integer ta=(Integer)request.getAttribute("totalActivities"); Double th=(Double)request.getAttribute("totalHours"); Integer te=(Integer)request.getAttribute("totalExperiences"); Integer pe=(Integer)request.getAttribute("pendingExperiences"); List<Volunteer> pr=(List<Volunteer>)request.getAttribute("pointsRanking"); %>
<!DOCTYPE html><html><head><meta charset="UTF-8"><title>数据统计</title><link rel="stylesheet" href="${pageContext.request.contextPath}/css/style.css"></head>
<body><jsp:include page="../include/admin_header.jsp"/><div class="admin-content">
<h2>&#x1F4CA; 数据统计</h2>
<div class="row">
<div class="col-3"><div class="card"><div class="card-body stat-card"><div class="stat-icon blue">&#x1F465;</div><div class="stat-number"><%=tv%></div><div class="stat-label">志愿者总数</div></div></div></div>
<div class="col-3"><div class="card"><div class="card-body stat-card"><div class="stat-icon green">&#x2705;</div><div class="stat-number"><%=av%></div><div class="stat-label">审核通过</div></div></div></div>
<div class="col-3"><div class="card"><div class="card-body stat-card"><div class="stat-icon orange">&#x23F3;</div><div class="stat-number"><%=pv%></div><div class="stat-label">待审核</div></div></div></div>
<div class="col-3"><div class="card"><div class="card-body stat-card"><div class="stat-icon red">&#x1F4CB;</div><div class="stat-number"><%=ta%></div><div class="stat-label">活动总数</div></div></div></div>
</div>
<div class="row" style="margin-top:20px;">
<div class="col-4"><div class="card"><div class="card-body stat-card"><div class="stat-icon purple">&#x23F1;</div><div class="stat-number"><%=th!=null?String.format("%.1f",th):"0"%></div><div class="stat-label">总服务时长(小时)</div></div></div></div>
<div class="col-4"><div class="card"><div class="card-body stat-card"><div class="stat-icon orange">&#x270D;</div><div class="stat-number"><%=te%></div><div class="stat-label">心得总数</div></div></div></div>
<div class="col-4"><div class="card"><div class="card-body stat-card"><div class="stat-icon blue">&#x1F4DD;</div><div class="stat-number"><%=pe%></div><div class="stat-label">待审心得</div></div></div></div>
</div>
<div class="card" style="margin-top:20px;"><div class="card-header"><h3>&#x2B50; 积分排行榜 TOP10</h3></div><div class="card-body" style="padding:0;">
<table class="table"><thead><tr><th>排名</th><th>姓名</th><th>积分</th><th>服务时长</th></tr></thead><tbody>
<%if(pr!=null){int i=0;for(Volunteer v:pr){i++;%><tr><td><span class="ranking-num <%=i<=3?"top"+i:""%>"><%=i%></span></td><td><%=v.getVolName()%></td><td style="font-weight:bold;color:#fa8c16"><%=v.getVolTotalPoints()%></td><td><%=v.getVolTotalHours()%>h</td></tr><%}}%>
</tbody></table></div></div>
</div></body></html>