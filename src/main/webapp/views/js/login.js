$(function() {
	myListen();
});
project='/ccms_java';
/** 通用验证函数
    regStr:正则式
    vailStr:待验证字符串
    id:效果显示在该id上
*/
function pubValidate(regStr, valiStr, id) {
    var flag = 0;
    if (!regStr.test(valiStr)) flag = 1; //test()方法搜索字符串指定的值，根据结果并返回真或假。
    else flag = 0;
    if (flag) {//匹配不正确
        $("#" + id).parent().removeClass("has-success");
        $("#" + id).parent().addClass("has-error");
        return false;
    }
    else {//匹配正确
        $("#" + id).parent().removeClass("has-error");
        $("#" + id).parent().addClass("has-success");
        return true;
    }
}

/** 用户名验证*/
function valiUsername(id){
    var regStr = /^\w+$/,//正则，匹配字母数字下划线
        valiStr = $("#" + id).val(),
        result = pubValidate(regStr, valiStr, id);
        $("#loginHint").html("&nbsp;");
    return result;
}

/* 事件监听 */
function myListen() {
	/*点击显示密码*/
	$("#eye").mousedown(function() {
		$("#eye").removeClass("glyphicon-eye-close");
		$("#eye").addClass("glyphicon-eye-open");
		$("#password").attr("type", "text");
		$("#eye").css("color", "#666");
	});
	$("#eye").mouseup(function() {
		$("#eye").removeClass("glyphicon-eye-open");
		$("#eye").addClass("glyphicon-eye-close");
		$("#password").attr("type", "password");
		$("#eye").css("color", "#888");
	});
	document.onkeydown=function(event){
		var e = event || window.event || arguments.callee.caller.arguments[0];
		if(e && e.keyCode==13){//按Enter
			login();
		}
	};
}
function login(){
	var pwd = $("#password").val(),
		username = $("#username").val(),
		usernameResult = valiUsername("username");
		if (!usernameResult) {
			$("#loginHint").html("用户名错误!");
			$("#username").select();
		} else if (pwd == "") {
			$("#loginHint").html("请输入密码");
			$("#password").focus();
		} else {
			LOAD.msgbox.show("正在加载中，请稍后...",6);
			$.post(project+'/login/login.do', {
				username: username,
				password: pwd
			}, function(jsonStr) {
				if(parseInt(jsonStr.statusCode)==200){
					LOAD.msgbox.show("登录成功，正在跳转至home页面...",4);
					window.location.href='index2.html';
				}else{
					LOAD.msgbox.show("登录失败,请检查用户名和密码",5,2000);
					$('#loginHint').html(jsonStr.message);
				}
			}, 'json');
		}
}
