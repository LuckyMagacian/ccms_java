$(function() {
	/* 全局变量 */
	update_flag = null;
	//获取url的参数
	paramObj = getParam();
	AECSet(paramObj[0].action);

	/**
	 * 初始化select下拉框
	 * 函数位于js>public.js
	 * selectOp(id,json)
	 * */
	selectOp('actv_target', actvTarget);
	/*	selectOp('actv_style', actvStyle);
		selectOp('actv_type', actvType);
		selectOp('ssjh', actvRule);//活动规则
		selectOp('fqs',fqs);//分期数*/
	selectOp('actv_style', {});
	selectOp('actv_type', {});
	selectOp('rule_type', ruleType);
	selectOp('ssjh', {}); //活动规则
	selectOp('join_type', joinType); //参与方式
	selectOp('fqs', {}); //分期数
	//layui插件初始化
	layui.use(['form', 'laydate'], function() {
		var form = layui.form(),
			layer = layui.layer,
			laydate = layui.laydate;
		var index = parent.layer;
		/*var start = {
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
			}*/
		form.on('select(actv_target)', function(data) { //活动目的
			reloadItem();
			var $actvTypeItem = $("#actv_type").parents('.layui-form-item'); //活动类型对应的item对象
			switch(data.value) {
				case '1': //唤醒睡眠卡
					selectOp('actv_style', actvStyle1);
					$actvTypeItem.addClass('hidden');
					break;
				case '2': //提升业务量
					selectOp('actv_style', actvStyle2);
					$actvTypeItem.removeClass('hidden');
					break;
				default:
					console.log("error!活动目的不明,value=" + data.value);
					break;
			}
			form.render('select');
		});
		form.on('select(actv_style)', function(data) { //活动形式
			var $actvTypeItem = $("#actv_type").parents('.layui-form-item'), //活动类型对应的item对象
				$ssjhItem = $("#ssjh").parents('.layui-form-item'), //活动规则
				$skbsItem = $("#skbs").parents('.layui-form-item'), //刷卡笔数
				$dbjeItem = $("#dbje").parents('.layui-form-item'), //单笔金额
				$ljjeItem = $("#ljje").parents('.layui-form-item'), //累计金额
				$fqsItem = $("#fqs").parents('.layui-form-item'), //分期数
				$fqjeItem = $("#fqje").parents('.layui-form-item'); //分期金额
			switch(data.value) {
				case '1': //首刷激活
					$ssjhItem.removeClass('hidden'); //显示活动规则
					$skbsItem.addClass('hidden');
					$dbjeItem.addClass('hidden');
					$ljjeItem.addClass('hidden');
					$fqsItem.addClass('hidden');
					$fqjeItem.addClass('hidden');
					break;
				case '2': //冻卡激活
					$ssjhItem.addClass('hidden');
					$skbsItem.removeClass('hidden'); //显示刷卡笔数,单笔金额,累计金额
					$dbjeItem.removeClass('hidden');
					$ljjeItem.removeClass('hidden');
					$fqsItem.addClass('hidden');
					$fqjeItem.addClass('hidden');
					break;
				case '3': //分期
					selectOp('actv_type', actvType1);
					$ssjhItem.addClass('hidden');
					$skbsItem.addClass('hidden');
					$dbjeItem.addClass('hidden');
					$ljjeItem.addClass('hidden');
					$fqsItem.removeClass('hidden'); //显示分期数,分期金额
					$fqjeItem.removeClass('hidden');
					selectOp('fqs',fqs);//分期数
					break;
				case '4': //刷卡
					selectOp('actv_type', actvType2);
					$ssjhItem.addClass('hidden');
					$skbsItem.removeClass('hidden');
					$dbjeItem.removeClass('hidden');
					$ljjeItem.removeClass('hidden');
					$fqsItem.addClass('hidden');
					$fqjeItem.addClass('hidden');
					break;
				default:
					console.log("error!活动形式不明,value=" + data.value);
					break;
			}
			form.render('select');
		});
		form.on('select(join_type)',function(data){
			var $peopleLimitItem = $("#people_limit").parents('.layui-form-item'); //活动名额对应的item对象
			switch(data.value) {
				case '0': //自动选择
					selectOp('actv_style', actvStyle1);
					$peopleLimitItem.addClass('hidden');
					break;
				case '1': //主动报名
					selectOp('actv_style', actvStyle2);
					$peopleLimitItem.removeClass('hidden');
					break;
				default:
					console.log("error!参与方式不明,value=" + data.value);
					break;
			}
		});
		//监听提交
		form.on('submit(marketAECForm)', function(data) {
			var temp = data.field,
				actvStyle = temp.actv_style;
			temp.prop = [];
			switch(actvStyle) {
				case '1': //首刷激活
					temp.prop.push({
						'date_key': '',
						'source_table': '',
						'operation': '',
						'cust_pk': '',
						'prop_key': 'SSJH',
						'compare': '',
						'prop_value': temp.ssjh
					});
					break;
				case '2': //东卡激活
				case '4': //刷卡
					if(temp.skbs != '') { //刷卡笔数不为空
						temp.prop.push({
							'date_key': 'INP_DATE',
							'source_table': 'EVENT_D',
							'operation': 'count',
							'cust_pk': 'ACCTNBR',
							'prop_key': 'BILL_AMT',
							'compare': '>=',
							'prop_value': temp.skbs
						});
					}
					if(temp.dbje != '') { //单笔金额不为空
						temp.prop.push({
							'date_key': 'INP_DATE',
							'source_table': 'EVENT_D',
							'operation': 'compare',
							'cust_pk': 'ACCTNBR',
							'prop_key': 'BILL_AMT',
							'compare': '>=',
							'prop_value': temp.dbje
						});
					}
					if(temp.ljje != '') { //累计金额不为空
						temp.prop.push({
							'date_key': 'INP_DATE',
							'source_table': 'EVENT_D',
							'operation': 'sum',
							'cust_pk': 'ACCTNBR',
							'prop_key': 'BILL_AMT',
							'compare': '>=',
							'prop_value': temp.ljje
						});
					}
					break;
				case '3': //分期
					if(temp.fqs != '') { //分期数不为空
						temp.prop.push({
							'date_key': 'PURCH_DAY',
							'source_table': 'MPUR_D',
							'operation': 'compare',
							'cust_pk': 'XACCOUNT',
							'prop_key': 'NBR_MTHS',
							'compare': '>=',
							'prop_value': temp.fqs
						});
					}
					if(temp.fqje != '') { //分期金额不为空
						temp.prop.push({
							'date_key': 'PURCH_DAY',
							'source_table': 'MPUR_D',
							'operation': 'compare',
							'cust_pk': 'XACCOUNT',
							'prop_key': 'ORIG_PURCH',
							'compare': '>=',
							'prop_value': temp.fqje
						});
					}
					break;
				default:
					console.log("未定义的错误! actvStyle=" + actvStyle);
					break;
			}
			temp.prop = JSON.stringify(temp.prop);
			$.ajax({
				type: "post",
				url: project + "/activity/generatActivity.do",
				dataType: 'json',
				data: temp,
				beforeSend: function() {
					layer.open({
						type: 3,
						scrollbar: false
					});
				},
				success: function(jsonStr) {
					if(jsonStr.errCode == "0000") {

						var actv_no = jsonStr.content.actv_no,
							batch_no = jsonStr.content.batch_no;
						var index = parent.layer.getFrameIndex(window.name); //先得到当前iframe层的索引
						filterPeople(actv_no, batch_no, update_flag);
						parent.layer.close(index); //执行关闭自身操作

					} else {
						layer.alert(jsonStr.errMsg);
					}
					layer.closeAll('loading');
				},
				error: function(e) {
					console.log("error:" + JSON.stringify(e));
				}
			});
			return false;
		});
	});
});

