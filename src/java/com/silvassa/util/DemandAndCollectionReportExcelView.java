/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.silvassa.util;
import com.silvassa.bean.PrivateNoticeBean;
import com.silvassa.model.CorrectionFormFloorBean;
import com.silvassa.model.CorrectionFormFloorReport;
import com.silvassa.model.CorrectionFormReport;
import com.silvassa.model.CorrectionFormSaveBean;
import com.silvassa.model.PaymentBean;
import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import org.apache.poi.ss.usermodel.FillPatternType;  
import org.apache.poi.ss.usermodel.IndexedColors;  

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.poi.hssf.usermodel.HSSFRow;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.CellStyle;
import org.springframework.web.servlet.view.document.AbstractExcelView;

/**
 *
 * @author CEINFO
 */
public class DemandAndCollectionReportExcelView extends AbstractExcelView {
    private Object XSSFCellStyle;
    HSSFWorkbook workbookforWrap;
   @Override
  
   protected void buildExcelDocument(Map model, HSSFWorkbook workbook,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {

		HSSFSheet excelSheet = workbook.createSheet("Deand Collection");
                //excelSheet.autoSizeColumn((short) (15));
                
                CellStyle newCellStyle = workbook.createCellStyle();
                workbookforWrap=workbook;
               //newCellStyle.setFillPattern(FillPatternType.BIG_SPOTS);  
                newCellStyle.setWrapText(true);
		//setExcelHeader(excelSheet);
//                ArrayList<CorrectionFormFloorReport> rttt=mapFloorData.get("S05023975000");
//        Iterator rr=rttt.iterator();
//        while(rr.hasNext()){
//          CorrectionFormFloorReport pp=(CorrectionFormFloorReport)rr.next();
//           System.out.println("rttt"+pp.getFloorType());
//        }
		
		
                List<PrivateNoticeBean> demandList = (List<PrivateNoticeBean>) model.get("listReport");
                List<PaymentBean> paymentList = (List<PaymentBean>) model.get("payment");
		//Map<String,CorrectionFormReport> mapList= (Map<String,CorrectionFormReport>) model.get("mapdata");
                //List<CorrectionFormFloorBean> listFloorReport = (List<CorrectionFormFloorBean>)model.get("listFloorReport");
                //Map<String,ArrayList<CorrectionFormFloorReport>> mapFloorData=(Map<String,ArrayList<CorrectionFormFloorReport>>)model.get("mapFloorData");
                
                //Map<String,ArrayList<CorrectionFormFloorReport>> mapFloorData = (Map<String,Arraylist<CorrectionFormFloorReport>>)model.get("mapFloorData");
                //System.out.println("mapFloorData "+mapFloorData);
                    setExcelHeaderOwner(excelSheet);
                    setExcelRowsOwner(excelSheet,demandList,paymentList);
                
               
                //setExcelRows(excelSheet,correctionList,mapList);
		
	}

	public void setExcelHeaderOwner(HSSFSheet excelSheet) {
		HSSFRow excelHeader = excelSheet.createRow(0);
                 //excelSheet.autoSizeColumn(15);
                //excelHeader.setRowStyle(newCellStyle);
		excelHeader.createCell(0).setCellValue("Sr. No.");
		excelHeader.createCell(1).setCellValue("SMC  House Property No");
                excelHeader.createCell(2).setCellValue("Permanent Property Id");
		
                excelHeader.createCell(3).setCellValue("Owner's Name\n" +"Father's / Husband's Name\n" +"Mobile No\n" +"E-Mail");
		excelHeader.createCell(4).setCellValue("Postal Address");
                
                excelHeader.createCell(5).setCellValue("Floor");
                excelHeader.createCell(6).setCellValue("Floor wise\n" +"covered /Built up area\n" +"(Sq. Ft.)");
		
                excelHeader.createCell(7).setCellValue("Use of the Property");
                excelHeader.createCell(8).setCellValue("Property  Category");
                
                excelHeader.createCell(9).setCellValue("Rent / Self");
                excelHeader.createCell(10).setCellValue("Actual\n" +"Annual Rent ");
                
                
                excelHeader.createCell(11).setCellValue("Location Class");
                excelHeader.createCell(12).setCellValue("Presumed Annual Rent\n per sq. feet per annum");
                
                excelHeader.createCell(13).setCellValue("Annual Rateable value (M*90%)");
                excelHeader.createCell(14).setCellValue("Property Tax Rate ");
                
                excelHeader.createCell(15).setCellValue("Annual Demand  Property Tax ( G*N*O ) ");
                excelHeader.createCell(16).setCellValue("Arrear\n" +"Property Tax as on \n" +"01-04-18");
                
                excelHeader.createCell(17).setCellValue("Interest according to Section 157");
                excelHeader.createCell(18).setCellValue("Total  Property Tax (P+Q+R)");
                
                excelHeader.createCell(19).setCellValue("Receipt No.");
                excelHeader.createCell(20).setCellValue("Receipt Date");
                
                excelHeader.createCell(21).setCellValue("Receipt Amount");
                excelHeader.createCell(22).setCellValue("Total Amount Deposited P. Tax\n" +"(from 01.04.18     to 31.03.19)  ");
                
                excelHeader.createCell(23).setCellValue("Payment received upto");
                excelHeader.createCell(24).setCellValue("Rebate");
                excelHeader.createCell(25).setCellValue("Penalty");
                
                excelHeader.createCell(26).setCellValue("Exemption Type / Amount");
                excelHeader.createCell(27).setCellValue("Balance\n" +"P. Tax\n" +"Interest\n" +"as on 31-03-19");
                
                excelHeader.createCell(28).setCellValue("Remarks");
                
	}
        
        
       
	
      public void setExcelRowsOwner(HSSFSheet excelSheet, List<PrivateNoticeBean> correctionList,List<PaymentBean> payment){
		int record = 1;
                int totalPay=0;
                DecimalFormat df = new DecimalFormat("#,###");
                
		for (PrivateNoticeBean fr : correctionList) {
			
                        
                        CellStyle styleWrap = workbookforWrap.createCellStyle();
                        //styleColor.setFillForegroundColor(IndexedColors.LIGHT_YELLOW.getIndex());
                        //styleColor.setFillPattern(CellStyle.SOLID_FOREGROUND);
                        styleWrap.setWrapText(true); 
                        //Cell cell3=excelRow.createCell(3);
                        //Cell cell4=excelRow.createCell(4);
                        
                        
                        //cell3.setCellStyle(newCellStyle);
                        //cell3.setCellValue(rt.getFloorType());
                        //cell4.setCellStyle(styleColor);
                        //cell4.setCellValue(br.getFloorType());
                        
                        
                        //excelSheet.autoSizeColumn(15);
                        HSSFRow excelRow = excelSheet.createRow(record++);
                        excelSheet.setColumnWidth(2,6000);
                        excelSheet.setColumnWidth(3,6000);
                        excelSheet.setColumnWidth(4,6000);
                        excelSheet.setColumnWidth(7,6000);
                        excelSheet.setColumnWidth(8,5000);
                        excelSheet.setColumnWidth(19,7000);
                        excelSheet.setColumnWidth(20,4000);
                        
                        Cell cell0=excelRow.createCell(0);
                        cell0.setCellStyle(styleWrap);
                        Cell cell1=excelRow.createCell(1);
                        cell1.setCellStyle(styleWrap);
                        Cell cell2=excelRow.createCell(2);
                        cell2.setCellStyle(styleWrap);
                        Cell cell3=excelRow.createCell(3);
                        cell3.setCellStyle(styleWrap);
                        Cell cell4=excelRow.createCell(4);
                        cell4.setCellStyle(styleWrap);
                        
			cell0.setCellValue(fr.getSlNo());
			cell1.setCellValue(fr.getOldMc());
                        cell2.setCellValue(fr.getUniqueId());
                        cell3.setCellValue(fr.getOwner_Father());
			if(fr.getContact()!=null && fr.getContact().length()>0){
                           cell3.setCellValue(fr.getOwner_Father()+"\n"+fr.getContact()); 
                        }
                        if(fr.getEmail()!=null && fr.getEmail().length()>0){
                            cell3.setCellValue(fr.getOwner_Father()+"\n"+fr.getEmail()); 
                        }
                        
                        cell4.setCellValue(fr.getAddress());
                        String flr[] = null;
                        String meter[] = null;
                        String cover_ar[] = null;
                        String buse_ar[] = null;
                        String category_ar[] = null;
                        String construction_ar[] = null;
                        String self_ar[] = null;
                        String location_ar[] = null;
                        String annualRent_ar[] = null;
                        String ratableValue_ar[] = null;
                        String tax_rate_ar[] = null;
                        String tax_ar[] = null;
                        String actualRent[]=null;
                        String anuualRatableValue[]=null;
                        int totalCoveredArea = 0;
                        int totalTax = 0;
                        String flName="";
                        String coverArea="";
                        String buildingUse="";
                        String category="";
                        String actualRent_val="";
                        String self_rent="";
                        String locationClass="";
                        String annualRent="";
                        String ratableValue="";
                        String anuualRatable="";
                        String h_tax="";
                        String tax_rate="";
                        
                        
                        

                        flr = fr.getFloorName();
                        meter = fr.getPf_electric_con_num();
                        cover_ar = fr.getBuiltupArea();
                        buse_ar = fr.getBuildingUse();
                        category_ar = fr.getPropCat();
                        construction_ar = fr.getConstructionType();
                        self_ar = fr.getFloorSelfRent();
                        location_ar = fr.getPropertyClass();
                        annualRent_ar = fr.getRentableValue();
                        ratableValue_ar = fr.getAnuualRatableValue();
                        tax_rate_ar = fr.getMultiplicatioFactor();
                        tax_ar = fr.getFloorWiseTax();
                        actualRent=fr.getActualRentValue();
                        anuualRatableValue=fr.getActualRatableValue();
                        
                        for (int i = 0; i < buse_ar.length; i++) {
                          totalCoveredArea = totalCoveredArea + (int) Double.parseDouble(cover_ar[i]);
                          totalTax = totalTax + (int) (Double.parseDouble(tax_ar[i]));
                          if(flr[i]!=null){
                             flName=flName+flr[i]+"\n"; 
                          }
                          if(cover_ar[i]!=null){
                            coverArea=coverArea+cover_ar[i]+"\n";  
                          }
                          if(buse_ar[i]!=null){
                            buildingUse=buildingUse+buse_ar[i]+"\n";  
                          }
                          if(category_ar[i]!=null){
                            category=category+category_ar[i]+"\n";  
                          }
                          if(self_ar[i]!=null){
                            self_rent=self_rent+self_ar[i]+"\n";  
                          }
                          if(actualRent[i]!=null){
                            actualRent_val=actualRent_val+actualRent[i]+"\n";  
                          }
                          if(location_ar[i]!=null){
                            locationClass=locationClass+location_ar[i]+"\n";  
                          }
                          if(annualRent_ar[i]!=null){
                            annualRent=annualRent+annualRent_ar[i]+"\n";  
                          }
                          if(ratableValue_ar[i]!=null){
                            ratableValue=ratableValue+ratableValue_ar[i]+"\n";  
                          }
                          if(anuualRatableValue[i]!=null){
                            anuualRatable=anuualRatable+anuualRatableValue[i]+"\n";  
                          }
                          if(tax_rate_ar[i]!=null){
                             tax_rate=tax_rate+tax_rate_ar[i]+"\n";
                          }
                          if(tax_ar[i]!=null){
                            h_tax=h_tax+tax_ar[i]+"\n";  
                          }
                          
                          
                          
                          
                          
                        }
                         Cell cell5=excelRow.createCell(5);
                         cell5.setCellStyle(styleWrap);
                         Cell cell6=excelRow.createCell(6);
                         cell6.setCellStyle(styleWrap);
                         Cell cell7=excelRow.createCell(7);
                         cell7.setCellStyle(styleWrap);
                         Cell cell8=excelRow.createCell(8);
                         cell8.setCellStyle(styleWrap);
                         
                         Cell cell9=excelRow.createCell(9);
                         cell9.setCellStyle(styleWrap);
                         Cell cell10=excelRow.createCell(10);
                         cell10.setCellStyle(styleWrap);
                         Cell cell11=excelRow.createCell(11);
                         cell11.setCellStyle(styleWrap);
                         Cell cell12=excelRow.createCell(12);
                         cell12.setCellStyle(styleWrap);
                         Cell cell13=excelRow.createCell(13);
                         cell13.setCellStyle(styleWrap);
                         Cell cell14=excelRow.createCell(14);
                         cell14.setCellStyle(styleWrap);
                         Cell cell15=excelRow.createCell(15);
                         cell15.setCellStyle(styleWrap);
                         
                         
                         
                         cell5.setCellValue(""+flName);
                         cell6.setCellValue(""+coverArea);
                         cell7.setCellValue(""+buildingUse);
                         cell8.setCellValue(""+category);
                         cell9.setCellValue(""+self_rent);
                         cell10.setCellValue(""+actualRent_val);
                         cell11.setCellValue(""+locationClass);
                         cell12.setCellValue(""+annualRent);
                         cell13.setCellValue(""+ratableValue);
                         cell14.setCellValue(""+tax_rate);
                         cell15.setCellValue(""+h_tax);
                         //excelRow.createCell(15).setCellValue(""+df.format(Long.parseLong(h_tax)));
                        
                        
                          
                        for(PaymentBean pr:payment){
                           if(fr.getUniqueId().equalsIgnoreCase(pr.getPropId())){
                             //pr.getPropId();
                             //pr.getReceiptDate();
                             //pr.getPayRefId();
                             //pr.getAmountPaid();
                              totalPay=Integer.parseInt(pr.getTotalPayment()); 
                              Cell cell19=excelRow.createCell(19);
                              cell19.setCellStyle(styleWrap);
                              Cell cell20=excelRow.createCell(20);
                              cell20.setCellStyle(styleWrap);
                              Cell cell21=excelRow.createCell(21);
                              cell21.setCellStyle(styleWrap);
                              Cell cell22=excelRow.createCell(22);
                              cell22.setCellStyle(styleWrap);
                              Cell cell23=excelRow.createCell(23);
                              cell23.setCellStyle(styleWrap);
                              
                              cell19.setCellValue(pr.getPayRefId());
                              cell20.setCellValue(pr.getReceiptDate());
                              cell21.setCellValue(pr.getAmountPaid());
                              cell22.setCellValue(pr.getTotalPayment());
                              cell23.setCellValue("");
                           }
                           
                         }
                          Cell cell16=excelRow.createCell(16);
                          cell16.setCellStyle(styleWrap);
                          Cell cell17=excelRow.createCell(17);
                          cell17.setCellStyle(styleWrap);
                          Cell cell18=excelRow.createCell(18);
                          cell18.setCellStyle(styleWrap);
                          Cell cell24=excelRow.createCell(24);
                          cell24.setCellStyle(styleWrap);
                          Cell cell25=excelRow.createCell(25);
                          cell25.setCellStyle(styleWrap);
                          Cell cell26=excelRow.createCell(26);
                          cell26.setCellStyle(styleWrap);
                          Cell cell27=excelRow.createCell(27);
                          cell27.setCellStyle(styleWrap);
                          Cell cell28=excelRow.createCell(28);
                          cell28.setCellStyle(styleWrap);
                          
                        
                        cell16.setCellValue(fr.getArrearAmount());
                        cell17.setCellValue(0); // for interest
                        
                        int dd=totalTax+Integer.parseInt(fr.getArrearAmount());
                        cell18.setCellValue(dd);
                        //excelRow.createCell(19).setCellValue("");// for receiptno
                        
			//excelRow.createCell(20).setCellValue("");// for receiptdate
                        //excelRow.createCell(21).setCellValue("");// for receiptamount
                        
                        //excelRow.createCell(22).setCellValue("");// total collection
                        cell24.setCellValue(""+fr.getRebate());
                        int balance=  dd-totalPay-Integer.parseInt(fr.getRebate());
                        
                        cell25.setCellValue("");// for penalty
                        cell26.setCellValue(""); // exemption type
                        
                        cell27.setCellValue(""+balance);// payable amount
                        //cell26.setCellValue(""+fr.getPayableAmount());// payable amount
                        cell28.setCellValue("");
                        balance=0;
                        totalPay=0;
                        
                        
                        
                        
                        
                        
		}
	}  

    
     

   

}
