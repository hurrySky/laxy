package com.sbs.system.menu.service;

import java.util.List;
import java.util.Map;

import com.sbs.system.menu.entity.Menu;

public interface MenuService {
	/**
     * 查询导航栏菜单树集合对象，其中每个菜单项都是visible 为可见的
     * 
     * @return 完整的可见菜单树
     */
    public List<Menu> findVisibleMenuList();
	
    /**
     * 查询菜单树集合对象，每个菜单项visible 既可以是为可见的也可以是不可见的
     * 
     * @return 完整的菜单树
     */
    public List<Menu> findMenuList();
    
    /**
     * 获得菜单列表,根据父菜单id获得子菜单
     * @return List<Menu>
     */
    public List<Menu> findSonMenuListByMenuId(Integer[] menuIds);
	/**
	 * 根据菜单id 查找菜单
	 * @param id
	 * @return 菜单对象
	 */
	public Menu findMenuByMenuId(int id);
	
    /**
     * 通过菜单ID删除菜单
     * 
     * @param MenuId 菜单ID
     * @return 结果
     */
    public int deleteMenuById(int id);

    /**
     * 批量删除菜单信息
     * 
     * @param ids 需要删除的数据ID
     * @return 结果
     */
    public int batchDeleteMenu(int[] ids);

    /**
     * 保存菜单信息
     * 
     * @param Menu 菜单信息
     * @return 结果
     */
    public int saveMenu(Menu menu);
    
    /**
     * 获得一级菜单
     * @return 一级菜单集合
     */
    public Map<String, Object> findMenuTree();
}
