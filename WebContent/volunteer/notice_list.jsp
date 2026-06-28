<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ page import="java.util.List, com.volunteer.po.*" %>
<%
    List<Notice> notices = (List<Notice>) request.getAttribute("notices");
    Notice notice = (Notice) request.getAttribute("notice");
    Integer currentPage = (Integer) request.getAttribute("currentPage");
%>
<!DOCTYPE html>
<html><head><meta charset="UTF-8"><title>公告通知 - 志愿者服务平台</title><link rel="stylesheet" href="${pageContext.request.contextPath}/css/style.css"></head>
<body>
<jsp:include page="../include/header.jsp"/>
<div class="container main-content">
    <div class="breadcrumb"><a href="index.jsp">首页</a><span>&gt;</span>公告通知</div>
    <% if (notice != null) { %>
    <div class="card">
        <div class="card-header"><h3><%=notice.getNoticeTitle()%></h3></div>
        <div class="card-body">
            <p style="color:#999;margin-bottom:15px;">发布时间：<%=new java.text.SimpleDateFormat("yyyy-MM-dd HH:mm").format(notice.getCreateTime())%> | 发布者：<%=notice.getAdminName()%></p>
            <div style="line-height:2;color:#555;white-space:pre-line;"><%=notice.getNoticeContent()%></div>
            <div style="margin-top:20px;text-align:center;">
                <a href="${pageContext.request.contextPath}/NoticeServlet" class="btn btn-outline">&larr; 返回列表</a>
            </div>
        </div>
    </div>
    <% } else { %>
    <div class="card">
        <div class="card-header"><h3>&#x1F4E2; 通知公告</h3></div>
        <div class="card-body" style="padding:0;">
            <% if (notices != null && !notices.isEmpty()) {
                for (Notice n : notices) { %>
            <div class="notice-item">
                <h5><a href="${pageContext.request.contextPath}/NoticeServlet?action=detail&noticeId=<%=n.getNoticeId()%>"><%=n.getNoticeTitle()%></a>
                <% if (n.getNoticePriority() >= 2) { %><span class="notice-priority priority-urgent">紧急</span>
                <% } else if (n.getNoticePriority() == 1) { %><span class="notice-priority priority-important">重要</span>
                <% } %>
                </h5>
                <div class="notice-meta"><%=new java.text.SimpleDateFormat("yyyy-MM-dd HH:mm").format(n.getCreateTime())%> | <%=n.getAdminName()%></div>
            </div>
            <% } } else { %>
            <div class="empty-state"><p>暂无公告</p></div>
            <% } %>
        </div>
    </div>
    <% } %>
</div>
<jsp:include page="../include/footer.jsp"/>
</body></html>
