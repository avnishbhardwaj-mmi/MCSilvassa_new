/**
 *
 */
package com.silvassa.controller;

import javax.servlet.http.HttpServletRequest;

import org.json.simple.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.silvassa.bean.FilterBean;
import com.silvassa.bean.LoginDetailsBean;
import com.silvassa.bean.PrivateNoticeBean;
import com.silvassa.service.SilvassaService;
import com.silvassa.model.ActionTracker;
import com.silvassa.notice.service.NoticeGenerationService;
import com.silvassa.util.MMIUtil;
//import com.silvassa.util.PDFBuilder;
import java.util.List;
import javax.servlet.http.HttpServletResponse;
import org.apache.log4j.Logger;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

/**
 * @author ce
 *
 */
@Controller
public class NoticeGenerationController {

    private static final Logger logger = Logger.getLogger(NoticeGenerationController.class);

    @Autowired
    private HttpServletRequest request;

    @Autowired
    private NoticeGenerationService noticeService;

    @Autowired
    private SilvassaService silvassaService;

    @RequestMapping(value = "/viewNotice", method = {RequestMethod.POST, RequestMethod.GET})
    public ModelAndView viewNotice(String taxNo) {

        ModelAndView responsePage = null;
        try {
            responsePage = noticeService.viewNotice(taxNo);
        } catch (Exception e) {
            logger.info(e.getMessage());
        }
        return responsePage;

    }

    @RequestMapping(value = "/noticeWindow", method = RequestMethod.GET)
    public String noticePage(@RequestParam(required = false) String error, Model model) {

        return "viewnotice";
    }

    @RequestMapping(value = "/notice", method = RequestMethod.GET)
    public String noticeForm(@RequestParam(required = false) String error, Model model) {

        ActionTracker actionTracker = silvassaService.getLastNoticeGenerated();
        request.setAttribute("msg", "");
        request.setAttribute("noticeGeneratedOn", actionTracker.getCompleteTime());

        return "notice";
    }

    @RequestMapping(value = "/noticeAfterObjection", method = RequestMethod.GET)
    public String noticeAfterObjection(Model model) {

        ActionTracker actionTracker = silvassaService.getLastNoticeGenerated();
        request.setAttribute("msg", "");
        request.setAttribute("noticeGeneratedOn", actionTracker.getCompleteTime());

        return "noticeAfterObjection";
    }

    @RequestMapping(value = "/generateNotice", method = RequestMethod.POST)
    public String noticeGeneration(@ModelAttribute("filterBean") FilterBean filterBean, HttpServletRequest request) {
        String responsePage = null;

        try {
            String mode = "NEW";
            responsePage = "notice";
            ActionTracker actionT = silvassaService.getLastNoticeGenerated();
            request.setAttribute("noticeGeneratedOn", actionT.getCompleteTime());

            LoginDetailsBean loginDetails = (LoginDetailsBean) request.getSession(false).getAttribute("userDetailsBean");

            String param = "";
            String param1 = "";
            String type = "";
            if (filterBean.getZoneId() != null && !filterBean.getZoneId().equals("-1")) {
                param = filterBean.getZoneId();
                type = "zone";
                if (filterBean.getWard() != null && !filterBean.getWard().equals("-1")) {
                    param1 = filterBean.getWard();
                    type = "ward";
                }
            } else if (filterBean.getPropertyId() != null) {
                param = filterBean.getPropertyId();
                type = "property";
            }

            String cmd = "";//MmiPathController.getDataPath("notice.cmd");
            String path = cmd + " " + param + " " + type + " " + loginDetails.getUserName() + " " + mode;

            String ipAddress = request.getHeader("X-FORWARDED-FOR");
            if (ipAddress == null) {
                ipAddress = request.getRemoteAddr();
            }

            ActionTracker actionTracker = new ActionTracker();
            actionTracker.setInitTime(String.valueOf(MMIUtil.getEpocTime()));
            actionTracker.setInitBy(loginDetails.getUserId());
            actionTracker.setTask(MMIUtil.NOTICE_GEN);
            actionTracker.setTaskStatus(MMIUtil.STATUS_START);
            actionTracker.setIpAddress(ipAddress);
            actionTracker.setRemarks(filterBean.toString());
            silvassaService.appendToActionTracker(actionTracker);
            noticeService.generateTaxNotice(param + "," + param1 + "," + type + "," + loginDetails.getUserName() + "," + mode);
//            logger.info("path : " + path);

//            Runtime.getRuntime().exec(path);
            request.setAttribute("msg", "Process has been started...");

            actionTracker.setTaskStatus(MMIUtil.STATUS_COMPLETE);
            actionTracker.setCompleteTime(String.valueOf(MMIUtil.getEpocTime()));
            silvassaService.appendToActionTracker(actionTracker);

        } catch (Exception e) {
            request.setAttribute("msg", "ERROR IN NOTICE GENERATION");
            logger.info(e.getMessage());
        }
        return responsePage;
    }

