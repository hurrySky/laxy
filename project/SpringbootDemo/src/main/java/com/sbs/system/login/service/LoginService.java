package com.sbs.system.login.service;

import javax.security.auth.login.AccountNotFoundException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.sbs.common.exception.user.UserNotExistsException;
import com.sbs.common.exception.user.UserPasswordNotMatchException;
import com.sbs.common.exception.user.UserPasswordRetryLimitExceedException;
import com.sbs.system.user.entity.User;
import com.sbs.system.user.service.UserService;
@Service("loginService")
public class LoginService {

	@Autowired
	private UserService userService;
	
	@Autowired
	private PasswordService passwordService;
	/**
     * 登录
     */
	public User login(String loginName, String passWord) {
		User user = userService.findUserByLoginName(loginName);
		if (user == null) {
			throw new UserNotExistsException();
		}
//		if (!user.getPassword().equals(passWord)) {
//			throw new UserPasswordNotMatchException();
//		}
		boolean flag = false;
		try {
			flag = passwordService.checkUser(user, passWord);
		} catch (UserPasswordRetryLimitExceedException e) {
			throw new UserPasswordRetryLimitExceedException();
		}
		return user;
	}
}
