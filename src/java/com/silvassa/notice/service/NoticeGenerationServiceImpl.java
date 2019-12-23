/**
 *
 */
package com.silvassa.notice.service;

import com.silvassa.login.service.LoginServiceImp;
import javax.servlet.http.HttpSession;

import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.criterion.Restrictions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.ModelAndView;

import com.silvassa.model.Generatedtax;
import com.silvassa.model.NoticeViewBean;
import com.silvassa.notice.dao.NoticeGenerationDAO;
import org.apache.log4j.Logger;
import org.json.simple.JSONObject;

/**
 * @author ce
 *
 */
@Component("noticeGenerationService")
public class NoticeGenerationServiceImpl implements NoticeGenerationService {

    @Autowired
    private HttpSession session;

    /**
     * The session factory.
     */
    private SessionFactory sessionFactory;
    private static final Logger logger = Logger.getLogger(NoticeGenerationServiceImpl.class);

    /**
     * Sets the session factory.
     *
     * @param sessionFactory the new session factory
     */
    @Autowired(required = true)
    @Qualifier("sessionHB")
    public void setSessionFactory(SessionFactory sessionFactory) {
        this.sessionFactory = sessionFactory;
    }

    @Autowired
    @Qualifier("noticeGenerationDAO")
    NoticeGenerationDAO noticeGenerationDAO;

    @Override
    public void getData(String zone_id, String ward_id, String loc_id,
            String prop_id, String type) {
        Session sessionHB = null;

        logger.info("zone->" + zone_id + " ward-->" + ward_id + " loc-> " + loc_id + " prop id->" + prop_id + " type->" + type);

        try {
            sessionHB = sessionFactory.openSession();
            Criteria criteria1 = sessionHB.createCriteria(Generatedtax.class);
            criteria1.add(Restrictions.eq("zoneId", "1"));

        } catch (Exception e) {

            logger.info(e.getMessage());
        }

    }

    @Override
    public ModelAndView viewNotice(String taxNo) {

        ModelAndView mv = null;

        try {

            NoticeViewBean nvb = noticeGenerationDAO.getNotice(taxNo);

            if (nvb == null) {
                mv = new ModelAndView("notice");
            } else {
                mv = new ModelAndView("pdfViewBill", "data", nvb);
            }

        } catch (Exception ex) {
            logger.info(ex.getMessage());
        } finally {

        }

        return mv;

    }

    @Override
    public JSONObject loadtTaxNoticeData(String noticeSel) {

        return noticeGenerationDAO.loadtTaxNoticeData(noticeSel);
    }

    @Override
    public String generateTaxNotice(String params) {
        return noticeGenerationDAO.generateTaxNotice(params);
    }

}
