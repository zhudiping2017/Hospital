<%@ page import="com.qhit.baseUser.pojo.BaseUser" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@include file="/common/common.jsp"%>
<html>
<head>
    <title>Title</title>

</head>

<body class="main">
<h1>修改用户</h1>
<form action="${path}/baseUser/update.action">
    <input type="hidden" name="userId" value="${user.userId}">
    <div class="update">
        <div class="left">
            <span>用户名</span>
            <input type="text" name="userName" value="${user.userName}">
        </div>
        <div class="right">
            <span>密码</span>
            <input type="text" name="password" value="${user.password}">
        </div>
        <div class="left">
            <span>姓名</span>
            <input type="text" name="cname" value="${user.cname}">
        </div>
        <div class="right">
            <span>性别</span>
            <input type="text" name="sex" value="${user.sex}">
        </div>
        <div id="error"></div>
        <div class="buttons">
            <input type="submit" value="提交">
            <input type="button" onclick="history.back()" value="返回">
        </div>
    </div>
</form>
</body>

</html>
