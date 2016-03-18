package com.codefest.main.controller;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import com.codefest.main.config.HttpSessionObjectStore;
import com.codefest.main.entity.CFUser;
import com.codefest.main.vo.HomeVO;

@Controller
public class HomeController {

	@Autowired
	public JdbcTemplate jdbcTemplate;

	@RequestMapping(value = { "/index", "/" })
	public String welcome() {

		/*
		 * List<?> records = new ArrayList<>(); Class<?> entityClass = null;
		 * Object entityObj = null; String sql = "Select * from CF_USER"; try {
		 * entityClass = Class.forName("com.codefest.main.entity.CFUser");
		 * entityObj = entityClass.newInstance(); records =
		 * jdbcTemplate.query(sql, new
		 * BeanPropertyRowMapper(entityObj.getClass()));
		 * System.out.println("size ******" + records.size()); } catch
		 * (ClassNotFoundException | InstantiationException |
		 * IllegalAccessException e) { e.printStackTrace(); }
		 */
		return "index";
	}

	@SuppressWarnings("unchecked")
	@RequestMapping(value = "/home", method = RequestMethod.POST)
	public String hello(
			@RequestParam(value = "userName", required = true) String userName1,
			@RequestParam(value = "passWord", required = true) String passWord1,
			Model model) {

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
				returnString = "menu";
			}
			HttpSessionObjectStore.setObject("userId", user.get(0).getUserId());
		}
		/*
		 * try { String recipient = "+919940353033"; String message =
		 * " Test SMS through Mobile Success!!!"; String username = "admin";
		 * String password = "abc123"; String originator = "+919952717373";
		 * String route="T"; String requestUrl =
		 * "http://10.242.188.71:9501/api?action=sendmessage&" + "username=" +
		 * URLEncoder.encode(username, "UTF-8") + "&password=" +
		 * URLEncoder.encode(password, "UTF-8") + "&recipient=" +
		 * URLEncoder.encode(recipient, "UTF-8") + "&messagetype=SMS:TEXT" +
		 * "&messagedata=" + URLEncoder.encode(message, "UTF-8") +
		 * "&originator=" + URLEncoder.encode(originator, "UTF-8") +
		 * "&serviceprovider=GSMModem1" + "&responseformat=html"; String
		 * recipient = "+919940353033"; String message =
		 * " Test SMS through Mobile Success!!!"; String username =
		 * "s.sakthivel.pdm@gmail.com"; String password = "Smsc23"; String
		 * originator = "+919952717373"; String route="T"; String
		 * sender_id="SMSIND"; String requestUrl
		 * ="http://smsc.biz/httpapi/send?username="+URLEncoder.encode(username,
		 * "UTF-8")+ "&password="+URLEncoder.encode(password, "UTF-8")+
		 * "&sender_id="+URLEncoder.encode(sender_id, "UTF-8")+
		 * "&route="+URLEncoder.encode(route, "UTF-8")+
		 * "&phonenumber="+URLEncoder.encode(originator, "UTF-8")+
		 * "&message="+URLEncoder.encode(message, "UTF-8"); String requestURL=
		 * "http://smsc.biz/httpapi/send?username=s.sakthivel.pdm@gmail.com&password=Smsc23&sender_id=SMSIND&route=T&phonenumber=9952717373&message=Test%20sms%20from%20s.sakthivel.pdm@gmail.com.%20Thanks%20for%20choosing%20our%20service%20-%20USERNAME%20SERVICE%20-%20SMSC%20Platform"
		 * ; URL url = new URL(requestURL); HttpURLConnection uc =
		 * (HttpURLConnection)url.openConnection();
		 * System.out.println(uc.getResponseMessage()); uc.disconnect(); }
		 * catch(Exception ex) { System.out.println(ex.getMessage()); }
		 * PrintWriter pw= new PrintWriter("ac.txt"); try {
		 * System.out.println("try111******"); final TrustManager[]
		 * trustAllCerts = new TrustManager[] { new X509TrustManager() { public
		 * void checkClientTrusted( final X509Certificate[] chain, final String
		 * authType ) { } public void checkServerTrusted( final
		 * X509Certificate[] chain, final String authType ) { } public
		 * X509Certificate[] getAcceptedIssuers() { return null; } } }; final
		 * SSLContext sslContext = SSLContext.getInstance( "SSL" );
		 * sslContext.init( null, trustAllCerts, new
		 * java.security.SecureRandom() );
		 * 
		 * final SSLSocketFactory sslSocketFactory =
		 * sslContext.getSocketFactory();
		 * HttpsURLConnection.setDefaultSSLSocketFactory(
		 * sslContext.getSocketFactory() ); URL url = new URL(getURLPath());
		 * System.out.println("url   "+url); final HttpURLConnection conn =
		 * (HttpURLConnection) url.openConnection();
		 * 
		 * conn.setRequestMethod("GET"); int responseCode =
		 * conn.getResponseCode();
		 * System.out.println("responecode      "+responseCode);
		 * //System.out.println("Response Code : " + responseCode);
		 * BufferedReader in = new BufferedReader( new
		 * InputStreamReader(conn.getInputStream())); String inputLine;
		 * StringBuffer response = new StringBuffer();
		 * 
		 * while ((inputLine = in.readLine()) != null) {
		 * response.append(inputLine); } in.close();
		 * System.out.println(response.toString());
		 * System.out.println("tryend******"); } catch (MalformedURLException e)
		 * { e.printStackTrace(); } catch (IOException e) { e.printStackTrace();
		 * } catch (NoSuchAlgorithmException e) { // TODO Auto-generated catch
		 * block e.printStackTrace(); } catch (KeyManagementException e) { //
		 * TODO Auto-generated catch block e.printStackTrace(); }
		 */
		return returnString;

	}

	private String getURLPath() {
		String twar = "http://smsc.biz/httpapi/send?username=s.sakthivel.pdm@gmail.com&password=Smsc23&sender_id=SMSIND&route=T&phonenumber=9952717373&message=Test%20sms%20from%20s.sakthivel.pdm@gmail.com.%20Thanks%20for%20choosing%20our%20service%20-%20USERNAME%20SERVICE%20-%20SMSC%20Platform";
		return twar;
	}

	private String getURL() {
		return "http://smsc.biz/";
	}

}
