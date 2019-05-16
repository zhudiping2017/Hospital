
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@include file="/common/common.jsp"%>
<html>
<head>
    
    <title>Title</title>
    <script>
        $(function () {
            $("#addBtn").click(function () {
                location.href = "${path}/jsp/baseFunction/add.jsp";
            })
            $(".edit").click(function () {
                var fid = $(this).attr("name");
                location.href = "${path}/baseFunction/load.action?fid="+fid;
            })
            $(".del").click(function () {
                var fid = $(this).attr("name");
                location.href = "${path}/baseFunction/delete.action?fid="+fid;
            })
            $("#searchBtn").click(function () {
                var fname = $("#fname").val();
                location.href = "${path}/baseFunction/list.action?fname="+fname;
            })
        })
    </script>
</head>
<body class="main">
    <div class="search">
        <span>功能名称 <input type="text" id="fname" value="${searchObject.fname}"></span>

        <span>
            <button id="searchBtn">查询</button>
        </span>
        <span>
            <button id="addBtn">增加</button>
        </span>
    </div>
    <table cellpadding="0" cellspacing="0">
        <thead>
            <td>序号</td>
            <td>功能名称</td>
            <td>模块名称</td>
            <td>url</td>
            <td>编辑</td>
            <td>删除</td>
        </thead>
        <tbody>
            <c:forEach items="${list}" var="baseFunction" varStatus="status">
                <tr>
                    <td>${status.index+1}</td>
                    <td>${baseFunction.fname}</td>
                    <td>${baseFunction.baseModule.mname}</td>
                    <td>${baseFunction.url}</td>

                    <td><img src="${path}/images/edit.gif" alt="" class="edit" name="${baseFunction.fid}"></td>
                    <td><img src="${path}/images/del.gif" class="del" name="${baseFunction.fid}"></td>
                </tr>
            </c:forEach>
        </tbody>
    </table>
</body>
</html>
