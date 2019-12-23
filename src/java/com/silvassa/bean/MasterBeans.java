/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.silvassa.bean;

import java.util.ArrayList;

/**
 *
 * @author CEINFO
 */
public class MasterBeans {

    private ArrayList<MasterBean> masterBeans;

    /**
     * @return the masterBeans
     */
    public ArrayList<MasterBean> getMasterBeans() {
        return masterBeans;
    }

    /**
     * @param masterBeans the masterBeans to set
     */
    public void setMasterBeans(ArrayList<MasterBean> masterBeans) {
        this.masterBeans = masterBeans;
    }

    @Override
    public String toString() {
        return "MasterBeans{" + "masterBeans=" + masterBeans + '}';
    }

}
