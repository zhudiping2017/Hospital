
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@include file="/common/common.jsp"%>
<html>
<head>
    <title>Title</title>

    <script>
        $(function () {
            $("#addBtn").click(function () {
                location.href="${path}/jsp/baseRole/add.jsp";
            })
            $(".update").click(function () {
                var rid = $(this).attr("name");
                location.href="${path}/baseRole/load.action?rid="+rid;
            })
            $(".delete").click(function () {
                var rid = $(this).attr("name");
                location.href="${path}/baseRole/delete.action?rid="+rid;
            })
            $("#search").click(function () {
                var rname = $("#rname").val();
                location.href = "${path}/baseRole/search.action?rname="+rname;
            })
            $(".distribute").click(function () {
                var rid = $(this).attr("name");
                location.href="${path}/baseRole/distributeLoad.action?rid="+rid;
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
            <td>角色</td>
            <td>修改</td>
            <td>删除</td>
            <td>授权</td>
        </thead>
        <c:forEach items="${list}" var="baseRole" varStatus="status">
            <tr>
                <td>${status.index+1}</td>
                <td>${baseRole.rname}</td>
                <td><img src="${path}/images/edit.gif" class="update" name="${baseRole.rid}"></td>
                <td><img src="${path}/images/del.gif" class="delete" name="${baseRole.rid}"></td>
                <td><img src="${path}/images/distribute.gif" class="distribute" name="${baseRole.rid}"></td>
            </tr>
        </c:forEach>
    </table>


</body>
</html>
