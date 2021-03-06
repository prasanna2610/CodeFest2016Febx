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
	
	@RequestMapping(value = "/vendorHome", method = RequestMethod.GET)
	public String showAdminHome(){
		return "vendor";
	}
	
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
			String sqlVendor = "SELECT m.*, v.vendor_name FROM MENU m, vendor v where m.vendor_id = v.vendor_id and m.vendor_id= ? order by m.menu_id ";
			entityClass = Class.forName("com.codefest.main.entity.Menu");
			entityObj = entityClass.newInstance();
			menu = jdbcTemplate.query(sqlVendor, new BeanPropertyRowMapper(entityObj.getClass()),vendorId);
			ObjectMapper mapper = new ObjectMapper();
			if(menu!=null && menu.size()>0){
			Long menuID = menu.get(0).getMenuId();
			 msg = mapper.writeValueAsString(menu);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		if(msg == null) {
			msg = "";
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
		String UPDATE_SQL = "UPDATE MENU  SET MENU_NAME=? , MENU_DESCRIPTION  =?,PRICE =?,quantity=?, availability=? WHERE MENU_ID =? and vendor_id=?";
		jdbcTemplate.update(UPDATE_SQL,
				new Object[] {menuName, desc,price,quantity, quantity,menuId,vendorId

		});
		return "";
	}
	
	@RequestMapping(value="/deleteMenu", method = RequestMethod.GET, produces="application/json")

	@SuppressWarnings("all")
	@ResponseBody
	public String deleteMenu(@RequestParam(value="menuId", required=true) Long menuId ){
		Long vendorId = (Long) HttpSessionObjectStore.getObject("userId") ;
		System.out.println(menuId);
		String DELETE_SQL = "DELETE FROM  MENU   WHERE MENU_ID =? and vendor_id=?";
		jdbcTemplate.update(DELETE_SQL,new Object[] {menuId,vendorId});
		return "";
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
		String INSERT_SQL = "INSERT INTO  MENU(MENU_NAME, MENU_DESCRIPTION,PRICE,quantity,vendor_id,availability,menu_id) VALUES (?,?,?,?,?,?"+ ",nextval('MENU_SEQ') )";
		jdbcTemplate.update(INSERT_SQL,
				new Object[] {menuName, desc,price,quantity,vendorId,quantity

		});
		return "";
	}
	
	@RequestMapping(value="/transaction", method = RequestMethod.GET, produces="application/json")
	@SuppressWarnings("all")
	@ResponseBody
	public String getTransactionDetails() {
		String msg=null;
		Long vendorId = (Long) HttpSessionObjectStore.getObject("userId") ;
		
		try {
			List<Menu> menu = new ArrayList<>();
			Class<?> entityClass = null;
			Object entityObj = null;
			String sqlTransaction = "SELECT  t.transaction_id, t.user_id, t.date,t.delivery, o.quantity, m.menu_name , m.price "+
					"FROM  transaction t, order_items o  join menu m   on o.menu_id=m.menu_id "+
					"where t.transaction_id = o.transaction_id and m.vendor_id = ? order by t.date desc";
			entityClass = Class.forName("com.codefest.main.entity.Transaction");
			entityObj = entityClass.newInstance();
			menu = jdbcTemplate.query(sqlTransaction, new BeanPropertyRowMapper(entityObj.getClass()),vendorId);
			ObjectMapper mapper = new ObjectMapper();
			if(menu!=null && menu.size()>0){
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
}
