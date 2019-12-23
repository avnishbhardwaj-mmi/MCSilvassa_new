package com.silvassa.controller;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.itextpdf.text.Document;
import com.itextpdf.text.DocumentException;
import com.itextpdf.text.Paragraph;
import com.itextpdf.text.pdf.PdfWriter;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.silvassa.bean.FilterBean;
import com.silvassa.bean.LoginDetailsBean;
import com.silvassa.bean.PrivateNoticeBean;
import com.silvassa.dao.OTPDaoImpl;
import com.silvassa.service.SilvassaService;
import com.silvassa.model.ActionTracker;
import com.silvassa.model.BankMaster;
import com.silvassa.model.CorrectionFormFloorBean;
import com.silvassa.model.CorrectionFormFloorReport;
import com.silvassa.model.CorrectionFormReport;
import com.silvassa.model.CorrectionFormSaveBean;
import com.silvassa.model.Localitymaster;
import com.silvassa.model.PaymentBean;
import com.silvassa.model.PaymentModeMaster;
import com.silvassa.model.PaymentStatus;
import com.silvassa.model.PropertyArrearReport;
import com.silvassa.model.PropertyDetails;
import com.silvassa.model.PropertyFloor;
import com.silvassa.model.PropertyForTaxBean;
import com.silvassa.model.PropertyRentable;
import com.silvassa.model.PropertyTransferCollectionBean;
import com.silvassa.model.PropertyTransferWithInstrument;
import com.silvassa.model.PropertyTransferWithOutInstrument;
import com.silvassa.model.PropertytypeMaster;
import com.silvassa.model.TAXCalculationBean;
import com.silvassa.model.TAXDetailBean;
import com.silvassa.model.Wardmaster;
import com.silvassa.service.OTPService;
import com.silvassa.tax.service.TaxCollectionService;
import com.silvassa.tax.service.TaxService;
import com.silvassa.util.MMIUtil;
import com.silvassa.util.MmiPathController;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Date;
import java.util.Iterator;
import java.util.Map;
import javax.servlet.http.HttpServletResponse;
import org.apache.log4j.Logger;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.springframework.web.servlet.ModelAndView;

@Controller
public class TaxCollectionController {

    @Autowired
    private HttpServletRequest request;

    private static final Logger logger = Logger.getLogger(TaxCollectionController.class);

    @Autowired
    @Qualifier("taxService")
    TaxService taxService;

    @Autowired
    @Qualifier("taxCollectionService")
    TaxCollectionService taxCollectionService;

    @Autowired
    private SilvassaService silvassaService;

    @Autowired
    private OTPService OTPServiceImpl;

    @Autowired
    private OTPDaoImpl otpDaoImpl;

    @ResponseBody
    @RequestMapping(value = "/getPropertyDetail", method = RequestMethod.POST)
    public PropertyForTaxBean homePage(HttpServletRequest request, Model model, String propertyId) {
        PropertyForTaxBean pftb = taxCollectionService.getPropertyDetail(propertyId);
        return pftb;
    }

    @ResponseBody
    @RequestMapping(value = "/getLocality", method = RequestMethod.POST)
    public List<Localitymaster> loadLocality(HttpServletRequest request, Model model) {
        return taxCollectionService.getLocality();
    }

    @ResponseBody
    @RequestMapping(value = "/getPropType", method = RequestMethod.POST)
    public List<PropertytypeMaster> loadPropType(HttpServletRequest request, Model model) {
        return taxCollectionService.getPropertyType();
    }

    @ResponseBody
    @RequestMapping(value = "/getWard", method = RequestMethod.POST)
    public List<Wardmaster> loadWard(HttpServletRequest request, Model model) {
        return taxCollectionService.getWard();
    }

    @ResponseBody
    @RequestMapping(value = "/getTAXCaLculation", method = RequestMethod.POST)
    public List<TAXCalculationBean> getTAXCaLculation(HttpServletRequest request, String propertyId) {

        if (propertyId == null || propertyId == "") {
            return null;
        }
        return taxCollectionService.getTAXInDetail(propertyId);
    }

    @ResponseBody
    @RequestMapping(value = "/getTAXDetails", method = RequestMethod.POST)
    public JSONObject getTAXDetails(HttpServletRequest request, String propertyId) {

        TAXDetailBean tb = null;
        List<PropertyFloor> floors = null;
        JSONObject jo = new JSONObject();
        if (propertyId == null || propertyId == "" || propertyId == "undefined") {
            return null;
        }
        floors = silvassaService.getPropertyFloorDetails(propertyId);
        tb = taxCollectionService.getTAXDetails(propertyId, MMIUtil.getCurrentFinancialYear(), "Y");

        if (tb != null) {
            tb.setFloorWiseTAXDetails(taxCollectionService.loadtFloorWiseTAXDetails(tb.getTaxNo()));
        }

        jo.put("taxDetails", tb);
        jo.put("floorDetails", floors);

        return jo;
    }

    @ResponseBody
    @RequestMapping(value = "/searchPropertyForTAX", method = RequestMethod.POST)
    public JSONObject searchPropertyForTAX(HttpServletRequest request, String params) {

        JSONParser jp = new JSONParser();
        JSONObject jObj = null;
        try {
            JSONObject jo = (JSONObject) jp.parse(params);

            jObj = taxCollectionService.searchPropertyForTAX(jo);

        } catch (Exception ex) {

        }

        return jObj;
    }

