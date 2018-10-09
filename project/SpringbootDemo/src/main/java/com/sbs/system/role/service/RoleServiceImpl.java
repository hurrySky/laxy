package com.sbs.system.role.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.sbs.common.tools.StringUtil;
import com.sbs.system.role.entity.Role;
import com.sbs.system.role.mapper.RoleMapper;
import com.sbs.system.user.entity.User;

@Service("roleService")
public class RoleServiceImpl implements RoleService{

	@Autowired
	RoleMapper roleMapper;
	
	@Override
	public List<Role> findRoleList(Role role) {
		List<Role> list = roleMapper.findRoleList(role);
		return list;
	}

	@Override
	public Role findRoleByRoleId(Integer RoleId) {
		Role role = roleMapper.findRoleByRoleId(RoleId);
		return role;
	}

	@Override
	public int saveRole(Role role) {
		int count = -99;
		if (StringUtil.isNotNull(role)) {
			if (StringUtil.isNotNull(role.getRoleId())) {
				count = roleMapper.updateRole(role);
			}else {
				count = roleMapper.saveRole(role);
			}
		}
		return count;
	}

	@Override
	public int deleteRoleByRoleId(Integer roleId) {
		int res = roleMapper.deleteRoleByRoleId(roleId);
		return res;
	}

	@Override
	public int batchDeleteRoleByRoleId(int[] roleId) {
		// TODO Auto-generated method stub
		return 0;
	}

}
