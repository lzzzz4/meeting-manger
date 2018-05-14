function init(search) {
	//清除loading动画
	$("html").removeClass("loading")
	$("#loading").hide()
	$("#tUser").empty();
	$.post(url + "/showRole/show.do", {
		"search" : search
	}, function(data) {
		$.each(data, function(index, value) {
			$("#tUser").append("<tr>" +
				"<td>" + value.username + "</td>" + "<td>" + value.account + "</td>" + "<td>" + value.name + "</td>"
				+ "<td><div class='am-btn-toolbar'><div class='am-btn-group am-btn-group-xs'>"
				+ "<button type='button'"
				+ "class='am-btn am-btn-default am-btn-xs am-text-danger am-hide-sm-only'"
				+ "onclick='del(" + value.id + ")'>"
				+ "<span class='am-icon-trash-o'></span> 删除"
				+ "</button></div></div></td>"
				+ "</tr>"
			)

		})
	}, "json")
}
function search() {
	var search2 = $("#departName").val();
	if (typeof (search2) == "undefined" || search2.trim() == "") {
		search2 = "nosearch";
	}
	init(search2)
}
$(function() {
	search();
})
$("#btnsearch").click(function() {
	search()
})

//回车事件
$(document).keyup(function(event) {
	if (event.keyCode == 13) {
		search()
	}
});

function del(id) {
	var flag = confirm("确认删除?")
	$.post(url + "/showRole/del.do", {
		"id" : id
	}, function(data) {
		if (data.code == 200) {
			window.location.href = url + "/showRole.html"
		} else {
			alert("您无权限操作！请联系超管!")
		}
	}, "json")
}