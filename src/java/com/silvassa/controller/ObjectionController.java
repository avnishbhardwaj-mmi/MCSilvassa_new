/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.silvassa.controller;

import com.google.gson.Gson;
import com.silvassa.bean.LoginDetailsBean;
import com.silvassa.bean.ObjSearchBean;
import com.silvassa.bean.ResolutionBean;
import com.silvassa.model.ObjDocument;
import com.silvassa.model.ObjRelations;
import com.silvassa.model.ObjectionActionHistory;
import com.silvassa.model.ObjectionBean;
import com.silvassa.model.PropertyEditableFields;
import com.silvassa.model.ObjectionTx;
import com.silvassa.model.PropertyFloor;
import com.silvassa.model.Usermaster;
import com.silvassa.objection.service.ObjectionService;
import com.silvassa.util.MMIUtil;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import javax.servlet.http.HttpServletRequest;
import org.apache.log4j.Logger;
import org.json.simple.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
public class ObjectionController {

    private static final Logger logger = Logger.getLogger(ObjectionController.class);

    @Autowired
    private ObjectionService objectionService;

    @RequestMapping(value = "/objectionNew", method = RequestMethod.GET)
    public String objectionPage(HttpServletRequest request, Model model) {
        return "objectionNew";
    }

    // Sandeep added below for existing objection
    @RequestMapping(value = "/objection", method = RequestMethod.GET)
    public String objectionExist(HttpServletRequest request, Model model) {
        return "objection";
    }

    @RequestMapping(value = "/objectionAllocation", method = RequestMethod.GET)
    public String objectionAllocation(HttpServletRequest request, Model model) {
        return "objectionAllocation";
    }

    // Sandeep added below method on 20Feb2017
    @RequestMapping(value = "/getPendingObjectionStatus", method = {RequestMethod.POST, RequestMethod.GET})
    public @ResponseBody
    Object countObjections() {

        Object[] ls = null;
        try {
            ls = (Object[]) objectionService.rangeOfPendingObjection();

            if (ls[0] == null || ls[0] == null) {
                return "NA";
            }

            if (ls[0] != null) {
                ls[0] = MMIUtil.formateDate(MMIUtil.epocToDate(Long.valueOf(String.valueOf(ls[0]))));
            }
            if (ls[1] != null) {
                ls[1] = MMIUtil.formateDate(MMIUtil.epocToDate(Long.valueOf(String.valueOf(ls[1]))));
            }

        } catch (Exception e) {
            logger.info(e.getMessage());
        }

        return ls;

    }

    // Sandeep added below method
    @SuppressWarnings("unchecked")
    @RequestMapping(value = "/searchProperty", method = {RequestMethod.POST})
    public @ResponseBody
    List loadObjData(@RequestParam("jsonViewCrit") String jsonViewCrit) {

        ObjSearchBean objSearchBean = new Gson().fromJson(jsonViewCrit, ObjSearchBean.class);
        logger.info("objSearchBean : " + objSearchBean);
        List propertyDetailsBeanlist = null;
        try {
            propertyDetailsBeanlist = objectionService.searchObjectionDetails(objSearchBean);
            if (propertyDetailsBeanlist == null) {
                propertyDetailsBeanlist = new ArrayList();
            }
        } catch (Exception e) {
            logger.info(e.getMessage());
        }
        return propertyDetailsBeanlist;
    }

    @RequestMapping(value = "/getEditableAttr", method = {RequestMethod.POST, RequestMethod.GET})
    public @ResponseBody
    List<PropertyEditableFields> getEditableAttr() {

        return objectionService.getEditableAttr();

    }

    @RequestMapping(value = "/getFloorDetails", method = {RequestMethod.POST, RequestMethod.GET})
    public @ResponseBody
    List<PropertyFloor> getFloorDetails(String propertyId) {

        return objectionService.getFloorDetails(propertyId);

    }

    @RequestMapping(value = "/getVerifyingDocuments", method = {RequestMethod.POST, RequestMethod.GET})
    public @ResponseBody
    List<ObjDocument> getVerifyingDocuments(String propertyId) {

        return objectionService.getVerifyingDocuments();

    }

