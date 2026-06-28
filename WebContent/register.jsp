<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE html>
<html><head><meta charset="UTF-8"><title>注册 - 社区志愿者服务平台</title><link rel="stylesheet" href="css/style.css"></head>
<body>
<div class="auth-page">
    <div class="auth-left">
        <h1>&#127775; 成为志愿者</h1>
        <p>加入我们的志愿者大家庭，参与有意义的公益活动，积累服务时长和志愿积分</p>
    </div>
    <div class="auth-right">
        <div class="auth-card">
            <h2>志愿者注册</h2>
            <% if (request.getAttribute("error") != null) { %>
            <div class="alert alert-danger"><%=request.getAttribute("error")%></div>
            <% } %>
            <form action="RegisterServlet" method="post" data-validate>
                <div class="form-row">
                    <div class="form-group"><label>姓名 *</label><input type="text" name="volName" class="form-control" required placeholder="真实姓名"></div>
                    <div class="form-group"><label>性别</label><select name="volGender" class="form-control"><option value="1">男</option><option value="2">女</option></select></div>
                </div>
                <div class="form-row">
                    <div class="form-group"><label>账号 *</label><input type="text" name="volAccount" class="form-control" required placeholder="登录账号"></div>
                    <div class="form-group"><label>年龄</label><input type="number" name="volAge" class="form-control" min="16" max="80" placeholder="年龄"></div>
                </div>
                <div class="form-row">
                    <div class="form-group"><label>密码 *</label><input type="password" name="volPassword" id="password" class="form-control" required></div>
                    <div class="form-group"><label>确认密码 *</label><input type="password" name="volConfirmPwd" id="confirmPassword" class="form-control" required></div>
                </div>
                <div class="form-row">
                    <div class="form-group"><label>手机号 *</label><input type="text" name="volPhone" class="form-control" required></div>
                    <div class="form-group"><label>邮箱</label><input type="email" name="volEmail" class="form-control"></div>
                </div>
                <div class="form-group"><label>身份证号</label><input type="text" name="volIdcard" class="form-control"></div>
                <div class="form-group"><label>家庭住址</label><input type="text" name="volAddress" class="form-control"></div>
                <div class="form-row">
                    <div class="form-group"><label>紧急联系人</label><input type="text" name="volEmergencyContact" class="form-control"></div>
                    <div class="form-group"><label>紧急联系人电话</label><input type="text" name="volEmergencyPhone" class="form-control"></div>
                </div>
                <div class="form-group"><label>技能特长</label><textarea name="volSkills" class="form-control" rows="3" placeholder="如摄影、急救、教学等"></textarea></div>
                <div class="form-group"><button type="submit" class="btn btn-primary btn-block btn-lg">注 册</button></div>
            </form>
            <p style="text-align:center;color:#999;">已有账号？<a href="login.jsp?role=volunteer">立即登录</a></p>
            <p style="text-align:center;margin-top:10px;"><a href="index.jsp">&larr; 返回首页</a></p>
        </div>
    </div>
</div>
<script src="js/main.js"></script>
</body></html>