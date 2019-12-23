/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.silvassa.report.service;

import com.silvassa.bean.ReportQuery;
import com.silvassa.model.PropertyDetails;
import java.util.List;
import org.json.simple.JSONObject;

/**
 *
 * @author CEINFO
 */
public interface ReportService {

    public Object searchByPropertyDetails(ReportQuery reportQuery);
    
    public Object searchObjectionReport(ReportQuery reportQuery) throws Exception;

    public Object searchByTAXDetails(ReportQuery reportQuery);

    public Object searchByPaymentDue(ReportQuery reportQuery);
    
    public Object searchByArrearDue(ReportQuery reportQuery);
    
    public Object searchByNotice(ReportQuery reportQuery);
    
    public Object searchByTAXGen(ReportQuery reportQuery);
    
    public Object searchByTAXAmount(ReportQuery reportQuery);
    
    public Object searchPropertyForReceipt(ReportQuery reportQuery);
    
    public Object getObjectionCategories(ReportQuery reportQuery);  

}
