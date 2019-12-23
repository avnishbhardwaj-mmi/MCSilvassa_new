/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.silvassa.dao;

import com.silvassa.bean.CorrectionAction;
import com.silvassa.bean.CorrectionFormLoadData;
import com.silvassa.bean.ImageBean;
import com.silvassa.bean.LoginDetailsBean;
import com.silvassa.bean.PropertyFloorData;
import com.silvassa.model.CorrectionFormBean;
import com.silvassa.model.CorrectionFormFloorBean;
import com.silvassa.model.CorrectionFormFloorReport;
import com.silvassa.model.CorrectionFormHitLogger;
import com.silvassa.model.CorrectionFormImageBean;
import com.silvassa.model.CorrectionFormReport;
import com.silvassa.model.CorrectionFormSaveBean;
import com.silvassa.model.CorrectionRequestBean;
import com.silvassa.model.MobileBean;
import com.silvassa.model.PropertyDetails;
import com.silvassa.model.CorrectionActionHistory;
import java.io.ByteArrayOutputStream;
import java.io.InputStream;
import java.io.ObjectOutputStream;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import org.apache.commons.codec.binary.Base64;
import org.apache.commons.io.IOUtils;
import org.apache.log4j.Logger;
import org.hibernate.Criteria;
import org.hibernate.Query;
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
@Component("CorrectionDAOImpl")
public class CorrectionDAOImpl implements CorrectionDAO {

    private Logger logger = Logger.getLogger(CorrectionDAOImpl.class);

    @Autowired
    @Qualifier("sessionHB")
    private SessionFactory sessionFactory;

    @Override
    public CorrectionFormBean getCorrectionForm(String propId, String applicationNo) {

        Session sessionHB = null;
        CorrectionFormBean pftb = null;

        try {

            sessionHB = sessionFactory.openSession();
            Criteria criteria = sessionHB.createCriteria(CorrectionFormBean.class);
            criteria.add(Restrictions.eq("uniqueId", propId));
            criteria.add(Restrictions.eq("application_no", applicationNo));
            pftb = (CorrectionFormBean) criteria.uniqueResult();
            pftb.setFloorDetails(getFloorDetails(propId, applicationNo));

        } catch (Exception ex) {
            logger.info("[CorrectionFormFloorBean.getCorrectionForm] Exception : " + ex.getMessage());
        } finally {
            if (sessionHB != null) {
                sessionHB.flush();
                sessionHB.clear();
                sessionHB.close();
            }
        }

        return pftb;
    }

    public List<CorrectionFormFloorBean> getFloorDetails(String propertyId, String applicationNo) throws Exception {

        Session sessionHB = null;
        List<CorrectionFormFloorBean> floorDetails = null;

        try {

            sessionHB = sessionFactory.openSession();
            Criteria criteria = sessionHB.createCriteria(CorrectionFormFloorBean.class);
            criteria.add(Restrictions.eq("uniqueId", propertyId));
            criteria.add(Restrictions.eq("application_no", applicationNo));
            floorDetails = (List<CorrectionFormFloorBean>) criteria.list();

        } catch (Exception ex) {
            logger.info("[CorrectionFormFloorBean.getFloorDetails] Exceprion : " + ex.getMessage());
            throw ex;
        } finally {
            if (sessionHB != null) {
                sessionHB.flush();
                sessionHB.clear();
                sessionHB.close();
            }
        }

        return floorDetails;
    }

    @Override
    public CorrectionFormLoadData getPropertyDetail(String propId) {

        Session sessionHB = null;
        CorrectionFormLoadData ptdBean = null;
        StringBuffer sb = new StringBuffer();
        ArrayList<CorrectionFormLoadData> ar_bean = new ArrayList<CorrectionFormLoadData>();
        CorrectionFormLoadData propDetail = new CorrectionFormLoadData();
        Map<String, PropertyFloorData> floorMap = new HashMap<>();
        try {
            //System.out.println("session "+bean.getUniqueId());
            sb.append(" select d.property_unique_id,d.ward,d.property_owner,d.property_owner_father,d.property_contact ,");
            sb.append(" d.property_owner_email,d.property_occupier_name,d.complete_address,d.occupier_father, ");
            sb.append(" d.occupier_contactno,d.occupier_email,f.prop_class,f.pf_electric_con_num,d.property_owner_spouse,d.property_house_no,d.property_building_name,d.property_sublocality,d.property_landmark,d.property_city,d.property_pincode,d.property_plot_num,d.property_locality,d.property_road ");
            sb.append(",f.pf_id,pf_floor_name,f.pf_builtup_area,f.pf_floorwise_build_use,f.pf_property_subtype,f.pf_construction_type,f.pf_self_rent,f.pf_annual_rent_value,d.property_aadhar_num,d.occupier_aadhar_no,d.smc_survey_no,d.smc_plot_no,t.arrear_amount, d.owner_sex,d.occupier_sex,d.property_owner_address, d.property_old_smc_prop_tax_num,r.rentable_value,d.private_notice_no ");
            sb.append(" from property_details d, property_tax t,property_floor f,property_rentable r where d.property_unique_id=f.property_unique_id and d.property_unique_id= t.property_unique_id and f.property_rentable_id=r.property_rentable_id and d.property_unique_id='" + propId + "' and t.financial_year='2017-2018' order by f.flooriwse_short");
            sessionHB = sessionFactory.openSession();

            Query query1 = sessionHB.createSQLQuery(sb.toString());
            List<Object[]> rows = query1.list();
            for (Object[] row : rows) {

                ptdBean = new CorrectionFormLoadData();
                ptdBean.setUniqueId(row[0] == null ? "" : String.valueOf(row[0]));
                ptdBean.setWardNo(row[1] == null ? "" : String.valueOf(row[1]));
                ptdBean.setOwnerName(row[2] == null ? "" : String.valueOf(row[2]));
                if (row[3] == null) {
                    ptdBean.setOwnerFather("");
                } else {
                    ptdBean.setOwnerFather(row[3].toString());
                }
                ptdBean.setOwnerContact(row[4] == null ? "" : String.valueOf(row[4]));
                ptdBean.setOwnerEmail(row[5] == null ? "" : String.valueOf(row[5]));
                ptdBean.setOccupierName(row[6] == null ? "" : String.valueOf(row[6]));
                ptdBean.setAddress(row[7] == null ? "" : String.valueOf(row[7]));
                ptdBean.setOccupierFather(row[8] == null ? "" : String.valueOf(row[8]));
                ptdBean.setOccupierContact(row[9] == null ? "" : String.valueOf(row[9]));
                ptdBean.setOccupierEmail(row[10] == null ? "" : String.valueOf(row[10]));
                ptdBean.setOccupierEmail(row[10] == null ? "" : String.valueOf(row[10]));
                ptdBean.setLocationClass(row[11] == null ? "" : String.valueOf(row[11]));
                ptdBean.setElectricServiceConNo(row[12] == null ? "" : String.valueOf(row[12]));

                ptdBean.setSpouseName(row[13] == null ? "" : String.valueOf(row[13]));
                ptdBean.setHoueNo(row[14] == null ? "" : String.valueOf(row[14]));
                ptdBean.setBuildingName(row[15] == null ? "" : String.valueOf(row[15]));
                ptdBean.setSubLocality(row[16] == null ? "" : String.valueOf(row[16]));
                ptdBean.setLandMark(row[17] == null ? "" : String.valueOf(row[17]));
                ptdBean.setCity(row[18] == null ? "" : String.valueOf(row[18]));
                ptdBean.setPincode(row[19] == null ? "" : String.valueOf(row[19]));
                ptdBean.setPlotNo(row[20] == null ? "" : String.valueOf(row[20]));
                ptdBean.setLocName(row[21] == null ? "" : String.valueOf(row[21]));
                ptdBean.setRoadName(row[22] == null ? "" : String.valueOf(row[22]));

                ptdBean.setOwnerAadharNo(row[31] == null ? "" : String.valueOf(row[31]));
                ptdBean.setOccupierAadharNo(row[32] == null ? "" : String.valueOf(row[32]));
                ptdBean.setSurveyNo(row[33] == null ? "" : String.valueOf(row[33]));
                ptdBean.setPlotSmc(row[34] == null ? "" : String.valueOf(row[34]));
                ptdBean.setArrearAmount(row[35] == null ? "0" : String.valueOf(row[35]));
                ptdBean.setOwnerSex(row[36] == null ? "" : String.valueOf(row[36]));
                ptdBean.setOccupierSex(row[37] == null ? "" : String.valueOf(row[37]));
                ptdBean.setPropertyOwnerAddress(row[38] == null ? "" : String.valueOf(row[38]));
                ptdBean.setSmcProperty(row[39] == null ? "" : String.valueOf(row[39]));
                ptdBean.setPresumeRent(row[40] == null ? "" : String.valueOf(row[40]));
                ptdBean.setPrivateNoticeNo(row[41] == null ? "0" : String.valueOf(row[41]));

                PropertyFloorData pfd = new PropertyFloorData();
                pfd.setPropertyFloorId(row[23] == null ? "" : String.valueOf(row[23]));
                pfd.setFloorType(row[24] == null ? "" : String.valueOf(row[24]));
                pfd.setCarpetArea(row[25] == null ? "" : String.valueOf(row[25]));
                pfd.setPropertyUse(row[26] == null ? "" : String.valueOf(row[26]));
                pfd.setPropertySubType(row[27] == null ? "" : String.valueOf(row[27]));
                pfd.setConstructionType(row[28] == null ? "" : String.valueOf(row[28]));
                pfd.setSelfRent(row[29] == null ? "" : String.valueOf(row[29]));
                pfd.setRentedValue(row[30] == null ? "" : String.valueOf(row[30]));
                floorMap.put(pfd.getPropertyFloorId(), pfd);
                ar_bean.add(ptdBean);

            }

            if (ar_bean.size() > 0) {
                propDetail = ar_bean.get(0);
                propDetail.setFloorMap(floorMap);
            }

        } catch (Exception e) {
            e.printStackTrace();
            logger.info(e.getMessage());
        } finally {
            if (sessionHB != null) {
                sessionHB.flush();
                sessionHB.clear();
                sessionHB.close();
            }
        }
        return propDetail;
    }

    @Override
    public void markCorrectionAsRead(String propId, String applicationNo) {
        Session sessionHB = null;
        try {

            sessionHB = sessionFactory.openSession();
            Query query = sessionHB.createSQLQuery("INSERT INTO correction_status(correction_id, property_id, status) "
                    + " select application_no, unique_id, 'read' from property_correction_form where application_no=? and unique_id = ?");
            query.setString(0, applicationNo);
            query.setString(1, propId);
            query.executeUpdate();
        } catch (Exception e) {
//            e.printStackTrace();
        } finally {
            if (sessionHB != null) {
                sessionHB.flush();
                sessionHB.clear();
                sessionHB.close();
            }
        }
    }

    @Override
    public void addUserAction(CorrectionAction correctionAction, LoginDetailsBean loginUserBean) {
        Session sessionHB = null;
        try {
            sessionHB = sessionFactory.openSession();
            Query query = sessionHB.createSQLQuery("INSERT INTO correction_action_history"
                    + " (property_id, correction_id, action_by, action_taken, action_remarks) "
                    + " VALUES (?, ?, ?, ?, ?)");
            query.setString(0, correctionAction.getPropertyId());
            query.setString(1, correctionAction.getApplicationNo());
            query.setString(2, loginUserBean.getUserName());
            query.setString(3, correctionAction.getCorrectionStatus());
            query.setString(4, correctionAction.getCorrectionRemarks());
            query.executeUpdate();

        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            if (sessionHB != null) {
                sessionHB.flush();
                sessionHB.clear();
                sessionHB.close();
            }
        }
    }

    @Override
    public void updateCorrectionStatus(CorrectionAction correctionAction) {
        Session sessionHB = null;
        try {
            sessionHB = sessionFactory.openSession();
            Query query = sessionHB.createSQLQuery("UPDATE correction_status SET status=?, entrydatetime=now() "
                    + " WHERE correction_id=? and property_id=?");
            query.setString(0, correctionAction.getCorrectionStatus());
            query.setString(1, correctionAction.getApplicationNo());
            query.setString(2, correctionAction.getPropertyId());
            query.executeUpdate();
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            if (sessionHB != null) {
                sessionHB.flush();
                sessionHB.clear();
                sessionHB.close();
            }
        }
    }

    /*   @Override
     public Map<String, Object> getCorrectionCount(int actionFliter) {
     Session sessionHB = null;
     List<Map<String, String>> pftb = null;
     Map<String, Object> map = new HashMap<>();
     try {
     sessionHB = sessionFactory.openSession();
     Query query = sessionHB.createSQLQuery("with total_count as (select count(*) as t_count from property_correction_form "
     + " where to_date(notice_date, 'DD-MM-YYYY') >  now() + interval ' - " + actionFliter + " months' ),"
     + " correction_data as (select notice_date,unique_id,application_no,status from property_correction_form "
     + " left join correction_status on correction_id = application_no "
     + " where to_date(notice_date, 'DD-MM-YYYY') >  now() + interval ' - " + actionFliter + " months')"
     + " select *, (tcount*100)/t_count as percntg from ("
     + " select 'total' as cnttype, count(*) as tcount from correction_data union all"
     + " select 'inbox' as cnttype, count(*) as tcount from correction_data where status is null or status ='read' union all"
     + " select 'solve' as cnttype, count(*) as tcount from correction_data where status ='applied' union all"
     + " select 'approve' as cnttype, count(*) as tcount from correction_data where status ='approve' union all"
     + " select 'reject' as cnttype, count(*) as tcount from correction_data where status ='reject' union all"
     + " select 'fieldverify' as cnttype, count(*) as tcount from correction_data where status ='fieldverify' union all "
     + " select 'overdue' as cnttype, count(*) as tcount from correction_data where (status is null or status ='read') "
     + " and to_date(notice_date, 'DD-MM-YYYY') >  now() + interval ' - 21 days') as aa_,total_count");

            
     query.setResultTransformer(Criteria.ALIAS_TO_ENTITY_MAP);
     pftb = (List<Map<String, String>>) query.list();
     for (Map m : pftb) {
     map.put(String.valueOf(m.get("cnttype")), m);
     }
     } catch (Exception ex) {
     ex.printStackTrace();

     } finally {
     if (sessionHB != null) {
     sessionHB.flush();
     sessionHB.clear();
     sessionHB.close();
     }
     }
     return map;
     }*/
    @Override
    public Map<String, Object> getCorrectionCount(int actionFliter) {
        Session sessionHB = null;
        List<Map<String, String>> pftb = null;
        Map<String, Object> map = new HashMap<>();
        try {
            sessionHB = sessionFactory.openSession();
            Query query = sessionHB.createSQLQuery("with total_count as (select count(*) as t_count from property_correction_form "
                    + " where to_date(notice_date, 'DD-MM-YYYY') >=  date_trunc('day', now()) + interval ' - " + actionFliter + " months' ),"
                    + " correction_data as (select notice_date,unique_id,application_no,status from property_correction_form "
                    + " left join correction_status on correction_id = application_no "
                    + " where to_date(notice_date, 'DD-MM-YYYY') >=  date_trunc('day', now()) + interval ' - " + actionFliter + " months')"
                    + " select *, (tcount*100)/t_count as percntg from ("
                    + " select 'total' as cnttype, count(*) as tcount from correction_data union all"
                    + " select 'inbox' as cnttype, count(*) as tcount from correction_data where status is null or status ='read' union all"
                    + " select 'solve' as cnttype, count(*) as tcount from correction_data where status ='applied' union all"
                    + " select 'approve' as cnttype, count(*) as tcount from correction_data where status ='approve' union all"
                    + " select 'reject' as cnttype, count(*) as tcount from correction_data where status ='reject' union all"
                    + " select 'fieldverify' as cnttype, count(*) as tcount from correction_data where status ='fieldverify' union all "
                    + " select 'overdue' as cnttype, count(*) as tcount from correction_data where (status is null or status ='read') "
                    + " and to_date(notice_date, 'DD-MM-YYYY') >=  date_trunc('day', now()) + interval ' - 21 days') as aa_,total_count");

            query.setResultTransformer(Criteria.ALIAS_TO_ENTITY_MAP);
            pftb = (List<Map<String, String>>) query.list();
            for (Map m : pftb) {
                map.put(String.valueOf(m.get("cnttype")), m);
            }
        } catch (Exception ex) {
            ex.printStackTrace();

        } finally {
            if (sessionHB != null) {
                sessionHB.flush();
                sessionHB.clear();
                sessionHB.close();
            }
        }
        return map;
    }

    @Override
    public Map<String, Map> getCorrectionCountOnWard(int actionFliter) {
        Session sessionHB = null;
        List<Map<String, String>> pftb = null;
        LinkedHashMap<String, Map> map = new LinkedHashMap<String, Map>();
        try {
            sessionHB = sessionFactory.openSession();
            Query query = sessionHB.createSQLQuery("select * from ( "
                    + " with total_count as ("
                    + "	select ward_no,count(*) as t_count from property_correction_form "
                    + " where to_date(notice_date, 'DD-MM-YYYY') >  now() + interval ' - " + actionFliter + " months' group by ward_no "
                    + " ),"
                    + " correction_data as ("
                    + "	select notice_date,unique_id,application_no,status, ward_no from property_correction_form "
                    + "	left join correction_status on correction_id = application_no "
                    + "	where to_date(notice_date, 'DD-MM-YYYY') >  now() + interval ' - " + actionFliter + " months'"
                    + " )"
                    + " select t_count, aaa.*, round((tcount*100)/t_count, -1) as percntg from ("
                    + " select 'total' as cnttype, count(*) as tcount, ward_no from correction_data group by ward_no  union all"
                    + " select 'inbox' as cnttype, count(*) as tcount, ward_no from correction_data where status is null or status ='read'  group by ward_no union all"
                    + " select 'solve' as cnttype, count(*) as tcount, ward_no from correction_data where status ='applied' group by ward_no union all"
                    + " select 'approve' as cnttype, count(*) as tcount, ward_no from correction_data where status ='approve' group by ward_no union all"
                    + " select 'reject' as cnttype, count(*) as tcount, ward_no from correction_data where status ='reject' group by ward_no union all"
                    + " select 'fieldverify' as cnttype, count(*) as tcount, ward_no from correction_data where status ='fieldverify' group by ward_no union all"
                    + " select 'overdue' as cnttype, count(*) as tcount, ward_no from correction_data where (status is null or status ='read') "
                    + " and to_date(notice_date, 'DD-MM-YYYY') >  now() + interval ' - 21 days' group by ward_no) aaa, total_count "
                    + " where aaa.ward_no=total_count.ward_no )"
                    + " dataward order by cast(SUBSTRING(ward_no,3,length(ward_no)) as INTEGER)  ");

            query.setResultTransformer(Criteria.ALIAS_TO_ENTITY_MAP);
            pftb = (List<Map<String, String>>) query.list();
            Map inMap = null;
            for (Map m : pftb) {
                inMap = null;
                if (!map.containsKey(String.valueOf(m.get("ward_no")))) {
                    map.put(String.valueOf(m.get("ward_no")), new HashMap<>());
                }
                inMap = map.get(String.valueOf(m.get("ward_no")));
                inMap.put(String.valueOf(m.get("cnttype")), String.valueOf(m.get("tcount")));

            }
        } catch (Exception ex) {
            ex.printStackTrace();

        } finally {
            if (sessionHB != null) {
                sessionHB.flush();
                sessionHB.clear();
                sessionHB.close();
            }
        }
        return map;
    }

    @Override
    public List<CorrectionFormBean> getCorrectionListAll(int actionFliter) {

        Session sessionHB = null;
        List<CorrectionFormBean> pftb = null;

        try {
            sessionHB = sessionFactory.openSession();
            Query query = sessionHB.createSQLQuery("select application_no, applicant_name, notice_date, unique_id, ward_no, owner_name, status, "
                    + " case when correction_id is null then 'Y' else 'N' end as isnew from property_correction_form "
                    + " left join correction_status on correction_id = application_no"
                    + " where to_date(notice_date, 'DD-MM-YYYY') >  now() + interval ' - " + actionFliter + " months' order by to_date(notice_date,'DD-MM-YYYY')");

            query.setResultTransformer(Criteria.ALIAS_TO_ENTITY_MAP);
            pftb = query.list();

        } catch (Exception ex) {
            logger.info("[CorrectionFormFloorBean.getCorrectionListAll] Exception : " + ex.getMessage());
        } finally {
            if (sessionHB != null) {
                sessionHB.flush();
                sessionHB.clear();
                sessionHB.close();
            }
        }

        return pftb;
    }

    @Override
    public List<CorrectionFormBean> getCorrectionListInbox() {
        Session sessionHB = null;
        List<CorrectionFormBean> pftb = null;

        try {
            sessionHB = sessionFactory.openSession();
            Query query = sessionHB.createSQLQuery("select application_no, applicant_name, notice_date, unique_id, ward_no, owner_name, status, "
                    + " case when correction_id is null then 'Y' else 'N' end as isnew  from property_correction_form "
                    + " left join correction_status on correction_id = application_no where status is null or status ='read' order by to_date(notice_date,'DD-MM-YYYY')");

            query.setResultTransformer(Criteria.ALIAS_TO_ENTITY_MAP);
            pftb = query.list();

        } catch (Exception ex) {
            logger.info("[CorrectionFormFloorBean.getCorrectionListAll] Exception : " + ex.getMessage());
        } finally {
            if (sessionHB != null) {
                sessionHB.flush();
                sessionHB.clear();
                sessionHB.close();
            }
        }

        return pftb;
    }

    @Override
    public List<CorrectionFormBean> getCorrectionListDone() {
        Session sessionHB = null;
        List<CorrectionFormBean> pftb = null;

        try {
            sessionHB = sessionFactory.openSession();
            Query query = sessionHB.createSQLQuery("select application_no, applicant_name, notice_date, unique_id, ward_no, owner_name, status, "
                    + " case when correction_id is null then 'Y' else 'N' end as isnew from property_correction_form "
                    + " left join correction_status on correction_id = application_no where status ='applied'");

            query.setResultTransformer(Criteria.ALIAS_TO_ENTITY_MAP);
            pftb = query.list();

        } catch (Exception ex) {
            logger.info("[CorrectionFormFloorBean.getCorrectionListAll] Exception : " + ex.getMessage());
        } finally {
            if (sessionHB != null) {
                sessionHB.flush();
                sessionHB.clear();
                sessionHB.close();
            }
        }

        return pftb;
    }

    @Override
    public List<CorrectionFormBean> getCorrectionListApproved() {
        Session sessionHB = null;
        List<CorrectionFormBean> pftb = null;

        try {
            sessionHB = sessionFactory.openSession();
            Query query = sessionHB.createSQLQuery("select application_no, applicant_name, notice_date, unique_id, ward_no, owner_name, status, "
                    + " case when correction_id is null then 'Y' else 'N' end as isnew from property_correction_form "
                    + " left join correction_status on correction_id = application_no where status ='approve'");

            query.setResultTransformer(Criteria.ALIAS_TO_ENTITY_MAP);
            pftb = query.list();

        } catch (Exception ex) {
            logger.info("[CorrectionFormFloorBean.getCorrectionListAll] Exception : " + ex.getMessage());
        } finally {
            if (sessionHB != null) {
                sessionHB.flush();
                sessionHB.clear();
                sessionHB.close();
            }
        }

        return pftb;
    }

    @Override
    public List<CorrectionFormBean> getCorrectionListReject() {
        Session sessionHB = null;
        List<CorrectionFormBean> pftb = null;

        try {
            sessionHB = sessionFactory.openSession();
            Query query = sessionHB.createSQLQuery("select application_no, applicant_name, notice_date, unique_id, ward_no, owner_name, status, "
                    + " case when correction_id is null then 'Y' else 'N' end as isnew from property_correction_form "
                    + " left join correction_status on correction_id = application_no where status ='reject'");

            query.setResultTransformer(Criteria.ALIAS_TO_ENTITY_MAP);
            pftb = query.list();

        } catch (Exception ex) {
            logger.info("[CorrectionFormFloorBean.getCorrectionListAll] Exception : " + ex.getMessage());
        } finally {
            if (sessionHB != null) {
                sessionHB.flush();
                sessionHB.clear();
                sessionHB.close();
            }
        }

        return pftb;
    }

    @Override
    public List<CorrectionFormBean> getCorrectionListLongHalt() {
        Session sessionHB = null;
        List<CorrectionFormBean> pftb = null;

        try {
            sessionHB = sessionFactory.openSession();
            Query query = sessionHB.createSQLQuery("select application_no, applicant_name, notice_date, unique_id, ward_no, owner_name, status, "
                    + " case when correction_id is null then 'Y' else 'N' end as isnew from property_correction_form "
                    + " left join correction_status on correction_id = application_no where "
                    + " (status is null or status ='read') and to_date(notice_date, 'DD-MM-YYYY') >  now() + interval ' - 21 days' order by to_date(notice_date,'DD-MM-YYYY')");

            query.setResultTransformer(Criteria.ALIAS_TO_ENTITY_MAP);
            pftb = query.list();

        } catch (Exception ex) {
            logger.info("[CorrectionFormFloorBean.getCorrectionListAll] Exception : " + ex.getMessage());
        } finally {
            if (sessionHB != null) {
                sessionHB.flush();
                sessionHB.clear();
                sessionHB.close();
            }
        }

        return pftb;
    }

    @Override
    public List<CorrectionFormBean> getCorrectionListFieldVerify() {
        Session sessionHB = null;
        List<CorrectionFormBean> pftb = null;

        try {
            sessionHB = sessionFactory.openSession();
            Query query = sessionHB.createSQLQuery("select application_no, applicant_name, notice_date, unique_id, ward_no, owner_name, status, "
                    + " case when correction_id is null then 'Y' else 'N' end as isnew from property_correction_form "
                    + " left join correction_status on correction_id = application_no where status ='fieldverify' order by to_date(notice_date,'DD-MM-YYYY')");

            query.setResultTransformer(Criteria.ALIAS_TO_ENTITY_MAP);
            pftb = query.list();

        } catch (Exception ex) {
            logger.info("[CorrectionFormFloorBean.getCorrectionListAll] Exception : " + ex.getMessage());
        } finally {
            if (sessionHB != null) {
                sessionHB.flush();
                sessionHB.clear();
                sessionHB.close();
            }
        }

        return pftb;
    }

    @Override
    public List<Map<String, String>> getCorrectionActionHistory(String propId, String applicationNo) {
        Session sessionHB = null;
        List<Map<String, String>> pftb = null;

        try {
            sessionHB = sessionFactory.openSession();
            Query query = sessionHB.createSQLQuery("SELECT action_by, action_taken, action_remarks, to_char(entrydatetime, 'DD-MM-YYYY HH:mm AM') as entrydatetime FROM correction_action_history "
                    + " where correction_id =? and property_id=? order by entrydatetime desc");
            query.setString(0, applicationNo);
            query.setString(1, propId);
            query.setResultTransformer(Criteria.ALIAS_TO_ENTITY_MAP);
            pftb = (List<Map<String, String>>) query.list();

        } catch (Exception ex) {
            logger.info("[CorrectionFormFloorBean.getCorrectionActionHistory] Exception : " + ex.getMessage());
        } finally {
            if (sessionHB != null) {
                sessionHB.flush();
                sessionHB.clear();
                sessionHB.close();
            }
        }

        return pftb;
    }

    @Override
    public String getCorrectionStatus(String propId, String applicationNo) {
        Session sessionHB = null;
        List<Map<String, String>> pftb = null;

        try {
            sessionHB = sessionFactory.openSession();
            Query query = sessionHB.createSQLQuery("SELECT status FROM correction_status where  correction_id =? and property_id=?");
            query.setString(0, applicationNo);
            query.setString(1, propId);
            query.setResultTransformer(Criteria.ALIAS_TO_ENTITY_MAP);
            pftb = (List<Map<String, String>>) query.list();
            if (pftb.size() > 0) {
                return pftb.get(0).get("status");
            }

        } catch (Exception ex) {
            logger.info("[CorrectionFormFloorBean.getCorrectionActionHistory] Exception : " + ex.getMessage());
        } finally {
            if (sessionHB != null) {
                sessionHB.flush();
                sessionHB.clear();
                sessionHB.close();
            }
        }

        return null;
    }

