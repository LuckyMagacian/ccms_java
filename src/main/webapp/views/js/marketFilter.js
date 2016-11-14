 $(function() {
 	paramObj= getParam();
  	$("#filterTable").DataTable({
  		dom: '<"top"<"toolbar"><"searchbar">>rt<"bottom"lip>',
  		data: [
  			['1','张三', '333444324123433412342355', '15757857341']
  		],
  		//pageSize:20,
  		language: {
  			"lengthMenu": "每页 _MENU_ 条记录",
  			"zeroRecords": "没有找到记录",
  			"info": "第 _PAGE_ 页 ( 总共 _PAGES_ 页 )",
  			"infoEmpty": "无记录",
  			"infoFiltered": "(从 _MAX_ 条记录过滤)"
  		}
  		//,ajax:
  	}); 
  	
  	var jsonArr1=[{
		"class":"btn-success",
		"icon":"glyphicon-plus",
		"click":"smartFilter()",
		"name":"智能筛选客户"
	}];
	tableBtn('toolbar',jsonArr1);//加载表格头部按钮
	
	var jsonArr2=[{
		"name":"性别",
		"htmlStr":"<select id='gender' name='gender'></select>",
		"type":"select",
		"id":"gender",
		"jsonArr":gender
	},{
		"name":"年龄范围",
		"htmlStr":'<input id="age_min" type="number" min="0" max="300" name="age_min" placeholder="min" autocomplete="off" class="layui-input"></div>'
					+'<div class="layui-form-mid"> -- </div><div class="layui-input-inline">'
					+'<input id="age_max" type="number" min="0" max="300" name="age_max" placeholder="max" autocomplete="off" class="layui-input">',
		"type":"other"
	},{
		"name":"开户时长",
		"htmlStr":'<input id="opened_mths" type="number" min="0" max="999" name="opened_mths" autocomplete="off" class="layui-input"></div><div class="layui-form-mid">月',
		"type":"other"
	},{
		"name":"单位性质",
		"htmlStr":"<select id='occ_catgry' name='occ_catgry'></select>",
		"type":"select",
		"id":"occ_catgry",
		"jsonArr":occCatgry
	},{
		"name":"消费偏好",
		"htmlStr":"<select id='shopping' name='shopping'></select>",
		"type":"select",
		"id":"shopping",
		"jsonArr":shopping
	},{
		"name":"自定义筛选",
		"type":"btn",
		"form":"filterSearch",
		"cls":" layui-btn-normal",
		"htmlStr":'<button lay-submit lay-filter="filterSearch" class="layui-btn layui-btn-normal">自定义筛选</button>'
	}];
	var formJson={
		"id":"filterSearch",//id=name
		"action":"#",
		"method":"post",
		"onsubmit":"return false;"//function
	}
	searchbar('searchbar',jsonArr2,formJson);//加载表格筛选条件
	
	//layui插件初始化
	layui.use('form', function() {
		var form = layui.form(),
			layer = layui.layer;

			//监听提交
		form.on('submit(filterSearch)', function(data) {
			var temp=new Object(),
				actv_no=$("#actv_no").val(),
				batch_no=$("#batch_no").val();
			temp.actv_no=actv_no;
			temp.batch_no=batch_no;
			temp.condition=JSON.stringify(data.field);
			alert(temp);
			return false;
		});
	});
});