
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
    <script>
        $(function () {
            $("#oldPassword").blur(function () {
                var oldPassword = $("#oldPassword").val();
                $.get("${path}/baseUser/oldPassword.action",{password:oldPassword,userId:"${sessionUser.userId}"},function (data) {
                    if(data=='N'){
                        $("#oldPasswordError").html("旧密码错误");
                    }else{
                        $("#oldPasswordError").html("");
                    }
                })
            })
            $("#repeatPassword").blur(function () {
                var password= $("#password").val();
                var repeatPassword= $("#repeatPassword").val();
                if(password!=repeatPassword){
                    $("#repeatPasswordError").html("两次密码不一致");
                }else{
                    $("#repeatPasswordError").html("");
                }
            })
        })

        function submit() {
            var oldPassword = $("#oldPassword").val();
            var repeatPassword = $("#repeatPassword").val();
            var oldPasswordError = $("#oldPasswordError").html();
            var repeatPasswordError = $("#repeatPasswordError").html();
            var password = $("#password").val();
            if(!oldPassword||!repeatPassword||oldPasswordError||repeatPasswordError){
                alert("您没有通过验证，请检查输入");
                return false;
            }else{
                $.get("${path}/baseUser/updatePassword.action",{password:password,userId:"${sessionUser.userId}"},function (data) {
                    if(data=='Y'){
                        alert("密码更改成功");
                        window.close();
                    }
                })
            }

        }

    </script>
</head>

<body class="main">
    <h1>修改密码</h1>
        <div class="update">
            <div class="left">
                <span>请输入旧密码</span>
                <input type="text" id="oldPassword"><span id="oldPasswordError"></span>
            </div>
            <div class="right">
            </div>
            <div class="left">
                <span>新密码</span>
                <input type="password" name="password" id="password">
            </div>
            <div class="right">
            </div>
            <div class="left">
                <span>确认密码</span>
                <input type="password" id="repeatPassword"><span id="repeatPasswordError"></span>
            </div>
            <div class="buttons">
                <input type="button" value="提交" onclick="submit()">
                <input type="button" onclick="history.back()" value="返回">
            </div>
        </div>
</body>

</html>
