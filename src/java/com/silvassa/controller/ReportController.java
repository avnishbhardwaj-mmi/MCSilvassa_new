package com.silvassa.controller;


import com.google.gson.Gson;
import com.silvassa.bean.ReportQuery;
import com.silvassa.report.service.ReportService;
import javax.servlet.http.HttpServletRequest;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
public class ReportController {

    @Autowired
    @Qualifier("reportService")
    ReportService reportService;
    
    private static final Logger logger = Logger.getLogger(ReportController.class);

    @RequestMapping(value = "/reportPropertyDetails", method = RequestMethod.GET)
    public String reportPropertyDetails(HttpServletRequest request, Model model) {
        return "reportPropertyDetails";
    }

    @RequestMapping(value = "/reportTAXDetails", method = RequestMethod.GET)
    public String reportTAXDetails(HttpServletRequest request, Model model) {
        return "reportTAXDetails";
    }

    @RequestMapping(value = "/reportPaymentDue", method = RequestMethod.GET)
    public String reportPaymentDue(HttpServletRequest request, Model model) {
        return "reportPaymentDue";
    }

    @RequestMapping(value = "/reportArrearDue", method = RequestMethod.GET)
    public String reportArrearDue(HttpServletRequest request, Model model) {
        return "reportArrearDue";
    }

    @RequestMapping(value = "/reportTAXAmount", method = RequestMethod.GET)
    public String reportTAXAmount(HttpServletRequest request, Model model) {
        return "reportTAXAmount";
    }

    @RequestMapping(value = "/reportObjection", method = RequestMethod.GET)
    public String reportObjection(HttpServletRequest request, Model model) {
        return "reportObjection";
    }

    @RequestMapping(value = "/reportNotice", method = RequestMethod.GET)
    public String reportNotice(HttpServletRequest request, Model model) {
        return "reportNotice";
    }

    @RequestMapping(value = "/reportTAXGEN", method = RequestMethod.GET)
    public String reportTAXGEN(HttpServletRequest request, Model model) {
        return "reportTAXGEN";
    }

    @RequestMapping(value = "/reportCollection", method = RequestMethod.GET)
    public String reportCollection(HttpServletRequest request, Model model) {
        return "reportCollection";
    }

    @RequestMapping(value = "/searchByPropertyDetail", method = RequestMethod.POST)
    @ResponseBody
    public Object reportOnPropertyDetail(HttpServletRequest request, String params) {

        ReportQuery reportQuery = new Gson().fromJson(params, ReportQuery.class);
        Object jo = reportService.searchByPropertyDetails(reportQuery);

        return jo;
    }
    
     // Sandeep added below method
    @RequestMapping(value = "/searchObjReport", method = RequestMethod.POST)
    @ResponseBody
    public Object searchObjectionReport(HttpServletRequest request, @RequestParam("jsonViewCrit") String jsonViewCrit) {
        ReportQuery reportQuery = new Gson().fromJson(jsonViewCrit, ReportQuery.class);
        Object jo=null;
        try{
            jo = reportService.searchObjectionReport(reportQuery);
         }catch(Exception ex){
            logger.info(ex.getMessage());
        }
        return jo;
    }

    @RequestMapping(value = "/searchByTAXDetail", method = RequestMethod.POST)
    @ResponseBody
    public Object searchByTAXDetail(HttpServletRequest request, String params) {

        ReportQuery reportQuery = new Gson().fromJson(params, ReportQuery.class);
        Object jo = reportService.searchByTAXDetails(reportQuery);

        return jo;
    }

    @RequestMapping(value = "/searchByPendingPayment", method = RequestMethod.POST)
    @ResponseBody
    public Object searchByPendingPayment(HttpServletRequest request, String params) {

        ReportQuery reportQuery = new Gson().fromJson(params, ReportQuery.class);
        Object jo = reportService.searchByTAXDetails(reportQuery);

        return jo;
    }