    @ResponseBody
    @RequestMapping(value = "/getRentableValues", method = RequestMethod.POST)
    public List<PropertyRentable> getRentableValues(HttpServletRequest request) {

        List<PropertyRentable> ls = null;
        ls = taxCollectionService.loadRentableValues();

        return ls;
    }

    @RequestMapping(value = "/taxgeneration", method = RequestMethod.GET)
    public String taxgenerationPage(
            Model model) {
        String responsePage = null;

        try {

            responsePage = "taxgeneration";
            ActionTracker actionTracker = silvassaService.getLastTAXCalculated();
            request.setAttribute("msg", "");
            request.setAttribute("tAXGeneratedOn", actionTracker.getCompleteTime());

        } catch (Exception e) {
            logger.info(e.getMessage());
        }
        return responsePage;
    }

    @RequestMapping(value = "/generateTax", method = RequestMethod.POST)
    public String generateTax(HttpServletRequest request, @ModelAttribute("filterBean") FilterBean filterBean) {
        String responsePage = null;
        String result = null;
        String msg = null;

        try {

            responsePage = "taxgeneration";
            String param = null;
            if (filterBean.getZoneId() == null && filterBean.getZoneId().equals("-1") && filterBean.getPropertyId() == null) {
                msg = "Kindly provide input.";
                return responsePage;
            } else {
                LoginDetailsBean loginDetails = (LoginDetailsBean) request.getSession(false).getAttribute("userDetailsBean");
                String ipAddress = request.getHeader("X-FORWARDED-FOR");
                if (ipAddress == null) {
                    ipAddress = request.getRemoteAddr();
                }
                ActionTracker actionTracker = new ActionTracker();
                actionTracker.setInitTime(String.valueOf(MMIUtil.getEpocTime()));
                actionTracker.setInitBy(loginDetails.getUserId());
                actionTracker.setTask(MMIUtil.TAX_GEN);
                actionTracker.setTaskStatus(MMIUtil.STATUS_START);
                actionTracker.setIpAddress(ipAddress);
                actionTracker.setRemarks(filterBean.toString());

                silvassaService.appendToActionTracker(actionTracker);

                result = taxService.generateTax(filterBean, actionTracker);
                if (result.equals("success")) {
                    actionTracker.setTaskStatus(MMIUtil.STATUS_COMPLETE);
                    msg = "TAX GENERATED SUCCESSFUlY";
                } else {
                    actionTracker.setTaskStatus(MMIUtil.STATUS_ERROR);
                    msg = "ERROR IN TAX GENERATION";
                }

                actionTracker.setCompleteTime(String.valueOf(MMIUtil.getEpocTime()));
                silvassaService.appendToActionTracker(actionTracker);

            }
        } catch (Exception e) {
            logger.info(e.getMessage());;
        }
        request.setAttribute("msg", msg);
        request.setAttribute("tAXGeneratedOn", silvassaService.getLastTAXCalculated().getCompleteTime());
        return responsePage;
    }

    @RequestMapping(value = "/generateTaxById", method = RequestMethod.POST)
    public String generateTaxById(HttpServletRequest request, String uniqueId) {
        String responsePage = null;
        String result = null;
        String msg = null;

        try {

            responsePage = "taxgeneration";
            String param = null;
            if (uniqueId == null) {
                msg = "Kindly provide input.";
                return responsePage;
            } else {
                LoginDetailsBean loginDetails = (LoginDetailsBean) request.getSession(false).getAttribute("userDetailsBean");
                String ipAddress = request.getHeader("X-FORWARDED-FOR");
                if (ipAddress == null) {
                    ipAddress = request.getRemoteAddr();
                }
                ActionTracker actionTracker = new ActionTracker();
                actionTracker.setInitTime(String.valueOf(MMIUtil.getEpocTime()));
                actionTracker.setInitBy(loginDetails.getUserId());
                actionTracker.setTask(MMIUtil.TAX_GEN);
                actionTracker.setTaskStatus(MMIUtil.STATUS_START);
                actionTracker.setIpAddress(ipAddress);
                actionTracker.setRemarks(uniqueId);

                silvassaService.appendToActionTracker(actionTracker);

                result = taxService.generateTaxById(uniqueId, actionTracker);
                if (result.equals("success")) {
                    actionTracker.setTaskStatus(MMIUtil.STATUS_COMPLETE);
                    msg = "TAX GENERATED SUCCESSFUlY";
                } else {
                    actionTracker.setTaskStatus(MMIUtil.STATUS_ERROR);
                    msg = "ERROR IN TAX GENERATION";
                }

                actionTracker.setCompleteTime(String.valueOf(MMIUtil.getEpocTime()));
                silvassaService.appendToActionTracker(actionTracker);

            }
        } catch (Exception e) {
            logger.info(e.getMessage());;
        }
        request.setAttribute("msg", msg);
        request.setAttribute("tAXGeneratedOn", silvassaService.getLastTAXCalculated().getCompleteTime());
        return responsePage;
    }

    @RequestMapping(value = "/bankList", method = {RequestMethod.POST})
    public @ResponseBody
    String getBanks(@RequestParam(required = false) String error, Model model) {

        List<BankMaster> banks = null;

        try {
            banks = taxCollectionService.getBanks();
        } catch (Exception ex) {
            logger.info("[CommonController.paymentPage] Exception : " + ex.getMessage());
            return null;
        }

        return new Gson().toJson(banks);

    }

