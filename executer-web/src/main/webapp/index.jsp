<html>
    <head>
        <script src="https://apps.bdimg.com/libs/jquery/2.1.4/jquery.min.js" ></script>
    </head>
    <body>
        <h2>Hello World!</h2>
        <button id="post_btn" name="sendPost" ></button>

    </body>


    <script >
        $(document).ready(function(){
            $("button").click(function(){
                $.get("/executer-web/api/queryPage/",function(data,status){
                    alert("数据: " + data + "\n状态: " + status);
                });
            });
        });
    </script>
</html>
