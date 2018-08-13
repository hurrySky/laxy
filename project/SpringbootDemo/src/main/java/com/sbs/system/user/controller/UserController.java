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
import com.sbs.common.tools.CodeCreateUtil;
import com.sbs.system.user.entity.User;
import com.sbs.system.user.service.UserService;

@Controller
@RequestMapping("/system/user")
public class UserController extends BaseController{

	@Autowired
	private UserService userService;
	
	@GetMapping()
	private String toUser() {
		
		return "user/userList";
	}
	
	@GetMapping("/list")
	@ResponseBody
	private TableDataInfo getUserList() {
		buildPagination();
		List<User> list = userService.findUserList(null);
//		String json = JSONArray.toJSONString(list);
//		System.out.println(json);
		TableDataInfo data = doPagination(list);
		return data;
	}
	
	@GetMapping("/edit/{userId}")
	public String toUserEdit(@PathVariable("userId") Integer userId, Model model) {
		User user = userService.findUserByID(userId);
		model.addAttribute("user", user);
		return "user/userEdit";
	}
	
	@GetMapping("/add")
	public String toUserAdd(Model model) {
		model.addAttribute("code", CodeCreateUtil.createCode("USER"));
		return "user/userAdd";
	}
	
	@PostMapping("/save")
    @ResponseBody
    public int save(User user, BindingResult bindingResult) {
		int res = userService.saveUser(user);
        return res;
    }
}
