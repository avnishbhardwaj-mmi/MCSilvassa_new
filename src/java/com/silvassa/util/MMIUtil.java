package com.silvassa.util;

import com.itextpdf.text.BaseColor;
import com.itextpdf.text.DocumentException;
import com.itextpdf.text.Font;
import com.itextpdf.text.FontFactory;
import com.itextpdf.text.PageSize;
import com.itextpdf.text.Paragraph;
import com.itextpdf.text.Phrase;
import com.itextpdf.text.pdf.PdfPCell;
import com.itextpdf.text.pdf.PdfPTable;
import com.itextpdf.text.pdf.PdfWriter;
import com.silvassa.bean.MasterBean;
import com.silvassa.bean.PropertyAssessmentBean;
import java.io.ByteArrayOutputStream;
import java.sql.Timestamp;
import java.text.DecimalFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.Map;
import org.apache.commons.beanutils.BeanMap;
import org.apache.commons.lang.StringUtils;
import org.apache.log4j.Logger;
import org.apache.poi.hssf.usermodel.HSSFCellStyle;
import org.apache.poi.hssf.usermodel.HSSFFont;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.CellStyle;
import org.apache.poi.ss.usermodel.IndexedColors;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.util.CellRangeAddress;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;
import org.springframework.stereotype.Component;

@Component("mMIUtil")
public class MMIUtil {

    private static final Logger logger = Logger.getLogger(MMIUtil.class);

    public static final String PAY_TX_INIT = "I"; // Payment Initiated
    public static final String PAY_TX_VERIFY = "V"; // Payment Verify
    public static final String PAY_TX_REJECT = "R"; // Payment Reject
    public static final String PAY_TX_COMPLETE = "C"; // Payment Complete
    public static final String PAY_TX_ERROR_OUR_SIDE = "EOS"; // Error in our program 
    public static final String PAY_TX_ERROR_BANK_SIDE = "EBS"; // Error return form bank
    public static final String PAY_TX_ERROR_BANK_NO_RESPONSE = "EBNR"; // Error return form bank
    public static final String PAY_TX_ERROR_IN_INPUT = "EII"; // Error in our input
    public static final String TAX_GEN = "TAX Generation";
    public static final String NOTICE_GEN = "Notice Generation";
    public static final String STATUS_START = "Start";
    public static final String STATUS_COMPLETE = "Complete";
    public static final String STATUS_ERROR = "Error";
    public static final String OBJ_STATUS_CREATE = "OPEN";
    public static final String OBJ_STATUS_APPROVED = "APPROVED";
    public static final String OBJ_STATUS_FORWARD = "FORWARD";
    public static final String OBJ_STATUS_REVERT = "REVERT";
    public static final String OBJ_STATUS_REJECTED = "REJECTED";

    private static final SimpleDateFormat sdf = new SimpleDateFormat("yyyy.MM.dd.HH.mm.ss");
    private static final DecimalFormat df = new DecimalFormat("#.##");

    public static String getTAXAbleFinancialYear() {

//        Calendar now = Calendar.getInstance();
//
//        int currentMnth = now.get(Calendar.MONTH) + 1; // +1, as in Calendar class month start from 0 instead of 1;
//        int currentYr = now.get(Calendar.YEAR);
//        String assYr = "";
//        if (currentMnth > 3) {
//            assYr = String.valueOf((currentYr - 0)) + "-" + String.valueOf((currentYr + 1));
//        } else {
//            assYr = String.valueOf((currentYr - 1)) + "-" + String.valueOf((currentYr - 0));
//        }
//
//        return assYr;
        return "2017-2018";
    }

    public static String getCurrentFinancialYear() {

//        Calendar now = Calendar.getInstance();
//
//        int currentMnth = now.get(Calendar.MONTH) + 1; // +1, as in Calendar class month start from 0 instead of 1;
//        int currentYr = now.get(Calendar.YEAR);
//        String assYr = "";
//        if (currentMnth > 3) {
//            assYr = String.valueOf((currentYr - 0)) + "-" + String.valueOf((currentYr + 1));
//        } else {
//            assYr = String.valueOf((currentYr - 1)) + "-" + String.valueOf((currentYr - 0));
//        }
//
//        return assYr;
        return "2018-2019";
    }

