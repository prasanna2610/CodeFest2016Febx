package com.codefest.main.controller;

import java.sql.Types;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Set;

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
import com.codefest.main.entity.Vendor;

@Controller
@RequestMapping("/vendor")
public class VendorController {
	
	@Autowired
	public JdbcTemplate jdbcTemplate;
	
	@RequestMapping(method = RequestMethod.GET, produces="application/json")
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
	
	public String updateVendor(@RequestParam(value="vendor", required=true) Vendor vendor){
		System.out.println("Entered updateVendor");
		Long vendorId = vendor.getVendorId();
		String updateQuery = "UPDATE VENDOR SET VENDOR_NAME = ?, VEBDOR_EMAIL = ?, VENDOR_PHONE = ?, INCHARGE = ?, VENDOR_DETAIL = ? WHEN VENDOR_ID = ?";
		try{
		List<Vendor> vendorListDB = jdbcTemplate.query("SELECT * FROM VENDOR WHERE VENDOR_ID = ?", new Object[] { vendorId },
				new BeanPropertyRowMapper(Vendor.class));
		populateVendor(vendor,vendorListDB.get(0));
		Object[] params = { vendor.getVendorName(), vendor.getVendorEmail(), vendor.getVendorPhone(), vendor.getIncharge(), vendor.getVendorDetail()};
		int[] types = {Types.VARCHAR, Types.BIGINT};

		jdbcTemplate.update(updateQuery, params, types);
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		return null;
	}
	
	private void populateVendor(Vendor vendor, Vendor vendorDB){
		
	}

}
