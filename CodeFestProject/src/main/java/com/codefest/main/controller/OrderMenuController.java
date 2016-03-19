package com.codefest.main.controller;

import java.util.ArrayList;
import java.util.List;

import org.codehaus.jackson.map.ObjectMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.stereotype.Controller;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.codefest.main.config.HttpSessionObjectStore;
import com.codefest.main.entity.Menu;
import com.codefest.main.entity.Vendor;

@Controller
@RequestMapping(value = "/order")
public class OrderMenuController {
	
	@Autowired
	public JdbcTemplate jdbcTemplate;
	
	@Autowired
	public NamedParameterJdbcTemplate namedParameterJdbcTemplate;
	
	@RequestMapping(method = RequestMethod.GET, produces=MediaType.APPLICATION_JSON_VALUE)
	@ResponseBody
	@SuppressWarnings("all")
	public ModelAndView getMenuPage() {
		System.out.println("Entered getMenuPage");
		List<Vendor> vendorList = new ArrayList<>();
		Class<?> entityClass = null;
		Object entityObj = null;
		String queryVendor = "Select * from VENDOR;";
		String queryVendorMenu = "select m.* from menu m, vendor v where m.vendor_id = v.vendor_id and v.vendor_id = ?";
		ModelAndView modelView = new ModelAndView("menu");
		try {
			entityClass = Class.forName("com.codefest.main.entity.Vendor");
			entityObj = entityClass.newInstance();
			vendorList = jdbcTemplate.query(queryVendor, new BeanPropertyRowMapper(entityObj.getClass()));
			
			if(null != vendorList && !vendorList.isEmpty()){
				Long vendorId = vendorList.get(0).getVendorId();
				List<Menu> menuList = jdbcTemplate.query(queryVendorMenu, new Object[] { vendorId },
						new BeanPropertyRowMapper(Menu.class));
				vendorList.get(0).setMenu(menuList);
			}
			modelView.addObject("vendorList", vendorList);
			
		} catch (Exception  e) {
			e.printStackTrace();
		}
    	return modelView;
	}
	
	@RequestMapping(value = "/menu/{vendorId}", method = RequestMethod.GET, produces=MediaType.APPLICATION_JSON_VALUE)
	@ResponseBody
	@SuppressWarnings("all")
	public String getMenu(@PathVariable("vendorId") Long vendorId){
		Vendor vendor = null;
		Class<?> entityClass = null;
		Object entityObj = null;
		String queryVendor = "Select * from VENDOR where vendor_id= ?;";
		String queryVendorMenu = "select m.* from menu m, vendor v where m.vendor_id = v.vendor_id and v.vendor_id = ?";
		String msg=null;
		try {
			entityClass = Class.forName("com.codefest.main.entity.Vendor");
			entityObj = entityClass.newInstance();
			vendor = (Vendor) jdbcTemplate.queryForObject(queryVendor, new Object[] { vendorId }, new BeanPropertyRowMapper(entityObj.getClass()));
			ObjectMapper mapper = new ObjectMapper();
			
			if(null != vendor){
				List<Menu> menuList = jdbcTemplate.query(queryVendorMenu, new Object[] { vendorId },
						new BeanPropertyRowMapper(Menu.class));
				vendor.setMenu(menuList);
			}
			msg = mapper.writeValueAsString(vendor);
			
		} catch (Exception  e) {
			e.printStackTrace();
		}
    	return msg;
	}
	
	@RequestMapping(value="/create", method = RequestMethod.POST, produces=MediaType.APPLICATION_JSON_VALUE, consumes=MediaType.APPLICATION_JSON_VALUE)
	@Transactional
	public String purchase(@RequestBody List<Menu> menuDetails){
		
		if(null != menuDetails && !menuDetails.isEmpty()){
			Long txnId = null;
			String getNextTxnId = "SELECT NEXTVAL(TRANSACTION_SEQ)";
			String insertTransactionQuery = "INSERT INTO TRANSACTION (TRANSACTION_ID, USER_ID, DATE, DELIVERED) TRANSACTION_ID VALUES (?, ?, CURRENT_TIMESTAMP, 'N'";
			String insertOrderItemsQuery = "INSERT INTO ORDER_ITEMS (TRANSACTION_ID, MENU_ID, QUANTITY) TRANSACTION_ID VALUES (?, ?, ?)";
			try {
				txnId = (Long)jdbcTemplate.queryForLong(getNextTxnId);
				Long userId = (Long) HttpSessionObjectStore.getObject("userId") ;
				if(null != userId && userId.longValue() != 0){
					jdbcTemplate.update(insertTransactionQuery,
							new Object[] {txnId, userId});
				}else{
					txnId = null;
					return null;
				}
			}catch(Exception e){
				e.printStackTrace();
				txnId = null;
				return null; 
			}
			try{
				if(null != txnId){
					for (Menu menu : menuDetails){
						jdbcTemplate.update(insertOrderItemsQuery,
							new Object[] {txnId, menu.getMenuId(), menu.getQuantity()});
					}
				}
				
			} catch (Exception  e) {
				e.printStackTrace();
				txnId = null;
				return null;
			}
			return txnId.toString();
		}
		return null;
	}
}
