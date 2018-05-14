/**
 * login.html的js业务
 */

function showCheck(a) {
	var c = document.getElementById("myCanvas");
	var ctx = c.getContext("2d");
	ctx.clearRect(0, 0, 1000, 1000);
	ctx.font = "80px 'Microsoft Yahei'";
	ctx.fillText(a, 0, 100);
	ctx.fillStyle = "white";
}
var code;
function createCode() {
	code = "";
	var codeLength = 4;
	var selectChar = new Array(1, 2, 3, 4, 5, 6, 7, 8, 9, 'a', 'b', 'c', 'd',
		'e', 'f', 'g', 'h', 'j', 'k', 'l', 'm', 'n', 'p', 'q', 'r', 's',
		't', 'u', 'v', 'w', 'x', 'y', 'z', 'A', 'B', 'C', 'D', 'E', 'F',
		'G', 'H', 'J', 'K', 'L', 'M', 'N', 'P', 'Q', 'R', 'S', 'T', 'U',
		'V', 'W', 'X', 'Y', 'Z');
	for (var i = 0; i < codeLength; i++) {
		var charIndex = Math.floor(Math.random() * 60);
		code += selectChar[charIndex];
	}
	if (code.length != codeLength) {
		createCode();
	}
	showCheck(code);
}

function validate() {
	var inputCode = document.getElementById("J_codetext").value.toUpperCase();
	var codeToUp = code.toUpperCase();
	if (inputCode.length <= 0) {
		document.getElementById("J_codetext").setAttribute("placeholder",
			"输入验证码");
		alert("请输入验证码!")
		// createCode();
		return false;
	} else if (inputCode != codeToUp) {
		document.getElementById("J_codetext").value = "";
		document.getElementById("J_codetext").setAttribute("placeholder",
			"验证码错误");
		alert("验证码错误!")
		// createCode();
		return false;
	} else {
		return true;
	}
}


//登录复用代码
function mylogin() {
	var status = validate()
	if (status) {
		var account = $("#account").val();
		var password = $("#password").val();
		if (account.trim() != "" && password.trim() != "") {
			$("#sub").attr("disabled", "disabled");
			$("#sub").attr("value", "正在请求中....");
			$.post(url + "/login/doLogin.do", {
				"account" : account,
				"password" : password
			}, function(data) {
				if (data.code == 200) {
					window.location.href = url + data.url
				} else if (data.code == 404) {
					alert("帐号或密码错误")
					$("#sub").attr("value", "登陆")
					$("#sub").removeAttr("disabled");
					createCode()
				} else if (data.code == 403) {
					alert("帐号被锁定!请联系管理员")
					$("#sub").attr("value", "登陆")
					$("#sub").removeAttr("disabled");
					createCode()
				} else {
					alert("error!")
					$("#sub").attr("value", "登陆")
					$("#sub").removeAttr("disabled");
					createCode()
				}

			}, "json")
		} else {
			alert("请输入帐号密码!")
		}
	} else {
	}
}
$(function() {
	// 点击提交按钮
	$("#sub").click(function() {
		mylogin();
	})

	//回车
	$(document).keyup(function(event) {
		if (event.keyCode == 13) {
			mylogin();
		}
	});
})