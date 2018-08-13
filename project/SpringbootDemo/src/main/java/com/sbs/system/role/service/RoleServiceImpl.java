package com.sbs.system.role.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.sbs.system.role.entity.Role;
import com.sbs.system.role.mapper.RoleMapper;

@Service("roleService")
public class RoleServiceImpl implements RoleService{

	@Autowired
	RoleMapper roleMapper;
	
	@Override
	public List<Role> findRoleList() {
		List<Role> list = roleMapper.findRoleList();
		return list;
	}

}
