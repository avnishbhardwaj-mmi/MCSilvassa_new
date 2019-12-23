package com.silvassa.service;

import com.itextpdf.text.Document;
import com.itextpdf.text.DocumentException;
import com.itextpdf.text.PageSize;
import com.itextpdf.text.pdf.PdfWriter;
import com.silvassa.model.PaymentBean;
import com.silvassa.util.MmiPathController;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.OutputStream;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.springframework.web.servlet.view.AbstractView;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
/**
 *
 * @author CEINFO
 */
public abstract class AbstractITextPdfNoDataView extends AbstractView {

    public AbstractITextPdfNoDataView() {
        setContentType("application/pdf");
    }

    @Override
    protected boolean generatesDownloadContent() {
        return true;
    }

    @Override
    protected void renderMergedOutputModel(Map<String, Object> model,
            HttpServletRequest request, HttpServletResponse response) throws Exception {
        // IE workaround: write into byte array first.
        ByteArrayOutputStream baos = createTemporaryOutputStream();
        String ppid = "";
               //30f
        // Apply preferences and build metadata.

        Document document = newDocument();
        document.setMargins(-10f, -20f, 10f, 0f);
                //document.setMargins(0f,-20f,10f,0f);

        PdfWriter writer = newWriter(document, baos);
        prepareWriter(model, writer, request);
        buildPdfMetadata(model, document, request);

        // Build PDF document.
        document.open();
        buildPdfDocument(model, document, writer, request, response);

        document.close();
        byte bb[] = baos.toByteArray();
//                for(int i=0;i<bb.length;i++){
//                   System.out.println((char)bb[i]); 
//                }
//                File f1=new File("D:\\"+ppid+".pdf") ;
//                FileOutputStream fout1=new FileOutputStream(f1);    
//                PdfWriter writer1 = PdfWriter.getInstance(document,  baos);
//                fout1.write(bb);
//                fout1.close();

                     //baos.writeTo(fout1); 
        // Flush to HTTP response.
        writeToResponse(response, baos);
               // File f1=new File("D:\\f1.pdf") ;
        //FileOutputStream fout1=new FileOutputStream(f1);    
        //PdfWriter writer1 = PdfWriter.getInstance(document, response.getOutputStream());
    }

    protected Document newDocument() {
        return new Document(PageSize.A4);
    }

    protected PdfWriter newWriter(Document document, OutputStream os) throws DocumentException {
        return PdfWriter.getInstance(document, os);
    }

    protected void prepareWriter(Map<String, Object> model, PdfWriter writer, HttpServletRequest request)
            throws DocumentException {

        writer.setViewerPreferences(getViewerPreferences());
    }

    protected int getViewerPreferences() {
        return PdfWriter.ALLOW_PRINTING | PdfWriter.PageLayoutSinglePage;
    }

    protected void buildPdfMetadata(Map<String, Object> model, Document document, HttpServletRequest request) {
    }

    protected abstract void buildPdfDocument(Map<String, Object> model, Document document, PdfWriter writer,
            HttpServletRequest request, HttpServletResponse response) throws Exception;
}
