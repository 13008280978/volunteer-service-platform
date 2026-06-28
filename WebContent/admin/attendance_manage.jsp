<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ page import="java.util.List, com.volunteer.po.*" %>
<% List<Attendance> atts=(List<Attendance>)request.getAttribute("attendances"); %>
<!DOCTYPE html><html><head><meta charset="UTF-8"><title>签到管理</title><link rel="stylesheet" href="${pageContext.request.contextPath}/css/style.css"></head>
<body><jsp:include page="../include/admin_header.jsp"/><div class="admin-content">
<h2>&#x2705; 签到管理</h2>
<div class="card"><div class="card-body"><table class="table"><thead><tr><th>ID</th><th>志愿者</th><th>活动</th><th>签到</th><th>签退</th><th>时长</th><th>状态</th><th>操作</th></tr></thead><tbody>
<%if(atts!=null)for(Attendance a:atts){%><tr><td><%=a.getAttendId()%></td><td><%=a.getVolName()%></td><td><%=a.getActTitle()%></td><td><%=a.getCheckinTime()!=null?new java.text.SimpleDateFormat("MM-dd HH:mm").format(a.getCheckinTime()):"-"%></td><td><%=a.getCheckoutTime()!=null?new java.text.SimpleDateFormat("HH:mm").format(a.getCheckoutTime()):"-"%></td><td><%=a.getActualHours()%>h</td><td><%=a.getCheckinStatus()==1?"已签到":a.getCheckinStatus()==2?"已签退":a.getCheckinStatus()==3?"已确认":"未签到"%></td><td><%if(a.getCheckinStatus()==2){%><form action="AdminAttendanceServlet" method="post" style="display:inline"><input type="hidden" name="action" value="confirm"><input type="hidden" name="attendId" value="<%=a.getAttendId()%>"><button class="btn btn-sm btn-success">确认</button></form><%}%></td></tr><%}%>
</tbody></table></div></div></div></body></html>