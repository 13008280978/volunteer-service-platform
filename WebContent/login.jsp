<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>登录 - 社区志愿者服务平台</title>
    <link rel="stylesheet" href="css/style.css">
</head>
<body>
    <div class="auth-page">
        <div class="auth-left">
            <h1>&#x1F91D; 志愿者服务平台</h1>
            <p>用爱心点亮社区，用行动传递温暖。加入我们，一起为社区贡献我们的力量！</p>
        </div>
        <div class="auth-right">
            <div class="auth-card">
                <h2>用户登录</h2>
                <div class="auth-tabs">
                    <a href="login.jsp?role=volunteer" class="<%=!"admin".equals(request.getParameter("role"))?"active":""%>">志愿者登录</a>
                    <a href="login.jsp?role=admin" class="<%="admin".equals(request.getParameter("role"))?"active":""%>">管理员登录</a>
                </div>
                
                <% if (request.getAttribute("error") != null) { %>
                <div class="alert alert-danger"><%=request.getAttribute("error")%></div>
                <% } %>
                <% if (request.getAttribute("success") != null) { %>
                <div class="alert alert-success"><%=request.getAttribute("success")%></div>
                <% } %>
                
                <form action="LoginServlet" method="post" data-validate>
                    <input type="hidden" name="role" value="<%=request.getParameter("role") != null ? request.getParameter("role") : "volunteer"%>">
                    <div class="form-group">
                        <label>账号</label>
                        <input type="text" name="account" class="form-control" placeholder="请输入账号" required>
                    </div>
                    <div class="form-group">
                        <label>密码</label>
                        <input type="password" name="password" class="form-control" placeholder="请输入密码" required>
                    </div>
                    <div class="form-group">
                        <button type="submit" class="btn btn-primary btn-block btn-lg">登 录</button>
                    </div>
                </form>
                
                <% if (!"admin".equals(request.getParameter("role"))) { %>
                <p style="text-align:center;color:#999;">还没有账号？<a href="register.jsp">立即注册</a></p>
                <% } %>
                <p style="text-align:center;margin-top:15px;"><a href="index.jsp">&larr; 返回首页</a></p>
            </div>
        </div>
    </div>
    <script src="js/main.js"></script>
</body>
</html>
