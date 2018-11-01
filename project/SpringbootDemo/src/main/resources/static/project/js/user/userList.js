
$(document).ready(function() {
	queryUserList();
	initOperation();
});

/**
 * 表格元素血染完成后，调用调整 表格内容样式的方法
 */
$('#userList').on( 'draw.dt', function () {
	// 初始化表格：若需要表格内容居中，只需要给 表头 th 添加类 content_center
	$.initTdStyle("userList");
});

function queryUserList() {
	
	$("#userList").dataTable({
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
			"info" : "显示 _START_ 到 _END_ 条，共 _TOTAL_ 条",
			"sInfoFiltered" : "数据表中共为 _MAX_ 条",
			"sSearch" : "搜索",
			"loadingRecords" : "Loading...",
			"paginate" : {
				"first" : "首页",
				"last" : "尾页",
				"next" : "下一页",
				"previous" : "上一页"
			}
		},
		"orderClasses":true,
		"searching": true,	// 查询
		//"bFilter" : true, // 查询 和 searching作用相同
		"lengthMenu" : [5, 10, 20 ],
		"bAutoWidth" : true, // 自动适应宽度
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
			"url" : '/system/user/list',
			"data" : {
				
			},
			type:'Get',
			
//			dataSrc : function(json) {
//				var retData = {
//						"draw": 1,
//						"recordsTotal": json.total,
//						"recordsFiltered": json.pages * json.size,
//						"data" :json.list
//				}
//				console.log(json);
//				console.log(json.list);
//				return "";
//			}
		},
		"columns" : [ {
			"data" : 'userId',
			"visible": false,
			"searchable": false
		}, {
			"data" : 'code',
			"searchable": true
		}, {
			"data" : 'loginName',
			"searchable": false
		}, {
			"data" : 'userName',
			"searchable": true
		}, {
			"data" : 'email'
		}, {
			"data" : 'phonenumber'
		}, {
			"data" : 'sex',
			"searchable": false,
			"render": function(data, type, row) {
                if (data == 0) {
					return "男"
				}else {
					return "女"
				}
			}
		}, {
			"data" : 'headImgUrl',
			"visible": false,
			"searchable": false
		}, {
			"data" : 'password',
			"visible": false,
			"searchable": false
		}, {
			"data" : 'salt',
			"visible": false,
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
			"data" : 'loginIp',
			"visible": false,
			"searchable": false
		},{
			"data" : 'loginDate',
			"visible": false,
			"searchable": false
		},{
			"data" : 'createBy'
		},{
			"data" : 'createTime',
			"render": function(data, type, row) {
				return row.createTime.split(":")[0] + ":" + row.createTime.split(":")[1];
			}
		},{
			"data" : 'updateBy',
			"visible": false,
			"searchable": false
		},{
			"data" : 'updateTime',
			"visible": false
		},{
			"data" : 'remark',
			"visible": false,
			"searchable": false
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
	                            
	                            var retHtml = '<a class="btn btn-success btn-xs" href="#" onclick="editUser('+ row.userId +')"><i class="fa fa-edit"></i>编辑</a>&nbsp;'
	            	        	+'<a class="btn btn-danger btn-xs" href="#" onclick="deleteUser('+ row.userId +')"><i class="fa fa-remove"></i>删除</a>&nbsp;'
	            	        	+'<a class="btn btn-info btn-xs" href="#" onclick="'+ row.userId +'"><i class="fa fa-key"></i>重置</a>';
	                            return retHtml;
	                        }
	                    },
	                   ]
	});
}

function initOperation() {
	$('#userList').on( 'init.dt', function () {
		$(".operation").append('<div class="col-lg-9 col-md-9 col-sm-9">' +
									'<button style="float:right;" type="button" class="btn btn-outline btn-primary" onclick="addUser()">新&nbsp;&nbsp;增</button>' +
							   '</div>');
		
    }).dataTable();
}

// 添加用户
function addUser() {
	var url = '/system/user/add';
	$.openPopup("添加用户", 900, 450, url, null);
}

//修改用户
function editUser(userId) {
	var url = '/system/user/edit/';
	$.openPopup("编辑用户", 900, 450, url, userId);
}

//删除用户
function deleteUser(userId) {
	layer.confirm('您确定要删除该用户吗？', {
		btn: ['删了吧','算了'] //按钮
	}, function(){
	 	layer.msg('正在加载中。。。', {icon: 1});
	 	var url = '/system/user/delete';
		$.ajax({
			type : "POST",
			url : url,
			async:true,
			data : {
				"userId" : userId
			},
			success : function(data) {
				if (data == true) {
					layer.msg('好的，删掉了', {icon: 1});
					var userTable = $('#userList').dataTable();
					userTable.fnDraw();
				}else {
					layer.msg('删除失败了', {icon: 1});
				}
			}
		});
	})
}

//
// column: [
// {
// "sName": "userId",
// "mDataProp": "userId",
// "bSearchable": true, //检索可用
// "bStorable": true
// },
// {
// "sName": "code",
// "mDataProp": "code",
// "bSearchable": false,
// "bStorable": false
// },
// {
// "mDataProp": "loginName",
// "sName": "loginName"
// },
// {
// "sName": "userName",
// "mDataProp": "userName"
// },
// {
// "sName": "email",
// "mDataProp": "email"
// },
// {
// "sName": "phonenumber",
// "mDataProp": "phonenumber"
// },
// {
// "sName": "sex",
// "mDataProp": "sex"
// },
// {
// "sName": "headImgUrl",
// "mDataProp": "headImgUrl"
// },
// {
// "sName": "password",
// "mDataProp": "password"
// },
// {
// "sName": "salt",
// "mDataProp": "salt"
// }
// ]
