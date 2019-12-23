/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.silvassa.controller;

import com.google.gson.Gson;
import com.itextpdf.text.log.SysoLogger;
import com.silvassa.bean.CorrectionAction;
import com.silvassa.bean.CorrectionFormLoadData;
import com.silvassa.bean.ImageBean;
import com.silvassa.bean.LoginDetailsBean;
import com.silvassa.model.CorrectionFormBean;
import com.silvassa.dao.CorrectionDAO;
import com.silvassa.model.ActionTracker;
import com.silvassa.model.CorrectionFormFloorBean;
import com.silvassa.model.CorrectionFormFloorReport;
import com.silvassa.model.CorrectionFormHitLogger;
import com.silvassa.model.CorrectionFormReport;
import com.silvassa.model.CorrectionFormSaveBean;
import com.silvassa.model.CorrectionRequestBean;
import com.silvassa.model.MobileBean;
import com.silvassa.service.OTPService;
import com.silvassa.service.SilvassaService;
import com.silvassa.tax.service.TaxService;
import com.silvassa.util.MMIUtil;
import java.io.IOException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import javax.servlet.http.HttpServletRequest;
import org.apache.commons.codec.binary.Base64;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

@Controller
public class CorrectionController {

    private static final Logger logger = Logger.getLogger(CorrectionController.class);

    @Autowired
    CorrectionDAO correctionDAO;
    @Autowired
    private SilvassaService silvassaService;
    @Autowired
    @Qualifier("taxService")
    TaxService taxService;
    @Autowired
    private OTPService OTPServiceImpl;
    @Autowired
    private HttpServletRequest request;

    @RequestMapping(value = "/dashboard", method = {RequestMethod.GET})
    public ModelAndView dashboard(HttpServletRequest request, Model model, int actionFliter) {
        ModelAndView mv = new ModelAndView();
        String view = "dashboard";

        if (actionFliter > 12 || actionFliter < 1) {
            actionFliter = 1;
        }

        model.addAttribute("actionFliter", actionFliter);
        model.addAttribute("countdata", correctionDAO.getCorrectionCount(actionFliter));
        model.addAttribute("countdataward", correctionDAO.getCorrectionCountOnWard(actionFliter));
        mv.setViewName(view);
        return mv;
    }

    @RequestMapping(value = "/correction_all_list", method = {RequestMethod.GET})
    public ModelAndView correctionAllList(HttpServletRequest request, Model model, int actionFliter) {
        ModelAndView mv = new ModelAndView();
        String view = "correction_all_list";
        if (actionFliter > 12 || actionFliter < 1) {
            actionFliter = 1;
        }
        model.addAttribute("actionFliter", actionFliter);
        model.addAttribute("correctiondata", correctionDAO.getCorrectionListAll(actionFliter));
        mv.setViewName(view);
        return mv;
    }

    @RequestMapping(value = "/correction_inbox_list", method = {RequestMethod.GET})
    public ModelAndView correction_inbox_list(HttpServletRequest request, Model model) {
        ModelAndView mv = new ModelAndView();
        String view = "correction_inbox_list";
        model.addAttribute("correctiondata", correctionDAO.getCorrectionListInbox());
        model.addAttribute("submitmsg", "Updated successfully!!");
        mv.setViewName(view);
        return mv;
    }

    @RequestMapping(value = "/correction_field_list", method = {RequestMethod.GET})
    public ModelAndView correction_field_list(HttpServletRequest request, Model model) {
        ModelAndView mv = new ModelAndView();
        String view = "correction_field_list";
        model.addAttribute("correctiondata", correctionDAO.getCorrectionListFieldVerify());
        model.addAttribute("submitmsg", "Updated successfully!!");
        mv.setViewName(view);
        return mv;
    }

    @RequestMapping(value = "/correction_overdue_list", method = {RequestMethod.GET})
    public ModelAndView correction_overdue_list(HttpServletRequest request, Model model) {
        ModelAndView mv = new ModelAndView();
        String view = "correction_overdue_list";
        model.addAttribute("correctiondata", correctionDAO.getCorrectionListLongHalt());
        model.addAttribute("submitmsg", "Updated successfully!!");
        mv.setViewName(view);
        return mv;
    }

    @RequestMapping(value = "/correction_reject_list", method = {RequestMethod.GET})
    public ModelAndView correction_reject_list(HttpServletRequest request, Model model) {
        ModelAndView mv = new ModelAndView();
        String view = "correction_reject_list";
        model.addAttribute("correctiondata", correctionDAO.getCorrectionListReject());
        model.addAttribute("submitmsg", "Updated successfully!!");
        mv.setViewName(view);
        return mv;
    }

    @RequestMapping(value = "/correction_solve_list", method = {RequestMethod.GET})
    public ModelAndView correction_solve_list(HttpServletRequest request, Model model) {
        ModelAndView mv = new ModelAndView();
        String view = "correction_solve_list";
        model.addAttribute("correctiondata", correctionDAO.getCorrectionListDone());
        model.addAttribute("submitmsg", "Updated successfully!!");
        mv.setViewName(view);
        return mv;
    }

    @RequestMapping(value = "/correction_apply_list", method = {RequestMethod.GET})
    public ModelAndView correction_apply_list(HttpServletRequest request, Model model) {
        ModelAndView mv = new ModelAndView();
        String view = "correction_apply_list";
        model.addAttribute("correctiondata", correctionDAO.getCorrectionListApproved());
        model.addAttribute("submitmsg", "Updated successfully!!");
        mv.setViewName(view);
        return mv;
    }

    @RequestMapping(value = "/correction_compare", method = {RequestMethod.GET})
    public ModelAndView correctionCompareView(HttpServletRequest request, Model model, String propId, String applicationNo) {
        ModelAndView mv = new ModelAndView();
        String view = "correction_compare";
        CorrectionFormBean cb = correctionDAO.getCorrectionForm(propId, applicationNo);
        CorrectionFormLoadData propDetail = correctionDAO.getPropertyDetail(propId);
        model.addAttribute("correction", cb);
        model.addAttribute("correctionsproofs", correctionDAO.getCorrectionImageProof(propId, applicationNo));
        model.addAttribute("correctionstatus", correctionDAO.getCorrectionStatus(propId, applicationNo));
        model.addAttribute("actual", propDetail);
        model.addAttribute("actionHistory", correctionDAO.getCorrectionActionHistory(propId, applicationNo));
        mv.setViewName(view);
        correctionDAO.markCorrectionAsRead(propId, applicationNo);
        return mv;
    }

