<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ page import="com.volunteer.po.*" %>
<%
    Activity activity = (Activity) request.getAttribute("activity");
    Volunteer vol = (Volunteer) session.getAttribute("volunteer");
%>
<!DOCTYPE html>
<html><head><meta charset="UTF-8"><title>活动报名 - 志愿者服务平台</title><link rel="stylesheet" href="${pageContext.request.contextPath}/css/style.css"></head>
<body>
<jsp:include page="../include/header.jsp"/>
<div class="container main-content">
    <div class="breadcrumb"><a href="index.jsp">首页</a><span>&gt;</span><a href="${pageContext.request.contextPath}/ActivityServlet">志愿活动</a><span>&gt;</span>活动报名</div>
    <div class="card" style="max-width:600px;margin:0 auto;">
        <div class="card-header"><h3>&#x270B; 活动报名确认</h3></div>
        <div class="card-body">
            <% if (activity != null) { %>
            <div style="margin-bottom:20px;padding:15px;background:#f9fbff;border-radius:8px;">
                <h4><%=activity.getActTitle()%></h4>
                <p style="color:#666;margin:8px 0;">&#x1F4CD; <%=activity.getActLocation()%></p>
                <p style="color:#666;">&#x1F552; <%=new java.text.SimpleDateFormat("yyyy-MM-dd HH:mm").format(activity.getActStartTime())%></p>
                <p style="color:#666;">&#x2B50; 积分奖励：<%=activity.getActPoints()%>积分</p>
            </div>
            <form action="${pageContext.request.contextPath}/EnrollServlet" method="post" data-validate>
                <input type="hidden" name="actId" value="<%=activity.getActId()%>">
                <div class="form-group">
                    <label>报名原因</label>
                    <textarea name="enrollReason" class="form-control" rows="4" placeholder="请简要说明您参加此次活动的理由和期望..." required></textarea>
                </div>
                <div class="form-group" style="text-align:center;">
                    <a href="${pageContext.request.contextPath}/ActivityDetailServlet?actId=<%=activity.getActId()%>" class="btn btn-outline">返回</a>
                    <button type="submit" class="btn btn-primary" style="margin-left:15px;">确认报名</button>
                </div>
            </form>
            <% } %>
        </div>
    </div>
</div>
<jsp:include page="../include/footer.jsp"/>
</body></html>
