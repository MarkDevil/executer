<html>
<head>
    <#import "common/common.macro" as comm>
    <@comm.commonStyle/>
    <title>银行渠道测试工具</title>
    <meta name="viewport" content="width=device-width, initial-scale=1, shrink-to-fit=no">
</head>

<body>
    <div>
        <nav class="navbar navbar-fixed-top navbar-inverse" role="navigation">
            <div class="navbar-header">
                <div class="navbar-brand">信审测试工具</div>
                <button type="button" class="navbar-toggle collapsed" data-toggle="collapse" data-target="#navbar-menu" aria-expanded="false">
                    <span class="sr-only">Toggle navigation</span>
                    <span class="icon-bar"/>
                    <span class="icon-bar"/>
                    <span class="icon-bar"/>
                    <span class="icon-bar"/>
                    <span class="icon-bar"/>
                </button>
            </div>

            <div id="navbar-menu" class="collapse navbar-collapse">
                <ul class="nav navbar-nav">
                    <li>
                        <a href="#" type="button" onclick="openMonitor()">中邮测试工具</a>
                    </li>
                    <li>
                        <a href="#" type="button" onclick="openMonitor()">厦门测试工具</a>
                    </li>
                    <li>
                        <a href="#">信审绑卡工具</a>
                    </li>
                    <li>
                        <a href="api">api请求工具</a>
                    </li>
                </ul>
            </div>
        </nav>
    </div>


    <div>
        <@comm.commonFooter/>
    </div>


    <@comm.commonScript/>
</body>

</html>