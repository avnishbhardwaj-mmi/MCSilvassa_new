/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.silvassa.service;

import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.itextpdf.text.BaseColor;
import com.itextpdf.text.Chunk;
import com.itextpdf.text.Document;
import com.itextpdf.text.Element;
import com.itextpdf.text.Font;
import com.itextpdf.text.FontFactory;
import com.itextpdf.text.Image;
import com.itextpdf.text.PageSize;
import com.itextpdf.text.Paragraph;
import com.itextpdf.text.Phrase;
import com.itextpdf.text.Rectangle;
import com.itextpdf.text.pdf.BaseFont;
import com.itextpdf.text.pdf.PdfPCell;
import com.itextpdf.text.pdf.PdfPTable;
import com.itextpdf.text.pdf.PdfWriter;
import com.silvassa.bean.PrivateNoticeBean;
import com.silvassa.model.BankMaster;
import com.silvassa.model.PaymentBean;
import com.silvassa.model.PropertyDetails;
import com.silvassa.util.NumberToWord;
import java.io.File;
import java.io.FileOutputStream;
import java.text.DecimalFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.Iterator;
import javax.servlet.ServletOutputStream;
import org.springframework.stereotype.Component;

/**
 *
 * @author CEINFO
 */
public class PrintCollectionReceiptPDF extends AbstractITextPdfCollectionReceiptView {

