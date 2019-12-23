/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.silvassa.report.service;

import com.silvassa.bean.ReportQuery;
import com.silvassa.model.ObjectionBean;
import com.silvassa.model.ObjectionTx;
import com.silvassa.model.PaymentBean;
import com.silvassa.model.PropertyDetails;
import com.silvassa.model.PropertyEditableFields;
import com.silvassa.model.TAXDetailBean;
import com.silvassa.model.TaxNotices;
import com.silvassa.report.dao.ReportDAO;
import com.silvassa.tax.dao.TaxCollectionDAO;
import com.silvassa.util.MMIUtil;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Set;
import org.apache.log4j.Logger;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Component;

/**
 *
 * @author CEINFO
 */
@Component("reportService")
public class ReportServiceImpl implements ReportService {

    @Autowired
    @Qualifier("reportDAO")
    ReportDAO reportDAO;

    @Autowired
    @Qualifier("taxCollectionDAO")
    TaxCollectionDAO taxCollection;

    private static final Logger logger = Logger.getLogger(ReportServiceImpl.class);

    @Override
    public Object searchByPropertyDetails(ReportQuery reportQuery) {

        JSONObject jo = new JSONObject();
        List<PropertyDetails> propertyDetailslist = null;
        try {
            propertyDetailslist = reportDAO.showPropertyDetails(reportQuery);
            jo.put("propertyArr", propertyDetailslist);

            if (propertyDetailslist.size() > 0) {
                List<String> st = new ArrayList();
                for (PropertyDetails pd : propertyDetailslist) {
                    st.add(pd.getPropertyUniqueId());
                }
                List<TAXDetailBean> taxDetailArr = reportDAO.searchTAXAmount(reportQuery, st);
                jo.put("taxDetailArr", taxDetailArr);
            }

        } catch (Exception e) {
            logger.info(e.getMessage());
        }

        return jo;

    }

    @Override
    public Object searchObjectionReport(ReportQuery reportQuery) throws Exception {
        JSONObject jo = new JSONObject();
        try {
            List<ObjectionTx> objectionList = reportDAO.showObjectionDetails(reportQuery);
            jo.put("objectionArr", objectionList);
            if (objectionList.size() > 0) {
                List<String> st = new ArrayList();
           
                for (ObjectionTx pd : objectionList) {
                    st.add(pd.getPropertyId());
                    pd.setObjectionBeans(reportDAO.getObjectedAttributes(pd.getPropertyId(), pd.getObjectionId()));

                }
                List<PropertyDetails> propDetailArr = reportDAO.getPropertyDetails(reportQuery, st);
                jo.put("propDetailArr", propDetailArr);

            }
        } catch (Exception e) {
            logger.info(e.getMessage());
        }
        return jo;
    }

    @Override
    public Object searchByTAXDetails(ReportQuery reportQuery) {

        JSONObject jo = new JSONObject();
        List<PropertyDetails> propertyDetailslist = null;
        try {
            propertyDetailslist = reportDAO.showPropertyDetails(reportQuery);
            jo.put("propertyArr", propertyDetailslist);

            if (propertyDetailslist.size() > 0) {
                List<String> st = new ArrayList();
                for (PropertyDetails pd : propertyDetailslist) {
                    st.add(pd.getPropertyUniqueId());
                }
                List<TAXDetailBean> taxDetailArr = reportDAO.searchTAXAmount(reportQuery, st);
                jo.put("taxDetailArr", taxDetailArr);
            }

        } catch (Exception e) {
            logger.info(e.getMessage());
        }

        return jo;

    }

    @Override
    public Object searchByPaymentDue(ReportQuery reportQuery) {
        List<TAXDetailBean> taxList = null;
        List<PropertyDetails> proplist = null;
        JSONObject jo = new JSONObject();
        List<String> pt = null;
        try {

            taxList = reportDAO.filterPendingPaymentCases(reportQuery);

            if (taxList == null || taxList.size() <= 0) {
                jo.put("status", "700");
            } else {

                if (taxList == null || taxList.size() < 1) {
                    return taxList;
                }
                pt = new ArrayList<String>();
                for (TAXDetailBean pd : taxList) {
                    pt.add(pd.getPropertyId());
                }

                proplist = reportDAO.getPropertyDetails(reportQuery, pt);
                jo.put("status", "200");
                jo.put("taxDetails", taxList);
                jo.put("propDetails", proplist);
            }

        } catch (Exception e) {
            jo.put("status", "700");
            logger.info(e.getMessage());

        }
        return jo;
    }

