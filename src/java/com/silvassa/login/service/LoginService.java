package com.silvassa.login.service;

import java.util.ArrayList;
import java.io.IOException;
import com.silvassa.bean.LoginDetailsBean;
import com.silvassa.model.LoginForm;
import com.silvassa.model.LoginUserBean;




public interface LoginService {

	LoginUserBean verifyUsernamePassword(LoginForm form);

	public LoginDetailsBean loggedinUser(LoginDetailsBean loginDetailsBean);
	
	boolean logoutUser();

	ArrayList<String> getPropertyImage(String property_unique_id) throws IOException;
    
		
}
