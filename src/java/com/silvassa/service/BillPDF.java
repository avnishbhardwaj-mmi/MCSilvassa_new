
/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.silvassa.service;

import com.itextpdf.text.BadElementException;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.itextpdf.text.BaseColor;
import com.itextpdf.text.Chunk;
import com.itextpdf.text.Document;
import com.itextpdf.text.DocumentException;
import com.itextpdf.text.Element;
import com.itextpdf.text.Font;
import com.itextpdf.text.FontFactory;
import com.itextpdf.text.Image;
import com.itextpdf.text.PageSize;
import com.itextpdf.text.Paragraph;
import com.itextpdf.text.Phrase;
import com.itextpdf.text.Rectangle;
import com.itextpdf.text.pdf.Barcode128;
import com.itextpdf.text.pdf.BarcodeEAN;
import com.itextpdf.text.pdf.BarcodePDF417;
import com.itextpdf.text.pdf.BarcodeQRCode;
import com.itextpdf.text.pdf.BaseFont;
import com.itextpdf.text.pdf.PdfContentByte;
import com.itextpdf.text.pdf.PdfPCell;
import com.itextpdf.text.pdf.PdfPTable;
import com.itextpdf.text.pdf.PdfTemplate;
import com.itextpdf.text.pdf.PdfWriter;
import com.silvassa.bean.PrivateNoticeBean;
import com.silvassa.model.BankMaster;
import com.silvassa.model.PaymentBean;
import com.silvassa.model.PropertyDetails;
import com.silvassa.model.QrcodePaymentBean;
import com.silvassa.util.ASE;
import com.silvassa.util.MmiPathController;
import com.silvassa.util.NumberToWord;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.text.DecimalFormat;
import java.text.SimpleDateFormat;
import java.time.Clock;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Iterator;
import javax.servlet.ServletOutputStream;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.PropertySource;
import org.springframework.core.env.Environment;
import org.springframework.stereotype.Component;
//import sun.java2d.pipe.SolidTextRenderer;

/**
 *
 * @author CEINFO
 */
@PropertySource("classpath:ApplicationResources.properties")
public class BillPDF extends AbstractITextPdfNoticeView {

    @Autowired
    private Environment env;