    @Override
    public Object searchByArrearDue(ReportQuery reportQuery) {
        List<TAXDetailBean> taxList = null;
        List<PropertyDetails> proplist = null;
        JSONObject jo = new JSONObject();
        List<String> pt = null;
        try {

            taxList = reportDAO.filterPendingArrearCases(reportQuery);

            if (taxList == null || taxList.size() <= 0) {
                jo.put("status", "700");
            } else {

                if (taxList == null || taxList.size() < 1) {
                    return taxList;
                }
                pt = new ArrayList<String>();
                for (TAXDetailBean pd : taxList) {
                    pt.add(pd.getPropertyId());
                }

                proplist = reportDAO.getPropertyDetails(reportQuery, pt);
                jo.put("status", "200");
                jo.put("taxDetails", taxList);
                jo.put("propDetails", proplist);
            }

        } catch (Exception e) {
            jo.put("status", "700");
            logger.info(e.getMessage());

        }
        return jo;
    }

    @Override
    public Object searchByNotice(ReportQuery reportQuery) {
        List<TaxNotices> noticeList = null;
        List<PropertyDetails> proplist = null;
        JSONObject jo = new JSONObject();
        List<String> pt = null;
        try {

            proplist = reportDAO.showPropertyDetails(reportQuery);

            if (proplist == null || proplist.size() < 1) {
                jo.put("status", "700");
                return jo;
            }

            pt = new ArrayList<String>();
            for (PropertyDetails pd : proplist) {
                pt.add(pd.getPropertyUniqueId());
            }

            noticeList = reportDAO.loadtTaxNoticeData(reportQuery, pt);

            if (noticeList == null || noticeList.size() <= 0) {
                jo.put("status", "700");
            } else {
                jo.put("status", "200");
                jo.put("taxBeans", noticeList);
                jo.put("propBeans", proplist);
            }

        } catch (Exception e) {
            jo.put("status", "700");
            logger.info(e.getMessage());

        }
        return jo;
    }

    @Override
    public Object searchByTAXGen(ReportQuery reportQuery) {
        List<TAXDetailBean> taxList = null;
        List<PropertyDetails> proplist = null;
        JSONObject jo = new JSONObject();
        List<String> pt = null;
        try {

            proplist = reportDAO.showPropertyDetails(reportQuery);

            if (proplist == null || proplist.size() < 1) {
                jo.put("status", "700");
                return jo;
            }

            pt = new ArrayList<String>();
            for (PropertyDetails pd : proplist) {
                pt.add(pd.getPropertyUniqueId());
            }

            taxList = reportDAO.searchTAXAmount(reportQuery, pt);

            if (taxList == null || taxList.size() <= 0) {
                jo.put("status", "700");
            } else {
                jo.put("status", "200");
                jo.put("taxDetails", taxList);
                jo.put("propDetails", proplist);
            }

        } catch (Exception e) {
            jo.put("status", "700");
            logger.info(e.getMessage());

        }
        return jo;
    }

    @Override
    public Object searchByTAXAmount(ReportQuery reportQuery) {
        List<TAXDetailBean> taxList = null;
        List<PropertyDetails> proplist = null;
        JSONObject jo = new JSONObject();
        List<String> pt = null;
        try {

            proplist = reportDAO.showPropertyDetails(reportQuery);

            if (proplist == null || proplist.size() < 1) {
                jo.put("status", "700");
                return jo;
            }

            pt = new ArrayList<String>();
            for (PropertyDetails pd : proplist) {
                pt.add(pd.getPropertyUniqueId());
            }

            taxList = reportDAO.searchTAXAmount(reportQuery, pt);

            if (taxList == null || taxList.size() <= 0) {
                jo.put("status", "700");
            } else {
                jo.put("status", "200");
                jo.put("taxDetails", taxList);
                jo.put("propDetails", proplist);
            }

        } catch (Exception e) {
            jo.put("status", "700");
            logger.info(e.getMessage());

        }
        return jo;
    }

    @Override
    public Object searchPropertyForReceipt(ReportQuery reportQuery) {
        JSONObject jo = new JSONObject();
        try {
            List<PropertyDetails> propertyList = reportDAO.getPropDetailsForReceipt(reportQuery);
            jo.put("propArr", propertyList);
            //  logger.info("propertyList::"+propertyList.toString());
            if (propertyList.size() > 0) {
                Set st = new HashSet();
                for (PropertyDetails pd : propertyList) {
                    st.add(pd.getPropertyUniqueId());
                }
                List<PaymentBean> paymentDetailArr = reportDAO.getPropertyDetailsForPayment(st, reportQuery);
                jo.put("paymentArr", paymentDetailArr);
            }
        } catch (Exception e) {
            logger.info(e.getMessage());
        }
        return jo;
    }

    @Override
    public Object getObjectionCategories(ReportQuery reportQuery) {
        JSONObject jo = new JSONObject();
        try {
            List<ObjectionBean> attrList = reportDAO.getObjAttributes(reportQuery);
            jo.put("attrList", attrList);
            Set category = new HashSet();
            for (ObjectionBean ob : attrList) {
                category.add(ob.getAttr());
            }
            List<PropertyEditableFields> objCatList = reportDAO.getObjectionCategory(category);
            jo.put("objCatList", objCatList);
        } catch (Exception e) {
            logger.info(e.getMessage());
        }
        return jo;
    }

}