    public static int compareDates(Date fromDate, Date toDate) throws ParseException, Exception {

//	 -1 : formDate is after toDate
//	  0 : formDate is equal to toDate
//	 +1 : formDate is before toDate
        return fromDate.compareTo(toDate);

    }

    public static Date convertStringToDate(String dateStr) {

        SimpleDateFormat formatter = new SimpleDateFormat("MM/dd/yyyy");
        Date parsedDate = null;
        try {
            parsedDate = formatter.parse(dateStr);
        } catch (Exception ex) {
        }
        return parsedDate;

    }

    public static int compareDates(String fromDate, String toDate) throws ParseException, Exception {

//	 -1 : formDate is after toDate
//	  0 : formDate is equal to toDate
//	 +1 : formDate is before toDate
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
        Date date1 = sdf.parse(fromDate);
        Date date2 = sdf.parse(toDate);

        return date1.compareTo(date2);
    }

    public static boolean isLatePayment(String dueDate) throws ParseException, Exception {

        boolean b = false;

        int res = compareDates(dueDate, formateDate(Calendar.getInstance().getTime()));
        if (res == -1) {
            b = true;
        }
        return b;
    }

    public static boolean isArrearApply(String arrear) throws Exception {

        boolean b = false;
        if (arrear == null || arrear.equals("")) {
            return b;
        }
        Double l = Double.parseDouble(arrear);

        if (l > 0) {
            b = true;
        }

        return b;
    }
  
    public static boolean isRebateApply(String rebate) throws Exception {

        boolean b = false;
        if (rebate == null || rebate.equals("")) {
            return b;
        }
        Double l = Double.parseDouble(rebate);

        if (l > 0) {
            b = true;
        }

        return b;
    }

    public static boolean isPartialAmountPaid(String amount) throws Exception {

        boolean b = false;
        if (amount == null || amount.equals("")) {
            return b;
        }
        Double l = Double.parseDouble(amount);
        if (l > 0) {
            b = true;
        }

        return b;
    }

    public static String formateDate(Date d) {
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
        return sdf.format(d);
    }

    public static Timestamp getCurrentTimestamp() {
        return new Timestamp(System.currentTimeMillis()); //2016-11-16 06:43:19.77
    }

    public static long getEpocTime() {
        Timestamp timestamp = getCurrentTimestamp();
        return timestamp.getTime(); //1479249799770
    }

    public static Date epocToDate(long epocTime) {
        return new java.util.Date(epocTime);
    }

