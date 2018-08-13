package com.sbs.system.menu.mapper;

import java.util.List;

import com.sbs.system.menu.entity.Menu;
import com.sbs.system.user.entity.User;

public interface MenuMapper {

	/**
     * 获得一级菜单
     * 
     * @param
     * @return 一级菜单集合
     */
    public List<Menu> findFirstMenuList();
    
	/**
	 * 根据菜单id(menuId)查找菜单
	 * @param menuId
	 * @return 菜单对象
	 */
	public Menu findMenuByID(int menuId);
	
	/**
	 *  根据父菜单ID查询子菜单
	 * @param id
	 * @return
	 */
	public List<Menu> findSonMenuByMenuId(int menuId);
    /**
     * 通过菜单ID删除菜单
     * 
     * @param userId 菜单ID
     * @return 结果
     */
    public int deleteMenuById(int menuId);

    /**
     * 批量删除菜单信息
     * 
     * @param ids 需要删除的数据ID
     * @return 结果
     */
    public int batchDeleteMenu(int[] menuIds);

    /**
     * 保存菜单信息
     * 
     * @param user 菜单信息
     * @return 结果
     */
    public int saveMenu(Menu menu);

    /**
     * 修改菜单信息
     * 
     * @param user 菜单信息
     * @return 结果
     */
    public int updateMenu(Menu Menu);

}