    @RequestMapping(value = "/generatePrivateNotice", method = {RequestMethod.GET, RequestMethod.POST})
    public ModelAndView privateNoticeGeneation(String zoneId, String ward, String propertyId, HttpServletRequest request,
            HttpServletResponse response) {

        response.setHeader("Content-Disposition", "attachment; filename=PrivateNotice-" + propertyId + ".pdf");

        String responsePage = null;
        ModelAndView mv = null;
        List<PrivateNoticeBean> taxList = null;
        //ModelAndView mv = new ModelAndView();
        //System.out.println("generatePrivateNotice");
        try {
            String mode = "NEW";
            responsePage = "notice";
            //String zone="";
            //String ward="";
            //String pid="";
            String param = "";
            String param1 = "";
            String type = "";
            JSONObject taxObj = new JSONObject();
            taxObj.put("zoneid", zoneId);
            taxObj.put("wardid", ward);
            taxObj.put("prop_id", propertyId);
        //System.out.println("taxObj "+taxObj.toJSONString());

            /*if (filterBean.getZoneId() != null && !filterBean.getZoneId().equals("-1")) {
             zone = filterBean.getZoneId();
             type = "zone";
             if (filterBean.getWard() != null && !filterBean.getWard().equals("-1")) {
             ward = filterBean.getWard();
             type = "ward";
             }
             } else if (filterBean.getPropertyId() != null) {
             pid = filterBean.getPropertyId();
             type = "property";
             }*/
            //System.out.println("zone "+zone);
            //System.out.println("ward "+ward);
            //JSONObject taxObj = new JSONObject();
            //taxObj.put("zoneid", zone);
            //taxObj.put("wardid", ward);
            //taxObj.put("prop_id", pid);
            taxList = silvassaService.viewPrivateNotice(taxObj.toJSONString());
            //System.out.println("ddddd "+taxList.size());
            //ActionTracker actionT = silvassaService.getLastNoticeGenerated();
            //request.setAttribute("noticeGeneratedOn", actionT.getCompleteTime());

            //LoginDetailsBean loginDetails = (LoginDetailsBean) request.getSession(false).getAttribute("userDetailsBean");
            /*String cmd = "";//MmiPathController.getDataPath("notice.cmd");
             String path = cmd + " " + param + " " + type + " " + loginDetails.getUserName() + " " + mode;

             String ipAddress = request.getHeader("X-FORWARDED-FOR");
             if (ipAddress == null) {
             ipAddress = request.getRemoteAddr();
             }

             ActionTracker actionTracker = new ActionTracker();
             actionTracker.setInitTime(String.valueOf(MMIUtil.getEpocTime()));
             actionTracker.setInitBy(loginDetails.getUserId());
             actionTracker.setTask(MMIUtil.NOTICE_GEN);
             actionTracker.setTaskStatus(MMIUtil.STATUS_START);
             actionTracker.setIpAddress(ipAddress);
             actionTracker.setRemarks(filterBean.toString());
             silvassaService.appendToActionTracker(actionTracker);
             noticeService.generateTaxNotice(param + "," + param1 + "," + type + "," + loginDetails.getUserName() + "," + mode);
             //            logger.info("path : " + path);

             //            Runtime.getRuntime().exec(path);
             request.setAttribute("msg", "Process has been started...");

             actionTracker.setTaskStatus(MMIUtil.STATUS_COMPLETE);
             actionTracker.setCompleteTime(String.valueOf(MMIUtil.getEpocTime()));
             silvassaService.appendToActionTracker(actionTracker);*/
            mv = new ModelAndView("pdfView", "taxList", taxList);

        } catch (Exception e) {
            request.setAttribute("msg", "ERROR IN NOTICE GENERATION");
            //System.out.println("catch");
            logger.info(e.getMessage());

        }
        //System.out.println("last");
        //mv.setViewName("pdfView");
        //mv.addObject( "taxList", taxList);
        return mv;
    }

