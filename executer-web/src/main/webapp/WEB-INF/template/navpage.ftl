<html>
<head>
    <!-- 引入 Bootstrap -->
    <link href="${request.contextPath}/resources/css/bootstrap/bootstrap.min.css" rel="stylesheet" type="text/css">
    <!-- 可选的Bootstrap主题文件（一般不使用） -->
    <link href="https://cdn.datatables.net/1.10.15/css/jquery.dataTables.min.css" rel="stylesheet" type="text/css">
    <link href="${request.contextPath}/resources/css/buttons/buttons.css" rel="stylesheet" type="text/css">
    <link href="${request.contextPath}/resources/css/video-js.css" rel="stylesheet" type="text/css">
    <!-- Latest compiled and minified CSS -->
    <link rel="stylesheet" href="${request.contextPath}/resources/css/bootstrap/bootstrap-select.min.css"/>
    <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/bootstrap-table/1.11.1/bootstrap-table.min.css"/>
    <title>银行渠道测试工具</title>
</head>
<body>

<h1 style="margin-left: 30px">后台管理系统</h1>
<div class="container-fluid" >
    <div class="col-lg-12">
        <nav class="navbar nav-justified navbar-inverse">

            <div class="navbar-header">
                <button type="button" class="navbar-toggle collapsed" data-toggle="collapse" data-target="#navbar-menu" aria-expanded="false">
                    <span class="sr-only">Toggle navigation</span>
                    <span class="icon-bar"/>
                    <span class="icon-bar"/>
                    <span class="icon-bar"/>
                </button>
            </div>

            <div id="navbar-menu" class="collapse navbar-collapse">
                <ul class="nav navbar-nav">
                    <li class="active">
                        <li class="dropdown">
                            <a class="dropdown-toggle" id="dropdownMenu1"
                                    data-toggle="dropdown">
                                测试工具集
                                <span class="caret"/>
                            </a>
                            <ul class="dropdown-menu" role="menu" aria-labelledby="dropdownMenu1">
                                <li role="presentation"><a role="menuitem" tabindex="0" onclick="openMonitor()" href="#">系统信息监控</a></li>
                                <li role="presentation"><a role="menuitem" tabindex="1" onclick="openMonitor()" href="#">系统信息</a></li>
                            </ul>
                        </li>
                    <li>
                        <a href="#" onclick="display()" >中邮测试工具</a>
                    </li>
                    <li><a href="#" onclick="display()">厦门测试工具</a></li>
                </ul>
            </div>

        </nav>
    </div>

</div>


