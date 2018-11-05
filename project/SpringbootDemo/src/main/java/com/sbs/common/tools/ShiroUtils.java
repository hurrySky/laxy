package com.sbs.common.tools;

import org.apache.shiro.SecurityUtils;
import org.apache.shiro.subject.Subject;

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
	 * 登出
	 */
	public void logout() {
		getSubjct().logout();
	}
	
}
