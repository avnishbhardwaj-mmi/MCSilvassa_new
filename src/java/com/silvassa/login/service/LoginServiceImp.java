package com.silvassa.login.service;

import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.StringTokenizer;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.apache.commons.codec.binary.Base64;
import org.hibernate.Criteria;
import org.hibernate.SQLQuery;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.silvassa.bean.LoginDetailsBean;
import com.silvassa.dao.LoginDao;
import com.silvassa.util.MmiPathController;
import com.silvassa.model.LoginForm;
import com.silvassa.model.LoginUserBean;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Qualifier;

@Component
@Service("loginService")
public class LoginServiceImp implements LoginService {

    /**
     * The session.
     */
    @Autowired
    private HttpSession session;

    private static final Logger logger = Logger.getLogger(LoginServiceImp.class);

    @Autowired
    private LoginDao loginDao;

    @Autowired
    @Qualifier("sessionHB")
    private SessionFactory sessionFactory;

    @Transactional(readOnly = true)
    public LoginUserBean verifyUsernamePassword(LoginForm form) {
        // TODO Auto-generated method stub
        LoginUserBean loginUser = null;
        try {
            if (form != null) {
                loginUser = addDetails(form);
            }
            if (loginUser != null) {
                loginUser = loginDao.getUserDetails(loginUser);
            }
        } catch (Exception e) {
            logger.info(e.getMessage());
        }
        return loginUser;
    }

    /**
     * Adds the details.
     *
     * @param loginForm the login form
     * @return the login user bean
     */
    public LoginUserBean addDetails(LoginForm loginForm) {
        LoginUserBean bean = new LoginUserBean();
        bean.setUsername(loginForm.getUsername());
        bean.setPassword(loginForm.getPassword());
        return bean;
    }

    public boolean loggedinUser(HttpServletRequest request) {
        if (null != session.getAttribute("user")) {
            return true;
        }
        return false;

        // boolean flag = false;
        // session = request.getSession(false);
        // boolean loggedIn = session != null
        // && session.getAttribute("user") != null;
        // if (loggedIn)
        // flag = true;
        //
        // return flag;
    }

    @Override
    public LoginDetailsBean loggedinUser(LoginDetailsBean loginDetailsBean) {

        LoginDetailsBean loginDetails = null;
        List<String> loginUser = null;
        List<String> roleList = null;
        Session sessionHB = null;
        Map row = null;
        Map<String, String> userPermissionMap = new HashMap<String, String>();

        try {
            String sql = "select user_id,username,role_id from Usermaster where lower(user_id)=lower('"
                    + loginDetailsBean.getUserId()
                    + "') and pasword=('"
                    + loginDetailsBean.getUserPassword() + "') and status='Y'";
            System.out.println("sql : " + sql);
            sessionHB = sessionFactory.openSession();
            SQLQuery query = sessionHB.createSQLQuery(sql);
            query.setResultTransformer(Criteria.ALIAS_TO_ENTITY_MAP);
            loginUser = query.list();
            if (loginUser.size() > 0) {
                loginDetails = new LoginDetailsBean();
                for (Object object : loginUser) {
                    row = (Map) object;
                    loginDetails.setUserId(row.get("user_id").toString());
                    loginDetails.setUserName(row.get("username").toString());
                    loginDetails.setRoleId(row.get("role_id").toString());
                }
                row = null;
                String sqlRole = "select permission_id,menu_id from permission_master where permission_id in "
                        + " (select permission_id from user_permission where user_role_id='"
                        + loginDetails.getRoleId() + "' and active='Y')";
                SQLQuery qryRole = sessionHB.createSQLQuery(sqlRole);
                qryRole.setResultTransformer(Criteria.ALIAS_TO_ENTITY_MAP);
                roleList = qryRole.list();
                for (Object object : roleList) {
                    row = (Map) object;
                    userPermissionMap.put(row.get("menu_id").toString(), row
                            .get("permission_id").toString());
                }
                loginDetails.setUserPermissionMap(userPermissionMap);

            } else {
                loginDetails = null;
            }
        } catch (Exception ex) {
            loginDetails = null;
            ex.printStackTrace();
        } finally {
            roleList = null;
            row = null;
            userPermissionMap = null;
            sessionHB.close();
        }
        return loginDetails;
    }

    public boolean logoutUser() {
        logger.info("Entering for logout user");
        boolean flag = false;
        try {
            session.invalidate();
            flag = true;
        } catch (Exception ex) {

            logger.info("User logout Exception : " + ex.getMessage());
            flag = false;
        }

        return flag;

    }

    @Override
    public ArrayList<String> getPropertyImage(String prop_unique_id) throws IOException {

        String propUniqueId = "";
        Session sessionHB = null;
        List<String> imageList = null;
        ArrayList<String> photoList = new ArrayList<String>();
        ArrayList<String> encodedPhotoList = new ArrayList<String>();
        try {

            String sql = "select property_photo_id from property_details where property_unique_id='" + prop_unique_id + "'";

            sessionHB = sessionFactory.openSession();
            SQLQuery query = sessionHB.createSQLQuery(sql);
            query.setResultTransformer(Criteria.ALIAS_TO_ENTITY_MAP);
            imageList = query.list();
            for (Object object : imageList) {
                Map row = (Map) object;
                propUniqueId = (String) row.get("property_photo_id");
            }
            StringTokenizer st = new StringTokenizer(propUniqueId, "/");
            while (st.hasMoreElements()) {
                photoList.add(st.nextToken());
            }

            for (String photoId : photoList) {

                String encodedString = "";
                String path = MmiPathController.getDataPath("silvassa.property.image");
                File file = new File(path + "/" + photoId);
                try {
                    FileInputStream fis = new FileInputStream(file);
                    ByteArrayOutputStream bos = new ByteArrayOutputStream();
                    int b;
                    byte[] buffer = new byte[1024];
                    while ((b = fis.read(buffer)) != -1) {
                        bos.write(buffer, 0, b);
                    }
                    byte[] fileBytes = bos.toByteArray();
                    byte[] encoded = Base64.encodeBase64(fileBytes);
                    encodedString = new String(encoded);
                    encodedPhotoList.add(encodedString);
                    fis.close();
                    bos.close();
                } catch (IOException ex) {
                    logger.info(ex.getMessage());
                }
            }
        } catch (Exception e) {
            logger.info(e.getMessage());
        } finally {
            sessionHB.close();
        }

        return encodedPhotoList;
    }

}
