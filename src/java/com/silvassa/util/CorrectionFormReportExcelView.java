/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.silvassa.util;

import com.silvassa.controller.CorrectionController;
import com.silvassa.model.CorrectionFormFloorBean;
import com.silvassa.model.CorrectionFormFloorReport;
import com.silvassa.model.CorrectionFormReport;
import com.silvassa.model.CorrectionFormSaveBean;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import org.apache.poi.ss.usermodel.IndexedColors;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.poi.hssf.usermodel.HSSFRow;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.CellStyle;
import org.springframework.web.servlet.view.document.AbstractExcelView;
import com.silvassa.util.MatchString;
import org.apache.log4j.Logger;

/**
 *
 * @author CEINFO
 */
public class CorrectionFormReportExcelView extends AbstractExcelView {

    private static final Logger logger = Logger.getLogger(CorrectionFormReportExcelView.class);
    private Object XSSFCellStyle;
    HSSFWorkbook workbookforColor;

    @Override

    protected void buildExcelDocument(Map model, HSSFWorkbook workbook,
            HttpServletRequest request, HttpServletResponse response)
            throws Exception {

        HSSFSheet excelSheet = workbook.createSheet("Correction List");
        CellStyle newCellStyle = workbook.createCellStyle();
        workbookforColor = workbook;
        //newCellStyle.setFillPattern(FillPatternType.BIG_SPOTS);  
        newCellStyle.setWrapText(true);
        //setExcelHeader(excelSheet);
//                ArrayList<CorrectionFormFloorReport> rttt=mapFloorData.get("S05023975000");
//        Iterator rr=rttt.iterator();
//        while(rr.hasNext()){
//          CorrectionFormFloorReport pp=(CorrectionFormFloorReport)rr.next();
//           System.out.println("rttt"+pp.getFloorType());
//        }      
        String rtType = (String) model.get("reportType");
        if (rtType.equalsIgnoreCase("owner")) {
            List<CorrectionFormSaveBean> correctionList = (List<CorrectionFormSaveBean>) model.get("listReport");
            Map<String, CorrectionFormReport> mapList = (Map<String, CorrectionFormReport>) model.get("mapdata");
            setExcelHeaderOwner(excelSheet);
            setExcelRowsOwner(excelSheet, correctionList, mapList);
            response.setHeader("Content-Disposition", "attachment; filename=Owner_Name_Modification.xls");
        } else if (rtType.equalsIgnoreCase("address")) {
            List<CorrectionFormSaveBean> correctionList = (List<CorrectionFormSaveBean>) model.get("listReport");
            Map<String, CorrectionFormReport> mapList = (Map<String, CorrectionFormReport>) model.get("mapdata");
            setExcelHeaderAddress(excelSheet);
            setExcelRowsAddress(excelSheet, correctionList, mapList);
            response.setHeader("Content-Disposition", "attachment; filename=Address_Modification.xls");
        } else if (rtType.equalsIgnoreCase("arrear")) {
            List<CorrectionFormSaveBean> correctionList = (List<CorrectionFormSaveBean>) model.get("listReport");
            Map<String, CorrectionFormReport> mapList = (Map<String, CorrectionFormReport>) model.get("mapdata");
            setExcelHeaderArrear(excelSheet);
            setExcelRowsArrear(excelSheet, correctionList, mapList);
            response.setHeader("Content-Disposition", "attachment; filename=Arrear_Amount_Modification.xls");
        } else if (rtType.equalsIgnoreCase("smchouseno")) {
            List<CorrectionFormSaveBean> correctionList = (List<CorrectionFormSaveBean>) model.get("listReport");
            Map<String, CorrectionFormReport> mapList = (Map<String, CorrectionFormReport>) model.get("mapdata");
            setExcelHeaderSmcHouse(excelSheet);
            setExcelRowsSmcHouse(excelSheet, correctionList, mapList);
            response.setHeader("Content-Disposition", "attachment; filename=SMC_Holding_Number_Modification.xls");
        } else if (rtType.equalsIgnoreCase("floor")) {
            List<CorrectionFormFloorBean> listFloorReport = (List<CorrectionFormFloorBean>) model.get("listFloorReport");
            Map<String, ArrayList<CorrectionFormFloorReport>> mapFloorData = (Map<String, ArrayList<CorrectionFormFloorReport>>) model.get("mapFloorData");
            setExcelHeaderFloor(excelSheet, newCellStyle);
            setExcelRowsFloor(excelSheet, listFloorReport, mapFloorData, newCellStyle);
            response.setHeader("Content-Disposition", "attachment; filename=Floor_Detail_Modification.xls");
        } else if (rtType.equalsIgnoreCase("combinereport")) {
            List<CorrectionFormSaveBean> correctionList = (List<CorrectionFormSaveBean>) model.get("listReport");
            Map<String, CorrectionFormReport> mapList = (Map<String, CorrectionFormReport>) model.get("mapdata");

            Map<String, ArrayList<CorrectionFormFloorBean>> mapCorrectionFloorData = (Map<String, ArrayList<CorrectionFormFloorBean>>) model.get("mapCorrectionFloorData");
            Map<String, ArrayList<CorrectionFormFloorReport>> mapFloorData = (Map<String, ArrayList<CorrectionFormFloorReport>>) model.get("mapFloorSurveyData");
            //System.out.println("mapCorrectionFloorData excel "+mapCorrectionFloorData.size());
            //System.out.println("mapFloorData excel "+mapFloorData.size());
            setExcelHeaderCombine(excelSheet);
            setExcelRowsCombine(excelSheet, correctionList, mapList, mapCorrectionFloorData, mapFloorData);
            response.setHeader("Content-Disposition", "attachment; filename=Combine_Report_Modification.xls");
        } else if (rtType.equalsIgnoreCase("combineowner")) {
            List<CorrectionFormSaveBean> correctionList = (List<CorrectionFormSaveBean>) model.get("listReport");
            Map<String, CorrectionFormReport> mapList = (Map<String, CorrectionFormReport>) model.get("mapdata");

            Map<String, ArrayList<CorrectionFormFloorBean>> mapCorrectionFloorData = (Map<String, ArrayList<CorrectionFormFloorBean>>) model.get("mapCorrectionFloorData");
            Map<String, ArrayList<CorrectionFormFloorReport>> mapFloorData = (Map<String, ArrayList<CorrectionFormFloorReport>>) model.get("mapFloorSurveyData");
            //System.out.println("mapCorrectionFloorData excel "+mapCorrectionFloorData.size());
            //System.out.println("mapFloorData excel "+mapFloorData.size());
            setExcelHeaderOwnerCombine(excelSheet);
            setExcelRowsOwnerCombine(excelSheet, correctionList, mapList);
            response.setHeader("Content-Disposition", "attachment; filename=CombineOwner_Report_Modification.xls");
        }

                //Map<String,ArrayList<CorrectionFormFloorReport>> mapFloorData = (Map<String,Arraylist<CorrectionFormFloorReport>>)model.get("mapFloorData");
        //System.out.println("mapFloorData "+mapFloorData);
        //setExcelRows(excelSheet,correctionList,mapList);
    }

    public void setExcelHeaderOwner(HSSFSheet excelSheet) {
        HSSFRow excelHeader = excelSheet.createRow(0);

        //excelHeader.setRowStyle(newCellStyle);
        excelHeader.createCell(0).setCellValue("Sl.No.");
        excelHeader.createCell(1).setCellValue("Property Id");
        excelHeader.createCell(2).setCellValue("Application No.");
        excelHeader.createCell(3).setCellValue("Ward No.");

        excelHeader.createCell(4).setCellValue("Owner's name (survey)");
        excelHeader.createCell(5).setCellValue("Owner's name(for update)");

        //excelHeader.createCell(5).setCellValue("owner's sex (survey)");
        //excelHeader.createCell(6).setCellValue("owner's sex (for update)");
        excelHeader.createCell(6).setCellValue("Father's name (survey)");
        excelHeader.createCell(7).setCellValue("Father's name (for update)");

        excelHeader.createCell(8).setCellValue("owner's spouse (survey)");
        excelHeader.createCell(9).setCellValue("owner's spouse(for update)");
        excelHeader.createCell(10).setCellValue("Status");
        excelHeader.createCell(11).setCellValue("Remarks");
        excelHeader.createCell(12).setCellValue("Minor/Major");

//                excelHeader.createCell(9).setCellValue("Owner's contact (survey)");
//                excelHeader.createCell(10).setCellValue("Owner's contact (for update)");
//                
//                
//                excelHeader.createCell(11).setCellValue("Owner's email (survey)");
//                excelHeader.createCell(12).setCellValue("Owner's email (for update)");
//                
//                excelHeader.createCell(13).setCellValue("Owner's aadhaar no. (survey)");
//                excelHeader.createCell(14).setCellValue("Owner's aadhaar no.(for update)");
//                
//                excelHeader.createCell(15).setCellValue("Property owner correpondence address (survey)");
//                excelHeader.createCell(16).setCellValue("Property owner correpondence address (for update)");
//                
//                excelHeader.createCell(17).setCellValue("Occupier name (survey)");
//                excelHeader.createCell(18).setCellValue("Occupier name (for update)");
//                
//                excelHeader.createCell(19).setCellValue("Occupier's sex (survey)");
//                excelHeader.createCell(20).setCellValue("Occupier's sex (for update)");
//                
//                excelHeader.createCell(21).setCellValue("Occupier's father name (survey)");
//                excelHeader.createCell(22).setCellValue("Occupier's father name (for update)");
//                
//                excelHeader.createCell(23).setCellValue("Occupier's contact (survey)");
//                excelHeader.createCell(24).setCellValue("Occupier's contact (for update)");
//                
//                excelHeader.createCell(25).setCellValue("Occupier's email (survey)");
//                excelHeader.createCell(26).setCellValue("Occupier's email (for updaate)");
//                
//                excelHeader.createCell(27).setCellValue("Occupier's aadhaar no. (survey)");
//                excelHeader.createCell(28).setCellValue("Occupier's aadhaar no. (for update)");
//                
//                excelHeader.createCell(29).setCellValue("Ward no.");
//                excelHeader.createCell(30).setCellValue("Application no.");
//                excelHeader.createCell(31).setCellValue("Reqeust date");
//                excelHeader.createCell(32).setCellValue("Applicant name");
//                
    }

