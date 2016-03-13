package com.codefest.main.controller;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import org.codehaus.jackson.JsonGenerationException;
import org.codehaus.jackson.map.JsonMappingException;
import org.codehaus.jackson.map.ObjectMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.codefest.main.entity.Transaction;
import com.codefest.main.entity.Vendor;

@Controller
@RequestMapping(value = "/admin")
public class AdminController {
	@Autowired
	public JdbcTemplate jdbcTemplate;
	
	@Autowired
	public NamedParameterJdbcTemplate namedParameterJdbcTemplate;
	
	@RequestMapping(method = RequestMethod.GET, produces="application/json")
	@ResponseBody
	@SuppressWarnings("all")
	public String getAdminPage(Model model) {
		System.out.println("Entered getAdminPage");
		List<Vendor> vendor = new ArrayList<>();
		Class<?> entityClass = null;
		Object entityObj = null;
		String sqlVendor = "Select * from VENDOR;";
		// String sqlTransaction = "Select * from Transaction where vendor_id in (:listOfValues)";
		String sqlTransaction = "SELECT t.TRANSACTION_ID, t.VENDOR_ID, m.MENU_NAME, m.PRICE, t.USER_ID, t.DATE FROM TRANSACTION t, ORDER_ITEMS o, "
				+ "MENU m where t.transaction_id = o.transaction_id and o.menu_id = m.menu_id and t.vendor_id = ?";
		/*List<Integer> vendorIds = new ArrayList<>();
		Map<String, Object> params = new HashMap<String, Object>();
		params.put("listOfValues", vendorIds);*/
		String msg=null;
		try {
			entityClass = Class.forName("com.codefest.main.entity.Vendor");
			entityObj = entityClass.newInstance();
			vendor = jdbcTemplate.query(sqlVendor, new BeanPropertyRowMapper(entityObj.getClass()));
			ObjectMapper mapper = new ObjectMapper();
			
			
			Long vendorId = vendor.get(0).getVendorId();
			List<Transaction> transactionList = jdbcTemplate.query(sqlTransaction, new Object[] { vendorId },
					new BeanPropertyRowMapper(Transaction.class));

			vendor.get(0).setTransaction(transactionList);
			try {
				 msg = mapper.writeValueAsString(vendor);
			} catch (JsonGenerationException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (JsonMappingException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			/*
			 * List<Transaction> transactionList =
			 * namedParameterJdbcTemplate.query( sqlTransaction, params,
			 * ParameterizedBeanPropertyRowMapper.newInstance(Transaction.class)
			 * );
			 */
			/*List<Transaction> tl = null;
			for (Vendor ven : vendor) {
				tl = new ArrayList<>();
				for (Transaction transaction : transactionList) {
					if (ven.getVendorId().longValue() == transaction.getVendorId().longValue()) {
						tl.add(transaction);
						ven.setTransaction(tl);
					}
				}
				// vendor.add(ven);
			}*/
		} catch (ClassNotFoundException | InstantiationException | IllegalAccessException e) {
			e.printStackTrace();
		}

    	return msg;
	}
	
	@RequestMapping(value = "/{vendorId}", method = RequestMethod.GET, produces="application/json")
	@ResponseBody
	@SuppressWarnings("all")
	public String getTransactionDetails(@PathVariable("vendorId") Long vendorId){
		System.out.println("Entered getTransactionDetail");
		/*List<Transaction> transactionList = new ArrayList<>();
		Class<?> entityClass = null;
		Object entityObj = null;
		String sqlTransaction = "SELECT t.TRANSACTION_ID, t.VENDOR_ID, m.MENU_NAME, m.PRICE, t.USER_ID, t.DATE FROM TRANSACTION t, ORDER_ITEMS o, "
				+ "MENU m where t.transaction_id = o.transaction_id and o.menu_id = m.menu_id and t.vendor_id = ?";
		try{
			entityClass = Class.forName("com.codefest.main.entity.Transaction");
			entityObj = entityClass.newInstance();
			transactionList = jdbcTemplate.query(sqlTransaction, new Object[]{vendorId}, new BeanPropertyRowMapper(entityObj.getClass()));
			
		}catch (ClassNotFoundException | InstantiationException | IllegalAccessException e) {
			e.printStackTrace();
		}*/
		Vendor vendorDB = new Vendor();
		Class<?> entityClass = null;
		Object entityObj = null;
		String sqlVendor = "Select * from vendor where vendor_id = ?";
		// String sqlTransaction = "Select * from Transaction where vendor_id in (:listOfValues)";
		String sqlTransaction = "SELECT t.TRANSACTION_ID, t.VENDOR_ID, m.MENU_NAME, m.PRICE, t.USER_ID, t.DATE FROM TRANSACTION t, ORDER_ITEMS o, "
				+ "MENU m where t.transaction_id = o.transaction_id and o.menu_id = m.menu_id and t.vendor_id = ?";
		/*List<Integer> vendorIds = new ArrayList<>();
		Map<String, Object> params = new HashMap<String, Object>();
		params.put("listOfValues", vendorIds);*/
		String msg=null;
		try {
			entityClass = Class.forName("com.codefest.main.entity.Vendor");
			entityObj = entityClass.newInstance();
			vendorDB = (Vendor) jdbcTemplate.queryForObject(sqlVendor, new Object[]{vendorId}, new BeanPropertyRowMapper(entityObj.getClass()));
			//Long vendorId = vendor.get(0).getVendorId();
			List<Transaction> transactionList = jdbcTemplate.query(sqlTransaction, new Object[] { vendorId },
					new BeanPropertyRowMapper(Transaction.class));

			vendorDB.setTransaction(transactionList);
			ObjectMapper mapper = new ObjectMapper();
			
			try {
				 msg = mapper.writeValueAsString(vendorDB);
			} catch (JsonGenerationException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (JsonMappingException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			/*
			 * List<Transaction> transactionList =
			 * namedParameterJdbcTemplate.query( sqlTransaction, params,
			 * ParameterizedBeanPropertyRowMapper.newInstance(Transaction.class)
			 * );
			 */
			/*List<Transaction> tl = null;
			for (Vendor ven : vendor) {
				tl = new ArrayList<>();
				for (Transaction transaction : transactionList) {
					if (ven.getVendorId().longValue() == transaction.getVendorId().longValue()) {
						tl.add(transaction);
						ven.setTransaction(tl);
					}
				}
				// vendor.add(ven);
			}*/
		} catch (ClassNotFoundException | InstantiationException | IllegalAccessException e) {
			e.printStackTrace();
		}
		return msg;
	}

}
