package com.silvassa.tax.dao;

import com.silvassa.bean.FilterBean;
import com.silvassa.model.ActionTracker;
import java.util.List;
import java.util.Map;

public interface TaxDao {

    public String generateTax(FilterBean filterBean, ActionTracker actionTracker);

    public void updateTaxPenalty();

    public void updateDelayPaymentCharges();

    public void updatePaymentStatus(String payrefid, String status);

    public List<Map<String, String>> getPaymentWithIncompleteStatus();
    
    public String generateTaxById(String id, ActionTracker actionTracker);
}
