<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ page import="java.util.List, com.volunteer.po.*" %>
<% List<Notice> notices=(List<Notice>)request.getAttribute("notices"); %>
<!DOCTYPE html><html><head><meta charset="UTF-8"><title>公告管理</title><link rel="stylesheet" href="${pageContext.request.contextPath}/css/style.css"></head>
<body><jsp:include page="../include/admin_header.jsp"/><div class="admin-content">
<h2>&#x1F4E2; 公告管理</h2>
<div class="row"><div class="col-4"><div class="card"><div class="card-header"><h3>发布公告</h3></div><div class="card-body">
<form action="AdminNoticeServlet" method="post"><input type="hidden" name="action" value="add">
<div class="form-group"><label>标题*</label><input type="text" name="noticeTitle" class="form-control" required></div>
<div class="form-group"><label>类型</label><select name="noticeType" class="form-control"><option value="1">系统通知</option><option value="2">活动公告</option><option value="3">政策通知</option></select></div>
<div class="form-group"><label>优先级</label><select name="noticePriority" class="form-control"><option value="0">普通</option><option value="1">重要</option><option value="2">紧急</option></select></div>
<div class="form-group"><label>内容*</label><textarea name="noticeContent" class="form-control" rows="6" required></textarea></div>
<button type="submit" class="btn btn-primary btn-block">发布</button></form></div></div></div>
<div class="col-8"><div class="card"><div class="card-body"><table class="table"><thead><tr><th>标题</th><th>类型</th><th>优先级</th><th>发布者</th><th>状态</th><th>时间</th><th>操作</th></tr></thead><tbody>
<%if(notices!=null){String[] nt={"","系统","活动","政策"};for(Notice n:notices){%><tr><td><%=n.getNoticeTitle()%></td><td><%=nt[n.getNoticeType()]%></td><td><%={"普通","重要","紧急"}[n.getNoticePriority()]%></td><td><%=n.getAdminName()%></td><td><%=n.getNoticeStatus()==1?"已发布":"已撤回"%></td><td><%=new java.text.SimpleDateFormat("MM-dd").format(n.getCreateTime())%></td><td><a href="javascript:confirmAction('撤回?','AdminNoticeServlet?action=status&noticeId=<%=n.getNoticeId()%>&noticeStatus=2')" class="btn btn-sm btn-danger">撤回</a></td></tr><%}}%>
</tbody></table></div></div></div></div></div></body></html>