$(function () {
	/* 月度占比饼状图 */
    $('#monthPie').highcharts({
        chart: {
            plotBackgroundColor: null,
            plotBorderWidth: null,
            plotShadow: false
        },
        title:false,
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
            	name:'分期',
            	y:3453
            },{
            	name:'取现',
            	y:1253
            },{
            	name:'刷卡',
            	y:5433
            },{
            	name:'循环',
            	y:7521
            }
            ]
        }],
        credits:{
            enabled:false // 禁用版权信息
       }
    });
    
    /* 生命周期柱状图 */
   $('#lifecycleBar').highcharts({
        chart: {
            type: 'column'
        },
        title:false,
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
            pointFormat: '<tr><td style="padding:0"><b>{point.y:.1f} 人</b></td></tr>',
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
            data: [49.9, 71.5, 106.4,56,33]
        }],credits:{
            enabled:false // 禁用版权信息
       }
    });
    /* 用户忠诚度 */
   $('#loyaltyBar').highcharts({
        chart: {
            type: 'column'
        },
        title:false,
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
            pointFormat: '<tr><td style="padding:0"><b>{point.y:.1f} 人</b></td></tr>',
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
            data: [44,46,88]
        }],credits:{
            enabled:false // 禁用版权信息
       }
    });
    /* 用户忠诚度 */
   $('#worthBar').highcharts({
        chart: {
            type: 'column'
        },
        title:false,
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
            pointFormat: '<tr><td style="padding:0"><b>{point.y:.1f} 人</b></td></tr>',
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
            data: [44,46,88]
        }],credits:{
            enabled:false // 禁用版权信息
       }
    });
    /* 本月与上月柱状图 */
   $('#contrastBar').highcharts({
        chart: {
            type: 'column'
        },
        title:false,
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
                text: '人数 (人)'
            }
        },
        tooltip: {
            headerFormat: '<span style="font-size:10px">{point.key}</span><table>',
            pointFormat: '<tr><td style="color:{series.color};padding:0">{series.name}: </td>' +
            '<td style="padding:0"><b>{point.y}人</b></td></tr>',
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
            data: [499, 715, 106,543]
        },{
        	name: '本月',
            data: [533, 786, 123,567]
        }],credits:{
            enabled:false // 禁用版权信息
       }
    });
});
