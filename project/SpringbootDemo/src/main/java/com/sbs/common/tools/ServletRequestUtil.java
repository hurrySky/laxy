package com.sbs.common.tools;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import org.springframework.web.context.request.RequestAttributes;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

public class ServletRequestUtil {

	/**
	 * 获得请求参数
	 */
	
	public static Object getRequestParam(String name) {
		HttpServletRequest httpServletRequest = ServletRequestUtil.getRequestAttributes().getRequest();
		Object object=	httpServletRequest.getParameter(name);
		return object;
	}
	
	public static Object getRequestArrParam(String names) {
		HttpServletRequest httpServletRequest = ServletRequestUtil.getRequestAttributes().getRequest();
		Object object =	httpServletRequest.getParameterValues(names);
		return object;
	}
	public static ServletRequestAttributes getRequestAttributes() {
        RequestAttributes attributes = RequestContextHolder.getRequestAttributes();
        return (ServletRequestAttributes) attributes;
    }
	
	/**
	 * 获得当前request
	 */
	public static HttpServletRequest getCurrentRequest() {
		return ServletRequestUtil.getRequestAttributes().getRequest();
	}
	/**
	 * 获得当前响应
	 */
	public static HttpServletResponse getCurrentResponse() {
		HttpServletResponse httpServletResponse = ServletRequestUtil.getRequestAttributes().getResponse();
		return httpServletResponse;
	}
	/**
	 * 获得当前session
	 */
	public static HttpSession getCurrentSession() {
		HttpSession httpSession = ServletRequestUtil.getCurrentRequest().getSession();
		return httpSession;
	}
}
