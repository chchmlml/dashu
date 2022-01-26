/**
 * 根据指数名模糊查询指数信息
 */
function searchIndex() {

    $('#dg').datagrid('load', {
        IndexName: $('#s_name').textbox('getValue')
    });
}

var url;

/**
 * 打开新增指数窗口
 */
function openIndexAddDialog() {
    $('#dlg').dialog({
        title: '添加指数',
        iconCls: 'add',
        closed: false,
        top: $(window).height() / 4,
        width: 500,
        height: 450,
        onClose: function () {
            $('#name').textbox('setValue', '');
            $('#code').textbox('setValue', '');
        }
    });

    url = "/index/save";
}

/**
 * 打开修改指数窗口
 */
function openIndexModifyDialog() {
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
        title: '修改指数',
        iconCls: 'update',
        closed: false,
        top: $(window).height() / 4,
        width: 500,
        height: 450,
        onClose: function () {
            $('#name').textbox('setValue', '');
            $('#code').textbox('setValue', '');
        }
    });

    url = "/index/save?id=" + selections[0].id;
}

/**
 * 关闭窗口
 */
function closeDlg() {
    $('#name').textbox('setValue', '');
    $('#code').textbox('setValue', '');
    ;
    $('#dlg').dialog('close');
}

$(function () {
    //数据表格加载完毕后，绑定双击打开修改窗口事件
    $('#dg').datagrid({
        columns: [[
            {field: 'id', hidden: true},
            {field: 'name', title: '指数名称'},
            {field: 'code', title: '指数code', width: 100}
        ]],
        fit: true,
        fitColumns: true,
        pagination: true,
        rownumbers: true,
        singleSelect: false,
        url: "/index/list",
        pageList: [30, 40, 50],
        pageSize: "30",
        toolbar: "#tb",
        striped: true,
        onDblClickRow: function (index, row) {
            //加载数据至表单
            $('#fm').form('load', row);
            $('#dlg').dialog({
                title: '修改指数',
                iconCls: 'update',
                closed: false,
                top: $(window).height() / 4,
                width: 500,
                height: 350,
                onClose: function () {
                    $('#name').textbox('setValue', '');
                    $('#code').textbox('setValue', '');
                }
            });

            url = "/index/save?id=" + row.id;
        }
    })
});

/**
 * 保存指数信息
 */
function saveData() {
    $('#fm').form('submit', {
        url: url,
        onSubmit: function () {
            if ($('#name').val() === null || $('#name').val() === '') {
                $.messager.alert({
                    title: '系统提示',
                    msg: '请输入指数名称',
                    icon: 'error',
                    top: $(window).height() / 4
                });

                return false;
            }
            if ($('#code').val() === null || $('#code').val()
                === '') {
                $.messager.alert({
                    title: '系统提示',
                    msg: '请输入指数code',
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
function deleteIndex() {
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
                    url: '/index/delete',
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