    @Override
    public List<Map<String, String>> getCorrectionImageProof(String propId, String applicationNo) {
        Session sessionHB = null;
        List<Map<String, String>> pftb = null;

        try {
            sessionHB = sessionFactory.openSession();
            Query query = sessionHB.createSQLQuery("select img_id, split_part(img_val, ';', 1) as img_name, split_part(img_val, ';', 2) as img_col from ("
                    + " with correction_proof_data as (select * from property_correction_form where application_no=? and unique_id=?)"
                    + " SELECT unnest(array['prop_use_img_1', 'covered_img_2']) AS \"img_id\", unnest(array[prop_use_img_1, covered_img_2]) AS \"img_val\""
                    + " FROM (select imagefilecovered || ';image_covered' as prop_use_img_1,imagefilepropertyuse || ';image_property_use' as covered_img_2  from correction_proof_data ) aa"
                    + " union all"
                    + " SELECT unnest(array['owner_img_1', 'owner_img_2', 'owner_img_3', 'owner_img_4']) AS \"img_id\", "
                    + " unnest(array[owner_img_1, owner_img_2, owner_img_3, owner_img_4]) AS \"img_val\""
                    + " FROM ( select imagefilename || ';image' as owner_img_1, imagefileowner2 || ';image_owner2' as owner_img_2,"
                    + " imagefilenameowner2 || ';image_owner3' as owner_img_3,imagefilenameowner3 || ';image_owner4' as owner_img_4 "
                    + " from correction_proof_data) aa"
                    + " union all"
                    + " SELECT unnest(array['occupier_img_1', 'occupier_img_2', 'occupier_img_3', 'occupier_img_4']) AS \"img_id\","
                    + " unnest(array[occupier_img_1, occupier_img_2, occupier_img_3, occupier_img_4]) AS \"img_val\""
                    + " FROM (select imagefilenameoccupier || ';image_occupier' as occupier_img_1,imagefileoccupier2 || ';image_occupier2' as occupier_img_2,"
                    + " imagefilenameoccupier2 || ';image_occupier3' as occupier_img_3,imagefilenameoccupier3 || ';image_occupier4' as occupier_img_4"
                    + " from correction_proof_data) aa"
                    + " union all"
                    + " SELECT unnest(array['address_img_1', 'address_img_2', 'address_img_3', 'address_img_4']) AS \"img_id\","
                    + " unnest(array[address_img_1, address_img_2, address_img_3, address_img_4]) AS \"img_val\""
                    + " FROM (select imagefilenameaddress || ';image_address' as address_img_1,imagefileaddress2 || ';image_address2' as address_img_2,"
                    + " imagefilenameaddress2 || ';image_address3' as address_img_3,imagefilenameaddress3 || ';image_address4' as address_img_4"
                    + " from correction_proof_data) aa"
                    + " union all"
                    + " SELECT unnest(array['electric_img_1']) AS \"img_id\", unnest(array[electric_img_1]) AS \"img_val\""
                    + " FROM (select imagefilenmaeelectric || ';image_electric' as electric_img_1 from  correction_proof_data) aa"
                    + " union all"
                    + " SELECT unnest(array['arrear_img_1']) AS \"img_id\", unnest(array[arrear_img_1]) AS \"img_val\""
                    + " FROM (select imagefilearrear || ';image_arrear' as arrear_img_1 from  correction_proof_data) aa) img_d where img_val is not null;");
            query.setString(0, applicationNo);
            query.setString(1, propId);
            query.setResultTransformer(Criteria.ALIAS_TO_ENTITY_MAP);
            pftb = (List<Map<String, String>>) query.list();

        } catch (Exception ex) {
            logger.info("[CorrectionFormFloorBean.getCorrectionActionHistory] Exception : " + ex.getMessage());
        } finally {
            if (sessionHB != null) {
                sessionHB.flush();
                sessionHB.clear();
                sessionHB.close();
            }
        }

        return pftb;
    }

    @Override
    public ImageBean getProofImage(String propId, String applicationNo, String imageCol) {
        Session sessionHB = null;
        ImageBean pftb = new ImageBean();
        List<Map<String, byte[]>> ls;

        try {
            sessionHB = sessionFactory.openSession();
            Query query = sessionHB.createSQLQuery("select " + imageCol + " from property_correction_form where application_no = ? and unique_id = ?");

            query.setString(0, applicationNo);
            query.setString(1, propId);
            query.setResultTransformer(Criteria.ALIAS_TO_ENTITY_MAP);
            ls = (List<Map<String, byte[]>>) query.list();
            if (ls.size() > 0) {
                pftb.setImagebytes(ls.get(0).get(imageCol));
            }

        } catch (Exception ex) {
            logger.info("[CorrectionFormFloorBean.getProofImage] Exception : " + ex.getMessage());
        } finally {
            if (sessionHB != null) {
                sessionHB.flush();
                sessionHB.clear();
                sessionHB.close();
            }
        }

        return pftb;
    }

    public List<Map<String, String>> getCorrectionOfflineListAll(int actionFliter) {

        Session sessionHB = null;
        List<Map<String, String>> pftb = null;

        try {
            sessionHB = sessionFactory.openSession();
            Query query = sessionHB.createSQLQuery("SELECT application_no, upload_date, applicant_name, unique_id,  apllicant_mobile,"
                    + " applicant_email, offline_counter_ref_no FROM property_correction_form_image"
                    + " where to_date(upload_date, 'DD-MM-YYYY') >  now() + interval ' - " + actionFliter + " months' and move_from_offline_to_online='N' order by to_date(upload_date,'DD-MM-YYYY')");

            query.setResultTransformer(Criteria.ALIAS_TO_ENTITY_MAP);
            pftb = (List<Map<String, String>>) query.list();

        } catch (Exception ex) {
            logger.info("[CorrectionFormFloorBean.getCorrectionListAll] Exception : " + ex.getMessage());
        } finally {
            if (sessionHB != null) {
                sessionHB.flush();
                sessionHB.clear();
                sessionHB.close();
            }
        }

        return pftb;
    }

    public CorrectionFormImageBean getCorrectionOfflineForm(String propId, String applicationNo) {

        Session sessionHB = null;
        CorrectionFormImageBean pftb = null;

        try {

            sessionHB = sessionFactory.openSession();
            Criteria criteria = sessionHB.createCriteria(CorrectionFormImageBean.class);
            criteria.add(Restrictions.eq("uniqueId", propId));
            criteria.add(Restrictions.eq("application_no", applicationNo));
            pftb = (CorrectionFormImageBean) criteria.uniqueResult();

            pftb.setImageFile(new String(Base64.encodeBase64(pftb.getImage_byte())));
            pftb.setImageFile1(new String(Base64.encodeBase64(pftb.getImage_byte1())));
            pftb.setImageFile2(new String(Base64.encodeBase64(pftb.getImage_byte2())));

        } catch (Exception ex) {
            logger.info("[CorrectionFormFloorBean.getCorrectionForm] Exception : " + ex.getMessage());
        } finally {
            if (sessionHB != null) {
                sessionHB.flush();
                sessionHB.clear();
                sessionHB.close();
            }
        }

        return pftb;
    }

    public CorrectionFormFloorBean editPropertyFloorId(String floorId, String id, String applicatonNo) {

        Session sessionHB = null;
        String msg = "";
        CorrectionFormFloorBean ptdBean = null;
        List<CorrectionFormFloorBean> ar = new ArrayList<CorrectionFormFloorBean>();
        StringBuffer sb = new StringBuffer();

        try {
            //System.out.println("session "+bean.getUniqueId());
            sb.append("select property_floor_id ,uniqueid,floortype,carpetarea,propertyuse,propertysubtype, ");
            sb.append(" constructiontype,selfrent,rentedvalue,editdata,deletedata,application_no,permission_data,editdata  ");
            sb.append("  from property_correction_form_floor where property_floor_id='" + floorId + "' and uniqueid='" + id + "' and application_no='" + applicatonNo + "' ");
            sessionHB = sessionFactory.openSession();
            //System.out.println("query floor "+sb.toString());
            Query query1 = sessionHB.createSQLQuery(sb.toString());
            List<Object[]> rows = query1.list();
            for (Object[] row : rows) {
                //System.out.println("row[15] mark"+row[15]);
                //System.out.println("row[11] road"+row[11]);

                ptdBean = new CorrectionFormFloorBean();
//               

                ptdBean.setPropertyFloorId(row[0] == null ? "" : String.valueOf(row[0]));
                ptdBean.setUniqueId(row[1] == null ? "" : String.valueOf(row[1]));

                ptdBean.setFloorType(row[2] == null ? "" : String.valueOf(row[2]));
                ptdBean.setCarpetArea(row[3] == null ? "" : String.valueOf(row[3]));
                ptdBean.setPropertyUse(row[4] == null ? "" : String.valueOf(row[4]));
                ptdBean.setPropertySubType(row[5] == null ? "" : String.valueOf(row[5]));

                ptdBean.setConstructionType(row[6] == null ? "" : String.valueOf(row[6]));
                ptdBean.setSelfRent(row[7] == null ? "" : String.valueOf(row[7]));
                ptdBean.setRentedValue(row[8] == null ? "" : String.valueOf(row[8]));
                ptdBean.setEditData(row[9] == null ? "" : String.valueOf(row[9]));

                ptdBean.setDeleteData(row[10] == null ? "" : String.valueOf(row[10]));
                ptdBean.setApplication_no(row[11] == null ? "" : String.valueOf(row[11]));
                ptdBean.setPermissionData(row[12] == null ? "" : String.valueOf(row[12]));
                ptdBean.setEditData(row[13] == null ? "" : String.valueOf(row[13]));

                //ptdBean.setDocumentType(row[12] == null ? "" : String.valueOf(row[12]));
                //ptdBean.setId(row[13] == null ? -1 : Integer.valueOf(String.valueOf(row[13])));
                //ar.add(ptdBean);
            }
            //bean= (CorrectionFormBean)sessionHB.get(CorrectionFormBean.class, id);
            //System.out.println("session object "+sessionHB);

        } catch (Exception e) {
            e.printStackTrace();
            logger.info(e.getMessage());
        } finally {
            if (sessionHB != null) {
                sessionHB.flush();
                sessionHB.clear();
                sessionHB.close();
            }
        }
        return ptdBean;

    }

    public CorrectionRequestBean editPropertyId(String id, String applicationNo) {
        Session sessionHB = null;
        String msg = "";
        CorrectionRequestBean ptdBean = null;
        List<CorrectionRequestBean> ar = new ArrayList<CorrectionRequestBean>();
        StringBuffer sb = new StringBuffer();

        try {
            //System.out.println("session "+bean.getUniqueId());
            sb.append("select unique_id,owner_name,owner_father,owner_contact,owner_email,occupier_name,occupier_father, ");
            sb.append(" occupier_contact,occupier_email,address,electric_service_connection_no, ");
            sb.append(" spouse_name ,house_no ,sublocality ,building_name ,landmark ,city ,pincode ,plotno ,locality_name,road_name,");
            sb.append("  check_owner_name ,check_owner_father ,check_spouse_name ,check_owner_contact ,check_owner_email ,check_occupier_name ,check_occupier_father ,check_occupier_contact,");
            sb.append(" check_occupier_email ,check_plot_no ,check_house_no ,check_building_name ,check_road_name,");
            sb.append(" check_sublocality ,check_loc_name ,check_land_mark ,check_electric_serice_connection_no,owneraadharno,occupieraadharno,surveyno,plotsmc,checkowneraadharno,checkoccupieraadharno,checksurveyno,checkplotsmc,checkarrear,arrearamount,smc_holding_no,check_smc_holding_no,owner_sex,occupier_sex,check_owner_sex,check_occupier_sex,property_owner_address,check_property_owner_address ");
            sb.append(" from property_correction_form where unique_id='" + id + "' and application_no='" + applicationNo + "'");
            sessionHB = sessionFactory.openSession();
            //System.out.println("query "+sb.toString());
            Query query1 = sessionHB.createSQLQuery(sb.toString());
            List<Object[]> rows = query1.list();
            for (Object[] row : rows) {
                //System.out.println("row[15] mark"+row[15]);
                //System.out.println("row[20] road"+row[20]);

                ptdBean = new CorrectionRequestBean();
//                ptdBean.setUniqueId(msg);
//                ptdBean.setOwnerName(msg);
//                ptdBean.setOwnerFather(queryForTAX);
//                ptdBean.setOwnerContact(queryForTAX);
//                ptdBean.setOwnerEmail(msg);
//                ptdBean.setOccupierName(queryForTAX);
//                ptdBean.setOccupierFather(queryForTAX);
//                ptdBean.setOccupierContact(queryForTAX);
//                ptdBean.setOccupierEmail(msg);
//                ptdBean.setAddress(msg);
//                ptdBean.setElectricSericeConnectionNo(queryForTAX);
//                ptdBean.setImageFileName(msg);

                ptdBean.setUniqueId(row[0] == null ? "" : String.valueOf(row[0]));
                ptdBean.setOwnerName(row[1] == null ? "" : String.valueOf(row[1]));
                if (row[2] == null) {
                    ptdBean.setOwnerFather("");
                    // System.out.println(" row[6].toString() if"+row[6].toString());
                } else {
                    ptdBean.setOwnerFather(row[2].toString());
                    //System.out.println(" row[6].toString() else "+row[6].toString());
                }
                ptdBean.setOwnerContact(row[3] == null ? "" : String.valueOf(row[3]));
                ptdBean.setOwnerEmail(row[4] == null ? "" : String.valueOf(row[4]));
                ptdBean.setOccupierName(row[5] == null ? "" : String.valueOf(row[5]));
                ptdBean.setOccupierFather(row[6] == null ? "" : String.valueOf(row[6]));

                ptdBean.setOccupierContact(row[7] == null ? "" : String.valueOf(row[7]));
                ptdBean.setOccupierEmail(row[8] == null ? "" : String.valueOf(row[8]));
                ptdBean.setAddress(row[9] == null ? "" : String.valueOf(row[9]));
                ptdBean.setElectricSericeConnectionNo(row[10] == null ? "" : String.valueOf(row[10]));
                ptdBean.setSpouseName(row[11] == null ? "" : String.valueOf(row[11]));
                ptdBean.setHoueNo(row[12] == null ? "" : String.valueOf(row[12]));
                ptdBean.setSubLocality(row[13] == null ? "" : String.valueOf(row[13]));
                ptdBean.setBuildingName(row[14] == null ? "" : String.valueOf(row[14]));
                ptdBean.setLandMark(row[15] == null ? "" : String.valueOf(row[15]));
                ptdBean.setCity(row[16] == null ? "" : String.valueOf(row[16]));
                ptdBean.setPincode(row[17] == null ? "" : String.valueOf(row[17]));
                ptdBean.setPlotNo(row[18] == null ? "" : String.valueOf(row[18]));
                ptdBean.setLocName(row[19] == null ? "" : String.valueOf(row[19]));
                ptdBean.setRoadName(row[20] == null ? "" : String.valueOf(row[20]));
                ptdBean.setCheckOwnerName(row[21] == null ? "" : String.valueOf(row[21]));
                ptdBean.setCheckOwnerFather(row[22] == null ? "" : String.valueOf(row[22]));
                ptdBean.setCheckSpouseName(row[23] == null ? "" : String.valueOf(row[23]));
                ptdBean.setCheckOwnerContact(row[24] == null ? "" : String.valueOf(row[24]));
                ptdBean.setCheckOwnerEmail(row[25] == null ? "" : String.valueOf(row[25]));
                ptdBean.setCheckOccupierName(row[26] == null ? "" : String.valueOf(row[26]));
                ptdBean.setCheckOccupierFather(row[27] == null ? "" : String.valueOf(row[27]));
                ptdBean.setCheckOccupierContact(row[28] == null ? "" : String.valueOf(row[28]));
                ptdBean.setCheckOccupierEmail(row[29] == null ? "" : String.valueOf(row[29]));
                ptdBean.setCheckPlotNo(row[30] == null ? "" : String.valueOf(row[30]));
                ptdBean.setCheckHouseNo(row[31] == null ? "" : String.valueOf(row[31]));
                ptdBean.setCheckBuildingName(row[32] == null ? "" : String.valueOf(row[32]));
                ptdBean.setCheckRoadName(row[33] == null ? "" : String.valueOf(row[33]));
                ptdBean.setCheckSubLocality(row[34] == null ? "" : String.valueOf(row[34]));
                ptdBean.setCheckLocName(row[35] == null ? "" : String.valueOf(row[35]));
                ptdBean.setCheckLandMark(row[36] == null ? "" : String.valueOf(row[36]));
                ptdBean.setCheckElectricSericeConnectionNo(row[37] == null ? "" : String.valueOf(row[37]));
                ptdBean.setOwnerAadharNo(row[38] == null ? "" : String.valueOf(row[38]));
                ptdBean.setOccupierAadharNo(row[39] == null ? "" : String.valueOf(row[39]));
                ptdBean.setSurveyNo(row[40] == null ? "" : String.valueOf(row[40]));
                ptdBean.setPlotSmc(row[41] == null ? "" : String.valueOf(row[41]));
                ptdBean.setCheckOwnerAadharNo(row[42] == null ? "" : String.valueOf(row[42]));
                ptdBean.setCheckOccupierAadharNo(row[43] == null ? "" : String.valueOf(row[43]));
                ptdBean.setCheckSurveyNo(row[44] == null ? "" : String.valueOf(row[44]));
                ptdBean.setCheckPlotSmc(row[45] == null ? "" : String.valueOf(row[45]));
                ptdBean.setCheckArrear(row[46] == null ? "" : String.valueOf(row[46]));
                ptdBean.setArrearAmount(row[47] == null ? "" : String.valueOf(row[47]));
                ptdBean.setSmcHoldingNo(row[48] == null ? "" : String.valueOf(row[48]));
                ptdBean.setCheckSmcHoldingNo(row[49] == null ? "" : String.valueOf(row[49]));
                ptdBean.setOwnerSex(row[50] == null ? "" : String.valueOf(row[50]));
                ptdBean.setOccupierSex(row[51] == null ? "" : String.valueOf(row[51]));
                ptdBean.setCheckOwnerSex(row[52] == null ? "" : String.valueOf(row[52]));
                ptdBean.setCheckOccupierSex(row[53] == null ? "" : String.valueOf(row[53]));
                ptdBean.setPropertyOwnerAddress(row[54] == null ? "" : String.valueOf(row[54]));
                ptdBean.setCheckPropertyOwnerAddress(row[55] == null ? "" : String.valueOf(row[55]));

            }
            //bean= (CorrectionFormBean)sessionHB.get(CorrectionFormBean.class, id);
            //System.out.println("session object "+sessionHB);

        } catch (Exception e) {
            e.printStackTrace();
            logger.info(e.getMessage());
        } finally {
            if (sessionHB != null) {
                sessionHB.flush();
                sessionHB.clear();
                sessionHB.close();
            }
        }
        return ptdBean;
    }

    public CorrectionFormSaveBean getUpdatedDataFromCorrectionForm(CorrectionRequestBean bean) {
        CorrectionFormSaveBean saveBean = null;
        if (bean != null) {
            String id = bean.getUniqueId();
            String ownerName = bean.getOwnerName();
            String ownerFather = bean.getOwnerFather();
            String ownerContact = bean.getOwnerContact();
            String ownerEmail = bean.getOwnerEmail();
            String occupierName = bean.getOccupierName();
            String occupierFather = bean.getOccupierFather();
            String occupierContact = bean.getOccupierContact();
            String occupierEmail = bean.getOccupierEmail();
            String address = bean.getAddress();
            String electricServiceNo = bean.getElectricSericeConnectionNo();
            String spouseName = bean.getSpouseName();
            String houseNo = bean.getHoueNo();
            String plotNo = bean.getPlotNo();
            String locName = bean.getLocName();
            String subLoc = bean.getSubLocality();
            String buildName = bean.getBuildingName();
            String landMark = bean.getLandMark();
            String roadName = bean.getRoadName();
            String ownerAadhar = bean.getOwnerAadharNo();
            String occupierAadhar = bean.getOccupierAadharNo();
            String smcSurveyNo = bean.getSurveyNo();
            String smcPlotNo = bean.getPlotSmc();
        //System.out.println("landMark 567 "+landMark);
            //System.out.println("roadName 567"+roadName);
            String checkOwnerName = bean.getCheckOwnerName();
            String checkOwnerFather = bean.getCheckOwnerFather();
            String checkOwnerContact = bean.getCheckOwnerContact();
            String checkOwnerEmail = bean.getCheckOwnerEmail();
            String checkOccupierName = bean.getCheckOccupierName();
            String checkOccupierFather = bean.getCheckOccupierFather();
            String checkOccupierContact = bean.getCheckOccupierContact();
        //System.out.println("occupierContact "+occupierContact);
            //System.out.println("checkOccupierContact "+checkOccupierContact);
            String checkOccupierEmail = bean.getCheckOccupierEmail();
            String checkHouseNo = bean.getCheckHouseNo();
            String checkSpouseName = bean.getCheckSpouseName();
            String checkPlotNo = bean.getCheckPlotNo();
            String CheckLocName = bean.getCheckLocName();
            String checkSublocName = bean.getCheckSubLocality();
            String checkLocName = bean.getCheckLocName();
            String checkRoadName = bean.getCheckRoadName();
            String checkBuildName = bean.getCheckBuildingName();
            String checkLandMark = bean.getCheckLandMark().trim();
            String checkElectri = bean.getCheckElectricSericeConnectionNo();

            String checkOwnerAadhar = bean.getCheckOwnerAadharNo();
            String checkOccupierAadhar = bean.getCheckOccupierAadharNo();
            String checkSmcSurveyNo = bean.getCheckSurveyNo();
            String checkSmcPlotNo = bean.getCheckPlotSmc();
            String arrearAmount = bean.getArrearAmount();
            String checkArrear = bean.getCheckArrear();
            String smcHoldingNo = bean.getSmcHoldingNo();
            String checkSmcHoldingNo = bean.getCheckSmcHoldingNo();
            String ownerSex = bean.getOwnerSex();
            String occupierSex = bean.getOccupierSex();
            String checkOwnerSex = bean.getCheckOwnerSex();
            String checkOccupierSex = bean.getCheckOccupierSex();
            String propertyOwnerAddress = bean.getPropertyOwnerAddress();
            String checkPropertyOnwerAddress = bean.getCheckPropertyOwnerAddress();

        //System.out.println("landMark 567 "+landMark);
            //System.out.println("checkLandMark "+checkLandMark);
            saveBean = new CorrectionFormSaveBean();
            saveBean.setUniqueId(id);
            if (ownerName != null && ownerName.length() > 0 && checkOwnerName.equalsIgnoreCase("Y")) {
                saveBean.setOwnerName(ownerName);
            }
            if (ownerFather != null && ownerFather.length() > 0 && checkOwnerFather.equalsIgnoreCase("Y")) {
                saveBean.setOwnerFather(ownerFather);
            }
            if (ownerContact != null && ownerContact.length() > 0 && checkOwnerContact.equalsIgnoreCase("Y")) {
                saveBean.setOwnerContact(ownerContact);
            }
            if (ownerEmail != null && ownerEmail.length() > 0 && checkOwnerEmail.equalsIgnoreCase("Y")) {
                saveBean.setOwnerEmail(ownerEmail);
            }
            if (occupierName != null && occupierName.length() > 0 && checkOccupierName.equalsIgnoreCase("Y")) {
                saveBean.setOccupierName(occupierName);
            }
            if (occupierFather != null && occupierFather.length() > 0 && checkOccupierFather.equalsIgnoreCase("Y")) {
                saveBean.setOccupierFather(occupierFather);
            }
            if (occupierContact != null && occupierContact.length() > 0 && checkOccupierContact.equalsIgnoreCase("Y")) {
                saveBean.setOccupierContact(occupierContact);
            }
            if (occupierEmail != null && occupierEmail.length() > 0 && checkOccupierEmail.equalsIgnoreCase("Y")) {
                saveBean.setOccupierEmail(occupierEmail);
            }
            /*if(address!=null && address.length()>0){
             saveBean.setAddress(address);
             }*/
            if (electricServiceNo != null && electricServiceNo.length() > 0 && checkElectri.equalsIgnoreCase("Y")) {
                saveBean.setElectricServiceNo(electricServiceNo);
            }

            if (spouseName != null && spouseName.length() > 0 && checkSpouseName.equalsIgnoreCase("Y")) {
                saveBean.setSpouseName(spouseName);
            }
            if (houseNo != null && houseNo.length() > 0 && checkHouseNo.equalsIgnoreCase("Y")) {
                saveBean.setHoueNo(houseNo);
            }
            if (plotNo != null && plotNo.length() > 0 && checkPlotNo.equalsIgnoreCase("Y")) {
                saveBean.setPlotNo(plotNo);
            }
            if (locName != null && locName.length() > 0 && CheckLocName.equalsIgnoreCase("Y")) {
                saveBean.setLocName(locName);
            }
            if (subLoc != null && subLoc.length() > 0 && checkSublocName.equalsIgnoreCase("Y")) {
                saveBean.setSubLocality(subLoc);
            }
            if (buildName != null && buildName.length() > 0 && checkBuildName.equalsIgnoreCase("Y")) {
                saveBean.setBuildingName(buildName);
            }
            if (landMark != null && landMark.length() > 0 && checkLandMark.equalsIgnoreCase("Y")) {
                saveBean.setLandMark(landMark);
            //System.out.println("landMark if 567 "+landMark);
                //System.out.println("checkLandMark if "+checkLandMark);
            }
            if (roadName != null && roadName.length() > 0 && checkRoadName.equalsIgnoreCase("Y")) {
                saveBean.setRoadName(roadName);
            }

            if (ownerAadhar != null && ownerAadhar.length() > 0 && checkOwnerAadhar.equalsIgnoreCase("Y")) {
                saveBean.setOwnerAadharNo(ownerAadhar);
            }
            if (occupierAadhar != null && occupierAadhar.length() > 0 && checkOccupierAadhar.equalsIgnoreCase("Y")) {
                saveBean.setOccupierAadharNo(occupierAadhar);
            }
            if (smcSurveyNo != null && smcSurveyNo.length() > 0 && checkSmcSurveyNo.equalsIgnoreCase("Y")) {
                saveBean.setSurveyNo(smcSurveyNo);
            }
            if (smcPlotNo != null && smcPlotNo.length() > 0 && checkSmcPlotNo.equalsIgnoreCase("Y")) {
                saveBean.setPlotSmc(smcPlotNo);
            }
            if (arrearAmount != null && arrearAmount.length() > 0 && checkArrear.equalsIgnoreCase("Y")) {
                saveBean.setArrearAmount(arrearAmount);
            }
            if (smcHoldingNo != null && smcHoldingNo.length() > 0 && checkSmcHoldingNo.equalsIgnoreCase("Y")) {
                saveBean.setSmcHoldingNo(smcHoldingNo);
            }
            if (ownerSex != null && ownerSex.length() > 0 && checkOwnerSex.equalsIgnoreCase("Y")) {
                saveBean.setOwnerSex(ownerSex);
            }
            if (occupierSex != null && occupierSex.length() > 0 && checkOccupierSex.equalsIgnoreCase("Y")) {
                saveBean.setOccupierSex(occupierSex);
            }
            if (propertyOwnerAddress != null && propertyOwnerAddress.length() > 0 && checkPropertyOnwerAddress.equalsIgnoreCase("Y")) {
                saveBean.setPropertyOwnerAddress(propertyOwnerAddress);
            }

        }
        return saveBean;
    }

