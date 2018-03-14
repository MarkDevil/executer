
function invoke(formid,url){
    let form = $('#' + formid).formSerialize();
    console.log(form);
    $.ajax({
        type:"post",
        url:url,
        data:form,
        cache:false,//false是不缓存，true为缓存
        async:true,//true为异步，false为同步
        beforeSend:function(){
            //请求前
        },
        success:function(result){
            console.log(result);
            confirm("调用成功");
        },
        complete:function(){
            //请求结束时
            confirm("结果返回成功");
        },
        error:function(){
            //请求失败时
            alert("请求失败");
        }
    });

}

$.ajax({
    type:"get",
    url:"/executer-web/api/testDataList",
    dataType:"json",
    success: function (data) {
        $("#bt-table").bootstrapTable('load',data);
    }

});

function openMonitor() {
    window.location.href = "/executer-web/monitor.ftl";
}

function displayPage(bank) {
    let tabContents = document.getElementsByClassName("tab-content");
    for(let i=0; i<tabContents.length; i++){
        let tab = tabContents[i];
        if (tab.getAttribute("id") !== bank){
            tab.setAttribute('hidden','hidden');
        }else {
            tab.removeAttribute("hidden");
        }
    }
}