    @Override
    protected void buildPdfDocument(Map<String, Object> model, Document document,
            PdfWriter writer, HttpServletRequest request, HttpServletResponse response)
            throws Exception {
        // get data model which is passed by the Spring container
        //PdfWriter.getInstance(document, new FileOutputStream("D:\\mnt\\vol1\\paymenReceipt\\HelloWorld.pdf"));
        //ServletOutputStream out=response.getOutputStream();

        String owner = "";
        String ppid = "";
        String occupier = "";
        String completeAddress = "";
        List<PaymentBean> beanList = (List<PaymentBean>) model.get("taxList");
        List<PropertyDetails> ownerList = (List<PropertyDetails>) model.get("listowner");
        List<BankMaster> bnk = (List<BankMaster>) model.get("bnk");

        //System.out.println("beanList "+beanList.size());
        Iterator itrOwner = ownerList.iterator();
        while (itrOwner.hasNext()) {
            PropertyDetails pd = (PropertyDetails) itrOwner.next();
            ppid = pd.getPropertyUniqueId();
            owner = pd.getPropertyOwner();
            occupier = pd.getPropertyOccupierName();
            completeAddress = pd.getCompleteAddress();
        }
        //byte bb[]=response.getOutputStream().toByteArray();
        //File f1=new File("D:\\"+ppid+".pdf") ;
        //FileOutputStream fout1=new FileOutputStream(f1);    
        //PdfWriter writer1 = PdfWriter.getInstance(document,  fout1);
        //fout1.write(bb);

        Iterator itr = beanList.iterator();
        //System.out.println("PDFBuilder142Notice");
        String path = request.getRealPath("/res/img/logo2.png");
        //String path1=request.getRealPath("/res/img/23.png");
        String pid = "";
        String clientName = "";
        String address1 = "";
        String address2 = "";
        String amount = "";
        String period = "upto2018";
        String refer_no = "Ref. No.:   ";
        String modeOfPayment = "Cash";

        //String fontPathGujrati=request.getRealPath("/res/fonts/wwgj0101.ttf");
        String fontPathHindi1 = request.getRealPath("/res/fonts/kruti-dev-021.ttf");
        //String fontPathHindi2=request.getRealPath("/res/fonts/mangal.ttf");
        BaseFont bf = BaseFont.createFont(fontPathHindi1, BaseFont.IDENTITY_H, BaseFont.EMBEDDED);
        //BaseFont bf = BaseFont.createFont("c:\\windows\\fonts\\Kruti_Dev.ttf",BaseFont.IDENTITY_H, BaseFont.EMBEDDED);

        Font fontHindi = new Font(bf, 18, Font.BOLD);
        Font fontHindi_1 = new Font(bf, 14, Font.BOLD);
        Font fontHindi_2 = new Font(bf, 8, Font.BOLD);
        Font fontEngH = new Font(Font.FontFamily.TIMES_ROMAN, 11, Font.NORMAL);
        Font fontHead = new Font(Font.FontFamily.TIMES_ROMAN, 11, Font.BOLD);
        Font fontEngH_1 = new Font(Font.FontFamily.TIMES_ROMAN, 8, Font.NORMAL);
        Font fontEngH_2 = new Font(Font.FontFamily.TIMES_ROMAN, 8, Font.NORMAL);
        Font fontEngH_3 = new Font(Font.FontFamily.TIMES_ROMAN, 14, Font.UNDERLINE);
        Font fontEngH_4 = new Font(Font.FontFamily.TIMES_ROMAN, 10, Font.NORMAL);
        Font fontEngH_5 = new Font(Font.FontFamily.TIMES_ROMAN, 12, Font.NORMAL);
        Font fontEngH_6 = new Font(Font.FontFamily.TIMES_ROMAN, 12, Font.BOLD);
        float[] columnWidths = {.8f, 1f, 1f, 1f, 1f, 1f};
        //Document document = new Document(PageSize.A4,0,0,10,0);
        //PdfWriter writer = PdfWriter.getInstance(document, new FileOutputStream("d://noticetest.pdf"));
        //document.open();
        Image image1 = Image.getInstance(path);
        image1.scalePercent(15f);
        //Image image2 = Image.getInstance(path1);
        //Image image2 = Image.getInstance("d:\\img\\23.png");
        SimpleDateFormat sdf = new SimpleDateFormat(("dd-MM-yyyy hh:mm:ss"));
        String date2 = (String) sdf.format(Calendar.getInstance().getTime());
        date2 = date2.substring(0, 10);
        DecimalFormat df = new DecimalFormat("#,###");
        Calendar c = Calendar.getInstance();
        c.add(Calendar.DAY_OF_MONTH, 30);
        String newDate = sdf.format(c.getTime());
        newDate = newDate.substring(0, 10);

        while (itr.hasNext()) {
            String sp = "";
            String op = "";
            String oldmc = "";
            int index = 1;
            int sno = 1;
            String bankName = "";

            PaymentBean bean = (PaymentBean) itr.next();

            Iterator itrBank = bnk.iterator();
            while (itrBank.hasNext()) {
                BankMaster bkn = (BankMaster) itrBank.next();
                String bankId = bkn.getBankId();

                if (bankId.equalsIgnoreCase(bean.getBankName())) {
                    bankName = bkn.getBankName();
                    break;
                }
            }
            clientName = bean.getBankName();
            address1 = bean.getContactNo();
            amount = "2";//bean.getPayerName();
            pid = bean.getPropId();
            if (bean.getPaymentMode().equals("BHIM_UPI")) {
                refer_no = "BHIM UPI Ref. No.:   ";
                modeOfPayment = "BHIM UPI";
            } else if (bean.getPaymentMode().equals("POS_DEVICE")) {
                refer_no = "POS Ref. No.:   ";
                modeOfPayment = "POS";
            } else if (bean.getPaymentMode().equals("DDF")) {
                refer_no = "DD No.:   ";
                modeOfPayment = "Demand Draft";
            } else if (bean.getPaymentMode().equals("CHQ")) {
                refer_no = "Cheque No.:   ";
                modeOfPayment = "Cheque";
            } else if (bean.getPaymentMode().equals("NETBANKING")) {

                modeOfPayment = "NETBANKING";
            } else if (bean.getPaymentMode().equals("NEFT_RTGS")) {
                refer_no = "NEFT/RTGS Ref. No.:   ";
                modeOfPayment = "NEFT/RTGS";
            } else if (bean.getPaymentMode().equals("UPI")) {
                modeOfPayment = "UPI";
            }

            NumberToWord.convertNumberToWords(Long.parseLong(bean.getAmountDemand()));

            Paragraph c1 = new Paragraph("  ");
            document.add(c1);

            PdfPTable headertable = new PdfPTable(6);
            PdfPCell cell1_ = new PdfPCell(image1);
            //cell1.setBorderWidthColor(BaseColor.BLUE);
            //cell1.setPaddingLeft(10);
            cell1_.setBorderWidth(0);
            //cell6.setPhrase(new Phrase("ak",fontEngH ));
            cell1_.setHorizontalAlignment(Element.ALIGN_CENTER);
            cell1_.setVerticalAlignment(Element.ALIGN_MIDDLE);
            cell1_.setColspan(1);

            PdfPCell cell5_ = new PdfPCell();
            //cell1.setBorderWidthWidthColor(BaseColor.BLUE);
            //cell1.setPaddingLeft(10);
            cell5_.setBorderWidth(0);
            cell5_.setPhrase(new Phrase("Silvassa Municipal Council", fontHead));
            cell5_.setHorizontalAlignment(Element.ALIGN_CENTER);
            cell5_.setVerticalAlignment(Element.ALIGN_MIDDLE);
            cell5_.setColspan(2);

            PdfPCell cell8_ = new PdfPCell();
            //cell1.setBorderWidthColor(BaseColor.BLUE);
            //cell1.setPaddingLeft(10);
            cell8_.setBorderWidth(0);
            cell8_.setPhrase(new Phrase("Silvassa Municipal Council, Opposite Udyog Bhawan Secretariat Road, Amli, U.T. of Dadra & Nagar Haveli, Silvassa – 396230 Help Line Number: 1800-1030-636 Email-Id:silvassamunicipalcouncil@gmail.com  Timing 09:30 AM to 06:00 PM. ", fontEngH_1));
            cell8_.setHorizontalAlignment(Element.ALIGN_LEFT);
            //cell8.setHorizontalAlignment(Element.ALIGN_JUSTIFIED);
            cell8_.setColspan(3);
            headertable.addCell(cell1_);
            headertable.addCell(cell5_);
            headertable.addCell(cell8_);
            document.add(headertable);
            Paragraph c2_ = new Paragraph("  ");
            document.add(c2_);

//                    
//                    
//                    PdfPTable table = new PdfPTable(6); 
//                    PdfPCell cell6 = new PdfPCell(image1);
//                    //cell1.setBorderWidthColor(BaseColor.BLUE);
//                    //cell1.setPaddingLeft(10);
//                    cell6.setBorderWidth(0);
//                    //cell6.setPhrase(new Phrase("ak",fontEngH ));
//                    cell6.setHorizontalAlignment(Element.ALIGN_CENTER);
//                    cell6.setVerticalAlignment(Element.ALIGN_MIDDLE);
//                    cell6.setColspan(6);
//                    
//                    table.addCell(cell6);
//                    document.add(table);
//                    Paragraph c2=new Paragraph("  ");
//                    document.add(c2);
//                    
//                    PdfPTable table1 = new PdfPTable(6); 
//                    PdfPCell cell5 = new PdfPCell();
//                    //cell1.setBorderWidthWidthColor(BaseColor.BLUE);
//                    //cell1.setPaddingLeft(10);
//                    cell5.setBorderWidth(0);
//                    cell5.setPhrase(new Phrase("Silvassa Municipal Council",fontEngH));
//                    cell5.setHorizontalAlignment(Element.ALIGN_CENTER);
//                    cell5.setVerticalAlignment(Element.ALIGN_MIDDLE);
//                    cell5.setColspan(6);
//                    table1.addCell(cell5); 
//                    document.add(table1);
//                    
//                    PdfPTable table2 = new PdfPTable(6); 
//                    PdfPCell cell7 = new PdfPCell();
//                    //cell1.setBorderWidthColor(BaseColor.BLUE);
//                    //cell1.setPaddingLeft(10);
//                    cell7.setBorderWidth(0);
//                    cell7.setPhrase(new Phrase(" ",fontEngH));
//                    cell7.setHorizontalAlignment(Element.ALIGN_CENTER);
//                    cell7.setVerticalAlignment(Element.ALIGN_MIDDLE);
//                    cell7.setColspan(6);
//                    table2.addCell(cell7); 
//                    document.add(table2);
//                    
//                    PdfPTable table3 = new PdfPTable(6); 
////                    PdfPCell cell8_1 = new PdfPCell();
////                    //cell1.setBorderWidthColor(BaseColor.BLUE);
////                    //cell1.setPaddingLeft(10);
////                    cell8_1.setBorderWidth(0);
////                    cell8_1.setPhrase(new Phrase("",fontEngH));
////                    cell8_1.setHorizontalAlignment(Element.ALIGN_CENTER);
////                    //cell8.setHorizontalAlignment(Element.ALIGN_JUSTIFIED);
////                    cell8_1.setColspan(1);
////                    table3.addCell(cell8_1); 
//                    
//                    
//                    PdfPCell cell8 = new PdfPCell();
//                    //cell1.setBorderWidthColor(BaseColor.BLUE);
//                    //cell1.setPaddingLeft(10);
//                    cell8.setBorderWidth(0);
//                    cell8.setPhrase(new Phrase("Silvassa Municipal Council, Opposite Udyog Bhawan Secretariat Road, Amli, U.T. of Dadra & Nagar Haveli, Silvassa – 396230 Help Line Number: 1800-1030-636 Email-Id:silvassamunicipalcouncil@gmail.com  Timing 09:30 AM to 06:00 PM. ",fontEngH_1));
//                    cell8.setHorizontalAlignment(Element.ALIGN_LEFT);
//                    //cell8.setHorizontalAlignment(Element.ALIGN_JUSTIFIED);
//                    cell8.setColspan(6);
//                    table3.addCell(cell8); 
//                    
////                    PdfPCell cell8_2 = new PdfPCell();
////                    //cell1.setBorderWidthColor(BaseColor.BLUE);
////                    //cell1.setPaddingLeft(10);
////                    cell8_2.setBorderWidth(0);
////                    cell8_2.setPhrase(new Phrase("",fontEngH));
////                    cell8_2.setHorizontalAlignment(Element.ALIGN_CENTER);
////                    //cell8.setHorizontalAlignment(Element.ALIGN_JUSTIFIED);
////                    cell8_2.setColspan(1);
////                    table3.addCell(cell8_2); 
//                    
//                    
//                    document.add(table3);
//                    PdfPTable table4 = new PdfPTable(6); 
//                    PdfPCell cell9 = new PdfPCell();
//                    //cell1.setBorderWidthColor(BaseColor.BLUE);
//                    //cell1.setPaddingLeft(10);
//                    cell9.setBorderWidth(0);
//                    cell9.setPhrase(new Phrase(" ",fontEngH));
//                    cell9.setHorizontalAlignment(Element.ALIGN_CENTER);
//                    cell9.setVerticalAlignment(Element.ALIGN_MIDDLE);
//                    cell9.setColspan(6);
//                    table4.addCell(cell9); 
//                    document.add(table4);
            PdfPTable table5 = new PdfPTable(6);
            PdfPCell cell10 = new PdfPCell();
            //cell1.setBorderWidthColor(BaseColor.BLUE);
            //cell1.setPaddingLeft(10);
            cell10.setBorderWidth(0);
            cell10.setPhrase(new Phrase("Acknowledgement ", fontEngH_3));
            cell10.setHorizontalAlignment(Element.ALIGN_CENTER);
            cell10.setVerticalAlignment(Element.ALIGN_MIDDLE);
            cell10.setColspan(6);
            table5.addCell(cell10);
            document.add(table5);

//                    PdfPTable table6 = new PdfPTable(6); 
//                    PdfPCell cell11 = new PdfPCell();
//                    //cell1.setBorderWidthColor(BaseColor.BLUE);
//                    //cell1.setPaddingLeft(10);
//                    cell11.setBorderWidth(0);
//                    cell11.setPhrase(new Phrase(" ",fontEngH));
//                    cell11.setHorizontalAlignment(Element.ALIGN_CENTER);
//                    cell11.setVerticalAlignment(Element.ALIGN_MIDDLE);
//                    cell11.setColspan(6);
//                    table6.addCell(cell11); 
//                    document.add(table6);
            PdfPTable table7 = new PdfPTable(6);
            PdfPCell cell12 = new PdfPCell();
            //cell1.setBorderWidthColor(BaseColor.BLUE);
            //cell1.setPaddingLeft(10);
            cell12.setBorderWidth(0);
            cell12.setPhrase(new Phrase("Receipt of property tax payment ", fontEngH_1));
            cell12.setHorizontalAlignment(Element.ALIGN_CENTER);
            cell12.setVerticalAlignment(Element.ALIGN_MIDDLE);
            cell12.setColspan(6);
            table7.addCell(cell12);
            document.add(table7);

            PdfPTable table8 = new PdfPTable(6);
            PdfPCell cell13 = new PdfPCell();
            //cell1.setBorderWidthColor(BaseColor.BLUE);
            //cell1.setPaddingLeft(10);
            cell13.setBorderWidth(0);
            cell13.setPhrase(new Phrase(" ", fontEngH));
            cell13.setHorizontalAlignment(Element.ALIGN_CENTER);
            cell13.setVerticalAlignment(Element.ALIGN_MIDDLE);
            cell13.setColspan(6);
            table8.addCell(cell13);
            document.add(table8);

            PdfPTable table9_1 = new PdfPTable(6);
            PdfPCell cell16_1 = new PdfPCell();
            cell16_1.setBorderColor(BaseColor.DARK_GRAY);
            //cell1.setPaddingLeft(10);
            cell16_1.setBorderWidth(1);
            cell16_1.setPhrase(new Phrase("Owner Name :  " + owner, fontEngH));
            cell16_1.setHorizontalAlignment(Element.ALIGN_LEFT);
            cell16_1.setVerticalAlignment(Element.ALIGN_MIDDLE);
            cell16_1.setColspan(6);
            table9_1.addCell(cell16_1);
            document.add(table9_1);

//            Chunk c_addTxt=new Chunk("Address : ",fontEngH);
//            Chunk c_addTxt1=new Chunk(" "+completeAddress,fontEngH);
//            Phrase p_addTxt=new Phrase();
//            p_addTxt.add(c_addTxt);
//            p_addTxt.add(c_addTxt1);
            PdfPTable table9_3 = new PdfPTable(6);
            table9_3.setWidths(columnWidths);
            PdfPCell cell177_1 = new PdfPCell();

            //cell1.setBorderWidthColor(BaseColor.BLUE);
            //cell1.setPaddingLeft(10);
            cell177_1.setBorderWidth(1);
            //cell177_1.setPhrase(p_addTxt);
            cell177_1.setPhrase(new Phrase("Address : ", fontEngH));
            cell177_1.setHorizontalAlignment(Element.ALIGN_LEFT);
            cell177_1.setVerticalAlignment(Element.ALIGN_MIDDLE);
            cell177_1.setBorder(Rectangle.TOP | Rectangle.BOTTOM | Rectangle.LEFT);
            //cell8.setHorizontalAlignment(Element.ALIGN_JUSTIFIED);
            cell177_1.setColspan(1);
            table9_3.addCell(cell177_1);

            PdfPCell cell177_1_3 = new PdfPCell();
            //cell1.setBorderWidthColor(BaseColor.BLUE);
            //cell1.setPaddingLeft(10);
            cell177_1_3.setBorderWidth(1);
            //cell177_1.setPhrase(p_addTxt);
            cell177_1_3.setPhrase(new Phrase(completeAddress, fontEngH));
            cell177_1_3.setHorizontalAlignment(Element.ALIGN_LEFT);
            cell177_1_3.setVerticalAlignment(Element.ALIGN_MIDDLE);
            cell177_1_3.setBorder(Rectangle.TOP | Rectangle.BOTTOM | Rectangle.RIGHT);
            //cell8.setHorizontalAlignment(Element.ALIGN_JUSTIFIED);
            cell177_1_3.setColspan(5);
            table9_3.addCell(cell177_1_3);

            document.add(table9_3);

            PdfPTable table9 = new PdfPTable(6);
            PdfPCell cell16 = new PdfPCell();
            cell16.setBorderColor(BaseColor.DARK_GRAY);
            //cell1.setPaddingLeft(10);
            cell16.setBorderWidth(1);
            cell16.setPhrase(new Phrase("Payment id:     " + bean.getPayRefId(), fontEngH));
            cell16.setHorizontalAlignment(Element.ALIGN_LEFT);
            cell16.setVerticalAlignment(Element.ALIGN_MIDDLE);
            cell16.setColspan(2);
            table9.addCell(cell16);
            String paymentDate = "";
            if (bean.getEntryDateTime() != null) {
                Date rdate = bean.getEntryDateTime();
                paymentDate = sdf.format(rdate);
                paymentDate = paymentDate.substring(0, 10);
            }

            PdfPCell cell177 = new PdfPCell();
            //cell1.setBorderWidthColor(BaseColor.BLUE);
            //cell1.setPaddingLeft(10);
            cell177.setBorderWidth(1);
            cell177.setPhrase(new Phrase("Property id:     " + bean.getPropId(), fontEngH));
            cell177.setHorizontalAlignment(Element.ALIGN_LEFT);
            cell177.setVerticalAlignment(Element.ALIGN_MIDDLE);
            //cell8.setHorizontalAlignment(Element.ALIGN_JUSTIFIED);
            cell177.setColspan(2);
            table9.addCell(cell177);

            PdfPCell cell15 = new PdfPCell();
            //cell1.setBorderWidthColor(BaseColor.BLUE);
            //cell1.setPaddingLeft(10);
            cell15.setBorderColor(BaseColor.DARK_GRAY);
            cell15.setBorderWidth(1);
            cell15.setPhrase(new Phrase("Payment date:     " + paymentDate, fontEngH));
            cell15.setHorizontalAlignment(Element.ALIGN_LEFT);
            cell15.setVerticalAlignment(Element.ALIGN_MIDDLE);
            cell15.setColspan(2);
            table9.addCell(cell15);
            document.add(table9);

            PdfPTable table_9 = new PdfPTable(6);
            PdfPCell cell1_6 = new PdfPCell();
            cell16.setBorderColor(BaseColor.DARK_GRAY);
            //cell1.setPaddingLeft(10);
            cell1_6.setBorderWidth(1);
            // cell1_6.setPhrase(new Phrase("Arrears:     "+bean.getArrear(),fontEngH));
            cell1_6.setPhrase(new Phrase("Arrears:     ", fontEngH));
            cell1_6.setHorizontalAlignment(Element.ALIGN_LEFT);
            cell1_6.setVerticalAlignment(Element.ALIGN_MIDDLE);
            cell1_6.setColspan(2);
            table_9.addCell(cell1_6);

            String interest = "0";
            if (bean.getPendingAmount() == null) {
                interest = "0";
            } else {
                interest = bean.getPendingAmount();
            }

            PdfPCell cell1_7 = new PdfPCell();
            //cell1.setBorderWidthColor(BaseColor.BLUE);
            //cell1.setPaddingLeft(10);
            cell1_7.setBorderWidth(1);
            //cell1_7.setPhrase(new Phrase("Interest:     "+interest,fontEngH));
            cell1_7.setPhrase(new Phrase("Interest:     ", fontEngH));
            cell1_7.setHorizontalAlignment(Element.ALIGN_LEFT);
            cell1_7.setVerticalAlignment(Element.ALIGN_MIDDLE);
            //cell8.setHorizontalAlignment(Element.ALIGN_JUSTIFIED);
            cell1_7.setColspan(2);
            table_9.addCell(cell1_7);

            PdfPCell cell1_5 = new PdfPCell();
            //cell1.setBorderWidthColor(BaseColor.BLUE);
            //cell1.setPaddingLeft(10);
            cell1_5.setBorderColor(BaseColor.DARK_GRAY);
            cell1_5.setBorderWidth(1);
            cell1_5.setPhrase(new Phrase("Tax Due:     ", fontEngH));
            cell1_5.setHorizontalAlignment(Element.ALIGN_LEFT);
            cell1_5.setVerticalAlignment(Element.ALIGN_MIDDLE);
            cell1_5.setColspan(2);
            table_9.addCell(cell1_5);
            document.add(table_9);

            PdfPTable table_91 = new PdfPTable(6);
            PdfPCell cell1_1 = new PdfPCell();
            cell1_1.setBorderColor(BaseColor.DARK_GRAY);
            //cell1.setPaddingLeft(10);
            cell1_1.setBorderWidth(1);
            cell1_1.setPhrase(new Phrase("Payment Received (Rs.):     " + bean.getAmountPaid(), fontEngH));
            cell1_1.setHorizontalAlignment(Element.ALIGN_LEFT);
            cell1_1.setVerticalAlignment(Element.ALIGN_MIDDLE);
            cell1_1.setColspan(6);
            table_91.addCell(cell1_1);
            document.add(table_91);

            PdfPTable table_99 = new PdfPTable(6);
            PdfPCell cell1 = new PdfPCell();
            cell1.setBorderColor(BaseColor.DARK_GRAY);
            //cell1.setPaddingLeft(10);
            cell1.setBorderWidth(1);
            cell1.setPhrase(new Phrase("Arrears Left:     ", fontEngH));
            cell1.setHorizontalAlignment(Element.ALIGN_LEFT);
            cell1.setVerticalAlignment(Element.ALIGN_MIDDLE);
            cell1.setColspan(2);
            table_99.addCell(cell1);

            PdfPCell cell1_12 = new PdfPCell();
            //cell1.setBorderWidthColor(BaseColor.BLUE);
            //cell1.setPaddingLeft(10);
            cell1_12.setBorderWidth(1);
            cell1_12.setPhrase(new Phrase("Interest Left:     ", fontEngH));
            cell1_12.setHorizontalAlignment(Element.ALIGN_LEFT);
            cell1_12.setVerticalAlignment(Element.ALIGN_MIDDLE);
            //cell8.setHorizontalAlignment(Element.ALIGN_JUSTIFIED);
            cell1_12.setColspan(2);
            table_99.addCell(cell1_12);

            PdfPCell cell1_15 = new PdfPCell();
            //cell1.setBorderWidthColor(BaseColor.BLUE);
            //cell1.setPaddingLeft(10);
            cell1_15.setBorderColor(BaseColor.DARK_GRAY);
            cell1_15.setBorderWidth(1);
            cell1_15.setPhrase(new Phrase("Tax Left:     ", fontEngH));
            cell1_15.setHorizontalAlignment(Element.ALIGN_LEFT);
            cell1_15.setVerticalAlignment(Element.ALIGN_MIDDLE);
            cell1_15.setColspan(2);
            table_99.addCell(cell1_15);
            document.add(table_99);

            PdfPTable table_19 = new PdfPTable(6);
            PdfPCell cel1 = new PdfPCell();
            cel1.setBorderColor(BaseColor.DARK_GRAY);
            //cell1.setPaddingLeft(10);
            cel1.setBorderWidth(1);
            cel1.setPhrase(new Phrase("Name of payee:     ", fontEngH));
            cel1.setHorizontalAlignment(Element.ALIGN_LEFT);
            cel1.setVerticalAlignment(Element.ALIGN_MIDDLE);
            cel1.setColspan(3);
            table_19.addCell(cel1);

//                    
//                    PdfPCell cell2 = new PdfPCell();
//                    //cell1.setBorderWidthColor(BaseColor.BLUE);
//                    //cell1.setPaddingLeft(10);
//                    cell2.setBorderWidth(1);
//                    cell2.setPhrase(new Phrase("Contact:     ",fontEngH));
//                    cell2.setHorizontalAlignment(Element.ALIGN_LEFT);
//                    cell2.setVerticalAlignment(Element.ALIGN_MIDDLE);
//                    //cell8.setHorizontalAlignment(Element.ALIGN_JUSTIFIED);
//                    cell2.setColspan(3);
//                    table_19.addCell(cell2);
//                    document.add(table_19);
//                    
//                    PdfPTable table10 = new PdfPTable(6); 
//                    PdfPCell cell17 = new PdfPCell();
//                    //cell1.setBorderWidthColor(BaseColor.BLUE);
//                    //cell1.setPaddingLeft(10);
//                    cell17.setBorderWidth(1);
//                    cell17.setPhrase(new Phrase("Property id:     "+bean.getPropId(),fontEngH));
//                    cell17.setHorizontalAlignment(Element.ALIGN_LEFT);
//                    cell17.setVerticalAlignment(Element.ALIGN_MIDDLE);
//                    //cell8.setHorizontalAlignment(Element.ALIGN_JUSTIFIED);
//                    cell17.setColspan(3);
//                    table10.addCell(cell17); 
//                    
//                    PdfPCell cell18 = new PdfPCell();
//                    //cell1.setBorderWidthColor(BaseColor.BLUE);
//                    //cell1.setPaddingLeft(10);
//                    cell18.setBorderWidth(1);
//                    cell18.setPhrase(new Phrase("Financial year:     "+bean.getFinancialYear(),fontEngH));
//                    cell18.setHorizontalAlignment(Element.ALIGN_LEFT);
//                    //cell8.setHorizontalAlignment(Element.ALIGN_JUSTIFIED);
//                    cell18.setVerticalAlignment(Element.ALIGN_MIDDLE);
//                    cell18.setColspan(3);
//                    table10.addCell(cell18); 
//                    document.add(table10);
//                    
//                    PdfPTable table11 = new PdfPTable(6); 
//                    PdfPCell cell19 = new PdfPCell();
//                    //cell1.setBorderWidthColor(BaseColor.BLUE);
//                    //cell1.setPaddingLeft(10);
//                    cell19.setBorderWidth(1);
//                    cell19.setPhrase(new Phrase("Payment for arrear:     "+bean.getArrear(),fontEngH));
//                    cell19.setHorizontalAlignment(Element.ALIGN_LEFT);
//                    //cell8.setHorizontalAlignment(Element.ALIGN_JUSTIFIED);
//                    cell19.setVerticalAlignment(Element.ALIGN_MIDDLE);
//                    cell19.setColspan(3);
//                    table11.addCell(cell19); 
//                    
//                    PdfPCell cell20 = new PdfPCell();
//                    //cell1.setBorderWidthColor(BaseColor.BLUE);
//                    //cell1.setPaddingLeft(10);
//                    cell20.setBorderWidth(1);
//                    cell20.setPhrase(new Phrase("Demand as on date (Amount of tax etc):     "+bean.getAmopuntDemand(),fontEngH));
//                    cell20.setHorizontalAlignment(Element.ALIGN_LEFT);
//                    //cell8.setHorizontalAlignment(Element.ALIGN_JUSTIFIED);
//                    cell20.setVerticalAlignment(Element.ALIGN_MIDDLE);
//                    cell20.setColspan(3);
//                    table11.addCell(cell20); 
//                    document.add(table11);
//                    
//                    if(bean.getPendingAmount()==null){
//                        int interest=0;
//                        PdfPTable table12_1 = new PdfPTable(6); 
//                    PdfPCell cell21_1 = new PdfPCell();
//                    //cell1.setBorderWidthColor(BaseColor.BLUE);
//                    //cell1.setPaddingLeft(10);
//                    cell21_1.setBorderWidth(1);
//                    cell21_1.setPhrase(new Phrase("Interest amount:     "+interest,fontEngH));
//                    cell21_1.setHorizontalAlignment(Element.ALIGN_LEFT);
//                    //cell8.setHorizontalAlignment(Element.ALIGN_JUSTIFIED);
//                    cell21_1.setVerticalAlignment(Element.ALIGN_MIDDLE);
//                    cell21_1.setColspan(6);
//                    
//                    table12_1.addCell(cell21_1); 
//                    document.add(table12_1);
//                    }else{
//                       PdfPTable table12_1 = new PdfPTable(6); 
//                    PdfPCell cell21_1 = new PdfPCell();
//                    //cell1.setBorderWidthColor(BaseColor.BLUE);
//                    //cell1.setPaddingLeft(10);
//                    cell21_1.setBorderWidth(1);
//                    cell21_1.setPhrase(new Phrase("Interst amount:     "+bean.getPendingAmount(),fontEngH));
//                    cell21_1.setHorizontalAlignment(Element.ALIGN_LEFT);
//                    //cell8.setHorizontalAlignment(Element.ALIGN_JUSTIFIED);
//                    cell21_1.setVerticalAlignment(Element.ALIGN_MIDDLE);
//                    cell21_1.setColspan(6);
//                    
//                    table12_1.addCell(cell21_1); 
//                    document.add(table12_1); 
//                    }
//                    
//                    
//                    
//                    PdfPTable table12_1_2 = new PdfPTable(6); 
//                    PdfPCell cell21_1_2 = new PdfPCell();
//                    //cell1.setBorderWidthColor(BaseColor.BLUE);
//                    //cell1.setPaddingLeft(10);
//                    cell21_1_2.setBorderWidth(1);
//                    cell21_1_2.setPhrase(new Phrase("Penalty amount:     "+bean.getLatePaymentCharge(),fontEngH));
//                    cell21_1_2.setHorizontalAlignment(Element.ALIGN_LEFT);
//                    //cell8.setHorizontalAlignment(Element.ALIGN_JUSTIFIED);
//                    cell21_1_2.setVerticalAlignment(Element.ALIGN_MIDDLE);
//                    cell21_1_2.setColspan(6);
//                    
//                    table12_1_2.addCell(cell21_1_2); 
//                    document.add(table12_1_2);
//                    String ownerN="";
//                    if(owner!=null){
//                        ownerN=owner;
//                    }
//                    
//                    
//                    PdfPTable table12 = new PdfPTable(6); 
//                    PdfPCell cell21 = new PdfPCell();
//                    //cell1.setBorderWidthColor(BaseColor.BLUE);
//                    //cell1.setPaddingLeft(10);
//                    cell21.setBorderWidth(1);
//                    cell21.setPhrase(new Phrase("Owner name:     "+ownerN,fontEngH));
//                    cell21.setHorizontalAlignment(Element.ALIGN_LEFT);
//                    //cell8.setHorizontalAlignment(Element.ALIGN_JUSTIFIED);
//                    cell21.setVerticalAlignment(Element.ALIGN_MIDDLE);
//                    cell21.setColspan(6);
//                    
//                    table12.addCell(cell21); 
//                    document.add(table12);
//                    
////                    PdfPTable table13 = new PdfPTable(6); 
////                    PdfPCell cell22 = new PdfPCell();
////                    //cell1.setBorderWidthColor(BaseColor.BLUE);
////                    //cell1.setPaddingLeft(10);
////                    cell22.setBorderWidth(1);
////                    cell22.setPhrase(new Phrase("Father/Spouse Name ",fontEngH));
////                    cell22.setHorizontalAlignment(Element.ALIGN_LEFT);
////                    //cell8.setHorizontalAlignment(Element.ALIGN_JUSTIFIED);
////                    cell22.setVerticalAlignment(Element.ALIGN_MIDDLE);
////                    cell22.setColspan(6);
////                    table13.addCell(cell22); 
////                    document.add(table13);
//                    
//                    String occupName="";
//                    if(occupier!=null){
//                        occupName=occupier;
//                    }
//                    PdfPTable table14 = new PdfPTable(6); 
//                    PdfPCell cell23 = new PdfPCell();
//                    //cell1.setBorderWidthColor(BaseColor.BLUE);
//                    //cell1.setPaddingLeft(10);
//                    cell23.setBorderWidth(1);
//                    cell23.setPhrase(new Phrase("Occupier name :     "+occupName,fontEngH));
//                    cell23.setHorizontalAlignment(Element.ALIGN_LEFT);
//                    //cell8.setHorizontalAlignment(Element.ALIGN_JUSTIFIED);
//                    cell23.setColspan(6);
//                    table14.addCell(cell23); 
//                    document.add(table14);
//                    
////                    PdfPTable table15 = new PdfPTable(6); 
////                    PdfPCell cell24 = new PdfPCell();
////                    //cell1.setBorderWidthColor(BaseColor.BLUE);
////                    //cell1.setPaddingLeft(10);
////                    cell24.setBorderWidth(1);
////                    cell24.setPhrase(new Phrase("Relation with Occupier  : ",fontEngH));
////                    cell24.setHorizontalAlignment(Element.ALIGN_LEFT);
////                    //cell8.setHorizontalAlignment(Element.ALIGN_JUSTIFIED);
////                    cell24.setColspan(6);
////                    table15.addCell(cell24); 
////                    document.add(table15);
//                     //String wordcov=NumberToWord.convertNumberToWords(Long.parseLong(bean.getAmountDemand()));
//                    PdfPTable table16 = new PdfPTable(6); 
//                    PdfPCell cell25 = new PdfPCell();
//                    //cell1.setBorderWidthColor(BaseColor.BLUE);
//                    //cell1.setPaddingLeft(10);
//                    cell25.setBorderWidth(1);
//                    cell25.setPhrase(new Phrase("Address of the property :     "+completeAddress,fontEngH));
//                    cell25.setHorizontalAlignment(Element.ALIGN_LEFT);
//                    //cell8.setHorizontalAlignment(Element.ALIGN_JUSTIFIED);
//                    cell25.setColspan(6);
//                    table16.addCell(cell25); 
//                    document.add(table16);
//                    
//                    PdfPTable table17 = new PdfPTable(6); 
//                    PdfPCell cell26 = new PdfPCell();
//                    //cell1.setBorderWidthColor(BaseColor.BLUE);
//                    //cell1.setPaddingLeft(10);
//                    cell26.setBorderWidth(1);
//                   
//                    cell26.setPhrase(new Phrase("Amount received Rs :     "+bean.getAmopuntPaid(),fontEngH));
//                    cell26.setHorizontalAlignment(Element.ALIGN_LEFT);
//                    //cell8.setHorizontalAlignment(Element.ALIGN_JUSTIFIED);
//                    cell26.setColspan(6);
//                    table17.addCell(cell26); 
//                    document.add(table17);
//                    String wordcov="";
//                    if(bean.getAmopuntPaid().length()>0){
//                        wordcov=NumberToWord.convertNumberToWords(Long.parseLong(bean.getAmopuntPaid()));
//                    }
//                    
//                    
//                    
//                    PdfPTable table18 = new PdfPTable(6); 
//                    PdfPCell cell27 = new PdfPCell();
//                    //cell1.setBorderWidthColor(BaseColor.BLUE);
//                    //cell1.setPaddingLeft(10);
//                    cell27.setBorderWidth(1);
//                    cell27.setPhrase(new Phrase("In word :     "+wordcov,fontEngH));
//                    cell27.setHorizontalAlignment(Element.ALIGN_LEFT);
//                    //cell8.setHorizontalAlignment(Element.ALIGN_JUSTIFIED);
//                    cell27.setColspan(6);
//                    table18.addCell(cell27); 
//                    document.add(table18);
            PdfPTable table19 = new PdfPTable(6);
            PdfPCell cell28 = new PdfPCell();
            //cell1.setBorderWidthColor(BaseColor.BLUE);
            //cell1.setPaddingLeft(10);
            cell28.setBorderWidth(1);
            cell28.setPhrase(new Phrase("Mode of payment:     " + modeOfPayment, fontEngH));
            cell28.setHorizontalAlignment(Element.ALIGN_LEFT);
            //cell8.setHorizontalAlignment(Element.ALIGN_JUSTIFIED);
            cell28.setColspan(3);
            table19.addCell(cell28);
//                    PdfPCell cell29 = new PdfPCell();
//                    //cell1.setBorderWidthColor(BaseColor.BLUE);
//                    //cell1.setPaddingLeft(10);
//                    cell29.setBorderWidth(1);
//                    cell29.setPhrase(new Phrase(" ",fontEngH));
//                    cell29.setHorizontalAlignment(Element.ALIGN_LEFT);
//                    //cell8.setHorizontalAlignment(Element.ALIGN_JUSTIFIED);
//                    cell29.setColspan(3);
//                    table19.addCell(cell29); 

            document.add(table19);
            String chequeDDNo = "";
            if (bean.getPaymentMode().equals("NETBANKING")) {
                chequeDDNo = bean.getBankRefId() != null ? bean.getBankRefId() : chequeDDNo;     //bankRefId
            } else {
                if (bean.getChequeDDNum() != null) {
                    chequeDDNo = bean.getChequeDDNum();
                }
            }

//                    PdfPTable table20 = new PdfPTable(6); 
//                    PdfPCell cell30 = new PdfPCell();
//                    //cell1.setBorderWidthColor(BaseColor.BLUE);
//                    //cell1.setPaddingLeft(10);
//                    cell30.setBorderWidth(1);
//                    cell30.setPhrase(new Phrase("E-Transaction id(if E-Payment):     "+bean.getPaymentMode(),fontEngH));
//                    cell30.setHorizontalAlignment(Element.ALIGN_LEFT);
//                    //cell8.setHorizontalAlignment(Element.ALIGN_JUSTIFIED);
//                    cell30.setColspan(3);
//                    table20.addCell(cell30); 
            PdfPCell cell31 = new PdfPCell();
            //cell1.setBorderWidthColor(BaseColor.BLUE);
            //cell1.setPaddingLeft(10);
            cell31.setBorderWidth(1);
            cell31.setPhrase(new Phrase(refer_no + chequeDDNo, fontEngH));
            cell31.setHorizontalAlignment(Element.ALIGN_LEFT);
            //cell8.setHorizontalAlignment(Element.ALIGN_JUSTIFIED);
            cell31.setColspan(3);
            table19.addCell(cell31);
//                    
            document.add(table19);
            String branch = "";
            if (bean.getBankBranch() != null) {
                branch = bean.getBankBranch();
            }

            PdfPTable table21 = new PdfPTable(6);
            PdfPCell cell32 = new PdfPCell();
            //cell1.setBorderWidthColor(BaseColor.BLUE);
            //cell1.setPaddingLeft(10);
            cell32.setBorderWidth(1);
            cell32.setPhrase(new Phrase("Bank:     " + bankName, fontEngH));
            cell32.setHorizontalAlignment(Element.ALIGN_LEFT);
            //cell8.setHorizontalAlignment(Element.ALIGN_JUSTIFIED);
            cell32.setColspan(3);
            table21.addCell(cell32);
            PdfPCell cell33 = new PdfPCell();
            //cell1.setBorderWidthColor(BaseColor.BLUE);
            //cell1.setPaddingLeft(10);
            cell33.setBorderWidth(1);
            cell33.setPhrase(new Phrase("Branch:     " + branch, fontEngH));
            cell33.setHorizontalAlignment(Element.ALIGN_LEFT);
            //cell8.setHorizontalAlignment(Element.ALIGN_JUSTIFIED);
            cell33.setColspan(3);
            table21.addCell(cell33);

            document.add(table21);
            String payerName = "";
            String contactNo = "";
            String paymentPeriod = "";
            if (bean.getPayerName() != null) {
                payerName = bean.getPayerName();
            }
            if (bean.getContactNo() != null) {
                contactNo = bean.getContactNo();
            }
            if (bean.getPaymentPeriod() != null) {
                paymentPeriod = bean.getPaymentPeriod();
            }
            PdfPTable table22 = new PdfPTable(6);
            PdfPCell cell34 = new PdfPCell();
            //cell1.setBorderWidthColor(BaseColor.BLUE);
            //cell1.setPaddingLeft(10);
            cell34.setBorderWidth(1);
            cell34.setPhrase(new Phrase("Payee name:     " + payerName, fontEngH));
            cell34.setHorizontalAlignment(Element.ALIGN_LEFT);
            //cell8.setHorizontalAlignment(Element.ALIGN_JUSTIFIED);
            cell34.setColspan(3);
            table22.addCell(cell34);
            PdfPCell cell35 = new PdfPCell();
            //cell1.setBorderWidthColor(BaseColor.BLUE);
            //cell1.setPaddingLeft(10);
            cell35.setBorderWidth(1);
            cell35.setPhrase(new Phrase("Mobile no.:     " + contactNo, fontEngH));
            cell35.setHorizontalAlignment(Element.ALIGN_LEFT);
            //cell8.setHorizontalAlignment(Element.ALIGN_JUSTIFIED);
            cell35.setColspan(3);
            table22.addCell(cell35);

            document.add(table22);

            PdfPTable table24_1 = new PdfPTable(6);
            PdfPCell cell38_1 = new PdfPCell();
            //cell1.setBorderWidthColor(BaseColor.BLUE);
            //cell1.setPaddingLeft(10);
            cell38_1.setBorderWidth(1);
            cell38_1.setPhrase(new Phrase("Payment Period:     " + paymentPeriod, fontEngH));
            cell38_1.setHorizontalAlignment(Element.ALIGN_LEFT);
            //cell8.setHorizontalAlignment(Element.ALIGN_JUSTIFIED);
            cell38_1.setColspan(6);
            table24_1.addCell(cell38_1);

            document.add(table24_1);

            if (modeOfPayment.equalsIgnoreCase("Cheque")) {
                if (!bean.getStatus().equalsIgnoreCase("C")) {
                    PdfPTable table26_1 = new PdfPTable(6);
                    PdfPCell cell39_1 = new PdfPCell();
                    //cell1.setBorderWidthColor(BaseColor.BLUE);
                    //cell1.setPaddingLeft(10);
                    cell39_1.setBorderWidth(0);
                    cell39_1.setPhrase(new Phrase("*The receipt is provisional and subject to clearing of cheque. ", fontEngH_6));
                    //cell39.setPhrase(new Phrase("Silvassa Municipal Council\n(Authorized Signature of Authority with stamp,date) ",fontEngH));
                    cell39_1.setHorizontalAlignment(Element.ALIGN_LEFT);
                    cell39_1.setVerticalAlignment(Element.ALIGN_MIDDLE);
                    cell39_1.setColspan(6);
                    table26_1.addCell(cell39_1);
                    document.add(table26_1);
                }

            }

            PdfPTable table23 = new PdfPTable(6);
            PdfPCell cell36 = new PdfPCell();
            //cell1.setBorderWidthColor(BaseColor.BLUE);
            //cell1.setPaddingLeft(10);
            cell36.setBorderWidth(0);
            cell36.setPhrase(new Phrase(" ", fontEngH));
            cell36.setHorizontalAlignment(Element.ALIGN_CENTER);
            cell36.setVerticalAlignment(Element.ALIGN_MIDDLE);
            cell36.setColspan(6);
            table23.addCell(cell36);
            document.add(table23);

//                    PdfPTable table24 = new PdfPTable(6); 
//                    PdfPCell cell37 = new PdfPCell();
//                    //cell1.setBorderWidthColor(BaseColor.BLUE);
//                    //cell1.setPaddingLeft(10);
//                    cell37.setBorderWidth(0);
//                    cell37.setPhrase(new Phrase("Thanks",fontEngH));
//                    cell37.setHorizontalAlignment(Element.ALIGN_LEFT);
//                    cell37.setVerticalAlignment(Element.ALIGN_MIDDLE);
//                    cell37.setColspan(6);
//                    table24.addCell(cell37); 
//                    document.add(table24);
//                    
//                    PdfPTable table25 = new PdfPTable(6); 
//                    PdfPCell cell38 = new PdfPCell();
//                    //cell1.setBorderWidthColor(BaseColor.BLUE);
//                    //cell1.setPaddingLeft(10);
//                    cell38.setBorderWidth(0);
//                    cell38.setPhrase(new Phrase(" ",fontEngH));
//                    cell38.setHorizontalAlignment(Element.ALIGN_CENTER);
//                    cell38.setVerticalAlignment(Element.ALIGN_MIDDLE);
//                    cell38.setColspan(6);
//                    table25.addCell(cell38); 
//                    document.add(table25);
//                    
            PdfPTable table26 = new PdfPTable(6);
            PdfPCell cell39 = new PdfPCell();
            //cell1.setBorderWidthColor(BaseColor.BLUE);
            //cell1.setPaddingLeft(10);
            cell39.setBorderWidth(0);
            cell39.setPhrase(new Phrase("It is a system generated receipt which does not need a signature. ", fontEngH));
            //cell39.setPhrase(new Phrase("Silvassa Municipal Council\n(Authorized Signature of Authority with stamp,date) ",fontEngH));
            cell39.setHorizontalAlignment(Element.ALIGN_LEFT);
            cell39.setVerticalAlignment(Element.ALIGN_MIDDLE);
            cell39.setColspan(6);
            table26.addCell(cell39);
            document.add(table26);
//                    

            //System.out.println("last");
            //document.close();
            //writer.close();
            sno++;
            index++;
            sp = "";
            op = "";
            oldmc = "";
            document.newPage();

        }
    }
}