    @RequestMapping(value = "/correctionUpdateAction", method = {RequestMethod.POST})
    public ModelAndView correctionUpdateAction(HttpServletRequest request, Model model,
            @ModelAttribute(value = "correctionAction") CorrectionAction correctionAction) {
        ModelAndView mv = new ModelAndView();
        String view = "correction_compare";
        String msgForOwnerAddress = "";
        String msgForFloorUpdate = "";
        String msgForNewFloorAdd = "";
        String msgForDeleteFloor = "";
        String msg = "";
        String uid = "";
        String appno = "";

        LoginDetailsBean userData = (LoginDetailsBean) request.getSession().getAttribute("userDetailsBean");
        if (correctionAction.getCorrectionStatus() == null || correctionAction.getCorrectionStatus().isEmpty()) {
            model.addAttribute("submitmsg", "Invalid Action!!");
        } else if (correctionAction.getCorrectionRemarks() == null || correctionAction.getCorrectionRemarks().isEmpty()) {
            model.addAttribute("submitmsg", "Please add remarks!!");
        } else {
            if (correctionAction.getCorrectionStatus() != null
                    && correctionAction.getCorrectionStatus().equals("applied")) {
                // Call modification method;
                //System.out.println("id "+correctionAction.getPropertyId());
                //System.out.println("id "+correctionAction.getApplicationNo());
                String uniqueId = correctionAction.getPropertyId();
                String applicationNo = correctionAction.getApplicationNo();
                //System.out.println("correctionUpdateAction : 1");
                if (uniqueId != null && applicationNo != null) {
                    CorrectionRequestBean bean = correctionDAO.editPropertyId(uniqueId, applicationNo);
                    CorrectionFormSaveBean saveBean = correctionDAO.getUpdatedDataFromCorrectionForm(bean);
                    msg = correctionDAO.saveCorrectionFormData(saveBean);
                    msgForOwnerAddress = "Owner, address etc updated";

                }
                //System.out.println("correctionUpdateAction : 2");
                if (uniqueId != null && applicationNo != null) {
                    List<CorrectionFormFloorBean> floorList = correctionDAO.editPropertyFloorMultipleId(uniqueId, applicationNo);
                    //System.out.println("mutiple  "+floorList.size());
                    //System.out.println("correctionUpdateAction : 3");
                    if (floorList.size() > 0) {
                        Iterator itr = floorList.iterator();
                        while (itr.hasNext()) {
                            //System.out.println("correctionUpdateAction : 4.1");
                            CorrectionFormFloorBean floorBean = (CorrectionFormFloorBean) itr.next();
                            if (floorBean != null && floorBean.getEditData().equalsIgnoreCase("Y")) {
                                String msg1 = correctionDAO.saveCorrectionFormFloorData(floorBean);
                                msgForFloorUpdate = "Floor updated";
                            }
                            if (floorBean != null && floorBean.getEditData().equalsIgnoreCase("new")) {
                                String msg2 = correctionDAO.addNewCorrectionFormFloorData(floorBean);
                                msgForNewFloorAdd = "New floor added";
                            }
                            if (floorBean != null && floorBean.getDeleteData().equalsIgnoreCase("Y")) {
                                String msg3 = correctionDAO.deleteCorrectionFormFloorData(floorBean);
                                msgForDeleteFloor = "Floor deleted";
                            }
                            //System.out.println("correctionUpdateAction : 4.2");
                        }
                        //System.out.println("correctionUpdateAction : 5");
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
                        //System.out.println("correctionUpdateAction : 6");
                        silvassaService.appendToActionTracker(actionTracker);
                        //System.out.println("correctionUpdateAction : 7");
                        String result = taxService.generateTaxById(uniqueId, actionTracker);
                        //System.out.println("correctionUpdateAction : 8");
                        //System.out.println("result "+result);
                        if (result.equals("success")) {
                            actionTracker.setTaskStatus(MMIUtil.STATUS_COMPLETE);
                            msg = "Changes applied.";
                        } else {
                            actionTracker.setTaskStatus(MMIUtil.STATUS_ERROR);
                            msg = "Changes applied. But error in tax calculation.";
                        }

                        actionTracker.setCompleteTime(String.valueOf(MMIUtil.getEpocTime()));
                        //System.out.println("correctionUpdateAction : 9");
                        silvassaService.appendToActionTracker(actionTracker);
                        //System.out.println("correctionUpdateAction : 10");
                    }
                }
                //System.out.println("msgForFloorUpdate" +msgForFloorUpdate);
                //System.out.println("msgForNewFloorAdd" +msgForNewFloorAdd);
                //System.out.println("msgForDeleteFloor" +msgForDeleteFloor);
                MobileBean bean = correctionDAO.getMbileNo(uniqueId);
//                System.out.println(" id "+bean.getUniqueId());
//                System.out.println(" mobile "+bean.getMobileNo());
//                System.out.println("email "+bean.getEmailId());
//                System.out.println("uniqueId "+uniqueId);

                String msgForMobile = "Your request for property id " + uniqueId + " and application no. " + applicationNo + " has been processed. \nThanks Silvassa Municipal Council";
                String msgForMail = "Your request for property id " + uniqueId + " and application no. " + applicationNo + " has been processed. <br/>Thanks Silvassa Municipal Council";
                //OTPServiceImpl.generateOTPCorrectionUpdate(uniqueId, applicationNo, bean.getEmailId(), bean.getMobileNo(), msgForMobile,msgForMail);

                correctionDAO.addUserAction(correctionAction, userData);
                correctionDAO.updateCorrectionStatus(correctionAction);
            } else if (correctionAction.getCorrectionStatus() != null
                    && correctionAction.getCorrectionStatus().equals("reject")) {
                correctionDAO.addUserAction(correctionAction, userData);
                correctionDAO.updateCorrectionStatus(correctionAction);
                String uniqueId = correctionAction.getPropertyId();
                String applicationNo = correctionAction.getApplicationNo();
                MobileBean bean = correctionDAO.getMbileNo(uniqueId);
                String msgForMobile = "Your request for property id " + uniqueId + " and application no. " + applicationNo + " has been rejected. \nThanks Silvassa Municipal Council";
                String msgForMail = "Your request for property id " + uniqueId + " and application no. " + applicationNo + " has been rejected. <br/>Thanks Silvassa Municipal Council";
                OTPServiceImpl.generateOTPCorrectionUpdate(uniqueId, applicationNo, bean.getEmailId(), bean.getMobileNo(), msgForMobile,msgForMail);
            } else {
                correctionDAO.addUserAction(correctionAction, userData);
                correctionDAO.updateCorrectionStatus(correctionAction);
                msg = "Updated successfully!!";
            }

        }
        model.addAttribute("submitmsg", msg);
        mv.setViewName(view);

        return mv;
    }

    @RequestMapping(value = "/getBase64ImageProof", method = {RequestMethod.GET, RequestMethod.POST})
    @ResponseBody
    public String getBase64ImageProof(String propId, String applicationNo, String imgIdentifier) throws IOException {
        String encodedString = "";
        try {
            ImageBean doc = correctionDAO.getProofImage(propId, applicationNo, imgIdentifier);
            byte[] fileBytes = Base64.encodeBase64(doc.getImagebytes());
            if (fileBytes.length < 1) {
                throw new IOException();
            }
            encodedString = new String(fileBytes);
        } catch (Exception ex) {
            encodedString = "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";
        } finally {
        }
        return encodedString;
    }

