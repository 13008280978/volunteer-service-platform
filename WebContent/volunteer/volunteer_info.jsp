<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ page import="com.volunteer.po.*" %>
<%
    Volunteer vol = (Volunteer) request.getAttribute("volunteer");
    if (vol == null) vol = (Volunteer) session.getAttribute("volunteer");
%>
<!DOCTYPE html>
<html><head><meta charset="UTF-8"><title>个人中心 - 志愿者服务平台</title><link rel="stylesheet" href="${pageContext.request.contextPath}/css/style.css"></head>
<body>
<jsp:include page="../include/header.jsp"/>
<div class="container main-content">
    <div class="breadcrumb"><a href="index.jsp">首页</a><span>&gt;</span>个人中心</div>
    <% if (request.getAttribute("success") != null) { %>
    <div class="alert alert-success"><%=request.getAttribute("success")%></div>
    <% } %>
    <% if (request.getAttribute("error") != null) { %>
    <div class="alert alert-danger"><%=request.getAttribute("error")%></div>
    <% } %>
    <div class="row">
        <div class="col-4">
            <div class="card" style="text-align:center;">
                <div class="card-body">
                    <div style="width:80px;height:80px;border-radius:50%;background:linear-gradient(135deg,#1890ff,#722ed1);display:flex;align-items:center;justify-content:center;margin:0 auto 15px;color:#fff;font-size:36px;">&#x1F464;</div>
                    <h3><%=vol.getVolName()%></h3>
                    <p style="color:#999;margin:10px 0;">累计服务 <%=vol.getVolTotalHours()%> 小时</p>
                    <p style="color:#fa8c16;font-size:20px;font-weight:bold;">&#x2B50; <%=vol.getVolTotalPoints()%> 积分</p>
                    <div style="margin-top:15px;">
                        <a href="${pageContext.request.contextPath}/ServiceRecordServlet" class="btn btn-sm btn-outline">服务记录</a>
                        <a href="${pageContext.request.contextPath}/PointsServlet" class="btn btn-sm btn-outline">积分中心</a>
                    </div>
                </div>
            </div>
        </div>
        <div class="col-8">
            <div class="card">
                <div class="card-header"><h3>&#x1F4DD; 个人信息</h3></div>
                <div class="card-body">
                    <form action="${pageContext.request.contextPath}/VolunteerServlet" method="post">
                        <input type="hidden" name="action" value="update">
                        <div class="form-row">
                            <div class="form-group">
                                <label>姓名</label>
                                <input type="text" name="volName" class="form-control" value="<%=vol.getVolName()%>">
                            </div>
                            <div class="form-group">
                                <label>性别</label>
                                <select name="volGender" class="form-control">
                                    <option value="1" <%=vol.getVolGender()!=null&&vol.getVolGender()==1?"selected":""%>>男</option>
                                    <option value="2" <%=vol.getVolGender()!=null&&vol.getVolGender()==2?"selected":""%>>女</option>
                                </select>
                            </div>
                        </div>
                        <div class="form-row">
                            <div class="form-group">
                                <label>手机号</label>
                                <input type="text" name="volPhone" class="form-control" value="<%=vol.getVolPhone()%>">
                            </div>
                            <div class="form-group">
                                <label>邮箱</label>
                                <input type="email" name="volEmail" class="form-control" value="<%=vol.getVolEmail()%>">
                            </div>
                        </div>
                        <div class="form-group">
                            <label>家庭住址</label>
                            <input type="text" name="volAddress" class="form-control" value="<%=vol.getVolAddress()%>">
                        </div>
                        <div class="form-row">
                            <div class="form-group">
                                <label>紧急联系人</label>
                                <input type="text" name="volEmergencyContact" class="form-control" value="<%=vol.getVolEmergencyContact()%>">
                            </div>
                            <div class="form-group">
                                <label>紧急联系人电话</label>
                                <input type="text" name="volEmergencyPhone" class="form-control" value="<%=vol.getVolEmergencyPhone()%>">
                            </div>
                        </div>
                        <div class="form-group">
                            <label>技能特长</label>
                            <textarea name="volSkills" class="form-control" rows="3"><%=vol.getVolSkills()%></textarea>
                        </div>
                        <div class="form-group">
                            <button type="submit" class="btn btn-primary">保存修改</button>
                        </div>
                    </form>
                </div>
            </div>
            <div class="card">
                <div class="card-header"><h3>&#x1F512; 修改密码</h3></div>
                <div class="card-body">
                    <form action="${pageContext.request.contextPath}/VolunteerServlet" method="post">
                        <input type="hidden" name="action" value="password">
                        <div class="form-row">
                            <div class="form-group"><label>原密码</label><input type="password" name="oldPassword" class="form-control" required></div>
                            <div class="form-group"><label>新密码</label><input type="password" name="newPassword" class="form-control" required></div>
                            <div class="form-group"><label>确认密码</label><input type="password" name="confirmPassword" class="form-control" required></div>
                        </div>
                        <button type="submit" class="btn btn-warning">修改密码</button>
                    </form>
                </div>
            </div>
        </div>
    </div>
</div>
<jsp:include page="../include/footer.jsp"/>
</body></html>
