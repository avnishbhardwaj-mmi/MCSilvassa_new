/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.silvassa.service;
import java.io.ByteArrayOutputStream;
import java.io.OutputStream;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.web.servlet.view.AbstractView;

import com.itextpdf.text.Document;
import com.itextpdf.text.DocumentException;
import com.itextpdf.text.PageSize;
import com.itextpdf.text.pdf.PdfWriter;
import com.silvassa.model.PaymentBean;
import com.silvassa.model.PropertyDetails;
import com.silvassa.util.MmiPathController;
import java.io.File;
import java.io.FileOutputStream;
import java.util.Iterator;
import java.util.List;
/**
 *
 * @author CEINFO
 */
public abstract class AbstractITextPdfCollectionReceiptView extends AbstractView {

	public AbstractITextPdfCollectionReceiptView() {
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
                String ppid="";
               //30f
		// Apply preferences and build metadata.
		List<PaymentBean> beanList = (List<PaymentBean>) model.get("taxList");
                Iterator itrOwner=beanList.iterator();
                while(itrOwner.hasNext()){
                 PaymentBean pd=   (PaymentBean)itrOwner.next();
                 ppid=pd.getPayRefId();
                 
                }
                //System.out.println("check ddfdfds  "+ppid);
                
                Document document = newDocument();
                document.setMargins(-10f,-20f,10f,0f);
                //document.setMargins(0f,-20f,10f,0f);
                
		PdfWriter writer = newWriter(document, baos);
		prepareWriter(model, writer, request);
		buildPdfMetadata(model, document, request);
                
		// Build PDF document.
		document.open();
		buildPdfDocument(model, document, writer, request, response);
		
                document.close();
                byte bb[]=baos.toByteArray();
//                for(int i=0;i<bb.length;i++){
//                   System.out.println((char)bb[i]); 
//                }
                String pdfFile = MmiPathController.getDataPath("receipt.path");
                //System.out.println("pdfFile "+pdfFile);
                String path1=pdfFile+ppid+".pdf";
                //String path="D:\\mnt\\vol1\\paymenReceipt\\"+ppid+"_receipt.pdf";
                File f1=new File(path1) ;
                //File f1=new File("D:\\"+ppid+"_receipt.pdf") ;
                FileOutputStream fout1=new FileOutputStream(f1);    
//                PdfWriter writer1 = PdfWriter.getInstance(document,  baos);
                fout1.write(bb);
                fout1.close();
                
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