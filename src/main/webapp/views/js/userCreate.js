$(function() {
	/**
	 * 初始化select下拉框
	 * 函数位于js>public.js
	 * selectOp(id,json)
	 * */
	selectOp('admin_type', userType);

//layui插件初始化
	layui.use('form', function() {
		var form = layui.form(),
			layer = layui.layer;
			//监听提交
		form.on('submit(userCreateForm)', function(data) {
			console.log(data.field);
			ajaxPost(project+'/admin/admin.add',data.field,function(jsonStr){
				layer.alert(JSON.stringify(jsonStr));
			});
			return false;
		});
	});
});