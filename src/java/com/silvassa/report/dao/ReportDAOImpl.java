/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.silvassa.report.dao;

import com.silvassa.bean.ReportQuery;
import com.silvassa.model.ObjectionBean;
import com.silvassa.model.ObjectionTx;
import com.silvassa.model.PaymentBean;
import com.silvassa.model.PropertyDetails;
import com.silvassa.model.PropertyEditableFields;
import com.silvassa.model.TAXDetailBean;
import com.silvassa.model.TaxNotices;
import com.silvassa.report.service.ReportServiceImpl;
import com.silvassa.util.MMIUtil;
import java.math.BigDecimal;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.Set;
import org.apache.log4j.Logger;
import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.criterion.Restrictions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Component;

/**
 *
 * @author CEINFO
 */
@Component("reportDAO")
public class ReportDAOImpl implements ReportDAO { 

    private SessionFactory sessionFactory;

    private static final Logger logger = Logger.getLogger(ReportServiceImpl.class);

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

    @Override
    public List<PropertyDetails> showPropertyDetails(ReportQuery reportQuery) {
        Session sessionHB = null;
        Criteria criteria = null;
        List results = null;

        try {

            logger.info(reportQuery.toString());
            sessionHB = sessionFactory.openSession();
            criteria = sessionHB.createCriteria(PropertyDetails.class);
            criteria.add(Restrictions.eq("zoneId", reportQuery.getZone()));

            if (reportQuery.getPropId() != null && !reportQuery.getPropId().equalsIgnoreCase("-1") && !reportQuery.getPropId().equalsIgnoreCase("")) {
                criteria.add(Restrictions.eq("propertyUniqueId", reportQuery.getPropId()));
            }
            if (reportQuery.getPAN() != null && !reportQuery.getPAN().equalsIgnoreCase("-1") && !reportQuery.getPAN().equalsIgnoreCase("")) {
                //criteria.add(Restrictions.eq("propertyUniqueId", reportQuery.getPAN()));
            }
            if (reportQuery.getADHAAR() != null && !reportQuery.getADHAAR().equalsIgnoreCase("-1") && !reportQuery.getADHAAR().equalsIgnoreCase("")) {
                criteria.add(Restrictions.eq("propertyAadharNum", reportQuery.getADHAAR()));
            }
            if (reportQuery.getPropType() != null && !reportQuery.getPropType().equalsIgnoreCase("-1") && !reportQuery.getPropType().equalsIgnoreCase("")) {
                //criteria.add(Restrictions.eq("propertyAadharNum", reportQuery.getPropType()));
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

    @Override
    public List<TAXDetailBean> searchTAXAmount(ReportQuery reportQuery, List<String> propList) {
        List<TAXDetailBean> ptlist = null;
        Session sessionHB = null;
        Criteria criteria = null;

        try {

            if (propList == null || propList.size() < 1) {
                return ptlist;
            }

            Date dFrom = MMIUtil.convertStringToDate(reportQuery.getObjectionFrom());
            Date dTo = MMIUtil.convertStringToDate(reportQuery.getObjectionTo());

            sessionHB = sessionFactory.openSession();
            criteria = sessionHB.createCriteria(TAXDetailBean.class);
            criteria.add(Restrictions.in("propertyId", propList));
            if (dTo != null && dFrom != null) {
                Calendar c = Calendar.getInstance();
                c.setTime(dTo);
                c.add(Calendar.DATE, 1);
                dTo = c.getTime();
                criteria.add(Restrictions.between("generatedOn", dFrom, dTo));
            }

            if (reportQuery.getMinTaxAmount() != null && !reportQuery.getMinTaxAmount().equals("")
                    && (reportQuery.getMaxTaxAmount() != null && !reportQuery.getMaxTaxAmount().equals(""))) {

                criteria.add(Restrictions.between("grandTotalAsInt",
                        BigDecimal.valueOf(Double.valueOf(reportQuery.getMinTaxAmount())),
                        BigDecimal.valueOf(Double.valueOf(reportQuery.getMaxTaxAmount()))));
            }

            ptlist = criteria.list();

        } catch (Exception e) {
            logger.info(e.getMessage());

        } finally {
            if (sessionHB != null) {
                sessionHB.flush();
                sessionHB.close();
            }
        }
        return ptlist;

    }

    @Override
    public List<TAXDetailBean> filterPendingPaymentCases(ReportQuery reportQuery) {

        List<TAXDetailBean> ptlist = null;
        Session sessionHB = null;
        Criteria criteria = null;

        try {

            sessionHB = sessionFactory.openSession();
            criteria = sessionHB.createCriteria(TAXDetailBean.class);
            criteria.add(Restrictions.gt("grandTotal", "0"));
            criteria.add(Restrictions.eq("financialYear", MMIUtil.getCurrentFinancialYear()));
            criteria.add(Restrictions.lt("dueDate", MMIUtil.getCurrentTimestamp()));
            ptlist = criteria.list();

        } catch (Exception e) {
            logger.info(e.getMessage());

        } finally {
            if (sessionHB != null) {
                sessionHB.flush();
                sessionHB.close();
            }
        }
        return ptlist;
    }

    @Override
    public List<PropertyDetails> getPropertyDetails(ReportQuery reportQuery, List<String> propertyIds) {
        Session sessionHB = null;
        Criteria criteria = null;
        List<PropertyDetails> results = null;

        try {
            sessionHB = sessionFactory.openSession();
            criteria = sessionHB.createCriteria(PropertyDetails.class);
            criteria.add(Restrictions.eq("zoneId", reportQuery.getZone()));
            criteria.add(Restrictions.in("propertyUniqueId", propertyIds));
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

    @Override
    public List<ObjectionTx> showObjectionDetails(ReportQuery reportQuery) {
        Session sessionHB = null;
        Criteria criteria = null;
        List<ObjectionTx> results = null;
        try {
            sessionHB = sessionFactory.openSession();
            criteria = sessionHB.createCriteria(ObjectionTx.class);
            if (reportQuery.getObjectionFrom() != null && !reportQuery.getObjectionFrom().equalsIgnoreCase("")) {
                criteria.add(Restrictions.ge("entrydatetime", MMIUtil.getFormattedDateTimStamp(reportQuery.getObjectionFrom())));
            }
            if (reportQuery.getObjectionTo() != null && !reportQuery.getObjectionTo().equalsIgnoreCase("")) {
                criteria.add(Restrictions.le("entrydatetime", MMIUtil.getFormattedDateTimStamp(reportQuery.getObjectionTo())));
            }
            if (reportQuery.getObjectionStatus() != null && !reportQuery.getObjectionStatus().equalsIgnoreCase("") && !reportQuery.getObjectionStatus().equalsIgnoreCase("-1")) {
                criteria.add(Restrictions.eq("status", reportQuery.getObjectionStatus()));
            }

            if (reportQuery.getPropId() != null && !reportQuery.getPropId().equalsIgnoreCase("") && !reportQuery.getPropId().equalsIgnoreCase("-1")) {
                criteria.add(Restrictions.eq("propertyId", reportQuery.getPropId()));
            }

            results = (List<ObjectionTx>) criteria.list();
        } catch (Exception e) {
            logger.info(e.getMessage());
        } finally {
            if (sessionHB != null) {
                sessionHB.close();
            }
        }
        return results;
    }


    @Override
    public List<TAXDetailBean> filterPendingArrearCases(ReportQuery reportQuery) {

        List<TAXDetailBean> ptlist = null;
        Session sessionHB = null;
        Criteria criteria = null;

        try {

            sessionHB = sessionFactory.openSession();
            criteria = sessionHB.createCriteria(TAXDetailBean.class);
            criteria.add(Restrictions.gt("arrearAmount", "0"));
            criteria.add(Restrictions.eq("financialYear", MMIUtil.getCurrentFinancialYear()));
            //criteria.add(Restrictions.lt("dueDate", MMIUtil.getCurrentTimestamp()));
            ptlist = criteria.list();

        } catch (Exception e) {
            logger.info(e.getMessage());

        } finally {
            if (sessionHB != null) {
                sessionHB.flush();
                sessionHB.close();
            }
        }
        return ptlist;
    }

    @Override
    public List<TaxNotices> loadtTaxNoticeData(ReportQuery reportQuery, List<String> propertyIds) {

        Session sessionHB = null;
        Criteria criteria = null;
        List<TaxNotices> results = null;

        try {

            Date dFrom = MMIUtil.convertStringToDate(reportQuery.getObjectionFrom());
            Date dTo = MMIUtil.convertStringToDate(reportQuery.getObjectionTo());

            sessionHB = sessionFactory.openSession();
            criteria = sessionHB.createCriteria(TaxNotices.class);
            criteria.add(Restrictions.eq("financialYear", MMIUtil.getCurrentFinancialYear()));
            criteria.add(Restrictions.in("propertyId", propertyIds));
            if (dTo != null && dFrom != null) {
                Calendar c = Calendar.getInstance();
                c.setTime(dTo);
                c.add(Calendar.DATE, 1);
                dTo = c.getTime();
                criteria.add(Restrictions.between("noticeGenDate", dFrom, dTo));
            }

            results = (List<TaxNotices>) criteria.list();

        } catch (Exception e) {
            logger.info(e.getMessage());
        } finally {
            if (sessionHB != null) {
                sessionHB.close();
            }
        }
        return results;
    }

    @Override
    public List<PropertyDetails> getPropDetailsForReceipt(ReportQuery reportQuery) {
        Session sessionHB = null;
        Criteria criteria = null;
        List<PropertyDetails> results = null;

        try {
            sessionHB = sessionFactory.openSession();
            criteria = sessionHB.createCriteria(PropertyDetails.class);

            if (!reportQuery.getZone().equalsIgnoreCase("-1") && !reportQuery.getZone().isEmpty() && !reportQuery.getZone().equalsIgnoreCase("")) {
                criteria.add(Restrictions.eq("zoneId", reportQuery.getZone()));
            }
            if (!reportQuery.getPropId().equalsIgnoreCase("-1") && !reportQuery.getPropId().isEmpty() && !reportQuery.getPropId().equalsIgnoreCase("")) {
                criteria.add(Restrictions.eq("propertyUniqueId", reportQuery.getPropId()));
            }
            results = criteria.list();

        } catch (Exception e) {
            logger.info(e.getMessage());
        } finally {
            if (sessionHB != null) {
                sessionHB.close();
            }
        }
        return results;
    }

    @Override
    public List<PaymentBean> getPropertyDetailsForPayment(Set propList, ReportQuery reportQuery) {
        List<PaymentBean> ptlist = null;
        Session sessionHB = null;
        Criteria criteria = null;
        try {
            if (propList == null || propList.size() < 1) {
                return ptlist;
            }
            sessionHB = sessionFactory.openSession();
            criteria = sessionHB.createCriteria(PaymentBean.class);
            criteria.add(Restrictions.in("propId", propList));
            ptlist = criteria.list();

        } catch (Exception e) {
            logger.info(e.getMessage());

        } finally {
            if (sessionHB != null) {
                sessionHB.flush();
                sessionHB.close();
            }
        }
        return ptlist;

    }

    @Override
    public List<ObjectionBean> getObjAttributes(ReportQuery reportQuery) {
        List<ObjectionBean> ptlist = null;
        Session sessionHB = null;
        Criteria criteria = null;
        try {
            sessionHB = sessionFactory.openSession();
            criteria = sessionHB.createCriteria(ObjectionBean.class);
            criteria.add(Restrictions.eq("objectionId", reportQuery.getObjectionId()));
            ptlist = criteria.list();
        } catch (Exception e) {
            logger.info(e.getMessage());

        } finally {
            if (sessionHB != null) {
                sessionHB.flush();
                sessionHB.close();
            }
        }
        return ptlist;
    }

    @Override
    public List<PropertyEditableFields> getObjectionCategory(Set objCatList) {
        List<PropertyEditableFields> ptlist = null;
        Session sessionHB = null;
        Criteria criteria = null;
        try {
            if (objCatList == null || objCatList.size() < 1) {
                return ptlist;
            }
            sessionHB = sessionFactory.openSession();
            criteria = sessionHB.createCriteria(PropertyEditableFields.class);
            criteria.add(Restrictions.in("key", objCatList));
            ptlist = criteria.list();

        } catch (Exception e) {
            logger.info(e.getMessage());

        } finally {
            if (sessionHB != null) {
                sessionHB.flush();
                sessionHB.close();
            }
        }
        return ptlist;
    }

    @Override
    public List<ObjectionBean> getObjectedAttributes(String propertyId, String objectionId) throws Exception {

        Criteria criteria = null;
        Session sessionHB = null;
        List<ObjectionBean> objs = null;
        try {
            sessionHB = sessionFactory.openSession();
            criteria = sessionHB.createCriteria(ObjectionBean.class);
            criteria.add(Restrictions.eq("objectionId", objectionId));
            criteria.add(Restrictions.eq("propertyId", propertyId));
            objs = (List<ObjectionBean>) criteria.list();

        } catch (Exception e) {
            logger.info(e.getMessage());
            throw e;
        } finally {
            if (sessionHB != null) {
                sessionHB.close();
            }
        }

        return objs;
    }

}
