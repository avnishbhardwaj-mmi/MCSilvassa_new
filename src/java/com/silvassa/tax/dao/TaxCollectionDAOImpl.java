package com.silvassa.tax.dao;

import com.google.gson.Gson;
import com.itextpdf.text.log.SysoLogger;
import com.silvassa.bean.PrivateNoticeBean;
import com.silvassa.bean.PropertyDetailsForAssessmentList;
import com.silvassa.bean.PropertyFloorForAssessmentList;
import com.silvassa.model.BankMaster;
import com.silvassa.model.CorrectionFormBean;
import com.silvassa.model.CorrectionFormSaveBean;
import java.util.ArrayList;
import java.util.List;

import org.hibernate.Criteria;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.criterion.Restrictions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.silvassa.model.FloorWiseTAXDetails;
import com.silvassa.model.Localitymaster;
import com.silvassa.model.PaymentBean;
import com.silvassa.model.PaymentModeMaster;
import com.silvassa.model.PaymentStatus;
import com.silvassa.model.PropertyArrearReport;
import com.silvassa.model.PropertyDetails;
import com.silvassa.model.PropertyForTaxBean;
import com.silvassa.model.PropertyOwner;
import com.silvassa.model.PropertyRentable;
import com.silvassa.model.PropertyTransferCollectionBean;
import com.silvassa.model.PropertyTransferWithInstrument;
import com.silvassa.model.PropertyTransferWithOutInstrument;
import com.silvassa.model.PropertytypeMaster;
import com.silvassa.model.QrcodePaymentBean;
import com.silvassa.model.TAXCalculationBean;
import com.silvassa.model.TAXDetailBean;
import com.silvassa.model.TAXDetailBeanOldTax;
import com.silvassa.model.TaxNotices;
import com.silvassa.model.Wardmaster;
import com.silvassa.util.MMIUtil;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.time.Instant;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import java.util.Set;
import org.apache.log4j.Logger;
import org.json.simple.JSONObject;
import org.springframework.beans.factory.annotation.Qualifier;

@Component("taxCollectionDAO")
public class TaxCollectionDAOImpl implements TaxCollectionDAO {

    private SessionFactory sessionFactory;

    private static final Logger logger = Logger.getLogger(TaxCollectionDAOImpl.class);

    private static String queryForTAX = " select pf_floor_name, pf_builtup_area, property_sub_cat, property_cat, multiplication_factor, rentable_value, ((C * multiplication_factor)/100) from ("
            + " select *, (B *  12) as C from ("
            + " select *, (A - ((10 * A) / 100)) as B from ("
            + " select pf_floor_name, pf_builtup_area, property_sub_cat, property_cat, multiplication_factor, rentable_value, (CAST(pf_builtup_area AS numeric) * rentable_value) as A from property_floor flt"
            + " left join property_rentable pr on flt.property_rentable_id = pr.property_rentable_id"
            + " where flt.property_unique_id = :prop_id order by property_cat) A_level) B_level) C_level";

    @Autowired(required = true)
    @Qualifier("sessionHB")
    public void setSessionFactory(SessionFactory sessionFactory) {
        this.sessionFactory = sessionFactory;
    }

    @Override
    public PropertyForTaxBean getPropertyDetail(String propertyId) {

        Session sessionHB = null;
        PropertyForTaxBean pftb = null;

        try {
            sessionHB = sessionFactory.openSession();
            Criteria criteria = sessionHB.createCriteria(PropertyForTaxBean.class);
            criteria.add(Restrictions.eq("propertyID", propertyId));

            pftb = (PropertyForTaxBean) criteria.uniqueResult();

        } catch (Exception e) {
            logger.info(e.getMessage());
        } finally {
            sessionHB.close();
        }

        return pftb;
    }

    @Override
    @SuppressWarnings("unchecked")
    public List<Wardmaster> getWard() {

        Session sessionHB = null;
        List<Wardmaster> l = null;

        try {
            sessionHB = sessionFactory.openSession();
            Criteria criteria1 = sessionHB.createCriteria(Wardmaster.class);
            l = criteria1.list();

        } catch (Exception e) {
            logger.info(e.getMessage());
        } finally {
            sessionHB.close();
        }

        return l;
    }

    @Override
    @SuppressWarnings("unchecked")
    public List<Localitymaster> getLocality() {

        Session sessionHB = null;
        List<Localitymaster> l = null;

        try {
            sessionHB = sessionFactory.openSession();
            Criteria criteria1 = sessionHB.createCriteria(Localitymaster.class);
            l = criteria1.list();

        } catch (Exception e) {
            logger.info(e.getMessage());
        } finally {
            sessionHB.close();
        }

        return l;
    }

    @Override
    @SuppressWarnings("unchecked")
    public List<PropertytypeMaster> getPropertyType() {

        Session sessionHB = null;
        List<PropertytypeMaster> l = null;

        try {
            sessionHB = sessionFactory.openSession();
            Criteria criteria1 = sessionHB
                    .createCriteria(PropertytypeMaster.class);
            l = criteria1.list();

        } catch (Exception e) {
            logger.info(e.getMessage());
        } finally {
            sessionHB.close();
        }

        logger.info(l.size());

        return l;
    }

    @Override
    public PropertyOwner getPropertyOwnerDetail(int propertyDetailId) {

        Session sessionHB = null;
        PropertyOwner pftb = null;

        try {
            sessionHB = sessionFactory.openSession();
            Criteria criteria = sessionHB.createCriteria(PropertyOwner.class);
            criteria.add(Restrictions.eq("propertyToOwnerID", propertyDetailId));

            pftb = (PropertyOwner) criteria.uniqueResult();

        } catch (Exception e) {
            logger.info(e.getMessage());
            logger.info(e.getMessage());
        } finally {
            sessionHB.close();
        }

        return pftb;
    }

    public List<TAXCalculationBean> getTAXInDetail(String propertyId) {

        Session sessionHB = null;
        List<TAXCalculationBean> tcblist = new ArrayList<TAXCalculationBean>();
        try {

            sessionHB = sessionFactory.openSession();
            List l = sessionHB.createSQLQuery(queryForTAX).setParameter("prop_id", propertyId).list();

            for (Object ob : l) {
                Object[] objArr = (Object[]) ob;

                TAXCalculationBean tcb = new TAXCalculationBean();

                tcb.setFloor(String.valueOf(objArr[0]));
                tcb.setBuiltArea(String.valueOf(objArr[1]));
                tcb.setPropertySubCat(String.valueOf(objArr[2]));
                tcb.setPropertyCat(String.valueOf(objArr[3]));
                tcb.setmFactor(String.valueOf(objArr[4]));
                tcb.setRentableValue(String.valueOf(objArr[5]));
                tcb.setTaxAmount(String.valueOf(objArr[6]));
                tcblist.add(tcb);
            }

        } catch (Exception e) {
            logger.info(e.getMessage());
            logger.info(e.getMessage());
        } finally {
            sessionHB.close();
        }

        return tcblist;
    }

