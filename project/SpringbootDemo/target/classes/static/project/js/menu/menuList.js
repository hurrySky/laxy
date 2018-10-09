
//var oTable;
$(document).ready(function() {
	queryMenuList();
});

function queryMenuList() {

	$("#menuList").dataTable({
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
		
		//"lengthMenu" : [ 2, 5, 10 ],
	//	 responsive: true,
	//	"ordering": false,
		"bAutoWidth" : true, // 自动适应宽度
		"bFilter" : false, // 查询
		"bSort" : false, // 排序
		"ordering" : true,
		"info" : true, // 页脚信息
		"bLengthChange" : false, // 改变每页显示数据数量
		"serverSide": true,
		"bProcessing" : true, // 当datatable获取数据时候是否显示正在处理提示信息。
		"bPaginate" : false, // 显示分页器
		//"iDisplayLength " : 2, // 一页显示条数
		"paging": false,
		ajax : {
			"url" : '/system/menu/list',
			"data": function (d) {
				 return $.extend({}, d, {
				        "menuIds": $("#menuIds").val()
				        });
				
			},
//			"data" : {
//				//"menuIds" : decodeURIComponent(menuIds)
//				"menuIds" : $("#menuids").val()
//			},
//			dataType:"json",
			type:'Get'
		},
		"columns" : [ {
			"data" : 'menuId',
			"visible": false
		}, {
			"data" : 'menuName',
			"orderable": false,
			"render": function(data, type, row) {
				var lengthBlank = ""; 
				for (var int = 1; int < row.menuLevel; int++) {
					lengthBlank = lengthBlank + "&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;";
				}
                return '<a href="javascript:void(0)">'+lengthBlank+'<span onclick="getSonMenu('+row.menuId + ')"><i class="' + row.icon+'"></i>&nbsp;' + row.menuName + '</span></a>';
			}
		}, {
			"data" : 'parentId',
			"visible": false
		}, {
			"data" : 'orderNum',
			"orderable": false			
		},{
			"data" : 'url',
			"visible": false
		}, {
			"data" : 'menuType',
			"visible": true,
			"orderable": false,
			"render": function(data, type, row) {
				if (data == 'M') {
					return '<span class="label label-success">目录</span>';
				}else {
					return '<span class="label label-primary">菜单</span>';
				}
			}
		}, {
			"data" : 'visible',
			"visible": true,
			"orderable": false,
			"render": function(data, type, row) {
				if (data == 0) {
					return '<span class="badge badge-danger">不显示</span>';
				}else {
					return '<span class="badge badge-primary">显&nbsp;&nbsp;&nbsp;&nbsp;示</span>';
				}
			}
		},{
			"data" : 'perms',
			"visible": false,
			"orderable": false
		},{
			"data" : 'icon',
			"visible": true,
			"orderable": false,
			"render": function(data, type, row) {
                return '<i class="' + row.icon+'"></i>';
			}
		},{
			"data" : 'createBy',
			"orderable": false
		},{
			"data" : 'createTime',
			"orderable": false
		},{
			"data" : 'updateBy',
			"visible": false,
			"orderable": false
		},{
			"data" : 'updateTime',
			"orderable": false,
			"visible": false
		},{
			"data" : 'remark',
			"orderable": false,
			"visible": false
		},{
			"data" : 'menuLevel',
			"visible": true,
			"orderable": false,
			"render": function(data, type, row) {
                if (data == 1) {
					return "一级";
				} else if (data == 2) {
					return "二级";
				} else if (data == 3) {
					return "三级";
				}else if (data == 4) {
					return "四级";
				}
			}
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
	                            var retHtml = '<a class="btn btn-success btn-xs" href="#" onclick="addMenu('+ row.menuId +')"><i class="fa fa-edit"></i>新增</a>&nbsp;'
	                            + '<a class="btn btn-success btn-xs" href="#" onclick="editMenu('+ row.menuId +')"><i class="fa fa-edit"></i>编辑</a>&nbsp;'
	            	        	+'<a class="btn btn-danger btn-xs" href="#" onclick="deleteMenu('+ row.menuId +')"><i class="fa fa-remove"></i>删除</a>&nbsp;';
	                            return retHtml;
	                        }
	                    },
	                   ]
	});
}

// 添加用户
function addMenu(menuId) {
	var url = '/system/menu/add/'+menuId;
	$.openPopup("添加菜单", 900, 450, url, null);
}

//修改用户
function editMenu(menuId) {
	var url = '/system/menu/edit/'+menuId;
	$.openPopup("编辑菜单", 900, 450, url, null);
}

//修改用户
function deleteMenu(menuId) {
	layer.confirm('您确定要删除该菜单吗？', {
		btn: ['删了吧','算了'] //按钮
	}, function(){
	 	layer.msg('正在加载中。。。', {icon: 1});
	 	var url = '/system/menu/delete';
		$.ajax({
			type : "POST",
			url : url,
			async:true,
			data : {
				"menuId" : menuId
			},
			success : function(data) {
				if (data == true) {
					layer.msg('好的，删掉了', {icon: 1});
					var userTable = $('#menuList').dataTable();
					userTable.fnDraw();
				}else {
					layer.msg('删除失败了', {icon: 1});
				}
			}
		});
	})
}

var menuIds = new Array();
function getSonMenu(menuId) {

	addOrRemoveMenuid(menuId);
	var result = "";
	if (menuIds.length > 0) {
		for (var i = 0; i < menuIds.length; i++) {
			i == 0 ? (result = result + menuIds[i]) : (result = result + ',' + menuIds[i]);
		}
		//alert(result);
	}
	$("#menuIds").val(result);

	var oTable = $("#menuList").dataTable();
	oTable.api().ajax.reload();
	oTable.fnDraw();
}

function addOrRemoveMenuid(menuId) {
	var index = $.inArray(menuId, menuIds);
//	alert(index);
	if (index == -1) {
		menuIds.push(menuId);
	} else {
		menuIds.splice(index,1);
	}
}