    //Added By Jay
    @RequestMapping(value = "/getVerifyingRelations", method = {RequestMethod.POST, RequestMethod.GET})
    public @ResponseBody
    List<ObjRelations> getVerifyingRelations() {
        return objectionService.getVerifyingRelations();

    }
//Added By Jay End

    @RequestMapping(value = "/raiseObjection", method = {RequestMethod.POST, RequestMethod.GET})
    public @ResponseBody
    JSONObject raiseObjection(HttpServletRequest request, String objectionTxStr) {

        String msg = "";
        String objRefId = "";
        JSONObject jSONObject = new JSONObject();

        boolean objStatus = false;
        try {

            LoginDetailsBean loginDetails = (LoginDetailsBean) request.getSession(false).getAttribute("userDetailsBean");
            ObjectionTx objectionTx = new Gson().fromJson(objectionTxStr, ObjectionTx.class);

            objectionTx.setStatus(MMIUtil.OBJ_STATUS_CREATE);
            objectionTx.setRaisedBy(loginDetails.getUserId());
            objectionTx.setRaisedOn(String.valueOf(MMIUtil.getEpocTime()));
            objectionTx.setEntrydatetime(new Date());
            objRefId = objectionService.getObjectionRefNumber();
            if (objRefId == null) {
                throw new Exception("Unable to get Objection reference Id.");
            }
            objectionTx.setObjectionId(objRefId);

            List<ObjectionBean> objectionBeans = objectionTx.getObjectionBeans();
            for (Object ob : objectionBeans) {
                ObjectionBean objectionBean = (ObjectionBean) ob;
                objectionBean.setPropertyId(objectionTx.getPropertyId());
                objectionBean.setObjectionId(objRefId);
            }
            System.out.println("objectionTx : " + objectionTx.toString());
            objStatus = objectionService.raiseObjection(objectionTx);
            if (!objStatus) {
                throw new Exception("Unable Commite Objection Data.");
            }

            msg = "Request forwarded for approval. Objection Id is <b>" + objRefId + "</b>.";
            jSONObject.put("status", "200");
            jSONObject.put("objectionBean", objectionTx);
            jSONObject.put("msg", msg);

        } catch (Exception ex) {
            msg = "Some error has been occurred. Try again later.";
            jSONObject.put("Status", "500");
            jSONObject.put("msg", msg);
            ex.printStackTrace();
            logger.info("[ObjectionController.raisObjection] Exception : " + ex.getMessage());

        }

        return jSONObject;

    }

    @RequestMapping(value = "/checkValidityForObjectinGeneration", method = {RequestMethod.POST, RequestMethod.GET})
    public @ResponseBody
    JSONObject checkValidityForObjectinGeneration(@RequestParam String propertyId) {
        String msg = "";
        JSONObject jSONObject = new JSONObject();
        try {
            if (objectionService.checkIfPendingObjection(propertyId)) {
                msg = "This property is under objection. Cannot raise new objection.";
                jSONObject.put("status", "2000");
                jSONObject.put("msg", msg);
                return jSONObject;
            } else if (objectionService.checkNoticeGeneratedOrNOt(propertyId).equals("N")) {
                msg = "Notice not generated. Cannot raise new objection.";
                jSONObject.put("status", "2000");
                jSONObject.put("msg", msg);
                return jSONObject;
            }
            msg = "Sucess";
            jSONObject.put("status", "200");
            jSONObject.put("msg", msg);
        } catch (Exception ex) {
            msg = "Some error has been occurred. Try again later.";
            jSONObject.put("Status", "500");
            jSONObject.put("msg", msg);
            ex.printStackTrace();
            logger.info("[ObjectionController.checkValidityForObjectinGeneration] Exception : " + ex.getMessage());
        }
        return jSONObject;
    }

