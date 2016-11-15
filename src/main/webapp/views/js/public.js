/* 全局变量 */
project = '/ccms_java';
/** 营销活动模块
 * jsonName=<value:name>
 * */
actvTarget = { //活动目的
	"1": "唤醒睡眠卡",
	"2": "提升业务量"
};
actvStyle = { //活动形式
	"1": "首刷激活",
	"2": "冻卡激活",
	"3": "分期",
	"4": "刷卡"
};
actvStyle1 = { //唤醒睡眠卡->活动形式
	"1": "首刷激活",
	"2": "冻卡激活"
};
actvStyle2 = { //提升业务量->活动形式
	"3": "分期",
	"4": "刷卡"
};
actvType = { //活动类型
	"1": "赠送积分",
	"2": "手续费折扣",
	"3": "赠送礼品",
	"4": "分期抽奖",
	"5": "首刷",
	"6": "激活",
	"7": "满减",
	"8": "满送",
	"9": "满额返现"
};
actvType1 = { //分期->活动类型
	"1": "赠送积分",
	"2": "手续费折扣",
	"3": "赠送礼品",
	"4": "分期抽奖"
};
actvType2 = { //刷卡->活动类型
	"5": "首刷",
	"6": "激活",
	"7": "满减",
	"8": "满送",
	"9": "满额返现"
};
actvState = { //活动状态
	"1": "待审核",
	"2": "未开始",
	"3": "进行中",
	"4": "已结束",
	"5": "审核未通过"
};
actvState1 = { //活动状态（帶全部）
	"":"全部",
	"1": "待审核",
	"2": "未开始",
	"3": "进行中",
	"4": "已结束",
	"5": "审核未通过"
};
ruleType = { //规则类型
	"0": "满足任意条件",
	"1": "满足全部条件"
};
actvRule = { //活动规则
	"1": "首刷",
	"2": "激活",
	"3": "首刷与激活"
};
joinType = { //参与类型
	"0": "自动选择",
	"1": "主动报名"
};
fqs = { //分期数
	"6": "6期",
	"12": "12期",
	"18": "18期",
	"24": "24期",
	"36": "36期"
};
gender = { //性别
	"0": "女",
	"1": "男"
};
occCatgry = { //单位性质
	"1": "政府机关",
	"2": "事业单位",
	"3": "国有企业",
	"4": "三资企业",
	"5": "股份制企业",
	"6": "民营企业",
	"7": "其他企业",
	"8": "个体/自由职业"
};
shopping = { //消费偏好
	"0": "餐饮",
	"1": "出版印刷",
	"2": "房产",
	"3": "纺织服饰日用品",
	"4": "家电及电子文娱产品",
	"5": "交通物流运输仓储",
	"6": "教育",
	"7": "金融",
	"8": "居民服务",
	"9": "批发类",
	"10": "汽车燃料配件零售",
	"11": "食品饮料烟草零售",
	"12": "休闲娱乐",
	"13": "医疗医药器材",
	"14": "综合零售",
	"15": "租赁服务"
};
/* 一下需要后台传递,先写死 */
actvName = { //活动名称,ID:name
	"1001": "活动一",
	"1003": "活动3",
	"1004": "活动4"
};
userType = { //用户角色
	"1": "系统管理员",
	"2": "审核人员",
	"3": "业务人员"
};

/**
 * 获取url参数
 * return [<key:value>,<>]
 * */
function getParam() {
	var params = location.search.substr(1); //  获取参数 并且去掉？
	var ArrParam = params.split('&'),
		temp;
	var jsonArr = new Array();
	if(ArrParam.length == 1) {
		temp = params.split('=');
		jsonArr[0] = JSON.parse('{"' + temp[0] + '":"' + temp[1] + '"}');
	} else {
		//多个参数参数的情况
		$.each(ArrParam, function(i) {
			temp = ArrParam[i].split('=');
			jsonArr[i] = JSON.parse('{"' + temp[0] + '":"' + temp[1] + '"}');
		});
	}
	return jsonArr;
}

/**
 * 初始化下拉框
 * id:下拉框对应id;json:下拉选项json串jsonName=<value:name>
 * */
function selectOp(id, json) {
	var temp = '<option value="">=== 请选择 ===</option>';
	$.each(json, function(key, val) {
		temp += '<option value="' + key + '">' + val + '</option>';
	});
	$("#" + id).html(temp);
}

/**
 * 初始化表格按钮
 * cls:表格对应class;jsonArr
 * 表格按钮对丁json数组串
 * jsonArrName=[<"class":"按钮样式",
				 "icon":"前置图标",
				 "click":"点击函数",
				 "name":"按钮名">,<>]
 * */
function tableBtn(cls, jsonArr) {
	var temp = '';
	$.each(jsonArr, function(i) {
		var _this = this;
		temp = '<a class="btn ' + _this.class + '" href="javascript:' + _this.click + ';"><span class="glyphicon ' + _this.icon + '"></span> ' + _this.name + '</a>';
		$("div." + cls).append(temp);
	});
}

/**
 * 初始化表格筛选条件
 * cls:筛选条件对应的class类,form放在该class之下
 * jsonArr:[<
		"name":"性别",
		"htmlStr":"<select id='gender' name='GENDER'></select>",
		"type":"select",
		"id":"gender",
		"jsonArr":gender
	},<
		"name":"年龄范围",
		"htmlStr":'<input id="age_min" type="number" min="0" max="300" name="AGE_MIN" placeholder="min" autocomplete="off" class="layui-input"></div>'
					+'<div class="layui-form-mid"> -- </div><div class="layui-input-inline">'
					+'<input id="age_max" type="number" min="0" max="300" name="AGE_MAX" placeholder="max" autocomplete="off" class="layui-input">',
		"type":"other"
	},<
		"name":"自定义筛选",
		"type":"btn",
		"form":"filterSearch",
		"cls":" layui-btn-normal",
		"htmlStr":'<button lay-submit lay-filter="filterSearch" class="layui-btn layui-btn-normal">自定义筛选</button>'
	}]
*   form表单信息:formJson默认值:<
		"id":"searchForm",//id=name
		"action":"#",
		"method":"post",
		"onsubmit":"return false;"//function
	}
 * */
