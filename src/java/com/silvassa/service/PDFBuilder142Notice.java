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
import com.itextpdf.text.pdf.PdfPCell;
import com.itextpdf.text.pdf.PdfPTable;
import com.itextpdf.text.pdf.PdfWriter;
import com.silvassa.bean.PrivateNoticeBean;
import java.io.FileOutputStream;
import java.text.DecimalFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Iterator;
import org.springframework.stereotype.Component;
/**
 *
 * @author CEINFO
 */
//@Component("pdfView1")
public class PDFBuilder142Notice extends AbstractITextPdfNoticeView {

	@Override
	protected void buildPdfDocument(Map<String, Object> model, Document document,
			PdfWriter writer, HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		// get data model which is passed by the Spring container
		List<PrivateNoticeBean> beanList = (List<PrivateNoticeBean>) model.get("taxList");
                //System.out.println("beanList "+beanList.size());
                Iterator itr=beanList.iterator();
                //System.out.println("PDFBuilder142Notice");
                String path=request.getRealPath("/res/img/logo1.png");
                String path1=request.getRealPath("/res/img/23.png");
                String pid="";
                String clientName="";
                String address1="";
                String address2="";
                String amount="";
                String period="upto2018";
                
                //String fontPathGujrati=request.getRealPath("/res/fonts/wwgj0101.ttf");
                String fontPathHindi1=request.getRealPath("/res/fonts/kruti-dev-021.ttf");
                //String fontPathHindi2=request.getRealPath("/res/fonts/mangal.ttf");
                BaseFont bf = BaseFont.createFont(fontPathHindi1,BaseFont.IDENTITY_H, BaseFont.EMBEDDED);
                //BaseFont bf = BaseFont.createFont("c:\\windows\\fonts\\Kruti_Dev.ttf",BaseFont.IDENTITY_H, BaseFont.EMBEDDED);
                
                Font fontHindi=new Font(bf,18,Font.BOLD);
                Font fontHindi_1=new Font(bf,14,Font.BOLD);
                Font fontHindi_2=new Font(bf,8,Font.BOLD);
                Font fontEngH=new Font(Font.FontFamily.TIMES_ROMAN,11,Font.BOLD);
                Font fontEngH_1=new Font(Font.FontFamily.TIMES_ROMAN,11,Font.BOLD);
                Font fontEngH_2=new Font(Font.FontFamily.TIMES_ROMAN,8,Font.NORMAL);
                Font fontEngH_3=new Font(Font.FontFamily.TIMES_ROMAN,14,Font.UNDERLINE);
                Font fontEngH_4=new Font(Font.FontFamily.TIMES_ROMAN,10,Font.NORMAL);
                Font fontEngH_5=new Font(Font.FontFamily.TIMES_ROMAN,12,Font.NORMAL);
                Font fontEngH_6=new Font(Font.FontFamily.TIMES_ROMAN,12,Font.BOLD);
                    
                
                    
                    
                    
                
             //Document document = new Document(PageSize.A4,0,0,10,0);
            //PdfWriter writer = PdfWriter.getInstance(document, new FileOutputStream("d://noticetest.pdf"));
            //document.open();
            Image image1 = Image.getInstance(path);
            Image image2 = Image.getInstance(path1);
            //Image image2 = Image.getInstance("d:\\img\\23.png");
             SimpleDateFormat sdf = new SimpleDateFormat(("dd-MM-yyyy hh:mm:ss"));
             String  date2 = (String)sdf.format(Calendar.getInstance().getTime());
             date2=date2.substring(0,10);
             DecimalFormat df = new DecimalFormat("#,###");
             Calendar c = Calendar.getInstance();
             c.add(Calendar.DAY_OF_MONTH, 30); 
             String newDate = sdf.format(c.getTime()); 
             newDate=newDate.substring(0,10);
                    
            
                
            
               while(itr.hasNext()){
                String sp = "";
                String op = "";
                String oldmc = "";
                int index = 1;
                int sno = 1;
                
                   
                 PrivateNoticeBean bean= (PrivateNoticeBean)itr.next();
                 clientName=bean.getOwner_Father();
                 address1=bean.getAddress();
                 amount=bean.getTax();
                 pid=bean.getUniqueId();
                 
                 
                  PdfPTable table = new PdfPTable(6); // 3 columns.
                    //table.setWidthPercentage(100); //Width 100%
                    //table.setSpacingBefore(10f); //Space before table
                    //table.setSpacingAfter(10f); //Space after table

                    //Set Column widths
                    float[] columnWidths = {1f, 1f, 1f,1f,1f,1f};
                    table.setWidths(columnWidths);

                    PdfPCell cell1 = new PdfPCell();
                    //cell1.setBorderColor(BaseColor.BLUE);
                    //cell1.setPaddingLeft(10);
                    cell1.setBorder(0);
                    cell1.setPhrase(new Phrase(" ",fontHindi_2 ));
                    cell1.setHorizontalAlignment(Element.ALIGN_CENTER);
                    cell1.setVerticalAlignment(Element.ALIGN_MIDDLE);
                    cell1.setColspan(2);
                    
                    PdfPCell cell2 = new PdfPCell();
                    //cell1.setBorderColor(BaseColor.BLUE);
                    //cell1.setPaddingLeft(10);
                    cell2.setBorder(0);
                    cell2.setPhrase(new Phrase("flyoklk uxj ifj’kn",fontHindi));
                    cell2.setHorizontalAlignment(Element.ALIGN_CENTER);
                    cell2.setVerticalAlignment(Element.ALIGN_MIDDLE);
                    cell2.setColspan(3);
                    
                    PdfPCell cell3 = new PdfPCell();
                    //cell1.setBorderColor(BaseColor.BLUE);
                    //cell1.setPaddingLeft(10);
                    cell3.setBorder(0);
                    cell3.setPhrase(new Phrase("",fontHindi_2 ));
                    cell3.setHorizontalAlignment(Element.ALIGN_CENTER);
                    cell3.setVerticalAlignment(Element.ALIGN_MIDDLE);
                    cell3.setColspan(1);
                    table.addCell(cell1);
                    table.addCell(cell2);
                    table.addCell(cell3);
                    //System.out.println("document "+document.isOpen());

                    document.add(table);
                    
                    PdfPTable table1 = new PdfPTable(6);
                    table1.setWidths(columnWidths);
                    
                    PdfPCell cell4 = new PdfPCell(image1);
                    //cell1.setBorderColor(BaseColor.BLUE);
                    //cell1.setPaddingLeft(10);
                    cell4.setBorder(0);
                    //cell4.setPhrase(new Phrase("SILVASSA ",fontEngH ));
                    cell4.setHorizontalAlignment(Element.ALIGN_CENTER);
                    cell4.setVerticalAlignment(Element.ALIGN_MIDDLE);
                    cell4.setColspan(2);
                    
                    PdfPCell cell5 = new PdfPCell();
                    //cell1.setBorderColor(BaseColor.BLUE);
                    //cell1.setPaddingLeft(10);
                    cell5.setBorder(0);
                    cell5.setPhrase(new Phrase("SILVASSA MUNICIPAL COUNCIL",fontEngH));
                    cell5.setHorizontalAlignment(Element.ALIGN_CENTER);
                    cell5.setVerticalAlignment(Element.ALIGN_MIDDLE);
                    cell5.setColspan(3);
                    
                    PdfPCell cell6 = new PdfPCell(image2);
                    //cell1.setBorderColor(BaseColor.BLUE);
                    //cell1.setPaddingLeft(10);
                    cell6.setBorder(0);
                    //cell6.setPhrase(new Phrase("ak",fontEngH ));
                    cell6.setHorizontalAlignment(Element.ALIGN_CENTER);
                    cell6.setVerticalAlignment(Element.ALIGN_MIDDLE);
                    cell6.setColspan(1);
                    
                    table1.addCell(cell4);
                    table1.addCell(cell5);
                    table1.addCell(cell6);

                    document.add(table1);
                    
                    
                    PdfPTable table2 = new PdfPTable(6);
                    table2.setWidths(columnWidths);
                    PdfPCell cell7 = new PdfPCell();
                    //cell1.setBorderColor(BaseColor.BLUE);
                    //cell1.setPaddingLeft(10);
                    cell7.setBorder(0);
                    cell7.setPhrase(new Phrase(" ",fontHindi_2 ));
                    cell7.setHorizontalAlignment(Element.ALIGN_CENTER);
                    cell7.setVerticalAlignment(Element.ALIGN_MIDDLE);
                    cell7.setColspan(2);
                    
                    PdfPCell cell8 = new PdfPCell();
                    //cell1.setBorderColor(BaseColor.BLUE);
                    //cell1.setPaddingLeft(10);
                    cell8.setBorder(0);
                    cell8.setPhrase(new Phrase("la?k”kkflr izns”k nknjk ,oa uxj gosyh",fontHindi_1));
                    cell8.setHorizontalAlignment(Element.ALIGN_CENTER);
                    cell8.setVerticalAlignment(Element.ALIGN_MIDDLE);
                    cell8.setColspan(3);
                    
                    PdfPCell cell9 = new PdfPCell();
                    //cell1.setBorderColor(BaseColor.BLUE);
                    //cell1.setPaddingLeft(10);
                    cell9.setBorder(0);
                    cell9.setPhrase(new Phrase("",fontHindi_2 ));
                    cell9.setHorizontalAlignment(Element.ALIGN_CENTER);
                    cell9.setVerticalAlignment(Element.ALIGN_MIDDLE);
                    cell9.setColspan(1);
                    table2.addCell(cell7);
                    table2.addCell(cell8);
                    table2.addCell(cell9);

                    document.add(table2);
                    
                    PdfPTable table3 = new PdfPTable(6);
                    table3.setWidths(columnWidths);
                    PdfPCell cell10 = new PdfPCell();
                    //cell1.setBorderColor(BaseColor.BLUE);
                    //cell1.setPaddingLeft(10);
                    cell10.setBorder(0);
                    cell10.setPhrase(new Phrase(" ",fontHindi_2 ));
                    cell10.setHorizontalAlignment(Element.ALIGN_CENTER);
                    cell10.setVerticalAlignment(Element.ALIGN_MIDDLE);
                    cell10.setColspan(2);
                    
                    PdfPCell cell11 = new PdfPCell();
                    //cell1.setBorderColor(BaseColor.BLUE);
                    //cell1.setPaddingLeft(10);
                    cell11.setBorder(0);
                    cell11.setPhrase(new Phrase("SILVASSA - 39620",fontEngH_1));
                    cell11.setHorizontalAlignment(Element.ALIGN_CENTER);
                    cell11.setVerticalAlignment(Element.ALIGN_MIDDLE);
                    cell11.setColspan(3);
                    
                    PdfPCell cell12 = new PdfPCell();
                    //cell1.setBorderColor(BaseColor.BLUE);
                    //cell1.setPaddingLeft(10);
                    cell12.setBorder(0);
                    cell12.setPhrase(new Phrase("",fontHindi_2 ));
                    cell12.setHorizontalAlignment(Element.ALIGN_CENTER);
                    cell12.setVerticalAlignment(Element.ALIGN_MIDDLE);
                    cell12.setColspan(1);
                    table3.addCell(cell10);
                    table3.addCell(cell11);
                    table3.addCell(cell12);

                    document.add(table3);
                    
                    PdfPTable table4 = new PdfPTable(6);
                    table4.setWidths(columnWidths);
                    PdfPCell cell13 = new PdfPCell();
                    //cell1.setBorderColor(BaseColor.BLUE);
                    //cell1.setPaddingLeft(10);
                    cell13.setBorder(0);
                    cell13.setPhrase(new Phrase(" ",fontHindi_2 ));
                    cell13.setHorizontalAlignment(Element.ALIGN_CENTER);
                    cell13.setVerticalAlignment(Element.ALIGN_MIDDLE);
                    cell13.setColspan(2);
                    
                    PdfPCell cell14 = new PdfPCell();
                    //cell1.setBorderColor(BaseColor.BLUE);
                    //cell1.setPaddingLeft(10);
                    cell14.setBorder(0);
                    cell14.setPhrase(new Phrase("Phone No.:0260-2633192, Fax No.:0260-2633191",fontEngH_2));
                    cell14.setHorizontalAlignment(Element.ALIGN_CENTER);
                    cell14.setVerticalAlignment(Element.ALIGN_MIDDLE);
                    cell14.setColspan(3);
                    
                    PdfPCell cell15 = new PdfPCell();
                    //cell1.setBorderColor(BaseColor.BLUE);
                    //cell1.setPaddingLeft(10);
                    cell15.setBorder(0);
                    cell15.setPhrase(new Phrase("",fontHindi_2 ));
                    cell15.setHorizontalAlignment(Element.ALIGN_CENTER);
                    cell15.setVerticalAlignment(Element.ALIGN_MIDDLE);
                    cell15.setColspan(1);
                    table4.addCell(cell13);
                    table4.addCell(cell14);
                    table4.addCell(cell15);

                    document.add(table4);
                    
                    PdfPTable table5 = new PdfPTable(6);
                    table5.setWidths(columnWidths);
                    PdfPCell cell16 = new PdfPCell();
                    //cell1.setBorderColor(BaseColor.BLUE);
                    //cell1.setPaddingLeft(10);
                    cell13.setBorder(0);
                    cell13.setPhrase(new Phrase(" ",fontHindi_2 ));
                    cell13.setHorizontalAlignment(Element.ALIGN_CENTER);
                    cell13.setVerticalAlignment(Element.ALIGN_MIDDLE);
                    cell13.setColspan(6);
                    
                    table5.addCell(cell16);
                    
                    document.add(table5);
                    
                    PdfPTable table6 = new PdfPTable(6);
                    table6.setWidths(columnWidths);
                    PdfPCell cell17 = new PdfPCell();
                    //cell1.setBorderColor(BaseColor.BLUE);
                    //cell1.setPaddingLeft(10);
                    cell17.setBorder(0);
                    cell17.setPhrase(new Phrase("__________________________________________________________________________________ ",fontEngH_1 ));
                    cell17.setHorizontalAlignment(Element.ALIGN_CENTER);
                    cell17.setVerticalAlignment(Element.ALIGN_MIDDLE);
                    cell17.setColspan(6);
                    
                    table6.addCell(cell17);
                    
                    document.add(table6);
                    
                    
                    PdfPTable table7 = new PdfPTable(6);
                    table6.setWidths(columnWidths);
                    PdfPCell cell18 = new PdfPCell();
                    //cell1.setBorderColor(BaseColor.BLUE);
                    //cell1.setPaddingLeft(10);
                    cell18.setBorder(0);
                    cell18.setPhrase(new Phrase("No.SMC/CO/Acct/2018-19/471/1913 ",fontEngH_2 ));
                    cell18.setHorizontalAlignment(Element.ALIGN_LEFT);
                    cell18.setVerticalAlignment(Element.ALIGN_MIDDLE);
                    cell18.setColspan(2);
                    
                    PdfPCell cell19 = new PdfPCell();
                    //cell1.setBorderColor(BaseColor.BLUE);
                    //cell1.setPaddingLeft(10);
                    cell19.setBorder(0);
                    cell19.setPhrase(new Phrase(" ",fontEngH_2));
                    cell19.setHorizontalAlignment(Element.ALIGN_CENTER);
                    cell19.setVerticalAlignment(Element.ALIGN_MIDDLE);
                    cell19.setColspan(3);
                    
                    PdfPCell cell20 = new PdfPCell();
                    //cell1.setBorderColor(BaseColor.BLUE);
                    //cell1.setPaddingLeft(10);
                    cell20.setBorder(0);
                    cell20.setPhrase(new Phrase("Date: "+date2,fontEngH_2 ));
                    cell20.setHorizontalAlignment(Element.ALIGN_LEFT);
                    cell20.setVerticalAlignment(Element.ALIGN_MIDDLE);
                    cell20.setColspan(1);
                    table7.addCell(cell18);
                    table7.addCell(cell19);
                    table7.addCell(cell20);

                    document.add(table7);
                    document.add(new Paragraph(" "));
                    document.add(new Paragraph(" "));
                    
                    PdfPTable table8 = new PdfPTable(6);
                    table8.setWidths(columnWidths);
                    PdfPCell cell21 = new PdfPCell();
                    //cell1.setBorderColor(BaseColor.BLUE);
                    //cell1.setPaddingLeft(10);
                    cell21.setBorder(0);
                    cell21.setPhrase(new Phrase("  ",fontEngH_2 ));
                    cell21.setHorizontalAlignment(Element.ALIGN_LEFT);
                    cell21.setVerticalAlignment(Element.ALIGN_MIDDLE);
                    cell21.setColspan(2);
                    
                    PdfPCell cell22 = new PdfPCell();
                    //cell1.setBorderColor(BaseColor.BLUE);
                    //cell1.setPaddingLeft(10);
                    cell22.setBorder(0);
                    cell22.setPhrase(new Phrase(" NOTICE OF DEMAND ",fontEngH_3));
                    cell22.setHorizontalAlignment(Element.ALIGN_CENTER);
                    cell22.setVerticalAlignment(Element.ALIGN_MIDDLE);
                    cell22.setColspan(3);
                    
                    PdfPCell cell23 = new PdfPCell();
                    //cell1.setBorderColor(BaseColor.BLUE);
                    //cell1.setPaddingLeft(10);
                    cell23.setBorder(0);
                    cell23.setPhrase(new Phrase(" ",fontEngH_2 ));
                    cell23.setHorizontalAlignment(Element.ALIGN_RIGHT);
                    cell23.setVerticalAlignment(Element.ALIGN_MIDDLE);
                    cell23.setColspan(1);
                    table8.addCell(cell21);
                    table8.addCell(cell22);
                    table8.addCell(cell23);

                    document.add(table8);
                    
                    PdfPTable table9 = new PdfPTable(6);
                    table9.setWidths(columnWidths);
                    PdfPCell cell24 = new PdfPCell();
                    //cell1.setBorderColor(BaseColor.BLUE);
                    //cell1.setPaddingLeft(10);
                    cell24.setBorder(0);
                    cell24.setPhrase(new Phrase("  ",fontEngH_2 ));
                    cell24.setHorizontalAlignment(Element.ALIGN_LEFT);
                    cell24.setVerticalAlignment(Element.ALIGN_MIDDLE);
                    cell24.setColspan(2);
                    
                    PdfPCell cell25 = new PdfPCell();
                    //cell1.setBorderColor(BaseColor.BLUE);
                    //cell1.setPaddingLeft(10);
                    cell25.setBorder(0);
                    cell25.setPhrase(new Phrase("(SECTION 142) ",fontEngH_4));
                    cell25.setHorizontalAlignment(Element.ALIGN_CENTER);
                    cell25.setVerticalAlignment(Element.ALIGN_MIDDLE);
                    cell25.setColspan(3);
                    
                    PdfPCell cell26 = new PdfPCell();
                    //cell1.setBorderColor(BaseColor.BLUE);
                    //cell1.setPaddingLeft(10);
                    cell26.setBorder(0);
                    cell26.setPhrase(new Phrase(" ",fontEngH_2 ));
                    cell26.setHorizontalAlignment(Element.ALIGN_RIGHT);
                    cell26.setVerticalAlignment(Element.ALIGN_MIDDLE);
                    cell26.setColspan(1);
                    
                    table9.addCell(cell24);
                    table9.addCell(cell25);
                    table9.addCell(cell26);

                    document.add(table9);
                    document.add(new Paragraph(" "));
                    
                    PdfPTable table10 = new PdfPTable(6);
                    table10.setWidths(columnWidths);
                    PdfPCell cell27 = new PdfPCell();
                    //cell1.setBorderColor(BaseColor.BLUE);
                    //cell1.setPaddingLeft(10);
                    cell27.setBorder(0);
                    cell27.setPhrase(new Phrase("To,"+"\n"+pid+"\n"+clientName+"\n"+address1+"\n",fontEngH_6 ));
                    cell27.setHorizontalAlignment(Element.ALIGN_LEFT);
                    cell27.setVerticalAlignment(Element.ALIGN_MIDDLE);
                    cell27.setColspan(6);
                    
                    table10.addCell(cell27);
                    document.add(table10);
                    document.add(new Paragraph(" "));
                    
                    PdfPTable table11 = new PdfPTable(6);
                    table11.setWidths(columnWidths);
                    PdfPCell cell28 = new PdfPCell();
                    //cell1.setBorderColor(BaseColor.BLUE);
                    //cell1.setPaddingLeft(10);
                    cell28.setBorder(0);
                    cell28.setPhrase(new Phrase("Whereas, several notice have been issued to you, "+clientName+" for payment of property tax to the tune of Rs. "+df.format(Long.parseLong(amount))+"/-"+" due from "+period,fontEngH_5 ));
                    cell28.setHorizontalAlignment(Element.ALIGN_LEFT);
                    cell28.setVerticalAlignment(Element.ALIGN_MIDDLE);
                    cell28.setLeading(1.5f, 1.2f);
                    cell28.setColspan(6);
                                        
                    table11.addCell(cell28);
                    document.add(table11);
                    
                    document.add(new Paragraph(" "));
                    PdfPTable table12 = new PdfPTable(6);
                    table12.setWidths(columnWidths);
                    PdfPCell cell29 = new PdfPCell();
                    //cell1.setBorderColor(BaseColor.BLUE);
                    //cell1.setPaddingLeft(10);
                    cell29.setBorder(0);
                    cell29.setPhrase(new Phrase("Take notice that the Silvassa Municipal Council demands from  " +clientName+", the sum of Rs. "+df.format(Long.parseLong(amount))+"/- due from "+period+" on account of property tax or other tax leviable under bye-law Section 102 of Municipal Council Regulation 2004 and that if, within 15 days from the service of the notice, the said sum in not paid in Silvassa Municipal council, Silvassa and sufficient cause for non-payment in not shown in the Chief Officer, a warrant of distress or attachment will be issued for the recovery of the same with costs.",fontEngH_5 ));
                    cell29.setHorizontalAlignment(Element.ALIGN_LEFT);
                    cell29.setVerticalAlignment(Element.ALIGN_MIDDLE);
                    cell29.setColspan(6);
                    cell29.setLeading(1.5f, 1.2f);
                    table12.addCell(cell29);
                    document.add(table12);
                    
                    document.add(new Paragraph(" "));
                    document.add(new Paragraph(" "));
                    
                    PdfPTable table13 = new PdfPTable(6);
                    table13.setWidths(columnWidths);
                    PdfPCell cell30 = new PdfPCell();
                    //cell1.setBorderColor(BaseColor.BLUE);
                    //cell1.setPaddingLeft(10);
                    cell30.setBorder(0);
                    cell30.setPhrase(new Phrase("  ",fontEngH_2 ));
                    cell30.setHorizontalAlignment(Element.ALIGN_LEFT);
                    cell30.setVerticalAlignment(Element.ALIGN_MIDDLE);
                    cell30.setColspan(2);
                    
                    PdfPCell cell31 = new PdfPCell();
                    //cell1.setBorderColor(BaseColor.BLUE);
                    //cell1.setPaddingLeft(10);
                    cell31.setBorder(0);
                    cell31.setPhrase(new Phrase(" ",fontEngH_4));
                    cell31.setHorizontalAlignment(Element.ALIGN_CENTER);
                    cell31.setVerticalAlignment(Element.ALIGN_MIDDLE);
                    cell31.setColspan(2);
                    
                    PdfPCell cell32 = new PdfPCell();
                    //cell1.setBorderColor(BaseColor.BLUE);
                    //cell1.setPaddingLeft(10);
                    cell32.setBorder(0);
                    cell32.setPhrase(new Phrase("Chief Officer\n"+"Silvassa Municipal Council\n" +"Silvassa ",fontEngH_1 ));
                    cell32.setHorizontalAlignment(Element.ALIGN_CENTER);
                    cell32.setVerticalAlignment(Element.ALIGN_MIDDLE);
                    cell32.setColspan(2);
                    
                    table13.addCell(cell30);
                    table13.addCell(cell31);
                    table13.addCell(cell32);

                    document.add(table13);
                    
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
            //writer.close();
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