    @RequestMapping(value = "/correction_alloffline_list", method = {RequestMethod.GET})
    public ModelAndView correctionOfflineAllList(HttpServletRequest request, Model model, int actionFliter) {
        ModelAndView mv = new ModelAndView();
        String view = "correction_alloffline_list";
        if (actionFliter > 12 || actionFliter < 1) {
            actionFliter = 1;
        }
        model.addAttribute("actionFliter", actionFliter);
        model.addAttribute("correctiondata", correctionDAO.getCorrectionOfflineListAll(actionFliter));
        mv.setViewName(view);
        return mv;
    }

    @RequestMapping(value = "/correction_compare_offline", method = {RequestMethod.GET})
    public ModelAndView correctionCompareOfflineView(HttpServletRequest request, Model model, String propId, String applicationNo) {
        ModelAndView mv = new ModelAndView();
        String view = "correction_compare_offline";
        model.addAttribute("correction", correctionDAO.getCorrectionOfflineForm(propId, applicationNo));
        mv.setViewName(view);
        return mv;
    }

    @RequestMapping(value = "edit/{id}/{applicationNo}")
    @ResponseBody
    public ModelAndView editPropertyId(@PathVariable("id") String id, @PathVariable("applicationNo") String applicationNo,
            Model model) {
        //System.out.println("ashok data edit" + id +" "+applicationNo);
        ModelAndView mv = new ModelAndView();
        CorrectionRequestBean bean = correctionDAO.editPropertyId(id, applicationNo);
        CorrectionFormSaveBean saveBean = correctionDAO.getUpdatedDataFromCorrectionForm(bean);
        //System.out.println("bean ask "+bean);
        String ss = correctionDAO.saveCorrectionFormData(saveBean);
        //List data = correctionDAO.LoadCorrectionData();
        //mv.setViewName("saveCorrectionFormData");
        // model.addAttribute("bean", data);

        return mv;

    }

    @RequestMapping(value = "editFloor/{floorId}/{pid}/{applicationNo}")
    @ResponseBody
    public ModelAndView editPropertyFloor(@PathVariable("floorId") String floorId, @PathVariable("pid") String uniqueId, @PathVariable("applicationNo") String applicationNo, Model model) {
        ModelAndView mv = new ModelAndView();
        //System.out.println("floorId " + floorId + " " + uniqueId+" "+applicationNo);
        CorrectionFormFloorBean floorBean = correctionDAO.editPropertyFloorId(floorId, uniqueId, applicationNo);
        //System.out.println("floorBean "+floorBean);
        String msg = correctionDAO.saveCorrectionFormFloorData(floorBean);
        //List data = correctionDAO.loadFloorData();
        //mv.setViewName("saveCorrectionFormFloorData");
        //model.addAttribute("bean", data);
        return mv;
    }

    @RequestMapping(value = "deleteFloor/{floorId}/{pid}/{applicationNo}")
    @ResponseBody
    public ModelAndView deletePropertyFloor(@PathVariable("floorId") String floorId, @PathVariable("pid") String uniqueId, @PathVariable("applicationNo") String applicationNo, Model model) {
        ModelAndView mv = new ModelAndView();
        //System.out.println("floorId " + floorId + " " + uniqueId+" "+applicationNo);
        //CorrectionFormFloorBean floorBean = commonService.editPropertyFloorId(floorId, uniqueId,applicationNo);
        List<CorrectionFormFloorBean> floorList = correctionDAO.editPropertyFloorMultipleId(uniqueId, applicationNo);
        if (floorList.size() > 0) {
            Iterator itr = floorList.iterator();
            while (itr.hasNext()) {
                CorrectionFormFloorBean floorBean = (CorrectionFormFloorBean) itr.next();
                String msg = correctionDAO.deleteCorrectionFormFloorData(floorBean);

            }
        }

        //List data = correctionDAO.loadFloorData();
        // mv.setViewName("saveCorrectionFormFloorData");
        //model.addAttribute("bean", data);
        return mv;
    }

    @RequestMapping(value = "addNewfloor/{floorId}/{pid}/{applicationNo}")
    @ResponseBody
    public ModelAndView addNewRowPropertyFloor(@PathVariable("floorId") String floorId, @PathVariable("pid") String uniqueId, @PathVariable("applicationNo") String applicationNo, Model model) {
        ModelAndView mv = new ModelAndView();
        //System.out.println("floorId " + floorId + " " + uniqueId+" "+applicationNo);
        List<CorrectionFormFloorBean> floorList = correctionDAO.editPropertyFloorMultipleId(uniqueId, applicationNo);
        //System.out.println("mutiple  "+floorList.size());
        if (floorList.size() > 0) {
            Iterator itr = floorList.iterator();
            while (itr.hasNext()) {
                CorrectionFormFloorBean floorBean = (CorrectionFormFloorBean) itr.next();
                String msg = correctionDAO.addNewCorrectionFormFloorData(floorBean);

            }

        }

        //List data = correctionDAO.loadFloorData();
        mv.setViewName("saveCorrectionFormFloorData");
        //model.addAttribute("bean", data);
        return mv;
    }

    @RequestMapping(value = "offLineToOnLineData", method = RequestMethod.GET)
    @ResponseBody
    public ModelAndView showOffLineToOnLine(String propertyId, String complainNo, Model model) {
        //System.out.println("ashok data" + id);
        ModelAndView mv = new ModelAndView();
        Gson g = new Gson();
        model.addAttribute("currentData", g.toJson(correctionDAO.getPropertyIdOffline(propertyId)));
        model.addAttribute("correctionFormBean", new CorrectionFormBean());
        model.addAttribute("propertyId", propertyId);
        model.addAttribute("correction", correctionDAO.getCorrectionOfflineForm(propertyId, complainNo));
        model.addAttribute("complainNo", complainNo);

        mv.setViewName("OffLineToOnLine");
        return mv;

    }

