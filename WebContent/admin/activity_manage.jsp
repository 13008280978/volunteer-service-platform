<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ page import="java.util.List, com.volunteer.po.*" %>
<% List<Activity> activities = (List<Activity>) request.getAttribute("activities"); %>
<!DOCTYPE html><html><head><meta charset="UTF-8"><title>活动管理</title><link rel="stylesheet" href="${pageContext.request.contextPath}/css/style.css"></head>
<body><jsp:include page="../include/admin_header.jsp"/><div class="admin-content">
<div style="display:flex;justify-content:space-between;align-items:center;margin-bottom:20px;"><h2>&#x1F4CB; 活动管理</h2><a href="AdminActivityServlet?action=add" class="btn btn-primary">+ 发布活动</a></div>
<div class="card"><div class="card-body"><table class="table"><thead><tr><th>ID</th><th>名称</th><th>类型</th><th>时间</th><th>人数</th><th>状态</th><th>操作</th></tr></thead><tbody>
<% if(activities!=null){String[] sn={"草稿","报名中","进行中","已结束","已取消"};for(Activity a:activities){%>
<tr><td><%=a.getActId()%></td><td><%=a.getActTitle()%></td><td><%=a.getTypeName()%></td><td><%=new java.text.SimpleDateFormat("MM-dd HH:mm").format(a.getActStartTime())%></td><td><%=a.getActCurrentPeople()%>/<%=a.getActMaxPeople()%></td><td><span class="activity-status"><%=sn[a.getActStatus()]%></span></td><td><a href="AdminActivityServlet?action=edit&actId=<%=a.getActId()%>" class="btn btn-sm btn-info">编辑</a> <a href="javascript:confirmAction('确认取消?','AdminActivityServlet?action=status&actId=<%=a.getActId()%>&status=4')" class="btn btn-sm btn-danger">取消</a></td></tr>
<%}}%></tbody></table></div></div></div></body></html>