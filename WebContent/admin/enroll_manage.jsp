<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ page import="java.util.List, com.volunteer.po.*" %>
<% List<Enrollment> enrolls=(List<Enrollment>)request.getAttribute("enrollments"); %>
<!DOCTYPE html><html><head><meta charset="UTF-8"><title>报名管理</title><link rel="stylesheet" href="${pageContext.request.contextPath}/css/style.css"></head>
<body><jsp:include page="../include/admin_header.jsp"/><div class="admin-content">
<h2>&#x270B; 报名管理</h2>
<div class="card"><div class="card-body"><table class="table"><thead><tr><th>ID</th><th>志愿者</th><th>电话</th><th>活动</th><th>报名时间</th><th>状态</th><th>操作</th></tr></thead><tbody>
<%if(enrolls!=null){String[] es={"待审核","已通过","已拒绝","已取消"};for(Enrollment e:enrolls){%><tr><td><%=e.getEnrollId()%></td><td><%=e.getVolName()%></td><td><%=e.getVolPhone()%></td><td><%=e.getActTitle()%></td><td><%=new java.text.SimpleDateFormat("MM-dd HH:mm").format(e.getEnrollTime())%></td><td><%=es[e.getEnrollStatus()]%></td><td><%if(e.getEnrollStatus()==0){%><a href="javascript:auditEnroll(<%=e.getEnrollId()%>,1)" class="btn btn-sm btn-success">通过</a><a href="javascript:auditEnroll(<%=e.getEnrollId()%>,2)" class="btn btn-sm btn-danger">拒绝</a><%}%></td></tr><%}}%>
</tbody></table></div></div></div><script src="${pageContext.request.contextPath}/js/main.js"></script></body></html>