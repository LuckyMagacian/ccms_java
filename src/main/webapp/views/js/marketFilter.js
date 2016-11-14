$(function() {
			paramObj = getParam();
			var jsonArr = {
				toolbar: [{
					"class": "btn-success",
					"icon": "glyphicon-plus",
					"click": "smartFilter()",
					"name": "智能筛选客户"
				}],
				searchbar: [{
					"name": "性别",
					"htmlStr": "<select id='gender' name='gender'></select>",
					"type": "select",
					"id": "gender",
					"jsonArr": gender
				}, {
					"name": "年龄范围",
					"htmlStr": '<input id="age_min" type="number" min="0" max="300" name="age_min" placeholder="min" autocomplete="off" class="layui-input"></div>' +
						'<div class="layui-form-mid"> -- </div><div class="layui-input-inline">' +
						'<input id="age_max" type="number" min="0" max="300" name="age_max" placeholder="max" autocomplete="off" class="layui-input">',
					"type": "other"
				}, {
					"name": "开户时长",
					"htmlStr": '<input id="opened_mths" type="number" min="0" max="999" name="opened_mths" autocomplete="off" class="layui-input"></div><div class="layui-form-mid">月',
					"type": "other"
				}, {
					"name": "单位性质",
					"htmlStr": "<select id='occ_catgry' name='occ_catgry'></select>",
					"type": "select",
					"id": "occ_catgry",
					"jsonArr": occCatgry
				}, {
					"name": "消费偏好",
					"htmlStr": "<select id='shopping' name='shopping'></select>",
					"type": "select",
					"id": "shopping",
					"jsonArr": shopping
				}, {
					"name": "自定义筛选",
					"type": "btn",
					"form": "filterSearch",
					"cls": " layui-btn-normal",
					"htmlStr": '<button lay-submit lay-filter="filterSearch" class="layui-btn layui-btn-normal">自定义筛选</button>'
				}],
				formInfo: {
					"id": "filterSearch", //id=name
					"action": "#",
					"method": "post",
					"onsubmit": "return false;" //function

				}
				installTable('filterTable', project + '/user/autoFilter.do', jsonArr, tableData);

				//layui插件初始化
				layui.use('form', function() {
					var form = layui.form(),
						layer = layui.layer;

					//监听提交
					form.on('submit(filterSearch)', function(data) {
						var temp = new Object(),
							actv_no = $("#actv_no").val(),
							batch_no = $("#batch_no").val();
						temp.actv_no = actv_no;
						temp.batch_no = batch_no;
						temp.condition = JSON.stringify(data.field);
						alert(temp);
						return false;
					});
				});
			});

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
			return temp;
		}