<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ page import="java.util.List, com.volunteer.po.*" %>
<%
    List<ServiceRecord> records = (List<ServiceRecord>) request.getAttribute("records");
    Double totalHours = (Double) request.getAttribute("totalHours");
    Integer totalCount = (Integer) request.getAttribute("totalCount");
%>
<!DOCTYPE html>
<html><head><meta charset="UTF-8"><title>我的服务记录 - 志愿者服务平台</title><link rel="stylesheet" href="${pageContext.request.contextPath}/css/style.css"></head>
<body>
<jsp:include page="../include/header.jsp"/>
<div class="container main-content">
    <div class="breadcrumb"><a href="index.jsp">首页</a><span>&gt;</span>我的服务记录</div>
    <div class="points-summary">
        <div class="points-box hours"><div class="num"><%=totalHours!=null?totalHours:0%></div><div class="label">累计服务时长（小时）</div></div>
        <div class="points-box count"><div class="num"><%=totalCount!=null?totalCount:0%></div><div class="label">服务次数</div></div>
    </div>
    <div class="card">
        <div class="card-header"><h3>&#x1F4CB; 服务记录明细</h3></div>
        <div class="card-body">
            <% if (records != null && !records.isEmpty()) { %>
            <table class="table">
                <thead><tr><th>服务日期</th><th>活动名称</th><th>服务时长</th><th>服务内容</th><th>服务评价</th></tr></thead>
                <tbody>
                <% for (ServiceRecord r : records) { %>
                <tr>
                    <td><%=r.getServiceDate()!=null?new java.text.SimpleDateFormat("yyyy-MM-dd").format(r.getServiceDate()):"-"%></td>
                    <td><%=r.getActTitle()%></td>
                    <td><%=r.getServiceHours()%>小时</td>
                    <td><%=r.getServiceContent()%></td>
                    <td><%=r.getServiceEvaluation()!=null?r.getServiceEvaluation():"-"%></td>
                </tr>
                <% } %>
                </tbody>
            </table>
            <% } else { %>
            <div class="empty-state"><p>暂无服务记录，快去参加活动吧！</p></div>
            <% } %>
        </div>
    </div>
</div>
<jsp:include page="../include/footer.jsp"/>
</body></html>