    //-------------------------------------------------------------------------------------------------
// Method                   : convertHtmlToExcel
// Parameters               : String htmlStr, ByteArrayOutputStream outByteStream
// Return type              : byte[]
// Created on               : MAY,2017
// Ceated by                : Sunil Kumar
// Objective                : Convert HTML table to Excel sheet.
// Modified on              :
// Modified by              :
//-------------------------------------------------------------------------------------------------
    public byte[] convertHtmlToExcel(String title, String htmlStr, ByteArrayOutputStream outByteStream) {

        HSSFWorkbook wb = new HSSFWorkbook();
        HSSFSheet sheet = wb.createSheet();

        //Set Header Font
        HSSFFont headerFont = wb.createFont();
        headerFont.setBoldweight(headerFont.BOLDWEIGHT_BOLD);
        headerFont.setFontHeightInPoints((short) 12);

        //Set Title Style
        CellStyle titleStyle = wb.createCellStyle();
        titleStyle.setFillBackgroundColor(IndexedColors.BLACK.getIndex());
        titleStyle.setAlignment(titleStyle.ALIGN_LEFT);
        titleStyle.setFont(headerFont);

        //Set Header Style
        CellStyle headerStyle = wb.createCellStyle();
        headerStyle.setFillBackgroundColor(IndexedColors.BLACK.getIndex());
        headerStyle.setAlignment(headerStyle.ALIGN_CENTER);
        headerStyle.setFont(headerFont);
        headerStyle.setBorderTop(HSSFCellStyle.BORDER_MEDIUM);
        headerStyle.setBorderBottom(HSSFCellStyle.BORDER_MEDIUM);

        //Text Style
        CellStyle textStyle = wb.createCellStyle();
        textStyle.setWrapText(true);

        //Number Style
        CellStyle numBlockStyle = wb.createCellStyle();
        numBlockStyle.setAlignment(numBlockStyle.ALIGN_RIGHT);
        int rowCount = 0;
        Row header;

        if (title != null && !title.equals("")) {
            Cell titleCell = sheet.createRow(rowCount).createCell(0);
            titleCell.setCellValue(title + " (" + new SimpleDateFormat("dd-MMM-yyyy").format(Calendar.getInstance().getTime()) + ")");
            titleCell.setCellStyle(titleStyle);
            sheet.addMergedRegion(new CellRangeAddress(0, 0, 0, 100));
        } else {
            Cell titleCell = sheet.createRow(rowCount).createCell(0);
            titleCell.setCellValue("Report (" + new SimpleDateFormat("dd-MMM-yyyy").format(Calendar.getInstance().getTime()) + ")");
            titleCell.setCellStyle(titleStyle);
            sheet.addMergedRegion(new CellRangeAddress(0, 0, 0, 100));
        }

        Document doc = Jsoup.parse(htmlStr);
        boolean addSerailHead = true;
        boolean columResize = true;

        Cell cell;

        int serialNo = 0;
        for (Element table : doc.select("table")) {
            rowCount++;
            rowCount++;

            for (Element row : table.select("tr")) {
                header = sheet.createRow(rowCount);
                Elements ths = row.select("th");
                int count = 0;
                for (Element element : ths) {
                    if (addSerailHead) {
                        addSerailHead = false;
                        cell = header.createCell(count);
                        cell.setCellValue("S. No.");
                        cell.setCellStyle(headerStyle);
                        count++;
                    }
                    cell = header.createCell(count);
                    cell.setCellValue(element.text());

                    try {
                        int colSpan = Integer.parseInt(element.attr("colspan"));
                        sheet.addMergedRegion(new CellRangeAddress(2, 2, count, (count + colSpan - 1)));
                    } catch (Exception ex) {
                        logger.info(ex.getMessage());
                    }

                    cell.setCellStyle(headerStyle);

                    count++;
                }
                Elements tds = row.select("td");

                count = 0;
                boolean addSerailId = true;
                if (tds.size() > 0) {
                    header.setHeightInPoints((3 * sheet.getDefaultRowHeightInPoints()));
                    if (row.attr("skipCount") != null && row.attr("skipCount").equalsIgnoreCase("Y")) {
                        // Do nothing.
                    } else {
                        serialNo++;
                    }

                }
                for (Element element : tds) {
                    if (addSerailId) {
                        addSerailId = false;
                        cell = header.createCell(count);
                        if (row.attr("skipCount") != null && row.attr("skipCount").equalsIgnoreCase("Y")) {
                            cell.setCellValue("");
                        } else {
                            cell.setCellValue(serialNo);
                        }

                        cell.setCellStyle(numBlockStyle);
                        count++;
                    }
                    cell = header.createCell(count);
//                  sheet.autoSizeColumn(count);

                    if (StringUtils.isNumericSpace(element.text()) && !element.text().equals("")) {
                        cell.setCellValue(element.text());
                        cell.setCellStyle(numBlockStyle);
                    } else {

                        String str = element.text();
                        //System.out.println("str hhhh"+str);

                        if (str != null) {
                            str = str.replaceAll("-nln-", "\n");
                        }
                        cell.setCellValue(str);
                        cell.setCellStyle(textStyle);

                    }
                    count++;
                }
                rowCount++;
                sheet = wb.getSheetAt(0);
                if (columResize) {
                    for (int j = 0; j < row.select("td").size(); j++) {
                        columResize = false;
                        sheet.autoSizeColumn(j);
                    }
                }

            }

        }

        try {
            wb.write(outByteStream);
        } catch (Exception ex) {
            logger.info(ex.getMessage());
        }
        return outByteStream.toByteArray();
    }

//-------------------------------------------------------------------------------------------------
// Method                   : convertHtmlToPDF
// Parameters               : String htmlStr, ByteArrayOutputStream outByteStream
// Return type              : byte[]
// Created on               : MAY,2017
// Ceated by                : Sunil Kumar
// Objective                : Convert HTML table to PDF file.
// Modified on              :
// Modified by              :
//-------------------------------------------------------------------------------------------------
    private static Font headerFont = new Font(Font.FontFamily.HELVETICA, 8, Font.BOLD);
    private static Font normal_underline = new Font(Font.FontFamily.HELVETICA, 8, Font.BOLD | Font.UNDERLINE);
    private static Font titleFont = new Font(Font.FontFamily.HELVETICA, 11, Font.BOLD);
    private static Font normal = new Font(Font.FontFamily.HELVETICA, 8f, Font.NORMAL);
    private static Font normalb = new Font(Font.FontFamily.HELVETICA, 8f, Font.BOLD);
    private static Font small = new Font(Font.FontFamily.HELVETICA, 7f, Font.NORMAL);
    private static Font headerbold = FontFactory.getFont(FontFactory.HELVETICA, 8.0f, Font.BOLD);

