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
    <title>Nav testing tool</title>
</head>
<body>


    <ul id="myTab" class="nav nav-tabs">
        <li class="active">
            <a href="#home" data-toggle="tab">
                测试工具集
            </a>
        </li>
        <li><a href="#ios" data-toggle="tab">iOS</a></li>
        <li class="dropdown">
            <a href="#" id="myTabDrop1" class="dropdown-toggle"
               data-toggle="dropdown">Java
                <b class="caret"></b>
            </a>
            <ul class="dropdown-menu" role="menu" aria-labelledby="myTabDrop1">
                <li><a href="#jmeter" tabindex="-1" data-toggle="tab">jmeter</a></li>
                <li><a href="#ejb" tabindex="-1" data-toggle="tab">ejb</a></li>
            </ul>
        </li>
    </ul>
    <div id="myTabContent" class="tab-content">
        <div class="tab-pane fade in active" id="home">
            <div>
                <form name="form1" method="post" autocomplete="on">
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
        <div class="tab-pane fade" id="ios">
            <p>iOS 是一个由苹果公司开发和发布的手机操作系统。最初是于 2007 年首次发布 iPhone、iPod Touch 和 Apple
                TV。iOS 派生自 OS X，它们共享 Darwin 基础。OS X 操作系统是用在苹果电脑上，iOS 是苹果的移动版本。</p>
        </div>
        <div class="tab-pane fade" id="jmeter">
            <p>jMeter 是一款开源的测试软件。它是 100% 纯 Java 应用程序，用于负载和性能测试。</p>
        </div>
        <div class="tab-pane fade" id="ejb">
            <p>Enterprise Java Beans（EJB）是一个创建高度可扩展性和强大企业级应用程序的开发架构，部署在兼容应用程序服务器（比如 JBOSS、Web Logic 等）的 J2EE 上。
            </p>
        </div>
    </div>

    <!-- Latest compiled and minified JavaScript -->
    <script src="<c:url value="/resources/js/third/bootstrap/bootstrap-select.min.js"/>"></script>
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
</body>
</html>
