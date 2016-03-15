package com.codefest.main.controller;

import java.util.ArrayList;
import java.util.List;

import org.codehaus.jackson.map.ObjectMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.codefest.main.entity.Menu;

@Controller
public class VendorController {
	
	@Autowired
	public JdbcTemplate jdbcTemplate;
	
	@RequestMapping(value="/vendor", method = RequestMethod.GET, produces="application/json")

	@SuppressWarnings("all")
	@ResponseBody
	public String getMenuDetails() {
		System.out.println("Entered getAdminPage");
		String msg=null;
		try {
			List<Menu> menu = new ArrayList<>();
			Class<?> entityClass = null;
			Object entityObj = null;
			String sqlVendor = "SELECT * FROM MENU;";
			entityClass = Class.forName("com.codefest.main.entity.Menu");
			entityObj = entityClass.newInstance();
			menu = jdbcTemplate.query(sqlVendor, new BeanPropertyRowMapper(entityObj.getClass()));
			ObjectMapper mapper = new ObjectMapper();
			Long menuID = menu.get(0).getMenuId();
			 msg = mapper.writeValueAsString(menu);
		} catch (Exception e) {
			e.printStackTrace();
		}
		if(msg == null) {
			msg = " ";
		}
		System.out.println("msg:"+msg);
		return msg;	
	}
	
	@RequestMapping(value="/updateMenu", method = RequestMethod.GET, produces="application/json")

	@SuppressWarnings("all")
	@ResponseBody
	public String updateMenu(@RequestParam(value="menuName", required=true) String menuName, 
			@RequestParam(value="desc", required=true) String desc,
			@RequestParam(value="price", required=true) Integer price,
			@RequestParam(value="quantity", required=true) Integer quantity,
			@RequestParam(value="menuId", required=true) Long menuId 
			){
		
		//Quantity, availability
		String UPDATE_SQL = "UPDATE MENU  SET MENU_NAME=? , MENU_DESCRIPTION  =?,PRICE =? WHERE MENU_ID =? ";
		jdbcTemplate.update(UPDATE_SQL,
				new Object[] {menuName, desc,price,menuId

		});
		return null;
	}
	
	@RequestMapping(value="/deleteMenu", method = RequestMethod.GET, produces="application/json")

	@SuppressWarnings("all")
	@ResponseBody
	public String deleteMenu(@RequestParam(value="menuId", required=true) Long menuId ){
		
		System.out.println(menuId);
		String DELETE_SQL = "DELETE FROM  MENU   WHERE MENU_ID =? ";
		jdbcTemplate.update(DELETE_SQL,new Object[] {menuId});
		return null;
	}
	
	@RequestMapping(value="/addMenu", method = RequestMethod.GET, produces="application/json")

	@SuppressWarnings("all")
	@ResponseBody
	public String addMenu(@RequestParam(value="menuName", required=true) String menuName, 
			@RequestParam(value="desc", required=true) String desc,
			@RequestParam(value="price", required=true) Integer price,
			@RequestParam(value="quantity", required=true) Integer quantity
			){
		//VENDOR id VENDOR_ID 
		String INSERT_SQL = "INSERT INTO  MENU(MENU_NAME, MENU_DESCRIPTION,PRICE,quantity,vendor_id,menu_id) VALUES (?,?,?,?,?"+ ",nextval('MENU_SEQ') )";
		jdbcTemplate.update(INSERT_SQL,
				new Object[] {menuName, desc,price,quantity,123

		});
		return null;
	}
	
}
