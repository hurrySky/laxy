package com.sbs.common.tools;

import org.apache.shiro.SecurityUtils;
import org.apache.shiro.subject.Subject;

import com.sbs.system.user.entity.User;

public class ShiroUtils {

	public static void main(String[] args) {

	}
	
	/**
	 * 获得主体
	 * @return
	 */
	public static Subject getSubjct() {
		return SecurityUtils.getSubject();
	}
	
	/**
	 * 获得当前用户
	 * @return
	 */
	public static User getUser() {
		User user = (User)ShiroUtils.getSubjct().getPrincipal();
		return user;
	}
	/**
	 * 登出
	 */
	public void logout() {
		getSubjct().logout();
	}
	
}
