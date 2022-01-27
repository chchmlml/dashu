/**
 * 根据基金名模糊查询基金信息
 */
function searchData() {

    $('#dg').datagrid('load', {
        name: $('#s_name').textbox('getValue'),
        date: $('#s_date').combobox('getValue')
    });
}

function clearText() {
    $('#name').textbox('setValue', '');
    $('#code').textbox('setValue', '');
    $('#industry').textbox('setValue', '');
    $('#pb').textbox('setValue', '');
    $('#pbScore').textbox('setValue', '');
    $('#peTtmScore').textbox('setValue', '');
    $('#chgScore').textbox('setValue', '');
    $('#businessReputation').textbox('setValue', '');
    $('#invisibleAssets').textbox('setValue', '');
    $('#stockMortgage').textbox('setValue', '');
    $('#financial').textbox('setValue', '');
    $('#roe').textbox('setValue', '');
    $('#minPrice').textbox('setValue', '');
    $('#dividend').textbox('setValue', '');
    $('#bugPrice').textbox('setValue', '');
    $('#hold').textbox('setValue', '');
}
var url;
/**
 * 打开新增条目窗口
 */
function openAddDialog() {
    $('#dlg').dialog({
        title: '添加',
        iconCls: 'add',
        closed: false,
        top: $(window).height() / 4,
        width: '80%',
        height: 450,
        modal: true,
        onClose: function () {
            clearText()
        }
    });

    url = "/dashu/save";
}


/**
 * 打开修改条目窗口
 */
function openModifyDialog() {
    var selections = $('#dg').datagrid('getSelections');
    if (selections.length < 1) {
        $.messager.alert({
            title: '系统提示',
            msg: '请选择一条您要修改的记录',
            icon: 'error',
            top: $(window).height() / 4
        });
        return;
    }
    //加载数据至表单
    $('#fm').form('load', selections[0]);
    //设置窗口相关属性，并打开
    $('#dlg').dialog({
        title: '修改条目',
        iconCls: 'update',
        closed: false,
        top: $(window).height() / 4,
        width: '80%',
        height: 450,
        modal: true,
        onClose: function () {
            clearText()
        }
    });

    url = "/dashu/save?id=" + selections[0].id;
}


/**
 * 关闭窗口
 */
function closeDlg() {
    clearText()
    $('#dlg').dialog('close');
}

function formatScore(val, row) {
    return row.pbScore + row.peTtmScore + row.chgScore;
}

function formatPass(val, row) {
    return (val == 'PASS') ? '<span style="color:#63B239;">PASS</span>' : '<span style="color:#FEA043;">' + val + '</span>';
}

function formatChg(val, row) {
    var chg = (row.currentPrice - row.bugPrice) / row.bugPrice;
    if (chg < 0) {
        return '<span style="color:#63B239;">' + (chg * 100).toFixed(2) + '%↓</span>';
    } else {
        return '<span style="color:#F9544D;">' + (chg * 100).toFixed(2) + '%↑</span>';
    }
}

var url;
$(function () {
    $('#dashuId').combobox({
        mode: 'remote',
        url: '/dashu/getComboboxListDashu',
        valueField: 'id',
        textField: 'name'
    });

    $('#dg').datagrid({
        columns: [[
            { field: 'name', title: '股票名称', width: 100 },
            { field: 'code', title: '股票代号', width: 100 },
            { field: 'industry', title: '细分行业', width: 100 },
            { field: 'pb', title: 'PB', width: 100 },
            { field: 'pbScore', title: 'PB得分', width: 100 },
            { field: 'peTtm', title: 'PE', width: 100 },
            { field: 'peTtmScore', title: 'PE得分', width: 100 },
            { field: 'chgScore', title: '涨幅得分', width: 100 },
            { field: 'score', title: '总得分', width: 100, formatter: formatScore },
            { field: 'businessReputation', title: '商誉', width: 100, formatter: formatPass },
            { field: 'invisibleAssets', title: '无形资产', width: 100 },
            { field: 'stockMortgage', title: '股权质押', width: 100, formatter: formatPass },
            { field: 'financial', title: '财务分析', width: 100, formatter: formatPass },
            { field: 'roe', title: 'ROE', width: 100 },
            { field: 'currentPrice', title: '现价', width: 100 },
            { field: 'minPrice', title: '一年低价', width: 100 },
            { field: 'dividend', title: '股息率', width: 100 },
            { field: 'bugPrice', title: '买入价', width: 100 },
            { field: 'chg', title: '涨幅', width: 100, formatter: formatChg },
            { field: 'hold', title: '投入资金', width: 100 }
        ]],
        fit: true,
        fitColumns: true,
        pagination: true,
        rownumbers: true,
        singleSelect: false,
        url: "/dashu/list?dashu_id=" + param("dashu_id"),
        pageList: [30, 40, 50],
        pageSize: "30",
        toolbar: "#tb",
        striped: true
    })

});

/**
 * 保存条目信息
 */
function saveData() {
    $('#fm').form('submit', {
        url: url,
        onSubmit: function () {
            // if ($('#itemName').val() === null || $('#itemName').val() === '') {
            //     $.messager.alert({
            //         title: '系统提示',
            //         msg: '请输入条目名称',
            //         icon: 'error',
            //         top: $(window).height() / 4
            //     });

            //     return false;
            // }

            return true;
        },
        success: function (result) {
            var resultJson = eval('(' + result + ')');
            if (resultJson.code === 100) {
                $.messager.alert({
                    title: '系统提示',
                    msg: '保存成功',
                    icon: 'info',
                    top: $(window).height() / 4
                });
                $('#dlg').dialog('close');
                $('#dg').datagrid('reload');
            } else {
                $.messager.alert({
                    title: '系统提示',
                    msg: resultJson.msg,
                    icon: 'error',
                    top: $(window).height() / 4
                });
            }
        }
    })
}


function deleteDashu() {
    var selections = $('#dg').datagrid('getSelections');
    if (selections.length < 1) {
        $.messager.alert({
            title: '系统提示',
            msg: '请选择您要删除的记录',
            icon: 'error',
            top: $(window).height() / 4
        });
        return;
    }
    $.messager.confirm({
        title: '系统提示',
        msg: '您确定要删除这' + selections.length + '条记录吗？',
        top: $(window).height() / 4,
        fn: function (r) {
            if (r) {
                var idsAr = [];
                for (var i = 0; i < selections.length; i++) {
                    idsAr.push(selections[i].id);
                }
                var ids = idsAr.join(",");
                $.ajax({
                    url: '/dashu/delete',
                    dataType: 'json',
                    type: 'post',
                    data: {
                        'ids': ids
                    },
                    success: function (result) {
                        if (result.code === 100) {
                            $.messager.alert({
                                title: '系统提示',
                                msg: '删除成功',
                                icon: 'info',
                                top: $(window).height() / 4
                            });
                            $('#dg').datagrid('reload');
                        } else {
                            $.messager.alert({
                                title: '系统提示',
                                msg: result.msg,
                                icon: 'error',
                                top: $(window).height() / 4
                            });
                        }
                    }
                });
            }
        }
    })
}