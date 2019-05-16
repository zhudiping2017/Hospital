<%-- 
  Created by IntelliJ IDEA. 
  2018/12/06 
  To change this template use File | Settings | File Templates. 
--%> 
<%@ page contentType="text/html;charset=UTF-8" language="java" %> 
<%@include file="/common/common.jsp"%> 
<html> 
<head> 
    <script type="text/javascript"> 
        $(function () { 
            $("#addBtn").click(function () { 
                location.href="${path}/jsp/medicinePurchaseInfo/add.jsp";
             })
            $(".update").click(function () { 
                var pchId = $(this).attr("name");
                location.href="${path}/medicinePurchaseInfo/load.action?pchId="+pchId;
             })
            $(".delete").click(function () { 
                var pchId = $(this).attr("name");
                location.href="${path}/medicinePurchaseInfo/delete.action?pchId="+pchId;
             })
            $("#search").click(function () { 
                var medicineCodeid = $("#medicineCodeid").val();
                location.href = "${path}/medicinePurchaseInfo/search.action?medicineCodeid="+medicineCodeid;
             })
            $("#instockBtn").click(function () {
                var url = "${path}/medicinePurchaseInfo/instock.action?a=a";
                $(":checked").each(function () {
                    var pchId=$(this).val();
                    var invno = $(this).parent().parent().find(":text").val();
                    url+="&info="+pchId+","+invno;
                })
                location.href = url;
            })
            $(".invno").keyup(function (event) {
                var code = event.keyCode;
                var row = parseInt($(this).attr("id"));
                if(code==38){
//                    向上键
                    $("#"+(row-1)).focus();
                }else if(code==40){
//                    向下键
                    $("#"+(row+1)).focus();
                }
            })
        })  
    </script> 
</head> 
<body class="main"> 
    <div class="search"> 
        <span> 
            药品：<input type="text" id="medicineCodeid" value="${searchObject.medicineCodeid}"> 
        </span> 
        <span> 
            <button id="search">查询</button> 
        </span> 
        <span> 
            <button id="addBtn">增加</button> 
        </span>
        <span>
            <button id="instockBtn">入库</button>
        </span>
    </div> 
    <table> 
        <thead> 
            <td></td>
            <td>序号</td>
            <td>药品</td>
            <td>供应商</td> 
            <td>采购数量</td> 
            <td>采购单价</td> 
            <td>采购总价</td> 
            <td>发票号</td>
            <td>状态</td>
            <td>审批人</td>
            <td>审批日期</td>
        </thead>
        <c:forEach items="${list}" var="medicinePurchaseInfo" varStatus="status"> 
            <tr> 
                <td><input type="checkbox" value="${medicinePurchaseInfo.pchId}"></td>
                <td>${status.index+1}</td>
                <td>${medicinePurchaseInfo.medicineCode.medicineName}</td>
                <td>${medicinePurchaseInfo.baseManufacturer.manChnName}</td>
                <td>${medicinePurchaseInfo.pchAmt}</td> 
                <td>${medicinePurchaseInfo.pchPrice}</td> 
                <td>${medicinePurchaseInfo.pchTotal}</td>
                <td><input type="text" id="${status.index+1}" class="invno"></td>
                <c:choose>
                    <c:when test="${medicinePurchaseInfo.status==1}">
                        <td>采购未审批</td>
                    </c:when>
                    <c:when test="${medicinePurchaseInfo.status==2}">
                        <td>采购已审批</td>
                    </c:when>
                    <c:otherwise>
                        <td>采购已入库</td>
                    </c:otherwise>
                </c:choose>

                <td>${medicinePurchaseInfo.apprvUserid}</td>
                <td>${medicinePurchaseInfo.apprvDate}</td> 
            </tr>
        </c:forEach> 
    </table> 
</body> 
</html> 
