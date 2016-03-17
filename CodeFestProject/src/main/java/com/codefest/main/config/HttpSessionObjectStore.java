package com.codefest.main.config;

import javax.servlet.http.HttpSession;

/**
 * An adapter for the IObjectStore interface using the HttpSession object for
 * storage.
 */
public class HttpSessionObjectStore {
	/**
	 * This is the current session.
	 */
	private static HttpSession httpSession;
	/**
	 * Key under which the ScopusWebRequest object will be stored in the
	 * session.
	 */
	public static String WEB_REQUEST = "codeFest.webRequest";


	/**
	 * Constructor.
	 * 
	 * @param session
	 *            The HttpSession
	 */
	public  HttpSessionObjectStore(HttpSession session) {
		httpSession = session;
	}

	/**
	 * @see IObjectStore#getObject(String)
	 */
	public static Object getObject(String name) {
		Object obj = httpSession.getAttribute(name);
		return obj;
	}

	/**
	 * @see IObjectStore#setObject(String,Object)
	 */
	public static void setObject(String name, Object value) {
		httpSession.setAttribute(name, value);
	}

	/**
	 * @return the webRequest
	 */
	public static String getWebRequest() {
		return WEB_REQUEST;
	}

	/**
	 * @param webRequest the webRequest to set
	 */
	public static void setWebRequest(String webRequest) {
		WEB_REQUEST = webRequest;
	}
	

}
