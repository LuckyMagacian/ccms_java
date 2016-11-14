 $(function() {
 	jsonArr={
		"toolbar":[{
			"class":"btn-success",
			"icon":"glyphicon-plus",
			"click":"userCreate()",
			"name":"添加用户"
		}]
	}
  	installTable('userManageTable',project+'/admin/admin.getAllAdmin',jsonArr,dataFormat);
});

/**
 * 表格数据格式化
 * */
function dataFormat(jsonStr){
	var temp=new Array();
	if(jsonStr.statusCode==200){
		var jsonArr=jsonStr.adminList;//[{"admin_Id":"2","admin_Type":"1","username":"3","password":"3"},{}]
		$.each(jsonArr,function(i){
			var tempRow=new Array();
			tempRow[0]=i+1;
			tempRow[1]=userType[this.admin_Type];
			tempRow[2]=this.username;
			tempRow[3]=this.admin_Id;
			tempRow[4]='[<a href="javascript:editUser('+this.admin_Id+')">修改</a>][<a href="javascript:delUser('+this.admin_Id+')">删除</a>]';
			temp[i]=tempRow;
		});
	}else{
		console.log("error! "+jsonStr.message);
	}
	return temp;
}

/* 添加用户 */
function userCreate(){
	//layui插件初始化
	layui.use('layer', function() {
		var layer = layui.layer;
		layer.open({
		  	title:"添加用户",
		  	type: 2,
			area: ['500px', '330px'],
			maxmin: true,
			content: 'userCreate.html',
			scrollbar: false
		  });
		
	});
}

/* 修改用户 */
function editUser(id){
	layui.use('layer', function() {
		var layer = layui.layer;
		layer.prompt({
		  formType: 1,
		  value: '123456',
		  offset:"100px",
		  title: '重置密码'
		}, function(value, index, elem){
		  ajaxPost('/admin/admin.update',{'password':value,'admin_Id':id},function(jsonStr){
		  	if(jsonStr.statusCode==200){
		  		layer.msg('修改密码成功');
		  		layer.close(index);
		  	}else{
		  		layer.msg(jsonStr.message);
		  	}
		  });
		});
	});
}

/* 删除用户 */
function delUser(id){
	//询问框
	layer.confirm('确认删除该用户？', {
		offset:"100px",
		btn: ['确定','取消'] //按钮
	}, function(){
	  ajaxPost(project+'/admin/admin.delete',{'password':value},function(jsonStr){
		  	
		  	alert(JSON.stringify(jsonStr));
		  });
	});
}