    public void setExcelHeaderAddress(HSSFSheet excelSheet) {
        HSSFRow excelHeader = excelSheet.createRow(0);
        excelHeader.createCell(0).setCellValue("Sl.No.");
        excelHeader.createCell(1).setCellValue("Property Id");
        excelHeader.createCell(2).setCellValue("Application No.");
        excelHeader.createCell(3).setCellValue("Ward No.");
        excelHeader.createCell(4).setCellValue("Owner's name (survey)");

        excelHeader.createCell(5).setCellValue("Complete address (survey)");
        excelHeader.createCell(6).setCellValue("Complete address(for update)");
        excelHeader.createCell(7).setCellValue("Status");
        excelHeader.createCell(8).setCellValue("Remarks");

//                excelHeader.createCell(3).setCellValue("House no. (survey)");
//                excelHeader.createCell(4).setCellValue("House no. (for update)");
//                
//                excelHeader.createCell(5).setCellValue("Building name (survey)");
//                excelHeader.createCell(6).setCellValue("Building name (for update)");
//                
//                excelHeader.createCell(7).setCellValue("Road  name (survey)");
//                excelHeader.createCell(8).setCellValue("Road  name (for update)");
//                
//                excelHeader.createCell(9).setCellValue("Sublocality (survey)");
//                excelHeader.createCell(10).setCellValue("Sublocality (for update)");
//                
//                excelHeader.createCell(11).setCellValue("Locality (survey)");
//                excelHeader.createCell(12).setCellValue("Locality (for update)");
//                
//                excelHeader.createCell(13).setCellValue("Landmark (survey)");
//                excelHeader.createCell(14).setCellValue("Landmark (for update)");
//                
//                excelHeader.createCell(15).setCellValue("Ward no.");
//                excelHeader.createCell(16).setCellValue("Application no.");
//                excelHeader.createCell(17).setCellValue("Reqeust date");
//                excelHeader.createCell(18).setCellValue("Applicant name");
//                
    }

    public void setExcelHeaderArrear(HSSFSheet excelSheet) {
        HSSFRow excelHeader = excelSheet.createRow(0);
        excelHeader.createCell(0).setCellValue("Sl.No.");
        excelHeader.createCell(1).setCellValue("Property Id");
        excelHeader.createCell(2).setCellValue("Application No.");
        excelHeader.createCell(3).setCellValue("Ward No.");
        excelHeader.createCell(4).setCellValue("Owner's name (survey)");

        //excelHeader.createCell(4).setCellValue("SMC house property No.(survey)");
        //excelHeader.createCell(5).setCellValue("SMC house property No.( for update)");
        excelHeader.createCell(5).setCellValue("Arrear amount (survey) ");
        excelHeader.createCell(6).setCellValue("Arrear amount (for update)");
        excelHeader.createCell(7).setCellValue("Status");
        excelHeader.createCell(8).setCellValue("Remarks");

//                excelHeader.createCell(1).setCellValue("Electric service connection no (survey)");
//                excelHeader.createCell(2).setCellValue("Electric service connection no (for update)");
//                
//                excelHeader.createCell(3).setCellValue("Survey no. smc (survey)");
//                excelHeader.createCell(4).setCellValue("Survey no. smc (for update)");
//                
//                excelHeader.createCell(5).setCellValue("Plot No. smc (survey)");
//                excelHeader.createCell(6).setCellValue("Plot No. smc (for update)");
//                
//                
//                
//                excelHeader.createCell(11).setCellValue("Ward no.");
//                excelHeader.createCell(12).setCellValue("Application no.");
//                excelHeader.createCell(13).setCellValue("Reqeust date");
//                excelHeader.createCell(14).setCellValue("Applicant name");
//                
    }

    public void setExcelHeaderSmcHouse(HSSFSheet excelSheet) {
        HSSFRow excelHeader = excelSheet.createRow(0);
        excelHeader.createCell(0).setCellValue("Sl.No.");
        excelHeader.createCell(1).setCellValue("Property Id");
        excelHeader.createCell(2).setCellValue("Application No.");
        excelHeader.createCell(3).setCellValue("Ward No.");
        excelHeader.createCell(4).setCellValue("Owner's name (survey)");

        excelHeader.createCell(5).setCellValue("SMC house property No.(survey)");
        excelHeader.createCell(6).setCellValue("SMC house property No.( for update)");
        excelHeader.createCell(7).setCellValue("Status");
        excelHeader.createCell(8).setCellValue("Remarks");

//                excelHeader.createCell(6).setCellValue("Arrear amount (survey) ");
//                excelHeader.createCell(7).setCellValue("Arrear amount (for update)");
//                excelHeader.createCell(1).setCellValue("Electric service connection no (survey)");
//                excelHeader.createCell(2).setCellValue("Electric service connection no (for update)");
//                
//                excelHeader.createCell(3).setCellValue("Survey no. smc (survey)");
//                excelHeader.createCell(4).setCellValue("Survey no. smc (for update)");
//                
//                excelHeader.createCell(5).setCellValue("Plot No. smc (survey)");
//                excelHeader.createCell(6).setCellValue("Plot No. smc (for update)");
//                
//                
//                
//                excelHeader.createCell(11).setCellValue("Ward no.");
//                excelHeader.createCell(12).setCellValue("Application no.");
//                excelHeader.createCell(13).setCellValue("Reqeust date");
//                excelHeader.createCell(14).setCellValue("Applicant name");
//                
    }

    public void setExcelHeaderFloor(HSSFSheet excelSheet, CellStyle newCellStyle) {
        HSSFRow excelHeader = excelSheet.createRow(0);
        excelHeader.setRowStyle(newCellStyle);
        excelHeader.createCell(0).setCellValue("Sl.No.");
        excelHeader.createCell(1).setCellValue("Property Id");
        excelHeader.createCell(2).setCellValue("Application No.");
        excelHeader.createCell(3).setCellValue("Ward No.");
        excelHeader.createCell(4).setCellValue("Owner's name (survey)");

        excelHeader.createCell(5).setCellValue("Property floor id(survey)");
        excelHeader.createCell(6).setCellValue("Property floor id(for update)");

        excelHeader.createCell(7).setCellValue("Floor type (survey)");
        excelHeader.createCell(8).setCellValue("Floor type (for update)");

        excelHeader.createCell(9).setCellValue("Built up area (survey)");
        excelHeader.createCell(10).setCellValue("Built up area (for update)");

        excelHeader.createCell(11).setCellValue("Property use (survey)");
        excelHeader.createCell(12).setCellValue("Property use( for update)");

        excelHeader.createCell(13).setCellValue("Property subtype (survey) ");
        excelHeader.createCell(14).setCellValue("Property subtype(for update)");

        excelHeader.createCell(15).setCellValue("Constructiontype (survey)");
        excelHeader.createCell(16).setCellValue("Constructiontype (for update)");

        excelHeader.createCell(17).setCellValue("self rent(survey)");
        excelHeader.createCell(18).setCellValue("self rent(for update)");

        excelHeader.createCell(19).setCellValue("Annual rent value(survey)");
        excelHeader.createCell(20).setCellValue("Annual rent value(for update)");

        //excelHeader.createCell(17).setCellValue("Ward no.");
        //excelHeader.createCell(18).setCellValue("Application no.");
        //excelHeader.createCell(19).setCellValue("Reqeust date");
        //excelHeader.createCell(20).setCellValue("Applicant name");
        excelHeader.createCell(21).setCellValue("Edit/New row/Delete row");
        excelHeader.createCell(22).setCellValue("Status");
        excelHeader.createCell(23).setCellValue("Remarks");
        //excelHeader.createCell(21).setCellValue("Delete row");
        //excelHeader.createCell(23).setCellValue("Status");

    }

    public void setExcelHeader(HSSFSheet excelSheet) {
        HSSFRow excelHeader = excelSheet.createRow(0);
        excelHeader.createCell(0).setCellValue("Property id");
        excelHeader.createCell(1).setCellValue("Owner's name (survey)");
        excelHeader.createCell(2).setCellValue("Owner's name(for update)");

        excelHeader.createCell(3).setCellValue("owner's sex(survey)");
        excelHeader.createCell(4).setCellValue("owner's sex(for update)");

        excelHeader.createCell(5).setCellValue("Father's name(survey)");
        excelHeader.createCell(6).setCellValue("Father's name(for update");

        excelHeader.createCell(7).setCellValue("owner's spouse (survey)");
        excelHeader.createCell(8).setCellValue("owner's spouse(for update)");

        excelHeader.createCell(9).setCellValue("Owner's contact (survey)");
        excelHeader.createCell(10).setCellValue("Owner's contact (for update)");

        excelHeader.createCell(11).setCellValue("Owner's email (survey)");
        excelHeader.createCell(12).setCellValue("Owner's email (for update)");

        excelHeader.createCell(13).setCellValue("Owner's aadhaar no. (survey)");
        excelHeader.createCell(14).setCellValue("Owner's aadhaar no.(for update)");

        excelHeader.createCell(15).setCellValue("Property owner correpondence address (survey)");
        excelHeader.createCell(16).setCellValue("Property owner correpondence address (for update)");

        excelHeader.createCell(17).setCellValue("Occupier name (survey)");
        excelHeader.createCell(18).setCellValue("Occupier name (for update)");

        excelHeader.createCell(19).setCellValue("Occupier's sex (survey)");
        excelHeader.createCell(20).setCellValue("Occupier's sex (for update)");

        excelHeader.createCell(21).setCellValue("Occupier's father name (survey)");
        excelHeader.createCell(22).setCellValue("Occupier's father name (for update)");

        excelHeader.createCell(23).setCellValue("Occupier's contact (survey)");
        excelHeader.createCell(24).setCellValue("Occupier's contact (for update)");

        excelHeader.createCell(25).setCellValue("Occupier's email (survey)");
        excelHeader.createCell(26).setCellValue("Occupier's email (for updaate)");

        excelHeader.createCell(27).setCellValue("Occupier's aadhaar no. (survey)");
        excelHeader.createCell(28).setCellValue("Occupier's aadhaar no. (for update)");

        excelHeader.createCell(29).setCellValue("Flat no. (survey)");
        excelHeader.createCell(30).setCellValue("Plot no.(for update)");

        excelHeader.createCell(31).setCellValue("House no. (survey)");
        excelHeader.createCell(32).setCellValue("House no. (for update)");

        excelHeader.createCell(33).setCellValue("Building name (survey)");
        excelHeader.createCell(34).setCellValue("Building name (for update)");

        excelHeader.createCell(35).setCellValue("Road  name (survey)");
        excelHeader.createCell(36).setCellValue("Road  name (for update)");

        excelHeader.createCell(37).setCellValue("Sublocality (survey)");
        excelHeader.createCell(38).setCellValue("Sublocality (for update)");

        excelHeader.createCell(39).setCellValue("Locality (survey)");
        excelHeader.createCell(40).setCellValue("Locality (for update)");

        excelHeader.createCell(41).setCellValue("LandMark (survey)");
        excelHeader.createCell(42).setCellValue("LandMark (for update)");

        excelHeader.createCell(43).setCellValue("Electric service connection no (survey)");
        excelHeader.createCell(44).setCellValue("Electric service connection no (for update)");

        excelHeader.createCell(45).setCellValue("Survey no. smc (survey)");
        excelHeader.createCell(46).setCellValue("Survey no. smc (for update)");

        excelHeader.createCell(47).setCellValue("Plot No. smc (survey)");
        excelHeader.createCell(48).setCellValue("Plot No. smc (for update)");

        excelHeader.createCell(49).setCellValue("SMC house property no. (survey)");
        excelHeader.createCell(50).setCellValue("SMC house property no.( for update)");

        excelHeader.createCell(51).setCellValue("Arrear amount (survey) ");
        excelHeader.createCell(52).setCellValue("Arrear amount (for update)");

        excelHeader.createCell(53).setCellValue("Ward no.");
        excelHeader.createCell(54).setCellValue("Application no.");
        excelHeader.createCell(55).setCellValue("Reqeust date");
        excelHeader.createCell(56).setCellValue("Applicant name");

    }

