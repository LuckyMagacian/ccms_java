$(function() {
	ajaxPost("/chart/chart.do", {}, initChart);
});

/**
 * 
 * */

function initChart(jsonStr) {
	if(jsonStr.statusCode == 200) {
		/* 月度占比饼状图 */
		monthPie = jsonStr.content[0]['01'];
		monthPie = eval('(' + monthPie + ')');
		$('#monthPie').highcharts({
			chart: {
				plotBackgroundColor: null,
				plotBorderWidth: null,
				plotShadow: false
			},
			title: false,
			tooltip: {
				pointFormat: '{series.name}: <b>{point.percentage:.1f}%</b>'
			},
			plotOptions: {
				pie: {
					allowPointSelect: true,
					cursor: 'pointer',
					dataLabels: {
						enabled: false
					},
					showInLegend: true
				}
			},
			series: [{
				type: 'pie',
				name: '占比',
				data: [{
					name: '分期',
					y: monthPie.installment
				}, {
					name: '取现',
					y: monthPie.cash
				}, {
					name: '刷卡',
					y: monthPie.swipe
				}, {
					name: '循环',
					y: monthPie.cycle
				}]
			}],
			credits: {
				enabled: false // 禁用版权信息
			}
		});

		/* 本月与上月柱状图 */
		var contrastBar = jsonStr.content[1]['02'];
		contrastBar = eval('(' + contrastBar + ')');
		last_month = contrastBar.last_month;
		var current_month = contrastBar.current_month;
		$('#contrastBar').highcharts({
			chart: {
				type: 'column'
			},
			title: false,
			xAxis: {
				categories: [
					'分期',
					'取现',
					'刷卡',
					'循环'
				],
				crosshair: true
			},
			yAxis: {
				min: 0,
				title: {
					text: '金额 (元)'
				}
			},
			tooltip: {
				headerFormat: '<span style="font-size:10px">{point.key}</span><table>',
				pointFormat: '<tr><td style="color:{series.color};padding:0">{series.name}: </td>' +
					'<td style="padding:0"><b>{point.y}元</b></td></tr>',
				footerFormat: '</table>',
				shared: true,
				useHTML: true
			},
			plotOptions: {
				column: {
					pointPadding: 0.2,
					borderWidth: 0
				}
			},
			series: [{
				name: '上月',
				data: [last_month.installment, last_month.cash, last_month.swipe, last_month.cycle]
			}, {
				name: '本月',
				data: [current_month.installment, current_month.cash, current_month.swipe, current_month.cycle]
			}],
			credits: {
				enabled: false // 禁用版权信息
			}
		});

		/* 生命周期柱状图 */
		var lifecycleBar = jsonStr.content[3]['04'];
		lifecycleBar = eval('(' + lifecycleBar + ')');
		$('#lifecycleBar').highcharts({
			chart: {
				type: 'column'
			},
			title: false,
			xAxis: {
				categories: [
					'加速期',
					'加速期',
					'稳定期',
					'衰退期',
					'流失期'
				],
				crosshair: true
			},
			yAxis: {
				min: 0,
				title: {
					text: '人数 (人)'
				}
			},
			tooltip: {
				headerFormat: '<span style="font-size:10px">{point.key}</span><table>',
				pointFormat: '<tr><td style="padding:0"><b>{point.y} 人</b></td></tr>',
				footerFormat: '</table>',
				shared: true,
				useHTML: true
			},
			plotOptions: {
				column: {
					pointPadding: 0.2,
					borderWidth: 0
				}
			},
			series: [{
				name: '用户生命周期',
				data: [lifecycleBar[1], lifecycleBar[2], lifecycleBar[3], lifecycleBar[4], lifecycleBar[5]]
			}],
			credits: {
				enabled: false // 禁用版权信息
			}
		});

		/* 用户忠诚度 */
		var loyaltyBar = jsonStr.content[4]['05'];
		loyaltyBar = eval('(' + loyaltyBar + ')');
		$('#loyaltyBar').highcharts({
			chart: {
				type: 'column'
			},
			title: false,
			xAxis: {
				categories: [
					'低',
					'中',
					'高'
				],
				crosshair: true
			},
			yAxis: {
				min: 0,
				title: {
					text: '人数 (人)'
				}
			},
			tooltip: {
				headerFormat: '<span style="font-size:10px">{point.key}</span><table>',
				pointFormat: '<tr><td style="padding:0"><b>{point.y} 人</b></td></tr>',
				footerFormat: '</table>',
				shared: true,
				useHTML: true
			},
			plotOptions: {
				column: {
					pointPadding: 0.2,
					borderWidth: 0
				}
			},
			series: [{
				name: '用户忠诚度',
				data: [loyaltyBar[1], loyaltyBar[2], loyaltyBar[3]]
			}],
			credits: {
				enabled: false // 禁用版权信息
			}
		});

		/* 客户价值 */
		var worthBar = jsonStr.content[4]['05'];
		worthBar = eval('(' + worthBar + ')');
		$('#worthBar').highcharts({
			chart: {
				type: 'column'
			},
			title: false,
			xAxis: {
				categories: [
					'低',
					'中',
					'高'
				],
				crosshair: true
			},
			yAxis: {
				min: 0,
				title: {
					text: '人数 (人)'
				}
			},
			tooltip: {
				headerFormat: '<span style="font-size:10px">{point.key}</span><table>',
				pointFormat: '<tr><td style="padding:0"><b>{point.y} 人</b></td></tr>',
				footerFormat: '</table>',
				shared: true,
				useHTML: true
			},
			plotOptions: {
				column: {
					pointPadding: 0.2,
					borderWidth: 0
				}
			},
			series: [{
				name: '客户价值',
				data: [worthBar[1], worthBar[2], worthBar[3]]
			}],
			credits: {
				enabled: false // 禁用版权信息
			}
		});
		
		monthData();
	} else {
		console.log(jsonStr.message);
	}

}