    @RequestMapping(value = "/getGeneratedObjection", method = {RequestMethod.POST, RequestMethod.GET})
    public @ResponseBody
    JSONObject getGeneratedObjection(String propertyId) {

        JSONObject jSONObject = new JSONObject();
        String msg = "";
        try {
            if (propertyId == null || propertyId == "") {
                jSONObject.put("Status", "500");
                jSONObject.put("msg", "Kindly provide propertyId");

            } else {
                jSONObject.put("Status", "200");
                ObjectionTx objectionTx = objectionService.getObjection(propertyId);
                if (objectionTx == null) {
                    jSONObject.put("Status", "201");
                    jSONObject.put("msg", "No objection raised.");
                } else {
                    objectionTx.setObjectionBeans(objectionService.getObjectedAttributes(objectionTx.getPropertyId(), objectionTx.getObjectionId()));
                    jSONObject.put("msg", objectionTx);
                }
            }

        } catch (Exception ex) {
            msg = "Some error has been occurred. Try again later.";
            jSONObject.put("Status", "500");
            jSONObject.put("msg", msg);

            logger.info("[ObjectionController.getObjection] Exception : " + ex.getMessage());

        }

        return jSONObject;

    }

    @RequestMapping(value = "/getObjectedPropertyForSearch", method = {RequestMethod.POST, RequestMethod.GET})
    public @ResponseBody
    JSONObject getObjectedPropertyForSearch(String zoneId, String ward) {

        JSONObject jSONObject = new JSONObject();
        String msg = "";
        try {
            if (zoneId == null || zoneId.equals("") || ward == null || ward.equals("")) {
                jSONObject.put("Status", "500");
                jSONObject.put("msg", "Kindly provide zone and ward both.");

            } else {
                jSONObject.put("Status", "200");
                JSONObject ls = objectionService.getObjectedPropertyForSearch(zoneId, ward);
                jSONObject.put("msg", ls);
            }

        } catch (Exception ex) {
            msg = "Some error has been occurred. Try again later.";
            jSONObject.put("Status", "500");
            jSONObject.put("msg", msg);

            logger.info("[ObjectionController.getObjectedPropertyForSearch] Exception : " + ex.getMessage());

        }

        return jSONObject;

    }

    @SuppressWarnings("unchecked")
    @RequestMapping(value = "/searchObjectedProperty", method = {RequestMethod.POST, RequestMethod.GET})
    public @ResponseBody
    List searchObjectedProperty(@RequestParam("jsonViewCrit") String jsonViewCrit) {

        ObjSearchBean objSearchBean = new Gson().fromJson(jsonViewCrit, ObjSearchBean.class);

        List propertyDetailsBeanlist = null;
        try {
            propertyDetailsBeanlist = objectionService.searchObjectedProperty(objSearchBean);
            if (propertyDetailsBeanlist == null) {
                propertyDetailsBeanlist = new ArrayList();
            }
        } catch (Exception e) {
            logger.info(e.getMessage());
        }
        return propertyDetailsBeanlist;
    }

