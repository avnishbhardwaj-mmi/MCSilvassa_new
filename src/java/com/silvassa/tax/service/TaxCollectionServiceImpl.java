package com.silvassa.tax.service;

import com.silvassa.bean.PrivateNoticeBean;
import com.silvassa.model.BankMaster;
import com.silvassa.model.CorrectionFormSaveBean;
import com.silvassa.model.FloorWiseTAXDetails;
import com.silvassa.service.SilvassaService;
import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Component;

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
import com.silvassa.model.TAXCalculationBean;
import com.silvassa.model.TAXDetailBean;
import com.silvassa.model.Wardmaster;
import com.silvassa.tax.dao.TaxCollectionDAO;
import com.silvassa.util.MMIUtil;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.Map;
import java.util.Set;
import org.apache.log4j.Logger;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;

@Component("taxCollectionService")
public class TaxCollectionServiceImpl implements TaxCollectionService {

    @Autowired
    @Qualifier("taxCollectionDAO")
    TaxCollectionDAO taxCollection;

    @Autowired
    private SilvassaService silvassaService;

    private static final Logger logger = Logger.getLogger(TaxCollectionServiceImpl.class);

    @Autowired
    MMIUtil mMIUtil;

    @Override
    public PropertyForTaxBean getPropertyDetail(String propertyId) {
        PropertyForTaxBean pftb = taxCollection.getPropertyDetail(propertyId);
        if (pftb != null) {
            PropertyOwner po = taxCollection.getPropertyOwnerDetail(pftb.getPropertyToOwnerID());
            if (po != null) {
                pftb.setOwnerName(po.getPropOwnerSurname() + " " + po.getPropOwnerName());
            }
        }
        return pftb;
    }

    @Override
    public List<Wardmaster> getWard() {
        return taxCollection.getWard();
    }

    @Override
    public List<Localitymaster> getLocality() {
        return taxCollection.getLocality();
    }

    @Override
    public List<PropertytypeMaster> getPropertyType() {
        return taxCollection.getPropertyType();
    }

    @Override
    public List<TAXCalculationBean> getTAXInDetail(String propertyId) {
        return taxCollection.getTAXInDetail(propertyId);
    }

    @Override
    public JSONObject searchPropertyForTAX(JSONObject jo) {
        JSONObject jObj = new JSONObject();

        List lsProp = taxCollection.searchPropertyForTAX(jo);
        Set lsPropIds = null;
        if (lsProp.size() > 0) {
            jObj.put("propBeans", lsProp);
            lsPropIds = new HashSet();
            for (Object obj : lsProp) {
                PropertyDetails pd = (PropertyDetails) obj;
                lsPropIds.add(String.valueOf(pd.getPropertyUniqueId()));
            }
            jObj.put("taxBeans", silvassaService.searchTAXAmount(lsPropIds, MMIUtil.getCurrentFinancialYear()));
        } else {
            jObj.put("propBeans", null);
            jObj.put("taxBeans", null);
        }

        return jObj;
    }

    @Override
    public TAXDetailBean getTAXDetails(String propId, String financialYear, String noticeGenerated) {
        TAXDetailBean tAXDetailBean = taxCollection.getTAXDetails(propId, financialYear, noticeGenerated);
        return tAXDetailBean;
    }

    @Override
    public String getPaymentRefNumber() {
        return taxCollection.getPaymentRefNumber();
    }

