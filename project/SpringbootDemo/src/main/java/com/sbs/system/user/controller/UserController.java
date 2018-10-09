package com.sbs.system.user.controller;

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
import com.sbs.system.user.entity.User;
import com.sbs.system.user.service.UserService;

@Controller
@RequestMapping("/system/user")
public class UserController extends BaseController{

	@Autowired
	private UserService userService;
	
	/**
	 * 跳转到用户列表
	 * @return 视图页面
	 */
	@GetMapping()
	private String toUser() {
		
		return "user/userList";
	}
	
	/**
	 * 获得用户表格数据
	 * @return TableDataInfo 数据包装类
	 * @throws IllegalAccessException 
	 * @throws InstantiationException 
	 */
	@GetMapping("/list")
	@ResponseBody
	private TableDataInfo getUserList() {
		buildPagination();
		// 查询参数
		User user = (User) doSearch(ModuleEnum.USER);
		List<User> list = userService.findUserList(user);
		TableDataInfo data = doPagination(list);
		return data;
	}
	
	/**
	 * 编辑用户
	 * @param userId
	 * @param model
	 * @return 编辑页面视图
	 */
	@GetMapping("/edit/{userId}")
	public String toUserEdit(@PathVariable("userId") Integer userId, Model model) {
		User user = userService.findUserByUserId(userId);
		model.addAttribute("user", user);
		return "user/userEdit";
	}
	
	/**
	 * 添加用户
	 * @param model
	 * @return 添加页面视图
	 */
	@GetMapping("/add")
	public String toUserAdd(Model model) {
		model.addAttribute("code", CodeCreateUtil.createCode("USER"));
		return "user/userAdd";
	}
	
	/**
	 * 保存
	 * @param user
	 * @param bindingResult
	 * @return
	 */
	@PostMapping("/save")
    @ResponseBody
    public int save(User user, BindingResult bindingResult) {
		int res = userService.saveUser(user);
        return res;
    }
	
	/**
	 * 删除用户
	 * @param userId
	 * @return
	 */
	@PostMapping("/delete")
	@ResponseBody
	 public Boolean delete(Integer userId) {
		int res = userService.deleteUserById(userId);
		if (res > 0) {
			return true;
		} else {
			return false;
		}
    }
}
