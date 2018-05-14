$(function() {
	init();
})
function init() {
	$.post(url + "/shiro/show.do", {}, function(data) {
		if (data.code == 200) {
			$("#tUser").empty();
			$.each(data.list, function(index, value) {
				$("#tUser").append("<tr>" +
					"<td>" + value.id + "</td><td>" + value.name + "</td>"
					+ "<td><a href='#'>" + value.info + "</a></td>"
					+ "<td><button type='button'"
					+ "class='am-btn am-btn-default am-btn-xs am-text-secondary btnEdit' data-toggle='modal' data-target='#addUser' onclick='addinit("
					+ value.id
					+ ")'>"
					+ "<span class='am-icon-pencil-square-o'></span> 授权"
					+ "</button></td>"
					+ "</tr>")
			})
		} else {
			window.location.href = url + data.url
		}
	}, "json");
}
$("#add_account").blur(function() {
	findUser();
})
function addinit(roleId) {
	$("#add_role_id").val(roleId)
}
function findUser() {
	var account = $("#add_account").val();
	$.post(url + "/shiro/find.do", {
		"account" : account
	}, function(data) {
		$("#add_name").val(data.username)
		$("#add_id").val(data.id)
	}, "json");
}

$("#addSubmit").click(function() {
	$("#addSubmit").attr("disabled", "true");
	var userId = $("#add_id").val()
	var roleId = $("#add_role_id").val()
	$.post(url + "/shiro/add.do", {
		"userId" : userId,
		"roleId" : roleId
	}, function(data) {
		if (data.code == 200) {
			$("#addSubmit").removeAttr("disabled");
			window.location.href = url + "/showRole.html";
		} else if (data.code == 300) {
			alert("当前用户已经拥有其它或当前权限!请确认!")
			$("#addSubmit").removeAttr("disabled");
		} else {
			window.location.href = url + data.url
		}
	}, "json");
})