    public byte[] convertHtmlToPDF(String title, String htmlStr, ByteArrayOutputStream outByteStream) {

        com.itextpdf.text.Document document = new com.itextpdf.text.Document(PageSize.A3.rotate());
        org.jsoup.nodes.Document doc = Jsoup.parse(htmlStr);
        ArrayList<String> thArray = new ArrayList();
        ArrayList<ArrayList<String>> trArray = new ArrayList();
        ArrayList<String> tdArray;
        float commonWidth = 5f;
        float sNoWidth = 2f;
        float phoneWidth = 3.5f;
        float pincodeWidth = 2.5f;

        float[] columnWidths = {};
        float[] columnHeaderWidths = {};
        // Pares html data.
//        thArray.add("S. No.");
        int trCount = 0;
        int serialNo = 0;
        boolean fisrtRowSkip = false;
        boolean isRowSkipped = false;
        int colSize = 0;
        for (Element table : doc.select("table")) {
            String customHeader = table.attr("customHeader");
            if (customHeader != null && customHeader.equalsIgnoreCase("Y")) {
                fisrtRowSkip = true;
                isRowSkipped = true;
            } else {
                thArray.add("S. No.");
            }

            for (Element row : table.select("tr")) {
                if (fisrtRowSkip) {
                    fisrtRowSkip = false;
                } else {

                    //Fetch header cell if '<th>' found.
                    Elements ths = row.select("th");
                    if (ths.size() > 0) {
                        colSize = ths.size();
                    }
                    for (Element element : ths) {
                        thArray.add(element.text());
                    }
                    //Add row cell if '<td>' found.

                    Elements tds = row.select("td");

                    if (tds.size() > 0) {
                        if (row.attr("skipCount") != null && row.attr("skipCount").equalsIgnoreCase("Y")) {
                            // Do nothing.
                        } else {
                            serialNo++;
                        }

                    }

                    if (!tds.isEmpty()) {
                        tdArray = new ArrayList();
                        if (row.attr("skipCount") != null && row.attr("skipCount").equalsIgnoreCase("Y")) {
                            tdArray.add("");
                        } else {
                            tdArray.add(String.valueOf(serialNo));
                        }

                        for (Element element : tds) {
                            String str = element.text();
                            //System.out.println("str hhhh"+str);

                            if (str != null) {
                                str = str.replaceAll("-nln-", "\n");
                            }
                            //System.out.println("data "+str);
                            tdArray.add(str);
                        }
                        trArray.add(tdArray);
                    }
                    trCount += 1;
                }
            }
        }

        // Create PDF file
        try {
            PdfWriter.getInstance(document, outByteStream);
            document.open();

            if (title != null && !title.equals("")) {
                Paragraph titleP = new Paragraph();
                titleP.setFont(titleFont);
                titleP.add(title + " (" + new SimpleDateFormat("dd-MMM-yyyy").format(Calendar.getInstance().getTime()) + ")");
                document.add(titleP);
                document.add(new Paragraph("\n"));
            } else {
                Paragraph titleP = new Paragraph();
                titleP.setFont(titleFont);
                titleP.add("Report (" + new SimpleDateFormat("dd-MMM-yyyy").format(Calendar.getInstance().getTime()) + ")");
                document.add(titleP);
                document.add(new Paragraph("\n"));
            }
            if (thArray.isEmpty()) {

                logger.info("[convertHtmlToPDF] : No header found.");
            }

            if (isRowSkipped) {

                // For custom header
                Element row = doc.select("table").select("tr:nth-child(1)").get(0);

                Elements ths = row.select("th");
                PdfPTable tableH = new PdfPTable(colSize);
                columnHeaderWidths = new float[colSize];
                tableH.setWidthPercentage(100f);
                int thCount = 0;

                PdfPCell cell2 = new PdfPCell(new Phrase("S. No.", headerFont));
                cell2.setVerticalAlignment(com.itextpdf.text.Element.ALIGN_MIDDLE);
                cell2.setHorizontalAlignment(com.itextpdf.text.Element.ALIGN_CENTER);
                cell2.setBackgroundColor(BaseColor.LIGHT_GRAY);
                cell2.setBorderColor(BaseColor.DARK_GRAY);
                cell2.setMinimumHeight(18f);
                tableH.addCell(cell2);
                thCount += 1;
                columnHeaderWidths[0] = commonWidth;
                for (Element element : ths) {

                    PdfPCell cell1 = new PdfPCell(new Phrase(element.text(), headerFont));
                    cell1.setVerticalAlignment(com.itextpdf.text.Element.ALIGN_MIDDLE);
                    cell1.setHorizontalAlignment(com.itextpdf.text.Element.ALIGN_CENTER);
                    cell1.setBackgroundColor(BaseColor.LIGHT_GRAY);
                    cell1.setBorderColor(BaseColor.DARK_GRAY);
                    cell1.setMinimumHeight(18f);

                    try {
                        String spanSize = element.attr("colspan");
                        if (spanSize != null && !spanSize.equalsIgnoreCase("")) {
                            columnHeaderWidths[thCount] = commonWidth;

                            if (Integer.valueOf(spanSize) == 0) {
                                columnHeaderWidths[thCount] = 0f;
                            } else {
                                cell1.setColspan(Integer.valueOf(spanSize));
                                tableH.addCell(cell1);
                            }

                        } else {
                            columnHeaderWidths[thCount] = 0f;
                        }
                    } catch (Exception ex) {
                    }

                    thCount += 1;
                }
                document.add(tableH);

            }

            PdfPTable table = new PdfPTable(thArray.size());
            table.setWidthPercentage(100f);
            int thCount = 0;
            columnWidths = new float[thArray.size()];

            for (String th : thArray) {
                PdfPCell cell1 = new PdfPCell(new Phrase(th, headerFont));
                cell1.setVerticalAlignment(com.itextpdf.text.Element.ALIGN_MIDDLE);
                cell1.setHorizontalAlignment(com.itextpdf.text.Element.ALIGN_CENTER);
                cell1.setBackgroundColor(BaseColor.LIGHT_GRAY);
                cell1.setBorderColor(BaseColor.DARK_GRAY);
                cell1.setMinimumHeight(18f);
                table.addCell(cell1);
                th = th.trim();

                if (th.equalsIgnoreCase("pincode")) {
                    columnWidths[thCount] = pincodeWidth;
                } else if (th.equalsIgnoreCase("S. No.")) {
                    columnWidths[thCount] = sNoWidth;
                } else if (th.equalsIgnoreCase("Contact No")) {
                    columnWidths[thCount] = phoneWidth;
                } else {
                    columnWidths[thCount] = commonWidth;
                }

                thCount += 1;
            }

            for (ArrayList<String> row : trArray) {
                for (String col : row) {
                    PdfPCell cell1 = new PdfPCell(new Phrase(col, small));
                    if (StringUtils.isNumericSpace(col)) {
                        cell1.setHorizontalAlignment(com.itextpdf.text.Element.ALIGN_RIGHT);
                    }
                    cell1.setVerticalAlignment(com.itextpdf.text.Element.ALIGN_CENTER);
                    table.addCell(cell1);
                }
            }
            table.setWidths(columnWidths);
            document.add(table);
            document.close();

        } catch (DocumentException e) {
            logger.info(e.getMessage());
        }

        return outByteStream.toByteArray();
    }

