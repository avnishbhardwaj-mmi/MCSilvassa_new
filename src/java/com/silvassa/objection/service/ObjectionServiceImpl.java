/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.silvassa.objection.service;

import com.silvassa.bean.ObjSearchBean;
import com.silvassa.bean.ResolutionBean;
import com.silvassa.model.ObjDocument;
import com.silvassa.model.ObjRelations;
import com.silvassa.model.ObjectionActionHistory;
import com.silvassa.model.ObjectionActionTray;
import com.silvassa.model.ObjectionBean;
import com.silvassa.model.PropertyEditableFields;
import com.silvassa.model.ObjectionTx;
import com.silvassa.model.PropertyDetails;
import com.silvassa.model.PropertyFloor;
import com.silvassa.model.TAXDetailBean;
import com.silvassa.model.UserPermission;
import com.silvassa.model.Usermaster;
import com.silvassa.util.MMIUtil;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import org.apache.commons.beanutils.BeanMap;
import org.apache.commons.beanutils.BeanUtils;
import org.apache.log4j.Logger;
import org.hibernate.Criteria;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.criterion.Order;
import org.hibernate.criterion.Projections;
import org.hibernate.criterion.Restrictions;
import org.json.simple.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Component;

/**
 *
 * @author CEINFO
 */
@Component("objectionService")
public class ObjectionServiceImpl implements ObjectionService {

    
    private SessionFactory sessionFactory;

    private static final Logger logger = Logger.getLogger(ObjectionServiceImpl.class);

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

    // Sandeep added below method for objection datatable
    @SuppressWarnings({"unchecked", "rawtypes"})
    @Override
    public List searchObjectionDetails(ObjSearchBean objSearchBean) {

        Session sessionHB = null;
        Criteria criteria = null;
        List<PropertyDetails> results = null;

        try {
            sessionHB = sessionFactory.openSession();
            criteria = sessionHB.createCriteria(PropertyDetails.class);
            criteria.add(Restrictions.eq("zoneId", objSearchBean.getZone()));
            criteria.add(Restrictions.eq("ward", objSearchBean.getWard()));

            if (objSearchBean.getOwner() != null && !objSearchBean.getOwner().equalsIgnoreCase("-1")) {
                criteria.add(Restrictions.eq("propertyOwner", objSearchBean.getOwner()));
            }

            if (objSearchBean.getPropID() != null && !objSearchBean.getPropID().equalsIgnoreCase("-1")) {
                criteria.add(Restrictions.eq("propertyUniqueId", objSearchBean.getPropID()));
            }

            if (objSearchBean.getOccupier() != null && !objSearchBean.getOccupier().equalsIgnoreCase("-1")) {
                criteria.add(Restrictions.eq("propertyOccupierName", objSearchBean.getOccupier()));
            }

            results = (List<PropertyDetails>) criteria.list();

        } catch (Exception e) {
            logger.info(e.getMessage());
        } finally {
            if (sessionHB != null) {
                sessionHB.flush();
                sessionHB.close();
            }

        }
        return results;
    }

