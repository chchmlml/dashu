/**
 * 根据基金名模糊查询基金信息
 */
function searchData() {

    $('#dg').datagrid('load', {
        name: $('#s_name').textbox('getValue'),
        date: $('#s_date').combobox('getValue')
    });
}

function formatScore(val, row) {
    return row.pbScore + row.peTtmScore + row.chgScore;
}

function formatPass(val, row) {
    return val ? '<span style="color:#63B239;">PASS</span>' : '<span style="color:#F9544D;">UNPASS</span>' ;
}

function formatChg(val, row) {
    var chg = (row.currentPrice - row.bugPrice) / row.bugPrice;
    return  (chg * 100).toFixed(2) + "%";
}

var url;
$(function () {
    $('#s_date').combobox({
        mode: 'remote',
        url: '/dashu/getComboboxListDate',
        valueField: 'date',
        textField: 'date'
    });

    $('#dg').datagrid({
        columns: [[
            {field: 'name', title: '股票名称', width: 100},
            {field: 'code', title: '股票代号', width: 100},
            {field: 'industry', title: '细分行业', width: 100},
            {field: 'pb', title: 'PB', width: 100},
            {field: 'pbScore', title: 'PB得分', width: 100},
            {field: 'peTtm', title: 'PE', width: 100},
            {field: 'peTtmScore', title: 'PE得分', width: 100},
            {field: 'chgScore', title: '涨幅得分', width: 100},
            {field: 'score', title: '总得分', width: 100, formatter: formatScore},
            {field: 'businessReputation', title: '商誉', width: 100, formatter: formatPass},
            {field: 'invisibleAssets', title: '无形资产', width: 100},
            {field: 'stockMortgage', title: '股权质押', width: 100, formatter: formatPass},
            {field: 'financial', title: '财务分析', width: 100, formatter: formatPass},
            {field: 'roe', title: 'ROE', width: 100},
            {field: 'currentPrice', title: '现价', width: 100},
            {field: 'minPrice', title: '一年低价', width: 100},
            {field: 'dividend', title: '股息率', width: 100},
            {field: 'bugPrice', title: '买入价', width: 100},
            {field: 'chg', title: '涨幅', width: 100, formatter: formatChg},
            {field: 'hold', title: '投入资金', width: 100}
        ]],
        fit: true,
        fitColumns: true,
        pagination: true,
        rownumbers: true,
        singleSelect: false,
        url: "/dashu/list",
        pageList: [30, 40, 50],
        pageSize: "30",
        toolbar: "#tb",
        striped: true
    })

});
