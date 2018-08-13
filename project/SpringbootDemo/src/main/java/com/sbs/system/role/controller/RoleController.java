package com.sbs.system.role.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.sbs.base.web.controller.BaseController;
import com.sbs.base.web.pagination.TableDataInfo;
import com.sbs.system.role.entity.Role;
import com.sbs.system.role.service.RoleService;

@Controller
public class RoleController extends BaseController{

	@Autowired
	private RoleService roleService;
	
	@GetMapping("/system/role")
	public String toRole() {
		
		return "role/roleList";
	}
	
	@GetMapping("/system/role/list")
	@ResponseBody
	public TableDataInfo getUserList() {
		buildPagination();
		List<Role> list = roleService.findRoleList();
		return doPagination(list);
	}
}
