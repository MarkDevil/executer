<!DOCTYPE html>
<html>
<head>
    <title>测试API管理平台</title>
    <#import "common/common.macro" as netCommon>
    <@netCommon.commonStyle />
    <link rel="stylesheet" href="${request.contextPath}/resources/static/adminlte/plugins/iCheck/square/blue.css">
</head>
<body class="hold-transition login-page">
    <div class="jumbotron">
        <div class="container">
            <h1>测试API管理平台</h1>
            <p>用户接口测试的API测试平台</p>
        </div>
    </div>
    <div class="login-box">
        <form id="loginForm" method="post" >
            <div class="login-box-body">
                <p class="login-box-msg">测试管理平台</p>
                <div class="form-group has-feedback">
                    <input type="text" name="userName" class="form-control" placeholder="请输入登陆账号" value="mamingfeng" >
                    <span class="glyphicon glyphicon-envelope form-control-feedback"/>
                </div>
                <div class="form-group has-feedback">
                    <input type="password" name="password" class="form-control" placeholder="请输入登陆密码" value="123456" >
                    <span class="glyphicon glyphicon-lock form-control-feedback"/>
                </div>
                <div class="row">
                    <div class="col-xs-8">
                        <div class="checkbox icheck">
                            <label>
                                <input type="checkbox" name="ifRemember"> Remember Me
                            </label>
                        </div>
                    </div>
                    <div class="col-xs-4">
                        <button type="submit" class="btn btn-primary btn-block btn-flat">登陆</button>
                    </div>
                </div>
            </div>
        </form>
    </div>
    <@netCommon.commonScript />
    <script src="${request.contextPath}/resources/static/plugins/jquery/jquery.validate.min.js"></script>
    <script src="${request.contextPath}/resources/static/adminlte/plugins/iCheck/icheck.min.js"></script>
    <script src="${request.contextPath}/resources/static/js/login.1.js"></script>

    </body>
</html>