    public static StringBuilder convertMasterBeanToHTMLString(ArrayList<MasterBean> listMBean, String[] attrToAdd, String[] tHead) {

        StringBuilder sb = new StringBuilder();

        sb.append("<table>");
        sb.append("<thead>");
        sb.append("<tr skipCount='Y' >");
        for (String thStr : tHead) {
            sb.append("<th>");
            sb.append(thStr);
            sb.append("</th>");
        }
        sb.append("</tr>");
        sb.append("</thead>");

        sb.append("<tbody>");
        for (MasterBean mb : listMBean) {
            Map map = new BeanMap(mb);
            sb.append("<tr>");
            for (String tdStr : attrToAdd) {
                String[] conArr = null;
                if (tdStr.indexOf(",") != -1) {
                    conArr = tdStr.split(",");
                }
                if (conArr != null) {

                    sb.append("<td>");
                    String val = "";
                    for (String stSub : conArr) {
                        if (map.get(stSub) != null) {
                            val += String.valueOf(map.get(stSub));
                        }
                    }

                    sb.append(val);
                    sb.append("</td>");

                } else {
                    sb.append("<td>");
                    String val = "";
                    if (map.get(tdStr) != null) {
                        val = String.valueOf(map.get(tdStr));
                    }
                    sb.append(val);
                    sb.append("</td>");
                }

            }
            sb.append("</tr>");
        }
        sb.append("</tbody>");

        sb.append("</table>");

        return sb;
    }

