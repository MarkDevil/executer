<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%--
  Created by IntelliJ IDEA.
  User: mark
  Date: 2017/7/21
  Time: 下午5:22
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>

<html>
<head>
    <!-- 引入 Bootstrap -->
    <link href="<c:url value="/resources/css/bootstrap/bootstrap.min.css"/>" rel="stylesheet" type="text/css">
    <!-- 可选的Bootstrap主题文件（一般不使用） -->
    <link href="https://cdn.datatables.net/1.10.15/css/jquery.dataTables.min.css" rel="stylesheet" type="text/css">
    <link href="<c:url value="/resources/css/buttons/buttons.css"/>" rel="stylesheet" type="text/css">
    <link href="<c:url value="/resources/css/video-js.css"/>" rel="stylesheet" type="text/css">
    <!-- Latest compiled and minified CSS -->
    <link rel="stylesheet" href="<c:url value="/resources/css/bootstrap/bootstrap-select.min.css"/>">
    <link rel="stylesheet" href="<c:url value="//cdnjs.cloudflare.com/ajax/libs/bootstrap-table/1.11.1/bootstrap-table.min.css"/>">
    <title>Nav testing tool</title>
</head>
<body>
    <ul id="myTab" class="nav nav-tabs">

        <li class="dropdown">
            <a href="#" id="testTool" class="dropdown-toggle"
               data-toggle="dropdown">
                测试工具集
                <b class="caret"></b>
            </a>
            <ul class="dropdown-menu" role="menu" aria-labelledby="myTabDrop1">
                <li><a data-toggle="tab" onclick="openMonitor()">系统信息监控</a></li>
                <li><a href="#"  data-toggle="tab">系统信息</a></li>
            </ul>
        </li>


        <li>
            <a href="#psbc" data-toggle="tab">中邮银行</a>
        </li>

        <li class="dropdown">
            <a href="#" id="myTabDrop1" class="dropdown-toggle"
               data-toggle="dropdown">厦门国际
                <b class="caret"></b>
            </a>
            <ul class="dropdown-menu" role="menu" aria-labelledby="myTabDrop1">
                <li><a href="#jmeter"  data-toggle="tab">bootStrap数据测试</a></li>
                <li><a href="#ejb"  data-toggle="tab">ejb</a></li>
            </ul>
        </li>
    </ul>

    <div id="myTabContent" class="tab-content">
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
                            <td><input type="button" value="更新三方状态" onclick="updateThirdStatus()">&nbsp;&nbsp;
                                <input type="button" value="刷新数据" onclick="refreshTable()">
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
        <div class="tab-pane fade" id="jmeter">
            <table id="bt-table" data-toggle="table" class="table table-hover">
                <thead>
                    <tr>
                        <th data-field="id">编号</th>
                        <th data-field="name">姓名</th>
                    </tr>
                </thead>
            </table>
        </div>


        <div class="tab-pane fade" id="ejb">
            <p>Enterprise Java Beans</p>
        </div>


    </div>

    <div id="monitor" class="tab-pane focus">
        <table id="bt-table1" data-toggle="table" class="table table-hover">
            <thead>
            <tr>
                <th data-field="id">编号</th>
                <th data-field="name">姓名</th>
            </tr>
            </thead>
        </table>
    </div>

    <script src="https://code.jquery.com/jquery.js"></script>
    <script src="<c:url value="/resources/js/third/bootstrap/bootstrap-select.min.js"/>"></script>
    <script src="<c:url value="/resources/js/third/bootstrap/bootstrap.min.js"/>"></script>
    <script src="https://cdn.datatables.net/1.10.15/js/jquery.dataTables.min.js"></script>
    <script src="<c:url value="/resources/js/third/video/videojs-ie8.min.js"/>"></script>
    <script src="<c:url value="/resources/js/third/video/video.min.js"/>"></script>
    <script src="http://cdn.bootcss.com/jquery.form/4.2.1/jquery.form.min.js"></script>
    <script src="//cdnjs.cloudflare.com/ajax/libs/bootstrap-table/1.11.1/bootstrap-table.min.js"></script>
    <script src="//cdnjs.cloudflare.com/ajax/libs/bootstrap-table/1.11.1/locale/bootstrap-table-zh-CN.min.js"></script>

    <script>
        function invoke(formid,url){
            let form = $('#' + formid).formSerialize();
            console.log(form);
            $.ajax({
                url:url,
                data:form,
                cache:false,//false是不缓存，true为缓存
                async:true,//true为异步，false为同步
                beforeSend:function(){
                    //请求前
                },
                success:function(result){
                    alert("调用成功");
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
            window.location.href = "/executer-web/monitor";
        }



    </script>

</body>
</html>
