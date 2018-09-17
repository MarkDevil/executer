<html>
<head>
    <title>System monitor</title>
    <#import "common/common.macro" as comm>
    <@comm.commonStyle/>
</head>
<body>
<@comm.commonHeader></@comm.commonHeader>
<div class="main-bank-header">
    <div class="container">
        <h1>数据库同步工具</h1>
        <p>通过指定源数据库和目标数据库来获取缺失的数据信息</p>
    </div>
</div>
<div>
    <div class="row">
        <div class="col-lg-pull-6">
            <div class="container">
                <form id="compareForm" action="/executer-web/db/compare" method="post" role="form">
                    <legend>数据库信息</legend>
                    <div class="form-group">
                        <label for="源数据库名"/>
                        <input type="text" class="form-control" name="sourceDbName" placeholder="源数据库名" value="hb" size="60">
                        <label for="源数据库ip"/>
                        <input type="text" class="form-control" name="sourceDbIp" placeholder="源数据库ip" value="localhost" size="60">
                        <label for="源数据库port"/>
                        <input type="text" class="form-control" name="sourceDbPort" placeholder="源数据库port" value="3307" size="60">
                        <label for="源数据库user"/>
                        <input type="text" class="form-control" name="sourceDbUser" placeholder="源数据库user" value="root" size="60">
                        <label for="源数据库passwd"/>
                        <input type="text" class="form-control" name="sourceDbPasswd" placeholder="源数据库passwd" value="root" size="60">

                        <label for="目标数据库名"/>
                        <input type="text" class="form-control" name="targetDbName" placeholder="目标数据库名" value="hb" size="60">
                        <label for="目标数据库ip"/>
                        <input type="text" class="form-control" name="targetDbIp" placeholder="目标数据库ip" value="localhost" size="60">
                        <label for="目标数据库port"/>
                        <input type="text" class="form-control" name="targetDbPort" placeholder="目标数据库port" value="3307" size="60">
                        <label for="目标数据库user"/>
                        <input type="text" class="form-control" name="targetDbUser" placeholder="目标数据库user" value="root" size="60">
                        <label for="目标数据库passwd"/>
                        <input type="text" class="form-control" name="targetDbPasswd" placeholder="目标数据库passwd" value="root" size="60">
                    </div>
                    <button type="submit" class="btn btn-primary" style="position: relative ;bottom: 50px">Submit</button>
                </form>
            </div>
        </div>
        <div class="col-lg-pull-6">
            <div class="container">
                <a>返回信息：</a>
                <br>
                <div class="well-lg" id="retText"></div>
            </div>
        </div>

    </div>

</div>


<@comm.commonFooter></@comm.commonFooter>
<@comm.commonScript></@comm.commonScript>

<script type="text/javascript">
    let retAera = document.getElementById("retText");
    $(document).ready(function () {
        $("#compareForm").ajaxForm(function (data) {
            $.each(data,function (index,value) {
                console.log(value)
            });
            // retAera.innerText = JSON.stringify(data);
        })
    })
</script>
</body>


</html>
