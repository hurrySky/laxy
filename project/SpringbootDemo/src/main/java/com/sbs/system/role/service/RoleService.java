package com.sbs.system.role.service;

import java.util.List;

import com.sbs.system.role.entity.Role;

public interface RoleService {

	/**
     * 根据条件分页查询菜单对象
     * 
     * @param Menu 菜单信息
     * @return 菜单信息集合信息
     */
    public List<Role> findRoleList();
}