    // Sandeep added below on 31Jan
    @SuppressWarnings("unchecked")
    @Override
    public JSONObject loadPropertyMasterList() {

        JSONObject jo = new JSONObject();
        List<String> propIdArr = null;
        List<String> ownerArr = null;
        List<String> occupierArr = null;

        Session sessionHB = null;
        try {
            sessionHB = sessionFactory.openSession();

            String hqlpropIdArr = "select distinct(propertyUniqueId) as property_unique_id from PropertyDetails where propertyUniqueId is not null order by propertyUniqueId";
            String hqlownerArr = "select distinct(propertyOwner) as property_owner from PropertyDetails where propertyOwner is not null and trim(propertyOwner) != '' order by propertyOwner";
            String hqloccupierArr = "select distinct(propertyOccupierName) as property_occupier_name from PropertyDetails where propertyOccupierName is not null and trim(propertyOccupierName) != '' order by propertyOccupierName";

            Query query = sessionHB.createQuery(hqlpropIdArr);
            query.setResultTransformer(Criteria.ALIAS_TO_ENTITY_MAP);
            propIdArr = query.list();

            query = sessionHB.createQuery(hqlownerArr);
            query.setResultTransformer(Criteria.ALIAS_TO_ENTITY_MAP);
            ownerArr = query.list();

            query = sessionHB.createQuery(hqloccupierArr);
            query.setResultTransformer(Criteria.ALIAS_TO_ENTITY_MAP);
            occupierArr = query.list();

        } catch (Exception e) {
            logger.info(e.getMessage());
        } finally {
            if (sessionHB != null) {
                sessionHB.flush();
                sessionHB.close();
            }
        }
        jo.put("propIdArr", propIdArr);
        jo.put("ownerArr", ownerArr);
        jo.put("occupierArr", occupierArr);
        return jo;
    }

//-------------------------------------------------------------------------------------------------
// Method                   : getEditableAttr
// Parameters               : void
// Return type              : List
// Created on               : MARCH,2017
// Ceated by                : Sunil Kumar
// Objective                : Return all the editable attributes of property master that can be update by editor.
// Modified on              :
// Modified by              :
//-------------------------------------------------------------------------------------------------
    @Override
    public List<PropertyEditableFields> getEditableAttr() {

        Session sessionHB = null;
        Criteria criteria = null;
        List<PropertyEditableFields> results = null;

        try {

            sessionHB = sessionFactory.openSession();
            criteria = sessionHB.createCriteria(PropertyEditableFields.class);

            criteria.setProjection(Projections.projectionList()
                    .add(Projections.property("categoryId"))
                    .add(Projections.property("category"))
                    .add(Projections.property("key"))
                    .add(Projections.property("fieldAssociated"))
                    .add(Projections.property("modelAttr"))
                    .add(Projections.property("isComplex"))
            );

            results = (List<PropertyEditableFields>) criteria.list();

        } catch (Exception e) {
            logger.info(e.getMessage());
        } finally {
            if (sessionHB != null) {
                sessionHB.flush();
                sessionHB.close();
            }

        }
        return results;
    }

//-------------------------------------------------------------------------------------------------
// Method                   : getFloorDetails
// Parameters               : String propertyId
// Return type              : List<PropertyFloor>
// Created on               : MARCH,2017
// Ceated by                : Sunil Kumar
// Objective                : Return floor details, floor comes under a property.
// Modified on              :
// Modified by              :
//-------------------------------------------------------------------------------------------------
    @Override
    public List<PropertyFloor> getFloorDetails(String propertyId) {
        Criteria criteria = null;
        Session sessionHB = null;
        List<PropertyFloor> floorList = null;
        try {
            sessionHB = sessionFactory.openSession();

            criteria = sessionHB.createCriteria(PropertyFloor.class, "pf");
            criteria.add(Restrictions.eq("pf.propertyUniqueId", propertyId));

            floorList = (List<PropertyFloor>) criteria.list();

        } catch (Exception e) {
            logger.info(e.getMessage());
        } finally {
            if (sessionHB != null) {
                sessionHB.close();
            }
        }

        return floorList;
    }

//-------------------------------------------------------------------------------------------------
// Method                   : getObjectionRefNumber
// Parameters               : void
// Return type              : String
// Created on               : MARCH,2017
// Ceated by                : Sunil Kumar
// Objective                : Return refrence Id
// Modified on              :
// Modified by              :
//-------------------------------------------------------------------------------------------------
    @Override
    public String getObjectionRefNumber() throws Exception {

        Session sessionHB = null;
        String seqNo = null;
        try {

            sessionHB = sessionFactory.openSession();
            Query query = sessionHB.createSQLQuery("select fn_tx_objection_refid()");
            seqNo = (String) query.uniqueResult();
        } catch (Exception ex) {
            logger.info("[ObjectionServiceImpl.getObjectionRefNumber] Exception : " + ex.getMessage());
            throw ex;
        } finally {
            if (sessionHB != null) {
                sessionHB.flush();
                sessionHB.clear();
                sessionHB.close();
            }
        }

        return seqNo;
    }
//-------------------------------------------------------------------------------------------------
// Method                   : raiseObjection
// Parameters               : ObjectionTx objectionTx
// Return type              : String
// Created on               : MARCH,2017
// Ceated by                : Sunil Kumar
// Objective                : Return refrence Id
// Modified on              :
// Modified by              :
//-------------------------------------------------------------------------------------------------

    @Override
    public boolean raiseObjection(ObjectionTx objectionTx) {

        Session sessionHB = null;
        boolean updateStatus = false;
        Transaction tx = null;
        try {

            sessionHB = sessionFactory.openSession();
            tx = sessionHB.beginTransaction();
            sessionHB.save(objectionTx);
            updateStatus = fillObjectionData(sessionHB, objectionTx.getObjectionBeans());
            tx.commit();
        } catch (Exception ex) {
            ex.printStackTrace();
            logger.info("[ObjectionServiceImpl.raiseObjection] Exception : " + ex.getMessage());
        } finally {
            if (sessionHB != null) {
                sessionHB.flush();
                sessionHB.clear();
                sessionHB.close();
            }
        }

        return updateStatus;
    }
//-------------------------------------------------------------------------------------------------
// Method                   : fillObjectionData
// Parameters               : List<ObjectionBean> objectionBeans
// Return type              : String
// Created on               : MARCH,2017
// Ceated by                : Sunil Kumar
// Objective                : Return refrence Id
// Modified on              :
// Modified by              :
//-------------------------------------------------------------------------------------------------

    public boolean fillObjectionData(Session sessionHB, List<ObjectionBean> objectionBeans) throws Exception {

        boolean updateStatus = false;
        String propertyId = "";
        try {

            for (ObjectionBean objectionBean : objectionBeans) {
                sessionHB.save(objectionBean);
                propertyId = objectionBean.getPropertyId();
            }
            if (objectionBeans.size() > 0) {
                updateStatus = updatePropertyAsObjected(sessionHB, propertyId);
            }

            updateStatus = true;

        } catch (Exception ex) {
            logger.info("[ObjectionServiceImpl.fillObjectionData] Exception : " + ex.getMessage());
            throw ex;
        }

        return updateStatus;
    }