    @RequestMapping(value = "/generate142Notice", method = {RequestMethod.GET, RequestMethod.POST})
    public ModelAndView generate142Notice(String zoneId, String ward, String propertyId, String phone_no, String locality, String owner_id, HttpServletRequest request) {
        String responsePage = null;
        ModelAndView mv = null;
        //System.out.println("zoneId "+request.getParameter(zoneId));
        //System.out.println("ward "+request.getParameter(ward));
        //zoneId="5";
        //ward="W-14";
        List<PrivateNoticeBean> taxList = null;
        //ModelAndView mv = new ModelAndView();
        //System.out.println("generate142Notice");
        try {
            String mode = "NEW";
            responsePage = "notice";
            //String zone="";
            //String ward="";
            //String pid="";
            String param = "";
            String param1 = "";
            String type = "";
            JSONObject taxObj = new JSONObject();
            taxObj.put("zoneid", zoneId);
            taxObj.put("wardid", ward);
            taxObj.put("prop_id", propertyId);
            taxObj.put("phoneNo", phone_no);
            taxObj.put("locality", locality);
            taxObj.put("owner_id", owner_id);
        //System.out.println("taxObj "+taxObj.toJSONString());

            /*if (filterBean.getZoneId() != null && !filterBean.getZoneId().equals("-1")) {
             zone = filterBean.getZoneId();
             type = "zone";
             if (filterBean.getWard() != null && !filterBean.getWard().equals("-1")) {
             ward = filterBean.getWard();
             type = "ward";
             }
             } else if (filterBean.getPropertyId() != null) {
             pid = filterBean.getPropertyId();
             type = "property";
             }*/
            //System.out.println("zone "+zone);
            //System.out.println("ward "+ward);
            //JSONObject taxObj = new JSONObject();
            //taxObj.put("zoneid", zone);
            //taxObj.put("wardid", ward);
            //taxObj.put("prop_id", pid);
            taxList = silvassaService.view142Notice(taxObj.toJSONString());

            if (taxList.isEmpty()) {
                //mv = new ModelAndView("notice", "msg", "NO Result Found!");
                mv = new ModelAndView("noDataPdf", "msg", "No Result Found For Mentioned Data!");
            } // System.out.println("generate142Notice size "+taxList.size());
            //ActionTracker actionT = silvassaService.getLastNoticeGenerated();
            //request.setAttribute("noticeGeneratedOn", actionT.getCompleteTime());
            //LoginDetailsBean loginDetails = (LoginDetailsBean) request.getSession(false).getAttribute("userDetailsBean");
            /*String cmd = "";//MmiPathController.getDataPath("notice.cmd");
             String path = cmd + " " + param + " " + type + " " + loginDetails.getUserName() + " " + mode;

             String ipAddress = request.getHeader("X-FORWARDED-FOR");
             if (ipAddress == null) {
             ipAddress = request.getRemoteAddr();
             }

             ActionTracker actionTracker = new ActionTracker();
             actionTracker.setInitTime(String.valueOf(MMIUtil.getEpocTime()));
             actionTracker.setInitBy(loginDetails.getUserId());
             actionTracker.setTask(MMIUtil.NOTICE_GEN);
             actionTracker.setTaskStatus(MMIUtil.STATUS_START);
             actionTracker.setIpAddress(ipAddress);
             actionTracker.setRemarks(filterBean.toString());
             silvassaService.appendToActionTracker(actionTracker);
             noticeService.generateTaxNotice(param + "," + param1 + "," + type + "," + loginDetails.getUserName() + "," + mode);
             //            logger.info("path : " + path);

             //            Runtime.getRuntime().exec(path);
             request.setAttribute("msg", "Process has been started...");

             actionTracker.setTaskStatus(MMIUtil.STATUS_COMPLETE);
             actionTracker.setCompleteTime(String.valueOf(MMIUtil.getEpocTime()));
             silvassaService.appendToActionTracker(actionTracker);*/ else {
                mv = new ModelAndView("pdfView1", "taxList", taxList);
            }

        } catch (Exception e) {
            request.setAttribute("msg", "ERROR IN 142 NOTICE GENERATION");
            //System.out.println("catch");
            logger.info(e.getMessage());

        }
        //System.out.println("last");
        //mv.setViewName("pdfView");
        //mv.addObject( "taxList", taxList);
        return mv;
    }

