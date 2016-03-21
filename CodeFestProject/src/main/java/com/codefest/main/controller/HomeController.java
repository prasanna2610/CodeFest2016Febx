package com.codefest.main.controller;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.codefest.main.config.HttpSessionObjectStore;
import com.codefest.main.entity.CFUser;
import com.codefest.main.vo.HomeVO;

@Controller
public class HomeController {

	@Autowired
	public JdbcTemplate jdbcTemplate;

	@RequestMapping(value = { "/index", "/" })
	public String welcome() {
		return "index";
	}
	
	@RequestMapping(value = "/home", method = RequestMethod.GET)
	public String hello() throws IOException {
		return "index";
		
	}
	
	@RequestMapping(value = "/validate", method = RequestMethod.GET)
	@ResponseBody
	public String validateSession(HttpServletRequest request) throws IOException {
		Object userId = HttpSessionObjectStore.getObject("userId");
		if(null != userId){
			return userId.toString();
		}
		return "invalid";
		
	}

	@SuppressWarnings("unchecked")
	@RequestMapping(value = "/home", method = RequestMethod.POST)
	public String hello(
			@RequestParam(value = "userName", required = true) String userName1,
			@RequestParam(value = "passWord", required = true) String passWord1,
			Model model,
			HttpServletResponse response) throws IOException {

		HomeVO homevoObject = new HomeVO();
		homevoObject.setUserName(userName1);
		homevoObject.setPassWord(passWord1);
		List<CFUser> user = null;
		try {
			Class<?> entityClass = null;
			Object entityObj = null;
			String sqlVendor = "SELECT * FROM cf_user where first_name = ?";
			entityClass = Class.forName("com.codefest.main.entity.CFUser");
			entityObj = entityClass.newInstance();
			user = (ArrayList<CFUser>) jdbcTemplate.query(sqlVendor,
					new Object[] { userName1 }, new BeanPropertyRowMapper(
							entityObj.getClass()));
		} catch (Exception e) {
			e.printStackTrace();
		}
		String returnString = null;
		if (user == null || user.isEmpty()) {
			model.addAttribute("errorMsg", "Incorrect Username and Password");
			returnString = "index";
		} else if (!(user.get(0).getPassword().matches(passWord1))) {
			model.addAttribute("errorMsg", "Incorrect Password");
			returnString = "index";
		} else {
			model.addAttribute("userInfo",
					"Welcome " + homevoObject.getUserName());
			if (user.get(0).getUserType().equalsIgnoreCase("vendor")) {
				returnString = "vendor";
			} else if (user.get(0).getUserType().equalsIgnoreCase("admin")) {
				returnString = "admin";
			} else if (user.get(0).getUserType().equalsIgnoreCase("user")) {
				HttpSessionObjectStore.setObject("userInfo", "Welcome " + homevoObject.getUserName());
				response.sendRedirect("/order");
			}
			HttpSessionObjectStore.setObject("userId", user.get(0).getUserId());
		}
		return returnString;

	}



}
