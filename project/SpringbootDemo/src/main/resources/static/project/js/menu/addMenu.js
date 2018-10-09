
$(document).ready(function(){
	// 初始化icheck
	$(".i-checks").iCheck({
		checkboxClass:"icheckbox_square-green",
		radioClass:"iradio_square-green",
	});
	// 图标控件获得焦点后显示图标列表
	$("input[name='icon']").focus(function() {
        $(".icon-drop").show();
    });
	
	$("#menuAdd").click(function(event) {
	    var obj = event.srcElement || event.target;
	    if (!$(obj).is("input[name='icon']")) {
	    	$(".icon-drop").hide();
	    }
	});
	
	$(".icon-drop").find(".ico-list i").on("click", function() {
		$('#icon').val($(this).attr('class'));
    });
	
	$('input').on('ifChecked', function(event){
		var menuType = $(event.target).val();
		if (menuType == "M") {
            $("#url").parent().parent().hide();
            $("#perms").parent().parent().hide();
            $("#icon").parent().parent().show();
        } else if (menuType == "C") {
        	$("#url").parent().parent().show();
            $("#perms").parent().parent().show();
            $("#icon").parent().parent().hide();
        } else if (menuType == "F") {
        	$("#url").parent().parent().hide();
        	$("#perms").parent().parent().show();
            $("#icon").parent().parent().hide();
        }
	});
	
	// 表单校验
	$("#menuAdd").validate({
	      rules:{
	      menuId:"required",
	      menuName: "required",
	      remark: "required"
	    },
	    messages: {
	      menuId: "系统异常，父菜单menu_id出错了",
	      menuName: "请输入您的菜单名称",
	      remark: "请输入描述信息"
	     },
	     remote: {
			    url: "/system/user/save",   //后台处理程序
			    type: "post",               //数据发送方式
			    dataType: "json",           //接受数据格式   
			    data: {                     //要传递的数据
			       
			    }
			},
	     submitHandler: function(form) {
	    	 addMenu();
	     }
	  })
});
    
function addMenu() {
	var parentId = $("input[name='parentId']").val();
	var menuName = $("input[name='menuName']").val();
	var menuType;
	$("div.menuType").find("label").each(function(){
		if ($(this).find("div").hasClass("checked")) {
			menuType = $(this).find("div").find("input").val();
		}
	});
	var menuLevel;
	var menuLevelVal = Number($("input[name= 'menuLevel']").val());
	if (menuLevelVal != NaN && menuLevelVal != undefined) {
		menuLevel = menuLevelVal + 1;
	}
	
	var icon = $("input[name='icon']").val();
	var url = $("input[name='url']").val();
	var perms = $("input[name='perms']").val();
	var visible;
	$("div.visible").find("label").each(function(){
		if ($(this).find("div").hasClass("checked")) {
			visible = $(this).find("div").find("input").val();
		}
	});
	var remark = $("textarea[name='remark']").val();
	if (menuType == 'M') {
		perms = '';
		url = '';
	}else if (menuType == 'C') {
		// 都需要，所以什么都不做
	} else if(menuType == 'F'){
		url = '';
	}
	$.ajax({
		cache : true,
		type : "POST",
		url : "/system/menu/save",
		data : {
			"parentId": parentId,
			"menuName": menuName,
			"menuType": menuType,
			"menuLevel": menuLevel,
			"icon": icon,
			"url": url,
			"perms": perms,
			"visible": visible,
			"remark": remark
		},
		async : false,
		error : function(request) {
			$.modalAlert("系统错误", modal_status.FAIL);
		},
		success : function(data) {
			if (data == 1) {
				parent.layer.msg("添加成功,正在刷新数据请稍后……",{icon:1,time: 500,shade: [0.1,'#fff']},function(){
					//$.parentReload();
					parent.location.reload();
				//	alert("cccccc");
				});
			} else {
//				$.modalAlert(data.msg, modal_status.FAIL);
			}

		}
	});
}

function selectMenu() {
	//alert($("#menuTree").val());
	var url = "/system/menu/selectMenuTree";
	$.openPopup("选择菜单", 500, 340, url, null);
	
}
