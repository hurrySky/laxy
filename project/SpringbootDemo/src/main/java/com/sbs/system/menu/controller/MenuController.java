package com.sbs.system.menu.controller;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.alibaba.druid.support.json.JSONUtils;
import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.sbs.base.web.controller.BaseController;
import com.sbs.base.web.pagination.TableDataInfo;
import com.sbs.common.tools.CodeCreateUtil;
import com.sbs.common.tools.ServletRequestUtil;
import com.sbs.common.tools.StringUtil;
import com.sbs.system.menu.entity.Menu;
import com.sbs.system.menu.service.MenuService;
import com.sbs.system.user.entity.User;

@Controller
@RequestMapping("/system/menu")
public class MenuController extends BaseController{

	@Autowired
	private MenuService menuService;
	
	@GetMapping()
	public String toMenu() {
		
		return "menu/menuList";
	}
	
	@GetMapping("/list")
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
	
	/**
	 * 添加菜单
	 * @param model
	 * @return 添加页面视图
	 */
//	@GetMapping("/add/{menuId}")
	@RequestMapping(value = {"/add/{menuId}", "/add"}, method = RequestMethod.GET)
	public String toMenuAdd(@PathVariable(required = false) Integer menuId,Model model) {
		Menu menu = new Menu();
		// 若menuId 为null，则在根目录下添加菜单
		// 若menuId 不为null，则在menuid菜单下添加子菜单
		if (StringUtil.isNotNull(menuId)) {
			menu = menuService.findMenuByMenuId(menuId);
		} else {
			menu.setMenuName("根目录");
			menu.setMenuLevel(0);
			menu.setMenuId(0);
		}
		model.addAttribute("menu", menu);
		return "menu/addMenu";
	}
	
	/**
	 * 编辑菜单
	 * @param model
	 * @return 添加页面视图
	 */
	@GetMapping("/edit/{menuId}")
	public String toMenuEdit(@PathVariable("menuId") Integer menuId,Model model) {
		Menu menu = menuService.findMenuByMenuId(menuId);
		Menu parentMenu = menuService.findMenuByMenuId(menu.getParentId());
		model.addAttribute("menu", menu);
		if (StringUtil.isNull(parentMenu)) {
			parentMenu = new Menu();
			parentMenu.setMenuName("根目录");
		}
		model.addAttribute("parentMenu", parentMenu);
		return "menu/editMenu";
	}
	
	/**
     * 选择菜单图标
     */
    @GetMapping("/icon")
    public String icon() {
        return "menu/icon";
    }
    
	/**
	 * 保存
	 * @param Menu
	 * @param bindingResult
	 * @return
	 */
	@PostMapping("/save")
    @ResponseBody
    public int save(Menu menu, BindingResult bindingResult) {
		int res = menuService.saveMenu(menu);
        return res;
    }
	
	/**
	 * 删除
	 * @return int
	 */
	@PostMapping("/delete")
	@ResponseBody
	public Boolean delete(Integer menuId,Model model) {
		int res = menuService.deleteMenuById(menuId);
		if (res > 0) {
			return true;
		} else {
			return false;
		}
	}
	
	/**
     * 选择菜单树
     */
    @GetMapping("/selectMenuTree")
    public String selectMenuTree(){
        return "/menu/menuTree";
    }
    
    @GetMapping("/getMenuTreeNodes")
    @ResponseBody
    public String getMenuTreeNodes() {
    	Map map = menuService.findMenuTree();
    	String treeString = JSONUtils.toJSONString(map);
    	return treeString;
    }
}