    @SuppressWarnings("static-access")
    @Override
    public PaymentBean initPayment(String propId) {
        PaymentBean ipb = null;

        try {

            TAXDetailBean tAXDetailBean = getTAXDetails(propId, MMIUtil.getCurrentFinancialYear(), "Y");

            ipb = taxCollection.findPaymentEntry(tAXDetailBean.getTaxNo());

            if (ipb == null) {
                ipb = new PaymentBean();
                ipb.setPayRefId(getPaymentRefNumber());
            }

            ipb.setPropId(tAXDetailBean.getPropertyId());
            ipb.setTaxNo(tAXDetailBean.getTaxNo());

            ipb.setAmountDemand(tAXDetailBean.getPayableAmount());
            ipb.setAmountPaid("");
            ipb.setPendingAmount("");

            ipb.setLatePaymentStatus("N");
            ipb.setLatePaymentCharge("0");
            if (mMIUtil.isLatePayment(String.valueOf(tAXDetailBean.getDueDate()))) {
                ipb.setLatePaymentStatus("Y");
                ipb.setLatePaymentCharge(getLatePaymentCharge(tAXDetailBean.getDueDate()));
            }

            ipb.setArrearStatus("N");
            ipb.setArrear("0");
            if (mMIUtil.isArrearApply(tAXDetailBean.getArrearAmount())) {
                ipb.setArrearStatus("Y");
                ipb.setArrear(tAXDetailBean.getArrearAmount());
            }

            ipb.setRebateStatus("N");
            ipb.setRebateOnlinePayment("0");
            ipb.setRebateOther("0");

            if (mMIUtil.isRebateApply(tAXDetailBean.getRebateAmount())) {
                ipb.setRebateStatus("Y");
                ipb.setRebateOther(tAXDetailBean.getRebateAmount());
            }

            ipb.setPartialPaymentStatus("N");
            ipb.setPartialCollectedAmount("0");
            if (mMIUtil.isPartialAmountPaid(tAXDetailBean.getAdvancePaidAmount())) {
                ipb.setPartialPaymentStatus("Y");
                ipb.setPartialCollectedAmount(tAXDetailBean.getAdvancePaidAmount());
            }

            ipb.setStatus(mMIUtil.PAY_TX_INIT);
            ipb.setBankName("");
            ipb.setBankStatus("");
            ipb.setBankRefId("");
            ipb.setPaymentMode("");
            ipb.setRemarks("");
            ipb.setDueDate(tAXDetailBean.getDueDate());
            ipb.setBillDate(tAXDetailBean.getGeneratedOn());
            ipb.setFloorWiseTAXDetails(tAXDetailBean.getFloorWiseTAXDetails());
            taxCollection.initPayment(ipb);

        } catch (Exception ex) {
            ipb = null;

            logger.info("[TaxCollectionServiceImpl.initPayment] Exception : " + ex.getMessage());
        } finally {

        }

        return ipb;
    }

    public String getLatePaymentCharge(Date dueDate) {
        return "1";
    }

    public String getOnlinePaymentRebate(String propId) {
        return "1";
    }

    public String getOtherRebate(String propId) {
        return "1";
    }

    @Override
    public List<FloorWiseTAXDetails> loadtFloorWiseTAXDetails(String taxNo) {
        return taxCollection.loadtFloorWiseTAXDetails(taxNo);
    }

    @Override
    public List<PropertyRentable> loadRentableValues() {
        return taxCollection.loadRentableValues();
    }

    @Override
    public List<BankMaster> getBanks() {

        return taxCollection.getBanks();
    }

    @Override
    public List<PaymentModeMaster> getPaymentModes() {
        return taxCollection.getPaymentModes();
    }

