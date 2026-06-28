<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ page import="java.util.List, com.volunteer.po.*" %>
<% List<ActivityType> types=(List<ActivityType>)request.getAttribute("types"); %>
<!DOCTYPE html><html><head><meta charset="UTF-8"><title>类型管理</title><link rel="stylesheet" href="${pageContext.request.contextPath}/css/style.css"></head>
<body><jsp:include page="../include/admin_header.jsp"/><div class="admin-content">
<h2>&#x1F3F7; 活动类型管理</h2>
<div class="row"><div class="col-4"><div class="card"><div class="card-header"><h3>添加类型</h3></div><div class="card-body">
<form action="AdminTypeServlet" method="post"><input type="hidden" name="action" value="add">
<div class="form-group"><label>名称</label><input type="text" name="typeName" class="form-control" required></div>
<div class="form-group"><label>描述</label><input type="text" name="typeDesc" class="form-control"></div>
<div class="form-group"><label>排序</label><input type="number" name="typeSort" class="form-control" value="0"></div>
<button type="submit" class="btn btn-primary btn-block">添加</button></form></div></div></div>
<div class="col-8"><div class="card"><div class="card-body"><table class="table"><thead><tr><th>ID</th><th>名称</th><th>描述</th><th>排序</th><th>状态</th><th>操作</th></tr></thead><tbody>
<%if(types!=null)for(ActivityType t:types){%><tr><td><%=t.getTypeId()%></td><td><%=t.getTypeName()%></td><td><%=t.getTypeDesc()%></td><td><%=t.getTypeSort()%></td><td><%=t.getTypeStatus()==1?"启用":"禁用"%></td><td><a href="javascript:confirmAction('切换状态?','AdminTypeServlet?action=status&typeId=<%=t.getTypeId()%>&typeStatus=<%=t.getTypeStatus()==1?0:1%>')" class="btn btn-sm btn-warning"><%=t.getTypeStatus()==1?"禁用":"启用"%></a></td></tr><%}%>
</tbody></table></div></div></div></div></div></body></html>