    public void setExcelHeaderOwnerCombine(HSSFSheet excelSheet) {

        logger.info("HeaderOwnerCombine header start");
        HSSFRow excelHeader = excelSheet.createRow(0);

        //excelHeader.setRowStyle(newCellStyle);
        excelHeader.createCell(0).setCellValue("Sl.No.");
        excelHeader.createCell(1).setCellValue("Property Id");
        excelHeader.createCell(2).setCellValue("Application No.");
        excelHeader.createCell(3).setCellValue("Ward No.");

        excelHeader.createCell(4).setCellValue("Owner's name (survey)");
        excelHeader.createCell(5).setCellValue("Owner's name(for update)");

        //excelHeader.createCell(5).setCellValue("owner's sex (survey)");
        //excelHeader.createCell(6).setCellValue("owner's sex (for update)");
        excelHeader.createCell(6).setCellValue("Father's name (survey)");
        excelHeader.createCell(7).setCellValue("Father's name (for update)");

        excelHeader.createCell(8).setCellValue("owner's spouse (survey)");
        excelHeader.createCell(9).setCellValue("owner's spouse(for update)");

        excelHeader.createCell(10).setCellValue("Owner's aadhaar no. (survey)");
        excelHeader.createCell(11).setCellValue("Owner's aadhaar no.(for update)");

        excelHeader.createCell(12).setCellValue("Complete address (survey)");
        excelHeader.createCell(13).setCellValue("Complete address(for update)");

        excelHeader.createCell(14).setCellValue("SMC house property No.(survey)");
        excelHeader.createCell(15).setCellValue("SMC house property No.( for update)");

        excelHeader.createCell(16).setCellValue("Arrear amount (survey) ");
        excelHeader.createCell(17).setCellValue("Arrear amount (for update)");

        excelHeader.createCell(18).setCellValue("Status");
        excelHeader.createCell(19).setCellValue("Remarks");
        //excelHeader.createCell(34).setCellValue("Minor/Major");

//                excelHeader.createCell(9).setCellValue("Owner's contact (survey)");
//                excelHeader.createCell(10).setCellValue("Owner's contact (for update)");
//                
//                
//                excelHeader.createCell(11).setCellValue("Owner's email (survey)");
//                excelHeader.createCell(12).setCellValue("Owner's email (for update)");
//                
//                excelHeader.createCell(13).setCellValue("Owner's aadhaar no. (survey)");
//                excelHeader.createCell(14).setCellValue("Owner's aadhaar no.(for update)");
//                
//                excelHeader.createCell(15).setCellValue("Property owner correpondence address (survey)");
//                excelHeader.createCell(16).setCellValue("Property owner correpondence address (for update)");
//                
//                excelHeader.createCell(17).setCellValue("Occupier name (survey)");
//                excelHeader.createCell(18).setCellValue("Occupier name (for update)");
//                
//                excelHeader.createCell(19).setCellValue("Occupier's sex (survey)");
//                excelHeader.createCell(20).setCellValue("Occupier's sex (for update)");
//                
//                excelHeader.createCell(21).setCellValue("Occupier's father name (survey)");
//                excelHeader.createCell(22).setCellValue("Occupier's father name (for update)");
//                
//                excelHeader.createCell(23).setCellValue("Occupier's contact (survey)");
//                excelHeader.createCell(24).setCellValue("Occupier's contact (for update)");
//                
//                excelHeader.createCell(25).setCellValue("Occupier's email (survey)");
//                excelHeader.createCell(26).setCellValue("Occupier's email (for updaate)");
//                
//                excelHeader.createCell(27).setCellValue("Occupier's aadhaar no. (survey)");
//                excelHeader.createCell(28).setCellValue("Occupier's aadhaar no. (for update)");
//                
//                excelHeader.createCell(29).setCellValue("Ward no.");
//                excelHeader.createCell(30).setCellValue("Application no.");
//                excelHeader.createCell(31).setCellValue("Reqeust date");
//                excelHeader.createCell(32).setCellValue("Applicant name");
        logger.info("HeaderOwnerCombine header end");
    }

    public void setExcelHeaderCombine(HSSFSheet excelSheet) {
        HSSFRow excelHeader = excelSheet.createRow(0);

        //excelHeader.setRowStyle(newCellStyle);
        excelHeader.createCell(0).setCellValue("Sl.No.");
        excelHeader.createCell(1).setCellValue("Property Id");
        excelHeader.createCell(2).setCellValue("Application No.");
        excelHeader.createCell(3).setCellValue("Ward No.");

        excelHeader.createCell(4).setCellValue("Owner's name (survey)");
        excelHeader.createCell(5).setCellValue("Owner's name(for update)");

        //excelHeader.createCell(5).setCellValue("owner's sex (survey)");
        //excelHeader.createCell(6).setCellValue("owner's sex (for update)");
        excelHeader.createCell(6).setCellValue("Father's name (survey)");
        excelHeader.createCell(7).setCellValue("Father's name (for update)");

        excelHeader.createCell(8).setCellValue("owner's spouse (survey)");
        excelHeader.createCell(9).setCellValue("owner's spouse(for update)");

        excelHeader.createCell(10).setCellValue("Owner's aadhaar no. (survey)");
        excelHeader.createCell(11).setCellValue("Owner's aadhaar no.(for update)");

        excelHeader.createCell(12).setCellValue("Complete address (survey)");
        excelHeader.createCell(13).setCellValue("Complete address(for update)");

        excelHeader.createCell(14).setCellValue("SMC house property No.(survey)");
        excelHeader.createCell(15).setCellValue("SMC house property No.( for update)");

        excelHeader.createCell(16).setCellValue("Arrear amount (survey) ");
        excelHeader.createCell(17).setCellValue("Arrear amount (for update)");

        excelHeader.createCell(18).setCellValue("Floor type (survey)");
        excelHeader.createCell(19).setCellValue("Floor type (for update)");

        excelHeader.createCell(20).setCellValue("Built up area (survey)");
        excelHeader.createCell(21).setCellValue("Built up area (for update)");

        excelHeader.createCell(22).setCellValue("Property use (survey)");
        excelHeader.createCell(23).setCellValue("Property use( for update)");
        excelHeader.createCell(24).setCellValue("Property subtype (survey) ");
        excelHeader.createCell(25).setCellValue("Property subtype(for update)");

        excelHeader.createCell(26).setCellValue("Constructiontype (survey)");
        excelHeader.createCell(27).setCellValue("Constructiontype (for update)");

        excelHeader.createCell(28).setCellValue("self rent(survey)");
        excelHeader.createCell(29).setCellValue("self rent(for update)");

        excelHeader.createCell(30).setCellValue("Annual rent value(survey)");
        excelHeader.createCell(31).setCellValue("Annual rent value(for update)");
        excelHeader.createCell(32).setCellValue("Edit/New row/Delete row");

        excelHeader.createCell(33).setCellValue("Status");
        excelHeader.createCell(34).setCellValue("Remarks");
        //excelHeader.createCell(34).setCellValue("Minor/Major");

//                excelHeader.createCell(9).setCellValue("Owner's contact (survey)");
//                excelHeader.createCell(10).setCellValue("Owner's contact (for update)");
//                
//                
//                excelHeader.createCell(11).setCellValue("Owner's email (survey)");
//                excelHeader.createCell(12).setCellValue("Owner's email (for update)");
//                
//                excelHeader.createCell(13).setCellValue("Owner's aadhaar no. (survey)");
//                excelHeader.createCell(14).setCellValue("Owner's aadhaar no.(for update)");
//                
//                excelHeader.createCell(15).setCellValue("Property owner correpondence address (survey)");
//                excelHeader.createCell(16).setCellValue("Property owner correpondence address (for update)");
//                
//                excelHeader.createCell(17).setCellValue("Occupier name (survey)");
//                excelHeader.createCell(18).setCellValue("Occupier name (for update)");
//                
//                excelHeader.createCell(19).setCellValue("Occupier's sex (survey)");
//                excelHeader.createCell(20).setCellValue("Occupier's sex (for update)");
//                
//                excelHeader.createCell(21).setCellValue("Occupier's father name (survey)");
//                excelHeader.createCell(22).setCellValue("Occupier's father name (for update)");
//                
//                excelHeader.createCell(23).setCellValue("Occupier's contact (survey)");
//                excelHeader.createCell(24).setCellValue("Occupier's contact (for update)");
//                
//                excelHeader.createCell(25).setCellValue("Occupier's email (survey)");
//                excelHeader.createCell(26).setCellValue("Occupier's email (for updaate)");
//                
//                excelHeader.createCell(27).setCellValue("Occupier's aadhaar no. (survey)");
//                excelHeader.createCell(28).setCellValue("Occupier's aadhaar no. (for update)");
//                
//                excelHeader.createCell(29).setCellValue("Ward no.");
//                excelHeader.createCell(30).setCellValue("Application no.");
//                excelHeader.createCell(31).setCellValue("Reqeust date");
//                excelHeader.createCell(32).setCellValue("Applicant name");
//                
    }

    public void setExcelHeaderCombineFloor(HSSFSheet excelSheet, CellStyle newCellStyle) {
        HSSFRow excelHeader = excelSheet.createRow(0);
        excelHeader.setRowStyle(newCellStyle);
        excelHeader.createCell(0).setCellValue("Sl.No.");
        excelHeader.createCell(1).setCellValue("Property Id");
        excelHeader.createCell(2).setCellValue("Application No.");
        excelHeader.createCell(3).setCellValue("Ward No.");
        excelHeader.createCell(4).setCellValue("Owner's name (survey)");

        excelHeader.createCell(5).setCellValue("Property floor id(survey)");
        excelHeader.createCell(6).setCellValue("Property floor id(for update)");

        excelHeader.createCell(7).setCellValue("Floor type (survey)");
        excelHeader.createCell(8).setCellValue("Floor type (for update)");

        excelHeader.createCell(9).setCellValue("Built up area (survey)");
        excelHeader.createCell(10).setCellValue("Built up area (for update)");

        excelHeader.createCell(11).setCellValue("Property use (survey)");
        excelHeader.createCell(12).setCellValue("Property use( for update)");

        excelHeader.createCell(13).setCellValue("Property subtype (survey) ");
        excelHeader.createCell(14).setCellValue("Property subtype(for update)");

        excelHeader.createCell(15).setCellValue("Constructiontype (survey)");
        excelHeader.createCell(16).setCellValue("Constructiontype (for update)");

        excelHeader.createCell(17).setCellValue("self rent(survey)");
        excelHeader.createCell(18).setCellValue("self rent(for update)");

        excelHeader.createCell(19).setCellValue("Annual rent value(survey)");
        excelHeader.createCell(20).setCellValue("Annual rent value(for update)");

        //excelHeader.createCell(17).setCellValue("Ward no.");
        //excelHeader.createCell(18).setCellValue("Application no.");
        //excelHeader.createCell(19).setCellValue("Reqeust date");
        //excelHeader.createCell(20).setCellValue("Applicant name");
        excelHeader.createCell(21).setCellValue("Edit/New row/Delete row");
        excelHeader.createCell(22).setCellValue("Status");
        excelHeader.createCell(23).setCellValue("Remarks");
        //excelHeader.createCell(21).setCellValue("Delete row");
        //excelHeader.createCell(23).setCellValue("Status");

    }

