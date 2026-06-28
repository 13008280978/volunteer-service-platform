<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ page import="java.util.List, com.volunteer.po.*" %>
<% List<ActivityType> types=(List<ActivityType>)request.getAttribute("types"); Activity act=(Activity)request.getAttribute("activity"); boolean isE=act!=null; %>
<!DOCTYPE html><html><head><meta charset="UTF-8"><title><%=isE?"编辑":"发布"%>活动</title><link rel="stylesheet" href="${pageContext.request.contextPath}/css/style.css"></head>
<body><jsp:include page="../include/admin_header.jsp"/><div class="admin-content">
<h2><%=isE?"&#x270F; 编辑活动":"&#x2795; 发布新活动"%></h2>
<div class="card"><div class="card-body"><form action="AdminActivityServlet" method="post" data-validate>
<input type="hidden" name="action" value="<%=isE?"update":"add"%>"><%if(isE){%><input type="hidden" name="actId" value="<%=act.getActId()%>"><%}%>
<div class="form-group"><label>活动标题*</label><input type="text" name="actTitle" class="form-control" required value="<%=isE?act.getActTitle():""%>"></div>
<div class="form-row"><div class="form-group"><label>类型*</label><select name="actTypeId" class="form-control"><%if(types!=null)for(ActivityType t:types){%><option value="<%=t.getTypeId()%>" <%=isE&&act.getActTypeId().equals(t.getTypeId())?"selected":""%>><%=t.getTypeName()%></option><%}%></select></div>
<div class="form-group"><label>状态</label><select name="actStatus" class="form-control"><option value="0" <%=isE&&act.getActStatus()==0?"selected":""%>>草稿</option><option value="1" <%=isE&&act.getActStatus()==1?"selected":""%>>报名中</option><option value="2" <%=isE&&act.getActStatus()==2?"selected":""%>>进行中</option><option value="3" <%=isE&&act.getActStatus()==3?"selected":""%>>已结束</option></select></div></div>
<div class="form-group"><label>地点*</label><input type="text" name="actLocation" class="form-control" required value="<%=isE?act.getActLocation():""%>"></div>
<div class="form-row"><div class="form-group"><label>开始时间*</label><input type="datetime-local" name="actStartTime" class="form-control" required value="<%=isE&&act.getActStartTime()!=null?new java.text.SimpleDateFormat("yyyy-MM-dd'T'HH:mm").format(act.getActStartTime()):""%>"></div>
<div class="form-group"><label>结束时间*</label><input type="datetime-local" name="actEndTime" class="form-control" required value="<%=isE&&act.getActEndTime()!=null?new java.text.SimpleDateFormat("yyyy-MM-dd'T'HH:mm").format(act.getActEndTime()):""%>"></div></div>
<div class="form-row"><div class="form-group"><label>报名截止</label><input type="datetime-local" name="actEnrollDeadline" class="form-control" value="<%=isE&&act.getActEnrollDeadline()!=null?new java.text.SimpleDateFormat("yyyy-MM-dd'T'HH:mm").format(act.getActEnrollDeadline()):""%>"></div>
<div class="form-group"><label>最大人数</label><input type="number" name="actMaxPeople" class="form-control" value="<%=isE?act.getActMaxPeople():0%>"></div></div>
<div class="form-row"><div class="form-group"><label>时长(h)</label><input type="number" name="actHours" class="form-control" step="0.5" value="<%=isE?act.getActHours():0%>"></div>
<div class="form-group"><label>积分</label><input type="number" name="actPoints" class="form-control" value="<%=isE?act.getActPoints():0%>"></div></div>
<div class="form-row"><div class="form-group"><label>联系人</label><input type="text" name="actContact" class="form-control" value="<%=isE?act.getActContact():""%>"></div>
<div class="form-group"><label>电话</label><input type="text" name="actContactPhone" class="form-control" value="<%=isE?act.getActContactPhone():""%>"></div></div>
<div class="form-group"><label>详细描述*</label><textarea name="actDesc" class="form-control" rows="6" required><%=isE?act.getActDesc():""%></textarea></div>
<a href="AdminActivityServlet" class="btn btn-outline">返回</a><button type="submit" class="btn btn-primary" style="margin-left:10px;"><%=isE?"保存":"发布"%></button>
</form></div></div></div></body></html>