    @RequestMapping(value = "/generate143Notice", method = {RequestMethod.GET, RequestMethod.POST})
    public ModelAndView generate143Notice(String zoneId, String ward, String propertyId, String phone_no, String locality, String owner_id, HttpServletRequest request) {
        String responsePage = null;
        ModelAndView mv = null;
        //System.out.println("zoneId "+request.getParameter(zoneId));
        //System.out.println("ward "+request.getParameter(ward));
        //zoneId="5";
        //ward="W-14";
        List<PrivateNoticeBean> taxList = null;
        //ModelAndView mv = new ModelAndView();
        //System.out.println("generate142Notice");
        try {
            String mode = "NEW";
            responsePage = "notice";
            //String zone="";
            //String ward="";
            //String pid="";
            String param = "";
            String param1 = "";
            String type = "";
            JSONObject taxObj = new JSONObject();
            taxObj.put("zoneid", zoneId);
            taxObj.put("wardid", ward);
            taxObj.put("prop_id", propertyId);
            taxObj.put("phoneNo", phone_no);
            taxObj.put("locality", locality);
            taxObj.put("owner_id", owner_id);
            System.out.println("taxObj " + taxObj.toJSONString());

            /*if (filterBean.getZoneId() != null && !filterBean.getZoneId().equals("-1")) {
             zone = filterBean.getZoneId();
             type = "zone";
             if (filterBean.getWard() != null && !filterBean.getWard().equals("-1")) {
             ward = filterBean.getWard();
             type = "ward";
             }
             } else if (filterBean.getPropertyId() != null) {
             pid = filterBean.getPropertyId();
             type = "property";
             }*/
            //System.out.println("zone "+zone);
            //System.out.println("ward "+ward);
            //JSONObject taxObj = new JSONObject();
            //taxObj.put("zoneid", zone);
            //taxObj.put("wardid", ward);
            //taxObj.put("prop_id", pid);
            taxList = silvassaService.view143Notice(taxObj.toJSONString());
            if (taxList.isEmpty()) {
                //  mv = new ModelAndView("notice", "msg", "NO Result Found!");
                mv = new ModelAndView("noDataPdf", "msg", "No Result Found For Mentioned Data!");
                // mv = new ModelAndView("noDataPdf", "taxList", taxList);
            } // System.out.println("generate142Notice size "+taxList.size());
            //ActionTracker actionT = silvassaService.getLastNoticeGenerated();
            //request.setAttribute("noticeGeneratedOn", actionT.getCompleteTime());
            //LoginDetailsBean loginDetails = (LoginDetailsBean) request.getSession(false).getAttribute("userDetailsBean");
            /*String cmd = "";//MmiPathController.getDataPath("notice.cmd");
             String path = cmd + " " + param + " " + type + " " + loginDetails.getUserName() + " " + mode;

             String ipAddress = request.getHeader("X-FORWARDED-FOR");
             if (ipAddress == null) {
             ipAddress = request.getRemoteAddr();
             }

             ActionTracker actionTracker = new ActionTracker();
             actionTracker.setInitTime(String.valueOf(MMIUtil.getEpocTime()));
             actionTracker.setInitBy(loginDetails.getUserId());
             actionTracker.setTask(MMIUtil.NOTICE_GEN);
             actionTracker.setTaskStatus(MMIUtil.STATUS_START);
             actionTracker.setIpAddress(ipAddress);
             actionTracker.setRemarks(filterBean.toString());
             silvassaService.appendToActionTracker(actionTracker);
             noticeService.generateTaxNotice(param + "," + param1 + "," + type + "," + loginDetails.getUserName() + "," + mode);
             //            logger.info("path : " + path);

             //            Runtime.getRuntime().exec(path);
             request.setAttribute("msg", "Process has been started...");

             actionTracker.setTaskStatus(MMIUtil.STATUS_COMPLETE);
             actionTracker.setCompleteTime(String.valueOf(MMIUtil.getEpocTime()));
             silvassaService.appendToActionTracker(actionTracker);*/ else {
                mv = new ModelAndView("pdfView2", "taxList", taxList);
            }

        } catch (Exception e) {
            request.setAttribute("msg", "ERROR IN 143 NOTICE GENERATION");
            //System.out.println("catch");
            logger.info(e.getMessage());

        }
        //System.out.println("last");
        //mv.setViewName("pdfView");
        //mv.addObject( "taxList", taxList);
        return mv;
    }