    public List<CorrectionFormFloorBean> editPropertyFloorMultipleId(String id, String applicatonNo) {
        Session sessionHB = null;
        String msg = "";
        List<CorrectionFormFloorBean> floormultiple_ar = new ArrayList<CorrectionFormFloorBean>();
        CorrectionFormFloorBean ptdBean = null;
        List<CorrectionFormFloorBean> ar = new ArrayList<CorrectionFormFloorBean>();
        StringBuffer sb = new StringBuffer();

        try {
            //System.out.println("session "+bean.getUniqueId());
            sb.append("select property_floor_id ,uniqueid,floortype,carpetarea,propertyuse,propertysubtype, ");
            sb.append(" constructiontype,selfrent,rentedvalue,editdata,deletedata,application_no,permission_data ");
            sb.append("  from property_correction_form_floor where uniqueid='" + id + "' and application_no='" + applicatonNo + "' ");
            sessionHB = sessionFactory.openSession();
            //System.out.println("query floor "+sb.toString());
            Query query1 = sessionHB.createSQLQuery(sb.toString());
            List<Object[]> rows = query1.list();
            for (Object[] row : rows) {
                //System.out.println("row[15] mark"+row[15]);
                //System.out.println("row[11] road"+row[11]);

                ptdBean = new CorrectionFormFloorBean();
//               

                ptdBean.setPropertyFloorId(row[0] == null ? "" : String.valueOf(row[0]));
                ptdBean.setUniqueId(row[1] == null ? "" : String.valueOf(row[1]));

                ptdBean.setFloorType(row[2] == null ? "" : String.valueOf(row[2]));
                ptdBean.setCarpetArea(row[3] == null ? "" : String.valueOf(row[3]));
                ptdBean.setPropertyUse(row[4] == null ? "" : String.valueOf(row[4]));
                ptdBean.setPropertySubType(row[5] == null ? "" : String.valueOf(row[5]));

                ptdBean.setConstructionType(row[6] == null ? "" : String.valueOf(row[6]));
                ptdBean.setSelfRent(row[7] == null ? "" : String.valueOf(row[7]));
                ptdBean.setRentedValue(row[8] == null ? "" : String.valueOf(row[8]));
                ptdBean.setEditData(row[9] == null ? "" : String.valueOf(row[9]));

                ptdBean.setDeleteData(row[10] == null ? "" : String.valueOf(row[10]));
                ptdBean.setApplication_no(row[11] == null ? "" : String.valueOf(row[11]));
                ptdBean.setPermissionData(row[12] == null ? "" : String.valueOf(row[12]));

                floormultiple_ar.add(ptdBean);
                //ptdBean.setDocumentType(row[12] == null ? "" : String.valueOf(row[12]));
                //ptdBean.setId(row[13] == null ? -1 : Integer.valueOf(String.valueOf(row[13])));
                //ar.add(ptdBean);

            }
            //bean= (CorrectionFormBean)sessionHB.get(CorrectionFormBean.class, id);
            //System.out.println("session object "+sessionHB);

        } catch (Exception e) {
            e.printStackTrace();
            logger.info(e.getMessage());
        } finally {
            if (sessionHB != null) {
                sessionHB.flush();
                sessionHB.clear();
                sessionHB.close();
            }
        }
        return floormultiple_ar;
    }

    public String deleteCorrectionFormFloorData(CorrectionFormFloorBean floorBean) {
        Session sessionHB = null;
        String msg = "";
        try {
            System.out.println("session floor data " + floorBean.getUniqueId() + " " + floorBean.getPropertyFloorId());
            sessionHB = sessionFactory.openSession();

            if (floorBean.getUniqueId() != null) {
                Query query = sessionHB.createSQLQuery("delete from  property_floor where property_unique_id=:uid and pf_id=:floorid");
                query.setParameter("uid", floorBean.getUniqueId());
                query.setParameter("floorid", Integer.parseInt(floorBean.getPropertyFloorId()));
                int result = query.executeUpdate();
                //System.out.println("check1 "+result);

                Query query1 = sessionHB.createSQLQuery("delete from property_tax_details where floor_id=:floorid");
                //query1.setParameter("uid", floorBean.getUniqueId());
                query1.setParameter("floorid", Integer.parseInt(floorBean.getPropertyFloorId()));
                int result1 = query1.executeUpdate();
                //System.out.println("check2 "+result1);

            }

            Query query5 = sessionHB.createSQLQuery("UPDATE  property_floor set property_rentable_id = rt.property_rentable_id FROM property_rentable rt where UPPER(pf_floorwise_build_use) = UPPER(property_cat) and prop_class=property_subcat_code and property_unique_id=:uid ");
            query5.setParameter("uid", floorBean.getUniqueId());
            //query2.setParameter("floorid", Integer.parseInt(floorBean.getPropertyFloorId()));
            int result5 = query5.executeUpdate();

            StringBuffer sb2 = new StringBuffer();
            sb2.append(" update property_details pd set no_tax_type = 'Y' from ( ");
            sb2.append(" select * from ( ");
            sb2.append(" select property_unique_id, sum(pf_builtup_area) as pf_builtup_area from property_floor where property_unique_id not in( ");
            sb2.append(" select property_unique_id from property_floor where pf_floorwise_build_use <> 'Residential' ");
            sb2.append(") group by property_unique_id) flt_prop where pf_builtup_area <=269 and property_unique_id=:uid ");
            sb2.append(" ) no_tax_prop where pd.property_unique_id = no_tax_prop.property_unique_id");

            Query query3 = sessionHB.createSQLQuery(sb2.toString());
            query3.setParameter("uid", floorBean.getUniqueId());
            int result3 = query3.executeUpdate();

            Query query2 = sessionHB.createSQLQuery("update property_correction_form_floor set permission_data=:data1 where uniqueid=:uid and property_floor_id=:floorid and application_no=:app");
            query2.setParameter("data1", "Y");
            query2.setParameter("uid", floorBean.getUniqueId());
            query2.setParameter("floorid", floorBean.getPropertyFloorId());
            query2.setParameter("app", floorBean.getApplication_no());
            int result2 = query2.executeUpdate();

            Query query4 = sessionHB.createSQLQuery("update property_correction_form_floor set permission_data=:data1 where uniqueid=:uid and property_floor_id=:floorid and application_no=:app");
            query4.setParameter("data1", "Y");
            query4.setParameter("uid", floorBean.getUniqueId());
            query4.setParameter("floorid", floorBean.getPropertyFloorId());
            query4.setParameter("app", floorBean.getApplication_no());
            int result4 = query4.executeUpdate();

//            CorrectionFormSaveBean bean1=(CorrectionFormSaveBean)sessionHB.get(bean.getUniqueId());
//            System.out.println("id ghjkk "+bean1.getUniqueId());
//            System.out.println("owner "+bean1.getOwnerName());
//            System.out.println("owner father "+bean1.getOwnerFather());
//            System.out.println("owner contact "+bean1.getOwnerContact());
//            System.out.println("owner email "+bean1.getOwnerEmail());
//            System.out.println("occupier name "+bean1.getOccupierName());
//            System.out.println("occupier father "+bean1.getOccupierFather());
//            System.out.println("occupier contact "+bean1.getOccupierContact());
//            System.out.println("occupier email "+bean1.getOccupierEmail());
//            System.out.println("address "+bean1.getAddress());
//            sessionHB.update(bean1);
//            
            //System.out.println("sessionhhhh");
            msg = "Successfully updated";

        } catch (Exception e) {
            msg = "error in correction form";
            //e.printStackTrace();
            logger.info(e.getMessage());
        } finally {
            if (sessionHB != null) {
                sessionHB.flush();
                sessionHB.clear();
                sessionHB.close();
            }
        }

        return msg;

        //return ""; 
    }

    public String addNewCorrectionFormFloorData(CorrectionFormFloorBean floorBean) {
        Session sessionHB = null;
        String msg = "";
        String propClass = "";
        String pf_commercial_activity_name = "";
        String pf_commercial_type = "";
        String pf_water_pipe_con = "";
        String pf_water_con_num = "";
        String pf_sewerage_con = "";
        String pf_sewerage_con_num = "";
        String pf_electric_meter_num = "";
        String pf_electric_con_num = "";
        String pf_cctv_camrea = "";
        String pf_fire_equipment = "";
        String pf_lift_available = "";
        String pf_rain_water_harvest = "";
        String pf_num_of_borewells = "";
        String pf_sanitation = "";
        String pf_hording_avail = "";
        String pf_mobile_tower = "";
        //PropertyFloor pf=new PropertyFloor();
        StringBuffer sb = new StringBuffer();
        StringBuffer sb1 = new StringBuffer();
        String id = floorBean.getUniqueId();
        sb1.append("select property_unique_id,prop_class,pf_commercial_activity_name,pf_commercial_type,pf_water_pipe_con,pf_water_con_num,pf_sewerage_con,pf_sewerage_con_num,");
        sb1.append(" pf_electric_meter_num,pf_electric_con_num,pf_cctv_camrea,pf_fire_equipment,pf_lift_available,pf_rain_water_harvest,pf_num_of_borewells,pf_sanitation,pf_hording_avail,pf_mobile_tower from property_floor where property_unique_id='" + id + "' limit 1");

        sb.append("insert into property_floor(property_unique_id,pf_floor_name,pf_builtup_area,pf_construction_type,pf_floorwise_build_use, ");
        sb.append(" pf_property_subtype,pf_self_rent,pf_annual_rent_value,prop_class,pf_commercial_activity_name,pf_commercial_type,pf_water_pipe_con,pf_water_con_num,");
        sb.append(" pf_sewerage_con,pf_sewerage_con_num, pf_electric_meter_num,pf_electric_con_num,pf_cctv_camrea,pf_fire_equipment,pf_lift_available,pf_rain_water_harvest,pf_num_of_borewells,pf_sanitation,pf_hording_avail,pf_mobile_tower) ");
        sb.append("  values(:uid,:floorname,:cover,:ctype,:buse,:subtype,:self,:rentvalue,:propclass,:commactivity,:comtype,:waterpipecon,:waterpipeconno,:seweragecon,:sewerageconnum,");
        sb.append(" :electric_meter_num,:electric_con,:cctv_camrea,:fireequip,:lift,:rain_water,:borewells,:sanitation,:hording,:mobile_tower)");
        try {
            //System.out.println("session fllor data "+floorBean.getUniqueId());
            sessionHB = sessionFactory.openSession();

            if (floorBean.getUniqueId() != null) {

                Query query1 = sessionHB.createSQLQuery(sb1.toString());
                List<Object[]> rows = query1.list();
                for (Object[] row : rows) {
                //System.out.println("row[15] mark"+row[15]);
                    //System.out.println("row[20] road"+row[20]);

                    if (row[1] == null) {
                        propClass = "";
                    } else {
                        propClass = String.valueOf(row[1]);
                    }
                    if (row[2] == null) {
                        pf_commercial_activity_name = "";
                    } else {
                        pf_commercial_activity_name = String.valueOf(row[2]);
                    }
                    if (row[3] == null) {
                        pf_commercial_type = "";
                    } else {
                        pf_commercial_type = String.valueOf(row[3]);
                    }
                    if (row[4] == null) {
                        pf_water_pipe_con = "";
                    } else {
                        pf_water_pipe_con = String.valueOf(row[4]);
                    }
                    if (row[5] == null) {
                        pf_water_con_num = "";
                    } else {
                        pf_water_con_num = String.valueOf(row[5]);
                    }
                    if (row[6] == null) {
                        pf_sewerage_con = "";
                    } else {
                        pf_sewerage_con = String.valueOf(row[6]);
                    }
                    if (row[7] == null) {
                        pf_sewerage_con_num = "";
                    } else {
                        pf_sewerage_con_num = String.valueOf(row[7]);
                    }
                    if (row[8] == null) {
                        pf_electric_meter_num = "";
                    } else {
                        pf_electric_meter_num = String.valueOf(row[8]);
                    }
                    if (row[9] == null) {
                        pf_electric_con_num = "";
                    } else {
                        pf_electric_con_num = String.valueOf(row[9]);
                    }
                    if (row[10] == null) {
                        pf_cctv_camrea = "";
                    } else {
                        pf_cctv_camrea = String.valueOf(row[10]);
                    }
                    if (row[11] == null) {
                        pf_fire_equipment = "";
                    } else {
                        pf_fire_equipment = String.valueOf(row[11]);
                    }
                    if (row[12] == null) {
                        pf_lift_available = "";
                    } else {
                        pf_lift_available = String.valueOf(row[12]);
                    }
                    if (row[13] == null) {
                        pf_rain_water_harvest = "";
                    } else {
                        pf_rain_water_harvest = String.valueOf(row[13]);
                    }
                    if (row[14] == null) {
                        pf_num_of_borewells = "";
                    } else {
                        pf_num_of_borewells = String.valueOf(row[14]);
                    }
                    if (row[15] == null) {
                        pf_sanitation = "";
                    } else {
                        pf_sanitation = String.valueOf(row[15]);
                    }
                    if (row[16] == null) {
                        pf_hording_avail = "";
                    } else {
                        pf_hording_avail = String.valueOf(row[16]);
                    }
                    if (row[17] == null) {
                        pf_mobile_tower = "";
                    } else {
                        pf_mobile_tower = String.valueOf(row[17]);
                    }

                }
            //be

                Query query = sessionHB.createSQLQuery(sb.toString());
                query.setParameter("uid", floorBean.getUniqueId());
                query.setParameter("floorname", floorBean.getFloorType());
                query.setParameter("cover", Float.parseFloat(floorBean.getCarpetArea()));
                query.setParameter("ctype", floorBean.getConstructionType());
                query.setParameter("buse", floorBean.getPropertyUse());
                query.setParameter("subtype", floorBean.getPropertySubType());
                query.setParameter("self", floorBean.getSelfRent());
                query.setParameter("rentvalue", floorBean.getRentedValue());
                query.setParameter("propclass", propClass);
                query.setParameter("commactivity", pf_commercial_activity_name);
                query.setParameter("comtype", pf_commercial_type);
                query.setParameter("waterpipecon", pf_water_pipe_con);
                query.setParameter("waterpipeconno", pf_water_con_num);
                query.setParameter("seweragecon", pf_sewerage_con);
                query.setParameter("sewerageconnum", pf_sewerage_con_num);
                query.setParameter("electric_meter_num", pf_electric_meter_num);
                query.setParameter("electric_con", pf_electric_con_num);
                query.setParameter("cctv_camrea", pf_cctv_camrea);
                query.setParameter("fireequip", pf_fire_equipment);
                query.setParameter("lift", pf_lift_available);
                query.setParameter("rain_water", pf_rain_water_harvest);
                query.setParameter("borewells", pf_num_of_borewells);
                query.setParameter("sanitation", pf_sanitation);
                query.setParameter("hording", pf_hording_avail);
                query.setParameter("mobile_tower", pf_mobile_tower);
                //System.out.println("dddd "+sb.toString());
                //System.out.println("query "+query);

                //query.setParameter("floorid", Integer.parseInt(floorBean.getPropertyFloorId()));
                int result = query.executeUpdate();

                Query query2 = sessionHB.createSQLQuery("UPDATE  property_floor set property_rentable_id = rt.property_rentable_id FROM property_rentable rt where UPPER(pf_floorwise_build_use) = UPPER(property_cat) and prop_class=property_subcat_code and property_unique_id=:uid ");
                query2.setParameter("uid", floorBean.getUniqueId());
                //query2.setParameter("floorid", Integer.parseInt(floorBean.getPropertyFloorId()));
                int result2 = query2.executeUpdate();

            }

            StringBuffer sb2 = new StringBuffer();
            sb2.append(" update property_details pd set no_tax_type = 'Y' from ( ");
            sb2.append(" select * from ( ");
            sb2.append(" select property_unique_id, sum(pf_builtup_area) as pf_builtup_area from property_floor where property_unique_id not in( ");
            sb2.append(" select property_unique_id from property_floor where pf_floorwise_build_use <> 'Residential' ");
            sb2.append(") group by property_unique_id) flt_prop where pf_builtup_area <=269 and property_unique_id=:uid ");
            sb2.append(" ) no_tax_prop where pd.property_unique_id = no_tax_prop.property_unique_id");

            Query query3 = sessionHB.createSQLQuery(sb2.toString());
            query3.setParameter("uid", floorBean.getUniqueId());
            int result3 = query3.executeUpdate();

            Query query2 = sessionHB.createSQLQuery("update property_correction_form_floor set permission_data=:data1 where uniqueid=:uid and property_floor_id=:floorid and application_no=:app");
            query2.setParameter("data1", "Y");
            query2.setParameter("uid", floorBean.getUniqueId());
            query2.setParameter("floorid", floorBean.getPropertyFloorId());
            query2.setParameter("app", floorBean.getApplication_no());
            int result2 = query2.executeUpdate();

            Query query4 = sessionHB.createSQLQuery("update property_correction_form_floor set permission_data=:data1 where uniqueid=:uid and property_floor_id=:floorid and application_no=:app");
            query4.setParameter("data1", "Y");
            query4.setParameter("uid", floorBean.getUniqueId());
            query4.setParameter("floorid", floorBean.getPropertyFloorId());
            query4.setParameter("app", floorBean.getApplication_no());
            int result4 = query4.executeUpdate();

//            CorrectionFormSaveBean bean1=(CorrectionFormSaveBean)sessionHB.get(bean.getUniqueId());
//            System.out.println("id ghjkk "+bean1.getUniqueId());
//            System.out.println("owner "+bean1.getOwnerName());
//            System.out.println("owner father "+bean1.getOwnerFather());
//            System.out.println("owner contact "+bean1.getOwnerContact());
//            System.out.println("owner email "+bean1.getOwnerEmail());
//            System.out.println("occupier name "+bean1.getOccupierName());
//            System.out.println("occupier father "+bean1.getOccupierFather());
//            System.out.println("occupier contact "+bean1.getOccupierContact());
//            System.out.println("occupier email "+bean1.getOccupierEmail());
//            System.out.println("address "+bean1.getAddress());
//            sessionHB.update(bean1);
//            
            //System.out.println("sessionhhhh");
            msg = "SuccessFully updated";

        } catch (Exception e) {
            msg = "error in correction form";
            e.printStackTrace();
            logger.info(e.getMessage());
        } finally {
            if (sessionHB != null) {
                sessionHB.flush();
                sessionHB.clear();
                sessionHB.close();
            }
        }
        return msg;
    }

    public String saveCorrectionFormData(CorrectionFormSaveBean bean) {
        Session sessionHB = null;
        String msg = "";
        String finYear = "2017-2018";

        try {
            //System.out.println("session "+bean.getUniqueId());
            sessionHB = sessionFactory.openSession();
//            System.out.println("id "+bean.getUniqueId());
//            System.out.println("owner "+bean.getOwnerName());
//            System.out.println("owner father "+bean.getOwnerFather());
//            System.out.println("owner contact "+bean.getOwnerContact());
//            System.out.println("owner email "+bean.getOwnerEmail());
//            System.out.println("occupier name "+bean.getOccupierName());
//            System.out.println("occupier father "+bean.getOccupierFather());
//            System.out.println("occupier contact "+bean.getOccupierContact());
//            System.out.println("occupier email "+bean.getOccupierEmail());
//            System.out.println("address "+bean.getAddress());
            //System.out.println("road "+bean.getRoadName());
            //System.out.println("land "+bean.getLandMark());
            if (bean.getOwnerName() != null && bean.getOwnerName().length() > 0) {
                Query query = sessionHB.createSQLQuery("update property_details set property_owner=:owner where property_unique_id=:uid");
                query.setParameter("owner", bean.getOwnerName());
                query.setParameter("uid", bean.getUniqueId());
                int result = query.executeUpdate();
            }
            if (bean.getOwnerFather() != null && bean.getOwnerFather().length() > 0) {
                Query query = sessionHB.createSQLQuery("update property_details set property_owner_father=:father where property_unique_id=:uid");
                query.setParameter("father", bean.getOwnerFather());
                query.setParameter("uid", bean.getUniqueId());
                int result = query.executeUpdate();
            }
            if (bean.getSpouseName() != null && bean.getSpouseName().length() > 0) {
                Query query = sessionHB.createSQLQuery("update property_details set property_owner_spouse=:spouse where property_unique_id=:uid");
                query.setParameter("spouse", bean.getSpouseName());
                query.setParameter("uid", bean.getUniqueId());
                int result = query.executeUpdate();
            }
            if (bean.getOwnerContact() != null && bean.getOwnerContact().length() > 0) {
                Query query = sessionHB.createSQLQuery("update property_details set property_contact=:contact where property_unique_id=:uid");
                query.setParameter("contact", bean.getOwnerContact());
                query.setParameter("uid", bean.getUniqueId());
                int result = query.executeUpdate();
            }
            if (bean.getOwnerEmail() != null && bean.getOwnerEmail().length() > 0) {
                Query query = sessionHB.createSQLQuery("update property_details set property_owner_email=:email where property_unique_id=:uid");
                query.setParameter("email", bean.getOwnerEmail());
                query.setParameter("uid", bean.getUniqueId());
                int result = query.executeUpdate();
            }
            if (bean.getOccupierName() != null && bean.getOccupierName().length() > 0) {
                Query query = sessionHB.createSQLQuery("update property_details set property_occupier_name=:occupier where property_unique_id=:uid");
                query.setParameter("occupier", bean.getOccupierName());
                query.setParameter("uid", bean.getUniqueId());
                int result = query.executeUpdate();
            }
            if (bean.getOccupierFather() != null && bean.getOccupierFather().length() > 0) {
                Query query = sessionHB.createSQLQuery("update property_details set occupier_father=:occufather where property_unique_id=:uid");
                query.setParameter("occufather", bean.getOccupierFather());
                query.setParameter("uid", bean.getUniqueId());
                int result = query.executeUpdate();
            }
            if (bean.getOccupierContact() != null && bean.getOccupierContact().length() > 0) {
                Query query = sessionHB.createSQLQuery("update property_details set occupier_contactno=:occucontact where property_unique_id=:uid");
                query.setParameter("occucontact", bean.getOccupierContact());
                query.setParameter("uid", bean.getUniqueId());
                int result = query.executeUpdate();
            }

            if (bean.getOccupierEmail() != null && bean.getOccupierEmail().length() > 0) {
                Query query = sessionHB.createSQLQuery("update property_details set occupier_email=:occuemail where property_unique_id=:uid");
                query.setParameter("occuemail", bean.getOccupierEmail());
                query.setParameter("uid", bean.getUniqueId());
                int result = query.executeUpdate();
            }
            if (bean.getPlotNo() != null && bean.getPlotNo().length() > 0) {
                Query query = sessionHB.createSQLQuery("update property_details set property_plot_num=:plot where property_unique_id=:uid");
                query.setParameter("plot", bean.getPlotNo());
                query.setParameter("uid", bean.getUniqueId());
                int result = query.executeUpdate();
            }

            if (bean.getHoueNo() != null && bean.getHoueNo().length() > 0) {
                Query query = sessionHB.createSQLQuery("update property_details set property_house_no=:house where property_unique_id=:uid");
                query.setParameter("house", bean.getHoueNo());
                query.setParameter("uid", bean.getUniqueId());
                int result = query.executeUpdate();
            }
            if (bean.getBuildingName() != null && bean.getBuildingName().length() > 0) {
                Query query = sessionHB.createSQLQuery("update property_details set property_building_name=:buioldname where property_unique_id=:uid");
                query.setParameter("buioldname", bean.getBuildingName());
                query.setParameter("uid", bean.getUniqueId());
                int result = query.executeUpdate();
            }
            if (bean.getRoadName() != null && bean.getRoadName().length() > 0) {
                Query query = sessionHB.createSQLQuery("update property_details set property_road=:road where property_unique_id=:uid");
                query.setParameter("road", bean.getRoadName());
                query.setParameter("uid", bean.getUniqueId());
                int result = query.executeUpdate();
            }
            if (bean.getSubLocality() != null && bean.getSubLocality().length() > 0) {
                Query query = sessionHB.createSQLQuery("update property_details set property_sublocality=:subloc where property_unique_id=:uid");
                query.setParameter("subloc", bean.getSubLocality());
                query.setParameter("uid", bean.getUniqueId());
                int result = query.executeUpdate();
            }
            if (bean.getLocName() != null && bean.getLocName().length() > 0) {
                Query query = sessionHB.createSQLQuery("update property_details set property_locality=:loc where property_unique_id=:uid");
                query.setParameter("loc", bean.getLocName());
                query.setParameter("uid", bean.getUniqueId());
                int result = query.executeUpdate();
            }
            if (bean.getLandMark() != null && bean.getLandMark().length() > 0) {
                Query query = sessionHB.createSQLQuery("update property_details set property_landmark=:landmark where property_unique_id=:uid");
                query.setParameter("landmark", bean.getLandMark());
                query.setParameter("uid", bean.getUniqueId());
                int result = query.executeUpdate();
            }

            if (bean.getOwnerAadharNo() != null && bean.getOwnerAadharNo().length() > 0) {
                Query query1 = sessionHB.createSQLQuery("update property_details set property_aadhar_num=:aadhar where property_unique_id=:uid");
                query1.setParameter("aadhar", bean.getOwnerAadharNo());
                query1.setParameter("uid", bean.getUniqueId());
                int result1 = query1.executeUpdate();
            }

            if (bean.getSurveyNo() != null && bean.getSurveyNo().length() > 0) {
                Query query1 = sessionHB.createSQLQuery("update property_details set smc_survey_no=:survey where property_unique_id=:uid");
                query1.setParameter("survey", bean.getSurveyNo());
                query1.setParameter("uid", bean.getUniqueId());
                int result1 = query1.executeUpdate();
            }

            if (bean.getPlotSmc() != null && bean.getPlotSmc().length() > 0) {
                Query query1 = sessionHB.createSQLQuery("update property_details set smc_plot_no=:plotsmc where property_unique_id=:uid");
                query1.setParameter("plotsmc", bean.getPlotSmc());
                query1.setParameter("uid", bean.getUniqueId());
                int result1 = query1.executeUpdate();
            }
            if (bean.getOccupierAadharNo() != null && bean.getOccupierAadharNo().length() > 0) {
                Query query1 = sessionHB.createSQLQuery("update property_details set occupier_aadhar_no=:aadhar where property_unique_id=:uid");
                query1.setParameter("aadhar", bean.getOccupierAadharNo());
                query1.setParameter("uid", bean.getUniqueId());
                int result1 = query1.executeUpdate();
            }

            if (bean.getElectricServiceNo() != null && bean.getElectricServiceNo().length() > 0) {
                Query query1 = sessionHB.createSQLQuery("update property_floor set pf_electric_con_num=:electric where property_unique_id=:uid");
                query1.setParameter("electric", bean.getElectricServiceNo());
                query1.setParameter("uid", bean.getUniqueId());
                int result1 = query1.executeUpdate();
            }
            if (bean.getArrearAmount() != null && bean.getArrearAmount().length() > 0) {
                //System.out.println("arrear "+bean.getArrearAmount());
                Query query1 = sessionHB.createSQLQuery("update property_tax set  arrear_amount=:arrear,payable_amount=:arrear1 where property_unique_id=:uid and financial_year=:finYear");
                query1.setParameter("arrear", bean.getArrearAmount());
                query1.setParameter("arrear1", bean.getArrearAmount());
                query1.setParameter("uid", bean.getUniqueId());
                query1.setParameter("finYear", finYear);
                int result1 = query1.executeUpdate();
            }

            if (bean.getSmcHoldingNo() != null && bean.getSmcHoldingNo().length() > 0) {
                Query query1 = sessionHB.createSQLQuery("update property_details set property_old_smc_prop_tax_num=:holding where property_unique_id=:uid ");
                query1.setParameter("holding", bean.getSmcHoldingNo());
                query1.setParameter("uid", bean.getUniqueId());
                int result1 = query1.executeUpdate();
            }
            //System.out.println("ddd "+bean.getOwnerSex()+" "+bean.getOwnerSex().length());
            if (bean.getOwnerSex() != null && bean.getOwnerSex().length() > 0) {
                //System.out.println("if "+bean.getOwnerSex()+" "+bean.getOwnerSex().length());
                Query query1 = sessionHB.createSQLQuery("update property_details set owner_sex=:ownersex where property_unique_id=:uid ");
                query1.setParameter("ownersex", bean.getOwnerSex());
                query1.setParameter("uid", bean.getUniqueId());
                int result1 = query1.executeUpdate();
            }
            if (bean.getOccupierSex() != null && bean.getOccupierSex().length() > 0) {
                Query query1 = sessionHB.createSQLQuery("update property_details set occupier_sex=:occusex where property_unique_id=:uid ");
                query1.setParameter("occusex", bean.getOccupierSex());
                query1.setParameter("uid", bean.getUniqueId());
                int result1 = query1.executeUpdate();
            }

            if (bean.getPropertyOwnerAddress() != null && bean.getPropertyOwnerAddress().length() > 0) {
                Query query1 = sessionHB.createSQLQuery("update property_details set property_owner_address=:owneraddr where property_unique_id=:uid ");
                query1.setParameter("owneraddr", bean.getPropertyOwnerAddress());
                query1.setParameter("uid", bean.getUniqueId());
                int result1 = query1.executeUpdate();
            }

            //System.out.println("session object "+sessionHB);
            /*Query query=sessionHB.createSQLQuery("update property_details_14012018 set property_owner=:owner,property_owner_father=:father,property_contact=:contact,property_owner_email=:email,property_occupier_name=:occupier,occupier_father=:occufather,occupier_contactno=:occucontact,occupier_email=:occuemail,complete_address=:address where property_unique_id=:uid");
             query.setParameter("owner", bean.getOwnerName());
             query.setParameter("father", bean.getOwnerFather());
             query.setParameter("contact", bean.getOwnerContact());
             query.setParameter("email", bean.getOwnerEmail());
             query.setParameter("occupier", bean.getOccupierName());
             query.setParameter("occufather", bean.getOccupierFather());
             query.setParameter("occucontact", bean.getOccupierContact());
             query.setParameter("occuemail", bean.getOccupierEmail());
             query.setParameter("address", bean.getAddress());
             query.setParameter("address", bean.getAddress());
             query.setParameter("uid", bean.getUniqueId());
             int result = query.executeUpdate();*/
            Query query2 = sessionHB.createSQLQuery("update property_correction_form set permission_data=:data1 where unique_id=:uid");
            query2.setParameter("data1", "Y");
            query2.setParameter("uid", bean.getUniqueId());
            int result2 = query2.executeUpdate();

//            CorrectionFormSaveBean bean1=(CorrectionFormSaveBean)sessionHB.get(bean.getUniqueId());
//            System.out.println("id ghjkk "+bean1.getUniqueId());
//            System.out.println("owner "+bean1.getOwnerName());
//            System.out.println("owner father "+bean1.getOwnerFather());
//            System.out.println("owner contact "+bean1.getOwnerContact());
//            System.out.println("owner email "+bean1.getOwnerEmail());
//            System.out.println("occupier name "+bean1.getOccupierName());
//            System.out.println("occupier father "+bean1.getOccupierFather());
//            System.out.println("occupier contact "+bean1.getOccupierContact());
//            System.out.println("occupier email "+bean1.getOccupierEmail());
//            System.out.println("address "+bean1.getAddress());
//            sessionHB.update(bean1);
//            
            //System.out.println("sessionhhhh");
            msg = "SuccessFully updated";

        } catch (Exception e) {
            msg = "error in correction form";
            e.printStackTrace();
            logger.info(e.getMessage());
        } finally {
            if (sessionHB != null) {
                sessionHB.flush();
                sessionHB.clear();
                sessionHB.close();
            }
        }
        return msg;
    }

