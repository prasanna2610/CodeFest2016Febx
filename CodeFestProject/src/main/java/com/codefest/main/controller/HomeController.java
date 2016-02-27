package com.codefest.main.controller;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.security.KeyManagementException;
import java.security.NoSuchAlgorithmException;
import java.security.cert.X509Certificate;

import javax.net.ssl.HttpsURLConnection;
import javax.net.ssl.SSLContext;
import javax.net.ssl.SSLSocketFactory;
import javax.net.ssl.TrustManager;
import javax.net.ssl.X509TrustManager;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import com.codefest.main.validator.LoginScreenValidator;
import com.codefest.main.vo.HomeVO;

@Controller
public class HomeController { 
	
	@RequestMapping(value = "/")
	public String welcome() {
	    return "index";
	}
	
    @RequestMapping(value="/home" , method = RequestMethod.POST)
    public String hello(@RequestParam(value="userName", required=true) String userName1 ,@RequestParam(value="passWord", required=true) String passWord1, Model model) throws FileNotFoundException {

    	HomeVO homevoObject=new HomeVO();
        homevoObject.setUserName(userName1);
        homevoObject.setPassWord(passWord1);
        LoginScreenValidator loginValidation= new LoginScreenValidator();
        boolean validationStatus=loginValidation.processValidation(homevoObject);
       /* try {
        	String recipient = "+919940353033";
        	String message = " Test SMS through Mobile Success!!!";
        	String username = "admin";
        	String password = "abc123";
        	String originator = "+919952717373";
        	String route="T";
        	String requestUrl  = "http://10.242.188.71:9501/api?action=sendmessage&" +        	
        	 "username=" + URLEncoder.encode(username, "UTF-8") +
        	 "&password=" + URLEncoder.encode(password, "UTF-8") +
        	 "&recipient=" + URLEncoder.encode(recipient, "UTF-8") +
        	 "&messagetype=SMS:TEXT" +
        	 "&messagedata=" + URLEncoder.encode(message, "UTF-8") +
        	 "&originator=" + URLEncoder.encode(originator, "UTF-8") +
        	 "&serviceprovider=GSMModem1" +
        	 "&responseformat=html";
        	String recipient = "+919940353033";
        	String message = " Test SMS through Mobile Success!!!";
        	String username = "s.sakthivel.pdm@gmail.com";
        	String password = "Smsc23";
        	String originator = "+919952717373";
        	String route="T";
        	String sender_id="SMSIND";
        	String requestUrl  ="http://smsc.biz/httpapi/send?username="+URLEncoder.encode(username, "UTF-8")+
        			"&password="+URLEncoder.encode(password, "UTF-8")+
        			"&sender_id="+URLEncoder.encode(sender_id, "UTF-8")+
        			"&route="+URLEncoder.encode(route, "UTF-8")+
        			"&phonenumber="+URLEncoder.encode(originator, "UTF-8")+
        			"&message="+URLEncoder.encode(message, "UTF-8");
        	String requestURL="http://smsc.biz/httpapi/send?username=s.sakthivel.pdm@gmail.com&password=Smsc23&sender_id=SMSIND&route=T&phonenumber=9952717373&message=Test%20sms%20from%20s.sakthivel.pdm@gmail.com.%20Thanks%20for%20choosing%20our%20service%20-%20USERNAME%20SERVICE%20-%20SMSC%20Platform";
        	URL url = new URL(requestURL);
        	HttpURLConnection uc = (HttpURLConnection)url.openConnection();
        	System.out.println(uc.getResponseMessage());
        	uc.disconnect();
        	} catch(Exception ex) {
        	System.out.println(ex.getMessage());
        	}
        PrintWriter pw= new PrintWriter("ac.txt");
        try {
        	System.out.println("try111******");
			final TrustManager[] trustAllCerts = new TrustManager[] { 
				new X509TrustManager() 
				{
					public void checkClientTrusted( final X509Certificate[] chain, final String authType ) {
					}
					public void checkServerTrusted( final X509Certificate[] chain, final String authType ) {
					}
					public X509Certificate[] getAcceptedIssuers() {
						return null;
					}
				} };
			final SSLContext sslContext = SSLContext.getInstance( "SSL" );
			sslContext.init( null, trustAllCerts, new java.security.SecureRandom() );

			final SSLSocketFactory sslSocketFactory = sslContext.getSocketFactory();
			HttpsURLConnection.setDefaultSSLSocketFactory( sslContext.getSocketFactory() );
			URL url = new URL(getURLPath());
			System.out.println("url   "+url);
			final HttpURLConnection conn = (HttpURLConnection) url.openConnection();

			conn.setRequestMethod("GET");
			int responseCode = conn.getResponseCode();
			System.out.println("responecode      "+responseCode);
			//System.out.println("Response Code : " + responseCode);
			BufferedReader in = new BufferedReader(
			        new InputStreamReader(conn.getInputStream()));
			String inputLine;
			StringBuffer response = new StringBuffer();
	 
			while ((inputLine = in.readLine()) != null) {
				response.append(inputLine);
			}
			in.close();
			System.out.println(response.toString());
			System.out.println("tryend******");
		} catch (MalformedURLException e) {
			e.printStackTrace();
		}
		catch (IOException e) {
			e.printStackTrace();
		} catch (NoSuchAlgorithmException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (KeyManagementException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}*/
        
        if(validationStatus==true){
        	model.addAttribute("userInfo", "Welcome "+homevoObject.getUserName());
        	return "userHomeScreen";
        }else{
        	System.out.println("test error");
        	model.addAttribute("errorMsg", "Incorrect Username and Password");
        	return "index";
        }
    }

	private String getURLPath() {
		System.out.println("path******");
		String twar = "http://smsc.biz/httpapi/send?username=s.sakthivel.pdm@gmail.com&password=Smsc23&sender_id=SMSIND&route=T&phonenumber=9952717373&message=Test%20sms%20from%20s.sakthivel.pdm@gmail.com.%20Thanks%20for%20choosing%20our%20service%20-%20USERNAME%20SERVICE%20-%20SMSC%20Platform";
		return twar;
	}

	private String getURL() {
		System.out.println("url******");
		return "http://smsc.biz/";
}

}
