package com.sbs.system.role.mapper;

import java.util.List;

import com.sbs.system.role.entity.Role;
import com.sbs.system.user.entity.User;

public interface RoleMapper {
	/**
     * 根据条件分页查询角色，通过role对象进行搜索
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
     * 修改角色信息
     * 
     * @param user 用户信息
     * @return 结果
     */
    public int updateRole(Role role);
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
