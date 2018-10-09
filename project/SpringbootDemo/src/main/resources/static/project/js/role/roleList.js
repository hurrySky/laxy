
$(document).ready(function() {
	queryRoleList();
	initOperation();
});

function queryRoleList() {
	
	$("#roleList").dataTable({
		"dom": 
			'<"operation"' +
				'<"search col-lg-3 col-md-3 col-sm-3"f>' +
			'>'+
			'<t>' +
			'<"table_foot"' +
				'<"col-lg-5 col-md-5 col-sm-5"' +
					'<"col-lg-6 col-md-6 col-sm-6"i>' +
					'<"col-lg-5 col-md-5 col-sm-5"l>' +
				'>' +
				'<"col-lg-7 col-md-7 col-sm-7"p>' +
			'>',
		"language" : { // 语言设置
			"sProcessing" : "正在加载中......",
			"sLengthMenu" : "显示 _MENU_ 条",
			"sZeroRecords" : "对不起，查询不到相关数据！",
			"sEmptyTable" : "表中无数据存在！",
			"infoEmpty" : "显示" + 0 + "到 0 条，共 " +0 + "条",
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
			"visible": false,
			"searchable": false
		}, {
			"data" : 'code',
			"searchable": true
		}, {
			"data" : 'roleName'
		}, {
			"data" : 'roleKey',
			"searchable": false
		}, {
			"data" : 'orderNum',
			"searchable": false
		},{
			"data" : 'status',
			"searchable": false,
			"render": function(data, type, row) {
                if (data == 0) {
                	return '<span class="badge badge-primary">启用</span>';
				}else {
					return '<span class="badge badge-danger">停用</span>';
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
			"data" : 'caozuo',
			"searchable": false
		}
		],
		"aoColumnDefs":[
	                    {//倒数第一列
	                        "targets":-1,
	                        "bSortable": false,
	                        render: function(data, type, row) {
	                            var html ='<button class="btn btn-xs jfedit btn-danger" value="'+row.tcId+'">编辑</button>&nbsp;&nbsp;&nbsp;&nbsp;'
	                            +'<button class="btn btn-xs btn-danger jfdelete" value="'+row.tcId+'">删除</button>';
	                            
	                            var retHtml = '<a class="btn btn-success btn-xs" href="#" onclick="editRole('+ row.roleId +')"><i class="fa fa-edit"></i>编辑</a>&nbsp;'
	            	        	+'<a class="btn btn-danger btn-xs" href="#" onclick="deleteRole('+ row.roleId +')"><i class="fa fa-remove"></i>删除</a> ';
	                            return retHtml;
	                        }
	                    },
	                   ]
	});
}

function initOperation() {
	$('#roleList').on( 'init.dt', function () {
		$(".operation").append('<div class="col-lg-9 col-md-9 col-sm-9">' +
									'<button style="float:right;" type="button" class="btn btn-info" onclick="addRole()">添&nbsp;&nbsp;加</button>' +
							   '</div>');
		
    }).dataTable();
}

// 添加用户
function addRole(roleId) {
	var url = '/system/role/add';
	$.openPopup("添加角色", 900, 450, url, null);
}

//修改用户
function editRole(roleId) {
	var url = '/system/role/edit/';
	$.openPopup("编辑用户", 900, 450, url, roleId);
}

//修改用户
function deleteRole(roleId) {
	layer.confirm('您确定要删除该角色吗？', {
		btn: ['删了吧','算了'] //按钮
	}, function(){
	 	layer.msg('正在加载中。。。', {icon: 1});
	 	var url = '/system/role/delete';
		$.ajax({
			type : "POST",
			url : url,
			async:true,
			data : {
				"roleId" : roleId
			},
			success : function(data) {
				if (data == true) {
					layer.msg('好的，删掉了', {icon: 1});
					var userTable = $('#roleList').dataTable();
					userTable.fnDraw();
				}else {
					layer.msg('删除失败了', {icon: 1});
				}
			}
		});
	})
}
