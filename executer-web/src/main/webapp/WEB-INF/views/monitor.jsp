<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%--
  Created by IntelliJ IDEA.
  User: mark
  Date: 2017/10/28
  Time: 下午3:09
To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>System monitor</title>
    <script src="<c:url value="/resources/js/third/echarts/echarts.min.js"/>"></script>
    <script src="<c:url value="/resources/js/third/echarts/shine.js"/>"></script>
</head>
<body>
    <div id="main" style="height: 500px"></div>
</body>

<script type="text/javascript">
    let xValues = [1,2,3,4,5,6];
    let mychart = echarts.init(document.getElementById("main"));
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
    mychart.setOption(option);





</script>





</html>
