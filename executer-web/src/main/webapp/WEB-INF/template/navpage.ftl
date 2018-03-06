<html>
<#import "common/common.macro" as netCommon>
<head>
    <@netCommon.commonStyle></@netCommon.commonStyle>
    <title>银行渠道测试工具</title>
</head>
<body>
    <div class="row">
    <div class="container">
        <nav class="navbar navbar-default navbar-fixed-top nav-mark" role="navigation">
            <div class="navbar-header">
                <div class="navbar-brand">信审测试工具</div>
                <button type="button" class="navbar-toggle collapsed" data-toggle="collapse" data-target="#navbar-menu" aria-expanded="false">
                    <span class="sr-only">Toggle navigation</span>
                    <span class="icon-bar"/>
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
                            <li role="presentation">
                                <a role="menuitem" tabindex="0" onclick="openMonitor()" href="#">系统信息监控</a>
                            </li>
                            <li role="presentation">
                                <a role="menuitem" tabindex="1" onclick="openMonitor()" href="#">系统信息</a>
                            </li>
                        </ul>
                    </li>
                    <li>
                        <a href="#" onclick="display('psbc')">中邮测试工具</a>
                    </li>
                    <li>
                        <a href="#" onclick="display('xib')">厦门测试工具</a>
                    </li>
                    <li>
                        <a>信审绑卡工具</a>
                    </li>
                </ul>
            </div>
        </nav>
    </div>
</div>


    <div id="xib" class="tab-content" hidden="hidden">
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
                                 <td>
                                     <input type="button" class="btn btn-default btn-primary" value="更新三方状态" onclick="updateThirdStatus()">
                                     <input type="button" class="btn brn-default btn-primary" value="刷新数据" onclick="refreshTable()">
                                 </td>

                             </tr>
                         </table>
                     </form>
                 </div>
             </div>
     </div>

    <div id="psbc" class="tab-content" hidden="hidden" >
            <form id="form_db" autocomplete="on">
                <table>
                    <tr>
                        <td>数据库类型:</td>
                        <td>
                            <input type="text" id="dbType1" name="dbType1" value="mysql" size="60" />
                        </td>
                        <td>数据库类型,例如:mysql,oracle</td>
                    </tr>

                    <tr>
                        <td>ip地址:</td>
                        <td>
                            <input type="text" id="ip1" name="ip1" value="10.150.20.87" size="60" />
                        </td>
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
                        <td><textarea id="sql" name="sql" class="input-lg" style="width: auto; height: auto;"/></td>
                    </tr>

                    <tr style="text-align: left">
                        <td>
                            <input type="button" value="提交" class="btn-primary"
                                   onclick="invoke('form_db','db/exec')">&nbsp;&nbsp;
                        </td>
                    </tr>

                </table>
            </form>

        </div>


    <@netCommon.commonScript></@netCommon.commonScript>
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
                    console.log(result);
                    confirm("调用成功");
                },
                complete:function(){
                    //请求结束时
                    confirm("结果返回成功");
                },
                error:function(){
                    //请求失败时
                    alert("请求失败");
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

        function display(bank) {
            let tabContents = document.getElementsByClassName("tab-content");
            for(let i=0; i<tabContents.length; i++){
                let tab = tabContents[i];
                if (tab.getAttribute("id") !== bank){
                    tab.setAttribute('hidden','hidden');
                }else {
                    tab.removeAttribute("hidden")
                }
            }
        }
    </script>

    <@netCommon.commonFooter></@netCommon.commonFooter>
</body>

</html>