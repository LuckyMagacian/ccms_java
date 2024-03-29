$(function() {
	var jsonArr = {
		toolbar: [{
			"class": "btn-success",
			"icon": "glyphicon-plus",
			"click": "createMarketActivity()",
			"name": "创建活动"
		}],
		searchbar: [{
			"name": "活动名称",
			"htmlStr": '<input id="actv_name" type="text" maxlength="50" name="actv_name" autocomplete="off" class="layui-input">',
			"type": "other"
		}, {
			"name": "活动状态",
			"htmlStr": "<select id='actv_state' name='actv_state'></select>",
			"type": "select",
			"id": "actv_state",
			"jsonArr": actvState1
		}, {
			"name": "活动开始日期",
			"htmlStr": '<input id="start1" type="text" name="start1" placeholder="开始日期起" autocomplete="off" class="layui-input" readonly="readonly" onclick="layui.laydate({elem: this, festival: true})"></div>' +
				'<div class="layui-form-mid"> - </div><div class="layui-input-inline">' +
				'<input id="start2" type="text" name="start2" placeholder="开始日期止" autocomplete="off" readonly="readonly" class="layui-input" onclick="layui.laydate({elem: this, festival: true})">',
			"type": "other"
		}, {
			"name": "查询",
			"type": "btn",
			"form": "activitySearch",
			"cls": " layui-btn-normal"
		}],
		formInfo: {
			"id": "activitySearch", //id=name
			"action": "#",
			"method": "post",
			"onsubmit": "return false;" //function
		}
	}
	installTable('marketTable', project + '/activity/queryActivity.do', jsonArr, tableData);
});

function createMarketActivity() {
	layui.use('layer', function() {
		var layer = layui.layer;
		marketActivity = layer.open({
			title: "创建营销活动",
			type: 2,
			area: ['700px', '560px'],
			maxmin: true,
			content: 'marketAEC.html?action=add',
			scrollbar: false
				//,cancel: layer.msg('取消创建活动') //回调客户筛选
		});
	});
}

function tableData(jsonStr) {
	var temp = new Array();
	if(jsonStr.errCode == "0000") {
		var jsonArr = jsonStr.content;
		$.each(jsonArr, function(i) {
			var startDate = getLocalTime(this.start_date),
				stopDate = getLocalTime(this.stop_date),
				acvtStatus = this.actv_state,
				tempRow = new Array();
			actv_no = this.actv_no;
			batch_no = this.batch_no;
			tempRow[0] = i + 1; //#
			tempRow[1] = this.actv_name; //活动名称
			tempRow[2] = '第' + batch_no + '期'; //期数
			tempRow[3] = startDate.split(' ')[0]; //开始时间
			tempRow[4] = stopDate.split(' ')[0]; //结束时间
			tempRow[5] = actvState[acvtStatus]; //活动状态
			tempRow[6] = '[<a href="' + project + '/user/exportUser.xls?actv_no=' + actv_no + '&batch_no=' + batch_no + '">导出</a>]'; //客户详情
			tempRow[7] = '[<a href="javascript:actvDetail(' + actv_no + ',' + batch_no + ')">查看</a>]'; //活动详情
			tempRow[8] = userOperate(acvtStatus); //用户操作
			temp[i] = tempRow;
		});
	} else {
		layer.alert('加载表格数据失败');
		console.log(jsonStr.errMsg);
	}
	//layui插件初始化
	layui.use(['form', 'laydate'], function() {
		var form = layui.form(),
			layer = layui.layer,
			laydate = layui.laydate;

		var start = {
			max: '2099-12-31 23:59:59',
			istoday: false,
			choose: function(datas) {
				end.min = datas; //开始日选好后，重置结束日的最小日期
				end.start = datas //将结束日的初始值设定为开始日
			}
		};
		var end = {
			max: '2099-12-31 23:59:59',
			istoday: false,
			choose: function(datas) {
				start.max = datas; //结束日选好后，重置开始日的最大日期
			}
		};
		document.getElementById('start1').onclick = function() {
			start.elem = this;
			laydate(start);
		}
		document.getElementById('start2').onclick = function() {
				end.elem = this
				laydate(end);
			}
			//监听提交
		form.on('submit(activitySearch)', function(data) {
			console.log(JSON.stringify(data.field));
			ajaxPost(project + '/activity/queryActivity.do', data.field, resetTable);
			return false;
		});
	});
	return temp;
}

/**
 * actvState = { //活动状态
	"1": "待审核",//审核、修改、删除
	"2": "未开始",//无
	"3": "进行中",//无
	"4": "已结束",//删除
	"5": "审核未通过"//重新审核、修改、删除
};
 * */
