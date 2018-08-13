package com.sbs.control;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.sbs.system.user.entity.User;
import com.sbs.system.user.service.UserService;

@Controller
public class SpringbootTController {
	
	@Autowired
	UserService userService;
	@RequestMapping("/dd")
	public String retHello(Model model) {
		model.addAttribute("name", "lixin");
		User user = userService.findUserByID(1);
		return "index";
	}
}
