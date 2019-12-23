package com.silvassa.tax.service;

import com.silvassa.bean.FilterBean;
import com.silvassa.model.ActionTracker;
import com.silvassa.model.PaymentBean;
import java.util.List;

public interface TaxService {
	public String generateTax(FilterBean taxBean, ActionTracker actionTracker); 
        public String generateTaxById(String id, ActionTracker actionTracker); 
        public void updateTaxPenalty();
        public void updateDelayPaymentCharges();
//        public void updatePaymentStatus();
        
}