    public String saveCorrectionFormFloorData(CorrectionFormFloorBean floorBean) {
        Session sessionHB = null;
        String msg = "";
        try {
            //System.out.println("session floor data "+floorBean.getUniqueId());
            sessionHB = sessionFactory.openSession();

            if (floorBean.getFloorType() != null && floorBean.getFloorType().length() > 0) {
                Query query = sessionHB.createSQLQuery("update  property_floor set pf_floor_name=:floorname where property_unique_id=:uid and pf_id=:floorid");
                query.setParameter("floorname", floorBean.getFloorType());
                query.setParameter("uid", floorBean.getUniqueId());
                query.setParameter("floorid", Integer.parseInt(floorBean.getPropertyFloorId()));
                int result = query.executeUpdate();

            }
            if (floorBean.getCarpetArea() != null && floorBean.getCarpetArea().length() > 0) {
                Query query = sessionHB.createSQLQuery("update  property_floor set pf_builtup_area=:cover where property_unique_id=:uid and pf_id=:floorid");
                query.setParameter("cover", Float.parseFloat(floorBean.getCarpetArea()));
                query.setParameter("uid", floorBean.getUniqueId());
                query.setParameter("floorid", Integer.parseInt(floorBean.getPropertyFloorId()));
                int result = query.executeUpdate();
            }
            if (floorBean.getPropertyUse() != null && floorBean.getPropertyUse().length() > 0) {
                Query query = sessionHB.createSQLQuery("update  property_floor set pf_floorwise_build_use=:puse where property_unique_id=:uid and pf_id=:floorid");
                query.setParameter("puse", floorBean.getPropertyUse());
                query.setParameter("uid", floorBean.getUniqueId());
                query.setParameter("floorid", Integer.parseInt(floorBean.getPropertyFloorId()));
                int result = query.executeUpdate();
                Query query1 = sessionHB.createSQLQuery("UPDATE   property_floor set property_rentable_id = rt.property_rentable_id FROM property_rentable rt where UPPER(pf_floorwise_build_use) = UPPER(property_cat) and prop_class=property_subcat_code and property_unique_id=:uid and pf_id=:floorid");
                query1.setParameter("uid", floorBean.getUniqueId());
                query1.setParameter("floorid", Integer.parseInt(floorBean.getPropertyFloorId()));
                int result1 = query1.executeUpdate();

            }
            if (floorBean.getPropertySubType() != null && floorBean.getPropertySubType().length() > 0) {
                Query query = sessionHB.createSQLQuery("update  property_floor set pf_property_subtype=:subtype where property_unique_id=:uid and pf_id=:floorid");
                query.setParameter("subtype", floorBean.getPropertySubType());
                query.setParameter("uid", floorBean.getUniqueId());
                query.setParameter("floorid", Integer.parseInt(floorBean.getPropertyFloorId()));
                int result = query.executeUpdate();
            }
            if (floorBean.getConstructionType() != null && floorBean.getConstructionType().length() > 0) {
                Query query = sessionHB.createSQLQuery("update  property_floor set pf_construction_type=:constype where property_unique_id=:uid and pf_id=:floorid");
                query.setParameter("constype", floorBean.getConstructionType());
                query.setParameter("uid", floorBean.getUniqueId());
                query.setParameter("floorid", Integer.parseInt(floorBean.getPropertyFloorId()));
                int result = query.executeUpdate();
            }
            if (floorBean.getSelfRent() != null && floorBean.getSelfRent().length() > 0) {
                Query query = sessionHB.createSQLQuery("update  property_floor set pf_self_rent=:self where property_unique_id=:uid and pf_id=:floorid");
                query.setParameter("self", floorBean.getSelfRent());
                query.setParameter("uid", floorBean.getUniqueId());
                query.setParameter("floorid", Integer.parseInt(floorBean.getPropertyFloorId()));
                int result = query.executeUpdate();
            }
            if (floorBean.getRentedValue() != null && floorBean.getRentedValue().length() > 0) {
                Query query = sessionHB.createSQLQuery("update  property_floor set pf_annual_rent_value=:rent where property_unique_id=:uid and pf_id=:floorid");
                query.setParameter("rent", floorBean.getRentedValue());
                query.setParameter("uid", floorBean.getUniqueId());
                query.setParameter("floorid", Integer.parseInt(floorBean.getPropertyFloorId()));
                int result = query.executeUpdate();
            }

            Query query5 = sessionHB.createSQLQuery("UPDATE  property_floor set property_rentable_id = rt.property_rentable_id FROM property_rentable rt where UPPER(pf_floorwise_build_use) = UPPER(property_cat) and prop_class=property_subcat_code and property_unique_id=:uid ");
            query5.setParameter("uid", floorBean.getUniqueId());
            //query2.setParameter("floorid", Integer.parseInt(floorBean.getPropertyFloorId()));
            int result5 = query5.executeUpdate();

            StringBuffer sb2 = new StringBuffer();
            sb2.append(" update property_details pd set no_tax_type = 'Y' from ( ");
            sb2.append(" select * from ( ");
            sb2.append(" select property_unique_id, sum(pf_builtup_area) as pf_builtup_area from property_floor where property_unique_id not in( ");
            sb2.append(" select property_unique_id from property_floor where pf_floorwise_build_use <> 'Residential' ");
            sb2.append(") group by property_unique_id) flt_prop where pf_builtup_area <=269 and property_unique_id=:uid ");
            sb2.append(" ) no_tax_prop where pd.property_unique_id = no_tax_prop.property_unique_id");

            Query query3 = sessionHB.createSQLQuery(sb2.toString());
            query3.setParameter("uid", floorBean.getUniqueId());
            int result3 = query3.executeUpdate();

            Query query2 = sessionHB.createSQLQuery("update property_correction_form_floor set permission_data=:data1 where uniqueid=:uid and property_floor_id=:floorid and application_no=:app");
            query2.setParameter("data1", "Y");
            query2.setParameter("uid", floorBean.getUniqueId());
            query2.setParameter("floorid", floorBean.getPropertyFloorId());
            query2.setParameter("app", floorBean.getApplication_no());
            int result2 = query2.executeUpdate();

            Query query4 = sessionHB.createSQLQuery("update property_correction_form_floor set permission_data=:data1 where uniqueid=:uid and property_floor_id=:floorid and application_no=:app");
            query4.setParameter("data1", "Y");
            query4.setParameter("uid", floorBean.getUniqueId());
            query4.setParameter("floorid", floorBean.getPropertyFloorId());
            query4.setParameter("app", floorBean.getApplication_no());
            int result4 = query4.executeUpdate();

//            CorrectionFormSaveBean bean1=(CorrectionFormSaveBean)sessionHB.get(bean.getUniqueId());
//            System.out.println("id ghjkk "+bean1.getUniqueId());
//            System.out.println("owner "+bean1.getOwnerName());
//            System.out.println("owner father "+bean1.getOwnerFather());
//            System.out.println("owner contact "+bean1.getOwnerContact());
//            System.out.println("owner email "+bean1.getOwnerEmail());
//            System.out.println("occupier name "+bean1.getOccupierName());
//            System.out.println("occupier father "+bean1.getOccupierFather());
//            System.out.println("occupier contact "+bean1.getOccupierContact());
//            System.out.println("occupier email "+bean1.getOccupierEmail());
//            System.out.println("address "+bean1.getAddress());
//            sessionHB.update(bean1);
//            
            //System.out.println("sessionhhhh");
            msg = "SuccessFully updated";

        } catch (Exception e) {
            msg = "error in correction form";
            e.printStackTrace();
            logger.info(e.getMessage());
        } finally {
            if (sessionHB != null) {
                sessionHB.flush();
                sessionHB.clear();
                sessionHB.close();
            }
        }
        return msg;

        //return ""; 
    }

    public MobileBean getMbileNo(String propId) {
        Session sessionHB = null;
        String msg = "";
        List<CorrectionFormFloorBean> floormultiple_ar = new ArrayList<CorrectionFormFloorBean>();
        MobileBean ptdBean = null;
        List<CorrectionFormFloorBean> ar = new ArrayList<CorrectionFormFloorBean>();
        StringBuffer sb = new StringBuffer();

        try {
            //System.out.println("session "+bean.getUniqueId());
            sb.append("select property_unique_id,property_contact,property_owner_email from property_details where property_unique_id= '" + propId + "'");
            sessionHB = sessionFactory.openSession();
            //System.out.println("query floor "+sb.toString());
            Query query1 = sessionHB.createSQLQuery(sb.toString());
            List<Object[]> rows = query1.list();
            for (Object[] row : rows) {
                //System.out.println("row[15] mark"+row[15]);
                //System.out.println("row[11] road"+row[11]);

                ptdBean = new MobileBean();
//               

                ptdBean.setUniqueId(row[0] == null ? "" : String.valueOf(row[0]));
                ptdBean.setMobileNo(row[1] == null ? "" : String.valueOf(row[1]));

                ptdBean.setEmailId(row[2] == null ? "" : String.valueOf(row[2]));

                //ptdBean.setDocumentType(row[12] == null ? "" : String.valueOf(row[12]));
                //ptdBean.setId(row[13] == null ? -1 : Integer.valueOf(String.valueOf(row[13])));
                //ar.add(ptdBean);
            }
            //bean= (CorrectionFormBean)sessionHB.get(CorrectionFormBean.class, id);
            //System.out.println("session object "+sessionHB);

        } catch (Exception e) {
            e.printStackTrace();
            logger.info(e.getMessage());
        } finally {
            if (sessionHB != null) {
                sessionHB.flush();
                sessionHB.clear();
                sessionHB.close();
            }
        }
        return ptdBean;
    }

    @Override
    public List<CorrectionFormLoadData> getPropertyIdOffline(String id) {
        Session sessionHB = null;
        String msg = "";
        CorrectionFormLoadData ptdBean = null;
        StringBuffer sb = new StringBuffer();
        ArrayList<CorrectionFormLoadData> ar_bean = new ArrayList<CorrectionFormLoadData>();

        try {
            //System.out.println("session "+bean.getUniqueId());
            sb.append(" select d.property_unique_id,d.ward,d.property_owner,d.property_owner_father,d.property_contact ,");
            sb.append(" d.property_owner_email,d.property_occupier_name,d.complete_address,d.occupier_father, ");
            sb.append(" d.occupier_contactno,d.occupier_email,f.prop_class,f.pf_electric_con_num,d.property_owner_spouse,d.property_house_no,d.property_building_name,d.property_sublocality,d.property_landmark,d.property_city,d.property_pincode,d.property_plot_num,d.property_locality,d.property_road ");
            sb.append(",f.pf_id,pf_floor_name,f.pf_builtup_area,f.pf_floorwise_build_use,f.pf_property_subtype,f.pf_construction_type,f.pf_self_rent,f.pf_annual_rent_value,d.property_aadhar_num,d.occupier_aadhar_no,d.smc_survey_no,d.smc_plot_no,t.arrear_amount, d.owner_sex,d.occupier_sex,d.property_owner_address, d.property_old_smc_prop_tax_num,r.rentable_value,d.private_notice_no ");
            sb.append(" from property_details d, property_tax t,property_floor f,property_rentable r where d.property_unique_id=f.property_unique_id and d.property_unique_id= t.property_unique_id and f.property_rentable_id=r.property_rentable_id and d.property_unique_id='" + id + "' and t.financial_year='2017-2018' order by f.flooriwse_short");
            sessionHB = sessionFactory.openSession();
            //System.out.println("ddd "+sb.toString());
            Query query1 = sessionHB.createSQLQuery(sb.toString());
            List<Object[]> rows = query1.list();
            for (Object[] row : rows) {
                //System.out.println("rows[39] "+row[39]);

                ptdBean = new CorrectionFormLoadData();
                ptdBean.setUniqueId(row[0] == null ? "" : String.valueOf(row[0]));
                ptdBean.setWardNo(row[1] == null ? "" : String.valueOf(row[1]));
                ptdBean.setOwnerName(row[2] == null ? "" : String.valueOf(row[2]));
                if (row[3] == null) {
                    ptdBean.setOwnerFather("");
                    // System.out.println(" row[6].toString() if"+row[6].toString());
                } else {
                    ptdBean.setOwnerFather(row[3].toString());
                    //System.out.println(" row[6].toString() else "+row[6].toString());
                }
                ptdBean.setOwnerContact(row[4] == null ? "" : String.valueOf(row[4]));
                ptdBean.setOwnerEmail(row[5] == null ? "" : String.valueOf(row[5]));
                ptdBean.setOccupierName(row[6] == null ? "" : String.valueOf(row[6]));
                ptdBean.setAddress(row[7] == null ? "" : String.valueOf(row[7]));
                ptdBean.setOccupierFather(row[8] == null ? "" : String.valueOf(row[8]));
                ptdBean.setOccupierContact(row[9] == null ? "" : String.valueOf(row[9]));
                ptdBean.setOccupierEmail(row[10] == null ? "" : String.valueOf(row[10]));
                ptdBean.setOccupierEmail(row[10] == null ? "" : String.valueOf(row[10]));
                ptdBean.setLocationClass(row[11] == null ? "" : String.valueOf(row[11]));
                ptdBean.setElectricServiceConNo(row[12] == null ? "" : String.valueOf(row[12]));

                ptdBean.setSpouseName(row[13] == null ? "" : String.valueOf(row[13]));
                ptdBean.setHoueNo(row[14] == null ? "" : String.valueOf(row[14]));
                ptdBean.setBuildingName(row[15] == null ? "" : String.valueOf(row[15]));
                ptdBean.setSubLocality(row[16] == null ? "" : String.valueOf(row[16]));
                ptdBean.setLandMark(row[17] == null ? "" : String.valueOf(row[17]));
                ptdBean.setCity(row[18] == null ? "" : String.valueOf(row[18]));
                ptdBean.setPincode(row[19] == null ? "" : String.valueOf(row[19]));
                ptdBean.setPlotNo(row[20] == null ? "" : String.valueOf(row[20]));
                ptdBean.setLocName(row[21] == null ? "" : String.valueOf(row[21]));
                ptdBean.setRoadName(row[22] == null ? "" : String.valueOf(row[22]));

                ptdBean.setPropertyFloorId(row[23] == null ? "" : String.valueOf(row[23]));
                ptdBean.setFloorType(row[24] == null ? "" : String.valueOf(row[24]));
                ptdBean.setCarpetArea(row[25] == null ? "" : String.valueOf(row[25]));
                ptdBean.setPropertyUse(row[26] == null ? "" : String.valueOf(row[26]));
                ptdBean.setPropertySubType(row[27] == null ? "" : String.valueOf(row[27]));
                ptdBean.setConstructionType(row[28] == null ? "" : String.valueOf(row[28]));
                ptdBean.setSelfRent(row[29] == null ? "" : String.valueOf(row[29]));
                ptdBean.setRentedValue(row[30] == null ? "" : String.valueOf(row[30]));

                ptdBean.setOwnerAadharNo(row[31] == null ? "" : String.valueOf(row[31]));
                ptdBean.setOccupierAadharNo(row[32] == null ? "" : String.valueOf(row[32]));
                ptdBean.setSurveyNo(row[33] == null ? "" : String.valueOf(row[33]));
                ptdBean.setPlotSmc(row[34] == null ? "" : String.valueOf(row[34]));
                ptdBean.setArrearAmount(row[35] == null ? "0" : String.valueOf(row[35]));
                ptdBean.setOwnerSex(row[36] == null ? "" : String.valueOf(row[36]));
                ptdBean.setOccupierSex(row[37] == null ? "" : String.valueOf(row[37]));
                ptdBean.setPropertyOwnerAddress(row[38] == null ? "" : String.valueOf(row[38]));
                ptdBean.setSmcProperty(row[39] == null ? "" : String.valueOf(row[39]));
                ptdBean.setPresumeRent(row[40] == null ? "" : String.valueOf(row[40]));
                ptdBean.setPrivateNoticeNo(row[41] == null ? "0" : String.valueOf(row[41]));

                //ptdBean.setFloorList(String.valueOf(row[24]));
                ar_bean.add(ptdBean);

            }
            //bean= (CorrectionFormBean)sessionHB.get(CorrectionFormBean.class, id);
            //System.out.println("session object "+sessionHB);

        } catch (Exception e) {
            e.printStackTrace();
            logger.info(e.getMessage());
        } finally {
            if (sessionHB != null) {
                sessionHB.flush();
                sessionHB.clear();
                sessionHB.close();
            }
        }
        return ar_bean;
    }

    public List<CorrectionFormLoadData> getPropertyIdSplitProperty(String id, String newPid) {
        Session sessionHB = null;
        String msg = "";
        CorrectionFormLoadData ptdBean = null;
        StringBuffer sb = new StringBuffer();
        ArrayList<CorrectionFormLoadData> ar_bean = new ArrayList<CorrectionFormLoadData>();

        try {
            //System.out.println("session "+bean.getUniqueId());
            sb.append(" select d.property_unique_id,d.ward,d.property_owner,d.property_owner_father,d.property_contact ,");
            sb.append(" d.property_owner_email,d.property_occupier_name,d.complete_address,d.occupier_father, ");
            sb.append(" d.occupier_contactno,d.occupier_email,f.prop_class,f.pf_electric_con_num,d.property_owner_spouse,d.property_house_no,d.property_building_name,d.property_sublocality,d.property_landmark,d.property_city,d.property_pincode,d.property_plot_num,d.property_locality,d.property_road ");
            sb.append(",f.pf_id,pf_floor_name,f.pf_builtup_area,f.pf_floorwise_build_use,f.pf_property_subtype,f.pf_construction_type,f.pf_self_rent,f.pf_annual_rent_value,d.property_aadhar_num,d.occupier_aadhar_no,d.smc_survey_no,d.smc_plot_no,t.arrear_amount, d.owner_sex,d.occupier_sex,d.property_owner_address, d.property_old_smc_prop_tax_num,r.rentable_value,d.private_notice_no ");
            sb.append(" from property_details d, property_tax t,property_floor f,property_rentable r where d.property_unique_id=f.property_unique_id and d.property_unique_id= t.property_unique_id and f.property_rentable_id=r.property_rentable_id and d.property_unique_id='" + id + "' and t.financial_year='2017-2018' order by f.flooriwse_short");
            sessionHB = sessionFactory.openSession();
            //System.out.println("ddd "+sb.toString());
            Query query1 = sessionHB.createSQLQuery(sb.toString());
            List<Object[]> rows = query1.list();
            for (Object[] row : rows) {
                //System.out.println("rows[39] "+row[39]);

                ptdBean = new CorrectionFormLoadData();
                ptdBean.setUniqueId(newPid);
                //ptdBean.setUniqueId(row[0] == null ? "" : String.valueOf(row[0]));
                ptdBean.setWardNo(row[1] == null ? "" : String.valueOf(row[1]));
                ptdBean.setOwnerName(row[2] == null ? "" : String.valueOf(row[2]));
                if (row[3] == null) {
                    ptdBean.setOwnerFather("");
                    // System.out.println(" row[6].toString() if"+row[6].toString());
                } else {
                    ptdBean.setOwnerFather(row[3].toString());
                    //System.out.println(" row[6].toString() else "+row[6].toString());
                }
                ptdBean.setOwnerContact(row[4] == null ? "" : String.valueOf(row[4]));
                ptdBean.setOwnerEmail(row[5] == null ? "" : String.valueOf(row[5]));
                ptdBean.setOccupierName(row[6] == null ? "" : String.valueOf(row[6]));
                ptdBean.setAddress(row[7] == null ? "" : String.valueOf(row[7]));
                ptdBean.setOccupierFather(row[8] == null ? "" : String.valueOf(row[8]));
                ptdBean.setOccupierContact(row[9] == null ? "" : String.valueOf(row[9]));
                ptdBean.setOccupierEmail(row[10] == null ? "" : String.valueOf(row[10]));
                ptdBean.setOccupierEmail(row[10] == null ? "" : String.valueOf(row[10]));
                ptdBean.setLocationClass(row[11] == null ? "" : String.valueOf(row[11]));
                ptdBean.setElectricServiceConNo(row[12] == null ? "" : String.valueOf(row[12]));

                ptdBean.setSpouseName(row[13] == null ? "" : String.valueOf(row[13]));
                ptdBean.setHoueNo(row[14] == null ? "" : String.valueOf(row[14]));
                ptdBean.setBuildingName(row[15] == null ? "" : String.valueOf(row[15]));
                ptdBean.setSubLocality(row[16] == null ? "" : String.valueOf(row[16]));
                ptdBean.setLandMark(row[17] == null ? "" : String.valueOf(row[17]));
                ptdBean.setCity(row[18] == null ? "" : String.valueOf(row[18]));
                ptdBean.setPincode(row[19] == null ? "" : String.valueOf(row[19]));
                ptdBean.setPlotNo(row[20] == null ? "" : String.valueOf(row[20]));
                ptdBean.setLocName(row[21] == null ? "" : String.valueOf(row[21]));
                ptdBean.setRoadName(row[22] == null ? "" : String.valueOf(row[22]));

                ptdBean.setPropertyFloorId(row[23] == null ? "" : String.valueOf(row[23]));
                ptdBean.setFloorType(row[24] == null ? "" : String.valueOf(row[24]));
                ptdBean.setCarpetArea(row[25] == null ? "" : String.valueOf(row[25]));
                ptdBean.setPropertyUse(row[26] == null ? "" : String.valueOf(row[26]));
                ptdBean.setPropertySubType(row[27] == null ? "" : String.valueOf(row[27]));
                ptdBean.setConstructionType(row[28] == null ? "" : String.valueOf(row[28]));
                ptdBean.setSelfRent(row[29] == null ? "" : String.valueOf(row[29]));
                ptdBean.setRentedValue(row[30] == null ? "" : String.valueOf(row[30]));

                ptdBean.setOwnerAadharNo(row[31] == null ? "" : String.valueOf(row[31]));
                ptdBean.setOccupierAadharNo(row[32] == null ? "" : String.valueOf(row[32]));
                ptdBean.setSurveyNo(row[33] == null ? "" : String.valueOf(row[33]));
                ptdBean.setPlotSmc(row[34] == null ? "" : String.valueOf(row[34]));
                ptdBean.setArrearAmount(row[35] == null ? "0" : String.valueOf(row[35]));
                ptdBean.setOwnerSex(row[36] == null ? "" : String.valueOf(row[36]));
                ptdBean.setOccupierSex(row[37] == null ? "" : String.valueOf(row[37]));
                ptdBean.setPropertyOwnerAddress(row[38] == null ? "" : String.valueOf(row[38]));
                ptdBean.setSmcProperty(row[39] == null ? "" : String.valueOf(row[39]));
                ptdBean.setPresumeRent(row[40] == null ? "" : String.valueOf(row[40]));
                ptdBean.setPrivateNoticeNo(row[41] == null ? "0" : String.valueOf(row[41]));

                //ptdBean.setFloorList(String.valueOf(row[24]));
                ar_bean.add(ptdBean);

            }
            //bean= (CorrectionFormBean)sessionHB.get(CorrectionFormBean.class, id);
            //System.out.println("session object "+sessionHB);

        } catch (Exception e) {
            e.printStackTrace();
            logger.info(e.getMessage());
        } finally {
            if (sessionHB != null) {
                sessionHB.flush();
                sessionHB.clear();
                sessionHB.close();
            }
        }
        return ar_bean;
    }

    public void addCorrectionFormHitLogger(CorrectionFormHitLogger correctionHitLogger) {
        Session sessionHB = null;
        try {
            sessionHB = sessionFactory.openSession();
            System.out.println("correctionHitLogger " + correctionHitLogger.getPropId() + " " + correctionHitLogger.getId());
            sessionHB.save(correctionHitLogger);
        } catch (Exception ex) {
            ex.printStackTrace();
            logger.info("[CommonDaoImpl.correctin form HitLog] Exception : " + ex.getMessage());

        } finally {
            if (sessionHB != null) {
                sessionHB.flush();
                sessionHB.clear();
                sessionHB.close();
            }
        }

    }

    public String addCorrectionFormData(CorrectionFormBean bean) {
        Session sessionHB = null;
        String msg = "";

        try {
            //System.out.println("session "+bean.getUniqueId());
            sessionHB = sessionFactory.openSession();
            //System.out.println("session object "+sessionHB);
            sessionHB.save(bean);
            //System.out.println("sessionhhhh");

            msg = "Successfully submission";

        } catch (Exception e) {
            msg = "error in correction form";
            e.printStackTrace();
            logger.info(e.getMessage());
        } finally {
            if (sessionHB != null) {
                sessionHB.flush();
                sessionHB.clear();
                sessionHB.close();
            }
        }
        return msg;
    }

    public String addCorrectionFormDataFloor(CorrectionFormFloorBean bean) {
        Session sessionHB = null;
        String msg = "";

        try {
            //System.out.println("session "+bean.getUniqueId());
            sessionHB = sessionFactory.openSession();
            //System.out.println("session object "+sessionHB);
            sessionHB.save(bean);
            //System.out.println("sessionhhhh");

            msg = "Successfully inserted";

        } catch (Exception e) {
            msg = "error in correction form";
            e.printStackTrace();
            logger.info(e.getMessage());
        } finally {
            if (sessionHB != null) {
                sessionHB.flush();
                sessionHB.clear();
                sessionHB.close();
            }
        }
        return msg;
    }