    public void setExcelRows(HSSFSheet excelSheet, List<CorrectionFormSaveBean> correctionList, Map<String, CorrectionFormReport> mapList) {
        int record = 1;
        for (CorrectionFormSaveBean fr : correctionList) {
            CorrectionFormReport rt = mapList.get(fr.getUniqueId());

            HSSFRow excelRow = excelSheet.createRow(record++);
            excelRow.createCell(0).setCellValue(fr.getUniqueId());
            excelRow.createCell(1).setCellValue(rt.getOwnerName());
            excelRow.createCell(2).setCellValue(fr.getOwnerName());
            if (fr.getOwnerSex().equals("-1")) {
                excelRow.createCell(3).setCellValue(rt.getOwnerSex());
                excelRow.createCell(4).setCellValue("");
            } else {
                excelRow.createCell(3).setCellValue(rt.getOwnerSex());
                excelRow.createCell(4).setCellValue(fr.getOwnerSex());
            }

            excelRow.createCell(5).setCellValue(rt.getOwnerFather());
            excelRow.createCell(6).setCellValue(fr.getOwnerFather());

            excelRow.createCell(7).setCellValue(rt.getSpouseName());
            excelRow.createCell(8).setCellValue(fr.getSpouseName());

            excelRow.createCell(9).setCellValue(rt.getOwnerContact());
            excelRow.createCell(10).setCellValue(fr.getOwnerContact());

            excelRow.createCell(11).setCellValue(rt.getOwnerEmail());
            excelRow.createCell(12).setCellValue(fr.getOwnerEmail());

            excelRow.createCell(13).setCellValue(rt.getOwnerAadharNo());
            excelRow.createCell(14).setCellValue(fr.getOwnerAadharNo());

            excelRow.createCell(15).setCellValue(rt.getPropertyOwnerAddress());
            excelRow.createCell(16).setCellValue(fr.getPropertyOwnerAddress());

            excelRow.createCell(17).setCellValue(rt.getOccupierName());
            excelRow.createCell(18).setCellValue(fr.getOccupierName());

            if (fr.getOccupierSex().equals("-1")) {
                excelRow.createCell(19).setCellValue(rt.getOccupierSex());
                excelRow.createCell(20).setCellValue("");
            } else {
                excelRow.createCell(19).setCellValue(rt.getOccupierSex());
                excelRow.createCell(20).setCellValue(fr.getOccupierSex());
            }

            excelRow.createCell(21).setCellValue(rt.getOccupierFather());
            excelRow.createCell(22).setCellValue(fr.getOccupierFather());

            excelRow.createCell(23).setCellValue(rt.getOccupierContact());
            excelRow.createCell(24).setCellValue(fr.getOccupierContact());

            excelRow.createCell(25).setCellValue(rt.getOccupierEmail());
            excelRow.createCell(26).setCellValue(fr.getOccupierEmail());

            excelRow.createCell(27).setCellValue(rt.getOccupierAadharNo());
            excelRow.createCell(28).setCellValue(fr.getOccupierAadharNo());

            excelRow.createCell(29).setCellValue(rt.getPlotNo());
            excelRow.createCell(30).setCellValue(fr.getPlotNo());

            excelRow.createCell(31).setCellValue(rt.getHoueNo());
            excelRow.createCell(32).setCellValue(fr.getHoueNo());

            excelRow.createCell(33).setCellValue(rt.getBuildingName());
            excelRow.createCell(34).setCellValue(fr.getBuildingName());

            excelRow.createCell(35).setCellValue(rt.getRoadName());
            excelRow.createCell(36).setCellValue(fr.getRoadName());

            excelRow.createCell(37).setCellValue(rt.getSubLocality());
            excelRow.createCell(38).setCellValue(fr.getSubLocality());

            excelRow.createCell(39).setCellValue(rt.getLocName());
            excelRow.createCell(40).setCellValue(fr.getLocName());

            excelRow.createCell(41).setCellValue(rt.getLandMark());
            excelRow.createCell(42).setCellValue(fr.getLandMark());

            excelRow.createCell(43).setCellValue(rt.getElectricServiceNo());
            excelRow.createCell(44).setCellValue(fr.getElectricServiceNo());

            excelRow.createCell(45).setCellValue(rt.getSurveyNo());
            excelRow.createCell(46).setCellValue(fr.getSurveyNo());

            excelRow.createCell(47).setCellValue(rt.getPlotSmc());
            excelRow.createCell(48).setCellValue(fr.getPlotSmc());

            excelRow.createCell(49).setCellValue(rt.getSmcHoldingNo());
            excelRow.createCell(50).setCellValue(fr.getSmcHoldingNo());

            excelRow.createCell(51).setCellValue(rt.getArrearAmount());
            excelRow.createCell(52).setCellValue(fr.getArrearAmount());
            excelRow.createCell(53).setCellValue(fr.getWardNo());
            excelRow.createCell(54).setCellValue(fr.getApplicationNo());
            excelRow.createCell(55).setCellValue(fr.getNoticeDate());
            excelRow.createCell(56).setCellValue(fr.getApplicantName());

        }
    }

    public void setExcelRowsOwner(HSSFSheet excelSheet, List<CorrectionFormSaveBean> correctionList, Map<String, CorrectionFormReport> mapList) {
        int record = 1;
        int cellCtr = 0;
        String showMsg = "";
        CellStyle styleColor = workbookforColor.createCellStyle();
        for (CorrectionFormSaveBean fr : correctionList) {
            cellCtr++;
            CorrectionFormReport rt = mapList.get(fr.getUniqueId());
            if (rt == null) {
                continue;
            }

            
            styleColor.setFillForegroundColor(IndexedColors.LIGHT_YELLOW.getIndex());
            styleColor.setFillPattern(CellStyle.SOLID_FOREGROUND);
            styleColor.setWrapText(true);
            HSSFRow excelRow = excelSheet.createRow(record++);
            excelRow.createCell(0).setCellValue(cellCtr);
            excelRow.createCell(1).setCellValue(fr.getUniqueId());
            excelRow.createCell(2).setCellValue(fr.getApplicationNo());
            excelRow.createCell(3).setCellValue(fr.getWardNo());
            excelRow.createCell(4).setCellValue(rt.getOwnerName());

            int matchCtr = MatchString.getMatchPercentage(rt.getOwnerName(), fr.getOwnerName());
            if (matchCtr == 100) {
                showMsg = "No Change";
            } else if (matchCtr >= 60) {
                showMsg = "Minor";
            } else {
                showMsg = "Major";
            }
            String checkOwner = fr.getCheckOwnerName();
            String checkFather = fr.getCheckFatherName();
            String checkSpouse = fr.getCheckSpouseName();

            if (checkOwner.equalsIgnoreCase("Y")) {

                if (showMsg.equalsIgnoreCase("No Change")) {
                    Cell cell1 = excelRow.createCell(5);
                    cell1.setCellValue(fr.getOwnerName());
                    //cell1.setCellStyle(styleColor);
                } else {
                    Cell cell1 = excelRow.createCell(5);
                    cell1.setCellValue(fr.getOwnerName());
                    cell1.setCellStyle(styleColor);
                }

            } else {
                excelRow.createCell(5).setCellValue(fr.getOwnerName());
            }

//			if(fr.getOwnerSex().equals("-1")){
//                            excelRow.createCell(5).setCellValue(rt.getOwnerSex());
//                            excelRow.createCell(6).setCellValue("");
//                        }else{
//                            excelRow.createCell(5).setCellValue(rt.getOwnerSex());
//                            excelRow.createCell(6).setCellValue(fr.getOwnerSex()); 
//                        }
            excelRow.createCell(6).setCellValue(rt.getOwnerFather());

            if (checkFather.equalsIgnoreCase("Y")) {
                Cell cell1 = excelRow.createCell(7);
                cell1.setCellValue(fr.getOwnerFather());
                cell1.setCellStyle(styleColor);

                //excelRow.createCell(7).setCellValue(fr.getOwnerFather());
            } else {
                excelRow.createCell(7).setCellValue(fr.getOwnerFather());
            }

            excelRow.createCell(8).setCellValue(rt.getSpouseName());
            if (checkSpouse.equalsIgnoreCase("Y")) {
                Cell cell1 = excelRow.createCell(9);
                cell1.setCellValue(fr.getSpouseName());
                cell1.setCellStyle(styleColor);

                //excelRow.createCell(7).setCellValue(fr.getOwnerFather());
            } else {
                excelRow.createCell(9).setCellValue(fr.getSpouseName());
            }

            excelRow.createCell(10).setCellValue("Verified");
            excelRow.createCell(11).setCellValue(fr.getRemarks());
            excelRow.createCell(12).setCellValue(showMsg);

//                        excelRow.createCell(9).setCellValue(rt.getOwnerContact());
//                        excelRow.createCell(10).setCellValue(fr.getOwnerContact());
//                        
//			excelRow.createCell(11).setCellValue(rt.getOwnerEmail());
//                        excelRow.createCell(12).setCellValue(fr.getOwnerEmail());
//                        
//                        excelRow.createCell(13).setCellValue(rt.getOwnerAadharNo());
//                        excelRow.createCell(14).setCellValue(fr.getOwnerAadharNo());
//                        
//                        excelRow.createCell(15).setCellValue(rt.getPropertyOwnerAddress());
//                        excelRow.createCell(16).setCellValue(fr.getPropertyOwnerAddress());
//                        
//                        excelRow.createCell(17).setCellValue(rt.getOccupierName());
//                        excelRow.createCell(18).setCellValue(fr.getOccupierName());
//                        
//                        if(fr.getOccupierSex().equals("-1")){
//                             excelRow.createCell(19).setCellValue(rt.getOccupierSex());
//                            excelRow.createCell(20).setCellValue("");
//                        }else{
//                            excelRow.createCell(19).setCellValue(rt.getOccupierSex()); 
//                            excelRow.createCell(20).setCellValue(fr.getOccupierSex()); 
//                        }
//                        
//                        
//                        excelRow.createCell(21).setCellValue(rt.getOccupierFather());
//                        excelRow.createCell(22).setCellValue(fr.getOccupierFather());
//                        
//                        excelRow.createCell(23).setCellValue(rt.getOccupierContact());
//                        excelRow.createCell(24).setCellValue(fr.getOccupierContact());
//                        
//                        excelRow.createCell(25).setCellValue(rt.getOccupierEmail());
//                        excelRow.createCell(26).setCellValue(fr.getOccupierEmail());
//                        
//                        excelRow.createCell(27).setCellValue(rt.getOccupierAadharNo());
//                        excelRow.createCell(28).setCellValue(fr.getOccupierAadharNo());
//                        
//                        
//                        excelRow.createCell(31).setCellValue(fr.getNoticeDate());
//                        excelRow.createCell(32).setCellValue(fr.getApplicantName());
//                        
            showMsg = "";

        }
    }