    @RequestMapping(value = "/generatePrivateNoticeNo", method = {RequestMethod.GET, RequestMethod.POST})
    public String generatePrivateNoticeNo(String zoneId, String ward, String propertyId, HttpServletRequest request) {
        String responsePage = null;
        ModelAndView mv = null;
        String msg = "";
        //System.out.println("zoneId "+request.getParameter(zoneId));
        //System.out.println("ward "+request.getParameter(ward));
        //zoneId="5";
        //ward="W-14";
        List<PrivateNoticeBean> taxList = null;
        //ModelAndView mv = new ModelAndView();
        //System.out.println("generate142Notice");
        try {
            String mode = "NEW";
            responsePage = "notice";
            //String zone="";
            //String ward="";
            //String pid="";
            String param = "";
            String param1 = "";
            String type = "";
            JSONObject taxObj = new JSONObject();
            taxObj.put("zoneid", zoneId);
            taxObj.put("wardid", ward);
            taxObj.put("prop_id", propertyId);
            //System.out.println("taxObj notice number "+taxObj.toJSONString());

            msg = silvassaService.generatePrivateNoticeNo(taxObj.toJSONString());
            request.setAttribute("msg", msg);

            //mv=new ModelAndView("pdfView2", "taxList", taxList);
        } catch (Exception e) {
            request.setAttribute("msg", "ERROR IN private  NOTICE number GENERATION");
            //System.out.println("catch");
            logger.info(e.getMessage());

        }
        //System.out.println("last");
        //mv.setViewName("pdfView");
        //mv.addObject( "taxList", taxList);
        return responsePage;
    }

    @RequestMapping(value = "/generateNoticeAfterObjection", method = RequestMethod.POST)
    public String generateNoticeAfterObjection(@ModelAttribute("filterBean") FilterBean filterBean, HttpServletRequest request) {
        String responsePage = null;

        try {
            String mode = "RGEN";
            responsePage = "noticeAfterObjection";
            ActionTracker actionT = silvassaService.getLastNoticeGenerated();
            request.setAttribute("noticeGeneratedOn", actionT.getCompleteTime());

            String param = null;
            String param1 = "";
            String type = null;
            if (filterBean.getZoneId() != null && !filterBean.getZoneId().equals("-1")) {
                param = filterBean.getZoneId();
                type = "zone";
                if (filterBean.getWard() != null && !filterBean.getWard().equals("-1")) {
                    param1 = filterBean.getWard();
                    type = "ward";
                }
            } else if (filterBean.getPropertyId() != null) {
                param = filterBean.getPropertyId();
                type = "property";
            }

            String cmd = ""; //MmiPathController.getDataPath("notice.cmd");

            LoginDetailsBean loginDetails = (LoginDetailsBean) request.getSession(false).getAttribute("userDetailsBean");
            String path = cmd + " " + param + " " + type + " " + loginDetails.getUserName() + " " + mode;
            String ipAddress = request.getHeader("X-FORWARDED-FOR");
            if (ipAddress == null) {
                ipAddress = request.getRemoteAddr();
            }

            ActionTracker actionTracker = new ActionTracker();
            actionTracker.setInitTime(String.valueOf(MMIUtil.getEpocTime()));
            actionTracker.setInitBy(loginDetails.getUserId());
            actionTracker.setTask(MMIUtil.NOTICE_GEN);
            actionTracker.setTaskStatus(MMIUtil.STATUS_START);
            actionTracker.setIpAddress(ipAddress);
            actionTracker.setRemarks(filterBean.toString());
            silvassaService.appendToActionTracker(actionTracker);

            logger.info("path : " + path);
            noticeService.generateTaxNotice(param + "," + param1 + "," + type + "," + loginDetails.getUserName() + "," + mode);
//            Runtime.getRuntime().exec(path);
            request.setAttribute("msg", "Process has been started...");

            actionTracker.setTaskStatus(MMIUtil.STATUS_COMPLETE);
            actionTracker.setCompleteTime(String.valueOf(MMIUtil.getEpocTime()));
            silvassaService.appendToActionTracker(actionTracker);

        } catch (Exception e) {
            request.setAttribute("msg", "ERROR IN NOTICE GENERATION");
            logger.info(e.getMessage());
        }
        return responsePage;
    }

