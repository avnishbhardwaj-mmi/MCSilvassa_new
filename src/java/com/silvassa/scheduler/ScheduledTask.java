/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.silvassa.scheduler;

import com.silvassa.tax.service.TaxServiceImpl;
import javax.annotation.PostConstruct;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Component;

/**
 *
 * @author CEINFO
 */
@Component("scheduledTask")
public class ScheduledTask {

    @Autowired
    @Qualifier("taxService")
    TaxServiceImpl taxServiceImpl;

    public void updateTaxPenalty() {

        taxServiceImpl.updateTaxPenalty();

    }

    public void updateDelayPaymentCharge() {
        System.out.println("ScheduledTask [updateDelayPaymentCharge] : Start ");
        taxServiceImpl.updateDelayPaymentCharges();
        System.out.println("ScheduledTask [updateDelayPaymentCharge] : End ");
    }

//    @PostConstruct - Moved to SilvassaPay
    public void updatePaymentStatus() {
        System.out.println("ScheduledTask [updatePaymentStatus] : Start ");
        taxServiceImpl.updatePaymentStatus();
        System.out.println("ScheduledTask [updatePaymentStatus] : End ");
    }
}
