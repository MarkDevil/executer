<html>
    <meta name="viewport" charset="UTF-8">
    <head>
        <script src="https://apps.bdimg.com/libs/jquery/2.1.4/jquery.min.js" ></script>
    </head>
    <body>
        <table>
            <tr>
                <h2>Start task</h2>
                <input id="btn1" type="button" value="send">
            </tr>
            <tr>
                <h3>Schedule invoke</h3>
                <input id="sch_btn" value="invokeSchedule" type="button">
            </tr>
            <tr>
                <comment href="web-inf/html/homePage.html"></comment>
            </tr>
        </table>



    </body>

    <%--<script>--%>
        <%--window.location.href='html/homePage.html';--%>
    <%--</script>--%>
</html>

<script>
    $(document).ready(function(){
        $("btn1").click(function(){
            alert("test info");
            $.get("api/queryPage",function(data,status){
                alert("数据: " + data + "\n 状态: " + status);
            });
        });
    });

    $(document).ready(function () {
        $("sch_btn").click(function () {
            $.ajax({
                url:'api/schedule/onetime',
                type: 'GET',
                success : function (data) {
                    if (data.success){
                        alert("调用成功");
                        print(data)
                    }
                }

            })
        })
    })


</script>