    @Override
    public List<PropertyDetails> searchPropertyForTAX(JSONObject jo) {
        Session sessionHB = null;
        Criteria criteria = null;
        List results = null;

        String zone_id = String.valueOf(jo.get("zone_id"));
        String property_id = String.valueOf(jo.get("property_id"));
        String owner_name = String.valueOf(jo.get("owner_name"));
        String occ_name = String.valueOf(jo.get("occ_name"));
        String ward = String.valueOf(jo.get("ward"));
        String locality = String.valueOf(jo.get("locality"));
        String aadhar_num = String.valueOf(jo.get("aadhar_num"));
        String propertyCategory = String.valueOf(jo.get("propertyCategory"));
        String prop_id_input = String.valueOf(jo.get("prop_id_input"));
        String phone_no = String.valueOf(jo.get("phone_no"));
        String Locality = String.valueOf(jo.get("Locality"));

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
            if (Locality != null && !Locality.isEmpty()) {
                criteria.add(Restrictions.eq("propertySublocality", Locality));
            }
            if (aadhar_num != null && !aadhar_num.isEmpty()) {
                criteria.add(Restrictions.eq("propertyAadharNum", aadhar_num));
            }
            if (propertyCategory != null && !propertyCategory.isEmpty()) {
                criteria.add(Restrictions.eq("property_category_desc", propertyCategory));
            }
            results = criteria.list();

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
    public List<PropertyDetails> searchPropertyForTAXPaymentApproval(JSONObject jo) {
        Session sessionHB = null;
        Criteria criteria = null;
        List results = null;

        String prop_id_input = String.valueOf(jo.get("prop_id_input"));
        String ward = String.valueOf(jo.get("ward"));
        String owner_name = String.valueOf(jo.get("owner_name"));
        String occ_name = String.valueOf(jo.get("occ_name"));
        String locality = String.valueOf(jo.get("locality"));
        String aadhar_num = String.valueOf(jo.get("aadhar_num"));
        String propertyCategory = String.valueOf(jo.get("propertyCategory"));
        String phone_no = String.valueOf(jo.get("phone_no"));
        String Locality = String.valueOf(jo.get("Locality"));

        try {
            sessionHB = sessionFactory.openSession();
            criteria = sessionHB.createCriteria(PropertyDetails.class);

//            if (!zone_id.equalsIgnoreCase("null") && !zone_id.equalsIgnoreCase("-1") && !ward.isEmpty()) {
//                criteria.add(Restrictions.eq("zoneId", zone_id));
//            }
//            if (!ward.equalsIgnoreCase("null") && !ward.equalsIgnoreCase("-1") && !ward.isEmpty()) {
//                criteria.add(Restrictions.eq("ward", ward));
//            }

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
            if (Locality != null && !Locality.isEmpty()) {
                criteria.add(Restrictions.eq("propertySublocality", Locality));
            }
            if (aadhar_num != null && !aadhar_num.isEmpty()) {
                criteria.add(Restrictions.eq("propertyAadharNum", aadhar_num));
            }
            if (propertyCategory != null && !propertyCategory.isEmpty()) {
                criteria.add(Restrictions.eq("property_category_desc", propertyCategory));
            }

            results = criteria.list();

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

    public List<FloorWiseTAXDetails> getFloorWiseTAXDetails(String taxNumber) {

        Session sessionHB = null;
        List<FloorWiseTAXDetails> floorWiseTAXDetails = null;
        logger.info(taxNumber);
        try {

            sessionHB = sessionFactory.openSession();
            Criteria criteria = sessionHB.createCriteria(FloorWiseTAXDetails.class);

            criteria.add(Restrictions.eq("taxNo", taxNumber));
            floorWiseTAXDetails = criteria.list();

        } catch (Exception ex) {
            logger.info("[TAXDetailBean.getFloorWiseTAXDetails] Exception : " + ex.getMessage());

        } finally {
            if (sessionHB != null) {
                sessionHB.flush();
                sessionHB.clear();
                sessionHB.close();
            }
        }

        return floorWiseTAXDetails;
    }

    public String getPaymentRefNumber() {

        Session sessionHB = null;
        String seqNo = null;
        try {

            sessionHB = sessionFactory.openSession();
            Query query = sessionHB.createSQLQuery("select fn_sl_pay_payment_refId()");
            seqNo = (String) query.uniqueResult();
            logger.info(seqNo);
        } catch (Exception ex) {
            logger.info("[TAXDetailBean.getFloorWiseTAXDetails] Exception : " + ex.getMessage());

        } finally {
            if (sessionHB != null) {
                sessionHB.flush();
                sessionHB.clear();
                sessionHB.close();
            }
        }

        return seqNo;
    }

    public String getPaymentPropertyTransferRefNumber() {

        Session sessionHB = null;
        String seqNo = null;
        try {

            sessionHB = sessionFactory.openSession();
            Query query = sessionHB.createSQLQuery("select fn_sl_pay_payment_property_transfer_refid()");
            seqNo = (String) query.uniqueResult();
            logger.info(seqNo);
        } catch (Exception ex) {
            logger.info("[TAXDetailBean.getFloorWiseTAXDetails] Exception : " + ex.getMessage());

        } finally {
            if (sessionHB != null) {
                sessionHB.flush();
                sessionHB.clear();
                sessionHB.close();
            }
        }

        return seqNo;
    }

    @Override
    public PaymentBean findPaymentEntry(String taxNo) {

        Session sessionHB = null;
        PaymentBean ipb = null;
        try {

            sessionHB = sessionFactory.openSession();
            Criteria criteria = sessionHB.createCriteria(PaymentBean.class);
            criteria.add(Restrictions.eq("taxNo", taxNo));
            ipb = (PaymentBean) criteria.uniqueResult();

        } catch (Exception ex) {
            logger.info("[TAXDetailBean.getFloorWiseTAXDetails] Exception : " + ex.getMessage());

        } finally {
            if (sessionHB != null) {
                sessionHB.flush();
                sessionHB.clear();
                sessionHB.close();
            }
        }

        return ipb;
    }

    @Override
    public PaymentBean initPayment(PaymentBean ipb) {

        Session sessionHB = null;

        try {

            sessionHB = sessionFactory.openSession();
            sessionHB.saveOrUpdate(ipb);

        } catch (Exception ex) {
            logger.info("[TAXDetailBean.getFloorWiseTAXDetails] Exception : " + ex.getMessage());

        } finally {
            if (sessionHB != null) {
                sessionHB.flush();
                sessionHB.clear();
                sessionHB.close();
            }
        }

        return ipb;
    }

    @Override
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

            tAXDetailBean.setFloorWiseTAXDetails(getFloorWiseTAXDetails(tAXDetailBean.getTaxNo()));

            TAXDetailBeanOldTax oldTax = searchOldTax(propId);
            if (oldTax != null && oldTax.getOldtaxAmount() != null && !oldTax.getOldtaxAmount().equalsIgnoreCase("")) {
                tAXDetailBean.setOldtaxAmount(oldTax.getOldtaxAmount());
            } else {
                tAXDetailBean.setOldtaxAmount("0");
            }

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

    public TAXDetailBeanOldTax searchOldTax(String propIds) {
        Criteria criteria = null;
        Session sessionHB = null;
        TAXDetailBeanOldTax pList = null;

        try {
            sessionHB = sessionFactory.openSession();

            criteria = sessionHB.createCriteria(TAXDetailBeanOldTax.class);
            criteria.add(Restrictions.eq("propertyId", propIds));
            criteria.add(Restrictions.eq("financialYear", "2017-2018"));

            pList = (TAXDetailBeanOldTax) criteria.uniqueResult();

        } catch (Exception e) {
            logger.info(e.getMessage());
            // TODO: handle exception
        } finally {
            if (sessionHB != null) {
                sessionHB.flush();
                sessionHB.close();
            }
        }

        return pList;

    }

    @Override
    public List loadtTaxNoticeData(List propIds) {

        Session sessionHB = null;
        Criteria criteria = null;
        List results = null;
        try {
            sessionHB = sessionFactory.openSession();
            criteria = sessionHB.createCriteria(TaxNotices.class);
            criteria.add(Restrictions.eq("financialYear", MMIUtil.getCurrentFinancialYear()));
            criteria.add(Restrictions.in("propertyId", propIds));
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
    public List<FloorWiseTAXDetails> loadtFloorWiseTAXDetails(String taxNo) {

        Session sessionHB = null;
        Criteria criteria = null;
        List<FloorWiseTAXDetails> results = null;
        try {
            sessionHB = sessionFactory.openSession();
            criteria = sessionHB.createCriteria(FloorWiseTAXDetails.class);
            criteria.add(Restrictions.eq("taxNo", taxNo));
            results = (List<FloorWiseTAXDetails>) criteria.list();
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
    public List<PropertyRentable> loadRentableValues() {
        Session sessionHB = null;
        Criteria criteria = null;
        List<PropertyRentable> results = null;
        try {
            sessionHB = sessionFactory.openSession();
            criteria = sessionHB.createCriteria(PropertyRentable.class);
            results = (List<PropertyRentable>) criteria.list();
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
    public List<BankMaster> getBanks() {

        Session sessionHB = null;
        List<BankMaster> bnk = null;

        try {

            sessionHB = sessionFactory.openSession();
            Criteria criteria = sessionHB.createCriteria(BankMaster.class);

            bnk = criteria.list();

        } catch (Exception ex) {
            logger.info("[CommonDaoImpl.getPropertyDetail] Exception : " + ex.getMessage());
        } finally {
            if (sessionHB != null) {
                sessionHB.flush();
                sessionHB.clear();
                sessionHB.close();
            }
        }

        return bnk;
    }

    @Override
    public List<PaymentModeMaster> getPaymentModes() {

        Session sessionHB = null;
        List<PaymentModeMaster> bnk = null;

        try {

            sessionHB = sessionFactory.openSession();
            Criteria criteria = sessionHB.createCriteria(PaymentModeMaster.class);

            bnk = criteria.list();

        } catch (Exception ex) {
            logger.info("[CommonDaoImpl.getPropertyDetail] Exception : " + ex.getMessage());
        } finally {
            if (sessionHB != null) {
                sessionHB.flush();
                sessionHB.clear();
                sessionHB.close();
            }
        }

        return bnk;
    }

    @Override
    public PaymentBean collectPayment(PaymentBean paymentBean) {

        Session sessionHB = null;

        try {

            sessionHB = sessionFactory.openSession();
            System.out.println("ifscscc" + paymentBean.getIfscCode());
            sessionHB.saveOrUpdate(paymentBean);

        } catch (Exception ex) {
            paymentBean = null;
            System.out.println("error 1 : " + ex.getMessage());
            logger.info("[TaxCollectionDAOImpl.collectPayment] Exception : " + ex.getMessage());
        } finally {
            if (sessionHB != null) {
                sessionHB.flush();
                sessionHB.clear();
                sessionHB.close();
            }
        }
        return paymentBean;

    }

    @Override
    public List<PaymentBean> getPaymentsForApproval(JSONObject jo) {

        Session sessionHB = null;
        List<PaymentBean> pftb = null;

        try {

            Set properties = (Set) jo.get("propertyList");
            sessionHB = sessionFactory.openSession();
            Criteria criteria = sessionHB.createCriteria(PaymentBean.class);
            criteria.add(Restrictions.eq("financialYear", jo.get("financialYear")));
            criteria.add(Restrictions.in("paymentMode", new Object[]{"CSH", "CHQ", "DDF", "POS_DEVICE", "BHIM_UPI" ,"NEFT_RTGS","NEFT_RTGS"}));
            criteria.add(Restrictions.eq("status", MMIUtil.PAY_TX_VERIFY));
            criteria.add(Restrictions.in("propId", properties));

            pftb = criteria.list();

        } catch (Exception e) {
            e.printStackTrace();
            logger.info(e.getMessage());
        } finally {
            sessionHB.close();
        }

        return pftb;

    }

    @Override
    public PaymentBean getPaymentDetails(JSONObject jo) {

        Session sessionHB = null;
        PaymentBean pftb = null;

        try {

            sessionHB = sessionFactory.openSession();
            Criteria criteria = sessionHB.createCriteria(PaymentBean.class);
            criteria.add(Restrictions.eq("payRefId", jo.get("payRefId")));
            pftb = (PaymentBean) criteria.uniqueResult();

        } catch (Exception e) {
            e.printStackTrace();
            logger.info(e.getMessage());
        } finally {
            sessionHB.close();
        }

        return pftb;

    }

    @Override
    public PaymentBean savePaymentWithStatus(PaymentBean paymentBean) {

        Session sessionHB = null;

        try {

            sessionHB = sessionFactory.openSession();
            sessionHB.update(paymentBean);

        } catch (Exception ex) {
            paymentBean = null;
            System.out.println("savePaymentWithStatus 1 : " + ex.getMessage());
            logger.info("[TaxCollectionDAOImpl.savePaymentWithStatus] Exception : " + ex.getMessage());
            throw ex;
        } finally {
            if (sessionHB != null) {
                sessionHB.flush();
                sessionHB.clear();
                sessionHB.close();
            }
        }
        return paymentBean;

    }

    @Override
    public void savePaymentStatus(PaymentStatus paymentStatus) {

        Session sessionHB = null;
        try {
            sessionHB = sessionFactory.openSession();
            sessionHB.saveOrUpdate(paymentStatus);
        } catch (Exception ex) {
            System.out.println("savePaymentStatus 1 : " + ex.getMessage());
            logger.info("[TaxCollectionDAOImpl.savePaymentStatus] Exception : " + ex.getMessage());
            throw ex;
        } finally {
            if (sessionHB != null) {
                sessionHB.flush();
                sessionHB.clear();
                sessionHB.close();
            }
        }
    }

    @Override
    public void saveUpdatedTAX(TAXDetailBean tAXDetailBean) {

        Session sessionHB = null;
        try {
            sessionHB = sessionFactory.openSession();
            sessionHB.update(tAXDetailBean);
        } catch (Exception ex) {

            System.out.println("saveUpdatedTAX 1 : " + ex.getMessage());
            logger.info("[TaxCollectionDAOImpl.saveUpdatedTAX] Exception : " + ex.getMessage());
            throw ex;
        } finally {
            if (sessionHB != null) {
                sessionHB.flush();
                sessionHB.clear();
                sessionHB.close();
            }
        }
    }

    @Override
    public List<PaymentBean> loadPaymentHistory(String propId) {
        Session sessionHB = null;
        Criteria criteria = null;
        List<PaymentBean> results = null;
        try {

            sessionHB = sessionFactory.openSession();
            criteria = sessionHB.createCriteria(PaymentBean.class);
            criteria.add(Restrictions.eq("status", MMIUtil.PAY_TX_COMPLETE));
            criteria.add(Restrictions.eq("propId", propId));
            results = (List<PaymentBean>) criteria.list();
        } catch (Exception e) {
            logger.info(e.getMessage());
        } finally {
            if (sessionHB != null) {
                sessionHB.close();
            }
        }

        return results;
    }

    public List<PaymentBean> printCollectionReceipt(String propId, String receiptId) {
        Session sessionHB = null;
        Criteria criteria = null;
        List<PaymentBean> results = null;
        try {

            sessionHB = sessionFactory.openSession();
            criteria = sessionHB.createCriteria(PaymentBean.class);
            //criteria.add(Restrictions.eq("status", MMIUtil.PAY_TX_COMPLETE));
            criteria.add(Restrictions.eq("propId", propId));
            criteria.add(Restrictions.eq("payRefId", receiptId));
            results = (List<PaymentBean>) criteria.list();
        } catch (Exception e) {
            logger.info(e.getMessage());
        } finally {
            if (sessionHB != null) {
                sessionHB.close();
            }
        }
        return results;
    }

    public List<PropertyDetails> getOwnerName(String propId) {
        //PropertyDetails pd=new PropertyDetails();
        Criteria criteria = null;
        List<PropertyDetails> results = null;
        Session sessionHB = null;
        try {

            sessionHB = sessionFactory.openSession();
            criteria = sessionHB.createCriteria(PropertyDetails.class);
            criteria.add(Restrictions.eq("propertyUniqueId", propId));
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

    public String savePropertyTrasferWithInstrument(PropertyTransferWithInstrument pt) {
        Session sessionHB = null;
        String msg = "";
        try {
            sessionHB = sessionFactory.openSession();
            Date date = Calendar.getInstance().getTime();
            DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd hh:mm:ss");
            String strDate = dateFormat.format(date);
            pt.setRequestDate(strDate);
            sessionHB.save(pt);
            msg = "" + pt.getId();
        } catch (Exception ex) {

            System.out.println("saveUpdatedTAX 1 : " + ex.getMessage());
            logger.info("[TaxCollectionDAOImpl.saveUpdatedTAX] Exception : " + ex.getMessage());
            throw ex;
        } finally {
            if (sessionHB != null) {
                sessionHB.flush();
                sessionHB.clear();
                sessionHB.close();
            }
        }
        return msg;
    }

    public String savePropertyTrasferWithOutInstrument(PropertyTransferWithOutInstrument pt) {
        Session sessionHB = null;
        String msg = "";
        try {
            Date date = Calendar.getInstance().getTime();
            DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd hh:mm:ss");
            String strDate = dateFormat.format(date);

            sessionHB = sessionFactory.openSession();
            pt.setRequestDate(strDate);
            sessionHB.save(pt);
            msg = "" + pt.getId();
        } catch (Exception ex) {

            System.out.println("saveUpdatedTAX 1 : " + ex.getMessage());
            logger.info("[TaxCollectionDAOImpl.saveUpdatedTAX] Exception : " + ex.getMessage());
            throw ex;
        } finally {
            if (sessionHB != null) {
                sessionHB.flush();
                sessionHB.clear();
                sessionHB.close();
            }
        }
        return msg;
    }

    public String savePropertyTrasferCollection(PropertyTransferCollectionBean ptc) {
        Session sessionHB = null;
        String msg = "";
        try {
            ptc.setPayRefId(getPaymentPropertyTransferRefNumber());
            ptc.setStatus("P");
            if (ptc.getBankName() == "-1") {
                ptc.setBankName("");
            }
            ptc.setEntryDateTime(new java.util.Date());
            ptc.setTranserType("Instrument");
            sessionHB = sessionFactory.openSession();
            sessionHB.save(ptc);
            msg = "Successfully inserted";
        } catch (Exception ex) {

            System.out.println("saveUpdatedTAX 1 : " + ex.getMessage());
            logger.info("[TaxCollectionDAOImpl.saveUpdatedTAX] Exception : " + ex.getMessage());
            throw ex;
        } finally {
            if (sessionHB != null) {
                sessionHB.flush();
                sessionHB.clear();
                sessionHB.close();
            }
        }
        return msg;
    }

    public String savePropertyTrasferCollectionWithoutInstrument(PropertyTransferCollectionBean ptc) {
        Session sessionHB = null;
        String msg = "";
        try {
            ptc.setPayRefId(getPaymentPropertyTransferRefNumber());
            ptc.setStatus("P");
            if (ptc.getBankName() == "-1") {
                ptc.setBankName("");
            }
            ptc.setEntryDateTime(new java.util.Date());
            ptc.setTranserType("WithoutInstrument");
            sessionHB = sessionFactory.openSession();
            sessionHB.save(ptc);
            msg = "Successfully inserted";
        } catch (Exception ex) {

            System.out.println("saveUpdatedTAX 1 : " + ex.getMessage());
            logger.info("[TaxCollectionDAOImpl.saveUpdatedTAX] Exception : " + ex.getMessage());
            throw ex;
        } finally {
            if (sessionHB != null) {
                sessionHB.flush();
                sessionHB.clear();
                sessionHB.close();
            }
        }
        return msg;
    }

    public Integer checkPropertyDues(String pid) {
        Session sessionHB = null;
        String uid = "";
        String dueAmt = "";
        Integer amt = null;

        try {

            sessionHB = sessionFactory.openSession();
            Query query1 = sessionHB.createSQLQuery("Select property_unique_id,payable_amount from property_tax where property_unique_id='" + pid + "' and financial_year='2018-2019'");
            //List dueAmount=   query1.list();
            List<Object[]> rows = query1.list();

            for (Object[] row : rows) {
                if (row[0] != null) {
                    pid = String.valueOf(row[0]);
                }
                if (row[1] != null) {
                    dueAmt = String.valueOf(row[1]);
                }

            }

            amt = new Integer(dueAmt);

        } catch (Exception e) {
            logger.info(e.getMessage());
            logger.info(e.getMessage());
        } finally {
            sessionHB.flush();
            sessionHB.close();
        }

        return amt;
    }

    public List<PropertyArrearReport> getArrearReport(String ward, String propertyId, String mobile, String email, String ownerid, String occ_name, String Easy_City_Code, String locality, String Locality, String src_aadhar_no, String category) {
        Session sessionHB = null;
        String uid = "";
        String pid = "";
        String tax = "0";
        String payableAmount = "0";
        HashMap<String, String> map = new HashMap<String, String>();
        StringBuffer sb = new StringBuffer();
        StringBuffer sb1 = new StringBuffer();
        List propertyDetailArr = null;

        sb.append("select d.property_unique_id,d.property_owner,d.complete_address,t.arrear_amount,d.service_date_for_bill,d.ward,d.property_contact, d.property_owner_email from property_details d,property_tax  t where d.property_unique_id=t.property_unique_id and t.financial_year='2018-2019'  ");
        sb1.append("select t.property_unique_id,t.property_tax,t.payable_amount,d.ward from property_details d,property_tax  t   where d.property_unique_id=t.property_unique_id  and  t.financial_year='2018-2019'");

        if (ward != null && ward.length() > 0) {
            sb.append("and d.ward='" + ward + "' ");
            sb1.append("and d.ward='" + ward + "' ");
        }
        if (propertyId != null && propertyId.length() > 0) {
            sb.append("and d.property_unique_id='" + propertyId + "' ");
            sb1.append("and d.property_unique_id='" + propertyId + "' ");
        }
        if (mobile != null && mobile.length() > 0) {
            sb.append("and d.property_contact='" + mobile + "' ");
            sb1.append("and d.property_contact='" + mobile + "' ");
        }
//        if (email != null && email.length() > 0) {
//            sb.append("d.ward='" + ward + "' ");
//            sb1.append("d.ward='" + ward + "' ");
//        }
        if (ownerid != null && ownerid.length() > 0) {
            sb.append("and d.property_owner='" + ownerid + "' ");
            sb1.append("and d.property_owner='" + ownerid + "' ");
        }
        if (occ_name != null && occ_name.length() > 0) {
            sb.append("and d.property_occupier_name='" + occ_name + "' ");
            sb1.append("and d.property_occupier_name='" + occ_name + "' ");
        }
        if (Easy_City_Code != null && Easy_City_Code.length() > 0) {
            sb.append("and d.city_code='" + Easy_City_Code + "' ");
            sb1.append("and d.city_code='" + Easy_City_Code + "' ");
        }
        if (locality != null && locality.length() > 0) {
            sb.append("and d.property_locality='" + locality + "' ");
            sb1.append("and d.property_locality='" + locality + "' ");
        }
        if (Locality != null && Locality.length() > 0) {
            sb.append("and d.property_sublocality='" + Locality + "' ");
            sb1.append("and d.property_sublocality='" + Locality + "' ");
        }
        if (src_aadhar_no != null && src_aadhar_no.length() > 0) {
            sb.append("and d.property_aadhar_num='" + src_aadhar_no + "' ");
            sb1.append("and d.property_aadhar_num='" + src_aadhar_no + "' ");
        }
        if (category != null && category.length() > 0) {
            sb.append("and d.property_category_desc='" + category + "' ");
            sb1.append("and d.property_category_desc='" + category + "' ");
        }


        /*    if (ward != null && ward.length() > 0 && !ward.equalsIgnoreCase("-1")) {
         sb.append("select d.property_unique_id,d.property_owner,d.complete_address,t.arrear_amount,d.service_date_for_bill,d.ward,d.property_contact, d.property_owner_email from property_details d,property_tax  t where d.property_unique_id=t.property_unique_id and d.ward='" + ward + "' and  t.financial_year='2018-2019'");
         sb1.append("select t.property_unique_id,t.property_tax,t.payable_amount,d.ward from property_details d,property_tax  t   where d.property_unique_id=t.property_unique_id and d.ward='" + ward + "' and  t.financial_year='2018-2019'");

         } else if (propertyId != null && propertyId.length() > 0) {
         sb.append("select d.property_unique_id,d.property_owner,d.complete_address,t.arrear_amount,d.service_date_for_bill,d.ward,d.property_contact, d.property_owner_email from property_details d,property_tax  t where d.property_unique_id=t.property_unique_id and d.property_unique_id='" + propertyId + "' and  t.financial_year='2018-2019'");
         sb1.append("select t.property_unique_id,t.property_tax,t.payable_amount,d.ward from property_details d,property_tax  t   where d.property_unique_id=t.property_unique_id and d.property_unique_id='" + propertyId + "' and  t.financial_year='2018-2019'");

         } else if (mobile != null && mobile.length() > 0) {
         sb.append("select d.property_unique_id,d.property_owner,d.complete_address,t.arrear_amount,d.service_date_for_bill,d.ward,d.property_contact, d.property_owner_email from property_details d,property_tax  t where d.property_unique_id=t.property_unique_id and d.property_contact='" + mobile + "' and  t.financial_year='2018-2019'");
         sb1.append("select t.property_unique_id,t.property_tax,t.payable_amount,d.ward from property_details d,property_tax  t   where d.property_unique_id=t.property_unique_id and d.property_contact='" + mobile + "' and  t.financial_year='2018-2019'");

         } else if (email != null && email.length() > 0) {
         sb.append("select d.property_unique_id,d.property_owner,d.complete_address,t.arrear_amount,d.service_date_for_bill,d.ward,d.property_contact, d.property_owner_email from property_details d,property_tax  t where d.property_unique_id=t.property_unique_id and d.property_owner_email='" + email + "' and  t.financial_year='2018-2019'");
         sb1.append("select t.property_unique_id,t.property_tax,t.payable_amount,d.ward from property_details d,property_tax  t   where d.property_unique_id=t.property_unique_id and d.property_owner_email='" + email + "' and  t.financial_year='2018-2019'");

         }*/
        //System.out.println("query1 "+sb.toString());
        //System.out.println("query2 "+sb1.toString());
        try {
            sessionHB = sessionFactory.openSession();
            Query query1 = sessionHB.createSQLQuery(sb.toString());
            Query query2 = sessionHB.createSQLQuery(sb1.toString());
            List<Object[]> rows1 = query2.list();
            for (Object[] row : rows1) {
                if (row[0] != null) {
                    uid = String.valueOf(row[0]);

                }
                if (row[1] != null) {
                    tax = String.valueOf(row[1]);
                    tax = tax.substring(0, tax.length() - 3);
                } else {
                    tax = "0";
                }
                if (row[2] != null) {
                    payableAmount = String.valueOf(row[2]);
                } else {
                    payableAmount = "0";
                }
                String combine = tax + ":" + payableAmount;
                map.put(uid, tax);
                map.put(uid, combine);

                combine = "";
                uid = "";
                tax = "0";
                payableAmount = "0";

            }

            propertyDetailArr = new ArrayList<PropertyArrearReport>();
            List<Object[]> rows = query1.list();
            //System.out.println("gggg "+rows.size());
            for (Object[] row : rows) {
                //System.out.println("row[1] "+String.valueOf(row[1]));
                PropertyArrearReport ptdBean = new PropertyArrearReport();
                ptdBean.setPropertyUniqueId(row[0] == null ? "" : String.valueOf(row[0]));
                if (row[1] != null) {
                    ptdBean.setPropertyOwner(String.valueOf(row[1]));
                    //System.out.println("if");
                } else {
                    ptdBean.setPropertyOwner("");
                    //System.out.println("else");
                }
                // ptdBean.setOwnerName(row[1] == null ? "5t6yy" : String.valueOf(row[1]));
                ptdBean.setAddress(row[2] == null ? "" : String.valueOf(row[2]));
                ptdBean.setArrearAmount(row[3] == null ? "" : String.valueOf(row[3]));
                ptdBean.setBillDate(row[4] == null ? "" : String.valueOf(row[4]));

                ptdBean.setWard(row[5] == null ? "" : String.valueOf(row[5]));
                ptdBean.setMobile(row[6] == null ? "" : String.valueOf(row[6]));
                ptdBean.setEmail(row[7] == null ? "" : String.valueOf(row[7]));
                String tax_payable = (String) map.get(ptdBean.getPropertyUniqueId());
                String pay[] = tax_payable.split(":");
                String taxVal = pay[0];
                String payableAmountVal = pay[1];
                ptdBean.setOneYrTax(taxVal);
                ptdBean.setPayableAmount(payableAmountVal);

                //System.out.println(" row[1].toString() end "+row[1].toString());
                propertyDetailArr.add(ptdBean);

            }
        } catch (Exception e) {
            //e.printStackTrace();
            logger.info(e.getMessage());

        } finally {
            if (sessionHB != null) {
                sessionHB.flush();
                sessionHB.close();
            }
        }

        return propertyDetailArr;
    }

    @Override
    public List<PrivateNoticeBean> generateDemnadAndCollection(String ward) {

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
        String ward23 = "";
        String propId = "";

        try {

            if (ward != null && !ward.equalsIgnoreCase("-1")) {
                sb.append("     select pd.property_unique_id,pd.complete_address,pd.zone_id,pd.ward,pd.property_total_floor, ");
                sb.append(" pd.property_owner,pd.property_owner_father,pd.property_owner_spouse,pd.property_relation_owner,pd.property_occupier_name,ptx.property_tax,ptx.arrear_amount,pd.property_old_smc_prop_tax_num,pd.property_contact,pd.property_owner_email,ptx.rebate_amount,ptx.payable_amount ");
                sb.append(" from property_tax ptx, property_details pd where ptx.property_unique_id=pd.property_unique_id  and pd.ward='" + ward + "' and ptx.financial_year='2018-2019'   order by pd.property_unique_id ");

                sb1.append(" select f.property_unique_id, f.pf_floor_name,f.pf_builtup_area,f.pf_construction_Type,");
                sb1.append(" f.pf_floorwise_build_use,f.prop_class,f.property_rentable_id,d.rentable_value,d.multiplication_factor_percent as multiplication_factor,(d.rentable_value*d.percentbuilduparea)annualRatableValue,");
                sb1.append(" d.percentbuilduparea,pfd.floor_tax_amount,f.pf_self_rent,f.pf_property_subtype,f.pf_electric_con_num,f.pf_annual_rent_value,pfd.floor_annual_ratable_value from property_tax ptx,property_details pd, property_floor  f,property_rentable d, property_tax_details  pfd ");
                sb1.append(" where ptx.property_unique_id=pd.property_unique_id  and pd.property_unique_id=f.property_unique_id and f.pf_id=pfd.floor_id ");
                sb1.append(" and f.property_rentable_id=d.property_rentable_id and ptx.financial_year='2018-2019'  and pd.ward='" + ward + "'   order by pd.property_unique_id,f.flooriwse_short ");

                sb2.append(" select pd.property_unique_id,count(*) as id_count from property_details pd,property_floor pf where pd.property_unique_id=pf.property_unique_id  and pd.ward='" + ward + "'   group by pd.property_unique_id  ");

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
            //System.out.println("query1 "+sb.toString());
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
                ptdBean.setRebate(row[15] == null ? "" : String.valueOf(row[15]));
                ptdBean.setPayableAmount(row[16] == null ? "" : String.valueOf(row[16]));

                //ptdBean.setOccupier_father(row[15] == null ? "" : String.valueOf(row[15]));
                //ptdBean.setOccupier_contactno(row[16] == null ? "" : String.valueOf(row[16]));
                //ptdBean.setOccupier_email(row[17] == null ? "" : String.valueOf(row[17]));
                //ptdBean.setProperetyOwnerAadhaarNo(row[18] == null ? "" : String.valueOf(row[18]));
                //ptdBean.setProperetyOccupierAadhaarNo(row[19] == null ? "" : String.valueOf(row[19]));
                //ptdBean.setPropertyOwnerCorrepondenceAddress(row[20] == null ? "" : String.valueOf(row[20]));
                //ptdBean.setOwnerSex(row[21] == null ? "" : String.valueOf(row[21]));
                //ptdBean.setOccupierSex(row[22] == null ? "" : String.valueOf(row[22]));
                //ptdBean.setPrivateNotceNo(row[23] == null ? "0" : String.valueOf(row[23]));
                //ptdBean.setServiceDate(row[24] == null ? "" : String.valueOf(row[24]));
                //ptdBean.setDueDate(row[25] == null ? "" : String.valueOf(row[25]));
                //ptdBean.setDistributionId(row[26] == null ? "" : String.valueOf(row[26]));
                //ptdBean.setHolderName(row[27] == null ? "" : String.valueOf(row[27]));
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
                // beanData.setProperetyOwnerAadhaarNo(bean.getProperetyOwnerAadhaarNo());
                //beanData.setProperetyOccupierAadhaarNo(bean.getProperetyOccupierAadhaarNo());
                //beanData.setOwnerSex(bean.getOwnerSex());
                //beanData.setPropertyOwnerCorrepondenceAddress(bean.getPropertyOwnerCorrepondenceAddress());
                // beanData.setOccupier_contactno(bean.getOccupier_contactno());
                //beanData.setOccupier_email(bean.getOccupier_email());
                //beanData.setPrivateNotceNo(bean.getPrivateNotceNo());
                //beanData.setServiceDate(bean.getServiceDate());
                //beanData.setDueDate(bean.getDueDate());
                //beanData.setDistributionId(bean.getDistributionId());
                //beanData.setHolderName(bean.getHolderName());

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
                    //System.out.println(bean.getPropertyUniqueId()+" "+stFloor+" ");
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
                beanData.setRebate(bean.getRebate());
                beanData.setPayableAmount(bean.getPayableAmount());
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

    public List<PaymentBean> getPaymentData(String ward) {
        List<PaymentBean> ptlist = null;

        Session sessionHB = null;
        Criteria criteria = null;
        StringBuffer sb = new StringBuffer();
        StringBuffer sb1 = new StringBuffer();
        StringBuffer sb2 = new StringBuffer();
        int recordCtr = 0;
        int idCount = 0;
        int idCountFloor = 0;
        String zone = "";
        String ward23 = "";
        String propId = "";

        try {

            if (ward != null && !ward.equalsIgnoreCase("-1")) {
                sb.append("  select p.propid,p.payrefid,p.amopuntpaid,date(p.entrydatetime) as dateentry,p.payment_app,d.ward from sl_payment p,property_details d where p.propid=d.property_unique_id and d.ward='" + ward + "'  order by propid ");
                sb2.append(" select p.propid,count(*) from sl_payment p,property_details d where p.propid=d.property_unique_id and d.ward='" + ward + "' group by propid  ");

            }

            String receiptNo = "";
            String receiptDate = "";
            String amountPaid = "";
            String paymentApp = "";
            String uid = "";
            String count_id = "";
            int totalPaid = 0;

            sessionHB = sessionFactory.openSession();
            Query query1 = sessionHB.createSQLQuery(sb.toString());
            Query query3 = sessionHB.createSQLQuery(sb2.toString());
            //System.out.println("query1 "+sb.toString());
            //System.out.println("query3 "+sb2.toString());

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
            //System.out.println("hashIdCount "+hashIdCount.size());
            ptlist = new ArrayList<PaymentBean>();
            List<Object[]> rows = query1.list();
            //System.out.println("gggg "+rows.size());
            for (Object[] row : rows) {
                recordCtr++;
//                System.out.println("row[0] "+row[0]);
//                System.out.println("row[1] "+row[1]);
//                System.out.println("row[2] "+row[2]);
//                System.out.println("row[3] "+row[3]);
//                System.out.println("row[4] "+row[4]);

                if (row[0] != null) {
                    uid = row[0].toString();
                }

                if (hashIdCount.containsKey(uid)) {
                    count_id = (String) hashIdCount.get(uid);
                }
                //System.out.println("count_id "+count_id+" "+uid);

                if (row[1] != null) {
                    receiptNo = receiptNo + row[1].toString() + "\n";
                }
                //System.out.println("receiptNo "+receiptNo);
                if (row[2] != null) {
                    amountPaid = amountPaid + row[2].toString() + "\n";
                    if (row[2].toString().length() == 0) {
                        totalPaid = totalPaid + 0;
                    } else {
                        totalPaid = totalPaid + Integer.parseInt(row[2].toString());
                    }
                    //totalPaid=totalPaid+Integer.parseInt(row[2].toString());
                }
                //System.out.println("amountPaid "+amountPaid);
                if (row[3] != null) {
                    receiptDate = receiptDate + row[3].toString() + "\n";

                }
                //System.out.println("receiptDate "+receiptDate);
                if (row[4] != null) {
                    paymentApp = paymentApp + row[4].toString() + "\n";
                }
                //System.out.println("paymentApp "+paymentApp);

                //System.out.println("recordCtr "+recordCtr);
//                ptdBean.setPropId(row[0] == null ? "" : String.valueOf(row[0]));
//                ptdBean.setPayRefId(row[1] == null ? "" : String.valueOf(row[1]));
//                ptdBean.setAmountPaid(row[2] == null ? "" : String.valueOf(row[2]));
//                if(row[3]!=null){
//                    Date date1=new SimpleDateFormat("yyyy-MM-dd").parse(row[3].toString());
//                     ptdBean.setEntryDateTime(date1);
//                }else{
//                  ptdBean.setEntryDateTime(null);
//                }
//                
//                ptdBean.setPaymentApp(row[4] == null ? "" : String.valueOf(row[4]));
                //System.out.println(" row[1].toString() end "+row[1].toString());
                //System.out.println("compare "+recordCtr+" "+count_id);
                if (count_id.length() > 0) {
                    if (recordCtr == Integer.parseInt(count_id)) {
                        //System.out.println("uid "+uid);
                        PaymentBean ptdBean = new PaymentBean();
                        ptdBean.setPropId(uid);
                        ptdBean.setPayRefId(receiptNo);
                        ptdBean.setAmountPaid(amountPaid);
                        ptdBean.setReceiptDate(receiptDate);
                        ptdBean.setPaymentApp(paymentApp);
                        ptdBean.setTotalPayment(new Integer(totalPaid).toString());
                        ptlist.add(ptdBean);
                        receiptNo = "";
                        receiptDate = "";
                        amountPaid = "";
                        paymentApp = "";
                        uid = "";
                        count_id = "";
                        recordCtr = 0;
                        ptdBean = null;

                    }
                }

            }

            // System.out.println("hashIdCount "+hashIdCount.size());
        } catch (Exception e) {
            //e.printStackTrace();
            logger.info(e.getMessage());

        } finally {
            if (sessionHB != null) {
                sessionHB.flush();
                sessionHB.close();
            }
        }
        return ptlist;
    }

    public List<PropertyArrearReport> getCollectionAmount(String prop_id_input, String ward, String ownerid, String occ_name, String Phone_No, String Easy_City_Code, String locality, String Locality, String src_aadhar_no, String category, String frmDate, String endDate) {
        Session sessionHB = null;
        String uid = "";
        String pid = "";
        String tax = "0";
        String payableAmount = "0";
        String bankName = "";
        HashMap<String, String> map = new HashMap<String, String>();
        StringBuffer sb = new StringBuffer();
        StringBuffer sb1 = new StringBuffer();
        List propertyDetailArr = null;
        //ast(bill_no as int

        sb.append("select p.propid,d.property_owner,d.ward,d.complete_address ,p.payrefid,p.amopuntpaid,p.paymentmode,p.bankname,p.entrydatetime,p.financial_year,p.bankbranch,p.payer_name,p.contactno,p.paymentperiod,p.status,p.cheque_dd_num from sl_payment p,property_details d where p.propid=d.property_unique_id ");
        if (!prop_id_input.equals("")) {
            sb.append("and d.property_unique_id='" + prop_id_input + "'");
        }
        if (!ward.equals("") && ward != null) {
            sb.append("and d.ward='" + ward + "'");
        }
        if (!ownerid.equals("")) {
            sb.append("and d.property_owner='" + ownerid + "'");
        }
        if (!occ_name.equals("")) {
            sb.append("and d.property_occupier_name='" + occ_name + "'");
        }
        if (!Phone_No.equals("")) {
            sb.append("and d.property_contact='" + Phone_No + "'");
        }
        if (!locality.equals("")) {
            sb.append("and d.property_locality='" + locality + "'");
        }
        if (!Locality.equals("")) {
            sb.append("and d.property_sublocality='" + Locality + "'");
        }
        if (!src_aadhar_no.equals("")) {
            sb.append("and d.property_aadhar_num='" + src_aadhar_no + "'");
        }
        if (!category.equals("")) {
            sb.append("and d.property_category_desc='" + category + "'");
        }
        if (!frmDate.equals("") && !endDate.equals("")) {
            sb.append("and to_date(TO_CHAR(p.entrydatetime,'YYYY-MM-DD'),'YYYY-MM-DD') between  to_date('" + frmDate + "','YYYY-MM-DD') and to_date('" + endDate + "','YYYY-MM-DD')");
        }
        /*
         if (ward != null && !ward.equalsIgnoreCase("-1") && ward.length() > 0) {
         //System.out.println("dao ward "+ward);
         sb.append("select p.propid,d.property_owner,d.ward,d.complete_address ,p.payrefid,p.amopuntpaid,p.paymentmode,p.bankname,p.entrydatetime,p.financial_year,p.bankbranch,p.payer_name,p.contactno,p.paymentperiod,p.status,p.cheque_dd_num from sl_payment p,property_details d where p.propid=d.property_unique_id and d.ward='" + ward + "'");
         } else if (collectDate != null && !collectDate.equalsIgnoreCase("-1") && collectDate.length() > 0) {
         if (collectDate.equalsIgnoreCase("dailywise")) {
         sb.append("select p.propid,d.property_owner,d.ward,d.complete_address ,p.payrefid,p.amopuntpaid,p.paymentmode,p.bankname,p.entrydatetime,p.financial_year,p.bankbranch,p.payer_name,p.contactno,p.paymentperiod,p.status,p.cheque_dd_num from sl_payment p,property_details d where p.propid=d.property_unique_id and TO_CHAR(p.entrydatetime,'YYYY-MM-DD') =TO_CHAR(now(),'YYYY-MM-DD')");
         } else if (collectDate.equalsIgnoreCase("monthwise")) {
         if (startDate != null && startDate.length() > 0) {
         if (endDate != null && endDate.length() > 0) {
         sb.append("select p.propid,d.property_owner,d.ward,d.complete_address ,p.payrefid,p.amopuntpaid,p.paymentmode,p.bankname,p.entrydatetime,p.financial_year,p.bankbranch,p.payer_name,p.contactno,p.paymentperiod,p.status,p.cheque_dd_num from sl_payment p,property_details d where p.propid=d.property_unique_id and to_date(TO_CHAR(p.entrydatetime,'YYYY-MM-DD'),'YYYY-MM-DD') between  to_date('" + startDate + "','YYYY-MM-DD') and to_date('" + endDate + "','YYYY-MM-DD');");
         }
         }

         }
         //System.out.println("dao date "+collectDate);

         } else if (propId != null && propId.length() > 0) {
         //System.out.println("dao propId "+propId);
         sb.append("select p.propid,d.property_owner,d.ward,d.complete_address ,p.payrefid,p.amopuntpaid,p.paymentmode,p.bankname,p.entrydatetime,p.financial_year,p.bankbranch,p.payer_name,p.contactno,p.paymentperiod,p.status,p.cheque_dd_num from sl_payment p,property_details d where p.propid=d.property_unique_id and d.property_unique_id='" + propId + "'");
         }*/
        System.out.println("query " + sb.toString());

//            if(collect!=null && collect.equalsIgnoreCase("collection")){
//                sb.append("  select a.*, b.property_tax, b.payable_amount");
//                sb.append(" from (select d.property_unique_id,d.property_owner,d.ward,d.complete_address,t.arrear_amount, sum(cast(p.amopuntpaid as int)) amopuntpaid ");
//                sb.append("  from property_details d,");
//                sb.append(" property_tax  t,");
//                sb.append(" (SELECT propid, CASE WHEN amopuntpaid~E'^\\\\d+$' THEN cast(amopuntpaid as int)  ELSE 0 END amopuntpaid FROM sl_payment) p ");
//                sb.append(" where d.property_unique_id=t.property_unique_id and d.property_unique_id=p.propid and t.financial_year='2017-2018' ");
//                sb.append(" group by d.property_unique_id,t.arrear_amount ) as a left join");
//                sb.append(" (select t.property_unique_id,t.property_tax,t.payable_amount,d.ward from property_details d,property_tax  t ");
//                sb.append(" where d.property_unique_id=t.property_unique_id and financial_year='2018-2019') as b ");
//                sb.append(" on a.property_unique_id=b.property_unique_id");
//            
//            }else if(collect!=null && collect.equalsIgnoreCase("withoutcollection")){
//                sb.append("  select a.property_unique_id, property_owner, a.ward,complete_address,  b.arrear_amount,c.amopuntpaid , b.property_tax, b.payable_amount ");
//                sb.append("  from property_details as a  ");
//                sb.append("  left join (select t.property_unique_id,t.property_tax,t.payable_amount,d.ward, t.arrear_amount from property_details d,property_tax  t");
//                sb.append(" where d.property_unique_id=t.property_unique_id and financial_year='2018-2019') as b ");
//                sb.append(" on a.property_unique_id=b.property_unique_id ");
//                sb.append(" left join sl_payment as c on a.property_unique_id=c.propid ");
//                sb.append(" where a.property_unique_id not in (select d.property_unique_id from property_details d,property_tax  t,");
//                sb.append(" (SELECT propid, CASE WHEN amopuntpaid~E'^\\\\d+$' THEN cast(amopuntpaid as int) ELSE 0 END amopuntpaid FROM sl_payment) p ");
//                sb.append(" where d.property_unique_id=t.property_unique_id and d.property_unique_id=p.propid and t.financial_year='2018-2019' ");
//                sb.append(" group by d.property_unique_id,t.arrear_amount) ");
//          
//            }
        //System.out.println("query1 "+sb.toString());
        //System.out.println("query2 "+sb1.toString());
        try {
            sessionHB = sessionFactory.openSession();
            Query query1 = sessionHB.createSQLQuery(sb.toString());
            List<BankMaster> bnk = getBanks();

            propertyDetailArr = new ArrayList<PropertyArrearReport>();
            List<Object[]> rows = query1.list();
            //System.out.println("gggg "+rows.size());
            for (Object[] row : rows) {
                //System.out.println("row[1] "+String.valueOf(row[1]));
                PropertyArrearReport ptdBean = new PropertyArrearReport();
                ptdBean.setPropertyUniqueId(row[0] == null ? "" : String.valueOf(row[0]));
                if (row[1] != null) {
                    ptdBean.setPropertyOwner(String.valueOf(row[1]));
                    //System.out.println("if");
                } else {
                    ptdBean.setPropertyOwner("");
                    //System.out.println("else");
                }

                // ptdBean.setOwnerName(row[1] == null ? "5t6yy" : String.valueOf(row[1]));
                ptdBean.setWard(row[2] == null ? "" : String.valueOf(row[2]));
                ptdBean.setAddress(row[3] == null ? "" : String.valueOf(row[3]));
                ptdBean.setPayrefid(row[4] == null ? "" : String.valueOf(row[4]));
                ptdBean.setPaidAmout(row[5] == null ? "0" : String.valueOf(row[5]));
                ptdBean.setPaymentMode(row[6] == null ? "" : String.valueOf(row[6]));
                ptdBean.setBankName(row[7] == null ? "" : String.valueOf(row[7]));
                Iterator itrBank = bnk.iterator();
                while (itrBank.hasNext()) {
                    BankMaster bkn = (BankMaster) itrBank.next();
                    String bankId = bkn.getBankId();

                    if (bankId.equalsIgnoreCase(ptdBean.getBankName())) {
                        bankName = bkn.getBankName();
                        break;
                    }
                }
                ptdBean.setBankName(bankName);

                ptdBean.setCollectionDate(row[8] == null ? "" : String.valueOf(row[8]));
                ptdBean.setFinancialyear(row[9] == null ? "" : String.valueOf(row[9]));
                ptdBean.setBranchName(row[10] == null ? "" : String.valueOf(row[10]));
                ptdBean.setPayerName(row[11] == null ? "" : String.valueOf(row[11]));
                ptdBean.setContactNo(row[12] == null ? "" : String.valueOf(row[12]));
                ptdBean.setPaymentPeriod(row[13] == null ? "" : String.valueOf(row[13]));
                ptdBean.setPaymentStatus(row[14] == null ? "" : String.valueOf(row[14]));
                ptdBean.setCheque_dd_num(row[15] == null ? "" : String.valueOf(row[15]));

                //ptdBean.setArrearAmount(row[4] == null ? "0" : String.valueOf(row[4]));
                //ptdBean.setOneYrTax(row[6] == null ? "0" : String.valueOf(row[6]));
                //ptdBean.setPayableAmount(row[7] == null ? "0" : String.valueOf(row[7]));
                //System.out.println(" row[1].toString() end "+row[1].toString());
                //int pp=Integer.parseInt(ptdBean.getPaidAmout());
                //int dd=Integer.parseInt(ptdBean.getPayableAmount());
                //String finalBalace=new Integer(dd-pp).toString();
                //ptdBean.setFinalBalance(finalBalace);
                propertyDetailArr.add(ptdBean);
                bankName = "";
                //pp=0;
                //dd=0;
                //finalBalace="0";

            }
        } catch (Exception e) {
            //e.printStackTrace();
            logger.info(e.getMessage());

        } finally {
            if (sessionHB != null) {
                sessionHB.flush();
                sessionHB.close();
            }
        }

        return propertyDetailArr;
    }

    public PaymentBean paymentInitDetails(String refNo) {

        Session sessionHB = null;
        PaymentBean ipb = null;
        try {

            sessionHB = sessionFactory.openSession();
            Criteria criteria = sessionHB.createCriteria(PaymentBean.class);
            criteria.add(Restrictions.eq("payRefId", refNo));
            ipb = (PaymentBean) criteria.uniqueResult();

        } catch (Exception ex) {
            logger.info("[TaxCollectionDAOImpl.paymentInitDetails] Exception : " + ex.getMessage());
            throw ex;
        } finally {
            if (sessionHB != null) {
                sessionHB.flush();
                sessionHB.clear();
                sessionHB.close();
            }
        }

        return ipb;
    }

    public PropertyDetails propertyDetails(String propId) {

        Session sessionHB = null;
        PropertyDetails ipb = null;
        try {

            sessionHB = sessionFactory.openSession();
            Criteria criteria = sessionHB.createCriteria(PropertyDetails.class);
            criteria.add(Restrictions.eq("propertyUniqueId", propId));
            ipb = (PropertyDetails) criteria.uniqueResult();

        } catch (Exception ex) {
            logger.info("[TaxCollectionDAOImpl.propertyDetails] Exception : " + ex.getMessage());
            throw ex;
        } finally {
            if (sessionHB != null) {
                sessionHB.flush();
                sessionHB.clear();
                sessionHB.close();
            }
        }

        return ipb;
    }
    
    public String addqrcodeData(QrcodePaymentBean bean) {
        Session sessionHB = null;
        String msg = "";

        try {
            sessionHB = sessionFactory.openSession();
            sessionHB.save(bean);
            msg = "Successfully submission";
        } catch (Exception e) {
            msg = "error in correction form";
            e.printStackTrace();
            logger.info("[TaxCollectionDAOImpl.propertyDetails] "+e.getMessage() );
        } finally {
            if (sessionHB != null) {
                sessionHB.flush();
                sessionHB.clear();
                sessionHB.close();
            }
        }
        return msg;
    }

//         public String getPaymentRefNumber() {
//
//        Session sessionHB = null;
//        String seqNo = null;
//        try {
//
//            sessionHB = sessionFactory.openSession();
//            Query query = sessionHB.createSQLQuery("select fn_sl_pay_payment_refId()");
//            seqNo = (String) query.uniqueResult();
//            logger.info(seqNo);
//        } catch (Exception ex) {
//            logger.info("[TAXDetailBean.getFloorWiseTAXDetails] Exception : " + ex.getMessage());
//
//        } finally {
//            if (sessionHB != null) {
//                sessionHB.flush();
//                sessionHB.clear();
//                sessionHB.close();
//            }
//        }
//
//        return seqNo;
//    }
    
    public  String getTransactionId(){
     String transactionId ="";
     Session sessionHb= null;
     
     try{
      sessionHb =  sessionFactory.openSession();
      Query query = sessionHb.createSQLQuery("Select fn_sl_qrcode_payment_tid()");
      transactionId = (String) query.uniqueResult();
         logger.info("Transaction Id"+transactionId);
     }catch(Exception ex)
     {
         logger.info("[TaxCollectionDAOImpl.getTransactionId]"+ ex);
     }finally{
         sessionHb.flush();
         sessionHb.clear();
         sessionHb.close();  
     }
     return transactionId;       
    }
}
