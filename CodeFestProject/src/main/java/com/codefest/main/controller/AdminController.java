package com.codefest.main.controller;

import java.io.IOException;
import java.math.BigInteger;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.codehaus.jackson.JsonGenerationException;
import org.codehaus.jackson.map.JsonMappingException;
import org.codehaus.jackson.map.ObjectMapper;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
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
	
	/*@Autowired
	public JSONObject jsonbObj;*/
	
	@RequestMapping(method = RequestMethod.GET, produces="application/json")
	@ResponseBody
	@SuppressWarnings("all")
	public String getAdminPage(Model model) {
		System.out.println("Entered getAdminPage");
		List<Vendor> vendor = new ArrayList<>();
		Class<?> entityClass = null;
		Object entityObj = null;
		String sqlVendor = "Select * from VENDOR;";
		String sqlTransaction = "SELECT t.TRANSACTION_ID, t.VENDOR_ID, m.MENU_NAME, m.PRICE, t.USER_ID, t.DATE FROM TRANSACTION t, ORDER_ITEMS o, "
				+ "MENU m where t.transaction_id = o.transaction_id and o.menu_id = m.menu_id and t.vendor_id = ?";
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
				e.printStackTrace();
			} catch (JsonMappingException e) {
				e.printStackTrace();
			} catch (IOException e) {
				e.printStackTrace();
			}
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
		Vendor vendorDB = new Vendor();
		Class<?> entityClass = null;
		Object entityObj = null;
		String sqlVendor = "Select * from vendor where vendor_id = ?";
		String sqlTransaction = "SELECT t.TRANSACTION_ID, t.VENDOR_ID, m.MENU_NAME, m.PRICE, t.USER_ID, t.DATE FROM TRANSACTION t, ORDER_ITEMS o, "
				+ "MENU m where t.transaction_id = o.transaction_id and o.menu_id = m.menu_id and t.vendor_id = ?";
		String msg=null;
		try {
			entityClass = Class.forName("com.codefest.main.entity.Vendor");
			entityObj = entityClass.newInstance();
			vendorDB = (Vendor) jdbcTemplate.queryForObject(sqlVendor, new Object[]{vendorId}, new BeanPropertyRowMapper(entityObj.getClass()));
			List<Transaction> transactionList = jdbcTemplate.query(sqlTransaction, new Object[] { vendorId },
					new BeanPropertyRowMapper(Transaction.class));

			vendorDB.setTransaction(transactionList);
			ObjectMapper mapper = new ObjectMapper();
			
			try {
				 msg = mapper.writeValueAsString(vendorDB);
			} catch (JsonGenerationException e) {
				e.printStackTrace();
			} catch (JsonMappingException e) {
				e.printStackTrace();
			} catch (IOException e) {
				e.printStackTrace();
			}
		} catch (ClassNotFoundException | InstantiationException | IllegalAccessException e) {
			e.printStackTrace();
		}
		return msg;
	}
	
	@RequestMapping(value = "/edit/{vendorId}",method = RequestMethod.POST,consumes="application/json")
	@ResponseBody
	@SuppressWarnings("all")
	public String updateVendor(@PathVariable("vendorId") Long vendorId, @RequestBody String jsonPostData,HttpServletRequest request ) throws Exception{
		JSONObject jsonDta=new JSONObject(jsonPostData);
		System.out.println(jsonDta.get("VendorName"));
		Long vendorId1 = null;
		Vendor vendorvo=new Vendor();
		String sqlVendor = "Select * from vendor where vendor_id = ?";
		Vendor vendorDB = new Vendor();
		Class<?> entityClass = null;
		Object entityObj = null;
		try{
			entityClass = Class.forName("com.codefest.main.entity.Vendor");
			entityObj = entityClass.newInstance();
			vendorvo.setVendorId(Long.valueOf((String) jsonDta.get("VendorId")));
			vendorvo.setVendorPhone(Long.valueOf((String) jsonDta.get("ContactNumber")));
			System.out.println("id    "+vendorvo.getVendorId()+"    phone    "+vendorvo.getVendorPhone());
			String sqlUpdateVendor = "UPDATE Vendor set vendor_name=?, vendor_email = ?, vendor_phone = ?, incharge = ?, vendor_detail = ? where vendor_id=?";
			jdbcTemplate.update(sqlUpdateVendor,jsonDta.get("VendorName"), jsonDta.get("Email"), vendorvo.getVendorPhone(),
					jsonDta.get("Incharge"), jsonDta.get("Details"),vendorvo.getVendorId());
		}catch (ClassNotFoundException | InstantiationException | IllegalAccessException | EmptyResultDataAccessException e) {
			e.printStackTrace();
		}
		//handle model objects
		return "success";
	}
	
	@RequestMapping(value="/create", method = RequestMethod.POST, produces="application/json")

	@SuppressWarnings("all")
	@ResponseBody
	public String addMenu(@RequestParam(value="vendorName", required=true) String vendorName, 
			@RequestParam(value="inCharge", required=true) String inCharge,
			@RequestParam(value="passwordF", required=true) String passwordF,
			@RequestParam(value="venDetails", required=true) String venDetails,
			@RequestParam(value="email", required=true) String email,
			@RequestParam(value="mobileNumber", required=true) Long mobileNumber
			) throws InstantiationException, IllegalAccessException{
		Class<?> entityClass = null;
		Object entityObj = null;
		try{
			entityClass = Class.forName("com.codefest.main.entity.Vendor");
			entityObj = entityClass.newInstance();
			String INSERT_VENDOR_SQL = "INSERT INTO  VENDOR(VENDOR_ID, VENDOR_NAME,PASSWORD,VENDOR_EMAIL,VENDOR_PHONE,INCHARGE,VENDOR_DETAIL) VALUES (nextval('vendor_seq'),?,?,?,?,?,? )";
			jdbcTemplate.update(INSERT_VENDOR_SQL,
					new Object[] {vendorName, passwordF,email,mobileNumber,inCharge,venDetails});
			Vendor vendor=new Vendor();
			String sqlVendor = "Select VENDOR_ID from vendor where VENDOR_PHONE=? ;";
			vendor = (Vendor) jdbcTemplate.queryForObject(sqlVendor, new Object[]{mobileNumber}, new BeanPropertyRowMapper(entityObj.getClass()));
			System.out.println(vendor.getVendorId());
			String INSERT_USER_SQL = "INSERT INTO  CF_USER(USER_ID, PASSWORD,EMAIL,PHONE,FIRST_NAME,LAST_NAME,USER_TYPE) VALUES (?,?,?,?,?,?,? )";
			jdbcTemplate.update(INSERT_USER_SQL,
					new Object[] {vendor.getVendorId(), passwordF,email,mobileNumber,vendorName,vendorName,"Vendor"});
			
			System.out.println("sucess");
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		}
		
		return null;
	}
	
	private void populateVendor(Vendor vendorUI,Vendor vendorDB){
		if(vendorUI.getVendorName() != null){
			vendorDB.setVendorName(vendorUI.getVendorName());
		}
		if(vendorUI.getVendorEmail() != null){
			vendorDB.setVendorEmail(vendorUI.getVendorEmail());
		}
		if(vendorUI.getVendorDetail() != null){
			vendorDB.setVendorDetail(vendorUI.getVendorDetail());
		}
		if(vendorUI.getVendorPhone() != null){
			vendorDB.setVendorPhone(vendorUI.getVendorPhone());
		}
		if(vendorUI.getIncharge() != null){
			vendorDB.setIncharge(vendorUI.getIncharge());
		}
	}

}
