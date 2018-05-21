//初始化被包装在search函数中

//新增会议室信息
$("#addSubmit").click(function () {
    //获取所有的被选中的checkedbox的值
    var days = new Array()
    $("#addSubmit").attr("disabled", true);
    $("input:checkbox[name=day]:checked").each(function (i) {
        days[i] = $(this).val()
    })
    //判断是否开启自动审核,并赋予值
    var ischeck = "Y"
    if ($("#autoReview").is(":checked")) {
    } else {
        ischeck = "N"
    }
    var add_room = $("#add_room").val()
    var add_max = $("#add_max").val();
    var add_start_time = $("#add_start_time").val()
    var add_close_time = $("#add_close_time").val()
    if (add_start_time >= add_close_time) {
        alert("开放时间应在关闭时间前!")
        $("#addSubmit").removeAttr("disabled");
        return;
    }
    var add_department = $("#add_department").val()
    var add_department_name = $("#add_department").find("option:selected").text();
    if (days.length == 0) {
        alert("会议室开放时间不能为空")
        $("#addSubmit").removeAttr("disabled");
        return;
    }
    if (add_room.trim() == "" || add_max.trim() == "" || add_start_time.trim() == "" || add_close_time.trim() == "") {
        alert("输入错误!");
        $("#addSubmit").removeAttr("disabled");
        return;
    }
    $.post(url + "/room/add.do", {
        "room": add_room,
        "max": add_max,
        "start_time": add_start_time,
        "close_time": add_close_time,
        "department": add_department,
        "department_name": add_department_name,
        "days": days,
        "autoReview": ischeck
    }, function (data) {
        if (data.code == 1000) {
            alert("您无权限!")
        } else if (data.code == 300) {
            alert("新增会议室已存在!")
            $("#addSubmit").removeAttr("disabled");
        } else {
            $('#addUser').modal('hide')
            $("#addSubmit").removeAttr("disabled");
            window.location.href = url + '/room.html'
        }
    }, "json");

})


//更新会议室信息的模态框信息初始化
function update(id, name, max, openDate, endDate, openTime, closeTime, departmentName, departmentId, autoReview) {
    var array = new Array();
    array = openDate.split(",")
    for (var i = 0; i < array.length; i++) {
        $("#updateCheckbox" + array[i]).attr("checked", "true");
    }
    $("#update_room").val(name)
    $("#update_max").val(max)
    $("#update_open_time").val(openTime)
    $("#update_close_time").val(closeTime)
    $("#update_department").val(departmentId)
    $("#update_end_date").val(endDate)
    $("#update_id").val(id)
    if (autoReview == "Y")
        $("#update_auto").prop("checked", "checked")
    else
        $("#update_auto").removeProp("checked")
}


//更新update信息
$("#updateUserSubmit").click(function () {
    var days = new Array()
    $("input:checkbox[name=day]:checked").each(function (i) {
        days[i] = $(this).val()
    })
    if (days.length == 0) {
        alert("请选择时间!")
        return;
    }
    var room = $("#update_room").val()
    var max = $("#update_max").val()
    var openTime = $("#update_open_time").val()
    var closeTime = $("#update_close_time").val()
    var departmentId = $("#update_department").val()
    var id = $("#update_id").val()
    var endDate = $("#update_end_date").val()
    var update_department_name = $("#update_department").find("option:selected").text();
    var auto = ""
    if ($("#update_auto").is(":checked")) {
        auto = "Y"
    } else {
        auto = "N"
    }
    if (openTime >= closeTime) {
        alert("开始时间不能大于结束时间")
        return;
    }
    $("#updateUserSubmit").attr("disabled", "true")
    $.post(url + "/room/update.do", {
        "id": id,
        "room": room,
        "max": max,
        "start_time": openTime,
        "close_time": closeTime,
        "department": departmentId,
        "department_name": update_department_name,
        "days": days,
        "autoReview": auto
    }, function (data) {
        $("#updateUserSubmit").removeAttr("disabled")
        if (data.code == 200) {
            window.location.href = url + '/room.html'
        } else {
            alert("会议室已经存在!")
            $("#updateUserSubmit").removeAttr("disabled", "true")
        }
    }, "json")
})

//删除meeting
function delUser(id) {
    var flag = confirm("确认删除?")
    if (flag) {
        $.post(url + "/room/del.do", {
            "id": id
        }, function (data) {
            if (data.code == 200) {
                window.location.href = url + '/room.html'
            } else {
                alert("您没有权限")
            }
        }, "json")
    }
}


//页面初始化,并对权限进行校验
$(function () {
    init(1)
})

