package com.sbs.common.exception.user;

import com.sbs.common.exception.BaseException;

public class UserPasswordRetryLimitExceedException extends BaseException{

	private static final long serialVersionUID = 1L;

	public UserPasswordRetryLimitExceedException() {
		 super("password.retry.limit", "您登陆失败次数太多,请稍后再试！");
	}
}
