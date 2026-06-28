<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ page import="java.util.List, com.volunteer.po.*" %>
<%
    List<Activity> activities = (List<Activity>) request.getAttribute("activities");
    List<ActivityType> types = (List<ActivityType>) request.getAttribute("types");
    Volunteer vol = (Volunteer) session.getAttribute("volunteer");
%>
<!DOCTYPE html>
<html><head><meta charset="UTF-8"><title>志愿活动 - 志愿者服务平台</title><link rel="stylesheet" href="${pageContext.request.contextPath}/css/style.css"></head>
<body>
<jsp:include page="../include/header.jsp"/>
<div class="container main-content">
    <div class="breadcrumb"><a href="index.jsp">首页</a><span>&gt;</span>志愿活动</div>
    <div class="card">
        <div class="card-header"><h3>&#x1F50D; 活动搜索</h3></div>
        <div class="card-body">
            <div class="filter-bar">
                <select id="typeId" class="form-control">
                    <option value="">全部类型</option>
                    <% if (types != null) for (ActivityType t : types) { %>
                    <option value="<%=t.getTypeId()%>" <%=String.valueOf(t.getTypeId()).equals(String.valueOf(request.getAttribute("typeId")))? "selected":""%>><%=t.getTypeName()%></option>
                    <% } %>
                </select>
                <input type="text" id="keyword" class="form-control" placeholder="搜索活动名称..." value="<%=request.getAttribute("keyword") != null ? request.getAttribute("keyword") : ""%>">
                <input type="text" id="location" class="form-control" placeholder="搜索地点..." value="<%=request.getAttribute("location") != null ? request.getAttribute("location") : ""%>">
                <button onclick="searchActivities()" class="btn btn-primary">&#x1F50D; 搜索</button>
            </div>
        </div>
    </div>
    <div class="card">
        <div class="card-header"><h3>&#x1F4CB; 活动列表 <span style="color:#999;font-size:14px;font-weight:normal;">共<%=activities!=null?activities.size():0%>个活动</span></h3></div>
        <div class="card-body" style="padding:0;">
            <% if (activities != null && !activities.isEmpty()) { 
                for (Activity act : activities) { %>
            <div class="activity-item">
                <div class="activity-info">
                    <h4><a href="${pageContext.request.contextPath}/ActivityDetailServlet?actId=<%=act.getActId()%>"><%=act.getActTitle()%></a>
                    <span class="activity-tag tag-<%=act.getTypeName()!=null&&act.getTypeName().contains("环保")?"env":act.getTypeName()!=null&&act.getTypeName().contains("社区")?"service":act.getTypeName()!=null&&act.getTypeName().contains("教育")?"edu":"medical"%>"><%=act.getTypeName()%></span>
                    </h4>
                    <p style="color:#666;font-size:14px;margin:8px 0;"><%=act.getActDesc().length()>100?act.getActDesc().substring(0,100)+"...":act.getActDesc()%></p>
                    <div class="activity-meta">
                        <span>&#x1F4CD; <%=act.getActLocation()%></span>
                        <span>&#x1F552; <%=new java.text.SimpleDateFormat("yyyy-MM-dd HH:mm").format(act.getActStartTime())%></span>
                        <span>&#x23F1; <%=act.getActHours()%>小时</span>
                        <span>&#x2B50; <%=act.getActPoints()%>积分</span>
                        <span>&#x1F465; <%=act.getActCurrentPeople()%>/<%=act.getActMaxPeople()>0?act.getActMaxPeople():"不限"%></span>
                    </div>
                </div>
                <div style="text-align:right;padding-left:20px;min-width:100px;">
                    <% if (act.getActStatus() == 1) { %>
                    <span class="activity-status status-enrolling">报名中</span><br><br>
                    <a href="${pageContext.request.contextPath}/ActivityDetailServlet?actId=<%=act.getActId()%>" class="btn btn-sm btn-primary">立即报名</a>
                    <% } else if (act.getActStatus() == 2) { %>
                    <span class="activity-status status-ongoing">进行中</span>
                    <% } else if (act.getActStatus() == 3) { %>
                    <span class="activity-status status-ended">已结束</span>
                    <% } else if (act.getActStatus() == 4) { %>
                    <span class="activity-status status-cancelled">已取消</span>
                    <% } %>
                </div>
            </div>
            <% } } else { %>
            <div class="empty-state"><p>暂无符合条件的活动</p></div>
            <% } %>
        </div>
    </div>
</div>
<jsp:include page="../include/footer.jsp"/>
<script src="${pageContext.request.contextPath}/js/main.js"></script>
</body></html>
