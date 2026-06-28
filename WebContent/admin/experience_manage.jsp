<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ page import="java.util.List, com.volunteer.po.*" %>
<% List<Experience> exps=(List<Experience>)request.getAttribute("experiences"); %>
<!DOCTYPE html><html><head><meta charset="UTF-8"><title>心得审核</title><link rel="stylesheet" href="${pageContext.request.contextPath}/css/style.css"></head>
<body><jsp:include page="../include/admin_header.jsp"/><div class="admin-content">
<h2>&#x270D; 心得体会审核</h2>
<div class="card"><div class="card-body"><table class="table"><thead><tr><th>ID</th><th>标题</th><th>作者</th><th>活动</th><th>状态</th><th>浏览</th><th>时间</th><th>操作</th></tr></thead><tbody>
<%if(exps!=null)for(Experience e:exps){%><tr><td><%=e.getExpId()%></td><td><%=e.getExpTitle()%></td><td><%=e.getVolName()%></td><td><%=e.getActTitle()!=null?e.getActTitle():"-"%></td><td><%=e.getExpStatus()==0?"<span style='color:#faad14'>待审核</span>":e.getExpStatus()==1?"<span style='color:#52c41a'>已通过</span>":"<span style='color:#f5222d'>已拒绝</span>"%></td><td><%=e.getExpViews()%></td><td><%=new java.text.SimpleDateFormat("MM-dd").format(e.getCreateTime())%></td><td><%if(e.getExpStatus()==0){%><a href="javascript:auditExperience(<%=e.getExpId()%>,1)" class="btn btn-sm btn-success">通过</a><a href="javascript:auditExperience(<%=e.getExpId()%>,2)" class="btn btn-sm btn-danger">拒绝</a><%}%></td></tr><%}%>
</tbody></table></div></div></div><script src="${pageContext.request.contextPath}/js/main.js"></script></body></html>