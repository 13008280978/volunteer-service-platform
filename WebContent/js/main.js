/**
 * 社区志愿者服务平台 - 前端交互脚本
 */

// 页面加载完成后初始化
document.addEventListener('DOMContentLoaded', function() {
    initCheckinClock();
    initFormValidation();
    initModalHandlers();
});

// ============================================
// 签到打卡功能
// ============================================
function initCheckinClock() {
    var clockEl = document.getElementById('liveClock');
    if (clockEl) {
        updateClock();
        setInterval(updateClock, 1000);
    }
}

function updateClock() {
    var now = new Date();
    var hours = String(now.getHours()).padStart(2, '0');
    var minutes = String(now.getMinutes()).padStart(2, '0');
    var seconds = String(now.getSeconds()).padStart(2, '0');
    var clockEl = document.getElementById('liveClock');
    if (clockEl) {
        clockEl.textContent = hours + ':' + minutes + ':' + seconds;
    }
    var dateEl = document.getElementById('liveDate');
    if (dateEl) {
        var year = now.getFullYear();
        var month = String(now.getMonth() + 1).padStart(2, '0');
        var day = String(now.getDate()).padStart(2, '0');
        var weekDays = ['星期日', '星期一', '星期二', '星期三', '星期四', '星期五', '星期六'];
        dateEl.textContent = year + '年' + month + '月' + day + '日 ' + weekDays[now.getDay()];
    }
}

// 签到确认
function confirmCheckin(enrollId, volId, actId) {
    if (confirm('确认签到吗？签到后将记录您的服务时间。')) {
        var form = document.getElementById('checkinForm');
        if (form) {
            form.submit();
        }
    }
}

// 签退确认
function confirmCheckout(attendId, actualHours) {
    var hours = prompt('请输入实际服务时长（小时）：', actualHours || '4');
    if (hours !== null && hours !== '') {
        if (isNaN(hours) || parseFloat(hours) <= 0) {
            alert('请输入有效的服务时长！');
            return;
        }
        if (confirm('确认签退吗？服务时长：' + hours + '小时')) {
            var form = document.createElement('form');
            form.method = 'POST';
            form.action = 'AttendanceServlet';
            form.innerHTML = '<input type="hidden" name="action" value="checkout">' +
                '<input type="hidden" name="attendId" value="' + attendId + '">' +
                '<input type="hidden" name="actualHours" value="' + hours + '">';
            document.body.appendChild(form);
            form.submit();
        }
    }
}

// ============================================
// 表单验证
// ============================================
function initFormValidation() {
    var forms = document.querySelectorAll('form[data-validate]');
    forms.forEach(function(form) {
        form.addEventListener('submit', function(e) {
            var requiredFields = form.querySelectorAll('[required]');
            var isValid = true;
            requiredFields.forEach(function(field) {
                if (!field.value.trim()) {
                    field.style.borderColor = '#f5222d';
                    isValid = false;
                } else {
                    field.style.borderColor = '#d9d9d9';
                }
            });
            if (!isValid) {
                e.preventDefault();
                alert('请填写所有必填项！');
            }
        });
    });
}

// 密码确认验证
function validatePassword() {
    var pwd = document.getElementById('password');
    var confirm = document.getElementById('confirmPassword');
    if (pwd && confirm) {
        if (pwd.value !== confirm.value) {
            confirm.style.borderColor = '#f5222d';
            return false;
        } else {
            confirm.style.borderColor = '#d9d9d9';
            return true;
        }
    }
    return true;
}

// ============================================
// 模态框处理
// ============================================
function initModalHandlers() {
    // 关闭模态框
    document.querySelectorAll('.modal-close, .modal-overlay').forEach(function(el) {
        el.addEventListener('click', function(e) {
            if (e.target === el || el.classList.contains('modal-close')) {
                var modal = el.closest('.modal-overlay');
                if (modal) modal.classList.remove('show');
            }
        });
    });
}

function showModal(modalId) {
    var modal = document.getElementById(modalId);
    if (modal) modal.classList.add('show');
}

function hideModal(modalId) {
    var modal = document.getElementById(modalId);
    if (modal) modal.classList.remove('show');
}