    @Override
    protected void buildPdfDocument(Map<String, Object> model, Document document,
            PdfWriter writer, HttpServletRequest request, HttpServletResponse response)
            throws Exception {
		// get data model which is passed by the Spring container
        //PdfWriter.getInstance(document, new FileOutputStream("D:\\mnt\\vol1\\paymenReceipt\\HelloWorld.pdf"));
        //ServletOutputStream out=response.getOutputStream();

        //document.setMargins(marginLeft, marginRight, marginTop, marginBottom)
        //document.setMargins(marginLeft, marginRight, marginTop, marginBottom)
        //document.setMargins(-5f,-10f,10f,0f);
        int columnWidths2[] = {2, 1, 1, 4, 4, 4, 3, 3, 3, 1, 2, 2};
        int columnWidths3[] = {2, 5, 2, 2, 2, 2, 2, 3, 1, 2, 2, 2};
        int columnWidths4[] = {2, 2, 2, 2, 3, 2, 2, 2, 2, 2, 2, 2};
        //int width[]={4,4,4,4,4,4};

        String owner = "";
        String ppid = "";
        String occupier = "";
        String completeAddress = "";
        String holdderName = "";
        String smcType = "";
        List<PrivateNoticeBean> beanList = (List<PrivateNoticeBean>) model.get("taxList");

                //System.out.println("beanList "+beanList.size());
        //byte bb[]=response.getOutputStream().toByteArray();
        //File f1=new File("D:\\"+ppid+".pdf") ;
        //FileOutputStream fout1=new FileOutputStream(f1);    
        //PdfWriter writer1 = PdfWriter.getInstance(document,  fout1);
        //fout1.write(bb);
        Iterator itr = beanList.iterator();
        //System.out.println("PDFBuilder142Notice");
        String path = request.getRealPath("/res/img/logo2.png");
        String path1 = request.getRealPath("/res/img/swachta_hi_sewa_logo.png");
        String pid = "";
        String clientName = "";
        String address1 = "";
        String address2 = "";
        String amount = "";
        String period = "upto2018";
        String fontPathGujrati = request.getRealPath("/res/fonts/wwgj0101.ttf");
        //String fontPathGujrati=request.getRealPath("/res/fonts/wwgj0101.ttf");
        String fontPathHindi1 = request.getRealPath("/res/fonts/kruti-dev-021.ttf");
        //String fontPathHindi2=request.getRealPath("/res/fonts/mangal.ttf");
        BaseFont bf = BaseFont.createFont(fontPathHindi1, BaseFont.IDENTITY_H, BaseFont.EMBEDDED);
        //BaseFont bf = BaseFont.createFont("c:\\windows\\fonts\\Kruti_Dev.ttf",BaseFont.IDENTITY_H, BaseFont.EMBEDDED);
        BaseFont bf_gujrati = BaseFont.createFont(fontPathGujrati, BaseFont.IDENTITY_H, BaseFont.EMBEDDED);
                //Font fontHindi=new Font(bf,18,Font.BOLD);
        //Font fontHindi_1=new Font(bf,14,Font.BOLD);
        // Font fontHindi_2=new Font(bf,8,Font.BOLD);
        //Font fontEngH=new Font(Font.FontFamily.TIMES_ROMAN,11,Font.BOLD);
        // Font fontEngH_1=new Font(Font.FontFamily.TIMES_ROMAN,10,Font.NORMAL);
        //Font fontEngH_2=new Font(Font.FontFamily.TIMES_ROMAN,8,Font.NORMAL);
        //Font fontEngH_3=new Font(Font.FontFamily.TIMES_ROMAN,14,Font.UNDERLINE);
        //Font fontEngH_4=new Font(Font.FontFamily.TIMES_ROMAN,10,Font.NORMAL);
        // Font fontEngH_5=new Font(Font.FontFamily.TIMES_ROMAN,12,Font.NORMAL);
        // Font fontEngH_6=new Font(Font.FontFamily.TIMES_ROMAN,12,Font.BOLD);

        //Font fontGujrati = new Font(bf_gujrati, 11, Font.NORMAL);
        Font fontGujrati = new Font(bf_gujrati, 11, Font.NORMAL);
        Font fontHindi = new Font(bf, 18, Font.BOLD);
        Font fontHindi_1 = new Font(bf, 17, Font.BOLD);
        Font fontHindi_2 = new Font(bf, 10, Font.NORMAL);
        Font fontHindi_2k = new Font(bf, 9, Font.NORMAL);
        Font fontEngH = new Font(Font.FontFamily.TIMES_ROMAN, 11, Font.BOLD);
        Font fontEngH_1 = new Font(Font.FontFamily.TIMES_ROMAN, 11, Font.NORMAL);
        Font fontEngH_header = new Font(Font.FontFamily.TIMES_ROMAN, 9, Font.NORMAL);
        Font fontEngH_dd = new Font(Font.FontFamily.TIMES_ROMAN, 9, Font.NORMAL);
        Font fontEngH_dd_1 = new Font(Font.FontFamily.TIMES_ROMAN, 7, Font.BOLD);
        Font fontEngH_dd_2 = new Font(Font.FontFamily.TIMES_ROMAN, 8, Font.BOLD);
        Font fontEngH_2k = new Font(Font.FontFamily.TIMES_ROMAN, 10, Font.NORMAL);
        Font fontEngH_2ky = new Font(Font.FontFamily.TIMES_ROMAN, 11, Font.BOLD);
        Font fontEngH_2ky_register = new Font(Font.FontFamily.TIMES_ROMAN, 9, Font.BOLD);
        Font fontEngH_2 = new Font(Font.FontFamily.TIMES_ROMAN, 9, Font.NORMAL);
        Font fontEngH_2_ = new Font(Font.FontFamily.TIMES_ROMAN, 8, Font.NORMAL);
        Font fontEngH_3 = new Font(Font.FontFamily.TIMES_ROMAN, 18, Font.BOLD);
        fontEngH_3.setColor(BaseColor.GRAY);
        Font fontEngH_4 = new Font(Font.FontFamily.TIMES_ROMAN, 10, Font.NORMAL);
        Font fontEngH_5 = new Font(Font.FontFamily.TIMES_ROMAN, 12, Font.NORMAL);
        Font fontEngH_6 = new Font(Font.FontFamily.TIMES_ROMAN, 4, Font.NORMAL);
        Font fontEngH_7 = new Font(Font.FontFamily.TIMES_ROMAN, 7, Font.NORMAL);
        Font fontEngH_7_1 = new Font(Font.FontFamily.TIMES_ROMAN, 9, Font.NORMAL);
        Font fontEngH_8 = new Font(Font.FontFamily.TIMES_ROMAN, 7, Font.BOLD);
        Font fontEngH_rule = new Font(Font.FontFamily.TIMES_ROMAN, 11, Font.NORMAL);
        //Font fontHindi_rule=new Font(bf,10,Font.NORMAL);
        Font fontHindi_rule = new Font(bf, 11, Font.NORMAL);
        Font fontEngH_4k = new Font(Font.FontFamily.TIMES_ROMAN, 7, Font.BOLD);
        Font fontEngH_9 = new Font(Font.FontFamily.TIMES_ROMAN, 2, Font.NORMAL);
        Font fontEngH_11 = new Font(Font.FontFamily.TIMES_ROMAN, 7, Font.NORMAL);
        Font fontEngH_4k_eng = new Font(Font.FontFamily.TIMES_ROMAN, 9, Font.NORMAL);
        Font fontEngH_8_1 = new Font(Font.FontFamily.TIMES_ROMAN, 3, Font.NORMAL);
        Font fontHindi_rule_header = new Font(bf, 10, Font.NORMAL);
        Font fontHindi_pid = new Font(bf, 9, Font.NORMAL);

        //Document document = new Document(PageSize.A4,0,0,10,0);
        //PdfWriter writer = PdfWriter.getInstance(document, new FileOutputStream("d://noticetest.pdf"));
        //document.open();
        Image image1 = Image.getInstance(path);
        image1.scalePercent(20f);
        Image image2 = Image.getInstance(path1);

        image2.scalePercent(30);
        //Image image2 = Image.getInstance("d:\\img\\23.png");
        SimpleDateFormat sdf = new SimpleDateFormat(("dd-MM-yyyy hh:mm:ss"));
        String date2 = (String) sdf.format(Calendar.getInstance().getTime());
        date2 = date2.substring(0, 10);
        DecimalFormat df = new DecimalFormat("#,###");
        Calendar c = Calendar.getInstance();
        c.add(Calendar.DAY_OF_MONTH, 30);
        String newDate = sdf.format(c.getTime());
        newDate = newDate.substring(0, 10);
        int seq = 0;
        while (itr.hasNext()) {
            seq++;
            String sp = "";
            String op = "";
            String oldmc = "";
            int index = 1;
            int sno = 1;
            String bankName = "";

            PrivateNoticeBean bean = (PrivateNoticeBean) itr.next();
        //    QrcodePaymentBean qrbean = bean.getQrcodedata(); //////FOR QRCODE///////
            Paragraph c1 = new Paragraph("  ");
            document.add(c1);

            PdfPTable table = new PdfPTable(12); // 3 columns.
            table.setWidthPercentage(88); //Width 100%
            //table.setSpacingBefore(10f); //Space before table
            //table.setSpacingAfter(10f); //Space after table

            //Set Column widths
            //int columnWidths[]={2,1,4,3,8,9,10,9,8,7,8,6};
            //float[] columnWidths = {1f, 1f, 1f,1f,1f,1f,1f,1f,1f,1f,1f,1f};
            table.setWidths(columnWidths2);

            PdfPCell cell2 = new PdfPCell(image1);
            //cell1.setBorderColor(BaseColor.BLUE);
            //cell1.setPaddingLeft(10);
            cell2.setBorderWidth(0);

            //cell2.setBorderColor(BaseColor.BLACK);
            //cell2.setBackgroundColor(BaseColor.LIGHT_GRAY);
            cell2.setHorizontalAlignment(Element.ALIGN_RIGHT);
            //cell2.setVerticalAlignment(Element.ALIGN_TOP);
            cell2.setColspan(2);
            table.addCell(cell2);
            if (bean.getSmcType() != null && bean.getSmcType().equalsIgnoreCase("Unregistered")) {
                smcType = "(UNREGISTERED)";
            } else {
                smcType = "(REGISTERED)";
            }
            Chunk c_msg = new Chunk("Silvassa Municipal Council ", fontEngH);
            Chunk c_msg_blank = new Chunk("\n", fontEngH_8_1);
            Chunk c_msg1 = new Chunk("flyoklk uxj ikfydk \n", fontHindi_1);
            Chunk c_msg_blank1 = new Chunk(" \n", fontEngH_8_1);
            Chunk c_msg2 = new Chunk("Property Tax Bill (2019-2020) \n", fontEngH_2k);
            Chunk c_msg_blank2 = new Chunk(" \n", fontEngH_8_1);
            Chunk c_msg3 = new Chunk("laifRr dj facy ¼2019&2020½ ", fontHindi_2);
            Chunk c_msg_blank4 = new Chunk("\n", fontEngH_8_1);
            Chunk c_msg4 = new Chunk("www.smcdnh.nic.in                    Notice u/s 141 ", fontEngH_2ky);

            Chunk c_msg5 = new Chunk("                                                                " + smcType, fontEngH_2ky_register);
            Phrase p_msg = new Phrase();
            p_msg.add(c_msg);
            p_msg.add(c_msg_blank);
            p_msg.add(c_msg1);
            p_msg.add(c_msg_blank1);
            p_msg.add(c_msg2);
            p_msg.add(c_msg_blank1);
            p_msg.add(c_msg3);
            p_msg.add(c_msg_blank1);
            p_msg.add(c_msg4);
            p_msg.add(c_msg_blank1);
            p_msg.add(c_msg5);

            Chunk c_owner = new Chunk("Owner Name: ", fontEngH_dd_2);
            Chunk c_owner_data = new Chunk("" + bean.getOwner_Father(), fontEngH_dd);
            Chunk c_msg_blank_owner = new Chunk("\n", fontEngH_8_1);
            if (bean.getHolderName() != null && bean.getHolderName().length() > 0) {
                if (bean.getHolderName().equalsIgnoreCase("null")) {
                    holdderName = "";
                } else {
                    holdderName = bean.getHolderName();
                }

            } else {
                holdderName = "";
            }

            Chunk c_holder = new Chunk("Holder Name: ", fontEngH_dd_2);
            Chunk c_holder_data = new Chunk("" + bean.getHolderName(), fontEngH_dd);
            Chunk c_msg_blank_holder = new Chunk("\n", fontEngH_8_1);

            Chunk c_address = new Chunk("Property Address: ", fontEngH_dd_2);
            Chunk c_address_data = new Chunk("" + bean.getProperty_old_smc_prop_tax_num() + "\n" + bean.getAddress(), fontEngH_dd);

            Chunk c_EasyCityCode = new Chunk("Easy City Code: ", fontEngH_dd_2);
            Chunk c_EasyCityCode_data = new Chunk("N/A", fontEngH_dd);
            Chunk c_msg_blank_EasyCityCode = new Chunk("\n", fontEngH_8_1);

            Phrase p_msg_owner = new Phrase();
            p_msg_owner.add(c_owner);
            p_msg_owner.add(c_owner_data);
            p_msg_owner.add(c_msg_blank_owner);
            p_msg_owner.add(c_holder);
            p_msg_owner.add(c_holder_data);
            p_msg_owner.add(c_msg_blank_holder);
            p_msg_owner.add(c_address);
            p_msg_owner.add(c_address_data);
            p_msg_owner.add(c_msg_blank_owner);

            p_msg_owner.add(c_EasyCityCode);
            p_msg_owner.add(c_EasyCityCode_data);
            p_msg_owner.add(c_msg_blank_EasyCityCode);

            PdfPTable inntertable = new PdfPTable(5);
            //inntertable.setWidths(columnWidths2);
            PdfPCell innercell1 = new PdfPCell();
            innercell1.setBorderWidth(0);
            innercell1.setPhrase(p_msg);
            //innercell1.setPhrase(new Phrase("Silvassa Municipal Council ", fontEngH));
            //innercell1.setHorizontalAlignment(Element.ALIGN_LEFT);
            //innercell1.setVerticalAlignment(Element.ALIGN_TOP);
            innercell1.setColspan(5);
            //innercell1.setRowspan(2);
            inntertable.addCell(innercell1);
            //table.addCell(inntertable);

            PdfPTable inntertableHindi = new PdfPTable(5);
            //inntertable.setWidths(columnWidths2);
            innercell1 = new PdfPCell();
            innercell1.setBorderWidth(0);
            innercell1.setPhrase(p_msg_owner);
            //innercell1.setHorizontalAlignment(Element.ALIGN_LEFT);
            //innercell1.setVerticalAlignment(Element.ALIGN_TOP);
            innercell1.setColspan(5);
            //innercell1.setRowspan(2);
            inntertableHindi.addCell(innercell1);

                   //table.addCell(inntertable1);
            //table.addCell(inntertable1);
            PdfPCell cell3 = new PdfPCell();
            //cell3.setBorderColor(BaseColor.BLACK);
            //cell1.setPaddingLeft(10);
            cell3.setBorderWidth(0);
            //cell3.setBackgroundColor(BaseColor.LIGHT_GRAY);
            //cell3.setPhrase(new Phrase("flyoklk uxj ifj’kn \n2018-2019",fontHindi_1 ));
            cell3.setHorizontalAlignment(Element.ALIGN_CENTER);
            cell3.setVerticalAlignment(Element.ALIGN_TOP);
            cell3.setColspan(5);

            //table.addCell(inntertable2);
            PdfPCell cell1 = new PdfPCell();
            //cell1.setBorderColor(BaseColor.BLUE);
            //cell1.setPaddingLeft(10);
            cell1.setBorderWidth(0);
                   // cell1.setBorderColor(BaseColor.BLACK);
            //cell1.setBackgroundColor(BaseColor.LIGHT_GRAY);

            //cell1.setPhrase(new Phrase("SILVASSA MUNICIPAL COUNCIL ",fontEngH ));
            cell1.setHorizontalAlignment(Element.ALIGN_LEFT);
            cell1.setVerticalAlignment(Element.ALIGN_TOP);
            cell1.setColspan(5);
            cell1.addElement(inntertable);
            //cell1.addElement(inntertableHindi);
            //cell1.addElement(inntertable1);
            // cell1.addElement(inntertableHindi1);
            //cell1.addElement(inntertableHindi2);
            //cell1.addElement(inntertable2);

            //cell1.setRowspan(2);
            table.addCell(cell1);

            PdfPCell cell14 = new PdfPCell();
            //cell1.setBorderColor(BaseColor.BLUE);
            //cell1.setPaddingLeft(10);
            cell14.setBorderWidth(0);
                   // cell1.setBorderColor(BaseColor.BLACK);
            //cell1.setBackgroundColor(BaseColor.LIGHT_GRAY);

            //cell1.setPhrase(new Phrase("SILVASSA MUNICIPAL COUNCIL ",fontEngH ));
            cell14.setHorizontalAlignment(Element.ALIGN_LEFT);
            cell14.setVerticalAlignment(Element.ALIGN_TOP);
            cell14.setColspan(5);
            cell14.addElement(inntertableHindi);
            //cell1.addElement(inntertable1);
            // cell1.addElement(inntertableHindi1);
            //cell1.addElement(inntertableHindi2);
            //cell1.addElement(inntertable2);

            //cell1.setRowspan(2);
            table.addCell(cell14);

            table.addCell(cell3);
            document.add(table);

            /*cell1 = new PdfPCell();
                    
             // cell1.setBorderColor(BaseColor.BLACK);
             //cell1.setBackgroundColor(BaseColor.LIGHT_GRAY);
             //cellr.setRowspan(2);
             cell1.setBorder(0);
             cell1.setPhrase(new Phrase("Property Tax Assessment Notice ",fontEngH_2 ));
             cell1.setHorizontalAlignment(Element.ALIGN_LEFT);
             cell1.setVerticalAlignment(Element.ALIGN_TOP);
             //cell1.setColspan(5);
             table.addCell(cell1);
                    
             cell1 = new PdfPCell();
                    
             // cell1.setBorderColor(BaseColor.BLACK);
             //cell1.setBackgroundColor(BaseColor.LIGHT_GRAY);
             //cellr.setRowspan(2);
             cell1.setBorder(0);
             cell1.setPhrase(new Phrase("2018-2019 ",fontEngH_2 ));
             cell1.setHorizontalAlignment(Element.ALIGN_LEFT);
             cell1.setVerticalAlignment(Element.ALIGN_TOP);
             //cell1.setColspan(5);
             table.addCell(cell1);*/
            //table.addCell(inntertable);
            //table.addCell(cellr);
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
            PdfPTable table8 = new PdfPTable(6);
            PdfPCell cell13 = new PdfPCell();
            //cell1.setBorderWidthColor(BaseColor.BLUE);
            //cell1.setPaddingLeft(10);
            cell13.setBorderWidth(0);
            cell13.setPhrase(new Phrase(" ", fontEngH_8_1));
            cell13.setHorizontalAlignment(Element.ALIGN_LEFT);
            cell13.setVerticalAlignment(Element.ALIGN_MIDDLE);
            cell13.setColspan(6);
            table8.addCell(cell13);

            document.add(table8);

            PdfPTable table1 = new PdfPTable(12);
            //table3.setWidths(columnWidths);
            PdfPCell cell5 = new PdfPCell();
            //cell1.setBorderColor(BaseColor.BLUE);
            //cell1.setPaddingLeft(10);
            cell5.setBorderWidth(1);
            Chunk j1 = new Chunk("As per Dadra and Nagar Haveli Municipal Council Regulation, 2004, Section 141(3), if a taxpayer pays the tax before due date, he/she shall be entitled to get a discount of 1% on the demand amount. After the said period, the taxpayer shall be liable to pay interest. This bill is being sent with prior approval of Chief Officer, Silvassa Municipal Council.", fontEngH_header);
            //Chunk j2=new Chunk(" www.smcdnh.nic.in ",fontEngH_4);
            //Chunk j3=new Chunk("“p dpÝed ‘u ky’pfZp ap¡d® Ädp Lfu iLip¡. Ap ky’pfZp ap¡d",fontGujrati);
            // Chunk j4=new Chunk(" www.smcdnh.nic.in ",fontEngH_4);
            //Chunk j5=new Chunk("h¡bkpCV ”f ”Z D”gå’ R¡. k»”qÑ“u Ahgp¡L“ k»¿epdp sdpfp Öpfp hp»’p A“¡ ky’pfZp ap¡d® 30 qvhk “u A»vf Ädp “lu » Lfhpdp» Aph¡ sp¡ ”°p¡”Vu® kh¡® “u dpqlsu kpQu dp“hdp» Aphi¡. ",fontGujrati);

            Phrase pj = new Phrase();
            pj.add(j1);
                    //pj.add(j2);
            // pj.add(j3);
            //pj.add(j4);
            //pj.add(j5);

            // cell1.setBorderColor(BaseColor.BLACK);
            //cell1.setBackgroundColor(BaseColor.LIGHT_GRAY);
            cell5.setPhrase(pj);
            cell5.setBorderWidth(1);
            //+cell5.setPhrase(new Phrase("vpvfp A“¡ “Nf lh¡gu “Nf ”pqgLp Aq’q“ed 2004 “u ’pfp 111 A“¡ ’pfp 115 D”’pfp (1) A“¡ (2) “p A“yk»’p“¡ k¡ghpk “Nf ”pqgLp dp» Aph¡g kdõs ”°p¡”Vu® “y» kh¡® ”wZ® ‘e¡g R¡. Mpk sdp¡“¡ qh“»su R¡ L¡ Ap k»”qÑ “u “p¡qVk Ýep“ ”wh®L hp»Qu Ap k»”qÑ “u dpqlsu “p¡qVk dp» vip®h¡g kwQ“p dyÄb Lp¡C”Z ”°Lpf “p¡ hp»’p¡ A‘hp cwg dm¡ sp¡ ky’pfp¡ Lfhp sd¡ ky’pfZp ap¡d® cfu “¡ 30 qvhk “u A»vf “Nf ”pqgLp“u Ap¡qak A‘hp Ap¡“ gpC“ smcdnh.nic.in “p dpÝed ‘u ky’pfZp ap¡d® Ädp Lfu iLip¡. Ap ky’pfZp ap¡d® Map My India.in h¡bkpCV ”f ”Z D”gå’ R¡. k»”qÑ“u Ahgp¡L“ k»¿epdp sdpfp Öpfp hp»’p A“¡ ky’pfZp ap¡d® 30 qvhk “u A»vf Ädp “lu » Lfhpdp» Aph¡ sp¡ ”°p¡”Vu® kh¡® “u dpqlsu kpQu dp“hdp» Aphi¡. ", fontGujrati));
            cell5.setPadding(8);
            cell5.setHorizontalAlignment(Element.ALIGN_JUSTIFIED);
            cell5.setVerticalAlignment(Element.ALIGN_TOP);
            cell5.setBorderColor(BaseColor.LIGHT_GRAY);
            cell5.setBorder(Rectangle.RIGHT);
            cell5.setColspan(6);
            table1.addCell(cell5);

            PdfPCell cell6 = new PdfPCell();
            //cell1.setBorderColor(BaseColor.BLUE);
            //cell1.setPaddingLeft(10);
            cell6.setBorderWidth(1);

            Chunk c11 = new Chunk("nknjk ,oa uxj gosyh uxj ikfydk vf/kfu;e 2004 dhs /kkjk 141 dhs mi/kkjk ¼3½ ds varxZr ;fn dksbZ O;fä facy rkjh[k  ds iaæg fnuksa ds Hkhrj jkf'k dk Hkqxrku djrk gS] rks mls fcy ds ekax jkf'k ij 1 çfr'kr dh NwV feysxhA ;fn og fu;fer rkjh[k ij Hkqxrku ugha djrk gS rks og O;fä C;kt nsus gsrq ck/; gksxkA ;g fcy eq[; vf/kdkjh flyoklk uxj ikfydk ds iwokZuqefr ls Hkstk tk jgk gSA", fontHindi_rule_header);

            //  Chunk c11=new Chunk("nknjk ,oa uxj gosyh uxj ikfydk vf/kfu;e 2004 dhs /kkjk 141 dhs mi/kkjk ¼3½ ds varxZr ;fn dksbZ O;fä facy rkjh[k  ds iaæg fnuksa ds Hkhrj jkf'k dk Hkqxrku djrk gS] rks uxj ikfydk iwjs ;ksx ij 1 çfr'kr dh NwV nsxkA ;fn og fu;fer rkjh[k ij Hkqdrku ugha djrk gS rRi'pkr ns; lEifÙk dj ds Åij og O;fä C;kt nsus gsrq ck/; gksxkA " +
            //";g fcy phQ v‚fQlj flyoklk uxj ikfydk ds iwokZuqefr ls Hkstk tk  jgk gSA ",fontHindi_rule_header);
            //Chunk c2=new Chunk(" www.smcdnh.nic.in ",fontEngH_4);
            //Chunk c3=new Chunk("ds ek/;e ls Hkh tek dj ldrs gSA ;g la'kks/ku QkeZ uxj ikfydk dh osclkbV",fontHindi_rule);
            //Chunk c4=new Chunk(" www.smcdnh.nic.in ",fontEngH_4);
            //Chunk c5=new Chunk("ij Hkh miyC/k gSA laifRr dj vkadyu dk ;g MkVk] tks bl uksfVl esa fn;k x;k gSA vkids }kjk vkifRr ;k vuqj¨/k 30 fnu ds Hkhrj uk vkus ij lgh eku fy;k tk,xkA",fontHindi_rule);
            Phrase p1 = new Phrase();
            p1.add(c11);
            //p1.add(c2);
            //p1.add(c3);
            //p1.add(c4);
            // p1.add(c5);
            // cell1.setBorderColor(BaseColor.BLACK);
            //cell1.setBackgroundColor(BaseColor.LIGHT_GRAY);
            cell6.setPhrase(p1);
            cell6.setBorderWidth(0);
            //cell6.setPhrase(new Phrase("nknjk ,oa uxj gosyh uxj ikfydk vf/kfu;e 2004 dhs /kkjk 111rFkk /kkjk 115 dhs mi /kkjk ¼1½ ,oa ¼2½ ds varxZr flyoklk uxj ikfydk esa leLr izkWaiVhZ losZ dk dke iw.kZ gks pqdk gSA vr% vki lc ls vuqjks/k gS fd bl laifRr dj vkadyu uksfVl dks /;kuiwoZd i<s+ A bl laifRr dj vkadyu uksfVl esa n'kkZbZ xbZ lwpuk esa fdlh Òh izdkj dh vkifRr@ =qfV ik, tkus ij lq/kkj gsrw vki la'kks/ku QkeZ Hkj dj 30 fnu ds Hkhrj uxj ikfydk ds n¶rj esa rFkk vkWauykbu               ds ek/;e ls Hkh tek djk ldrs gSA ;g la'kks/ku QkeZ uxj ikfydk dh osclkbV            ij Hkh miyC/k gSA laifRr dj vkadyu dk ;g MkVk] tks bl uksfVl esa fn;k x;k gSA vkids }kjk vkifRr ;k vuqj¨/k 30 fnu ds Hkhrj uk vkus ij lgh eku fy;k tk,xkA  ", fontHindi_rule));
            cell6.setHorizontalAlignment(Element.ALIGN_JUSTIFIED);
            cell6.setVerticalAlignment(Element.ALIGN_TOP);
            cell6.setPadding(8);
            cell6.setColspan(6);
            cell6.setBorderColor(BaseColor.LIGHT_GRAY);
            table1.addCell(cell6);

            document.add(table1);

            PdfPTable table2 = new PdfPTable(6);
            PdfPCell cell7 = new PdfPCell();
            //cell1.setBorderWidthColor(BaseColor.BLUE);
            //cell1.setPaddingLeft(10);
            cell7.setBorderWidth(0);
            cell7.setPhrase(new Phrase(" ", fontEngH_8_1));
            cell7.setHorizontalAlignment(Element.ALIGN_CENTER);
            cell7.setVerticalAlignment(Element.ALIGN_MIDDLE);
            cell7.setColspan(6);
            table2.addCell(cell7);
            document.add(table2);

            //PdfPTable table3 = new PdfPTable(6); 
//                    PdfPCell cell8_1 = new PdfPCell();
//                    //cell1.setBorderWidthColor(BaseColor.BLUE);
//                    //cell1.setPaddingLeft(10);
//                    cell8_1.setBorderWidth(0);
//                    cell8_1.setPhrase(new Phrase("",fontEngH));
//                    cell8_1.setHorizontalAlignment(Element.ALIGN_CENTER);
//                    //cell8.setHorizontalAlignment(Element.ALIGN_JUSTIFIED);
//                    cell8_1.setColspan(1);
//                    table3.addCell(cell8_1); 
//                    PdfPTable table4 = new PdfPTable(6); 
//                    PdfPCell cell9 = new PdfPCell();
//                    //cell1.setBorderWidthColor(BaseColor.BLUE);
//                    //cell1.setPaddingLeft(10);
//                    cell9.setBorderWidth(0);
//                    cell9.setPhrase(new Phrase("",fontEngH));
//                    cell9.setHorizontalAlignment(Element.ALIGN_CENTER);
//                    cell9.setVerticalAlignment(Element.ALIGN_MIDDLE);
//                    cell9.setColspan(6);
//                    table4.addCell(cell9); 
//                    document.add(table4);
//                    PdfPTable table8_1 = new PdfPTable(6); 
//                    PdfPCell cell13_1 = new PdfPCell();
//                    cell13_1.setBorderColor(BaseColor.LIGHT_GRAY);
//                    //cell1.setBorderWidthColor(BaseColor.BLUE);
//                    //cell1.setPaddingLeft(10);
//                    cell13_1.setBorderWidth(1);
//                    cell13_1.setPhrase(new Phrase("According to The Dadra And Nagar Haveli Municipal Council Regulation ,2004, Section 141(3), if a person pays the sum within the fifteen days of period of service date, the municipal will given 1% rebate on whole sum. If he doesn’t pay the sum within the period 1% rebate will not be given to the person. ",fontEngH));
//                    cell13_1.setHorizontalAlignment(Element.ALIGN_JUSTIFIED);
//                    cell13_1.setVerticalAlignment(Element.ALIGN_MIDDLE);
//                    cell13_1.setColspan(6);
//                    table8_1.addCell(cell13_1); 
//                    
//                    document.add(table8_1);
            PdfPTable table34 = new PdfPTable(6);
            PdfPCell cell137 = new PdfPCell();
            cell137.setBorderColor(BaseColor.LIGHT_GRAY);
            //cell137.setBorderColor(BaseColor.BLUE);
            //cell1.setPaddingLeft(10);
            cell137.setBackgroundColor(BaseColor.LIGHT_GRAY);
            cell137.setBorderWidth(1);

            cell137.setPhrase(new Phrase("Tax Details ", fontEngH));
            cell137.setHorizontalAlignment(Element.ALIGN_CENTER);
            cell137.setPadding(3);
            cell137.setUseAscender(true);
            cell137.setVerticalAlignment(Element.ALIGN_TOP);
            cell137.setColspan(6);
            table34.addCell(cell137);
            document.add(table34);

            Chunk c_pid = new Chunk("Property ID/", fontEngH_dd_1);
            Chunk c_pid_hindi = new Chunk("lEifÙk uacj ", fontHindi_pid);
            Chunk c_pid_column = new Chunk(":", fontEngH_dd_1);
            Chunk c_pid_1 = new Chunk(" " + bean.getUniqueId(), fontEngH_dd);
            //Chunk c2=new Chunk(" www.smcdnh.nic.in ",fontEngH_4);
            //Chunk c3=new Chunk("ds ek/;e ls Hkh tek dj ldrs gSA ;g la'kks/ku QkeZ uxj ikfydk dh osclkbV",fontHindi_rule);
            //Chunk c4=new Chunk(" www.smcdnh.nic.in ",fontEngH_4);
            //Chunk c5=new Chunk("ij Hkh miyC/k gSA laifRr dj vkadyu dk ;g MkVk] tks bl uksfVl esa fn;k x;k gSA vkids }kjk vkifRr ;k vuqj¨/k 30 fnu ds Hkhrj uk vkus ij lgh eku fy;k tk,xkA",fontHindi_rule);
            Phrase p_pid = new Phrase();
            p_pid.add(c_pid);
            p_pid.add(c_pid_hindi);
            p_pid.add(c_pid_column);
            p_pid.add(c_pid_1);

            Chunk c_bill = new Chunk("Bill No./ ", fontEngH_dd_1);
            Chunk c_bill_hindi = new Chunk("fcy uacj", fontHindi_pid);
            Chunk c_bill_column = new Chunk(":", fontEngH_dd_1);
            Chunk c_bill_1 = new Chunk(" " + bean.getPrivateNotceNo(), fontEngH_dd);
            Phrase p_bill = new Phrase();
            p_bill.add(c_bill);
            p_bill.add(c_bill_hindi);
            p_bill.add(c_bill_column);
            p_bill.add(c_bill_1);

            Chunk c_service = new Chunk("Bill Date/ ", fontEngH_dd_1);
            Chunk c_service_hindi = new Chunk("fcy rkjh[k", fontHindi_pid);
            Chunk c_service_column = new Chunk(":", fontEngH_dd_1);
            Chunk c_service_1 = new Chunk(" " + bean.getServiceDate(), fontEngH_dd);
            Phrase p_service = new Phrase();

            p_service.add(c_service);
            p_service.add(c_service_hindi);
            p_service.add(c_service_column);
            p_service.add(c_service_1);

            Chunk c_due = new Chunk("Due Date /", fontEngH_dd_1);
            Chunk c_due_hindi = new Chunk("ns; rkjh[k", fontHindi_pid);
            Chunk c_due_column = new Chunk(" : ", fontEngH_dd_1);
            Chunk c_due_1 = new Chunk("" + bean.getDueDate(), fontEngH_dd);
            Phrase p_due = new Phrase();
            p_due.add(c_due);
            p_due.add(c_due_hindi);
            p_due.add(c_due_column);
            p_due.add(c_due_1);
            Phrase p_water = new Phrase();
            Phrase p_sewerage = new Phrase();
            Phrase p_professional = new Phrase();
            Phrase p_Sanitation = new Phrase();
            if (bean.getWaterTax() != null && Integer.parseInt(bean.getWaterTax()) > 0) {
                Chunk c_water = new Chunk("Water Tax /", fontEngH_dd_1);
                Chunk c_water_hindi = new Chunk("ty dj", fontHindi_pid);
                Chunk c_water_column = new Chunk(" :", fontEngH_dd_1);
                Chunk c_water_1 = new Chunk(" " + bean.getWaterTax(), fontEngH_dd);

                p_water.add(c_water);
                p_water.add(c_water_hindi);
                p_water.add(c_water_column);
                p_water.add(c_water_1);
            } else {
                Chunk c_water = new Chunk("Water Tax /", fontEngH_dd_1);
                Chunk c_water_hindi = new Chunk("ty dj", fontHindi_pid);
                Chunk c_water_column = new Chunk(" :", fontEngH_dd_1);
                Chunk c_water_1 = new Chunk(" N/A", fontEngH_dd);
                //Phrase p_water=new Phrase();
                p_water.add(c_water);
                p_water.add(c_water_hindi);
                p_water.add(c_water_column);
                p_water.add(c_water_1);
            }

            if (bean.getSewerageTax() != null && Integer.parseInt(bean.getSewerageTax()) > 0) {
                Chunk c_sewerage = new Chunk("Sewerage Tax / ", fontEngH_dd_1);
                Chunk c_sewerage_hindi = new Chunk("lhojst dj", fontHindi_pid);
                Chunk c_sewerage_column = new Chunk(" :", fontEngH_dd_1);
                Chunk c_sewerage_1 = new Chunk(" " + bean.getSewerageTax(), fontEngH_dd);

                p_sewerage.add(c_sewerage);
                p_sewerage.add(c_sewerage_hindi);
                p_sewerage.add(c_sewerage_column);
                p_sewerage.add(c_sewerage_1);

            } else {
                Chunk c_sewerage = new Chunk("Sewerage Tax / ", fontEngH_dd_1);
                Chunk c_sewerage_hindi = new Chunk("lhojst dj", fontHindi_pid);
                Chunk c_sewerage_column = new Chunk(" :", fontEngH_dd_1);
                Chunk c_sewerage_1 = new Chunk(" N/A", fontEngH_dd);
                //Phrase p_sewerage=new Phrase();
                p_sewerage.add(c_sewerage);
                p_sewerage.add(c_sewerage_hindi);
                p_sewerage.add(c_sewerage_column);
                p_sewerage.add(c_sewerage_1);
            }

            if (bean.getProfessionalTax() != null && Integer.parseInt(bean.getProfessionalTax()) > 0) {
                Chunk c_Professional = new Chunk("Professional Tax / ", fontEngH_dd_1);
                Chunk c_Professional_hindi = new Chunk("çksQs'kuy dj", fontHindi_pid);
                Chunk c_Professional_column = new Chunk(" :", fontEngH_dd_1);
                Chunk c_Professional_1 = new Chunk(" " + bean.getProfessionalTax(), fontEngH_dd);

                p_professional.add(c_Professional);
                p_professional.add(c_Professional_hindi);
                p_professional.add(c_Professional_column);
                p_professional.add(c_Professional_1);

            } else {
                Chunk c_Professional = new Chunk("Professional Tax / ", fontEngH_dd_1);
                Chunk c_Professional_hindi = new Chunk("çksQs'kuy dj", fontHindi_pid);
                Chunk c_Professional_column = new Chunk(" :", fontEngH_dd_1);
                Chunk c_Professional_1 = new Chunk(" N/A", fontEngH_dd);
                //Phrase p_professional=new Phrase();
                p_professional.add(c_Professional);
                p_professional.add(c_Professional_hindi);
                p_professional.add(c_Professional_column);
                p_professional.add(c_Professional_1);

            }

            if (bean.getSanitationTax() != null && Integer.parseInt(bean.getSanitationTax()) > 0) {
                Chunk c_Sanitation = new Chunk("Sanitation /", fontEngH_dd_1);
                Chunk c_Sanitation_hindi = new Chunk(" LoPNrk dj", fontHindi_pid);
                Chunk c_Sanitation_column = new Chunk(" :", fontEngH_dd_1);
                Chunk c_Sanitation_1 = new Chunk(" " + bean.getSanitationTax(), fontEngH_dd);

                p_Sanitation.add(c_Sanitation);
                p_Sanitation.add(c_Sanitation_hindi);
                p_Sanitation.add(c_Sanitation_column);
                p_Sanitation.add(c_Sanitation_1);
            } else {
                Chunk c_Sanitation = new Chunk("Sanitation /", fontEngH_dd_1);
                Chunk c_Sanitation_hindi = new Chunk(" LoPNrk dj", fontHindi_pid);
                Chunk c_Sanitation_column = new Chunk(" :", fontEngH_dd_1);
                Chunk c_Sanitation_1 = new Chunk(" N/A", fontEngH_dd);
                //Phrase p_Sanitation=new Phrase();
                p_Sanitation.add(c_Sanitation);
                p_Sanitation.add(c_Sanitation_hindi);
                p_Sanitation.add(c_Sanitation_column);
                p_Sanitation.add(c_Sanitation_1);
            }

            Chunk c_arrear = new Chunk("Arrear Amount/ ", fontEngH_dd_1);
            Chunk c_arrear_hindi = new Chunk("cdk;k jkf'k", fontHindi_pid);
            Chunk c_arrear_column = new Chunk(" : Rs.", fontEngH_dd_1);
            Chunk c_arrear_1 = new Chunk(" " + bean.getArrearAmount(), fontEngH_dd);
            Phrase p_arrear = new Phrase();
            p_arrear.add(c_arrear);
            p_arrear.add(c_arrear_hindi);
            p_arrear.add(c_arrear_column);
            p_arrear.add(c_arrear_1);

            Chunk c_demand = new Chunk("Demand Amount/", fontEngH_dd_1);
            Chunk c_demand_1 = new Chunk("" + bean.getTax(), fontEngH_dd);
            Chunk c_demand_hindi = new Chunk("ekax jkf'k", fontHindi_pid);
            Chunk c_demand_column = new Chunk(":(2019-2020): Rs. ", fontEngH_dd_1);

            Phrase p_demand = new Phrase();
            p_demand.add(c_demand);
            p_demand.add(c_demand_hindi);
            p_demand.add(c_demand_column);
            p_demand.add(c_demand_1);

            ///payavlbe avnish
            Chunk c_payable = new Chunk("Payable Amount/ ", fontEngH_dd_1);
            Chunk c_payable_hindi = new Chunk("Hkqxrku ;ksX; jkf'k", fontHindi_pid);
            Chunk c_payable_column = new Chunk(": Rs. ", fontEngH_dd_1);
            Chunk c_payable_1 = new Chunk("" + bean.getPayableAmount(), fontEngH_dd);
            Phrase p_payable = new Phrase();
            p_payable.add(c_payable);
            p_payable.add(c_payable_hindi);
            p_payable.add(c_payable_column);
            p_payable.add(c_payable_1);

            //any other
            Chunk any_other = new Chunk("Any Other Amount/ ", fontEngH_dd_1);
            Chunk any_other_hindi = new Chunk("vU; Hkqxrku jkf'k", fontHindi_pid);
            Chunk any_other_column = new Chunk(": Rs. ", fontEngH_dd_1);
            //Chunk any_other_1=new Chunk(""+bean.getPayableAmount(),fontEngH_dd);
            Phrase any_other_payable = new Phrase();
            any_other_payable.add(any_other);
            any_other_payable.add(any_other_hindi);
            any_other_payable.add(any_other_column);
                    //any_other_payable.add(any_other_1);

            //int rb=(int)Math.round(Double.parseDouble(bean.getPayableAmount())*.99);
            int rb = (int) Math.round(Double.parseDouble(bean.getTax()) * .99);
            int afterRebate = rb + (int) Double.parseDouble(bean.getArrearAmount());

            Chunk c_rebateAmt = new Chunk("Amount payable after rebate /", fontEngH_dd_1);
            Chunk c_rebateAmt_hindi = new Chunk("NwV ds ckn ns; jkf'k", fontHindi_pid);
            Chunk c_rebateAmt_column = new Chunk(": Rs.", fontEngH_dd_1);

            Chunk c_rebateAmt_1 = new Chunk("(1% rebate on demand amount) ", fontEngH_dd);
            Chunk c_rebateAmt_2 = new Chunk(" Rs. " + afterRebate, fontEngH_dd);

            Phrase p_rebateAmt = new Phrase();
            p_rebateAmt.add(c_rebateAmt);
            p_rebateAmt.add(c_rebateAmt_hindi);
            p_rebateAmt.add(c_rebateAmt_1);
            p_rebateAmt.add(c_rebateAmt_2);

            Chunk c_smc_house = new Chunk("SMC House No./ ", fontEngH_dd_1);
            Chunk c_smc_house_hindi = new Chunk("gkml ua", fontHindi_pid);
            Chunk c_smc_house_column = new Chunk(" :", fontEngH_dd_1);
            Chunk c_smc_house_1 = new Chunk("  " + bean.getProperty_old_smc_prop_tax_num(), fontEngH_dd);
            Phrase p_smc_house = new Phrase();
            p_smc_house.add(c_smc_house);
            p_smc_house.add(c_smc_house_hindi);
            p_smc_house.add(c_smc_house_column);

            p_smc_house.add(c_smc_house_1);

            Chunk c_ward = new Chunk("Ward No./ ", fontEngH_dd_1);
            Chunk c_ward_hindi = new Chunk("okMZ ua", fontHindi_pid);
            Chunk c_ward_column = new Chunk(" :", fontEngH_dd_1);
            Chunk c_ward_house_1 = new Chunk("  " + bean.getWard(), fontEngH_dd);
            Phrase p_ward = new Phrase();
            p_ward.add(c_ward);
            p_ward.add(c_ward_hindi);
            p_ward.add(c_ward_column);
            p_ward.add(c_ward_house_1);

            PdfPTable table9 = new PdfPTable(12);
            table9.setWidths(columnWidths3);
            PdfPCell cell16 = new PdfPCell();
            cell16.setBorderColor(BaseColor.LIGHT_GRAY);
            //cell1.setPaddingLeft(10);
            cell16.setBorderWidth(1);
            cell16.setPhrase(new Phrase(p_pid));
            //cell16.setPhrase(new Phrase("Property ID:  "+bean.getUniqueId(),fontEngH_dd));
            cell16.setHorizontalAlignment(Element.ALIGN_LEFT);
            cell16.setUseAscender(true);
            cell16.setUseDescender(true);
            cell16.setVerticalAlignment(Element.ALIGN_TOP);
            cell16.setColspan(3);
            table9.addCell(cell16);

            PdfPCell cell16_3 = new PdfPCell();
            cell16_3.setBorderColor(BaseColor.LIGHT_GRAY);
            //cell1.setPaddingLeft(10);
            cell16_3.setBorderWidth(1);
            cell16_3.setPhrase(new Phrase(p_bill));
            //cell16_3.setPhrase(new Phrase("Bill No.:  "+bean.getPrivateNotceNo(),fontEngH_dd));
            cell16_3.setHorizontalAlignment(Element.ALIGN_LEFT);
            cell16_3.setUseAscender(true);
            cell16_3.setUseDescender(true);
            cell16_3.setVerticalAlignment(Element.ALIGN_TOP);
            cell16_3.setColspan(3);
            table9.addCell(cell16_3);

            PdfPCell cell15 = new PdfPCell();
            //cell1.setBorderWidthColor(BaseColor.BLUE);
            //cell1.setPaddingLeft(10);
            cell15.setBorderColor(BaseColor.LIGHT_GRAY);
            cell15.setBorderWidth(1);
            cell15.setPhrase(p_service);
            //cell15.setPhrase(new Phrase("Service Date:  "+bean.getServiceDate(),fontEngH_dd));
            cell15.setHorizontalAlignment(Element.ALIGN_LEFT);
            cell15.setUseAscender(true);
            cell15.setUseDescender(true);
            cell15.setVerticalAlignment(Element.ALIGN_TOP);
            cell15.setColspan(3);
            table9.addCell(cell15);

            PdfPCell cell15_1 = new PdfPCell();
            //cell1.setBorderWidthColor(BaseColor.BLUE);
            //cell1.setPaddingLeft(10);
            cell15_1.setBorderColor(BaseColor.LIGHT_GRAY);
            cell15_1.setBorderWidth(1);
            cell15_1.setPhrase(new Phrase(p_due));
            //cell15_1.setPhrase(new Phrase("Due Date : "+bean.getDueDate(),fontEngH_dd));
            cell15_1.setHorizontalAlignment(Element.ALIGN_LEFT);
            cell15_1.setUseAscender(true);
            cell15_1.setUseDescender(true);
            cell15_1.setVerticalAlignment(Element.ALIGN_TOP);
            cell15_1.setColspan(3);
            table9.addCell(cell15_1);

            document.add(table9);

            PdfPTable table10 = new PdfPTable(12);
            table10.setWidths(columnWidths3);
            PdfPCell cell17 = new PdfPCell();
            cell17.setBorderColor(BaseColor.LIGHT_GRAY);
            //cell1.setBorderWidthColor(BaseColor.BLUE);
            //cell1.setPaddingLeft(10);
            cell17.setBorderWidth(1);
            cell17.setPhrase(p_water);
            cell17.setHorizontalAlignment(Element.ALIGN_LEFT);
            cell17.setUseAscender(true);
            cell17.setUseDescender(true);
            cell17.setVerticalAlignment(Element.ALIGN_TOP);
            //cell8.setHorizontalAlignment(Element.ALIGN_JUSTIFIED);
            cell17.setColspan(6);
            table10.addCell(cell17);

            PdfPCell cell17_1 = new PdfPCell();
            cell17_1.setBorderColor(BaseColor.LIGHT_GRAY);
            //cell1.setBorderWidthColor(BaseColor.BLUE);
            //cell1.setPaddingLeft(10);
            cell17_1.setBorderWidth(1);
            cell17_1.setPhrase(p_sewerage);
            cell17_1.setHorizontalAlignment(Element.ALIGN_LEFT);
            cell17_1.setUseAscender(true);
            cell17_1.setUseDescender(true);
            cell17_1.setVerticalAlignment(Element.ALIGN_TOP);
            //cell8.setHorizontalAlignment(Element.ALIGN_JUSTIFIED);
            cell17_1.setColspan(6);
            table10.addCell(cell17_1);
            document.add(table10);

            PdfPTable table10_1 = new PdfPTable(12);
            table10_1.setWidths(columnWidths3);
            PdfPCell cell18 = new PdfPCell();
            cell18.setBorderColor(BaseColor.LIGHT_GRAY);
            //cell1.setBorderWidthColor(BaseColor.BLUE);
            //cell1.setPaddingLeft(10);
            cell18.setBorderWidth(1);
            cell18.setPhrase(p_professional);
            cell18.setHorizontalAlignment(Element.ALIGN_LEFT);
            //cell8.setHorizontalAlignment(Element.ALIGN_JUSTIFIED);
            cell18.setUseAscender(true);
            cell18.setUseDescender(true);
            cell18.setVerticalAlignment(Element.ALIGN_TOP);
            cell18.setColspan(6);
            table10_1.addCell(cell18);

            PdfPCell cell18_1 = new PdfPCell();
            cell18_1.setBorderColor(BaseColor.LIGHT_GRAY);
            //cell1.setBorderWidthColor(BaseColor.BLUE);
            //cell1.setPaddingLeft(10);
            cell18_1.setBorderWidth(1);
            cell18_1.setPhrase(new Phrase(p_Sanitation));
            cell18_1.setHorizontalAlignment(Element.ALIGN_LEFT);
            //cell8.setHorizontalAlignment(Element.ALIGN_JUSTIFIED);
            cell18_1.setUseAscender(true);
            cell18_1.setUseDescender(true);
            cell18_1.setVerticalAlignment(Element.ALIGN_TOP);
            cell18_1.setColspan(6);
            table10_1.addCell(cell18_1);
            document.add(table10_1);

            PdfPTable table11 = new PdfPTable(12);
            table11.setWidths(columnWidths4);
            PdfPCell cell19 = new PdfPCell();
            cell19.setBorderColor(BaseColor.LIGHT_GRAY);
            //cell1.setBorderWidthColor(BaseColor.BLUE);
            //cell1.setPaddingLeft(10);
            cell19.setBorderWidth(1);
            cell19.setPhrase(p_arrear);
            //cell19.setPhrase(new Phrase("Arrear Amount: Rs. "+bean.getArrearAmount(),fontEngH_dd));
            cell19.setHorizontalAlignment(Element.ALIGN_LEFT);
            //cell8.setHorizontalAlignment(Element.ALIGN_JUSTIFIED);
            cell19.setUseAscender(true);
            cell19.setUseDescender(true);
            cell19.setVerticalAlignment(Element.ALIGN_MIDDLE);
            cell19.setColspan(4);
            table11.addCell(cell19);

            PdfPCell cell20_1 = new PdfPCell();
            cell20_1.setBorderColor(BaseColor.LIGHT_GRAY);
            //cell1.setBorderWidthColor(BaseColor.BLUE);
            //cell1.setPaddingLeft(10);
            cell20_1.setBorderWidth(1);
            cell20_1.setPhrase(p_demand);
            //cell20_1.setPhrase(new Phrase("Demand Amount (2018-2019): Rs. "+bean.getTax(),fontEngH_dd));
            cell20_1.setHorizontalAlignment(Element.ALIGN_LEFT);
            //cell8.setHorizontalAlignment(Element.ALIGN_JUSTIFIED);
            cell20_1.setUseAscender(true);
            cell20_1.setUseDescender(true);
            cell20_1.setVerticalAlignment(Element.ALIGN_TOP);
            cell20_1.setColspan(4);
            table11.addCell(cell20_1);

            PdfPCell cell19_1_4 = new PdfPCell();
            cell19_1_4.setBorderColor(BaseColor.LIGHT_GRAY);
            //cell1.setBorderWidthColor(BaseColor.BLUE);
            //cell1.setPaddingLeft(10);
            cell19_1_4.setBorderWidth(1);

            //cell19_1_4.setPhrase(p_payable);///acomment by avnish
            cell19_1_4.setPhrase(any_other_payable);

            //cell19_1_4.setPhrase(new Phrase("Payable Amount:  Rs. "+bean.getPayableAmount(),fontEngH_dd));
            cell19_1_4.setHorizontalAlignment(Element.ALIGN_LEFT);
            //cell8.setHorizontalAlignment(Element.ALIGN_JUSTIFIED);
            cell19_1_4.setUseAscender(true);
            cell19_1_4.setUseDescender(true);
            cell19_1_4.setVerticalAlignment(Element.ALIGN_TOP);
            cell19_1_4.setColspan(4);
            table11.addCell(cell19_1_4);
            document.add(table11);

            //int rb=(int)Math.round(Double.parseDouble(bean.getPayableAmount())*.99);
            PdfPTable table11_2_4_5 = new PdfPTable(12);
            table11_2_4_5.setWidths(columnWidths3);
            PdfPCell cell19_1_4_5 = new PdfPCell();
            cell19_1_4_5.setBorderColor(BaseColor.LIGHT_GRAY);
            //cell1.setBorderWidthColor(BaseColor.BLUE);
            //cell1.setPaddingLeft(10);
            cell19_1_4_5.setBorderWidth(1);
            cell19_1_4_5.setPhrase(p_rebateAmt);
            //cell19_1_4_5.setPhrase(new Phrase("After Rebate Payable Amount (1% rebate on payable amount)",fontEngH_dd));
            cell19_1_4_5.setHorizontalAlignment(Element.ALIGN_LEFT);
            //cell8.setHorizontalAlignment(Element.ALIGN_JUSTIFIED);
            cell19_1_4_5.setUseAscender(true);
            cell19_1_4_5.setUseDescender(true);
            cell19_1_4_5.setVerticalAlignment(Element.ALIGN_TOP);
            cell19_1_4_5.setColspan(6);
            table11_2_4_5.addCell(cell19_1_4_5);

            PdfPCell cell20_1_4_5_1 = new PdfPCell();
            cell20_1_4_5_1.setBorderColor(BaseColor.LIGHT_GRAY);
            //cell1.setBorderWidthColor(BaseColor.BLUE);
            //cell1.setPaddingLeft(10);
            cell20_1_4_5_1.setBorderWidth(1);
            cell20_1_4_5_1.setPhrase(p_payable);
            //cell20_1_4_5_1.setPhrase(new Phrase(" ",fontEngH_dd));
            cell20_1_4_5_1.setHorizontalAlignment(Element.ALIGN_LEFT);
            //cell8.setHorizontalAlignment(Element.ALIGN_JUSTIFIED);
            cell20_1_4_5_1.setVerticalAlignment(Element.ALIGN_TOP);
            cell20_1_4_5_1.setColspan(6);
            table11_2_4_5.addCell(cell20_1_4_5_1);
            document.add(table11_2_4_5);

            // for smc house and ward
            PdfPTable table11_2_4_5_6 = new PdfPTable(12);
            table11_2_4_5_6.setWidths(columnWidths3);
            PdfPCell cell19_1_4_5_6 = new PdfPCell();
            cell19_1_4_5_6.setBorderColor(BaseColor.LIGHT_GRAY);
            //cell1.setBorderWidthColor(BaseColor.BLUE);
            //cell1.setPaddingLeft(10);
            cell19_1_4_5_6.setBorderWidth(1);
            cell19_1_4_5_6.setPhrase(p_smc_house);
            //cell19_1_4_5.setPhrase(new Phrase("After Rebate Payable Amount (1% rebate on payable amount)",fontEngH_dd));
            cell19_1_4_5_6.setHorizontalAlignment(Element.ALIGN_LEFT);
            //cell8.setHorizontalAlignment(Element.ALIGN_JUSTIFIED);
            cell19_1_4_5_6.setUseAscender(true);
            cell19_1_4_5_6.setUseDescender(true);
            cell19_1_4_5_6.setVerticalAlignment(Element.ALIGN_TOP);
            cell19_1_4_5_6.setColspan(6);
            table11_2_4_5_6.addCell(cell19_1_4_5_6);

            PdfPCell cell20_1_4_5_1_3 = new PdfPCell();
            cell20_1_4_5_1_3.setBorderColor(BaseColor.LIGHT_GRAY);
            //cell1.setBorderWidthColor(BaseColor.BLUE);
            //cell1.setPaddingLeft(10);
            cell20_1_4_5_1_3.setBorderWidth(1);
            cell20_1_4_5_1_3.setPhrase(p_ward);
            //cell20_1_4_5_1.setPhrase(new Phrase(" ",fontEngH_dd));
            cell20_1_4_5_1_3.setHorizontalAlignment(Element.ALIGN_LEFT);
            //cell8.setHorizontalAlignment(Element.ALIGN_JUSTIFIED);
            cell20_1_4_5_1_3.setUseAscender(true);
            cell20_1_4_5_1_3.setUseDescender(true);
            cell20_1_4_5_1_3.setVerticalAlignment(Element.ALIGN_TOP);
            cell20_1_4_5_1_3.setColspan(6);
            table11_2_4_5_6.addCell(cell20_1_4_5_1_3);
            document.add(table11_2_4_5_6);

            PdfPTable table26_1 = new PdfPTable(12);
            PdfPCell cell39_1 = new PdfPCell();
            //cell1.setBorderWidthColor(BaseColor.BLUE);
            //cell1.setPaddingLeft(10);
            cell39_1.setBorderWidth(0);
            cell39_1.setPhrase(new Phrase("", fontEngH_dd));
            cell39_1.setHorizontalAlignment(Element.ALIGN_LEFT);
            cell39_1.setVerticalAlignment(Element.ALIGN_MIDDLE);
            cell39_1.setColspan(12);
            table26_1.addCell(cell39_1);
            document.add(table26_1);

//                    PdfPTable table26 = new PdfPTable(6); 
//                    PdfPCell cell39 = new PdfPCell();
//                    //cell1.setBorderWidthColor(BaseColor.BLUE);
//                    //cell1.setPaddingLeft(10);
//                    cell39.setBorderWidth(0);
//                    cell39.setPhrase(new Phrase("Silvassa Municipal Council\n(It is a system generated bill which does not need a signature) ",fontEngH_dd));
//                    cell39.setHorizontalAlignment(Element.ALIGN_LEFT);
//                    cell39.setVerticalAlignment(Element.ALIGN_MIDDLE);
//                    cell39.setColspan(6);
//                    table26.addCell(cell39); 
//                    document.add(table26);
            //According to The Dadra And Nagar Haveli Municipal Council Regulation ,2004, Section 141(3), if a person pays the sum within the fifteen days of period of service date, the municipal will given 1% rebate on whole sum. If he doesn’t pay the sum within the period 1% rebate will not be given to the person. 
            PdfPTable table75 = new PdfPTable(12);
            PdfPCell cell78 = new PdfPCell();
            //cell1.setBorderWidthColor(BaseColor.BLUE);
            //cell1.setPaddingLeft(10);
            cell78.setBorderWidth(0);
            cell78.setPhrase(new Phrase(" ", fontEngH_8_1));
            cell78.setHorizontalAlignment(Element.ALIGN_CENTER);
            cell78.setVerticalAlignment(Element.ALIGN_MIDDLE);
            cell78.setColspan(12);
            table75.addCell(cell78);
            document.add(table75);

            PdfPTable table20 = new PdfPTable(12);

            //table20.setTableEvent(new BorderEvent());
            //table20.getDefaultCell().setBorder(Rectangle.NO_BORDER);
            //table5.setWidths(columnWidths);
            PdfPCell cell71 = new PdfPCell();
            //cell1.setBorderColor(BaseColor.BLUE);
            //cell1.setPaddingLeft(10);

            cell71.setBorderWidth(1);

            cell71.setBorderColor(BaseColor.GRAY);
            cell71.setBorder(Rectangle.LEFT | Rectangle.TOP | Rectangle.BOTTOM);

            Chunk ch1 = new Chunk("Silvassa Municipal Council, Opposite Udyog Bhawan Secretariat Road, Amli, U.T. of Dadra & Nagar Haveli, Silvassa – 396230 ", fontEngH_2);
            Chunk ch2 = new Chunk("Help Line Number: 0260-2633192 : Email-Id: silvassamunicipalcouncil@gmail.com ", fontEngH_2_);
            Chunk ch3 = new Chunk("Toll free No. 1800-1030-636 ", fontEngH_2);
            Phrase add = new Phrase();
            Paragraph para = new Paragraph();
            para.add(ch1);
            para.add(new Chunk("\n"));
            para.add(ch2);
            para.add(new Chunk("\n"));
            para.add(ch3);

            add.add(para);
            //add.add(ch2);
            // add.add(ch3);
            //cell1.setBackgroundColor(BaseColor.LIGHT_GRAY);
//               cell71.setPhrase(new Phrase("Silvassa Municipal Council, Opposite Udyog Bhawan Secretariat Road, Amli, U.T. of Dadra & Nagar Haveli, Silvassa – 396230 Help Line Number: 0260-2633192 : Email-Id: silvassamunicipalcouncil@gmail.com Toll free No. 1800-1030-636", fontEngH_2));

            Chunk msg_23 = new Chunk("Silvassa Municipal Council, Opposite Udyog Bhawan Secretariat Road, Amli,\n U.T. of Dadra & Nagar Haveli, Silvassa – 396230 \n Help Line Number: 0260-2633192 : Email-Id:  silvassamunicipalcouncil@gmail.com  \n Toll free No. 1800-1030-636\n For Complaints/Suggestions/Queries visit  ", fontEngH_2_);
            Chunk msg_23_3 = new Chunk(" www.smcdnh.in ", fontEngH_2_);
            msg_23_3.setUnderline(0.1f, -2f);
            Chunk msg_24 = new Chunk("\n In case any details mentioned in the notices are wrong or incomplete please visit smc office or ", fontEngH_2_);

            Chunk msg_23_1 = new Chunk("http://smcdnh.nic.in/", fontEngH_2_);
            msg_23_1.setUnderline(0.1f, -2f);
            Chunk msg_23_2 = new Chunk(" and fill form to apply for correction and completion.", fontEngH_2_);
            Phrase ph45 = new Phrase();
            ph45.add(msg_23);
            ph45.add(msg_23_3);
            ph45.add(msg_24);
            ph45.add(msg_23_1);
            ph45.add(msg_23_2);

            Paragraph pp = new Paragraph(" Silvassa Municipal Council, Opposite Udyog Bhawan Secretariat Road, Amli,\n U.T. of Dadra & Nagar Haveli, Silvassa – 396230 \n Help Line Number: 0260-2633192 : Email-Id:  silvassamunicipalcouncil@gmail.com  \n Toll free No. 1800-1030-636\n For Complaints/Suggestions/Queries visit www.smcdnh.in  \n In case any details mentioned in the notices are wrong or incomplete please visit smc office or www.smcdnh.nic.in and fill form to apply for correction and completion.", fontEngH_2_);
            pp.setAlignment(Paragraph.ALIGN_LEFT);
            cell71.setPhrase(ph45);
//                cell71.setHorizontalAlignment(Element.ALIGN_CENTER);
            cell71.setPaddingBottom(5);
            cell71.setColspan(7);

            table20.addCell(cell71);

            PdfPCell cell72 = new PdfPCell();
            cell72.setBorderWidth(1);
            cell72.setPaddingTop(10);

            cell72.setBorderColor(BaseColor.GRAY);
            cell72.setBorder(Rectangle.TOP | Rectangle.BOTTOM);
            cell72.setPhrase(new Phrase("", fontEngH_2));
            cell72.setHorizontalAlignment(Element.ALIGN_CENTER);
            cell72.setColspan(1);
            table20.addCell(cell72);

            PdfPCell cell73 = new PdfPCell(image2);
            cell73.setBorderWidth(1);
            cell73.setPaddingTop(9);
            cell73.setBorderColor(BaseColor.GRAY);
            cell73.setBorder(Rectangle.TOP | Rectangle.BOTTOM);
            cell73.setVerticalAlignment(Element.ALIGN_CENTER);
            cell73.setColspan(2);
            table20.addCell(cell73);

            String crypt_prop = ASE.parseToURLParam(bean.getUniqueId());//ASE.parseToURLParam(ASE.encrypt(bean.getUniqueId()));

            String url = MmiPathController.getDataPath("billPdf") + crypt_prop;
            
     //       String upiurl = MmiPathController.getDataPath("upiurl") + qrbean.getPayableAmnt() + "&tn=" + qrbean.getTransactionId() + "&tr=UPIPAYMENTFROMSILVASSA&cu=INR&mc=9311";
                    
            BarcodeQRCode barcodeQrcode = new BarcodeQRCode(url, 1, 1, null);
            Image qrcodeImage = barcodeQrcode.getImage();
            qrcodeImage.setAbsolutePosition(20, 500);
            qrcodeImage.scalePercent(120);
            // qrcodeImage.setAlignment(Image.MIDDLE);
            //  qrcodeImage.setAbsolutePosition(70, 30);
            //image2.scalePercent(33);
            PdfPCell cell74 = new PdfPCell(qrcodeImage);
            cell74.setBorderWidth(1);
            cell74.setPaddingTop(8);
            cell74.setBorderColor(BaseColor.GRAY);
            cell74.setBorder(Rectangle.TOP | Rectangle.BOTTOM | Rectangle.RIGHT);
            cell74.setHorizontalAlignment(Element.ALIGN_CENTER);
            cell74.setColspan(2);
            table20.addCell(cell74);

            document.add(table20);

            PdfPTable table24 = new PdfPTable(12);
            PdfPCell cell75 = new PdfPCell();
            cell75.setBorderWidth(0);
            cell75.setPaddingTop(4);
            cell75.setPaddingRight(20);
            cell75.setBorderColor(BaseColor.GRAY);
            cell75.setPhrase(new Phrase("Scan to Pay", fontEngH_7_1));
            cell75.setBorder(Rectangle.TOP | Rectangle.BOTTOM);
            cell75.setHorizontalAlignment(Element.ALIGN_RIGHT);
            cell75.setColspan(12);
            table24.addCell(cell75);
            document.add(table24);

            PdfPTable table22 = new PdfPTable(12);
            //table5.setWidths(columnWidths);
            PdfPCell cell72_1 = new PdfPCell();
            //cell1.setBorderColor(BaseColor.BLUE);
            //cell1.setPaddingLeft(10);
            cell72_1.setBorderWidth(0);
            //cell72_1.setBorderColor(BaseColor.GRAY);
            //cell72.setBackgroundColor(BaseColor.BLACK);
            cell72_1.setPhrase(new Phrase("" + bean.getDistributionId(), fontEngH_11));
            //cell68.setBorderColor(BaseColor.LIGHT_GRAY);
            //cell72.setGrayFill(0.9f);
            cell72_1.setHorizontalAlignment(Element.ALIGN_RIGHT);
            cell72_1.setVerticalAlignment(Element.ALIGN_MIDDLE);
            cell72_1.setColspan(12);
            table22.addCell(cell72_1);
            document.add(table22);

            //System.out.println("last");
            //document.close();
            //writer.close();
            sno++;
            index++;
            sp = "";
            op = "";
            oldmc = "";
            if (seq % 2 == 0) {
                document.newPage();
            } else {
               Paragraph paraforspace = new Paragraph ("---------------------------------------------------------------------------------------------------------------------------------------------------------");  
               document.add(paraforspace);
            }

        }
    }

}

