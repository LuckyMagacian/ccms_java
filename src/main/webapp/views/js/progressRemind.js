 $(function() {
  	$("#remindTable").DataTable({
  		dom: '<"top"<"toolbar">f<"searchbar">>rt<"bottom"lip>',
  		data: [
  			['1', '活动1', '任务名称1', '进行中', '短信模版1111', '<a>导出</a>', '<a>查看</a>', '[<a>修改</a>][<a>删除</a>]']
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
  	
	var jsonArr1=[{
		"class":"btn-success",
		"icon":"glyphicon-plus",
		"click":"remindCreate()",
		"name":"创建任务"
	}];
	tableBtn('toolbar',jsonArr1);//加载表格头部按钮
});

function remindCreate(){
	//layui插件初始化
	layui.use('layer', function() {
		var layer = layui.layer;
		layer.open({
		  	title:"创建提醒任务",
		  	type: 2,
			area: ['700px', '530px'],
			maxmin: true,
			content: 'remindCreate.html',
			scrollbar: false
		  });
		
	});
}