  @RequestMapping(value = "/submitCorrectionForm", method = RequestMethod.POST)
    public ModelAndView submitCorrectionForm(
            
            @ModelAttribute(value = "correctionForm") CorrectionFormBean correctionForm, Model model
    ) throws IOException {

        System.out.println("correctionForm getComplainNo : " + correctionForm.getComplainNo());
        String msg = "";
          
//        CorrectionFormBean beanH = new CorrectionFormBean();
        //System.out.println("uniqueId "+uniqueId);
        ModelAndView mv = new ModelAndView();
        List<CorrectionFormFloorBean> floorDetailData = new ArrayList<CorrectionFormFloorBean>();
        try {
//        

            Calendar cal = Calendar.getInstance();
            Date date = cal.getTime();
            DateFormat dateFormat = new SimpleDateFormat("dd-MM-yyyy HH:mm:ss");
            String formattedDate = dateFormat.format(date);
            correctionForm.setNoticeDate(formattedDate);
            String ipAddress = request.getHeader("X-FORWARDED-FOR");
            if (ipAddress == null) {
                ipAddress = request.getRemoteAddr();
            }
            String loc = correctionForm.getLocName();
            if (loc != null && loc.equalsIgnoreCase("Other")) {
                if (correctionForm.getLocNameOther() != null) {
                    correctionForm.setLocName(correctionForm.getLocNameOther().trim());
                }
            }
            correctionForm.setIpAddress(ipAddress);
            correctionForm.setPermissionData("N");
            if (null != correctionForm.getImageFile() && !correctionForm.getImageFile().isEmpty()) {
                correctionForm.setImageFileName(correctionForm.getImageFile().getOriginalFilename());
                correctionForm.setImage_byte(correctionForm.getImageFile().getBytes());
            }
            if (null != correctionForm.getImageFile1() && !correctionForm.getImageFile1().isEmpty()) {
                correctionForm.setImageFilenameOccupier(correctionForm.getImageFile1().getOriginalFilename());
                correctionForm.setImage_occupier(correctionForm.getImageFile1().getBytes());
            }
            if (null != correctionForm.getImageFile2() && !correctionForm.getImageFile2().isEmpty()) {
                correctionForm.setImageFilenameAddress(correctionForm.getImageFile2().getOriginalFilename());
                correctionForm.setImage_addressr(correctionForm.getImageFile2().getBytes());
            }
            if (null != correctionForm.getImageFile3() && !correctionForm.getImageFile3().isEmpty()) {
                correctionForm.setImageFilenmaeElectric(correctionForm.getImageFile3().getOriginalFilename());
                correctionForm.setImage_electric(correctionForm.getImageFile3().getBytes());
            }
            if (null != correctionForm.getImageFileOwner() && !correctionForm.getImageFileOwner().isEmpty()) {
                correctionForm.setImageFileOwner2(correctionForm.getImageFileOwner().getOriginalFilename());
                correctionForm.setImage_owner2(correctionForm.getImageFileOwner().getBytes());

            }
            if (null != correctionForm.getImageFileOccupier() && !correctionForm.getImageFileOccupier().isEmpty()) {
                correctionForm.setImageFileOccupier2(correctionForm.getImageFileOccupier().getOriginalFilename());
                correctionForm.setImage_occupier2(correctionForm.getImageFileOccupier().getBytes());
            }
            if (null != correctionForm.getImageFileAddress() && !correctionForm.getImageFileAddress().isEmpty()) {
                correctionForm.setImageFileAddress2(correctionForm.getImageFileAddress().getOriginalFilename());
                correctionForm.setImage_address2(correctionForm.getImageFileAddress().getBytes());
            }
            if (null != correctionForm.getImageFileCovered() && !correctionForm.getImageFileCovered().isEmpty()) {
                correctionForm.setImageFileNameCovered(correctionForm.getImageFileCovered().getOriginalFilename());
                correctionForm.setImage_covered(correctionForm.getImageFileCovered().getBytes());
            }
            if (null != correctionForm.getImagePropertyUse() && !correctionForm.getImagePropertyUse().isEmpty()) {
                correctionForm.setImageFilePropertyUse(correctionForm.getImagePropertyUse().getOriginalFilename());
                correctionForm.setImage_property_use(correctionForm.getImagePropertyUse().getBytes());
            }
            if (null != correctionForm.getImageFileArrear() && !correctionForm.getImageFileArrear().isEmpty()) {
                correctionForm.setImageFileNameArrear(correctionForm.getImageFileArrear().getOriginalFilename());
                correctionForm.setImage_arrear(correctionForm.getImageFileArrear().getBytes());
            }

            if (null != correctionForm.getImageFileOwner2Data() && !correctionForm.getImageFileOwner2Data().isEmpty()) {
                correctionForm.setImageFileOwnerData2(correctionForm.getImageFileOwner2Data().getOriginalFilename());
                correctionForm.setImage_owner2Data(correctionForm.getImageFileOwner2Data().getBytes());
            }
            if (null != correctionForm.getImageFileOwner3Data() && !correctionForm.getImageFileOwner3Data().isEmpty()) {
                correctionForm.setImageFileOwnerData3(correctionForm.getImageFileOwner3Data().getOriginalFilename());
                correctionForm.setImage_owner3Data(correctionForm.getImageFileOwner3Data().getBytes());
            }
            if (null != correctionForm.getImageFileOccupier1Data() && !correctionForm.getImageFileOccupier1Data().isEmpty()) {
                correctionForm.setImageFileNameccupier1Data(correctionForm.getImageFileOccupier1Data().getOriginalFilename());
                correctionForm.setImage_occupier2Data(correctionForm.getImageFileOccupier1Data().getBytes());
            }
            if (null != correctionForm.getImageFileOccupier2Data() && !correctionForm.getImageFileOccupier2Data().isEmpty()) {
                correctionForm.setImageFileNameOccupier2Data(correctionForm.getImageFileOccupier2Data().getOriginalFilename());
                correctionForm.setImage_occupier3Data(correctionForm.getImageFileOccupier2Data().getBytes());
            }
            if (null != correctionForm.getImageFileAddress1Data() && !correctionForm.getImageFileAddress1Data().isEmpty()) {
                correctionForm.setImageFileNameAddress1(correctionForm.getImageFileAddress1Data().getOriginalFilename());
                correctionForm.setImage_address3(correctionForm.getImageFileAddress1Data().getBytes());
            }
            if (null != correctionForm.getImageFileAddress2Data() && !correctionForm.getImageFileAddress2Data().isEmpty()) {
                correctionForm.setImageFileNameAddress2(correctionForm.getImageFileAddress2Data().getOriginalFilename());
                correctionForm.setImage_address4(correctionForm.getImageFileAddress2Data().getBytes());
            }

            String propId = correctionForm.getUniqueId();
            CorrectionFormHitLogger hitLogger = new CorrectionFormHitLogger();
            hitLogger.setIpaddress(ipAddress);
            hitLogger.setPropId(propId);
            //hitLogger.setToURL("elaborate");

            correctionDAO.addCorrectionFormHitLogger(hitLogger);
            int applicationNo = (hitLogger.getId());

            correctionForm.setApplication_no(new Integer(applicationNo).toString());

//        }
            msg = correctionDAO.addCorrectionFormData(correctionForm);
                  
            //System.out.println("correctionForm.getFloorDetails : " + correctionForm.getFloorDetails().size());
            List<CorrectionFormFloorBean> floorData = correctionForm.getFloorDetails();
            for (CorrectionFormFloorBean fData : floorData) {

                String propertyFloorId = fData.getPropertyFloorId();
                String uid = propId;
                String floorType = fData.getFloorType();
                String coveredArea = fData.getCarpetArea();
                String propertyUse = fData.getPropertyUse();
                String propertySubType = fData.getPropertySubType();
                String constructiontype = fData.getConstructionType();
                String selfRent = fData.getSelfRent();
                String rentedValue = fData.getRentedValue();
                String editData = fData.getEditData();
                String deletData = fData.getDeleteData();
                if (editData != null) {
                    CorrectionFormFloorBean floorbean = new CorrectionFormFloorBean();
                    //System.out.println("inside if");

                    floorbean.setPropertyFloorId(propertyFloorId);
                    floorbean.setUniqueId(uid);
                    floorbean.setFloorType(floorType);
                    floorbean.setCarpetArea(coveredArea);
                    floorbean.setPropertyUse(propertyUse);
                    floorbean.setPropertySubType(propertySubType);
                    floorbean.setConstructionType(constructiontype);
                    floorbean.setSelfRent(selfRent);
                    floorbean.setRentedValue(rentedValue);
                    floorbean.setEditData(editData);
                    floorbean.setDeleteData(deletData);
                    floorbean.setPermissionData("N");
                    floorbean.setStatus("N");
                    floorbean.setApplication_no(new Integer(applicationNo).toString());
                    floorDetailData.add(floorbean);

                    String msgFloor = correctionDAO.addCorrectionFormDataFloor(floorbean);
                } else if (deletData != null) {
                    CorrectionFormFloorBean floorbean = new CorrectionFormFloorBean();
                    //System.out.println("inside if");

                    floorbean.setPropertyFloorId(propertyFloorId);
                    floorbean.setUniqueId(uid);
                    floorbean.setFloorType(floorType);
                    floorbean.setCarpetArea(coveredArea);
                    floorbean.setPropertyUse(propertyUse);
                    floorbean.setPropertySubType(propertySubType);
                    floorbean.setConstructionType(constructiontype);
                    floorbean.setSelfRent(selfRent);
                    floorbean.setRentedValue(rentedValue);
                    floorbean.setEditData(editData);
                    floorbean.setDeleteData(deletData);
                    floorbean.setPermissionData("N");
                    floorbean.setStatus("N");
                    floorbean.setApplication_no(new Integer(applicationNo).toString());
                    floorDetailData.add(floorbean);
                    String msgFloor = correctionDAO.addCorrectionFormDataFloor(floorbean);
                } else {
                    String msgFloor = "Floor data not updated";
                }
//                if((editData!=null && editData.equalsIgnoreCase("Y")) || (editData.equalsIgnoreCase("new"))
//                        || (deletData !=null) || (deletData.equalsIgnoreCase("Y"))){
//                  CorrectionFormFloorBean floorbean=new CorrectionFormFloorBean();
//                  System.out.println("inside if");
//                        
//                floorbean.setPropertyFloorId(propertyFloorId);
//                floorbean.setUniqueId(uid);
//                floorbean.setFloorType(floorType);
//                floorbean.setCarpetArea(coveredArea);
//                floorbean.setPropertyUse(propertyUse);
//                floorbean.setPropertySubType(propertySubType);
//                floorbean.setConstructionType(constructiontype);
//                floorbean.setSelfRent(selfRent);
//                floorbean.setRentedValue(rentedValue);
//                floorbean.setEditData(editData);
//                floorbean.setDeleteData(deletData);
//                String msgFloor=commonService.addCorrectionFormDataFloor(floorbean);  
//                }else{
//                  String msgFloor="Floor data not updated";  
//                }

                //System.out.println("fData : " + fData.getPropertyFloorId());
            }
            
            String msggg=correctionDAO.updateOfflineToOnlineRefNo(propId,correctionForm.getApplication_no(),correctionForm.getComplainNo());
            request.setAttribute("MSG", msg);
            model.addAttribute("bean", correctionForm);
            model.addAttribute("floorbean", floorDetailData);
            //System.out.println("image "+beanH.getImageFileName());
           

            // mv.addObject(msg);
            mv.setViewName("success");

        } catch (Exception e) {
            e.printStackTrace();
        }
        //System.out.println("Path "+path);

        //System.out.println("last "+msg);
        return mv;

    }  