    @RequestMapping(value = "/paymentModes", method = {RequestMethod.POST})
    public @ResponseBody
    String getPaymentMode(@RequestParam(required = false) String error, Model model) {

        List<PaymentModeMaster> pMode = null;

        try {
            pMode = taxCollectionService.getPaymentModes();
        } catch (Exception ex) {
            logger.info("[CommonController.paymentPage] Exception : " + ex.getMessage());
            return null;
        }

        return new Gson().toJson(pMode);

    }

    @RequestMapping(value = "/collectPayment", method = {RequestMethod.POST})
    public @ResponseBody
    JSONObject getPaymentMode(HttpServletRequest request, HttpServletResponse response, @RequestParam(required = false) String error, Model model, String param) {

        JSONObject jo = new JSONObject();

        try {
            Gson gson = new GsonBuilder().setDateFormat("yyyy-MM-dd").create();
            PaymentBean paymentBean = gson.fromJson(param, PaymentBean.class);

            System.out.println("front" + paymentBean.getIfscCode() + "payer name" + paymentBean.getPayerName());
            jo.put("code", 200);
            jo.put("msg", "Success");
            PaymentBean payBean = taxCollectionService.collectPayment(paymentBean);
            jo.put("data", payBean);
            if (payBean.getPaymentMode() != null && (payBean.getPaymentMode().equalsIgnoreCase("CSH") || payBean.getPaymentMode().equalsIgnoreCase("POS_DEVICE") || payBean.getPaymentMode().equalsIgnoreCase("BHIM_UPI") || payBean.getPaymentMode().equalsIgnoreCase("NEFT_RTGS"))) {
                appovePayments(request, response, payBean.getPayRefId(), "Auto Update By Server.");
            }
        } catch (Exception ex) {
            ex.printStackTrace();
            logger.info("[CommonController.paymentPage] Exception : " + ex.getMessage());
            jo.put("code", 500);
            jo.put("msg", "Error");
            jo.put("data", null);
        }

        return jo;

    }

    @RequestMapping(value = "/getPaymentsForApproval", method = {RequestMethod.POST})
    public @ResponseBody
    JSONObject getPaymentsForApproval(@RequestParam(required = false) String error, Model model, String param) {

        JSONObject jo = new JSONObject();
        System.out.println("param" + param);
        try {
            jo = taxCollectionService.getPaymentsForApproval(param);
        } catch (Exception ex) {
            ex.printStackTrace();
            logger.info("[CommonController.paymentPage] Exception : " + ex.getMessage());
            jo.put("code", 500);
            jo.put("msg", "Error");
            jo.put("data", null);
        }

        return jo;

    }

    @RequestMapping(value = "/appovePayment", method = {RequestMethod.POST})
    public @ResponseBody
    JSONObject appovePayments(HttpServletRequest request, HttpServletResponse response, String paymentRefId, String remarks) {

        JSONObject jSONObject = new JSONObject();
        try {

            if (request == null
                    || request.getSession(false) == null
                    || request.getSession(false).getAttribute("userDetailsBean") == null) {

                jSONObject.put("code", "5001");
                jSONObject.put("msg", "Invalid session, please login again");
                jSONObject.put("data", null);
                return jSONObject;
            }

            LoginDetailsBean loginDetails = (LoginDetailsBean) request.getSession(false).getAttribute("userDetailsBean");

            String ipAddress = request.getHeader("X-FORWARDED-FOR");
            if (ipAddress == null) {
                ipAddress = request.getRemoteAddr();
            }

            PaymentStatus paymentStatus = new PaymentStatus();
            paymentStatus.setPayrefid(paymentRefId);
            paymentStatus.setEntrydatetime(MMIUtil.getCurrentTimestamp());
            paymentStatus.setUserId(loginDetails.getUserId());
            paymentStatus.setIpAddress(ipAddress);
            paymentStatus.setRemarks(remarks);

            taxCollectionService.approvePayment(paymentStatus);
            PaymentBean paymentBean = taxCollectionService.paymentInitDetails(paymentRefId);
            PropertyDetails propertyDetails = taxCollectionService.propertyDetails(paymentBean.getPropId());

            if (propertyDetails.getPropertyContact() != null) {
                String phone_msg = taxCollectionService.phonePaymentMsg(paymentBean, propertyDetails);
                OTPServiceImpl.sendOTPtoMobile(propertyDetails.getPropertyContact(), phone_msg);
            }
            if (propertyDetails.getPropertyOwnerEmail() != null) {
                String email_msg = taxCollectionService.emailPaymentMsg(paymentBean, propertyDetails);
                otpDaoImpl.registerMail(propertyDetails.getPropertyOwnerEmail(), null, "Silvassa Municipal Council : Payment Acknowledgement", email_msg);
            }

            jSONObject.put("code", "200");
            jSONObject.put("msg", "OK");
            jSONObject.put("data", null);

        } catch (Exception e) {
            e.printStackTrace();
            jSONObject.put("code", "500");
            jSONObject.put("msg", e.getMessage());
            jSONObject.put("data", null);
            logger.info(e.getMessage());
        }

        return jSONObject;
    }