    public boolean updatePropertyAsObjected(Session sessionHB, String propertyId) throws Exception {

        boolean updateStatus = false;
        try {

            TAXDetailBean tsb = getTAXDetails(propertyId, MMIUtil.getCurrentFinancialYear(), "Y");

            Query query = sessionHB.createSQLQuery("UPDATE PROPERTY_TAX SET NOTICE_GENERATED = :NOTICE_STATUS, OBJECTION_STATUS"
                    + " = :OBJ_STATUS WHERE PROPERTY_UNIQUE_ID = :PROPID AND FINANCIAL_YEAR = :FY AND TAX_NO = :TAX_NO");

            query.setParameter("NOTICE_STATUS", "N");
            query.setParameter("OBJ_STATUS", "Y");
            query.setParameter("PROPID", tsb.getPropertyId());
            query.setParameter("FY", tsb.getFinancialYear());
            query.setParameter("TAX_NO", tsb.getTaxNo());
            query.executeUpdate();

            updateStatus = true;

        } catch (Exception ex) {
            logger.info("[ObjectionServiceImpl.fillObjectionData] Exception : " + ex.getMessage());
            throw ex;
        }

        return updateStatus;
    }

    public TAXDetailBean getTAXDetails(String propId, String financialYear, String noticeGenerated) {

        Session sessionHB = null;
        TAXDetailBean tAXDetailBean = null;

        try {

            sessionHB = sessionFactory.openSession();
            Criteria criteria = sessionHB.createCriteria(TAXDetailBean.class);

            criteria.add(Restrictions.eq("noticeGenerated", noticeGenerated));
            criteria.add(Restrictions.eq("propertyId", propId));
            criteria.add(Restrictions.eq("financialYear", financialYear));

            tAXDetailBean = (TAXDetailBean) criteria.uniqueResult();

        } catch (Exception ex) {
            logger.info("[TAXDetailBean.getTAXDetails] Exception : " + ex.getMessage());

        } finally {
            if (sessionHB != null) {
                sessionHB.flush();
                sessionHB.clear();
                sessionHB.close();
            }
        }

        return tAXDetailBean;
    }

    //-------------------------------------------------------------------------------------------------
// Method                   : getVerifyingDocuments
// Parameters               : void
// Return type              : List<PropertyFloor>
// Created on               : MARCH,2017
// Ceated by                : Sunil Kumar
// Objective                : Return floor details, floor comes under a property.
// Modified on              :
// Modified by              :
//-------------------------------------------------------------------------------------------------
    @Override
    public List<ObjDocument> getVerifyingDocuments() {
        Criteria criteria = null;
        Session sessionHB = null;
        List<ObjDocument> objDocuments = null;
        try {
            sessionHB = sessionFactory.openSession();

            criteria = sessionHB.createCriteria(ObjDocument.class);
            criteria.add(Restrictions.eq("isActive", "Y"));
            objDocuments = (List<ObjDocument>) criteria.list();

        } catch (Exception e) {
            logger.info(e.getMessage());
        } finally {
            if (sessionHB != null) {
                sessionHB.close();
            }
        }

        return objDocuments;
    }

    //-------------------------------------------------------------------------------------------------
// Method                   : checkIfPendingObjection
// Parameters               : String propertyId
// Return type              : boolean
// Created on               : MARCH,2017
// Ceated by                : Sunil Kumar
// Objective                : Return objection status.
// Modified on              :
// Modified by              :
//-------------------------------------------------------------------------------------------------
    @Override
    public boolean checkIfPendingObjection(String propertyId) throws Exception {
        Criteria criteria = null;
        Session sessionHB = null;
        List objs = null;
        boolean status = false;
        try {
            sessionHB = sessionFactory.openSession();

            criteria = sessionHB.createCriteria(ObjectionTx.class);
            criteria.add(Restrictions.eq("propertyId", propertyId));
            criteria.add(Restrictions.eq("status", MMIUtil.OBJ_STATUS_CREATE));
            objs = criteria.list();
            if (objs.size() > 0) {
                status = true;
            }
        } catch (Exception e) {
            logger.info(e.getMessage());
            throw e;
        } finally {
            if (sessionHB != null) {
                sessionHB.close();
            }
        }

        return status;
    }

