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
import java.util.List;
import java.util.Set;

/**
 *
 * @author CEINFO
 */
public interface ReportDAO {

    public List<TAXDetailBean> searchTAXAmount(ReportQuery reportQuery, List<String> propList);

    public List showPropertyDetails(ReportQuery reportQuery);
    
    public List<ObjectionTx> showObjectionDetails(ReportQuery reportQuery);

    public List<PropertyDetails> getPropertyDetails(ReportQuery reportQuery, List<String> propertyIds);

    public List<TAXDetailBean> filterPendingPaymentCases(ReportQuery reportQuery);
    
    public List<TAXDetailBean> filterPendingArrearCases(ReportQuery reportQuery);
    
    public List<TaxNotices> loadtTaxNoticeData(ReportQuery reportQuery,  List<String> propertyIds);
    
    public List<PropertyDetails> getPropDetailsForReceipt(ReportQuery reportQuery);
    
    public List<PaymentBean> getPropertyDetailsForPayment(Set propList, ReportQuery reportQuery);    
    
     public List<ObjectionBean> getObjAttributes(ReportQuery reportQuery);
     
     public List<PropertyEditableFields> getObjectionCategory(Set objCatList);

     public List<ObjectionBean> getObjectedAttributes(String propertyId, String objectionId) throws Exception ;

}