    @RequestMapping(value = "/loadLocalityWard", method = {RequestMethod.GET, RequestMethod.POST})
    @ResponseBody
    public String loadLocality(String ward) throws IOException {
        String msg = "";
        try {
            msg = correctionDAO.loadLocality(ward);
            //System.out.println("ward data " + msg);

        } catch (Exception ex) {

        } finally {
        }
        return msg;
    }
    
    
     @RequestMapping(value = "/userRegistrationAdmin", method = {RequestMethod.GET})
    public ModelAndView userRegistrationAdmin(HttpServletRequest request, Model model) {
        ModelAndView mv = new ModelAndView();
        String view = "userRegistrationAdmin";
        model.addAttribute("requestdata", correctionDAO.getAllRegisterationReq());
        mv.setViewName(view);

        return mv;
    }

    @RequestMapping(value = "/userRegistrationAdminForm", method = {RequestMethod.GET})
    public ModelAndView userRegistrationAdmin(HttpServletRequest request, Model model, String sno,String pid) {
        ModelAndView mv = new ModelAndView();
        System.out.println("uid "+pid);
        String view = "userRegistrationAdminForm";
        model.addAttribute("requestdata", correctionDAO.getRegisterationReq(sno));
        model.addAttribute("ownerdata", correctionDAO.getRegisterationForMobile(pid));
        mv.setViewName(view);

        return mv;
    }

    @RequestMapping(value = "/updateUserRegistration", method = {RequestMethod.POST})
    @ResponseBody
    public ModelAndView updateUserRegistration(HttpServletRequest request, Model model, Integer sno, String remarks,String emailId,String mobileNo,
            String uniqueId) {
           
       int result =  correctionDAO.updateUserRegistrationStatus(sno, remarks, "update");
       String updateMsg=correctionDAO.saveMobile(uniqueId, mobileNo, emailId);
       //System.out.println("reult "+result);
       //System.out.println("mobile "+mobileNo+" "+emailId);
        if(result == 1){
               String msgForMobile = "Your request to update contact details has been processed successfully. \nThanks Silvassa Municipal Council";
               String msgForEmail = "Your request to update contact details has been processed successfully. <br/>Thanks Silvassa Municipal Council";
               OTPServiceImpl.generateOTPCorrectionUpdate(sno.toString(), uniqueId,emailId, mobileNo, msgForMobile,msgForEmail); 
          }
        ModelAndView mv = new ModelAndView();
        String view = "userRegistrationAdminForm";
        model.addAttribute("requestdata", correctionDAO.getAllRegisterationReq());
        model.addAttribute("message", "Request processed.");
        mv.setViewName(view);

        return mv;
        
     
    }

    @RequestMapping(value = "/discardUserRegistration", method = {RequestMethod.POST})
    @ResponseBody
    public ModelAndView discardUserRegistration(HttpServletRequest request, Model model, Integer sno, String remarks,String emailId,String mobileNo,
            String uniqueId) {
        
      int result =   correctionDAO.updateUserRegistrationStatus(sno, remarks, "discard");
      
      if(result == 1){
               String msgForMobile = "Your request to update contact details has been rejected. Reason," + remarks + ". \nThanks Silvassa Municipal Council";
               String msgForEmail = "Your request to update contact details has been rejected. Reason," + remarks + ". <br/>Thanks Silvassa Municipal Council"; 
               OTPServiceImpl.generateOTPCorrectionUpdate(sno.toString(), uniqueId,emailId, mobileNo, msgForMobile,msgForEmail); 
          }
        ModelAndView mv = new ModelAndView();
        String view = "userRegistrationAdminForm";
        model.addAttribute("requestdata", correctionDAO.getAllRegisterationReq());
        model.addAttribute("message", "Request rejected.");
        mv.setViewName(view);

        return mv;

        
    }
@RequestMapping(value = "/correctionFormReport", method = {RequestMethod.GET})
    public ModelAndView correctionFormReport(HttpServletRequest request, Model model) {
        ModelAndView mv = new ModelAndView();
        String view = "correctionFormReport";
        mv.setViewName(view);
        return mv;
    } 
    
