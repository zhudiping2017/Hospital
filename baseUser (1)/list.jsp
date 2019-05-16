
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@include file="/common/common.jsp"%>
<html>
<head>
    <title>Title</title>

    <script>
        $(function () {
            $("#addBtn").click(function () {
                location.href="${path}/jsp/baseUser/add.jsp";
            })
            $(".update").click(function () {
                var userId = $(this).attr("name");
                location.href="${path}/baseUser/load.action?userId="+userId;
            })
            $(".delete").click(function () {
                var userId = $(this).attr("name");
                location.href="${path}/baseUser/delete.action?userId="+userId;
            })
            $("#search").click(function () {
                var cname = $("#cname").val();
                var sex = $("#sex").val();
                location.href = "${path}/baseUser/list.action?cname="+cname+"&sex="+sex;
            })
            $(".distribute").click(function () {
                var userId = $(this).attr("name");
                location.href="${path}/baseUser/distributeLoad.action?userId="+userId;
            })

        })
    </script>
</head>
<body class="main">
    <div class="search">
        <span>
            姓名：<input type="text" id="cname" value="${searchObject.cname}">
        </span>
        <span>
            性别：<input type="text" id="sex" value="${searchObject.sex}">
        </span>
        <span>
            <button id="search">查询</button>
        </span>
        <span>
            <button id="addBtn">增加</button>
        </span>
    </div>




    <table>
        <thead>
            <td>序号</td>
            <td>用户名</td>
            <td>姓名</td>
            <td>性别</td>
            <td>修改</td>
            <td>删除</td>
            <td>分配</td>
        </thead>
        <c:forEach items="${list}" var="baseUser" varStatus="status">
            <tr>
                <td>${status.index+1}</td>
                <td>${baseUser.userName}</td>
                <td>${baseUser.cname}</td>
                <td>${baseUser.sex}</td>
                <td><img src="${path}/images/edit.gif" class="update" name="${baseUser.userId}"></td>
                <td><img src="${path}/images/del.gif" class="delete" name="${baseUser.userId}"></td>
                <td><img src="${path}/images/distribute.gif" class="distribute" name="${baseUser.userId}"></td>
            </tr>
        </c:forEach>
    </table>


</body>
</html>