    @RequestMapping(value = "/getNoticeList", method = {RequestMethod.POST})
    public @ResponseBody
    JSONObject getNoticeDetails(@RequestParam("noticeSel") String noticeSel) {
        JSONObject jo = new JSONObject();
        JSONObject result = null;

        try {
            if (noticeSel.isEmpty() || noticeSel == null) {
                jo.put("status", "700");
                return jo;
            }
            result = noticeService.loadtTaxNoticeData(noticeSel);

            if (result == null || result.size() <= 0) {
                jo.put("status", "700");
            } else {

                jo.put("status", "200");
                jo.putAll(result);

            }

        } catch (Exception e) {
            jo.put("status", "700");
            logger.info(e.getMessage());

        } finally {
            noticeSel = null;
        }
        return jo;
    }

    @RequestMapping(value = "/generateBillNo", method = {RequestMethod.GET, RequestMethod.POST})
    public ModelAndView generateBillNo(String zoneId, String ward, String propertyId, String phone_no, String locality, String owner_id, RedirectAttributes rm, HttpServletRequest request) {
        String responsePage = null;
        ModelAndView mv = null;
        String msg = "";
        System.out.println("generateBillNo   zoneId " + request.getParameter(zoneId));
        //System.out.println("ward "+request.getParameter(ward));
        //zoneId="5";
        //ward="W-14";
        List<PrivateNoticeBean> taxList = null;
        //ModelAndView mv = new ModelAndView();
        //System.out.println("generate142Notice");
        try {
            String mode = "NEW";
            responsePage = "notice";
            //String zone="";
            //String ward="";
            //String pid="";
            String param = "";
            String param1 = "";
            String type = "";
            JSONObject taxObj = new JSONObject();
            taxObj.put("zoneid", zoneId);
            taxObj.put("wardid", ward);
            taxObj.put("prop_id", propertyId);
            taxObj.put("phoneNo", phone_no);
            taxObj.put("locality", locality);
            taxObj.put("ownerId", owner_id);
            System.out.println("taxObj " + taxObj.toJSONString());
            //System.out.println("taxObj notice number "+taxObj.toJSONString());

            msg = silvassaService.generateBillNumber(taxObj.toJSONString());
            request.setAttribute("msg", msg);
            mv = new ModelAndView("redirect:notice");
            rm.addFlashAttribute("msg", msg);

            //mv=new ModelAndView("pdfView2", "taxList", taxList);
        } catch (Exception e) {
            request.setAttribute("msg", "ERROR IN private  NOTICE number GENERATION");
            //System.out.println("catch");
            logger.info(e.getMessage());

        }
        //System.out.println("last");
        //mv.setViewName("pdfView");
        //mv.addObject( "taxList", taxList);
        return mv;
        // return responsePage;
    }

