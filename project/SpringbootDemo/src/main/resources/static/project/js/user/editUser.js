$().ready(function() {
	$("#userEdit").validate({
	      rules: {
	      loginName: "required",
	      userName: "required",
	      email: {
	        required: true,
	        email: true
	      }
	    },
	    messages: {
	      loginName: "请输入您的登录名称",
	      userName: "请输入您的用户名称",
	      email: "请输入一个正确的邮箱"
	    
	     },
	     remote: {
			    url: "/system/user/save",   //后台处理程序
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
	var userId = $("input[name='userId']").val();
	var code = $("input[name='code']").val();
	var loginName = $("input[name='loginName']").val();
	var userName = $("input[name='userName']").val();
	var email = $("input[name='email']").val();
	var password = $("input[name='password']").val();
	var phonenumber = $("input[name='phonenumber']").val();
	var sex = $("#sex option:selected").val();
	var status = $("input[name='status']").is(':checked') == true ? 0 : 1;
	var remark = $("textarea[name='remark']").val();
	$.ajax({
		cache : true,
		type : "POST",
		url : "/system/user/save",
		data : {
			"userId": userId,
			"code": code,
			"loginName": loginName,
			"password": password,
			"userName": userName,
			"email": email,
			"phonenumber": phonenumber,
			"sex": sex,
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