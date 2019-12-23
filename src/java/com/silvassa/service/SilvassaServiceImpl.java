/**
 *
 */
package com.silvassa.service;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Set;

import javax.servlet.http.HttpSession;

import org.hibernate.Criteria;
import org.hibernate.Query;
import org.hibernate.SQLQuery;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.criterion.Projections;
import org.hibernate.criterion.Restrictions;
import org.json.simple.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

import com.google.gson.Gson;
import com.itextpdf.text.log.SysoLogger;
//import com.silvasa.model.PropertyTaxDetails;
import com.silvassa.bean.FilterBean;
import com.silvassa.bean.PermissionBean;
import com.silvassa.bean.PrivateNoticeBean;
import com.silvassa.bean.PropertyAssessmentBean;
import com.silvassa.bean.PropertyDetailsBean;
import com.silvassa.bean.PropertyDetailsForAssessmentList;
import com.silvassa.bean.PropertyFloorForAssessmentList;
import com.silvassa.model.ActionTracker;
import com.silvassa.model.Localitymaster;
import com.silvassa.model.PropertyDetails;
import com.silvassa.model.PropertyFloor;
import com.silvassa.model.QrcodePaymentBean;
import com.silvassa.model.SlJobAllocation;
import com.silvassa.model.SubLocality;
import com.silvassa.model.TAXDetailBean;
import com.silvassa.model.TAXDetailBeanOldTax;
import com.silvassa.model.UserPermission;
import com.silvassa.model.UserRegistration;
import com.silvassa.model.Usermaster;
import com.silvassa.model.Wardmaster;
import com.silvassa.model.Zonemaster;
import com.silvassa.tax.dao.TaxCollectionDAOImpl;
import com.silvassa.tax.dao.TaxDaoImpl;
import com.silvassa.util.MMIOTPUtil;
import com.silvassa.util.MMIUtil;
import java.text.DecimalFormat;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.Iterator;
import org.apache.commons.lang.StringUtils;
import org.apache.log4j.Logger;
import org.hibernate.criterion.Order;
import org.springframework.beans.factory.annotation.Qualifier;

/**
 * @author ce
 *
 */
@Component
@Service("silvassaService")
public class SilvassaServiceImpl implements SilvassaService {

    @Autowired
    private HttpSession session;

    @Autowired
    private TaxCollectionDAOImpl taxdao;

    @Autowired
    private MMIOTPUtil mmiOTPUtil;

    private static final Logger logger = Logger.getLogger(SilvassaServiceImpl.class);

    /**
     * The session factory.
     */
    private SessionFactory sessionFactory;

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
    public List<Zonemaster> loadAllZones() {
        List<Zonemaster> zList = new ArrayList<Zonemaster>();
        Session sessionHB = null;
        try {
            sessionHB = sessionFactory.openSession();
            Query query = sessionHB.createQuery("from Zonemaster order by zoneName");

            zList = query.list();
            // logger.info("zlist size is " + zList.size());

        } catch (Exception e) {
            logger.info(e.getMessage());
            // TODO: handle exception
        } finally {
            if (sessionHB != null) {
                sessionHB.close();
            }

        }

        // TODO Auto-generated method stub
        return zList;
    }

    @Override
    public List<Wardmaster> loadAllWards() {
        List<Wardmaster> zList = new ArrayList<Wardmaster>();
        Session sessionHB = null;
        try {
            sessionHB = sessionFactory.openSession();
            Query query = sessionHB.createQuery("from Wardmaster order by wardName");

            zList = query.list();
            // logger.info("zlist size is " + zList.size());

        } catch (Exception e) {
            e.printStackTrace();
            logger.info(e.getMessage());
            // TODO: handle exception
        } finally {
            if (sessionHB != null) {
                sessionHB.close();
            }

        }

        // TODO Auto-generated method stub
        return zList;
    }

    @Override
    public List<Localitymaster> loadAllLocality() {
        List<Localitymaster> zList = new ArrayList<Localitymaster>();
        Session sessionHB = null;
        try {
            sessionHB = sessionFactory.openSession();
            Query query = sessionHB
                    .createQuery("from Localitymaster order by localityName");

            zList = query.list();
            // logger.info("zlist size is " + zList.size());

        } catch (Exception e) {
            logger.info(e.getMessage());
            // TODO: handle exception
        } finally {
            if (sessionHB != null) {
                sessionHB.close();
            }

        }

        // TODO Auto-generated method stub
        return zList;
    }

