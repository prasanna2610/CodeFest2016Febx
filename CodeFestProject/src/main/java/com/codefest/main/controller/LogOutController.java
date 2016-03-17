package com.codefest.main.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.codefest.main.config.ObjectStoreManager;

@Controller
public class LogOutController {

	@RequestMapping(value = "/logout", method = RequestMethod.POST)
	public String logOut(Model model) {
		ObjectStoreManager.getInstance().cleanup();
		return "/index";
	}
}
