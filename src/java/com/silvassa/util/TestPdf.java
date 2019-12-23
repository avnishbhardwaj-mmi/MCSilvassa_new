/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.silvassa.util;
import com.itextpdf.text.Document;
import com.itextpdf.text.DocumentException;
import com.itextpdf.text.Element;
import com.itextpdf.text.Phrase;
import com.itextpdf.text.pdf.PdfPCell;
import com.itextpdf.text.pdf.PdfPTable;
import com.itextpdf.text.pdf.PdfWriter;

import java.io.FileNotFoundException;
import java.io.FileOutputStream;

public class TestPdf {
    public static void main(String[] args) {
        Document doc = new Document();
        try {
            PdfWriter.getInstance(doc, new FileOutputStream("c://data//TableCellAlignment.pdf"));
            doc.open();

            // Setting table's cells horizontal alignment
            PdfPTable table = new PdfPTable(3);
            PdfPCell cell1 = new PdfPCell(new Phrase("Cell 1"));
            cell1.setHorizontalAlignment(Element.ALIGN_LEFT);
            table.addCell(cell1);
            PdfPCell cell2 = new PdfPCell(new Phrase("Cell 2"));
            cell2.setHorizontalAlignment(Element.ALIGN_CENTER);
            table.addCell(cell2);
            PdfPCell cell3 = new PdfPCell(new Phrase("Cell 3"));
            cell3.setHorizontalAlignment(Element.ALIGN_RIGHT);
            table.addCell(cell3);
            table.completeRow();

            // Setting table's cells vertical alignment
            PdfPCell[] cells = new PdfPCell[3];
            int[] alignments = new int[]{
                    Element.ALIGN_TOP,
                    Element.ALIGN_MIDDLE,
                    Element.ALIGN_BOTTOM
            };
            int[] alignments2 = new int[]{
                    Element.ALIGN_LEFT,
                    Element.ALIGN_CENTER,
                    Element.ALIGN_RIGHT
            };
            for (int i = 1; i < cells.length; i++) {
                cells[i] = new PdfPCell(new Phrase("Cell " + (i + 1)));
                cells[i].setMinimumHeight(50);
                cells[i].setHorizontalAlignment(alignments2[i]);
                cells[i].setVerticalAlignment(alignments[i]);
                cells[i].setColspan(3);
                table.addCell(cells[i]);
                break;
            }
            table.completeRow();

            doc.add(table);
        } catch (DocumentException | FileNotFoundException e) {
            e.printStackTrace();
        } finally {
            doc.close();
        }
    }
}
