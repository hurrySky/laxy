package com.sbs.system.menu.service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.thymeleaf.util.ArrayUtils;

import com.alibaba.druid.support.json.JSONUtils;
import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.sbs.common.tools.ShiroUtils;
import com.sbs.common.tools.StringUtil;
import com.sbs.system.menu.entity.Menu;
import com.sbs.system.menu.mapper.MenuMapper;
import com.sbs.system.user.entity.User;

@Service("menuService")
public class MenuServiceImpl implements MenuService, Cloneable{

	@Autowired
	private MenuMapper menuMapper;
	
	private Map<String, Object> resMap = new HashMap<String, Object>();
	
	@Override
	public List<Menu> findVisibleMenuList(User user) {
		// 获得Visible 显示的一级菜单集合
		List<Menu> list = menuMapper.findVisibleFirstMenuList(user.getUserId());
		// 通过一级菜单遍历查询出子菜单集合，得道的list为完整的菜单树
		list = getMenuList(list, true);
		return list;
	}

	@Override
	public List<Menu> findMenuList() {
		// 获得Visible 显示的一级菜单集合
		List<Menu> list = menuMapper.findFirstMenuList();
		// 通过一级菜单遍历查询出子菜单集合，得道的list为完整的菜单树
		list = getMenuList(list, false);
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
	public Menu findMenuByMenuId(int menuId) {
		Menu menu = menuMapper.findMenuByID(menuId);
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
		int count = -99;
		if (StringUtil.isNotNull(menu)) {
			if (StringUtil.isNotNull(menu.getMenuId())) {
				count = menuMapper.updateMenu(menu);
			}else {
				int maxSameLevelOrder = menuMapper.findSameLevelMaxOrderNum(menu.getParentId());
				menu.setOrderNum(maxSameLevelOrder + 1);
				count = menuMapper.saveMenu(menu);
			}
		}
		return count;
	}

	/**
	 * 通过父级菜单集合参数，是否查询可见菜单，查询获得该集合下的子菜单及父菜单的集合
	 * 递归查询
	 * 
	 * @param lists:父级菜单集合
	 * @param isVisible:是否可见菜单
	 * @return： 该父菜单集合的菜单及子菜单的集合
	 */
	private List<Menu> getMenuList(List<Menu> lists, Boolean onlyVisible) {
		if (lists != null) {
			for (Menu menu : lists) {
				List<Menu> list = null;
				if (onlyVisible) {
					User user = ShiroUtils.getUser();
					list = menuMapper.findVisibleSonMenuByMenuId(menu.getMenuId(),user.getUserId());
				}else {
					list = menuMapper.findSonMenuByMenuId(menu.getMenuId());
				}
				if (list != null && list.size() > 0) {
					menu.setChildMenu(list);
					getMenuList(list, onlyVisible);
				}
			}
		}
		return lists;
	}
	
	/**
	 * 获得菜单及该菜单的子菜单 树
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

	@Override
	public Map<String, Object> findMenuTree() {
		List<Menu> list = findMenuList();
		resMap.put("name", "根目录");
		resMap.put("open", "true");
		resMap.put("id", 0);
		resMap.put("parentId", 0);
		resMap.put("menuLevel", 0);
		List<Map<String,Object>> res = new ArrayList<Map<String,Object>>();
		for (Menu menu : list) {
			res.add(getMenuTreeData(menu));
		}
		resMap.put("children", res);
		//System.out.println(JSONUtils.toJSONString(resMap));
		//JSONObject jsonObject = JSONObject.parseObject(JSON.toJSONString(resMap));
		//System.out.println(jsonObject);
		return resMap;
	}
	
	public Map<String, Object> getMenuTreeData(Menu menu) {

		Map<String, Object> mapTemp = new HashMap<String, Object>();
		if (StringUtil.isNotNull(menu)) {
			mapTemp.put("name", menu.getMenuName());
			mapTemp.put("id", menu.getMenuId());
			mapTemp.put("parentId", menu.getParentId());
			mapTemp.put("menuLevel", menu.getMenuLevel());
			if ("M".equals(menu.getMenuType())) {
				mapTemp.put("open", true);
			}
			if (StringUtil.isNotNull(menu.getChildMenu())) {
				for (Menu menuSon : menu.getChildMenu()) {
					List<Map<String,Object>> list = new ArrayList<Map<String,Object>>();
					list.add(getMenuTreeData(menuSon));
					mapTemp.put("children", list);
				}
			}
		}
		return mapTemp;
	}
	
//	public void getMenuTreeData(Menu menu, Map<String, Object> map) {
//		Map<String, Object> mapTemp = null;
//		Map<String, Object> resMap = new HashMap<String, Object>();
//		if (resMap.size()== 0) {
//			resMap.putAll(map);
//		}
//		if (StringUtil.isNotNull(menu)) {
//			mapTemp = new HashMap<String, Object>();
//			mapTemp.put("name", menu.getMenuName());
//			if ("M".equals(menu.getMenuType())) {
//				mapTemp.put("open", true);
//			}
//			map.put("children", mapTemp);
//			if (StringUtil.isNotNull(menu.getChildMenu())) {
//				for (Menu menuSon : menu.getChildMenu()) {
//					getMenuTreeData(menuSon,mapTemp);
//				}
//			}
//		}
//	}
	
}
