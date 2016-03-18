package com.codefest.main.controller;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.security.KeyManagementException;
import java.security.NoSuchAlgorithmException;
import java.security.cert.X509Certificate;
import java.util.ArrayList;
import java.util.List;

import javax.net.ssl.HttpsURLConnection;
import javax.net.ssl.SSLContext;
import javax.net.ssl.SSLSocketFactory;
import javax.net.ssl.TrustManager;
import javax.net.ssl.X509TrustManager;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.codefest.main.config.HttpSessionObjectStore;
import com.codefest.main.entity.CFUser;

@Controller
public class SendSMSController {
	
	@Autowired
	public JdbcTemplate jdbcTemplate;
	
	
	@RequestMapping(value = "/sendSMS", method = RequestMethod.POST)
	public String sendSMS(Model model) {
		System.out.println("Entering sendSMS");
		List<CFUser> user = null;
		Long UserId = (Long)HttpSessionObjectStore.getObject("userId");
		String userName = null;
		int TransactionId = 0;
		try {
			Class<?> entityClass = null;
			Object entityObj = null;
			String sqlVendor = "SELECT * FROM cf_user where user_id = ?";
			entityClass = Class.forName("com.codefest.main.entity.CFUser");
			entityObj = entityClass.newInstance();
			user = (ArrayList<CFUser>) jdbcTemplate.query(sqlVendor,
					new Object[] { UserId }, new BeanPropertyRowMapper(
							entityObj.getClass()));
			userName = user.get(0).getFirstName();
		} catch (Exception e) {
			e.printStackTrace();
		}
		sendMessage(userName);
		return "/menu";
	}

	private void sendMessage(String userName) {

		try {

			final TrustManager[] trustAllCerts = new TrustManager[] { new X509TrustManager() {
				@Override
				public void checkClientTrusted(final X509Certificate[] chain,
						final String authType) {
				}

				@Override
				public void checkServerTrusted(final X509Certificate[] chain,
						final String authType) {
				}

				@Override
				public X509Certificate[] getAcceptedIssuers() {
					return null;
				}
			} };

			final SSLContext sslContext = SSLContext.getInstance("SSL");
			sslContext.init(null, trustAllCerts,
					new java.security.SecureRandom());

			final SSLSocketFactory sslSocketFactory = sslContext
					.getSocketFactory();
			HttpsURLConnection.setDefaultSSLSocketFactory(sslContext
					.getSocketFactory());
			URL url = new URL(getURLPath(userName));
			final HttpURLConnection conn = (HttpURLConnection) url
					.openConnection();

			conn.setRequestMethod("GET");
			int responseCode = conn.getResponseCode();
			// System.out.println("Response Code : " + responseCode);
			BufferedReader in = new BufferedReader(new InputStreamReader(
					conn.getInputStream()));
			String inputLine;
			StringBuffer response = new StringBuffer();

			while ((inputLine = in.readLine()) != null) {
				response.append(inputLine);
			}
			in.close();
			System.out.println(response.toString());

		} catch (MalformedURLException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		} catch (NoSuchAlgorithmException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (KeyManagementException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}

	private String getURLPath(String userName) {
		String twar = getURL()
		+"httpapi/send?username=santhia.rajendran@gmail.com&password=indian42&sender_id=PROMOTIONAL&route=P&phonenumber=9952717373&message=Hi%20" +
		userName+"," 
		+ "Your%20Transaction%20is%20Success%20Your%20Order%20Number%20123456%20Thanks%20For%20Using%20BOOK%20YOUR%20MEAL";
		return twar;
	}

	private String getURL() {

		return "http://smsc.biz/";
	}

}
