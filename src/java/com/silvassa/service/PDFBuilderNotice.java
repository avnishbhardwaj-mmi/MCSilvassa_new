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
import com.itextpdf.text.Document;
import com.itextpdf.text.Element;
import com.itextpdf.text.Font;
import com.itextpdf.text.FontFactory;
import com.itextpdf.text.Image;
import com.itextpdf.text.PageSize;
import com.itextpdf.text.Paragraph;
import com.itextpdf.text.Phrase;
import com.itextpdf.text.pdf.BaseFont;
import com.itextpdf.text.pdf.GrayColor;
import com.itextpdf.text.pdf.PdfPCell;
import com.itextpdf.text.pdf.PdfPTable;
import com.itextpdf.text.pdf.PdfWriter;
import com.silvassa.bean.PrivateNoticeBean;
import java.io.FileOutputStream;
import java.text.DecimalFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.HashSet;
import java.util.Iterator;
import com.itextpdf.text.Chunk;
//import com.itextpdf.text.List;
import com.itextpdf.text.ListItem;

/**
 *
 * @author CEINFO
 */
public class PDFBuilderNotice extends AbstractITextPdfNoticeView {

    @Override
    protected void buildPdfDocument(Map<String, Object> model, Document document,
            PdfWriter writer, HttpServletRequest request, HttpServletResponse response)
            throws Exception {
        
        
        try{
        
        // get data model which is passed by the Spring container
        List<PrivateNoticeBean> beanList = (List<PrivateNoticeBean>) model.get("taxList");
        Iterator itr = beanList.iterator();
        //System.out.println("PDFBuilder "+beanList.size());
        String path = request.getRealPath("/res/img/logo2.png");

             //Document document = new Document(PageSize.A4,0,0,10,0);
        //PdfWriter writer = PdfWriter.getInstance(document, new FileOutputStream("d://noticetest.pdf"));
        //document.open();
        Image image1 = Image.getInstance(path);
        image1.scalePercent(20f);
        
        //Image image2 = Image.getInstance("d:\\img\\23.png");
        int columnWidths[] = {4, 2, 2, 3, 1, 3, 4, 3, 3, 1, 2, 2};
        int columnWidths1[] = {2,3,3,3, 3, 2, 2, 3, 3, 3, 3, 2,4};
        int columnWidths2[] = {4, 2, 2, 3, 5, 3, 3, 3, 3, 1, 2, 2};
        SimpleDateFormat sdf = new SimpleDateFormat(("dd-MM-yyyy hh:mm:ss"));
        String date2 = (String) sdf.format(Calendar.getInstance().getTime());
        date2 = date2.substring(0, 10);
        DecimalFormat df = new DecimalFormat("#,###");
        Calendar c = Calendar.getInstance();
        c.add(Calendar.DAY_OF_MONTH, 30);
        String newDate = sdf.format(c.getTime());
        newDate = newDate.substring(0, 10);
        String fontPathGujrati=request.getRealPath("/res/fonts/wwgj0101.ttf");
        String fontPathHindi1=request.getRealPath("/res/fonts/Krutidev_055.TTF");
        String fontPathHindi2=request.getRealPath("/res/fonts/mangal.ttf");
        

        BaseFont bf_gujrati = BaseFont.createFont(fontPathGujrati, BaseFont.IDENTITY_H, BaseFont.EMBEDDED);
        BaseFont bf = BaseFont.createFont(fontPathHindi1, BaseFont.IDENTITY_H, BaseFont.EMBEDDED);
        BaseFont bf_mangal = BaseFont.createFont(fontPathHindi2, BaseFont.IDENTITY_H, BaseFont.EMBEDDED);

        //BaseFont bf_gujrati = BaseFont.createFont("c:\\windows\\fonts\\wwgj0101.TTF", BaseFont.IDENTITY_H, BaseFont.EMBEDDED);
        //BaseFont bf = BaseFont.createFont("c:\\windows\\fonts\\kruti-dev-021.ttf", BaseFont.IDENTITY_H, BaseFont.EMBEDDED);
        //BaseFont bf_mangal = BaseFont.createFont("c:\\windows\\fonts\\mangal.ttf", BaseFont.IDENTITY_H, BaseFont.EMBEDDED);

        Font fontGujrati = new Font(bf_gujrati, 11, Font.NORMAL);
        Font fontHindi = new Font(bf, 18, Font.BOLD);
        Font fontHindi_1 = new Font(bf, 15, Font.BOLD);
        Font fontHindi_2 = new Font(bf, 8, Font.NORMAL);
        Font fontHindi_2k = new Font(bf, 9, Font.NORMAL);
        Font fontEngH = new Font(Font.FontFamily.TIMES_ROMAN, 13, Font.BOLD);
        Font fontEngH_1 = new Font(Font.FontFamily.TIMES_ROMAN, 12, Font.BOLD);
        Font fontEngH_2k = new Font(Font.FontFamily.TIMES_ROMAN, 10, Font.NORMAL);
        Font fontEngH_2ky = new Font(Font.FontFamily.TIMES_ROMAN, 11, Font.BOLD);
        Font fontEngH_2 = new Font(Font.FontFamily.TIMES_ROMAN, 7, Font.NORMAL);
        Font fontEngH_3 = new Font(Font.FontFamily.TIMES_ROMAN, 18, Font.BOLD);
        fontEngH_3.setColor(BaseColor.GRAY);
        Font fontEngH_4 = new Font(Font.FontFamily.TIMES_ROMAN, 10, Font.NORMAL);
        Font fontEngH_5 = new Font(Font.FontFamily.TIMES_ROMAN, 12, Font.NORMAL);
        Font fontEngH_6 = new Font(Font.FontFamily.TIMES_ROMAN, 4, Font.NORMAL);
        Font fontEngH_7 = new Font(Font.FontFamily.TIMES_ROMAN, 7, Font.NORMAL);
        Font fontEngH_8 = new Font(Font.FontFamily.TIMES_ROMAN, 7, Font.BOLD);
        Font fontEngH_rule = new Font(Font.FontFamily.TIMES_ROMAN, 11, Font.NORMAL);
        //Font fontHindi_rule=new Font(bf,10,Font.NORMAL);
        Font fontHindi_rule = new Font(bf, 11, Font.NORMAL);
        Font fontEngH_4k = new Font(Font.FontFamily.TIMES_ROMAN, 7, Font.BOLD);
        Font fontEngH_9 = new Font(Font.FontFamily.TIMES_ROMAN, 2, Font.NORMAL);
        Font fontEngH_11 = new Font(Font.FontFamily.TIMES_ROMAN, 7, Font.NORMAL);
        Font fontEngH_4k_eng = new Font(Font.FontFamily.TIMES_ROMAN, 9, Font.NORMAL);
        

        while (itr.hasNext()) {
            String sp = "";
            String op = "";
            String oldmc = "";
            int index = 1;
            int sno = 1;
            String electricData = "";
            String stFloor = "";
            String stCover = "";
            String stBuse = "";
            String stCategoryDetail = "";
            String stContructionType = "";
            String stSelfRent = "";
            String stLocationClassess = "";
            String stAnnualRent = "";
            String stAnnualRatabaleValue = "";
            String stTax = "";
            String stTaxRate = "";
            String stActualRentValue="";
            String finalRatableValue="";

            PrivateNoticeBean bean = (PrivateNoticeBean) itr.next();
            PdfPTable table = new PdfPTable(12); // 3 columns.
            table.setWidthPercentage(88); //Width 100%
            //table.setSpacingBefore(10f); //Space before table
            //table.setSpacingAfter(10f); //Space after table

                    //Set Column widths
            //int columnWidths[]={2,1,4,3,8,9,10,9,8,7,8,6};
            //float[] columnWidths = {1f, 1f, 1f,1f,1f,1f,1f,1f,1f,1f,1f,1f};
            table.setWidths(columnWidths2);

            PdfPTable inntertable = new PdfPTable(5);
            //inntertable.setWidths(columnWidths2);
            PdfPCell innercell1 = new PdfPCell();
            innercell1.setBorderWidth(0);
            innercell1.setPhrase(new Phrase("Silvassa Municipal Council ", fontEngH));
                   //innercell1.setHorizontalAlignment(Element.ALIGN_LEFT);
            //innercell1.setVerticalAlignment(Element.ALIGN_TOP);
            innercell1.setColspan(5);
            //innercell1.setRowspan(2);
            inntertable.addCell(innercell1);
                   //table.addCell(inntertable);

            PdfPTable inntertable1 = new PdfPTable(5);
            //inntertable1.setWidths(columnWidths2);
            innercell1 = new PdfPCell();
            innercell1.setBorderWidth(0);
            innercell1.setPhrase(new Phrase("Property Tax Assessment Notice (2019-2020) ", fontEngH_2k));
                   //innercell1.setHorizontalAlignment(Element.ALIGN_LEFT);
            //innercell1.setVerticalAlignment(Element.ALIGN_TOP);
            innercell1.setColspan(5);
            //innercell1.setRowspan(2);
            inntertable1.addCell(innercell1);
                   //table.addCell(inntertable1);

            PdfPTable inntertable2 = new PdfPTable(5);
            //inntertable2.setWidths(columnWidths2);
            innercell1 = new PdfPCell();
            innercell1.setBorderWidth(0);
            innercell1.setPhrase(new Phrase("www.smcdnh.nic.in ", fontEngH_2ky));
                   //innercell1.setHorizontalAlignment(Element.ALIGN_LEFT);
            //innercell1.setVerticalAlignment(Element.ALIGN_TOP);
            innercell1.setColspan(5);
            //innercell1.setRowspan(2);
            inntertable2.addCell(innercell1);
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
            cell1.addElement(inntertable1);
            cell1.addElement(inntertable2);
            //cell1.setRowspan(2);
            table.addCell(cell1);

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
            PdfPCell cell2 = new PdfPCell(image1);
                    //cell1.setBorderColor(BaseColor.BLUE);
            //cell1.setPaddingLeft(10);
            cell2.setBorderWidth(0);
            
                    //cell2.setBorderColor(BaseColor.BLACK);
            //cell2.setBackgroundColor(BaseColor.LIGHT_GRAY);
            cell2.setHorizontalAlignment(Element.ALIGN_LEFT);
            //cell2.setVerticalAlignment(Element.ALIGN_TOP);
            cell2.setColspan(2);

            PdfPTable inntertableHindi = new PdfPTable(5);
            //inntertable.setWidths(columnWidths2);
            innercell1 = new PdfPCell();
            innercell1.setBorderWidth(0);
            innercell1.setPhrase(new Phrase("flyoklk uxj ikfydk", fontHindi_1));
                   //innercell1.setHorizontalAlignment(Element.ALIGN_LEFT);
            //innercell1.setVerticalAlignment(Element.ALIGN_TOP);
            innercell1.setColspan(5);
            //innercell1.setRowspan(2);
            inntertableHindi.addCell(innercell1);
                   //table.addCell(inntertable);

            PdfPTable inntertableHindi1 = new PdfPTable(5);
            //inntertable1.setWidths(columnWidths2);
            innercell1 = new PdfPCell();
            innercell1.setBorderWidth(0);
            innercell1.setPhrase(new Phrase("laifRr dj vkadyu uksfVl ¼2019&2020½ ", fontHindi_2));
                   //innercell1.setHorizontalAlignment(Element.ALIGN_LEFT);
            //innercell1.setVerticalAlignment(Element.ALIGN_TOP);
            innercell1.setColspan(5);
            //innercell1.setRowspan(2);
            inntertableHindi1.addCell(innercell1);
                   //table.addCell(inntertable1);

            PdfPTable inntertableHindi2 = new PdfPTable(5);
            //inntertable2.setWidths(columnWidths2);
            innercell1 = new PdfPCell();
            innercell1.setBorderWidth(0);
            innercell1.setPhrase(new Phrase("  ", fontEngH_2));
                   //innercell1.setHorizontalAlignment(Element.ALIGN_LEFT);
            //innercell1.setVerticalAlignment(Element.ALIGN_TOP);
            innercell1.setColspan(5);
            //innercell1.setRowspan(2);
            inntertableHindi2.addCell(innercell1);

            PdfPCell cell3 = new PdfPCell();
                    //cell3.setBorderColor(BaseColor.BLACK);
            //cell1.setPaddingLeft(10);
            cell3.setBorderWidth(0);
                    //cell3.setBackgroundColor(BaseColor.LIGHT_GRAY);
            //cell3.setPhrase(new Phrase("flyoklk uxj ifj’kn \n2018-2019",fontHindi_1 ));
            cell3.setHorizontalAlignment(Element.ALIGN_CENTER);
            cell3.setVerticalAlignment(Element.ALIGN_TOP);
            cell3.setColspan(5);
            cell3.addElement(inntertableHindi);
            cell3.addElement(inntertableHindi1);
            cell3.addElement(inntertableHindi2);

            //table.addCell(cellr);
            table.addCell(cell2);
            table.addCell(cell3);
            document.add(table);

            PdfPTable table1 = new PdfPTable(12);
            table1.setWidths(columnWidths);
            PdfPCell cell4 = new PdfPCell();
                    //cell1.setBorderColor(BaseColor.BLUE);
            //cell1.setPaddingLeft(10);
            cell4.setBorderWidth(0);
                   // cell1.setBorderColor(BaseColor.BLACK);
            //cell1.setBackgroundColor(BaseColor.LIGHT_GRAY);
            cell4.setPhrase(new Phrase("", fontEngH_9));
            cell4.setHorizontalAlignment(Element.ALIGN_LEFT);
            cell4.setVerticalAlignment(Element.ALIGN_TOP);
            cell4.setColspan(12);
            table1.addCell(cell4);
            document.add(table1);

            PdfPTable table3 = new PdfPTable(12);
            //table3.setWidths(columnWidths);
            PdfPCell cell5 = new PdfPCell();
                    //cell1.setBorderColor(BaseColor.BLUE);
            //cell1.setPaddingLeft(10);
            cell5.setBorderWidth(1);
            Chunk j1=new Chunk("vpvfp A“¡ “Nf lh¡gu “Nf ”pqgLp Aq’q“ed 2004 “u ’pfp 111 A“¡ ’pfp 115 D”’pfp (1) A“¡ (2) “p A“yk»’p“¡ k¡ghpk “Nf ”pqgLp dp» Aph¡g kdõs ”°p¡”Vu® “y» kh¡® ”wZ® ‘e¡g R¡. Mpk sdp¡“¡ qh“»su R¡ L¡ Ap k»”qÑ “u “p¡qVk Ýep“ ”wh®L hp»Qu Ap k»”qÑ “u dpqlsu “p¡qVk dp» vip®h¡g kwQ“p dyÄb Lp¡C”Z ”°Lpf “p¡ hp»’p¡ A‘hp cwg dm¡ sp¡ ky’pfp¡ Lfhp sd¡ ky’pfZp ap¡d® cfu “¡ 30 qvhk “u A»vf “Nf ”pqgLp“u Ap¡qak A‘hp Ap¡“ gpC“",fontGujrati);
            Chunk j2=new Chunk(" www.smcdnh.nic.in ",fontEngH_4);
            Chunk j3=new Chunk("“p dpÝed ‘u ky’pfZp ap¡d® Ädp Lfu iLip¡. Ap ky’pfZp ap¡d",fontGujrati);
            Chunk j4=new Chunk(" www.smcdnh.nic.in ",fontEngH_4);
            Chunk j5=new Chunk("h¡bkpCV ”f ”Z D”gå’ R¡. k»”qÑ“u Ahgp¡L“ k»¿epdp sdpfp Öpfp hp»’p A“¡ ky’pfZp ap¡d® 30 qvhk “u A»vf Ädp “lu » Lfhpdp» Aph¡ sp¡ ”°p¡”Vu® kh¡® “u dpqlsu kpQu dp“hdp» Aphi¡. ",fontGujrati);
            
            Phrase pj=new Phrase();
            pj.add(j1);
            pj.add(j2);
            pj.add(j3);
            pj.add(j4);
            pj.add(j5);
            
                   // cell1.setBorderColor(BaseColor.BLACK);
            //cell1.setBackgroundColor(BaseColor.LIGHT_GRAY);
            cell5.setPhrase(pj);
            //+cell5.setPhrase(new Phrase("vpvfp A“¡ “Nf lh¡gu “Nf ”pqgLp Aq’q“ed 2004 “u ’pfp 111 A“¡ ’pfp 115 D”’pfp (1) A“¡ (2) “p A“yk»’p“¡ k¡ghpk “Nf ”pqgLp dp» Aph¡g kdõs ”°p¡”Vu® “y» kh¡® ”wZ® ‘e¡g R¡. Mpk sdp¡“¡ qh“»su R¡ L¡ Ap k»”qÑ “u “p¡qVk Ýep“ ”wh®L hp»Qu Ap k»”qÑ “u dpqlsu “p¡qVk dp» vip®h¡g kwQ“p dyÄb Lp¡C”Z ”°Lpf “p¡ hp»’p¡ A‘hp cwg dm¡ sp¡ ky’pfp¡ Lfhp sd¡ ky’pfZp ap¡d® cfu “¡ 30 qvhk “u A»vf “Nf ”pqgLp“u Ap¡qak A‘hp Ap¡“ gpC“ smcdnh.nic.in “p dpÝed ‘u ky’pfZp ap¡d® Ädp Lfu iLip¡. Ap ky’pfZp ap¡d® Map My India.in h¡bkpCV ”f ”Z D”gå’ R¡. k»”qÑ“u Ahgp¡L“ k»¿epdp sdpfp Öpfp hp»’p A“¡ ky’pfZp ap¡d® 30 qvhk “u A»vf Ädp “lu » Lfhpdp» Aph¡ sp¡ ”°p¡”Vu® kh¡® “u dpqlsu kpQu dp“hdp» Aphi¡. ", fontGujrati));
            cell5.setPadding(8);
            cell5.setHorizontalAlignment(Element.ALIGN_JUSTIFIED);
            cell5.setVerticalAlignment(Element.ALIGN_TOP);
            cell5.setBorderColor(BaseColor.LIGHT_GRAY);
            cell5.setColspan(6);
            table3.addCell(cell5);

            PdfPCell cell6 = new PdfPCell();
                    //cell1.setBorderColor(BaseColor.BLUE);
            //cell1.setPaddingLeft(10);
            cell6.setBorderWidth(1);
            Chunk c1=new Chunk("nknjk ,oa uxj gosyh uxj ikfydk vf/kfu;e 2004 dhs /kkjk 111rFkk /kkjk 115 dhs mi/kkjk ¼1½ ,oa ¼2½ ds varxZr flyoklk uxj ikfydk esa leLr izkWaiVhZ losZ dk dke iw.kZ gks pqdk gSA vr% vki lc ls vuqjks/k gS fd bl laifRr dj vkadyu uksfVl dks /;kuiwoZd i<s+ A bl laifRr dj vkadyu uksfVl esa n'kkZbZ xbZ lwpuk esa fdlh Òh izdkj dh vkifRr@ =qfV ik, tkus ij lq/kkj gsrw vki la'kks/ku QkeZ Hkj dj 30 fnu ds Hkhrj uxj ikfydk ds n¶rj esa rFkk vkWauykbu",fontHindi_rule);
            Chunk c2=new Chunk(" www.smcdnh.nic.in ",fontEngH_4);
            Chunk c3=new Chunk("ds ek/;e ls Hkh tek dj ldrs gSA ;g la'kks/ku QkeZ uxj ikfydk dh osclkbV",fontHindi_rule);
            Chunk c4=new Chunk(" www.smcdnh.nic.in ",fontEngH_4);
            Chunk c5=new Chunk("ij Hkh miyC/k gSA laifRr dj vkadyu dk ;g MkVk] tks bl uksfVl esa fn;k x;k gSA vkids }kjk vkifRr ;k vuqj¨/k 30 fnu ds Hkhrj uk vkus ij lgh eku fy;k tk,xkA",fontHindi_rule);
            Phrase p1=new Phrase();
            p1.add(c1);
            p1.add(c2);
            p1.add(c3);
            p1.add(c4);
            p1.add(c5);
                   // cell1.setBorderColor(BaseColor.BLACK);
            //cell1.setBackgroundColor(BaseColor.LIGHT_GRAY);
            cell6.setPhrase(p1);
            //cell6.setPhrase(new Phrase("nknjk ,oa uxj gosyh uxj ikfydk vf/kfu;e 2004 dhs /kkjk 111rFkk /kkjk 115 dhs mi /kkjk ¼1½ ,oa ¼2½ ds varxZr flyoklk uxj ikfydk esa leLr izkWaiVhZ losZ dk dke iw.kZ gks pqdk gSA vr% vki lc ls vuqjks/k gS fd bl laifRr dj vkadyu uksfVl dks /;kuiwoZd i<s+ A bl laifRr dj vkadyu uksfVl esa n'kkZbZ xbZ lwpuk esa fdlh Òh izdkj dh vkifRr@ =qfV ik, tkus ij lq/kkj gsrw vki la'kks/ku QkeZ Hkj dj 30 fnu ds Hkhrj uxj ikfydk ds n¶rj esa rFkk vkWauykbu               ds ek/;e ls Hkh tek djk ldrs gSA ;g la'kks/ku QkeZ uxj ikfydk dh osclkbV            ij Hkh miyC/k gSA laifRr dj vkadyu dk ;g MkVk] tks bl uksfVl esa fn;k x;k gSA vkids }kjk vkifRr ;k vuqj¨/k 30 fnu ds Hkhrj uk vkus ij lgh eku fy;k tk,xkA  ", fontHindi_rule));
            cell6.setHorizontalAlignment(Element.ALIGN_JUSTIFIED);
            cell6.setVerticalAlignment(Element.ALIGN_TOP);
            cell6.setPadding(8);
            cell6.setColspan(6);
            cell6.setBorderColor(BaseColor.LIGHT_GRAY);
            table3.addCell(cell6);

            document.add(table3);

            PdfPTable table4 = new PdfPTable(12);
            //table4.setWidths(columnWidths);
            PdfPCell cell7 = new PdfPCell();
                    //cell1.setBorderColor(BaseColor.BLUE);
            //cell1.setPaddingLeft(10);
            cell7.setBorderWidth(0);;
                   // cell1.setBorderColor(BaseColor.BLACK);
            //cell1.setBackgroundColor(BaseColor.LIGHT_GRAY);
            cell7.setPhrase(new Phrase(" ", fontEngH_6));
            cell7.setHorizontalAlignment(Element.ALIGN_LEFT);
            cell7.setVerticalAlignment(Element.ALIGN_MIDDLE);
            cell7.setColspan(12);
            //cell7.setBorderColor(BaseColor.LIGHT_GRAY);
            table4.addCell(cell7);
            document.add(table4);

            PdfPTable table5 = new PdfPTable(12);
            //table5.setWidths(columnWidths);
            PdfPCell cell8 = new PdfPCell();
                    //cell1.setBorderColor(BaseColor.BLUE);
            //cell1.setPaddingLeft(10);
            cell8.setBorderWidth(1);
                   // cell1.setBorderColor(BaseColor.BLACK);
            //cell1.setBackgroundColor(BaseColor.LIGHT_GRAY);
            cell8.setPhrase(new Phrase("Owner Details", fontEngH_1));
            cell8.setBorderColor(BaseColor.LIGHT_GRAY);
            cell8.setGrayFill(0.9f);
            cell8.setHorizontalAlignment(Element.ALIGN_CENTER);
            cell8.setVerticalAlignment(Element.ALIGN_CENTER);
            cell8.setColspan(12);
            table5.addCell(cell8);
            document.add(table5);

            PdfPTable table6 = new PdfPTable(12);
            table6.setWidthPercentage(100);
            table6.setWidths(columnWidths);
            PdfPCell cell9 = new PdfPCell();
                    //cell9.rectangle(0, 100);
            //cell9.setBorderColor(BaseColor.BLUE);
            //cell9.setPaddingLeft(0);
            cell9.setBorderWidth(0);
            // cell1.setBorderColor(BaseColor.BLACK);
            //cell1.setBackgroundColor(BaseColor.LIGHT_GRAY);
            cell9.setPhrase(new Phrase("Property Id", fontEngH_2));
            //cell9.setBorderColor(BaseColor.LIGHT_GRAY);
                    //cell9.setGrayFill(0.9f);

            cell9.setHorizontalAlignment(Element.ALIGN_LEFT);
            //cell9.setVerticalAlignment(Element.ALIGN_CENTER);
            cell9.setColspan(1);
            table6.addCell(cell9);
                    //document.add(table6);

            PdfPCell cell10 = new PdfPCell();
                    //cell1.setBorderColor(BaseColor.BLUE);
            //cell1.setPaddingLeft(10);
            cell10.setBorderWidth(0);
                   // cell1.setBorderColor(BaseColor.BLACK);
            //cell1.setBackgroundColor(BaseColor.LIGHT_GRAY);
            cell10.setPhrase(new Phrase("" + bean.getUniqueId(), fontEngH_2));//ak
            //cell10.setPhrase(new Phrase("S07000111000" , fontEngH_2));
            //cell10.setBorderColor(BaseColor.LIGHT_GRAY);
            //cell9.setGrayFill(0.9f);
            cell10.setHorizontalAlignment(Element.ALIGN_LEFT);
            //cell9.setVerticalAlignment(Element.ALIGN_CENTER);
            cell10.setColspan(2);
            table6.addCell(cell10);
                    //document.add(table6);

            PdfPCell cell11 = new PdfPCell();
                    //cell1.setBorderColor(BaseColor.BLUE);
            //cell1.setPaddingLeft(10);
            cell11.setBorderWidth(0);
                   // cell1.setBorderColor(BaseColor.BLACK);
            //cell1.setBackgroundColor(BaseColor.LIGHT_GRAY);
            cell11.setPhrase(new Phrase("Notice No.", fontEngH_2));
            //cell11.setBorderColor(BaseColor.LIGHT_GRAY);
            //cell9.setGrayFill(0.9f);
            cell11.setHorizontalAlignment(Element.ALIGN_LEFT);
            //cell9.setVerticalAlignment(Element.ALIGN_CENTER);
            cell11.setColspan(1);
            table6.addCell(cell11);
                     //document.add(table6);

            PdfPCell cell12 = new PdfPCell();
                    //cell1.setBorderColor(BaseColor.BLUE);
            //cell1.setPaddingLeft(10);
            cell12.setBorderWidth(0);
                   // cell1.setBorderColor(BaseColor.BLACK);
            //cell1.setBackgroundColor(BaseColor.LIGHT_GRAY);
            //cell12.setPhrase(new Phrase("XXXXXX", fontEngH_2));
            cell12.setPhrase(new Phrase(""+bean.getPrivateNotceNo(), fontEngH_2));//ak
            //cell12.setBorderColor(BaseColor.LIGHT_GRAY);
            //cell9.setGrayFill(0.9f);
            cell12.setHorizontalAlignment(Element.ALIGN_LEFT);
            //cell9.setVerticalAlignment(Element.ALIGN_CENTER);
            cell12.setColspan(2);
            table6.addCell(cell12);
                     //document.add(table6);

            PdfPCell cell13 = new PdfPCell();
                    //cell1.setBorderColor(BaseColor.BLUE);
            //cell1.setPaddingLeft(10);
            cell13.setBorderWidth(0);
                   // cell1.setBorderColor(BaseColor.BLACK);
            //cell1.setBackgroundColor(BaseColor.LIGHT_GRAY);
            cell13.setPhrase(new Phrase("Service Date", fontEngH_2));
            //cell13.setBorderColor(BaseColor.LIGHT_GRAY);
            //cell9.setGrayFill(0.9f);
            cell13.setHorizontalAlignment(Element.ALIGN_LEFT);
            //cell9.setVerticalAlignment(Element.ALIGN_CENTER);
            cell13.setColspan(1);
            table6.addCell(cell13);
                    //document.add(table6);

            PdfPCell cell14 = new PdfPCell();
                    //cell1.setBorderColor(BaseColor.BLUE);
            //cell1.setPaddingLeft(10);
            cell14.setBorderWidth(0);
                   // cell1.setBorderColor(BaseColor.BLACK);
            //cell1.setBackgroundColor(BaseColor.LIGHT_GRAY);
            //cell14.setPhrase(new Phrase("xx-xx-xxxx" , fontEngH_2));
            cell14.setPhrase(new Phrase("" + bean.getServiceDate(), fontEngH_2));//ak
            //cell14.setBorderColor(BaseColor.LIGHT_GRAY);
            //cell9.setGrayFill(0.9f);
            cell14.setHorizontalAlignment(Element.ALIGN_LEFT);
            //cell9.setVerticalAlignment(Element.ALIGN_CENTER);
            cell14.setColspan(1);
            table6.addCell(cell14);
            //document.add(table6);
            PdfPCell cell15 = new PdfPCell();
                    //cell1.setBorderColor(BaseColor.BLUE);
            //cell1.setPaddingLeft(10);
            cell15.setBorderWidth(0);
                   // cell1.setBorderColor(BaseColor.BLACK);
            //cell1.setBackgroundColor(BaseColor.LIGHT_GRAY);
            cell15.setPhrase(new Phrase("Due Date", fontEngH_2));
            //cell15.setBorderColor(BaseColor.LIGHT_GRAY);
            //cell9.setGrayFill(0.9f);
            cell15.setHorizontalAlignment(Element.ALIGN_LEFT);
            //cell9.setVerticalAlignment(Element.ALIGN_CENTER);
            cell15.setColspan(2);
            table6.addCell(cell15);
            //document.add(table6);
            PdfPCell cell16 = new PdfPCell();
                    //cell1.setBorderColor(BaseColor.BLUE);
            //cell1.setPaddingLeft(10);
            cell16.setBorderWidth(0);
                   // cell1.setBorderColor(BaseColor.BLACK);
            //cell1.setBackgroundColor(BaseColor.LIGHT_GRAY);
            //cell16.setPhrase(new Phrase("xx-xx-xxxx" , fontEngH_2));
            cell16.setPhrase(new Phrase("" + bean.getDueDate(), fontEngH_2));//ak
            //cell16.setBorderColor(BaseColor.LIGHT_GRAY);
            //cell9.setGrayFill(0.9f);
            cell16.setHorizontalAlignment(Element.ALIGN_LEFT);
            //cell9.setVerticalAlignment(Element.ALIGN_CENTER);
            cell16.setColspan(2);
            table6.addCell(cell16);
                     //document.add(table6);

            sp = bean.getOwner_Father();
            op = bean.getOccupier();
            if (bean.getOldMc().trim().length() > 0) {
                oldmc = "REGISTERED";
            } else {
                oldmc = "UNREGISTERED";
            }

            /*if (bean.getPropertyOccupierName().trim().length() > 0 && bean.getPropertyRelationOwner().trim().length() > 0) {
             op = bean.getPropertyOccupierName().trim() + "\n" + bean.getPropertyRelationOwner().trim();
             } else if (bean.getPropertyOccupierName().trim().length() > 0 && bean.getPropertyRelationOwner().trim().length() == 0) {
             op = bean.getPropertyOccupierName();
             } else if (bean.getPropertyOwner().trim().length() == 0 && bean.getPropertyRelationOwner().trim().length() > 0) {
             op = bean.getPropertyRelationOwner();
             }*/
            /*if (bean.getProperty_old_smc_prop_tax_num().trim().length() > 0) {
             oldmc = "REGISTERED";
             } else {
             oldmc = "UNREGISTERED";
             }*/

            /*if (bean.getPropertyContact().trim().length() > 0 && bean.getPropertyOwnerEmail().trim().length() > 0) {
             sp = sp + "\n" + bean.getPropertyContact().trim() + "\n" + bean.getPropertyOwnerEmail().trim();
             } else if (bean.getPropertyContact().trim().length() > 0 && bean.getPropertyOwnerEmail().trim().length() == 0) {
             sp = sp + "\n" + bean.getPropertyContact().trim();
             } else if (bean.getPropertyContact().trim().length() == 0 && bean.getPropertyOwnerEmail().trim().length() > 0) {
             sp = sp + "\n" + bean.getPropertyOwnerEmail().trim();
             }*/
            PdfPTable table7_1=null;
            if(bean.getOldMc().trim().length() == 0){
            table7_1 = new PdfPTable(12);
            table7_1.setWidthPercentage(100);
            table7_1.setWidths(columnWidths);
            PdfPCell cell17_1 = new PdfPCell();
                    //cell1.setBorderColor(BaseColor.BLUE);
            //cell1.setPaddingLeft(10);
            cell17_1.setBorderWidth(0);
                   // cell1.setBorderColor(BaseColor.BLACK);
            //cell1.setBackgroundColor(BaseColor.LIGHT_GRAY);
            cell17_1.setPhrase(new Phrase("Holder's Name", fontEngH_2));
            cell17_1.setBorderColor(BaseColor.LIGHT_GRAY);
            //cell9.setGrayFill(0.9f);
            cell17_1.setHorizontalAlignment(Element.ALIGN_LEFT);
            //cell9.setVerticalAlignment(Element.ALIGN_CENTER);
            cell17_1.setColspan(1);
            table7_1.addCell(cell17_1);

            PdfPCell cell18_1 = new PdfPCell();
                    //cell1.setBorderColor(BaseColor.BLUE);
            //cell1.setPaddingLeft(10);
            cell18_1.setBorderWidth(0);
                   // cell1.setBorderColor(BaseColor.BLACK);
            //cell1.setBackgroundColor(BaseColor.LIGHT_GRAY);
            //cell18.setPhrase(new Phrase("ABC" , fontEngH_2));
            cell18_1.setPhrase(new Phrase("" + bean.getHolderName(), fontEngH_2));//ak
            cell18_1.setBorderColor(BaseColor.LIGHT_GRAY);
            //cell9.setGrayFill(0.9f);
            cell18_1.setHorizontalAlignment(Element.ALIGN_LEFT);
            //cell9.setVerticalAlignment(Element.ALIGN_CENTER);
            cell18_1.setColspan(11);
            table7_1.addCell(cell18_1);
            }
            
            
            PdfPTable table7 = new PdfPTable(12);
            table7.setWidthPercentage(100);
            table7.setWidths(columnWidths);
            PdfPCell cell17 = new PdfPCell();
                    //cell1.setBorderColor(BaseColor.BLUE);
            //cell1.setPaddingLeft(10);
            cell17.setBorderWidth(0);
                   // cell1.setBorderColor(BaseColor.BLACK);
            //cell1.setBackgroundColor(BaseColor.LIGHT_GRAY);
            cell17.setPhrase(new Phrase("Owner's Name", fontEngH_2));
            cell17.setBorderColor(BaseColor.LIGHT_GRAY);
            //cell9.setGrayFill(0.9f);
            cell17.setHorizontalAlignment(Element.ALIGN_LEFT);
            //cell9.setVerticalAlignment(Element.ALIGN_CENTER);
            cell17.setColspan(1);
            table7.addCell(cell17);

            PdfPCell cell18 = new PdfPCell();
                    //cell1.setBorderColor(BaseColor.BLUE);
            //cell1.setPaddingLeft(10);
            cell18.setBorderWidth(0);
                   // cell1.setBorderColor(BaseColor.BLACK);
            //cell1.setBackgroundColor(BaseColor.LIGHT_GRAY);
            //cell18.setPhrase(new Phrase("ABC" , fontEngH_2));
            cell18.setPhrase(new Phrase("" + sp, fontEngH_2));//ak
            cell18.setBorderColor(BaseColor.LIGHT_GRAY);
            //cell9.setGrayFill(0.9f);
            cell18.setHorizontalAlignment(Element.ALIGN_LEFT);
            //cell9.setVerticalAlignment(Element.ALIGN_CENTER);
            cell18.setColspan(11);
            table7.addCell(cell18);
            
            PdfPTable table9 = new PdfPTable(12);
            table9.setWidthPercentage(100);
            table9.setWidths(columnWidths);
            PdfPCell cell21 = new PdfPCell();
                    //cell1.setBorderColor(BaseColor.BLUE);
            //cell1.setPaddingLeft(10);
            cell21.setBorderWidth(0);
                   // cell1.setBorderColor(BaseColor.BLACK);
            //cell1.setBackgroundColor(BaseColor.LIGHT_GRAY);
            cell21.setPhrase(new Phrase("Mobile No.:", fontEngH_2));
            cell21.setBorderColor(BaseColor.LIGHT_GRAY);
            //cell9.setGrayFill(0.9f);
            cell21.setHorizontalAlignment(Element.ALIGN_LEFT);
            //cell9.setVerticalAlignment(Element.ALIGN_CENTER);
            cell21.setColspan(1);
            table9.addCell(cell21);
                    //document.add(table9);

            PdfPCell cell22 = new PdfPCell();
                    //cell1.setBorderColor(BaseColor.BLUE);
            //cell1.setPaddingLeft(10);
            cell22.setBorderWidth(0);
                   // cell1.setBorderColor(BaseColor.BLACK);
            //cell1.setBackgroundColor(BaseColor.LIGHT_GRAY);
            cell22.setPhrase(new Phrase("" + bean.getContact(), fontEngH_2));
            cell22.setBorderColor(BaseColor.LIGHT_GRAY);
            //cell9.setGrayFill(0.9f);
            cell22.setHorizontalAlignment(Element.ALIGN_LEFT);
            //cell9.setVerticalAlignment(Element.ALIGN_CENTER);
            cell22.setColspan(11);
            table9.addCell(cell22);
                    //document.add(table9);

            PdfPTable table10 = new PdfPTable(12);
            table10.setWidthPercentage(100);
            table10.setWidths(columnWidths);
            PdfPCell cell23 = new PdfPCell();
                    //cell1.setBorderColor(BaseColor.BLUE);
            //cell1.setPaddingLeft(10);
            cell23.setBorderWidth(0);
                   // cell1.setBorderColor(BaseColor.BLACK);
            //cell1.setBackgroundColor(BaseColor.LIGHT_GRAY);
            cell23.setPhrase(new Phrase("Email Id:", fontEngH_2));
            cell23.setBorderColor(BaseColor.LIGHT_GRAY);
            //cell9.setGrayFill(0.9f);
            cell23.setHorizontalAlignment(Element.ALIGN_LEFT);
            //cell9.setVerticalAlignment(Element.ALIGN_CENTER);
            cell23.setColspan(1);
            table10.addCell(cell23);

            PdfPCell cell24 = new PdfPCell();
                    //cell1.setBorderColor(BaseColor.BLUE);
            //cell1.setPaddingLeft(10);
            cell24.setBorderWidth(0);
                   // cell1.setBorderColor(BaseColor.BLACK);
            //cell1.setBackgroundColor(BaseColor.LIGHT_GRAY);
            cell24.setPhrase(new Phrase("" + bean.getEmail(), fontEngH_2));
            cell24.setBorderColor(BaseColor.LIGHT_GRAY);
            //cell9.setGrayFill(0.9f);
            cell24.setHorizontalAlignment(Element.ALIGN_LEFT);
            //cell9.setVerticalAlignment(Element.ALIGN_CENTER);
            cell24.setColspan(11);
            table10.addCell(cell24);
            
            PdfPTable table10_3 = new PdfPTable(12);
            table10_3.setWidthPercentage(100);
            table10_3.setWidths(columnWidths);
            PdfPCell cell23_3 = new PdfPCell();
                    //cell1.setBorderColor(BaseColor.BLUE);
            //cell1.setPaddingLeft(10);
            cell23_3.setBorderWidth(0);
                   // cell1.setBorderColor(BaseColor.BLACK);
            //cell1.setBackgroundColor(BaseColor.LIGHT_GRAY);
            cell23_3.setPhrase(new Phrase("Aadhaar No.:", fontEngH_2));
            cell23_3.setBorderColor(BaseColor.LIGHT_GRAY);
            //cell9.setGrayFill(0.9f);
            cell23_3.setHorizontalAlignment(Element.ALIGN_LEFT);
            //cell9.setVerticalAlignment(Element.ALIGN_CENTER);
            cell23_3.setColspan(1);
            table10_3.addCell(cell23_3);

            PdfPCell cell24_3 = new PdfPCell();
                    //cell1.setBorderColor(BaseColor.BLUE);
            //cell1.setPaddingLeft(10);
            cell24_3.setBorderWidth(0);
                   // cell1.setBorderColor(BaseColor.BLACK);
            //cell1.setBackgroundColor(BaseColor.LIGHT_GRAY);
            cell24_3.setPhrase(new Phrase("" + bean.getProperetyOwnerAadhaarNo(), fontEngH_2));
            cell24_3.setBorderColor(BaseColor.LIGHT_GRAY);
            //cell9.setGrayFill(0.9f);
            cell24_3.setHorizontalAlignment(Element.ALIGN_LEFT);
            //cell9.setVerticalAlignment(Element.ALIGN_CENTER);
            cell24_3.setColspan(11);
            table10_3.addCell(cell24_3);

                    //document.add(table7);
            PdfPTable table8 = new PdfPTable(12);
            table8.setWidthPercentage(100);
            table8.setWidths(columnWidths);
            PdfPCell cell19 = new PdfPCell();
                    //cell1.setBorderColor(BaseColor.BLUE);
            //cell1.setPaddingLeft(10);
            cell19.setBorderWidth(0);
                   // cell1.setBorderColor(BaseColor.BLACK);
            //cell1.setBackgroundColor(BaseColor.LIGHT_GRAY);
            cell19.setPhrase(new Phrase("Occupier's Name", fontEngH_2));
            cell19.setBorderColor(BaseColor.LIGHT_GRAY);
            //cell9.setGrayFill(0.9f);
            cell19.setHorizontalAlignment(Element.ALIGN_LEFT);
            //cell9.setVerticalAlignment(Element.ALIGN_CENTER);
            cell19.setColspan(0);
            table8.addCell(cell19);

            PdfPCell cell20 = new PdfPCell();
                    //cell1.setBorderColor(BaseColor.BLUE);
            //cell1.setPaddingLeft(10);
            cell20.setBorderWidth(0);
                   // cell1.setBorderColor(BaseColor.BLACK);
            //cell1.setBackgroundColor(BaseColor.LIGHT_GRAY);
            cell20.setPhrase(new Phrase(" " + op, fontEngH_2));
            cell20.setBorderColor(BaseColor.LIGHT_GRAY);
            //cell9.setGrayFill(0.9f);
            cell20.setHorizontalAlignment(Element.ALIGN_LEFT);
            //cell9.setVerticalAlignment(Element.ALIGN_CENTER);
            cell20.setColspan(11);
            table8.addCell(cell20);
            
            // for occupier detail
            PdfPTable table9_1 = new PdfPTable(12);
            table9_1.setWidthPercentage(100);
            table9_1.setWidths(columnWidths);
            PdfPCell cell21_1 = new PdfPCell();
                    //cell1.setBorderColor(BaseColor.BLUE);
            //cell1.setPaddingLeft(10);
            cell21_1.setBorderWidth(0);
                   // cell1.setBorderColor(BaseColor.BLACK);
            //cell1.setBackgroundColor(BaseColor.LIGHT_GRAY);
            cell21_1.setPhrase(new Phrase("Mobile No.:", fontEngH_2));
            cell21_1.setBorderColor(BaseColor.LIGHT_GRAY);
            //cell9.setGrayFill(0.9f);
            cell21_1.setHorizontalAlignment(Element.ALIGN_LEFT);
            //cell9.setVerticalAlignment(Element.ALIGN_CENTER);
            cell21_1.setColspan(1);
            table9_1.addCell(cell21_1);
                    //document.add(table9);

            PdfPCell cell22_1 = new PdfPCell();
                    //cell1.setBorderColor(BaseColor.BLUE);
            //cell1.setPaddingLeft(10);
            cell22_1.setBorderWidth(0);
                   // cell1.setBorderColor(BaseColor.BLACK);
            //cell1.setBackgroundColor(BaseColor.LIGHT_GRAY);
            cell22_1.setPhrase(new Phrase("" + bean.getOccupier_contactno(), fontEngH_2));
            cell22_1.setBorderColor(BaseColor.LIGHT_GRAY);
            //cell9.setGrayFill(0.9f);
            cell22_1.setHorizontalAlignment(Element.ALIGN_LEFT);
            //cell9.setVerticalAlignment(Element.ALIGN_CENTER);
            cell22_1.setColspan(11);
            table9_1.addCell(cell22_1);
                    //document.add(table9);

            PdfPTable table10_1 = new PdfPTable(12);
            table10_1.setWidthPercentage(100);
            table10_1.setWidths(columnWidths);
            PdfPCell cell23_1 = new PdfPCell();
                    //cell1.setBorderColor(BaseColor.BLUE);
            //cell1.setPaddingLeft(10);
            cell23_1.setBorderWidth(0);
                   // cell1.setBorderColor(BaseColor.BLACK);
            //cell1.setBackgroundColor(BaseColor.LIGHT_GRAY);
            cell23_1.setPhrase(new Phrase("Email Id:", fontEngH_2));
            cell23_1.setBorderColor(BaseColor.LIGHT_GRAY);
            //cell9.setGrayFill(0.9f);
            cell23_1.setHorizontalAlignment(Element.ALIGN_LEFT);
            //cell9.setVerticalAlignment(Element.ALIGN_CENTER);
            cell23_1.setColspan(1);
            table10_1.addCell(cell23_1);

            PdfPCell cell24_1 = new PdfPCell();
                    //cell1.setBorderColor(BaseColor.BLUE);
            //cell1.setPaddingLeft(10);
            cell24_1.setBorderWidth(0);
                   // cell1.setBorderColor(BaseColor.BLACK);
            //cell1.setBackgroundColor(BaseColor.LIGHT_GRAY);
            cell24_1.setPhrase(new Phrase("" + bean.getOccupier_email(), fontEngH_2));
            cell24_1.setBorderColor(BaseColor.LIGHT_GRAY);
            //cell9.setGrayFill(0.9f);
            cell24_1.setHorizontalAlignment(Element.ALIGN_LEFT);
            //cell9.setVerticalAlignment(Element.ALIGN_CENTER);
            cell24_1.setColspan(11);
            table10_1.addCell(cell24_1);
            
            PdfPTable table10_2 = new PdfPTable(12);
            table10_2.setWidthPercentage(100);
            table10_2.setWidths(columnWidths);
            PdfPCell cell23_2 = new PdfPCell();
                    //cell1.setBorderColor(BaseColor.BLUE);
            //cell1.setPaddingLeft(10);
            cell23_2.setBorderWidth(0);
                   // cell1.setBorderColor(BaseColor.BLACK);
            //cell1.setBackgroundColor(BaseColor.LIGHT_GRAY);
            cell23_2.setPhrase(new Phrase("Aadhaar No.:", fontEngH_2));
            cell23_2.setBorderColor(BaseColor.LIGHT_GRAY);
            //cell9.setGrayFill(0.9f);
            cell23_2.setHorizontalAlignment(Element.ALIGN_LEFT);
            //cell9.setVerticalAlignment(Element.ALIGN_CENTER);
            cell23_2.setColspan(1);
            table10_2.addCell(cell23_2);

            PdfPCell cell24_2 = new PdfPCell();
                    //cell1.setBorderColor(BaseColor.BLUE);
            //cell1.setPaddingLeft(10);
            cell24_2.setBorderWidth(0);
                   // cell1.setBorderColor(BaseColor.BLACK);
            //cell1.setBackgroundColor(BaseColor.LIGHT_GRAY);
            cell24_2.setPhrase(new Phrase("" + bean.getProperetyOccupierAadhaarNo(), fontEngH_2));
            cell24_2.setBorderColor(BaseColor.LIGHT_GRAY);
            //cell9.setGrayFill(0.9f);
            cell24_2.setHorizontalAlignment(Element.ALIGN_LEFT);
            //cell9.setVerticalAlignment(Element.ALIGN_CENTER);
            cell24_2.setColspan(11);
            table10_2.addCell(cell24_2);
                    //document.add(table8);

            
                    //document.add(table10);

            PdfPTable table11 = new PdfPTable(12);
            table11.setWidthPercentage(100);
            table11.setWidths(columnWidths);
            PdfPCell cell25 = new PdfPCell();
                    //cell1.setBorderColor(BaseColor.BLUE);
            //cell1.setPaddingLeft(10);
            cell25.setBorderWidth(0);
                   // cell1.setBorderColor(BaseColor.BLACK);
            //cell1.setBackgroundColor(BaseColor.LIGHT_GRAY);
            cell25.setPhrase(new Phrase("Address:", fontEngH_2));
            cell25.setBorderColor(BaseColor.LIGHT_GRAY);
            //cell9.setGrayFill(0.9f);
            cell25.setHorizontalAlignment(Element.ALIGN_LEFT);
            //cell9.setVerticalAlignment(Element.ALIGN_CENTER);
            cell25.setColspan(1);
            table11.addCell(cell25);

            PdfPCell cell26 = new PdfPCell();
                    //cell1.setBorderColor(BaseColor.BLUE);
            //cell1.setPaddingLeft(10);
            cell26.setBorderWidth(0);
                   // cell1.setBorderColor(BaseColor.BLACK);
            //cell1.setBackgroundColor(BaseColor.LIGHT_GRAY);
            //cell26.setPhrase(new Phrase("" , fontEngH_2));
            cell26.setPhrase(new Phrase("" + bean.getAddress(), fontEngH_2));//ak
            cell26.setBorderColor(BaseColor.LIGHT_GRAY);
            //cell9.setGrayFill(0.9f);
            cell26.setHorizontalAlignment(Element.ALIGN_LEFT);
            //cell9.setVerticalAlignment(Element.ALIGN_CENTER);
            cell26.setColspan(11);
            table11.addCell(cell26);
                    //document.add(table11);

            PdfPTable table12 = new PdfPTable(12);
            table12.setWidthPercentage(100);
            table12.setWidths(columnWidths);
            PdfPCell cell27 = new PdfPCell();
                    //cell1.setBorderColor(BaseColor.BLUE);
            //cell1.setPaddingLeft(10);
            cell27.setBorderWidth(0);
                   // cell1.setBorderColor(BaseColor.BLACK);
            //cell1.setBackgroundColor(BaseColor.LIGHT_GRAY);
            cell27.setPhrase(new Phrase("SMC House Property No.", fontEngH_2));
            cell27.setBorderColor(BaseColor.LIGHT_GRAY);
            //cell9.setGrayFill(0.9f);
            cell27.setHorizontalAlignment(Element.ALIGN_LEFT);
            //cell9.setVerticalAlignment(Element.ALIGN_CENTER);
            cell27.setColspan(2);
            table12.addCell(cell27);

            PdfPCell cell28 = new PdfPCell();
                    //cell1.setBorderColor(BaseColor.BLUE);
            //cell1.setPaddingLeft(10);
            cell28.setBorderWidth(0);
                   // cell1.setBorderColor(BaseColor.BLACK);
            //cell1.setBackgroundColor(BaseColor.LIGHT_GRAY);
            cell28.setPhrase(new Phrase("" + bean.getOldMc(), fontEngH_2));
            cell28.setBorderColor(BaseColor.LIGHT_GRAY);
            //cell9.setGrayFill(0.9f);
            cell28.setHorizontalAlignment(Element.ALIGN_LEFT);
            //cell9.setVerticalAlignment(Element.ALIGN_CENTER);
            cell28.setColspan(6);
            table12.addCell(cell28);

            PdfPCell cell29 = new PdfPCell();
                    //cell1.setBorderColor(BaseColor.BLUE);
            //cell1.setPaddingLeft(10);
            cell29.setBorderWidth(0);
                   // cell1.setBorderColor(BaseColor.BLACK);
            //cell1.setBackgroundColor(BaseColor.LIGHT_GRAY);
            cell29.setPhrase(new Phrase("Ward No.", fontEngH_2));
            cell29.setBorderColor(BaseColor.LIGHT_GRAY);
            //cell9.setGrayFill(0.9f);
            cell29.setHorizontalAlignment(Element.ALIGN_RIGHT);
            //cell9.setVerticalAlignment(Element.ALIGN_CENTER);
            cell29.setColspan(2);
            table12.addCell(cell29);

            PdfPCell cell30 = new PdfPCell();
                    //cell1.setBorderColor(BaseColor.BLUE);
            //cell1.setPaddingLeft(10);
            cell30.setBorderWidth(0);
                   // cell1.setBorderColor(BaseColor.BLACK);
            //cell1.setBackgroundColor(BaseColor.LIGHT_GRAY);
            //cell30.setPhrase(new Phrase("" , fontEngH_2));
            cell30.setPhrase(new Phrase("" + bean.getWard(), fontEngH_2));//ak
            cell30.setBorderColor(BaseColor.LIGHT_GRAY);
            //cell9.setGrayFill(0.9f);
            cell30.setHorizontalAlignment(Element.ALIGN_LEFT);
            //cell9.setVerticalAlignment(Element.ALIGN_CENTER);
            cell30.setColspan(2);
            table12.addCell(cell30);

                    //document.add(table12);
            PdfPTable table11_1 = new PdfPTable(12);
            table11_1.setWidthPercentage(100);
            table11_1.setWidths(columnWidths);
            PdfPCell cell25_1 = new PdfPCell();
                    //cell1.setBorderColor(BaseColor.BLUE);
            //cell1.setPaddingLeft(10);
            cell25_1.setBorderWidth(0);
                   // cell1.setBorderColor(BaseColor.BLACK);
            //cell1.setBackgroundColor(BaseColor.LIGHT_GRAY);
            cell25_1.setPhrase(new Phrase("Arrear Upto (fin. Yr.18-19)", fontEngH_2));
            cell25_1.setBorderColor(BaseColor.LIGHT_GRAY);
            //cell9.setGrayFill(0.9f);
            cell25_1.setHorizontalAlignment(Element.ALIGN_LEFT);
            //cell9.setVerticalAlignment(Element.ALIGN_CENTER);
            cell25_1.setColspan(2);
            table11_1.addCell(cell25_1);

            PdfPCell cell26_1 = new PdfPCell();
                    //cell1.setBorderColor(BaseColor.BLUE);
            //cell1.setPaddingLeft(10);
            cell26_1.setBorderWidth(0);
                   // cell1.setBorderColor(BaseColor.BLACK);
            //cell1.setBackgroundColor(BaseColor.LIGHT_GRAY);
            cell26_1.setPhrase(new Phrase("" + bean.getArrearAmount(), fontEngH_2));
            cell26_1.setBorderColor(BaseColor.LIGHT_GRAY);
            //cell9.setGrayFill(0.9f);
            cell26_1.setHorizontalAlignment(Element.ALIGN_LEFT);
            //cell9.setVerticalAlignment(Element.ALIGN_CENTER);
            cell26_1.setColspan(4);
            table11_1.addCell(cell26_1);
            
            
            PdfPCell cell26_1_2_3 = new PdfPCell();
                    //cell1.setBorderColor(BaseColor.BLUE);
            //cell1.setPaddingLeft(10);
            cell26_1_2_3.setBorderWidth(0);
                   // cell1.setBorderColor(BaseColor.BLACK);
            //cell1.setBackgroundColor(BaseColor.LIGHT_GRAY);
            cell26_1_2_3.setPhrase(new Phrase("Old Tax (from april-19 to septmeber-19)" , fontEngH_2));
            cell26_1_2_3.setBorderColor(BaseColor.LIGHT_GRAY);
            //cell9.setGrayFill(0.9f);
            cell26_1_2_3.setHorizontalAlignment(Element.ALIGN_LEFT);
            //cell9.setVerticalAlignment(Element.ALIGN_CENTER);
            cell26_1_2_3.setColspan(4);
            table11_1.addCell(cell26_1_2_3);
            
            PdfPCell cell26_1_3 = new PdfPCell();
                    //cell1.setBorderColor(BaseColor.BLUE);
            //cell1.setPaddingLeft(10);
            cell26_1_3.setBorderWidth(0);
                   // cell1.setBorderColor(BaseColor.BLACK);
            //cell1.setBackgroundColor(BaseColor.LIGHT_GRAY);
            cell26_1_3.setPhrase(new Phrase("" + bean.getOldTax(), fontEngH_2));
            cell26_1_3.setBorderColor(BaseColor.LIGHT_GRAY);
            //cell9.setGrayFill(0.9f);
            cell26_1_3.setHorizontalAlignment(Element.ALIGN_LEFT);
            //cell9.setVerticalAlignment(Element.ALIGN_CENTER);
            cell26_1_3.setColspan(2);
            table11_1.addCell(cell26_1_3);
            
            
            
            String meterCon[]=bean.getPf_electric_con_num();
            HashSet<String> meterC=new HashSet<String>();
            String electricS="";
            String eletricValue="";
            String mm="";
            if(meterCon!=null){
                for(int j=0;j<meterCon.length;j++){
                   electricS=meterCon[j].trim();
                   meterC.add(electricS);
                }
            
             Iterator itrMeter=meterC.iterator();
             while(itrMeter.hasNext()){
                 eletricValue=(String)itrMeter.next();
                 mm=mm+eletricValue+",";
             }
            mm=mm.substring(0,mm.length()-1);
            }
            
            // for meter connection
            PdfPTable table11_1_2 = new PdfPTable(12);
            table11_1_2.setWidthPercentage(100);
            table11_1_2.setWidths(columnWidths);
            PdfPCell cell25_1_2 = new PdfPCell();
                    //cell1.setBorderColor(BaseColor.BLUE);
            //cell1.setPaddingLeft(10);
            cell25_1_2.setBorderWidth(0);
                   // cell1.setBorderColor(BaseColor.BLACK);
            //cell1.setBackgroundColor(BaseColor.LIGHT_GRAY);
            cell25_1_2.setPhrase(new Phrase("Electric Service Connection No.", fontEngH_2));
            cell25_1_2.setBorderColor(BaseColor.LIGHT_GRAY);
            //cell9.setGrayFill(0.9f);
            cell25_1_2.setHorizontalAlignment(Element.ALIGN_LEFT);
            //cell9.setVerticalAlignment(Element.ALIGN_CENTER);
            cell25_1_2.setColspan(3);
            table11_1_2.addCell(cell25_1_2);

            PdfPCell cell26_1_2 = new PdfPCell();
                    //cell1.setBorderColor(BaseColor.BLUE);
            //cell1.setPaddingLeft(10);
            cell26_1_2.setBorderWidth(0);
                   // cell1.setBorderColor(BaseColor.BLACK);
            //cell1.setBackgroundColor(BaseColor.LIGHT_GRAY);
            cell26_1_2.setPhrase(new Phrase("" + mm, fontEngH_2));
            cell26_1_2.setBorderColor(BaseColor.LIGHT_GRAY);
            //cell9.setGrayFill(0.9f);
            cell26_1_2.setHorizontalAlignment(Element.ALIGN_LEFT);
            //cell9.setVerticalAlignment(Element.ALIGN_CENTER);
            cell26_1_2.setColspan(9);
            table11_1_2.addCell(cell26_1_2);
            
                    //document.add(table11_1);
           
            // ashok
            PdfPTable tablebig = new PdfPTable(12);
//                    tablebig.setWidthPercentage(70);
            //tablebig.setWidths(columnWidths);
            PdfPCell cell31big = new PdfPCell();
                    //cell1.setBorderColor(BaseColor.BLUE);
            //cell1.setPaddingLeft(10);

            cell31big.setBorderWidth(1);
            cell31big.setBorderColor(BaseColor.LIGHT_GRAY);
                    //cell8.setBorderColor(BaseColor.LIGHT_GRAY);
            //cell8.setGrayFill(0.9f);
            //cell1.setBackgroundColor(BaseColor.LIGHT_GRAY);
            //cell31big.setPhrase(new Phrase(" ",fontEngH_6 ));
//                    cell31big.setHorizontalAlignment(Element.ALIGN_LEFT);
//                    cell31big.setPadding(10);
            //cell31big.setVerticalAlignment(Element.ALIGN_MIDDLE);
            cell31big.setColspan(12);
            cell31big.addElement(table6);
            cell31big.addElement(table7);
            if(bean.getOldMc().trim().length() == 0){
            cell31big.addElement(table7_1);
            }
            cell31big.addElement(table9);
            cell31big.addElement(table10);
            cell31big.addElement(table10_3);
            cell31big.addElement(table8);
            cell31big.addElement(table9_1);
            cell31big.addElement(table10_1);
            cell31big.addElement(table10_2);
            
            cell31big.addElement(table11);
            cell31big.addElement(table12);
            cell31big.addElement(table11_1);
            cell31big.addElement(table11_1_2);
            
            
            
            
            

            //cell7.setBorderColor(BaseColor.LIGHT_GRAY);
            tablebig.addCell(cell31big);
            document.add(tablebig);

            PdfPTable table13 = new PdfPTable(12);
            //table4.setWidths(columnWidths);
            PdfPCell cell31 = new PdfPCell();
                    //cell1.setBorderColor(BaseColor.BLUE);
            //cell1.setPaddingLeft(10);
            cell31.setBorderWidth(0);
                   // cell1.setBorderColor(BaseColor.BLACK);
            //cell1.setBackgroundColor(BaseColor.LIGHT_GRAY);
            cell31.setPhrase(new Phrase("", fontEngH_9));
            cell31.setHorizontalAlignment(Element.ALIGN_LEFT);
            cell31.setVerticalAlignment(Element.ALIGN_MIDDLE);
            cell31.setColspan(12);
            //cell7.setBorderColor(BaseColor.LIGHT_GRAY);
            table13.addCell(cell31);
            document.add(table13);

            PdfPTable table14 = new PdfPTable(13);
            table14.setWidths(columnWidths1);
            PdfPCell cell32 = new PdfPCell();
                    //cell1.setBorderColor(BaseColor.BLUE);
            //cell1.setPaddingLeft(10);
            cell32.setBorderWidth(1);
                   // cell1.setBorderColor(BaseColor.BLACK);
            //cell1.setBackgroundColor(BaseColor.LIGHT_GRAY);
            cell32.setPhrase(new Phrase("A", fontEngH_7));
            cell32.setBorderColor(BaseColor.LIGHT_GRAY);
            //cell9.setGrayFill(0.9f);
            cell32.setHorizontalAlignment(Element.ALIGN_CENTER);
            //cell9.setVerticalAlignment(Element.ALIGN_CENTER);
            cell32.setColspan(1);
            table14.addCell(cell32);

            PdfPCell cell33 = new PdfPCell();
                    //cell1.setBorderColor(BaseColor.BLUE);
            //cell1.setPaddingLeft(10);
            cell33.setBorderWidth(1);
                   // cell1.setBorderColor(BaseColor.BLACK);
            //cell1.setBackgroundColor(BaseColor.LIGHT_GRAY);
            cell33.setPhrase(new Phrase("B", fontEngH_7));
            cell33.setBorderColor(BaseColor.LIGHT_GRAY);
            //cell9.setGrayFill(0.9f);
            cell33.setHorizontalAlignment(Element.ALIGN_CENTER);
            //cell9.setVerticalAlignment(Element.ALIGN_CENTER);
            cell33.setColspan(1);
            table14.addCell(cell33);

            PdfPCell cell34 = new PdfPCell();
                    //cell1.setBorderColor(BaseColor.BLUE);
            //cell1.setPaddingLeft(10);
            cell34.setBorderWidth(1);
                   // cell1.setBorderColor(BaseColor.BLACK);
            //cell1.setBackgroundColor(BaseColor.LIGHT_GRAY);
            cell34.setPhrase(new Phrase("C", fontEngH_7));
            cell34.setBorderColor(BaseColor.LIGHT_GRAY);
            //cell9.setGrayFill(0.9f);
            cell34.setHorizontalAlignment(Element.ALIGN_CENTER);
            //cell9.setVerticalAlignment(Element.ALIGN_CENTER);
            cell34.setColspan(1);
            table14.addCell(cell34);

            PdfPCell cell35 = new PdfPCell();
                    //cell1.setBorderColor(BaseColor.BLUE);
            //cell1.setPaddingLeft(10);
            cell35.setBorderWidth(1);
                   // cell1.setBorderColor(BaseColor.BLACK);
            //cell1.setBackgroundColor(BaseColor.LIGHT_GRAY);
            cell35.setPhrase(new Phrase("D", fontEngH_7));
            cell35.setBorderColor(BaseColor.LIGHT_GRAY);
            //cell9.setGrayFill(0.9f);
            cell35.setHorizontalAlignment(Element.ALIGN_CENTER);
            //cell9.setVerticalAlignment(Element.ALIGN_CENTER);
            cell35.setColspan(1);
            table14.addCell(cell35);

            PdfPCell cell36 = new PdfPCell();
                    //cell1.setBorderColor(BaseColor.BLUE);
            //cell1.setPaddingLeft(10);
            cell36.setBorderWidth(1);
                   // cell1.setBorderColor(BaseColor.BLACK);
            //cell1.setBackgroundColor(BaseColor.LIGHT_GRAY);
            cell36.setPhrase(new Phrase("E", fontEngH_7));
            cell36.setBorderColor(BaseColor.LIGHT_GRAY);
            //cell9.setGrayFill(0.9f);
            cell36.setHorizontalAlignment(Element.ALIGN_CENTER);
            //cell9.setVerticalAlignment(Element.ALIGN_CENTER);
            cell36.setColspan(1);
            table14.addCell(cell36);

            PdfPCell cell37 = new PdfPCell();
                    //cell1.setBorderColor(BaseColor.BLUE);
            //cell1.setPaddingLeft(10);
            cell37.setBorderWidth(1);
                   // cell1.setBorderColor(BaseColor.BLACK);
            //cell1.setBackgroundColor(BaseColor.LIGHT_GRAY);
            cell37.setPhrase(new Phrase("F", fontEngH_7));
            cell37.setBorderColor(BaseColor.LIGHT_GRAY);
            //cell9.setGrayFill(0.9f);
            cell37.setHorizontalAlignment(Element.ALIGN_CENTER);
            //cell9.setVerticalAlignment(Element.ALIGN_CENTER);
            cell37.setColspan(1);
            table14.addCell(cell37);

            PdfPCell cell38 = new PdfPCell();
                    //cell1.setBorderColor(BaseColor.BLUE);
            //cell1.setPaddingLeft(10);
            cell38.setBorderWidth(1);
                   // cell1.setBorderColor(BaseColor.BLACK);
            //cell1.setBackgroundColor(BaseColor.LIGHT_GRAY);
            cell38.setPhrase(new Phrase("G", fontEngH_7));
            cell38.setBorderColor(BaseColor.LIGHT_GRAY);
            //cell9.setGrayFill(0.9f);
            cell38.setHorizontalAlignment(Element.ALIGN_CENTER);
            //cell9.setVerticalAlignment(Element.ALIGN_CENTER);
            cell38.setColspan(1);
            table14.addCell(cell38);

            PdfPCell cell39 = new PdfPCell();
                    //cell1.setBorderColor(BaseColor.BLUE);
            //cell1.setPaddingLeft(10);
            cell39.setBorderWidth(1);
                   // cell1.setBorderColor(BaseColor.BLACK);
            //cell1.setBackgroundColor(BaseColor.LIGHT_GRAY);
            cell39.setPhrase(new Phrase("H", fontEngH_7));
            cell39.setBorderColor(BaseColor.LIGHT_GRAY);
            //cell9.setGrayFill(0.9f);
            cell39.setHorizontalAlignment(Element.ALIGN_CENTER);
            //cell9.setVerticalAlignment(Element.ALIGN_CENTER);
            cell39.setColspan(1);
            table14.addCell(cell39);

            PdfPCell cell40 = new PdfPCell();
                    //cell1.setBorderColor(BaseColor.BLUE);
            //cell1.setPaddingLeft(10);
            cell40.setBorderWidth(1);
                   // cell1.setBorderColor(BaseColor.BLACK);
            //cell1.setBackgroundColor(BaseColor.LIGHT_GRAY);
            cell40.setPhrase(new Phrase("I", fontEngH_7));
            cell40.setBorderColor(BaseColor.LIGHT_GRAY);
            //cell9.setGrayFill(0.9f);
            cell40.setHorizontalAlignment(Element.ALIGN_CENTER);
            //cell9.setVerticalAlignment(Element.ALIGN_CENTER);
            cell40.setColspan(1);
            table14.addCell(cell40);

            PdfPCell cell41 = new PdfPCell();
                    //cell1.setBorderColor(BaseColor.BLUE);
            //cell1.setPaddingLeft(10);
            cell41.setBorderWidth(1);
                   // cell1.setBorderColor(BaseColor.BLACK);
            //cell1.setBackgroundColor(BaseColor.LIGHT_GRAY);
            cell41.setPhrase(new Phrase("J", fontEngH_7));
            cell41.setBorderColor(BaseColor.LIGHT_GRAY);
            //cell9.setGrayFill(0.9f);
            cell41.setHorizontalAlignment(Element.ALIGN_CENTER);
            //cell9.setVerticalAlignment(Element.ALIGN_CENTER);
            cell41.setColspan(1);
            table14.addCell(cell41);

            
            PdfPCell cell42 = new PdfPCell();
                    //cell1.setBorderColor(BaseColor.BLUE);
            //cell1.setPaddingLeft(10);
            cell42.setBorderWidth(1);
                   // cell1.setBorderColor(BaseColor.BLACK);
            //cell1.setBackgroundColor(BaseColor.LIGHT_GRAY);
            cell42.setPhrase(new Phrase("K", fontEngH_7));
            cell42.setBorderColor(BaseColor.LIGHT_GRAY);
            //cell9.setGrayFill(0.9f);
            cell42.setHorizontalAlignment(Element.ALIGN_CENTER);
            //cell9.setVerticalAlignment(Element.ALIGN_CENTER);
            cell42.setColspan(1);
            table14.addCell(cell42);

            PdfPCell cell43 = new PdfPCell();
                    //cell1.setBorderColor(BaseColor.BLUE);
            //cell1.setPaddingLeft(10);
            cell43.setBorderWidth(1);
                   // cell1.setBorderColor(BaseColor.BLACK);
            //cell1.setBackgroundColor(BaseColor.LIGHT_GRAY);
            cell43.setPhrase(new Phrase("L", fontEngH_7));
            cell43.setBorderColor(BaseColor.LIGHT_GRAY);
            //cell9.setGrayFill(0.9f);
            cell43.setHorizontalAlignment(Element.ALIGN_CENTER);
            //cell9.setVerticalAlignment(Element.ALIGN_CENTER);
            cell43.setColspan(1);
            table14.addCell(cell43);
            
                 //    new line
//             PdfPTable table17_2 = new PdfPTable(12);
//            table17_2.setSpacingAfter(0f);
//            PdfPCell cell74 = new PdfPCell();
//            cell74.setBorderWidth(0);
//            cell74.setPhrase(new Phrase("", fontHindi_2));
//            cell74.setHorizontalAlignment(Element.ALIGN_CENTER);
//            cell74.setVerticalAlignment(Element.ALIGN_CENTER);
//            cell74.setColspan(12);
//            table17_2.addCell(cell74);
//            document.add(table17_2);
            Font zapfdingbatsab = new Font();
            Chunk bulletaa = new Chunk("\u2022", zapfdingbatsab);
            Chunk t11=new Chunk(" The tax mentioned in the following table is only for assessment. This is not a tax payment notice. In case of any arrear/objections/suggestions, the same may be submitted to Silvassa Municipal Council.", fontEngH_4k);
            Phrase ptaa=new Phrase();
            ptaa.add(bulletaa);
            ptaa.add(t11);
            PdfPTable table18_1_5_3 = new PdfPTable(12);
            table18_1_5_3.setSpacingBefore(0f);
            //table18_1_5_3.setSpacingAfter(-1f);
            PdfPCell table18_1_5_4 = new PdfPCell();
            table18_1_5_4.setBorderWidth(0);
            table18_1_5_4.setPhrase(ptaa);
            table18_1_5_4.setHorizontalAlignment(Element.ALIGN_JUSTIFIED);
            table18_1_5_4.setColspan(12);
            table18_1_5_3.addCell(table18_1_5_4);
            document.add(table18_1_5_3); 
            Chunk t12=new Chunk(";g laifRr dj vkadyu uksfVl dsoy laifRr dj olwyh ls lEcfU/kr gSA ;g fdlh Hkh izdkj ls feyfd;r ;k dCTks dk lcwr ugh gSA vr% bl uksfVl dks uk rks feyfd;r ;k dCTks ds lcwr ds rkSj ij izLrqr fd;k tk ldrk gS uk iz;ksx fd;k tk ldrk gSA", fontHindi_2k);
            Phrase pt8=new Phrase();
            pt8.add(t12);
            
            PdfPTable table18_1_45 = new PdfPTable(12);
            PdfPCell cell69_1_45 = new PdfPCell();
            cell69_1_45.setBorderWidth(0);
            cell69_1_45.setPhrase(pt8);
            cell69_1_45.setPhrase(new Phrase("bl rkfydk esa mfYyfz[kr laifRr dj ek= vkadyu gsrq fy[kk x;k gSA ;g laifRr dj vnk;xh uksfVl ugha gSA fdlh Òh vkifRr] =qfV] lq>ko dh voLFkk esa mls uxj ikfydk dks Ikszf”kr djsaA ", fontHindi_2k));
            cell69_1_45.setHorizontalAlignment(Element.ALIGN_JUSTIFIED);
            cell69_1_45.setColspan(12);
            table18_1_45.addCell(cell69_1_45);
            document.add(table18_1_45);
            table18_1_45.setSpacingBefore(0f);
            table18_1_45.setSpacingAfter(-1f);
            
            PdfPTable table18_1_45ggf = new PdfPTable(12);
            PdfPCell cell69_1_45gfjgfj = new PdfPCell();
            cell69_1_45gfjgfj.setBorderWidth(0);
            cell69_1_45gfjgfj.setPhrase(pt8);
            cell69_1_45gfjgfj.setPhrase(new Phrase(""));
            cell69_1_45gfjgfj.setHorizontalAlignment(Element.ALIGN_JUSTIFIED);
            cell69_1_45gfjgfj.setColspan(12);
            table18_1_45ggf.addCell(cell69_1_45gfjgfj);
            document.add(table18_1_45ggf);
            
            //new
            PdfPCell cell43_1 = new PdfPCell();
                    //cell1.setBorderColor(BaseColor.BLUE);
            //cell1.setPaddingLeft(10);
            cell43_1.setBorderWidth(1);
                   // cell1.setBorderColor(BaseColor.BLACK);
            //cell1.setBackgroundColor(BaseColor.LIGHT_GRAY);
            cell43_1.setPhrase(new Phrase("M", fontEngH_7));
            cell43_1.setBorderColor(BaseColor.LIGHT_GRAY);
            //cell9.setGrayFill(0.9f);
            cell43_1.setHorizontalAlignment(Element.ALIGN_CENTER);
            //cell9.setVerticalAlignment(Element.ALIGN_CENTER);
            cell43_1.setColspan(1);
            table14.addCell(cell43_1);
            
            document.add(table14);
             
            PdfPTable table15 = new PdfPTable(13);
            table15.setWidths(columnWidths1);
            PdfPCell cell44 = new PdfPCell();
                    //cell1.setBorderColor(BaseColor.BLUE);
            //cell1.setPaddingLeft(10);
            cell44.setBorderWidth(1);
                   // cell1.setBorderColor(BaseColor.BLACK);
            //cell1.setBackgroundColor(BaseColor.LIGHT_GRAY);
            cell44.setPhrase(new Phrase("Floor\n(Base/GF/1F\n2F...)", fontEngH_7));
            cell44.setBorderColor(BaseColor.LIGHT_GRAY);
            //cell9.setGrayFill(0.9f);
            cell44.setHorizontalAlignment(Element.ALIGN_CENTER);
            //cell9.setVerticalAlignment(Element.ALIGN_CENTER);
            cell44.setColspan(1);
            table15.addCell(cell44);

            PdfPCell cell45 = new PdfPCell();
                    //cell1.setBorderColor(BaseColor.BLUE);
            //cell1.setPaddingLeft(10);
            cell45.setBorderWidth(1);
                   // cell1.setBorderColor(BaseColor.BLACK);
            //cell1.setBackgroundColor(BaseColor.LIGHT_GRAY);
            cell45.setPhrase(new Phrase("Floor wise\nCovered/\nBuilt up\nArea", fontEngH_7));
            cell45.setBorderColor(BaseColor.LIGHT_GRAY);
            //cell9.setGrayFill(0.9f);
            cell45.setHorizontalAlignment(Element.ALIGN_CENTER);
            //cell9.setVerticalAlignment(Element.ALIGN_CENTER);
            cell45.setColspan(1);
            table15.addCell(cell45);

            PdfPCell cell46 = new PdfPCell();
                    //cell1.setBorderColor(BaseColor.BLUE);
            //cell1.setPaddingLeft(10);
            cell46.setBorderWidth(1);
                   // cell1.setBorderColor(BaseColor.BLACK);
            //cell1.setBackgroundColor(BaseColor.LIGHT_GRAY);
            cell46.setPhrase(new Phrase("Use of the\nProperty", fontEngH_7));
            cell46.setBorderColor(BaseColor.LIGHT_GRAY);
            //cell9.setGrayFill(0.9f);
            cell46.setHorizontalAlignment(Element.ALIGN_CENTER);
            //cell9.setVerticalAlignment(Element.ALIGN_CENTER);
            cell46.setColspan(1);
            table15.addCell(cell46);

            PdfPCell cell47 = new PdfPCell();
                    //cell1.setBorderColor(BaseColor.BLUE);
            //cell1.setPaddingLeft(10);
            cell47.setBorderWidth(1);
                   // cell1.setBorderColor(BaseColor.BLACK);
            //cell1.setBackgroundColor(BaseColor.LIGHT_GRAY);
            cell47.setPhrase(new Phrase("Property\nCategory", fontEngH_7));
            cell47.setBorderColor(BaseColor.LIGHT_GRAY);
            //cell9.setGrayFill(0.9f);
            cell47.setHorizontalAlignment(Element.ALIGN_CENTER);
            //cell9.setVerticalAlignment(Element.ALIGN_CENTER);
            cell47.setColspan(1);
            table15.addCell(cell47);

            PdfPCell cell48 = new PdfPCell();
                    //cell1.setBorderColor(BaseColor.BLUE);
            //cell1.setPaddingLeft(10);
            cell48.setBorderWidth(1);
                   // cell1.setBorderColor(BaseColor.BLACK);
            //cell1.setBackgroundColor(BaseColor.LIGHT_GRAY);
            cell48.setPhrase(new Phrase("Type of\nConstruction", fontEngH_7));
            cell48.setBorderColor(BaseColor.LIGHT_GRAY);
            //cell9.setGrayFill(0.9f);
            cell48.setHorizontalAlignment(Element.ALIGN_CENTER);
            //cell9.setVerticalAlignment(Element.ALIGN_CENTER);
            cell48.setColspan(1);
            table15.addCell(cell48);

            PdfPCell cell49 = new PdfPCell();
                    //cell1.setBorderColor(BaseColor.BLUE);
            //cell1.setPaddingLeft(10);
            cell49.setBorderWidth(1);
                   // cell1.setBorderColor(BaseColor.BLACK);
            //cell1.setBackgroundColor(BaseColor.LIGHT_GRAY);
            cell49.setPhrase(new Phrase("Self/\nRent", fontEngH_7));
            cell49.setBorderColor(BaseColor.LIGHT_GRAY);
            //cell9.setGrayFill(0.9f);
            cell49.setHorizontalAlignment(Element.ALIGN_CENTER);
            //cell9.setVerticalAlignment(Element.ALIGN_CENTER);
            cell49.setColspan(1);
            table15.addCell(cell49);

            PdfPCell cell50 = new PdfPCell();
                    //cell1.setBorderColor(BaseColor.BLUE);
            //cell1.setPaddingLeft(10);
            cell50.setBorderWidth(1);
                   // cell1.setBorderColor(BaseColor.BLACK);
            //cell1.setBackgroundColor(BaseColor.LIGHT_GRAY);
            cell50.setPhrase(new Phrase("Location\nClass", fontEngH_7));
            cell50.setBorderColor(BaseColor.LIGHT_GRAY);
            //cell9.setGrayFill(0.9f);
            cell50.setHorizontalAlignment(Element.ALIGN_CENTER);
            //cell9.setVerticalAlignment(Element.ALIGN_CENTER);
            cell50.setColspan(1);
            table15.addCell(cell50);

            PdfPCell cell51 = new PdfPCell();
                    //cell1.setBorderColor(BaseColor.BLUE);
            //cell1.setPaddingLeft(10);
            cell51.setBorderWidth(1);
                   // cell1.setBorderColor(BaseColor.BLACK);
            //cell1.setBackgroundColor(BaseColor.LIGHT_GRAY);
            cell51.setPhrase(new Phrase("Presumed\nAnnual\nRent\nper Annum", fontEngH_7));
            cell51.setBorderColor(BaseColor.LIGHT_GRAY);
            //cell9.setGrayFill(0.9f);
            cell51.setHorizontalAlignment(Element.ALIGN_CENTER);
            //cell9.setVerticalAlignment(Element.ALIGN_CENTER);
            cell51.setColspan(1);
            table15.addCell(cell51);

            PdfPCell cell52 = new PdfPCell();
                    //cell1.setBorderColor(BaseColor.BLUE);
            //cell1.setPaddingLeft(10);
            cell52.setBorderWidth(1);
                   // cell1.setBorderColor(BaseColor.BLACK);
            //cell1.setBackgroundColor(BaseColor.LIGHT_GRAY);
            cell52.setPhrase(new Phrase("Annual\nRateable\nValue\n(H*90%)", fontEngH_7));
            cell52.setBorderColor(BaseColor.LIGHT_GRAY);
            //cell9.setGrayFill(0.9f);
            cell52.setHorizontalAlignment(Element.ALIGN_CENTER);
            //cell9.setVerticalAlignment(Element.ALIGN_CENTER);
            cell52.setColspan(1);
            table15.addCell(cell52);

            PdfPCell cell53 = new PdfPCell();
                    //cell1.setBorderColor(BaseColor.BLUE);
            //cell1.setPaddingLeft(10);
            cell53.setBorderWidth(1);
                   // cell1.setBorderColor(BaseColor.BLACK);
            //cell1.setBackgroundColor(BaseColor.LIGHT_GRAY);
            cell53.setPhrase(new Phrase("Actual\nAnnual Rent", fontEngH_7));
            cell53.setBorderColor(BaseColor.LIGHT_GRAY);
            //cell9.setGrayFill(0.9f);
            cell53.setHorizontalAlignment(Element.ALIGN_CENTER);
            //cell9.setVerticalAlignment(Element.ALIGN_CENTER);
            cell53.setColspan(1);
            table15.addCell(cell53);
            
            PdfPCell cell53_1 = new PdfPCell();
                    //cell1.setBorderColor(BaseColor.BLUE);
            //cell1.setPaddingLeft(10);
            cell53_1.setBorderWidth(1);
                   // cell1.setBorderColor(BaseColor.BLACK);
            //cell1.setBackgroundColor(BaseColor.LIGHT_GRAY);
            cell53_1.setPhrase(new Phrase("Actual\n Annual rent\nRateable Value\n (J*90%)", fontEngH_7));
            cell53_1.setBorderColor(BaseColor.LIGHT_GRAY);
            //cell9.setGrayFill(0.9f);
            cell53_1.setHorizontalAlignment(Element.ALIGN_LEFT);
            //cell9.setVerticalAlignment(Element.ALIGN_CENTER);
            cell53_1.setColspan(1);
            table15.addCell(cell53_1);
            

            PdfPCell cell54 = new PdfPCell();
                    //cell1.setBorderColor(BaseColor.BLUE);
            //cell1.setPaddingLeft(10);
            cell54.setBorderWidth(1);
                   // cell1.setBorderColor(BaseColor.BLACK);
            //cell1.setBackgroundColor(BaseColor.LIGHT_GRAY);
            cell54.setPhrase(new Phrase("Prop.\nTax\nRate", fontEngH_7));
            cell54.setBorderColor(BaseColor.LIGHT_GRAY);
            //cell9.setGrayFill(0.9f);
            cell54.setHorizontalAlignment(Element.ALIGN_CENTER);
            //cell9.setVerticalAlignment(Element.ALIGN_CENTER);
            cell54.setColspan(1);
            table15.addCell(cell54);

            PdfPCell cell55 = new PdfPCell();
                    //cell1.setBorderColor(BaseColor.BLUE);
            //cell1.setPaddingLeft(10);
            cell55.setBorderWidth(1);
                   // cell1.setBorderColor(BaseColor.BLACK);
            //cell1.setBackgroundColor(BaseColor.LIGHT_GRAY);
            cell55.setPhrase(new Phrase("Annual\nProperty\nTax\n(B*I*L)\n (from 01-10-2019 to 31-03-2020)", fontEngH_7));
            cell55.setBorderColor(BaseColor.LIGHT_GRAY);
            //cell9.setGrayFill(0.9f);
            cell55.setHorizontalAlignment(Element.ALIGN_CENTER);
            //cell9.setVerticalAlignment(Element.ALIGN_CENTER);
            cell55.setColspan(1);
            table15.addCell(cell55);
            document.add(table15);

            //String flr[];
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

            flr = bean.getFloorName();
            meter = bean.getPf_electric_con_num();
            cover_ar = bean.getBuiltupArea();
            buse_ar = bean.getBuildingUse();
            category_ar = bean.getPropCat();
            construction_ar = bean.getConstructionType();
            self_ar = bean.getFloorSelfRent();
            location_ar = bean.getPropertyClass();
            annualRent_ar = bean.getRentableValue();
            ratableValue_ar = bean.getAnuualRatableValue();
            tax_rate_ar = bean.getMultiplicatioFactor();
            tax_ar = bean.getFloorWiseTax();
            actualRent=bean.getActualRentValue();
            anuualRatableValue=bean.getActualRatableValue();
                     //System.out.println(" ratableValue_ar "+ratableValue_ar);

            for (int i = 0; i < buse_ar.length; i++) {

                totalCoveredArea = totalCoveredArea + (int) Double.parseDouble(cover_ar[i]);
                totalTax = totalTax + (int) (Double.parseDouble(tax_ar[i]));

                // coding
                PdfPTable table16 = new PdfPTable(13);
                table16.setWidths(columnWidths1);
                PdfPCell cell56 = new PdfPCell();
                    //cell1.setBorderColor(BaseColor.BLUE);
                //cell1.setPaddingLeft(10);
                cell56.setBorderWidth(1);
                   // cell1.setBorderColor(BaseColor.BLACK);
                //cell1.setBackgroundColor(BaseColor.LIGHT_GRAY);
                //cell56.setPhrase(new Phrase("GF" , fontEngH_7));
                cell56.setPhrase(new Phrase("" + flr[i], fontEngH_7));//ak
                cell56.setBorderColor(BaseColor.LIGHT_GRAY);
                //cell9.setGrayFill(0.9f);
                cell56.setHorizontalAlignment(Element.ALIGN_CENTER);
                //cell9.setVerticalAlignment(Element.ALIGN_CENTER);
                cell56.setColspan(1);
                table16.addCell(cell56);

                PdfPCell cell57 = new PdfPCell();
                    //cell1.setBorderColor(BaseColor.BLUE);
                //cell1.setPaddingLeft(10);
                cell57.setBorderWidth(1);
                   // cell1.setBorderColor(BaseColor.BLACK);
                //cell1.setBackgroundColor(BaseColor.LIGHT_GRAY);
                cell57.setPhrase(new Phrase("" + cover_ar[i], fontEngH_7));
                cell57.setBorderColor(BaseColor.LIGHT_GRAY);
                //cell9.setGrayFill(0.9f);
                cell57.setHorizontalAlignment(Element.ALIGN_CENTER);
                //cell9.setVerticalAlignment(Element.ALIGN_CENTER);
                cell57.setColspan(1);
                table16.addCell(cell57);

                PdfPCell cell58 = new PdfPCell();
                    //cell1.setBorderColor(BaseColor.BLUE);
                //cell1.setPaddingLeft(10);
                cell58.setBorderWidth(1);
                   // cell1.setBorderColor(BaseColor.BLACK);
                //cell1.setBackgroundColor(BaseColor.LIGHT_GRAY);
                cell58.setPhrase(new Phrase("" + buse_ar[i], fontEngH_7));
                cell58.setBorderColor(BaseColor.LIGHT_GRAY);
                //cell9.setGrayFill(0.9f);
                cell58.setHorizontalAlignment(Element.ALIGN_RIGHT);
                //cell9.setVerticalAlignment(Element.ALIGN_CENTER);
                cell58.setColspan(1);
                table16.addCell(cell58);

                PdfPCell cell59 = new PdfPCell();
                    //cell1.setBorderColor(BaseColor.BLUE);
                //cell1.setPaddingLeft(10);
                cell59.setBorderWidth(1);
                   // cell1.setBorderColor(BaseColor.BLACK);
                //cell1.setBackgroundColor(BaseColor.LIGHT_GRAY);
                cell59.setPhrase(new Phrase("" + category_ar[i], fontEngH_7));
                cell59.setBorderColor(BaseColor.LIGHT_GRAY);
                //cell9.setGrayFill(0.9f);
                cell59.setHorizontalAlignment(Element.ALIGN_CENTER);
                //cell9.setVerticalAlignment(Element.ALIGN_CENTER);
                cell59.setColspan(1);
                table16.addCell(cell59);
                    //System.out.println(" ratableValue_ar "+buse_ar[i]);

                PdfPCell cell60 = new PdfPCell();
                    //cell1.setBorderColor(BaseColor.BLUE);
                //cell1.setPaddingLeft(10);
                cell60.setBorderWidth(1);
                   // cell1.setBorderColor(BaseColor.BLACK);
                //cell1.setBackgroundColor(BaseColor.LIGHT_GRAY);
                //cell60.setPhrase(new Phrase("RCC" , fontEngH_7));
                cell60.setPhrase(new Phrase("" + construction_ar[i], fontEngH_7));//ak
                cell60.setBorderColor(BaseColor.LIGHT_GRAY);
                //cell9.setGrayFill(0.9f);
                cell60.setHorizontalAlignment(Element.ALIGN_CENTER);
                //cell9.setVerticalAlignment(Element.ALIGN_CENTER);
                cell60.setColspan(1);
                table16.addCell(cell60);

                PdfPCell cell61 = new PdfPCell();
                    //cell1.setBorderColor(BaseColor.BLUE);
                //cell1.setPaddingLeft(10);
                cell61.setBorderWidth(1);
                   // cell1.setBorderColor(BaseColor.BLACK);
                //cell1.setBackgroundColor(BaseColor.LIGHT_GRAY);
                cell61.setPhrase(new Phrase("" + self_ar[i], fontEngH_7));
                cell61.setBorderColor(BaseColor.LIGHT_GRAY);
                //cell9.setGrayFill(0.9f);
                cell61.setHorizontalAlignment(Element.ALIGN_CENTER);
                //cell9.setVerticalAlignment(Element.ALIGN_CENTER);
                cell61.setColspan(1);
                table16.addCell(cell61);

                PdfPCell cell62 = new PdfPCell();
                    //cell1.setBorderColor(BaseColor.BLUE);
                //cell1.setPaddingLeft(10);
                cell62.setBorderWidth(1);
                   // cell1.setBorderColor(BaseColor.BLACK);
                //cell1.setBackgroundColor(BaseColor.LIGHT_GRAY);
                cell62.setPhrase(new Phrase("" + location_ar[i], fontEngH_7));
                cell62.setBorderColor(BaseColor.LIGHT_GRAY);
                //cell9.setGrayFill(0.9f);
                cell62.setHorizontalAlignment(Element.ALIGN_CENTER);
                //cell9.setVerticalAlignment(Element.ALIGN_CENTER);
                cell62.setColspan(1);
                table16.addCell(cell62);

                PdfPCell cell63 = new PdfPCell();
                    //cell1.setBorderColor(BaseColor.BLUE);
                //cell1.setPaddingLeft(10);
                cell63.setBorderWidth(1);
                   // cell1.setBorderColor(BaseColor.BLACK);
                //cell1.setBackgroundColor(BaseColor.LIGHT_GRAY);
                cell63.setPhrase(new Phrase("" + annualRent_ar[i], fontEngH_7));
                cell63.setBorderColor(BaseColor.LIGHT_GRAY);
                //cell9.setGrayFill(0.9f);
                cell63.setHorizontalAlignment(Element.ALIGN_CENTER);
                //cell9.setVerticalAlignment(Element.ALIGN_CENTER);
                cell63.setColspan(1);
                table16.addCell(cell63);

                PdfPCell cell64 = new PdfPCell();
                    //cell1.setBorderColor(BaseColor.BLUE);
                //cell1.setPaddingLeft(10);
                cell64.setBorderWidth(1);
                   // cell1.setBorderColor(BaseColor.BLACK);
                //cell1.setBackgroundColor(BaseColor.LIGHT_GRAY);
                cell64.setPhrase(new Phrase("" + ratableValue_ar[i], fontEngH_7));
                cell64.setBorderColor(BaseColor.LIGHT_GRAY);
                //cell9.setGrayFill(0.9f);
                cell64.setHorizontalAlignment(Element.ALIGN_CENTER);
                //cell9.setVerticalAlignment(Element.ALIGN_CENTER);
                cell64.setColspan(1);
                table16.addCell(cell64);

                PdfPCell cell65 = new PdfPCell();
                    //cell1.setBorderColor(BaseColor.BLUE);
                //cell1.setPaddingLeft(10);
                cell65.setBorderWidth(1);
                   // cell1.setBorderColor(BaseColor.BLACK);
                //cell1.setBackgroundColor(BaseColor.LIGHT_GRAY);
                cell65.setPhrase(new Phrase(""+actualRent[i] , fontEngH_7));
                cell65.setBorderColor(BaseColor.LIGHT_GRAY);
                //cell9.setGrayFill(0.9f);
                cell65.setHorizontalAlignment(Element.ALIGN_CENTER);
                //cell9.setVerticalAlignment(Element.ALIGN_CENTER);
                cell65.setColspan(1);
                table16.addCell(cell65);
                
                PdfPCell cell65_1 = new PdfPCell();
                    //cell1.setBorderColor(BaseColor.BLUE);
                //cell1.setPaddingLeft(10);
                cell65_1.setBorderWidth(1);
                   // cell1.setBorderColor(BaseColor.BLACK);
                //cell1.setBackgroundColor(BaseColor.LIGHT_GRAY);
                cell65_1.setPhrase(new Phrase(""+anuualRatableValue[i] , fontEngH_7));
                cell65_1.setBorderColor(BaseColor.LIGHT_GRAY);
                //cell9.setGrayFill(0.9f);
                cell65_1.setHorizontalAlignment(Element.ALIGN_CENTER);
                //cell9.setVerticalAlignment(Element.ALIGN_CENTER);
                cell65_1.setColspan(1);
                table16.addCell(cell65_1);
                
//                PdfPCell cell65_1_2 = new PdfPCell();
//                    //cell1.setBorderColor(BaseColor.BLUE);
//                //cell1.setPaddingLeft(10);
//                cell65_1_2.setBorderWidth(1);
//                   // cell1.setBorderColor(BaseColor.BLACK);
//                //cell1.setBackgroundColor(BaseColor.LIGHT_GRAY);
//                cell65_1_2.setPhrase(new Phrase(" " , fontEngH_7));
//                cell65_1_2.setBorderColor(BaseColor.LIGHT_GRAY);
//                //cell9.setGrayFill(0.9f);
//                cell65_1_2.setHorizontalAlignment(Element.ALIGN_CENTER);
//                //cell9.setVerticalAlignment(Element.ALIGN_CENTER);
//                cell65_1_2.setColspan(1);
//                table16.addCell(cell65_1_2);
                

                    //System.out.println(" ratableValue_ar "+buse_ar[i]+" "+ratableValue_ar[i]+" "+annualRent_ar[i]);
                PdfPCell cell66 = new PdfPCell();
                    //cell1.setBorderColor(BaseColor.BLUE);
                //cell1.setPaddingLeft(10);
                cell66.setBorderWidth(1);
                   // cell1.setBorderColor(BaseColor.BLACK);
                //cell1.setBackgroundColor(BaseColor.LIGHT_GRAY);

                cell66.setPhrase(new Phrase("" + tax_rate_ar[i], fontEngH_7));
                cell66.setBorderColor(BaseColor.LIGHT_GRAY);
                //cell9.setGrayFill(0.9f);
                cell66.setHorizontalAlignment(Element.ALIGN_CENTER);
                //cell9.setVerticalAlignment(Element.ALIGN_CENTER);
                cell66.setColspan(1);
                table16.addCell(cell66);

                PdfPCell cell67 = new PdfPCell();
                    //cell1.setBorderColor(BaseColor.BLUE);
                //cell1.setPaddingLeft(10);
                cell67.setBorderWidth(1);
                   // cell1.setBorderColor(BaseColor.BLACK);
                //cell1.setBackgroundColor(BaseColor.LIGHT_GRAY);
                cell67.setPhrase(new Phrase("" + df.format(Long.parseLong(tax_ar[i])), fontEngH_7));
                cell67.setBorderColor(BaseColor.LIGHT_GRAY);
                //cell9.setGrayFill(0.9f);
                cell67.setHorizontalAlignment(Element.ALIGN_RIGHT);
                //cell9.setVerticalAlignment(Element.ALIGN_CENTER);
                cell67.setColspan(1);
                table16.addCell(cell67);
                document.add(table16);

            }

            PdfPTable table16 = new PdfPTable(13);
            table16.setWidths(columnWidths1);
            PdfPCell cell56 = new PdfPCell();
                    //cell1.setBorderColor(BaseColor.BLUE);
            //cell1.setPaddingLeft(10);
            cell56.setBorderWidth(1);
                   // cell1.setBorderColor(BaseColor.BLACK);
            //cell1.setBackgroundColor(BaseColor.LIGHT_GRAY);
            cell56.setPhrase(new Phrase("Total", fontEngH_7));
            cell56.setBorderColor(BaseColor.LIGHT_GRAY);
            //cell9.setGrayFill(0.9f);
            cell56.setHorizontalAlignment(Element.ALIGN_CENTER);
            //cell9.setVerticalAlignment(Element.ALIGN_CENTER);
            cell56.setColspan(1);
            table16.addCell(cell56);

            PdfPCell cell57 = new PdfPCell();
                    //cell1.setBorderColor(BaseColor.BLUE);
            //cell1.setPaddingLeft(10);
            cell57.setBorderWidth(1);
                   // cell1.setBorderColor(BaseColor.BLACK);
            //cell1.setBackgroundColor(BaseColor.LIGHT_GRAY);
            cell57.setPhrase(new Phrase(""+totalCoveredArea, fontEngH_7));
            cell57.setBorderColor(BaseColor.LIGHT_GRAY);
            //cell9.setGrayFill(0.9f);
            cell57.setHorizontalAlignment(Element.ALIGN_CENTER);
            //cell9.setVerticalAlignment(Element.ALIGN_CENTER);
            cell57.setColspan(1);
            table16.addCell(cell57);

            PdfPCell cell58 = new PdfPCell();
                    //cell1.setBorderColor(BaseColor.BLUE);
            //cell1.setPaddingLeft(10);
            cell58.setBorderWidth(1);
                   // cell1.setBorderColor(BaseColor.BLACK);
            //cell1.setBackgroundColor(BaseColor.LIGHT_GRAY);
            cell58.setPhrase(new Phrase("-" , fontEngH_7));
            cell58.setBorderColor(BaseColor.LIGHT_GRAY);
            //cell9.setGrayFill(0.9f);
            cell58.setHorizontalAlignment(Element.ALIGN_RIGHT);
            //cell9.setVerticalAlignment(Element.ALIGN_CENTER);
            cell58.setColspan(1);
            table16.addCell(cell58);

            PdfPCell cell59 = new PdfPCell();
                    //cell1.setBorderColor(BaseColor.BLUE);
            //cell1.setPaddingLeft(10);
            cell59.setBorderWidth(1);
                   // cell1.setBorderColor(BaseColor.BLACK);
            //cell1.setBackgroundColor(BaseColor.LIGHT_GRAY);
            cell59.setPhrase(new Phrase("-", fontEngH_7));
            cell59.setBorderColor(BaseColor.LIGHT_GRAY);
            //cell9.setGrayFill(0.9f);
            cell59.setHorizontalAlignment(Element.ALIGN_CENTER);
            //cell9.setVerticalAlignment(Element.ALIGN_CENTER);
            cell59.setColspan(1);
            table16.addCell(cell59);

            PdfPCell cell60 = new PdfPCell();
                    //cell1.setBorderColor(BaseColor.BLUE);
            //cell1.setPaddingLeft(10);
            cell60.setBorderWidth(1);
                   // cell1.setBorderColor(BaseColor.BLACK);
            //cell1.setBackgroundColor(BaseColor.LIGHT_GRAY);
            cell60.setPhrase(new Phrase("-", fontEngH_7));
            cell60.setBorderColor(BaseColor.LIGHT_GRAY);
            //cell9.setGrayFill(0.9f);
            cell60.setHorizontalAlignment(Element.ALIGN_CENTER);
            //cell9.setVerticalAlignment(Element.ALIGN_CENTER);
            cell60.setColspan(1);
            table16.addCell(cell60);

            PdfPCell cell61 = new PdfPCell();
                    //cell1.setBorderColor(BaseColor.BLUE);
            //cell1.setPaddingLeft(10);
            cell61.setBorderWidth(1);
                   // cell1.setBorderColor(BaseColor.BLACK);
            //cell1.setBackgroundColor(BaseColor.LIGHT_GRAY);
            cell61.setPhrase(new Phrase("-", fontEngH_7));
            cell61.setBorderColor(BaseColor.LIGHT_GRAY);
            //cell9.setGrayFill(0.9f);
            cell61.setHorizontalAlignment(Element.ALIGN_CENTER);
            //cell9.setVerticalAlignment(Element.ALIGN_CENTER);
            cell61.setColspan(1);
            table16.addCell(cell61);

            PdfPCell cell62 = new PdfPCell();
                    //cell1.setBorderColor(BaseColor.BLUE);
            //cell1.setPaddingLeft(10);
            cell62.setBorderWidth(1);
                   // cell1.setBorderColor(BaseColor.BLACK);
            //cell1.setBackgroundColor(BaseColor.LIGHT_GRAY);
            cell62.setPhrase(new Phrase("-", fontEngH_7));
            cell62.setBorderColor(BaseColor.LIGHT_GRAY);
            //cell9.setGrayFill(0.9f);
            cell62.setHorizontalAlignment(Element.ALIGN_CENTER);
            //cell9.setVerticalAlignment(Element.ALIGN_CENTER);
            cell62.setColspan(1);
            table16.addCell(cell62);

            PdfPCell cell63 = new PdfPCell();
                    //cell1.setBorderColor(BaseColor.BLUE);
            //cell1.setPaddingLeft(10);
            cell63.setBorderWidth(1);
                   // cell1.setBorderColor(BaseColor.BLACK);
            //cell1.setBackgroundColor(BaseColor.LIGHT_GRAY);
            cell63.setPhrase(new Phrase("-", fontEngH_7));
            cell63.setBorderColor(BaseColor.LIGHT_GRAY);
            //cell9.setGrayFill(0.9f);
            cell63.setHorizontalAlignment(Element.ALIGN_CENTER);
            //cell9.setVerticalAlignment(Element.ALIGN_CENTER);
            cell63.setColspan(1);
            table16.addCell(cell63);

            PdfPCell cell64 = new PdfPCell();
                    //cell1.setBorderColor(BaseColor.BLUE);
            //cell1.setPaddingLeft(10);
            cell64.setBorderWidth(1);
                   // cell1.setBorderColor(BaseColor.BLACK);
            //cell1.setBackgroundColor(BaseColor.LIGHT_GRAY);
            cell64.setPhrase(new Phrase("-", fontEngH_7));
            cell64.setBorderColor(BaseColor.LIGHT_GRAY);
            //cell9.setGrayFill(0.9f);
            cell64.setHorizontalAlignment(Element.ALIGN_CENTER);
            //cell9.setVerticalAlignment(Element.ALIGN_CENTER);
            cell64.setColspan(1);
            table16.addCell(cell64);

            PdfPCell cell65 = new PdfPCell();
                    //cell1.setBorderColor(BaseColor.BLUE);
            //cell1.setPaddingLeft(10);
            cell65.setBorderWidth(1);
                   // cell1.setBorderColor(BaseColor.BLACK);
            //cell1.setBackgroundColor(BaseColor.LIGHT_GRAY);
            cell65.setPhrase(new Phrase("-", fontEngH_7));
            cell65.setBorderColor(BaseColor.LIGHT_GRAY);
            //cell9.setGrayFill(0.9f);
            cell65.setHorizontalAlignment(Element.ALIGN_CENTER);
            //cell9.setVerticalAlignment(Element.ALIGN_CENTER);
            cell65.setColspan(1);
            table16.addCell(cell65);

            PdfPCell cell66 = new PdfPCell();
                    //cell1.setBorderColor(BaseColor.BLUE);
            //cell1.setPaddingLeft(10);
            cell66.setBorderWidth(1);
                   // cell1.setBorderColor(BaseColor.BLACK);
            //cell1.setBackgroundColor(BaseColor.LIGHT_GRAY);
            cell66.setPhrase(new Phrase("-", fontEngH_7));
            cell66.setBorderColor(BaseColor.LIGHT_GRAY);
            //cell9.setGrayFill(0.9f);
            cell66.setHorizontalAlignment(Element.ALIGN_CENTER);
            //cell9.setVerticalAlignment(Element.ALIGN_CENTER);
            cell66.setColspan(1);
            table16.addCell(cell66);
            
            PdfPCell cell66_1 = new PdfPCell();
                    //cell1.setBorderColor(BaseColor.BLUE);
            //cell1.setPaddingLeft(10);
            cell66_1.setBorderWidth(1);
                   // cell1.setBorderColor(BaseColor.BLACK);
            //cell1.setBackgroundColor(BaseColor.LIGHT_GRAY);
            cell66_1.setPhrase(new Phrase("-", fontEngH_7));
            cell66_1.setBorderColor(BaseColor.LIGHT_GRAY);
            //cell9.setGrayFill(0.9f);
            cell66_1.setHorizontalAlignment(Element.ALIGN_CENTER);
            //cell9.setVerticalAlignment(Element.ALIGN_CENTER);
            cell66_1.setColspan(1);
            table16.addCell(cell66_1);

            PdfPCell cell67 = new PdfPCell();
                    //cell1.setBorderColor(BaseColor.BLUE);
            //cell1.setPaddingLeft(10);
            cell67.setBorderWidth(1);
                   // cell1.setBorderColor(BaseColor.BLACK);
            //cell1.setBackgroundColor(BaseColor.LIGHT_GRAY);
            cell67.setPhrase(new Phrase(" " + df.format(totalTax), fontEngH_8));
            cell67.setBorderColor(BaseColor.LIGHT_GRAY);
            //cell9.setGrayFill(0.9f);
            cell67.setHorizontalAlignment(Element.ALIGN_RIGHT);
            //cell9.setVerticalAlignment(Element.ALIGN_CENTER);
            cell67.setColspan(1);
            table16.addCell(cell67);
            document.add(table16);
            
            int toatalPayableAmount=totalTax+Integer.parseInt(bean.getArrearAmount())+Integer.parseInt(bean.getOldTax());
            PdfPTable table18_total = new PdfPTable(13);
            table18_total.setWidths(columnWidths1);
            PdfPCell cell69_total = new PdfPCell();
                    //cell1.setBorderColor(BaseColor.BLUE);
            //cell1.setPaddingLeft(10);
            cell69_total.setBorderWidth(0);
            //cell69_total.setBorderColor(BaseColor.LIGHT_GRAY);
                   // cell1.setBorderColor(BaseColor.BLACK);
            //cell1.setBackgroundColor(BaseColor.LIGHT_GRAY);
            cell69_total.setPhrase(new Phrase(" ", fontEngH_4k));
                    //cell69.setBorderColor(BaseColor.LIGHT_GRAY);
            //cell68.setGrayFill(0.9f);
            cell69_total.setHorizontalAlignment(Element.ALIGN_LEFT);
            //cell69.setVerticalAlignment(Element.ALIGN_CENTER);
            cell69_total.setColspan(6);
            table18_total.addCell(cell69_total);
            
            PdfPCell cell69_total_1 = new PdfPCell();
                    //cell1.setBorderColor(BaseColor.BLUE);
            //cell1.setPaddingLeft(10);
            cell69_total_1.setBorderWidth(1);
            cell69_total_1.setBorderColor(BaseColor.LIGHT_GRAY);
                   // cell1.setBorderColor(BaseColor.BLACK);
            //cell1.setBackgroundColor(BaseColor.LIGHT_GRAY);
            cell69_total_1.setPhrase(new Phrase("Total Payable Amount(Arrear Amount+old Tax+Total Tax) ", fontEngH_4k));
                    //cell69.setBorderColor(BaseColor.LIGHT_GRAY);
            //cell68.setGrayFill(0.9f);
            cell69_total_1.setHorizontalAlignment(Element.ALIGN_JUSTIFIED);
            //cell69.setVerticalAlignment(Element.ALIGN_CENTER);
            cell69_total_1.setColspan(6);
            table18_total.addCell(cell69_total_1);
            
            PdfPCell cell69_total_1_2 = new PdfPCell();
                    //cell1.setBorderColor(BaseColor.BLUE);
            //cell1.setPaddingLeft(10);
            cell69_total_1_2.setBorderWidth(1);
            cell69_total_1_2.setBorderColor(BaseColor.LIGHT_GRAY);
                   // cell1.setBorderColor(BaseColor.BLACK);
            //cell1.setBackgroundColor(BaseColor.LIGHT_GRAY);
            cell69_total_1_2.setPhrase(new Phrase(""+df.format(toatalPayableAmount), fontEngH_4k));
                    //cell69.setBorderColor(BaseColor.LIGHT_GRAY);
            //cell68.setGrayFill(0.9f);
            cell69_total_1_2.setHorizontalAlignment(Element.ALIGN_RIGHT);
            //cell69.setVerticalAlignment(Element.ALIGN_CENTER);
            cell69_total_1_2.setColspan(1);
            table18_total.addCell(cell69_total_1_2);
            
            document.add(table18_total);
            
            
            

            PdfPTable table17 = new PdfPTable(12);
            //table5.setWidths(columnWidths);
            PdfPCell cell68 = new PdfPCell();
                    //cell1.setBorderColor(BaseColor.BLUE);
            //cell1.setPaddingLeft(10);
            cell68.setBorderWidth(0);
                   // cell1.setBorderColor(BaseColor.BLACK);
            //cell1.setBackgroundColor(BaseColor.LIGHT_GRAY);
            cell68.setPhrase(new Phrase("", fontHindi_2));
                    //cell68.setBorderColor(BaseColor.LIGHT_GRAY);
            //cell68.setGrayFill(0.9f);
            cell68.setHorizontalAlignment(Element.ALIGN_CENTER);
            cell68.setVerticalAlignment(Element.ALIGN_CENTER);
            cell68.setColspan(12);
            table17.addCell(cell68);
            document.add(table17);
            
            Font zapfdingbats = new Font();
            Chunk bullet = new Chunk("\u2022", zapfdingbats);
            Chunk t2=new Chunk(" This property tax assessment notice has been issued for the sole purpuse of property tax assessment.This is not a proof of ownership or possession and cannot be used as a proof in any court of law.", fontEngH_4k);
            Phrase pt=new Phrase();
            pt.add(bullet);
            pt.add(t2);
            
            
            PdfPTable table18_1_5 = new PdfPTable(12);
            //table5.setWidths(columnWidths);
            PdfPCell cell69_1_5 = new PdfPCell();
                    //cell1.setBorderColor(BaseColor.BLUE);
            //cell1.setPaddingLeft(10);
            cell69_1_5.setBorderWidth(0);
                   // cell1.setBorderColor(BaseColor.BLACK);
            //cell1.setBackgroundColor(BaseColor.LIGHT_GRAY);
            cell69_1_5.setPhrase(pt);
            //cell69_1_5.setPhrase(new Phrase("This property tax assessment notice has been issued for the sole purpuse of property tax assessment.This is not a proof of ownership or possession and cannot be used as a proof in any court of law.", fontEngH_4k));
                    //cell69.setBorderColor(BaseColor.LIGHT_GRAY);
            //cell68.setGrayFill(0.9f);
            cell69_1_5.setHorizontalAlignment(Element.ALIGN_JUSTIFIED);
            //cell69.setVerticalAlignment(Element.ALIGN_CENTER);
            cell69_1_5.setColspan(12);
            table18_1_5.addCell(cell69_1_5);
            document.add(table18_1_5);
            
            Chunk t3=new Chunk(";g laifRr dj vkadyu uksfVl dsoy laifRr dj olwyh ls lEcfU/kr gSA ;g fdlh Hkh izdkj ls feyfd;r ;k dCTks dk lcwr ugh gSA vr% bl uksfVl dks uk rks feyfd;r ;k dCTks ds lcwr ds rkSj ij izLrqr fd;k tk ldrk gS uk iz;ksx fd;k tk ldrk gSA", fontHindi_2k);
            Phrase pt1=new Phrase();
            //pt1.add(bullet);
            pt1.add(t3);
            
            
            PdfPTable table18 = new PdfPTable(12);
            //table5.setWidths(columnWidths);
            PdfPCell cell69 = new PdfPCell();
                    //cell1.setBorderColor(BaseColor.BLUE);
            //cell1.setPaddingLeft(10);
            cell69.setBorderWidth(0);
                   // cell1.setBorderColor(BaseColor.BLACK);
            //cell1.setBackgroundColor(BaseColor.LIGHT_GRAY);
            cell69.setPhrase(pt1);
            //cell69.setPhrase(new Phrase(";g laifRr dj vkadyu uksfVl dsoy laifRr dj olwyh ls lEcfU/kr gSA ;g fdlh Hkh izdkj ls feyfd;r ;k dCTks dk lcwr ugh gSA vr% bl uksfVl dks uk rks feyfd;r ;k dCTks ds lcwr ds rkSj ij izLrqr fd;k tk ldrk gS uk iz;ksx fd;k tk ldrk gSA", fontHindi_2k));
                    //cell69.setBorderColor(BaseColor.LIGHT_GRAY);
            //cell68.setGrayFill(0.9f);
            cell69.setHorizontalAlignment(Element.ALIGN_JUSTIFIED);
            //cell69.setVerticalAlignment(Element.ALIGN_CENTER);
            cell69.setColspan(12);
            table18.addCell(cell69);
            document.add(table18);
            
            Chunk t4=new Chunk(" In case actual annual rent is more than presumed annual rent than tax as per actual annual rent is to be paid.", fontEngH_4k);
            Phrase pt2=new Phrase();
            pt2.add(bullet);
            pt2.add(t4);
            
            
            
            PdfPTable table18_1 = new PdfPTable(12);
            //table5.setWidths(columnWidths);
            PdfPCell cell69_1 = new PdfPCell();
                    //cell1.setBorderColor(BaseColor.BLUE);
            //cell1.setPaddingLeft(10);
            cell69_1.setBorderWidth(0);
                   // cell1.setBorderColor(BaseColor.BLACK);
            //cell1.setBackgroundColor(BaseColor.LIGHT_GRAY);
            cell69_1.setPhrase(pt2);
            //cell69_1.setPhrase(new Phrase("In case actual annual rent is more than presumed annual rent than tax as per actual annual rent is to be paid.", fontEngH_4k));
                    //cell69.setBorderColor(BaseColor.LIGHT_GRAY);
            //cell68.setGrayFill(0.9f);
            cell69_1.setHorizontalAlignment(Element.ALIGN_JUSTIFIED);
            //cell69.setVerticalAlignment(Element.ALIGN_CENTER);
            cell69_1.setColspan(12);
            table18_1.addCell(cell69_1);
            document.add(table18_1);
            
            Chunk t5=new Chunk(";fn fdlh laifRr dk okLrfod okf”kZd fdjk;k vuqekfur okf”kZd fdjk;s ls vf/kd gS rks okLrfod okf”kZd fdjk;s dh nj Lks laifRr dj dk Hkqxrku djuk gksxkA", fontHindi_2k);
            Phrase pt3=new Phrase();
            //pt3.add(bullet);
            pt3.add(t5);
            
            
            PdfPTable table18_1_2 = new PdfPTable(12);
            //table5.setWidths(columnWidths);
            PdfPCell cell69_1_2 = new PdfPCell();
                    //cell1.setBorderColor(BaseColor.BLUE);
            //cell1.setPaddingLeft(10);
            cell69_1_2.setBorderWidth(0);
                   // cell1.setBorderColor(BaseColor.BLACK);
            //cell1.setBackgroundColor(BaseColor.LIGHT_GRAY);
            cell69_1_2.setPhrase(pt3);
            //cell69_1_2.setPhrase(new Phrase(";fn fdlh laifRr dk okLrfod okf”kZd fdjk;k vuqekfur okf”kZd fdjk;s ls vf/kd gS rks okLrfod okf”kZd fdjk;s dh nj Lks laifRr dj dk Hkqxrku djuk gksxkA", fontHindi_2k));
                    //cell69.setBorderColor(BaseColor.LIGHT_GRAY);
            //cell68.setGrayFill(0.9f);
            cell69_1_2.setHorizontalAlignment(Element.ALIGN_JUSTIFIED);
            //cell69.setVerticalAlignment(Element.ALIGN_CENTER);
            cell69_1_2.setColspan(12);
            table18_1_2.addCell(cell69_1_2);
            document.add(table18_1_2);
            
            
            //System.out.println("bean.getOldMc() "+bean.getOldMc());
            
            //added
            
            PdfPTable table17_1 = new PdfPTable(12);
            PdfPCell cell73 = new PdfPCell();
            cell73.setBorderWidth(0);
            cell73.setPhrase(new Phrase("", fontHindi_2));
            cell73.setHorizontalAlignment(Element.ALIGN_CENTER);
            cell73.setVerticalAlignment(Element.ALIGN_CENTER);
            cell73.setColspan(12);
            table17_1.addCell(cell73);
            document.add(table17_1);
            Font zapfdingbatsa = new Font();
            Chunk bulleta = new Chunk("\u2022", zapfdingbatsa);
            Chunk t10=new Chunk(" In case of any correction or query related to arrear (fin. Yr. 2019-20) please submit correction form with last property tax payment receipt in the Silvassa Municipal Office or online property tax portal.", fontEngH_4k);
            Phrase pta=new Phrase();
            pta.add(bulleta);
            pta.add(t10);
            PdfPTable table18_1_5_1 = new PdfPTable(12);
            PdfPCell table18_1_5_2 = new PdfPCell();
            table18_1_5_2.setBorderWidth(0);
            table18_1_5_2.setPhrase(pta);
            table18_1_5_2.setHorizontalAlignment(Element.ALIGN_JUSTIFIED);
            table18_1_5_2.setColspan(12);
            table18_1_5_1.addCell(table18_1_5_2);
            document.add(table18_1_5_1); 
            Chunk t9=new Chunk(";g laifRr dj vkadyu uksfVl dsoy laifRr dj olwyh ls lEcfU/kr gSA ;g fdlh Hkh izdkj ls feyfd;r ;k dCTks dk lcwr ugh gSA vr% bl uksfVl dks uk rks feyfd;r ;k dCTks ds lcwr ds rkSj ij izLrqr fd;k tk ldrk gS uk iz;ksx fd;k tk ldrk gSA", fontHindi_2k);
           //Phrase pt8=new Phrase();
            pt1.add(t9);
            
            PdfPTable table18_1_4 = new PdfPTable(12);
            PdfPCell cell69_1_4 = new PdfPCell();
            cell69_1_4.setBorderWidth(0);
            cell69_1_4.setPhrase(pt3);
            cell69_1_4.setPhrase(new Phrase("Ckdk;k jk’kh ls lacaf/kr fdlh Hkh lq/kkj ;k iwNrkN ¼foRr o”kZ 2019&2020½ ds ekeys esa d`Ik;k flyoklk uxj ikfydk dk;kZy; ;k vkWauykbu laifRr dj iksVZy esa lq/kkj QWkeZ vafre laifRr dj Hkqxrku jlhn ds lkFk tek djsaA", fontHindi_2k));
            cell69_1_4.setHorizontalAlignment(Element.ALIGN_JUSTIFIED);
            cell69_1_4.setColspan(12);
            table18_1_4.addCell(cell69_1_4);
            document.add(table18_1_4);
            
            //added
            
        
            //for space
//            PdfPTable table18_1_45ggf1 = new PdfPTable(12);
//            PdfPCell cell69_1_45gfjgfj2 = new PdfPCell();
//            cell69_1_45gfjgfj2.setBorderWidth(0);
//            cell69_1_45gfjgfj2.setPhrase(pt3);
//            cell69_1_45gfjgfj2.setPhrase(new Phrase("                                    "));
//            cell69_1_45gfjgfj2.setHorizontalAlignment(Element.ALIGN_JUSTIFIED);
//            cell69_1_45gfjgfj2.setColspan(12);
//            table18_1_45ggf.addCell(cell69_1_45gfjgfj2);
//            document.add(cell69_1_45gfjgfj2);
            
            
            
        
            
            
            Chunk t6=new Chunk(" In case of unregistered properties, the applicant shall have to submit legal documents to prove his/her ownership.", fontEngH_4k);
            Phrase pt4=new Phrase();
            pt4.add(bullet);
            pt4.add(t6);
            
            Chunk t7=new Chunk("vIkathd`r laifRr;kas ds ekeys ea]s vkosnd dks vius LokfeRo@ekfydkuk lkfcr djus ds fy, dkuwuh nLrkost tek djuk  vfuok;Z gSA", fontHindi_2k);
            Phrase pt5=new Phrase();
            //pt5.add(bullet);
            pt5.add(t7);
            
            if(bean.getOldMc().trim().length() == 0){
            
            PdfPTable table18_2_3_4 = new PdfPTable(12);
            //table5.setWidths(columnWidths);
            PdfPCell cell69_2_3_4 = new PdfPCell();
                    //cell1.setBorderColor(BaseColor.BLUE);
            //cell1.setPaddingLeft(10);
            cell69_2_3_4.setBorderWidth(0);
                   // cell1.setBorderColor(BaseColor.BLACK);
            //cell1.setBackgroundColor(BaseColor.LIGHT_GRAY);
            cell69_2_3_4.setPhrase(pt4);
            //cell69_2_3_4.setPhrase(new Phrase("In case of unregistered properties, the applicant shall have to submit legal documents to prove his/her ownership.", fontEngH_4k));
                    //cell69.setBorderColor(BaseColor.LIGHT_GRAY);
            //cell68.setGrayFill(0.9f);
            cell69_2_3_4.setHorizontalAlignment(Element.ALIGN_JUSTIFIED);
            //cell69.setVerticalAlignment(Element.ALIGN_CENTER);
            cell69_2_3_4.setColspan(12);
            table18_2_3_4.addCell(cell69_2_3_4);
            document.add(table18_2_3_4);
            
            
            
            PdfPTable table18_2_3 = new PdfPTable(12);
            //table5.setWidths(columnWidths);
            PdfPCell cell69_2_3 = new PdfPCell();
                    //cell1.setBorderColor(BaseColor.BLUE);
            //cell1.setPaddingLeft(10);
            cell69_2_3.setBorderWidth(0);
                   // cell1.setBorderColor(BaseColor.BLACK);
            //cell1.setBackgroundColor(BaseColor.LIGHT_GRAY);
            cell69_2_3.setPhrase(pt5);
            //cell69_2_3.setPhrase(new Phrase("vIkathd`r laifRr;kas ds ekeys ea]s vkosnd dks vius LokfeRo@ekfydkuk lkfcr djus ds fy, dkuwuh nLrkost tek djuk  vfuok;Z gSA", fontHindi_2k));
                    //cell69.setBorderColor(BaseColor.LIGHT_GRAY);
            //cell68.setGrayFill(0.9f);
            cell69_2_3.setHorizontalAlignment(Element.ALIGN_JUSTIFIED);
            //cell69.setVerticalAlignment(Element.ALIGN_CENTER);
            cell69_2_3.setColspan(12);
            table18_2_3.addCell(cell69_2_3);
            document.add(table18_2_3);
            }
            
            Chunk t8=new Chunk(" bl uksfVl esa tks lwpuk ugha gSA d`I;k lwpuk nsA", fontHindi_2k);
            Phrase pt6=new Phrase();
            pt6.add(bullet);
            pt6.add(t8);
            
            
            /*PdfPTable table18_2 = new PdfPTable(12);
            //table5.setWidths(columnWidths);
            PdfPCell cell69_2 = new PdfPCell();
                    //cell1.setBorderColor(BaseColor.BLUE);
            //cell1.setPaddingLeft(10);
            cell69_2.setBorderWidth(0);
                   // cell1.setBorderColor(BaseColor.BLACK);
            //cell1.setBackgroundColor(BaseColor.LIGHT_GRAY);
            cell69_2.setPhrase(pt6);
            //cell69_2.setPhrase(new Phrase("bl uksfVl esa tks lwpuk ugha gSA d`I;k lwpuk nsA", fontHindi_2k));
                    //cell69.setBorderColor(BaseColor.LIGHT_GRAY);
            //cell68.setGrayFill(0.9f);
            cell69_2.setHorizontalAlignment(Element.ALIGN_JUSTIFIED);
            //cell69.setVerticalAlignment(Element.ALIGN_CENTER);
            cell69_2.setColspan(12);
            table18_2.addCell(cell69_2);
            document.add(table18_2);*/
            

            PdfPTable table19 = new PdfPTable(12);
            //table5.setWidths(columnWidths);
            PdfPCell cell70 = new PdfPCell();
                    //cell1.setBorderColor(BaseColor.BLUE);
            //cell1.setPaddingLeft(10);
            cell70.setBorderWidth(0);
                   // cell1.setBorderColor(BaseColor.BLACK);
            //cell1.setBackgroundColor(BaseColor.LIGHT_GRAY);
            cell70.setPhrase(new Phrase("", fontHindi_2));
                    //cell68.setBorderColor(BaseColor.LIGHT_GRAY);
            //cell68.setGrayFill(0.9f);
            cell70.setHorizontalAlignment(Element.ALIGN_CENTER);
            cell70.setVerticalAlignment(Element.ALIGN_CENTER);
            cell70.setColspan(12);
            table19.addCell(cell70);
            document.add(table19);

            PdfPTable table20 = new PdfPTable(12);
            //table5.setWidths(columnWidths);
            PdfPCell cell71 = new PdfPCell();
                    //cell1.setBorderColor(BaseColor.BLUE);
            //cell1.setPaddingLeft(10);
            cell71.setBorderWidth(1);
            cell71.setBorderColor(BaseColor.GRAY);
            //cell1.setBackgroundColor(BaseColor.LIGHT_GRAY);
            cell71.setPhrase(new Phrase("Silvassa Municipal Council, Opposite Udyog Bhawan Secretariat Road, Amli, U.T. of Dadra & Nagar Haveli, Silvassa – 396230\nHelp Line Number:1800-1030-636: Email-Id: silvassamunicipalcouncil@gmail.com  \nTiming 09:30 AM to 06:00 PM\n. ", fontEngH_2));
                    //cell68.setBorderColor(BaseColor.LIGHT_GRAY);
            //cell68.setGrayFill(0.9f);
            cell71.setHorizontalAlignment(Element.ALIGN_CENTER);
            //cell71.setVerticalAlignment(Element.ALIGN_CENTER);
            cell71.setColspan(12);
            table20.addCell(cell71);
            document.add(table20);
            
             
            

            if(oldmc.equalsIgnoreCase("REGISTERED")){
                PdfPTable table21 = new PdfPTable(12);
            //table5.setWidths(columnWidths);
            PdfPCell cell72 = new PdfPCell();
                    //cell1.setBorderColor(BaseColor.BLUE);
            //cell1.setPaddingLeft(10);
            cell72.setBorderWidth(1);
            cell72.setBorderColor(BaseColor.GRAY);
            //cell72.setBackgroundColor(BaseColor.LIGHT_GRAY);
            cell72.setPhrase(new Phrase(""+oldmc  , fontEngH_3));
                    //cell68.setBorderColor(BaseColor.LIGHT_GRAY);
            //cell72.setGrayFill(0.9f);
            cell72.setHorizontalAlignment(Element.ALIGN_CENTER);
            cell72.setVerticalAlignment(Element.ALIGN_MIDDLE);
            cell72.setColspan(12);
            table21.addCell(cell72);
            document.add(table21);
            }else{
               PdfPTable table21 = new PdfPTable(12);
            //table5.setWidths(columnWidths);
            PdfPCell cell72 = new PdfPCell();
                    //cell1.setBorderColor(BaseColor.BLUE);
            //cell1.setPaddingLeft(10);
            cell72.setBorderWidth(1);
            cell72.setBorderColor(BaseColor.GRAY);
            //cell72.setBackgroundColor(BaseColor.BLACK);
            cell72.setPhrase(new Phrase(""+oldmc , fontEngH_3));
                    //cell68.setBorderColor(BaseColor.LIGHT_GRAY);
            //cell72.setGrayFill(0.9f);
            cell72.setHorizontalAlignment(Element.ALIGN_CENTER);
            cell72.setVerticalAlignment(Element.ALIGN_MIDDLE);
            cell72.setColspan(12);
            table21.addCell(cell72);
            document.add(table21); 
            }
            
            PdfPTable table22 = new PdfPTable(12);
            //table5.setWidths(columnWidths);
            PdfPCell cell72_1 = new PdfPCell();
                    //cell1.setBorderColor(BaseColor.BLUE);
            //cell1.setPaddingLeft(10);
            cell72_1.setBorderWidth(0);
            //cell72_1.setBorderColor(BaseColor.GRAY);
            //cell72.setBackgroundColor(BaseColor.BLACK);
            cell72_1.setPhrase(new Phrase(""+bean.getDistributionId() , fontEngH_11));
                    //cell68.setBorderColor(BaseColor.LIGHT_GRAY);
            //cell72.setGrayFill(0.9f);
            cell72_1.setHorizontalAlignment(Element.ALIGN_RIGHT);
            cell72_1.setVerticalAlignment(Element.ALIGN_MIDDLE);
            cell72_1.setColspan(12);
            table22.addCell(cell72_1);
            document.add(table22); 
                
            

            sno++;
            index++;
            sp = "";
            op = "";
            oldmc = "";
            stFloor = "";
            electricData = "";
            stCover = "";
            stBuse = "";
            stTax = "";
            stTaxRate = "";
            stAnnualRatabaleValue = "";
            stAnnualRent = "";
            stLocationClassess = "";
            stSelfRent = "";
            stContructionType = "";
            stCategoryDetail = "";
            document.newPage();
            totalCoveredArea = 0;
            totalTax = 0;

        }
        } catch(Exception ex){
            ex.printStackTrace();
        }
        //System.out.println("end");
    }

            //writer.close();
    //SimpleDateFormat sdf = new SimpleDateFormat(("yyyy-MM-dd hh:mm:ss"));
    //String date = (String) sdf.format(Calendar.getInstance().getTime());
    //date = date.substring(0, 10);
    //String date1 = date.toString();
    //String mname = "";
    //String path = "c://excel";
    //String name = "Assessment_Register";
    //String fileName = path + "//" + mname + "- " + date + ".xls";
    //FileOutputStream fileOut = new FileOutputStream(fileName);
    //hwb.write(fileOut);
    //fileOut.close();
    //mname = "";
}