    @SuppressWarnings("unchecked")
    @Override
    public JSONObject getSearchCriteria(String zone_id, String ward, String locality) {
        JSONObject jo = new JSONObject();
        List<PropertyDetails> list = new ArrayList<PropertyDetails>();

        List<String> propIdArr = null;
        List<String> ownerArr = null;
        List<String> occupierArr = null;
        List<String> propertyCategoryArr = null;

        //	Criteria criteria = null;
        Session sessionHB = null;
        try {
            sessionHB = sessionFactory.openSession();

            String hqlpropIdArr = "select distinct(propertyUniqueId) as property_unique_id from PropertyDetails where "
                    + " zone_id = :zone_id and ward = :ward "
                    + " and propertyUniqueId is not null order by propertyUniqueId";
            String hqlownerArr = "select distinct(propertyOwner) as property_owner from PropertyDetails where"
                    + " zone_id = :zone_id and ward = :ward "
                    + " and propertyOwner is not null and trim(propertyOwner) != '' order by propertyOwner";
            String hqloccupierArr = "select distinct(propertyOccupierName) as property_occupier_name from PropertyDetails where"
                    + " zone_id = :zone_id and ward = :ward  "
                    + " and propertyOccupierName is not null and trim(propertyOccupierName) != '' order by propertyOccupierName";

            String hqlpropertyCategoryArr = "select distinct(property_category_desc) as property_category_name from PropertyDetails where"
                    + " zone_id = :zone_id and ward = :ward  "
                    + " and property_category_desc is not null and trim(property_category_desc) != '' order by property_category_desc";

            System.out.println("zone_id : " + zone_id);
            System.out.println("ward : " + ward);
            System.out.println("sublocality : " + locality);

            Query query = sessionHB.createQuery(hqlpropIdArr);
            query.setParameter("zone_id", zone_id);
            query.setParameter("ward", ward);
            query.setResultTransformer(Criteria.ALIAS_TO_ENTITY_MAP);
            propIdArr = query.list();

            query = sessionHB.createQuery(hqlownerArr);
            query.setParameter("zone_id", zone_id);
            query.setParameter("ward", ward);
            query.setResultTransformer(Criteria.ALIAS_TO_ENTITY_MAP);
            ownerArr = query.list();

            query = sessionHB.createQuery(hqloccupierArr);
            query.setParameter("zone_id", zone_id);
            query.setParameter("ward", ward);
            query.setResultTransformer(Criteria.ALIAS_TO_ENTITY_MAP);
            occupierArr = query.list();

            query = sessionHB.createQuery(hqlpropertyCategoryArr);
            query.setParameter("zone_id", zone_id);
            query.setParameter("ward", ward);
            query.setResultTransformer(Criteria.ALIAS_TO_ENTITY_MAP);
            propertyCategoryArr = query.list();

            query = sessionHB.createQuery(hqlpropertyCategoryArr);
            query.setParameter("zone_id", zone_id);
            query.setParameter("ward", ward);
            query.setResultTransformer(Criteria.ALIAS_TO_ENTITY_MAP);
            propertyCategoryArr = query.list();

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
        jo.put("propertyCategoryArr", propertyCategoryArr);
        return jo;
    }

    @Override
    public JSONObject getSearchCriteria1(String zone_id, String ward, String locality) {
        JSONObject jo = new JSONObject();
        List<PropertyDetails> list = new ArrayList<PropertyDetails>();

        List<String> propIdArr = null;
        List<String> ownerArr = null;
        List<String> occupierArr = null;

        //	Criteria criteria = null;
        Session sessionHB = null;
        try {
            sessionHB = sessionFactory.openSession();

            String hqlpropIdArr = "select distinct(propertyUniqueId) as property_unique_id from PropertyDetails where "
                    + " zone_id = :zone_id "
                    + " and propertyUniqueId is not null order by propertyUniqueId";
            String hqlownerArr = "select distinct(propertyOwner) as property_owner from PropertyDetails where"
                    + " zone_id = :zone_id "
                    + " and propertyOwner is not null and trim(propertyOwner) != '' order by propertyOwner";
            String hqloccupierArr = "select distinct(propertyOccupierName) as property_occupier_name from PropertyDetails where"
                    + " zone_id = :zone_id "
                    + " and propertyOccupierName is not null and trim(propertyOccupierName) != '' order by propertyOccupierName";

            System.out.println("zone_id : " + zone_id);
            System.out.println("ward : " + ward);
            System.out.println("sublocality : " + locality);

            Query query = sessionHB.createQuery(hqlpropIdArr);
            query.setParameter("zone_id", zone_id);
            query.setResultTransformer(Criteria.ALIAS_TO_ENTITY_MAP);
            propIdArr = query.list();

            query = sessionHB.createQuery(hqlownerArr);
            query.setParameter("zone_id", zone_id);
            query.setResultTransformer(Criteria.ALIAS_TO_ENTITY_MAP);
            ownerArr = query.list();

            query = sessionHB.createQuery(hqloccupierArr);
            query.setParameter("zone_id", zone_id);
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

    @Override
    public List<PropertyDetails> showPropertyDetails(String zone_id, String property_id, String owner_name, String occ_name,
            String ward, String locality, String aadharNum, String category, String prop_id_input, String phone_no, String sub_locality, String houseNo) {
        Session sessionHB = null;
        Criteria criteria = null;
        List<PropertyDetails> results = null;

        // logger.info("zone id is ->" + Zone_id + " Owner -->" + owner_name + "  Prop id is -->" + property_id);
        try {
            sessionHB = sessionFactory.openSession();
            criteria = sessionHB.createCriteria(PropertyDetails.class);

            if (prop_id_input != null && !prop_id_input.isEmpty()) {
                criteria.add(Restrictions.eq("propertyUniqueId", prop_id_input));
            }
            if (ward != null && !ward.isEmpty()) {
                criteria.add(Restrictions.eq("ward", ward));
            }
            if (owner_name != null && !owner_name.isEmpty()) {
                criteria.add(Restrictions.eq("propertyOwner", owner_name));
            }
            if (occ_name != null && !occ_name.isEmpty()) {
                criteria.add(Restrictions.eq("propertyOccupierName", occ_name));
            }
            if (phone_no != null && !phone_no.isEmpty()) {
                criteria.add(Restrictions.eq("propertyContact", phone_no));
            }
            if (locality != null && !locality.isEmpty()) {
                criteria.add(Restrictions.eq("propertyLocality", locality));
            }
            if (sub_locality != null && !sub_locality.isEmpty()) {
                criteria.add(Restrictions.eq("propertySublocality", sub_locality));
            }
            if (aadharNum != null && !aadharNum.isEmpty()) {
                criteria.add(Restrictions.eq("propertyAadharNum", aadharNum));
            }
            if (category != null && !category.isEmpty()) {
                criteria.add(Restrictions.eq("property_category_desc", category));
            }
            if (houseNo != null && !houseNo.isEmpty()) {
                criteria.add(Restrictions.eq("propertyHouseNo", houseNo));
            }
            results = (List<PropertyDetails>) criteria.list();

            // logger.info("--->new prop list is " + results.toString());
        } catch (Exception e) {
            logger.info(e.getMessage());
            // TODO: handle exception
        } finally {
            if (sessionHB != null) {
                sessionHB.close();
            }
        }
        return results;
    }

    @Override
    public List<TAXDetailBean> searchTAXAmount(Set propIds, String financialYr) {
        Criteria criteria = null;
        Session sessionHB = null;
        List<TAXDetailBean> pList = null;

        try {
            sessionHB = sessionFactory.openSession();

            criteria = sessionHB.createCriteria(TAXDetailBean.class);
            criteria.add(Restrictions.in("propertyId", propIds));
            criteria.add(Restrictions.eq("financialYear", financialYr));

            pList = (List<TAXDetailBean>) criteria.list();
            Map<String, String> mapOldTax = searchOldTax(propIds);

            for (TAXDetailBean tAXDetailBean : pList) {
                logger.info(tAXDetailBean.getPropertyId() + ", " + mapOldTax.get(tAXDetailBean.getPropertyId()));

                if (mapOldTax.get(tAXDetailBean.getPropertyId()) == null || mapOldTax.get(tAXDetailBean.getPropertyId()).equals("")) {
                    tAXDetailBean.setOldtaxAmount("0");
                } else {
                    tAXDetailBean.setOldtaxAmount(mapOldTax.get(tAXDetailBean.getPropertyId()));
                }
            }

        } catch (Exception e) {
            e.printStackTrace();
//            logger.info(e.getMessage());
            // TODO: handle exception
        } finally {
            if (sessionHB != null) {
                sessionHB.flush();
                sessionHB.close();
            }
        }

        return pList;

    }

    public Map<String, String> searchOldTax(Set propIds) {
        Criteria criteria = null;
        Session sessionHB = null;
        List<TAXDetailBeanOldTax> pList = null;
        Map<String, String> map = new HashMap<String, String>();
        try {
            sessionHB = sessionFactory.openSession();

            criteria = sessionHB.createCriteria(TAXDetailBeanOldTax.class);
            criteria.add(Restrictions.in("propertyId", propIds));
            criteria.add(Restrictions.eq("financialYear", "2017-2018"));

            pList = (List<TAXDetailBeanOldTax>) criteria.list();
            for (TAXDetailBeanOldTax TAXDetailBeanOldTax : pList) {
                logger.info(TAXDetailBeanOldTax.getPropertyId() + ", " + TAXDetailBeanOldTax.getOldtaxAmount());
                map.put(TAXDetailBeanOldTax.getPropertyId(), TAXDetailBeanOldTax.getOldtaxAmount());
            }
        } catch (Exception e) {
            logger.info(e.getMessage());
            // TODO: handle exception
        } finally {
            if (sessionHB != null) {
                sessionHB.flush();
                sessionHB.close();
            }
        }

        return map;

    }

    @Override
    public List<PropertyFloor> getPropertyFloorDetails(String property_unique_id) {
        Criteria criteria = null;
        Session sessionHB = null;
        List<PropertyFloor> PfzList = null;
        try {
            sessionHB = sessionFactory.openSession();

            criteria = sessionHB.createCriteria(PropertyFloor.class);
            criteria.add(Restrictions.eq("propertyUniqueId", property_unique_id));

            PfzList = criteria.list();

        } catch (Exception e) {
            logger.info(e.getMessage());
        } finally {
            if (sessionHB != null) {
                sessionHB.close();
            }
        }

        return PfzList;
    }

    @Override
    public int updatePropertyDetails(PropertyDetailsBean propBean) {
        Session sessionHB = null;
        int result = 0;
        System.out
                .println("in impl propBean.getProperty_smc_tax_payee()----- >"
                        + propBean.getProperty_smc_tax_payee());
        try {
            sessionHB = sessionFactory.openSession();
            // //logger.infoln("update values are->"
            // + propBean.getProperty_smc_tax_payee() + "#####"
            // + propBean.getProperty_unique_id());
            // String sql = " update property_details "
            // +
            // "set property_smc_tax_payee = '"+propBean.getProperty_smc_tax_payee()+"' "
            // + ",created_by ='"+propBean.getUsername()+"'"
            // +
            // "where property_unique_id='"+propBean.getProperty_unique_id()+"' ";

            String sql = "update property_details "
                    + "set property_smc_tax_payee='"
                    + propBean.getProperty_smc_tax_payee()
                    + "', "
                    + "property_house_no='"
                    + propBean.getProp_house_no()
                    + "',property_camera_num='"
                    + propBean.getProp_cam_id()
                    + "',property_plot_num='"
                    + propBean.getProperty_plot_num()
                    + "',"
                    + "property_survey_num='"
                    + propBean.getProperty_survey_num()
                    + "',"
                    + "property_sublocality='"
                    + propBean.getProp_sub_locality_id()
                    + "',property_landmark='"
                    + propBean.getProp_landmark_id()
                    + "',property_road='"
                    + propBean.getProperty_road()
                    + "',"
                    + "property_pincode='"
                    + propBean.getProperty_pincode()
                    + "'"
                    + "where property_unique_id='"
                    + propBean.getProperty_unique_id() + "'";
            // +
            // ",property_usage_details='"+propBean.getProperty_usage_details()+"',property_age_of_building='"+propBean.getProperty_age_of_building()+"',"
            // +
            // "prop_cost_id='"+propBean.getProp_cost_id()+"',prop_ownername_id='"+propBean.getProp_ownername_id()+"',prop_mobile_id='"+propBean.getProp_mobile_id()+"',"
            // +
            // "prop_father_id='"+propBean.getProp_father_id()+"',prop_spouse_id='"+propBean.getProp_spouse_id()+"',prop_occupier_id='"+propBean.getProp_occupier_id()+"',"
            // +
            // "prop_relation_id='"+propBean.getProp_relation_id()+"',owner_adhar_id='"+propBean.getOwner_adhar_id()+"',owner_email_id='"+propBean.getOwner_email_id()+"',"
            // +
            // "owner_floors_id='"+propBean.getOwner_floors_id()+"',owner_rooms_id='"+propBean.getOwner_rooms_id()+"',ca_pipe_id='"+propBean.getCa_pipe_id()+"',"
            // +
            // "ca_sew_id='"+propBean.getCa_sew_id()+"',ca_sew_no_id='"+propBean.getCa_sew_no_id()+"',ca_elec_id='"+propBean.getCa_elec_id()+"',ca_elec_no_id='"+propBean.getCa_elec_no_id()+"',"
            // +
            // "ca_ccCam_id='"+propBean.getCa_ccCam_id()+"',ca_hording_id='"+propBean.getCa_hording_id()+"',ca_fire_id='"+propBean.getCa_fire_id()+"',ca_lift_id='"+propBean.getCa_lift_id()+"',"
            // +
            // "ca_borewell_id='"+propBean.getCa_borewell_id()+"',ca_mobiletower_id='"+propBean.getCa_mobiletower_id()+"',ca_rainharvest_id='"+propBean.getCa_rainharvest_id()+"',"
            // +
            // "prop_floor_room_cnt_hotel='"+propBean.getProp_floor_room_cnt_hotel()+"',prop_floor_room_cnt_school_clg='"+propBean.getProp_floor_room_cnt_school_clg()+"',"
            // +
            // "prop_floor_room_cnt_hospi_nurse='"+propBean.getProp_floor_room_cnt_hospi_nurse()+"',prop_floor_room_cnt_hostel='"+propBean.getProp_floor_room_cnt_hostel()+"', "
            // +
            // "floor_type_desc='"+propBean.getFloor_type_desc()+"',prop_floor_plot_area='"+propBean.getProp_floor_plot_area()+"',"
            // +
            // "prop_floor_construction_type='"+propBean.getProp_floor_construction_type()+"',prop_floor_usage_type='"+propBean.getProp_floor_usage_type()+"',"
            // +
            // "sanitation_type_desc='"+propBean.getSanitation_type_desc()+"' "

            // logger.info("update query-->" + sql);
            Query query = sessionHB.createSQLQuery(sql);
            result = query.executeUpdate();
            // //logger.infoln("update result is " + result);

        } catch (Exception e) {
            logger.info(e.getMessage());

        } finally {
            if (sessionHB != null) {
                sessionHB.close();
            }

        }
        return result;
    }

    //  @Override -1111
//    public List<Map<String, Object>> calculateTax(String zoneid, String prop_id, String type) {
//        // TODO Auto-generated method stub
//        List<Map<String, Object>> response = new ArrayList<Map<String, Object>>();
//        List<Generatedtax> taxList = new ArrayList<Generatedtax>();
//        Session sessionHB = null;
//        List<Map<String, Object>> resp = new ArrayList<Map<String, Object>>();
//        sessionHB = sessionFactory.openSession();
//        String sql = "";
//        Criteria criteria = null;
//        List<PropertyFloor> PfList = null;
//        Transaction trans = sessionHB.beginTransaction();
//        try {
//
//            Date now = new Date();
//            SimpleDateFormat formatter = new SimpleDateFormat("yyyy-dd-MM");
//            if (type.equalsIgnoreCase("calculate")) {
//
//                // criteria = sessionHB.createCriteria(PropertyFloor.class,
//                // "pf");
//                // if(!zoneid.isEmpty()){
//                // criteria.add(Restrictions.eq("pf.propertyDetails.zoneId",
//                // zoneid));
//                // }
//                // PfList = criteria.list();
//                sql = "select  property_unique_id,pf_construction_type,pf_floor_name, pf_builtup_area, property_sub_cat, property_cat, multiplication_factor, rentable_value, ((C * multiplication_factor)/100) "
//                        + " from ( select *, (B *  12) as C from ( select *, (A - ((10 * A) / 100)) as B "
//                        + " from ( select property_unique_id,pf_construction_type ,pf_floor_name, pf_builtup_area, property_sub_cat, property_cat, multiplication_factor, rentable_value, (CAST(pf_builtup_area AS numeric) * rentable_value) as A  "
//                        + " from property_floor flt left join property_rentable pr on flt.property_rentable_id = pr.property_rentable_id"
//                        + " order by property_unique_id) A_level) B_level) C_level";
//
//                // //logger.infoln("qri si " + sql);
//                Query query = sessionHB.createSQLQuery(sql);
//                query.setResultTransformer(Criteria.ALIAS_TO_ENTITY_MAP);
//                resp = query.list();
//
//                // List<PropertyTaxDetails> detailList = new
//                // ArrayList<PropertyTaxDetails>();
//                for (int i = 0; i < resp.size(); i++) {
//
//                }
//                // trans.commit();
//                // Inserting into generate tax table.
//                // logger.info("calling insert through Executor....");
//                // TaxGeneratedLog taxBean = new TaxGeneratedLog();
//                // ApplicationUtil util = new ApplicationUtil();
//                // util.insertUpdate(taxList, sessionHB, taxBean);
//
//                response = null;
//                return response;
//            } else {
//                // //logger.infoln("inside viewTax");
//                // HERE VIEW QUERY WILL BE THERE
//
//                sql = "";
//				// "SELECT gt.unique_tax_id, gt.property_unique_id, gt.tax_amount,"
//                // +
//                // "       gt.built_up_area, gt.rentable_value, gt.generatedby, gt.generatedon, gt.zonename,"
//                // +
//                // "       gt.remarks,po.prop_owner_occupier,po.prop_owner_name,"
//                // +
//                // "       pd.property_house_no, pd.property_road,pd.property_sublocality,pd.property_building_name"
//                // +
//                // "       FROM generatedtax gt,property_details pd, localitymaster lm, property_owner po ,wardmaster wm"
//                // +
//                // "       where gt.property_unique_id = pd.property_unique_id AND wm.ward_id = pd.ward_id  "
//                // +
//                // " 		AND  lm.locality_id = pd.locality_id AND  po.property_details_id = pd.property_details_id  "
//                // + "		AND pd.zone_id='"
//                // + zoneid.trim()
//                // + "'"
//                // + " "
//                // + wd.trim() + lc.trim() + pp.trim();
//
//                // logger.info("qri si " + sql);
//                Query query = sessionHB.createSQLQuery(sql);
//                query.setResultTransformer(Criteria.ALIAS_TO_ENTITY_MAP);
//                resp = query.list();
//
//                for (int i = 0; i < resp.size(); i++) {
//                    Map<String, Object> tmp = resp.get(i);
//                    Map<String, Object> tmp2 = new HashMap<String, Object>();
//                    String prop_owner_name = tmp.get("prop_owner_name") == null ? ""
//                            : (String) tmp.get("prop_owner_name");
//                    String prop_owner_occupier = tmp.get("prop_owner_occupier") == null ? ""
//                            : (String) tmp.get("prop_owner_occupier");
//                    String unique_id = tmp.get("property_unique_id") == null ? ""
//                            : (String) tmp.get("property_unique_id");
//
//                    String building_name = tmp.get("property_building_name") == null ? ""
//                            : (String) tmp.get("property_building_name");
//                    String house_no = tmp.get("property_house_no") == null ? ""
//                            : (String) tmp.get("property_house_no");
//                    String road = tmp.get("property_road") == null ? ""
//                            : (String) tmp.get("property_road");
//                    String sublocality = tmp.get("property_sublocality") == null ? ""
//                            : (String) tmp.get("property_sublocality");
//                    String zone = tmp.get("zonename") == null ? ""
//                            : (String) tmp.get("zonename");
//                    String tax_amount = tmp.get("tax_amount") == null ? ""
//                            : (String) tmp.get("tax_amount");
//                    String generatedby = tmp.get("generatedby") == null ? ""
//                            : (String) tmp.get("generatedby");
//                    Object generatedon = tmp.get("generatedon") == null ? ""
//                            : (Object) tmp.get("generatedon");
//
//                    tmp2.put("poName", prop_owner_name + "-"
//                            + prop_owner_occupier);
//                    tmp2.put("uid", unique_id);
//                    tmp2.put("address", house_no + ", " + building_name + ", "
//                            + road + ", " + sublocality);
//                    tmp2.put("zone", zone);
//                    tmp2.put("taxAmt", tax_amount);
//                    tmp2.put("generatedby", generatedby);
//                    tmp2.put("generatedon", generatedon);
//                    response.add(tmp2);
//                }
//            }
//        } catch (Exception e) {
//            logger.info(e.getMessage());
//            // TODO: handle exception
//        } finally {
//            if (sessionHB != null) {
//                sessionHB.close();
//            }
//        }
//        return response;
//    }
    @Override
    public List<String> loadAllProperty() {
        // TODO Auto-generated method stub

        Session sessionHB = null;
        List<String> resp = new ArrayList<String>();
        sessionHB = sessionFactory.openSession();
        try {
            String sql = "select distinct property_id from property_details where property_id is not null  order by property_id";
            Query query = sessionHB.createSQLQuery(sql);
            query.setResultTransformer(Criteria.ALIAS_TO_ENTITY_MAP);
            resp = query.list();
        } catch (Exception e) {
            logger.info(e.getMessage());
            // TODO: handle exception
        } finally {
            if (sessionHB != null) {
                sessionHB.close();
            }
        }
        return resp;
    }

    @Override
    public List<Map<String, Object>> loadOwners(String property_id) {
        // TODO Auto-generated method stub

        Session sessionHB = null;
        List<Map<String, Object>> resp = new ArrayList<Map<String, Object>>();
        sessionHB = sessionFactory.openSession();
        try {
            String sql = "select po.prop_owner_master_id, po.prop_owner_name from property_details pd, property_owner po where po.property_details_id = pd.property_details_id and "
                    + "pd.property_id='"
                    + property_id
                    + "' "
                    + "order by po.prop_owner_name";
            Query query = sessionHB.createSQLQuery(sql);
            query.setResultTransformer(Criteria.ALIAS_TO_ENTITY_MAP);
            resp = query.list();
        } catch (Exception e) {
            logger.info(e.getMessage());
            // TODO: handle exception
        } finally {
            if (sessionHB != null) {
                sessionHB.close();
            }
        }
        return resp;
    }

    @Override
    public int checkGeneratedTax(String zone_id) {
        // TODO Auto-generated method stub
        int response = 0;
        Session sessionHB = null;
        sessionHB = sessionFactory.openSession();
        try {

            StringBuilder sql = new StringBuilder();
            sql.append("SELECT zoneid, generatedby, generatedon  FROM tax_generated_log where zoneid='" + zone_id + "'");
            // if (ward_id != "") {
            // sql.append(" and ward='" + ward_id + "'");
            // }
            //
            // if (loc_id != "") {
            // sql.append(" and locality='" + loc_id + "'");
            // }

            Query query = sessionHB.createSQLQuery(sql.toString());
            query.setResultTransformer(Criteria.ALIAS_TO_ENTITY_MAP);
            List<Map<String, Object>> resp = query.list();
            logger.info("-->" + resp.size());
            if (resp.size() > 0) {
                response = 1;
            }

        } catch (Exception e) {
            response = 0;
            logger.info(e.getMessage());
            // TODO: handle exception
        } finally {
            if (sessionHB != null) {
                sessionHB.flush();
                sessionHB.close();
            }
        }

        return response;
    }

    @Override
    public List<TAXDetailBean> getTaxDetails(String property_unique_id) {

        Session sessionHB = null;
        sessionHB = sessionFactory.openSession();
        Criteria criteria = null;
        List<TAXDetailBean> pList = null;
        try {
            criteria = sessionHB.createCriteria(TAXDetailBean.class);
            criteria.add(Restrictions.eq("propertyId", property_unique_id));
            pList = criteria.list();
        } catch (Exception e) {
            logger.info(e.getMessage());
        } finally {
            if (sessionHB != null) {
                sessionHB.flush();
                sessionHB.close();
            }
        }
        return pList;
    }

    @Override
    public List<TAXDetailBean> viewTax(List<PropertyDetails> propList) {
        // TODO Auto-generated method stub
        List<TAXDetailBean> ptlist = null;
        List<String> pt = null;
        Session sessionHB = null;
        Criteria criteria = null;

        try {

            if (propList == null || propList.size() < 1) {
                return ptlist;
            }
            pt = new ArrayList<String>();
            for (PropertyDetails pd : propList) {
                pt.add(pd.getPropertyUniqueId());
            }

            sessionHB = sessionFactory.openSession();
            criteria = sessionHB.createCriteria(TAXDetailBean.class);
            criteria.add(Restrictions.in("propertyId", pt));
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
    public List<PropertyDetails> filterProperty(String taxObj) {

        List<PropertyDetails> ptlist = null;
        Session sessionHB = null;
        Criteria criteria = null;

        try {

            JSONObject jo = new Gson().fromJson(taxObj, JSONObject.class);
            sessionHB = sessionFactory.openSession();
            criteria = sessionHB.createCriteria(PropertyDetails.class);
            //criteria.setProjection(Projections.projectionList().add(Projections.property("propertyUniqueId").as("propertyUniqueId")));

//            if (!jo.get("zoneid").equals("-1")) {
//                criteria.add(Restrictions.eq("zoneId", jo.get("zoneid")));
//                if (!jo.get("wardid").equals("-1")) {
//                    criteria.add(Restrictions.eq("ward", jo.get("wardid")));
//                }
//            } else if (!jo.get("prop_id").equals("")) {
//                criteria.add(Restrictions.eq("propertyUniqueId", jo.get("prop_id")));
//            }
            if (!jo.get("prop_id").toString().isEmpty() && jo.get("prop_id").toString() != null) {
                criteria.add(Restrictions.eq("propertyUniqueId", jo.get("prop_id").toString()));
            }
            if (!jo.get("wardid").toString().isEmpty() && jo.get("wardid").toString() != null) {
                criteria.add(Restrictions.eq("ward", jo.get("wardid").toString()));
            }
            if (!jo.get("owner_id").toString().isEmpty() && jo.get("owner_id").toString() != null) {
                criteria.add(Restrictions.eq("propertyOwner", jo.get("owner_id").toString()));
            }
            if (!jo.get("occ").toString().isEmpty() && jo.get("occ").toString() != null) {
                criteria.add(Restrictions.eq("propertyOccupierName", jo.get("occ").toString()));
            }
            if (!jo.get("Phone_no").toString().isEmpty() && jo.get("Phone_no").toString() != null) {
                criteria.add(Restrictions.eq("propertyContact", jo.get("Phone_no").toString()));
            }
            if (!jo.get("locality").toString().isEmpty() && jo.get("locality").toString() != null) {
                criteria.add(Restrictions.eq("propertyLocality", jo.get("locality").toString()));
            }
            if (!jo.get("Locality").toString().isEmpty() && jo.get("Locality").toString() != null) {
                criteria.add(Restrictions.eq("propertySublocality", jo.get("Locality").toString()));
            }
            if (!jo.get("aadhar_num").toString().isEmpty() && jo.get("aadhar_num").toString() != null) {
                criteria.add(Restrictions.eq("propertyAadharNum", jo.get("aadhar_num").toString()));
            }
            if (!jo.get("propertyCategory").toString().isEmpty() && jo.get("propertyCategory").toString() != null) {
                criteria.add(Restrictions.eq("property_category_desc", jo.get("propertyCategory").toString()));
            }
            ptlist = (List<PropertyDetails>) criteria.list();

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

    public String getRemarks(String uid) {
        Session sessionHB = null;
        StringBuffer sb = new StringBuffer();
        String msg = "";
        String allMsg = "";
        String correction_id = "";
        String action_taken = "";
        try {
            sessionHB = sessionFactory.openSession();
            sb.append("Select property_id,correction_id, action_taken, action_remarks from correction_action_history where property_id='" + uid + "'   ");
            Query query = sessionHB.createSQLQuery(sb.toString());
            List<Object[]> rows = query.list();
            for (Object[] row : rows) {
                if (row[2] != null) {
                    action_taken = row[2].toString();
                    if (action_taken.equalsIgnoreCase("applied") || action_taken.equalsIgnoreCase("reject")) {
                        if (row[1] != null) {
                            correction_id = correction_id + row[1] + ",";
                        } else {
                            correction_id = "";
                        }
                        if (row[3] == null) {
                            msg = "";
                        } else {
                            msg = msg + String.valueOf(row[3]) + ",";
                        }

                    }

                } else {
                    action_taken = "";
                }

            }
            if (correction_id.length() > 0) {
                allMsg = allMsg + correction_id + " ";
            }
            if (msg.length() > 0) {
                allMsg = allMsg + msg;
            }

        } catch (Exception ex) {
            logger.info("[get msg form correction   history Exception : " + ex.getMessage());
        } finally {
            if (sessionHB != null) {
                sessionHB.flush();
                sessionHB.clear();
                sessionHB.close();
            }
        }

        return allMsg;

    }

    @Override
    public List viewAssessmentList(String taxObj) {

        //System.out.println("assessment list");
        List<PropertyDetails> ptlist = null;
        ArrayList<PropertyDetailsForAssessmentList> propertyDetailArr = null;
        List<PropertyAssessmentBean> assessmentBean = new ArrayList<PropertyAssessmentBean>();
        Session sessionHB = null;
        Criteria criteria = null;
        StringBuffer sb = new StringBuffer();
        StringBuffer sb1 = new StringBuffer();
        StringBuffer sb2 = new StringBuffer();
        int recordCtr = 0;
        int idCount = 0;
        int idCountFloor = 0;
        String zone = "";
        String ward = "";
        String propId = "";

        try {

            JSONObject jo = new Gson().fromJson(taxObj, JSONObject.class);

            sb.append(" select pd.property_unique_id,pd.complete_address,pd.zone_id,pd.ward,pd.property_total_floor, ");
            sb.append(" pd.property_owner,pd.property_owner_father,pd.property_owner_spouse,pd.property_relation_owner,pd.property_occupier_name,ptx.property_tax,ptx.arrear_amount,pd.property_old_smc_prop_tax_num,pd.property_contact,pd.property_owner_email,ptx.oldtax,pd.occupier_father,pd.occupier_contactno,pd.occupier_email,pd.smc_type,pd.holder_Name ");
            sb.append(" from property_tax ptx, property_details pd where ptx.property_unique_id=pd.property_unique_id   and ptx.financial_year='2018-2019' ");

            sb1.append(" select f.property_unique_id, f.pf_floor_name,f.pf_builtup_area,f.pf_construction_Type,");
            sb1.append(" f.pf_floorwise_build_use,f.prop_class,f.property_rentable_id,d.rentable_value,d.multiplication_factor_percent as multiplication_factor,(d.rentable_value*d.percentbuilduparea)annualRatableValue,");
            sb1.append(" d.percentbuilduparea,pfd.floor_tax_amount,f.pf_self_rent,f.pf_property_subtype,f.pf_electric_con_num from property_tax ptx,property_details pd, property_floor  f,property_rentable d, property_tax_details  pfd ");
            sb1.append(" where ptx.property_unique_id=pd.property_unique_id  and pd.property_unique_id=f.property_unique_id and f.pf_id=pfd.floor_id ");
            sb1.append(" and f.property_rentable_id=d.property_rentable_id and ptx.financial_year='2018-2019' ");

            sb2.append(" select pd.property_unique_id,count(*) as id_count from property_details pd,property_floor pf where pd.property_unique_id=pf.property_unique_id   ");

            /*  if (!jo.get("zoneid").equals("-1")) {
             zone = (String) jo.get("zoneid");
             if (!jo.get("wardid").equals("-1")) {
             ward = (String) jo.get("wardid");
             sb.append(" select pd.property_unique_id,pd.complete_address,pd.zone_id,pd.ward,pd.property_total_floor, ");
             sb.append(" pd.property_owner,pd.property_owner_father,pd.property_owner_spouse,pd.property_relation_owner,pd.property_occupier_name,ptx.property_tax,ptx.arrear_amount,pd.property_old_smc_prop_tax_num,pd.property_contact,pd.property_owner_email,ptx.oldtax,pd.occupier_father,pd.occupier_contactno,pd.occupier_email,pd.smc_type,pd.holder_Name ");
             sb.append(" from property_tax ptx, property_details pd where ptx.property_unique_id=pd.property_unique_id   and ptx.financial_year='2018-2019' ");

             sb1.append(" select f.property_unique_id, f.pf_floor_name,f.pf_builtup_area,f.pf_construction_Type,");
             sb1.append(" f.pf_floorwise_build_use,f.prop_class,f.property_rentable_id,d.rentable_value,d.multiplication_factor_percent as multiplication_factor,(d.rentable_value*d.percentbuilduparea)annualRatableValue,");
             sb1.append(" d.percentbuilduparea,pfd.floor_tax_amount,f.pf_self_rent,f.pf_property_subtype,f.pf_electric_con_num from property_tax ptx,property_details pd, property_floor  f,property_rentable d, property_tax_details  pfd ");
             sb1.append(" where ptx.property_unique_id=pd.property_unique_id  and pd.property_unique_id=f.property_unique_id and f.pf_id=pfd.floor_id ");
             sb1.append(" and f.property_rentable_id=d.property_rentable_id and ptx.financial_year='2018-2019'   ");

             sb2.append(" select pd.property_unique_id,count(*) as id_count from property_details pd,property_floor pf where pd.property_unique_id=pf.property_unique_id  and pd.ward='" + ward + "'  group by pd.property_unique_id  ");

             }
             } else if (!jo.get("prop_id").equals("")) {
             propId = (String) jo.get("prop_id");
             sb.append(" select pd.property_unique_id,pd.complete_address,pd.zone_id,pd.ward,pd.property_total_floor, ");
             sb.append(" pd.property_owner,pd.property_owner_father,pd.property_owner_spouse,pd.property_relation_owner,pd.property_occupier_name,ptx.property_tax,ptx.arrear_amount,pd.property_old_smc_prop_tax_num,pd.property_contact,pd.property_owner_email,ptx.oldtax,pd.occupier_father,pd.occupier_contactno,pd.occupier_email,pd.smc_type,pd.holder_Name ");
             sb.append(" from property_tax ptx, property_details pd where ptx.property_unique_id=pd.property_unique_id  and pd.property_unique_id='" + propId + "' and ptx.financial_year='2018-2019'  order by pd.property_unique_id ");

             sb1.append(" select f.property_unique_id, f.pf_floor_name,f.pf_builtup_area,f.pf_construction_Type,");
             sb1.append(" f.pf_floorwise_build_use,f.prop_class,f.property_rentable_id,d.rentable_value,d.multiplication_factor_percent as multiplication_factor,(d.rentable_value*d.percentbuilduparea)annualRatableValue,");
             sb1.append(" d.percentbuilduparea,pfd.floor_tax_amount,f.pf_self_rent,f.pf_property_subtype,f.pf_electric_con_num from property_tax ptx,property_details pd, property_floor  f,property_rentable d, property_tax_details  pfd ");
             sb1.append(" where ptx.property_unique_id=pd.property_unique_id  and pd.property_unique_id=f.property_unique_id and f.pf_id=pfd.floor_id ");
             sb1.append(" and f.property_rentable_id=d.property_rentable_id and ptx.financial_year='2018-2019'  and f.property_unique_id='" + propId + "' order by pd.property_unique_id,f.flooriwse_short ");

             sb2.append(" select pd.property_unique_id,count(*) as id_count from property_details pd,property_floor pf where pd.property_unique_id=pf.property_unique_id  ");

             }*/
            System.out.println("www" + jo.get("wardid").toString());

            System.out.println("www" + jo.get("prop_id").toString());
            if (!jo.get("wardid").toString().equals("")) {
                sb.append("and pd.ward='" + jo.get("wardid") + "' ");
                sb1.append("and pd.ward='" + jo.get("wardid") + "' ");
                sb2.append("and pd.ward='" + jo.get("wardid") + "' ");

            }
            if (!jo.get("prop_id").toString().equals("")) {
                sb.append("and pd.property_unique_id='" + jo.get("prop_id") + "' ");
                sb1.append("and pd.property_unique_id='" + jo.get("prop_id") + "' ");
                sb2.append("and pd.property_unique_id='" + jo.get("prop_id") + "' ");
            }
            if (!jo.get("occ_name").toString().equals("")) {
                sb.append("and pd.property_occupier_name='" + jo.get("occ_name") + "' ");
                sb1.append("and pd.property_occupier_name='" + jo.get("occ_name") + "' ");
                sb2.append("and pd.property_occupier_name='" + jo.get("occ_name") + "' ");
            }
            if (!jo.get("ownerid").toString().equals("")) {
                sb.append("and pd.property_owner='" + jo.get("ownerid") + "' ");
                sb1.append("and pd.property_owner='" + jo.get("ownerid") + "' ");
                sb2.append("and pd.property_owner='" + jo.get("ownerid") + "' ");
            }
            if (!jo.get("locality").toString().equals("")) {
                sb.append("and pd.property_locality='" + jo.get("locality") + "' ");
                sb1.append("and pd.property_locality='" + jo.get("locality") + "' ");
                sb2.append("and pd.property_locality='" + jo.get("locality") + "' ");
            }
            if (!jo.get("src_aadhar_no").toString().equals("")) {
                sb.append("and pd.property_unique_id='" + jo.get("src_aadhar_no") + "' ");
                sb1.append("and pd.property_unique_id='" + jo.get("src_aadhar_no") + "' ");
                sb2.append("and pd.property_unique_id='" + jo.get("src_aadhar_no") + "' ");
            }
            if (!jo.get("category").toString().equals("")) {
                sb.append("and pd.property_category_desc='" + jo.get("category") + "' ");
                sb1.append("and pd.property_category_desc='" + jo.get("category") + "' ");
                sb2.append("and pd.property_category_desc='" + jo.get("category") + "' ");
            }
            if (!jo.get("Phone_No").toString().equals("")) {
                sb.append("and pd.property_contact='" + jo.get("Phone_No") + "' ");
                sb1.append("and pd.property_contact='" + jo.get("Phone_No") + "' ");
                sb2.append("and pd.property_contact='" + jo.get("Phone_No") + "' ");
            }
            if (!jo.get("Locality").toString().equals("")) {
                sb.append("and pd.property_sublocality='" + jo.get("Locality") + "' ");
                sb1.append("and pd.property_sublocality='" + jo.get("Locality") + "' ");
                sb2.append("and pd.property_sublocality='" + jo.get("Locality") + "' ");
            }

            sb.append(" order by pd.property_unique_id ");
            sb1.append(" order by pd.property_unique_id,f.flooriwse_short ");
            sb2.append(" group by pd.property_unique_id ");

            System.out.println("sb" + sb);
            System.out.println("sb1" + sb1);
            System.out.println("sb2" + sb2);

            sessionHB = sessionFactory.openSession();
            /*sb.append(" select pd.property_unique_id,pd.complete_address,pd.zone_id,pd.ward,pd.property_total_floor, ");
             sb.append(" pd.property_owner,pd.property_owner_father,pd.property_owner_spouse,pd.property_relation_owner,pd.property_occupier_name,ptx.property_tax,ptx.arrear_amount,pd.property_old_smc_prop_tax_num,pd.property_contact,pd.property_owner_email ");
             sb.append(" from property_tax ptx, property_details pd where ptx.property_unique_id=pd.property_unique_id  and pd.ward='"+ward+"' and ptx.financial_year='2018-2019'  order by pd.property_unique_id ");

             sb1.append(" select f.property_unique_id, f.pf_floor_name,f.pf_builtup_area,f.pf_construction_Type,");
             sb1.append(" f.pf_floorwise_build_use,f.prop_class,f.property_rentable_id,d.rentable_value,d.multiplication_factor_percent as multiplication_factor,(d.rentable_value*d.percentbuilduparea)annualRatableValue,");
             sb1.append(" d.percentbuilduparea,pfd.floor_tax_amount,f.pf_self_rent,f.pf_property_subtype,f.pf_electric_con_num from property_tax ptx,property_details pd, property_floor  f,property_rentable d, property_tax_details  pfd ");
             sb1.append(" where ptx.property_unique_id=pd.property_unique_id  and pd.property_unique_id=f.property_unique_id and f.pf_id=pfd.floor_id ");
             sb1.append(" and f.property_rentable_id=d.property_rentable_id and ptx.financial_year='2018-2019'  and pd.ward='"+ward+"'  order by pd.property_unique_id,f.flooriwse_short ");

             sb2.append(" select pd.property_unique_id,count(*) as id_count from property_details pd,property_floor pf where pd.property_unique_id=pf.property_unique_id  and pd.ward='"+ward+"'  group by pd.property_unique_id  ");*/

            //criteria = sessionHB.createCriteria(PropertyDetails.class);
            //criteria.setProjection(Projections.projectionList().add(Projections.property("propertyUniqueId").as("propertyUniqueId")));
            Query query1 = sessionHB.createSQLQuery(sb.toString());
            Query query2 = sessionHB.createSQLQuery(sb1.toString());
            Query query3 = sessionHB.createSQLQuery(sb2.toString());
            propertyDetailArr = new ArrayList<PropertyDetailsForAssessmentList>();
            List<Object[]> rows = query1.list();
            //System.out.println("gggg "+rows.size());
            for (Object[] row : rows) {
                recordCtr++;
//                System.out.println(" row[0].toString()"+row[0].toString());
//                System.out.println(" row[1].toString()"+row[1].toString());
//                System.out.println(" row[2].toString()"+row[2].toString());
//                System.out.println(" row[3].toString()"+row[3].toString());
//                System.out.println(" row[4].toString()"+row[4].toString());
//                System.out.println(" row[5].toString()"+row[5].toString());
//                System.out.println(" row[6].toString()"+row[6].toString());
//                System.out.println(" row[7].toString()"+row[7].toString());
//                System.out.println(" row[8].toString()"+row[8].toString());
//                System.out.println(" row[9].toString()"+row[9].toString());
//                System.out.println(" row[10].toString()"+row[10].toString());
//                System.out.println(" row[11].toString()"+row[11].toString());
//                System.out.println(" row[12].toString()"+row[12].toString());
//                System.out.println(" row[13].toString()"+row[13].toString());
//                System.out.println(" row[14].toString()"+row[14].toString());

                PropertyDetailsForAssessmentList ptdBean = new PropertyDetailsForAssessmentList();
                ptdBean.setPropertyUniqueId(row[0] == null ? "" : String.valueOf(row[0]));
                ptdBean.setAddress(row[1] == null ? "" : String.valueOf(row[1]));
                ptdBean.setZoneId(row[2] == null ? "" : String.valueOf(row[2]));
                ptdBean.setWard(row[3] == null ? "" : String.valueOf(row[3]));
                ptdBean.setProperty_total_floor(row[4] == null ? "" : String.valueOf(row[4]));

                ptdBean.setPropertyOwner(row[5] == null ? "" : String.valueOf(row[5]));
                if (row[6] == null) {
                    ptdBean.setProperty_owner_father("");
                    // System.out.println(" row[6].toString() if"+row[6].toString());
                } else {
                    ptdBean.setProperty_owner_father(row[6].toString());
                    //System.out.println(" row[6].toString() else "+row[6].toString());
                }

                //ptdBean.setProperty_owner_father(row[6].toString() == null ? "" : String.valueOf(row[6].toString()));
                ptdBean.setProperty_owner_spouse(row[7] == null ? "" : String.valueOf(row[7]));
                ptdBean.setPropertyRelationOwner(row[8] == null ? "" : String.valueOf(row[8]));
                ptdBean.setPropertyOccupierName(row[9] == null ? "" : String.valueOf(row[9]));
                ptdBean.setProperty_tax(row[10] == null ? "" : String.valueOf(row[10]));
                //ptdBean.setArrearAmount(row[11] == null ? "" : String.valueOf(row[11]));
                ptdBean.setProperty_old_smc_prop_tax_num(row[12] == null ? "" : String.valueOf(row[12]));
                ptdBean.setPropertyContact(row[13] == null ? "" : String.valueOf(row[13]));
                ptdBean.setPropertyOwnerEmail(row[14] == null ? "" : String.valueOf(row[14]));
                ptdBean.setOccupier_father(row[16] == null ? "" : String.valueOf(row[16]));
                ptdBean.setOccupier_contactno(row[17] == null ? "" : String.valueOf(row[17]));
                ptdBean.setOccupier_email(row[18] == null ? "" : String.valueOf(row[18]));
                ptdBean.setSmcType(row[19] == null ? "" : String.valueOf(row[19]));
                ptdBean.setHolderName(row[20] == null ? "" : String.valueOf(row[20]));
                //ptdBean.setOldTax(row[15] == null ? "" : String.valueOf(row[15]));
                String rmarks = getRemarks(ptdBean.getPropertyUniqueId());
                String arrarOldtax = getArrearAndOldTax(ptdBean.getPropertyUniqueId());
                //System.out.println("arrarOldtax "+arrarOldtax);
                if (arrarOldtax != null && arrarOldtax.length() > 0) {
                    String old[] = arrarOldtax.split(":");
                    //System.out.println("old "+old[0]+" "+old[1]);
                    ptdBean.setArrearAmount(old[0].toString());
                    ptdBean.setOldTax(old[1].toString());

                }
                //System.out.println("rmarks "+rmarks);
                ptdBean.setCorrectionRemarks(rmarks);
                ptdBean.setSlNo(new Integer(recordCtr).toString());
                //System.out.println(" row[1].toString() end "+row[1].toString());

                propertyDetailArr.add(ptdBean);

            }
            //System.out.println("size "+propertyDetailArr.size());
            ArrayList<String> bFloor = new ArrayList<String>();
            ArrayList<String> cover = new ArrayList<String>();
            ArrayList<String> buse = new ArrayList<String>();
            ArrayList<String> categoryList = new ArrayList<String>();
            ArrayList<String> typeOfConstruction = new ArrayList<String>();
            ArrayList<String> selfRent = new ArrayList<String>();
            ArrayList<String> locationClassese = new ArrayList<String>();
            ArrayList<String> annualRent = new ArrayList<String>();
            ArrayList<String> ratableValue = new ArrayList<String>();
            ArrayList<String> propertyTaxRate = new ArrayList<String>();
            ArrayList<String> htax = new ArrayList<String>();
            ArrayList<String> electric_meter = new ArrayList<String>();

            HashMap<String, ArrayList> hashFloor = new HashMap<String, ArrayList>();
            HashMap<String, ArrayList> hashcover = new HashMap<String, ArrayList>();
            HashMap<String, ArrayList> hashBuse = new HashMap<String, ArrayList>();
            HashMap<String, ArrayList> hashCategoryList = new HashMap<String, ArrayList>();
            HashMap<String, ArrayList> hashTypeOfConstruction = new HashMap<String, ArrayList>();
            HashMap<String, ArrayList> hashSelfRent = new HashMap<String, ArrayList>();
            HashMap<String, ArrayList> hashLocationClassess = new HashMap<String, ArrayList>();
            HashMap<String, ArrayList> hashAnnualRent = new HashMap<String, ArrayList>();
            HashMap<String, ArrayList> hashRatableValue = new HashMap<String, ArrayList>();
            HashMap<String, ArrayList> hashPropertyTaxRate = new HashMap<String, ArrayList>();
            HashMap<String, ArrayList> hashHTax = new HashMap<String, ArrayList>();
            HashMap<String, ArrayList> hashElectricMeter = new HashMap<String, ArrayList>();
            HashMap hashIdCount = new HashMap();

            List<Object[]> rowsCount = query3.list();
            for (Object[] row : rowsCount) {
                if (row[0] != null) {
                    String pid = row[0].toString();
                    String countId = new Integer(row[1].toString()).toString();
                    hashIdCount.put(pid, countId);
                    // System.out.println(" row[6].toString() if"+row[6].toString());
                }
            }
            // System.out.println("hashIdCount "+hashIdCount.size());

            Map floorDetailMap = new HashMap();
            List<Object[]> floorRows = query2.list();

            //System.out.println(" floorRows" +floorRows.size());
            for (Object[] row : floorRows) {
                idCount++;
                PropertyFloorForAssessmentList pfFloor = new PropertyFloorForAssessmentList();

                /*System.out.println(" row[0].toString()"+row[0].toString());
                 System.out.println(" row[1].toString()"+row[1].toString());
                 System.out.println(" row[2].toString()"+row[2].toString());
                 System.out.println(" row[3].toString()"+row[3].toString());
                 System.out.println(" row[4].toString()"+row[4].toString());
                 System.out.println(" row[5].toString()"+row[5].toString());
                 //System.out.println(" row[6].toString()"+row[6].toString());
                 System.out.println(" row[7].toString()"+row[7].toString());
                 System.out.println(" row[8].toString()"+row[8].toString());
                 System.out.println(" row[9].toString()"+row[9].toString());
                 System.out.println(" row[10].toString()"+row[10].toString());
                 System.out.println(" row[11].toString()"+row[11].toString());
                 System.out.println(" row[12].toString()"+row[12].toString());
                 System.out.println(" row[13].toString()"+row[13].toString());
                 System.out.println(" row[14].toString()"+row[14].toString());*/
                pfFloor.setPropertyId(row[0] == null ? "" : String.valueOf(row[0]));
                pfFloor.setFloorName(row[1] == null ? "" : String.valueOf(row[1]));
                pfFloor.setBuiltupArea(row[2] == null ? "" : String.valueOf(row[2]));
                pfFloor.setConstructionType(row[3] == null ? "" : String.valueOf(row[3]));
                pfFloor.setBuildingUse(row[4] == null ? "" : String.valueOf(row[4]));
                pfFloor.setPropertyClass(row[5] == null ? "" : String.valueOf(row[5]));
                pfFloor.setRentableValue(row[7] == null ? "" : String.valueOf(row[7]));
                pfFloor.setMultiplicatioFactor(row[8] == null ? "" : String.valueOf(row[8]));
                pfFloor.setPercentBuildupArea(row[9] == null ? "" : String.valueOf(row[9]));
                pfFloor.setFloorWiseTax(row[11] == null ? "" : String.valueOf(row[11]));
                pfFloor.setFloorSelfRent(row[12] == null ? "" : String.valueOf(row[12]));
                pfFloor.setPropCat(row[13] == null ? "" : String.valueOf(row[13]));
                pfFloor.setPf_electric_con_num(row[14] == null ? "" : String.valueOf(row[14]));
                //floorDetailMap.put(pfFloor.getPropertyId(), pfFloor);
                bFloor.add(pfFloor.getFloorName());
                cover.add(pfFloor.getBuiltupArea());
                buse.add(pfFloor.getBuildingUse());
                categoryList.add(pfFloor.getPropCat());
                typeOfConstruction.add(pfFloor.getConstructionType());
                selfRent.add(pfFloor.getFloorSelfRent());
                locationClassese.add(pfFloor.getPropertyClass());
                annualRent.add(pfFloor.getRentableValue());
                ratableValue.add(pfFloor.getPercentBuildupArea());
                propertyTaxRate.add(pfFloor.getMultiplicatioFactor());
                htax.add(pfFloor.getFloorWiseTax());
                electric_meter.add(pfFloor.getPf_electric_con_num());
                String countdata = (String) hashIdCount.get(pfFloor.getPropertyId());
                idCountFloor = Integer.parseInt(countdata);
                if (idCount == idCountFloor) {
                    hashFloor.put(pfFloor.getPropertyId(), bFloor);
                    hashcover.put(pfFloor.getPropertyId(), cover);
                    hashBuse.put(pfFloor.getPropertyId(), buse);
                    hashCategoryList.put(pfFloor.getPropertyId(), categoryList);
                    //hashCategoryList.put(pfFloor.getPropertyId(),categoryList);
                    hashTypeOfConstruction.put(pfFloor.getPropertyId(), typeOfConstruction);
                    hashSelfRent.put(pfFloor.getPropertyId(), selfRent);
                    hashLocationClassess.put(pfFloor.getPropertyId(), locationClassese);
                    hashAnnualRent.put(pfFloor.getPropertyId(), annualRent);
                    hashRatableValue.put(pfFloor.getPropertyId(), ratableValue);
                    hashPropertyTaxRate.put(pfFloor.getPropertyId(), propertyTaxRate);
                    hashHTax.put(pfFloor.getPropertyId(), htax);
                    hashElectricMeter.put(pfFloor.getPropertyId(), electric_meter);

                    bFloor = new ArrayList();
                    cover = new ArrayList();
                    buse = new ArrayList();
                    categoryList = new ArrayList();
                    typeOfConstruction = new ArrayList();
                    selfRent = new ArrayList();
                    locationClassese = new ArrayList();
                    annualRent = new ArrayList();
                    ratableValue = new ArrayList();
                    propertyTaxRate = new ArrayList();
                    htax = new ArrayList();
                    electric_meter = new ArrayList();

                    idCountFloor = 0;
                    idCount = 0;
                }

            }

            Iterator itr = propertyDetailArr.iterator();
            String sp = "";
            String op = "";
            String occupier = "";

            String oldmc = "";
            int index = 1;
            int sno = 1;
            String electricData = "";
            String stFloor = "";
            String stCover = "";
            String stBuse = "";
            String stCategoryDetail = "";
            String stContructionType = "";
            String stSelfRent = "";
            String stLocationClassess = "";
            String stAnnualRent = "";
            String stAnnualRatabaleValue = "";
            String stTax = "";
            String stTaxRate = "";

            while (itr.hasNext()) {
                //System.out.println("ashok kkkkkk");
                PropertyDetailsForAssessmentList bean = (PropertyDetailsForAssessmentList) itr.next();
                PropertyAssessmentBean beanData = new PropertyAssessmentBean();
                //System.out.println("ashok "+bean.getPropertyOwner()+" "+ bean.getPropertyRelationOwner()+" "+bean.getPropertyOccupierName());
         /*if(bean.getPropertyOwner().trim().length()>0 && bean.getPropertyRelationOwner().trim().length()>0 && bean.getPropertyOccupierName().trim().length()>0){
                 sp=bean.getPropertyOwner()+"-nln-"+bean.getPropertyRelationOwner()+" "+bean.getPropertyOccupierName();
                 }else if(bean.getPropertyOwner().trim().length()>0 && bean.getPropertyRelationOwner().trim().length()>0){
                 sp=bean.getPropertyOwner()+"-nln-"+bean.getPropertyRelationOwner();
                 }else if(bean.getPropertyOwner().trim().length()>0){
                 sp=bean.getPropertyOwner();
                 }*/

                if (bean.getSmcType().equals("UnRegistered")) {
                    if (bean.getPropertyOwner().trim().equals("") || bean.getPropertyOwner().length() == 0) {
                        if (bean.getHolderName() != null && bean.getHolderName().length() > 0) {
                            sp = bean.getHolderName();
                        }
                    } else {
                        if (bean.getPropertyOwner().trim().length() > 0 && bean.getProperty_owner_father().trim().length() > 0 && bean.getProperty_owner_spouse().trim().length() == 0) {
                            sp = bean.getPropertyOwner() + "-nln-S/o " + bean.getProperty_owner_father().trim();
                        } else if (bean.getPropertyOwner().trim().length() > 0 && bean.getProperty_owner_father().trim().length() == 0 && bean.getProperty_owner_spouse().trim().length() > 0) {
                            sp = bean.getPropertyOwner() + "-nln-W/o " + bean.getProperty_owner_spouse().trim();
                        } else if (bean.getPropertyOwner().trim().length() > 0 && bean.getProperty_owner_father().trim().length() == 0 && bean.getProperty_owner_spouse().trim().length() == 0) {
                            sp = bean.getPropertyOwner();
                        } else if (bean.getPropertyOwner().trim().length() > 0 && bean.getProperty_owner_father().trim().length() > 0 && bean.getProperty_owner_spouse().trim().length() > 0) {
                            sp = bean.getPropertyOwner() + "-nln-S/o " + bean.getProperty_owner_father().trim();
                        }
                    }
                } else {

                    if (bean.getPropertyOwner().trim().length() > 0 && bean.getProperty_owner_father().trim().length() > 0 && bean.getProperty_owner_spouse().trim().length() == 0) {
                        sp = bean.getPropertyOwner() + "-nln-S/o " + bean.getProperty_owner_father().trim();
                    } else if (bean.getPropertyOwner().trim().length() > 0 && bean.getProperty_owner_father().trim().length() == 0 && bean.getProperty_owner_spouse().trim().length() > 0) {
                        sp = bean.getPropertyOwner() + "-nln-W/o " + bean.getProperty_owner_spouse().trim();
                    } else if (bean.getPropertyOwner().trim().length() > 0 && bean.getProperty_owner_father().trim().length() == 0 && bean.getProperty_owner_spouse().trim().length() == 0) {
                        sp = bean.getPropertyOwner();
                    } else if (bean.getPropertyOwner().trim().length() > 0 && bean.getProperty_owner_father().trim().length() > 0 && bean.getProperty_owner_spouse().trim().length() > 0) {
                        sp = bean.getPropertyOwner() + "-nln-S/o " + bean.getProperty_owner_father().trim();
                    }
                }

                if (bean.getPropertyOccupierName().trim().length() > 0 && bean.getOccupier_father().trim().length() > 0) {
                    op = bean.getPropertyOccupierName() + "-nln-S/o " + bean.getOccupier_father().trim();
                } else if (bean.getPropertyOccupierName().trim().length() > 0 && bean.getOccupier_father().trim().length() == 0) {
                    op = bean.getPropertyOccupierName();
                } else {
                    op = "";
                }

//                if (bean.getPropertyOccupierName().trim().length() > 0) {
//                    op = bean.getPropertyOccupierName().trim();
//                } else {
//                    
//                }

                /*if (bean.getPropertyOccupierName().trim().length() > 0 && bean.getPropertyRelationOwner().trim().length() > 0) {
                 op = bean.getPropertyOccupierName().trim() + "-nln-" + bean.getPropertyRelationOwner().trim();
                 } else if (bean.getPropertyOccupierName().trim().length() > 0 && bean.getPropertyRelationOwner().trim().length() == 0) {
                 op = bean.getPropertyOccupierName();
                 } else if (bean.getPropertyOwner().trim().length() == 0 && bean.getPropertyRelationOwner().trim().length() > 0) {
                 op = bean.getPropertyRelationOwner();
                 }*/
                if (bean.getProperty_old_smc_prop_tax_num().trim().length() > 0) {
                    oldmc = bean.getProperty_old_smc_prop_tax_num().trim() + "-nln-" + "Registered";
                } else {
                    oldmc = "Unregistered";
                }

                if (bean.getPropertyContact().trim().length() > 0 && bean.getPropertyOwnerEmail().trim().length() > 0) {
                    sp = sp + "-nln-" + bean.getPropertyContact().trim() + "-nln-" + bean.getPropertyOwnerEmail().trim();
                } else if (bean.getPropertyContact().trim().length() > 0 && bean.getPropertyOwnerEmail().trim().length() == 0) {
                    sp = sp + "-nln-" + bean.getPropertyContact().trim();
                } else if (bean.getPropertyContact().trim().length() == 0 && bean.getPropertyOwnerEmail().trim().length() > 0) {
                    sp = sp + "-nln-" + bean.getPropertyOwnerEmail().trim();
                }

                if (bean.getOccupier_contactno().trim().length() > 0 && bean.getOccupier_email().trim().length() > 0) {
                    op = op + "-nln-" + bean.getOccupier_contactno().trim() + "-nln-" + bean.getOccupier_email().trim();
                } else if (bean.getOccupier_contactno().trim().length() > 0 && bean.getOccupier_email().trim().length() == 0) {
                    op = op + "-nln-" + bean.getOccupier_contactno().trim();
                } else if (bean.getOccupier_contactno().trim().length() == 0 && bean.getOccupier_email().trim().length() > 0) {
                    op = op + "-nln-" + bean.getOccupier_email().trim();
                }

                //HSSFRow row = sheet.createRow(index);
                //if (Integer.parseInt(bean.getSlNo()) > 0) {
                //row.createCell((short) 0).setCellValue(bean.getSlNo());
                //} else {
                //row.createCell((short) 0).setCellValue("");
                //}
                beanData.setSlNo(bean.getSlNo());
                beanData.setOldMc(oldmc);
                beanData.setUniqueId(bean.getPropertyUniqueId());
                beanData.setOwner_Father_Contac_Email(sp);
                beanData.setOccupier(op);
                beanData.setAddress(bean.getAddress());
                beanData.setCorrectionRemarks(bean.getCorrectionRemarks());
                beanData.setOldTax(bean.getOldTax());

                //row.createCell((short) 1).setCellValue(oldmc);
                //row.getCell((short) 1).setCellStyle(cs);
                //row.createCell((short) 2).setCellValue(bean.getPropertyUniqueId());
                //row.getCell((short) 2).setCellStyle(cs);
                //row.createCell((short) 3).setCellValue(sp);
                //row.getCell((short) 3).setCellStyle(cs);
                //row.createCell((short) 4).setCellValue(op);
                //row.getCell((short) 4).setCellStyle(cs);
                //row.createCell((short) 5).setCellValue(bean.getAddress());
                //row.getCell((short) 5).setCellStyle(cs);
                //row.createCell((short)5).setCellValue(bean.getProperty_total_floor());
                //row.getCell((short)5).setCellStyle(cs);
                //row.createCell((short)6).setCellValue(bean.getProperty_total_floor());
                //row.getCell((short)6).setCellStyle(cs);
                if (hashElectricMeter.containsKey(bean.getPropertyUniqueId())) {
                    ArrayList electric_meter_value = (ArrayList) hashElectricMeter.get(bean.getPropertyUniqueId());
                    Iterator itrfloor = electric_meter_value.iterator();
                    while (itrfloor.hasNext()) {
                        String fl = (String) itrfloor.next();
                        electricData = electricData + fl + "-nln-";

                    }
                    //System.out.println(bean.getUniqueId()+" "+stFloor+" ");
                }
                beanData.setPf_electric_con_num(electricData);
                //row.createCell((short) 6).setCellValue(electricData);
                electricData = "";

                if (hashFloor.containsKey(bean.getPropertyUniqueId())) {
                    ArrayList floorValue = (ArrayList) hashFloor.get(bean.getPropertyUniqueId());
                    Iterator itrfloor = floorValue.iterator();
                    while (itrfloor.hasNext()) {
                        String fl = (String) itrfloor.next();
                        stFloor = stFloor + fl + "-nln-";

                    }
                    //System.out.println(bean.getUniqueId()+" "+stFloor+" ");
                }
                beanData.setFloorName(stFloor);
                //row.createCell((short) 7).setCellValue(stFloor);
                stFloor = "";

                if (hashcover.containsKey(bean.getPropertyUniqueId())) {
                    ArrayList coverValue = (ArrayList) hashcover.get(bean.getPropertyUniqueId());
                    Iterator itrCover = coverValue.iterator();
                    while (itrCover.hasNext()) {
                        String fl = (String) itrCover.next();
                        stCover = stCover + fl + "-nln-";

                    }
                    //System.out.println(bean.getUniqueId()+" "+stCover+" ");
                }
                beanData.setBuiltupArea(stCover);
                //row.createCell((short) 8).setCellValue(stCover);
                stCover = "";
                if (hashBuse.containsKey(bean.getPropertyUniqueId())) {
                    ArrayList buseValue = (ArrayList) hashBuse.get(bean.getPropertyUniqueId());
                    Iterator itrBuse = buseValue.iterator();
                    while (itrBuse.hasNext()) {
                        String fl = (String) itrBuse.next();
                        stBuse = stBuse + fl + "-nln-";

                    }
                    //System.out.println(bean.getUniqueId()+" "+stBuse+" ");
                }
                beanData.setBuildingUse(stBuse);
                //row.createCell((short) 9).setCellValue(stBuse);
                stBuse = "";
                if (hashCategoryList.containsKey(bean.getPropertyUniqueId())) {
                    ArrayList categoryDetailValue = (ArrayList) hashCategoryList.get(bean.getPropertyUniqueId());
                    Iterator itrCategoryDetail = categoryDetailValue.iterator();
                    while (itrCategoryDetail.hasNext()) {
                        String fl = (String) itrCategoryDetail.next();
                        stCategoryDetail = stCategoryDetail + fl + "-nln-";

                    }
                    //System.out.println(bean.getUniqueId()+" "+stBuse+" ");
                }
                beanData.setPropCat(stCategoryDetail);
                //row.createCell((short) 10).setCellValue(stCategoryDetail);
                stCategoryDetail = "";
                if (hashTypeOfConstruction.containsKey(bean.getPropertyUniqueId())) {
                    ArrayList yrconstruction = (ArrayList) hashTypeOfConstruction.get(bean.getPropertyUniqueId());
                    Iterator itrYrconstruction = yrconstruction.iterator();
                    while (itrYrconstruction.hasNext()) {
                        String fl = (String) itrYrconstruction.next();
                        stContructionType = stContructionType + fl + "-nln-";

                    }
                    //System.out.println(bean.getUniqueId()+" "+stBuse+" ");
                }
                beanData.setConstructionType(stContructionType);
                //row.createCell((short) 11).setCellValue(stContructionType);
                stContructionType = "";

                if (hashSelfRent.containsKey(bean.getPropertyUniqueId())) {
                    ArrayList selfRentValue = (ArrayList) hashSelfRent.get(bean.getPropertyUniqueId());
                    Iterator itrSelfRent = selfRentValue.iterator();
                    while (itrSelfRent.hasNext()) {
                        String fl = (String) itrSelfRent.next();
                        stSelfRent = stSelfRent + fl + "-nln-";

                    }
                    //System.out.println(bean.getUniqueId()+" "+stBuse+" ");
                }
                beanData.setFloorSelfRent(stSelfRent);
                //row.createCell((short) 12).setCellValue(stSelfRent);
                stSelfRent = "";

                if (hashLocationClassess.containsKey(bean.getPropertyUniqueId())) {
                    ArrayList useFactorData = (ArrayList) hashLocationClassess.get(bean.getPropertyUniqueId());
                    Iterator itrUseFactorData = useFactorData.iterator();
                    while (itrUseFactorData.hasNext()) {
                        String fl = (String) itrUseFactorData.next();
                        stLocationClassess = stLocationClassess + fl + "-nln-";

                    }
                    //System.out.println(bean.getUniqueId()+" "+stBuse+" ");
                }
                beanData.setPropertyClass(stLocationClassess);
                //row.createCell((short) 13).setCellValue(stLocationClassess);
                stLocationClassess = "";

                if (hashAnnualRent.containsKey(bean.getPropertyUniqueId())) {
                    ArrayList structureFactorData = (ArrayList) hashAnnualRent.get(bean.getPropertyUniqueId());
                    Iterator itrStructureFactorData = structureFactorData.iterator();
                    while (itrStructureFactorData.hasNext()) {
                        String fl = (String) itrStructureFactorData.next();
                        stAnnualRent = stAnnualRent + fl + "-nln-";

                    }
                    //System.out.println(bean.getUniqueId()+" "+stBuse+" ");
                }
                beanData.setRentableValue(stAnnualRent);
                //row.createCell((short) 14).setCellValue(stAnnualRent);
                stAnnualRent = "";

                if (hashRatableValue.containsKey(bean.getPropertyUniqueId())) {
                    ArrayList structureFactorData = (ArrayList) hashRatableValue.get(bean.getPropertyUniqueId());
                    Iterator itrStructureFactorData = structureFactorData.iterator();
                    while (itrStructureFactorData.hasNext()) {
                        String fl = (String) itrStructureFactorData.next();
                        stAnnualRatabaleValue = stAnnualRatabaleValue + fl + "-nln-";

                    }
                    //System.out.println(bean.getUniqueId()+" "+stBuse+" ");
                }
                beanData.setAnuualRatableValue(stAnnualRatabaleValue);
                //row.createCell((short) 15).setCellValue(stAnnualRatabaleValue);
                stAnnualRatabaleValue = "";

                if (hashPropertyTaxRate.containsKey(bean.getPropertyUniqueId())) {
                    ArrayList structureFactorData = (ArrayList) hashPropertyTaxRate.get(bean.getPropertyUniqueId());
                    Iterator itrStructureFactorData = structureFactorData.iterator();
                    while (itrStructureFactorData.hasNext()) {
                        String fl = (String) itrStructureFactorData.next();
                        stTaxRate = stTaxRate + fl + "%" + "-nln-";

                    }
                    //System.out.println(bean.getUniqueId()+" "+stBuse+" ");
                }
                beanData.setMultiplicatioFactor(stTaxRate);
                //row.createCell((short) 16).setCellValue(stTaxRate);
                stTaxRate = "";
                if (hashHTax.containsKey(bean.getPropertyUniqueId())) {
                    ArrayList taxData = (ArrayList) hashHTax.get(bean.getPropertyUniqueId());
                    Iterator itrTaxData = taxData.iterator();
                    while (itrTaxData.hasNext()) {
                        String fl = (String) itrTaxData.next();

                        stTax = stTax + fl + "-nln-";

                    }
                    //System.out.println(bean.getUniqueId()+" "+stBuse+" ");
                }
                beanData.setFloorWiseTax(stTax);
                //row.createCell((short) 17).setCellValue(stTax);
                stTax = "";

                //bean.getArrearAmount();
                //bean.getProperty_tax();
                //bean.getOldTax();
                double totalTax = Double.parseDouble(bean.getArrearAmount()) + Double.parseDouble(bean.getProperty_tax());
                beanData.setTax(new Double(totalTax).toString());
                //beanData.setTax(bean.getProperty_tax());
                beanData.setArrearAmount(bean.getArrearAmount());
                assessmentBean.add(beanData);
                //row.createCell((short) 18).setCellValue(bean.getProperty_tax());
                //row.getCell((short) 18).setCellStyle(cs);

                //row.createCell((short) 19).setCellValue(bean.getArrearAmount());
                //row.getCell((short) 19).setCellStyle(cs);
                //row.createCell((short) 20).setCellValue(" ");
                //row.getCell((short) 20).setCellStyle(cs);
                sno++;
                index++;
                sp = "";
                op = "";
                oldmc = "";

            }
           // System.out.println("assessmentBean "+assessmentBean.size());

            /*if (!jo.get("zoneid").equals("-1")) {
             criteria.add(Restrictions.eq("zoneId", jo.get("zoneid")));
             if (!jo.get("wardid").equals("-1")) {
             criteria.add(Restrictions.eq("ward", jo.get("wardid")));
             }
             } else if (!jo.get("prop_id").equals("")) {
             criteria.add(Restrictions.eq("propertyUniqueId", jo.get("prop_id")));
             }*/
            //ptlist = (List<PropertyDetails>) criteria.list();
        } catch (Exception e) {
            e.printStackTrace();
            logger.info(e.getMessage());

        } finally {
            if (sessionHB != null) {
                sessionHB.flush();
                sessionHB.close();
            }
        }
        return assessmentBean;
    }

    @Override
    public List viewPrivateNotice(String taxObj) {

        //System.out.println("private notice");
        List<PropertyDetails> ptlist = null;
        ArrayList<PropertyDetailsForAssessmentList> propertyDetailArr = null;
        List<PrivateNoticeBean> noticeBean = new ArrayList<PrivateNoticeBean>();
        Session sessionHB = null;
        Criteria criteria = null;
        StringBuffer sb = new StringBuffer();
        StringBuffer sb1 = new StringBuffer();
        StringBuffer sb2 = new StringBuffer();
        int recordCtr = 0;
        int idCount = 0;
        int idCountFloor = 0;
        String zone = "";
        String ward = "";
        String propId = "";

        try {

            JSONObject jo = new Gson().fromJson(taxObj, JSONObject.class);
            if (!jo.get("zoneid").equals("-1")) {
                zone = (String) jo.get("zoneid");
                if (!jo.get("wardid").equals("-1")) {
                    ward = (String) jo.get("wardid");
                    sb.append(" select pd.property_unique_id,pd.complete_address,pd.zone_id,pd.ward,pd.property_total_floor, ");
                    sb.append(" pd.property_owner,pd.property_owner_father,pd.property_owner_spouse,pd.property_relation_owner,pd.property_occupier_name,ptx.property_tax,ptx.arrear_amount,pd.property_old_smc_prop_tax_num,pd.property_contact,pd.property_owner_email,pd.occupier_father,pd.occupier_contactno,pd.occupier_email,pd.property_aadhar_num,pd.occupier_aadhar_no,pd.property_owner_address,pd.owner_sex,pd.occupier_sex,pd.private_notice_no,pd.service_date,pd.due_date,pd.distribution_id,pd.holder_name ");
                    sb.append(" from property_tax ptx, property_details pd where ptx.property_unique_id=pd.property_unique_id  and pd.ward='" + ward + "' and ptx.financial_year='2018-2019'   order by pd.distribution_id ,pd.property_unique_id ");

                    sb1.append(" select f.property_unique_id, f.pf_floor_name,f.pf_builtup_area,f.pf_construction_Type,");
                    sb1.append(" f.pf_floorwise_build_use,f.prop_class,f.property_rentable_id,d.rentable_value,d.multiplication_factor_percent as multiplication_factor,(d.rentable_value*d.percentbuilduparea)annualRatableValue,");
                    sb1.append(" d.percentbuilduparea,pfd.floor_tax_amount,f.pf_self_rent,f.pf_property_subtype,f.pf_electric_con_num,f.pf_annual_rent_value,pfd.floor_annual_ratable_value from property_tax ptx,property_details pd, property_floor  f,property_rentable d, property_tax_details  pfd ");
                    sb1.append(" where ptx.property_unique_id=pd.property_unique_id  and pd.property_unique_id=f.property_unique_id and f.pf_id=pfd.floor_id ");
                    sb1.append(" and f.property_rentable_id=d.property_rentable_id and ptx.financial_year='2018-2019'  and pd.ward='" + ward + "'   order by pd.property_unique_id,f.flooriwse_short ");

                    sb2.append(" select pd.property_unique_id,count(*) as id_count from property_details pd,property_floor pf where pd.property_unique_id=pf.property_unique_id  and pd.ward='" + ward + "'   group by pd.property_unique_id  ");

                }
            } else if (!jo.get("prop_id").equals("")) {
                propId = (String) jo.get("prop_id");
                sb.append(" select pd.property_unique_id,pd.complete_address,pd.zone_id,pd.ward,pd.property_total_floor, ");
                sb.append(" pd.property_owner,pd.property_owner_father,pd.property_owner_spouse,pd.property_relation_owner,pd.property_occupier_name,ptx.property_tax,ptx.arrear_amount,pd.property_old_smc_prop_tax_num,pd.property_contact,pd.property_owner_email,pd.occupier_father,pd.occupier_contactno,pd.occupier_email,pd.property_aadhar_num,pd.occupier_aadhar_no,pd.property_owner_address,pd.owner_sex,pd.occupier_sex,pd.private_notice_no,pd.service_date,pd.due_date,pd.distribution_id,pd.holder_name ");
                sb.append(" from property_tax ptx, property_details pd where ptx.property_unique_id=pd.property_unique_id  and pd.property_unique_id='" + propId + "' and ptx.financial_year='2018-2019'   order by pd.distribution_id, pd.property_unique_id ");

                sb1.append(" select f.property_unique_id, f.pf_floor_name,f.pf_builtup_area,f.pf_construction_Type,");
                sb1.append(" f.pf_floorwise_build_use,f.prop_class,f.property_rentable_id,d.rentable_value,d.multiplication_factor_percent as multiplication_factor,(d.rentable_value*d.percentbuilduparea)annualRatableValue,");
                sb1.append(" d.percentbuilduparea,pfd.floor_tax_amount,f.pf_self_rent,f.pf_property_subtype,f.pf_electric_con_num,f.pf_annual_rent_value,pfd.floor_annual_ratable_value from property_tax ptx,property_details pd, property_floor  f,property_rentable d, property_tax_details  pfd ");
                sb1.append(" where ptx.property_unique_id=pd.property_unique_id  and pd.property_unique_id=f.property_unique_id and f.pf_id=pfd.floor_id ");
                sb1.append(" and f.property_rentable_id=d.property_rentable_id and ptx.financial_year='2018-2019'  and f.property_unique_id='" + propId + "'  order by pd.property_unique_id,f.flooriwse_short ");

                sb2.append(" select pd.property_unique_id,count(*) as id_count from property_details pd,property_floor pf where pd.property_unique_id=pf.property_unique_id  and pd.property_unique_id='" + propId + "'  group by pd.property_unique_id  ");

            }

            /*ward="W-14";
             sb.append(" select pd.property_unique_id,pd.complete_address,pd.zone_id,pd.ward,pd.property_total_floor, ");
             sb.append(" pd.property_owner,pd.property_owner_father,pd.property_owner_spouse,pd.property_relation_owner,pd.property_occupier_name,ptx.property_tax,ptx.arrear_amount,pd.property_old_smc_prop_tax_num,pd.property_contact,pd.property_owner_email ");
             sb.append(" from property_tax ptx, property_details pd where ptx.property_unique_id=pd.property_unique_id  and pd.ward='"+ward+"' and ptx.financial_year='2018-2019'  order by pd.property_unique_id ");

             sb1.append(" select f.property_unique_id, f.pf_floor_name,f.pf_builtup_area,f.pf_construction_Type,");
             sb1.append(" f.pf_floorwise_build_use,f.prop_class,f.property_rentable_id,d.rentable_value,d.multiplication_factor_percent as multiplication_factor,(d.rentable_value*d.percentbuilduparea)annualRatableValue,");
             sb1.append(" d.percentbuilduparea,pfd.floor_tax_amount,f.pf_self_rent,f.pf_property_subtype,f.pf_electric_con_num from property_tax ptx,property_details pd, property_floor  f,property_rentable d, property_tax_details  pfd ");
             sb1.append(" where ptx.property_unique_id=pd.property_unique_id  and pd.property_unique_id=f.property_unique_id and f.pf_id=pfd.floor_id ");
             sb1.append(" and f.property_rentable_id=d.property_rentable_id and ptx.financial_year='2018-2019'  and pd.ward='"+ward+"'  order by pd.property_unique_id,f.flooriwse_short ");

             sb2.append(" select pd.property_unique_id,count(*) as id_count from property_details pd,property_floor pf where pd.property_unique_id=pf.property_unique_id  and pd.ward='"+ward+"'  group by pd.property_unique_id  ");*/
            sessionHB = sessionFactory.openSession();
            /*sb.append(" select pd.property_unique_id,pd.complete_address,pd.zone_id,pd.ward,pd.property_total_floor, ");
             sb.append(" pd.property_owner,pd.property_owner_father,pd.property_owner_spouse,pd.property_relation_owner,pd.property_occupier_name,ptx.property_tax,ptx.arrear_amount,pd.property_old_smc_prop_tax_num,pd.property_contact,pd.property_owner_email ");
             sb.append(" from property_tax ptx, property_details pd where ptx.property_unique_id=pd.property_unique_id  and pd.ward='"+ward+"' and ptx.financial_year='2018-2019'  order by pd.property_unique_id ");

             sb1.append(" select f.property_unique_id, f.pf_floor_name,f.pf_builtup_area,f.pf_construction_Type,");
             sb1.append(" f.pf_floorwise_build_use,f.prop_class,f.property_rentable_id,d.rentable_value,d.multiplication_factor_percent as multiplication_factor,(d.rentable_value*d.percentbuilduparea)annualRatableValue,");
             sb1.append(" d.percentbuilduparea,pfd.floor_tax_amount,f.pf_self_rent,f.pf_property_subtype,f.pf_electric_con_num from property_tax ptx,property_details pd, property_floor  f,property_rentable d, property_tax_details  pfd ");
             sb1.append(" where ptx.property_unique_id=pd.property_unique_id  and pd.property_unique_id=f.property_unique_id and f.pf_id=pfd.floor_id ");
             sb1.append(" and f.property_rentable_id=d.property_rentable_id and ptx.financial_year='2018-2019'  and pd.ward='"+ward+"'  order by pd.property_unique_id,f.flooriwse_short ");

             sb2.append(" select pd.property_unique_id,count(*) as id_count from property_details pd,property_floor pf where pd.property_unique_id=pf.property_unique_id  and pd.ward='"+ward+"'  group by pd.property_unique_id  ");*/

            //criteria = sessionHB.createCriteria(PropertyDetails.class);
            //criteria.setProjection(Projections.projectionList().add(Projections.property("propertyUniqueId").as("propertyUniqueId")));
            Query query1 = sessionHB.createSQLQuery(sb.toString());
            Query query2 = sessionHB.createSQLQuery(sb1.toString());
            Query query3 = sessionHB.createSQLQuery(sb2.toString());
            // System.out.println("query1 "+sb.toString());
            //System.out.println("query3 "+sb1.toString());
            propertyDetailArr = new ArrayList<PropertyDetailsForAssessmentList>();
            List<Object[]> rows = query1.list();
            //System.out.println("gggg "+rows.size());
            for (Object[] row : rows) {
                recordCtr++;
//                System.out.println(" row[0].toString()"+row[0].toString());
//                System.out.println(" row[1].toString()"+row[1].toString());
//                System.out.println(" row[2].toString()"+row[2].toString());
//                System.out.println(" row[3].toString()"+row[3].toString());
//                System.out.println(" row[4].toString()"+row[4].toString());
//                System.out.println(" row[5].toString()"+row[5].toString());
//                System.out.println(" row[6].toString()"+row[6].toString());
//                System.out.println(" row[7].toString()"+row[7].toString());
//                System.out.println(" row[8].toString()"+row[8].toString());
//                System.out.println(" row[9].toString()"+row[9].toString());
//                System.out.println(" row[10].toString()"+row[10].toString());
//                System.out.println(" row[11].toString()"+row[11].toString());
//                System.out.println(" row[12].toString()"+row[12].toString());
//                System.out.println(" row[13].toString()"+row[13].toString());
//                System.out.println(" row[14].toString()"+row[14].toString());

                PropertyDetailsForAssessmentList ptdBean = new PropertyDetailsForAssessmentList();
                ptdBean.setPropertyUniqueId(row[0] == null ? "" : String.valueOf(row[0]));
                ptdBean.setAddress(row[1] == null ? "" : String.valueOf(row[1]));
                ptdBean.setZoneId(row[2] == null ? "" : String.valueOf(row[2]));
                ptdBean.setWard(row[3] == null ? "" : String.valueOf(row[3]));
                ptdBean.setProperty_total_floor(row[4] == null ? "" : String.valueOf(row[4]));

                ptdBean.setPropertyOwner(row[5] == null ? "" : String.valueOf(row[5]));
                if (row[6] == null) {
                    ptdBean.setProperty_owner_father("");
                    // System.out.println(" row[6].toString() if"+row[6].toString());
                } else {
                    ptdBean.setProperty_owner_father(row[6].toString());
                    //System.out.println(" row[6].toString() else "+row[6].toString());
                }

                //ptdBean.setProperty_owner_father(row[6].toString() == null ? "" : String.valueOf(row[6].toString()));
                ptdBean.setProperty_owner_spouse(row[7] == null ? "" : String.valueOf(row[7]));
                ptdBean.setPropertyRelationOwner(row[8] == null ? "" : String.valueOf(row[8]));
                ptdBean.setPropertyOccupierName(row[9] == null ? "" : String.valueOf(row[9]));
                ptdBean.setProperty_tax(row[10] == null ? "" : String.valueOf(row[10]));
                ptdBean.setArrearAmount(row[11] == null ? "" : String.valueOf(row[11]));
                ptdBean.setProperty_old_smc_prop_tax_num(row[12] == null ? "" : String.valueOf(row[12]));
                ptdBean.setPropertyContact(row[13] == null ? "" : String.valueOf(row[13]));
                ptdBean.setPropertyOwnerEmail(row[14] == null ? "" : String.valueOf(row[14]));
                ptdBean.setOccupier_father(row[15] == null ? "" : String.valueOf(row[15]));
                ptdBean.setOccupier_contactno(row[16] == null ? "" : String.valueOf(row[16]));
                ptdBean.setOccupier_email(row[17] == null ? "" : String.valueOf(row[17]));
                ptdBean.setProperetyOwnerAadhaarNo(row[18] == null ? "" : String.valueOf(row[18]));
                ptdBean.setProperetyOccupierAadhaarNo(row[19] == null ? "" : String.valueOf(row[19]));
                ptdBean.setPropertyOwnerCorrepondenceAddress(row[20] == null ? "" : String.valueOf(row[20]));
                ptdBean.setOwnerSex(row[21] == null ? "" : String.valueOf(row[21]));
                ptdBean.setOccupierSex(row[22] == null ? "" : String.valueOf(row[22]));

                ptdBean.setPrivateNotceNo(row[23] == null ? "0" : String.valueOf(row[23]));
                ptdBean.setServiceDate(row[24] == null ? "" : String.valueOf(row[24]));
                ptdBean.setDueDate(row[25] == null ? "" : String.valueOf(row[25]));
                ptdBean.setDistributionId(row[26] == null ? "" : String.valueOf(row[26]));
                ptdBean.setHolderName(row[27] == null ? "" : String.valueOf(row[27]));

                String arrarOldtax = getArrearAndOldTax(ptdBean.getPropertyUniqueId());
                //System.out.println("arrarOldtax "+arrarOldtax);
                if (arrarOldtax != null && arrarOldtax.length() > 0) {
                    String old[] = arrarOldtax.split(":");
                    //System.out.println("old "+old[0]+" "+old[1]);
                    //ptdBean.setArrearAmount(old[0].toString());
                    ptdBean.setOldTax(old[1].toString());

                }

                ptdBean.setSlNo(new Integer(recordCtr).toString());
                //System.out.println(" row[1].toString() end "+row[1].toString());

                propertyDetailArr.add(ptdBean);

            }
            //System.out.println("size "+propertyDetailArr.size());
            ArrayList<String> bFloor = new ArrayList<String>();
            ArrayList<String> cover = new ArrayList<String>();
            ArrayList<String> buse = new ArrayList<String>();
            ArrayList<String> categoryList = new ArrayList<String>();
            ArrayList<String> typeOfConstruction = new ArrayList<String>();
            ArrayList<String> selfRent = new ArrayList<String>();
            ArrayList<String> locationClassese = new ArrayList<String>();
            ArrayList<String> annualRent = new ArrayList<String>();
            ArrayList<String> ratableValue = new ArrayList<String>();
            ArrayList<String> propertyTaxRate = new ArrayList<String>();
            ArrayList<String> htax = new ArrayList<String>();
            ArrayList<String> electric_meter = new ArrayList<String>();
            ArrayList<String> actualAnnualRent = new ArrayList<String>();
            ArrayList<String> finalRatableValue = new ArrayList<String>();

            HashMap<String, ArrayList> hashFloor = new HashMap<String, ArrayList>();
            HashMap<String, ArrayList> hashcover = new HashMap<String, ArrayList>();
            HashMap<String, ArrayList> hashBuse = new HashMap<String, ArrayList>();
            HashMap<String, ArrayList> hashCategoryList = new HashMap<String, ArrayList>();
            HashMap<String, ArrayList> hashTypeOfConstruction = new HashMap<String, ArrayList>();
            HashMap<String, ArrayList> hashSelfRent = new HashMap<String, ArrayList>();
            HashMap<String, ArrayList> hashLocationClassess = new HashMap<String, ArrayList>();
            HashMap<String, ArrayList> hashAnnualRent = new HashMap<String, ArrayList>();
            HashMap<String, ArrayList> hashRatableValue = new HashMap<String, ArrayList>();
            HashMap<String, ArrayList> hashPropertyTaxRate = new HashMap<String, ArrayList>();
            HashMap<String, ArrayList> hashHTax = new HashMap<String, ArrayList>();
            HashMap<String, ArrayList> hashElectricMeter = new HashMap<String, ArrayList>();

            HashMap<String, ArrayList> hashActualAnnualRent = new HashMap<String, ArrayList>();
            HashMap<String, ArrayList> hasFinalRatableValue = new HashMap<String, ArrayList>();

            HashMap hashIdCount = new HashMap();

            List<Object[]> rowsCount = query3.list();
            for (Object[] row : rowsCount) {
                if (row[0] != null) {
                    String pid = row[0].toString();
                    String countId = new Integer(row[1].toString()).toString();
                    hashIdCount.put(pid, countId);
                    // System.out.println(" row[6].toString() if"+row[6].toString());
                }
            }
            // System.out.println("hashIdCount "+hashIdCount.size());

            Map floorDetailMap = new HashMap();
            List<Object[]> floorRows = query2.list();

            //System.out.println(" floorRows" +floorRows.size());
            for (Object[] row : floorRows) {
                idCount++;
                PropertyFloorForAssessmentList pfFloor = new PropertyFloorForAssessmentList();

                /*System.out.println(" row[0].toString()"+row[0].toString());
                 System.out.println(" row[1].toString()"+row[1].toString());
                 System.out.println(" row[2].toString()"+row[2].toString());
                 System.out.println(" row[3].toString()"+row[3].toString());
                 System.out.println(" row[4].toString()"+row[4].toString());
                 System.out.println(" row[5].toString()"+row[5].toString());
                 //System.out.println(" row[6].toString()"+row[6].toString());
                 System.out.println(" row[7].toString()"+row[7].toString());
                 System.out.println(" row[8].toString()"+row[8].toString());
                 System.out.println(" row[9].toString()"+row[9].toString());
                 System.out.println(" row[10].toString()"+row[10].toString());
                 System.out.println(" row[11].toString()"+row[11].toString());
                 System.out.println(" row[12].toString()"+row[12].toString());
                 System.out.println(" row[13].toString()"+row[13].toString());
                 System.out.println(" row[14].toString()"+row[14].toString());*/
                //System.out.println(" row[15].toString()"+row[15].toString());
                //System.out.println(" row[16].toString()"+row[16].toString());
                pfFloor.setPropertyId(row[0] == null ? "" : String.valueOf(row[0]));
                pfFloor.setFloorName(row[1] == null ? "" : String.valueOf(row[1]));
                pfFloor.setBuiltupArea(row[2] == null ? "" : String.valueOf(row[2]));
                pfFloor.setConstructionType(row[3] == null ? "" : String.valueOf(row[3]));
                pfFloor.setBuildingUse(row[4] == null ? "" : String.valueOf(row[4]));
                pfFloor.setPropertyClass(row[5] == null ? "" : String.valueOf(row[5]));
                pfFloor.setRentableValue(row[7] == null ? "" : String.valueOf(row[7]));
                pfFloor.setMultiplicatioFactor(row[8] == null ? "" : String.valueOf(row[8]));
                pfFloor.setPercentBuildupArea(row[9] == null ? "" : String.valueOf(row[9]));
                pfFloor.setFloorWiseTax(row[11] == null ? "" : String.valueOf(row[11]));
                pfFloor.setFloorSelfRent(row[12] == null ? "" : String.valueOf(row[12]));
                pfFloor.setPropCat(row[13] == null ? "" : String.valueOf(row[13]));
                pfFloor.setPf_electric_con_num(row[14] == null ? "" : String.valueOf(row[14]));
                pfFloor.setAnuualRent(row[15] == null ? "0" : String.valueOf(row[15]));
                pfFloor.setFinalRatableValue(row[16] == null ? "0" : String.valueOf(row[16]));

                //floorDetailMap.put(pfFloor.getPropertyId(), pfFloor);
                bFloor.add(pfFloor.getFloorName());
                cover.add(pfFloor.getBuiltupArea());
                buse.add(pfFloor.getBuildingUse());
                categoryList.add(pfFloor.getPropCat());
                typeOfConstruction.add(pfFloor.getConstructionType());
                selfRent.add(pfFloor.getFloorSelfRent());
                locationClassese.add(pfFloor.getPropertyClass());
                annualRent.add(pfFloor.getRentableValue());
                ratableValue.add(pfFloor.getPercentBuildupArea());
                propertyTaxRate.add(pfFloor.getMultiplicatioFactor());
                htax.add(pfFloor.getFloorWiseTax());
                electric_meter.add(pfFloor.getPf_electric_con_num());
                actualAnnualRent.add(pfFloor.getAnuualRent());
                finalRatableValue.add(pfFloor.getFinalRatableValue());
                String countdata = (String) hashIdCount.get(pfFloor.getPropertyId());
                idCountFloor = Integer.parseInt(countdata);
                if (idCount == idCountFloor) {
                    hashFloor.put(pfFloor.getPropertyId(), bFloor);
                    hashcover.put(pfFloor.getPropertyId(), cover);
                    hashBuse.put(pfFloor.getPropertyId(), buse);
                    hashCategoryList.put(pfFloor.getPropertyId(), categoryList);
                    //hashCategoryList.put(pfFloor.getPropertyId(),categoryList);
                    hashTypeOfConstruction.put(pfFloor.getPropertyId(), typeOfConstruction);
                    hashSelfRent.put(pfFloor.getPropertyId(), selfRent);
                    hashLocationClassess.put(pfFloor.getPropertyId(), locationClassese);
                    hashAnnualRent.put(pfFloor.getPropertyId(), annualRent);
                    hashRatableValue.put(pfFloor.getPropertyId(), ratableValue);
                    hashPropertyTaxRate.put(pfFloor.getPropertyId(), propertyTaxRate);
                    hashHTax.put(pfFloor.getPropertyId(), htax);
                    hashElectricMeter.put(pfFloor.getPropertyId(), electric_meter);
                    hashActualAnnualRent.put(pfFloor.getPropertyId(), actualAnnualRent);
                    hasFinalRatableValue.put(pfFloor.getPropertyId(), finalRatableValue);
                    //System.out.println("hasFinalRatableValue data "+hasFinalRatableValue.toString());
                    bFloor = new ArrayList();
                    cover = new ArrayList();
                    buse = new ArrayList();
                    categoryList = new ArrayList();
                    typeOfConstruction = new ArrayList();
                    selfRent = new ArrayList();
                    locationClassese = new ArrayList();
                    annualRent = new ArrayList();
                    ratableValue = new ArrayList();
                    propertyTaxRate = new ArrayList();
                    htax = new ArrayList();
                    electric_meter = new ArrayList();
                    actualAnnualRent = new ArrayList();
                    finalRatableValue = new ArrayList();

                    idCountFloor = 0;
                    idCount = 0;
                }

            }

            Iterator itr = propertyDetailArr.iterator();
            String sp = "";
            String op = "";
            String oldmc = "";
            int index = 1;
            int sno = 1;
            String electricData = "";
            String stFloor = "";
            String stCover = "";
            String stBuse = "";
            String stCategoryDetail = "";
            String stContructionType = "";
            String stSelfRent = "";
            String stLocationClassess = "";
            String stAnnualRent = "";
            String stAnnualRatabaleValue = "";
            String stTax = "";
            String stTaxRate = "";
            String stActutalAnnulRent = "";
            String stFinaRatableValue = "";

            while (itr.hasNext()) {
                //System.out.println("ashok kkkkkk");
                PropertyDetailsForAssessmentList bean = (PropertyDetailsForAssessmentList) itr.next();
                PrivateNoticeBean beanData = new PrivateNoticeBean();
                //System.out.println("ashok "+bean.getPropertyOwner()+" "+ bean.getPropertyRelationOwner()+" "+bean.getPropertyOccupierName());
         /*if(bean.getPropertyOwner().trim().length()>0 && bean.getPropertyRelationOwner().trim().length()>0 && bean.getPropertyOccupierName().trim().length()>0){
                 sp=bean.getPropertyOwner()+"-nln-"+bean.getPropertyRelationOwner()+" "+bean.getPropertyOccupierName();
                 }else if(bean.getPropertyOwner().trim().length()>0 && bean.getPropertyRelationOwner().trim().length()>0){
                 sp=bean.getPropertyOwner()+"-nln-"+bean.getPropertyRelationOwner();
                 }else if(bean.getPropertyOwner().trim().length()>0){
                 sp=bean.getPropertyOwner();
                 }*/

                if (bean.getPropertyOwner().trim().length() > 0 && bean.getProperty_owner_father().trim().length() > 0 && bean.getProperty_owner_spouse().trim().length() == 0) {
                    sp = bean.getPropertyOwner() + " S/o " + bean.getProperty_owner_father().trim();
                } else if (bean.getPropertyOwner().trim().length() > 0 && bean.getProperty_owner_father().trim().length() == 0 && bean.getProperty_owner_spouse().trim().length() > 0) {
                    sp = bean.getPropertyOwner() + " W/o " + bean.getProperty_owner_spouse().trim();
                } else if (bean.getPropertyOwner().trim().length() > 0 && bean.getProperty_owner_father().trim().length() == 0 && bean.getProperty_owner_spouse().trim().length() == 0) {
                    sp = bean.getPropertyOwner();
                } else if (bean.getPropertyOwner().trim().length() > 0 && bean.getProperty_owner_father().trim().length() > 0 && bean.getProperty_owner_spouse().trim().length() > 0) {
                    sp = bean.getPropertyOwner() + " S/o " + bean.getProperty_owner_father().trim();
                }

                if (bean.getPropertyOccupierName().trim().length() > 0) {
                    op = bean.getPropertyOccupierName().trim();
                    if (bean.getOccupier_father() != null && bean.getOccupier_father().trim().length() > 0) {
                        if (bean.getOccupierSex() != null && bean.getOccupierSex().trim().length() > 0) {
                            if (bean.getOccupierSex().equalsIgnoreCase("M")) {
                                op = op + " S/o " + bean.getOccupier_father();
                            } else if (bean.getOccupierSex().equalsIgnoreCase("F")) {
                                op = op + " W/o " + bean.getOccupier_father();
                            } else if (bean.getOccupierSex().equalsIgnoreCase("T")) {
                                op = op + " T/o " + bean.getOccupier_father();
                            }
                        }
                    }
                } else {
                    op = "";
                }
                //bean.getOccupier_father();
                //bean.getOccupierSex();
                /*if (bean.getPropertyOccupierName().trim().length() > 0 && bean.getPropertyRelationOwner().trim().length() > 0) {
                 op = bean.getPropertyOccupierName().trim() + "\n" + bean.getPropertyRelationOwner().trim();
                 } else if (bean.getPropertyOccupierName().trim().length() > 0 && bean.getPropertyRelationOwner().trim().length() == 0) {
                 op = bean.getPropertyOccupierName();
                 } else if (bean.getPropertyOwner().trim().length() == 0 && bean.getPropertyRelationOwner().trim().length() > 0) {
                 op = bean.getPropertyRelationOwner();
                 }*/
                if (bean.getProperty_old_smc_prop_tax_num().trim().length() > 0) {
                    oldmc = "REGISTERED";
                } else {
                    oldmc = "UNREGISTERED";
                }

                /*if (bean.getPropertyContact().trim().length() > 0 && bean.getPropertyOwnerEmail().trim().length() > 0) {
                 sp = sp + "-nln-" + bean.getPropertyContact().trim() + "-nln-" + bean.getPropertyOwnerEmail().trim();
                 } else if (bean.getPropertyContact().trim().length() > 0 && bean.getPropertyOwnerEmail().trim().length() == 0) {
                 sp = sp + "-nln-" + bean.getPropertyContact().trim();
                 } else if (bean.getPropertyContact().trim().length() == 0 && bean.getPropertyOwnerEmail().trim().length() > 0) {
                 sp = sp + "-nln-" + bean.getPropertyOwnerEmail().trim();
                 }*/
                //HSSFRow row = sheet.createRow(index);
                //if (Integer.parseInt(bean.getSlNo()) > 0) {
                //row.createCell((short) 0).setCellValue(bean.getSlNo());
                //} else {
                //row.createCell((short) 0).setCellValue("");
                //}
                beanData.setSlNo(bean.getSlNo());
                beanData.setOldMc(bean.getProperty_old_smc_prop_tax_num());
                beanData.setUniqueId(bean.getPropertyUniqueId());
                beanData.setOwner_Father(sp);
                beanData.setOccupier(op);
                beanData.setAddress(bean.getAddress());
                beanData.setContact(bean.getPropertyContact());
                beanData.setEmail(bean.getPropertyOwnerEmail());
                beanData.setWard(bean.getWard());
                beanData.setProperetyOwnerAadhaarNo(bean.getProperetyOwnerAadhaarNo());
                beanData.setProperetyOccupierAadhaarNo(bean.getProperetyOccupierAadhaarNo());
                beanData.setOwnerSex(bean.getOwnerSex());
                beanData.setPropertyOwnerCorrepondenceAddress(bean.getPropertyOwnerCorrepondenceAddress());
                beanData.setOccupier_contactno(bean.getOccupier_contactno());
                beanData.setOccupier_email(bean.getOccupier_email());
                beanData.setPrivateNotceNo(bean.getPrivateNotceNo());
                beanData.setServiceDate(bean.getServiceDate());
                beanData.setDueDate(bean.getDueDate());
                beanData.setDistributionId(bean.getDistributionId());
                beanData.setHolderName(bean.getHolderName());

                String flr[] = null;
                String meter[] = null;
                String cover_ar[] = null;
                String buse_ar[] = null;
                String category_ar[] = null;
                String construction_ar[] = null;
                String self_ar[] = null;
                String location_ar[] = null;
                String annualRent_ar[] = null;
                String ratableValue_ar[] = null;
                String tax_rate_ar[] = null;
                String tax_ar[] = null;
                String actual_AnualRent_ar[] = null;
                String anuuaRatableValue_ar[] = null;
                int totalCoveredArea = 0;
                int totalTax = 0;

                if (hashFloor.containsKey(bean.getPropertyUniqueId())) {
                    ArrayList floorValue = (ArrayList) hashFloor.get(bean.getPropertyUniqueId());
                    flr = new String[floorValue.size()];
                    Iterator itrfloor = floorValue.iterator();
                    int k = 0;
                    while (itrfloor.hasNext()) {
                        String fl = (String) itrfloor.next();
                        flr[k] = fl;
                        k++;
                        stFloor = stFloor + fl + "\n";

                    }
                    k = 0;
                    //System.out.println(bean.getUniqueId()+" "+stFloor+" ");
                }
                beanData.setFloorName(flr);

                if (hashElectricMeter.containsKey(bean.getPropertyUniqueId())) {
                    ArrayList electric_meter_value = (ArrayList) hashElectricMeter.get(bean.getPropertyUniqueId());
                    Iterator itrfloor = electric_meter_value.iterator();
                    meter = new String[electric_meter_value.size()];
                    int k = 0;
                    while (itrfloor.hasNext()) {
                        String fl = (String) itrfloor.next();
                        meter[k] = fl;
                        k++;
                        electricData = electricData + fl + "\n";

                    }
                    k = 0;
                    //System.out.println(bean.getUniqueId()+" "+stFloor+" ");
                }
                beanData.setPf_electric_con_num(meter);

                //electricData = "";
                if (hashcover.containsKey(bean.getPropertyUniqueId())) {
                    ArrayList coverValue = (ArrayList) hashcover.get(bean.getPropertyUniqueId());
                    Iterator itrCover = coverValue.iterator();
                    cover_ar = new String[coverValue.size()];
                    int k = 0;
                    while (itrCover.hasNext()) {
                        String fl = (String) itrCover.next();
                        totalCoveredArea = totalCoveredArea + (int) Double.parseDouble(fl);
                        cover_ar[k] = fl;
                        k++;
                        stCover = stCover + fl + "\n";

                    }
                    k = 0;
                    //System.out.println(bean.getUniqueId()+" "+stCover+" ");
                }
                beanData.setBuiltupArea(cover_ar);
                //stCover = "";
                if (hashBuse.containsKey(bean.getPropertyUniqueId())) {
                    ArrayList buseValue = (ArrayList) hashBuse.get(bean.getPropertyUniqueId());
                    Iterator itrBuse = buseValue.iterator();
                    buse_ar = new String[buseValue.size()];
                    int k = 0;
                    while (itrBuse.hasNext()) {
                        String fl = (String) itrBuse.next();
                        buse_ar[k] = fl;
                        k++;
                        stBuse = stBuse + fl + "\n";

                    }
                    k = 0;
                    //System.out.println(bean.getUniqueId()+" "+stBuse+" ");
                }
                beanData.setBuildingUse(buse_ar);
                //stBuse = "";
                if (hashCategoryList.containsKey(bean.getPropertyUniqueId())) {
                    ArrayList categoryDetailValue = (ArrayList) hashCategoryList.get(bean.getPropertyUniqueId());
                    Iterator itrCategoryDetail = categoryDetailValue.iterator();
                    category_ar = new String[categoryDetailValue.size()];
                    int k = 0;
                    while (itrCategoryDetail.hasNext()) {
                        String fl = (String) itrCategoryDetail.next();
                        category_ar[k] = fl;
                        k++;
                        stCategoryDetail = stCategoryDetail + fl + "\n";

                    }
                    k = 0;
                    //System.out.println(bean.getUniqueId()+" "+stBuse+" ");
                }
                beanData.setPropCat(category_ar);
                //stCategoryDetail = "";
                if (hashTypeOfConstruction.containsKey(bean.getPropertyUniqueId())) {
                    ArrayList yrconstruction = (ArrayList) hashTypeOfConstruction.get(bean.getPropertyUniqueId());
                    Iterator itrYrconstruction = yrconstruction.iterator();
                    construction_ar = new String[yrconstruction.size()];
                    int k = 0;
                    while (itrYrconstruction.hasNext()) {
                        String fl = (String) itrYrconstruction.next();
                        construction_ar[k] = fl;
                        k++;
                        stContructionType = stContructionType + fl + "\n";

                    }
                    k = 0;
                    //System.out.println(bean.getUniqueId()+" "+stBuse+" ");
                }
                beanData.setConstructionType(construction_ar);
                //stContructionType = "";

                if (hashSelfRent.containsKey(bean.getPropertyUniqueId())) {
                    ArrayList selfRentValue = (ArrayList) hashSelfRent.get(bean.getPropertyUniqueId());
                    Iterator itrSelfRent = selfRentValue.iterator();
                    self_ar = new String[selfRentValue.size()];
                    int k = 0;
                    while (itrSelfRent.hasNext()) {
                        String fl = (String) itrSelfRent.next();
                        self_ar[k] = fl;
                        k++;
                        stSelfRent = stSelfRent + fl + "\n";

                    }
                    k = 0;
                    //System.out.println(bean.getUniqueId()+" "+stBuse+" ");
                }
                beanData.setFloorSelfRent(self_ar);
                //stSelfRent = "";

                if (hashLocationClassess.containsKey(bean.getPropertyUniqueId())) {
                    ArrayList useFactorData = (ArrayList) hashLocationClassess.get(bean.getPropertyUniqueId());
                    Iterator itrUseFactorData = useFactorData.iterator();
                    location_ar = new String[useFactorData.size()];
                    int k = 0;
                    while (itrUseFactorData.hasNext()) {
                        String fl = (String) itrUseFactorData.next();
                        location_ar[k] = fl;
                        k++;
                        stLocationClassess = stLocationClassess + fl + "\n";

                    }
                    k = 0;
                    //System.out.println(bean.getUniqueId()+" "+stBuse+" ");
                }
                beanData.setPropertyClass(location_ar);
                //stLocationClassess = "";

                if (hashAnnualRent.containsKey(bean.getPropertyUniqueId())) {
                    ArrayList structureFactorData = (ArrayList) hashAnnualRent.get(bean.getPropertyUniqueId());
                    Iterator itrStructureFactorData = structureFactorData.iterator();
                    annualRent_ar = new String[structureFactorData.size()];
                    int k = 0;
                    while (itrStructureFactorData.hasNext()) {
                        String fl = (String) itrStructureFactorData.next();
                        annualRent_ar[k] = fl;
                        k++;
                        stAnnualRent = stAnnualRent + fl + "\n";

                    }
                    k = 0;
                    //System.out.println(bean.getUniqueId()+" "+stBuse+" ");
                }
                beanData.setRentableValue(annualRent_ar);
                //stAnnualRent = "";

                if (hashRatableValue.containsKey(bean.getPropertyUniqueId())) {
                    ArrayList structureFactorData = (ArrayList) hashRatableValue.get(bean.getPropertyUniqueId());
                    Iterator itrStructureFactorData = structureFactorData.iterator();
                    ratableValue_ar = new String[structureFactorData.size()];
                    int k = 0;
                    while (itrStructureFactorData.hasNext()) {
                        String fl = (String) itrStructureFactorData.next();
                        ratableValue_ar[k] = fl.substring(0, fl.length() - 2);
                        k++;
                        stAnnualRatabaleValue = stAnnualRatabaleValue + fl + "\n";

                    }
                    k = 0;
                    //System.out.println(bean.getUniqueId()+" "+stBuse+" ");
                }
                beanData.setAnuualRatableValue(ratableValue_ar);

                if (hashActualAnnualRent.containsKey(bean.getPropertyUniqueId())) {
                    //System.out.println("hashActualAnnualRent");
                    ArrayList structureFactorData = (ArrayList) hashActualAnnualRent.get(bean.getPropertyUniqueId());
                    //System.out.println("hashActualAnnualRent "+structureFactorData);
                    Iterator itrStructureFactorData = structureFactorData.iterator();
                    actual_AnualRent_ar = new String[structureFactorData.size()];
                    int k = 0;
                    while (itrStructureFactorData.hasNext()) {
                        String fl = (String) itrStructureFactorData.next();
                        actual_AnualRent_ar[k] = fl;
                        k++;
                        stActutalAnnulRent = stActutalAnnulRent + fl + "\n";

                    }
                    k = 0;
                    //System.out.println(bean.getUniqueId()+" "+stBuse+" ");
                }
                beanData.setActualRentValue(actual_AnualRent_ar);

                if (hasFinalRatableValue.containsKey(bean.getPropertyUniqueId())) {
                    //System.out.println("hasFinalRatableValue ");
                    ArrayList structureFactorData = (ArrayList) hasFinalRatableValue.get(bean.getPropertyUniqueId());
                    //System.out.println("hasFinalRatableValue "+structureFactorData);
                    Iterator itrStructureFactorData = structureFactorData.iterator();
                    anuuaRatableValue_ar = new String[structureFactorData.size()];
                    int k = 0;
                    while (itrStructureFactorData.hasNext()) {
                        String fl = (String) itrStructureFactorData.next();
                        anuuaRatableValue_ar[k] = fl;
                        k++;
                        stFinaRatableValue = stFinaRatableValue + fl + "\n";

                    }
                    k = 0;
                    //System.out.println(bean.getUniqueId()+" "+stBuse+" ");
                }
                beanData.setActualRatableValue(anuuaRatableValue_ar);
                //stAnnualRatabaleValue = "";

                if (hashPropertyTaxRate.containsKey(bean.getPropertyUniqueId())) {
                    ArrayList structureFactorData = (ArrayList) hashPropertyTaxRate.get(bean.getPropertyUniqueId());
                    Iterator itrStructureFactorData = structureFactorData.iterator();
                    tax_rate_ar = new String[structureFactorData.size()];
                    int k = 0;
                    while (itrStructureFactorData.hasNext()) {
                        String fl = (String) itrStructureFactorData.next();
                        tax_rate_ar[k] = fl + "%";
                        k++;
                        stTaxRate = stTaxRate + fl + "%" + "\n";

                    }
                    k = 0;
                    //System.out.println(bean.getUniqueId()+" "+stBuse+" ");
                }
                beanData.setMultiplicatioFactor(tax_rate_ar);
                //stTaxRate = "";
                if (hashHTax.containsKey(bean.getPropertyUniqueId())) {
                    ArrayList taxData = (ArrayList) hashHTax.get(bean.getPropertyUniqueId());
                    Iterator itrTaxData = taxData.iterator();
                    tax_ar = new String[taxData.size()];
                    int k = 0;
                    while (itrTaxData.hasNext()) {
                        String fl = (String) itrTaxData.next();
                        totalTax = totalTax + (int) (Double.parseDouble(fl));
                        tax_ar[k] = fl.substring(0, fl.length() - 3);
                        k++;
                        stTax = stTax + fl + "\n";

                    }
                    k = 0;
                    //System.out.println(bean.getUniqueId()+" "+stBuse+" ");
                }
                beanData.setFloorWiseTax(tax_ar);
                beanData.setTax(bean.getProperty_tax());
                beanData.setArrearAmount(bean.getArrearAmount());
                beanData.setOldTax(bean.getOldTax());
                noticeBean.add(beanData);
                //row.createCell((short) 18).setCellValue(bean.getProperty_tax());
                //row.getCell((short) 18).setCellStyle(cs);

                //row.createCell((short) 19).setCellValue(bean.getArrearAmount());
                //row.getCell((short) 19).setCellStyle(cs);
                //row.createCell((short) 20).setCellValue(" ");
                //row.getCell((short) 20).setCellStyle(cs);
                sno++;
                index++;
                sp = "";
                op = "";
                oldmc = "";

            }
            //System.out.println("assessmentBean "+noticeBean.size());

            /*if (!jo.get("zoneid").equals("-1")) {
             criteria.add(Restrictions.eq("zoneId", jo.get("zoneid")));
             if (!jo.get("wardid").equals("-1")) {
             criteria.add(Restrictions.eq("ward", jo.get("wardid")));
             }
             } else if (!jo.get("prop_id").equals("")) {
             criteria.add(Restrictions.eq("propertyUniqueId", jo.get("prop_id")));
             }*/
            //ptlist = (List<PropertyDetails>) criteria.list();
        } catch (Exception e) {
            logger.info(e.getMessage());

        } finally {
            if (sessionHB != null) {
                sessionHB.flush();
                sessionHB.close();
            }
        }
        return noticeBean;
    }

    public String generatePrivateNoticeNo(String taxObj) {
        Session sessionHB = null;
        Criteria criteria = null;
        StringBuffer sb = new StringBuffer();
        StringBuffer sb1 = new StringBuffer();
        StringBuffer sb2 = new StringBuffer();
        int recordCtr = 0;
        int idCount = 0;
        int idCountFloor = 0;
        String zone = "";
        String ward = "";
        String propId = "";
        SimpleDateFormat sdf = new SimpleDateFormat(("dd-MM-yyyy hh:mm:ss"));
        Calendar c2 = Calendar.getInstance();
        c2.add(Calendar.DAY_OF_MONTH, 1);
        String date2 = (String) sdf.format(c2.getTime());
        date2 = date2.substring(0, 10);
        DecimalFormat df = new DecimalFormat("#,###");
        Calendar c = Calendar.getInstance();
        c.add(Calendar.DAY_OF_MONTH, 30);
        String newDate = sdf.format(c.getTime());
        newDate = newDate.substring(0, 10);
        int privateNoticeNo = 0;
        String msg = "";
        String dd = "";

        try {

            JSONObject jo = new Gson().fromJson(taxObj, JSONObject.class);
            if (!jo.get("zoneid").equals("-1")) {
                zone = (String) jo.get("zoneid");
                if (!jo.get("wardid").equals("-1")) {
                    ward = (String) jo.get("wardid");
                    sb.append(" select property_unique_id,ward,property_old_smc_prop_tax_num,distribution_id from property_details where ward='" + ward + "'  order by distribution_id ");

                }
            } else if (!jo.get("prop_id").equals("")) {
                propId = (String) jo.get("prop_id");
                sb.append(" select property_unique_id,ward,property_old_smc_prop_tax_num,distribution_id from property_details where property_unique_id='" + propId + "'  order by distribution_id ");

            }

            //System.out.println("sql "+sb.toString());
            sessionHB = sessionFactory.openSession();

            //criteria = sessionHB.createCriteria(PropertyDetails.class);
            //criteria.setProjection(Projections.projectionList().add(Projections.property("propertyUniqueId").as("propertyUniqueId")));
            Query query1 = sessionHB.createSQLQuery(sb.toString());

            Query query2 = sessionHB.createSQLQuery("select max(cast(private_notice_no as int)) as notice_no from property_details");
            List<Integer> rows1 = query2.list();

            Iterator<Integer> itr = rows1.iterator();
            while (itr.hasNext()) {
                Integer data = (Integer) itr.next();
                privateNoticeNo = data;
            }
            privateNoticeNo = privateNoticeNo + 1;
            if (privateNoticeNo <= 9) {
                dd = "0000" + privateNoticeNo;
            } else if (privateNoticeNo > 9 && privateNoticeNo < 100) {
                dd = "000" + privateNoticeNo;
            } else if (privateNoticeNo > 100 && privateNoticeNo < 1000) {
                dd = "00" + privateNoticeNo;
            } else if (privateNoticeNo > 1000 && privateNoticeNo < 100000) {
                dd = "0" + privateNoticeNo;
            }

//            for(Object[] row : rows1){
//              if(row[0]!=null){
//                  String noticeno=String.valueOf(row[0]);
//                  privateNoticeNo=Integer.parseInt(noticeno);
//              }  
//            }
            //System.out.println("privateNoticeNo "+privateNoticeNo);
            List<Object[]> rows = query1.list();
            //System.out.println("gggg "+rows.size());
            if (rows.size() > 0) {
                for (Object[] row : rows) {
                    recordCtr++;
                    if (row[0] != null) {
                        String id = String.valueOf(row[0]);

                        //System.out.println(" id notice no "+id);
                        Query query = sessionHB.createSQLQuery("update property_details set private_notice_no=:notice,service_date=:servicedate,due_date=:duedate,check_pvt_notice='Y' where property_unique_id=:uid");
                        query.setParameter("notice", dd);
                        query.setParameter("servicedate", date2);
                        query.setParameter("duedate", newDate);
                        query.setParameter("uid", id);
                        int result = query.executeUpdate();
                        privateNoticeNo = privateNoticeNo + 1;
                        if (privateNoticeNo <= 9) {
                            dd = "0000" + privateNoticeNo;
                        } else if (privateNoticeNo > 9 && privateNoticeNo < 100) {
                            dd = "000" + privateNoticeNo;
                        } else if (privateNoticeNo > 100 && privateNoticeNo < 1000) {
                            dd = "00" + privateNoticeNo;
                        } else if (privateNoticeNo > 1000 && privateNoticeNo < 100000) {
                            dd = "0" + privateNoticeNo;
                        }

                    }

                }
            } else {
                msg = "No records found for generate private notice number";
                return msg;
            }
            if (ward != null && ward != "-1") {
                msg = "Private notice no. successfully generated for ward " + ward + " , total records " + recordCtr;
            } else if (propId != null) {
                msg = "Private notice no. successfully generated for property unique id " + propId + " , total records " + recordCtr;
            } else {
                msg = "Private notice no. not generated";
            }

            // System.out.println("assessmentBean "+assessmentBean.size());
        } catch (Exception e) {
            e.printStackTrace();
            logger.info(e.getMessage());

        } finally {
            if (sessionHB != null) {
                sessionHB.flush();
                sessionHB.close();
            }
        }
        return msg;
    }

    public List view142Notice(String taxObj) {
        List<PropertyDetails> ptlist = null;
        ArrayList<PropertyDetailsForAssessmentList> propertyDetailArr = null;
        List<PrivateNoticeBean> noticeBean = new ArrayList<PrivateNoticeBean>();
        Session sessionHB = null;
        Criteria criteria = null;
        StringBuffer sb = new StringBuffer();
        StringBuffer sb1 = new StringBuffer();
        StringBuffer sb2 = new StringBuffer();
        int recordCtr = 0;
        int idCount = 0;
        int idCountFloor = 0;
        String zone = "";
        String ward = "";
        String propId = "";

        try {

            JSONObject jo = new Gson().fromJson(taxObj, JSONObject.class);

            if (!jo.get("zoneid").equals("-1")) {
                sb1.append("and d.zone='" + jo.get("zoneid") + "'");
            }
            if (!jo.get("prop_id").equals("")) {
                sb1.append("and d.property_unique_id='" + jo.get("prop_id") + "'");
            }
            if (!jo.get("wardid").equals("")) {
                sb1.append("and d.ward='" + jo.get("wardid") + "'");
            }
            if (!jo.get("locality").equals("")) {
                sb1.append("and d.property_locality='" + jo.get("locality") + "'");
            }
            if (!jo.get("phoneNo").equals("")) {
                sb1.append("and d.property_contact='" + jo.get("phoneNo") + "'");
            }
            if (!jo.get("owner_id").equals("")) {
                sb1.append("and d.property_owner='" + jo.get("owner_id") + "'");
            }
            System.out.println("sb1" + sb1.toString());

            sb.append(" select d.property_unique_id,d.property_owner,d.property_occupier_name,d.complete_address,d.ward,t.payable_amount ");
            sb.append(" from property_details d ,property_tax t where d.property_unique_id=t.property_unique_id and t.financial_year ='2018-2019' " + sb1.toString() + " order by d.property_unique_id ");
            /* if (!jo.get("zoneid").equals("-1")) {
             zone = (String) jo.get("zoneid");
             if (!jo.get("wardid").equals("-1")) {
             ward = (String) jo.get("wardid");
             sb.append(" select d.property_unique_id,d.property_owner,d.property_occupier_name,d.complete_address,d.ward,t.payable_amount ");
             sb.append(" from property_details d ,property_tax t where d.property_unique_id=t.property_unique_id and t.financial_year ='2018-2019' '" + sb1.toString() + "' order by d.property_unique_id ");

             }
             } else if (!jo.get("prop_id").equals("")) {
             propId = (String) jo.get("prop_id");
             sb.append(" select d.property_unique_id,d.property_owner,d.property_occupier_name,d.complete_address,d.ward,t.payable_amount ");
             sb.append(" from property_details d ,property_tax t where d.property_unique_id=t.property_unique_id and t.financial_year ='2018-2019' '" + sb1.toString() + "' order by d.property_unique_id ");

             }*/

            System.out.println("sql " + sb.toString());
            sessionHB = sessionFactory.openSession();

            //criteria = sessionHB.createCriteria(PropertyDetails.class);
            //criteria.setProjection(Projections.projectionList().add(Projections.property("propertyUniqueId").as("propertyUniqueId")));
            Query query1 = sessionHB.createSQLQuery(sb.toString());

            propertyDetailArr = new ArrayList<PropertyDetailsForAssessmentList>();
            List<Object[]> rows = query1.list();
            //System.out.println("gggg "+rows.size());
            for (Object[] row : rows) {
                recordCtr++;
                PropertyDetailsForAssessmentList ptdBean = new PropertyDetailsForAssessmentList();
                ptdBean.setPropertyUniqueId(row[0] == null ? "" : String.valueOf(row[0]));
                ptdBean.setPropertyOwner(row[1] == null ? "" : String.valueOf(row[1]));
                ptdBean.setPropertyOccupierName(row[2] == null ? "" : String.valueOf(row[2]));
                ptdBean.setAddress(row[3] == null ? "" : String.valueOf(row[3]));
                ptdBean.setWard(row[4] == null ? "" : String.valueOf(row[4]));
                ptdBean.setProperty_tax(row[5] == null ? "" : String.valueOf(row[5]));

                propertyDetailArr.add(ptdBean);

            }

            Iterator itr = propertyDetailArr.iterator();
            String sp = "";
            String op = "";
            String oldmc = "";
            int index = 1;
            int sno = 1;

            while (itr.hasNext()) {
                //System.out.println("ashok kkkkkk");
                PropertyDetailsForAssessmentList bean = (PropertyDetailsForAssessmentList) itr.next();
                PrivateNoticeBean beanData = new PrivateNoticeBean();
                if (bean.getPropertyOwner().trim().length() > 0) {
                    sp = bean.getPropertyOwner();
                } else {
                    sp = "";
                }
                if (bean.getPropertyOccupierName().trim().length() > 0) {
                    op = bean.getPropertyOccupierName().trim();
                } else {
                    op = "";
                }
                beanData.setUniqueId(bean.getPropertyUniqueId());
                beanData.setOwner_Father(sp);
                beanData.setOccupier(op);
                beanData.setAddress(bean.getAddress());
                beanData.setWard(bean.getWard());
                beanData.setTax(bean.getProperty_tax());
                noticeBean.add(beanData);
                //row.createCell((short) 18).setCellValue(bean.getProperty_tax());
                //row.getCell((short) 18).setCellStyle(cs);

                //row.createCell((short) 19).setCellValue(bean.getArrearAmount());
                //row.getCell((short) 19).setCellStyle(cs);
                //row.createCell((short) 20).setCellValue(" ");
                //row.getCell((short) 20).setCellStyle(cs);
                sno++;
                index++;
                sp = "";
                op = "";
                oldmc = "";

            }
            // System.out.println("assessmentBean "+assessmentBean.size());

        } catch (Exception e) {
            logger.info(e.getMessage());

        } finally {
            if (sessionHB != null) {
                sessionHB.flush();
                sessionHB.close();
            }
        }
        return noticeBean;
    }

    public List view143Notice(String taxObj) {
        List<PropertyDetails> ptlist = null;
        ArrayList<PropertyDetailsForAssessmentList> propertyDetailArr = null;
        List<PrivateNoticeBean> noticeBean = new ArrayList<PrivateNoticeBean>();
        Session sessionHB = null;
        Criteria criteria = null;
        StringBuffer sb = new StringBuffer();
        StringBuffer sb1 = new StringBuffer();
        StringBuffer sb2 = new StringBuffer();
        int recordCtr = 0;
        int idCount = 0;
        int idCountFloor = 0;
        String zone = "";
        String ward = "";
        String propId = "";

        try {

            JSONObject jo = new Gson().fromJson(taxObj, JSONObject.class);

            sb.append(" select d.property_unique_id,d.property_owner,d.property_occupier_name,d.complete_address,d.ward,t.payable_amount ");
            sb.append(" from property_details d ,property_tax t where d.property_unique_id=t.property_unique_id and t.financial_year ='2018-2019' " + sb1.toString() + "  ");

            if (!jo.get("zoneid").equals("-1")) {
                sb.append("and d.zone='" + jo.get("zoneid") + "'");
            }
            if (!jo.get("prop_id").equals("")) {
                sb.append("and d.property_unique_id='" + jo.get("prop_id") + "'");
            }
            if (!jo.get("wardid").equals("")) {
                sb.append("and d.ward='" + jo.get("wardid") + "'");
            }
            if (!jo.get("locality").equals("")) {
                sb.append("and d.property_locality='" + jo.get("locality") + "'");
            }
            if (!jo.get("phoneNo").equals("")) {
                sb.append("and d.property_contact='" + jo.get("phoneNo") + "'");
            }
            if (!jo.get("owner_id").equals("")) {
                sb.append("and d.property_owner='" + jo.get("owner_id") + "'");
            }
            sb.append("order by d.property_unique_id");
            /*
             if (!jo.get("zoneid").equals("-1")) {
             zone = (String) jo.get("zoneid");
             if (!jo.get("wardid").equals("-1")) {
             ward = (String) jo.get("wardid");
             sb.append(" select d.property_unique_id,d.property_owner,d.property_occupier_name,d.complete_address,d.ward,t.payable_amount ");
             sb.append(" from property_details d ,property_tax t where d.property_unique_id=t.property_unique_id and t.financial_year ='2018-2019' and d.ward='" + ward + "' order by d.property_unique_id ");

             }
             } else if (!jo.get("prop_id").equals("")) {
             propId = (String) jo.get("prop_id");
             sb.append(" select d.property_unique_id,d.property_owner,d.property_occupier_name,d.complete_address,d.ward,t.payable_amount ");
             sb.append(" from property_details d ,property_tax t where d.property_unique_id=t.property_unique_id and t.financial_year ='2018-2019' and  d.property_unique_id='" + propId + "' order by d.property_unique_id ");

             }*/
            System.out.println("sql " + sb.toString());
            sessionHB = sessionFactory.openSession();

            //criteria = sessionHB.createCriteria(PropertyDetails.class);
            //criteria.setProjection(Projections.projectionList().add(Projections.property("propertyUniqueId").as("propertyUniqueId")));
            Query query1 = sessionHB.createSQLQuery(sb.toString());

            propertyDetailArr = new ArrayList<PropertyDetailsForAssessmentList>();
            List<Object[]> rows = query1.list();
            //System.out.println("gggg "+rows.size());
            for (Object[] row : rows) {
                recordCtr++;
                PropertyDetailsForAssessmentList ptdBean = new PropertyDetailsForAssessmentList();
                ptdBean.setPropertyUniqueId(row[0] == null ? "" : String.valueOf(row[0]));
                ptdBean.setPropertyOwner(row[1] == null ? "" : String.valueOf(row[1]));
                ptdBean.setPropertyOccupierName(row[2] == null ? "" : String.valueOf(row[2]));
                ptdBean.setAddress(row[3] == null ? "" : String.valueOf(row[3]));
                ptdBean.setWard(row[4] == null ? "" : String.valueOf(row[4]));
                ptdBean.setProperty_tax(row[5] == null ? "" : String.valueOf(row[5]));

                propertyDetailArr.add(ptdBean);

            }

            Iterator itr = propertyDetailArr.iterator();
            String sp = "";
            String op = "";
            String oldmc = "";
            int index = 1;
            int sno = 1;

            while (itr.hasNext()) {
                //System.out.println("ashok kkkkkk");
                PropertyDetailsForAssessmentList bean = (PropertyDetailsForAssessmentList) itr.next();
                PrivateNoticeBean beanData = new PrivateNoticeBean();
                if (bean.getPropertyOwner().trim().length() > 0) {
                    sp = bean.getPropertyOwner();
                } else {
                    sp = "";
                }
                if (bean.getPropertyOccupierName().trim().length() > 0) {
                    op = bean.getPropertyOccupierName().trim();
                } else {
                    op = "";
                }
                beanData.setUniqueId(bean.getPropertyUniqueId());
                beanData.setOwner_Father(sp);
                beanData.setOccupier(op);
                beanData.setAddress(bean.getAddress());
                beanData.setWard(bean.getWard());
                beanData.setTax(bean.getProperty_tax());
                noticeBean.add(beanData);
                //row.createCell((short) 18).setCellValue(bean.getProperty_tax());
                //row.getCell((short) 18).setCellStyle(cs);

                //row.createCell((short) 19).setCellValue(bean.getArrearAmount());
                //row.getCell((short) 19).setCellStyle(cs);
                //row.createCell((short) 20).setCellValue(" ");
                //row.getCell((short) 20).setCellStyle(cs);
                sno++;
                index++;
                sp = "";
                op = "";
                oldmc = "";

            }
            // System.out.println("assessmentBean "+assessmentBean.size());

        } catch (Exception e) {
            logger.info(e.getMessage());

        } finally {
            if (sessionHB != null) {
                sessionHB.flush();
                sessionHB.close();
            }
        }
        return noticeBean;
    }

    public List viewBillPdf(String taxObj) {
        List<PropertyDetails> ptlist = null;
        ArrayList<PropertyDetailsForAssessmentList> propertyDetailArr = null;
        List<PrivateNoticeBean> noticeBean = new ArrayList<PrivateNoticeBean>();
        Session sessionHB = null;
        Criteria criteria = null;
        StringBuffer sb = new StringBuffer();
        StringBuffer sb1 = new StringBuffer();
        StringBuffer sb2 = new StringBuffer();
        int recordCtr = 0;
        int idCount = 0;
        int idCountFloor = 0;
        String zone = "";
        String ward = "";
        String propId = "";

        try {

            JSONObject jo = new Gson().fromJson(taxObj, JSONObject.class);

            if (!jo.get("zoneid").equals("-1")) {
                sb1.append("and d.zone='" + jo.get("zoneid") + "'");
            }
            if (!jo.get("prop_id").equals("")) {
                sb1.append("and d.property_unique_id='" + jo.get("prop_id") + "'");
            }
            if (!jo.get("wardid").equals("")) {
                sb1.append("and d.ward='" + jo.get("wardid") + "'");
            }
            if (!jo.get("locality").equals("")) {
                sb1.append("and d.property_locality='" + jo.get("locality") + "'");
            }
            if (!jo.get("phoneNo").equals("")) {
                sb1.append("and d.property_contact='" + jo.get("phoneNo") + "'");
            }
            if (!jo.get("ownerId").equals("")) {
                sb1.append("and d.property_owner='" + jo.get("ownerId") + "'");
            }

            sb.append(" select d.property_unique_id,d.property_owner,d.property_occupier_name,d.complete_address,d.ward,t.payable_amount,t.property_tax,t.arrear_amount,d.property_old_smc_prop_tax_num,d.bill_no,d.service_date_for_bill,d.due_date_for_bill,t.professional_tax,t.sanitation_tax,t.interest_amount,t.rebate_amount,t.totalCollection_amount,water_tax,water_sewerage_charge,d.distribution_id,d.holder_name,d.smc_type,t.property_tax_id,d.property_contact ");
            sb.append(" from property_details d ,property_tax t where d.property_unique_id=t.property_unique_id and t.financial_year ='2018-2019' " + sb1.toString() + " order by d.distribution_id ,d.property_unique_id  ");

            /*  if (!jo.get("zoneid").equals("-1")) {
             zone = (String) jo.get("zoneid");
             if (!jo.get("wardid").equals("-1")) {
             ward = (String) jo.get("wardid");
             sb.append(" select d.property_unique_id,d.property_owner,d.property_occupier_name,d.complete_address,d.ward,t.payable_amount,t.property_tax,t.arrear_amount,d.property_old_smc_prop_tax_num,d.bill_no,d.service_date_for_bill,d.due_date_for_bill,t.professional_tax,t.sanitation_tax,t.interest_amount,t.rebate_amount,t.totalCollection_amount,water_tax,water_sewerage_charge ");
             sb.append(" from property_details d ,property_tax t where d.property_unique_id=t.property_unique_id and t.financial_year ='2018-2019' and d.ward='" + ward + "' order by d.property_unique_id ");

             }
             } else if (!jo.get("prop_id").equals("")) {
             propId = (String) jo.get("prop_id");
             sb.append(" select d.property_unique_id,d.property_owner,d.property_occupier_name,d.complete_address,d.ward,t.payable_amount,t.property_tax,t.arrear_amount,d.property_old_smc_prop_tax_num,d.bill_no,d.service_date_for_bill,d.due_date_for_bill,t.professional_tax,t.sanitation_tax,t.interest_amount,t.rebate_amount,t.totalCollection_amount,water_tax,water_sewerage_charge ");
             sb.append(" from property_details d ,property_tax t where d.property_unique_id=t.property_unique_id and t.financial_year ='2018-2019' and  d.property_unique_id='" + propId + "' order by d.property_unique_id ");

             }*/
            System.out.println("sql " + sb.toString());
            sessionHB = sessionFactory.openSession();

            //criteria = sessionHB.createCriteria(PropertyDetails.class);
            //criteria.setProjection(Projections.projectionList().add(Projections.property("propertyUniqueId").as("propertyUniqueId")));
            Query query1 = sessionHB.createSQLQuery(sb.toString());

            propertyDetailArr = new ArrayList<PropertyDetailsForAssessmentList>();
            List<Object[]> rows = query1.list();
            //System.out.println("gggg "+rows.size());
            for (Object[] row : rows) {
                recordCtr++;
                PropertyDetailsForAssessmentList ptdBean = new PropertyDetailsForAssessmentList();
                ptdBean.setPropertyUniqueId(row[0] == null ? "" : String.valueOf(row[0]));
                ptdBean.setPropertyOwner(row[1] == null ? "" : String.valueOf(row[1]));
                ptdBean.setPropertyOccupierName(row[2] == null ? "" : String.valueOf(row[2]));
                ptdBean.setAddress(row[3] == null ? "" : String.valueOf(row[3]));
                ptdBean.setWard(row[4] == null ? "" : String.valueOf(row[4]));
                ptdBean.setPayableAmount(row[5] == null ? "" : String.valueOf(row[5]));
                ptdBean.setProperty_tax(row[6] == null ? "" : String.valueOf(row[6]));
                ptdBean.setArrearAmount(row[7] == null ? "0" : String.valueOf(row[7]));
                ptdBean.setProperty_old_smc_prop_tax_num(row[8] == null ? "" : String.valueOf(row[8]));
                ptdBean.setPrivateNotceNo(row[9] == null ? "" : String.valueOf(row[9]));
                ptdBean.setServiceDate(row[10] == null ? "" : String.valueOf(row[10]));
                ptdBean.setDueDate(row[11] == null ? "" : String.valueOf(row[11]));
                ptdBean.setProfessionalTax(row[12] == null ? "" : String.valueOf(row[12]));
                ptdBean.setSanitationTax(row[13] == null ? "" : String.valueOf(row[13]));
                ptdBean.setInterestAmount(row[14] == null ? "" : String.valueOf(row[14]));
                ptdBean.setRebate(row[15] == null ? "" : String.valueOf(row[15]));
                ptdBean.setCollectionAmount(row[16] == null ? "" : String.valueOf(row[16]));
                ptdBean.setCollectionAmount(row[16] == null ? "" : String.valueOf(row[16]));
                ptdBean.setWaterTax(row[17] == null ? "" : String.valueOf(row[17]));
                ptdBean.setSewerageTax(row[18] == null ? "" : String.valueOf(row[18]));
                ptdBean.setDistributionId(row[19] == null ? "" : String.valueOf(row[19]));
                ptdBean.setHolderName(row[20] == null ? "" : String.valueOf(row[20]));
                ptdBean.setSmcType(row[21] == null ? "" : String.valueOf(row[21]));
                ptdBean.setPropertyTaxId(row[22] == null ? "" : String.valueOf(row[22]));
                ptdBean.setPropertyContact(row[23] == null ? "" : String.valueOf(row[23]));

                propertyDetailArr.add(ptdBean);

            }

            Iterator itr = propertyDetailArr.iterator();
            String sp = "";
            String op = "";
            String oldmc = "";
            int index = 1;
            int sno = 1;

            while (itr.hasNext()) {
                //System.out.println("ashok kkkkkk");
                PropertyDetailsForAssessmentList bean = (PropertyDetailsForAssessmentList) itr.next();
                PrivateNoticeBean beanData = new PrivateNoticeBean();
                //            QrcodePaymentBean qrcodeBean = new QrcodePaymentBean(); ////////////////FOR QRCODE////////////////
                if (bean.getPropertyOwner().trim().length() > 0) {
                    sp = bean.getPropertyOwner();
                } else {
                    sp = "";
                }
                if (bean.getPropertyOccupierName().trim().length() > 0) {
                    op = bean.getPropertyOccupierName().trim();
                } else {
                    op = "";
                }
                beanData.setUniqueId(bean.getPropertyUniqueId());
                beanData.setOwner_Father(sp);
                beanData.setOccupier(op);
                beanData.setAddress(bean.getAddress());
                beanData.setWard(bean.getWard());
                beanData.setTax(bean.getProperty_tax());

                beanData.setArrearAmount(bean.getArrearAmount());
                beanData.setPayableAmount(bean.getPayableAmount());
                beanData.setPrivateNotceNo(bean.getPrivateNotceNo());
                beanData.setServiceDate(bean.getServiceDate());
                beanData.setDueDate(bean.getDueDate());
                beanData.setProperty_old_smc_prop_tax_num(bean.getProperty_old_smc_prop_tax_num());
                beanData.setWaterTax(bean.getWaterTax());
                beanData.setProfessionalTax(bean.getProfessionalTax());
                beanData.setSanitationTax(bean.getSanitationTax());
                beanData.setRebate(bean.getRebate());
                beanData.setCollectionAmount(bean.getCollectionAmount());
                beanData.setInterestAmount(bean.getInterestAmount());
                beanData.setSewerageTax(bean.getSewerageTax());
                beanData.setDistributionId(bean.getDistributionId());
                beanData.setHolderName(bean.getHolderName());
                beanData.setSmcType(bean.getSmcType());
                ////////////////FOR QRCODE////////////////
//                qrcodeBean.setFinancialYear("2018-2019");
//                qrcodeBean.setUniqueId(bean.getPropertyUniqueId());
//                qrcodeBean.setPropertyTaxId(bean.getPropertyTaxId());
//                qrcodeBean.setPaymentStatus(MMIUtil.PAY_TX_INIT);
//                qrcodeBean.setPayableAmnt(bean.getPayableAmount());
//                qrcodeBean.setCreatedTimestamp(new Date());
//                qrcodeBean.setOwnerContactN(bean.getPropertyContact());
//                qrcodeBean.setOwnerName(bean.getPropertyOwner());
//                qrcodeBean.setTransactionId(taxdao.getTransactionId());
//                taxdao.addqrcodeData(qrcodeBean);
//                beanData.setQrcodedata(qrcodeBean);
                ////////////////FOR QRCODE////////////////
                noticeBean.add(beanData);

                //row.createCell((short) 18).setCellValue(bean.getProperty_tax());
                //row.getCell((short) 18).setCellStyle(cs);
                //row.createCell((short) 19).setCellValue(bean.getArrearAmount());
                //row.getCell((short) 19).setCellStyle(cs);
                //row.createCell((short) 20).setCellValue(" ");
                //row.getCell((short) 20).setCellStyle(cs);
                sno++;
                index++;
                sp = "";
                op = "";
                oldmc = "";

            }
            // System.out.println("assessmentBean "+assessmentBean.size());

        } catch (Exception e) {
            logger.info(e.getMessage());

        } finally {
            if (sessionHB != null) {
                sessionHB.flush();
                sessionHB.close();
            }
        }
        return noticeBean;
    }

    @Override
    public List<Usermaster> loadAllActiveUsers(Usermaster dto) {
        Session sessionHB = null;
        Criteria criteria = null;
        // logger.info("data");
        List<Usermaster> results = null;
        List<String> roleList = null;
        List<PermissionBean> permissionList = null;
        PermissionBean bean = null;
        Map row = null;
        StringBuilder sqlRole;
        try {
            sessionHB = sessionFactory.openSession();
            criteria = sessionHB.createCriteria(Usermaster.class);
            criteria.add(Restrictions.eq("status", "Y"));
            results = criteria.list();
            // Query query = sessionHB
            // .createQuery("from Usermaster ");
            // results = query.list();
            for (int i = 0; i < results.size(); i++) {
                permissionList = new ArrayList<PermissionBean>();
                sqlRole = new StringBuilder(
                        "select  pm.permission_id,pm.menu_id,pm.menu_description,up.user_permission_id,"
                        + " case when user_permission_id is not null then 'checked' else '' end ischeck from permission_master pm "
                        + " left join ( select * from user_permission up  where user_role_id='"
                        + results.get(i).getRoleId()
                        + "' and active='Y') up on"
                        + " pm.permission_id=up.permission_id order by pm.menu_description asc");
                SQLQuery qryRole = sessionHB.createSQLQuery(sqlRole.toString());
                qryRole.setResultTransformer(Criteria.ALIAS_TO_ENTITY_MAP);
                roleList = qryRole.list();
                for (Object object : roleList) {
                    row = (Map) object;
                    bean = new PermissionBean();
                    if (row.get("user_permission_id") != null) {
                        bean.setUserPermissionId(Integer.parseInt(row.get(
                                "user_permission_id").toString()));
                    }
                    bean.setPermissionId(Integer.parseInt(row.get(
                            "permission_id").toString()));
                    bean.setMenuId(row.get("menu_id").toString());
                    bean.setMenuDescription(row.get("menu_description")
                            .toString());
                    if (row.get("ischeck") != null) {
                        bean.setIschecked(row.get("ischeck").toString());
                    }
                    permissionList.add(bean);
                    bean = null;
                }
                results.get(i).setPermissionList(permissionList);
                permissionList = null;
                row = null;

            }
        } catch (Exception e) {
            logger.info(e.getMessage());
        } finally {
            if (sessionHB != null) {
                sessionHB.close();
                criteria = null;
            }
        }
        // logger.info("results"+results);
        return results;
    }

    @Override
    public String resetPermission(FilterBean filterBean) {
        Session sessionHB = null;
        String status = "fail";
        Query updateQry = null;
        Query insertQry = null;
        Criteria criteria = null;
        List<UserPermission> result = null;
        try {
            sessionHB = sessionFactory.openSession();
            // Transaction tx=sessionHB.beginTransaction();
            StringBuilder permissions = new StringBuilder("");
            for (int i = 0; i < filterBean.getPermissionId().length; i++) {
                permissions.append(Integer.parseInt(filterBean.getPermissionId()[i]) + ",");
                criteria = sessionHB.createCriteria(UserPermission.class);
                criteria.add(Restrictions.eq("userRoleId", filterBean.getRoleId()));
                criteria.add(Restrictions.eq("permissionId", Integer.parseInt(filterBean.getPermissionId()[i])));
                result = criteria.list();
                if (result.size() > 0) {
                    if (result.get(0).getActive().equalsIgnoreCase("N")) {
                        result.get(0).setActive("Y");
                        updateQry = sessionHB
                                .createSQLQuery("update user_permission set active='Y' ,modified_on=now() where user_permission_id="
                                        + result.get(0).getUserPermissionId());
                        int st = updateQry.executeUpdate();
                    }
                } else {
                    insertQry = sessionHB
                            .createSQLQuery("INSERT INTO user_permission(user_permission_id, user_role_id, permission_id, active, created_by,"
                                    + " modified_on) VALUES (nextval('user_permission_seq'),'"
                                    + filterBean.getRoleId() + "'," + Integer.parseInt(filterBean.getPermissionId()[i])
                                    + " , 'Y', 'Admin',now());");
                    int st = insertQry.executeUpdate();
                }

            }
            permissions.append(0);
            updateQry = sessionHB
                    .createSQLQuery("update user_permission set active='N' ,modified_on=now() where user_role_id='" + filterBean.getRoleId() + "' and "
                            + "permission_id not in (" + permissions + ")");
            int st = updateQry.executeUpdate();
            status = "success";

        } catch (Exception e) {
            logger.info(e.getMessage());
            status = "fail";
        } finally {
            sessionHB.close();
            updateQry = null;
            insertQry = null;
            criteria = null;
        }
        return status;
    }

    @Override
    public String registerNewUser(Usermaster usermaster) {
        Session sessionHB = null;
        String status = "fail";
        try {
            sessionHB = sessionFactory.openSession();
            sessionHB.save(usermaster);
            status = "success";
        } catch (Exception e) {
            logger.info(e.getMessage());
            status = "fail";
        } finally {
            sessionHB.close();
        }
        return status;
    }

    @Override
    public boolean checkIfUserIdExist(Usermaster usermaster) {

        Session sessionHB = null;
        boolean isExist = false;
        try {
            sessionHB = sessionFactory.openSession();
            Criteria criteria = sessionHB.createCriteria(Usermaster.class);
            criteria.add(Restrictions.eq("userId", usermaster.getUserId()));
            if (criteria.list().size() > 0) {
                isExist = true;
            }
        } catch (Exception e) {
            logger.info(e.getMessage());
        } finally {
            sessionHB.close();
        }

        return isExist;
    }

    @Override
    public void appendToActionTracker(ActionTracker actionTracker) {

        Session sessionHB = null;
        try {
            sessionHB = sessionFactory.openSession();
            sessionHB.saveOrUpdate(actionTracker);
        } catch (Exception ex) {
            logger.info("[SilvassaServiceImpl.appendToActionTracker] Exception : " + ex.getMessage());
        } finally {
            if (sessionHB != null) {
                sessionHB.flush();
                sessionHB.clear();
                sessionHB.close();
            }
        }

    }

    @Override
    public ActionTracker getLastTAXCalculated() {
        ActionTracker actionTracker = null;
        String str = null;
        Session sessionHB = null;
        try {
            sessionHB = sessionFactory.openSession();
            Criteria criteria = sessionHB.createCriteria(ActionTracker.class);
            criteria.add(Restrictions.eq("task", MMIUtil.TAX_GEN));
            criteria.add(Restrictions.eq("taskStatus", MMIUtil.STATUS_COMPLETE));
            criteria.addOrder(Order.desc("initTime"));
            criteria.setMaxResults(1);
            criteria.setProjection(Projections.projectionList().add(Projections.property("completeTime").as("completeTime")));
            str = (String) criteria.uniqueResult();
            actionTracker = new ActionTracker();
            if (str != null) {
                actionTracker.setCompleteTime(String.valueOf(MMIUtil.epocToDate(Long.valueOf(str))));
            }

        } catch (Exception e) {
            logger.info(e.getMessage());
        } finally {
            sessionHB.close();
        }

        return actionTracker;
    }

    @Override
    public ActionTracker getLastNoticeGenerated() {
        ActionTracker actionTracker = null;
        String str = null;
        Session sessionHB = null;
        try {
            sessionHB = sessionFactory.openSession();
            Criteria criteria = sessionHB.createCriteria(ActionTracker.class);
            criteria.add(Restrictions.eq("task", MMIUtil.NOTICE_GEN));
            criteria.add(Restrictions.eq("taskStatus", MMIUtil.STATUS_COMPLETE));
            criteria.addOrder(Order.desc("initTime"));
            criteria.setMaxResults(1);
            criteria.setProjection(Projections.projectionList().add(Projections.property("completeTime").as("completeTime")));
            str = (String) criteria.uniqueResult();
            actionTracker = new ActionTracker();
            if (str != null) {
                actionTracker.setCompleteTime(String.valueOf(MMIUtil.epocToDate(Long.valueOf(str))));
            }
        } catch (Exception e) {
            logger.info(e.getMessage());
        } finally {
            sessionHB.close();
        }

        return actionTracker;
    }

    @Override
    public List<Wardmaster> getWards(String zone_id) {
        Session sessionHB = null;
        Criteria criteria = null;
        List<Wardmaster> results = null;

        try {
            sessionHB = sessionFactory.openSession();
            criteria = sessionHB.createCriteria(Wardmaster.class);
            criteria.addOrder(Order.asc("sno"));

//            if(!zone_id.equals("") && !zone_id.equals(null)){
//                criteria.add(Restrictions.eq("zoneAssociated", zone_id));
//            }
            results = criteria.list();
//          Object ss[]=  results.toArray();
          /*for(int i=0;i<results.size();i++){
             System.out.println("ward "+results.get(i).getWard());
              
             }*/

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
    public List<SubLocality> getSubLocality(String ward) {
        Session sessionHB = null;
        Criteria criteria = null;
        List<SubLocality> results = null;

        try {
            sessionHB = sessionFactory.openSession();
            criteria = sessionHB.createCriteria(SubLocality.class);
            criteria.add(Restrictions.eq("wardAssociated", ward));
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

    // Added By Jay Prakash Kumar for new Property Addition
    @Override
    public void addNewProperty(PropertyDetails propertyDetail, List<PropertyFloor> propertyFloorList) {

        Session sessionHB = null;
        try {

            sessionHB = sessionFactory.openSession();
            try {
                sessionHB.saveOrUpdate(propertyDetail);
            } catch (Exception ex) {
                ex.printStackTrace();
            }
            for (PropertyFloor propertyFloor : propertyFloorList) {
                try {
                    //System.out.println("rentable id "+propertyFloor.getPropertyRentableId());
                    sessionHB.saveOrUpdate(propertyFloor);

                } catch (Exception ex) {
                    ex.printStackTrace();
                }
            }
            //System.out.println("propertyDetail.getPropertyUniqueId()"+propertyDetail.getPropertyUniqueId());

        } catch (Exception ex) {
            ex.printStackTrace();
            logger.info("[SilvassaServiceImpl.addNewProperty] Exception : " + ex.getMessage());
        } finally {
            if (sessionHB != null) {
                sessionHB.flush();
                sessionHB.clear();
                sessionHB.close();
            }
        }

        try {
            sessionHB = null;
            sessionHB = sessionFactory.openSession();
            Query query5 = sessionHB.createSQLQuery("UPDATE  property_floor set property_rentable_id = rt.property_rentable_id FROM property_rentable rt where UPPER(pf_floorwise_build_use) = UPPER(property_cat) and prop_class=property_subcat_code and property_unique_id=:uid ");
            query5.setParameter("uid", propertyDetail.getPropertyUniqueId());
            //query2.setParameter("floorid", Integer.parseInt(floorBean.getPropertyFloorId()));
            int result5 = query5.executeUpdate();
            //System.out.println("result5 "+result5);

            StringBuffer sb2 = new StringBuffer();
            sb2.append(" update property_details pd set no_tax_type = 'Y' from ( ");
            sb2.append(" select * from ( ");
            sb2.append(" select property_unique_id, sum(pf_builtup_area) as pf_builtup_area from property_floor where property_unique_id not in( ");
            sb2.append(" select property_unique_id from property_floor where pf_floorwise_build_use <> 'Residential' ");
            sb2.append(") group by property_unique_id) flt_prop where pf_builtup_area <=269 and property_unique_id=:uid ");
            sb2.append(" ) no_tax_prop where pd.property_unique_id = no_tax_prop.property_unique_id");

            Query query3 = sessionHB.createSQLQuery(sb2.toString());
            query3.setParameter("uid", propertyDetail.getPropertyUniqueId());
            int result3 = query3.executeUpdate();
            //System.out.println("result3 "+result3);

        } catch (Exception ex) {
            ex.printStackTrace();
            logger.info("[SilvassaServiceImpl.addNewProperty] Exception : " + ex.getMessage());
        } finally {
            if (sessionHB != null) {
                sessionHB.flush();
                sessionHB.clear();
                sessionHB.close();
            }
        }

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
            criteria.add(Restrictions.eq("permissionId", 32));
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
    public String jobAllocation(List<SlJobAllocation> slJobAllocations) {
        Session sessionHB = null;
        String status = "fail";
        try {
            sessionHB = sessionFactory.openSession();
            try {
                int i = 1;
                for (SlJobAllocation slJobAllocation : slJobAllocations) {
                    List<String> propIdArray = new ArrayList<>();
                    String hqlpropIdArr = "select distinct(propertyUniqueId) as property_unique_id from PropertyDetails where "
                            + " zone_id = :zone_id and ward = :ward "
                            + " and propertyUniqueId is not null order by propertyUniqueId";

                    System.out.println("zone_id : " + slJobAllocation.getZoneId());
                    System.out.println("ward : " + slJobAllocation.getWardNo());

                    Query query = sessionHB.createQuery(hqlpropIdArr);
                    String zoneId = slJobAllocation.getZoneId();
                    String wardId = slJobAllocation.getWardNo();
                    query.setParameter("zone_id", zoneId);
                    query.setParameter("ward", wardId);

                    String jobId = getJobAllocationId();
                    slJobAllocation.setJobID(jobId);  // Seting Job Id

//                    query.setResultTransformer(Criteria.ALIAS_TO_ENTITY_MAP);
                    propIdArray = query.list();
                    jobAllocationDetails(zoneId, wardId, jobId);
                    if (!propIdArray.isEmpty()) {
                        slJobAllocation.setPropertyIds(StringUtils.join(propIdArray, ","));
                        slJobAllocation.setCounts(Integer.toString(propIdArray.size()));
                    } else {
                        slJobAllocation.setPropertyIds("");
                        slJobAllocation.setCounts("0");
                    }
                    sessionHB.saveOrUpdate(slJobAllocation);
//                    if (i % 49 == 0) { //20, same as the JDBC batch size
//                        //flush a batch of inserts and release memory:
//                        sessionHB.flush();
//                        sessionHB.clear();
//                    }
                }
                status = "success";
            } catch (Exception ex) {
                ex.printStackTrace();
                logger.error("[SilvassaServiceImpl.jobAllocation] Exception : " + ex.getMessage());
                status = "fail";
            }
        } catch (Exception ex) {
            logger.info("[SilvassaServiceImpl.jobAllocation] Exception : " + ex.getMessage());
            status = "fail";
        } finally {
            if (sessionHB != null) {
                sessionHB.flush();
                sessionHB.clear();
                sessionHB.close();
            }
        }
        return status;
    }

    @Override
    public String jobDeAllocation(List<SlJobAllocation> slJobAllocations) {
        Session sessionHB = null;
        String status = "fail";
        try {
            sessionHB = sessionFactory.openSession();
            try {
                int i = 1;
                for (SlJobAllocation slJobAllocation : slJobAllocations) {
                    sessionHB.update(slJobAllocation);
                    i++;
                    status = "success";
//                    if (i % 49 == 0) { //20, same as the JDBC batch size
//                        //flush a batch of inserts and release memory:
//                        sessionHB.flush();
//                        sessionHB.clear();
//                    }
                }
            } catch (Exception ex) {
                ex.printStackTrace();
                logger.error("[SilvassaServiceImpl.jobDeAllocation] Exception : " + ex.getMessage());
                status = "fail";
            }
        } catch (Exception ex) {
            logger.info("[SilvassaServiceImpl.jobDeAllocation] Exception : " + ex.getMessage());
            status = "fail";
        } finally {
            if (sessionHB != null) {
                sessionHB.flush();
                sessionHB.clear();
                sessionHB.close();
            }
        }
        return status;
    }

    @Override
    public List getAllocatedJobs() {
        Session sessionHB = null;
        Criteria criteria = null;
        List<SlJobAllocation> slJobAllocations = new ArrayList<SlJobAllocation>();
        try {
            sessionHB = sessionFactory.openSession();
            criteria = sessionHB.createCriteria(SlJobAllocation.class);
//            criteria.add(Restrictions.eq("allocateTo", userMaster.getUserId()));
            criteria.add(Restrictions.eq("active", "Y"));
            slJobAllocations = criteria.list();
        } catch (Exception e) {
            e.printStackTrace();
            logger.error("[SilvassaServiceImpl.getAllocatedJobs] Exception : " + e.getMessage());
        } finally {
            if (sessionHB != null) {
                sessionHB.flush();
                sessionHB.clear();
                sessionHB.close();
            }
        }
        return slJobAllocations;
    }

    public String getJobAllocationId() throws Exception {

        Session sessionHB = null;
        String seqNo = null;
        try {

            sessionHB = sessionFactory.openSession();
            Query query = sessionHB.createSQLQuery("select fn_sl_job_allocation_job_id()");
            seqNo = (String) query.uniqueResult();
        } catch (Exception ex) {
            logger.info("[SilvassaServiceImpl.getJobAllocationId] Exception : " + ex.getMessage());
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

    private String jobAllocationDetails(String zoneId, String wardId, String jobId) {
        Session sessionHB = null;
        String status = "fail";
        try {
            sessionHB = sessionFactory.openSession();
            try {

                String hqlpropIdArr = "INSERT INTO SLJobAllocationDeatils(jobId,propertyId) "
                        + " select '" + jobId
                        + "', propertyUniqueId from PropertyDetails  where "
                        + " zone_id = :zone_id and ward = :ward "
                        + " and propertyUniqueId is not null order by propertyUniqueId";

                Query query = sessionHB.createQuery(hqlpropIdArr);
                query.setParameter("zone_id", zoneId);
                query.setParameter("ward", wardId);
                query.executeUpdate();

            } catch (Exception ex) {
                ex.printStackTrace();
                logger.error("[SilvassaServiceImpl.jobAllocationDetails] Exception : " + ex.getMessage());
                status = "fail";
            }
        } catch (Exception ex) {
            logger.info("[SilvassaServiceImpl.jobAllocationDetails] Exception : " + ex.getMessage());
            status = "fail";
        } finally {
            if (sessionHB != null) {
                sessionHB.flush();
                sessionHB.clear();
                sessionHB.close();
            }
        }
        return status;
    }

    public UserRegistration checkPropertyId(String propid) {
        Session sessionHB = null;
        String msg = "";
        StringBuffer sb = new StringBuffer();
        UserRegistration useRegisgration = new UserRegistration();
        try {
            //System.out.println("session "+bean.getUniqueId());
            sb.append("select property_unique_id,property_owner,property_contact,property_owner_email,property_occupier_name,occupier_contactno,occupier_email,property_owner_spouse, complete_address,proof_doc_for_mobile from property_details where property_unique_id='" + propid + "' ");
            sessionHB = sessionFactory.openSession();
            Query query1 = sessionHB.createSQLQuery(sb.toString());
            List<Object[]> rows = query1.list();
            if (rows.size() > 0) {
                msg = "Property Id Found";

                for (Object[] row : rows) {
                    //System.out.println("row[15] mark"+row[15]);
                    //System.out.println("row[20] road"+row[20]);

                    useRegisgration.setUniqueId(row[0] == null ? "" : String.valueOf(row[0]));
                    useRegisgration.setOwnerName(row[1] == null ? "" : String.valueOf(row[1]));

                    useRegisgration.setOnwerMobile(row[2] == null ? "" : String.valueOf(row[2]));
                    useRegisgration.setOwnerEmail(row[3] == null ? "" : String.valueOf(row[3]));
                    useRegisgration.setOccupierName(row[4] == null ? "" : String.valueOf(row[4]));
                    useRegisgration.setOccupierMobile(row[5] == null ? "" : String.valueOf(row[5]));

                    useRegisgration.setOccupierEmail(row[6] == null ? "" : String.valueOf(row[6]));
                    useRegisgration.setSpouseName(row[7] == null ? "" : String.valueOf(row[7]));
                    useRegisgration.setAddress(row[8] == null ? "" : String.valueOf(row[8]));
                    useRegisgration.setDocumentType(row[9] == null ? "-1" : String.valueOf(row[9]));
                    useRegisgration.setMsg(msg);

                    //ptdBean.setDeleteData(row[10] == null ? "" : String.valueOf(row[10]));
                    //ptdBean.setDocumentType(row[12] == null ? "" : String.valueOf(row[12]));
                    //ptdBean.setId(row[13] == null ? -1 : Integer.valueOf(String.valueOf(row[13])));
                    //ar.add(ptdBean);
                }
            } else {
                msg = "Property Id incorrect";
                useRegisgration.setMsg(msg);
            }

//            if(rows.size()>0){
//               msg="Property Id Found" ;
//               
//            }else{
//               msg="Property Id incorrect" ; 
//            }
//           
        } catch (Exception e) {
            //e.printStackTrace();
            logger.info(e.getMessage());
        } finally {
            if (sessionHB != null) {
                sessionHB.close();
            }
        }

        return useRegisgration;

    }

    public String saveMobile(String propid, String ownerMobile, String ownerEmail, String occupierMobile, String occupierEmail, String documentType, String proof_msg, String imageName, byte[] image) {
        Session sessionHB = null;
        String msg = "";
        StringBuffer sb = new StringBuffer();
        int p1 = 0;
        int p2 = 0;
        int p3 = 0;
        int p4 = 0;
        int p5 = 0;
        int p6 = 0;
        int p7 = 0;
        int p8 = 0;

        try {
            //System.out.println("ownerMobile "+ownerMobile);
            // System.out.println("ownerEmail "+ownerEmail);
            //System.out.println("occupierMobile "+occupierMobile);
            //System.out.println("occupierEmail "+occupierEmail);
            //System.out.println("documentType "+documentType);
            //System.out.println("save mobile");
            // sb.append("select property_unique_id,property_owner,property_contact,property_owner_email,property_occupier_name,occupier_contactno,occupier_email,property_owner_spouse, complete_address from property_details where property_unique_id='"+propid+"' ");
            sessionHB = sessionFactory.openSession();
            if (ownerMobile != null && ownerMobile.trim().length() > 0) {
                Query query1 = sessionHB.createSQLQuery("update property_details set property_contact=:contact where property_unique_id=:uid");
                query1.setParameter("uid", propid);
                query1.setParameter("contact", ownerMobile);
                int result = query1.executeUpdate();
                msg = " Owner mobile  ";
                p1 = 1;
            }
            if (ownerEmail != null && ownerEmail.trim().length() > 0) {
                Query query1 = sessionHB.createSQLQuery("update property_details set property_owner_email=:email where property_unique_id=:uid");
                query1.setParameter("uid", propid);
                query1.setParameter("email", ownerEmail);
                int result = query1.executeUpdate();
                msg = msg + " owner email ";
                p2 = 1;
            }
            if (occupierMobile != null && occupierMobile.trim().length() > 0) {
                Query query1 = sessionHB.createSQLQuery("update property_details set occupier_contactno=:occucontact where property_unique_id=:uid");
                query1.setParameter("uid", propid);
                query1.setParameter("occucontact", occupierMobile);
                int result = query1.executeUpdate();
                msg = msg + " occupier mobile ";
                p3 = 1;
            }
            if (occupierEmail != null && occupierEmail.trim().length() > 0) {
                Query query1 = sessionHB.createSQLQuery("update property_details set occupier_email=:occuemail where property_unique_id=:uid");
                query1.setParameter("uid", propid);
                query1.setParameter("occuemail", occupierEmail);
                int result = query1.executeUpdate();
                msg = msg + " occupier email ";
                p4 = 1;
            }
            if (documentType != null && documentType.trim().length() > 0 && !documentType.equalsIgnoreCase("-1")) {
                Query query1 = sessionHB.createSQLQuery("update property_details set proof_doc_for_mobile=:doc where property_unique_id=:uid");
                query1.setParameter("uid", propid);
                query1.setParameter("doc", documentType);
                int result = query1.executeUpdate();
                msg = msg + " proof document for mobile  ";
                p5 = 1;
            }
            if (proof_msg != null && proof_msg.trim().length() > 0) {
                Query query1 = sessionHB.createSQLQuery("update property_details set proof_msg=:proof where property_unique_id=:uid");
                query1.setParameter("uid", propid);
                query1.setParameter("proof", proof_msg);
                int result = query1.executeUpdate();
                msg = msg + " proof remarks  ";
                p6 = 1;
            }
            if (imageName != null && imageName.trim().length() > 0) {
                Query query1 = sessionHB.createSQLQuery("update property_details set image_file_name=:image where property_unique_id=:uid");
                query1.setParameter("uid", propid);
                query1.setParameter("image", imageName);
                int result = query1.executeUpdate();
                msg = msg + " image name  ";
                p7 = 1;
            }
            if (image.length > 0) {
                Query query1 = sessionHB.createSQLQuery("update property_details set image=:imagedata where property_unique_id=:uid");
                query1.setParameter("uid", propid);
                query1.setParameter("imagedata", image);
                int result = query1.executeUpdate();
                msg = msg + " image name  ";
                p8 = 1;
            }

            if (p1 == 1 || p2 == 2 || p3 == 1 || p4 == 1 || p5 == 1 || p6 == 1 || p7 == 1 || p8 == 1) {
                msg = "Updated successfully";
            } else {
                msg = "";
            }

        } catch (Exception e) {
            //e.printStackTrace();
            logger.info(e.getMessage());
        } finally {
            if (sessionHB != null) {
                sessionHB.close();
            }
        }

        return msg;
    }

    public UserRegistration showImage(String propid) {
        Session sessionHB = null;
        String msg = "";
        StringBuffer sb = new StringBuffer();
        UserRegistration useRegisgration = new UserRegistration();
        try {
            //System.out.println("session "+bean.getUniqueId());
            sb.append(" select property_unique_id,image_file_name from property_details where property_unique_id='" + propid + "' ");
            sessionHB = sessionFactory.openSession();
            Query query1 = sessionHB.createSQLQuery(sb.toString());
            List<Object[]> rows = query1.list();
            if (rows.size() > 0) {
                msg = "Property Id Found";

                for (Object[] row : rows) {
                    //System.out.println("row[15] mark"+row[15]);
                    //System.out.println("row[20] road"+row[20]);

                    useRegisgration.setUniqueId(row[0] == null ? "" : String.valueOf(row[0]));
                    useRegisgration.setImageName(row[1] == null ? "" : String.valueOf(row[1]));

                    useRegisgration.setMsg(msg);

                    //ptdBean.setDeleteData(row[10] == null ? "" : String.valueOf(row[10]));
                    //ptdBean.setDocumentType(row[12] == null ? "" : String.valueOf(row[12]));
                    //ptdBean.setId(row[13] == null ? -1 : Integer.valueOf(String.valueOf(row[13])));
                    //ar.add(ptdBean);
                }
            } else {
                msg = "Property Id incorrect";
                useRegisgration.setMsg(msg);
            }

//            if(rows.size()>0){
//               msg="Property Id Found" ;
//               
//            }else{
//               msg="Property Id incorrect" ; 
//            }
//           
        } catch (Exception e) {
            //e.printStackTrace();
            logger.info(e.getMessage());
        } finally {
            if (sessionHB != null) {
                sessionHB.close();
            }
        }

        return useRegisgration;

    }

    public UserRegistration getImage(String id) {
        Session sessionHB = null;
        UserRegistration beanImage = new UserRegistration();
        StringBuffer sb = new StringBuffer();
        try {
            // System.out.println("ask session "+id);
            String imagedata = "image";
            sessionHB = sessionFactory.openSession();
            sb.append(" select " + imagedata + " from property_details where property_unique_id='" + id + "' ");
            sessionHB = sessionFactory.openSession();
            Query query1 = sessionHB.createSQLQuery(sb.toString());
            List<Map<String, byte[]>> ls;

            query1.setResultTransformer(Criteria.ALIAS_TO_ENTITY_MAP);
            ls = (List<Map<String, byte[]>>) query1.list();
            if (ls.size() > 0) {
                beanImage.setImageData(ls.get(0).get(imagedata));
            }

//            List rows = query1.list();
//            Iterator itr=rows.iterator();
//            if(itr.hasNext()){
//             Object obj=   (Object)itr.next();
//             ByteArrayOutputStream bos = new ByteArrayOutputStream();
//             ObjectOutput out = null;
//             out = new ObjectOutputStream(bos);   
//             out.writeObject(obj);
//             //out.flush();
//             byte[] yourBytes = bos.toByteArray();
//             System.out.println("yourBytes "+yourBytes.length);
//             beanImage.setImageData(yourBytes);
//            }
//             
            //System.out.println("session object "+sessionHB);
            //sessionHB.save(bean);
            //System.out.println("sessionhhhh");
            //beanImage = (UserRegistration) sessionHB.get(UserRegistration.class, id);
            //msg="SuccessFully Inserted";
        } catch (Exception e) {
            e.printStackTrace();
            logger.info(e.getMessage());
        } finally {
            if (sessionHB != null) {
                sessionHB.close();
            }
        }
        return beanImage;
    }

    public String generateBillNumber(String taxObj) {
        Session sessionHB = null;
        Criteria criteria = null;
        StringBuffer sb = new StringBuffer();
        StringBuffer sb1 = new StringBuffer();
        StringBuffer sb2 = new StringBuffer();
        int recordCtr = 0;
        int idCount = 0;
        int idCountFloor = 0;
        String zone = "";
        String ward = "";
        String propId = "";
        SimpleDateFormat sdf = new SimpleDateFormat(("dd-MM-yyyy hh:mm:ss"));
        Calendar c2 = Calendar.getInstance();
        c2.add(Calendar.DAY_OF_MONTH, 1);
        String date2 = (String) sdf.format(c2.getTime());
        date2 = date2.substring(0, 10);
        DecimalFormat df = new DecimalFormat("#,###");
        Calendar c = Calendar.getInstance();
        c.add(Calendar.DAY_OF_MONTH, 15);
        String newDate = sdf.format(c.getTime());
        newDate = newDate.substring(0, 10);
        int privateNoticeNo = 0;
        String msg = "";
        String dd = "";
        boolean flag = false;

        try {

            JSONObject jo = new Gson().fromJson(taxObj, JSONObject.class);

            if (!jo.get("zoneid").equals("-1")) {
                sb1.append(" zone='" + jo.get("zoneid") + "'");
                flag = true;
            }
            if (!jo.get("prop_id").equals("")) {
                if (flag == true) {
                    sb1.append(" and property_unique_id='" + jo.get("prop_id") + "'");
                } else {
                    sb1.append("property_unique_id='" + jo.get("prop_id") + "'");
                    flag = true;
                }
            }
            if (!jo.get("wardid").equals("")) {
                if (flag == true) {
                    sb1.append("and ward='" + jo.get("wardid") + "'");
                } else {
                    sb1.append("ward='" + jo.get("wardid") + "'");
                    flag = true;
                }
            }
            if (!jo.get("locality").equals("")) {
                if (flag == true) {
                    sb1.append("and property_locality='" + jo.get("locality") + "'");
                } else {
                    sb1.append("property_locality='" + jo.get("locality") + "'");
                    flag = true;
                }
            }
            if (!jo.get("phoneNo").equals("")) {
                if (flag == true) {
                    sb1.append("and property_contact='" + jo.get("phoneNo") + "'");
                } else {
                    sb1.append(" property_contact='" + jo.get("phoneNo") + "'");
                }
            }
            if (!jo.get("ownerId").equals("")) {
                if (flag == true) {
                    sb1.append("and property_owner='" + jo.get("ownerId") + "'");
                } else {
                    sb1.append(" property_owner='" + jo.get("ownerId") + "'");
                }
            }

            sb.append(" select property_unique_id,ward,property_old_smc_prop_tax_num,distribution_id from property_details where " + sb1.toString() + " order by distribution_id ");
            /* if (!jo.get("zoneid").equals("-1")) {
             zone = (String) jo.get("zoneid");
             if (!jo.get("wardid").equals("-1")) {
             ward = (String) jo.get("wardid");
             sb.append(" select property_unique_id,ward,property_old_smc_prop_tax_num,distribution_id from property_details where ward='" + ward + "'  order by distribution_id ");

             }
             } else if (!jo.get("prop_id").equals("")) {
             propId = (String) jo.get("prop_id");
             sb.append(" select property_unique_id,ward,property_old_smc_prop_tax_num,distribution_id from property_details where property_unique_id='" + propId + "'  order by distribution_id ");

             }*/

            System.out.println("sql " + sb.toString());
            sessionHB = sessionFactory.openSession();

            //criteria = sessionHB.createCriteria(PropertyDetails.class);
            //criteria.setProjection(Projections.projectionList().add(Projections.property("propertyUniqueId").as("propertyUniqueId")));
            Query query1 = sessionHB.createSQLQuery(sb.toString());

            Query query2 = sessionHB.createSQLQuery("select max(cast(bill_no as int)) as notice_no from property_details");
            List<Integer> rows1 = query2.list();

            Iterator<Integer> itr = rows1.iterator();
            while (itr.hasNext()) {
                Integer data = (Integer) itr.next();
                privateNoticeNo = data;
            }
            privateNoticeNo = privateNoticeNo + 1;
            if (privateNoticeNo <= 9) {
                dd = "0000" + privateNoticeNo;
            } else if (privateNoticeNo > 9 && privateNoticeNo < 100) {
                dd = "000" + privateNoticeNo;
            } else if (privateNoticeNo > 100 && privateNoticeNo < 1000) {
                dd = "00" + privateNoticeNo;
            } else if (privateNoticeNo > 1000 && privateNoticeNo < 100000) {
                dd = "0" + privateNoticeNo;
            }

//            for(Object[] row : rows1){
//              if(row[0]!=null){
//                  String noticeno=String.valueOf(row[0]);
//                  privateNoticeNo=Integer.parseInt(noticeno);
//              }  
//            }
            //System.out.println("privateNoticeNo "+privateNoticeNo);
            List<Object[]> rows = query1.list();
            //System.out.println("gggg "+rows.size());
            if (rows.size() > 0) {
                for (Object[] row : rows) {
                    recordCtr++;
                    if (row[0] != null) {
                        String id = String.valueOf(row[0]);

                        //System.out.println(" id notice no "+id);
                        Query query = sessionHB.createSQLQuery("update property_details set bill_no=:notice,service_date_for_bill=:servicedate,due_date_for_bill=:duedate,check_bill='Y' where property_unique_id=:uid");
                        query.setParameter("notice", dd);
                        query.setParameter("servicedate", date2);
                        query.setParameter("duedate", newDate);
                        query.setParameter("uid", id);
                        int result = query.executeUpdate();
                        privateNoticeNo = privateNoticeNo + 1;
                        if (privateNoticeNo <= 9) {
                            dd = "0000" + privateNoticeNo;
                        } else if (privateNoticeNo > 9 && privateNoticeNo < 100) {
                            dd = "000" + privateNoticeNo;
                        } else if (privateNoticeNo > 100 && privateNoticeNo < 1000) {
                            dd = "00" + privateNoticeNo;
                        } else if (privateNoticeNo > 1000 && privateNoticeNo < 100000) {
                            dd = "0" + privateNoticeNo;
                        }

                    }

                }
            } else {
                msg = "No record found to generate bill  number";
                return msg;
            }
            if (ward != null && ward != "-1") {
                // msg = "Bill No. successfully generated for ward " + ward + " , total records " + recordCtr;
                msg = "Bill No. successfully generated, total records " + recordCtr;
            } else if (propId != null) {
                //  msg = "PBill No. successfully generated for property unique id " + propId + " , total records " + recordCtr;
                msg = "Bill No. successfully generated, total records " + recordCtr;
            } else {
                msg = "Bill No. not generated";
            }

            // System.out.println("assessmentBean "+assessmentBean.size());
        } catch (Exception e) {
            e.printStackTrace();
            logger.info(e.getMessage());

        } finally {
            if (sessionHB != null) {
                sessionHB.flush();
                sessionHB.close();
            }
        }
        return msg;
    }

    @Override
    public List getChartData() {
        Session sessionHB = null;
        String msg = "";
        StringBuffer sb = new StringBuffer();
        List<Object[]> lst = new ArrayList();
        List<Map<String, Object>> dataList = new ArrayList<Map<String, Object>>();
        //UserRegistration useRegisgration=new UserRegistration();
        try {
            //System.out.println("session "+bean.getUniqueId());
            sb.append("Select count(*),a.ward_no from(Select ward_no,count(*) as count from property_correction_form group by ward_no,unique_id )as a group by a.ward_no");
            sessionHB = sessionFactory.openSession();
            Query query1 = sessionHB.createSQLQuery(sb.toString());
            lst = query1.list();
            Map<String, Object> dataMap = new HashMap<String, Object>();
            for (Object[] row : lst) {
                dataMap.put(row[1].toString(), row[0]);
                //dataList.add(dataMap);
            }
            dataList.add(dataMap);
        } catch (Exception e) {
            //e.printStackTrace();
            logger.info(e.getMessage());
        } finally {
            if (sessionHB != null) {
                sessionHB.close();
            }
        }

        return dataList;

    }

    @Override
    public List getWardWiseData(String ward) {
        Session sessionHB = null;
        String msg = "";
        StringBuffer sb = new StringBuffer();
        List<Object[]> lst = new ArrayList();
        List<Map<String, Object>> dataList = new ArrayList<Map<String, Object>>();
        //UserRegistration useRegisgration=new UserRegistration();
        try {
            //System.out.println("session "+bean.getUniqueId());
            sb.append("Select owner_name ,occupier_name,unique_id from property_correction_form where ward_no ='" + ward + "'");
            sessionHB = sessionFactory.openSession();
            Query query1 = sessionHB.createSQLQuery(sb.toString());
            query1.setResultTransformer(Criteria.ALIAS_TO_ENTITY_MAP);
            lst = query1.list();
        } catch (Exception e) {
            //e.printStackTrace();
            logger.info(e.getMessage());
        } finally {
            if (sessionHB != null) {
                sessionHB.close();
            }
        }

        return lst;

    }

    @Override
    public Map<String, String> getZoneWardMaster() {
        Session sessionHB = null;
        Criteria criteria = null;
        List<Wardmaster> results = null;
        Map<String, String> m = new HashMap<String, String>();
        try {
            sessionHB = sessionFactory.openSession();
            criteria = sessionHB.createCriteria(Wardmaster.class);
            results = criteria.list();
            for (Wardmaster wm : results) {
                m.put(wm.getWard(), wm.getZoneAssociated());
            }
        } catch (Exception e) {
            logger.info(e.getMessage());
        } finally {
            if (sessionHB != null) {
                sessionHB.close();
            }
        }
        return m;
    }

    public List<Map> getPropertys(String searchStr) {
        Session session = null;
        Query q = null;
        List<Map> ls = new ArrayList<Map>();
        try {
            session = sessionFactory.openSession();
            q = session.createSQLQuery("select  distinct property_unique_id  from property_details where  property_unique_id like  upper(concat('%',:searchStr,'%'))  limit 30");
            q.setString("searchStr", searchStr);
            System.out.println("searchStr" + q);
            q.setResultTransformer(Criteria.ALIAS_TO_ENTITY_MAP);
            ls = q.list();
        } catch (Exception ex) {
            ex.printStackTrace();
            logger.info("[SilvassaServiceImpl] getPropertys : " + ex.getMessage());
        } finally {
            if (null != session) {
                session.close();
                session = null;
            }
        }
        return ls;
    }

    public List<Map> getPhoneNos(String searchStr) {
        Session session = null;
        Query q = null;
        List<Map> ls = new ArrayList<Map>();
        try {
            session = sessionFactory.openSession();
            q = session.createSQLQuery("select  distinct property_contact  from property_details where  property_contact like  upper(concat('%',:searchStr,'%'))  limit 30");
            q.setString("searchStr", searchStr);
            System.out.println("searchStr" + q);
            q.setResultTransformer(Criteria.ALIAS_TO_ENTITY_MAP);
            ls = q.list();
        } catch (Exception ex) {
            ex.printStackTrace();
            logger.info("[SilvassaServiceImpl] getPhoneNos : " + ex.getMessage());
        } finally {
            if (null != session) {
                session.close();
                session = null;
            }
        }
        return ls;
    }

    public List<Map> getCityCodes(String searchStr) {
        Session session = null;
        Query q = null;
        List<Map> ls = new ArrayList<Map>();
        try {
            session = sessionFactory.openSession();
            q = session.createSQLQuery("select  distinct city_code  from property_details where  property_contact like  upper(concat('%',:searchStr,'%'))  limit 30");
            q.setString("searchStr", searchStr);
            System.out.println("searchStr" + q);
            q.setResultTransformer(Criteria.ALIAS_TO_ENTITY_MAP);
            ls = q.list();
        } catch (Exception ex) {
            ex.printStackTrace();
            logger.info("[SilvassaServiceImpl] getCityCodes : " + ex.getMessage());
        } finally {
            if (null != session) {
                session.close();
                session = null;
            }
        }
        return ls;
    }

    public List<Map> getaadharNo(String searchStr) {
        Session session = null;
        Query q = null;
        List<Map> ls = new ArrayList<Map>();
        try {
            session = sessionFactory.openSession();
            q = session.createSQLQuery("select  distinct property_aadhar_num  from property_details where  property_aadhar_num like  upper(concat('%',:searchStr,'%'))  limit 30");
            q.setString("searchStr", searchStr);
            System.out.println("searchStr" + q);
            q.setResultTransformer(Criteria.ALIAS_TO_ENTITY_MAP);
            ls = q.list();
        } catch (Exception ex) {
            ex.printStackTrace();
            logger.info("[SilvassaServiceImpl] getaadharNo : " + ex.getMessage());
        } finally {
            if (null != session) {
                session.close();
                session = null;
            }
        }
        return ls;
    }

    public List<Map> getsubLocality(String searchStr) {
        Session session = null;
        Query q = null;
        List<Map> ls = new ArrayList<Map>();
        try {
            session = sessionFactory.openSession();
            q = session.createSQLQuery("select  distinct property_sublocality  from property_details where  property_sublocality like  initcap(concat('%',:searchStr,'%'))  limit 30");
            q.setString("searchStr", searchStr);
            System.out.println("searchStr" + q);
            q.setResultTransformer(Criteria.ALIAS_TO_ENTITY_MAP);
            ls = q.list();
        } catch (Exception ex) {
            ex.printStackTrace();
            logger.info("[SilvassaServiceImpl] getsubLocality : " + ex.getMessage());
        } finally {
            if (null != session) {
                session.close();
                session = null;
            }
        }
        return ls;
    }

    public List<Map> getWardlst(String searchStr) {
        Session session = null;
        Query q = null;
        List<Map> ls = new ArrayList<Map>();
        try {
            session = sessionFactory.openSession();
            q = session.createSQLQuery("select  distinct ward  from ms_ward where  ward like  upper(concat('%',:searchStr,'%'))  limit 30");
            q.setString("searchStr", searchStr);
            System.out.println("searchStr" + q);
            q.setResultTransformer(Criteria.ALIAS_TO_ENTITY_MAP);
            ls = q.list();
        } catch (Exception ex) {
            ex.printStackTrace();
            logger.info("[SilvassaServiceImpl] getWardlst : " + ex.getMessage());
        } finally {
            if (null != session) {
                session.close();
                session = null;
            }
        }
        return ls;
    }

    public List<Map> getOwnerlst(String searchStr) {
        Session session = null;
        Query q = null;
        List<Map> ls = new ArrayList<Map>();
        try {
            session = sessionFactory.openSession();
            q = session.createSQLQuery("select  distinct property_owner  from property_details where  property_owner like  initcap(concat('%',:searchStr,'%'))  limit 30");
            q.setString("searchStr", searchStr);
            System.out.println("searchStr" + q);
            q.setResultTransformer(Criteria.ALIAS_TO_ENTITY_MAP);
            ls = q.list();
        } catch (Exception ex) {
            ex.printStackTrace();
            logger.info("[SilvassaServiceImpl] getOwnerlst : " + ex.getMessage());
        } finally {
            if (null != session) {
                session.close();
                session = null;
            }
        }
        return ls;
    }

    public List<Map> getOccupierlst(String searchStr) {
        Session session = null;
        Query q = null;
        List<Map> ls = new ArrayList<Map>();
        try {
            session = sessionFactory.openSession();
            q = session.createSQLQuery("select  distinct property_occupier_name  from property_details where  property_occupier_name like  initcap(concat('%',:searchStr,'%'))  limit 30");
            q.setString("searchStr", searchStr);
            System.out.println("searchStr" + q);
            q.setResultTransformer(Criteria.ALIAS_TO_ENTITY_MAP);
            ls = q.list();
        } catch (Exception ex) {
            ex.printStackTrace();
            logger.info("[SilvassaServiceImpl] getOccupierlst : " + ex.getMessage());
        } finally {
            if (null != session) {
                session.close();
                session = null;
            }
        }
        return ls;
    }

    public List<Map> getLocalitylst(String searchStr) {
        Session session = null;
        Query q = null;
        List<Map> ls = new ArrayList<Map>();
        try {
            session = sessionFactory.openSession();
            q = session.createSQLQuery("select  distinct property_locality  from property_details where  property_locality like  initcap(concat('%',:searchStr,'%'))  limit 30");
            q.setString("searchStr", searchStr);
            System.out.println("searchStr" + q);
            q.setResultTransformer(Criteria.ALIAS_TO_ENTITY_MAP);
            ls = q.list();
        } catch (Exception ex) {
            ex.printStackTrace();
            logger.info("[SilvassaServiceImpl] getLocalitylst : " + ex.getMessage());
        } finally {
            if (null != session) {
                session.close();
                session = null;
            }
        }
        return ls;
    }

    public List<Map> getPropertyTypeLst(String searchStr) {
        Session session = null;
        Query q = null;
        List<Map> ls = new ArrayList<Map>();
        try {
            session = sessionFactory.openSession();
            q = session.createSQLQuery("select  distinct property_category_desc  from property_details where  property_category_desc like  initcap(concat('%',:searchStr,'%'))  limit 30");
            q.setString("searchStr", searchStr);
            System.out.println("searchStr" + q);
            q.setResultTransformer(Criteria.ALIAS_TO_ENTITY_MAP);
            ls = q.list();
        } catch (Exception ex) {
            ex.printStackTrace();
            logger.info("[SilvassaServiceImpl] getPropertyTypeLst : " + ex.getMessage());
        } finally {
            if (null != session) {
                session.close();
                session = null;
            }
        }
        return ls;
    }

    public List<Map> getHouseLst(String searchStr) {
        Session session = null;
        Query q = null;
        List<Map> ls = new ArrayList<Map>();
        try {
            session = sessionFactory.openSession();
            q = session.createSQLQuery("select  distinct property_house_no  from property_details where  property_house_no like  upper(concat('%',:searchStr,'%'))  limit 30");
            q.setString("searchStr", searchStr);
            System.out.println("searchStr" + q);
            q.setResultTransformer(Criteria.ALIAS_TO_ENTITY_MAP);
            ls = q.list();
        } catch (Exception ex) {
            ex.printStackTrace();
            logger.info("[SilvassaServiceImpl] getHouseLst : " + ex.getMessage());
        } finally {
            if (null != session) {
                session.close();
                session = null;
            }
        }
        return ls;
    }

    public String getArrearAndOldTax(String uid) {
        Session sessionHB = null;
        StringBuffer sb = new StringBuffer();
        String msg = "";
        String allMsg = "";
        String correction_id = "";
        String action_taken = "";
        String arrearAmount = "0";
        String oldTax = "0";
        String arrearOldtax = "";

        try {
            sessionHB = sessionFactory.openSession();
            sb.append("select property_unique_id,arrear_amount,oldtax  from property_tax where property_unique_id='" + uid + "' and financial_year='2017-2018'");
            Query query = sessionHB.createSQLQuery(sb.toString());
            List<Object[]> rows = query.list();
            for (Object[] row : rows) {
                if (row[1] != null) {
                    arrearAmount = row[1].toString();
                } else {
                    arrearAmount = "0";
                }
                if (row[2] != null) {
                    oldTax = row[2].toString();
                } else {
                    oldTax = "0";
                }

            }
            arrearOldtax = arrearAmount + ":" + oldTax;

        } catch (Exception ex) {
            logger.info("[get msg form correction   history Exception : " + ex.getMessage());
        } finally {
            if (sessionHB != null) {
                sessionHB.flush();
                sessionHB.clear();
                sessionHB.close();
            }
        }

        return arrearOldtax;

    }

    public List<Usermaster> getUserId(String userId) {

        Session currentSession = null;
        List<Usermaster> usermaster = null;
        try {
            currentSession = sessionFactory.openSession();
            Criteria cr = currentSession.createCriteria(Usermaster.class);
            cr.add(Restrictions.eq("userId", userId));
            usermaster = cr.list();
            return usermaster;
        } catch (Exception e) {
            logger.error("Exception " + e.getMessage());
        } finally {
            if (currentSession != null) {
                currentSession.flush();
                currentSession.close();
            }
        }
        return null;
    }

    @Override
    public String updateUserId(Usermaster usermaster) {
        Session sessionHB = null;
        String status = "fail";
        try {
            sessionHB = sessionFactory.openSession();
            String sqlQuery = "update usermaster set username=:userN,email=:emailN,mobile=:mobileN,emp_id=:empIdN,address=:addressN,pasword=:passN where user_id=:userId";
            SQLQuery query = sessionHB.createSQLQuery(sqlQuery);

            query.setParameter("userN", usermaster.getUsername());
            query.setParameter("emailN", usermaster.getEmail());
            query.setParameter("mobileN", usermaster.getMobile());
            query.setParameter("empIdN", usermaster.getEmpId());
            query.setParameter("addressN", usermaster.getAddress());
            query.setParameter("passN", usermaster.getPasword());
            query.setParameter("userId", usermaster.getUserId());
            int rowCount = query.executeUpdate();

            //sessionHB.save(usermaster);
            status = "success";
        } catch (Exception e) {
            e.printStackTrace();
            logger.info(e.getMessage());
            status = "fail";
        } finally {
            sessionHB.close();
        }
        return status;
    }

    public List getGeoMetry(String type, String dataList) {
        Session session = null;
        Query q = null;
        List<Map> ls = new ArrayList<Map>();
        try {
            session = sessionFactory.openSession();
            if (type.equalsIgnoreCase("ward_check")) {
                q = session.createSQLQuery("Select ST_AsGeoJSON(geom) as geom from ward_boundary_region where ward_no in('" + dataList + "')");
            } else if (type.equalsIgnoreCase("locality_check")) {
                q = session.createSQLQuery("Select ST_AsGeoJSON(geom) as geom from smc_locality_boundary where loclity in('" + dataList + "')");
            } else if (type.equalsIgnoreCase("sub_check")) {
                q = session.createSQLQuery("Select ST_AsGeoJSON(geom) as geom from smc_sub_locality where subloclity in('" + dataList + "')");
            }
            System.out.println("searchStr" + q);
            q.setResultTransformer(Criteria.ALIAS_TO_ENTITY_MAP);
            ls = q.list();
        } catch (Exception ex) {
            ex.printStackTrace();
            logger.info("[SilvassaServiceImpl] getGeometryData : " + ex.getMessage());
        } finally {
            if (null != session) {
                session.close();
                session = null;
            }
        }
        return ls;
    }

    public String billPdfHistory(String userId, String taxObj) {
        Session sessionHB = null;
        Criteria criteria = null;
        StringBuffer sb = new StringBuffer();
        String zone = "";
        String ward = "";
        String propId = "";
        String uid = "";
        String msg = "Bill PDF History updated";
        SimpleDateFormat sdf = new SimpleDateFormat(("dd-MM-yyyy hh:mm:ss"));
        String date2 = (String) sdf.format(Calendar.getInstance().getTime());
        date2 = date2.substring(0, 10);
        try {

            logger.info("bill pdf histroy start");
            JSONObject jo = new Gson().fromJson(taxObj, JSONObject.class);

            boolean flag = false;
            sb.append(" select d.property_unique_id,d.property_owner from property_details d where ");
            if (!jo.get("zoneid").equals("-1")) {
                if (flag == true) {
                    sb.append("and");
                }
                sb.append("d.zone='" + jo.get("zoneid") + "'");
                flag = true;
            }
            if (!jo.get("prop_id").equals("")) {
                if (flag == true) {
                    sb.append("and");
                }
                sb.append("d.property_unique_id='" + jo.get("prop_id") + "'");
                flag = true;
            }
            if (!jo.get("wardid").equals("")) {
                if (flag == true) {
                    sb.append("and");
                }
                sb.append("d.ward='" + jo.get("wardid") + "'");
                flag = true;
            }
            if (!jo.get("locality").equals("")) {
                if (flag == true) {
                    sb.append("and");
                }
                sb.append("d.property_locality='" + jo.get("locality") + "'");
                flag = true;
            }
            if (!jo.get("phoneNo").equals("")) {
                if (flag == true) {
                    sb.append("and");
                }
                sb.append("d.property_contact='" + jo.get("phoneNo") + "'");
                flag = true;
            }
            if (!jo.get("ownerId").equals("")) {
                if (flag == true) {
                    sb.append("and");
                }
                sb.append("d.property_owner='" + jo.get("ownerId") + "'");
                flag = true;
            }

            /*if (!jo.get("zoneid").equals("-1")) {
                zone = (String) jo.get("zoneid");
                if (!jo.get("wardid").equals("-1")) {
                    ward = (String) jo.get("wardid");
                    sb.append(" select d.property_unique_id,d.property_owner from property_details d where d.ward='" + ward + "'");

                }
            } else if (!jo.get("prop_id").equals("")) {
                propId = (String) jo.get("prop_id");
                sb.append(" select d.property_unique_id,d.property_owner from property_details d where d.property_unique_id='" + propId + "'");
                //sb.append(" from property_details d ,property_tax t where d.property_unique_id=t.property_unique_id and t.financial_year ='2018-2019' and  d.property_unique_id='" + propId + "' order by d.distribution_id ,d.property_unique_id");

            }*/

            //System.out.println("sql "+sb.toString());
            sessionHB = sessionFactory.openSession();

            //criteria = sessionHB.createCriteria(PropertyDetails.class);
            //criteria.setProjection(Projections.projectionList().add(Projections.property("propertyUniqueId").as("propertyUniqueId")));
            Query query1 = sessionHB.createSQLQuery(sb.toString());
            List<Object[]> rows = query1.list();
            logger.info("bill pdf history size " + rows.size());
            for (Object[] row : rows) {
                if (row[0] != null) {
                    uid = String.valueOf(row[0]);
                    Query query2 = sessionHB.createSQLQuery("update property_details set user_id='" + userId + "' , generate_bill_pdf_date = now() where property_unique_id='" + uid + "' ");
                    query2.executeUpdate();
                }

            }

            logger.info("bill pdf history end");

        } catch (Exception e) {
            logger.info(e.getMessage());

        } finally {
            if (sessionHB != null) {
                sessionHB.flush();
                sessionHB.close();
            }
        }

        return msg;
    }

}
