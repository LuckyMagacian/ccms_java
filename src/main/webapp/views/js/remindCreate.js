$(function() {
	/**
	 * 初始化select下拉框
	 * 函数位于js>public.js
	 * selectOp(id,json)
	 * */
	selectOp('actv_name', actvName);

//layui插件初始化
	layui.use('form', function() {
		var form = layui.form(),
			layer = layui.layer;

			//监听提交
		form.on('submit(remindCreateForm)', function(data) {
			console.log(data.field);
			return false;
		});
	});
});