    @Override
    public PaymentBean collectPayment(PaymentBean paymentBean) {

        PaymentBean ipb = null;

        try {

            TAXDetailBean tAXDetailBean = getTAXDetails(paymentBean.getPropId(), MMIUtil.getCurrentFinancialYear(), "Y");

            ipb = new PaymentBean();
            ipb.setPayRefId(getPaymentRefNumber());
            ipb.setFinancialYear(tAXDetailBean.getFinancialYear());
            ipb.setPropId(tAXDetailBean.getPropertyId());
            ipb.setTaxNo(tAXDetailBean.getTaxNo());
            ipb.setPaymentApp("Desk");
            ipb.setAmountDemand(tAXDetailBean.getGrandTotal());
            ipb.setAmountPaid(paymentBean.getAmountPaid());

            ipb.setLatePaymentStatus("N");
            ipb.setLatePaymentCharge("0");
            if (mMIUtil.isLatePayment(mMIUtil.formateDate(tAXDetailBean.getDueDate()))) {
                ipb.setLatePaymentStatus("Y");
                ipb.setLatePaymentCharge(getLatePaymentCharge(tAXDetailBean.getDueDate()));
            }

            ipb.setArrearStatus("N");
            ipb.setArrear("0");
            if (mMIUtil.isArrearApply(tAXDetailBean.getArrearAmount())) {
                ipb.setArrearStatus("Y");
                ipb.setArrear(tAXDetailBean.getArrearAmount());
            }

            ipb.setRebateStatus("N");
            ipb.setRebateOnlinePayment("0");
            ipb.setRebateOther("0");

            ipb.setPartialPaymentStatus("N");
            ipb.setPartialCollectedAmount("0");

            ipb.setStatus(mMIUtil.PAY_TX_VERIFY);
            ipb.setBankName(paymentBean.getBankName());
            ipb.setBankBranch(paymentBean.getBankBranch());
            ipb.setChequeDDDate(paymentBean.getChequeDDDate());
            ipb.setChequeDDNum(paymentBean.getChequeDDNum());
            ipb.setBankStatus("");
            ipb.setBankRefId("");
            ipb.setPaymentMode(paymentBean.getPaymentMode());
            ipb.setRemarks(paymentBean.getRemarks());
            ipb.setBillDate(tAXDetailBean.getGeneratedOn());
            ipb.setDueDate(tAXDetailBean.getDueDate());
            ipb.setEntryDateTime(mMIUtil.getCurrentTimestamp());
            ipb.setPayerName(paymentBean.getPayerName());
            ipb.setContactNo(paymentBean.getContactNo());
            ipb.setPaymentPeriod(paymentBean.getPaymentPeriod());
            ipb.setIfscCode(paymentBean.getIfscCode());
            taxCollection.collectPayment(ipb);

        } catch (Exception ex) {
            ipb = null;
            ex.printStackTrace();
            System.out.println("error : " + ex.getMessage());
            logger.info("[TaxCollectionServiceImpl.initPayment] Exception : " + ex.getMessage());
        } finally {

        }

        return ipb;
    }

    public List<PaymentBean> printCollectionReceipt(String propId, String receiptId) {

        List<PaymentBean> listBean = null;
        try {

            PaymentBean ipb = null;
            listBean = taxCollection.printCollectionReceipt(propId, receiptId);

        } catch (Exception ex) {
            ex.printStackTrace();
            System.out.println("error : " + ex.getMessage());
            logger.info("[TaxCollectionServiceImpl.initPayment] Exception : " + ex.getMessage());
        } finally {

        }
        return listBean;
    }

    public List<PropertyDetails> getOwnerName(String propId) {
        List<PropertyDetails> listBean = null;
        try {

            PropertyDetails ipb = null;
            listBean = taxCollection.getOwnerName(propId);

        } catch (Exception ex) {
            ex.printStackTrace();
            System.out.println("error : " + ex.getMessage());
            logger.info("[TaxCollectionServiceImpl.initPayment] Exception : " + ex.getMessage());
        } finally {

        }
        return listBean;
    }

    @Override
    public JSONObject getPaymentsForApproval(String param) {

        JSONParser jp = new JSONParser();
        JSONObject response = new JSONObject();
        List<PaymentBean> ls = null;
        Map<String, Object> propMap = new HashMap();
        try {
            JSONObject jo = (JSONObject) jp.parse(param);
            jo.put("financialYear", mMIUtil.getCurrentFinancialYear());

            List lsProp = taxCollection.searchPropertyForTAXPaymentApproval(jo);

            Set lsPropIds = null;
            if (lsProp.size() > 0) {
                lsPropIds = new HashSet();
                for (Object obj : lsProp) {
                    PropertyDetails pd = (PropertyDetails) obj;
                    lsPropIds.add(String.valueOf(pd.getPropertyUniqueId()));
                    propMap.put(pd.getPropertyUniqueId(), pd);
                }

                jo.put("propertyList", lsPropIds);
                System.out.println("lsPropIds : " + lsPropIds.size());
                ls = taxCollection.getPaymentsForApproval(jo);

                List filteredProp = null;
                if (ls != null) {
                    System.out.println("lsPropIds : " + ls.size());
                    filteredProp = new ArrayList();
                    for (PaymentBean paymentBean : ls) {
                        filteredProp.add(propMap.get(paymentBean.getPropId()));
                    }
                }
                response.put("propertyList", filteredProp);
                response.put("paymentBeanList", ls);

            } else {
                response.put("propertyList", new LinkedList());
                response.put("paymentBeanList", new LinkedList());
            }

            response.put("code", 200);
            response.put("msg", "Success");

        } catch (Exception ex) {

            response.put("code", 500);
            response.put("msg", "Error");
            response.put("data", null);

            ex.printStackTrace();
            System.out.println("error : " + ex.getMessage());
            logger.info("[TaxCollectionServiceImpl.getPaymentsForApproval] Exception : " + ex.getMessage());
        }

        return response;
    }

