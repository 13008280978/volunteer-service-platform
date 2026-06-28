<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ page import="java.util.List, com.volunteer.po.*" %>
<% List<Points> pts=(List<Points>)request.getAttribute("points"); %>
<!DOCTYPE html><html><head><meta charset="UTF-8"><title>积分管理</title><link rel="stylesheet" href="${pageContext.request.contextPath}/css/style.css"></head>
<body><jsp:include page="../include/admin_header.jsp"/><div class="admin-content">
<h2>&#x2B50; 积分管理</h2>
<div class="card" style="margin-bottom:15px;"><div class="card-header"><h3>积分调整</h3></div><div class="card-body">
<form action="AdminPointsServlet" method="post" style="display:flex;gap:10px;flex-wrap:wrap;align-items:end;"><input type="hidden" name="action" value="adjust">
<div class="form-group" style="margin:0"><label>志愿者ID</label><input type="number" name="volId" class="form-control" required style="width:100px"></div>
<div class="form-group" style="margin:0"><label>类型</label><select name="pointType" class="form-control" style="width:120px"><option value="3">额外奖励</option><option value="5">调整</option></select></div>
<div class="form-group" style="margin:0"><label>积分值</label><input type="number" name="pointValue" class="form-control" required style="width:100px"></div>
<div class="form-group" style="margin:0"><label>说明</label><input type="text" name="pointDesc" class="form-control" style="width:200px"></div>
<button type="submit" class="btn btn-primary" style="height:38px">提交</button></form></div></div>
<div class="card"><div class="card-body"><table class="table"><thead><tr><th>志愿者</th><th>类型</th><th>积分</th><th>说明</th><th>余额</th><th>时间</th></tr></thead><tbody>
<%if(pts!=null){String[] pt={"","活动","签到","额外","兑换","调整"};for(Points p:pts){%><tr><td><%=p.getVolName()%></td><td><%=pt[p.getPointType()]%></td><td style="font-weight:bold;<%=p.getPointValue()>0?"color:#52c41a":"color:#f5222d"%>"><%=p.getPointValue()>0?"+":""%><%=p.getPointValue()%></td><td><%=p.getPointDesc()%></td><td><%=p.getPointBalance()%></td><td><%=new java.text.SimpleDateFormat("MM-dd HH:mm").format(p.getCreateTime())%></td></tr><%}}%>
</tbody></table></div></div></div></body></html>