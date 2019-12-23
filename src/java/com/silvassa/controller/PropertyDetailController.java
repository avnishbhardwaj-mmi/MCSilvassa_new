//----------------------------------------------------------------------------------------------------
//                          MapMyIndia
//            Product / Project           : Silvassa
//            Module                      : PropertyAdding
//            File Name                   : newProperty
//            Author                      : Jay Prakash Kumar
//            Project Lead                :
//            Date written (DD/MM/YYYY)   : 4 Jul, 2017, 9:55:05 AM
//            Description                 : 
//----------------------------------------------------------------------------------------------------
//                                            CHANGE HISTORY
//----------------------------------------------------------------------------------------------------
// Date             Change By           Change Description (Bug No. (If Any))
// (DD/MM/YYYY)
//----------------------------------------------------------------------------------------------------
//----------------------------------------------------------------------------------------------------
package com.silvassa.controller;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.silvassa.bean.LoginDetailsBean;
import com.silvassa.model.ActionTracker;
import com.silvassa.model.PropertyDetails;
import com.silvassa.model.PropertyFloor;
import com.silvassa.model.SlJobAllocation;
import com.silvassa.model.Usermaster;
import com.silvassa.service.SilvassaService;
import com.silvassa.tax.service.TaxService;
import com.silvassa.util.MMIUtil;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import org.apache.log4j.Logger;
import org.hibernate.Query;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
public class PropertyDetailController {

    /**
     * The Constant logger.
     */
    private static final Logger logger = Logger.getLogger(HomeController.class);

    @Autowired
    private SilvassaService silvassaService;
    @Autowired
    @Qualifier("taxService")
    TaxService taxService;

    @RequestMapping(value = "/addNewProperty", method = RequestMethod.POST)
    @ResponseBody
    public Map addNewProperty(HttpServletRequest request, Model model) {
        //System.out.println("This is here");
        Map retMap = new HashMap();
        Gson json = new Gson();
        String msg = "";
        try {

            //System.out.println("data ass"+request.getParameter("newProperty"));
            PropertyDetails propertyDetail = json.fromJson(request.getParameter("newProperty"), PropertyDetails.class);
//            propertyDetail.setPropertyDetailsId(null);
            //System.out.println("addNewProperty"+propertyDetail.toString());;
            propertyDetail.getPropertyDetailsId();
//            String floorListDetails = (request.getParameter("newPropertyFloor"));
            List<PropertyFloor> propertyFloorList = json.fromJson(request.getParameter("newPropertyFloor"), new TypeToken<List<PropertyFloor>>() {
            }.getType());
            //System.out.println("Data:" + propertyDetail.toString());
            silvassaService.addNewProperty(propertyDetail, propertyFloorList);
            String uniqueId = propertyDetail.getPropertyUniqueId();
            LoginDetailsBean userData = (LoginDetailsBean) request.getSession().getAttribute("userDetailsBean");

            String ipAddress = request.getHeader("X-FORWARDED-FOR");
            if (ipAddress == null) {
                ipAddress = request.getRemoteAddr();
            }
            ActionTracker actionTracker = new ActionTracker();
            actionTracker.setInitTime(String.valueOf(MMIUtil.getEpocTime()));
            actionTracker.setInitBy(userData.getUserId());
            actionTracker.setTask(MMIUtil.TAX_GEN);
            actionTracker.setTaskStatus(MMIUtil.STATUS_START);
            actionTracker.setIpAddress(ipAddress);
            actionTracker.setRemarks(uniqueId.toString());

            silvassaService.appendToActionTracker(actionTracker);

            //////////////////TO RECALCULATE TAX OF PROPERTY/////////////////////////
            String result = taxService.generateTaxById(uniqueId, actionTracker);
            //System.out.println("result "+result);
            if (result.equals("success")) {
                actionTracker.setTaskStatus(MMIUtil.STATUS_COMPLETE);
                msg = "Changes applied.";
            } else {
                actionTracker.setTaskStatus(MMIUtil.STATUS_ERROR);
                msg = "Changes applied. But error in tax calculation.";
            }

            actionTracker.setCompleteTime(String.valueOf(MMIUtil.getEpocTime()));
            silvassaService.appendToActionTracker(actionTracker);
              //////////////////TO RECALCULATE TAX OF PROPERTY/////////////////////////
            retMap.put("Status", 200);
        } catch (Exception e) {
            retMap.put("Status", 412);
            logger.error("PropertyDetailController.addNewProperty error:" + e.getMessage());
            e.printStackTrace();
        }
        return retMap;
    }

