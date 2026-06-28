<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ page import="com.volunteer.po.*" %>
<% Activity activity=(Activity)request.getAttribute("activity"); Enrollment enrollment=(Enrollment)request.getAttribute("enrollment"); Volunteer vol=(Volunteer)session.getAttribute("volunteer"); %>
<!DOCTYPE html><html><head><meta charset="UTF-8"><title>活动详情</title><link rel="stylesheet" href="${pageContext.request.contextPath}/css/style.css"></head>
<body>
<jsp:include page="../include/header.jsp"/>
<div class="container main-content">
<div class="breadcrumb"><a href="index.jsp">首页</a><span>&gt;</span><a href="${pageContext.request.contextPath}/ActivityServlet">志愿活动</a><span>&gt;</span>活动详情</div>
<% if(request.getAttribute("success")!=null){%><div class="alert alert-success"><%=request.getAttribute("success")%></div><%}%>
<% if(request.getAttribute("error")!=null){%><div class="alert alert-danger"><%=request.getAttribute("error")%></div><%}%>
<% if(activity!=null){%>
<div class="card">
<div class="card-header"><h3><%=activity.getActTitle()%></h3>
<%if(activity.getActStatus()==1){%><span class="activity-status status-enrolling">报名中</span><%}else if(activity.getActStatus()==2){%><span class="activity-status status-ongoing">进行中</span><%}else{%><span class="activity-status status-ended">已结束</span><%}%>
</div>
<div class="card-body">
<div class="row" style="margin-bottom:20px;">
<div class="col-6">
<p><strong>活动类型：</strong><%=activity.getTypeName()%></p>
<p><strong>活动地点：</strong><%=activity.getActLocation()%></p>
<p><strong>开始时间：</strong><%=new java.text.SimpleDateFormat("yyyy-MM-dd HH:mm").format(activity.getActStartTime())%></p>
<p><strong>结束时间：</strong><%=new java.text.SimpleDateFormat("yyyy-MM-dd HH:mm").format(activity.getActEndTime())%></p>
</div>
<div class="col-6">
<p><strong>预计时长：</strong><%=activity.getActHours()%>小时</p>
<p><strong>积分奖励：</strong><%=activity.getActPoints()%>积分</p>
<p><strong>报名人数：</strong><%=activity.getActCurrentPeople()%>/<%=activity.getActMaxPeople()>0?activity.getActMaxPeople():"不限"%></p>
<p><strong>联系人：</strong><%=activity.getActContact()%>（<%=activity.getActContactPhone()%>）</p>
</div></div>
<div style="margin-top:20px;padding-top:20px;border-top:1px solid #f0f0f0;">
<h4>活动详情</h4>
<p style="line-height:1.8;color:#555;margin-top:10px;white-space:pre-line;"><%=activity.getActDesc()%></p>
</div>
<div style="margin-top:30px;text-align:center;padding-top:20px;border-top:1px solid #f0f0f0;">
<% if(vol!=null){
if(enrollment==null){if(activity.getActStatus()==1){%>
<a href="${pageContext.request.contextPath}/EnrollServlet?actId=<%=activity.getActId()%>" class="btn btn-primary btn-lg">&#x270B; 我要报名</a>
<%}else{%><span class="btn btn-lg" style="background:#f5f5f5;color:#999">活动不可报名</span><%}
}else if(enrollment.getEnrollStatus()==0){%><span class="btn btn-lg btn-warning">报名已提交，等待审核</span>
<%}else if(enrollment.getEnrollStatus()==1){%><span class="btn btn-lg btn-success">已通过审核</span>
<a href="${pageContext.request.contextPath}/AttendanceServlet?action=checkin&enrollId=<%=enrollment.getEnrollId()%>" class="btn btn-lg btn-info" style="margin-left:10px;">去签到</a>
<%}else if(enrollment.getEnrollStatus()==2){%><span class="btn btn-lg btn-danger">报名被拒绝</span>
<%}else{%><span class="btn btn-lg" style="background:#f5f5f5;color:#999">已取消</span><%}
}else{%><p style="color:#999;">请先<a href="${pageContext.request.contextPath}/login.jsp?role=volunteer">登录</a>后报名</p><%}%>
</div></div></div><%}%>
</div>
<jsp:include page="../include/footer.jsp"/>
</body></html>