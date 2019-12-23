/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.silvassa.service;

import com.itextpdf.text.Document;
import com.itextpdf.text.Element;
import com.itextpdf.text.Font;
import com.itextpdf.text.Image;
import com.itextpdf.text.Paragraph;
import com.itextpdf.text.Phrase;
import com.itextpdf.text.pdf.BaseFont;
import com.itextpdf.text.pdf.PdfPCell;
import com.itextpdf.text.pdf.PdfPTable;
import com.itextpdf.text.pdf.PdfWriter;
import java.util.Map;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.PropertySource;
import org.springframework.core.env.Environment;

/**
 *
 * @author CEINFO
 */
@PropertySource("classpath:ApplicationResources.properties")
public class NoDataFound extends AbstractITextPdfNoDataView {

    @Autowired
    private Environment env;

    @Override
    protected void buildPdfDocument(Map<String, Object> model, Document document,
            PdfWriter writer, HttpServletRequest request, HttpServletResponse response)
            throws Exception {

        String msg = (String) model.get("msg");

        String path = request.getRealPath("/res/img/logo2.png");
        String fontPathHindi1 = request.getRealPath("/res/fonts/kruti-dev-021.ttf");
        BaseFont bf = BaseFont.createFont(fontPathHindi1, BaseFont.IDENTITY_H, BaseFont.EMBEDDED);
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

        Image image1 = Image.getInstance(path);
        image1.scalePercent(15f);

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
        
        
        PdfPTable  msgTable = new PdfPTable(6);
        PdfPCell msg_cell1_ = new PdfPCell(new Phrase(msg,fontEngH_3));
        //cell1.setPaddingLeft(10);
        msg_cell1_.setBorderWidth(0);
        //cell6.setPhrase(new Phrase("ak",fontEngH ));
        msg_cell1_.setHorizontalAlignment(Element.ALIGN_CENTER);
        msg_cell1_.setVerticalAlignment(Element.ALIGN_MIDDLE);
        msg_cell1_.setColspan(6);
        msgTable.addCell(msg_cell1_);
        document.add(msgTable);
        
        
        
        
        
    }

}
