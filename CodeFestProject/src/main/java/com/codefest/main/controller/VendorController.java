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

import com.codefest.main.config.HttpSessionObjectStore;
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
		Long vendorId = (Long) HttpSessionObjectStore.getObject("userId") ;
		
		try {
			List<Menu> menu = new ArrayList<>();
			Class<?> entityClass = null;
			Object entityObj = null;
			String sqlVendor = "SELECT * FROM MENU where vendor_id= ?";
			entityClass = Class.forName("com.codefest.main.entity.Menu");
			entityObj = entityClass.newInstance();
			menu = jdbcTemplate.query(sqlVendor, new BeanPropertyRowMapper(entityObj.getClass()),vendorId);
			ObjectMapper mapper = new ObjectMapper();
			if(menu!=null && menu.size()<0){
			Long menuID = menu.get(0).getMenuId();
			 msg = mapper.writeValueAsString(menu);
			}
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
		
		Long vendorId = (Long) HttpSessionObjectStore.getObject("userId") ;
		//Quantity, availability
		String UPDATE_SQL = "UPDATE MENU  SET MENU_NAME=? , MENU_DESCRIPTION  =?,PRICE =? WHERE MENU_ID =? and vendor_id=?";
		jdbcTemplate.update(UPDATE_SQL,
				new Object[] {menuName, desc,price,menuId,vendorId

		});
		return null;
	}
	
	@RequestMapping(value="/deleteMenu", method = RequestMethod.GET, produces="application/json")

	@SuppressWarnings("all")
	@ResponseBody
	public String deleteMenu(@RequestParam(value="menuId", required=true) Long menuId ){
		Long vendorId = (Long) HttpSessionObjectStore.getObject("userId") ;
		System.out.println(menuId);
		String DELETE_SQL = "DELETE FROM  MENU   WHERE MENU_ID =? and vendor_id=?";
		jdbcTemplate.update(DELETE_SQL,new Object[] {menuId,vendorId});
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
		
		Long vendorId = (Long) HttpSessionObjectStore.getObject("userId") ;
		String INSERT_SQL = "INSERT INTO  MENU(MENU_NAME, MENU_DESCRIPTION,PRICE,quantity,vendor_id,menu_id) VALUES (?,?,?,?,?"+ ",nextval('MENU_SEQ') )";
		jdbcTemplate.update(INSERT_SQL,
				new Object[] {menuName, desc,price,quantity,vendorId

		});
		return null;
	}
	
}
