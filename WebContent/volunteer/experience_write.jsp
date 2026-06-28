<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%
    Volunteer vol = (Volunteer) session.getAttribute("volunteer");
    String actId = (String) request.getAttribute("actId");
%>
<!DOCTYPE html>
<html><head><meta charset="UTF-8"><title>发布心得 - 志愿者服务平台</title><link rel="stylesheet" href="${pageContext.request.contextPath}/css/style.css"></head>
<body>
<jsp:include page="../include/header.jsp"/>
<div class="container main-content">
    <div class="breadcrumb"><a href="index.jsp">首页</a><span>&gt;</span><a href="${pageContext.request.contextPath}/ExperienceServlet?action=list">心得体会</a><span>&gt;</span>发布心得</div>
    <div class="card" style="max-width:700px;margin:0 auto;">
        <div class="card-header"><h3>&#x270D; 发布心得体会</h3></div>
        <div class="card-body">
            <% if (request.getAttribute("success") != null) { %>
            <div class="alert alert-success"><%=request.getAttribute("success")%></div>
            <% } %>
            <% if (request.getAttribute("error") != null) { %>
            <div class="alert alert-danger"><%=request.getAttribute("error")%></div>
            <% } %>
            <form action="${pageContext.request.contextPath}/ExperienceServlet" method="post" data-validate>
                <div class="form-group">
                    <label>标题 *</label>
                    <input type="text" name="expTitle" class="form-control" required placeholder="请输入心得标题" maxlength="100">
                </div>
                <% if (actId != null) { %>
                <input type="hidden" name="actId" value="<%=actId%>">
                <% } %>
                <div class="form-group">
                    <label>心得体会内容 *</label>
                    <textarea name="expContent" class="form-control" rows="10" required placeholder="请分享您的志愿服务感悟和体会..."></textarea>
                </div>
                <div class="form-group" style="text-align:center;">
                    <a href="${pageContext.request.contextPath}/ExperienceServlet?action=list" class="btn btn-outline">返回</a>
                    <button type="submit" class="btn btn-primary" style="margin-left:15px;">发布心得</button>
                </div>
            </form>
        </div>
    </div>
</div>
<jsp:include page="../include/footer.jsp"/>
</body></html>
