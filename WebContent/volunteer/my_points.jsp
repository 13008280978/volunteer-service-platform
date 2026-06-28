<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ page import="java.util.List, com.volunteer.po.*" %>
<%
    List<Points> pointRecords = (List<Points>) request.getAttribute("pointRecords");
    Integer balance = (Integer) request.getAttribute("balance");
    Volunteer vol = (Volunteer) request.getAttribute("volunteer");
%>
<!DOCTYPE html>
<html><head><meta charset="UTF-8"><title>积分中心 - 志愿者服务平台</title><link rel="stylesheet" href="${pageContext.request.contextPath}/css/style.css"></head>
<body>
<jsp:include page="../include/header.jsp"/>
<div class="container main-content">
    <div class="breadcrumb"><a href="index.jsp">首页</a><span>&gt;</span>积分中心</div>
    <div class="points-summary">
        <div class="points-box total"><div class="num"><%=balance!=null?balance:0%></div><div class="label">当前积分</div></div>
        <div class="points-box hours"><div class="num"><%=vol.getVolTotalHours()%></div><div class="label">累计服务时长（小时）</div></div>
        <div class="points-box count"><div class="num"><%=vol.getVolTotalPoints()%></div><div class="label">历史总积分</div></div>
    </div>
    <div class="card">
        <div class="card-header"><h3>&#x2B50; 积分明细</h3></div>
        <div class="card-body">
            <% if (pointRecords != null && !pointRecords.isEmpty()) { %>
            <table class="table">
                <thead><tr><th>时间</th><th>类型</th><th>说明</th><th>积分变动</th><th>余额</th></tr></thead>
                <tbody>
                <% for (Points p : pointRecords) { %>
                <tr>
                    <td><%=new java.text.SimpleDateFormat("yyyy-MM-dd HH:mm").format(p.getCreateTime())%></td>
                    <td>
                        <% if (p.getPointType() == 1) { %><span style="color:#52c41a;">活动奖励</span>
                        <% } else if (p.getPointType() == 2) { %><span style="color:#1890ff;">签到奖励</span>
                        <% } else if (p.getPointType() == 3) { %><span style="color:#fa8c16;">额外奖励</span>
                        <% } else if (p.getPointType() == 4) { %><span style="color:#f5222d;">积分兑换</span>
                        <% } else { %><span style="color:#999;">积分调整</span>
                        <% } %>
                    </td>
                    <td><%=p.getPointDesc()%></td>
                    <td style="font-weight:bold;<%=p.getPointValue()>0?"color:#52c41a":"color:#f5222d"%>">
                        <%=p.getPointValue()>0?"+":""%><%=p.getPointValue()%>
                    </td>
                    <td><%=p.getPointBalance()%></td>
                </tr>
                <% } %>
                </tbody>
            </table>
            <% } else { %>
            <div class="empty-state"><p>暂无积分记录</p></div>
            <% } %>
        </div>
    </div>
    <div class="card">
        <div class="card-header"><h3>&#x1F4CC; 积分规则说明</h3></div>
        <div class="card-body">
            <ul style="list-style:disc;padding-left:20px;line-height:2;">
                <li>参与志愿活动可获得活动对应积分奖励（一般10-200积分）</li>
                <li>按时签到可额外获得5积分奖励</li>
                <li>发布心得体会并通过审核可获得10积分奖励</li>
                <li>积分可兑换志愿服务证书、纪念品等</li>
            </ul>
        </div>
    </div>
</div>
<jsp:include page="../include/footer.jsp"/>
</body></html>
