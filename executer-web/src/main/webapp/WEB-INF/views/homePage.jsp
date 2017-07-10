<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;" pageEncoding="utf-8" %>
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
    <script src="https://code.jquery.com/jquery.js"></script>
    <!-- jQuery文件。务必在bootstrap.min.js 之前引入 -->
    <script src="http://cdn.bootcss.com/jquery/2.1.1/jquery.min.js"></script>
    <script src="<c:url value="/resources/js/third/bootstrap/bootstrap.min.js"/>"></script>
    <script src="<c:url value="/resources/js/third/bootstrap/bootstrap.js"/>"></script>
    <!--引入datatables样式-->
    <script src="https://cdn.datatables.net/1.10.15/js/jquery.dataTables.min.js"></script>
    <!-- If you'd like to support IE8 -->
    <script src="<c:url value="/resources/js/third/video/videojs-ie8.min.js"/>"></script>
    <script src="<c:url value="/resources/js/third/video/video.min.js"/>"></script>

</head>

<body>

    <div class="container-fluid">
        <h1>中邮信审系统绑卡工具</h1>
        <form id="bindForm" action="<c:url value="/api/BindChargeCard"/>" method="post" style="align-self: baseline">
            <fieldset>
                <div class="form-group " style="padding-left: 15px">
                    <label>applyNo</label>
                    <input  type="text" class="form-control" style="width: auto "  name="applyno" placeholder="请输入申请号">
                    <br/>
                    <label>bankNo</label>
                    <input  type="text" class="form-control" style="width: auto"  name="bankno" placeholder="请输入银行卡号">
                </div>
            </fieldset>
            <div style="padding-left: 15px">
                <button id="subBtn" type="submit" class="btn btn-default" >submit</button>
            </div>
        </form>
    </div>

    <div class="container-fluid">
        <h2>定时任务调用方法</h2>
        <div style="padding-left: 15px">
            <button id="btn_invoke" type="submit" class="btn btn-default">invoke task</button>
        </div>
    </div>

    <blockquote></blockquote>


    <!--<div class="container-fluid">-->
            <!--<video id="my-video" class="video-js" controls preload="auto" width="auto" height="auto"-->
                   <!--poster="MY_VIDEO_POSTER.jpg" data-setup="{}">-->
                <!--<source src="http://vjs.zencdn.net/v/oceans.mp4" type="video/mp4">-->
                <!--&lt;!&ndash;<p class="vjs-no-js">&ndash;&gt;-->
                    <!--&lt;!&ndash;To view this video please enable JavaScript, and consider upgrading to a web browser that&ndash;&gt;-->
                    <!--&lt;!&ndash;<a href="http://videojs.com/html5-video-support/" target="_blank">supports HTML5 video</a>&ndash;&gt;-->
                <!--&lt;!&ndash;</p>&ndash;&gt;-->
            <!--</video>-->
    <!--</div>-->
    <!--<div class="container-fluid">-->
        <!--<table id="reportTable1" class="display" cellspacing="0" width="100%">-->
            <!--<thead>-->
            <!--<tr>-->
                <!--<th>Name</th>-->
                <!--<th>Position</th>-->
            <!--</tr>-->
            <!--</thead>-->
        <!--</table>-->
    <!--</div>-->
    <address style="padding-left: 15px " contenteditable="true"><strong>马铭锋</strong><br />
        795 Folsom Ave, Suite 600<br />
        San Francisco, CA 94107<br />
        <abbr title="Phone">P:</abbr> (123) 456-7890</address>
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
                alert(data)
            }
        })
    });

    $("#reportTable1").dataTable({
        "ajax":'/api/schedule/onetime'
    });
    



    $("#subBtn").click(function () {
        var applyno = $("input[name ='applyno']").val();
        var bankno = $("input[name ='bankno']").val();
        alert(applyno + bankno);
        $.ajax({
            url:'/api/schedule/onetime',
            type: 'GET',
            dataType:"text",
            success : function (data) {
                alert(data)
            }
        })
    });
</script>

