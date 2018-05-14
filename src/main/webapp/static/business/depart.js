//初始化
$(function() {
	mysearch();
})


//初始化复用函数
function init(search) {
	if (typeof (search) == "undefined" || search.trim() == "") {
		search = "";
	}
	$.post(url + "/department/init.do", {
		"search" : search
	}, function(data) {
		if (data.code == 1000) {
			window.location.href = url + data.url;
		}
		//清除动画
		$("html").removeClass("loading")
		$("#loading").hide()

		//初始化表格内容
		$("#tUser").empty();
		$.each(data, function(index, value) {
			$("#tUser").append("<tr>" +
				"<td>" + value.id + "</td><td>" + value.departmentName + "</td>"
				+ "<td>" + value.departmentSuperior + "</td>"
				+ "<td><div class='am-btn-toolbar'><div class='am-btn-group am-btn-group-xs'><button type='button'"
				+ "class='am-btn am-btn-default am-btn-xs am-text-secondary btnEdit' data-toggle='modal' data-target='#updateUser' onclick='update("
				+ value.id + ",\"" + value.departmentName + "\",\"" + value.departmentSuperior
				+ "\")'>"
				+ "<span class='am-icon-pencil-square-o'></span> 编辑"
				+ "</button>"
				+ "<button type='button'"
				+ "class='am-btn am-btn-default am-btn-xs am-text-danger am-hide-sm-only'"
				+ "onclick='delUser(" + value.id + ")'>"
				+ "<span class='am-icon-trash-o'></span> 删除"
				+ "</button></div></div></td></tr>")
		})
	}, "json")
}

//搜索
function mysearch() {
	var search = $("#departName").val()
	init(search)
}

//点击搜索按钮事件
$("#btnsearch").click(function() {
	mysearch()
})

//回车事件
$(document).keyup(function(event) {
	if (event.keyCode == 13) {
		mysearch(1)
	}
});

//更新初始化模态框
function update(id, name, supername) {
	$("#update_name").val(name);
	$("#update_password").val(supername);
	$("#update_id").val(id);
}

//新增add事件
$("#addSubmit").click(function() {
	$("#addSubmit").attr("disabled", true);
	var departmentSuperior = $("#add_super").val();
	var departmentName = $("#add_name").val();
	if (departmentName.trim() == "" || departmentSuperior.trim() == "") {
		alert("输入错误")
		$("#addSubmit").removeAttr("disabled");
		return;
	}
	$.post(url + "/department/add.do", {
		"departmentSuperior" : departmentSuperior,
		"departmentName" : departmentName
	}, function(data) {
		if (data.code == 200) {
			$('#addUser').modal('hide')
			window.location.href = url + '/depart.html'
		}
		$("#addSubmit").removeAttr("disabled");
	}, "json")
})

//更新事件
$("#updateUserSubmit").click(function() {
	$("#updateUserSubmit").attr("disabled", true);
	var departmentName = $("#update_name").val();
	var departmentSuperior = $("#update_password").val();
	var id = $("#update_id").val();
	$.post(url + "/department/update.do", {
		"departmentName" : departmentName,
		"departmentSuperior" : departmentSuperior,
		"id" : id
	}, function(data) {
		if (data.code == 200) {
			window.location.href = url + '/depart.html'
		}
		$("#updateUserSubmit").removeAttr("disabled");
	}, "json")
})

//删除事件
function delUser(id) {
	var flag = confirm("确认删除?")
	if (flag) {
		$.post(url + "/department/del.do", {
			"id" : id
		}, function(data) {
			if (data.code == 200) {
				window.location.href = url + '/depart.html'
			}else{
				alert("您没有权限!")
			}
		}, "json")
	}

}