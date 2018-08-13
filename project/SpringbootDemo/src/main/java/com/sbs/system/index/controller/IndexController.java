package com.sbs.system.index.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class IndexController {

	@GetMapping("/index/main")
	public String toMain() {
	
		return "main";
	}
}
