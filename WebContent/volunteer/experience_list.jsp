<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ page import="java.util.List, com.volunteer.po.*" %>
<%
    List<Experience> experiences = (List<Experience>) request.getAttribute("experiences");
    Experience detail = (Experience) request.getAttribute("experience");
    Integer currentPage = (Integer) request.getAttribute("currentPage");
    Integer totalPages = (Integer) request.getAttribute("totalPages");
    Boolean myExperiences = (Boolean) request.getAttribute("myExperiences");
%>
<!DOCTYPE html>
<html><head><meta charset="UTF-8"><title>心得体会 - 志愿者服务平台</title><link rel="stylesheet" href="${pageContext.request.contextPath}/css/style.css"></head>
<body>
<jsp:include page="../include/header.jsp"/>
<div class="container main-content">
    <div class="breadcrumb"><a href="index.jsp">首页</a><span>&gt;</span>心得体会</div>
    <% if (detail != null) { %>
    <div class="card">
        <div class="card-header">
            <h3><%=detail.getExpTitle()%></h3>
            <span style="color:#999;font-size:13px;">浏览 <%=detail.getExpViews()%></span>
        </div>
        <div class="card-body">
            <p style="color:#999;margin-bottom:15px;">
                作者：<%=detail.getVolName()%> | 
                发布时间：<%=new java.text.SimpleDateFormat("yyyy-MM-dd HH:mm").format(detail.getCreateTime())%>
                <% if (detail.getActTitle() != null) { %> | 关联活动：<%=detail.getActTitle()%><% } %>
            </p>
            <div style="line-height:2;color:#555;white-space:pre-line;"><%=detail.getExpContent()%></div>
            <div style="margin-top:20px;text-align:center;">
                <a href="${pageContext.request.contextPath}/ExperienceServlet?action=list" class="btn btn-outline">&larr; 返回列表</a>
            </div>
        </div>
    </div>
    <% } else { %>
    <div class="card">
        <div class="card-header">
            <h3><%=Boolean.TRUE.equals(myExperiences)?"我的心得":"心得体会"%></h3>
            <a href="${pageContext.request.contextPath}/ExperienceServlet?action=write" class="btn btn-sm btn-primary">&#x270D; 发布心得</a>
        </div>
        <div class="card-body">
            <% if (experiences != null && !experiences.isEmpty()) {
                for (Experience exp : experiences) { %>
            <div class="notice-item">
                <h5><a href="${pageContext.request.contextPath}/ExperienceServlet?action=detail&expId=<%=exp.getExpId()%>"><%=exp.getExpTitle()%></a></h5>
                <p style="color:#666;font-size:14px;margin:8px 0;"><%=exp.getExpContent().length()>100?exp.getExpContent().substring(0,100)+"...":exp.getExpContent()%></p>
                <div class="notice-meta">
                    <%=exp.getVolName()%> | <%=new java.text.SimpleDateFormat("yyyy-MM-dd").format(exp.getCreateTime())%> | 浏览 <%=exp.getExpViews()%>
                    <% if (Boolean.TRUE.equals(myExperiences)) { %>
                    | 状态：
                    <% if (exp.getExpStatus() == 0) { %><span style="color:#faad14;">待审核</span>
                    <% } else if (exp.getExpStatus() == 1) { %><span style="color:#52c41a;">已通过</span>
                    <% } else { %><span style="color:#f5222d;">已拒绝</span>
                    <% } } %>
                </div>
            </div>
            <% } } else { %>
            <div class="empty-state"><p>暂无心得体会</p></div>
            <% } %>
        </div>
        <% if (currentPage != null && totalPages != null && totalPages > 1) { %>
        <div class="card-footer">
            <ul class="pagination">
                <li <%=currentPage<=1?"class="disabled"":""%>><a href="${pageContext.request.contextPath}/ExperienceServlet?action=list&page=<%=currentPage-1%>">&laquo;</a></li>
                <% for (int i = 1; i <= totalPages; i++) { %>
                <li <%=i==currentPage?"class="active"":""%>><span><%=i%></span></li>
                <% } %>
                <li <%=currentPage>=totalPages?"class="disabled"":""%>><a href="${pageContext.request.contextPath}/ExperienceServlet?action=list&page=<%=currentPage+1%>">&raquo;</a></li>
            </ul>
        </div>
        <% } %>
    </div>
    <% } %>
</div>
<jsp:include page="../include/footer.jsp"/>
</body></html>
