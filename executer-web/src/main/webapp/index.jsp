<html>
    <meta name="viewport" charset="UTF-8">
    <head>
        <script src="https://apps.bdimg.com/libs/jquery/2.1.4/jquery.min.js" ></script>
    </head>
    <body>
        <table>
            <tr>
                <h2>Hello World!</h2>
                <input id="btn1" type="button" value="发送">
            </tr>
            <tr>
                <h3>定时任务调用</h3>
                <input id="sch_btn" value="invokeSchedule" type="button">
            </tr>
        </table>



    </body>

    <script>
        window.location.href='html/homePage.html';
    </script>


    <script>
        $(document).ready(function(){
            $("btn1").click(function(){
                $.get("../../api/queryPage/",function(data,status){
                    alert("数据: " + data + "\n状态: " + status);
                });
            });
        });

        $(document).ready(function () {
            $("sch_btn").click(function () {
                $.ajax({
                    url:'/api/schedule/onetime',
                    type: 'get',
                    success : function (data) {
                        if (data.success){
                            alert("调用成功");
                        }
                    }

                })
            })
        })


    </script>
</html>
