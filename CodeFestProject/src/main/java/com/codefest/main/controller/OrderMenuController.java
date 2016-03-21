package com.codefest.main.controller;

import java.util.ArrayList;
import java.util.List;

import org.codehaus.jackson.map.ObjectMapper;
import org.json.JSONArray;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.stereotype.Controller;
import org.springframework.transaction.annotation.Transactional;
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
	
	@Autowired
	public SendSMSController sendSMS; 
	
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
			String userName = (String) HttpSessionObjectStore.getObject("userInfo");
			modelView.getModel().put("userInfo", userName);
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
		String queryVendorMenu = "select m.* from menu m, vendor v where m.vendor_id = v.vendor_id and v.vendor_id = ? and m.availability > 0";
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
	
	@RequestMapping(value="/create", method = RequestMethod.POST, consumes=MediaType.APPLICATION_JSON_VALUE)
	@Transactional
	@ResponseBody
	@SuppressWarnings("all")
	public String purchase(@RequestBody String menuDetails){
		
		if(null != menuDetails && !menuDetails.isEmpty()){
			JSONObject jsonObj = new JSONObject(menuDetails);
			JSONArray menuArr = (JSONArray) jsonObj.get("menu");
			Long txnId = null;
			if(null == menuArr) {
				txnId = null;
				return null;
			}
			
			String getNextTxnId = "SELECT NEXTVAL('TRANSACTION_SEQ')";
			String insertTransactionQuery = "INSERT INTO TRANSACTION (TRANSACTION_ID, USER_ID, DATE, DELIVERY) VALUES (?, ?, CURRENT_TIMESTAMP, 'N')";
			String insertOrderItemsQuery = "INSERT INTO ORDER_ITEMS (TRANSACTION_ID, MENU_ID, QUANTITY) VALUES (?, ?, ?)";
			String queryAvailability = "SELECT AVAILABILITY FROM MENU WHERE MENU_ID = ?";
			String updateAvailability = "UPDATE MENU  SET AVAILABILITY = AVAILABILITY - ? WHERE MENU_ID =?";
			String deleteOrderItem = "DELETE FROM ORDER_ITEMS WHERE TRANSACTION_ID = ?";
			String deleteTransaction = "DELETE FROM TRANSACTION WHERE TRANSACTION_ID=?";
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
				if(null != txnId){
					jdbcTemplate.update(deleteOrderItem, new Object[] {txnId});
					jdbcTemplate.update(deleteTransaction, new Object[] {txnId});
				}
				txnId = null;
				return null; 
			}
			try{
				if(null != txnId){
					for (Object obj : menuArr){
						JSONObject menu = (JSONObject) obj;
						String menuId = (String) menu.get("menuId");
						String quantity = (String) menu.get("quantity");
						if(null != menuId && null != quantity){
							long menuIdLong = Long.parseLong(menuId);
							long quantityLong = Long.parseLong(quantity);
							List<Menu> menuObj = (List<Menu>) jdbcTemplate.query(queryAvailability, new Object[] { menuIdLong },
									new BeanPropertyRowMapper(Menu.class));
							if(null == menuObj || menuObj.isEmpty() || null == menuObj.get(0)){
								throw new Exception();
							}
							long availability = Long.parseLong(menuObj.get(0).getAvailability());
							availability-=quantityLong;
							if(Long.compare(quantityLong, availability) <= 0){
								jdbcTemplate.update(updateAvailability,
										new Object[] {txnId, availability});
								jdbcTemplate.update(insertOrderItemsQuery,
										new Object[] {txnId, menuIdLong, quantityLong});
							}else{
								if(null != txnId){
									throw new Exception();
								}
							}
						}
					}
				}
				
			} catch (Exception  e) {
				e.printStackTrace();
				if(null != txnId){
					jdbcTemplate.update(deleteOrderItem, new Object[] {txnId});
					jdbcTemplate.update(deleteTransaction, new Object[] {txnId});
				}
				txnId = null;
				return null;
			}
			if(null != txnId){
				sendSMS.sendSMS(txnId);
			}
			return txnId.toString();
		}
		return null;
	}
}