function monthData(){
	var temp='',
		temp1='<div class="col-sm-3 col-xs-6"><div class="description-block border-right"><span class="description-percentage text-green"><i class="fa fa-caret-up"></i> ',
		temp2='<div class="col-sm-3 col-xs-6"><div class="description-block border-right"><span class="description-percentage text-red"><i class="fa fa-caret-down"></i> ',
		temp3='<div class="col-sm-3 col-xs-6"><div class="description-block"><span class="description-percentage text-green"><i class="fa fa-caret-up"></i> ',
		temp4='<div class="col-sm-3 col-xs-6"><div class="description-block"><span class="description-percentage text-red"><i class="fa fa-caret-down"></i> ',
		//本月
		installment=monthPie.installment,//分期
		cash=monthPie.cash,//取现
		swipe=monthPie.swipe,//刷卡
		cycle=monthPie.cycle;//循环
		//上月
		installmentBefore=last_month.installment,//分期
		cashBefore=last_month.cash,//取现
		swipeBefore=last_month.swipe,//刷卡
		cycleBefore=last_month.cycle,//循环
		//对比(增长/减少比例)
		installmentBD=(installment-installmentBefore)/installment*100,//分期
		cashBD=(cash-cashBefore)/cash*100,//取现
		swipeBD=(swipe-swipeBefore)/swipe*100,//刷卡
		cycleBD=(cycle-cycleBefore)/cycle*100;//循环

		installmentBD<0?temp+=temp2:temp+=temp1;
		temp+=(Math.abs(installmentBD.toFixed(2)))+'%</span><h5 class="description-header">'+installment+'元</h5><span class="description-text">分期</span></div></div>';
		
		cashBD<0?temp+=temp2:temp+=temp1;
		temp+=(Math.abs(cashBD.toFixed(2)))+'%</span><h5 class="description-header">'+cash+'元</h5><span class="description-text">取现</span></div></div>';
		
		swipeBD<0?temp+=temp2:temp+=temp1;
		temp+=(Math.abs(swipeBD.toFixed(2)))+'%</span><h5 class="description-header">'+swipe+'元</h5><span class="description-text">刷卡</span></div></div>';
		
		cycleBD<0?temp+=temp4:temp+=temp3;
		temp+=(Math.abs(cycleBD.toFixed(2)))+'%</span><h5 class="description-header">'+cycle+'元</h5><span class="description-text">循环</span></div></div>';
		
		$("#monthData").html(temp);
}
