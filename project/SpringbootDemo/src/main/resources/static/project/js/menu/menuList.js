
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
		 responsive: true,
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
			 "cellsAlign" : "center",
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
			"data" : 'orderNum'
		},{
			"data" : 'url',
			"visible": false
		}, {
			"data" : 'menuType',
			"visible": true,
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
			"render": function(data, type, row) {
				if (data == 0) {
					return '<span class="badge badge-danger">不显示</span>';
				}else {
					return '<span class="badge badge-primary">显&nbsp;&nbsp;&nbsp;&nbsp;示</span>';
				}
			}
		},{
			"data" : 'perms',
			"visible": false
		},{
			"data" : 'icon',
			"visible": true,
			"render": function(data, type, row) {
                return '<i class="' + row.icon+'"></i>';
			}
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
			"data" : 'menuLevel',
			"visible": true,
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
		}
		],
	});
}

// 添加用户
function addMenu(menuId) {
	alert(userId);
}

//修改用户
function editMenu(menuId) {
	alert(userId);
}

//修改用户
function deleteMenu(menuId) {
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