    @RequestMapping(value = "/searchByPaymentDue", method = RequestMethod.POST)
    @ResponseBody
    public Object searchByPaymentDue(HttpServletRequest request, String params) {

        ReportQuery reportQuery = new Gson().fromJson(params, ReportQuery.class);
        Object jo = reportService.searchByPaymentDue(reportQuery);

        return jo;
    }

    @RequestMapping(value = "/searchByArrearDue", method = RequestMethod.POST)
    @ResponseBody
    public Object searchByArrearDue(HttpServletRequest request, String params) {

        ReportQuery reportQuery = new Gson().fromJson(params, ReportQuery.class);
        Object jo = reportService.searchByArrearDue(reportQuery);

        return jo;
    }

    @RequestMapping(value = "/searchByNotice", method = RequestMethod.POST)
    @ResponseBody
    public Object searchByNotice(HttpServletRequest request, String params) {

        ReportQuery reportQuery = new Gson().fromJson(params, ReportQuery.class);
        Object jo = reportService.searchByNotice(reportQuery);

        return jo;
    }
    
    @RequestMapping(value = "/searchByTAXGen", method = RequestMethod.POST)
    @ResponseBody
    public Object searchByTAXGen(HttpServletRequest request, String params) {

        ReportQuery reportQuery = new Gson().fromJson(params, ReportQuery.class);
        Object jo = reportService.searchByTAXGen(reportQuery);

        return jo;
    }
    
    @RequestMapping(value = "/searchByTAXAmount", method = RequestMethod.POST)
    @ResponseBody
    public Object searchByTAXAmount(HttpServletRequest request, String params) {

        ReportQuery reportQuery = new Gson().fromJson(params, ReportQuery.class);
        Object jo = reportService.searchByTAXAmount(reportQuery);

        return jo;
    }    
    
    @RequestMapping(value = "/searchByTAXCollection", method = RequestMethod.POST)
    @ResponseBody
    public Object searchByTAXCollection(HttpServletRequest request, String params) {

        ReportQuery reportQuery = new Gson().fromJson(params, ReportQuery.class);
        Object jo = reportService.searchByTAXGen(reportQuery);

        return jo;
    }
        
    @RequestMapping(value = "/printObjReceipt", method = RequestMethod.GET)
    public String printObjReceipt(HttpServletRequest request, Model model) {
        return "printObjReceipt";
    }

    @RequestMapping(value = "/printPaymentReceipt", method = RequestMethod.GET)
    public String printPaymentReceipt(HttpServletRequest request, Model model) {
        return "printPaymentReceipt";
    }
    
    @RequestMapping(value = "/loadObjectionData", method = RequestMethod.POST)
    @ResponseBody
    public Object searchObjectedProperty(HttpServletRequest request, @RequestParam("jsonViewCrit") String jsonViewCrit) {
        ReportQuery reportQuery = new Gson().fromJson(jsonViewCrit, ReportQuery.class);
        Object jo=null;
        try{
            jo = reportService.searchObjectionReport(reportQuery);
         }catch(Exception ex){
            logger.info(ex.getMessage());
        }
        return jo;
    }
    
    @ResponseBody
    @RequestMapping(value = "/printPayAmountReceipt", method = RequestMethod.POST)
    public Object printPayAmountReceipt(HttpServletRequest request, String params) {
         ReportQuery reportQuery = new Gson().fromJson(params, ReportQuery.class);
         Object jo=null;
        try {     
            jo = reportService.searchPropertyForReceipt(reportQuery);
        } catch (Exception ex) {
            logger.info(ex.getMessage());
        }
        return jo;
    }
    
    @RequestMapping(value = "/getObjectionCategory", method = RequestMethod.POST)
    @ResponseBody
    public Object getObjectionCategory(HttpServletRequest request, @RequestParam("jsonViewCrit") String jsonViewCrit) {   
        ReportQuery reportQuery = new Gson().fromJson(jsonViewCrit, ReportQuery.class);        
        Object jo=null;
        try{
            jo = reportService.getObjectionCategories(reportQuery);            
         }catch(Exception ex){
            logger.info(ex.getMessage());
        }
        return jo;
    }
    
}