    @Override
    public void approvePayment(PaymentStatus paymentStatus) {

        JSONObject jSONObject = new JSONObject();
        jSONObject.put("payRefId", paymentStatus.getPayrefid());

        PaymentBean paymentBean = taxCollection.getPaymentDetails(jSONObject);

        paymentStatus.setExistingStatus(paymentBean.getStatus());
        paymentStatus.setIsComplete("N");
        taxCollection.savePaymentStatus(paymentStatus);

        TAXDetailBean tAXDetailBean = getTAXDetails(paymentBean.getPropId(), paymentBean.getFinancialYear(), "Y");

        // Additional charge included
        paymentBean.setLatePaymentCharge(tAXDetailBean.getDelayPaymentCharges());
        paymentBean.setStatus(mMIUtil.PAY_TX_COMPLETE);
        taxCollection.savePaymentWithStatus(paymentBean);

        // Amount update
//        Double payable = Double.parseDouble(tAXDetailBean.getPayableAmount());
//        Double paid = Double.parseDouble(paymentBean.getAmountPaid());
//        Double remaining = payable - paid;
//        
//        Double advence_paid = Double.parseDouble(tAXDetailBean.getAdvancePaidAmount());
//        advence_paid = advence_paid + paid;
//        tAXDetailBean.setPayableAmount(MMIUtil.formateAmount(remaining)); 
//        tAXDetailBean.setAdvancePaidAmount(MMIUtil.formateAmount(advence_paid));
//        taxCollection.saveUpdatedTAX(tAXDetailBean); 
// Amount update -Start
        Double advence_paid = Double.parseDouble(tAXDetailBean.getAdvancePaidAmount());
        Double arrear = Double.parseDouble(tAXDetailBean.getArrearAmount());
        Double interest = Double.parseDouble(tAXDetailBean.getDelayPaymentCharges());
        Double propertyTax = Double.parseDouble(tAXDetailBean.getPropertyTax());

        Double paid_amount = Double.parseDouble(paymentBean.getAmountPaid());
        Double remaining = Double.parseDouble(tAXDetailBean.getPayableAmount());
        try {
            if ((paid_amount - arrear) >= 0) {

                paid_amount = paid_amount - arrear;
                remaining = remaining - arrear;
                arrear = 0D;
                if ((paid_amount - interest) >= 0) {
                    paid_amount = paid_amount - interest;
                    remaining = remaining - interest;
                    interest = 0D;

                    if ((paid_amount - propertyTax) >= 0) {
                        paid_amount = paid_amount - propertyTax;
                        remaining = remaining - propertyTax;
                        propertyTax = 0D;
                        advence_paid = advence_paid + paid_amount;
                    } else {
                        propertyTax = propertyTax - paid_amount;
                        remaining = remaining - paid_amount;
                    }

                } else {
                    interest = interest - paid_amount;
                    remaining = remaining - paid_amount;
                }

            } else {
                arrear = arrear - paid_amount;
                remaining = remaining - paid_amount;
            }

            //advence_paid = advence_paid + paid;
            tAXDetailBean.setArrearAmount(String.valueOf(arrear));
            tAXDetailBean.setPropertyTax(String.valueOf(propertyTax));
            tAXDetailBean.setDelayPaymentCharges(String.valueOf(interest));
            tAXDetailBean.setTotalPropertyTax(MMIUtil.formateAmount(remaining));
            tAXDetailBean.setGrandTotal(MMIUtil.formateAmount(remaining));
            tAXDetailBean.setPayableAmount(MMIUtil.formateAmount(remaining));
            tAXDetailBean.setAdvancePaidAmount(MMIUtil.formateAmount(advence_paid));

            // Amount update - End
            taxCollection.saveUpdatedTAX(tAXDetailBean);

        } catch (Exception e) {
            e.printStackTrace();

        }

        // Status Update
        paymentStatus.setAfterUpdateStatus(paymentBean.getStatus());
        paymentStatus.setIsComplete("Y");
        taxCollection.savePaymentStatus(paymentStatus);

    }

