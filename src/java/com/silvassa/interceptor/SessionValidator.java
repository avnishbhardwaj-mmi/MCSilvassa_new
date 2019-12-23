package com.silvassa.interceptor;

/**
 *
 * @author mmi
 */
import com.silvassa.model.LoginUserBean;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.stereotype.Component;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

@Component
public class SessionValidator extends HandlerInterceptorAdapter {

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {

//        String uri = request.getRequestURI();
//        if (!uri.endsWith("login") && !uri.endsWith("welcome") && !uri.endsWith("")) {
//            LoginUserBean userData = (LoginUserBean) request.getSession().getAttribute("userDetailsBean");
//            
//            if (userData == null) {
//                response.sendRedirect("");
//                return false;
//            }
//        }
        return true;
    }
}
