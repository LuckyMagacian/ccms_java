 $(function() {
 	/* 表格初始化 */
  	$("#marketTable").DataTable({
  		dom: '<"top"<"toolbar">f<"searchbar">>rt<"bottom"lip>',
  		data: [
  			['1', '活动1', '3期', '2016/10/10', '2017/01/01', '1', '<a>导出</a>', '<a>查看</a>', '[<a>审核</a>][<a>修改</a>][<a>删除</a>]']
  		],
  		language: {
  			"lengthMenu": "每页 _MENU_ 条记录",
  			"zeroRecords": "没有找到记录",
  			"info": "第 _PAGE_ 页 ( 总共 _PAGES_ 页 )",
  			"infoEmpty": "无记录",
  			"infoFiltered": "(从 _MAX_ 条记录过滤)"
  		}
  		//,ajax:
  	});
  	var jsonArr=[{
		"class":"btn-success",
		"icon":"glyphicon-plus",
		"click":"createMarketActivity()",
		"name":"创建活动"
	}];
	tableBtn('toolbar',jsonArr);//表格按钮创建
	
	var jsonArr2=[{
		"name":"活动名称",
		"htmlStr":'<input id="actv_name" type="text" maxlength="50" name="actv_name" autocomplete="off" class="layui-input">',
		"type":"other"
	},{
		"name":"活动状态",
		"htmlStr":"<select id='actv_state' name='actv_state'></select>",
		"type":"select",
		"id":"actv_state",
		"jsonArr":actvState
	},{
		"name":"活动开始日期",
		"htmlStr":'<input id="start_date" type="text" name="start_date" placeholder="开始日期起" autocomplete="off" class="layui-input" readonly="readonly" onclick="layui.laydate({elem: this, festival: true})"></div>'
					+'<div class="layui-form-mid"> - </div><div class="layui-input-inline">'
					+'<input id="stop_date" type="text" name="stop_date" placeholder="开始日期止" autocomplete="off" readonly="readonly" class="layui-input" onclick="layui.laydate({elem: this, festival: true})">',
		"type":"other"
	},{
		"name":"查询",
		"type":"btn",
		"form":"activitySearch",
		"cls":" layui-btn-normal"
	}];
	var formJson={
		"id":"activitySearch",//id=name
		"action":"#",
		"method":"post",
		"onsubmit":"return false;"//function
	}
	searchbar('searchbar',jsonArr2,formJson);//加载表格筛选条件
	
		//layui插件初始化
	layui.use(['form','laydate'], function() {
		var form = layui.form(),
			layer = layui.layer,
			laydate = layui.laydate;
		
		var start = {
			min: laydate.now(),
			max: '2099-12-31 23:59:59',
			istoday: false,
			choose: function(datas) {
				end.min = datas; //开始日选好后，重置结束日的最小日期
				end.start = datas //将结束日的初始值设定为开始日
			}
		};
		var end = {
			min: laydate.now(),
			max: '2099-12-31 23:59:59',
			istoday: false,
			choose: function(datas) {
				start.max = datas; //结束日选好后，重置开始日的最大日期
			}
		};
		document.getElementById('start_date').onclick = function() {
			start.elem = this;
			laydate(start);
		}
		document.getElementById('stop_date').onclick = function() {
				end.elem = this
				laydate(end);
			}
			//监听提交
		form.on('submit(activitySearch)', function(data) {
			layer.msg(JSON.stringify(data.field))
			return false;
		});
	});
});

/*function initTable(id,url,marketParse){
	
}
*/

 function createMarketActivity(){
 	layui.use('layer', function(){
	  var layer = layui.layer;
	  layer.open({
	  	title:"创建营销活动",
	  	type: 2,
		area: ['700px', '530px'],
		maxmin: true,
		content: 'marketAEC.html?action=add',
		scrollbar: false,
		end:filterPeople//回调客户筛选
	  });
	  
	});  
 }
function filterPeople(){
	layui.use('layer', function(){
	  var layer = layui.layer;
	  layer.open({
	  	title:"营销客户筛选",
	  	type: 2,
		area: ['700px', '530px'],
		maxmin: true,
		content: 'marketFilter.html?ACTV_NO=123&BATCH_NO=234',
		scrollbar: false,
		end:function(){
			layer.msg("end");
		}
	  });
	  
	});  
}