function userOperate(status) {
	var temp = '';
	switch(status) {
		case '1':
			temp = '[<a href="javascript:auditActv(' + actv_no + ',' + batch_no + ',' + status + ')">审核</a>]' +
				'[<a href="javascript:editActv(' + actv_no + ',' + batch_no + ',' + status + ')">修改</a>]' +
				'[<a href="javascript:delActv(' + actv_no + ',' + batch_no + ',' + status + ')">删除</a>]';
			break;
		case '2':
		case '3':
			temp = '';
			break;
		case '4':
			temp = '[<a href="javascript:delActv(' + actv_no + ',' + batch_no + ',' + status + ')">删除</a>]';
			break;
		case '5':
			temp = '[<a href="javascript:auditActv(' + actv_no + ',' + batch_no + ',' + status + ')">重新审核</a>]' +
				'[<a href="javascript:editActv(' + actv_no + ',' + batch_no + ',' + status + ')">修改</a>]' +
				'[<a href="javascript:delActv(' + actv_no + ',' + batch_no + ',' + status + ')">删除</a>]';
			break;
		default:
			console.log("未定义status:" + status);
			break;
	}
	return temp;
}

/**
 * 活动详情 
 * actv_no:活动编号,batch_no:批次号
 * */
function actvDetail(actv_no, batch_no) {
	ajaxPost(project+'/activity/queryDetail.do',{
		'actv_no':actv_no,
		'batch_no':batch_no
	},detailLayer);
}

function detailLayer(jsonStr){
	layui.use('layer', function() {
		var layer = layui.layer;
		layer.alert(JSON.stringify(jsonStr));
	});
}
/**
 * 活动审核 
 * actv_no:活动编号,batch_no:批次号,status:活动状态
 * */
function auditActv(actv_no, batch_no, status) {
	layui.use(['layer', 'form'], function() {
		var layer = layui.layer,
			form = layui.form();
		layer.open({
			type: 1,
			area: ['420px', '240px'], //宽高
			content: '<form id="auditForm" name="auditForm" action="#" method="post" onsubmit="" class="layui-form"><div class="layui-form-item">' +
				'<label class="layui-form-label">审核意见</label><div class="layui-input-inline">' +
				'<input type="hidden" name="actv_no" id="actv_no" value="' + actv_no + '" />' +
				'<input type="hidden" name="batch_no" id="batch_no" value="' + batch_no + '" />' +
				'<select id="actv_state" name="actv_state" required lay-verify="required"><option value="2">审核通过</option><option value="5">审核不通过</option></select></div></div>' +
				'<div class="layui-form-item"><label class="layui-form-label">审核说明</label><div class="layui-input-inline">' +
				'<input id="check_opinion" type="text" autocomplete="off" maxlength="50" class="layui-input"></div></div>' +
				'<div class="layui-form-item"><div class="layui-input-block"><button lay-submit lay-filter="auditForm" class="layui-btn">确定</button></div></div></form>'
		});
		//监听提交
		form.on('submit(auditForm)', function(data) {
			layer.msg(JSON.stringify(data.field))
			return false;
		});
	});
}

/**
 * 修改活动
 * actv_no:活动编号,batch_no:批次号,status:活动状态
 * */
function editActv(actv_no, batch_no, status) {
	layui.use('layer', function() {
		var layer = layui.layer;
		marketActivity = layer.open({
			title: "修改营销活动",
			type: 2,
			area: ['700px', '530px'],
			maxmin: true,
			content: 'marketAEC.html?action=edit&actv_no=' + actv_no + '&batch_no=' + batch_no,
			scrollbar: false
				//,cancel: layer.msg('取消创建活动') //回调客户筛选
		});

	});
}

/**
 * 删除活动 
 * actv_no:活动编号,batch_no:批次号,status:活动状态
 * */
function delActv(actv_no, batch_no, status) {
	//询问框
	layer.confirm('确认删除该活动？', {
		offset: "100px",
		btn: ['确定', '取消'] //按钮
	}, function() {

	});
}

/* 筛选后重新加载表格*/
function resetTable(jsonStr) {
	console.log(JSON.stringify(jsonStr));
	if(jsonStr.errCode == "0000") {
		temp = new Array();
		var jsonArr = jsonStr.content;
		$.each(jsonArr, function(i) {
			var startDate = getLocalTime(this.start_date),
				stopDate = getLocalTime(this.stop_date),
				acvtStatus = this.actv_state,
				tempRow = new Array();
			actv_no = this.actv_no;
			batch_no = this.batch_no;
			tempRow[0] = i + 1; //#
			tempRow[1] = this.actv_name; //活动名称
			tempRow[2] = '第' + batch_no + '期'; //期数
			tempRow[3] = startDate.split(' ')[0]; //开始时间
			tempRow[4] = stopDate.split(' ')[0]; //结束时间
			tempRow[5] = actvState[acvtStatus]; //活动状态
			tempRow[6] = '[<a href="' + project + '/user/exportUser.xls?actv_no=' + actv_no + '&batch_no=' + batch_no + '">导出</a>]'; //客户详情
			tempRow[7] = '[<a href="javascript:actvDetail(' + actv_no + ',' + batch_no + ')">查看</a>]'; //活动详情
			tempRow[8] = userOperate(acvtStatus); //用户操作
			temp[i] = tempRow;
		});
		var tab=$("#marketTable").DataTable();
			tab.clear();
			tab.rows.add(temp).draw();
	} else {
		layer.alert('加载表格数据失败');
		console.log(jsonStr.errMsg);
	}

}