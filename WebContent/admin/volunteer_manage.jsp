<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ page import="java.util.List, com.volunteer.po.*" %>
<% List<Volunteer> vols=(List<Volunteer>)request.getAttribute("volunteers"); %>
<!DOCTYPE html><html><head><meta charset="UTF-8"><title>志愿者管理</title><link rel="stylesheet" href="${pageContext.request.contextPath}/css/style.css"></head>
<body><jsp:include page="../include/admin_header.jsp"/><div class="admin-content">
<h2>&#x1F465; 志愿者管理</h2>
<div class="card" style="margin-bottom:15px;"><div class="card-body"><form action="AdminVolunteerServlet" method="get" style="display:flex;gap:10px;">
<select name="status" class="form-control" style="width:150px;"><option value="">全部</option><option value="0">待审核</option><option value="1">已通过</option><option value="2">已拒绝</option></select>
<input type="text" name="keyword" class="form-control" style="width:200px;" placeholder="搜索姓名..." value="<%=request.getAttribute("keyword")!=null?request.getAttribute("keyword"):""%>">
<button type="submit" class="btn btn-primary">搜索</button></form></div></div>
<div class="card"><div class="card-body"><table class="table"><thead><tr><th>ID</th><th>姓名</th><th>电话</th><th>积分</th><th>时长</th><th>状态</th><th>注册时间</th><th>操作</th></tr></thead><tbody>
<%if(vols!=null)for(Volunteer v:vols){%><tr><td><%=v.getVolId()%></td><td><%=v.getVolName()%></td><td><%=v.getVolPhone()%></td><td><%=v.getVolTotalPoints()%></td><td><%=v.getVolTotalHours()%>h</td><td><%=v.getVolStatus()==0?"<span style='color:#faad14'>待审核</span>":v.getVolStatus()==1?"<span style='color:#52c41a'>已通过</span>":"<span style='color:#f5222d'>已拒绝</span>"%></td><td><%=new java.text.SimpleDateFormat("MM-dd").format(v.getCreateTime())%></td><td><%if(v.getVolStatus()==0){%><a href="javascript:auditVolunteer(<%=v.getVolId()%>,1)" class="btn btn-sm btn-success">通过</a><a href="javascript:auditVolunteer(<%=v.getVolId()%>,2)" class="btn btn-sm btn-danger">拒绝</a><%}%><a href="AdminPointsServlet?volId=<%=v.getVolId()%>" class="btn btn-sm btn-info">积分</a></td></tr><%}%>
</tbody></table></div></div></div><script src="${pageContext.request.contextPath}/js/main.js"></script></body></html>