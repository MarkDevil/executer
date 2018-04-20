<html>
<head>
    <title>测试平台</title>
    <#import "common/common.macro" as comm>
    <@comm.commonStyle></@comm.commonStyle>
</head>

<body>
    <header>
        <@comm.commonHeader></@comm.commonHeader>
    </header>

    <a>
        <@comm.commonLeft 'projectList' />
    </a>


    <div>
        <@comm.commonFooter></@comm.commonFooter>
    </div>
    <@comm.commonScript />
</body>
</html>