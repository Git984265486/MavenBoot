$(function () {

    //入口函数

    layui.alert("进入自定义JS");

    var login = layui.sessionData('user');
    //检验是否登录
    if (!login.username) {
        window.location.href = "/login";
    }


    $("#exit").click(function () {
        layui.sessionData('login', null);
        window.location.href = "page/login.html";
    })
});