<div id="myTabContent" class="tab-content" hidden="hidden" style="margin-left: 30px">
    <div class="tab-pane fade in active" id="home">
        <h1>厦门银行修改三方状态</h1>
        <div>
            <form id="form1" method="post" autocomplete="on">
                <table>
                    <tr>
                        <td>申请单号:</td>
                        <td><input type="text" id="applyNo" name="applyNo" size="60" /></td>
                        <td>信审申请订单号,例如:A00421003011707110001</td>
                    </tr>

                    <tr>
                        <td>数据库类型:</td>
                        <td><input type="text" id="dbType"  value="mysql" size="60" /></td>
                        <td>数据库类型,例如:mysql,oracle</td>
                    </tr>

                    <tr>
                        <td>ip地址:</td>
                        <td><input type="text" id="ip" value="10.150.20.87" size="60" /></td>
                        <td>IP地址</td>
                    </tr>

                    <tr>
                        <td>端口号:</td>
                        <td><input type="text" id="port" value="3306" size="60" /></td>
                        <td>端口号</td>
                    </tr>

                    <tr>
                        <td>数据库库名:</td>
                        <td><input type="text" id="dbname" value="hb" size="60" /></td>
                        <td>数据库库名</td>
                    </tr>

                    <tr>
                        <td>数据库用户名:</td>
                        <td><input type="text" id="username" value="root" size="60" /></td>
                        <td>数据库用户名</td>
                    </tr>

                    <tr>
                        <td>数据库密码:</td>
                        <td><input type="text" id="passwd" value="root" size="60" /></td>
                        <td>数据库密码</td>
                    </tr>

                    <tr style="text-align: left">
                        <td></td>
                        <td><input type="button" class="button" value="更新三方状态" onclick="updateThirdStatus()">&nbsp;&nbsp;
                            <input type="button" class="button" value="刷新数据" onclick="refreshTable()">
                        </td>

                    </tr>
                </table>
            </form>
        </div>
        <table id="logsTable" class="display dataTable" cellspacing="0" width="95%">
            <thead>
            <tr>
                <th>BatchNo</th>
                <th>dbsql</th>
                <th>createTime</th>
            </tr>
            </thead>
            <tbody></tbody>
        </table>
    </div>
    <div id="psbc" class="tab-pane fade" >

        <form id="form_db" autocomplete="on">
            <table>
                <tr>
                    <td>数据库类型:</td>
                    <td><input type="text" id="dbType1" name="dbType1" value="mysql" size="60" /></td>
                    <td>数据库类型,例如:mysql,oracle</td>
                </tr>

                <tr>
                    <td>ip地址:</td>
                    <td><input type="text" id="ip1" name="ip1" value="10.150.20.87" size="60" /></td>
                    <td>IP地址</td>
                </tr>

                <tr>
                    <td>端口号:</td>
                    <td><input type="text" id="port1" name="port1" value="3306" size="60" /></td>
                    <td>端口号</td>
                </tr>

                <tr>
                    <td>数据库库名:</td>
                    <td><input type="text" id="dbname1" name="dbname1" value="hb" size="60" /></td>
                    <td>数据库库名</td>
                </tr>

                <tr>
                    <td>数据库用户名:</td>
                    <td><input type="text" id="username1" name="username1" value="root" size="60" /></td>
                    <td>数据库用户名</td>
                </tr>

                <tr>
                    <td>数据库密码:</td>
                    <td><input type="text" id="passwd1" name="passwd1" value="root" size="60" /></td>
                    <td>数据库密码</td>
                </tr>

                <tr>
                    <td>执行sql:</td>
                    <td><textarea id="sql" name="sql" class="input-lg" style = "width: 808px; height: 597px;"></textarea></td>
                </tr>

                <tr style="text-align: left">
                    <td></td>
                    <td><input type="button" value="提交" class="btn-primary"
                               onclick="invoke('form_db','db/exec')">&nbsp;&nbsp;
                    </td>

                </tr>

            </table>
        </form>

    </div>


    <div class="tab-pane fade" id="ejb">
        <p>Enterprise Java Beans</p>
    </div>


</div>


<script src="https://code.jquery.com/jquery.js"></script>
<script src="${request.contextPath}/resources/js/third/bootstrap/bootstrap-select.min.js" ></script>
<script src="${request.contextPath}/resources/js/third/bootstrap/bootstrap.min.js"></script>
<script src="https://cdn.datatables.net/1.10.15/js/jquery.dataTables.min.js" ></script>
<script src="${request.contextPath}/resources/js/third/video/videojs-ie8.min.js"></script>
<script src="${request.contextPath}/resources/js/third/video/video.min.js"></script>
<script src="http://cdn.bootcss.com/jquery.form/4.2.1/jquery.form.min.js"></script>
<script src="http://cdnjs.cloudflare.com/ajax/libs/bootstrap-table/1.11.1/bootstrap-table.min.js"></script>
<script src="http://cdnjs.cloudflare.com/ajax/libs/bootstrap-table/1.11.1/locale/bootstrap-table-zh-CN.min.js"></script>

<script>
    function invoke(formid,url){
        let form = $('#' + formid).formSerialize();
        console.log(form);
        $.ajax({
            type:"post",
            url:url,
            data:form,
            cache:false,//false是不缓存，true为缓存
            async:true,//true为异步，false为同步
            beforeSend:function(){
                //请求前
            },
            success:function(result){
                prompt("调用成功");
            },
            complete:function(){
                //请求结束时
            },
            error:function(){
                //请求失败时
            }
        });

    }

    $.ajax({
        type:"get",
        url:"/executer-web/api/testDataList",
        dataType:"json",
        success: function (data) {
            $("#bt-table").bootstrapTable('load',data);
        }

    });

    function openMonitor() {
        window.location.href = "/executer-web/monitor.ftl";
    }

    function display() {
        $('#myTabContent').removeAttr('hidden');
    }

</script>

</body>
</html>