    @Override
    public void rejectPayment(PaymentStatus paymentStatus) {

        JSONObject jSONObject = new JSONObject();
        jSONObject.put("payRefId", paymentStatus.getPayrefid());

        PaymentBean paymentBean = taxCollection.getPaymentDetails(jSONObject);

        paymentStatus.setExistingStatus(paymentBean.getStatus());
        paymentStatus.setIsComplete("N");
        taxCollection.savePaymentStatus(paymentStatus);

        // Additional charge included
        paymentBean.setStatus(mMIUtil.PAY_TX_REJECT);
        taxCollection.savePaymentWithStatus(paymentBean);

        // Status Update
        paymentStatus.setAfterUpdateStatus(paymentBean.getStatus());
        paymentStatus.setIsComplete("Y");
        taxCollection.savePaymentStatus(paymentStatus);

    }

    @Override
    public List<PaymentBean> loadPaymentHistory(String propId) {
        return taxCollection.loadPaymentHistory(propId);
    }

    @Override
    public String savePropertyTrasferWithInstrument(PropertyTransferWithInstrument pt) {
        return taxCollection.savePropertyTrasferWithInstrument(pt);
    }

    @Override
    public String savePropertyTrasferWithOutInstrument(PropertyTransferWithOutInstrument pt) {
        return taxCollection.savePropertyTrasferWithOutInstrument(pt);
    }

    public String savePropertyTrasferCollection(PropertyTransferCollectionBean ptc) {
        return taxCollection.savePropertyTrasferCollection(ptc);
    }

    public String savePropertyTrasferCollectionWithoutInstrument(PropertyTransferCollectionBean ptc) {
        return taxCollection.savePropertyTrasferCollectionWithoutInstrument(ptc);
    }

    public Integer checkPropertyDues(String pid) {
        return taxCollection.checkPropertyDues(pid);
    }

    public List<PropertyArrearReport> getArrearReport(String ward, String propertyId, String mobile, String email, String ownerid, String occ_name, String Easy_City_Code, String locality, String Locality, String src_aadhar_no, String category) {
        return taxCollection.getArrearReport(ward, propertyId, mobile, email, ownerid, occ_name, Easy_City_Code, locality, Locality, src_aadhar_no, category);
    }

    public List<PrivateNoticeBean> generateDemnadAndCollection(String ward) {
        return taxCollection.generateDemnadAndCollection(ward);
    }

    public List<PaymentBean> getPaymentData(String ward) {
        return taxCollection.getPaymentData(ward);
    }

    public List<PropertyArrearReport> getCollectionAmount(String prop_id_input, String ward, String ownerid, String occ_name, String Phone_No, String Easy_City_Code, String locality, String Locality, String src_aadhar_no, String category, String frmDate, String endDate) {
        return taxCollection.getCollectionAmount(prop_id_input, ward, ownerid, occ_name, Phone_No, Easy_City_Code, locality, Locality, src_aadhar_no, category, frmDate, endDate);
    }

    public PaymentBean paymentInitDetails(String refNo) {
        return taxCollection.paymentInitDetails(refNo);
    }

    public PropertyDetails propertyDetails(String propId) {
        return taxCollection.propertyDetails(propId);
    }