    public String updateOfflineToOnlineRefNo(String propertyId, String applicationNo, String complainNo) {
        Session sessionHB = null;
        String msg = "";
        String pid = "";
        String imgName = "";
        byte[] img = null;
        String imgName1 = "";
        byte[] img1 = null;
        String imgName2 = "";
        byte[] img2 = null;
        String doc1 = "";
        String doc2 = "";

        try {
            //System.out.println("session "+bean.getUniqueId());
            StringBuffer sb = new StringBuffer();
            sessionHB = sessionFactory.openSession();
            sb.append(" update property_correction_form f set imagefilename=d.image_name, imagefileowner2=d.image_file1,imagefilenameowner2=d.image_flie2,");
            sb.append(" documenttype=d.document_type,documenttype1=d.document_type1,image=d.image,image_owner2=d.image1,image_owner3=d.image2,offline_to_online_refno=d.application_no ");
            sb.append(" from (select unique_id,image_name,image,image_file1,image1,image_flie2,image2,document_type,document_type1,application_no from property_correction_form_image where unique_id='" + propertyId + "' and application_no='" + complainNo + "') as d");
            sb.append(" where f.unique_id=d.unique_id and f.unique_id='" + propertyId + "' and f.application_no='" + applicationNo + "'");

            //sb.append("select unique_id,image_name,image,image_file1,image1,image_flie2,image2,document_type,document_type1 from property_correction_form_image where unique_id='"+propertyId+"' and application_no='"+complainNo+"'");
            Query query1 = sessionHB.createSQLQuery(sb.toString());
            int result = query1.executeUpdate();
            Query query2 = sessionHB.createSQLQuery("update property_correction_form_image set move_from_offline_to_online=:data where unique_id=:uid and application_no=:ref");
            query2.setParameter("uid", propertyId);
            query2.setParameter("ref", complainNo);
            query2.setParameter("data", 'Y');

            int result1 = query2.executeUpdate();

//            List<Object[]> rows = query1.list();
//            for (Object[] row : rows) {
//             if (row[0]!= null) {
//                    pid=row[0].toString();
//                    // System.out.println(" row[6].toString() if"+row[6].toString());
//               }
//             if (row[1]!= null) {
//                    imgName=row[1].toString();
//                    // System.out.println(" row[6].toString() if"+row[6].toString());
//             }
//             if (row[2]!= null) {
//                 ByteArrayOutputStream bos=new  ByteArrayOutputStream();
//                 ObjectOutputStream oos =new ObjectOutputStream(bos);
//                 oos.writeObject(row[2]);
//                 oos.flush();
//                 img = bos.toByteArray();
//                 
//                    // System.out.println(" row[6].toString() if"+row[6].toString());
//             }
//             if (row[3]!= null) {
//                    imgName1=row[3].toString();
//                    // System.out.println(" row[6].toString() if"+row[6].toString());
//             }
//             if (row[4]!= null) {
//                    ByteArrayOutputStream bos=new  ByteArrayOutputStream();
//                    ObjectOutputStream oos =new ObjectOutputStream(bos);
//                    oos.writeObject(row[4]);
//                    oos.flush();
//                    img1 = bos.toByteArray();
//                    // System.out.println(" row[6].toString() if"+row[6].toString());
//             }
//             if (row[5]!= null) {
//                    imgName2=row[5].toString();
//                    // System.out.println(" row[6].toString() if"+row[6].toString());
//             }
//             if (row[6]!= null) {
//                    ByteArrayOutputStream bos=new  ByteArrayOutputStream();
//                    ObjectOutputStream oos =new ObjectOutputStream(bos);
//                    oos.writeObject(row[6]);
//                    oos.flush();
//                    img2 = bos.toByteArray();
//                    // System.out.println(" row[6].toString() if"+row[6].toString());
//             }
//             if (row[7]!= null) {
//                    doc1=row[7].toString();
//                    // System.out.println(" row[6].toString() if"+row[6].toString());
//             }
//             if (row[8]!= null) {
//                    doc2=row[8].toString();
//                    // System.out.println(" row[6].toString() if"+row[6].toString());
//             }
//             
//             
//            //System.out.println("session object "+sessionHB);
//            
//            //System.out.println("sessionhhhh");
//
//           
//            }
//            
//            if(imgName!=null && imgName.trim().length()>0){
//                Query query2 = sessionHB.createSQLQuery("update property_correction_form set imagefilename=:imgage1 where unique_id=:uid and application_no=:ref");
//                query2.setParameter("uid", propertyId);
//                query2.setParameter("ref", applicationNo);
//                query2.setParameter("imgage1", imgName);
//                int result = query2.executeUpdate();
//                                
//            }
//            if(imgName1!=null && imgName1.trim().length()>0){
//                Query query3 = sessionHB.createSQLQuery("update property_correction_form set imagefileowner2=:imgage2 where unique_id=:uid and application_no=:ref");
//                query3.setParameter("uid", propertyId);
//                query3.setParameter("ref", applicationNo);
//                query3.setParameter("imgage2", imgName1);
//                int result = query3.executeUpdate();
//                                
//            }
//            if(imgName2!=null && imgName2.trim().length()>0){
//                Query query4 = sessionHB.createSQLQuery("update property_correction_form set imagefilenameowner2=:imgage3 where unique_id=:uid and application_no=:ref");
//                query4.setParameter("uid", propertyId);
//                query4.setParameter("ref", applicationNo);
//                query4.setParameter("imgage3", imgName2);
//                int result = query4.executeUpdate();
//                                
//            }
//            if(doc1!=null && doc1.trim().length()>0){
//                Query query5 = sessionHB.createSQLQuery("update property_correction_form set documenttype=:doc where unique_id=:uid and application_no=:ref");
//                query5.setParameter("uid", propertyId);
//                query5.setParameter("ref", applicationNo);
//                query5.setParameter("doc", doc1);
//                int result = query5.executeUpdate();
//                                
//            }
//            if(doc2!=null && doc2.trim().length()>0){
//                Query query6 = sessionHB.createSQLQuery("update property_correction_form set documenttype1=:doc1 where unique_id=:uid and application_no=:ref");
//                query6.setParameter("uid", propertyId);
//                query6.setParameter("ref", applicationNo);
//                query6.setParameter("doc1", doc2);
//                int result = query6.executeUpdate();
//                                
//            }
//            
//            if(complainNo!=null && complainNo.trim().length()>0){
//                Query query7 = sessionHB.createSQLQuery("update property_correction_form set offline_to_online_refno=:doc1 where unique_id=:uid and application_no=:ref");
//                query7.setParameter("uid", propertyId);
//                query7.setParameter("ref", applicationNo);
//                query7.setParameter("doc1", complainNo);
//                int result = query7.executeUpdate();
//                                
//            }
//            if(img.length>0){
//                Query query8 = sessionHB.createSQLQuery("update property_details set image=:imagedata where unique_id=:uid and application_no=:ref");
//                query8.setParameter("uid", propertyId);
//                query8.setParameter("ref", applicationNo);
//                query8.setParameter("imagedata", img);
//                int result = query8.executeUpdate();
//                
//            }
//            if(img1.length>0){
//                Query query8 = sessionHB.createSQLQuery("update property_details set image_owner2=:imagedata where unique_id=:uid and application_no=:ref");
//                query8.setParameter("uid", propertyId);
//                query8.setParameter("ref", applicationNo);
//                query8.setParameter("imagedata", img1);
//                int result = query8.executeUpdate();
//                
//            }
//            if(img2.length>0){
//                Query query8 = sessionHB.createSQLQuery("update property_details set image_owner3=:imagedata where unique_id=:uid and application_no=:ref");
//                query8.setParameter("uid", propertyId);
//                query8.setParameter("ref", applicationNo);
//                query8.setParameter("imagedata", img2);
//                int result = query8.executeUpdate();
//                
//            }
            msg = "Successfully inserted and updated image";

        } catch (Exception e) {
            msg = "error in correction form";
            e.printStackTrace();
            logger.info(e.getMessage());
        } finally {
            if (sessionHB != null) {
                sessionHB.flush();
                sessionHB.clear();
                sessionHB.close();
            }
        }
        return msg;
    }

    public String loadLocality(String ward) {
        Session sessionHB = null;
        String msg = "";
        String warddata = "";
        String data = "";

        StringBuffer sb = new StringBuffer();

        try {
            //System.out.println(" dao "+ward );
            sb.append("select distinct property_locality from property_details  where property_locality!='' and  ward='" + ward + "'");
            sessionHB = sessionFactory.openSession();
            Query query1 = sessionHB.createSQLQuery(sb.toString());
            List<String> rows = query1.list();
            Iterator itr = rows.iterator();
            while (itr.hasNext()) {
                data = (String) itr.next();
                //System.out.println("data "+data);
                warddata = warddata + data + ":";

            }

//            for (Object[] row : rows) {
//                System.out.println("inside row "+row[0]);
//                if(row[0]!=null){
//                   System.out.println("row[0] "+row[0]);
//                    warddata=warddata+row[0]+":" ;
//                }
//            }
            warddata = warddata.substring(0, warddata.length() - 1);

        } catch (Exception e) {
            //e.printStackTrace();
            logger.info(e.getMessage());
        } finally {
            if (sessionHB != null) {
                sessionHB.close();
            }
        }

        return warddata;
    }

    public List<Map<String, Object>> getRegisterationReq(String reqNo) {

        Session sessionHB = null;
        List<Map<String, Object>> pftb = null;

        try {
            sessionHB = sessionFactory.openSession();
            Query query = sessionHB.createSQLQuery("SELECT sno, unique_id, owner_name, onwer_mobile, owner_email, occupier_name, "
                    + " occupier_mobile, occupier_email, document_type, image_name, image_data,"
                    + " applicant_name, image_name1, image_data1, to_char(entrydatetime, 'DD-MM-YYYY HH:mm AM') as req_time,document_proof_no"
                    + " FROM contact_change_req where sno= " + reqNo + " and req_status='P' order by entrydatetime");

            //System.out.println("query : "+query);
            query.setResultTransformer(Criteria.ALIAS_TO_ENTITY_MAP);
            pftb = (List<Map<String, Object>>) query.list();
            for (Map<String, Object> m : pftb) {
                //System.out.println("assdadad : "+m.get("image_data"));
                byte[] bArr = (byte[]) m.get("image_data");
                m.put("image_data", new String(Base64.encodeBase64(bArr)));
                bArr = (byte[]) m.get("image_data1");
                m.put("image_data1", new String(Base64.encodeBase64(bArr)));
                //System.out.println("sdsadad : "+m.get("image_data1"));
            }

            System.out.println("ptfb : " + pftb.get(0));

        } catch (Exception ex) {
            ex.printStackTrace();
            logger.info("[CorrectionFormFloorBean.getAllRegisterationReq] Exception : " + ex.getMessage());
        } finally {
            if (sessionHB != null) {
                sessionHB.flush();
                sessionHB.clear();
                sessionHB.close();
            }
        }

        return pftb;
    }

    public List<Map<String, Object>> getRegisterationForMobile(String pid) {
        Session sessionHB = null;
        List<Map<String, Object>> pftb = null;

        try {
            sessionHB = sessionFactory.openSession();
            Query query = sessionHB.createSQLQuery("select property_unique_id,property_owner,property_occupier_name,property_aadhar_num, "
                    + " property_contact,complete_address,property_owner_email from property_details where property_unique_id='" + pid + "'");

            //System.out.println("query : "+query);
            query.setResultTransformer(Criteria.ALIAS_TO_ENTITY_MAP);
            pftb = (List<Map<String, Object>>) query.list();

            System.out.println("ptfb : " + pftb.get(0));

        } catch (Exception ex) {
            ex.printStackTrace();
            logger.info("[CorrectionFormFloorBean.getAllRegisterationReq] Exception : " + ex.getMessage());
        } finally {
            if (sessionHB != null) {
                sessionHB.flush();
                sessionHB.clear();
                sessionHB.close();
            }
        }

        return pftb;
    }

    public List<Map<String, String>> getAllRegisterationReq() {

        Session sessionHB = null;
        List<Map<String, String>> pftb = null;

        try {
            sessionHB = sessionFactory.openSession();
            Query query = sessionHB.createSQLQuery("SELECT sno, unique_id, owner_name, "
                    + " applicant_name, to_char(entrydatetime, 'DD-MM-YYYY HH:mm AM') as req_time"
                    + " FROM contact_change_req where req_status='P' order by entrydatetime");

            query.setResultTransformer(Criteria.ALIAS_TO_ENTITY_MAP);
            pftb = (List<Map<String, String>>) query.list();

        } catch (Exception ex) {
            logger.info("[CorrectionFormFloorBean.getAllRegisterationReq] Exception : " + ex.getMessage());
        } finally {
            if (sessionHB != null) {
                sessionHB.flush();
                sessionHB.clear();
                sessionHB.close();
            }
        }

        return pftb;
    }

    @Override
    public int updateUserRegistrationStatus(Integer sno, String remarks, String action) {
        Session sessionHB = null;
        int result = 0;

        try {
            sessionHB = sessionFactory.openSession();
            Query query = sessionHB.createSQLQuery("UPDATE contact_change_req  SET req_status=?,remarks = ? WHERE sno = ?");
            if (action.equals("update")) {
                query.setString(0, "A");
            } else {
                query.setString(0, "R");
            }
            query.setString(1, remarks);
            query.setInteger(2, sno);

            result = query.executeUpdate();

        } catch (Exception ex) {
            //ex.printStackTrace();
            logger.info("[CorrectionFormFloorBean.updateUserRegistration] Exception : " + ex.getMessage());
        } finally {
            if (sessionHB != null) {
                sessionHB.flush();
                sessionHB.clear();
                sessionHB.close();
            }
        }

        return result;
    }

    public String saveMobile(String propid, String ownerMobile, String ownerEmail) {
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
            //System.out.println("ownerEmail "+ownerEmail);
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
//            if(occupierMobile!=null && occupierMobile.trim().length()>0){
//                Query query1 = sessionHB.createSQLQuery("update property_details set occupier_contactno=:occucontact where property_unique_id=:uid");
//                query1.setParameter("uid", propid);
//                query1.setParameter("occucontact", occupierMobile);
//                int result = query1.executeUpdate();
//                 msg=msg+" occupier mobile ";
//                 p3=1;
//            }
//            if(occupierEmail!=null && occupierEmail.trim().length()>0){
//                Query query1 = sessionHB.createSQLQuery("update property_details set occupier_email=:occuemail where property_unique_id=:uid");
//                query1.setParameter("uid", propid);
//                query1.setParameter("occuemail", occupierEmail);
//                int result = query1.executeUpdate();
//                msg=msg+" occupier email ";
//                p4=1;
//            }

            if (p1 == 1 || p2 == 2) {
                msg = "Updated successfully";
            } else {
                msg = "";
            }

        } catch (Exception e) {
            e.printStackTrace();
            logger.info(e.getMessage());
        } finally {
            if (sessionHB != null) {
                sessionHB.flush();
                sessionHB.clear();
                sessionHB.close();
            }
        }