    @RequestMapping(value = "/rejectPayment", method = {RequestMethod.POST})
    public @ResponseBody
    JSONObject rejectPayments(HttpServletRequest request, HttpServletResponse response, String paymentRefId, String remarks) {

        JSONObject jSONObject = new JSONObject();
        try {

            if (request == null
                    || request.getSession(false) == null
                    || request.getSession(false).getAttribute("userDetailsBean") == null) {

                jSONObject.put("code", "5001");
                jSONObject.put("msg", "Invalid session, please login again");
                jSONObject.put("data", null);
                return jSONObject;
            }
            LoginDetailsBean loginDetails = (LoginDetailsBean) request.getSession(false).getAttribute("userDetailsBean");

            String ipAddress = request.getHeader("X-FORWARDED-FOR");
            if (ipAddress == null) {
                ipAddress = request.getRemoteAddr();
            }

            PaymentStatus paymentStatus = new PaymentStatus();
            paymentStatus.setPayrefid(paymentRefId);
            paymentStatus.setEntrydatetime(MMIUtil.getCurrentTimestamp());
            paymentStatus.setUserId(loginDetails.getUserId());
            paymentStatus.setIpAddress(ipAddress);
            paymentStatus.setRemarks(remarks);

            taxCollectionService.rejectPayment(paymentStatus);

            jSONObject.put("code", "200");
            jSONObject.put("msg", "OK");
            jSONObject.put("data", null);

        } catch (Exception e) {
            e.printStackTrace();
            jSONObject.put("code", "500");
            jSONObject.put("msg", e.getMessage());
            jSONObject.put("data", null);
            logger.info(e.getMessage());
        }

        return jSONObject;
    }

    @ResponseBody
    @RequestMapping(value = "/getPaymentHistory", method = RequestMethod.POST)
    public List<PaymentBean> getPaymentHistory(HttpServletRequest request, String propId) {

        List<PaymentBean> ls = null;
        ls = taxCollectionService.loadPaymentHistory(propId);

        return ls;
    }

    @RequestMapping(value = "/printCollectionReceiptPdf", method = {RequestMethod.POST, RequestMethod.GET})
    public @ResponseBody
    ModelAndView printCollectionReceiptPdf(@RequestParam(required = false) String error, Model model, String param1, String param2) {

        //System.out.println("printCollectionReceiptPdf "+param1+" "+param2);
        JSONObject jo = new JSONObject();
        ModelAndView mv = null;

        try {
            //Gson gson = new GsonBuilder().setDateFormat("yyyy-MM-dd").create();
            //PaymentBean paymentBean = gson.fromJson(param, PaymentBean.class);

            List<PaymentBean> paymentList = taxCollectionService.printCollectionReceipt(param1, param2);
            List<PropertyDetails> listOwner = taxCollectionService.getOwnerName(param1);
            List<BankMaster> bnk = taxCollectionService.getBanks();
            //System.out.println(" listOwner "+listOwner.size());
            mv = new ModelAndView("pdfView3", "taxList", paymentList);
            //model.addAttribute("listowner",listOwner);
            mv.addObject("listowner", listOwner);
            mv.addObject("bnk", bnk);
            //jo.put("code", 200);
            //jo.put("msg", "Success");
            //jo.put("data", taxCollectionService.collectPayment(paymentBean));

        } catch (Exception ex) {
            ex.printStackTrace();
            logger.info("[CommonController.paymentPage] Exception : " + ex.getMessage());
            //jo.put("code", 500);
            //jo.put("msg", "Error");
            //jo.put("data", null);
        }

        return mv;

    }

    @RequestMapping(value = "/showCollectionReceiptHistoryPdf", method = {RequestMethod.POST})
    public @ResponseBody
    void showCollectionReceiptHistoryPdf(@RequestParam(required = false) String error, Model model,
            String propId, String receiptRefNo, HttpServletRequest request,
            HttpServletResponse response) throws DocumentException {

        //System.out.println("showCollectionReceiptHistoryPdf   "+receiptRefNo);
        JSONObject jo = new JSONObject();
        ModelAndView mv = null;
        String status = null;
        String mode = null;
        boolean isCheque = true;

        //Gson gson = new GsonBuilder().setDateFormat("yyyy-MM-dd").create();
        //PaymentBean paymentBean = gson.fromJson(param, PaymentBean.class);
        //String dd="D://mnt//vol1//paymenReceipt//"
        String fnmae = receiptRefNo + ".pdf";

        List<PaymentBean> paymentList = taxCollectionService.printCollectionReceipt(propId, receiptRefNo);
        if (paymentList.size() > 0) {
            Iterator itr = paymentList.iterator();
            while (itr.hasNext()) {
                PaymentBean bean = (PaymentBean) itr.next();
                status = bean.getStatus();
                mode = bean.getPaymentMode();
                //System.out.println("mode "+mode);
                //System.out.println("status "+status);
            }
        }

        String pdfFile = MmiPathController.getDataPath("receipt.path");
        Path file = Paths.get(pdfFile, fnmae);

        if (mode.equalsIgnoreCase("CHQ") && status.equals("C")) {
            try {
                if (Files.deleteIfExists(file)) {
                    isCheque = false;
                    //System.out.println("delete file");
                }

            } catch (Exception e) {
                logger.info("[CommonController.paymentPage]  delete file Exception : " + e.getMessage());
                e.printStackTrace();
            }

        }

        if (!Files.exists(file)) {
            printCollectionReceiptPdf(error, model, propId, receiptRefNo);
            try {
                response.sendRedirect("printCollectionReceiptPdf?param1=" + propId + "&param2=" + receiptRefNo);
            } catch (Exception e) {
            }

        }

        response.setContentType("application/pdf");
        response.addHeader("Content-Disposition", "inline; filename=" + fnmae);
        try {
            if (isCheque) {
                Files.copy(file, response.getOutputStream());
                response.getOutputStream().flush();
            }
        } catch (IOException ex) {
            ex.printStackTrace();
            logger.info("[CommonController.paymentPage] Exception : " + ex.getMessage());
        }

//            List<PaymentBean>paymentList=taxCollectionService.printCollectionReceipt(param1,param2);
//            List<PropertyDetails> listOwner= taxCollectionService.getOwnerName(param1);
//            List<BankMaster> bnk=taxCollectionService.getBanks();
//            //System.out.println(" listOwner "+listOwner.size());
//            mv=new ModelAndView("pdfView3", "taxList", paymentList);
//            //model.addAttribute("listowner",listOwner);
//            mv.addObject("listowner",listOwner);
//            mv.addObject("bnk",bnk);
//            //jo.put("code", 200);
//            //jo.put("msg", "Success");
//            //jo.put("data", taxCollectionService.collectPayment(paymentBean));
//
//        } catch (Exception ex) {
//            ex.printStackTrace();
//            logger.info("[CommonController.paymentPage] Exception : " + ex.getMessage());
//            //jo.put("code", 500);
//            //jo.put("msg", "Error");
//            //jo.put("data", null);
//        }
        //return mv;
    }