    public String phonePaymentMsg(PaymentBean paymentBean, PropertyDetails propertyDetails) {
        String msg = "Payment has been recieved";
        if (paymentBean.getPaymentMode().equals("CSH")) {
            msg = "Payment of Rs. " + paymentBean.getAmountPaid() + " has been received against Property Id  " + paymentBean.getPropId() + ".\n\n Silvassa Municipal Council ";
        } else if (paymentBean.getPaymentMode().equals("CHQ")) {
            msg = "Payment of Rs. " + paymentBean.getAmountPaid() + " in reference with Cheque No. " + paymentBean.getChequeDDNum() + " has been received against Property Id  " + paymentBean.getPropId() + ".\nKindly collect the acknowledgment receipt.\n\n Silvassa Municipal Council";
        } else if (paymentBean.getPaymentMode().equals("DDF")) {
            msg = "Payment of Rs. " + paymentBean.getAmountPaid() + " in reference with  DD No. " + paymentBean.getChequeDDNum() + " has been received against Property Id " + paymentBean.getPropId() + ".\n\n Silvassa Municipal Council";
        } else if (paymentBean.getPaymentMode().equals("POS_DEVICE")) {
            msg = "Payment of Rs. " + paymentBean.getAmountPaid() + " in reference with POS reference no. " + paymentBean.getChequeDDNum() + " has been received against Property Id  " + paymentBean.getPropId() + ".\n\n Silvassa Municipal Council";
        } else if (paymentBean.getPaymentMode().equals("BHIM_UPI")) {
            msg = "Payment of Rs. " + paymentBean.getAmountPaid() + " in reference with BHIM UPI reference no. " + paymentBean.getChequeDDNum() + " has been received against Property Id  " + paymentBean.getPropId() + ".\n\n Silvassa Municipal Council";
        }
        return msg;
    }

    public String emailPaymentMsg(PaymentBean paymentBean, PropertyDetails propertyDetails) {
        String msg = "Payment has been recieved";

        String name = "";
        if (propertyDetails.getPropertyOwner() == null || propertyDetails.getPropertyOwner().equals("")) {
            name = propertyDetails.getPropertyOccupierName();
        } else {
            name = propertyDetails.getPropertyOwner();
        }

        if (paymentBean.getPaymentMode().equals("CSH")) {
            msg = "Payment of Rs. " + paymentBean.getAmountPaid() + " has been received against Property Id  " + paymentBean.getPropId() + ".";
        } else if (paymentBean.getPaymentMode().equals("CHQ")) {
            msg = "Payment of Rs. " + paymentBean.getAmountPaid() + " in reference with Cheque No. " + paymentBean.getChequeDDNum() + " has been received against Property Id  " + paymentBean.getPropId() + ".\n Kindly collect the acknowledgment receipt.";
            msg += "<br><br> Note : Adjustment of amount is subjected to the clearance of Check/DD submitted. ";
        } else if (paymentBean.getPaymentMode().equals("DDF")) {
            msg = "Payment of Rs. " + paymentBean.getAmountPaid() + " in reference with  DD No. " + paymentBean.getChequeDDNum() + " has been received against Property Id " + paymentBean.getPropId() + ".";
            msg += "<br><br> Note : Adjustment of amount is subjected to the clearance of Check/DD submitted. ";
        } else if (paymentBean.getPaymentMode().equals("POS_DEVICE")) {
            msg = "Payment of Rs. " + paymentBean.getAmountPaid() + " in reference with POS reference no. " + paymentBean.getChequeDDNum() + " has been received against Property Id  " + paymentBean.getPropId() + ".";
        } else if (paymentBean.getPaymentMode().equals("BHIM_UPI")) {
            msg = "Payment of Rs. " + paymentBean.getAmountPaid() + " in reference with BHIM UPI reference no. " + paymentBean.getChequeDDNum() + " has been received against Property Id  " + paymentBean.getPropId() + ".";
        }

        StringBuilder emailBody = new StringBuilder();
        emailBody.append("Dear ");
        emailBody.append(name);
        emailBody.append(", <br><br>");
        emailBody.append(msg);
        emailBody.append("<br>");
        emailBody.append("-------------------------------------------------------------------------------------<br>");
        emailBody.append(" This is system generated mail. Please do not reply.<br>");
        emailBody.append("-------------------------------------------------------------------------------------<br>");

        return emailBody.toString();
    }

    @Override
    public String getTransactionId() {
        return taxCollection.getTransactionId();
    }

}