function filterPeople(actv_no, batch_no, update_flag) {
	layui.use('layer', function() {
		var layer = layui.layer;
		parent.layer.open({
			title: "营销客户筛选",
			type: 2,
			area: ['700px', '560px'],
			maxmin: false,
			content: 'marketFilter.html?actv_no=' + actv_no + '&batch_no=' + batch_no + '&update_flag=' + update_flag,
			scrollbar: false
				/*,cancel: function() {
					//询问框
					layer.confirm('手动关闭可能导致活动异常,确认?', {
						offset:"100px",
						btn: ['确定','取消'] //按钮
					}, function(){
					  return true;
					},function(){
						return false;
					});
				}*/
		});

	});
}

function AECSet(action) {
	switch(action) {
		case 'add':
			update_flag = 0;
			break;
		default:
			console.log("未定义action:" + action);
			break;
	}
}

function reloadItem() {
	var $actvTypeItem = $("#actv_type").parents('.layui-form-item'), //活动类型对应的item对象
		$ssjhItem = $("#ssjh").parents('.layui-form-item'), //活动规则
		$skbsItem = $("#skbs").parents('.layui-form-item'), //刷卡笔数
		$dbjeItem = $("#dbje").parents('.layui-form-item'), //单笔金额
		$ljjeItem = $("#ljje").parents('.layui-form-item'), //累计金额
		$fqsItem = $("#fqs").parents('.layui-form-item'), //分期数
		$fqjeItem = $("#fqje").parents('.layui-form-item'); //分期金额

	$ssjhItem.addClass('hidden');
	$skbsItem.addClass('hidden');
	$dbjeItem.addClass('hidden');
	$ljjeItem.addClass('hidden');
	$fqsItem.addClass('hidden');
	$fqjeItem.addClass('hidden');
}