    public void setExcelRowsAddress(HSSFSheet excelSheet, List<CorrectionFormSaveBean> correctionList, Map<String, CorrectionFormReport> mapList) {
        int record = 1;
        String cAddress = "";
        int check_plot = 0;
        int check_house = 0;
        int check_buildingName = 0;
        int check_roadName = 0;
        int check_subloc = 0;
        int check_locName = 0;
        int check_landMarks = 0;
        int cellCtr = 0;

        for (CorrectionFormSaveBean fr : correctionList) {
            cellCtr++;
            CorrectionFormReport rt = mapList.get(fr.getUniqueId());
            if (rt == null) {
                continue;
            }
            HSSFRow excelRow = excelSheet.createRow(record++);
            excelRow.createCell(0).setCellValue(cellCtr);
            excelRow.createCell(1).setCellValue(fr.getUniqueId());
            excelRow.createCell(2).setCellValue(fr.getApplicationNo());
            excelRow.createCell(3).setCellValue(fr.getWardNo());
            excelRow.createCell(4).setCellValue(rt.getOwnerName());
            excelRow.createCell(5).setCellValue(rt.getAddress());
            excelRow.createCell(6).setCellValue(fr.getAddress());
            excelRow.createCell(7).setCellValue("Verified");
            excelRow.createCell(8).setCellValue(fr.getRemarks());

//                        if(fr.getPlotNo()!=null && fr.getPlotNo().length()>0){
//                           cAddress=cAddress+"FLAT No - "+fr.getPlotNo(); 
//                           check_plot=1;
//                        }else{
//                            //cAddress="";
//                        }
//                        if(fr.getHoueNo()!=null && fr.getHoueNo().length()>0){
//                            if(check_plot==1){
//                               cAddress=cAddress+" , House No - "+fr.getHoueNo(); 
//                            }else{
//                                cAddress=cAddress+"House No - "+fr.getHoueNo(); 
//                            }
//                           check_house=1; 
//                              
//                        }
//                        if(fr.getBuildingName()!=null && fr.getBuildingName().length()>0){
//                            if(check_plot==1 && check_house==1 ){
//                               cAddress=cAddress+" , "+fr.getBuildingName(); 
//                            }else if(check_plot==1 && check_house==0){
//                               cAddress=cAddress+" , "+fr.getBuildingName(); 
//                            }else if(check_plot==0 && check_house==1){
//                                cAddress=cAddress+" , "+fr.getBuildingName(); 
//                            }else if(check_plot==0 && check_house==0){
//                                cAddress=cAddress+fr.getBuildingName(); 
//                            }
//                           check_buildingName=1;   
//                            
//                        }
//                        if(fr.getRoadName()!=null && fr.getRoadName().length()>0){
//                            if(check_buildingName==1 || check_house==1 || check_plot==1){
//                               cAddress=cAddress+" , "+fr.getRoadName(); 
//                            }else{
//                                 cAddress=cAddress+fr.getRoadName();
//                            }
//                           check_roadName=1;   
//                            
//                        }
//                        if(fr.getSubLocality()!=null && fr.getSubLocality().length()>0){
//                            if(check_buildingName==1 || check_house==1 || check_plot==1 || check_roadName==1){
//                              cAddress=cAddress+" , "+fr.getSubLocality();  
//                            }else{
//                                cAddress=cAddress+fr.getSubLocality();
//                            }
//                           check_subloc=1;  
//                            
//                        }
//                        if(fr.getLocName()!=null && fr.getLocName().length()>0){
//                            if(check_buildingName==1 || check_house==1 || check_plot==1 || check_roadName==1 || check_subloc==1){
//                               cAddress=cAddress+" , "+fr.getLocName(); 
//                            }else{
//                                cAddress=cAddress+fr.getLocName();
//                            }
//                            
//                           check_locName=1; 
//                        }
//                        if(fr.getLandMark()!=null && fr.getLandMark().length()>0){
//                            if(check_buildingName==1 || check_house==1 || check_plot==1 || check_roadName==1 || check_subloc==1 || check_locName==1){
//                              cAddress=cAddress+" , "+fr.getLandMark();   
//                            }else{
//                                cAddress=cAddress+fr.getLandMark(); 
//                            }
//                               
//                              
//                        }
            //excelRow.createCell(5).setCellValue(cAddress+", Silvassa, Pincode - 396230");
//                        excelRow.createCell(1).setCellValue(rt.getPlotNo());
//                        excelRow.createCell(2).setCellValue(fr.getPlotNo());
//                        
//                        excelRow.createCell(3).setCellValue(rt.getHoueNo());
//                        excelRow.createCell(4).setCellValue(fr.getHoueNo());
//                        
//                        excelRow.createCell(5).setCellValue(rt.getBuildingName());
//                        excelRow.createCell(6).setCellValue(fr.getBuildingName());
//                        
//                        excelRow.createCell(7).setCellValue(rt.getRoadName());
//                        excelRow.createCell(8).setCellValue(fr.getRoadName());
//                        
//                        excelRow.createCell(9).setCellValue(rt.getSubLocality());
//                        excelRow.createCell(10).setCellValue(fr.getSubLocality());
//                        
//                        excelRow.createCell(11).setCellValue(rt.getLocName());
//                        excelRow.createCell(12).setCellValue(fr.getLocName());
//                        
//                        excelRow.createCell(13).setCellValue(rt.getLandMark());
//                        excelRow.createCell(14).setCellValue(fr.getLandMark());
//                        
//                        excelRow.createCell(15).setCellValue(fr.getWardNo());
//                        excelRow.createCell(16).setCellValue(fr.getApplicationNo());
//                        excelRow.createCell(17).setCellValue(fr.getNoticeDate());
//                        excelRow.createCell(18).setCellValue(fr.getApplicantName());
//                        
            cAddress = "";
            check_plot = 0;
            check_house = 0;
            check_buildingName = 0;
            check_roadName = 0;
            check_subloc = 0;
            check_locName = 0;
            check_landMarks = 0;

        }
    }

    public void setExcelRowsArrear(HSSFSheet excelSheet, List<CorrectionFormSaveBean> correctionList, Map<String, CorrectionFormReport> mapList) {
        int record = 1;
        int cellCtr = 0;
        for (CorrectionFormSaveBean fr : correctionList) {
            cellCtr++;
            CorrectionFormReport rt = mapList.get(fr.getUniqueId());

            if (rt == null) {
                continue;
            }
            HSSFRow excelRow = excelSheet.createRow(record++);

            excelRow.createCell(0).setCellValue(cellCtr);
            excelRow.createCell(1).setCellValue(fr.getUniqueId());
            excelRow.createCell(2).setCellValue(fr.getApplicationNo());
            excelRow.createCell(3).setCellValue(fr.getWardNo());
            excelRow.createCell(4).setCellValue(rt.getOwnerName());
            //excelRow.createCell(5).setCellValue(rt.getSmcHoldingNo());
            //excelRow.createCell(6).setCellValue(fr.getSmcHoldingNo());

            excelRow.createCell(5).setCellValue(rt.getArrearAmount());
            excelRow.createCell(6).setCellValue(fr.getArrearAmount());
            excelRow.createCell(7).setCellValue("Verified");
            excelRow.createCell(8).setCellValue(fr.getRemarks());

//                        excelRow.createCell(1).setCellValue(rt.getElectricServiceNo());
//                        excelRow.createCell(2).setCellValue(fr.getElectricServiceNo());
//                        
//                        excelRow.createCell(3).setCellValue(rt.getSurveyNo());
//                        excelRow.createCell(4).setCellValue(fr.getSurveyNo());
//                        
//                        excelRow.createCell(5).setCellValue(rt.getPlotSmc());
//                        excelRow.createCell(6).setCellValue(fr.getPlotSmc());
//                        
//                        excelRow.createCell(11).setCellValue(fr.getWardNo());
//                        excelRow.createCell(12).setCellValue(fr.getApplicationNo());
//                        excelRow.createCell(13).setCellValue(fr.getNoticeDate());
//                        excelRow.createCell(14).setCellValue(fr.getApplicantName());
//                        
        }
    }

    public void setExcelRowsSmcHouse(HSSFSheet excelSheet, List<CorrectionFormSaveBean> correctionList, Map<String, CorrectionFormReport> mapList) {
        int record = 1;
        int cellCtr = 0;
        for (CorrectionFormSaveBean fr : correctionList) {
            cellCtr++;
            CorrectionFormReport rt = mapList.get(fr.getUniqueId());

            if (rt == null) {
                continue;
            }
            HSSFRow excelRow = excelSheet.createRow(record++);

            excelRow.createCell(0).setCellValue(cellCtr);
            excelRow.createCell(1).setCellValue(fr.getUniqueId());
            excelRow.createCell(2).setCellValue(fr.getApplicationNo());
            excelRow.createCell(3).setCellValue(fr.getWardNo());
            excelRow.createCell(4).setCellValue(rt.getOwnerName());
            excelRow.createCell(5).setCellValue(rt.getSmcHoldingNo());
            excelRow.createCell(6).setCellValue(fr.getSmcHoldingNo());
            excelRow.createCell(7).setCellValue("Verified");
            excelRow.createCell(8).setCellValue(fr.getRemarks());
            //excelRow.createCell(6).setCellValue(rt.getArrearAmount());
            //excelRow.createCell(7).setCellValue(fr.getArrearAmount());

//                        excelRow.createCell(1).setCellValue(rt.getElectricServiceNo());
//                        excelRow.createCell(2).setCellValue(fr.getElectricServiceNo());
//                        
//                        excelRow.createCell(3).setCellValue(rt.getSurveyNo());
//                        excelRow.createCell(4).setCellValue(fr.getSurveyNo());
//                        
//                        excelRow.createCell(5).setCellValue(rt.getPlotSmc());
//                        excelRow.createCell(6).setCellValue(fr.getPlotSmc());
//                        
//                        excelRow.createCell(11).setCellValue(fr.getWardNo());
//                        excelRow.createCell(12).setCellValue(fr.getApplicationNo());
//                        excelRow.createCell(13).setCellValue(fr.getNoticeDate());
//                        excelRow.createCell(14).setCellValue(fr.getApplicantName());
//                        
        }
    }