    @RequestMapping(value = "/exportCorrectionFormReport", method = {RequestMethod.POST})
    public ModelAndView exportCorrectionFormReport(HttpServletRequest request, Model model) {
        ModelAndView mv = new ModelAndView();
        String data=request.getParameter("report");
        //System.out.println("data "+data);
        //System.out.println("exportCorrectionFormReport");
        
        if(data!=null && (data.equalsIgnoreCase("owner"))){
            List<CorrectionFormSaveBean> listReport= correctionDAO.getCorrectionReport(data);
            Map<String,CorrectionFormReport> mapdata= correctionDAO.getCorrectionReportFromPropertyDetail(data);
            mv.addObject("listReport",listReport);
            mv.addObject("mapdata",mapdata);
            //System.out.println("listReport "+listReport.size());
            //System.out.println("mapdata "+mapdata.size());
        }else if(data!=null &&  data.equalsIgnoreCase("address")){
            List<CorrectionFormSaveBean> listReport= correctionDAO.getCorrectionReport(data);
            Map<String,CorrectionFormReport> mapdata= correctionDAO.getCorrectionReportFromPropertyDetail(data);
            mv.addObject("listReport",listReport);
            mv.addObject("mapdata",mapdata);
        }else if(data!=null &&  data.equalsIgnoreCase("arrear")){
            List<CorrectionFormSaveBean> listReport= correctionDAO.getCorrectionReport(data);
            Map<String,CorrectionFormReport> mapdata= correctionDAO.getCorrectionReportFromPropertyDetail(data);
            mv.addObject("listReport",listReport);
            mv.addObject("mapdata",mapdata);
        }else if(data!=null &&  data.equalsIgnoreCase("smchouseno")){
            List<CorrectionFormSaveBean> listReport= correctionDAO.getCorrectionReport(data);
            Map<String,CorrectionFormReport> mapdata= correctionDAO.getCorrectionReportFromPropertyDetail(data);
            mv.addObject("listReport",listReport);
            mv.addObject("mapdata",mapdata);
        }else if(data!=null && data.equalsIgnoreCase("floor")){
           List<CorrectionFormFloorBean>listFloorReport= correctionDAO.getCorrectionReportFloor();
           Map<String,ArrayList<CorrectionFormFloorReport>> mapFloorData=correctionDAO.getCorrectionReportFromPropertyFloor();
           mv.addObject("listFloorReport",listFloorReport);
           mv.addObject("mapFloorData",mapFloorData);
        }else if(data!=null && data.equalsIgnoreCase("combinereport")){
            List<CorrectionFormSaveBean> listReport= correctionDAO.getCorrectionReport(data);
            Map<String,CorrectionFormReport> mapdata= correctionDAO.getCorrectionReportFromPropertyDetail(data);
            logger.info("combinereport controller listReport.size() "+listReport.size()); 
            logger.info("combinereport controller mapdata.size() "+mapdata.size()); 
            
            //System.out.println("listReport "+listReport.size());
            //System.out.println("mapdata "+mapdata.size());
            mv.addObject("listReport",listReport);
            mv.addObject("mapdata",mapdata);
            Map<String,ArrayList<CorrectionFormFloorBean>> mapCorrectionFloorData= correctionDAO.getCorrectionReportFloorCombine();
            Map<String,ArrayList<CorrectionFormFloorReport>> mapFloorData=correctionDAO.getCorrectionReportFromPropertyFloorCombine();
            //System.out.println("mapCorrectionFloorData"+mapCorrectionFloorData.size());
            //System.out.println("mapFloorData"+mapFloorData.size());
            logger.info("combinereport controller floor1  listReport.size() "+mapCorrectionFloorData.size()); 
            logger.info("combinereport controller floor2 mapdata.size() "+mapFloorData.size()); 
            
            mv.addObject("mapCorrectionFloorData",mapCorrectionFloorData);
            mv.addObject("mapFloorSurveyData",mapFloorData);
            
            
        }else if(data!=null && data.equalsIgnoreCase("combineowner")){
            List<CorrectionFormSaveBean> listReport= correctionDAO.getCorrectionReport(data);
            Map<String,CorrectionFormReport> mapdata= correctionDAO.getCorrectionReportFromPropertyDetail(data);
            //System.out.println("listReport "+listReport.size());
            //System.out.println("mapdata "+mapdata.size());
            logger.info("combineowner controller listReport.size() "+listReport.size()); 
            logger.info("combineowner controller mapdata.size() "+mapdata.size()); 
            
            mv.addObject("listReport",listReport);
            mv.addObject("mapdata",mapdata);
            
        }
        
        
//       Set set = mapFloorData.entrySet();
//      Iterator iterator = set.iterator();
//      while(iterator.hasNext()) {
//         Map.Entry mentry = (Map.Entry)iterator.next();
//         ArrayList<CorrectionFormFloorReport> fr=(ArrayList<CorrectionFormFloorReport>)mentry.getValue();
//         CorrectionFormFloorReport fff=null;
//         Iterator itr=fr.iterator();
//         while(itr.hasNext()){
//             fff=(CorrectionFormFloorReport)itr.next();
//             //System.out.print("key is: "+ mentry.getKey() + " & Value is: ");
//             //System.out.println(fff.getFloorType());
//         }
//         
//      }
        
        
        
//        ArrayList<CorrectionFormFloorReport> rttt=mapFloorData.get("S05023975000");
//        Iterator rr=rttt.iterator();
//        while(rr.hasNext()){
//          CorrectionFormFloorReport pp=(CorrectionFormFloorReport)rr.next();
//           //System.out.println(" check  "+pp.getFloorType()+" "+pp.getUniqueId());
//        }
//       
        //System.out.println("mapdata "+mapdata.size());
        String view = "CorrectionFormReportExcelView";
        
        mv.addObject("reportType",data);
        
        mv.setViewName(view);
        return mv;
    }  
  @ResponseBody
  @RequestMapping(value = "/updateBuildingUse", method = {RequestMethod.GET})
    public String updateBuildingUse() {
         System.out.println("update Buildinguse...... ");
        String msg=correctionDAO.updateBuildingUse() ;
        System.out.println("update Buildinguse "+msg);
        return msg;
    }
    @RequestMapping(value = "/propertySplit", method = {RequestMethod.GET})
    public ModelAndView propertySplit(HttpServletRequest request, Model model) {
        ModelAndView mv = new ModelAndView();
        String view = "PropertySplit";
        mv.setViewName(view);
        return mv;
    }
   
    
    @RequestMapping(value = "/propertySplitData", method = {RequestMethod.POST})
    public ModelAndView propertySplitData(HttpServletRequest request, Model model) {
        ModelAndView mv = new ModelAndView();
        String view="";
        String propertyId=request.getParameter("propertyId").toUpperCase();
        String newPropertyId=request.getParameter("newPropertyId").toUpperCase();
        String propertyType=request.getParameter("propertyType");
        String msg=correctionDAO.propertySplit(propertyId,newPropertyId,propertyType);
        request.setAttribute("MSG", msg);
        request.setAttribute("PID", propertyId);
        request.setAttribute("NEWPID", newPropertyId);
        if(msg.equalsIgnoreCase("Property id exist")){
            view="PropertySplit";
        }else{
           view="redirect:/propertySplitAddRow?pid="+newPropertyId; 
        }
        
        //String view = "PropertySplit";
        mv.setViewName(view);
        return mv;
    } 
    
