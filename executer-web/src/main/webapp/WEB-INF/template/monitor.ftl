<html>
<head>
    <title>System monitor</title>
    <#import "common/common.macro" as comm>
    <@comm.commonStyle/>
    <script src="${request.contextPath}/resources/js/third/echarts/echarts.min.js"></script>
    <script src="${request.contextPath}/resources/js/third/echarts/shine.js"></script>
</head>
<body>
<@comm.commonHeader></@comm.commonHeader>
<div class="main-bank-header">
    <div class="container">
            <h1>组件</h1>
            <p>无数可复用的组件</p>
    </div>
</div>

<div class="row">
    <div class="container">
        <div class="col-lg-6" id="cpu" style="height: 300px"></div>
        <div class="col-lg-6" id="memory" style="height: 300px"></div>
    </div>

</div>

<div class="row">
    <div class="container">
        <div class="col-lg-6" id="net" style="height: 300px"></div>
        <div class="col-lg-6" id="disk" style="height: 300px"></div>
    </div>
</div>





<@comm.commonFooter></@comm.commonFooter>
<@comm.commonScript></@comm.commonScript>
</body>

<script type="text/javascript">
    let xValues = [1,2,3,4,5,6];
    let cpu = echarts.init(document.getElementById("cpu"));
    let memory = echarts.init(document.getElementById("memory"));
    let net = echarts.init(document.getElementById("net"));
    let disk = echarts.init(document.getElementById("disk"));

    let option = {
        tooltip: {
            show: true
        },
        legend: {
            data:['销量']
        },
        xAxis : [
            {
                type : 'category',
                data : ["衬衫","羊毛衫","雪纺衫","裤子","高跟鞋","袜子"]
            }
        ],
        yAxis : [
            {
                type : 'value'
            }
        ],
        series : [
            {
                "name":"销量",
                "type":"bar",
                "data":xValues
            }
        ]
    };
    cpu.setOption(option);
    memory.setOption(option);
    net.setOption(option);
    disk.setOption(option);

</script>





</html>