    public static StringBuilder convertAssementBeanToHTMLString(List<PropertyAssessmentBean> listMBean, String[] attrToAdd, String[] tHead) {

        StringBuilder sb = new StringBuilder();

        sb.append("<table>");
        sb.append("<thead>");
        sb.append("<tr skipCount='Y' >");
        for (String thStr : tHead) {
            sb.append("<th>");
            sb.append(thStr);
            sb.append("</th>");
        }
        sb.append("</tr>");
        sb.append("</thead>");

        sb.append("<tbody>");
        for (PropertyAssessmentBean mb : listMBean) {
            Map map = new BeanMap(mb);
            sb.append("<tr>");
            for (String tdStr : attrToAdd) {
                String[] conArr = null;
                if (tdStr.indexOf(",") != -1) {
                    conArr = tdStr.split(",");
                }
                if (conArr != null) {

                    sb.append("<td>");
                    String val = "";
                    for (String stSub : conArr) {
                        if (map.get(stSub) != null) {
                            val += String.valueOf(map.get(stSub));
                        }
                    }

                    sb.append(val);
                    sb.append("</td>");

                } else {
                    sb.append("<td>");
                    String val = "";
                    if (map.get(tdStr) != null) {
                        val = String.valueOf(map.get(tdStr));
                    }
                    sb.append(val);
                    sb.append("</td>");
                }

            }
            sb.append("</tr>");
        }
        sb.append("</tbody>");

        sb.append("</table>");

        return sb;
    }

    public static Date getFormattedDateTimStamp(String dateStr) throws java.text.ParseException {
        String formattedDate = "";
        SimpleDateFormat readFormat = new SimpleDateFormat("MM/dd/yyyy");
        SimpleDateFormat writeFormat = new SimpleDateFormat("MM/dd/yyyy");
        Date date = null;
        try {
            date = readFormat.parse(dateStr);
        } catch (ParseException e) {
            logger.info(e.getMessage());
        }
        if (date != null) {
            formattedDate = writeFormat.format(date);
            date = writeFormat.parse(formattedDate);
        }
        return date;
    }

    public static String formateAmount(Double d) {
        return df.format(d);
    }

}