    @RequestMapping(value = "/propertyTransferWithInsturment", method = {RequestMethod.GET})
    public ModelAndView propertyTransferWithInsturment(HttpServletRequest request, Model model) {
        ModelAndView mv = new ModelAndView();
        String view = "PropertyTransferWithInstrument";
        String piddata = request.getParameter("piddata");
        request.setAttribute("piddata", piddata);

        // model.addAttribute("actionFliter", actionFliter);
        // model.addAttribute("countdata", correctionDAO.getCorrectionCount(actionFliter));
        //  model.addAttribute("countdataward", correctionDAO.getCorrectionCountOnWard(actionFliter));
        mv.setViewName(view);
        return mv;
    }

    @RequestMapping(value = "/propertyTransferWithOutInsturment", method = {RequestMethod.GET})
    public ModelAndView propertyTransferWithOutInsturment(HttpServletRequest request, Model model) {
        ModelAndView mv = new ModelAndView();
        String view = "PropertyTransferWithoutInstrument";
        String piddata = request.getParameter("piddata");
        request.setAttribute("piddata", piddata);

        // model.addAttribute("actionFliter", actionFliter);
        // model.addAttribute("countdata", correctionDAO.getCorrectionCount(actionFliter));
        //  model.addAttribute("countdataward", correctionDAO.getCorrectionCountOnWard(actionFliter));
        mv.setViewName(view);
        return mv;
    }

    @RequestMapping(value = "/propertyTransferWithInsturmentDataSave", method = {RequestMethod.POST})
    public ModelAndView propertyTransferWithInsturmentDataSave(@ModelAttribute("propertyTransfer") PropertyTransferWithInstrument propertyTransfer, HttpServletRequest request, Model model) {
        ModelAndView mv = new ModelAndView();
        String view = "PropertyTransferCollection";
        String msg = "";
        //int pp=propertyTransfer.getId();
        //String pf_ref_id=new Integer(pp).toString();
        String dateOfNotice = propertyTransfer.getDateOfNotice();
        String dateOfTransfer = propertyTransfer.getDateOfInstrument();
        String venderName = propertyTransfer.getVendorName();
        String purchaserName = propertyTransfer.getPurchaserName();
        String amount = new Integer(propertyTransfer.getAmount()).toString();
        String propertyId = propertyTransfer.getPropertyUniqueId();

        //System.out.println("propertyTransferWithInsturmentDataSave ");
        msg = taxCollectionService.savePropertyTrasferWithInstrument(propertyTransfer);
        String msg1 = "successfully inserted";
        request.setAttribute("MSG", msg1);
        request.setAttribute("pf_ref_id", msg);
        request.setAttribute("dateOfNotice", dateOfNotice);
        request.setAttribute("dateOfTransfer", dateOfTransfer);
        request.setAttribute("venderName", venderName);
        request.setAttribute("purchaserName", purchaserName);
        request.setAttribute("amount", amount);
        request.setAttribute("piddata", propertyId);

        // model.addAttribute("actionFliter", actionFliter);
        // model.addAttribute("countdata", correctionDAO.getCorrectionCount(actionFliter));
        //  model.addAttribute("countdataward", correctionDAO.getCorrectionCountOnWard(actionFliter));
        mv.setViewName(view);
        return mv;
    }

