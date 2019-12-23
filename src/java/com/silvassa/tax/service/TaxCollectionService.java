package com.silvassa.tax.service;

import com.silvassa.bean.PrivateNoticeBean;
import com.silvassa.model.BankMaster;
import com.silvassa.model.CorrectionFormSaveBean;
import com.silvassa.model.FloorWiseTAXDetails;
import java.util.List;

import com.silvassa.model.Localitymaster;
import com.silvassa.model.PaymentBean;
import com.silvassa.model.PaymentModeMaster;
import com.silvassa.model.PaymentStatus;
import com.silvassa.model.PropertyArrearReport;
import com.silvassa.model.PropertyDetails;
import com.silvassa.model.PropertyForTaxBean;
import com.silvassa.model.PropertyRentable;
import com.silvassa.model.PropertyTransferCollectionBean;
import com.silvassa.model.PropertyTransferWithInstrument;
import com.silvassa.model.PropertyTransferWithOutInstrument;
import com.silvassa.model.PropertytypeMaster;
import com.silvassa.model.TAXCalculationBean;
import com.silvassa.model.TAXDetailBean;
import com.silvassa.model.Wardmaster;
import org.json.simple.JSONObject;

public interface TaxCollectionService {

    public PropertyForTaxBean getPropertyDetail(String propertyId);

    public List<Wardmaster> getWard();

    public List<Localitymaster> getLocality();

    public List<PropertytypeMaster> getPropertyType();

    public List<TAXCalculationBean> getTAXInDetail(String propertyId);

    public JSONObject searchPropertyForTAX(JSONObject jo);

    public TAXDetailBean getTAXDetails(String propId, String financialYear, String noticeGenerated);

    public String getPaymentRefNumber();

    public PaymentBean initPayment(String propId);

    public List<FloorWiseTAXDetails> loadtFloorWiseTAXDetails(String taxNo);

    public List<PropertyRentable> loadRentableValues();

    public List<BankMaster> getBanks();

    public List<PaymentModeMaster> getPaymentModes();

    public PaymentBean collectPayment(PaymentBean paymentBean);

    public JSONObject getPaymentsForApproval(String param);

    public void approvePayment(PaymentStatus paymentStatus);

    public void rejectPayment(PaymentStatus paymentStatus);

    public List<PaymentBean> loadPaymentHistory(String propId);

    public List<PaymentBean> printCollectionReceipt(String propId, String receiptId);

    public List<PropertyDetails> getOwnerName(String propId);

    public String savePropertyTrasferWithInstrument(PropertyTransferWithInstrument pt);

    public String savePropertyTrasferWithOutInstrument(PropertyTransferWithOutInstrument pt);

    public String savePropertyTrasferCollection(PropertyTransferCollectionBean ptc);

    public String savePropertyTrasferCollectionWithoutInstrument(PropertyTransferCollectionBean ptc);

    public Integer checkPropertyDues(String pid);

    public List<PropertyArrearReport> getArrearReport(String ward, String propertyId, String mobile, String email,String ownerid,String occ_name,String Easy_City_Code,String locality,String Locality,String src_aadhar_no,String category);

    public List<PrivateNoticeBean> generateDemnadAndCollection(String ward);

    public List<PaymentBean> getPaymentData(String ward);

    public List<PropertyArrearReport> getCollectionAmount(String prop_id_input, String ward, String ownerid , String occ_name , String Phone_No, String Easy_City_Code , String locality , String Locality , String src_aadhar_no , String category,String frmDate , String endDate );

    public PaymentBean paymentInitDetails(String refNo);

    public PropertyDetails propertyDetails(String propId);

    public String phonePaymentMsg(PaymentBean paymentBean,PropertyDetails propertyDetails);

    public String emailPaymentMsg(PaymentBean paymentBean,PropertyDetails propertyDetails);
    
    public String getTransactionId();

}
