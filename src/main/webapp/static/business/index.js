$(function () {
    $.post(url + "/index/init.do", {}, function (data) {
        $("#username").text(data.username);
        $("#userId").text(data.userId);
        $("#logout").attr("href", url + "/logout.do")
        $("#wait").attr("href", data.apiUrl)
    }, "json");
    $("#changePass").click(function () {
        alert("待完成!")
    })
})