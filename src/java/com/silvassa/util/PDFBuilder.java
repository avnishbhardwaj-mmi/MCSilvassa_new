package com.silvassa.util;

import java.io.ByteArrayInputStream;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.stereotype.Component;

import com.itextpdf.text.Document;
import com.itextpdf.text.pdf.PdfContentByte;
import com.itextpdf.text.pdf.PdfImportedPage;
import com.itextpdf.text.pdf.PdfReader;
import com.itextpdf.text.pdf.PdfWriter;
import com.silvassa.model.NoticeViewBean;

@Component("pdfViewBill")
public class PDFBuilder extends AbstractITextPdfView {

    @Override
    protected void buildPdfDocument(Map<String, Object> model, Document document, PdfWriter writer,
            HttpServletRequest request, HttpServletResponse response) throws Exception {

        NoticeViewBean nvb = (NoticeViewBean) model.get("data");

        document.open();
        PdfReader reader = new PdfReader(new ByteArrayInputStream(nvb.getNoticePdf()));
        PdfImportedPage page = writer.getImportedPage(reader, 1);
        PdfContentByte cb = writer.getDirectContent();
        cb.addTemplate(page, 0, 0);
        document.close();

    }
}
