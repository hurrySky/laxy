package com.sbs.system.role.service;

import java.util.List;

import com.sbs.system.role.entity.Role;
import com.sbs.system.user.entity.User;

public interface RoleService {

	/**
     * 根据条件分页查询角色对象
     * 
     * @param role 角色信息
     * @return 角色信息集合
     */
    public List<Role> findRoleList(Role role);
    
    /**
	 * 根据角色id 查找角色
	 * @param id
	 * @return 角色对象信息
	 */
	public Role findRoleByRoleId(Integer RoleId);
	
	/**
     * 保存角色信息
     * 
     * @param user 角色对象
     * @return 结果
     */
    public int saveRole(Role role);

	/**
	 * 通过角色ID删除角色
	 * 
	 * @param roleId 角色ID
	 * @return 结果
	 */
	public int deleteRoleByRoleId(Integer roleId);
	
	/**
     * 批量删除角色信息
     * 
     * @param ids 需要删除的数据roleIds
     * @return 结果
     */
    public int batchDeleteRoleByRoleId(int[] roleId);
}
