<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;" pageEncoding="utf-8" %>
<%@ page isELIgnored ="false" %>
<!DOCTYPE html>
<html lang="zh-cn">
<head>
    <meta charset="utf-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <title>中邮绑卡操作</title>
    <!-- 引入 Bootstrap -->
    <link href="<c:url value="/resources/css/bootstrap/bootstrap.min.css"/>" rel="stylesheet" type="text/css">
    <!-- 可选的Bootstrap主题文件（一般不使用） -->
    <link href="https://cdn.datatables.net/1.10.15/css/jquery.dataTables.min.css" rel="stylesheet" type="text/css">
    <link href="<c:url value="/resources/css/buttons/buttons.css"/>" rel="stylesheet" type="text/css">
    <link href="<c:url value="/resources/css/video-js.css"/>" rel="stylesheet" type="text/css">
    <!-- Latest compiled and minified CSS -->
    <link rel="stylesheet" href="<c:url value="/resources/css/bootstrap/bootstrap-select.min.css"/>">
</head>

<body>

    <div class="container-fluid pull-left col-lg-12">
        <h1>中邮信审系统绑卡工具</h1>
        <form id="bindForm" action="<c:url value="/api/BindChargeCard"/>" method="post" style="align-self: baseline">
            <fieldset>
                <div class="form-group " style="padding-left: 15px">
                    <label>applyNo</label>
                    <input  type="text" class="form-control" style="width: auto "  name="applyno" placeholder="请输入申请号">
                    <br/>
                    <label>bankNo</label>
                    <input  type="text" class="form-control" style="width: auto"  name="bankno" placeholder="请输入银行卡号">
                    <br/>
                    <label>DataBase</label>
                    <label>
                        <select name="databases">
                            <option value="dev">dev</option>
                            <option value="xib">xib</option>
                            <option value="psbc">psbc</option>
                            <option value="local">local</option>
                        </select>
                    </label>
                </div>
            </fieldset>
            <div style="padding-left: 15px" class="dl-horizontal">
                <button id="subBtn" type="submit" class="btn btn-primary" >提交</button>
                <button id="updateBtn" type="button" class="btn btn-primary" >代扣</button>
            </div>
        </form>
        <br>
        <address style="padding-left: 15px " contenteditable="true"><strong>马铭锋</strong><br />
            厚本金融<br />
            <abbr title="email">Email:</abbr> mamingfeng@houbank.cn</address>
    </div>

    <div class="container-fluid col-lg-12" >
        <h2>定时任务调用方法</h2>
        <div style="padding-left: 15px">
            <button id="btn_invoke" type="submit" class="btn btn-primary">调用定时任务</button>
        </div>
    </div>

    <div class="container-fluid ">
        <button id="btn_add" type="button" class="btn btn-primary" onclick="openNewPage()">跳转</button>
    </div>


    <!-- jQuery文件。务必在bootstrap.min.js 之前引入 -->
    <script src="http://cdn.bootcss.com/jquery/2.1.1/jquery.min.js"></script>
    <!-- Latest compiled and minified JavaScript -->
    <script src="<c:url value="/resources/js/third/bootstrap/bootstrap-select.min.js"/>"></script>
    <script src="<c:url value="/resources/js/third/bootstrap/bootstrap.min.js"/>"></script>
    <!--引入datatables样式-->
    <script src="https://cdn.datatables.net/1.10.15/js/jquery.dataTables.min.js"></script>
    <!-- If you'd like to support IE8 -->
    <script src="<c:url value="/resources/js/third/video/videojs-ie8.min.js"/>"></script>
    <script src="<c:url value="/resources/js/third/video/video.min.js"/>"></script>
</body>
</html>

<script type="text/javascript">
    var restR = null;
    $("#btn_invoke").click(function () {
        $.ajax({
            url:'/api/schedule/onetime',
            type: 'GET',
            dataType:"text",
            success : function (data) {
                if(data.get("retcode").eq("00")){
                    alert("调用成功");
                }else {
                    alert("调用失败");
                }
            }
        })
    });

    $("#reportTable1").dataTable({
        "ajax":'/api/schedule/onetime'
    });
    
    var ret = "";
    $("#updateBtn").click(function () {
        var applyno = $("input[name ='applyno']").val();
        var request = {applyNo:applyno};

        console.log("请求数据:" + request.toString());
        if (confirm("是否要更新订单代扣状态" + applyno + "?")){
            $.ajax({
                async: false,
                url:'/api/updateCardStatus',
                type:'POST',
                dataType:"text",
                data:request,
                success: function (data) {
                    if(data.indexOf("successfully") > -1){
                        alert("更新成功");
                    }else {
                        alert("更新失败")
                    }
                }
            })
        }
    });


    /**
     * 获取下拉框中的值
     */
    $("#selectValue").click(function () {
//        var db = $("select[name ='databases'] option:selected").text();
    });


    function openNewPage(){
        window.location.href = '/executer-web/nav'
    }






</script>

