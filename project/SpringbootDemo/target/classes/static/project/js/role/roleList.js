
$(document).ready(function() {
	queryRoleList();
});

function queryRoleList() {
	
	$("#roleList").dataTable({
		"language" : { // 语言设置
			"sProcessing" : "正在加载中......",
			"sLengthMenu" : "每页显示 _MENU_ 条记录",
			"sZeroRecords" : "对不起，查询不到相关数据！",
			"sEmptyTable" : "表中无数据存在！",
			"infoEmpty" : "显示" + 0 + "到 0 条，共 " +0 + "条记录",
			"info" : "显示 _START_ 到 _END_ 条，共 _TOTAL_ 条记录",
			"sInfoFiltered" : "数据表中共为 _MAX_ 条记录",
			"sSearch" : "搜索",
			"loadingRecords" : "Loading...",
			"paginate" : {
				"first" : "首页",
				"last" : "尾页",
				"next" : "下一页",
				"previous" : "上一页"
			}
		},
		
		"lengthMenu" : [5, 10, 20 ],
		"bAutoWidth" : true, // 自动适应宽度
		"bFilter" : true, // 查询
		"bSort" : true, // 排序
		"ordering" : true,
		"info" : true, // 页脚信息
		"bLengthChange" : true, // 改变每页显示数据数量
		//"bServerSide" : true, // 服务器数据
		"serverSide": true,
		// "sAjaxSource": "/system/user/list",
		"bProcessing" : true, // 当datatable获取数据时候是否显示正在处理提示信息。
		"bPaginate" : true, // 显示分页器
		"iDisplayLength " : 5, // 一页显示条数
		"paging": true,
		ajax : {
			"url" : '/system/role/list',
			"data" : {
				
			},
			type:'Get',
		},
		"columns" : [ {
			"data" : 'roleId',
			"visible": false
		}, {
			"data" : 'code'
		}, {
			"data" : 'roleName'
		}, {
			"data" : 'roleKey'
		}, {
			"data" : 'orderNum'
		},{
			"data" : 'status',
			"render": function(data, type, row) {
                if (data == 0) {
					return '<span class="badge badge-danger">停用</span>';
				}else {
					return '<span class="badge badge-primary">启用</span>';
				}
            },
		},{
			"data" : 'createBy'
		},{
			"data" : 'createTime'
		},{
			"data" : 'updateBy',
			"visible": false
		},{
			"data" : 'updateTime',
			"visible": false
		},{
			"data" : 'remark',
			"visible": false
		},{
			"data" : 'caozuo'
		}
		],
		"aoColumnDefs":[
	                    {//倒数第一列
	                        "targets":-1,
	                        "bSortable": false,
	                        render: function(data, type, row) {
	                            var html ='<button class="btn btn-xs jfedit btn-danger" value="'+row.tcId+'">编辑</button>&nbsp;&nbsp;&nbsp;&nbsp;'
	                            +'<button class="btn btn-xs btn-danger jfdelete" value="'+row.tcId+'">删除</button>';
	                            
	                            var retHtml = '<a class="btn btn-success btn-xs" href="#" onclick="editUser('+ row.userId +')"><i class="fa fa-edit"></i>编辑</a>'
	            	        	+'<a class="btn btn-danger btn-xs" href="#" onclick="deleteUser('+ row.userId +')"><i class="fa fa-remove"></i>删除</a> '
	            	        	+'<a class="btn btn-info btn-xs" href="#" onclick="'+ row.userId +'"><i class="fa fa-key"></i>重置</a>';
	                            return retHtml;
	                        }
	                    },
	                   ]
	});
}

// 添加用户
function addRole(roleId) {
	alert(userId);
}

//修改用户
function editRole(roleId) {
	alert(userId);
}

//修改用户
function deleteRole(roleId) {
	alert(userId);
	//layer_showAuto("新增用户");
	layer.open({
        type: 2,
        area: [100 + 'px', 100 + 'px'],
        fix: false,
        //不固定
        maxmin: true,
        shade: 0.4,
        title: "fff",
        content: url
    });
}