    @RequestMapping(value = "/propertySplitAddRow", method = {RequestMethod.GET})
    public ModelAndView propertySplitAddRow(HttpServletRequest request, Model model) {
        ModelAndView mv = new ModelAndView();
        String propertyId=request.getParameter("pid");
        
        //String msg=correctionDAO.propertySplit(propertyId,newPropertyId,propertyType);
        //request.setAttribute("MSG", msg);
        //request.setAttribute("PID", propertyId);
        //request.setAttribute("NEWPID", newPropertyId);
        Gson g = new Gson();
        model.addAttribute("currentData", g.toJson(correctionDAO.getPropertyIdOffline(propertyId)));
        model.addAttribute("correctionFormBean", new CorrectionFormBean());
        model.addAttribute("propertyId", propertyId);
        //model.addAttribute("correction", correctionDAO.getCorrectionOfflineForm(propertyId, complainNo));
        //model.addAttribute("complainNo", complainNo);
        
        String view = "PropertySplitCorrectionForm";
        mv.setViewName(view);
        return mv;
    } 

 @RequestMapping(value = "/submitCorrectionFormPropertySplit", method = RequestMethod.POST)
    public ModelAndView submitCorrectionFormPropertySplit(
            
            @ModelAttribute(value = "correctionForm") CorrectionFormBean correctionForm, Model model
    ) throws IOException {

        System.out.println("correctionForm getComplainNo : " + correctionForm.getComplainNo());
        String msg = "";
          
//        CorrectionFormBean beanH = new CorrectionFormBean();
        //System.out.println("uniqueId "+uniqueId);
        ModelAndView mv = new ModelAndView();
        List<CorrectionFormFloorBean> floorDetailData = new ArrayList<CorrectionFormFloorBean>();
        try {
//        

            Calendar cal = Calendar.getInstance();
            Date date = cal.getTime();
            DateFormat dateFormat = new SimpleDateFormat("dd-MM-yyyy HH:mm:ss");
            String formattedDate = dateFormat.format(date);
            correctionForm.setNoticeDate(formattedDate);
            String ipAddress = request.getHeader("X-FORWARDED-FOR");
            if (ipAddress == null) {
                ipAddress = request.getRemoteAddr();
            }
            String loc = correctionForm.getLocName();
            if (loc != null && loc.equalsIgnoreCase("Other")) {
                if (correctionForm.getLocNameOther() != null) {
                    correctionForm.setLocName(correctionForm.getLocNameOther().trim());
                }
            }
            correctionForm.setIpAddress(ipAddress);
            correctionForm.setPermissionData("N");
            if (null != correctionForm.getImageFile() && !correctionForm.getImageFile().isEmpty()) {
                correctionForm.setImageFileName(correctionForm.getImageFile().getOriginalFilename());
                correctionForm.setImage_byte(correctionForm.getImageFile().getBytes());
            }
            if (null != correctionForm.getImageFile1() && !correctionForm.getImageFile1().isEmpty()) {
                correctionForm.setImageFilenameOccupier(correctionForm.getImageFile1().getOriginalFilename());
                correctionForm.setImage_occupier(correctionForm.getImageFile1().getBytes());
            }
            if (null != correctionForm.getImageFile2() && !correctionForm.getImageFile2().isEmpty()) {
                correctionForm.setImageFilenameAddress(correctionForm.getImageFile2().getOriginalFilename());
                correctionForm.setImage_addressr(correctionForm.getImageFile2().getBytes());
            }
            if (null != correctionForm.getImageFile3() && !correctionForm.getImageFile3().isEmpty()) {
                correctionForm.setImageFilenmaeElectric(correctionForm.getImageFile3().getOriginalFilename());
                correctionForm.setImage_electric(correctionForm.getImageFile3().getBytes());
            }
            if (null != correctionForm.getImageFileOwner() && !correctionForm.getImageFileOwner().isEmpty()) {
                correctionForm.setImageFileOwner2(correctionForm.getImageFileOwner().getOriginalFilename());
                correctionForm.setImage_owner2(correctionForm.getImageFileOwner().getBytes());

            }
            if (null != correctionForm.getImageFileOccupier() && !correctionForm.getImageFileOccupier().isEmpty()) {
                correctionForm.setImageFileOccupier2(correctionForm.getImageFileOccupier().getOriginalFilename());
                correctionForm.setImage_occupier2(correctionForm.getImageFileOccupier().getBytes());
            }
            if (null != correctionForm.getImageFileAddress() && !correctionForm.getImageFileAddress().isEmpty()) {
                correctionForm.setImageFileAddress2(correctionForm.getImageFileAddress().getOriginalFilename());
                correctionForm.setImage_address2(correctionForm.getImageFileAddress().getBytes());
            }
            if (null != correctionForm.getImageFileCovered() && !correctionForm.getImageFileCovered().isEmpty()) {
                correctionForm.setImageFileNameCovered(correctionForm.getImageFileCovered().getOriginalFilename());
                correctionForm.setImage_covered(correctionForm.getImageFileCovered().getBytes());
            }
            if (null != correctionForm.getImagePropertyUse() && !correctionForm.getImagePropertyUse().isEmpty()) {
                correctionForm.setImageFilePropertyUse(correctionForm.getImagePropertyUse().getOriginalFilename());
                correctionForm.setImage_property_use(correctionForm.getImagePropertyUse().getBytes());
            }
            if (null != correctionForm.getImageFileArrear() && !correctionForm.getImageFileArrear().isEmpty()) {
                correctionForm.setImageFileNameArrear(correctionForm.getImageFileArrear().getOriginalFilename());
                correctionForm.setImage_arrear(correctionForm.getImageFileArrear().getBytes());
            }

            if (null != correctionForm.getImageFileOwner2Data() && !correctionForm.getImageFileOwner2Data().isEmpty()) {
                correctionForm.setImageFileOwnerData2(correctionForm.getImageFileOwner2Data().getOriginalFilename());
                correctionForm.setImage_owner2Data(correctionForm.getImageFileOwner2Data().getBytes());
            }
            if (null != correctionForm.getImageFileOwner3Data() && !correctionForm.getImageFileOwner3Data().isEmpty()) {
                correctionForm.setImageFileOwnerData3(correctionForm.getImageFileOwner3Data().getOriginalFilename());
                correctionForm.setImage_owner3Data(correctionForm.getImageFileOwner3Data().getBytes());
            }
            if (null != correctionForm.getImageFileOccupier1Data() && !correctionForm.getImageFileOccupier1Data().isEmpty()) {
                correctionForm.setImageFileNameccupier1Data(correctionForm.getImageFileOccupier1Data().getOriginalFilename());
                correctionForm.setImage_occupier2Data(correctionForm.getImageFileOccupier1Data().getBytes());
            }
            if (null != correctionForm.getImageFileOccupier2Data() && !correctionForm.getImageFileOccupier2Data().isEmpty()) {
                correctionForm.setImageFileNameOccupier2Data(correctionForm.getImageFileOccupier2Data().getOriginalFilename());
                correctionForm.setImage_occupier3Data(correctionForm.getImageFileOccupier2Data().getBytes());
            }
            if (null != correctionForm.getImageFileAddress1Data() && !correctionForm.getImageFileAddress1Data().isEmpty()) {
                correctionForm.setImageFileNameAddress1(correctionForm.getImageFileAddress1Data().getOriginalFilename());
                correctionForm.setImage_address3(correctionForm.getImageFileAddress1Data().getBytes());
            }
            if (null != correctionForm.getImageFileAddress2Data() && !correctionForm.getImageFileAddress2Data().isEmpty()) {
                correctionForm.setImageFileNameAddress2(correctionForm.getImageFileAddress2Data().getOriginalFilename());
                correctionForm.setImage_address4(correctionForm.getImageFileAddress2Data().getBytes());
            }

            String propId = correctionForm.getUniqueId();
            CorrectionFormHitLogger hitLogger = new CorrectionFormHitLogger();
            hitLogger.setIpaddress(ipAddress);
            hitLogger.setPropId(propId);
            //hitLogger.setToURL("elaborate");

            correctionDAO.addCorrectionFormHitLogger(hitLogger);
            int applicationNo = (hitLogger.getId());

            correctionForm.setApplication_no(new Integer(applicationNo).toString());

//        }
            msg = correctionDAO.addCorrectionFormData(correctionForm);
                  
            //System.out.println("correctionForm.getFloorDetails : " + correctionForm.getFloorDetails().size());
            List<CorrectionFormFloorBean> floorData = correctionForm.getFloorDetails();
            for (CorrectionFormFloorBean fData : floorData) {

                String propertyFloorId = fData.getPropertyFloorId();
                String uid = propId;
                String floorType = fData.getFloorType();
                String coveredArea = fData.getCarpetArea();
                String propertyUse = fData.getPropertyUse();
                String propertySubType = fData.getPropertySubType();
                String constructiontype = fData.getConstructionType();
                String selfRent = fData.getSelfRent();
                String rentedValue = fData.getRentedValue();
                String editData = fData.getEditData();
                String deletData = fData.getDeleteData();
                if (editData != null) {
                    CorrectionFormFloorBean floorbean = new CorrectionFormFloorBean();
                    //System.out.println("inside if");

                    floorbean.setPropertyFloorId(propertyFloorId);
                    floorbean.setUniqueId(uid);
                    floorbean.setFloorType(floorType);
                    floorbean.setCarpetArea(coveredArea);
                    floorbean.setPropertyUse(propertyUse);
                    floorbean.setPropertySubType(propertySubType);
                    floorbean.setConstructionType(constructiontype);
                    floorbean.setSelfRent(selfRent);
                    floorbean.setRentedValue(rentedValue);
                    floorbean.setEditData(editData);
                    floorbean.setDeleteData(deletData);
                    floorbean.setPermissionData("N");
                    floorbean.setStatus("N");
                    floorbean.setApplication_no(new Integer(applicationNo).toString());
                    floorDetailData.add(floorbean);

                    String msgFloor = correctionDAO.addCorrectionFormDataFloor(floorbean);
                } else if (deletData != null) {
                    CorrectionFormFloorBean floorbean = new CorrectionFormFloorBean();
                    //System.out.println("inside if");

                    floorbean.setPropertyFloorId(propertyFloorId);
                    floorbean.setUniqueId(uid);
                    floorbean.setFloorType(floorType);
                    floorbean.setCarpetArea(coveredArea);
                    floorbean.setPropertyUse(propertyUse);
                    floorbean.setPropertySubType(propertySubType);
                    floorbean.setConstructionType(constructiontype);
                    floorbean.setSelfRent(selfRent);
                    floorbean.setRentedValue(rentedValue);
                    floorbean.setEditData(editData);
                    floorbean.setDeleteData(deletData);
                    floorbean.setPermissionData("N");
                    floorbean.setStatus("N");
                    floorbean.setApplication_no(new Integer(applicationNo).toString());
                    floorDetailData.add(floorbean);
                    String msgFloor = correctionDAO.addCorrectionFormDataFloor(floorbean);
                } else {
                    String msgFloor = "Floor data not updated";
                }
//                if((editData!=null && editData.equalsIgnoreCase("Y")) || (editData.equalsIgnoreCase("new"))
//                        || (deletData !=null) || (deletData.equalsIgnoreCase("Y"))){
//                  CorrectionFormFloorBean floorbean=new CorrectionFormFloorBean();
//                  System.out.println("inside if");
//                        
//                floorbean.setPropertyFloorId(propertyFloorId);
//                floorbean.setUniqueId(uid);
//                floorbean.setFloorType(floorType);
//                floorbean.setCarpetArea(coveredArea);
//                floorbean.setPropertyUse(propertyUse);
//                floorbean.setPropertySubType(propertySubType);
//                floorbean.setConstructionType(constructiontype);
//                floorbean.setSelfRent(selfRent);
//                floorbean.setRentedValue(rentedValue);
//                floorbean.setEditData(editData);
//                floorbean.setDeleteData(deletData);
//                String msgFloor=commonService.addCorrectionFormDataFloor(floorbean);  
//                }else{
//                  String msgFloor="Floor data not updated";  
//                }

                //System.out.println("fData : " + fData.getPropertyFloorId());
            }
            
            String msggg=correctionDAO.updateOfflineToOnlineRefNo(propId,correctionForm.getApplication_no(),correctionForm.getComplainNo());
            request.setAttribute("MSG", msg);
            model.addAttribute("bean", correctionForm);
            model.addAttribute("floorbean", floorDetailData);
            //System.out.println("image "+beanH.getImageFileName());
           

            // mv.addObject(msg);
            mv.setViewName("success");

        } catch (Exception e) {
            e.printStackTrace();
        }
        //System.out.println("Path "+path);

        //System.out.println("last "+msg);
        return mv;

    }  

 
   
    
    
}