    //-------------------------------------------------------------------------------------------------
// Method                   : getObjection
// Parameters               : String propertyId
// Return type              : String
// Created on               : MARCH,2017
// Ceated by                : Sunil Kumar
// Objective                : Return ObjectionTx
// Modified on              :
// Modified by              :
//-------------------------------------------------------------------------------------------------
    @Override
    public ObjectionTx getObjection(String propertyId) throws Exception {

        Criteria criteria = null;
        Session sessionHB = null;
        ObjectionTx objs = null;
        try {
            sessionHB = sessionFactory.openSession();

            criteria = sessionHB.createCriteria(ObjectionTx.class);
            criteria.add(Restrictions.eq("propertyId", propertyId));
            criteria.add(Restrictions.eq("status", MMIUtil.OBJ_STATUS_CREATE));
            objs = (ObjectionTx) criteria.uniqueResult();

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
//-------------------------------------------------------------------------------------------------
// Method                   : fillObjectionData
// Parameters               : List<ObjectionBean> objectionBeans
// Return type              : String
// Created on               : MARCH,2017
// Ceated by                : Sunil Kumar
// Objective                : Return refrence Id
// Modified on              :
// Modified by              :
//-------------------------------------------------------------------------------------------------

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

    @Override
    public JSONObject getObjectedPropertyForSearch(String zoneId, String ward) throws Exception {

        Session sessionHB = null;

        JSONObject jo = new JSONObject();
        List<String> propIdArr = null;
        List<String> ownerArr = null;
        List<String> occupierArr = null;
        try {
            sessionHB = sessionFactory.openSession();
            String sqlProp = " select distinct(prop.property_unique_id)  "
                    + " from tx_objection objTx left join property_details prop on property_unique_id = propertyid "
                    + " where objTx.objectionId not in(select distinct(objection_id) from obj_action_tray where objection_id is not null) "
                    + " and prop.zone_id = :zone_id and prop.ward = :ward and objTx.status = :status order by property_unique_id";

            String sqlOwner = " select distinct(prop.property_owner) "
                    + " from tx_objection objTx left join property_details prop on property_unique_id = propertyid "
                    + " where objTx.objectionId not in(select distinct(objection_id) from obj_action_tray where objection_id is not null) "
                    + " and prop.zone_id = :zone_id and prop.ward = :ward and objTx.status = :status order by prop.property_owner";

            String sqlOccupier = " select distinct(prop.property_occupier_name) "
                    + " from tx_objection objTx left join property_details prop on property_unique_id = propertyid "
                    + " where objTx.objectionId not in(select distinct(objection_id) from obj_action_tray where objection_id is not null) "
                    + " and prop.zone_id = :zone_id and prop.ward = :ward and objTx.status = :status order by prop.property_occupier_name";

            Query query = sessionHB.createSQLQuery(sqlProp);
            query.setParameter("zone_id", zoneId);
            query.setParameter("ward", ward);
            query.setParameter("status", MMIUtil.OBJ_STATUS_CREATE);
            propIdArr = query.list();

            query = sessionHB.createSQLQuery(sqlOwner);
            query.setParameter("zone_id", zoneId);
            query.setParameter("ward", ward);
            query.setParameter("status", MMIUtil.OBJ_STATUS_CREATE);
            ownerArr = query.list();

            query = sessionHB.createSQLQuery(sqlOccupier);
            query.setParameter("zone_id", zoneId);
            query.setParameter("ward", ward);
            query.setParameter("status", MMIUtil.OBJ_STATUS_CREATE);
            occupierArr = query.list();

            jo.put("propIdArr", propIdArr);
            jo.put("ownerArr", ownerArr);
            jo.put("occupierArr", occupierArr);

        } catch (Exception e) {
            logger.info(e.getMessage());
            throw e;
        } finally {
            if (sessionHB != null) {
                sessionHB.close();
            }
        }

        return jo;
    }

    @Override
    public List searchObjectedProperty(ObjSearchBean objSearchBean) throws Exception {

        Session sessionHB = null;
        Criteria criteria = null;
        List<PropertyDetails> results = null;

        try {

            List<String> lsProperty = getObjectedPropertyList(objSearchBean);
            if (lsProperty == null || lsProperty.size() < 1) {
                return results;
            }
            sessionHB = sessionFactory.openSession();
            criteria = sessionHB.createCriteria(PropertyDetails.class);
            criteria.add(Restrictions.in("propertyUniqueId", lsProperty));
            results = (List<PropertyDetails>) criteria.list();

        } catch (Exception e) {
            logger.info(e.getMessage());
        } finally {
            if (sessionHB != null) {
                sessionHB.flush();
                sessionHB.close();
            }

        }
        return results;
    }

    public List<String> getObjectedPropertyList(ObjSearchBean objSearchBean) throws Exception {

        Session sessionHB = null;
        List<String> propIdArr = null;

        boolean isZone = false;
        boolean isWard = false;
        boolean isPropId = false;
        boolean isOwner = false;
        boolean isOccupier = false;

        boolean isFromDate = false;
        boolean isToDate = false;
        boolean isObjId = false;
        boolean isObjStatus = false;

        if (objSearchBean.getZone() != null && !String.valueOf(objSearchBean.getZone()).equals("")) {
            isZone = true;
            if (objSearchBean.getWard() != null && !String.valueOf(objSearchBean.getWard()).equals("")) {
                isWard = true;
            }
        }
        if (objSearchBean.getOwner() != null && !String.valueOf(objSearchBean.getOwner()).equals("")) {
            isOwner = true;
        }
        if (objSearchBean.getOccupier() != null && !String.valueOf(objSearchBean.getOccupier()).equals("")) {
            isOccupier = true;
        }
        if (objSearchBean.getPropID() != null && !String.valueOf(objSearchBean.getPropID()).equals("")) {
            isPropId = true;
        }

        if (objSearchBean.getObjId() != null && !String.valueOf(objSearchBean.getObjId()).equals("")) {
            isObjId = true;
        }
        if (objSearchBean.getObjStatus() != null && !String.valueOf(objSearchBean.getObjStatus()).equals("")) {
            isObjStatus = true;
        }
        if (objSearchBean.getFromDate() != null && !String.valueOf(objSearchBean.getFromDate()).equals("")) {
            isFromDate = true;
        }
        if (objSearchBean.getToDate() != null && !String.valueOf(objSearchBean.getToDate()).equals("")) {
            isToDate = true;
        }

        try {
            sessionHB = sessionFactory.openSession();
            String sqlProp = "select property_unique_id from tx_objection objTx left join property_details prop on property_unique_id = propertyid "
                    + " where objTx.objectionId not in(select distinct(objection_id) from obj_action_tray where objection_id is not null) and ";

            if (isZone && isFromDate) {
                return propIdArr;
            }

            if (isZone) {
                sqlProp += " prop.zone_id = :zone_id";
                if (isWard) {
                    sqlProp += " and prop.ward = :ward ";
                }
                if (isOwner) {
                    sqlProp += " and prop.property_owner = :property_owner ";
                }
                if (isOccupier) {
                    sqlProp += " and prop.property_occupier_name = :property_occupier_name ";
                }
                if (isPropId) {
                    sqlProp += " and prop.property_unique_id = :property_unique_id ";
                }
                sqlProp += " and objTx.status = :status ";
            } else {
                sqlProp += " entrydatetime between cast(:fromDate as date) - interval '1 day'  and cast(:toDate as date)  + interval '1 day' ";
                if (isObjId) {
                    sqlProp += " and objTx.objectionId = :objectionId ";
                }
                if (isObjStatus) {
                    sqlProp += " and objTx.status = :status ";
                }
            }

            sqlProp += " order by property_unique_id";
            System.out.println("sqlProp : " + sqlProp);
            Query query = sessionHB.createSQLQuery(sqlProp);

            if (isZone) {
                query.setParameter("zone_id", objSearchBean.getZone());
                if (isWard) {
                    query.setParameter("ward", objSearchBean.getWard());
                }
                if (isOwner) {
                    query.setParameter("property_owner", objSearchBean.getOwner());
                }
                if (isOccupier) {
                    query.setParameter("property_occupier_name", objSearchBean.getOccupier());
                }
                if (isPropId) {
                    query.setParameter("property_unique_id", objSearchBean.getPropID());
                }
                query.setParameter("status", MMIUtil.OBJ_STATUS_CREATE);
            } else {
                query.setParameter("fromDate", objSearchBean.getFromDate());
                query.setParameter("toDate", objSearchBean.getToDate());
                if (isObjId) {
                    query.setParameter("objectionId", objSearchBean.getObjId());
                }
                if (isObjStatus) {
                    query.setParameter("status", objSearchBean.getObjStatus());
                }
            }
            logger.info("sqlProp : " + sqlProp);
            propIdArr = query.list();

        } catch (Exception e) {
            logger.info(e.getMessage());
            throw e;
        } finally {
            if (sessionHB != null) {
                sessionHB.close();
            }
        }

        return propIdArr;
    }

//-------------------------------------------------------------------------------------------------
// Method                   : rangeOfPendingObjection
// Parameters               : void
// Return type              : 
// Created on               : MARCH,2017
// Ceated by                : Sunil Kumar
// Objective                : Return count of pending objections.
// Modified on              :
// Modified by              :
//-------------------------------------------------------------------------------------------------
    @Override
    public Object rangeOfPendingObjection() throws Exception {
        Criteria criteria = null;
        Session sessionHB = null;
        Object ls = null;
        try {
            sessionHB = sessionFactory.openSession();

            criteria = sessionHB.createCriteria(ObjectionTx.class);
            criteria.add(Restrictions.eq("status", MMIUtil.OBJ_STATUS_CREATE));
            criteria.setProjection(Projections.projectionList()
                    .add(Projections.min("raisedOn"))
                    .add(Projections.max("raisedOn"))
                    .add(Projections.count("propertyId"))
            );

            ls = criteria.uniqueResult();

        } catch (Exception e) {
            logger.info(e.getMessage());
            throw e;
        } finally {
            if (sessionHB != null) {
                sessionHB.close();
            }
        }

        return ls;
    }

    @Override
    public void approveObjection(ResolutionBean resolutionBean) throws Exception {

        try {
            ObjectionTx objectionTx = getObjection(resolutionBean.getPropertyId());
            List<ObjectionBean> objs = getObjectedAttributes(resolutionBean.getPropertyId(), resolutionBean.getObjectionId());
            List<PropertyEditableFields> attrLs = getEditableAttrForInternalUse();
            Map editableAttrMap = new HashMap();
            Map floorMap = new HashMap();

            for (PropertyEditableFields pef : attrLs) {
                editableAttrMap.put(pef.getKey(), pef);
            }

            if (objectionTx == null || objs == null || objs.size() < 1 || attrLs == null || attrLs.size() < 1) {
                throw new Exception("Insufficient Details Avialable.");
            }

            List<PropertyFloor> propertyFloors = findPropertyFloorsToUpdate(resolutionBean);
            for (PropertyFloor pf : propertyFloors) {
                floorMap.put(pf.getPfFloorName(), pf);
            }

            PropertyDetails propertyDetails = findPropertyToUpdate(resolutionBean);
            propertyDetails.setCreatedOn(MMIUtil.getCurrentTimestamp());
            Map propertyDetailMap = new BeanMap(propertyDetails);

            for (ObjectionBean oBean : objs) {
                if (oBean.getIsComplex().equalsIgnoreCase("N")) {

                    String key = oBean.getAttr();
                    PropertyEditableFields pef = (PropertyEditableFields) editableAttrMap.get(key);
                    propertyDetailMap.put(pef.getModelAttr(), oBean.getNewValue());

                } else if (oBean.getIsComplex().equalsIgnoreCase("Y")) {

                    String key = oBean.getAttr();
                    key = key.replace(oBean.getFloor(), "");
                    PropertyEditableFields pef = (PropertyEditableFields) editableAttrMap.get(key);
                    PropertyFloor propertyFloor = (PropertyFloor) floorMap.get(oBean.getFloor());
                    Map floorDetailMap = new BeanMap(propertyFloor);
                    floorDetailMap.put(pef.getModelAttr(), oBean.getNewValue());
                    BeanUtils.populate(propertyFloor, floorDetailMap);

                }
            }
            System.out.println("propertyDetails : " + propertyDetails);
            System.out.println("propertyDetailMap : " + propertyDetailMap);

            BeanUtils.populate(propertyDetails, propertyDetailMap);

            objectionTx.setStatus(MMIUtil.OBJ_STATUS_APPROVED);
            objectionTx.setResolvedBy(resolutionBean.getResolveBy());
            objectionTx.setResolvedByremarks(resolutionBean.getRemarks());
            objectionTx.setResolvedOn(resolutionBean.getResolveOn());

            TAXDetailBean tsb = getTAXDetails(objectionTx.getPropertyId(), MMIUtil.getCurrentFinancialYear(), "N");

            if (tsb != null) {
                tsb.setObjectionStatus("R");
            }

            if (propertyFloors.size() > 0) {
                Object[] objArr = new Object[propertyFloors.size() + 3];
                int count = 0;
                for (PropertyFloor pf : propertyFloors) {
                    objArr[count] = pf;
                    count++;
                }
                objArr[count] = propertyDetails;
                count++;
                objArr[count] = objectionTx;
                count++;
                objArr[count] = tsb;
                saveChanges(objArr);

            } else {
                saveChanges(new Object[]{propertyDetails, objectionTx, tsb});
            }

            ObjectionActionHistory oah = new ObjectionActionHistory();
            oah.setActionBy(resolutionBean.getResolveBy());
            oah.setActionRemarks(resolutionBean.getRemarks());
            oah.setActionTaken(resolutionBean.getDecision());
            oah.setEntrydatetime(MMIUtil.getCurrentTimestamp());
            oah.setObjectionId(resolutionBean.getObjectionId());
            oah.setPropertyId(resolutionBean.getPropertyId());

            addObjectionActionHistory(oah);

        } catch (Exception ex) {
            ex.printStackTrace();
            logger.info(ex.getMessage());
            throw ex;
        }

    }

    private PropertyDetails findPropertyToUpdate(ResolutionBean resolutionBean) {

        PropertyDetails propertyDetails = null;
        Criteria criteria = null;
        Session sessionHB = null;
        try {
            sessionHB = sessionFactory.openSession();
            criteria = sessionHB.createCriteria(PropertyDetails.class);
            criteria.add(Restrictions.eq("propertyUniqueId", resolutionBean.getPropertyId()));
            propertyDetails = (PropertyDetails) criteria.uniqueResult();

        } catch (Exception ex) {
            ex.printStackTrace();
            logger.info(ex.getMessage());
            throw ex;
        } finally {
            if (sessionHB != null) {
                sessionHB.flush();
                sessionHB.close();
            }

        }

        return propertyDetails;
    }

    private List<PropertyFloor> findPropertyFloorsToUpdate(ResolutionBean resolutionBean) {

        List<PropertyFloor> propertyFloors = null;
        Criteria criteria = null;
        Session sessionHB = null;
        try {
            sessionHB = sessionFactory.openSession();
            criteria = sessionHB.createCriteria(PropertyFloor.class);
            criteria.add(Restrictions.eq("propertyUniqueId", resolutionBean.getPropertyId()));
            propertyFloors = (List<PropertyFloor>) criteria.list();

        } catch (Exception ex) {
            logger.info(ex.getMessage());
            throw ex;
        } finally {
            if (sessionHB != null) {
                sessionHB.flush();
                sessionHB.close();
            }

        }

        return propertyFloors;
    }

    private List<PropertyEditableFields> getEditableAttrForInternalUse() {

        Session sessionHB = null;
        Criteria criteria = null;
        List<PropertyEditableFields> results = null;

        try {

            sessionHB = sessionFactory.openSession();
            criteria = sessionHB.createCriteria(PropertyEditableFields.class);
            results = (List<PropertyEditableFields>) criteria.list();

        } catch (Exception e) {
            logger.info(e.getMessage());
        } finally {
            if (sessionHB != null) {
                sessionHB.flush();
                sessionHB.close();
            }

        }
        return results;
    }

    private void saveChanges(Object[] objs) {

        Session sessionHB = null;

        try {
            sessionHB = sessionFactory.openSession();
            for (Object obj : objs) {
                if (obj != null) {
                    sessionHB.update(obj);
                }
            }

        } catch (Exception e) {
            e.printStackTrace();
            logger.info(e.getMessage());
        } finally {
            if (sessionHB != null) {
                sessionHB.flush();
                sessionHB.close();
            }

        }

    }

    @Override
    public void rejectObjection(ResolutionBean resolutionBean) throws Exception {

        ObjectionTx objectionTx = getObjection(resolutionBean.getPropertyId());
        objectionTx.setStatus(MMIUtil.OBJ_STATUS_REJECTED);
        objectionTx.setResolvedBy(resolutionBean.getResolveBy());
        objectionTx.setResolvedByremarks(resolutionBean.getRemarks());
        objectionTx.setResolvedOn(resolutionBean.getResolveOn());
        saveChanges(new Object[]{objectionTx});

        ObjectionActionHistory oah = new ObjectionActionHistory();
        oah.setActionBy(resolutionBean.getResolveBy());
        oah.setActionRemarks(resolutionBean.getRemarks());
        oah.setActionTaken(resolutionBean.getDecision());
        oah.setEntrydatetime(MMIUtil.getCurrentTimestamp());
        oah.setObjectionId(resolutionBean.getObjectionId());
        oah.setPropertyId(resolutionBean.getPropertyId());

        addObjectionActionHistory(oah);

    }

    @Override
    public void forwardObjection(ResolutionBean resolutionBean) throws Exception {

        ObjectionActionHistory oah = new ObjectionActionHistory();
        oah.setActionBy(resolutionBean.getResolveBy());
        oah.setActionRemarks(resolutionBean.getRemarks());
        oah.setActionTaken(resolutionBean.getDecision());
        oah.setEntrydatetime(MMIUtil.getCurrentTimestamp());
        oah.setObjectionId(resolutionBean.getObjectionId());
        oah.setPropertyId(resolutionBean.getPropertyId());
        oah.setForwardTo(resolutionBean.getForwardTo());
        addObjectionActionHistory(oah);

        ObjectionActionTray oat = getObjetionTray(resolutionBean);
        if (oat == null) {
            oat = new ObjectionActionTray();
            oat.setObjectionId(resolutionBean.getObjectionId());
            oat.setPropertyId(resolutionBean.getPropertyId());
        }
        oat.setTrayOwner(resolutionBean.getForwardTo());
        addUpdateObjetionTray(oat);

    }

    @Override
    public void forwardBackObjection(ResolutionBean resolutionBean) throws Exception {

        ObjectionActionHistory oah = new ObjectionActionHistory();
        oah.setActionBy(resolutionBean.getResolveBy());
        oah.setActionRemarks(resolutionBean.getRemarks());
        oah.setActionTaken(resolutionBean.getDecision());
        oah.setEntrydatetime(MMIUtil.getCurrentTimestamp());
        oah.setObjectionId(resolutionBean.getObjectionId());
        oah.setPropertyId(resolutionBean.getPropertyId());
        oah.setForwardTo(resolutionBean.getForwardTo());
        addObjectionActionHistory(oah);

        ObjectionActionTray oat = getObjetionTray(resolutionBean);
        if (oat != null) {
            removeObjetionTray(oat);
        }

    }

    //-------------------------------------------------------------------------------------------------
// Method                   : getVerifyingRelations
// Parameters               : void
// Return type              : List<PropertyFloor>
// Created on               : June,2017
// Ceated by                : Jay Prakash Kumar
// Objective                : Return relation Details.
// Modified on              :
// Modified by              :
//-------------------------------------------------------------------------------------------------
    @Override
    public List<ObjRelations> getVerifyingRelations() {
        Criteria criteria = null;
        Session sessionHB = null;
        List<ObjRelations> objRelations = null;
        try {
            sessionHB = sessionFactory.openSession();

            criteria = sessionHB.createCriteria(ObjRelations.class);
            criteria.add(Restrictions.eq("isActive", "Y"));
            objRelations = (List<ObjRelations>) criteria.list();

        } catch (Exception e) {
//            e.printStackTrace();
            logger.info(e.getMessage());
        } finally {
            if (sessionHB != null) {
                sessionHB.close();
            }
        }

        return objRelations;
    }

    @Override
    public boolean addObjectionActionHistory(ObjectionActionHistory oah) {

        Session sessionHB = null;
        boolean updateStatus = false;
        Transaction tx = null;
        try {
            sessionHB = sessionFactory.openSession();
            sessionHB.save(oah);
        } catch (Exception ex) {
            ex.printStackTrace();
            logger.info("[ObjectionServiceImpl.addObjectionActionHistory] Exception : " + ex.getMessage());
        } finally {
            if (sessionHB != null) {
                sessionHB.flush();
                sessionHB.clear();
                sessionHB.close();
            }
        }

        return updateStatus;
    }

    @Override
    public boolean addUpdateObjetionTray(ObjectionActionTray oat) {

        Session sessionHB = null;
        boolean updateStatus = false;
        Transaction tx = null;
        try {
            sessionHB = sessionFactory.openSession();
            sessionHB.saveOrUpdate(oat);
        } catch (Exception ex) {
            ex.printStackTrace();
            logger.info("[ObjectionServiceImpl.addUpdateObjetionTray] Exception : " + ex.getMessage());
        } finally {
            if (sessionHB != null) {
                sessionHB.flush();
                sessionHB.clear();
                sessionHB.close();
            }
        }

        return updateStatus;
    }

    @Override
    public ObjectionActionTray getObjetionTray(ResolutionBean resolutionBean) {

        Session sessionHB = null;
        Criteria criteria = null;
        ObjectionActionTray oat = null;
        try {
            sessionHB = sessionFactory.openSession();
            criteria = sessionHB.createCriteria(ObjectionActionTray.class);
            criteria.add(Restrictions.eq("objectionId", resolutionBean.getObjectionId()));
            criteria.add(Restrictions.eq("propertyId", resolutionBean.getPropertyId()));
            oat = (ObjectionActionTray) criteria.uniqueResult();

        } catch (Exception ex) {
            ex.printStackTrace();
            logger.info("[ObjectionServiceImpl.getObjetionTray] Exception : " + ex.getMessage());
        } finally {
            if (sessionHB != null) {
                sessionHB.flush();
                sessionHB.clear();
                sessionHB.close();
            }
        }

        return oat;
    }

    public ObjectionActionTray removeObjetionTray(ObjectionActionTray oat) {

        Session sessionHB = null;

        try {
            sessionHB = sessionFactory.openSession();
            Query q = sessionHB.createQuery("delete from ObjectionActionTray where sno=" + oat.getSno());
            q.executeUpdate();
        } catch (Exception ex) {
            ex.printStackTrace();
            logger.info("[ObjectionServiceImpl.removeObjetionTray] Exception : " + ex.getMessage());
        } finally {
            if (sessionHB != null) {
                sessionHB.flush();
                sessionHB.clear();
                sessionHB.close();
            }
        }

        return oat;
    }

    @Override
    public List<Usermaster> userList() {
        Session sessionHB = null;
        Criteria criteria = null;
        // logger.info("data");
        List<Usermaster> useMaster = new ArrayList<Usermaster>();
        List<String> userPermission = new ArrayList<String>();
        try {

            sessionHB = sessionFactory.openSession();
            criteria = sessionHB.createCriteria(UserPermission.class);
            criteria.add(Restrictions.eq("permissionId", 5));
            criteria.add(Restrictions.eq("active", "Y"));
            criteria.setProjection(Projections.property("userRoleId"));
            userPermission = criteria.list();
            String[] userPermissionArray = userPermission.toArray(new String[userPermission.size()]);
            if (userPermissionArray.length == 0) {
                userPermissionArray = new String[2];
                userPermissionArray[0] = "0";
            }
            useMaster = sessionHB.createCriteria(Usermaster.class).add(
                    Restrictions.and(
                            Restrictions.eq("status", "Y"),
                            Restrictions.in("roleId", userPermissionArray)
                    )
            ).list();

        } catch (Exception e) {
            e.printStackTrace();
            logger.info(e.getMessage());
        } finally {
            if (sessionHB != null) {
                sessionHB.close();
                criteria = null;
            }
        }

        return useMaster;
    }

    @Override
    public List<ObjectionActionHistory> getObjectionActionHistory(ResolutionBean resolutionBean) {

        Session sessionHB = null;
        Criteria criteria = null;
        List<ObjectionActionHistory> oat = null;
        try {
            sessionHB = sessionFactory.openSession();
            criteria = sessionHB.createCriteria(ObjectionActionHistory.class);
            criteria.add(Restrictions.eq("objectionId", resolutionBean.getObjectionId()));
            criteria.add(Restrictions.eq("propertyId", resolutionBean.getPropertyId()));
            criteria.addOrder(Order.asc("entrydatetime"));
            oat = criteria.list();

        } catch (Exception ex) {
            ex.printStackTrace();
            logger.info("[ObjectionServiceImpl.getObjetionTray] Exception : " + ex.getMessage());
        } finally {
            if (sessionHB != null) {
                sessionHB.flush();
                sessionHB.clear();
                sessionHB.close();
            }
        }

        return oat;
    }

    @Override
    public List objectionInMyTray(JSONObject jo) throws Exception {

        Session sessionHB = null;
        Criteria criteria = null;
        List<PropertyDetails> results = null;

        try {

            List<String> lsProperty = casesInMyTray(jo);
            if (lsProperty == null || lsProperty.size() < 1) {
                return results;
            }
            sessionHB = sessionFactory.openSession();
            criteria = sessionHB.createCriteria(PropertyDetails.class);
            criteria.add(Restrictions.in("propertyUniqueId", lsProperty));
            results = (List<PropertyDetails>) criteria.list();

        } catch (Exception e) {
            logger.info(e.getMessage());
        } finally {
            if (sessionHB != null) {
                sessionHB.flush();
                sessionHB.close();
            }

        }
        return results;
    }

    public List<String> casesInMyTray(JSONObject jo) throws Exception {

        Session sessionHB = null;
        Criteria criteria = null;
        List<String> lsProperty = null;
        try {

            sessionHB = sessionFactory.openSession();
            criteria = sessionHB.createCriteria(ObjectionActionTray.class);
            criteria.add(Restrictions.eq("trayOwner", jo.get("userId")));
            criteria.setProjection(Projections.projectionList().add(Projections.property("propertyId")));

            lsProperty = criteria.list();

        } catch (Exception e) {
            logger.info(e.getMessage());
        } finally {
            if (sessionHB != null) {
                sessionHB.flush();
                sessionHB.close();
            }

        }
        return lsProperty;
    }

    @Override
    public String checkNoticeGeneratedOrNOt(String propId) {

        Session sessionHB = null;
        TAXDetailBean tAXDetailBean = null;

        try {

            sessionHB = sessionFactory.openSession();
            Criteria criteria = sessionHB.createCriteria(TAXDetailBean.class);

            criteria.add(Restrictions.eq("noticeGenerated", "Y"));
            criteria.add(Restrictions.eq("propertyId", propId));
            criteria.add(Restrictions.eq("financialYear", MMIUtil.getCurrentFinancialYear()));

            tAXDetailBean = (TAXDetailBean) criteria.uniqueResult();

        } catch (Exception ex) {
            logger.info("[ObjectionServiceImpl.checkNoticeGeneratedOrNOt] Exception : " + ex.getMessage());

        } finally {
            if (sessionHB != null) {
                sessionHB.flush();
                sessionHB.clear();
                sessionHB.close();
            }
        }
        if (null == tAXDetailBean) {
            return "N";
        } else {
            return "Y";
        }
    }
}
