<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>用户注册</title>
    <link rel="stylesheet" href="${pageContext.request.contextPath}/css/bootstrap.css">
    <script src="${pageContext.request.contextPath}/js/jquery.min.js"></script>
    <script src="${pageContext.request.contextPath}/js/bootstrap.js"></script>
</head>
<body>

<form action="${pageContext.request.contextPath}/register" method="post" class="form-horizontal" role="form" id="registerForm">
    <h1 class="text-center">注册账号</h1>

    <div class="form-group">
        <label class="col-sm-4 control-label">用户名</label>
        <div class="col-sm-4">
            <input type="text" name="user_name" class="form-control" id="user_name" placeholder="请输入用户名">
        </div>
    </div>
    <div class="form-group">
        <label class="col-sm-4 control-label">用户邮箱</label>
        <div class="col-sm-4">
            <input type="text" name="user_email" class="form-control" id="user_email" placeholder="请输入用户邮箱">
        </div>
    </div>
    <div class="form-group">
        <label class="col-sm-4 control-label">密码</label>
        <div class="col-sm-4">
            <input type="password" name="user_password" class="form-control" id="user_password" placeholder="请输密码">
        </div>
    </div>

    <div class="form-group">
        <label class="col-sm-4 control-label">注册类型</label>
        <div class="col-sm-4">
            <label class="radio-inline">
                <input type="radio" name="user_role" value="USER"> 普通用户
            </label>
            <label class="radio-inline">
                <input type="radio" name="user_role" value="ADMIN"> 管理员
            </label>
        </div>
    </div>

    <div class="form-group btn-group-justified">
        <div class="col-sm-offset-5 col-sm-8">
            <button type="submit" class="btn btn-default" id="zhuce">注&nbsp;&nbsp;册</button>
            <button type="reset" class="btn btn-default" id="reset">重&nbsp;&nbsp;置</button>
        </div>
    </div>
</form>
<script>
    $(function () {
        $("#zhuce").click(function () {
            var username = document.getElementById("username").value;
            var password = document.getElementById("password").value;
            var role = $('input:radio:checked').val();
            if (username.length == 0) {
                alert("请输入用户名！");
                return;
            } else if (username.length != 0 && password.length == 0) {
                alert("请输入密码！");
                return;
            } else if (role == null) {
                alert("请填写注册类型！");
                return;
            }
        });
    });
</script>
</body>
</html>