// ============================================
// 管理员审核操作
// ============================================
function auditVolunteer(volId, status) {
    var remark = prompt(status == 1 ? '请输入审核通过备注（可选）：' : '请输入审核拒绝原因：');
    if (remark === null) return;
    
    var form = document.createElement('form');
    form.method = 'POST';
    form.action = 'AdminVolunteerServlet';
    form.innerHTML = '<input type="hidden" name="action" value="audit">' +
        '<input type="hidden" name="volId" value="' + volId + '">' +
        '<input type="hidden" name="status" value="' + status + '">' +
        '<input type="hidden" name="remark" value="' + (remark || '') + '">';
    document.body.appendChild(form);
    form.submit();
}

function auditEnroll(enrollId, status) {
    var remark = status == 1 ? '' : prompt('请输入拒绝原因：');
    if (status == 2 && remark === null) return;
    
    var form = document.createElement('form');
    form.method = 'POST';
    form.action = 'AdminEnrollServlet';
    form.innerHTML = '<input type="hidden" name="action" value="audit">' +
        '<input type="hidden" name="enrollId" value="' + enrollId + '">' +
        '<input type="hidden" name="status" value="' + status + '">' +
        '<input type="hidden" name="remark" value="' + (remark || '') + '">';
    document.body.appendChild(form);
    form.submit();
}

function auditExperience(expId, status) {
    var remark = status == 1 ? '' : prompt('请输入审核意见：');
    if (status == 2 && remark === null) return;
    
    var form = document.createElement('form');
    form.method = 'POST';
    form.action = 'AdminExperienceServlet';
    form.innerHTML = '<input type="hidden" name="action" value="audit">' +
        '<input type="hidden" name="expId" value="' + expId + '">' +
        '<input type="hidden" name="status" value="' + status + '">' +
        '<input type="hidden" name="remark" value="' + (remark || '') + '">';
    document.body.appendChild(form);
    form.submit();
}

function confirmAction(message, url) {
    if (confirm(message)) {
        window.location.href = url;
    }
}

// ============================================
// 活动搜索
// ============================================
function searchActivities() {
    var typeId = document.getElementById('typeId') ? document.getElementById('typeId').value : '';
    var keyword = document.getElementById('keyword') ? document.getElementById('keyword').value : '';
    var location = document.getElementById('location') ? document.getElementById('location').value : '';
    
    var params = [];
    if (typeId) params.push('typeId=' + typeId);
    if (keyword) params.push('keyword=' + encodeURIComponent(keyword));
    if (location) params.push('location=' + encodeURIComponent(location));
    
    window.location.href = 'ActivityServlet?' + params.join('&');
}

// ============================================
// 工具函数
// ============================================
function formatDate(dateStr) {
    if (!dateStr) return '-';
    var d = new Date(dateStr);
    return d.getFullYear() + '-' + String(d.getMonth()+1).padStart(2,'0') + '-' + String(d.getDate()).padStart(2,'0');
}

function formatDateTime(dateStr) {
    if (!dateStr) return '-';
    var d = new Date(dateStr);
    return formatDate(dateStr) + ' ' + String(d.getHours()).padStart(2,'0') + ':' + String(d.getMinutes()).padStart(2,'0');
}

// 倒计时
function initCountdown(targetTime, elementId) {
    var target = new Date(targetTime).getTime();
    setInterval(function() {
        var now = new Date().getTime();
        var diff = target - now;
        if (diff <= 0) {
            document.getElementById(elementId).textContent = '已截止';
            return;
        }
        var days = Math.floor(diff / (1000*60*60*24));
        var hours = Math.floor((diff % (1000*60*60*24)) / (1000*60*60));
        var mins = Math.floor((diff % (1000*60*60)) / (1000*60));
        document.getElementById(elementId).textContent = days + '天' + hours + '时' + mins + '分';
    }, 60000);
}

// 消息提示自动消失
setTimeout(function() {
    var alerts = document.querySelectorAll('.alert');
    alerts.forEach(function(alert) {
        alert.style.transition = 'opacity 0.5s';
        alert.style.opacity = '0';
        setTimeout(function() { alert.style.display = 'none'; }, 500);
    });
}, 5000);
