$().ready(function() {
	$("#roleEdit").validate({
	      rules: {
	      roleName: "required",
		  remark: "required"
	    },
	    messages: {
	    	roleName: "请输入您的角色名称",
		    remark: "请输入角色描述信息"
	     },
	     remote: {
			    url: "/system/role/save",   //后台处理程序
			    type: "post",               //数据发送方式
			    dataType: "json",           //接受数据格式   
			    data: {                     //要传递的数据
			       
			    }
			},
	     submitHandler: function(form) {
	        editUser();
	     }
	  })
});

function editUser() {
	var code = $("input[name='code']").val();
	var roleName = $("input[name='roleName']").val();
	var status = $("input[name='status']").is(':checked') == true ? 0 : 1;
	var remark = $("textarea[name='remark']").val();
	$.ajax({
		cache : true,
		type : "POST",
		url : "/system/role/save",
		data : {
			"code": code,
			"roleName": roleName,
			"status": status,
			"remark": remark
		},
		async : false,
		error : function(request) {
			// $.modalAlert("系统错误", modal_status.FAIL);
		},
		success : function(data) {
			if (data == 1) {
				parent.layer.msg("修改成功,正在刷新数据请稍后……",{icon:1,time: 500,shade: [0.1,'#fff']},function(){
					//$.parentReload();
					parent.location.reload();
				});
			} else {
//				$.modalAlert(data.msg, modal_status.FAIL);
			}

		}
	});
}