
function login() {
	
	var username = $("input[name='username']").val();
    var password = $("input[name='password']").val();
//    var validateCode = $("input[name='validateCode']").val();
//    var rememberMe = $("input[name='rememberme']").is(':checked');
 
    $.ajax({
        type: "POST",
        url: "login",
        data: {
            "username": username,
            "password": password,
        },
        success: function(data) {
            if (data == "success") {
//                parent.location.href = ctx + 'index';
            	window.location.href = "index";
            } else {
				alert(data);
			}
        }
    });
}

function validateRule() {
    var icon = "<i class='fa fa-times-circle'></i> ";
    $("#signupForm").validate({
        rules: {
            username: {
                required: true
            },
            password: {
                required: true
            }
        },
        messages: {
            username: {
                required: icon + "请输入您的用户名",
            },
            password: {
                required: icon + "请输入您的密码",
            }
        }
    })
}
