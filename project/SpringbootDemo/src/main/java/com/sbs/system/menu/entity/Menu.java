package com.sbs.system.menu.entity;

import java.util.List;

public class Menu {

	/**
	 * 菜单ID
	 */
	private int menuId;
	/**
	 * 菜单名称
	 */
	private String menuName;
	/**
	 * 父菜单ID
	 */
	private int parentId;
	/**
	 * 菜单排序
	 */
	private int orderNum;
	/**
	 * 菜单url
	 */
	private String url;
	/**
	 * 菜单类型
	 */
	private String menuType;
	/**
	 * 是否可见
	 */
	private int visible;
	/**
	 * 菜单权限标示
	 */
	private String perms;
	/**
	 * 菜单图标
	 */
	private String icon;
	/**
	 * 创建者
	 */
	private String createBy;
	/**
	 * 创建时间
	 */
	private String createTime;
	/**
	 * 更新着
	 */
	private String updateBy;
	/**
	 * 更新时间
	 */
	private String updateTime;
	/**
	 * 备注
	 */
	private String remark;
	/**
	 * 菜单等级
	 */
	private int menuLevel;
	
	public int getMenuLevel() {
		return menuLevel;
	}
	public void setMenuLevel(int menuLevel) {
		this.menuLevel = menuLevel;
	}
	private List<Menu> childMenu;
	
	public int getMenuId() {
		return menuId;
	}
	public void setMenuId(int menuId) {
		this.menuId = menuId;
	}
	public String getMenuName() {
		return menuName;
	}
	public void setMenuName(String menuName) {
		this.menuName = menuName;
	}
	public int getParentId() {
		return parentId;
	}
	public void setParentId(int parentId) {
		this.parentId = parentId;
	}
	public int getOrderNum() {
		return orderNum;
	}
	public void setOrderNum(int orderNum) {
		this.orderNum = orderNum;
	}
	public String getUrl() {
		return url;
	}
	public void setUrl(String url) {
		this.url = url;
	}
	public String getMenuType() {
		return menuType;
	}
	public void setMenuType(String menuType) {
		this.menuType = menuType;
	}
	public int getVisible() {
		return visible;
	}
	public void setVisible(int visible) {
		this.visible = visible;
	}
	public String getPerms() {
		return perms;
	}
	public void setPerms(String perms) {
		this.perms = perms;
	}
	public String getIcon() {
		return icon;
	}
	public void setIcon(String icon) {
		this.icon = icon;
	}
	public String getCreateBy() {
		return createBy;
	}
	public void setCreateBy(String createBy) {
		this.createBy = createBy;
	}
	public String getCreateTime() {
		return createTime;
	}
	public void setCreateTime(String createTime) {
		this.createTime = createTime;
	}
	public String getUpdateBy() {
		return updateBy;
	}
	public void setUpdateBy(String updateBy) {
		this.updateBy = updateBy;
	}
	public String getUpdateTime() {
		return updateTime;
	}
	public void setUpdateTime(String updateTime) {
		this.updateTime = updateTime;
	}
	public String getRemark() {
		return remark;
	}
	public void setRemark(String remark) {
		this.remark = remark;
	}
	public List<Menu> getChildMenu() {
		return childMenu;
	}
	public void setChildMenu(List<Menu> childMenu) {
		this.childMenu = childMenu;
	}
	
}