    @RequestMapping(value = "/jobAllocation", method = RequestMethod.POST)
    @ResponseBody
    public Map jobAllocation(HttpServletRequest request) {
        Map retMap = new HashMap();
        Gson json = new Gson();
        List<SlJobAllocation> jobAllocationDetailList = new ArrayList<>();
        try {
            jobAllocationDetailList = json.fromJson(request.getParameter("jobAllocationDetails"), new TypeToken<List<SlJobAllocation>>() {
            }.getType());
        } catch (Exception e) {
        }

        Usermaster userMaster = json.fromJson(request.getParameter("userDeatil"), Usermaster.class);

        HttpSession session = request.getSession(false);
        if (session == null) {
            retMap.put("Status", 412);
            retMap.put("Message", "User Session Invalid.");
        } else {
            LoginDetailsBean loginDetails = (LoginDetailsBean) session.getAttribute("userDetailsBean");
            for (SlJobAllocation jobAllocationDetail : jobAllocationDetailList) {
                jobAllocationDetail.setAllocateBy(loginDetails.getUserId());
            }
            String status = silvassaService.jobAllocation(jobAllocationDetailList);
            System.out.println("LoginDetails:" + loginDetails);
            System.out.println("Allocation Details:" + jobAllocationDetailList);
            if ("success".equals(status)) {
                retMap.put("Status", 200);
                retMap.put("Message", "Job Allocation Successful.");
            } else {
                retMap.put("Status", 412);
                retMap.put("Message", "Database connection error.");
            }
        }

        return retMap;
    }

    @RequestMapping(value = "/deAllocateJob", method = RequestMethod.POST)
    @ResponseBody
    public Map deAllocateJob(HttpServletRequest request) {
        Map retMap = new HashMap();
        Gson json = new Gson();
//        SlJobAllocation jobdeAllocationDetail = json.fromJson(request.getParameter("jobDetail"), SlJobAllocation.class);
        List<SlJobAllocation> jobdeAllocationDetail = new ArrayList<>();
        try {
            jobdeAllocationDetail = json.fromJson(request.getParameter("jobDetail"), new TypeToken<List<SlJobAllocation>>() {
            }.getType());
        } catch (Exception e) {
        }
        HttpSession session = request.getSession(false);
        if (session == null) {
            retMap.put("Status", 412);
            retMap.put("Message", "User Session Invalid.");
        } else {
//            jobdeAllocationDetail.setActive("N");
            String status = silvassaService.jobDeAllocation(jobdeAllocationDetail);

            System.out.println("Allocation Details:" + jobdeAllocationDetail);
            if ("success".equals(status)) {
                retMap.put("Status", 200);
                retMap.put("Message", "Job DeAllocation Successful.");
            } else {
                retMap.put("Status", 412);
                retMap.put("Message", "Database connection error.");
            }
        }
        return retMap;
    }

    @RequestMapping(value = "/getAllocatedJobs", method = RequestMethod.POST)
    @ResponseBody
    public Map getAllocatedJobs(HttpServletRequest request) {
//        Gson json = new Gson();
        Map retMap = new HashMap();
        List<SlJobAllocation> slJobList = null;
//        Usermaster userMaster = json.fromJson(request.getParameter("userDeatil"), Usermaster.class);
//        if (null != userMaster) {
        slJobList = silvassaService.getAllocatedJobs();
        if (null == slJobList) {
            retMap.put("Status", 412);
            retMap.put("count", slJobList.size());
            retMap.put("data", slJobList);
        } else if (slJobList.isEmpty()) {
            retMap.put("Status", 200);
            retMap.put("count", slJobList.size());
            retMap.put("data", slJobList);
        } else {
            retMap.put("Status", 200);
            retMap.put("count", slJobList.size());
            retMap.put("data", slJobList);
        }
//        } 
//        else {
//            retMap.put("Status", 412);
//            retMap.put("count", slJobList.size());
//            retMap.put("data", slJobList);
//        }
        return retMap;
    }

}
