package com.silvassa.notice.dao;

import java.util.List;

import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.criterion.Restrictions;
import org.json.simple.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.google.gson.Gson;
import com.silvassa.model.NoticeViewBean;
import com.silvassa.model.PropertyDetails;
import com.silvassa.model.TaxNotices;
import com.silvassa.util.MMIUtil;
import java.util.ArrayList;
import org.apache.log4j.Logger;
import org.hibernate.SQLQuery;
import org.springframework.beans.factory.annotation.Qualifier;

@Component("noticeGenerationDAO")
public class NoticeGenerationDAOImpl implements NoticeGenerationDAO {

    private SessionFactory sessionFactory;

    private static final Logger logger = Logger.getLogger(NoticeGenerationDAOImpl.class);

    @Autowired(required = true)
    @Qualifier("sessionHB")
    public void setSessionFactory(SessionFactory sessionFactory) {
        this.sessionFactory = sessionFactory;
    }

    @Override
    public NoticeViewBean getNotice(String taxNo) {

        NoticeViewBean nvb = null;
        Session sessionHB = null;

        try {
            sessionHB = sessionFactory.openSession();
            Criteria criteria = sessionHB.createCriteria(NoticeViewBean.class);
            criteria.add(Restrictions.eq("taxNo", taxNo));

            nvb = (NoticeViewBean) criteria.uniqueResult();

        } catch (Exception e) {
            logger.info(e.getMessage());
        } finally {
            if (sessionHB != null) {
                sessionHB.flush();
                sessionHB.clear();
                sessionHB.close();
            }
        }

        return nvb;
    }

    @Override
    public JSONObject loadtTaxNoticeData(String noticeSel) {

        Session sessionHB = null;
        Criteria criteria = null;
        List<TaxNotices> results = null;
        List<PropertyDetails> propertyAssociated = null;
        List propertyIdAssociated = null;
        JSONObject jObj = null;
        try {
            sessionHB = sessionFactory.openSession();
            criteria = sessionHB.createCriteria(TaxNotices.class);

            propertyAssociated = loadtAssociatedProeperties(noticeSel);

            criteria.add(Restrictions.eq("financialYear", MMIUtil.getCurrentFinancialYear()));

            if (propertyAssociated != null && propertyAssociated.size() > 0) {
                propertyIdAssociated = new ArrayList();
                for (PropertyDetails pd : propertyAssociated) {
                    propertyIdAssociated.add(pd.getPropertyUniqueId());
                }
                criteria.add(Restrictions.in("propertyId", propertyIdAssociated));

            } else {
                jObj = new JSONObject();
                jObj.put("propBeans", null);
                jObj.put("taxBeans", null);
                return jObj;
            }

            results = (List<TaxNotices>) criteria.list();

            jObj = new JSONObject();
            jObj.put("propBeans", propertyAssociated);
            jObj.put("taxBeans", results);

        } catch (Exception e) {
            logger.info(e.getMessage());
        } finally {
            if (sessionHB != null) {
                sessionHB.close();
            }
        }
        return jObj;
    }

    public List loadtAssociatedProeperties(String noticeSel) {

        Session sessionHB = null;
        Criteria criteria = null;
        List<PropertyDetails> results = null;
        try {
            sessionHB = sessionFactory.openSession();
            criteria = sessionHB.createCriteria(PropertyDetails.class);
            JSONObject jo = new Gson().fromJson(noticeSel, JSONObject.class);
//            criteria.setProjection(Projections.projectionList()
//                    .add(Projections.property("propertyUniqueId"))
//                    .add(Projections.property("propertyOwner"))
//                    .add(Projections.property("propertyOccupierName"))
//                    .add(Projections.property("propertyRelationOwner"))
//                    .add(Projections.property("completeAddress"))
//                    .add(Projections.property("zoneId"))
//            );

            if (jo.get("zoneid") != null && !jo.get("zoneid").equals("-1")) {
                criteria.add(Restrictions.eq("zoneId", jo.get("zoneid")));
                if (jo.get("ward") != null && !jo.get("ward").equals("-1")) {
                    criteria.add(Restrictions.eq("ward", jo.get("ward")));
                }
            } else if (!jo.get("prop_id").equals("")) {
                criteria.add(Restrictions.eq("propertyUniqueId", jo.get("prop_id")));
            }
            results = (List<PropertyDetails>) criteria.list();

        } catch (Exception e) {
            logger.info(e.getMessage());
        } finally {
            if (sessionHB != null) {
                sessionHB.close();

            }
        }
        return results;
    }

    public String generateTaxNotice(String params) {
        Session sessionHB = null;
        String status = "fail";
        SQLQuery query = null;
        String sql = null;
        try {

            sql = "INSERT INTO tx_notice_service(params, status) VALUES ('" + params + "', 'P');";
            sessionHB = sessionFactory.openSession();
            query = sessionHB.createSQLQuery(sql);
            query.executeUpdate();
            status = "Success";

        } catch (Exception e) {
            status = "fail";

            logger.info(e.getMessage());
        } finally {
            query = null;
            sessionHB.close();
        }
        return status;
    }

}
