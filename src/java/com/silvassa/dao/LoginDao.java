package com.silvassa.dao;

import org.springframework.dao.DataAccessException;

import com.silvassa.model.LoginUserBean;

public interface LoginDao {

    LoginUserBean getUserDetails(LoginUserBean bean) throws DataAccessException;

    
}
