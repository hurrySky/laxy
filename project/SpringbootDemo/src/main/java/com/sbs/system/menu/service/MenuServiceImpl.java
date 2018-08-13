package com.sbs.system.menu.service;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.thymeleaf.util.ArrayUtils;

import com.sbs.common.tools.StringUtil;
import com.sbs.system.menu.entity.Menu;
import com.sbs.system.menu.mapper.MenuMapper;

@Service("menuService")
public class MenuServiceImpl implements MenuService{

	@Autowired
	private MenuMapper menuMapper;
	
	@Override
	public List<Menu> findMenuList() {
		List<Menu> list = menuMapper.findFirstMenuList();
		list = getMenuList(list);
		return list;
	}

	@Override
	public List<Menu> findSonMenuListByMenuId(Integer[] menuIds) {
		List<Menu> result = null;
		List<Menu> list = menuMapper.findFirstMenuList();
		if (StringUtil.isNotNull(list)) {
			if (StringUtil.isNull(menuIds)) {
				result = list;
				return result;
			} else {
				result = getMenuSonTableList(list, menuIds);
				result = getOrderedMenuList(result);
				return result;
			}
		} else {
			return null;
		}
	}
	
	@Override
	public Menu findMenuByID(int id) {
		Menu menu = menuMapper.findMenuByID(id);
		return menu;
	}

	@Override
	public int deleteMenuById(int id) {
		int count = menuMapper.deleteMenuById(id);
		return count;
	}

	@Override
	public int batchDeleteMenu(int[] ids) {
		int count = menuMapper.batchDeleteMenu(ids);
		return count;
	}

	@Override
	public int saveMenu(Menu menu) {
		int count = menuMapper.saveMenu(menu);
		return count;
	}

	@Override
	public int updateMenu(Menu menu) {
		int count = menuMapper.updateMenu(menu);
		return count;
	}
	
	/**
	 * 递归获得子菜单
	 * 
	 * @param : 一级菜单集合
	 * @return： 菜单树集合
	 */
	private List<Menu> getMenuList(List<Menu> lists) {
		if (lists != null) {
			for (Menu menu : lists) {
				List<Menu> list = menuMapper.findSonMenuByMenuId(menu.getMenuId());
				if (list != null && list.size() > 0) {
					menu.setChildMenu(list);
					getMenuList(list);
				}
			}
		}
		return lists;
	}
	
	/**
	 * 获得菜单击该菜单的子菜单 树
	 * @param integerArr menuid 数组
	 * @param list 一级菜单
	 */
	private List<Menu> getMenuSonTableList(List<Menu> list, Integer[] integerArr) {
		List<Menu> result = new ArrayList<Menu>();
		if (StringUtil.isNotNull(list)) {
			for (Menu menu : list) {
				if (ArrayUtils.contains(integerArr, menu.getMenuId())){
					List<Menu> TempList = menuMapper.findSonMenuByMenuId(menu.getMenuId());
					menu.setChildMenu(TempList);
					getMenuSonTableList(TempList, integerArr); // 递归
				}
				result.add(menu);
			}
		}
		return result;
	}
	
	/**
	 * 获得按父菜单分组，子菜单排序序号排序后的菜单列表（调用 处理方法doOrderedMenuList）
	 * @param lists
	 * @return
	 */
	private List<Menu> getOrderedMenuList(List<Menu> lists) {
		List<Menu> result = new ArrayList<Menu>();
		result = doOrderedMenuList(lists, result);
		return result;
	}
	/**
	 *  通过递归， 获得按父菜单分组，子菜单排序序号排序后的菜单列表（实际处理方法）
	 * @param lists
	 * @param tempResult
	 * @return
	 */
	private List<Menu> doOrderedMenuList(List<Menu> lists, List<Menu> tempResult) {
		if (StringUtil.isNotNull(lists)) {
			for (Menu Menu : lists) {
				tempResult.add(Menu);
				if (StringUtil.isNotNull(Menu.getChildMenu())) {
					doOrderedMenuList(Menu.getChildMenu(), tempResult); // 递归
				}
			}
		}else{
			return tempResult;
		}
		return tempResult;
	}
}