//跳页
$("#go").click(function () {
    $("#go").attr("disabled", "true");
    page = $("#jump").val();
    if (page == "") {
        alert("请输入页数")
    }
    if (page > $("#finalPage").html()) {
        //删除锁定
        $("#go").removeAttr("disabled");
        alert("超出总页数!")
        return;
    }
    mysearch(page)
})


//select change函数
$("#selDepart").change(function () {
    mysearch(1)
})

//搜索点击事件
$("#btnsearch").click(function () {
    mysearch(1)
})


//回车事件
$(document).keyup(function (event) {
    if (event.keyCode == 13) {
        mysearch(1)
    }
});

//页面初始化的复用函数
function init(page, changeId, search) {
    if (typeof (changeId) == "undefined") {
        changeId = 0;
    }
    if (typeof (search) == "undefined" || search.trim() == "") {
        search = "nosearch";
    }
    $("html").addClass("loading")
    $("#loading").show();
    $.post(url + "/room/init.do", {
        "pageNum": page,
        "changeId": changeId,
        "search": search
    }, function (data) {
        if (data.code == 1000) {
            window.location.href = url + data.url;
        } else {
            //清除loading动画
            $("html").removeClass("loading")
            $("#loading").hide()
            //删除锁定
            $("#go").removeAttr("disabled");
            //初始化部门信息,并清除原本的信息
            $("#selDepart").empty();
            $("#update_department").empty();
            $("#add_department").empty();
            //增加空
            $("#selDepart").append("<option value='0'>全部</option>")
            $.each(data.departmentInfos, function (index, value) {
                $("#selDepart").append("<option value='" + value.id + "'>" + value.departmentName + "</option>")
                //初始化模态框中的select中的部门信息
                $("#update_department").append("<option value='" + value.id + "'>" + value.departmentName + "</option>")
                $("#add_department").append("<option value='" + value.id + "'>" + value.departmentName + "</option>")
            })
            //设置select默认选择值
            if (data.currentDepartmentId == null) {
                data.currentDepartmentId = 0;
            }
            $("#selDepart").val(data.currentDepartmentId);
            //初始化表格内容
            $("#tUser").empty();
            $.each(data.list, function (index, value) {
                $("#tUser").append("<tr>" +
                    "<td>" + value.id + "</td><td>" + value.room + "</td>"
                    + "<td>" + value.maxPeople + "</td>"
                    + "<td>" + value.openDate + "</td>"
                    + "<td>" + value.endDate + "</td>"
                    + "<td>" + value.openTime + "</td>"
                    + "<td>" + value.endTime + "</td>"
                    + "<td>" + value.department + "</td>"
                    + "<td>" + value.autoReview + "</td>"
                    + "<td><div class='am-btn-toolbar'><div class='am-btn-group am-btn-group-xs'><button type='button'"
                    + "class='am-btn am-btn-default am-btn-xs am-text-secondary btnEdit' data-toggle='modal' data-target='#updateUser' onclick='update("
                    + value.id + ",\"" + value.room + "\",\"" + value.maxPeople + "\",\"" + value.openDate + "\",\"" + value.endDate + "\",\""
                    + value.openTime + "\",\"" + value.endTime + "\",\""
                    + value.department + "\",\"" + value.departmentId
                    + "\",\""
                    + value.autoReview
                    + "\")'>"
                    + "<span class='am-icon-pencil-square-o'></span> 编辑"
                    + "</button>"
                    + "<button type='button'"
                    + "class='am-btn am-btn-default am-btn-xs am-text-danger am-hide-sm-only'"
                    + "onclick='delUser(" + value.id + ")'>"
                    + "<span class='am-icon-trash-o'></span> 删除"
                    + "</button></div></div></td></tr>")
            })
            //初始化总条数
            $("#totalCount").text(data.total + "  条记录    一页显示12条")
            //初始化总页数
            //最后一页
            $("#finalPage").html(data.totalPage)
            $("#finalPage").attr("onclick", "mysearch(" + data.totalPage + ")")
            $("#current").html(data.currentPage)
            //前进后退
            $("#finalPage_li").removeClass("am-active");
            if (data.currentPage > 1) {
                var int = data.currentPage - 1
                $("#pre").attr("onclick", "mysearch(" + int + ")")
            }
            else
                $("#pre").attr("onclick", "mysearch(1)")
            if (data.currentPage < data.totalPage) {
                var int = data.currentPage + 1
                $("#next").attr("onclick", "mysearch(" + int + ")")
                $("#current").show();
                $("#mid").show();
            } else {
                $("#next").attr("onclick", "mysearch(" + data.currentPage + ")")
                $("#current").hide();
                $("#finalPage_li").addClass("am-active");
                $("#mid").hide();
            }
        }
    }, "json");
}

//请求搜索的复用函数
function mysearch(page) {
    var changeId = $("#selDepart").val();
    var search2 = $("#search").val();
    init(page, changeId, search2)
}