    @RequestMapping(value = "/generateBillPDF", method = {RequestMethod.GET, RequestMethod.POST})
    public ModelAndView generateBillPDF(String zoneId, String ward, String propertyId, String phone_no, String locality, String owner_id, HttpServletRequest request) {
        String responsePage = null;
        ModelAndView mv = null;
        //System.out.println("zoneId "+request.getParameter(zoneId));
        //System.out.println("ward "+request.getParameter(ward));
        //zoneId="5";
        //ward="W-14";
        List<PrivateNoticeBean> taxList = null;
        //ModelAndView mv = new ModelAndView();
        //System.out.println("generate142Notice");
        try {
            String mode = "NEW";
            responsePage = "notice";
            //String zone="";
            //String ward="";
            //String pid="";
            String param = "";
            String param1 = "";
            String type = "";
            JSONObject taxObj = new JSONObject();
            taxObj.put("zoneid", zoneId);
            taxObj.put("wardid", ward);
            taxObj.put("prop_id", propertyId);
            taxObj.put("phoneNo", phone_no);
            taxObj.put("locality", locality);
            taxObj.put("ownerId", owner_id);
            System.out.println("taxObj " + taxObj.toJSONString());
            LoginDetailsBean userData = (LoginDetailsBean) request.getSession().getAttribute("userDetailsBean");
            String user_id = userData.getUserId();

            /*if (filterBean.getZoneId() != null && !filterBean.getZoneId().equals("-1")) {
             zone = filterBean.getZoneId();
             type = "zone";
             if (filterBean.getWard() != null && !filterBean.getWard().equals("-1")) {
             ward = filterBean.getWard();
             type = "ward";
             }
             } else if (filterBean.getPropertyId() != null) {
             pid = filterBean.getPropertyId();
             type = "property";
             }*/
            //System.out.println("zone "+zone);
            //System.out.println("ward "+ward);
            //JSONObject taxObj = new JSONObject();
            //taxObj.put("zoneid", zone);
            //taxObj.put("wardid", ward);
            //taxObj.put("prop_id", pid);
            String msgHistory=silvassaService.billPdfHistory(user_id,taxObj.toJSONString());
            taxList = silvassaService.viewBillPdf(taxObj.toJSONString());
            if (taxList.isEmpty()) {
                // mv = new ModelAndView("notice", "msg", "NO Result Found!");
                mv = new ModelAndView("noDataPdf", "msg", "No Result Found For Mentioned Data!");
            } //             if (taxList.isEmpty()) {
            ////                mv = new ModelAndView("notice", "msg", "NO Result Found!");
            //               rm.addFlashAttribute("msg", "NO tt Found!"); 
            //               mv = new ModelAndView("redirect:notice");
            //            } 
            // System.out.println("generate142Notice size "+taxList.size());
            //ActionTracker actionT = silvassaService.getLastNoticeGenerated();
            //request.setAttribute("noticeGeneratedOn", actionT.getCompleteTime());
            //LoginDetailsBean loginDetails = (LoginDetailsBean) request.getSession(false).getAttribute("userDetailsBean");
            /*String cmd = "";//MmiPathController.getDataPath("notice.cmd");
             String path = cmd + " " + param + " " + type + " " + loginDetails.getUserName() + " " + mode;

             String ipAddress = request.getHeader("X-FORWARDED-FOR");
             if (ipAddress == null) {
             ipAddress = request.getRemoteAddr();
             }

             ActionTracker actionTracker = new ActionTracker();
             actionTracker.setInitTime(String.valueOf(MMIUtil.getEpocTime()));
             actionTracker.setInitBy(loginDetails.getUserId());
             actionTracker.setTask(MMIUtil.NOTICE_GEN);
             actionTracker.setTaskStatus(MMIUtil.STATUS_START);
             actionTracker.setIpAddress(ipAddress);
             actionTracker.setRemarks(filterBean.toString());
             silvassaService.appendToActionTracker(actionTracker);
             noticeService.generateTaxNotice(param + "," + param1 + "," + type + "," + loginDetails.getUserName() + "," + mode);
             //            logger.info("path : " + path);

             //            Runtime.getRuntime().exec(path);
             request.setAttribute("msg", "Process has been started...");

             actionTracker.setTaskStatus(MMIUtil.STATUS_COMPLETE);
             actionTracker.setCompleteTime(String.valueOf(MMIUtil.getEpocTime()));
             silvassaService.appendToActionTracker(actionTracker);*/ else {

                mv = new ModelAndView("pdfView4", "taxList", taxList);
            }

        } catch (Exception e) {
            request.setAttribute("msg", "ERROR IN 143 NOTICE GENERATION");
            //System.out.println("catch");
            logger.info(e.getMessage());

        }
        //System.out.println("last");
        //mv.setViewName("pdfView");
        //mv.addObject( "taxList", taxList);
        return mv;
    }

}
