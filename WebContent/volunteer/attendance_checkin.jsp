<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ page import="java.util.List, com.volunteer.po.*" %>
<%
    Volunteer vol = (Volunteer) session.getAttribute("volunteer");
    List<Attendance> attendances = (List<Attendance>) request.getAttribute("attendances");
    List<Enrollment> enrollments = (List<Enrollment>) request.getAttribute("enrollments");
    Attendance attendance = (Attendance) request.getAttribute("attendance");
    Enrollment enrollment = (Enrollment) request.getAttribute("enrollment");
    String action = request.getParameter("action");
%>
<!DOCTYPE html>
<html><head><meta charset="UTF-8"><title>签到打卡 - 志愿者服务平台</title><link rel="stylesheet" href="${pageContext.request.contextPath}/css/style.css"></head>
<body>
<jsp:include page="../include/header.jsp"/>
<div class="container main-content">
    <div class="breadcrumb"><a href="index.jsp">首页</a><span>&gt;</span>签到打卡</div>
    <% if (request.getAttribute("success") != null) { %>
    <div class="alert alert-success"><%=request.getAttribute("success")%></div>
    <% } %>
    <% if (request.getAttribute("error") != null) { %>
    <div class="alert alert-danger"><%=request.getAttribute("error")%></div>
    <% } %>

    <% if ("checkin".equals(action) && enrollment != null) { %>
    <div class="card" style="max-width:500px;margin:0 auto;">
        <div class="card-header"><h3>&#x2705; 活动签到</h3></div>
        <div class="card-body">
            <div class="checkin-container">
                <p style="font-size:18px;font-weight:bold;margin-bottom:5px;"><%=enrollment.getActTitle()%></p>
                <div class="checkin-time" id="liveClock">--:--:--</div>
                <div class="checkin-date" id="liveDate"></div>
                <% if (attendance == null) { %>
                <div style="margin-top:30px;">
                    <form id="checkinForm" action="${pageContext.request.contextPath}/AttendanceServlet" method="post">
                        <input type="hidden" name="action" value="checkin">
                        <input type="hidden" name="enrollId" value="<%=enrollment.getEnrollId()%>">
                        <input type="hidden" name="volId" value="<%=vol.getVolId()%>">
                        <input type="hidden" name="actId" value="<%=enrollment.getActId()%>">
                        <input type="hidden" name="checkinAddress" value="活动现场">
                        <button type="button" class="checkin-btn btn-checkin" onclick="confirmCheckin(<%=enrollment.getEnrollId()%>,<%=vol.getVolId()%>,<%=enrollment.getActId()%>)">&#x1F4F1; 点击签到</button>
                    </form>
                </div>
                <% } else if (attendance.getCheckinStatus() == 1) { %>
                <div style="margin-top:20px;">
                    <div class="checkin-status" style="background:#f6ffed;color:#52c41a;">&#x2705; 已签到 <%=new java.text.SimpleDateFormat("HH:mm:ss").format(attendance.getCheckinTime())%></div>
                    <br><br>
                    <button class="checkin-btn btn-checkout" onclick="confirmCheckout(<%=attendance.getAttendId()%>, 4)">&#x1F6AA; 签退</button>
                </div>
                <% } else if (attendance.getCheckinStatus() >= 2) { %>
                <div style="margin-top:20px;">
                    <div class="checkin-status" style="background:#e6f7ff;color:#1890ff;">&#x2705; 已签退</div>
                    <p style="margin-top:15px;color:#666;">签到：<%=new java.text.SimpleDateFormat("HH:mm").format(attendance.getCheckinTime())%></p>
                    <p style="color:#666;">签退：<%=attendance.getCheckoutTime()!=null?new java.text.SimpleDateFormat("HH:mm").format(attendance.getCheckoutTime()):"-"%></p>
                    <p style="color:#666;">服务时长：<%=attendance.getActualHours()%>小时</p>
                    <% if (attendance.getCheckinStatus() == 3) { %>
                    <span class="checkin-status" style="background:#f6ffed;color:#52c41a;">已确认</span>
                    <% } %>
                </div>
                <% } %>
            </div>
        </div>
    </div>
    <% } else { %>
    <div class="card">
        <div class="card-header"><h3>&#x1F4CB; 我的报名活动</h3></div>
        <div class="card-body">
            <% if (enrollments != null && !enrollments.isEmpty()) { %>
            <table class="table">
                <thead><tr><th>活动名称</th><th>报名时间</th><th>状态</th><th>操作</th></tr></thead>
                <tbody>
                <% for (Enrollment e : enrollments) { %>
                <tr>
                    <td><%=e.getActTitle()%></td>
                    <td><%=new java.text.SimpleDateFormat("yyyy-MM-dd HH:mm").format(e.getEnrollTime())%></td>
                    <td>
                        <% if (e.getEnrollStatus() == 0) { %><span class="activity-status status-draft">待审核</span>
                        <% } else if (e.getEnrollStatus() == 1) { %><span class="activity-status status-enrolling">已通过</span>
                        <% } else if (e.getEnrollStatus() == 2) { %><span class="activity-status status-cancelled">已拒绝</span>
                        <% } else { %><span class="activity-status status-ended">已取消</span>
                        <% } %>
                    </td>
                    <td>
                        <% if (e.getEnrollStatus() == 1) { %>
                        <a href="${pageContext.request.contextPath}/AttendanceServlet?action=checkin&enrollId=<%=e.getEnrollId()%>" class="btn btn-sm btn-success">&#x2705; 签到/签退</a>
                        <% } %>
                    </td>
                </tr>
                <% } %>
                </tbody>
            </table>
            <% } else { %>
            <div class="empty-state"><p>暂无报名活动</p></div>
            <% } %>
        </div>
    </div>

    <div class="card" style="margin-top:20px;">
        <div class="card-header"><h3>&#x1F4CA; 签到记录</h3></div>
        <div class="card-body">
            <% if (attendances != null && !attendances.isEmpty()) { %>
            <table class="table">
                <thead><tr><th>活动</th><th>签到时间</th><th>签退时间</th><th>服务时长</th><th>状态</th></tr></thead>
                <tbody>
                <% for (Attendance a : attendances) { %>
                <tr>
                    <td><%=a.getActTitle()%></td>
                    <td><%=a.getCheckinTime()!=null?new java.text.SimpleDateFormat("yyyy-MM-dd HH:mm").format(a.getCheckinTime()):"-"%></td>
                    <td><%=a.getCheckoutTime()!=null?new java.text.SimpleDateFormat("yyyy-MM-dd HH:mm").format(a.getCheckoutTime()):"-"%></td>
                    <td><%=a.getActualHours()%>小时</td>
                    <td>
                        <% if (a.getCheckinStatus() == 0) { %>未签到
                        <% } else if (a.getCheckinStatus() == 1) { %><span style="color:#52c41a;">已签到</span>
                        <% } else if (a.getCheckinStatus() == 2) { %><span style="color:#1890ff;">已签退</span>
                        <% } else if (a.getCheckinStatus() == 3) { %><span style="color:#52c41a;">&#x2705; 已确认</span>
                        <% } %>
                    </td>
                </tr>
                <% } %>
                </tbody>
            </table>
            <% } else { %>
            <div class="empty-state"><p>暂无签到记录</p></div>
            <% } %>
        </div>
    </div>
    <% } %>
</div>
<jsp:include page="../include/footer.jsp"/>
<script src="${pageContext.request.contextPath}/js/main.js"></script>
</body></html>