    public void setExcelRowsOwnerCombine(HSSFSheet excelSheet, List<CorrectionFormSaveBean> correctionList, Map<String, CorrectionFormReport> mapList) {

        logger.info("setExcelRowsOwnerCombine start size " + correctionList.size() + " " + mapList.size());
        int record = 1;
        int cellCtr = 0;
        String showMsg = "";
        try {
            CellStyle styleColor = workbookforColor.createCellStyle();
            for (CorrectionFormSaveBean fr : correctionList) {
                cellCtr++;
                CorrectionFormReport rt = mapList.get(fr.getUniqueId());
                if (rt == null) {
                    logger.info("setExcelRowsOwnerCombine continue");
                    continue;
                }

                styleColor.setFillForegroundColor(IndexedColors.LIGHT_YELLOW.getIndex());
                styleColor.setFillPattern(CellStyle.SOLID_FOREGROUND);
                styleColor.setWrapText(true);
                HSSFRow excelRow = excelSheet.createRow(record++);
                excelRow.createCell(0).setCellValue(cellCtr);
                excelRow.createCell(1).setCellValue(fr.getUniqueId());
                excelRow.createCell(2).setCellValue(fr.getApplicationNo());
                excelRow.createCell(3).setCellValue(fr.getWardNo());
                excelRow.createCell(4).setCellValue(rt.getOwnerName());
                excelRow.createCell(5).setCellValue(fr.getOwnerName());
//            
                excelRow.createCell(6).setCellValue(rt.getOwnerFather());
                excelRow.createCell(7).setCellValue(fr.getOwnerFather());
                excelRow.createCell(8).setCellValue(rt.getSpouseName());
                excelRow.createCell(9).setCellValue(fr.getSpouseName());

                excelRow.createCell(10).setCellValue(rt.getOwnerAadharNo());
                excelRow.createCell(11).setCellValue(fr.getOwnerAadharNo());

                excelRow.createCell(12).setCellValue(rt.getAddress());
                excelRow.createCell(13).setCellValue(fr.getAddress());

                excelRow.createCell(14).setCellValue(rt.getSmcHoldingNo());
                excelRow.createCell(15).setCellValue(fr.getSmcHoldingNo());

                excelRow.createCell(16).setCellValue(rt.getArrearAmount());
                excelRow.createCell(17).setCellValue(fr.getArrearAmount());

                if (fr.getStatus().equalsIgnoreCase("read")) {
                    excelRow.createCell(18).setCellValue("Inbox");
                } else if (fr.getStatus().equalsIgnoreCase("fieldverify")) {
                    excelRow.createCell(18).setCellValue("Field Verification");
                } else if (fr.getStatus().equalsIgnoreCase("reject")) {
                    excelRow.createCell(18).setCellValue("Rejected");
                } else if (fr.getStatus().equalsIgnoreCase("approve")) {
                    excelRow.createCell(18).setCellValue("Verified");
                } else {
                    excelRow.createCell(18).setCellValue(fr.getStatus());
                }
                //excelRow.createCell(33).setCellValue(fr.getStatus()); 
                excelRow.createCell(19).setCellValue(fr.getRemarks());
                //excelRow.createCell(12).setCellValue(showMsg);

//                        excelRow.createCell(9).setCellValue(rt.getOwnerContact());
//                        excelRow.createCell(10).setCellValue(fr.getOwnerContact());
//                        
//			excelRow.createCell(11).setCellValue(rt.getOwnerEmail());
//                        excelRow.createCell(12).setCellValue(fr.getOwnerEmail());
//                        
//                        excelRow.createCell(13).setCellValue(rt.getOwnerAadharNo());
//                        excelRow.createCell(14).setCellValue(fr.getOwnerAadharNo());
//                        
//                        excelRow.createCell(15).setCellValue(rt.getPropertyOwnerAddress());
//                        excelRow.createCell(16).setCellValue(fr.getPropertyOwnerAddress());
//                        
//                        excelRow.createCell(17).setCellValue(rt.getOccupierName());
//                        excelRow.createCell(18).setCellValue(fr.getOccupierName());
//                        
//                        if(fr.getOccupierSex().equals("-1")){
//                             excelRow.createCell(19).setCellValue(rt.getOccupierSex());
//                            excelRow.createCell(20).setCellValue("");
//                        }else{
//                            excelRow.createCell(19).setCellValue(rt.getOccupierSex()); 
//                            excelRow.createCell(20).setCellValue(fr.getOccupierSex()); 
//                        }
//                        
//                        
//                        excelRow.createCell(21).setCellValue(rt.getOccupierFather());
//                        excelRow.createCell(22).setCellValue(fr.getOccupierFather());
//                        
//                        excelRow.createCell(23).setCellValue(rt.getOccupierContact());
//                        excelRow.createCell(24).setCellValue(fr.getOccupierContact());
//                        
//                        excelRow.createCell(25).setCellValue(rt.getOccupierEmail());
//                        excelRow.createCell(26).setCellValue(fr.getOccupierEmail());
//                        
//                        excelRow.createCell(27).setCellValue(rt.getOccupierAadharNo());
//                        excelRow.createCell(28).setCellValue(fr.getOccupierAadharNo());
//                        
//                        
//                        excelRow.createCell(31).setCellValue(fr.getNoticeDate());
//                        excelRow.createCell(32).setCellValue(fr.getApplicantName());
//                        
                showMsg = "";

            }
        } catch (Exception e) {
            e.printStackTrace();
            logger.info(e.getMessage());
        } finally {
            logger.info("report generated");
        }
        logger.info("setExcelRowsOwnerCombine end size " + correctionList.size() + " " + mapList.size());
    }

    public void setExcelRowsCombine(HSSFSheet excelSheet, List<CorrectionFormSaveBean> correctionList, Map<String, CorrectionFormReport> mapList, Map<String, ArrayList<CorrectionFormFloorBean>> mapCorrectionFloorData, Map<String, ArrayList<CorrectionFormFloorReport>> mapFloorSurveyData) {
        logger.info("setExcelRowsCombine start size " + correctionList.size() + " " + mapList.size());
        logger.info("setExcelRowsCombine start size " + mapCorrectionFloorData.size() + " " + mapFloorSurveyData.size());
        int record = 1;
        int cellCtr = 0;
        String showMsg = "";

        try {
            CellStyle styleColor = workbookforColor.createCellStyle();
            for (CorrectionFormSaveBean fr : correctionList) {
                cellCtr++;
                CorrectionFormReport rt = mapList.get(fr.getUniqueId());
                if (rt == null) {
                    logger.info("setExcelRowsCombine continue ");
                    continue;
                }

                
                styleColor.setFillForegroundColor(IndexedColors.LIGHT_YELLOW.getIndex());
                styleColor.setFillPattern(CellStyle.SOLID_FOREGROUND);
                styleColor.setWrapText(true);
                HSSFRow excelRow = excelSheet.createRow(record++);
                excelRow.createCell(0).setCellValue(cellCtr);
                excelRow.createCell(1).setCellValue(fr.getUniqueId());
                excelRow.createCell(2).setCellValue(fr.getApplicationNo());
                excelRow.createCell(3).setCellValue(fr.getWardNo());
                excelRow.createCell(4).setCellValue(rt.getOwnerName());
                excelRow.createCell(5).setCellValue(fr.getOwnerName());
//            
                excelRow.createCell(6).setCellValue(rt.getOwnerFather());
                excelRow.createCell(7).setCellValue(fr.getOwnerFather());
                excelRow.createCell(8).setCellValue(rt.getSpouseName());
                excelRow.createCell(9).setCellValue(fr.getSpouseName());

                excelRow.createCell(10).setCellValue(rt.getOwnerAadharNo());
                excelRow.createCell(11).setCellValue(fr.getOwnerAadharNo());

                excelRow.createCell(12).setCellValue(rt.getAddress());
                excelRow.createCell(13).setCellValue(fr.getAddress());

                excelRow.createCell(14).setCellValue(rt.getSmcHoldingNo());
                excelRow.createCell(15).setCellValue(fr.getSmcHoldingNo());

                excelRow.createCell(16).setCellValue(rt.getArrearAmount());
                excelRow.createCell(17).setCellValue(fr.getArrearAmount());

                if (mapCorrectionFloorData.containsKey(fr.getApplicationNo())) {
                    ArrayList<CorrectionFormFloorReport> reportSurvey = null;
                    ArrayList<CorrectionFormFloorBean> reportCorrectionForm = null;

                    // System.out.println("fr.getUniqueId()"+fr.getUniqueId());
                    if (mapFloorSurveyData.containsKey(fr.getApplicationNo())) {
                        reportSurvey = (ArrayList<CorrectionFormFloorReport>) mapFloorSurveyData.get(fr.getApplicationNo());
                    }

                    if (mapCorrectionFloorData.containsKey(fr.getApplicationNo())) {
                        reportCorrectionForm = (ArrayList<CorrectionFormFloorBean>) mapCorrectionFloorData.get(fr.getApplicationNo());
                    }

                    if (reportSurvey != null) {
                        logger.info("setExcelRowsCombine reportSurvey continue ");
                        Iterator itrSurvey = reportSurvey.iterator();
                        while (itrSurvey.hasNext()) {
                            CorrectionFormFloorReport rt_survey = (CorrectionFormFloorReport) itrSurvey.next();
                            //System.out.println("rt_cform.getFloorType() "+rt_survey.getFloorType()+" "+fr.getUniqueId());
                            excelRow.createCell(18).setCellValue(rt_survey.getFloorType());
                            excelRow.createCell(20).setCellValue(rt_survey.getCarpetArea());
                            excelRow.createCell(22).setCellValue(rt_survey.getPropertyUse());
                            excelRow.createCell(24).setCellValue(rt_survey.getPropertySubType());
                            excelRow.createCell(26).setCellValue(rt_survey.getConstructionType());
                            excelRow.createCell(28).setCellValue(rt_survey.getSelfRent());
                            excelRow.createCell(30).setCellValue(rt_survey.getRentedValue());

                        }
                    }

                    if (reportCorrectionForm != null) {
                        logger.info("setExcelRowsCombine reportCorrectionForm continue ");
                        Iterator itrcForm = reportCorrectionForm.iterator();
                        while (itrcForm.hasNext()) {
                            CorrectionFormFloorBean rt_cform = (CorrectionFormFloorBean) itrcForm.next();
                            //System.out.println("rt_cform.getFloorType() "+rt_cform.getFloorType()+" "+fr.getUniqueId());
                            excelRow.createCell(19).setCellValue(rt_cform.getFloorType());
                            excelRow.createCell(21).setCellValue(rt_cform.getCarpetArea());
                            excelRow.createCell(23).setCellValue(rt_cform.getPropertyUse());
                            excelRow.createCell(25).setCellValue(rt_cform.getPropertySubType());
                            excelRow.createCell(27).setCellValue(rt_cform.getConstructionType());
                            excelRow.createCell(29).setCellValue(rt_cform.getSelfRent());
                            excelRow.createCell(31).setCellValue(rt_cform.getRentedValue());
                            excelRow.createCell(32).setCellValue(rt_cform.getEditData());

                        }

                    }
                }

                if (fr.getStatus().equalsIgnoreCase("read")) {
                    excelRow.createCell(33).setCellValue("Inbox");
                } else if (fr.getStatus().equalsIgnoreCase("fieldverify")) {
                    excelRow.createCell(33).setCellValue("Field Verification");
                } else if (fr.getStatus().equalsIgnoreCase("reject")) {
                    excelRow.createCell(33).setCellValue("Rejected");
                } else if (fr.getStatus().equalsIgnoreCase("approve")) {
                    excelRow.createCell(33).setCellValue("Verified");
                } else {
                    excelRow.createCell(33).setCellValue(fr.getStatus());
                }
                //excelRow.createCell(33).setCellValue(fr.getStatus()); 
                excelRow.createCell(34).setCellValue(fr.getRemarks());
                //excelRow.createCell(12).setCellValue(showMsg);

//                        excelRow.createCell(9).setCellValue(rt.getOwnerContact());
//                        excelRow.createCell(10).setCellValue(fr.getOwnerContact());
//                        
//			excelRow.createCell(11).setCellValue(rt.getOwnerEmail());
//                        excelRow.createCell(12).setCellValue(fr.getOwnerEmail());
//                        
//                        excelRow.createCell(13).setCellValue(rt.getOwnerAadharNo());
//                        excelRow.createCell(14).setCellValue(fr.getOwnerAadharNo());
//                        
//                        excelRow.createCell(15).setCellValue(rt.getPropertyOwnerAddress());
//                        excelRow.createCell(16).setCellValue(fr.getPropertyOwnerAddress());
//                        
//                        excelRow.createCell(17).setCellValue(rt.getOccupierName());
//                        excelRow.createCell(18).setCellValue(fr.getOccupierName());
//                        
//                        if(fr.getOccupierSex().equals("-1")){
//                             excelRow.createCell(19).setCellValue(rt.getOccupierSex());
//                            excelRow.createCell(20).setCellValue("");
//                        }else{
//                            excelRow.createCell(19).setCellValue(rt.getOccupierSex()); 
//                            excelRow.createCell(20).setCellValue(fr.getOccupierSex()); 
//                        }
//                        
//                        
//                        excelRow.createCell(21).setCellValue(rt.getOccupierFather());
//                        excelRow.createCell(22).setCellValue(fr.getOccupierFather());
//                        
//                        excelRow.createCell(23).setCellValue(rt.getOccupierContact());
//                        excelRow.createCell(24).setCellValue(fr.getOccupierContact());
//                        
//                        excelRow.createCell(25).setCellValue(rt.getOccupierEmail());
//                        excelRow.createCell(26).setCellValue(fr.getOccupierEmail());
//                        
//                        excelRow.createCell(27).setCellValue(rt.getOccupierAadharNo());
//                        excelRow.createCell(28).setCellValue(fr.getOccupierAadharNo());
//                        
//                        
//                        excelRow.createCell(31).setCellValue(fr.getNoticeDate());
//                        excelRow.createCell(32).setCellValue(fr.getApplicantName());
//                        
                showMsg = "";

            }
        } catch (Exception e) {
            e.printStackTrace();
            logger.info(e.getMessage());
        } finally {
            logger.info("report floor generated");
        }
        logger.info("setExcelRowsCombine");
    }