    @RequestMapping(value = "/propertyTransferWithOutInsturmentDataSave", method = {RequestMethod.POST})
    public ModelAndView propertyTransferWithOutInsturmentDataSave(@ModelAttribute("propertyTransferWithout") PropertyTransferWithOutInstrument propertyTransfrer, HttpServletRequest request, Model model) {
        ModelAndView mv = new ModelAndView();
        String view = "PropertyTransferCollectionWithoutInstrumnent";
        String msg = "";
        //System.out.println("propertyTransferWithOutInsturmentDataSave ");
        msg = taxCollectionService.savePropertyTrasferWithOutInstrument(propertyTransfrer);
        String msg1 = "successfully inserted";
        String propertyId = propertyTransfrer.getPropertyUniqueId();
        request.setAttribute("MSG", msg1);
        request.setAttribute("pf_ref_id", msg);
        request.setAttribute("dateOfNotice", propertyTransfrer.getDateOfNotice());
        request.setAttribute("transfererName", propertyTransfrer.getTransferedPerson());
        request.setAttribute("piddata", propertyId);
        // model.addAttribute("actionFliter", actionFliter);
        // model.addAttribute("countdata", correctionDAO.getCorrectionCount(actionFliter));
        //  model.addAttribute("countdataward", correctionDAO.getCorrectionCountOnWard(actionFliter));
        mv.setViewName(view);
        return mv;
    }

    @RequestMapping(value = "/propertyTransferCollectionDataSave", method = {RequestMethod.POST})
    public ModelAndView propertyTransferCollectionDataSave(@ModelAttribute("propertyTransferCollection") PropertyTransferCollectionBean propertyTransferCollection, HttpServletRequest request, Model model) {
        ModelAndView mv = new ModelAndView();
        String view = "PropertyTransferCollection";
        String msg = "";

        //int pp=propertyTransfer.getId();
        //String pf_ref_id=new Integer(pp).toString();
//        String dateOfNotice=propertyTransfer.getDateOfNotice();
//        String dateOfTransfer=propertyTransfer.getDateOfInstrument();
//        String venderName=propertyTransfer.getVendorName();
//        String purchaserName=propertyTransfer.getPurchaserName();
//        String amount =new Integer(propertyTransfer.getAmount()).toString();
//        
        //System.out.println("propertyTransferWithInsturmentDataSave ");
        msg = taxCollectionService.savePropertyTrasferCollection(propertyTransferCollection);
        String msg1 = "Successfully inserted";
        request.setAttribute("MSG", msg1);
        request.setAttribute("pf_ref_collection_id", propertyTransferCollection.getPayRefId());
//        request.setAttribute("dateOfNotice", dateOfNotice);
//        request.setAttribute("dateOfTransfer", dateOfTransfer);
//        request.setAttribute("venderName", venderName);
//        request.setAttribute("purchaserName", purchaserName);
//        request.setAttribute("amount", amount);

        // model.addAttribute("actionFliter", actionFliter);
        // model.addAttribute("countdata", correctionDAO.getCorrectionCount(actionFliter));
        //  model.addAttribute("countdataward", correctionDAO.getCorrectionCountOnWard(actionFliter));
        mv.setViewName(view);
        return mv;
    }

    @RequestMapping(value = "/propertyTransferCollectionWithoutInstrumentDataSave", method = {RequestMethod.POST})
    public ModelAndView propertyTransferCollectionWithoutInstrumentDataSave(@ModelAttribute("propertyTransferCollection") PropertyTransferCollectionBean propertyTransferCollection, HttpServletRequest request, Model model) {
        ModelAndView mv = new ModelAndView();
        String view = "PropertyTransferCollectionWithoutInstrumnent";
        String msg = "";
        //int pp=propertyTransfer.getId();
        //String pf_ref_id=new Integer(pp).toString();
//        String dateOfNotice=propertyTransfer.getDateOfNotice();
//        String dateOfTransfer=propertyTransfer.getDateOfInstrument();
//        String venderName=propertyTransfer.getVendorName();
//        String purchaserName=propertyTransfer.getPurchaserName();
//        String amount =new Integer(propertyTransfer.getAmount()).toString();
//        
        //System.out.println("propertyTransferWithInsturmentDataSave ");
        msg = taxCollectionService.savePropertyTrasferCollectionWithoutInstrument(propertyTransferCollection);
        String msg1 = "Successfully inserted";
        request.setAttribute("MSG", msg1);
        request.setAttribute("pf_ref_collection_id", propertyTransferCollection.getPayRefId());
//        request.setAttribute("dateOfNotice", dateOfNotice);
//        request.setAttribute("dateOfTransfer", dateOfTransfer);
//        request.setAttribute("venderName", venderName);
//        request.setAttribute("purchaserName", purchaserName);
//        request.setAttribute("amount", amount);

        // model.addAttribute("actionFliter", actionFliter);
        // model.addAttribute("countdata", correctionDAO.getCorrectionCount(actionFliter));
        //  model.addAttribute("countdataward", correctionDAO.getCorrectionCountOnWard(actionFliter));
        mv.setViewName(view);
        return mv;
    }

    @RequestMapping(value = "/propertyTransferWithPropertyId", method = {RequestMethod.GET})
    public ModelAndView propertyTransferWithPropertyId(HttpServletRequest request, Model model) {
        ModelAndView mv = new ModelAndView();
        String view = "PropertyTransferWithPropertyId";

        // model.addAttribute("actionFliter", actionFliter);
        // model.addAttribute("countdata", correctionDAO.getCorrectionCount(actionFliter));
        //  model.addAttribute("countdataward", correctionDAO.getCorrectionCountOnWard(actionFliter));
        mv.setViewName(view);
        return mv;
    }