///*
// * To change this license header, choose License Headers in Project Properties.
// * To change this template file, choose Tools | Templates
// * and open the template in the editor.
// */
//package com.silvassa.service;
//import java.util.List;
//import java.util.Map;
//
//import javax.servlet.http.HttpServletRequest;
//import javax.servlet.http.HttpServletResponse;
//
//import com.itextpdf.text.BaseColor;
//import com.itextpdf.text.Chunk;
//import com.itextpdf.text.Document;
//import com.itextpdf.text.Element;
//import com.itextpdf.text.Font;
//import com.itextpdf.text.FontFactory;
//import com.itextpdf.text.Image;
//import com.itextpdf.text.PageSize;
//import com.itextpdf.text.Paragraph;
//import com.itextpdf.text.Phrase;
//import com.itextpdf.text.pdf.BaseFont;
//import com.itextpdf.text.pdf.PdfPCell;
//import com.itextpdf.text.pdf.PdfPTable;
//import com.itextpdf.text.pdf.PdfWriter;
//import com.silvassa.bean.PrivateNoticeBean;
//import com.silvassa.model.BankMaster;
//import com.silvassa.model.PaymentBean;
//import com.silvassa.model.PropertyDetails;
//import com.silvassa.util.NumberToWord;
//import java.io.File;
//import java.io.FileOutputStream;
//import java.text.DecimalFormat;
//import java.text.SimpleDateFormat;
//import java.util.ArrayList;
//import java.util.Calendar;
//import java.util.Iterator;
//import javax.servlet.ServletOutputStream;
//import org.springframework.stereotype.Component;
///**
// *
// * @author CEINFO
// */
//public class BillPDF extends  AbstractITextPdfNoticeView {
//    @Override
//	protected void buildPdfDocument(Map<String, Object> model, Document document,
//			PdfWriter writer, HttpServletRequest request, HttpServletResponse response)
//			throws Exception {
//		// get data model which is passed by the Spring container
//		//PdfWriter.getInstance(document, new FileOutputStream("D:\\mnt\\vol1\\paymenReceipt\\HelloWorld.pdf"));
//                //ServletOutputStream out=response.getOutputStream();
//                
//                 //document.setMargins(marginLeft, marginRight, marginTop, marginBottom)
//                         //document.setMargins(marginLeft, marginRight, marginTop, marginBottom)
//                
//                         //document.setMargins(-5f,-10f,10f,0f);
//                int columnWidths2[] = {2, 1, 1, 4, 4, 4, 3, 3, 3, 1, 2, 2};
//                int columnWidths3[] = {2, 5, 2, 2, 2, 2, 2, 3,1,2,2,2};
//                int columnWidths4[] = {2, 2, 2, 2, 3, 2, 2, 2,2,2,2,2};
//                //int width[]={4,4,4,4,4,4};
//                
//                String owner="";
//                String ppid="";
//                String occupier="";
//                String completeAddress="";
//                List<PrivateNoticeBean> beanList = (List<PrivateNoticeBean>) model.get("taxList");
//                
//                
//                //System.out.println("beanList "+beanList.size());
//                
//                //byte bb[]=response.getOutputStream().toByteArray();
//                //File f1=new File("D:\\"+ppid+".pdf") ;
//                //FileOutputStream fout1=new FileOutputStream(f1);    
//                //PdfWriter writer1 = PdfWriter.getInstance(document,  fout1);
//                //fout1.write(bb);
//                
//                
//                Iterator itr=beanList.iterator();
//                //System.out.println("PDFBuilder142Notice");
//                String path=request.getRealPath("/res/img/logo2.png");
//                String path1=request.getRealPath("/res/img/23.png");
//                String pid="";
//                String clientName="";
//                String address1="";
//                String address2="";
//                String amount="";
//                String period="upto2018";
//                String fontPathGujrati=request.getRealPath("/res/fonts/wwgj0101.ttf");
//                //String fontPathGujrati=request.getRealPath("/res/fonts/wwgj0101.ttf");
//                String fontPathHindi1=request.getRealPath("/res/fonts/kruti-dev-021.ttf");
//                //String fontPathHindi2=request.getRealPath("/res/fonts/mangal.ttf");
//                BaseFont bf = BaseFont.createFont(fontPathHindi1,BaseFont.IDENTITY_H, BaseFont.EMBEDDED);
//                //BaseFont bf = BaseFont.createFont("c:\\windows\\fonts\\Kruti_Dev.ttf",BaseFont.IDENTITY_H, BaseFont.EMBEDDED);
//                 BaseFont bf_gujrati = BaseFont.createFont(fontPathGujrati, BaseFont.IDENTITY_H, BaseFont.EMBEDDED);
//                //Font fontHindi=new Font(bf,18,Font.BOLD);
//                //Font fontHindi_1=new Font(bf,14,Font.BOLD);
//               // Font fontHindi_2=new Font(bf,8,Font.BOLD);
//                //Font fontEngH=new Font(Font.FontFamily.TIMES_ROMAN,11,Font.BOLD);
//               // Font fontEngH_1=new Font(Font.FontFamily.TIMES_ROMAN,10,Font.NORMAL);
//                //Font fontEngH_2=new Font(Font.FontFamily.TIMES_ROMAN,8,Font.NORMAL);
//                //Font fontEngH_3=new Font(Font.FontFamily.TIMES_ROMAN,14,Font.UNDERLINE);
//                //Font fontEngH_4=new Font(Font.FontFamily.TIMES_ROMAN,10,Font.NORMAL);
//               // Font fontEngH_5=new Font(Font.FontFamily.TIMES_ROMAN,12,Font.NORMAL);
//               // Font fontEngH_6=new Font(Font.FontFamily.TIMES_ROMAN,12,Font.BOLD);
//                
//                //Font fontGujrati = new Font(bf_gujrati, 11, Font.NORMAL);
//        Font fontGujrati = new Font(bf_gujrati, 11, Font.NORMAL);
//        Font fontHindi = new Font(bf, 18, Font.BOLD);
//        Font fontHindi_1 = new Font(bf, 17, Font.BOLD);
//        Font fontHindi_2 = new Font(bf, 10, Font.NORMAL);
//        Font fontHindi_2k = new Font(bf, 9, Font.NORMAL);
//        Font fontEngH = new Font(Font.FontFamily.TIMES_ROMAN, 11, Font.BOLD);
//        Font fontEngH_1 = new Font(Font.FontFamily.TIMES_ROMAN, 11, Font.NORMAL);
//        Font fontEngH_header = new Font(Font.FontFamily.TIMES_ROMAN, 9, Font.NORMAL);
//        Font fontEngH_dd = new Font(Font.FontFamily.TIMES_ROMAN, 9, Font.NORMAL);
//        Font fontEngH_dd_1 = new Font(Font.FontFamily.TIMES_ROMAN, 7, Font.BOLD);
//        Font fontEngH_dd_2 = new Font(Font.FontFamily.TIMES_ROMAN, 8, Font.BOLD);
//        Font fontEngH_2k = new Font(Font.FontFamily.TIMES_ROMAN, 10, Font.NORMAL);
//        Font fontEngH_2ky = new Font(Font.FontFamily.TIMES_ROMAN, 11, Font.BOLD);
//        Font fontEngH_2 = new Font(Font.FontFamily.TIMES_ROMAN, 9, Font.NORMAL);
//        Font fontEngH_3 = new Font(Font.FontFamily.TIMES_ROMAN, 18, Font.BOLD);
//        fontEngH_3.setColor(BaseColor.GRAY);
//        Font fontEngH_4 = new Font(Font.FontFamily.TIMES_ROMAN, 10, Font.NORMAL);
//        Font fontEngH_5 = new Font(Font.FontFamily.TIMES_ROMAN, 12, Font.NORMAL);
//        Font fontEngH_6 = new Font(Font.FontFamily.TIMES_ROMAN, 4, Font.NORMAL);
//        Font fontEngH_7 = new Font(Font.FontFamily.TIMES_ROMAN, 7, Font.NORMAL);
//        Font fontEngH_8 = new Font(Font.FontFamily.TIMES_ROMAN, 7, Font.BOLD);
//        Font fontEngH_rule = new Font(Font.FontFamily.TIMES_ROMAN, 11, Font.NORMAL);
//        //Font fontHindi_rule=new Font(bf,10,Font.NORMAL);
//        Font fontHindi_rule = new Font(bf, 11, Font.NORMAL);
//        Font fontEngH_4k = new Font(Font.FontFamily.TIMES_ROMAN, 7, Font.BOLD);
//        Font fontEngH_9 = new Font(Font.FontFamily.TIMES_ROMAN, 2, Font.NORMAL);
//        Font fontEngH_11 = new Font(Font.FontFamily.TIMES_ROMAN, 7, Font.NORMAL);
//        Font fontEngH_4k_eng = new Font(Font.FontFamily.TIMES_ROMAN, 9, Font.NORMAL);
//        Font fontEngH_8_1 = new Font(Font.FontFamily.TIMES_ROMAN, 3, Font.NORMAL);
//        Font fontHindi_rule_header = new Font(bf, 10, Font.NORMAL);
//        Font fontHindi_pid = new Font(bf, 9, Font.NORMAL);
//                    
//             
//                    
//                    
//                    
//                
//             //Document document = new Document(PageSize.A4,0,0,10,0);
//            //PdfWriter writer = PdfWriter.getInstance(document, new FileOutputStream("d://noticetest.pdf"));
//            //document.open();
//            Image image1 = Image.getInstance(path);
//            image1.scalePercent(20f);
//            Image image2 = Image.getInstance(path1);
//            //Image image2 = Image.getInstance("d:\\img\\23.png");
//             SimpleDateFormat sdf = new SimpleDateFormat(("dd-MM-yyyy hh:mm:ss"));
//             String  date2 = (String)sdf.format(Calendar.getInstance().getTime());
//             date2=date2.substring(0,10);
//             DecimalFormat df = new DecimalFormat("#,###");
//             Calendar c = Calendar.getInstance();
//             c.add(Calendar.DAY_OF_MONTH, 30); 
//             String newDate = sdf.format(c.getTime()); 
//             newDate=newDate.substring(0,10);
//                    
//            
//                
//            
//               while(itr.hasNext()){
//                String sp = "";
//                String op = "";
//                String oldmc = "";
//                int index = 1;
//                int sno = 1;
//                String bankName="";
//                
//                PrivateNoticeBean bean= (PrivateNoticeBean)itr.next();
//                    Paragraph c1=new Paragraph("  ");
//                    document.add(c1);
//                    
//            PdfPTable table = new PdfPTable(12); // 3 columns.
//            table.setWidthPercentage(88); //Width 100%
//            //table.setSpacingBefore(10f); //Space before table
//            //table.setSpacingAfter(10f); //Space after table
//
//                    //Set Column widths
//            //int columnWidths[]={2,1,4,3,8,9,10,9,8,7,8,6};
//            //float[] columnWidths = {1f, 1f, 1f,1f,1f,1f,1f,1f,1f,1f,1f,1f};
//            table.setWidths(columnWidths2);
//
//            PdfPCell cell2 = new PdfPCell(image1);
//                    //cell1.setBorderColor(BaseColor.BLUE);
//            //cell1.setPaddingLeft(10);
//            cell2.setBorderWidth(0);
//            
//                    //cell2.setBorderColor(BaseColor.BLACK);
//            //cell2.setBackgroundColor(BaseColor.LIGHT_GRAY);
//            cell2.setHorizontalAlignment(Element.ALIGN_RIGHT);
//            //cell2.setVerticalAlignment(Element.ALIGN_TOP);
//            cell2.setColspan(2);
//            table.addCell(cell2);
//
//            Chunk c_msg=new Chunk("Silvassa Municipal Council ",fontEngH);
//            Chunk c_msg_blank=new Chunk("\n",fontEngH_8_1);
//            Chunk c_msg1=new Chunk("flyoklk uxj ikfydk \n",fontHindi_1);
//            Chunk c_msg_blank1=new Chunk(" \n",fontEngH_8_1);
//            Chunk c_msg2=new Chunk("Property Tax Bill (2019-2020) \n", fontEngH_2k);
//            Chunk c_msg_blank2=new Chunk(" \n",fontEngH_8_1);
//            Chunk c_msg3=new Chunk("laifRr dj facy ¼2019&2020½ ", fontHindi_2);
//            Chunk c_msg_blank4=new Chunk("\n",fontEngH_8_1);
//            Chunk c_msg4=new Chunk("www.smcdnh.nic.in                         Notice u/s 141 ", fontEngH_2ky);
//            Phrase p_msg=new Phrase();
//            p_msg.add(c_msg);
//            p_msg.add(c_msg_blank);
//            p_msg.add(c_msg1);
//            p_msg.add(c_msg_blank1);
//            p_msg.add(c_msg2);
//            p_msg.add(c_msg_blank1);
//            p_msg.add(c_msg3);
//            p_msg.add(c_msg_blank1);
//            p_msg.add(c_msg4);
//            
//            Chunk c_owner=new Chunk("Owner Name: ",fontEngH_dd_2);
//            Chunk c_owner_data=new Chunk(""+bean.getOwner_Father(),fontEngH_dd);
//            Chunk c_msg_blank_owner=new Chunk("\n",fontEngH_8_1);
//            Chunk c_address=new Chunk("Property Address: ",fontEngH_dd_2);
//            Chunk c_address_data=new Chunk(""+bean.getAddress(),fontEngH_dd);
//            Phrase p_msg_owner=new Phrase();
//            p_msg_owner.add(c_owner);
//            p_msg_owner.add(c_owner_data);
//            p_msg_owner.add(c_msg_blank_owner);
//            p_msg_owner.add(c_address);
//            p_msg_owner.add(c_address_data);
//            
//            
//            PdfPTable inntertable = new PdfPTable(5);
//            //inntertable.setWidths(columnWidths2);
//            PdfPCell innercell1 = new PdfPCell();
//            innercell1.setBorderWidth(0);
//            innercell1.setPhrase(p_msg);
//            //innercell1.setPhrase(new Phrase("Silvassa Municipal Council ", fontEngH));
//                   //innercell1.setHorizontalAlignment(Element.ALIGN_LEFT);
//            //innercell1.setVerticalAlignment(Element.ALIGN_TOP);
//            innercell1.setColspan(5);
//            //innercell1.setRowspan(2);
//            inntertable.addCell(innercell1);
//                   //table.addCell(inntertable);
//
//            PdfPTable inntertableHindi = new PdfPTable(5);
//            //inntertable.setWidths(columnWidths2);
//            innercell1 = new PdfPCell();
//            innercell1.setBorderWidth(0);
//            innercell1.setPhrase(p_msg_owner);
//                   //innercell1.setHorizontalAlignment(Element.ALIGN_LEFT);
//            //innercell1.setVerticalAlignment(Element.ALIGN_TOP);
//            innercell1.setColspan(5);
//            //innercell1.setRowspan(2);
//            inntertableHindi.addCell(innercell1);
//            
//            
//                   //table.addCell(inntertable1);
//
//                   //table.addCell(inntertable1);
//
//            
//            PdfPCell cell3 = new PdfPCell();
//                    //cell3.setBorderColor(BaseColor.BLACK);
//            //cell1.setPaddingLeft(10);
//            cell3.setBorderWidth(0);
//                    //cell3.setBackgroundColor(BaseColor.LIGHT_GRAY);
//            //cell3.setPhrase(new Phrase("flyoklk uxj ifj’kn \n2018-2019",fontHindi_1 ));
//            cell3.setHorizontalAlignment(Element.ALIGN_CENTER);
//            cell3.setVerticalAlignment(Element.ALIGN_TOP);
//            cell3.setColspan(5);
//            
//                   //table.addCell(inntertable2);
//
//            PdfPCell cell1 = new PdfPCell();
//                    //cell1.setBorderColor(BaseColor.BLUE);
//            //cell1.setPaddingLeft(10);
//            cell1.setBorderWidth(0);
//                   // cell1.setBorderColor(BaseColor.BLACK);
//            //cell1.setBackgroundColor(BaseColor.LIGHT_GRAY);
//
//            //cell1.setPhrase(new Phrase("SILVASSA MUNICIPAL COUNCIL ",fontEngH ));
//            cell1.setHorizontalAlignment(Element.ALIGN_LEFT);
//            cell1.setVerticalAlignment(Element.ALIGN_TOP);
//            cell1.setColspan(5);
//            cell1.addElement(inntertable);
//            //cell1.addElement(inntertableHindi);
//            //cell1.addElement(inntertable1);
//           // cell1.addElement(inntertableHindi1);
//            //cell1.addElement(inntertableHindi2);
//            //cell1.addElement(inntertable2);
//            
//            //cell1.setRowspan(2);
//            table.addCell(cell1);
//            
//            PdfPCell cell14 = new PdfPCell();
//                    //cell1.setBorderColor(BaseColor.BLUE);
//            //cell1.setPaddingLeft(10);
//            cell14.setBorderWidth(0);
//                   // cell1.setBorderColor(BaseColor.BLACK);
//            //cell1.setBackgroundColor(BaseColor.LIGHT_GRAY);
//
//            //cell1.setPhrase(new Phrase("SILVASSA MUNICIPAL COUNCIL ",fontEngH ));
//            cell14.setHorizontalAlignment(Element.ALIGN_LEFT);
//            cell14.setVerticalAlignment(Element.ALIGN_TOP);
//            cell14.setColspan(5);
//            cell14.addElement(inntertableHindi);
//            //cell1.addElement(inntertable1);
//           // cell1.addElement(inntertableHindi1);
//            //cell1.addElement(inntertableHindi2);
//            //cell1.addElement(inntertable2);
//            
//            //cell1.setRowspan(2);
//            table.addCell(cell14);
//            
//            
//            table.addCell(cell3);
//            document.add(table);
//
//            /*cell1 = new PdfPCell();
//                    
//             // cell1.setBorderColor(BaseColor.BLACK);
//             //cell1.setBackgroundColor(BaseColor.LIGHT_GRAY);
//             //cellr.setRowspan(2);
//             cell1.setBorder(0);
//             cell1.setPhrase(new Phrase("Property Tax Assessment Notice ",fontEngH_2 ));
//             cell1.setHorizontalAlignment(Element.ALIGN_LEFT);
//             cell1.setVerticalAlignment(Element.ALIGN_TOP);
//             //cell1.setColspan(5);
//             table.addCell(cell1);
//                    
//             cell1 = new PdfPCell();
//                    
//             // cell1.setBorderColor(BaseColor.BLACK);
//             //cell1.setBackgroundColor(BaseColor.LIGHT_GRAY);
//             //cellr.setRowspan(2);
//             cell1.setBorder(0);
//             cell1.setPhrase(new Phrase("2018-2019 ",fontEngH_2 ));
//             cell1.setHorizontalAlignment(Element.ALIGN_LEFT);
//             cell1.setVerticalAlignment(Element.ALIGN_TOP);
//             //cell1.setColspan(5);
//             table.addCell(cell1);*/
//            
//                   //table.addCell(inntertable);
//
//            
//            
//            
//            
//
//            //table.addCell(cellr);
//            
//                    
//                    
//                    
//                    
//                    
//                    
////                    PdfPTable table = new PdfPTable(6); 
////                    PdfPCell cell6 = new PdfPCell(image1);
////                    //cell1.setBorderWidthColor(BaseColor.BLUE);
////                    //cell1.setPaddingLeft(10);
////                    cell6.setBorderWidth(0);
////                    //cell6.setPhrase(new Phrase("ak",fontEngH ));
////                    cell6.setHorizontalAlignment(Element.ALIGN_CENTER);
////                    cell6.setVerticalAlignment(Element.ALIGN_MIDDLE);
////                    cell6.setColspan(6);
////                    
////                    table.addCell(cell6);
////                    document.add(table);
////                    Paragraph c2=new Paragraph("  ");
////                    document.add(c2);
//                    
//                    PdfPTable table8 = new PdfPTable(6); 
//                    PdfPCell cell13 = new PdfPCell();
//                    //cell1.setBorderWidthColor(BaseColor.BLUE);
//                    //cell1.setPaddingLeft(10);
//                    cell13.setBorderWidth(0);
//                    cell13.setPhrase(new Phrase(" ",fontEngH_8_1));
//                    cell13.setHorizontalAlignment(Element.ALIGN_LEFT);
//                    cell13.setVerticalAlignment(Element.ALIGN_MIDDLE);
//                    cell13.setColspan(6);
//                    table8.addCell(cell13); 
//                    
//                    document.add(table8); 
//            
//                   PdfPTable table1 = new PdfPTable(12);
//            //table3.setWidths(columnWidths);
//                    PdfPCell cell5 = new PdfPCell();
//                    //cell1.setBorderColor(BaseColor.BLUE);
//            //cell1.setPaddingLeft(10);
//                    cell5.setBorderWidth(1);
//                    Chunk j1=new Chunk("As per Dadra and Nagar Haveli Municipal Council Regulation, 2004, Section 141(3), if a taxpayer pays the tax before due date, he/she shall be entitled to get a discount of 1% on the total bill amount. After the said period, the taxpayer shall be liable to pay interest. This bill is being sent with prior approval of Chief Officer, Silvassa Municipal Council. ",fontEngH_header);
//                    //Chunk j2=new Chunk(" www.smcdnh.nic.in ",fontEngH_4);
//                    //Chunk j3=new Chunk("“p dpÝed ‘u ky’pfZp ap¡d® Ädp Lfu iLip¡. Ap ky’pfZp ap¡d",fontGujrati);
//                   // Chunk j4=new Chunk(" www.smcdnh.nic.in ",fontEngH_4);
//                    //Chunk j5=new Chunk("h¡bkpCV ”f ”Z D”gå’ R¡. k»”qÑ“u Ahgp¡L“ k»¿epdp sdpfp Öpfp hp»’p A“¡ ky’pfZp ap¡d® 30 qvhk “u A»vf Ädp “lu » Lfhpdp» Aph¡ sp¡ ”°p¡”Vu® kh¡® “u dpqlsu kpQu dp“hdp» Aphi¡. ",fontGujrati);
//
//                    Phrase pj=new Phrase();
//                    pj.add(j1);
//                    //pj.add(j2);
//                   // pj.add(j3);
//                    //pj.add(j4);
//                    //pj.add(j5);
//            
//                   // cell1.setBorderColor(BaseColor.BLACK);
//            //cell1.setBackgroundColor(BaseColor.LIGHT_GRAY);
//                    cell5.setPhrase(pj);
//                    //+cell5.setPhrase(new Phrase("vpvfp A“¡ “Nf lh¡gu “Nf ”pqgLp Aq’q“ed 2004 “u ’pfp 111 A“¡ ’pfp 115 D”’pfp (1) A“¡ (2) “p A“yk»’p“¡ k¡ghpk “Nf ”pqgLp dp» Aph¡g kdõs ”°p¡”Vu® “y» kh¡® ”wZ® ‘e¡g R¡. Mpk sdp¡“¡ qh“»su R¡ L¡ Ap k»”qÑ “u “p¡qVk Ýep“ ”wh®L hp»Qu Ap k»”qÑ “u dpqlsu “p¡qVk dp» vip®h¡g kwQ“p dyÄb Lp¡C”Z ”°Lpf “p¡ hp»’p¡ A‘hp cwg dm¡ sp¡ ky’pfp¡ Lfhp sd¡ ky’pfZp ap¡d® cfu “¡ 30 qvhk “u A»vf “Nf ”pqgLp“u Ap¡qak A‘hp Ap¡“ gpC“ smcdnh.nic.in “p dpÝed ‘u ky’pfZp ap¡d® Ädp Lfu iLip¡. Ap ky’pfZp ap¡d® Map My India.in h¡bkpCV ”f ”Z D”gå’ R¡. k»”qÑ“u Ahgp¡L“ k»¿epdp sdpfp Öpfp hp»’p A“¡ ky’pfZp ap¡d® 30 qvhk “u A»vf Ädp “lu » Lfhpdp» Aph¡ sp¡ ”°p¡”Vu® kh¡® “u dpqlsu kpQu dp“hdp» Aphi¡. ", fontGujrati));
//                    cell5.setPadding(8);
//                    cell5.setHorizontalAlignment(Element.ALIGN_JUSTIFIED);
//                    cell5.setVerticalAlignment(Element.ALIGN_TOP);
//                    cell5.setBorderColor(BaseColor.LIGHT_GRAY);
//                    cell5.setColspan(6);
//                    table1.addCell(cell5);
//
//                    PdfPCell cell6 = new PdfPCell();
//                    //cell1.setBorderColor(BaseColor.BLUE);
//            //cell1.setPaddingLeft(10);
//                    cell6.setBorderWidth(1);
//                    Chunk c11=new Chunk("nknjk ,oa uxj gosyh uxj ikfydk vf/kfu;e 2004 dhs /kkjk 141 dhs mi/kkjk ¼3½ ds varxZr ;fn dksbZ O;fä facy rkjh[k  ds iaæg fnuksa ds Hkhrj jkf'k dk Hkqxrku djrk gS] rks uxj ikfydk iwjs ;ksx ij 1 çfr'kr dh NwV nsxkA ;fn og fu;fer rkjh[k ij Hkqdrku ugha djrk gS rRi'pkr ns; lEifÙk dj ds Åij og O;fä C;kt nsus gsrq ck/; gksxkA " +
//                    ";g fcy phQ v‚fQlj flyoklk uxj ikfydk ds iwokZuqefr ls Hkstk tk  jgk gSA ",fontHindi_rule_header);
//                    //Chunk c2=new Chunk(" www.smcdnh.nic.in ",fontEngH_4);
//                    //Chunk c3=new Chunk("ds ek/;e ls Hkh tek dj ldrs gSA ;g la'kks/ku QkeZ uxj ikfydk dh osclkbV",fontHindi_rule);
//                    //Chunk c4=new Chunk(" www.smcdnh.nic.in ",fontEngH_4);
//                    //Chunk c5=new Chunk("ij Hkh miyC/k gSA laifRr dj vkadyu dk ;g MkVk] tks bl uksfVl esa fn;k x;k gSA vkids }kjk vkifRr ;k vuqj¨/k 30 fnu ds Hkhrj uk vkus ij lgh eku fy;k tk,xkA",fontHindi_rule);
//                    Phrase p1=new Phrase();
//                    p1.add(c11);
//                    //p1.add(c2);
//                    //p1.add(c3);
//                    //p1.add(c4);
//                   // p1.add(c5);
//                           // cell1.setBorderColor(BaseColor.BLACK);
//                    //cell1.setBackgroundColor(BaseColor.LIGHT_GRAY);
//                    cell6.setPhrase(p1);
//                    //cell6.setPhrase(new Phrase("nknjk ,oa uxj gosyh uxj ikfydk vf/kfu;e 2004 dhs /kkjk 111rFkk /kkjk 115 dhs mi /kkjk ¼1½ ,oa ¼2½ ds varxZr flyoklk uxj ikfydk esa leLr izkWaiVhZ losZ dk dke iw.kZ gks pqdk gSA vr% vki lc ls vuqjks/k gS fd bl laifRr dj vkadyu uksfVl dks /;kuiwoZd i<s+ A bl laifRr dj vkadyu uksfVl esa n'kkZbZ xbZ lwpuk esa fdlh Òh izdkj dh vkifRr@ =qfV ik, tkus ij lq/kkj gsrw vki la'kks/ku QkeZ Hkj dj 30 fnu ds Hkhrj uxj ikfydk ds n¶rj esa rFkk vkWauykbu               ds ek/;e ls Hkh tek djk ldrs gSA ;g la'kks/ku QkeZ uxj ikfydk dh osclkbV            ij Hkh miyC/k gSA laifRr dj vkadyu dk ;g MkVk] tks bl uksfVl esa fn;k x;k gSA vkids }kjk vkifRr ;k vuqj¨/k 30 fnu ds Hkhrj uk vkus ij lgh eku fy;k tk,xkA  ", fontHindi_rule));
//                    cell6.setHorizontalAlignment(Element.ALIGN_JUSTIFIED);
//                    cell6.setVerticalAlignment(Element.ALIGN_TOP);
//                    cell6.setPadding(8);
//                    cell6.setColspan(6);
//                    cell6.setBorderColor(BaseColor.LIGHT_GRAY);
//                    table1.addCell(cell6);
//
//                    document.add(table1);
//
//                    
//                    PdfPTable table2 = new PdfPTable(6); 
//                    PdfPCell cell7 = new PdfPCell();
//                    //cell1.setBorderWidthColor(BaseColor.BLUE);
//                    //cell1.setPaddingLeft(10);
//                    cell7.setBorderWidth(0);
//                    cell7.setPhrase(new Phrase(" ",fontEngH_8_1));
//                    cell7.setHorizontalAlignment(Element.ALIGN_CENTER);
//                    cell7.setVerticalAlignment(Element.ALIGN_MIDDLE);
//                    cell7.setColspan(6);
//                    table2.addCell(cell7); 
//                    document.add(table2);
//                    
//                    //PdfPTable table3 = new PdfPTable(6); 
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
//                   
//                    
////                    PdfPTable table4 = new PdfPTable(6); 
////                    PdfPCell cell9 = new PdfPCell();
////                    //cell1.setBorderWidthColor(BaseColor.BLUE);
////                    //cell1.setPaddingLeft(10);
////                    cell9.setBorderWidth(0);
////                    cell9.setPhrase(new Phrase("",fontEngH));
////                    cell9.setHorizontalAlignment(Element.ALIGN_CENTER);
////                    cell9.setVerticalAlignment(Element.ALIGN_MIDDLE);
////                    cell9.setColspan(6);
////                    table4.addCell(cell9); 
////                    document.add(table4);
//                    
//                    
//                    
////                    PdfPTable table8_1 = new PdfPTable(6); 
////                    PdfPCell cell13_1 = new PdfPCell();
////                    cell13_1.setBorderColor(BaseColor.LIGHT_GRAY);
////                    //cell1.setBorderWidthColor(BaseColor.BLUE);
////                    //cell1.setPaddingLeft(10);
////                    cell13_1.setBorderWidth(1);
////                    cell13_1.setPhrase(new Phrase("According to The Dadra And Nagar Haveli Municipal Council Regulation ,2004, Section 141(3), if a person pays the sum within the fifteen days of period of service date, the municipal will given 1% rebate on whole sum. If he doesn’t pay the sum within the period 1% rebate will not be given to the person. ",fontEngH));
////                    cell13_1.setHorizontalAlignment(Element.ALIGN_JUSTIFIED);
////                    cell13_1.setVerticalAlignment(Element.ALIGN_MIDDLE);
////                    cell13_1.setColspan(6);
////                    table8_1.addCell(cell13_1); 
////                    
////                    document.add(table8_1);
//                    
//                    PdfPTable table34 = new PdfPTable(6); 
//                    PdfPCell cell137 = new PdfPCell();
//                    cell137.setBorderColor(BaseColor.LIGHT_GRAY);
//                    //cell137.setBorderColor(BaseColor.BLUE);
//                    //cell1.setPaddingLeft(10);
//                    cell137.setBackgroundColor(BaseColor.LIGHT_GRAY);
//                    cell137.setBorderWidth(1);
//                    
//                    cell137.setPhrase(new Phrase("Tax Details ",fontEngH));
//                    cell137.setHorizontalAlignment(Element.ALIGN_CENTER);
//                    cell137.setPadding(3);
//                    cell137.setUseAscender(true);
//                    cell137.setVerticalAlignment(Element.ALIGN_TOP);
//                    cell137.setColspan(6);
//                    table34.addCell(cell137); 
//                    document.add(table34);
//                    
//                    
//                    Chunk c_pid=new Chunk("Property ID/",fontEngH_dd_1);
//                    Chunk c_pid_hindi=new Chunk("lEifÙk uacj ",fontHindi_pid);
//                    Chunk c_pid_column=new Chunk(":",fontEngH_dd_1);
//                    Chunk c_pid_1=new Chunk(" "+bean.getUniqueId(),fontEngH_dd);
//                    //Chunk c2=new Chunk(" www.smcdnh.nic.in ",fontEngH_4);
//                    //Chunk c3=new Chunk("ds ek/;e ls Hkh tek dj ldrs gSA ;g la'kks/ku QkeZ uxj ikfydk dh osclkbV",fontHindi_rule);
//                    //Chunk c4=new Chunk(" www.smcdnh.nic.in ",fontEngH_4);
//                    //Chunk c5=new Chunk("ij Hkh miyC/k gSA laifRr dj vkadyu dk ;g MkVk] tks bl uksfVl esa fn;k x;k gSA vkids }kjk vkifRr ;k vuqj¨/k 30 fnu ds Hkhrj uk vkus ij lgh eku fy;k tk,xkA",fontHindi_rule);
//                    Phrase p_pid=new Phrase();
//                    p_pid.add(c_pid);
//                    p_pid.add(c_pid_hindi);
//                    p_pid.add(c_pid_column);
//                    p_pid.add(c_pid_1);
//                    
//                    Chunk c_bill=new Chunk("Bill No./ ",fontEngH_dd_1);
//                    Chunk c_bill_hindi=new Chunk("fcy uacj",fontHindi_pid);
//                    Chunk c_bill_column=new Chunk(":",fontEngH_dd_1);
//                    Chunk c_bill_1=new Chunk(" "+bean.getPrivateNotceNo(),fontEngH_dd);
//                    Phrase p_bill=new Phrase();
//                    p_bill.add(c_bill);
//                    p_bill.add(c_bill_hindi);
//                    p_bill.add(c_bill_column);
//                    p_bill.add(c_bill_1);
//                    
//                    Chunk c_service=new Chunk("Bill Date/ ",fontEngH_dd_1);
//                    Chunk c_service_hindi=new Chunk("fcy rkjh[k",fontHindi_pid);
//                    Chunk c_service_column=new Chunk(":",fontEngH_dd_1);
//                    Chunk c_service_1=new Chunk(" "+bean.getServiceDate(),fontEngH_dd);
//                    Phrase p_service=new Phrase();
//                    
//                    p_service.add(c_service);
//                    p_service.add(c_service_hindi);
//                    p_service.add(c_service_column);
//                    p_service.add(c_service_1);
//                    
//                    
//                    Chunk c_due=new Chunk("Due Date /",fontEngH_dd_1);
//                    Chunk c_due_hindi=new Chunk("ns; rkjh[k",fontHindi_pid);
//                    Chunk c_due_column=new Chunk(" : ",fontEngH_dd_1);
//                    Chunk c_due_1=new Chunk(""+bean.getDueDate(),fontEngH_dd);
//                    Phrase p_due=new Phrase();
//                    p_due.add(c_due);
//                    p_due.add(c_due_hindi);
//                    p_due.add(c_due_column);
//                    p_due.add(c_due_1);
//                     Phrase p_water=new Phrase();
//                     Phrase p_sewerage=new Phrase();
//                     Phrase p_professional=new Phrase();
//                     Phrase p_Sanitation=new Phrase();
//                    if(bean.getWaterTax()!=null && Integer.parseInt(bean.getWaterTax())>0){
//                        Chunk c_water=new Chunk("Water Tax /",fontEngH_dd_1);
//                        Chunk c_water_hindi=new Chunk("ty dj",fontHindi_pid);
//                        Chunk c_water_column=new Chunk(" :",fontEngH_dd_1);
//                        Chunk c_water_1=new Chunk(" "+bean.getWaterTax(),fontEngH_dd);
//                       
//                        p_water.add(c_water);
//                        p_water.add(c_water_hindi);
//                        p_water.add(c_water_column);
//                        p_water.add(c_water_1);
//                    }else {
//                        Chunk c_water=new Chunk("Water Tax /",fontEngH_dd_1);
//                        Chunk c_water_hindi=new Chunk("ty dj",fontHindi_pid);
//                        Chunk c_water_column=new Chunk(" :",fontEngH_dd_1);
//                        Chunk c_water_1=new Chunk(" N/A",fontEngH_dd);
//                        //Phrase p_water=new Phrase();
//                        p_water.add(c_water);
//                        p_water.add(c_water_hindi);
//                        p_water.add(c_water_column);
//                        p_water.add(c_water_1); 
//                    }
//                        
//                    
//                    if(bean.getSewerageTax()!=null && Integer.parseInt(bean.getSewerageTax())>0){
//                        Chunk c_sewerage=new Chunk("Sewerage Tax / ",fontEngH_dd_1);
//                        Chunk c_sewerage_hindi=new Chunk("lhojst dj",fontHindi_pid);
//                        Chunk c_sewerage_column=new Chunk(" :",fontEngH_dd_1);
//                        Chunk c_sewerage_1=new Chunk(" "+bean.getSewerageTax(),fontEngH_dd);
//                        
//                        p_sewerage.add(c_sewerage);
//                        p_sewerage.add(c_sewerage_hindi);
//                        p_sewerage.add(c_sewerage_column);
//                        p_sewerage.add(c_sewerage_1);
//                     
//                    }else{
//                        Chunk c_sewerage=new Chunk("Sewerage Tax / ",fontEngH_dd_1);
//                        Chunk c_sewerage_hindi=new Chunk("lhojst dj",fontHindi_pid);
//                        Chunk c_sewerage_column=new Chunk(" :",fontEngH_dd_1);
//                        Chunk c_sewerage_1=new Chunk(" N/A",fontEngH_dd);
//                        //Phrase p_sewerage=new Phrase();
//                        p_sewerage.add(c_sewerage);
//                        p_sewerage.add(c_sewerage_hindi);
//                        p_sewerage.add(c_sewerage_column);
//                        p_sewerage.add(c_sewerage_1);
//                    }
//                    
//                    
//                    if(bean.getProfessionalTax()!=null && Integer.parseInt(bean.getProfessionalTax())>0){
//                        Chunk c_Professional=new Chunk("Professional Tax / ",fontEngH_dd_1);
//                        Chunk c_Professional_hindi=new Chunk("çksQs'kuy dj",fontHindi_pid);
//                        Chunk c_Professional_column=new Chunk(" :",fontEngH_dd_1);
//                        Chunk c_Professional_1=new Chunk(" "+bean.getProfessionalTax(),fontEngH_dd);
//                        
//                        p_professional.add(c_Professional);
//                        p_professional.add(c_Professional_hindi);
//                        p_professional.add(c_Professional_column);
//                        p_professional.add(c_Professional_1);
//                    
//                    }else{
//                        Chunk c_Professional=new Chunk("Professional Tax / ",fontEngH_dd_1);
//                        Chunk c_Professional_hindi=new Chunk("çksQs'kuy dj",fontHindi_pid);
//                        Chunk c_Professional_column=new Chunk(" :",fontEngH_dd_1);
//                        Chunk c_Professional_1=new Chunk(" N/A",fontEngH_dd);
//                        //Phrase p_professional=new Phrase();
//                        p_professional.add(c_Professional);
//                        p_professional.add(c_Professional_hindi);
//                        p_professional.add(c_Professional_column);
//                        p_professional.add(c_Professional_1);
//                     
//                    }
//                    
//                    
//                    
//                    if(bean.getSanitationTax()!=null && Integer.parseInt(bean.getSanitationTax())>0){
//                        Chunk c_Sanitation=new Chunk("Sanitation /",fontEngH_dd_1);
//                        Chunk c_Sanitation_hindi=new Chunk(" LoPNrk dj",fontHindi_pid);
//                        Chunk c_Sanitation_column=new Chunk(" :",fontEngH_dd_1);
//                        Chunk c_Sanitation_1=new Chunk(" "+bean.getSanitationTax(),fontEngH_dd);
//                        
//                        p_Sanitation.add(c_Sanitation);
//                        p_Sanitation.add(c_Sanitation_hindi);
//                        p_Sanitation.add(c_Sanitation_column);
//                        p_Sanitation.add(c_Sanitation_1);
//                    }else{
//                        Chunk c_Sanitation=new Chunk("Sanitation /",fontEngH_dd_1);
//                        Chunk c_Sanitation_hindi=new Chunk(" LoPNrk dj",fontHindi_pid);
//                        Chunk c_Sanitation_column=new Chunk(" :",fontEngH_dd_1);
//                        Chunk c_Sanitation_1=new Chunk(" N/A",fontEngH_dd);
//                        //Phrase p_Sanitation=new Phrase();
//                        p_Sanitation.add(c_Sanitation);
//                        p_Sanitation.add(c_Sanitation_hindi);
//                        p_Sanitation.add(c_Sanitation_column);
//                        p_Sanitation.add(c_Sanitation_1);  
//                    }
//                    
//                    
//                    
//                   
//                    
//                    
//                    
//                    Chunk c_arrear=new Chunk("Arrear Amount/ ",fontEngH_dd_1);
//                    Chunk c_arrear_hindi=new Chunk("cdk;k jkf'k",fontHindi_pid);
//                    Chunk c_arrear_column=new Chunk(" : Rs.",fontEngH_dd_1);
//                    Chunk c_arrear_1=new Chunk(" "+bean.getArrearAmount(),fontEngH_dd);
//                    Phrase p_arrear=new Phrase();
//                    p_arrear.add(c_arrear);
//                    p_arrear.add(c_arrear_hindi);
//                    p_arrear.add(c_arrear_column);
//                    p_arrear.add(c_arrear_1);
//                    
//                    Chunk c_demand=new Chunk("Demand Amount/",fontEngH_dd_1);
//                    Chunk c_demand_1=new Chunk(""+bean.getTax(),fontEngH_dd);
//                    Chunk c_demand_hindi=new Chunk("ekax jkf'k",fontHindi_pid);
//                    Chunk c_demand_column=new Chunk(":(2018-2019): Rs. ",fontEngH_dd_1);
//
//                    Phrase p_demand=new Phrase();
//                    p_demand.add(c_demand);
//                    p_demand.add(c_demand_hindi);
//                    p_demand.add(c_demand_column);
//                    p_demand.add(c_demand_1);
//                    
//                    Chunk c_payable=new Chunk("Payable Amount/ ",fontEngH_dd_1);
//                    Chunk c_payable_hindi=new Chunk("Hkqxrku ;ksX; jkf'k",fontHindi_pid);
//                    Chunk c_payable_column=new Chunk(": Rs. ",fontEngH_dd_1);
//                    Chunk c_payable_1=new Chunk(""+bean.getPayableAmount(),fontEngH_dd);
//                    Phrase p_payable=new Phrase();
//                    p_payable.add(c_payable);
//                    p_payable.add(c_payable_hindi);
//                    p_payable.add(c_payable_column);
//                    p_payable.add(c_payable_1);
//                    
//                    //int rb=(int)Math.round(Double.parseDouble(bean.getPayableAmount())*.99);
//                    int rb=(int)Math.round(Double.parseDouble(bean.getPayableAmount())*.99);
//                    Chunk c_rebateAmt=new Chunk("After Rebate Payable Amount /",fontEngH_dd_1);
//                    Chunk c_rebateAmt_hindi=new Chunk("ns; jkf'k esa NwV ds ckn",fontHindi_pid);
//                    Chunk c_rebateAmt_column=new Chunk(": Rs.",fontEngH_dd_1);
//
//                    Chunk c_rebateAmt_1=new Chunk("(1% rebate on payable amount) ",fontEngH_dd);
//                    Chunk c_rebateAmt_2=new Chunk(" Rs. "+rb,fontEngH_dd);
//                    
//                    Phrase p_rebateAmt=new Phrase();
//                    p_rebateAmt.add(c_rebateAmt);
//                    p_rebateAmt.add(c_rebateAmt_hindi);
//                    p_rebateAmt.add(c_rebateAmt_1);
//                    p_rebateAmt.add(c_rebateAmt_2);
//                    
//                    
//                    
//                    PdfPTable table9 = new PdfPTable(12); 
//                    table9.setWidths(columnWidths3);
//                    PdfPCell cell16 = new PdfPCell();
//                    cell16.setBorderColor(BaseColor.LIGHT_GRAY);
//                    //cell1.setPaddingLeft(10);
//                    cell16.setBorderWidth(1);
//                    cell16.setPhrase(new Phrase(p_pid));
//                    //cell16.setPhrase(new Phrase("Property ID:  "+bean.getUniqueId(),fontEngH_dd));
//                    cell16.setHorizontalAlignment(Element.ALIGN_LEFT);
//                    cell16.setUseAscender(true);
//                    cell16.setUseDescender(true); 
//                    cell16.setVerticalAlignment(Element.ALIGN_TOP);
//                    cell16.setColspan(3);
//                    table9.addCell(cell16); 
//                    
//                    PdfPCell cell16_3 = new PdfPCell();
//                    cell16_3.setBorderColor(BaseColor.LIGHT_GRAY);
//                    //cell1.setPaddingLeft(10);
//                    cell16_3.setBorderWidth(1);
//                    cell16_3.setPhrase(new Phrase(p_bill));
//                    //cell16_3.setPhrase(new Phrase("Bill No.:  "+bean.getPrivateNotceNo(),fontEngH_dd));
//                    cell16_3.setHorizontalAlignment(Element.ALIGN_LEFT);
//                    cell16_3.setUseAscender(true);
//                    cell16_3.setUseDescender(true);
//                    cell16_3.setVerticalAlignment(Element.ALIGN_TOP);
//                    cell16_3.setColspan(3);
//                    table9.addCell(cell16_3); 
//                    
//                    PdfPCell cell15 = new PdfPCell();
//                    //cell1.setBorderWidthColor(BaseColor.BLUE);
//                    //cell1.setPaddingLeft(10);
//                    cell15.setBorderColor(BaseColor.LIGHT_GRAY);
//                    cell15.setBorderWidth(1);
//                    cell15.setPhrase(p_service);
//                    //cell15.setPhrase(new Phrase("Service Date:  "+bean.getServiceDate(),fontEngH_dd));
//                    cell15.setHorizontalAlignment(Element.ALIGN_LEFT);
//                    cell15.setUseAscender(true);
//                    cell15.setUseDescender(true);
//                    cell15.setVerticalAlignment(Element.ALIGN_TOP);
//                    cell15.setColspan(3);
//                    table9.addCell(cell15);
//                    
//                    PdfPCell cell15_1 = new PdfPCell();
//                    //cell1.setBorderWidthColor(BaseColor.BLUE);
//                    //cell1.setPaddingLeft(10);
//                    cell15_1.setBorderColor(BaseColor.LIGHT_GRAY);
//                    cell15_1.setBorderWidth(1);
//                    cell15_1.setPhrase(new Phrase(p_due));
//                    //cell15_1.setPhrase(new Phrase("Due Date : "+bean.getDueDate(),fontEngH_dd));
//                    cell15_1.setHorizontalAlignment(Element.ALIGN_LEFT);
//                    cell15_1.setUseAscender(true);
//                    cell15_1.setUseDescender(true);
//                    cell15_1.setVerticalAlignment(Element.ALIGN_TOP);
//                    cell15_1.setColspan(3);
//                    table9.addCell(cell15_1);
//                    
//                    document.add(table9);
//                    
//                    
//                    
//                    
//                    PdfPTable table10 = new PdfPTable(12); 
//                    table10.setWidths(columnWidths3);
//                    PdfPCell cell17 = new PdfPCell();
//                    cell17.setBorderColor(BaseColor.LIGHT_GRAY);
//                    //cell1.setBorderWidthColor(BaseColor.BLUE);
//                    //cell1.setPaddingLeft(10);
//                    cell17.setBorderWidth(1);
//                    cell17.setPhrase(p_water);
//                    cell17.setHorizontalAlignment(Element.ALIGN_LEFT);
//                    cell17.setUseAscender(true);
//                    cell17.setUseDescender(true);
//                    cell17.setVerticalAlignment(Element.ALIGN_TOP);
//                    //cell8.setHorizontalAlignment(Element.ALIGN_JUSTIFIED);
//                    cell17.setColspan(6);
//                    table10.addCell(cell17); 
//                    
//                    PdfPCell cell17_1 = new PdfPCell();
//                    cell17_1.setBorderColor(BaseColor.LIGHT_GRAY);
//                    //cell1.setBorderWidthColor(BaseColor.BLUE);
//                    //cell1.setPaddingLeft(10);
//                    cell17_1.setBorderWidth(1);
//                    cell17_1.setPhrase(p_sewerage);
//                    cell17_1.setHorizontalAlignment(Element.ALIGN_LEFT);
//                    cell17_1.setUseAscender(true);
//                    cell17_1.setUseDescender(true);
//                    cell17_1.setVerticalAlignment(Element.ALIGN_TOP);
//                    //cell8.setHorizontalAlignment(Element.ALIGN_JUSTIFIED);
//                    cell17_1.setColspan(6);
//                    table10.addCell(cell17_1); 
//                    document.add(table10);
//                    
//                    
//                    PdfPTable table10_1 = new PdfPTable(12); 
//                    table10_1.setWidths(columnWidths3);
//                    PdfPCell cell18 = new PdfPCell();
//                    cell18.setBorderColor(BaseColor.LIGHT_GRAY);
//                    //cell1.setBorderWidthColor(BaseColor.BLUE);
//                    //cell1.setPaddingLeft(10);
//                    cell18.setBorderWidth(1);
//                    cell18.setPhrase(p_professional);
//                    cell18.setHorizontalAlignment(Element.ALIGN_LEFT);
//                    //cell8.setHorizontalAlignment(Element.ALIGN_JUSTIFIED);
//                    cell18.setUseAscender(true);
//                    cell18.setUseDescender(true);
//                    cell18.setVerticalAlignment(Element.ALIGN_TOP);
//                    cell18.setColspan(6);
//                    table10_1.addCell(cell18); 
//                    
//                    PdfPCell cell18_1 = new PdfPCell();
//                    cell18_1.setBorderColor(BaseColor.LIGHT_GRAY);
//                    //cell1.setBorderWidthColor(BaseColor.BLUE);
//                    //cell1.setPaddingLeft(10);
//                    cell18_1.setBorderWidth(1);
//                    cell18_1.setPhrase(new Phrase(p_Sanitation));
//                    cell18_1.setHorizontalAlignment(Element.ALIGN_LEFT);
//                    //cell8.setHorizontalAlignment(Element.ALIGN_JUSTIFIED);
//                    cell18_1.setUseAscender(true);
//                    cell18_1.setUseDescender(true);
//                    cell18_1.setVerticalAlignment(Element.ALIGN_TOP);
//                    cell18_1.setColspan(6);
//                    table10_1.addCell(cell18_1); 
//                    document.add(table10_1);
//                    
//                    
//                    
//                    PdfPTable table11 = new PdfPTable(12); 
//                    table11.setWidths(columnWidths4);
//                    PdfPCell cell19 = new PdfPCell();
//                    cell19.setBorderColor(BaseColor.LIGHT_GRAY);
//                    //cell1.setBorderWidthColor(BaseColor.BLUE);
//                    //cell1.setPaddingLeft(10);
//                    cell19.setBorderWidth(1);
//                    cell19.setPhrase(p_arrear);
//                    //cell19.setPhrase(new Phrase("Arrear Amount: Rs. "+bean.getArrearAmount(),fontEngH_dd));
//                    cell19.setHorizontalAlignment(Element.ALIGN_LEFT);
//                    //cell8.setHorizontalAlignment(Element.ALIGN_JUSTIFIED);
//                    cell19.setUseAscender(true);
//                    cell19.setUseDescender(true);
//                    cell19.setVerticalAlignment(Element.ALIGN_MIDDLE);
//                    cell19.setColspan(4);
//                    table11.addCell(cell19); 
//                    
//                    PdfPCell cell20_1 = new PdfPCell();
//                    cell20_1.setBorderColor(BaseColor.LIGHT_GRAY);
//                    //cell1.setBorderWidthColor(BaseColor.BLUE);
//                    //cell1.setPaddingLeft(10);
//                    cell20_1.setBorderWidth(1);
//                    cell20_1.setPhrase(p_demand);
//                    //cell20_1.setPhrase(new Phrase("Demand Amount (2018-2019): Rs. "+bean.getTax(),fontEngH_dd));
//                    cell20_1.setHorizontalAlignment(Element.ALIGN_LEFT);
//                    //cell8.setHorizontalAlignment(Element.ALIGN_JUSTIFIED);
//                    cell20_1.setUseAscender(true);
//                    cell20_1.setUseDescender(true);
//                    cell20_1.setVerticalAlignment(Element.ALIGN_TOP);
//                    cell20_1.setColspan(4);
//                    table11.addCell(cell20_1); 
//                    
//                    PdfPCell cell19_1_4 = new PdfPCell();
//                    cell19_1_4.setBorderColor(BaseColor.LIGHT_GRAY);
//                    //cell1.setBorderWidthColor(BaseColor.BLUE);
//                    //cell1.setPaddingLeft(10);
//                    cell19_1_4.setBorderWidth(1);
//                    
//                    cell19_1_4.setPhrase(p_payable);
//                    //cell19_1_4.setPhrase(new Phrase("Payable Amount:  Rs. "+bean.getPayableAmount(),fontEngH_dd));
//                    cell19_1_4.setHorizontalAlignment(Element.ALIGN_LEFT);
//                    //cell8.setHorizontalAlignment(Element.ALIGN_JUSTIFIED);
//                    cell19_1_4.setUseAscender(true);
//                    cell19_1_4.setUseDescender(true);
//                    cell19_1_4.setVerticalAlignment(Element.ALIGN_TOP);
//                    cell19_1_4.setColspan(4);
//                    table11.addCell(cell19_1_4); 
//                    document.add(table11);
//                    
//                   //int rb=(int)Math.round(Double.parseDouble(bean.getPayableAmount())*.99);
//                    
//                    
//                    PdfPTable table11_2_4_5 = new PdfPTable(12); 
//                    table11_2_4_5.setWidths(columnWidths3);
//                    PdfPCell cell19_1_4_5 = new PdfPCell();
//                    cell19_1_4_5.setBorderColor(BaseColor.LIGHT_GRAY);
//                    //cell1.setBorderWidthColor(BaseColor.BLUE);
//                    //cell1.setPaddingLeft(10);
//                    cell19_1_4_5.setBorderWidth(1);
//                    cell19_1_4_5.setPhrase(p_rebateAmt);
//                    //cell19_1_4_5.setPhrase(new Phrase("After Rebate Payable Amount (1% rebate on payable amount)",fontEngH_dd));
//                    cell19_1_4_5.setHorizontalAlignment(Element.ALIGN_LEFT);
//                    //cell8.setHorizontalAlignment(Element.ALIGN_JUSTIFIED);
//                    cell19_1_4_5.setUseAscender(true);
//                    cell19_1_4_5.setUseDescender(true);
//                    cell19_1_4_5.setVerticalAlignment(Element.ALIGN_TOP);
//                    cell19_1_4_5.setColspan(8);
//                    table11_2_4_5.addCell(cell19_1_4_5); 
//                    
//                    PdfPCell cell20_1_4_5_1 = new PdfPCell();
//                    cell20_1_4_5_1.setBorderColor(BaseColor.LIGHT_GRAY);
//                    //cell1.setBorderWidthColor(BaseColor.BLUE);
//                    //cell1.setPaddingLeft(10);
//                    cell20_1_4_5_1.setBorderWidth(1);
//                    cell20_1_4_5_1.setPhrase(new Phrase(" ",fontEngH_dd));
//                    cell20_1_4_5_1.setHorizontalAlignment(Element.ALIGN_LEFT);
//                    //cell8.setHorizontalAlignment(Element.ALIGN_JUSTIFIED);
//                    cell20_1_4_5_1.setVerticalAlignment(Element.ALIGN_MIDDLE);
//                    cell20_1_4_5_1.setColspan(4);
//                    table11_2_4_5.addCell(cell20_1_4_5_1); 
//                    document.add(table11_2_4_5);
//                    
//                    
//                    PdfPTable table26_1 = new PdfPTable(12); 
//                    PdfPCell cell39_1 = new PdfPCell();
//                    //cell1.setBorderWidthColor(BaseColor.BLUE);
//                    //cell1.setPaddingLeft(10);
//                    cell39_1.setBorderWidth(0);
//                    cell39_1.setPhrase(new Phrase("",fontEngH_dd));
//                    cell39_1.setHorizontalAlignment(Element.ALIGN_LEFT);
//                    cell39_1.setVerticalAlignment(Element.ALIGN_MIDDLE);
//                    cell39_1.setColspan(12);
//                    table26_1.addCell(cell39_1); 
//                    document.add(table26_1);
//                    
//                    
////                    PdfPTable table26 = new PdfPTable(6); 
////                    PdfPCell cell39 = new PdfPCell();
////                    //cell1.setBorderWidthColor(BaseColor.BLUE);
////                    //cell1.setPaddingLeft(10);
////                    cell39.setBorderWidth(0);
////                    cell39.setPhrase(new Phrase("Silvassa Municipal Council\n(It is a system generated bill which does not need a signature) ",fontEngH_dd));
////                    cell39.setHorizontalAlignment(Element.ALIGN_LEFT);
////                    cell39.setVerticalAlignment(Element.ALIGN_MIDDLE);
////                    cell39.setColspan(6);
////                    table26.addCell(cell39); 
////                    document.add(table26);
//                    
//                    //According to The Dadra And Nagar Haveli Municipal Council Regulation ,2004, Section 141(3), if a person pays the sum within the fifteen days of period of service date, the municipal will given 1% rebate on whole sum. If he doesn’t pay the sum within the period 1% rebate will not be given to the person. 
//                    
//                    
//                    PdfPTable table75 = new PdfPTable(12); 
//                    PdfPCell cell78 = new PdfPCell();
//                    //cell1.setBorderWidthColor(BaseColor.BLUE);
//                    //cell1.setPaddingLeft(10);
//                    cell78.setBorderWidth(0);
//                    cell78.setPhrase(new Phrase(" ",fontEngH_8_1));
//                    cell78.setHorizontalAlignment(Element.ALIGN_CENTER);
//                    cell78.setVerticalAlignment(Element.ALIGN_MIDDLE);
//                    cell78.setColspan(12);
//                    table75.addCell(cell78); 
//                    document.add(table75);
//                    
//                    
//                    
//                PdfPTable table20 = new PdfPTable(12);
//                //table5.setWidths(columnWidths);
//                PdfPCell cell71 = new PdfPCell();
//                        //cell1.setBorderColor(BaseColor.BLUE);
//                //cell1.setPaddingLeft(10);
//                cell71.setBorderWidth(1);
//                cell71.setBorderColor(BaseColor.GRAY);
//                //cell1.setBackgroundColor(BaseColor.LIGHT_GRAY);
//                cell71.setPhrase(new Phrase("Silvassa Municipal Council, Opposite Udyog Bhawan Secretariat Road, Amli, U.T. of Dadra & Nagar Haveli, Silvassa – 396230\nHelp Line Number: 0260-2633192  : Email-Id: silvassamunicipalcouncil@gmail.com  \nTiming 09:30 AM to 06:00 PM. ", fontEngH_2));
//                        //cell68.setBorderColor(BaseColor.LIGHT_GRAY);
//                //cell68.setGrayFill(0.9f);
//                cell71.setHorizontalAlignment(Element.ALIGN_CENTER);
//                //cell71.setVerticalAlignment(Element.ALIGN_CENTER);
//                cell71.setColspan(12);
//                table20.addCell(cell71);
//                document.add(table20);
//                    
//                    
//                    
//                    
//                    
//                    
//                    
//                    
//                    
//                    
//                    
//                    
//                    
//                 
//                 
//                    
//                    
//                    //System.out.println("last");
//                    //document.close();
//                    //writer.close();
//                
//                
//                    sno++;
//                    index++;
//                    sp = "";
//                    op = "";
//                    oldmc = "";
//                    document.newPage();
//                    
//                
//
//            }
//}
//}
