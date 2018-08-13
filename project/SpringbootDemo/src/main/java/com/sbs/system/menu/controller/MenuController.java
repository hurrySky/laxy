package com.sbs.system.menu.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.sbs.base.web.controller.BaseController;
import com.sbs.base.web.pagination.TableDataInfo;
import com.sbs.common.tools.ServletRequestUtil;
import com.sbs.common.tools.StringUtil;
import com.sbs.system.menu.entity.Menu;
import com.sbs.system.menu.service.MenuService;

@Controller
public class MenuController extends BaseController{

	@Autowired
	private MenuService menuService;
	
	@GetMapping("/system/menu")
	public String toMenu() {
		
		return "menu/menuList";
	}
	
	@GetMapping("/system/menu/list")
	@ResponseBody
	public TableDataInfo getUserList() {
		buildPagination();
		// 得到要展示子菜单的菜单 menuId 字符串拼接集
		String strArr = (String) ServletRequestUtil.getRequestParam("menuIds");
		// 通过, 分割获得menuId 数组
		Integer[] integerArr = StringUtil.valueOfIntegerArrey(strArr, ",");
		
		List<Menu> list = menuService.findSonMenuListByMenuId(integerArr);
		return doPagination(list);
	}
}
