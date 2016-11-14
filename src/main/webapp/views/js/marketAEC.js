$(function() {
	//获取url的参数
	paramObj= getParam();
	/**
	 * 初始化select下拉框
	 * 函数位于js>public.js
	 * selectOp(id,json)
	 * */
	selectOp('actv_target', actvTarget);
	selectOp('actv_style', actvStyle);
	selectOp('actv_type', actvType);
	selectOp('rule_type', ruleType);
	selectOp('ssjh', actvRule);//活动规则
	selectOp('join_type', joinType);//参与方式
	selectOp('fqs',fqs);//分期数
	//layui插件初始化
	layui.use(['form', 'laydate'], function() {
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
		form.on('submit(marketAECForm)', function(data) {
			var temp=data.field,
				actvStyle=temp.actv_style;
			temp.prop=[];
			switch (actvStyle){
				case '1'://首刷激活
					temp.prop.push({
						"date_key":"",
						"source_table":"",
						"operation":"",
						"cust_pk":"",
						"prop_key":"SSJH",
						"compare":"",
						"prop_value":temp.ssjh
					});
					break;
				case '2'://东卡激活
				case '4'://刷卡
					if(temp.skbs!=""){//刷卡笔数不为空
						temp.prop.push({
							"date_key":"INP_DATE",
							"source_table":"EVENT_D",
							"operation":"count",
							"cust_pk":"ACCTNBR",
							"prop_key":"BILL_AMT",
							"compare":">=",
							"prop_value":temp.skbs
						});
					}
					if(temp.dbje!=""){//单笔金额不为空
						temp.prop.push({
							"date_key":"INP_DATE",
							"source_table":"EVENT_D",
							"operation":"compare",
							"cust_pk":"ACCTNBR",
							"prop_key":"BILL_AMT",
							"compare":">=",
							"prop_value":temp.dbje
						});
					}
					if(temp.ljje!=""){//累计金额不为空
						temp.prop.push({
							"date_key":"INP_DATE",
							"source_table":"EVENT_D",
							"operation":"sum",
							"cust_pk":"ACCTNBR",
							"prop_key":"BILL_AMT",
							"compare":">=",
							"prop_value":temp.ljje
						});
					}
					break;
				case '3'://分期
					if(temp.fqs!=""){//分期数不为空
						temp.prop.push({
							"date_key":"PURCH_DAY",
							"source_table":"MPUR_D",
							"operation":"compare",
							"cust_pk":"XACCOUNT",
							"prop_key":"NBR_MTHS",
							"compare":">=",
							"prop_value":temp.fqs
						});
					}
					if(temp.fqje!=""){//分期金额不为空
						temp.prop.push({
							"date_key":"PURCH_DAY",
							"source_table":"MPUR_D",
							"operation":"compare",
							"cust_pk":"XACCOUNT",
							"prop_key":"ORIG_PURCH",
							"compare":">=",
							"prop_value":temp.fqje
						});
					}
					break;
				default:console.log("未定义的错误! actvStyle="+actvStyle);
					break;
			}
			$.ajax({
				type:"post",
				url:project+"/activity/generatActivity.do",
				dataType:'json',
				data:temp,
				beforeSend:function(){
					layer.open({
						type:3,
						scrollbar: false
					});
				},
				success:function(jsonStr){
					if(jsonStr.errCode=="0000"){
						var actv_no=jsonStr.content.actv_no,
							batch_no=jsonStr.content.batch_no;
						filterPeople(actv_no,batch_no);
						/*var index = parent.layer.getFrameIndex(window.name); //先得到当前iframe层的索引
 							parent.layer.close(index); //执行关闭自身操作*/
						
					}else{
						layer.alert(jsonStr.errMsg);
					}
					layer.closeAll('loading');
				},
				error:function(e){
					console.log("error:"+JSON.stringify(e));
				}
			});
			return false;
		});
	});
});


function filterPeople(actv_no,batch_no) {
	layui.use('layer', function() {
		var layer = layui.layer;
		layer.open({
			title: "营销客户筛选",
			type: 2,
			area: ['700px', '530px'],
			maxmin: true,
			content: 'marketFilter.html?actv_no='+actv_no+'&batch_no='+batch_no,
			scrollbar: false,
			cancel: function() {
				//询问框
				layer.confirm('手动关闭可能导致活动异常,确认?', {
					offset:"100px",
					btn: ['确定','取消'] //按钮
				}, function(){
				  return true;
				},function(){
					return false;
				});
			}
		});

	});
}
