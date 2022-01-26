/**
 * 根据基金名模糊查询基金信息
 */
function searchFund() {

    $('#dg').datagrid('load', {
        fundName: $('#s_name').textbox('getValue')
    });
}

var url;

/**
 * 打开新增基金窗口
 */
function openFundAddDialog() {
    $('#dlg').dialog({
        title: '添加基金',
        iconCls: 'add',
        closed: false,
        top: $(window).height() / 4,
        width: 500,
        height: 450,
        onClose: function () {
            $('#fundName').textbox('setValue', '');
            $('#outsideFund').textbox('setValue', '');
            $('#insideFund').textbox('setValue', '');
            $('#scope').textbox('setValue', '');
            $('#desc').textbox('setValue', '');
            $('#buildDate').textbox('setValue', '');
        }
    });

    url = "/fund/save";
}

/**
 * 打开修改基金窗口
 */
function openFundModifyDialog() {
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
        title: '修改基金',
        iconCls: 'update',
        closed: false,
        top: $(window).height() / 4,
        width: 500,
        height: 450,
        onClose: function () {
            $('#fundName').textbox('setValue', '');
            $('#outsideFund').textbox('setValue', '');
            $('#insideFund').textbox('setValue', '');
            $('#scope').textbox('setValue', '');
            $('#desc').textbox('setValue', '');
            $('#buildDate').textbox('setValue', '');
        }
    });

    url = "/fund/save?id=" + selections[0].id;
}

/**
 * 关闭窗口
 */
function closeDlg() {
    $('#fundName').textbox('setValue', '');
    $('#outsideFund').textbox('setValue', '');
    $('#insideFund').textbox('setValue', '');
    $('#scope').textbox('setValue', '');
    $('#desc').textbox('setValue', '');
    $('#buildDate').datebox('setValue', '');
    ;
    $('#dlg').dialog('close');
}

$(function () {
    //数据表格加载完毕后，绑定双击打开修改窗口事件
    $('#dg').datagrid({
        columns: [[
            {field: 'id', hidden:true},
            {field: 'fundName', title: '基金名称'},
            {field: 'outsideFund', title: '基金代码（场外）', width: 100},
            {field: 'insideFund', title: '基金代码（场内）', width: 100},
            {field: 'scope', title: '基金规模', width: 100},
            {field: 'buildDate', title: '成立时间', width: 100}
        ]],
        fit: true,
        fitColumns: true,
        pagination: true,
        rownumbers: true,
        singleSelect: false,
        url: "/deal/list",
        pageList: [30, 40, 50],
        pageSize: "30",
        toolbar: "#tb",
        striped: true,
        onDblClickRow: function (index, row) {
            //加载数据至表单
            $('#fm').form('load', row);
            $('#dlg').dialog({
                title: '修改基金',
                iconCls: 'update',
                closed: false,
                top: $(window).height() / 4,
                width: 500,
                height: 350,
                onClose: function () {
                    $('#name').textbox('setValue', '');
                    $('#code').textbox('setValue', '');
                    $('#scope').textbox('setValue', '');
                    $('#buildDate').textbox('setValue', '2020-01-01');
                }
            });

            url = "/fund/save?id=" + row.id;
        }
    })
});

/**
 * 保存基金信息
 */
function saveData() {
    $('#fm').form('submit', {
        url: url,
        onSubmit: function () {
            if ($('#fundName').val() === null || $('#fundName').val() === '') {
                $.messager.alert({
                    title: '系统提示',
                    msg: '请输入基金名称',
                    icon: 'error',
                    top: $(window).height() / 4
                });

                return false;
            }
            if ($('#outsideFund').val() === null || $('#outsideFund').val()
                === '') {
                $.messager.alert({
                    title: '系统提示',
                    msg: '请输入基金代码（场外）',
                    icon: 'error',
                    top: $(window).height() / 4
                });

                return false;
            }
            if ($('#insideFund').val() === null || $('#insideFund').val()
                === '') {
                $.messager.alert({
                    title: '系统提示',
                    msg: '请输入基金代码（场内）',
                    icon: 'error',
                    top: $(window).height() / 4
                });

                return false;
            }
            if ($('#scope').val() === null || $('#scope').val() === '') {
                $.messager.alert({
                    title: '系统提示',
                    msg: '请输入基金规模',
                    icon: 'error',
                    top: $(window).height() / 4
                });

                return false;
            }
            if ($('#desc').val() === null || $('#desc').val() === '') {
                $.messager.alert({
                    title: '系统提示',
                    msg: '请输入基金描述',
                    icon: 'error',
                    top: $(window).height() / 4
                });

                return false;
            }
            if ($('#buildDate').textbox() === null || $('#buildDate').textbox()
                === '') {
                $.messager.alert({
                    title: '系统提示',
                    msg: '请输入基金成立时间',
                    icon: 'error',
                    top: $(window).height() / 4
                });

                return false;
            }

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

/**
 * 删除用户信息
 */
function deleteFund() {
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
                    url: '/fund/delete',
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