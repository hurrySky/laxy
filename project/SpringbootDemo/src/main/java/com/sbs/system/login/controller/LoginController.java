package com.sbs.system.login.controller;

import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.AuthenticationException;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.subject.Subject;
import org.apache.shiro.web.servlet.ShiroHttpServletRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.sbs.common.tools.ServletRequestUtil;
import com.sbs.system.menu.entity.Menu;
import com.sbs.system.menu.service.MenuService;
import com.sbs.system.user.entity.User;

@Controller
public class LoginController {

	@Autowired
	private MenuService menuService;
	
	@GetMapping("/login")
	public String  login() {
		return "login";
	}
	
	@PostMapping("/login")
    @ResponseBody
    public String Login(String username, String password) {
		
//		ShiroHttpServletRequest httpServletRequest = (ShiroHttpServletRequest) ServletRequestUtil.getRequestAttributes().getRequest();
//		Object object=	httpServletRequest.getParameter("username");
		
        UsernamePasswordToken token = new UsernamePasswordToken(username, password);
        Subject subject = SecurityUtils.getSubject();
        try {
            subject.login(token);
            return "success";
        }
        catch (AuthenticationException e) {
           String msg = "登录失败，请检查账号或密码！";
           if (e.getMessage() != null) {
        	   msg = e.getMessage();
           }
           return msg;
		}
    }
	
	/**
	 * 登录成功后跳转主页
	 * @return
	 */
	@GetMapping("/index")
	public String  toindex(Model model) {
		User user = (User) SecurityUtils.getSubject().getPrincipal();
		model.addAttribute("user", user);
		List<Menu> list = menuService.findVisibleMenuList();
		model.addAttribute("menus", list);
		System.out.println(JSONArray.toJSONString(list));
		return "index";
	}
}
