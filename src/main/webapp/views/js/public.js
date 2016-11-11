/* 全局变量 */

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
actvState = { //活动状态
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
gender = {//性别
	"0": "女",
	"1": "男"
};
occCatgry={//单位性质
	"1": "政府机关",
	"2": "事业单位",
	"3": "国有企业",
	"4": "三资企业",
	"5": "股份制企业",
	"6": "民营企业",
	"7": "其他企业",
	"8":"个体/自由职业"
};
shopping={//消费偏好
	"0":"餐饮",
	"1": "出版印刷",
	"2": "房产",
	"3": "纺织服饰日用品",
	"4": "家电及电子文娱产品",
	"5": "交通物流运输仓储",
	"6": "教育",
	"7": "金融",
	"8": "居民服务",
	"9": "批发类",
	"10":"汽车燃料配件零售",
	"11":"食品饮料烟草零售",
	"12":"休闲娱乐",
	"13":"医疗医药器材",
	"14":"综合零售",
	"15":"租赁服务"
};
/* 一下需要后台传递,先写死 */
actvName={//活动名称,ID:name
	"1001":"活动一",
	"1003":"活动3",
	"1004":"活动4"
}

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
		if(_this.type=="btn"){
			temp +='<div class="layui-form-item" style="margin-top:0px;"><div class="layui-input-inline">'+
				'<button lay-submit lay-filter="'+_this.form+'" class="layui-btn '+_this.cls+'">'+_this.name+'</button></div></div>';
		}else{
			temp +='<div class="layui-form-item"><label class="layui-form-label">'+_this.name+'</label>';
			temp += '<div class="layui-input-inline">' + _this.htmlStr + '</div></div>';
		}
		
	});
	temp += '</form>';
	$("div." + cls).append(temp);
	$.each(jsonArr, function(i) {
		if(this.type=="select"){
			selectOp(this.id,this.jsonArr);
		}
	});
}