        return msg;
    }

    public String getRemarks(String uid, String correctionId) {
        Session sessionHB = null;
        StringBuffer sb = new StringBuffer();
        String msg = "";
        try {
            sessionHB = sessionFactory.openSession();
            sb.append("Select property_id,correction_id, action_taken, action_remarks from correction_action_history where property_id='" + uid + "' and  correction_id ='" + correctionId + "'  order by entrydatetime desc limit 1");
            Query query = sessionHB.createSQLQuery(sb.toString());
            List<Object[]> rows = query.list();
            for (Object[] row : rows) {
                if (row[3] == null) {
                    msg = "";
                } else {
                    msg = String.valueOf(row[3]);
                }

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

        return msg;

    }

    public List<CorrectionFormSaveBean> getCorrectionReport(String report) {
        Session sessionHB = null;
        String msg = "";
        CorrectionFormSaveBean ptdBean = null;
        StringBuffer sb = new StringBuffer();
        ArrayList<CorrectionFormSaveBean> ar_bean = new ArrayList<CorrectionFormSaveBean>();

        try {
            //and cs.status='fieldverify'
            //System.out.println("session "+bean.getUniqueId());
            if (report.equalsIgnoreCase("owner")) {
                sb.append(" select cf.unique_id,cf.application_no,cf.ward_no,cf.owner_name,cf.owner_sex,cf.owner_father,cf.spouse_name,check_owner_name , cf.check_owner_father, cf.check_spouse_name,cs.status,cf.remarks from property_correction_form cf,correction_status cs where cf.unique_id=cs.property_id and cf.application_no=cs.correction_id  and  (cf.check_owner_name='Y' or cf.check_owner_father='Y' or cf.check_spouse_name='Y') and cs.status='approve' ");
                sessionHB = sessionFactory.openSession();
                //System.out.println("ddd "+sb.toString());
                Query query1 = sessionHB.createSQLQuery(sb.toString());
                List<Object[]> rows = query1.list();
                for (Object[] row : rows) {
                    //System.out.println("rows[39] "+row[39]);

                    ptdBean = new CorrectionFormSaveBean();
                    ptdBean.setUniqueId(row[0] == null ? "" : String.valueOf(row[0]));
                    ptdBean.setApplicationNo(row[1] == null ? "" : String.valueOf(row[1]));
                    ptdBean.setWardNo(row[2] == null ? "" : String.valueOf(row[2]));
                    //ptdBean.setWardNo(row[1] == null ? "" : String.valueOf(row[1]));
                    ptdBean.setOwnerName(row[3] == null ? "" : String.valueOf(row[3]));
                    ptdBean.setOwnerSex(row[4] == null ? "" : String.valueOf(row[4]));
                    if (row[5] == null) {
                        ptdBean.setOwnerFather("");
                        // System.out.println(" row[6].toString() if"+row[6].toString());
                    } else {
                        ptdBean.setOwnerFather(row[5].toString());
                        //System.out.println(" row[6].toString() else "+row[6].toString());
                    }
                    ptdBean.setSpouseName(row[6] == null ? "" : String.valueOf(row[6]));
                    ptdBean.setCheckOwnerName(row[7] == null ? "" : String.valueOf(row[7]));
                    ptdBean.setCheckFatherName(row[8] == null ? "" : String.valueOf(row[8]));
                    ptdBean.setCheckSpouseName(row[9] == null ? "" : String.valueOf(row[9]));
                    ptdBean.setStatus(row[10] == null ? "" : String.valueOf(row[10]));
                    String remarksMsg = getRemarks(ptdBean.getUniqueId(), ptdBean.getApplicationNo());
                    ptdBean.setRemarks(remarksMsg);
                 //ptdBean.setRemarks(row[11] == null ? "" : String.valueOf(row[11]));

                    ar_bean.add(ptdBean);

                }

            } else if (report.equalsIgnoreCase("address")) {
                sb.append(" select cf.unique_id,cf.application_no,cf.ward_no,cf.owner_name,cf.plotno,cf.house_no,cf.building_name,");
                sb.append(" cf.road_name,cf.sublocality,cf.locality_name,cf.landmark,fn_getAddress(unique_id) as complete_addr,cf.check_plot_no, cf.check_house_no, cf.check_building_name, cf.check_road_name, cf.check_sublocality,  cf.check_loc_name, cf.check_land_mark,cs.status,cf.remarks from property_correction_form cf,correction_status cs where ");
                sb.append(" cf.unique_id=cs.property_id and cf.application_no=cs.correction_id  and (cf.check_plot_no='Y' or cf.check_house_no='Y' or cf.check_building_name='Y' or cf.check_road_name='Y' or cf.check_sublocality='Y' or cf.check_loc_name='Y' or cf.check_land_mark='Y' ) and cs.status='approve'  ");
                sessionHB = sessionFactory.openSession();
                //System.out.println("ddd "+sb.toString());
                Query query1 = sessionHB.createSQLQuery(sb.toString());
                List<Object[]> rows = query1.list();
                for (Object[] row : rows) {
                    //System.out.println("rows[39] "+row[39]);

                    ptdBean = new CorrectionFormSaveBean();
                    ptdBean.setUniqueId(row[0] == null ? "" : String.valueOf(row[0]));
                    ptdBean.setApplicationNo(row[1] == null ? "" : String.valueOf(row[1]));
                    ptdBean.setWardNo(row[2] == null ? "" : String.valueOf(row[2]));
                    //ptdBean.setWardNo(row[1] == null ? "" : String.valueOf(row[1]));
                    ptdBean.setOwnerName(row[3] == null ? "" : String.valueOf(row[3]));
                    ptdBean.setPlotNo(row[4] == null ? "" : String.valueOf(row[4]));
                    ptdBean.setHoueNo(row[5] == null ? "" : String.valueOf(row[5]));
                    ptdBean.setBuildingName(row[6] == null ? "" : String.valueOf(row[6]));
                    ptdBean.setRoadName(row[7] == null ? "" : String.valueOf(row[7]));
                    ptdBean.setSubLocality(row[8] == null ? "" : String.valueOf(row[8]));
                    ptdBean.setLocName(row[9] == null ? "" : String.valueOf(row[9]));
                    ptdBean.setLandMark(row[10] == null ? "" : String.valueOf(row[10]));
                    ptdBean.setAddress(row[11] == null ? "" : String.valueOf(row[11]));
                    ptdBean.setCheckplotno(row[12] == null ? "" : String.valueOf(row[12]));
                    ptdBean.setCheckHouseNo(row[13] == null ? "" : String.valueOf(row[13]));
                    ptdBean.setCheckBuildingName(row[14] == null ? "" : String.valueOf(row[14]));
                    ptdBean.setCheckRoadName(row[15] == null ? "" : String.valueOf(row[15]));
                    ptdBean.setCheckSublocality(row[16] == null ? "" : String.valueOf(row[16]));
                    ptdBean.setCheckLocName(row[17] == null ? "" : String.valueOf(row[17]));
                    ptdBean.setCheckLandMark(row[18] == null ? "" : String.valueOf(row[18]));
                    ptdBean.setStatus(row[19] == null ? "" : String.valueOf(row[19]));
                    String remarksMsg = getRemarks(ptdBean.getUniqueId(), ptdBean.getApplicationNo());
                    ptdBean.setRemarks(remarksMsg);
                //ptdBean.setRemarks(row[20] == null ? "" : String.valueOf(row[20]));

                    ar_bean.add(ptdBean);

                }

            } else if (report.equalsIgnoreCase("arrear")) {
                sb.append("select cf.unique_id,cf.application_no,cf.ward_no,cf.owner_name,arrearamount,cs.status,cf.remarks,cf.checkarrear from property_correction_form cf,correction_status cs where cf.unique_id=cs.property_id and  cf.application_no=cs.correction_id and (cf.checkarrear='Y' )  and cs.status='approve' ");
                sessionHB = sessionFactory.openSession();
                //System.out.println("ddd "+sb.toString());
                Query query1 = sessionHB.createSQLQuery(sb.toString());
                List<Object[]> rows = query1.list();
                for (Object[] row : rows) {
                    //System.out.println("rows[39] "+row[39]);

                    ptdBean = new CorrectionFormSaveBean();
                    ptdBean.setUniqueId(row[0] == null ? "" : String.valueOf(row[0]));
                    ptdBean.setApplicationNo(row[1] == null ? "" : String.valueOf(row[1]));
                    ptdBean.setWardNo(row[2] == null ? "" : String.valueOf(row[2]));
                    //ptdBean.setWardNo(row[1] == null ? "" : String.valueOf(row[1]));
                    ptdBean.setOwnerName(row[3] == null ? "" : String.valueOf(row[3]));
                    ptdBean.setArrearAmount(row[4] == null ? "" : String.valueOf(row[4]));
                //ptdBean.setSmcHoldingNo(row[4] == null ? "" : String.valueOf(row[4]));

                    ptdBean.setStatus(row[5] == null ? "" : String.valueOf(row[5]));
                    //ptdBean.setRemarks(row[6] == null ? "" : String.valueOf(row[6]));
                    String remarksMsg = getRemarks(ptdBean.getUniqueId(), ptdBean.getApplicationNo());
                    ptdBean.setRemarks(remarksMsg);
                    ptdBean.setCheckArrear(row[7] == null ? "" : String.valueOf(row[7]));

                    ar_bean.add(ptdBean);

                }
            } else if (report.equalsIgnoreCase("smchouseno")) {
                sb.append("select cf.unique_id,cf.application_no,cf.ward_no,cf.owner_name,smc_holding_no,cf.check_smc_holding_no,"
                        + "cs.status,cf.remarks from property_correction_form cf, correction_status cs where cf.unique_id=cs.property_id "
                        + "and cf.application_no=cs.correction_id and  (cf.check_smc_holding_no='Y' ) and cs.status='approve' ");
                sessionHB = sessionFactory.openSession();
                //System.out.println("ddd "+sb.toString());
                Query query1 = sessionHB.createSQLQuery(sb.toString());
                List<Object[]> rows = query1.list();
                for (Object[] row : rows) {
                    //System.out.println("rows[39] "+row[39]);

                    ptdBean = new CorrectionFormSaveBean();
                    ptdBean.setUniqueId(row[0] == null ? "" : String.valueOf(row[0]));
                    ptdBean.setApplicationNo(row[1] == null ? "" : String.valueOf(row[1]));
                    ptdBean.setWardNo(row[2] == null ? "" : String.valueOf(row[2]));
                    //ptdBean.setWardNo(row[1] == null ? "" : String.valueOf(row[1]));
                    ptdBean.setOwnerName(row[3] == null ? "" : String.valueOf(row[3]));

                    ptdBean.setSmcHoldingNo(row[4] == null ? "" : String.valueOf(row[4]));

                    ptdBean.setCheckSmcHoldingNo(row[5] == null ? "" : String.valueOf(row[5]));
                    ptdBean.setStatus(row[6] == null ? "" : String.valueOf(row[6]));
                    //ptdBean.setRemarks(row[7] == null ? "" : String.valueOf(row[7]));
                    String remarksMsg = getRemarks(ptdBean.getUniqueId(), ptdBean.getApplicationNo());
                    ptdBean.setRemarks(remarksMsg);

                    //ptdBean.setArrearAmount(row[5] == null ? "" : String.valueOf(row[5]));
                    ar_bean.add(ptdBean);

                }
            } else if (report.equalsIgnoreCase("combinereport")) {
                logger.info("combinereport ");
//                sb.append(" select cf.unique_id,cf.application_no,cf.ward_no,cf.owner_name,cf.owner_sex,"
//                        + "cf.owner_father,cf.spouse_name,owneraadharno ,fn_getAddress(unique_id) as complete_addr,"
//                        + "cf.smc_holding_no,cf.arrearamount,cs.status from property_correction_form cf,correction_status"
//                        + " cs where cf.unique_id=cs.property_id and cf.application_no=cs.correction_id  and"
//                        + "  (cf.check_owner_name='Y' or cf.check_owner_father='Y' or cf.check_spouse_name='Y' "
//                        + "or cf.check_plot_no='Y' or cf.check_house_no='Y' or cf.check_building_name='Y' or "
//                        + "cf.check_road_name='Y' or cf.check_sublocality='Y' or cf.check_loc_name='Y' or "
//                        + "cf.check_land_mark='Y' or cf.check_smc_holding_no='Y' or cf.checkarrear='Y')");
//                

                sb.append(" select cf.unique_id,cf.application_no,cf.ward_no,cf.owner_name,cf.owner_sex,");
                sb.append(" cf.owner_father,cf.spouse_name,owneraadharno ,fn_getAddress(unique_id) as complete_addr,");
                sb.append(" cf.smc_holding_no,cf.arrearamount,cs.status from property_correction_form cf left join correction_status");
                sb.append(" cs on cf.unique_id=cs.property_id and cf.application_no=cs.correction_id ");

                sessionHB = sessionFactory.openSession();
                // System.out.println("ddd "+sb.toString());
                logger.info("combinereport query  " + sb.toString());
                Query query1 = sessionHB.createSQLQuery(sb.toString());
                List<Object[]> rows = query1.list();
                // System.out.println("rows "+rows.size());
                for (Object[] row : rows) {
                    // System.out.println("rows[39] "+String.valueOf(row[0]));

                    ptdBean = new CorrectionFormSaveBean();
                    ptdBean.setUniqueId(row[0] == null ? "" : String.valueOf(row[0]));
                    ptdBean.setApplicationNo(row[1] == null ? "" : String.valueOf(row[1]));
                    ptdBean.setWardNo(row[2] == null ? "" : String.valueOf(row[2]));
                    //ptdBean.setWardNo(row[1] == null ? "" : String.valueOf(row[1]));
                    ptdBean.setOwnerName(row[3] == null ? "" : String.valueOf(row[3]));
                    ptdBean.setOwnerSex(row[4] == null ? "" : String.valueOf(row[4]));
                    if (row[5] == null) {
                        ptdBean.setOwnerFather("");
                        // System.out.println(" row[6].toString() if"+row[6].toString());
                    } else {
                        ptdBean.setOwnerFather(row[5].toString());
                        //System.out.println(" row[6].toString() else "+row[6].toString());
                    }
                    ptdBean.setSpouseName(row[6] == null ? "" : String.valueOf(row[6]));
                    ptdBean.setOwnerAadharNo(row[7] == null ? "" : String.valueOf(row[7]));
                    ptdBean.setAddress(row[8] == null ? "" : String.valueOf(row[8]));
                    ptdBean.setSmcHoldingNo(row[9] == null ? "" : String.valueOf(row[9]));
                    ptdBean.setArrearAmount(row[10] == null ? "" : String.valueOf(row[10]));
                    ptdBean.setStatus(row[11] == null ? "" : String.valueOf(row[11]));
                    String remarksMsg = getRemarks(ptdBean.getUniqueId(), ptdBean.getApplicationNo());
                    ptdBean.setRemarks(remarksMsg);
                 //ptdBean.setRemarks(row[11] == null ? "" : String.valueOf(row[11]));

                    ar_bean.add(ptdBean);

                }
                logger.info("combinereport end");

            } else if (report.equalsIgnoreCase("combineowner")) {
                logger.info("property owner report start");
//                sb.append(" select cf.unique_id,cf.application_no,cf.ward_no,cf.owner_name,cf.owner_sex,"
//                        + "cf.owner_father,cf.spouse_name,owneraadharno ,fn_getAddress(unique_id) as complete_addr,"
//                        + "cf.smc_holding_no,cf.arrearamount,cs.status from property_correction_form cf,correction_status"
//                        + " cs where cf.unique_id=cs.property_id and cf.application_no=cs.correction_id  and"
//                        + "  (cf.check_owner_name='Y' or cf.check_owner_father='Y' or cf.check_spouse_name='Y' "
//                        + "or cf.check_plot_no='Y' or cf.check_house_no='Y' or cf.check_building_name='Y' or "
//                        + "cf.check_road_name='Y' or cf.check_sublocality='Y' or cf.check_loc_name='Y' or "
//                        + "cf.check_land_mark='Y' or cf.check_smc_holding_no='Y' or cf.checkarrear='Y')");
//                

                sb.append(" select cf.unique_id,cf.application_no,cf.ward_no,cf.owner_name,cf.owner_sex,");
                sb.append(" cf.owner_father,cf.spouse_name,owneraadharno ,fn_getAddress(unique_id) as complete_addr,");
                sb.append(" cf.smc_holding_no,cf.arrearamount,cs.status from property_correction_form cf left join correction_status");
                sb.append(" cs on cf.unique_id=cs.property_id and cf.application_no=cs.correction_id ");

                sessionHB = sessionFactory.openSession();
                // System.out.println("ddd "+sb.toString());
                logger.info("property owner " + sb.toString());
                Query query1 = sessionHB.createSQLQuery(sb.toString());
                List<Object[]> rows = query1.list();
                // System.out.println("rows "+rows.size());
                for (Object[] row : rows) {
                    // System.out.println("rows[39] "+String.valueOf(row[0]));

                    ptdBean = new CorrectionFormSaveBean();
                    ptdBean.setUniqueId(row[0] == null ? "" : String.valueOf(row[0]));
                    ptdBean.setApplicationNo(row[1] == null ? "" : String.valueOf(row[1]));
                    ptdBean.setWardNo(row[2] == null ? "" : String.valueOf(row[2]));
                    //ptdBean.setWardNo(row[1] == null ? "" : String.valueOf(row[1]));
                    ptdBean.setOwnerName(row[3] == null ? "" : String.valueOf(row[3]));
                    ptdBean.setOwnerSex(row[4] == null ? "" : String.valueOf(row[4]));
                    if (row[5] == null) {
                        ptdBean.setOwnerFather("");
                        // System.out.println(" row[6].toString() if"+row[6].toString());
                    } else {
                        ptdBean.setOwnerFather(row[5].toString());
                        //System.out.println(" row[6].toString() else "+row[6].toString());
                    }
                    ptdBean.setSpouseName(row[6] == null ? "" : String.valueOf(row[6]));
                    ptdBean.setOwnerAadharNo(row[7] == null ? "" : String.valueOf(row[7]));
                    ptdBean.setAddress(row[8] == null ? "" : String.valueOf(row[8]));
                    ptdBean.setSmcHoldingNo(row[9] == null ? "" : String.valueOf(row[9]));
                    ptdBean.setArrearAmount(row[10] == null ? "" : String.valueOf(row[10]));
                    ptdBean.setStatus(row[11] == null ? "" : String.valueOf(row[11]));
                    String remarksMsg = getRemarks(ptdBean.getUniqueId(), ptdBean.getApplicationNo());
                    ptdBean.setRemarks(remarksMsg);
                 //ptdBean.setRemarks(row[11] == null ? "" : String.valueOf(row[11]));

                    ar_bean.add(ptdBean);

                }

                logger.info("property owner end ");
            }
//            sb.append(" select unique_id,owner_name,owner_sex,owner_father,spouse_name,owner_contact,owner_email,owneraadharno,property_owner_address,");
//            sb.append(" occupier_name,occupier_sex,occupier_father,occupier_contact,occupier_email,occupieraadharno,plotno,house_no,building_name,road_name,sublocality, ");
//            sb.append(" locality_name,landmark,electric_service_connection_no,surveyno,plotsmc,smc_holding_no,applicant_name,application_no,permission_data,ward_no,notice_date,arrearamount from property_correction_form cf where  ");
//            sb.append(" (cf.check_owner_name='Y' or cf.check_owner_father='Y' or cf.check_spouse_name='Y' or cf.check_owner_contact='Y' or");
//            sb.append(" cf.check_owner_email='Y' or cf.check_occupier_name='Y' or cf.check_occupier_father='Y' or cf.check_occupier_contact='Y' or ");
//            sb.append(" cf.check_occupier_email='Y' or cf.check_plot_no='Y' or cf.check_house_no='Y' or cf.check_building_name='Y' or cf.check_road_name='Y' or");
//            sb.append(" cf.check_sublocality='Y' or cf.check_loc_name='Y' or cf.check_land_mark='Y' or cf.check_electric_serice_connection_no='Y' or ");
//            sb.append(" cf.checkowneraadharno='Y' or cf.checkoccupieraadharno='Y' or cf.checksurveyno='Y' or cf.checkplotsmc='Y' or cf.checkarrear='Y' or");
//            sb.append(" cf.check_smc_holding_no='Y' or   cf.check_owner_sex='Y' or cf.check_occupier_sex='Y' or cf.check_property_owner_address='Y')");
//                   
           /* sessionHB = sessionFactory.openSession();
             //System.out.println("ddd "+sb.toString());
             Query query1 = sessionHB.createSQLQuery(sb.toString());
             List<Object[]> rows = query1.list();
             for (Object[] row : rows) {
             //System.out.println("rows[39] "+row[39]);

             ptdBean = new CorrectionFormSaveBean();
             ptdBean.setUniqueId(row[0] == null ? "" : String.valueOf(row[0]));
             //ptdBean.setWardNo(row[1] == null ? "" : String.valueOf(row[1]));
             ptdBean.setOwnerName(row[1] == null ? "" : String.valueOf(row[1]));
             ptdBean.setOwnerSex(row[2] == null ? "" : String.valueOf(row[2]));
             if (row[3] == null) {
             ptdBean.setOwnerFather("");
             // System.out.println(" row[6].toString() if"+row[6].toString());
             } else {
             ptdBean.setOwnerFather(row[3].toString());
             //System.out.println(" row[6].toString() else "+row[6].toString());
             }
             ptdBean.setSpouseName(row[4] == null ? "" : String.valueOf(row[4]));
                
             ptdBean.setOwnerContact(row[5] == null ? "" : String.valueOf(row[5]));
             ptdBean.setOwnerEmail(row[6] == null ? "" : String.valueOf(row[6]));
             ptdBean.setOwnerAadharNo(row[7] == null ? "" : String.valueOf(row[7]));
             ptdBean.setPropertyOwnerAddress(row[8] == null ? "" : String.valueOf(row[8]));
             ptdBean.setOccupierName(row[9] == null ? "" : String.valueOf(row[9]));
             ptdBean.setOccupierSex(row[10] == null ? "" : String.valueOf(row[10]));
             ptdBean.setOccupierFather(row[11] == null ? "" : String.valueOf(row[11]));
             ptdBean.setOccupierContact(row[12] == null ? "" : String.valueOf(row[12]));
             ptdBean.setOccupierEmail(row[13] == null ? "" : String.valueOf(row[13]));
             ptdBean.setOccupierAadharNo(row[14] == null ? "" : String.valueOf(row[14]));
             ptdBean.setPlotNo(row[15] == null ? "" : String.valueOf(row[15]));
             ptdBean.setHoueNo(row[16] == null ? "" : String.valueOf(row[16]));
             ptdBean.setBuildingName(row[17] == null ? "" : String.valueOf(row[17]));
             ptdBean.setRoadName(row[18] == null ? "" : String.valueOf(row[18]));
             ptdBean.setSubLocality(row[19] == null ? "" : String.valueOf(row[19]));
             ptdBean.setLocName(row[20] == null ? "" : String.valueOf(row[20]));
             ptdBean.setLandMark(row[21] == null ? "" : String.valueOf(row[21]));
             ptdBean.setElectricServiceNo(row[22] == null ? "" : String.valueOf(row[22]));
             ptdBean.setSurveyNo(row[23] == null ? "" : String.valueOf(row[23]));
             ptdBean.setPlotSmc(row[24] == null ? "" : String.valueOf(row[24]));
             ptdBean.setSmcHoldingNo(row[25] == null ? "" : String.valueOf(row[25]));
             ptdBean.setApplicantName(row[26] == null ? "" : String.valueOf(row[26]));
             ptdBean.setApplicationNo(row[27] == null ? "" : String.valueOf(row[27]));
             ptdBean.setPermissionData(row[28] == null ? "" : String.valueOf(row[28]));
             ptdBean.setWardNo(row[29] == null ? "" : String.valueOf(row[29]));
             ptdBean.setNoticeDate(row[30] == null ? "" : String.valueOf(row[30]));
             ptdBean.setArrearAmount(row[31] == null ? "" : String.valueOf(row[31]));
             ar_bean.add(ptdBean);
                  
              
             }*/
            //bean= (CorrectionFormBean)sessionHB.get(CorrectionFormBean.class, id);
            //System.out.println("session object "+sessionHB);

        } catch (Exception e) {
            e.printStackTrace();
            logger.info(e.getMessage());
        } finally {
            if (sessionHB != null) {
                sessionHB.flush();
                sessionHB.clear();
                sessionHB.close();
            }
        }
        return ar_bean;
    }

    public Map<String, CorrectionFormReport> getCorrectionReportFromPropertyDetail(String reportType) {

        Session sessionHB = null;
        String msg = "";
        String uid = "";
        HashMap<String, CorrectionFormReport> mapData = new HashMap<String, CorrectionFormReport>();
        CorrectionFormReport ptdBean = null;
        StringBuffer sb = new StringBuffer();
        StringBuffer sb1 = new StringBuffer();
        //ArrayList<CorrectionFormSaveBean> ar_bean=new ArrayList<CorrectionFormSaveBean>();

        try {

            if (reportType.equalsIgnoreCase("owner")) {
                sb1.append("select d.property_unique_id,d.property_owner,d.owner_sex,d.property_owner_father,d.property_owner_spouse from ");
                sb1.append(" property_details d,property_correction_form cf,correction_status cs where d.property_unique_id=cf.unique_id and ");
                sb1.append(" cf.unique_id=cs.property_id and cf.application_no=cs.correction_id  and (cf.check_owner_name='Y' or cf.check_owner_father='Y' or cf.check_spouse_name='Y') and cs.status='approve' ");
                sb1.append(" group by d.property_unique_id; ");
                sessionHB = sessionFactory.openSession();
                //System.out.println("sb1 ak "+sb1.toString());
                Query query2 = sessionHB.createSQLQuery(sb1.toString());
                List<Object[]> rowsProperty = query2.list();
                for (Object[] rowp : rowsProperty) {
                    ptdBean = new CorrectionFormReport();
                    uid = rowp[0].toString();
                    ptdBean.setUniqueId(rowp[0] == null ? "" : String.valueOf(rowp[0]));
                    //ptdBean.setWardNo(row[1] == null ? "" : String.valueOf(row[1]));
                    ptdBean.setOwnerName(rowp[1] == null ? "" : String.valueOf(rowp[1]));
                    ptdBean.setOwnerSex(rowp[2] == null ? "" : String.valueOf(rowp[2]));
                    if (rowp[3] == null) {
                        ptdBean.setOwnerFather("");
                        // System.out.println(" row[6].toString() if"+row[6].toString());
                    } else {
                        ptdBean.setOwnerFather(rowp[3].toString());
                        //System.out.println(" row[6].toString() else "+row[6].toString());
                    }
                    ptdBean.setSpouseName(rowp[4] == null ? "" : String.valueOf(rowp[4]));

                    mapData.put(uid, ptdBean);
                }

                sb1.delete(0, sb1.length());

            } else if (reportType.equalsIgnoreCase("address")) {
                sb1.append("select d.property_unique_id,d.property_owner,d.complete_address from property_details d,property_correction_form cf,correction_status cs where d.property_unique_id=cf.unique_id and cf.unique_id=cs.property_id and cf.application_no=cs.correction_id and ");
                sb1.append("(cf.check_plot_no='Y' or cf.check_house_no='Y' or cf.check_building_name='Y' or cf.check_road_name='Y' or cf.check_sublocality='Y' or cf.check_loc_name='Y' or cf.check_land_mark='Y' ) and cs.status='approve' group by d.property_unique_id");
                sessionHB = sessionFactory.openSession();

                Query query2 = sessionHB.createSQLQuery(sb1.toString());
                List<Object[]> rowsProperty = query2.list();
                for (Object[] rowp : rowsProperty) {
                    ptdBean = new CorrectionFormReport();
                    uid = rowp[0].toString();
                    ptdBean.setUniqueId(rowp[0] == null ? "" : String.valueOf(rowp[0]));
                    //ptdBean.setWardNo(row[1] == null ? "" : String.valueOf(row[1]));
                    ptdBean.setOwnerName(rowp[1] == null ? "" : String.valueOf(rowp[1]));
                    ptdBean.setAddress(rowp[2] == null ? "" : String.valueOf(rowp[2]));

                    mapData.put(uid, ptdBean);
                }

                sb1.delete(0, sb1.length());

            } else if (reportType.equalsIgnoreCase("arrear")) {
                sb1.append(" select d.property_unique_id,d.property_owner,d.property_old_smc_prop_tax_num,t.arrear_amount from ");
                sb1.append(" property_details d,property_tax t,property_correction_form cf,correction_status cs where d.property_unique_id=cf.unique_id and d.property_unique_id= t.property_unique_id and cf.unique_id=cs.property_id   ");
                sb1.append(" and (cf.checkarrear='Y'  ) and t.financial_year='2018-2019'  and cs.status='approve'   group by d.property_unique_id ,t.arrear_amount ");
                sessionHB = sessionFactory.openSession();
                Query query2 = sessionHB.createSQLQuery(sb1.toString());
                List<Object[]> rowsProperty = query2.list();
                for (Object[] rowp : rowsProperty) {
                    ptdBean = new CorrectionFormReport();
                    uid = rowp[0].toString();
                    ptdBean.setUniqueId(rowp[0] == null ? "" : String.valueOf(rowp[0]));
                    //ptdBean.setWardNo(row[1] == null ? "" : String.valueOf(row[1]));
                    ptdBean.setOwnerName(rowp[1] == null ? "" : String.valueOf(rowp[1]));
                    ptdBean.setSmcHoldingNo(rowp[2] == null ? "" : String.valueOf(rowp[2]));
                    ptdBean.setArrearAmount(rowp[3] == null ? "" : String.valueOf(rowp[3]));

                    mapData.put(uid, ptdBean);
                }

                sb1.delete(0, sb1.length());

            } else if (reportType.equalsIgnoreCase("smchouseno")) {
                sb1.append(" select d.property_unique_id,d.property_owner,d.property_old_smc_prop_tax_num from ");
                sb1.append(" property_details d,property_correction_form cf ,correction_status cs  where d.property_unique_id=cf.unique_id  and cf.unique_id=cs.property_id ");
                sb1.append(" and (cf.check_smc_holding_no='Y'  ) and cs.status='approve'  group by d.property_unique_id  ");
                sessionHB = sessionFactory.openSession();
                Query query2 = sessionHB.createSQLQuery(sb1.toString());
                List<Object[]> rowsProperty = query2.list();
                for (Object[] rowp : rowsProperty) {
                    ptdBean = new CorrectionFormReport();
                    uid = rowp[0].toString();
                    ptdBean.setUniqueId(rowp[0] == null ? "" : String.valueOf(rowp[0]));
                    //ptdBean.setWardNo(row[1] == null ? "" : String.valueOf(row[1]));
                    ptdBean.setOwnerName(rowp[1] == null ? "" : String.valueOf(rowp[1]));
                    ptdBean.setSmcHoldingNo(rowp[2] == null ? "" : String.valueOf(rowp[2]));
                    //ptdBean.setArrearAmount(rowp[3] == null ? "" : String.valueOf(rowp[3]));

                    mapData.put(uid, ptdBean);
                }

                sb1.delete(0, sb1.length());

            } else if (reportType.equalsIgnoreCase("combinereport")) {
                logger.info("combinereport for propertyd detail start");
//                 sb1.append(" select d.property_unique_id,d.property_owner,d.owner_sex,d.property_owner_father,d.property_owner_spouse,d.property_aadhar_num,d.complete_address,d.property_old_smc_prop_tax_num,t.arrear_amount from ");
//                 sb1.append(" property_details d,property_correction_form cf,correction_status cs,property_tax t where d.property_unique_id=cf.unique_id and d.property_unique_id=t.property_unique_id and ");
//                 sb1.append(" cf.unique_id=cs.property_id and cf.application_no=cs.correction_id  and (cf.check_owner_name='Y' or cf.check_owner_father='Y' or cf.check_spouse_name='Y' or cf.check_plot_no='Y' or cf.check_house_no='Y' or cf.check_building_name='Y' or cf.check_road_name='Y' or cf.check_sublocality='Y' or cf.check_loc_name='Y' or cf.check_land_mark='Y' or cf.check_smc_holding_no='Y' or cf.checkarrear='Y') and t.financial_year='2018-2019' ");
//                 sb1.append(" group by d.property_unique_id,t.arrear_amount; ");
//                 
                sb1.append("select * from (");
                sb1.append(" select cf.unique_id,cf.application_no,cf.ward_no,d.property_owner ,d.owner_sex,");
                sb1.append(" d.property_owner_father ,d.property_owner_spouse ,d.property_aadhar_num  ,d.complete_address ,");
                sb1.append(" d.property_old_smc_prop_tax_num ,t.arrear_amount  from property_details d,property_correction_form cf,property_tax t");
                sb1.append(" where d.property_unique_id=cf.unique_id and d.property_unique_id=t.property_unique_id  and  t.financial_year='2018-2019')");
                sb1.append(" asas left join correction_status cs on asas.unique_id=cs.property_id and asas.application_no=cs.correction_id;");
                sessionHB = sessionFactory.openSession();
                //System.out.println("sb1 ak "+sb1.toString());
                logger.info("combinereport for propertyd end " + sb1.toString());
                Query query2 = sessionHB.createSQLQuery(sb1.toString());
                List<Object[]> rowsProperty = query2.list();
                for (Object[] rowp : rowsProperty) {
                    ptdBean = new CorrectionFormReport();
                    uid = rowp[0].toString();
                    ptdBean.setUniqueId(rowp[0] == null ? "" : String.valueOf(rowp[0]));
                    //ptdBean.setWardNo(row[1] == null ? "" : String.valueOf(row[1]));
                    ptdBean.setOwnerName(rowp[3] == null ? "" : String.valueOf(rowp[3]));
                    ptdBean.setOwnerSex(rowp[4] == null ? "" : String.valueOf(rowp[4]));
                    if (rowp[5] == null) {
                        ptdBean.setOwnerFather("");
                        // System.out.println(" row[6].toString() if"+row[6].toString());
                    } else {
                        ptdBean.setOwnerFather(rowp[5].toString());
                        //System.out.println(" row[6].toString() else "+row[6].toString());
                    }
                    ptdBean.setSpouseName(rowp[6] == null ? "" : String.valueOf(rowp[6]));
                    ptdBean.setOwnerAadharNo(rowp[7] == null ? "" : String.valueOf(rowp[7]));
                    ptdBean.setAddress(rowp[8] == null ? "" : String.valueOf(rowp[8]));
                    ptdBean.setSmcHoldingNo(rowp[9] == null ? "" : String.valueOf(rowp[9]));
                    ptdBean.setArrearAmount(rowp[10] == null ? "" : String.valueOf(rowp[10]));

                    mapData.put(uid, ptdBean);
                }

                sb1.delete(0, sb1.length());
                logger.info("combinereport for propertyd end");

            } else if (reportType.equalsIgnoreCase("combineowner")) {
                logger.info("property detail start");
//                 sb1.append(" select d.property_unique_id,d.property_owner,d.owner_sex,d.property_owner_father,d.property_owner_spouse,d.property_aadhar_num,d.complete_address,d.property_old_smc_prop_tax_num,t.arrear_amount from ");
//                 sb1.append(" property_details d,property_correction_form cf,correction_status cs,property_tax t where d.property_unique_id=cf.unique_id and d.property_unique_id=t.property_unique_id and ");
//                 sb1.append(" cf.unique_id=cs.property_id and cf.application_no=cs.correction_id  and (cf.check_owner_name='Y' or cf.check_owner_father='Y' or cf.check_spouse_name='Y' or cf.check_plot_no='Y' or cf.check_house_no='Y' or cf.check_building_name='Y' or cf.check_road_name='Y' or cf.check_sublocality='Y' or cf.check_loc_name='Y' or cf.check_land_mark='Y' or cf.check_smc_holding_no='Y' or cf.checkarrear='Y') and t.financial_year='2018-2019' ");
//                 sb1.append(" group by d.property_unique_id,t.arrear_amount; ");
                sessionHB = sessionFactory.openSession();
//                 //System.out.println("sb1 ak "+sb1.toString());
                sb1.append("select * from (");
                sb1.append(" select cf.unique_id,cf.application_no,cf.ward_no,d.property_owner ,d.owner_sex,");
                sb1.append(" d.property_owner_father ,d.property_owner_spouse ,d.property_aadhar_num  ,d.complete_address ,");
                sb1.append(" d.property_old_smc_prop_tax_num ,t.arrear_amount  from property_details d,property_correction_form cf,property_tax t");
                sb1.append(" where d.property_unique_id=cf.unique_id and d.property_unique_id=t.property_unique_id  and  t.financial_year='2018-2019')");
                sb1.append(" asas left join correction_status cs on asas.unique_id=cs.property_id and asas.application_no=cs.correction_id;");

                System.out.println("fdfdsfj " + sb1.toString());

                Query query2 = sessionHB.createSQLQuery(sb1.toString());
                List<Object[]> rowsProperty = query2.list();
                for (Object[] rowp : rowsProperty) {
                    ptdBean = new CorrectionFormReport();
                    uid = rowp[0].toString();
                    ptdBean.setUniqueId(rowp[0] == null ? "" : String.valueOf(rowp[0]));
                    //ptdBean.setWardNo(row[1] == null ? "" : String.valueOf(row[1]));
                    ptdBean.setOwnerName(rowp[3] == null ? "" : String.valueOf(rowp[3]));
                    ptdBean.setOwnerSex(rowp[4] == null ? "" : String.valueOf(rowp[4]));
                    if (rowp[5] == null) {
                        ptdBean.setOwnerFather("");
                        // System.out.println(" row[6].toString() if"+row[6].toString());
                    } else {
                        ptdBean.setOwnerFather(rowp[5].toString());
                        //System.out.println(" row[6].toString() else "+row[6].toString());
                    }
                    ptdBean.setSpouseName(rowp[6] == null ? "" : String.valueOf(rowp[6]));
                    ptdBean.setOwnerAadharNo(rowp[7] == null ? "" : String.valueOf(rowp[7]));
                    ptdBean.setAddress(rowp[8] == null ? "" : String.valueOf(rowp[8]));
                    ptdBean.setSmcHoldingNo(rowp[9] == null ? "" : String.valueOf(rowp[9]));
                    ptdBean.setArrearAmount(rowp[10] == null ? "" : String.valueOf(rowp[10]));

                    mapData.put(uid, ptdBean);
                }

                sb1.delete(0, sb1.length());
                logger.info("property details end");
            }
             //System.out.println("session "+bean.getUniqueId());
            //sb.append(" select unique_id,owner_name from property_correction_form where permission_data='N'"); // ask
            //sessionHB = sessionFactory.openSession();
            //System.out.println("ddd "+sb.toString());
            // Query query1 = sessionHB.createSQLQuery(sb.toString());// ask
            // List<Object[]> rows = query1.list();// ask
            //System.out.println("hhhh "+rows.size());
            //for (Object[] row : rows) {// ak
            //System.out.println("rows[0] "+row[0]);

                //if(row[0].toString()!=null){// ak
            //uid=row[0].toString();// ak
            //System.out.println("uid "+uid);
//                   sb1.append(" select d.property_unique_id,d.property_owner,d.owner_sex,d.property_owner_father,d.property_owner_spouse,d.property_contact,"); 
//                   sb1.append(" d.property_owner_email,d.property_aadhar_num,d.property_owner_address, "); 
//                   sb1.append(" d.property_occupier_name,d.occupier_sex,d.occupier_father,d.occupier_contactno,d.occupier_email,d.occupier_aadhar_no,d.property_plot_num,d.property_house_no,");
//                   sb1.append(" d.property_building_name,d.property_road,d.property_sublocality,d.property_locality,d.property_landmark, ");
//                   sb1.append(" f.pf_electric_con_num,d.smc_survey_no,d.smc_plot_no,d.property_old_smc_prop_tax_num,t.arrear_amount from  property_details d,property_floor f,property_tax t,property_correction_form cf where d.property_unique_id=f.property_unique_id and d.property_unique_id= t.property_unique_id and  d.property_unique_id=cf.unique_id and t.financial_year='2018-2019' ");
//                   sb1.append(" and (cf.check_owner_name='Y' or cf.check_owner_father='Y' or cf.check_spouse_name='Y' or cf.check_owner_contact='Y' or");
//                   sb1.append(" cf.check_owner_email='Y' or cf.check_occupier_name='Y' or cf.check_occupier_father='Y' or cf.check_occupier_contact='Y' or ");
//                   sb1.append(" cf.check_occupier_email='Y' or cf.check_plot_no='Y' or cf.check_house_no='Y' or cf.check_building_name='Y' or cf.check_road_name='Y' or");
//                   sb1.append(" cf.check_sublocality='Y' or cf.check_loc_name='Y' or cf.check_land_mark='Y' or cf.check_electric_serice_connection_no='Y' or ");
//                   sb1.append(" cf.checkowneraadharno='Y' or cf.checkoccupieraadharno='Y' or cf.checksurveyno='Y' or cf.checkplotsmc='Y' or cf.checkarrear='Y' or");
//                   sb1.append(" cf.check_smc_holding_no='Y' or   cf.check_owner_sex='Y' or cf.check_occupier_sex='Y' or cf.check_property_owner_address='Y')");
//                   sb1.append(" group by d.property_unique_id,f.pf_electric_con_num,t.arrear_amount");
//                   
                   /*System.out.println("dddd "+sb1.toString());
             Query query2 = sessionHB.createSQLQuery(sb1.toString());
             List<Object[]> rowsProperty = query2.list();
             for (Object[] rowp : rowsProperty) {
             ptdBean = new CorrectionFormReport();
             uid=rowp[0].toString();
             ptdBean.setUniqueId(rowp[0] == null ? "" : String.valueOf(rowp[0]));
             //ptdBean.setWardNo(row[1] == null ? "" : String.valueOf(row[1]));
             ptdBean.setOwnerName(rowp[1] == null ? "" : String.valueOf(rowp[1]));
             ptdBean.setOwnerSex(rowp[2] == null ? "" : String.valueOf(rowp[2]));
             if (rowp[3] == null) {
             ptdBean.setOwnerFather("");
             // System.out.println(" row[6].toString() if"+row[6].toString());
             } else {
             ptdBean.setOwnerFather(rowp[3].toString());
             //System.out.println(" row[6].toString() else "+row[6].toString());
             }
             ptdBean.setSpouseName(rowp[4] == null ? "" : String.valueOf(rowp[4]));

             ptdBean.setOwnerContact(rowp[5] == null ? "" : String.valueOf(rowp[5]));
             ptdBean.setOwnerEmail(rowp[6] == null ? "" : String.valueOf(rowp[6]));
             ptdBean.setOwnerAadharNo(rowp[7] == null ? "" : String.valueOf(rowp[7]));
             ptdBean.setPropertyOwnerAddress(rowp[8] == null ? "" : String.valueOf(rowp[8]));
             ptdBean.setOccupierName(rowp[9] == null ? "" : String.valueOf(rowp[9]));
                        
             ptdBean.setOccupierSex(rowp[10] == null ? "" : String.valueOf(rowp[10]));
             ptdBean.setOccupierFather(rowp[11] == null ? "" : String.valueOf(rowp[11]));
             ptdBean.setOccupierContact(rowp[12] == null ? "" : String.valueOf(rowp[12]));
             ptdBean.setOccupierEmail(rowp[13] == null ? "" : String.valueOf(rowp[13]));
             ptdBean.setOccupierAadharNo(rowp[14] == null ? "" : String.valueOf(rowp[14]));
             ptdBean.setPlotNo(rowp[15] == null ? "" : String.valueOf(rowp[15]));
             ptdBean.setHoueNo(rowp[16] == null ? "" : String.valueOf(rowp[16]));
             ptdBean.setBuildingName(rowp[17] == null ? "" : String.valueOf(rowp[17]));
             ptdBean.setRoadName(rowp[18] == null ? "" : String.valueOf(rowp[18]));
             ptdBean.setSubLocality(rowp[19] == null ? "" : String.valueOf(rowp[19]));
             ptdBean.setLocName(rowp[20] == null ? "" : String.valueOf(rowp[20]));
             ptdBean.setLandMark(rowp[21] == null ? "" : String.valueOf(rowp[21]));
             ptdBean.setElectricServiceNo(rowp[22] == null ? "" : String.valueOf(rowp[22]));
             ptdBean.setSurveyNo(rowp[23] == null ? "" : String.valueOf(rowp[23]));
             ptdBean.setPlotSmc(rowp[24] == null ? "" : String.valueOf(rowp[24]));
             ptdBean.setSmcHoldingNo(rowp[25] == null ? "" : String.valueOf(rowp[25]));
             ptdBean.setArrearAmount(rowp[26] == null ? "" : String.valueOf(rowp[26]));
             mapData.put(uid, ptdBean);
             }*/
                //sb1.delete(0, sb1.length());
            //}//ak
            //}//ak
            //bean= (CorrectionFormBean)sessionHB.get(CorrectionFormBean.class, id);
            //System.out.println("session object "+sessionHB);
        } catch (Exception e) {
            e.printStackTrace();
            logger.info(e.getMessage());
        } finally {
            if (sessionHB != null) {
                sessionHB.flush();
                sessionHB.clear();
                sessionHB.close();
            }
        }
        return mapData;
    }

    public List<CorrectionFormFloorBean> getCorrectionReportFloor() {
        Session sessionHB = null;
        String msg = "";
        CorrectionFormFloorBean ptdBean = null;
        List<CorrectionFormFloorBean> ar = new ArrayList<CorrectionFormFloorBean>();
        StringBuffer sb = new StringBuffer();
        StringBuffer sb2 = new StringBuffer();
        String p_id = "";

        try {
            //System.out.println("session "+bean.getUniqueId());
            sessionHB = sessionFactory.openSession();
            HashMap hashIdCount = new HashMap();
            sb2.append(" select f.uniqueid,count(*) as id_count from property_correction_form_floor f,property_correction_form d,correction_status cs where f.uniqueid=d.unique_id and  f.application_no=d.application_no and d.unique_id=cs.property_id and d.application_no=cs.correction_id and cs.status='approve'  group by f.uniqueid    ");
            Query query3 = sessionHB.createSQLQuery(sb2.toString());
            List<Object[]> rowsCount = query3.list();
            for (Object[] row : rowsCount) {
                if (row[0] != null) {
                    String pid = row[0].toString();
                    String countId = new Integer(row[1].toString()).toString();
                    hashIdCount.put(pid, countId);
                    // System.out.println(" row[6].toString() if"+row[6].toString());
                }
            }

            sb.append(" select f.property_floor_id ,f.uniqueid,floortype,f.carpetarea,f.propertyuse,f.propertysubtype, ");
            sb.append(" f.constructiontype,f.selfrent,f.rentedvalue,f.editdata,f.deletedata,f.permission_data,f.application_no,d.ward_no,d.notice_date,d.applicant_name,cs.status   ");
            sb.append("  from property_correction_form_floor f,property_correction_form d,correction_status cs  where f.uniqueid=d.unique_id and   f.application_no=d.application_no and d.unique_id=cs.property_id and d.application_no=cs.correction_id and cs.status='approve'  order by f.uniqueid,cast(f.property_floor_id as Integer) desc");

            Query query1 = sessionHB.createSQLQuery(sb.toString());
            List<Object[]> rows = query1.list();
            String fl = "";
            String cover = "";
            String constructionType = "";
            String propertyUse = "";
            String propertySubType = "";
            String selfRent = "";
            String rentedValue = "";
            String floorId = "";
            String editData = "";
            String deleteData = "";
            String permissionData = "";
            int idCountFloor = 0;
            int idCtr = 0;
            for (Object[] row : rows) {

//                ptdBean.setUniqueId(msg);
//                ptdBean.setOwnerName(msg);
//                ptdBean.setOwnerFather(queryForTAX);
//                ptdBean.setOwnerContact(queryForTAX);
//                ptdBean.setOwnerEmail(msg);
//                ptdBean.setOccupierName(queryForTAX);
//                ptdBean.setOccupierFather(queryForTAX);
//                ptdBean.setOccupierContact(queryForTAX);
//                ptdBean.setOccupierEmail(msg);
//                ptdBean.setAddress(msg);
//                ptdBean.setElectricSericeConnectionNo(queryForTAX);
//                ptdBean.setImageFileName(msg);
                idCtr++;
                if (row[0] != null) {
                    floorId = floorId + row[0].toString() + "\n";
                }
                if (row[1] != null) {
                    p_id = row[1].toString();
                }
                if (row[2] != null) {
                    fl = fl + row[2].toString() + "\n";
                }
                if (row[3] != null) {
                    cover = cover + row[3].toString() + "\n";
                }
                if (row[4] != null) {
                    propertyUse = propertyUse + row[4].toString() + "\n";
                }
                if (row[5] != null) {
                    propertySubType = propertySubType + row[5].toString() + "\n";
                }
                if (row[6] != null) {
                    constructionType = constructionType + row[6].toString() + "\n";
                }

                if (row[7] != null) {
                    selfRent = selfRent + row[7].toString() + "\n";
                }
                if (row[8] != null) {
                    rentedValue = rentedValue + row[8].toString() + "\n";
                }
                if (row[9] != null) {
                    if (row[9].toString().equalsIgnoreCase("Y")) {
                        editData = editData + "Edit" + "\n";
                    } else if (row[9].toString().equalsIgnoreCase("new")) {
                        editData = editData + "Add new row" + "\n";
                    }
                    //editData=editData+row[9].toString()+"\n";
                }
                if (row[10] != null) {
                    if (row[10].toString().equalsIgnoreCase("Y")) {
                        deleteData = deleteData + "Delete row" + "\n";
                        editData = editData + "Delete row" + "\n";
                    }
                    //deleteData=deleteData+row[10].toString()+"\n";
                }
                if (row[11] != null) {
                    permissionData = permissionData + row[11].toString() + "\n";
                }

                //ptdBean.setPropertyFloorId(row[0] == null ? "" : String.valueOf(row[0]));
                //ptdBean.setUniqueId(row[1] == null ? "" : String.valueOf(row[1]));
                String countdata = (String) hashIdCount.get(p_id);
                idCountFloor = Integer.parseInt(countdata);

//                ptdBean.setFloorType(row[2] == null ? "" : String.valueOf(row[2]));
//                ptdBean.setCarpetArea(row[3] == null ? "" : String.valueOf(row[3]));
//                ptdBean.setPropertyUse(row[4] == null ? "" : String.valueOf(row[4]));
//                ptdBean.setPropertySubType(row[5] == null ? "" : String.valueOf(row[5]));
//
//                ptdBean.setConstructionType(row[6] == null ? "" : String.valueOf(row[6]));
//                ptdBean.setSelfRent(row[7] == null ? "" : String.valueOf(row[7]));
//                ptdBean.setRentedValue(row[8] == null ? "" : String.valueOf(row[8]));
//                ptdBean.setEditData(row[9] == null ? "" : String.valueOf(row[9]));
//
//                ptdBean.setDeleteData(row[10] == null ? "" : String.valueOf(row[10]));
//                ptdBean.setPermissionData(row[11] == null ? "" : String.valueOf(row[11]));
                //ptdBean.setDocumentType(row[12] == null ? "" : String.valueOf(row[12]));
                //ptdBean.setId(row[13] == null ? -1 : Integer.valueOf(String.valueOf(row[13])));
                if (idCtr == idCountFloor) {
                    //System.out.println(" ggg "+idCtr+" "+idCountFloor+" "+p_id);
                    ptdBean = new CorrectionFormFloorBean();
                    ptdBean.setPropertyFloorId(floorId);
                    ptdBean.setUniqueId(p_id);
                    ptdBean.setFloorType(fl);
                    ptdBean.setCarpetArea(cover);
                    ptdBean.setPropertyUse(propertyUse);

                    ptdBean.setPropertySubType(propertySubType);

                    ptdBean.setConstructionType(constructionType);
                    ptdBean.setSelfRent(selfRent);
                    ptdBean.setRentedValue(rentedValue);
                    ptdBean.setEditData(editData);

                    ptdBean.setDeleteData(deleteData);
                    ptdBean.setPermissionData(permissionData);
                    ptdBean.setApplication_no(row[12] == null ? "" : String.valueOf(row[12]));
                    ptdBean.setWardNo(row[13] == null ? "" : String.valueOf(row[13]));
                    ptdBean.setNoticeDate(row[14] == null ? "" : String.valueOf(row[14]));
                    ptdBean.setApplicantName(row[15] == null ? "" : String.valueOf(row[15]));
                    ptdBean.setStatus(row[16] == null ? "" : String.valueOf(row[16]));
                    String msgData = getRemarks(ptdBean.getUniqueId(), ptdBean.getApplication_no());
                    ptdBean.setRemarks(msgData);
                    ar.add(ptdBean);
                    fl = "";
                    cover = "";
                    constructionType = "";
                    propertyUse = "";
                    propertySubType = "";
                    selfRent = "";
                    rentedValue = "";
                    floorId = "";
                    editData = "";
                    deleteData = "";
                    permissionData = "";
                    idCtr = 0;
                }

               //System.out.println("arraylist store "+ar);
            }
//            Iterator itr=ar.iterator();
//            while(itr.hasNext()){
//             CorrectionFormFloorBean tt=   (CorrectionFormFloorBean)itr.next();
//             //System.out.println("data "+tt.getUniqueId()+" "+tt.getFloorType());
//            }
            //bean= (CorrectionFormBean)sessionHB.get(CorrectionFormBean.class, id);
            //System.out.println("session object "+sessionHB);

        } catch (Exception e) {
            e.printStackTrace();
            logger.info(e.getMessage());
        } finally {
            if (sessionHB != null) {
                sessionHB.flush();
                sessionHB.clear();
                sessionHB.close();
            }
        }
        return ar;
    }
 //Map<String,ArrayList<CorrectionFormFloorBean>>
    //public List<CorrectionFormFloorBean> getCorrectionReportFloorCombine()

    public Map<String, ArrayList<CorrectionFormFloorBean>> getCorrectionReportFloorCombine() {
        Session sessionHB = null;
        String msg = "";
        CorrectionFormFloorBean ptdBean = null;
        ArrayList<CorrectionFormFloorBean> ar = new ArrayList<CorrectionFormFloorBean>();
        HashMap<String, ArrayList<CorrectionFormFloorBean>> mapData = new HashMap<String, ArrayList<CorrectionFormFloorBean>>();
        StringBuffer sb = new StringBuffer();
        StringBuffer sb2 = new StringBuffer();
        String p_id = "";

        try {
            //System.out.println("session "+bean.getUniqueId());
            sessionHB = sessionFactory.openSession();
            HashMap hashIdCount = new HashMap();
            sb2.append(" select f.application_no,count(*) as id_count from property_correction_form_floor f,property_correction_form d,correction_status cs where f.uniqueid=d.unique_id and  f.application_no=d.application_no and d.unique_id=cs.property_id and d.application_no=cs.correction_id   group by f.application_no    ");
            Query query3 = sessionHB.createSQLQuery(sb2.toString());
            List<Object[]> rowsCount = query3.list();
            for (Object[] row : rowsCount) {
                if (row[0] != null) {
                    String pid = row[0].toString();
                    String countId = new Integer(row[1].toString()).toString();
                    hashIdCount.put(pid, countId);
                    // System.out.println(" row[6].toString() if"+row[6].toString());
                }
            }

            //System.out.println("hashIdCount "+hashIdCount.size());
            sb.append(" select f.property_floor_id ,f.uniqueid,floortype,f.carpetarea,f.propertyuse,f.propertysubtype, ");
            sb.append(" f.constructiontype,f.selfrent,f.rentedvalue,f.editdata,f.deletedata,f.permission_data,f.application_no,d.ward_no,d.notice_date,d.applicant_name,cs.status   ");
            sb.append("  from property_correction_form_floor f,property_correction_form d,correction_status cs  where f.uniqueid=d.unique_id and   f.application_no=d.application_no and d.unique_id=cs.property_id and d.application_no=cs.correction_id   order by f.application_no,cast(f.property_floor_id as Integer) desc");

            Query query1 = sessionHB.createSQLQuery(sb.toString());
            List<Object[]> rows = query1.list();
            String fl = "";
            String cover = "";
            String constructionType = "";
            String propertyUse = "";
            String propertySubType = "";
            String selfRent = "";
            String rentedValue = "";
            String floorId = "";
            String editData = "";
            String deleteData = "";
            String permissionData = "";
            String appno = "0";
            int idCountFloor = 0;
            int idCtr = 0;
            for (Object[] row : rows) {

//                ptdBean.setUniqueId(msg);
//                ptdBean.setOwnerName(msg);
//                ptdBean.setOwnerFather(queryForTAX);
//                ptdBean.setOwnerContact(queryForTAX);
//                ptdBean.setOwnerEmail(msg);
//                ptdBean.setOccupierName(queryForTAX);
//                ptdBean.setOccupierFather(queryForTAX);
//                ptdBean.setOccupierContact(queryForTAX);
//                ptdBean.setOccupierEmail(msg);
//                ptdBean.setAddress(msg);
//                ptdBean.setElectricSericeConnectionNo(queryForTAX);
//                ptdBean.setImageFileName(msg);
                idCtr++;
                if (row[0] != null) {
                    floorId = floorId + row[0].toString() + "\n";
                }
                if (row[1] != null) {
                    p_id = row[1].toString();
                }
                if (row[2] != null) {
                    fl = fl + row[2].toString() + "\n";
                }
                if (row[3] != null) {
                    cover = cover + row[3].toString() + "\n";
                }
                if (row[4] != null) {
                    propertyUse = propertyUse + row[4].toString() + "\n";
                }
                if (row[5] != null) {
                    propertySubType = propertySubType + row[5].toString() + "\n";
                }
                if (row[6] != null) {
                    constructionType = constructionType + row[6].toString() + "\n";
                }

                if (row[7] != null) {
                    selfRent = selfRent + row[7].toString() + "\n";
                }
                if (row[8] != null) {
                    rentedValue = rentedValue + row[8].toString() + "\n";
                }
                if (row[9] != null) {
                    if (row[9].toString().equalsIgnoreCase("Y")) {
                        editData = editData + "Edit" + "\n";
                    } else if (row[9].toString().equalsIgnoreCase("new")) {
                        editData = editData + "Add new row" + "\n";
                    }
                    //editData=editData+row[9].toString()+"\n";
                }
                if (row[10] != null) {
                    if (row[10].toString().equalsIgnoreCase("Y")) {
                        deleteData = deleteData + "Delete row" + "\n";
                        editData = editData + "Delete row" + "\n";
                    }
                    //deleteData=deleteData+row[10].toString()+"\n";
                }
                if (row[11] != null) {
                    permissionData = permissionData + row[11].toString() + "\n";
                }
                if (row[12] != null) {
                    appno = row[12].toString();
                }

                //ptdBean.setPropertyFloorId(row[0] == null ? "" : String.valueOf(row[0]));
                //ptdBean.setUniqueId(row[1] == null ? "" : String.valueOf(row[1]));
                //System.out.println("appno "+appno);
                String countdata = (String) hashIdCount.get(appno);
                //System.out.println("countdata "+countdata);
                idCountFloor = Integer.parseInt(countdata);
                //System.out.println("idCountFloor"+idCountFloor);

//                ptdBean.setFloorType(row[2] == null ? "" : String.valueOf(row[2]));
//                ptdBean.setCarpetArea(row[3] == null ? "" : String.valueOf(row[3]));
//                ptdBean.setPropertyUse(row[4] == null ? "" : String.valueOf(row[4]));
//                ptdBean.setPropertySubType(row[5] == null ? "" : String.valueOf(row[5]));
//
//                ptdBean.setConstructionType(row[6] == null ? "" : String.valueOf(row[6]));
//                ptdBean.setSelfRent(row[7] == null ? "" : String.valueOf(row[7]));
//                ptdBean.setRentedValue(row[8] == null ? "" : String.valueOf(row[8]));
//                ptdBean.setEditData(row[9] == null ? "" : String.valueOf(row[9]));
//
//                ptdBean.setDeleteData(row[10] == null ? "" : String.valueOf(row[10]));
//                ptdBean.setPermissionData(row[11] == null ? "" : String.valueOf(row[11]));
                //ptdBean.setDocumentType(row[12] == null ? "" : String.valueOf(row[12]));
                //ptdBean.setId(row[13] == null ? -1 : Integer.valueOf(String.valueOf(row[13])));
                //System.out.println(" ggg "+idCtr+" "+idCountFloor+" "+appno);
                if (idCtr == idCountFloor) {
                    //System.out.println(" ggg "+idCtr+" "+idCountFloor+" "+appno);
                    ptdBean = new CorrectionFormFloorBean();
                    ptdBean.setPropertyFloorId(floorId);
                    ptdBean.setUniqueId(p_id);
                    ptdBean.setFloorType(fl);
                    ptdBean.setCarpetArea(cover);
                    ptdBean.setPropertyUse(propertyUse);

                    ptdBean.setPropertySubType(propertySubType);

                    ptdBean.setConstructionType(constructionType);
                    ptdBean.setSelfRent(selfRent);
                    ptdBean.setRentedValue(rentedValue);
                    ptdBean.setEditData(editData);

                    ptdBean.setDeleteData(deleteData);
                    ptdBean.setPermissionData(permissionData);
                    ptdBean.setApplication_no(row[12] == null ? "" : String.valueOf(row[12]));
                    ptdBean.setWardNo(row[13] == null ? "" : String.valueOf(row[13]));
                    ptdBean.setNoticeDate(row[14] == null ? "" : String.valueOf(row[14]));
                    ptdBean.setApplicantName(row[15] == null ? "" : String.valueOf(row[15]));
                    ptdBean.setStatus(row[16] == null ? "" : String.valueOf(row[16]));
                    String msgData = getRemarks(ptdBean.getUniqueId(), ptdBean.getApplication_no());
                    ptdBean.setRemarks(msgData);
                    ar.add(ptdBean);
                    mapData.put(ptdBean.getApplication_no(), ar);
                    ar = new ArrayList<CorrectionFormFloorBean>();
                    fl = "";
                    cover = "";
                    constructionType = "";
                    propertyUse = "";
                    propertySubType = "";
                    selfRent = "";
                    rentedValue = "";
                    floorId = "";
                    editData = "";
                    deleteData = "";
                    permissionData = "";
                    idCtr = 0;
                    appno = "0";
                }

               //System.out.println("arraylist store "+ar);
            }
//            Iterator itr=ar.iterator();
//            while(itr.hasNext()){
//             CorrectionFormFloorBean tt=   (CorrectionFormFloorBean)itr.next();
//             //System.out.println("data "+tt.getUniqueId()+" "+tt.getFloorType());
//            }
            //bean= (CorrectionFormBean)sessionHB.get(CorrectionFormBean.class, id);
            //System.out.println("session object "+sessionHB);

        } catch (Exception e) {
            e.printStackTrace();
            logger.info(e.getMessage());
        } finally {
            if (sessionHB != null) {
                sessionHB.flush();
                sessionHB.clear();
                sessionHB.close();
            }
        }
        return mapData;
    }

    public Map<String, ArrayList<CorrectionFormFloorReport>> getCorrectionReportFromPropertyFloor() {
        Session sessionHB = null;
        String msg = "";
        String uid = "";
        String app_no = "";
        HashMap<String, ArrayList<CorrectionFormFloorReport>> mapData = new HashMap<String, ArrayList<CorrectionFormFloorReport>>();
        ArrayList<CorrectionFormFloorReport> arFloor = new ArrayList<CorrectionFormFloorReport>();
        CorrectionFormFloorReport ptdBean = null;
        StringBuffer sb = new StringBuffer();
        StringBuffer sb1 = new StringBuffer();
        String flr[] = null;
        String cover_ar[] = null;
        String buse_ar[] = null;
        String category_ar[] = null;
        String construction_ar[] = null;
        String self_ar[] = null;
        String annualRent_ar[] = null;
        String actual_AnualRent_ar[] = null;
        String anuuaRatableValue_ar[] = null;
        //ArrayList<CorrectionFormSaveBean> ar_bean=new ArrayList<CorrectionFormSaveBean>();

        try {
            //System.out.println("session "+bean.getUniqueId());
            sb.append(" select f.uniqueid,f.application_no from property_correction_form_floor f,property_correction_form d,correction_status cs where f.uniqueid=d.unique_id  and f.application_no=d.application_no  and d.unique_id=cs.property_id and d.application_no=cs.correction_id and cs.status='approve'  group by f.uniqueid,f.application_no ");
            sessionHB = sessionFactory.openSession();
            //System.out.println("ddd "+sb.toString());
            Query query1 = sessionHB.createSQLQuery(sb.toString());
            List<Object[]> rows = query1.list();
           //List<String> rows = query1.list();
            //System.out.println("hhhh "+rows.size());
            //Iterator<String> itr= rows.iterator();

            for (Object[] row : rows) {
                //System.out.println("rows[0] "+row[0]);
                if (row[0] != null) {
                    uid = row[0].toString();
                }
                if (row[1] != null) {
                    app_no = row[1].toString();
                }
                //Object obj=(Object)itr.next();
                //uid=obj.toString();
                if (uid != null) {
                    uid = uid.toString();
                    //System.out.println("uid "+uid);
                    sb1.append(" select g.property_unique_id,g.pf_floor_name,g.pf_builtup_area,g.pf_construction_type,g.pf_floorwise_build_use,g.pf_property_subtype,");
                    sb1.append(" g.pf_self_rent,g.pf_annual_rent_value,g.pf_id,m.property_owner,f.application_no from property_correction_form_floor f,property_correction_form d,property_floor g,property_details m");
                    sb1.append(" where f.uniqueid=d.unique_id and f.uniqueid=g.property_unique_id and   f.application_no=d.application_no and m.property_unique_id=g.property_unique_id and");
                    sb1.append(" (cast(f.property_floor_id as Integer)=g.pf_id  ) and g.property_unique_id='" + uid + "' and d.application_no='" + app_no + "'  order by f.uniqueid,cast(f.property_floor_id as Integer)desc  ");
                   //sb1.append(" select property_unique_id,pf_floor_name,pf_builtup_area,pf_construction_type,pf_floorwise_build_use,pf_property_subtype,"); 
                    //sb1.append(" pf_self_rent,pf_annual_rent_value,pf_id from property_floor where property_unique_id='"+uid+"'   "); 
                    //System.out.println("dddd floor "+sb1.toString());
                    Query query2 = sessionHB.createSQLQuery(sb1.toString());
                    List<Object[]> rowsProperty = query2.list();
                    int countId = query2.list().size();
                   //System.out.println("countId old "+countId +" "+uid);
                    //System.out.println("hhhhjjjjjj "+countId +" "+uid);
//                   flr=new String[rowsProperty.size()];
//                   cover_ar=new String[rowsProperty.size()];
//                   construction_ar=new String[rowsProperty.size()];
//                   buse_ar=new String[rowsProperty.size()];
//                   category_ar=new String[rowsProperty.size()];
//                   self_ar=new String[rowsProperty.size()];
//                   actual_AnualRent_ar=new String[rowsProperty.size()];
                    String pid = "";
                    String fl = "";
                    String cover = "";
                    String constructionType = "";
                    String propertyUse = "";
                    String propertySubType = "";
                    String selfRent = "";
                    String rentedValue = "";
                    String pf_id = "";
                    String owner = "";
                    String appno = "";
                    int ctr = 0;

                    for (Object[] rowp : rowsProperty) {
                        ctr++;

                       //ptdBean.setUniqueId(rowp[0] == null ? "" : String.valueOf(rowp[0]));
                        // System.out.println("rowp[1] "+String.valueOf(rowp[1])+" "+rowp[0]);
                        //ptdBean.setWardNo(row[1] == null ? "" : String.valueOf(row[1]));
                        if (rowp[0] != null) {
                            uid = rowp[0].toString();
                        }
                        if (rowp[1] != null) {
                            fl = fl + rowp[1].toString() + "\n";
                        }
                        if (rowp[2] != null) {
                            cover = cover + rowp[2].toString() + "\n";
                        }
                        if (rowp[3] != null) {
                            constructionType = constructionType + rowp[3].toString() + "\n";
                        }
                        if (rowp[4] != null) {
                            propertyUse = propertyUse + rowp[4].toString() + "\n";
                        }
                        if (rowp[5] != null) {
                            propertySubType = propertySubType + rowp[5].toString() + "\n";
                        }
                        if (rowp[6] != null) {
                            selfRent = selfRent + rowp[6].toString() + "\n";
                        }
                        if (rowp[7] != null) {
                            rentedValue = rentedValue + rowp[7].toString() + "\n";
                        }
                        if (rowp[8] != null) {
                            pf_id = pf_id + rowp[8].toString() + "\n";
                        }
                        if (rowp[9] != null) {
                            //owner=owner+rowp[9].toString()+"\n";
                            owner = rowp[9].toString();
                        }
                        if (rowp[10] != null) {
                            //owner=owner+rowp[9].toString()+"\n";
                            appno = rowp[10].toString();
                        }

//                      ptdBean.setFloorType(rowp[1] == null ? "" : String.valueOf(rowp[1]));
//                      ptdBean.setCarpetArea(rowp[2] == null ? "" : String.valueOf(rowp[2]));
//                      ptdBean.setConstructionType(rowp[3] == null ? "" : String.valueOf(rowp[3]));
//                      ptdBean.setPropertyUse(rowp[4] == null ? "" : String.valueOf(rowp[4]));
//                      ptdBean.setPropertySubType(rowp[5] == null ? "" : String.valueOf(rowp[5]));
//                      ptdBean.setSelfRent(rowp[6] == null ? "" : String.valueOf(rowp[6]));
//                      ptdBean.setRentedValue(rowp[7] == null ? "" : String.valueOf(rowp[7]));
                        if (ctr == countId) {
                            //System.out.println("ctr "+ctr+" "+countId+" "+uid);
                            ptdBean = new CorrectionFormFloorReport();
                            //System.out.println("floor "+fl + uid);
                            ptdBean.setUniqueId(uid);
                            ptdBean.setFloorType(fl);
                            ptdBean.setCarpetArea(cover);
                            ptdBean.setConstructionType(constructionType);
                            ptdBean.setPropertyUse(propertyUse);
                            ptdBean.setPropertySubType(propertySubType);
                            ptdBean.setSelfRent(selfRent);
                            ptdBean.setRentedValue(rentedValue);
                            ptdBean.setPropertyFloorId(pf_id);
                            ptdBean.setOwner(owner);
                            ptdBean.setApplication_no(app_no);
                            arFloor.add(ptdBean);
                            mapData.put(uid, arFloor);
                            //System.out.println("mapData "+uid+" "+arFloor.size()+" "+mapData.size());
                            arFloor = null;
                            arFloor = new ArrayList<CorrectionFormFloorReport>();
                            ctr = 0;
                            pid = "";
                            fl = "";
                            cover = "";
                            constructionType = "";
                            propertyUse = "";
                            propertySubType = "";
                            selfRent = "";
                            rentedValue = "";
                            pf_id = "";
                            owner = "";
                        }

                    }

                    sb1.delete(0, sb1.length());
                }

            }
            //System.out.println("mapData store "+mapData);
            //bean= (CorrectionFormBean)sessionHB.get(CorrectionFormBean.class, id);
            //System.out.println("session object "+sessionHB);

        } catch (Exception e) {
            e.printStackTrace();
            logger.info(e.getMessage());
        } finally {
            if (sessionHB != null) {
                sessionHB.flush();
                sessionHB.clear();
                sessionHB.close();
            }
        }
        return mapData;
    }

    public Map<String, ArrayList<CorrectionFormFloorReport>> getCorrectionReportFromPropertyFloorCombine() {
        Session sessionHB = null;
        String msg = "";
        String uid = "";
        String app_no = "";
        HashMap<String, ArrayList<CorrectionFormFloorReport>> mapData = new HashMap<String, ArrayList<CorrectionFormFloorReport>>();
        ArrayList<CorrectionFormFloorReport> arFloor = new ArrayList<CorrectionFormFloorReport>();
        CorrectionFormFloorReport ptdBean = null;
        StringBuffer sb = new StringBuffer();
        StringBuffer sb1 = new StringBuffer();
        String flr[] = null;
        String cover_ar[] = null;
        String buse_ar[] = null;
        String category_ar[] = null;
        String construction_ar[] = null;
        String self_ar[] = null;
        String annualRent_ar[] = null;
        String actual_AnualRent_ar[] = null;
        String anuuaRatableValue_ar[] = null;
        //ArrayList<CorrectionFormSaveBean> ar_bean=new ArrayList<CorrectionFormSaveBean>();

        try {
            //System.out.println("session "+bean.getUniqueId());
            sb.append(" select f.uniqueid,f.application_no from property_correction_form_floor f,property_correction_form d,correction_status cs where f.uniqueid=d.unique_id  and f.application_no=d.application_no  and d.unique_id=cs.property_id and d.application_no=cs.correction_id   group by f.uniqueid,f.application_no ");
            sessionHB = sessionFactory.openSession();
            //System.out.println("ddd "+sb.toString());
            Query query1 = sessionHB.createSQLQuery(sb.toString());
            List<Object[]> rows = query1.list();
           //List<String> rows = query1.list();
            //System.out.println("hhhh "+rows.size());
            //Iterator<String> itr= rows.iterator();

            for (Object[] row : rows) {
                //System.out.println("rows[0] "+row[0]);
                if (row[0] != null) {
                    uid = row[0].toString();
                }
                if (row[1] != null) {
                    app_no = row[1].toString();
                }
                //Object obj=(Object)itr.next();
                //uid=obj.toString();
                if (uid != null) {
                    uid = uid.toString();
                    //System.out.println("uid "+uid);
                    sb1.append(" select g.property_unique_id,g.pf_floor_name,g.pf_builtup_area,g.pf_construction_type,g.pf_floorwise_build_use,g.pf_property_subtype,");
                    sb1.append(" g.pf_self_rent,g.pf_annual_rent_value,g.pf_id,m.property_owner,f.application_no from property_correction_form_floor f,property_correction_form d,property_floor g,property_details m");
                    sb1.append(" where f.uniqueid=d.unique_id and f.uniqueid=g.property_unique_id and   f.application_no=d.application_no and m.property_unique_id=g.property_unique_id and");
                    sb1.append(" (cast(f.property_floor_id as Integer)=g.pf_id  ) and g.property_unique_id='" + uid + "' and d.application_no='" + app_no + "'  order by f.uniqueid,cast(f.property_floor_id as Integer)desc  ");
                   //sb1.append(" select property_unique_id,pf_floor_name,pf_builtup_area,pf_construction_type,pf_floorwise_build_use,pf_property_subtype,"); 
                    //sb1.append(" pf_self_rent,pf_annual_rent_value,pf_id from property_floor where property_unique_id='"+uid+"'   "); 
                    //System.out.println("dddd floor "+sb1.toString());
                    Query query2 = sessionHB.createSQLQuery(sb1.toString());
                    List<Object[]> rowsProperty = query2.list();
                    int countId = query2.list().size();
                   //System.out.println("countId old "+countId +" "+uid);
                    //System.out.println("hhhhjjjjjj "+countId +" "+uid);
//                   flr=new String[rowsProperty.size()];
//                   cover_ar=new String[rowsProperty.size()];
//                   construction_ar=new String[rowsProperty.size()];
//                   buse_ar=new String[rowsProperty.size()];
//                   category_ar=new String[rowsProperty.size()];
//                   self_ar=new String[rowsProperty.size()];
//                   actual_AnualRent_ar=new String[rowsProperty.size()];
                    String pid = "";
                    String fl = "";
                    String cover = "";
                    String constructionType = "";
                    String propertyUse = "";
                    String propertySubType = "";
                    String selfRent = "";
                    String rentedValue = "";
                    String pf_id = "";
                    String owner = "";
                    String appno = "";
                    int ctr = 0;

                    for (Object[] rowp : rowsProperty) {
                        ctr++;

                       //ptdBean.setUniqueId(rowp[0] == null ? "" : String.valueOf(rowp[0]));
                        // System.out.println("rowp[1] "+String.valueOf(rowp[1])+" "+rowp[0]);
                        //ptdBean.setWardNo(row[1] == null ? "" : String.valueOf(row[1]));
                        if (rowp[0] != null) {
                            uid = rowp[0].toString();
                        }
                        if (rowp[1] != null) {
                            fl = fl + rowp[1].toString() + "\n";
                        }
                        if (rowp[2] != null) {
                            cover = cover + rowp[2].toString() + "\n";
                        }
                        if (rowp[3] != null) {
                            constructionType = constructionType + rowp[3].toString() + "\n";
                        }
                        if (rowp[4] != null) {
                            propertyUse = propertyUse + rowp[4].toString() + "\n";
                        }
                        if (rowp[5] != null) {
                            propertySubType = propertySubType + rowp[5].toString() + "\n";
                        }
                        if (rowp[6] != null) {
                            selfRent = selfRent + rowp[6].toString() + "\n";
                        }
                        if (rowp[7] != null) {
                            rentedValue = rentedValue + rowp[7].toString() + "\n";
                        }
                        if (rowp[8] != null) {
                            pf_id = pf_id + rowp[8].toString() + "\n";
                        }
                        if (rowp[9] != null) {
                            //owner=owner+rowp[9].toString()+"\n";
                            owner = rowp[9].toString();
                        }
                        if (rowp[10] != null) {
                            //owner=owner+rowp[9].toString()+"\n";
                            appno = rowp[10].toString();
                        }

//                      ptdBean.setFloorType(rowp[1] == null ? "" : String.valueOf(rowp[1]));
//                      ptdBean.setCarpetArea(rowp[2] == null ? "" : String.valueOf(rowp[2]));
//                      ptdBean.setConstructionType(rowp[3] == null ? "" : String.valueOf(rowp[3]));
//                      ptdBean.setPropertyUse(rowp[4] == null ? "" : String.valueOf(rowp[4]));
//                      ptdBean.setPropertySubType(rowp[5] == null ? "" : String.valueOf(rowp[5]));
//                      ptdBean.setSelfRent(rowp[6] == null ? "" : String.valueOf(rowp[6]));
//                      ptdBean.setRentedValue(rowp[7] == null ? "" : String.valueOf(rowp[7]));
                        if (ctr == countId) {
                            //System.out.println("ctr "+ctr+" "+countId+" "+uid);
                            ptdBean = new CorrectionFormFloorReport();
                            //System.out.println("floor "+fl + uid);
                            ptdBean.setUniqueId(uid);
                            ptdBean.setFloorType(fl);
                            ptdBean.setCarpetArea(cover);
                            ptdBean.setConstructionType(constructionType);
                            ptdBean.setPropertyUse(propertyUse);
                            ptdBean.setPropertySubType(propertySubType);
                            ptdBean.setSelfRent(selfRent);
                            ptdBean.setRentedValue(rentedValue);
                            ptdBean.setPropertyFloorId(pf_id);
                            ptdBean.setOwner(owner);
                            ptdBean.setApplication_no(app_no);
                            arFloor.add(ptdBean);
                            mapData.put(ptdBean.getApplication_no(), arFloor);
                            //System.out.println("mapData "+uid+" "+arFloor.size()+" "+mapData.size());
                            arFloor = null;
                            arFloor = new ArrayList<CorrectionFormFloorReport>();
                            ctr = 0;
                            pid = "";
                            fl = "";
                            cover = "";
                            constructionType = "";
                            propertyUse = "";
                            propertySubType = "";
                            selfRent = "";
                            rentedValue = "";
                            pf_id = "";
                            owner = "";
                            appno = "";
                        }

                    }

                    sb1.delete(0, sb1.length());
                }

            }
            //System.out.println("mapData store "+mapData);
            //bean= (CorrectionFormBean)sessionHB.get(CorrectionFormBean.class, id);
            //System.out.println("session object "+sessionHB);

        } catch (Exception e) {
            e.printStackTrace();
            logger.info(e.getMessage());
        } finally {
            if (sessionHB != null) {
                sessionHB.flush();
                sessionHB.clear();
                sessionHB.close();
            }
        }
        return mapData;
    }

    public String propertySplit(String pid, String newPid, String propertyType) {
        Session sessionHB = null;
        String msg = "";
        PropertyDetails ptdBean = null;
        StringBuffer sb = new StringBuffer();
        StringBuffer sb1 = new StringBuffer();
        StringBuffer sb2 = new StringBuffer();
        String tax_no = "2017-2018/" + newPid;
        ArrayList<CorrectionFormSaveBean> ar_bean = new ArrayList<CorrectionFormSaveBean>();

        try {
            //System.out.println("session "+bean.getUniqueId());
            String msgValue = checkPropertyId(newPid);
            if (msgValue.equalsIgnoreCase("Property id not exist")) {

                sb.append(" INSERT INTO property_details(property_unique_id,zone_id,property_survey_id,property_parcel_id,property_grid_no,");
                sb.append(" property_old_smc_prop_tax_num,property_photo_id,property_camera_num,property_survey_num,property_plot_num,property_house_no, ");
                sb.append(" property_building_name,property_sublocality,property_landmark,property_road,property_city,property_pincode,property_construction_year,property_cost, ");
                sb.append(" property_contact,property_owner,property_owner_father,property_owner_spouse,property_aadhar_num,property_owner_email,property_occupier_name,property_relation_owner,");
                sb.append(" property_total_floor,property_surveyed_floor,property_plot_area,property_num_of_rooms,property_num_of_persons,property_male_18_plus,property_fem_18_plus,property_male_18_minus,");
                sb.append(" property_fem_18_minus,property_name1,property_name2 ,property_name3 ,property_name4 ,property_name5 ,property_name6,property_room_cnt_hotel ,property_room_cnt_school_clg,");
                sb.append(" property_room_cnt_hostel,property_room_cnt_hospi_nurse,property_latitude,property_longitude,status,property_locality,property_type,property_present_status,property_builtup_area, ");
                sb.append(" complete_address,ward,property_category_desc,property_split_type)");
                sb.append(" select '" + newPid + "',zone_id,property_survey_id,property_parcel_id,property_grid_no,");
                sb.append(" property_old_smc_prop_tax_num,property_photo_id,property_camera_num,property_survey_num,property_plot_num,property_house_no,");
                sb.append(" property_building_name,property_sublocality,property_landmark,property_road,property_city,property_pincode,property_construction_year,property_cost,");
                sb.append(" property_contact,property_owner,property_owner_father,property_owner_spouse,property_aadhar_num,property_owner_email,property_occupier_name,property_relation_owner,");
                sb.append(" property_total_floor,property_surveyed_floor,property_plot_area,property_num_of_rooms,property_num_of_persons,property_male_18_plus,property_fem_18_plus,property_male_18_minus,");
                sb.append("  property_fem_18_minus,property_name1,property_name2 ,property_name3 ,property_name4 ,property_name5 ,property_name6,property_room_cnt_hotel ,property_room_cnt_school_clg,");
                sb.append(" property_room_cnt_hostel,property_room_cnt_hospi_nurse,property_latitude,property_longitude,status,property_locality,property_type,property_present_status,property_builtup_area,");
                sb.append(" complete_address,ward,property_category_desc,'" + propertyType + "' from property_details where property_unique_id='" + pid + "' ;");
                sessionHB = sessionFactory.openSession();
                //System.out.println("duplicate "+sb.toString());
                Query query1 = sessionHB.createSQLQuery(sb.toString());
                query1.executeUpdate();
                sb1.append(" INSERT INTO property_floor ( property_unique_id,property_rentable_id,pf_floor_name,pf_builtup_area,pf_construction_type,");
                sb1.append(" pf_floorwise_build_use,pf_commercial_activity_name,pf_commercial_type,pf_water_pipe_con,");
                sb1.append(" pf_water_con_num,pf_sewerage_con,pf_sewerage_con_num,pf_electric_meter_num,pf_electric_con_num,");
                sb1.append(" pf_cctv_camrea,pf_fire_equipment,pf_lift_available,pf_rain_water_harvest,pf_num_of_borewells,");
                sb1.append(" pf_sanitation,pf_hording_avail,pf_mobile_tower,status,prop_class,pf_property_subtype,pf_self_rent,flooriwse_short,pf_annual_rent_value) ");
                sb1.append(" select '" + newPid + "',property_rentable_id,pf_floor_name,pf_builtup_area,pf_construction_type,");
                sb1.append(" pf_floorwise_build_use,pf_commercial_activity_name,pf_commercial_type,pf_water_pipe_con,");
                sb1.append(" pf_water_con_num,pf_sewerage_con,pf_sewerage_con_num,pf_electric_meter_num,pf_electric_con_num,");
                sb1.append(" pf_cctv_camrea,pf_fire_equipment,pf_lift_available,pf_rain_water_harvest,pf_num_of_borewells,");
                sb1.append(" pf_sanitation,pf_hording_avail,pf_mobile_tower,status,prop_class,pf_property_subtype,pf_self_rent,flooriwse_short,");
                sb1.append(" pf_annual_rent_value from property_floor where property_unique_id='" + pid + "';");
                //System.out.println("duplicate floor "+sb1.toString());
                Query query2 = sessionHB.createSQLQuery(sb1.toString());
                query2.executeUpdate();

                sb2.append(" insert into property_tax(property_tax_id,tax_no,property_unique_id,financial_year,property_tax,water_tax,conservancy_tax,water_sewerage_charge, ");
                sb2.append(" water_meter_bill_amount,arrear_amount,advance_paid_amount,rebate_amount,adjustment_amount,total_property_tax,service_tax,other_tax,grand_total,delay_payment_charges,payable_amount,duedate,notice_generated,objection_status,delay_count_in_month) ");
                sb2.append(" select nextval('property_tax_seq'), '" + tax_no + "','" + newPid + "',financial_year,property_tax,water_tax,conservancy_tax,water_sewerage_charge,");
                sb2.append("water_meter_bill_amount,'0',advance_paid_amount,rebate_amount,adjustment_amount,'0',service_tax,other_tax,'0',");
                sb2.append(" delay_payment_charges,'0',duedate,notice_generated,objection_status,delay_count_in_month");
                sb2.append(" from property_tax where property_unique_id='" + pid + "' and financial_year='2017-2018';");
                //System.out.println("duplicate tax "+sb2.toString());
                Query query3 = sessionHB.createSQLQuery(sb2.toString());
                query3.executeUpdate();
                msg = "New property id created";
            } else {
                msg = "Property id exist";
            }

            //bean= (CorrectionFormBean)sessionHB.get(CorrectionFormBean.class, id);
            //System.out.println("session object "+sessionHB);
        } catch (Exception e) {
            //e.printStackTrace();
            logger.info(e.getMessage());
        } finally {
            if (sessionHB != null) {
                sessionHB.flush();
                sessionHB.clear();
                sessionHB.close();
            }
        }
        //return ar_bean;

        return msg;
    }

    public String updateBuildingUse() {
        String str = "success";

        Session sessionHB = null;
        String msg = "";
        HashMap hashIdCount = null;
        HashSet buseSet = new HashSet();
        HashMap mapBuse = new HashMap();
        int count = 0;
        int buseCount = 0;
        String mm = "";
        StringBuffer sb = new StringBuffer();
        StringBuffer sb1 = new StringBuffer();
        StringBuffer sb2 = new StringBuffer();
        String p_id = "";

        try {
            //System.out.println("session "+bean.getUniqueId());
            sessionHB = sessionFactory.openSession();
            hashIdCount = new HashMap();

            sb.append(" select property_unique_id,count(*) as idcount from  property_floor  group by property_unique_id");
            Query query3 = sessionHB.createSQLQuery(sb.toString());
            List<Object[]> rowsCount = query3.list();
            for (Object[] row : rowsCount) {
                if (row[0] != null) {
                    String pid = row[0].toString();
                    String countId = new Integer(row[1].toString()).toString();
                    hashIdCount.put(pid, countId);
                    // System.out.println(" row[6].toString() if"+row[6].toString());
                }
            }

            //System.out.println("hashIdCount "+hashIdCount.size());
            sb1.append(" select property_unique_id,pf_floorwise_build_use from property_floor order by property_unique_id ");
            Query query1 = sessionHB.createSQLQuery(sb1.toString());
            List<Object[]> rows = query1.list();
            int idCountFloor = 0;
            int idCtr = 0;
            String uid = "";
            String id = "";
            String buse = "";
            for (Object[] row : rows) {
                count++;
                if (row[0] != null) {
                    uid = row[0].toString();
                }
                if (row[1] != null) {
                    buse = row[1].toString();
                }

                String countdata = (String) hashIdCount.get(uid);
                buseCount = Integer.parseInt(countdata);
                buseSet.add(buse);
                if (count == buseCount) {
                    mapBuse.put(uid, buseSet);
                    buseSet = new HashSet();
                    count = 0;
                    //totalArr=0;
                }

            }

            sb2.append(" select property_unique_id,count(*) as idcount from property_floor where property_unique_id!='' group  by property_unique_id ");
            Query query2 = sessionHB.createSQLQuery(sb2.toString());
            List<Object[]> rows1 = query2.list();

            for (Object[] row : rows1) {

                if (row[0] != null) {
                    id = row[0].toString();
                }
                if (mapBuse.containsKey(id)) {
                    HashSet hashSet2 = (HashSet) mapBuse.get(id);
                    int size = hashSet2.size();
                    Iterator itr = hashSet2.iterator();
                    while (itr.hasNext()) {
                        String data = (String) itr.next();
                        String data1 = data.toString();
                        if (size == 1) {
                            if (data1.equalsIgnoreCase("Residential")) {
                                mm = "Resi";
                                Query queryUpdate = sessionHB.createSQLQuery("update property_details set check_building_use='" + mm + "' where property_unique_id='" + id + "'");
                                queryUpdate.executeUpdate();
                            } else if (data1.equalsIgnoreCase("Commercial")) {
                                mm = "Coml";
                                Query queryUpdate = sessionHB.createSQLQuery("update property_details set check_building_use='" + mm + "' where property_unique_id='" + id + "'");
                                queryUpdate.executeUpdate();
                            } else if (data1.equalsIgnoreCase("Other")) {
                                mm = "Other";
                                Query queryUpdate = sessionHB.createSQLQuery("update property_details set check_building_use='" + mm + "' where property_unique_id='" + id + "'");

                            }
                        } else {
                            mm = "MixUse";
                            Query queryUpdate = sessionHB.createSQLQuery("update property_details set check_building_use='" + mm + "' where property_unique_id='" + id + "'");
                            queryUpdate.executeUpdate();
                        }
                   //System.out.println("data "+mm);

                        //cb.executeUpdate("update property_tax_duplicate set arrear_amount='"+data1+"; where property_unique_id='"+id+"'");
                    }
                }
            }

        } catch (Exception e) {
            e.printStackTrace();
            str = "faail to update building use";
            logger.info(e.getMessage());
        } finally {
            if (sessionHB != null) {
                sessionHB.flush();
                sessionHB.clear();
                sessionHB.close();
            }
        }
        //return mapData;

        return str;
    }

    public String checkPropertyId(String pid) {
        Session sessionHB = null;
        String msg = "";
        StringBuffer sb = new StringBuffer();
        sb.append("select property_unique_id,property_owner from property_details where property_unique_id='" + pid + "'");
        try {
            sessionHB = sessionFactory.openSession();
            Query query1 = sessionHB.createSQLQuery(sb.toString());
            List<Object[]> rows = query1.list();
            if (rows.size() == 0) {
                msg = "Property id not exist";
            } else {
                msg = "Property id exist";
            }
        } catch (Exception e) {
            //e.printStackTrace();
            logger.info(e.getMessage());
        } finally {
            if (sessionHB != null) {
                sessionHB.flush();
                sessionHB.clear();
                sessionHB.close();
            }
        }
        return msg;

    }

    @Override
    public List<CorrectionFormBean> getCorrectionList(String fromDate, String todate, String cmpType) {
        Session sessionHB = null;
        List<CorrectionFormBean> pftb = null;
        String sqlQuerry = "";

        try {
            sessionHB = sessionFactory.openSession();
            switch (cmpType) {
                case "Inbox":
                    sqlQuerry = "Select c.* from (select application_no, applicant_name, notice_date, unique_id, ward_no, owner_name, status, "
                            + " case when correction_id is null then 'Y' else 'N' end as isnew  from property_correction_form "
                            + " left join correction_status on correction_id = application_no where status is null or status ='read' order by to_date(notice_date,'DD-MM-YYYY')) as c where to_date(c.notice_date, 'DD-MM-YYYY') >=  '" + fromDate + "' and to_date(c.notice_date, 'DD-MM-YYYY') <=  '" + todate + "'";
                    break;

                case "Unread":
                    sqlQuerry = "Select c.* from (select application_no, applicant_name, notice_date, unique_id, ward_no, owner_name, status,"
                            + "case when correction_id is null then 'Y' else 'N' end as isnew from property_correction_form "
                            + "left join correction_status on correction_id = application_no where "
                            + "(status is null or status ='read') and to_date(notice_date, 'DD-MM-YYYY') >  now() + interval ' - 21 days' order by to_date(notice_date,'DD-MM-YYYY')) as c where to_date(c.notice_date, 'DD-MM-YYYY') >=  '" + fromDate + "' and to_date(c.notice_date, 'DD-MM-YYYY') <=  '" + todate + "'";
                    break;

                case "Solved":
                    sqlQuerry = "Select c.* from (select application_no, applicant_name, notice_date, unique_id, ward_no, owner_name, status, "
                            + " case when correction_id is null then 'Y' else 'N' end as isnew from property_correction_form "
                            + " left join correction_status on correction_id = application_no where status ='applied')  as c where to_date(c.notice_date, 'DD-MM-YYYY') >=  '" + fromDate + "' and to_date(c.notice_date, 'DD-MM-YYYY') <=  '" + todate + "'";
                    break;

                case "Rejected":
                    sqlQuerry = "Select c.* from (select application_no, applicant_name, notice_date, unique_id, ward_no, owner_name, status, "
                            + " case when correction_id is null then 'Y' else 'N' end as isnew from property_correction_form "
                            + " left join correction_status on correction_id = application_no where status ='reject') as c where to_date(c.notice_date, 'DD-MM-YYYY') >=  '" + fromDate + "' and to_date(c.notice_date, 'DD-MM-YYYY') <= '" + todate + "'";
                    break;

                case "Field Verification":
                    sqlQuerry = "Select c.* from (select application_no, applicant_name, notice_date, unique_id, ward_no, owner_name, status, "
                            + " case when correction_id is null then 'Y' else 'N' end as isnew from property_correction_form "
                            + " left join correction_status on correction_id = application_no where status ='fieldverify' order by to_date(notice_date,'DD-MM-YYYY')) as c where to_date(c.notice_date, 'DD-MM-YYYY') >=  '" + fromDate + "' and to_date(c.notice_date, 'DD-MM-YYYY') <=  '" + todate + "'";
                    break;

                case "Pending for approval":
                    sqlQuerry = "Select c.* from (select application_no, applicant_name, notice_date, unique_id, ward_no, owner_name, status, "
                            + " case when correction_id is null then 'Y' else 'N' end as isnew from property_correction_form "
                            + " left join correction_status on correction_id = application_no where status ='approve') as c where to_date(c.notice_date, 'DD-MM-YYYY') >= '" + fromDate + "' and to_date(c.notice_date, 'DD-MM-YYYY') <= '" + todate + "'";
                    break;

            }
            Query query = sessionHB.createSQLQuery(sqlQuerry);

            query.setResultTransformer(Criteria.ALIAS_TO_ENTITY_MAP);
            pftb = query.list();

        } catch (Exception ex) {
            logger.info("[CorrectionFormFloorBean.getCorrectionListAll] Exception : " + ex.getMessage());
        } finally {
            if (sessionHB != null) {
                sessionHB.flush();
                sessionHB.clear();
                sessionHB.close();
            }
        }

        return pftb;
    }

    @Override
    public Map<String, Object> getCorrectionCountFilter(String fromDate, String todate) {
        Session sessionHB = null;
        List<Map<String, String>> pftb = null;
        Map<String, Object> map = new HashMap<>();
        try {
            sessionHB = sessionFactory.openSession();
            Query query = sessionHB.createSQLQuery("with total_count as (select case count(*)  WHEN 0 THEN 0.1   ELSE count(*)  END   t_count from property_correction_form "
                    + " where to_date(notice_date, 'DD-MM-YYYY') >= '" + fromDate + "'  and to_date(notice_date, 'DD-MM-YYYY') <= '" + todate + "'),"
                    + " correction_data as (select notice_date,unique_id,application_no,status from property_correction_form "
                    + " left join correction_status on correction_id = application_no "
                    + " where to_date(notice_date, 'DD-MM-YYYY') >= '" + fromDate + "'  and to_date(notice_date, 'DD-MM-YYYY') <= '" + todate + "')"
                    + " select *, (tcount*100)/t_count as percntg from ("
                    + " select 'total' as cnttype, count(*) as tcount from correction_data union all"
                    + " select 'inbox' as cnttype, count(*) as tcount from correction_data where status is null or status ='read' union all"
                    + " select 'solve' as cnttype, count(*) as tcount from correction_data where status ='applied' union all"
                    + " select 'approve' as cnttype, count(*) as tcount from correction_data where status ='approve' union all"
                    + " select 'reject' as cnttype, count(*) as tcount from correction_data where status ='reject' union all"
                    + " select 'fieldverify' as cnttype, count(*) as tcount from correction_data where status ='fieldverify' union all "
                    + " select 'overdue' as cnttype, count(*) as tcount from correction_data where (status is null or status ='read') "
                    + " and to_date(notice_date, 'DD-MM-YYYY') >  now() + interval ' - 21 days') as aa_,total_count");

            query.setResultTransformer(Criteria.ALIAS_TO_ENTITY_MAP);
            pftb = (List<Map<String, String>>) query.list();
            for (Map m : pftb) {
                map.put(String.valueOf(m.get("cnttype")), m);
            }
        } catch (Exception ex) {
            ex.printStackTrace();

        } finally {
            if (sessionHB != null) {
                sessionHB.flush();
                sessionHB.clear();
                sessionHB.close();
            }
        }
        return map;
    }

    public List<CorrectionActionHistory> getcorrectDetails(String propertyId) {

        Session sessionHB = null;
        List<CorrectionActionHistory> correctionDetails = null;

        try {

            sessionHB = sessionFactory.openSession();
            Criteria criteria = sessionHB.createCriteria(CorrectionActionHistory.class);
            criteria.add(Restrictions.eq("property_id", propertyId));
            correctionDetails = (List<CorrectionActionHistory>) criteria.list();

        } catch (Exception ex) {
            logger.info("[CorrectionActionHistory.getcorrectDetails] Exceprion : " + ex.getMessage());
            throw ex;
        } finally {
            if (sessionHB != null) {
                sessionHB.flush();
                sessionHB.clear();
                sessionHB.close();
            }
        }

        return correctionDetails;
    }

}
