package com.codefest.main.validator;

import com.codefest.main.vo.HomeVO;

public class LoginScreenValidator {

public boolean processValidation(HomeVO loginCheck){
	if(loginCheck.getUserName().matches("sakthi") && loginCheck.getPassWord().matches("123")){
		return true;
	}else{
		return false;
	}	
}
	
	
}