    public void setExcelRowsFloor(HSSFSheet excelSheet, List<CorrectionFormFloorBean> correctionList, Map<String, ArrayList<CorrectionFormFloorReport>> mapList, CellStyle newCellStyle) {
        int record = 1;
        int cellCtr = 0;
        String fl = "";
        CorrectionFormFloorReport newData = null;
        boolean fType = false;
        boolean coveredArea = false;

        for (CorrectionFormFloorBean oldData : correctionList) {

            ArrayList<CorrectionFormFloorReport> ar_fr = mapList.get(oldData.getUniqueId());
            if (ar_fr == null) {
                continue;
            }
            cellCtr++;
            Iterator rr = ar_fr.iterator();
            if (rr.hasNext()) {
                newData = (CorrectionFormFloorReport) rr.next();
            }

            CellStyle styleColor = workbookforColor.createCellStyle();
            styleColor.setFillForegroundColor(IndexedColors.LIGHT_YELLOW.getIndex());
            styleColor.setFillPattern(CellStyle.SOLID_FOREGROUND);
            styleColor.setWrapText(true);

            HSSFRow excelRow = excelSheet.createRow(record++);
            excelRow.setRowStyle(newCellStyle);
            excelRow.createCell(0).setCellValue(cellCtr);
            excelRow.createCell(1).setCellValue(oldData.getUniqueId());
            excelRow.createCell(2).setCellValue(oldData.getApplication_no());
            excelRow.createCell(3).setCellValue(oldData.getWardNo());
            excelRow.createCell(4).setCellValue(newData.getOwner());

            Cell cell = excelRow.createCell(5);
            //cell.setCellStyle(styleColor);

            Cell cell1 = excelRow.createCell(6);
            //newCellStyle.setFillPattern(XSSFCellStyle.FINE_DOTS );
            //newCellStyle.setFillForegroundColor(IndexedColors.BLUE.getIndex());
            //newCellStyle.setFillBackgroundColor(IndexedColors.RED.getIndex());
//            cell1.setCellStyle(styleColor);
            cell.setCellStyle(newCellStyle);
            cell.setCellValue(newData.getPropertyFloorId());
            cell1.setCellValue(oldData.getPropertyFloorId());
            cell1.setCellStyle(newCellStyle);

                        //excelRow.createCell(1).setCellValue(newData.getPropertyFloorId());
            //excelRow.createCell(2).setCellValue(oldData.getPropertyFloorId());
            // Color diff start from here...
            Cell cell6 = excelRow.createCell(7);
            Cell cell7 = excelRow.createCell(8);

            if (newData.getFloorType() != null && newData.getFloorType().equals(oldData.getFloorType())) {
                cell6.setCellStyle(newCellStyle);
                cell7.setCellStyle(newCellStyle);
            } else {
                cell6.setCellStyle(styleColor);
                cell7.setCellStyle(styleColor);
            }

            cell6.setCellValue(newData.getFloorType());
            cell7.setCellValue(oldData.getFloorType());

            Cell cell8 = excelRow.createCell(9);
            Cell cell9 = excelRow.createCell(10);

            if (newData.getCarpetArea() != null && newData.getCarpetArea().equals(oldData.getCarpetArea())) {
                cell8.setCellStyle(newCellStyle);
                cell9.setCellStyle(newCellStyle);
            } else {
                cell8.setCellStyle(styleColor);
                cell9.setCellStyle(styleColor);
            }
            cell8.setCellValue(newData.getCarpetArea());
            cell9.setCellValue(oldData.getCarpetArea());

            Cell cell10 = excelRow.createCell(11);
            Cell cell11 = excelRow.createCell(12);
            if (newData.getPropertyUse() != null && newData.getPropertyUse().equalsIgnoreCase(oldData.getPropertyUse())) {
                cell10.setCellStyle(newCellStyle);
                cell11.setCellStyle(newCellStyle);
            } else {
                cell10.setCellStyle(styleColor);
                cell11.setCellStyle(styleColor);
            }
            cell10.setCellValue(newData.getPropertyUse());
            cell11.setCellValue(oldData.getPropertyUse());

            Cell cell12 = excelRow.createCell(13);
            Cell cell13 = excelRow.createCell(14);

            if (newData.getPropertySubType() != null && newData.getPropertySubType().equals(oldData.getPropertySubType())) {
                cell12.setCellStyle(newCellStyle);
                cell13.setCellStyle(newCellStyle);
            } else {
                cell12.setCellStyle(styleColor);
                cell13.setCellStyle(styleColor);
            }
            //cell10.setCellStyle(newCellStyle);

//            cell11.setCellStyle(styleColor);
            cell12.setCellValue(newData.getPropertySubType());
            cell13.setCellValue(oldData.getPropertySubType());

            //cell12.setCellStyle(newCellStyle);
//            cell13.setCellStyle(styleColor);
            Cell cell14 = excelRow.createCell(15);
            Cell cell15 = excelRow.createCell(16);
            if (newData.getConstructionType() != null && newData.getConstructionType().equals(oldData.getConstructionType())) {
                cell14.setCellStyle(newCellStyle);
                cell15.setCellStyle(newCellStyle);
            } else {
                cell14.setCellStyle(styleColor);
                cell15.setCellStyle(styleColor);
            }
            cell14.setCellValue(newData.getConstructionType());
            cell15.setCellValue(oldData.getConstructionType());
            //cell14.setCellStyle(newCellStyle);

//            cell15.setCellStyle(styleColor);
            Cell cell16 = excelRow.createCell(17);
            Cell cell17 = excelRow.createCell(18);
            if (newData.getSelfRent() != null && newData.getSelfRent().equals(oldData.getSelfRent())) {
                cell16.setCellStyle(newCellStyle);
                cell17.setCellStyle(newCellStyle);
            } else {
                cell16.setCellStyle(styleColor);
                cell17.setCellStyle(styleColor);
            }
            cell16.setCellValue(newData.getSelfRent());
            cell17.setCellValue(oldData.getSelfRent());
            //cell16.setCellStyle(newCellStyle);

//            cell17.setCellStyle(styleColor);
            Cell cell18 = excelRow.createCell(19);
            Cell cell19 = excelRow.createCell(20);
            if (newData.getRentedValue() != null && newData.getRentedValue().equals(oldData.getRentedValue())) {
                cell18.setCellStyle(newCellStyle);
                cell19.setCellStyle(newCellStyle);
            } else {
                cell18.setCellStyle(styleColor);
                cell19.setCellStyle(styleColor);
            }
            cell18.setCellValue(newData.getRentedValue());
            cell19.setCellValue(oldData.getRentedValue());

            //cell18.setCellStyle(newCellStyle);
//            cell19.setCellStyle(styleColor);
            //excelRow.createCell(17).setCellValue(oldData.getWardNo());
            //excelRow.createCell(18).setCellValue(oldData.getApplication_no());
            //excelRow.createCell(19).setCellValue(oldData.getNoticeDate());
            //excelRow.createCell(20).setCellValue(oldData.getApplicantName());
            Cell cell20 = excelRow.createCell(21);
            Cell cell21 = excelRow.createCell(22);
//            cell20.setCellStyle(styleColor);
//            cell21.setCellStyle(styleColor);
            cell20.setCellValue(oldData.getEditData());
            cell20.setCellStyle(newCellStyle);

            excelRow.createCell(22).setCellValue("Verified");
            excelRow.createCell(23).setCellValue(oldData.getRemarks());
                        //cell21.setCellValue(oldData.getDeleteData());

                        //cell20.setCellValue(oldData.getEditData());
            //excelRow.createCell(23).setCellValue(oldData.getPermissionData());
        }

//                for (CorrectionFormFloorBean fr : correctionList) {
//			ArrayList<CorrectionFormFloorReport> rttt=mapList.get(fr.getUniqueId());
//                        System.out.println("correctionList inside for "+rttt.size());
//                        Iterator rr=rttt.iterator();
//                        if(rr.hasNext()){
//                        newData=(CorrectionFormFloorReport)rr.next();
//                        //fl=fl+newData.getFloorType()+"\n";
//                        System.out.println("rttt  "+fl+" " +fr.getUniqueId());
//                        }
//                        //newData=mapList.get(fr.getUniqueId());
//                        
//                       HSSFRow excelRow = excelSheet.createRow(record++);
//			
//                        excelRow.createCell(0).setCellValue(fr.getUniqueId());
//                        excelRow.createCell(1).setCellValue(newData.getFloorType());
//                        
//                        excelRow.createCell(2).setCellValue(fr.getFloorType());
//                        
//                        excelRow.createCell(3).setCellValue(newData.getCarpetArea());
//                        excelRow.createCell(4).setCellValue(fr.getCarpetArea());
//                        
//                        excelRow.createCell(5).setCellValue(newData.getPropertyUse());
//                        excelRow.createCell(6).setCellValue(fr.getPropertyUse());
//                        
//                        excelRow.createCell(7).setCellValue(newData.getPropertySubType());
//                        excelRow.createCell(8).setCellValue(fr.getPropertySubType());
//                        
//                        excelRow.createCell(9).setCellValue(newData.getConstructionType());
//                        excelRow.createCell(10).setCellValue(fr.getConstructionType());
//                        
//                        excelRow.createCell(11).setCellValue(newData.getSelfRent());
//                        excelRow.createCell(12).setCellValue(fr.getSelfRent());
//                        
//                        excelRow.createCell(13).setCellValue(newData.getRentedValue());
//                        excelRow.createCell(14).setCellValue(fr.getRentedValue());
//                        
//                        excelRow.createCell(15).setCellValue("");
//                        excelRow.createCell(16).setCellValue("");
//                        excelRow.createCell(17).setCellValue("");
//                        excelRow.createCell(18).setCellValue("");
//                     fl="";        
//                  }
    }

