package com.silvassa.dao;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpSession;

import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.criterion.Restrictions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Repository;

import com.silvassa.model.LoginUserBean;
import com.silvassa.model.StateBean;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Qualifier;

@Component
@Repository("loginDao")
public class LoginDaoImpl implements LoginDao {

    /**
     * The session.
     */
    @Autowired
    private HttpSession session;
    
    private static final Logger logger = Logger.getLogger(LoginDaoImpl.class);

    /**
     * The session factory.
     */
    
    private SessionFactory sessionFactory;

    @Autowired(required = true)
    @Qualifier("sessionHB")
    public void setSessionFactory(SessionFactory sessionFactory) {
        this.sessionFactory = sessionFactory;
    }

    public LoginUserBean getUserDetails(LoginUserBean bean)
            throws DataAccessException {
        LoginUserBean userDetails = null;
        Session sessionHB = null;
        try {
            sessionHB = sessionFactory.openSession();
            Criteria criteria = sessionHB.createCriteria(LoginUserBean.class);
            criteria.add(Restrictions.eq("username", bean.getUsername()));
            criteria.add(Restrictions.eq("password", bean.getPassword()));
            userDetails = (LoginUserBean) criteria.uniqueResult();
            session.setAttribute("user", userDetails);
            Criteria criteria1 = sessionHB.createCriteria(StateBean.class);
            List<StateBean> stateListBean = criteria1.list();

            Map<String, String> stateListMap = new HashMap<String, String>();
            for (int i = 0; i < stateListBean.size(); i++) {
                stateListMap.put(stateListBean.get(i).getStateCode(), stateListBean.get(i).getStateName());
            }
            session.setAttribute("stateList", stateListMap);
        } catch (Exception e) {
            logger.info(e.getMessage());
        } finally {
            sessionHB.close();
        }

        return userDetails;
    }

    
}
