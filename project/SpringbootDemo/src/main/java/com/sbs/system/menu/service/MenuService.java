package com.sbs.system.menu.service;

import java.util.List;

import com.sbs.system.menu.entity.Menu;

public interface MenuService {
	/**
     * 根据条件分页查询菜单对象
     * 
     * @param Menu 菜单信息
     * @return 菜单信息集合信息
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
	public Menu findMenuByID(int id);
	
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
     * 修改菜单信息
     * 
     * @param Menu 菜单信息
     * @return 结果
     */
    public int updateMenu(Menu menu);
    
}
