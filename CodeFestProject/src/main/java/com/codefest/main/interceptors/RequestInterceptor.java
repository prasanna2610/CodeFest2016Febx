package com.codefest.main.interceptors;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.stereotype.Component;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

import com.codefest.main.config.HttpSessionObjectStore;
import com.codefest.main.config.ObjectStoreManager;

@Component
public class RequestInterceptor extends HandlerInterceptorAdapter {

	@Override
	public boolean preHandle(HttpServletRequest request,
			HttpServletResponse response, Object handler) throws Exception {
		HttpSession httpSession = request.getSession();
		System.out.println("sessionId" + httpSession.getId());
		ObjectStoreManager.getInstance().setSessionLevelObjectStore(
				new HttpSessionObjectStore(httpSession));
		HttpSessionObjectStore.setObject("sessionId", request.getSession());
		
		if(HttpSessionObjectStore.getObject("userId") ==null){
			System.out.println("Not authenticated");
		}
		
		return true;
	}

}