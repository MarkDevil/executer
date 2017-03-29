<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<html>
    <head>
        <script src="https://apps.bdimg.com/libs/jquery/2.1.4/jquery.min.js" ></script>
    </head>
    <body>
        <table>
            <tr>
                <h2>Hello World!</h2>
                <button id="post_btn" name="sendPost" value="sendPost"></button>
            </tr>
            <tr>
                <h3>定时任务调用</h3>
                <button id="sch_btn" name="invokeSchedule" value="invokeSchedule"></button>
            </tr>
        </table>

    </body>


    <script>
        $(document).ready(function(){
            $("post_btn").click(function(){
                $.get("/executer-web/api/queryPage/",function(data,status){
                    alert("数据: " + data + "\n状态: " + status);
                });
            });
        });

        $(document).ready(function () {
            $("sch_btn").click(function () {
                $.get("/schedule/onetime"), function (data,status) {
                    print("数据 : " + data + "\n" + status)
                }
            })
        })
    </script>
</html>