function searchbar(cls, jsonArr, formJson) {
	if(formJson == undefined) {
		var temp = '<form id="searchForm" name="searchForm" action="#" method="post" onsubmit="" class="layui-form">'
	} else
		var temp = '<form id="' + formJson.id + '" name="' + formJson.id + '" action="' + formJson.action + '" method="' + formJson.method + '" onsubmit="' + formJson.onsubmit + '" class="layui-form">';
	$.each(jsonArr, function(i) {
		var _this = this;
		if(_this.type == "btn") {
			temp += '<div class="layui-form-item" style="margin-top:0px;"><div class="layui-input-inline">' +
				'<button lay-submit lay-filter="' + _this.form + '" class="layui-btn ' + _this.cls + '">' + _this.name + '</button></div></div>';
		} else {
			temp += '<div class="layui-form-item"><label class="layui-form-label">' + _this.name + '</label>';
			temp += '<div class="layui-input-inline">' + _this.htmlStr + '</div></div>';
		}

	});
	temp += '</form>';
	$("div." + cls).append(temp);
	$.each(jsonArr, function(i) {
		if(this.type == "select") {
			selectOp(this.id, this.jsonArr);
		}
	});
}

/** 
 * 创建完整表格，不含表格头(在id="id"的html table中初始化表格头)
 * id:表格对应id,jsonArr=<toolbar:[<>],searchbar:[<>],formInfo:<>>
 * toolbar,searchbar/formInfo详见tableBtn,searchbar函数中
 * func：表格数据格式化，带一个参数jsonStr，为ajax收到的原始数据
 * */
function installTable(id, url, jsonArr, func) {
	var data = null;
	if(jsonArr.data == undefined) {
		data = {};
	} else {
		data = jsonArr.data;
	}
	layui.use('layer', function() {
		var layer = layui.layer;
		$.ajax({
			type: "post",
			url: url,
			data: data,
			dataType: "json",
			beforeSend: function() {
				layer.open({
					type: 3,
					scrollbar: false
				});
			},
			success: function(jsonStr) {
				layer.closeAll('loading'); //关闭加载层
				var dataArr = jsonStr;
				if(func != undefined) {
					var dataArr = func(jsonStr);
				}
				$("#" + id).DataTable({
					dom: '<"top"<"toolbar">f<"searchbar">>rt<"bottom"lip>',
					language: {
						"lengthMenu": "每页 _MENU_ 条记录",
						"zeroRecords": "没有找到记录",
						"info": "第 _PAGE_ 页 ( 总共 _PAGES_ 页 )",
						"infoEmpty": "无记录",
						"infoFiltered": "(从 _MAX_ 条记录过滤)"
					},
					data: dataArr
				});
				if(jsonArr.toolbar != undefined) {
					tableBtn('toolbar', jsonArr.toolbar); //加载表格头部按钮
				}
				if(jsonArr.searchbar != undefined) {
					searchbar('searchbar', jsonArr.searchbar, jsonArr.formInfo);
				}
			},
			error: function(e) {
				console.log("err:" + JSON.stringify(e));
			}

		});
	});
}

/**
 *  ajax请求封装
 * url:请求后台地址,dataJson:post请求数据
 * beforeFunc:请求前触发函数,不带参数
 * successFunc:成功时触发函数,带一个参数jsonStr
 * */
function ajaxPost(url, dataJson, successFunc) {
	$.ajax({
		type: "post",
		url: url,
		data:dataJson,
		dataType: "json",
		beforeSend: function() {

		},
		success: function(jsonStr) {
			if(typeof successFunc == 'function') {
				successFunc(jsonStr);
			}

		},
		error: function(e) {
			console.log("error!\n " + e);
		}
	});
}

/* 用户权限控制 */
adminId = null,
	adminType = null;
loginName = null;

function userControll(jsonStr) {
	if(jsonStr.statusCode == 200) {
		if(jsonStr.admin_Type == null || jsonStr.username == null || jsonStr.admin == null) {
			alert("登录超时,请重新登录");
			location.href = project + "/views/login.html";
		} else {
			adminId = jsonStr.admin.admin_Id;
			adminType = jsonStr.admin_Type;
			loginName = jsonStr.username;
			$("#loginName").html(loginName);
		}
	} else {
		console.log("获取session失败!" + jsonStr.message);
	}
}

$(function() {
	ajaxPost(project + '/admin/admin.getSessionMessage', {}, userControll);
});

function rePwd() {
	ajaxPost(project + '/admin/admin.update', {}, function(jsonStr) {

	});
}

function logOut() {
	ajaxPost(project + '/login/loginOut.do', {}, function(jsonStr) {
		if(jsonStr.statusCode == 200) {
			location.href = project + "/views/login.html";
		} else {
			alert('登出失败!,请重试');
			console.log("error! " + jsonStr.message);
		}
	});
}

/* 时间戳格式化 */
function getLocalTime(nS) {
	return new Date(parseInt(nS)).toLocaleString().replace(/年|月/g, "-").replace(/日/g, " ");
}

/* 关闭当前弹窗 */
function closeThisLayer() {
	layui.use('layer', function() {
		var layer = layui.layer;
		var index = parent.layer.getFrameIndex(window.name); //先得到当前iframe层的索引
		parent.layer.close(index);
	});
}