    @RequestMapping(value = "/checkDuesPendingWithPropertyId", method = {RequestMethod.GET, RequestMethod.POST})
    public ModelAndView checkDuesPindingWithPropertyId(HttpServletRequest request, Model model) {
        ModelAndView mv = new ModelAndView();
        //String view = "PropertyTransferWithInstrument";
        String view = "PropertyTransferWithPropertyId";
        String view1 = "PropertyDuesNotClear";
        String pid = request.getParameter("pid");
        pid = pid.toUpperCase();
        Integer amt = taxCollectionService.checkPropertyDues(pid);
        int checkAmount = amt;
        //System.out.println("amount "+checkAmount+"  "+pid);
        if (checkAmount == 0 || checkAmount < 0) {
            mv.setViewName(view);
            request.setAttribute("dmsg", "Your dues clear from Silvassa Municipal Council: ");
        } else {
            mv.setViewName(view1);
        }
        request.setAttribute("piddata", pid);
        request.setAttribute("AMT", new Integer(checkAmount).toString());
        // model.addAttribute("actionFliter", actionFliter);
        // model.addAttribute("countdata", correctionDAO.getCorrectionCount(actionFliter));
        //  model.addAttribute("countdataward", correctionDAO.getCorrectionCountOnWard(actionFliter));
        //mv.setViewName(view);
        return mv;
    }

    @RequestMapping(value = "/arrearReport", method = {RequestMethod.GET})
    public ModelAndView arrearReport(HttpServletRequest request, Model model) {
        ModelAndView mv = new ModelAndView();

        String view = "ArrearReport";

        // model.addAttribute("actionFliter", actionFliter);
        // model.addAttribute("countdata", correctionDAO.getCorrectionCount(actionFliter));
        //  model.addAttribute("countdataward", correctionDAO.getCorrectionCountOnWard(actionFliter));
        mv.setViewName(view);
        return mv;
    }

    @RequestMapping(value = "/arrearReportData", method = {RequestMethod.POST})
    public ModelAndView arrearReportData(HttpServletRequest request, Model model) {
        ModelAndView mv = new ModelAndView();
        String view = "ArrearReportData";
        String propertyId = "";
        String mobileNo = "";
        String email = "";
        String ownerid = "";
        String occ_name = "";
        String Easy_City_Code = "";
        String locality = "";
        String Locality = "";
        String src_aadhar_no = "";
        String category = "";
        String ward = "";

        if (request.getParameter("ward") != null) {
            ward = request.getParameter("ward");
        }
        if (request.getParameter("prop_id_input") != null) {
            propertyId = request.getParameter("prop_id_input").toUpperCase().trim();

        }
        if (request.getParameter("Phone_No") != null) {
            mobileNo = request.getParameter("Phone_No").trim();

        }
        if (request.getParameter("email") != null) {
            email = request.getParameter("email").trim();

        }
        if (request.getParameter("ownerid") != null) {
            ownerid = request.getParameter("ownerid").trim();
        }
        if (request.getParameter("occ_name") != null) {
            occ_name = request.getParameter("occ_name").trim();
        }
        if (request.getParameter("Easy_City_Code") != null) {
            Easy_City_Code = request.getParameter("Easy_City_Code").trim();
        }
        if (request.getParameter("locality") != null) {
            locality = request.getParameter("locality").trim();
        }
        if (request.getParameter("Locality") != null) {
            Locality = request.getParameter("Locality").trim();
        }
        if (request.getParameter("src_aadhar_no") != null) {
            src_aadhar_no = request.getParameter("src_aadhar_no").trim();
        }
        if (request.getParameter("category") != null) {
            category = request.getParameter("category").trim();
        }

        List arrearList = taxCollectionService.getArrearReport(ward, propertyId, mobileNo, email, ownerid, occ_name, Easy_City_Code, locality, Locality, src_aadhar_no, category);
        //System.out.println("arrearList "+arrearList.size());
        model.addAttribute("ListArrearData", arrearList);

        // model.addAttribute("actionFliter", actionFliter);
        // model.addAttribute("countdata", correctionDAO.getCorrectionCount(actionFliter));
        //  model.addAttribute("countdataward", correctionDAO.getCorrectionCountOnWard(actionFliter));
        mv.setViewName(view);
        return mv;
    }

    @RequestMapping(value = "/demandAndCollection", method = {RequestMethod.GET})
    public ModelAndView demandAndCollection(HttpServletRequest request, Model model) {
        ModelAndView mv = new ModelAndView();

        String view = "DemandAndCollection";

        // model.addAttribute("actionFliter", actionFliter);
        // model.addAttribute("countdata", correctionDAO.getCorrectionCount(actionFliter));
        //  model.addAttribute("countdataward", correctionDAO.getCorrectionCountOnWard(actionFliter));
        mv.setViewName(view);
        return mv;
    }

