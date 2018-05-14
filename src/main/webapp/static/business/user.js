//初始化被包装在search函数中

//新增用户信息
$("#addSubmit").click(function() {
	$("#addSubmit").attr("disabled", true);
	var account = $("#add_account").val();
	var password = $("#add_password").val()
	var add_name = $("#add_name").val()
	var department = $("#add_department").val()
	if (account.trim() == "" || password.trim() == "" || add_name.trim() == "" || department.trim() == "") {
		alert("输入错误!");
		$("#addSubmit").removeAttr("disabled");
		return;
	}
	$.post(url + "/user/userAdd.do", {
		"account" : account,
		"password" : password,
		"name" : add_name,
		"departmentId" : department
	}, function(data) {
		if (data.code == 1000) {
			alert("您无权限!")
		} else if (data.code == 500) {
			alert("新增用户的工号已存在!")
			$("#addSubmit").removeAttr("disabled");
		} else {
			$('#addUser').modal('hide')
			$("#addSubmit").removeAttr("disabled");
			window.location.href = url + '/user.html'
		}
	}, "json");
})

var old_account;
//更新用户信息的模态框信息初始化
function update(id, account, password, name, mail, departmentId) {
	$("#update_account").val(account)
	$("#update_password").val(password)
	$("#update_name").val(name)
	$("#update_mail").val(mail)
	$("#update_department").val(departmentId)
	$("#update_id").val(id)
	old_account = account;
}


//更新用户信息
$("#updateUserSubmit").click(function() {
	var account = $("#update_account").val()
	var password = $("#update_password").val()
	var name = $("#update_name").val()
	var mail = $("#update_mail").val()
	var departmentId = $("#update_department").val()
	var id = $("#update_id").val()
	$("#updateUserSubmit").attr("disabled", "true")
	$.post(url + "/user/userUpdate.do", {
		"oldAccount" : old_account,
		"id" : id,
		"account" : account,
		"password" : password,
		"name" : name,
		"mail" : mail,
		"departmentId" : departmentId
	}, function(data) {
		$("#updateUserSubmit").removeAttr("disabled")
		if (data.code == 200) {
			window.location.href = url + '/user.html'
		} else {
			alert("要更新的用户已存在!")
		}
	}, "json")

})

//删除用户
function delUser(id) {
	var flag = confirm("确认删除?")
	if (flag) {
		$.post(url + "/user/del.do", {
			"id" : id
		}, function(data) {
			if (data.code == 200) {
				window.location.href = url + '/user.html'
			}else{
				alert("您没有权限")
			}
		}, "json")
	}
}


//页面初始化,并对权限进行校验
$(function() {
	init(1)
})

//跳页
$("#go").click(function() {
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
$("#selDepart").change(function() {
	mysearch(1)
})

//搜索点击事件
$("#btnsearch").click(function() {
	mysearch(1)
})


//回车事件
$(document).keyup(function(event) {
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
	$.post(url + "/user/init.do", {
		"pageNum" : page,
		"changeId" : changeId,
		"search" : search
	}, function(data) {
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
			$.each(data.departmentInfos, function(index, value) {
				$("#selDepart").append("<option value='" + value.id + "'>" + value.departmentName + "</option>")
				//初始化模态框中的部门信息
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
			$.each(data.dependUserInfos, function(index, value) {
				$("#tUser").append("<tr>" +
					"<td>" + value.id + "</td><td>" + value.account + "</td>"
					+ "<td><a href='#'>" + value.name + "</a></td>"
					+ "<td>" + value.department + "</td>"
					+ "<td>" + value.mail + "</td>"
					+ "<td><div class='am-btn-toolbar'><div class='am-btn-group am-btn-group-xs'><button type='button'"
					+ "class='am-btn am-btn-default am-btn-xs am-text-secondary btnEdit' data-toggle='modal' data-target='#updateUser' onclick='update("
					+ value.id + ",\"" + value.account + "\",\"" + value.password + "\",\"" + value.name + "\",\"" + value.mail + "\",\"" + value.departmentId
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