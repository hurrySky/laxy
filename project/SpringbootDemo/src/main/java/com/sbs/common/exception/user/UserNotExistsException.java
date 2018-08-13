package com.sbs.common.exception.user;

import com.sbs.common.exception.BaseException;

public class UserNotExistsException extends BaseException{

	private static final long serialVersionUID = 1L;
	
	public UserNotExistsException() {
        super("user.not.exists", "您的账号有误，请确认后重试！");
    }
}
