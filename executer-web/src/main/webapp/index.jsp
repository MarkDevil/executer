<html>
    <meta name="viewport" charset="UTF-8">
    <head>
        <script src="https://apps.bdimg.com/libs/jquery/2.1.4/jquery.min.js"></script>
    </head>
    <body>
    </body>
</html>

<script>
    window.location.href='html/homePage.html';
</script>

<script>
    $(document).ready(function(){
        $("btn1").click(function(){
            alert("test info");
            $.get("/api/queryPage",function(data,status){
                alert("数据: " + data + "\n 状态: " + status);
            });
        });
    });

    $(document).ready(function () {
//        $("btn_invoke").click(function () {
//            $.ajax({
//                url:'/api/schedule/onetime',
//                type: 'GET',
//                success : function (data) {
//                    if (data.success){
//                        alert("调用成功");
//                        print(data)
//                    }
//                }
//
//            })
//        })
    })


</script>
