
$(function () {
    var echartsA = echarts.init(document.getElementById('tpl-echarts-A'));
    $.ajax({
        url: '/charts',
        dataType: 'json',
        type: 'get',
        success: function (data) {
            echartsA.setOption({
                title: {
                    text: '资产盘点趋势'
                },
                tooltip: {},
                legend: {
                    data:['汇总']
                },
                xAxis: {
                    data: data.xaxis_data
                },
                yAxis: {},
                series: [{
                    name: '汇总',
                    type: 'bar',
                    data: data.series_data
                }]
            });
        }
    });

});