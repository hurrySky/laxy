package com.sbs.common.exception.user;

import com.sbs.common.exception.BaseException;

public class UserPasswordNotMatchException extends BaseException{
	private static final long serialVersionUID = 1L;
	
	public UserPasswordNotMatchException() {
        super("password.not.match", "您的密码不正确，请输入正确的密码！");
    }
}
