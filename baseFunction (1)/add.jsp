
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
            $.get("${path}/baseModule/ajaxList.action",function (data) {
                var $mid = $("#mid");
                var $op = $("<option></option>");
                $mid.append($op);
                $(data).each(function () {
                    var $option = $("<option value='"+this.mid+"'>"+this.mname+"</option>");
                    $option.appendTo($mid);
                })
            },"json")

        })
    </script>
</head>

<body class="main">
    <h1>添加功能</h1>
    <form action="${path}/baseFunction/insert.action">
        <div class="update">
            <div class="left">
                <span>功能名称</span>
                <input type="text" name="fname">
            </div>
            <div class="right">
                <span>模块名称</span>
                <select name="mid" id="mid"></select>
            </div>
            <div class="left">
                <span>url</span>
                <input type="text" name="url">
            </div>
            <div class="right">
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
