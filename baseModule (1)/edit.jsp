
<%@ page contentType="text/html;charset=UTF-8" language="java" %> 
<%@include file="/common/common.jsp"%> 
<html> 
<head> 
    <title>Title</title>
</head> 

<body class="main"> 
<form action="${path}/baseModule/update.action"> 
    <h1>修改模块</h1> 
    <input type="hidden" name="mid" value="${baseModule.mid}"> 
    <div class="update"> 
        <div class="left"> 
            <span>模块名称</span> 
            <input type="text" name="mname" value="${baseModule.mname}"> 
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
 