    @RequestMapping(value = "/exportDemandAndCollection", method = {RequestMethod.POST})
    public ModelAndView exportDeandAndCollection(HttpServletRequest request, Model model) {
        ModelAndView mv = new ModelAndView();
        String ward = request.getParameter("ward");
        //System.out.println("data "+data);
        //System.out.println("exportCorrectionFormReport");
        List<PrivateNoticeBean> demandData = taxCollectionService.generateDemnadAndCollection(ward);
        List<PaymentBean> payment = taxCollectionService.getPaymentData(ward);
         //System.out.println("size "+payment.size());

        //List<CorrectionFormSaveBean> listReport= correctionDAO.getCorrectionReport("rrr");
        //Map<String,CorrectionFormReport> mapdata= correctionDAO.getCorrectionReportFromPropertyDetail();
        //List<CorrectionFormFloorBean>listFloorReport= correctionDAO.getCorrectionReportFloor();
        // Map<String,ArrayList<CorrectionFormFloorReport>> mapFloorData=correctionDAO.getCorrectionReportFromPropertyFloor();
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
        String view = "DemandAndCollectionReportExcelView";
        mv.addObject("listReport", demandData);
        mv.addObject("payment", payment);
//        mv.addObject("reportType",data);
//        mv.addObject("listFloorReport",listFloorReport);
//        mv.addObject("mapFloorData",mapFloorData);
        mv.setViewName(view);
        return mv;
    }

    @RequestMapping(value = "/collectionReport", method = {RequestMethod.GET})
    public ModelAndView collectionReport(HttpServletRequest request, Model model) {
        ModelAndView mv = new ModelAndView();

        String view = "CollectionReport";

        // model.addAttribute("actionFliter", actionFliter);
        // model.addAttribute("countdata", correctionDAO.getCorrectionCount(actionFliter));
        //  model.addAttribute("countdataward", correctionDAO.getCorrectionCountOnWard(actionFliter));
        mv.setViewName(view);
        return mv;
    }

    @RequestMapping(value = "/collectionReportData", method = {RequestMethod.POST})
    public ModelAndView collectionReporttData(HttpServletRequest request, Model model) {
        ModelAndView mv = new ModelAndView();
        String view = "CollectionReportData";
        String prop_id_input = "";
        String ward = "";
        String ownerid = "";
        String occ_name = "";
        String Phone_No = "";
        String Easy_City_Code = "";
        String locality = "";
        String Locality = "";
        String src_aadhar_no = "";
        String category = "";
        String frmDate = "";
        String endDate = "";

//        if (request.getParameter("ward") != null && !request.getParameter("ward").equalsIgnoreCase("-1")) {
//            ward = request.getParameter("ward");
//        }
//        if (request.getParameter("collectDate") != null && !request.getParameter("collectDate").equalsIgnoreCase("-1")) {
//            collectDate = request.getParameter("collectDate");
//        }
        if (request.getParameter("prop_id_input") != null && request.getParameter("prop_id_input").length() > 0) {
            prop_id_input = request.getParameter("prop_id_input").toUpperCase().trim();
        }
        if (request.getParameter("ward") != null && request.getParameter("ward").length() > 0) {
            ward = request.getParameter("ward").toUpperCase().trim();
        }
        if (request.getParameter("ownerid") != null && request.getParameter("ownerid").length() > 0) {
            ownerid = request.getParameter("ownerid").toUpperCase().trim();
        }
        if (request.getParameter("occ_name") != null && request.getParameter("occ_name").length() > 0) {
            occ_name = request.getParameter("occ_name").toUpperCase().trim();
        }
        if (request.getParameter("Phone_No") != null && request.getParameter("Phone_No").length() > 0) {
            Phone_No = request.getParameter("Phone_No").toUpperCase().trim();
        }
        if (request.getParameter("Easy_City_Code") != null && request.getParameter("Easy_City_Code").length() > 0) {
            Easy_City_Code = request.getParameter("Easy_City_Code").toUpperCase().trim();
        }
        if (request.getParameter("locality") != null && request.getParameter("locality").length() > 0) {
            locality = request.getParameter("locality").toUpperCase().trim();
        }
        if (request.getParameter("Locality") != null && request.getParameter("Locality").length() > 0) {
            Locality = request.getParameter("Locality").toUpperCase().trim();
        }
        if (request.getParameter("src_aadhar_no") != null && request.getParameter("src_aadhar_no").length() > 0) {
            src_aadhar_no = request.getParameter("src_aadhar_no").toUpperCase().trim();
        }
        if (request.getParameter("category") != null && request.getParameter("category").length() > 0) {
            category = request.getParameter("category").toUpperCase().trim();
        }
        if (request.getParameter("frmDate") != null && request.getParameter("frmDate").length() > 0) {
            frmDate = request.getParameter("frmDate").toUpperCase().trim();
        }
        if (request.getParameter("endDate") != null && request.getParameter("endDate").length() > 0) {
            endDate = request.getParameter("endDate").toUpperCase().trim();
        }

        //System.out.println("ward "+ward);
        //System.out.println("collectDate "+collectDate);
        //System.out.println("pid "+pid);
        //System.out.println("startDate "+startDate);
        // System.out.println("endDate "+endDate);
        List collectionList = taxCollectionService.getCollectionAmount(prop_id_input, ward, ownerid, occ_name, Phone_No, Easy_City_Code, locality, Locality, src_aadhar_no, category, frmDate, endDate);
        //System.out.println("arrearList "+arrearList.size());
        model.addAttribute("ListCollectionData", collectionList);

        // model.addAttribute("actionFliter", actionFliter);
        // model.addAttribute("countdata", correctionDAO.getCorrectionCount(actionFliter));
        //  model.addAttribute("countdataward", correctionDAO.getCorrectionCountOnWard(actionFliter));
        mv.setViewName(view);
        return mv;
    }

}
