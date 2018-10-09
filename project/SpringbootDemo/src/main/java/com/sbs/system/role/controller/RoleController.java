package com.sbs.system.role.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.sbs.base.web.controller.BaseController;
import com.sbs.base.web.pagination.TableDataInfo;
import com.sbs.common.ModuleEnum;
import com.sbs.common.tools.CodeCreateUtil;
import com.sbs.system.role.entity.Role;
import com.sbs.system.role.service.RoleService;
import com.sbs.system.user.entity.User;

@Controller
@RequestMapping("/system/role")
public class RoleController extends BaseController{

	@Autowired
	private RoleService roleService;
	
	@GetMapping()
	public String toRole() {
		
		return "role/roleList";
	}
	
	@GetMapping("/list")
	@ResponseBody
	public TableDataInfo getUserList() {
		buildPagination();
		Role role = (Role) doSearch(ModuleEnum.ROLE);
		List<Role> list = roleService.findRoleList(role);
		return doPagination(list);
	}
	
	/**
	 * 编辑用户
	 * @param userId
	 * @param model
	 * @return 编辑页面视图
	 */
	@GetMapping("/edit/{roleId}")
	public String toUserEdit(@PathVariable("roleId") Integer roleId, Model model) {
		Role role = roleService.findRoleByRoleId(roleId);
		model.addAttribute("role", role);
		return "role/roleEdit";
	}
	
	/**
	 * 添加用户
	 * @param model
	 * @return 添加页面视图
	 */
	@GetMapping("/add")
	public String toUserAdd(Model model) {
		model.addAttribute("code", CodeCreateUtil.createCode("ROLE"));
		return "role/roleAdd";
	}
	
	/**
	 * 保存
	 * @param user
	 * @param bindingResult
	 * @return
	 */
	@PostMapping("/save")
    @ResponseBody
    public int save(Role role, BindingResult bindingResult) {
		int res = roleService.saveRole(role);
        return res;
    }
	
	@PostMapping("/delete")
	@ResponseBody
	 public Boolean delete(Integer roleId) {
		int res = roleService.deleteRoleByRoleId(roleId);
		if (res > 0) {
			return true;
		} else {
			return false;
		}
    }
}
