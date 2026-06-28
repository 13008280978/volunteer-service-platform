<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ page import="com.volunteer.po.*, com.volunteer.service.*, com.volunteer.service.impl.*" %>
<%@ page import="java.util.List" %>
<%
    ActivityService actSvc = new ActivityServiceImpl();
    NoticeService noticeSvc = new NoticeServiceImpl();
    ActivityTypeService typeSvc = new ActivityTypeServiceImpl();
    List<Activity> latestActs = actSvc.findLatest(6);
    List<Notice> latestNotices = noticeSvc.findPublished(1, 5);
    List<ActivityType> types = typeSvc.findActive();
    Volunteer vol = (Volunteer) session.getAttribute("volunteer");
%>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>社区志愿者服务平台 - 首页</title>
    <link rel="stylesheet" href="css/style.css">
</head>
<body>
    <nav class="navbar">
        <div class="container">
            <a class="navbar-brand" href="index.jsp">&#x1F91D; 社区志愿者服务平台</a>
            <ul class="navbar-nav">
                <li class="active"><a href="index.jsp">首页</a></li>
                <li><a href="ActivityServlet">志愿活动</a></li>
                <li><a href="ExperienceServlet?action=list">心得体会</a></li>
                <li><a href="NoticeServlet">公告通知</a></li>
                <% if (vol != null) { %>
                <li><a href="EnrollServlet">我的报名</a></li>
                <li><a href="ServiceRecordServlet">服务记录</a></li>
                <li><a href="PointsServlet">积分中心</a></li>
                <% } %>
            </ul>
            <div class="navbar-right">
                <% if (vol != null) { %>
                <span class="user-info">欢迎，<%=vol.getVolName()%></span>
                <a href="VolunteerServlet?action=info" class="btn btn-sm btn-outline" style="color:#fff;border-color:rgba(255,255,255,0.5);">个人中心</a>
                <a href="VolunteerServlet?action=logout" class="btn btn-sm btn-danger" style="margin-left:8px;">退出</a>
                <% } else { %>
                <a href="login.jsp?role=volunteer" class="btn btn-sm" style="background:rgba(255,255,255,0.2);color:#fff;">登录</a>
                <a href="register.jsp" class="btn btn-sm btn-warning" style="margin-left:8px;">注册</a>
                <% } %>
            </div>
        </div>
    </nav>

    <div class="banner">
        <div class="container">
            <h1>&#x1F31F; 用爱心点亮社区，用行动传递温暖</h1>
            <p>加入志愿者行列，一起为社区贡献我们的力量</p>
        </div>
    </div>

    <div class="container main-content">
        <div class="row">
            <div class="col-8">
                <div class="card fade-in">
                    <div class="card-header">
                        <h3>&#x1F4CB; 最新志愿活动</h3>
                        <a href="ActivityServlet" class="btn btn-sm btn-outline">查看全部 &rarr;</a>
                    </div>
                    <div class="card-body" style="padding:0;">
                        <% if (latestActs != null && !latestActs.isEmpty()) { 
                            for (Activity act : latestActs) { %>
                        <div class="activity-item">
                            <div class="activity-info">
                                <h4><a href="ActivityDetailServlet?actId=<%=act.getActId()%>"><%=act.getActTitle()%></a></h4>
                                <p style="color:#666;font-size:14px;"><%=act.getActDesc().length()>80 ? act.getActDesc().substring(0,80)+"..." : act.getActDesc()%></p>
                                <div class="activity-meta">
                                    <span><i>&#x1F4CD;</i><%=act.getActLocation()%></span>
                                    <span><i>&#x1F552;</i><%=new java.text.SimpleDateFormat("yyyy-MM-dd HH:mm").format(act.getActStartTime())%></span>
                                    <span><i>&#x23F1;</i><%=act.getActHours()%>小时</span>
                                    <span><i>&#x2B50;</i><%=act.getActPoints()%>积分</span>
                                    <span><i>&#x1F465;</i><%=act.getActCurrentPeople()%>/<%=act.getActMaxPeople()>0?act.getActMaxPeople():"不限"%></span>
                                </div>
                            </div>
                            <div style="text-align:right;padding-left:20px;">
                                <% if (act.getActStatus() == 1) { %>
                                <span class="activity-status status-enrolling">报名中</span>
                                <% } else if (act.getActStatus() == 2) { %>
                                <span class="activity-status status-ongoing">进行中</span>
                                <% } %>
                            </div>
                        </div>
                        <% } } else { %>
                        <div class="empty-state"><p>暂无活动</p></div>
                        <% } %>
                    </div>
                </div>
            </div>
            <div class="col-4">
                <div class="card fade-in">
                    <div class="card-header"><h3>&#x1F4E2; 通知公告</h3></div>
                    <div class="card-body" style="padding:0;">
                        <% if (latestNotices != null) { 
                            for (Notice n : latestNotices) { %>
                        <div class="notice-item">
                            <h5><a href="NoticeServlet?action=detail&noticeId=<%=n.getNoticeId()%>"><%=n.getNoticeTitle()%></a>
                            <% if (n.getNoticePriority() >= 2) { %>
                            <span class="notice-priority priority-urgent">紧急</span>
                            <% } else if (n.getNoticePriority() == 1) { %>
                            <span class="notice-priority priority-important">重要</span>
                            <% } %>
                            </h5>
                            <div class="notice-meta"><%=new java.text.SimpleDateFormat("yyyy-MM-dd").format(n.getCreateTime())%></div>
                        </div>
                        <% } } %>
                    </div>
                </div>
                <div class="card fade-in">
                    <div class="card-header"><h3>&#x1F3F7; 活动类型</h3></div>
                    <div class="card-body">
                        <% for (ActivityType t : types) { %>
                        <a href="ActivityServlet?typeId=<%=t.getTypeId()%>" class="btn btn-sm btn-outline" style="margin:4px;"><%=t.getTypeName()%></a>
                        <% } %>
                    </div>
                </div>
                <% if (vol == null) { %>
                <div class="card fade-in" style="text-align:center;padding:30px;">
                    <h3 style="margin-bottom:15px;">成为志愿者</h3>
                    <p style="color:#666;margin-bottom:20px;">注册成为志愿者，参与丰富多彩的志愿服务活动</p>
                    <a href="register.jsp" class="btn btn-primary btn-lg">立即注册</a>
                </div>
                <% } %>
            </div>
        </div>
    </div>

    <footer class="footer">
        <div class="container">
            <p>&copy; 2024 社区志愿者服务平台 | 用爱心点亮社区</p>
            <p>技术支持：JSP + Servlet + MyBatis | 管理员入口：<a href="login.jsp?role=admin">管理员登录</a></p>
        </div>
    </footer>
    <script src="js/main.js"></script>
</body>
</html>