    @RequestMapping(value = "/resolveObjection", method = {RequestMethod.POST, RequestMethod.GET})
    public @ResponseBody
    JSONObject resolveObjection(HttpServletRequest request, String resolutionBean) {

        String msg = "";
        JSONObject jSONObject = new JSONObject();

        try {

            ResolutionBean rb = new Gson().fromJson(resolutionBean, ResolutionBean.class);
            LoginDetailsBean loginDetails = (LoginDetailsBean) request.getSession(false).getAttribute("userDetailsBean");

            rb.setResolveBy(loginDetails.getUserId());
            rb.setResolveOn(String.valueOf(MMIUtil.getEpocTime()));

            if (rb.getDecision() == null) {
                msg = "Decision Missing.";
                jSONObject.put("Status", "1001");
                jSONObject.put("msg", msg);
                return jSONObject;
            }
            if (rb.getObjectionId() == null) {
                msg = "ObjectionId Missing.";
                jSONObject.put("Status", "1001");
                jSONObject.put("msg", msg);
                return jSONObject;
            }
            if (rb.getPropertyId() == null) {
                msg = "PropertyId Missing.";
                jSONObject.put("Status", "1001");
                jSONObject.put("msg", msg);
                return jSONObject;
            }
            if (rb.getRemarks() == null) {
                msg = "Remarks Missing.";
                jSONObject.put("Status", "1001");
                jSONObject.put("msg", msg);
                return jSONObject;
            }
            if (rb.getDecision().equalsIgnoreCase(MMIUtil.OBJ_STATUS_APPROVED)) {
                objectionService.approveObjection(rb);
            } else if (rb.getDecision().equalsIgnoreCase(MMIUtil.OBJ_STATUS_FORWARD)) {
                objectionService.forwardObjection(rb);
            } else if (rb.getDecision().equalsIgnoreCase(MMIUtil.OBJ_STATUS_REVERT)) {
                objectionService.forwardBackObjection(rb);
            } else if (rb.getDecision().equalsIgnoreCase(MMIUtil.OBJ_STATUS_REJECTED)) {
                objectionService.rejectObjection(rb);
            }

            msg = "Changes made successfully. Objection Id is <b>" + rb.getObjectionId() + "</b>.";
            jSONObject.put("status", "200");
            jSONObject.put("msg", msg);

        } catch (Exception ex) {
            ex.printStackTrace();
            msg = "Some error has been occurred. Try again later.";
            jSONObject.put("Status", "500");
            jSONObject.put("msg", msg);

            logger.info("[ObjectionController.raisObjection] Exception : " + ex.getMessage());

        }

        return jSONObject;

    }

    @RequestMapping(value = "/getObjectionHandlerUsers", method = RequestMethod.POST)
    @ResponseBody
    public List<Usermaster> getObjectionHandlerUsers(HttpServletRequest request, Model model) {
        List<Usermaster> userList = null;
        try {
            userList = objectionService.userList();
        } catch (Exception ex) {
            ex.printStackTrace();
        }

        return userList;
    }

    @RequestMapping(value = "/getObjectionActionHistory", method = RequestMethod.POST)
    @ResponseBody
    public JSONObject getObjectionActionHistory(HttpServletRequest request, Model model, String resolutionBean) {
        List<ObjectionActionHistory> actionHistory = null;
        JSONObject jSONObject = new JSONObject();
        String msg = "";

        try {

            ResolutionBean rb = new Gson().fromJson(resolutionBean, ResolutionBean.class);

            if (rb.getObjectionId() == null) {
                msg = "ObjectionId Missing.";
                jSONObject.put("Status", "1001");
                jSONObject.put("msg", msg);
                return jSONObject;
            }
            if (rb.getPropertyId() == null) {
                msg = "PropertyId Missing.";
                jSONObject.put("Status", "1001");
                jSONObject.put("msg", msg);
                return jSONObject;
            }

            actionHistory = objectionService.getObjectionActionHistory(rb);

            msg = "OK";
            jSONObject.put("status", "200");
            jSONObject.put("msg", msg);
            jSONObject.put("data", actionHistory);

        } catch (Exception ex) {
            ex.printStackTrace();
            msg = "Some error has been occurred. Try again later.";
            jSONObject.put("Status", "500");
            jSONObject.put("msg", msg);
        }

        return jSONObject;
    }

    @SuppressWarnings("unchecked")
    @RequestMapping(value = "/getObjectionFromMyTray", method = {RequestMethod.POST})
    public @ResponseBody
    List getObjectionFromMyTray(HttpServletRequest request, Model model) {

        List propertyDetailsBeanlist = null;
        try {

            LoginDetailsBean loginDetails = (LoginDetailsBean) request.getSession(false).getAttribute("userDetailsBean");

            JSONObject jo = new JSONObject();
            jo.put("userId", loginDetails.getUserId());

            propertyDetailsBeanlist = objectionService.objectionInMyTray(jo);
            if (propertyDetailsBeanlist == null) {
                propertyDetailsBeanlist = new ArrayList();
            }
        } catch (Exception e) {
            logger.info(e.getMessage());
        }
        return propertyDetailsBeanlist;
    }

}
