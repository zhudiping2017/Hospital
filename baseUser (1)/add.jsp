
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@include file="/common/common.jsp"%>
<html>
<head>
    <title>Title</title>
    <style>
        span{
            display: inline-block;
            width: 30%;
        }
        input[type='text'],input[type='datetime']{
            margin-top: 10px;
        }
    </style>
</head>

<body class="main">
    <h1>添加用户</h1>
    <form action="${path}/baseUser/insert.action">
        <div class="update">
            <div class="left">
                <span>用户名</span>
                <input type="text" name="userName">
            </div>
            <div class="right">
                <span>密码</span>
                <input type="password" name="password">
            </div>
            <div class="left">
                <span>姓名</span>
                <input type="text" name="cname">
            </div>
            <div class="right">
                <span>性别</span>
                <input type="text" name="sex">
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
