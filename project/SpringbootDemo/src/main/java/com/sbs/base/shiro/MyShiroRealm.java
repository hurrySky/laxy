package com.sbs.base.shiro;

import javax.security.auth.login.AccountNotFoundException;

import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.AuthenticationException;
import org.apache.shiro.authc.AuthenticationInfo;
import org.apache.shiro.authc.AuthenticationToken;
import org.apache.shiro.authc.ExcessiveAttemptsException;
import org.apache.shiro.authc.IncorrectCredentialsException;
import org.apache.shiro.authc.SimpleAuthenticationInfo;
import org.apache.shiro.authc.UnknownAccountException;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.authz.AuthorizationInfo;
import org.apache.shiro.realm.AuthenticatingRealm;
import org.apache.shiro.realm.AuthorizingRealm;
import org.apache.shiro.subject.PrincipalCollection;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import com.sbs.common.exception.user.UserNotExistsException;
import com.sbs.common.exception.user.UserPasswordNotMatchException;
import com.sbs.common.exception.user.UserPasswordRetryLimitExceedException;
import com.sbs.system.login.service.LoginService;
import com.sbs.system.menu.service.MenuService;
import com.sbs.system.role.service.RoleService;
import com.sbs.system.user.entity.User;
import com.sbs.system.user.service.UserService;

public class MyShiroRealm extends AuthorizingRealm{

	@Autowired
	private UserService userService;
	
	@Autowired
	private LoginService loginService;
	
	@Autowired
    private MenuService menuService;

    @Autowired
    private RoleService roleService;
	/**
	 * 授权
	 */
	@Override
	protected AuthorizationInfo doGetAuthorizationInfo(
			PrincipalCollection principals) {
		System.out.println("hhhh");
		System.out.println("hhhh");
		return null;
	}
	
	/**
	 * 认证
	 */
	@Override
	protected AuthenticationInfo doGetAuthenticationInfo(
			AuthenticationToken token) throws AuthenticationException {
		UsernamePasswordToken upDoken = (UsernamePasswordToken) token;
		String loginName = upDoken.getUsername();
		char[] passWord = upDoken.getPassword();
		String passWord_Str = String.valueOf(passWord);
		User user = null;
		try {
			user = loginService.login(loginName, passWord_Str);
		} catch (UserNotExistsException e) {
			throw new UnknownAccountException(e.getExceptionMessage(), null);
		} catch (UserPasswordNotMatchException e) {
			throw new IncorrectCredentialsException(e.getExceptionMessage(), null);
		} catch (UserPasswordRetryLimitExceedException e) {
			throw new ExcessiveAttemptsException(e.getExceptionMessage(), null);
		}
		
		SimpleAuthenticationInfo info = null;
		if (user != null) {
			info = new SimpleAuthenticationInfo(user,passWord, getName());
		}
		return info;
	}
}