    public void setExcelRowsCombineFloor(HSSFSheet excelSheet, List<CorrectionFormFloorBean> correctionList, Map<String, ArrayList<CorrectionFormFloorReport>> mapList, CellStyle newCellStyle) {
        int record = 1;
        int cellCtr = 0;
        String fl = "";
        CorrectionFormFloorReport newData = null;
        boolean fType = false;
        boolean coveredArea = false;

        for (CorrectionFormFloorBean oldData : correctionList) {

            ArrayList<CorrectionFormFloorReport> ar_fr = mapList.get(oldData.getUniqueId());
            if (ar_fr == null) {
                continue;
            }
            cellCtr++;
            Iterator rr = ar_fr.iterator();
            if (rr.hasNext()) {
                newData = (CorrectionFormFloorReport) rr.next();
            }

            CellStyle styleColor = workbookforColor.createCellStyle();
            styleColor.setFillForegroundColor(IndexedColors.LIGHT_YELLOW.getIndex());
            styleColor.setFillPattern(CellStyle.SOLID_FOREGROUND);
            styleColor.setWrapText(true);

            HSSFRow excelRow = excelSheet.createRow(record++);
            excelRow.setRowStyle(newCellStyle);
            excelRow.createCell(0).setCellValue(cellCtr);
            excelRow.createCell(1).setCellValue(oldData.getUniqueId());
            excelRow.createCell(2).setCellValue(oldData.getApplication_no());
            excelRow.createCell(3).setCellValue(oldData.getWardNo());
            excelRow.createCell(4).setCellValue(newData.getOwner());

            Cell cell = excelRow.createCell(5);
            //cell.setCellStyle(styleColor);

            Cell cell1 = excelRow.createCell(6);
            //newCellStyle.setFillPattern(XSSFCellStyle.FINE_DOTS );
            //newCellStyle.setFillForegroundColor(IndexedColors.BLUE.getIndex());
            //newCellStyle.setFillBackgroundColor(IndexedColors.RED.getIndex());
//            cell1.setCellStyle(styleColor);
            cell.setCellStyle(newCellStyle);
            cell.setCellValue(newData.getPropertyFloorId());
            cell1.setCellValue(oldData.getPropertyFloorId());
            cell1.setCellStyle(newCellStyle);

                        //excelRow.createCell(1).setCellValue(newData.getPropertyFloorId());
            //excelRow.createCell(2).setCellValue(oldData.getPropertyFloorId());
            // Color diff start from here...
            Cell cell6 = excelRow.createCell(7);
            Cell cell7 = excelRow.createCell(8);

            if (newData.getFloorType() != null && newData.getFloorType().equals(oldData.getFloorType())) {
                cell6.setCellStyle(newCellStyle);
                cell7.setCellStyle(newCellStyle);
            } else {
                cell6.setCellStyle(styleColor);
                cell7.setCellStyle(styleColor);
            }

            cell6.setCellValue(newData.getFloorType());
            cell7.setCellValue(oldData.getFloorType());

            Cell cell8 = excelRow.createCell(9);
            Cell cell9 = excelRow.createCell(10);

            if (newData.getCarpetArea() != null && newData.getCarpetArea().equals(oldData.getCarpetArea())) {
                cell8.setCellStyle(newCellStyle);
                cell9.setCellStyle(newCellStyle);
            } else {
                cell8.setCellStyle(styleColor);
                cell9.setCellStyle(styleColor);
            }
            cell8.setCellValue(newData.getCarpetArea());
            cell9.setCellValue(oldData.getCarpetArea());

            Cell cell10 = excelRow.createCell(11);
            Cell cell11 = excelRow.createCell(12);
            if (newData.getPropertyUse() != null && newData.getPropertyUse().equalsIgnoreCase(oldData.getPropertyUse())) {
                cell10.setCellStyle(newCellStyle);
                cell11.setCellStyle(newCellStyle);
            } else {
                cell10.setCellStyle(styleColor);
                cell11.setCellStyle(styleColor);
            }
            cell10.setCellValue(newData.getPropertyUse());
            cell11.setCellValue(oldData.getPropertyUse());

            Cell cell12 = excelRow.createCell(13);
            Cell cell13 = excelRow.createCell(14);

            if (newData.getPropertySubType() != null && newData.getPropertySubType().equals(oldData.getPropertySubType())) {
                cell12.setCellStyle(newCellStyle);
                cell13.setCellStyle(newCellStyle);
            } else {
                cell12.setCellStyle(styleColor);
                cell13.setCellStyle(styleColor);
            }
            //cell10.setCellStyle(newCellStyle);

//            cell11.setCellStyle(styleColor);
            cell12.setCellValue(newData.getPropertySubType());
            cell13.setCellValue(oldData.getPropertySubType());

            //cell12.setCellStyle(newCellStyle);
//            cell13.setCellStyle(styleColor);
            Cell cell14 = excelRow.createCell(15);
            Cell cell15 = excelRow.createCell(16);
            if (newData.getConstructionType() != null && newData.getConstructionType().equals(oldData.getConstructionType())) {
                cell14.setCellStyle(newCellStyle);
                cell15.setCellStyle(newCellStyle);
            } else {
                cell14.setCellStyle(styleColor);
                cell15.setCellStyle(styleColor);
            }
            cell14.setCellValue(newData.getConstructionType());
            cell15.setCellValue(oldData.getConstructionType());
            //cell14.setCellStyle(newCellStyle);

//            cell15.setCellStyle(styleColor);
            Cell cell16 = excelRow.createCell(17);
            Cell cell17 = excelRow.createCell(18);
            if (newData.getSelfRent() != null && newData.getSelfRent().equals(oldData.getSelfRent())) {
                cell16.setCellStyle(newCellStyle);
                cell17.setCellStyle(newCellStyle);
            } else {
                cell16.setCellStyle(styleColor);
                cell17.setCellStyle(styleColor);
            }
            cell16.setCellValue(newData.getSelfRent());
            cell17.setCellValue(oldData.getSelfRent());
            //cell16.setCellStyle(newCellStyle);

//            cell17.setCellStyle(styleColor);
            Cell cell18 = excelRow.createCell(19);
            Cell cell19 = excelRow.createCell(20);
            if (newData.getRentedValue() != null && newData.getRentedValue().equals(oldData.getRentedValue())) {
                cell18.setCellStyle(newCellStyle);
                cell19.setCellStyle(newCellStyle);
            } else {
                cell18.setCellStyle(styleColor);
                cell19.setCellStyle(styleColor);
            }
            cell18.setCellValue(newData.getRentedValue());
            cell19.setCellValue(oldData.getRentedValue());

            //cell18.setCellStyle(newCellStyle);
//            cell19.setCellStyle(styleColor);
            //excelRow.createCell(17).setCellValue(oldData.getWardNo());
            //excelRow.createCell(18).setCellValue(oldData.getApplication_no());
            //excelRow.createCell(19).setCellValue(oldData.getNoticeDate());
            //excelRow.createCell(20).setCellValue(oldData.getApplicantName());
            Cell cell20 = excelRow.createCell(21);
            Cell cell21 = excelRow.createCell(22);
//            cell20.setCellStyle(styleColor);
//            cell21.setCellStyle(styleColor);
            cell20.setCellValue(oldData.getEditData());
            cell20.setCellStyle(newCellStyle);

            excelRow.createCell(22).setCellValue(oldData.getStatus());
            excelRow.createCell(23).setCellValue(oldData.getRemarks());
                        //cell21.setCellValue(oldData.getDeleteData());

                        //cell20.setCellValue(oldData.getEditData());
            //excelRow.createCell(23).setCellValue(oldData.getPermissionData());
        }

//                for (CorrectionFormFloorBean fr : correctionList) {
//			ArrayList<CorrectionFormFloorReport> rttt=mapList.get(fr.getUniqueId());
//                        System.out.println("correctionList inside for "+rttt.size());
//                        Iterator rr=rttt.iterator();
//                        if(rr.hasNext()){
//                        newData=(CorrectionFormFloorReport)rr.next();
//                        //fl=fl+newData.getFloorType()+"\n";
//                        System.out.println("rttt  "+fl+" " +fr.getUniqueId());
//                        }
//                        //newData=mapList.get(fr.getUniqueId());
//                        
//                       HSSFRow excelRow = excelSheet.createRow(record++);
//			
//                        excelRow.createCell(0).setCellValue(fr.getUniqueId());
//                        excelRow.createCell(1).setCellValue(newData.getFloorType());
//                        
//                        excelRow.createCell(2).setCellValue(fr.getFloorType());
//                        
//                        excelRow.createCell(3).setCellValue(newData.getCarpetArea());
//                        excelRow.createCell(4).setCellValue(fr.getCarpetArea());
//                        
//                        excelRow.createCell(5).setCellValue(newData.getPropertyUse());
//                        excelRow.createCell(6).setCellValue(fr.getPropertyUse());
//                        
//                        excelRow.createCell(7).setCellValue(newData.getPropertySubType());
//                        excelRow.createCell(8).setCellValue(fr.getPropertySubType());
//                        
//                        excelRow.createCell(9).setCellValue(newData.getConstructionType());
//                        excelRow.createCell(10).setCellValue(fr.getConstructionType());
//                        
//                        excelRow.createCell(11).setCellValue(newData.getSelfRent());
//                        excelRow.createCell(12).setCellValue(fr.getSelfRent());
//                        
//                        excelRow.createCell(13).setCellValue(newData.getRentedValue());
//                        excelRow.createCell(14).setCellValue(fr.getRentedValue());
//                        
//                        excelRow.createCell(15).setCellValue("");
//                        excelRow.createCell(16).setCellValue("");
//                        excelRow.createCell(17).setCellValue("");
//                        excelRow.createCell(18).setCellValue("");
//                     fl="";        
//                  }
    }
}
