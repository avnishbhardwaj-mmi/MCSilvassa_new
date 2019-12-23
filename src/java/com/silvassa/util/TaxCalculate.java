/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package com.silvassa.util;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.List;

/**
 *
 * @author samsung
 */
public class TaxCalculate {
   public Date convertStringToDate(String str) {
     SimpleDateFormat formatter = new SimpleDateFormat("dd-MM-yyyy");
     //String dateInString = str;
     Date date=null;
     try {

            date = formatter.parse(str);
            
            
            
            
            //date=(Date)formatter.format(date);
            //System.out.println(date);
            //System.out.println(formatter.format(date));

        } catch (ParseException e) {
            e.printStackTrace();
        }
     
   return date;
   }
 public int getMonth(String str) {
     Date date=convertStringToDate(str);
     Calendar cal = Calendar.getInstance();
     cal.setTime(date);
     int month = cal.get(Calendar.MONTH)+1;
     return month;
     
 } 
 public int getYear(String str) {
     Date date=convertStringToDate(str);
     Calendar cal = Calendar.getInstance();
     cal.setTime(date);
     int year = cal.get(Calendar.YEAR);
     
     return year;
     
 }
 public int getDay(String str) {
     Date date=convertStringToDate(str);
     Calendar cal = Calendar.getInstance();
     cal.setTime(date);
     int day = cal.get(Calendar.DATE);
     return day;
     
 }
 
 public String dateDiff(String EndDate,String startDate) {
     Date dateEndDate=convertStringToDate(EndDate);
     Date dateStartDate=convertStringToDate(startDate);
     
     Calendar cal1 = Calendar.getInstance();
     Calendar cal2 = Calendar.getInstance();
     cal1.setTime(dateEndDate);
     cal2.setTime(dateStartDate);
     int diffYear = Math.abs(cal1.get(Calendar.YEAR) - cal2.get(Calendar.YEAR));
     int diffMonth = diffYear * 12 + Math.abs(cal1.get(Calendar.MONTH) - cal2.get(Calendar.MONTH));
     int diffdays = Math.abs(cal1.get(Calendar.DAY_OF_MONTH) - cal1.get(Calendar.DAY_OF_MONTH));
     String dd=diffMonth + " Months " + diffdays + " Days";
     System.out.println(diffMonth + " Months " + diffdays + " Days");
     
     
     
     
     return dd;
     
 } 
 public int getMonthFromDate(String str) {
         
     Date date=convertStringToDate(str);
            int result = -1;
            if (date != null) {
                Calendar cal = Calendar.getInstance();
                cal.setTime(date);
                result = cal.get(Calendar.MONTH)+1;
            }
            return result;
    }

    public int getYearFromDate(String str) {
        Date date=convertStringToDate(str);
        int result = -1;
        if (date != null) {
            Calendar cal = Calendar.getInstance();
            cal.setTime(date);
            result = cal.get(Calendar.YEAR);
        }
        return result;
    }
 
 public String financialYear(String str){
     int month=getMonthFromDate(str);
     int year=getYearFromDate(str);
     int curYear=year;
     int fy=0;
     int p=0;
     String finYear="";
     if(month<=3){
        fy=year-1; 
        p=1;
     }else if(month>3){
        fy=year+1;
        p=2;
     }
     if(p==1){
        finYear=fy+"-"+curYear; 
     }else if(p==2){
         finYear=curYear+"-"+fy; 
     }
     
     return finYear;
 }
 public String nextFinancialYear(String str){
  String currentFnyear=financialYear(str);
  String kk[]=currentFnyear.split("-");
  String jj=kk[0];
  String gg=kk[1];
  String nextFnYear=gg+"-"+((Integer.parseInt(gg)+1));
  return nextFnYear;
 }
 public String nexFinancialYearOnlyValue(String str){
  String kk[]=str.split("-");
  String jj=kk[0];
  String gg=kk[1];
  String nextFnYear=gg+"-"+((Integer.parseInt(gg)+1));
  return nextFnYear;
 }
 
 public int countFinancialYear(String str,String st1){
  int i=0;
  
  while(true){
  String lastPaymentFnYear=nextFinancialYear(str);
  String transferDateFnYear=financialYear(str);
      if(lastPaymentFnYear.equals(transferDateFnYear)){
      i++;
       break;
    }else{
        i++; 
    }
  }
  return i;
 }
 public String nextFinancialYear_firstDate(String str){
   String nextFnYear=nextFinancialYear(str);
   return "01-04-"+nextFnYear.substring(0,4);
 }
 public String nextFinancialYear_lastDate(String str){
   String nextFnYear=nextFinancialYear(str);
   return "31-03-"+nextFnYear.substring(5,nextFnYear.length());
 }
 
 public String getFirstFinancialYear(String str){
     String fn=financialYear(str);
     return "01-04-"+fn.substring(0,4);
     
 }
 public String getLastFinancialYear(String str){
     String fn=financialYear(str);
     return "31-03-"+fn.substring(5,fn.length());
     
 } 
 public double calculateOldTaxPerMonth(int pvalue,double prate)  {
   
      double calculateVal=((pvalue*prate)/1200);
      return calculateVal;
 }
 public double calculateNewTaxPerMonth(int carpetArea,int presumeRent,double prate)  {
   
      double calculateVal=((carpetArea*presumeRent*.90));
      calculateVal=(calculateVal*prate)/100;
      calculateVal=(calculateVal/12);
      return calculateVal;
 }
 
 public double calculateInterest(int taxValue,double rateofinterest)  {
   
      double calculateVal=((taxValue*rateofinterest)/100);
      return calculateVal;
      
 }
 public String calculateAge(String endDate,String startDate)
   {
      int years = 0;
      int months = 0;
      int days = 0;
      Date dateEnd=convertStringToDate(endDate);
      Date dateStart=convertStringToDate(startDate);
 
      //create calendar object for birth day
      Calendar endDate_f = Calendar.getInstance();
      endDate_f.setTimeInMillis(dateEnd.getTime());
 
      //create calendar object for current day
      long currentTime = System.currentTimeMillis();
      Calendar startDate_f = Calendar.getInstance();
      
      Calendar now = Calendar.getInstance();
      startDate_f.setTimeInMillis(dateStart.getTime());
 
      //Get difference between years
      years = startDate_f.get(Calendar.YEAR) - endDate_f.get(Calendar.YEAR);
      int currMonth = startDate_f.get(Calendar.MONTH) + 1;
      int birthMonth = endDate_f.get(Calendar.MONTH) + 1;
 
      //Get difference between months
      months = currMonth - birthMonth;
 
      //if month difference is in negative then reduce years by one
      //and calculate the number of months.
      if (months < 0)
      {
         years--;
         months = 12 - birthMonth + currMonth;
         if (startDate_f.get(Calendar.DATE) < endDate_f.get(Calendar.DATE))
            months--;
      } else if (months == 0 && startDate_f.get(Calendar.DATE) < endDate_f.get(Calendar.DATE))
      {
         years--;
         months = 11;
      }
 
      //Calculate the days
      if (startDate_f.get(Calendar.DATE) > endDate_f.get(Calendar.DATE))
         days = startDate_f.get(Calendar.DATE) - endDate_f.get(Calendar.DATE);
      else if (startDate_f.get(Calendar.DATE) < endDate_f.get(Calendar.DATE))
      {
         int today = startDate_f.get(Calendar.DAY_OF_MONTH);
         startDate_f.add(Calendar.MONTH, -1);
         days = startDate_f.getActualMaximum(Calendar.DAY_OF_MONTH) - endDate_f.get(Calendar.DAY_OF_MONTH) + today;
      }
      else
      {
         days = 0;
         if (months == 12)
         {
            years++;
            months = 0;
         }
      }
      //Create new Age object
      String gg="Years "+years+" Months "+months+" Days"+days;
      return gg;
   }
 
 public  String getDateDifferenceInDDMMYYYY(String from,String to) {
        
        Date fromDate_1=convertStringToDate(from);
        Date toDate_1=convertStringToDate(to);
        Calendar fromDate=Calendar.getInstance();
        Calendar toDate=Calendar.getInstance();
        fromDate.setTime(fromDate_1);
        toDate.setTime(toDate_1);
        int increment = 0;
        int year,month,day;
        System.out.println(fromDate.getActualMaximum(Calendar.DAY_OF_MONTH));
        if (fromDate.get(Calendar.DAY_OF_MONTH) > toDate.get(Calendar.DAY_OF_MONTH)) {
            increment =fromDate.getActualMaximum(Calendar.DAY_OF_MONTH);
        }
         //System.out.println("increment"+increment);
// DAY CALCULATION
        if (increment != 0) {
            day = (toDate.get(Calendar.DAY_OF_MONTH) + increment) - fromDate.get(Calendar.DAY_OF_MONTH);
            increment = 1;
        } else {
            day = toDate.get(Calendar.DAY_OF_MONTH) - fromDate.get(Calendar.DAY_OF_MONTH);
        }

// MONTH CALCULATION
        if ((fromDate.get(Calendar.MONTH) + increment) > toDate.get(Calendar.MONTH)) {
            month = (toDate.get(Calendar.MONTH) + 12) - (fromDate.get(Calendar.MONTH) + increment);
            increment = 1;
        } else {
            month = (toDate.get(Calendar.MONTH)) - (fromDate.get(Calendar.MONTH) + increment);
            increment = 0;
        }

// YEAR CALCULATION
        year = toDate.get(Calendar.YEAR) - (fromDate.get(Calendar.YEAR) + increment);
        String yrrr=year+":"+month+":"+day;
        //return   year+"\tYears\t\t"+month+"\tMonths\t\t"+day+"\tDays";
        return yrrr;
    }
 public int getMonthBetweenTwoDates(String stt1,String str2){
  String yymndd= getDateDifferenceInDDMMYYYY(stt1,str2);
  int totalMonth=0;
  String yymndd_br[]=yymndd.split(":");
  int yr=Integer.parseInt(yymndd_br[0]);
  int mn=Integer.parseInt(yymndd_br[1]);
  int day=Integer.parseInt(yymndd_br[2]);
  if(day<=15){
     mn=mn; 
  }else {
     mn=mn+1; 
  }
  if(yr>0){
   totalMonth=yr*12+mn;   
  }else{
    totalMonth=mn;  
  }
 return totalMonth;
 }
 
 
 /*
  * Start of the project
  */
 public void calculatePropertyTransferTaxes(){
  String transferDate="10-04-2018" ;            
  Date lastPaymentDate=convertStringToDate("31-03-2016");          // 1st param
  String taxDateFrom="01-04-2019";
  String axDateTo="30-09-2019";
  Date newTaxDateStartFrom=convertStringToDate("01-10-2019");         
  String newTaxDateEndTo="31-03-2020";
  String oldinterestDateStartFrom="01-05-2018";
  String oldInterestDateEndTo="31-03-2019";
  int costofpropropertyBeforeTransfer=1179000;
  int costofpropertyafterTransfer=2250000;
  double oldrate=.2;
  double rateofinterest=.6;
  double totalTax=0.0;
  Date nextDateOfPayment=convertStringToDate("01-10-2019");          // 2nd param
  
    List<String> assessmentYears = fetchFinancialYears(lastPaymentDate, nextDateOfPayment);
    System.out.println("list "+assessmentYears.size());
    for(int i=0; i<assessmentYears.size(); i++){
        System.out.println("print-"+assessmentYears.get(i));
        // compare assessmentYears with newTaxDateStartFrom, return 1=old, 2=new, 3=both
        //int oldNewTaxType = compareYearWithNewTaxDate(assessmentYears[i],newTaxDateStartFrom);
        
        //Map result = calculateTaxForYear(oldNewTaxType, );        
        
    }      
 }
 
    public static void main(String args[]) {


        TaxCalculate cal = new TaxCalculate();
// start actual code
        String dateString1 = "31-03-2019"; // last pymt date
        String dateString2 = "30-09-2019"; // tax change date
        String dateString3 = "31-03-2020"; // next pymt date
        String dateString4 = "01-05-2019"; //transfer date
        int propertyValueBeforeTransfer=2730000;
        int propertyValueAfterTransfer=3025000;
        Date date1 = cal.convertStringToDate(dateString1);
        Date date2 = cal.convertStringToDate(dateString2);
        Date date3 = cal.convertStringToDate(dateString3);
        Date date4 = cal.convertStringToDate(dateString4);
        int monthForOldTax = 0;
        int monthForOldTaxBeforeTrnasfer = 0;
        int monthForOldTaxAfterTransfer = 0;
        int monthForNewTax = 0;
        int intrestMonthCount = 0;
        int intrestMonthBeforeTransfer = 0;
        int intrestMonthAfterTransfer = 0;
        if (date1.before(date2)) {
// pay old tax from date1 to date2
            monthForOldTax = cal.getMonthBetweenTwoDates(dateString1, dateString2);
            if (date1.before(date4)) {
// use prop_val1
                monthForOldTaxBeforeTrnasfer =
                        cal.getMonthBetweenTwoDates(dateString1, dateString4);
                monthForOldTaxAfterTransfer = cal.getMonthBetweenTwoDates(dateString4,
                        dateString2);
            } else {
// use prop_val2
                monthForOldTaxAfterTransfer = cal.getMonthBetweenTwoDates(dateString1,
                        dateString2);
            }
//start changes for Interest
            String intrestStartDateString = "01-05-2018";
            String intrestEndDateString = "31-03-2019";
            Date intrestStartDate = cal.convertStringToDate(intrestStartDateString);
            Date intrestEndDate = cal.convertStringToDate(intrestEndDateString);
            if (date1.before(intrestStartDate)) {
                intrestMonthCount = cal.getMonthBetweenTwoDates(intrestStartDateString,
                        intrestEndDateString);
                if (intrestStartDate.before(date4) && date4.before(intrestEndDate)) {
                    intrestMonthBeforeTransfer = cal.getMonthBetweenTwoDates(intrestStartDateString,
                            dateString4);
                    intrestMonthAfterTransfer = cal.getMonthBetweenTwoDates(dateString4,
                            intrestEndDateString);
                }
            } else if (date1.before(intrestEndDate)) {
                intrestMonthCount = cal.getMonthBetweenTwoDates(dateString1,
                        intrestEndDateString);
                if (date1.before(date4) && date4.before(intrestEndDate)) {
                    intrestMonthBeforeTransfer = cal.getMonthBetweenTwoDates(dateString1,
                            dateString4);
                    intrestMonthAfterTransfer = cal.getMonthBetweenTwoDates(dateString4,
                            intrestEndDateString);
                }
            }
        }
        if (date2.before(date3)) {
// pay new tax from date2 to date3
            monthForNewTax = cal.getMonthBetweenTwoDates(dateString2, dateString3);
        }

        System.out.println("noOfMonthForOldTax= " + monthForOldTax);
        System.out.println("noOfMonthForNewTax= " + monthForNewTax);
        System.out.println("intrestMonthCount= " + intrestMonthCount);

        System.out.println("monthForOldTaxBeforeTrnasfer=" + monthForOldTaxBeforeTrnasfer);
        System.out.println("monthForOldTaxAfterTransfer= " + monthForOldTaxAfterTransfer);
        System.out.println("intrestMonthBeforeTransfer= " + intrestMonthBeforeTransfer);
        System.out.println("intrestMonthAfterTransfer= " + intrestMonthAfterTransfer);
         
        Double totalSum=0D;
        totalSum= totalSum + monthForOldTaxBeforeTrnasfer * cal.calculateOldTaxPerMonth(propertyValueBeforeTransfer, .2);
        System.out.println("monthForOldTaxBeforeTrnasfer totalSum= " + totalSum);
        totalSum= totalSum + monthForOldTaxAfterTransfer * cal.calculateOldTaxPerMonth(propertyValueAfterTransfer, .2);
        System.out.println("monthForOldTaxAfterTransfer totalSum= " + totalSum);
        
        int amountForIntrest=0; // hardcoded value 
        double principleAmountForIntrestD=0.0;
        double principleAmountForIntrestD1=0.0;
        double principleAmountForIntrestD2=0.0;
        double principleAmountForIntrestD3=0.0;
        if(intrestMonthBeforeTransfer!=0){
             amountForIntrest=propertyValueBeforeTransfer;
             principleAmountForIntrestD1=intrestMonthBeforeTransfer* cal.calculateOldTaxPerMonth(amountForIntrest, .2);
             System.out.println("principleAmountForIntrestD1 "+principleAmountForIntrestD1);
        }
        if(intrestMonthAfterTransfer!=0){
            amountForIntrest=propertyValueAfterTransfer;
            principleAmountForIntrestD2=intrestMonthAfterTransfer* cal.calculateOldTaxPerMonth(amountForIntrest, .2);
            System.out.println("principleAmountForIntrestD2 "+principleAmountForIntrestD2);
        }
        if(intrestMonthBeforeTransfer==0 && intrestMonthAfterTransfer==0){
           amountForIntrest=propertyValueAfterTransfer; 
           principleAmountForIntrestD3=12* cal.calculateOldTaxPerMonth(amountForIntrest, .2);
           System.out.println("principleAmountForIntrestD3 "+principleAmountForIntrestD3);
        }
        
        //double principleAmountForIntrestD=12* cal.calculateOldTaxPerMonth(amountForIntrest, .2);
        System.out.println("principleAmountForIntrestD = " + principleAmountForIntrestD);
        //int principleAmountForIntrest=(int)principleAmountForIntrestD;
        //System.out.println("principleAmountForIntrest = " + principleAmountForIntrest);
        if(intrestMonthBeforeTransfer != 0){
            int principleAmountForIntrest=(int)principleAmountForIntrestD1;
            totalSum= totalSum + intrestMonthBeforeTransfer * cal.calculateInterest(principleAmountForIntrest,.6);
            System.out.println("intrestMonthBeforeTransfer totalSum= " + totalSum);
        }
        if(intrestMonthAfterTransfer != 0){
             int principleAmountForIntrest=(int)principleAmountForIntrestD2;
            totalSum= totalSum + intrestMonthAfterTransfer * cal.calculateInterest(principleAmountForIntrest, .6);
            System.out.println("intrestMonthAfterTransfer totalSum= " + totalSum);
        }
        if(intrestMonthBeforeTransfer==0 && intrestMonthAfterTransfer==0 ){
             int principleAmountForIntrest=(int)principleAmountForIntrestD3;
             totalSum= totalSum + intrestMonthCount * cal.calculateInterest(principleAmountForIntrest, .6);
             System.out.println("intrestTotalMonth totalSum= " + totalSum);
        }
        
        totalSum = totalSum + monthForNewTax * cal.calculateNewTaxPerMonth(400, 100, 3.5);
        System.out.println("Final totalSum= " + totalSum);



    }
 
 //List<String> assessmentYears = fetchFinancialYears(lastPaymentDate, lastDateOfPayment);
 public List<String> fetchFinancialYears(Date lastPaymentDate, Date nextDateOfPayment){
     List<String> assessmentYears = null;
        Calendar calendar1 = new GregorianCalendar();
        calendar1.setTime(lastPaymentDate  );
        
        Calendar calendar2 = new GregorianCalendar();
        calendar2.setTime(nextDateOfPayment);
        
      int year = calendar2.get(Calendar.YEAR)-calendar1.get(Calendar.YEAR);
     for(int i=0; i<year; i++){
         int from = calendar1.get(Calendar.YEAR);//lastPaymentDate.getYear()+1900;
         Date fromDate =null;
         if(i==0){
            fromDate=lastPaymentDate;
         } else {
             fromDate=convertStringToDate("01-04-"+from); 
         }
         System.out.println("to ash "+lastPaymentDate.getYear()+" "+lastPaymentDate);
         int to = calendar1.get(Calendar.YEAR);//lastPaymentDate.getYear()+1+1900;
         System.out.println("to ashk "+to);
         Date toDate=convertStringToDate("31-03-"+to);
         System.out.println("to ashkkk "+fromDate);
         System.out.println("to ashkll "+toDate);
         
         
         System.out.println("to ashkkk "+fromDate);
         System.out.println("to ashkll "+toDate);
         
         assessmentYears.add(convertDateToString(fromDate)+"-"+convertDateToString(toDate));
     }
     return assessmentYears;
 }
 
 //int oldNewTaxType = compareYearWithNewTaxDate(assessmentYears[i],newTaxDateStartFrom);
 public int compareYearWithNewTaxDate(String assessmentYear, Date newTaxDateStartFrom){
    int oldNewTaxType=0;
    String years[] = assessmentYear.split("-");
    
    
    return oldNewTaxType;
}
 
 public String convertDateToString(Date date){     
     DateFormat dateFormat = new SimpleDateFormat("dd-MM-yyyy");  
     String strDate = dateFormat.format(date);  
     return strDate;
 }
 
 
 
 public double calculatePropertyTransferTax(){
  String transferDate="10-04-2018" ;            
  String lastPaymentDate="31-03-2016";          // 1st param
  String taxDateFrom="01-04-2019";
  String axDateTo="30-09-2019";
  String newTaxDateStartFrom="01-10-2019";
  String newTaxDateEndTo="31-03-2020";          // 2nd param
  String oldinterestDateStartFrom="01-05-2018";
  String oldInterestDateEndTo="31-03-2019";
  int costofpropropertyBeforeTransfer=1179000;
  int costofpropertyafterTransfer=2250000;
  double oldrate=.2;
  double rateofinterest=.6;
  double totalTax=0.0;
  
  
  if(convertStringToDate(lastPaymentDate).compareTo(convertStringToDate(transferDate))<0) {
      if(convertStringToDate(transferDate).compareTo(convertStringToDate(oldinterestDateStartFrom))<0) {
          if(convertStringToDate(oldInterestDateEndTo).compareTo(convertStringToDate(taxDateFrom))<0) {
           String lastPaymentDateFnYear= financialYear(lastPaymentDate);
           String transferDateFnYear= financialYear(transferDate);
           String getFirstFinancialYearDate_lastPayment= getFirstFinancialYear(lastPaymentDate);
           String getLastFinancialYearDate_lastPayment= getLastFinancialYear(lastPaymentDate);
           String getFirstFinancialYearDate_transferDate= getFirstFinancialYear(transferDate);
           String getLastFinancialYearDate_transferDate= getLastFinancialYear(transferDate);
           if(lastPaymentDateFnYear.equals(transferDateFnYear)){
               
           }else{
               if(lastPaymentDate.equals(getFirstFinancialYearDate_lastPayment)){
                 System.out.println("calculate tax full assessment year");
                 totalTax=totalTax+calculateOldTaxPerMonth(costofpropropertyBeforeTransfer,.2);
                 totalTax=totalTax*12;
               } else if(lastPaymentDate.equals(getLastFinancialYearDate_lastPayment)){
                 System.out.println("not calculate tax for this assessment year. calculate tax for next assessment yr");
                 totalTax=0;
                 
                 
                 
               } else if(convertStringToDate(lastPaymentDate).compareTo(convertStringToDate(getFirstFinancialYearDate_lastPayment))>0
                       && convertStringToDate(lastPaymentDate).compareTo(convertStringToDate(getLastFinancialYearDate_lastPayment))<0) {
                 int totalMonth=getMonthBetweenTwoDates(lastPaymentDate,getLastFinancialYearDate_lastPayment);
                 double dd=calculateOldTaxPerMonth(costofpropropertyBeforeTransfer,.2);
                 dd=dd*totalMonth;
                 totalTax=dd;
                 int countFnYear=countFinancialYear(lastPaymentDate,transferDate);
                   String nextFnyear_start=  nextFinancialYear_firstDate(lastPaymentDate);
                   String nextFnyear_end=    nextFinancialYear_lastDate(lastPaymentDate);
                   String fnYear=nextFinancialYear(lastPaymentDate);
                   String fnYearVal=fnYear;
                   String fnYearTranseerDate=financialYear(transferDate);
                 for(int i=0;i<countFnYear;i++){
                    
                   if(fnYear.equals(fnYearTranseerDate)){
                       int totalMonth_cal=getMonthBetweenTwoDates(getFirstFinancialYearDate_transferDate,transferDate);
                       int totalMonth_cal_next=getMonthBetweenTwoDates(getLastFinancialYearDate_transferDate,transferDate);
                       if(totalMonth_cal==0){
                           totalTax=totalTax+ 12*calculateOldTaxPerMonth(costofpropertyafterTransfer,.2);  
                       }else{
                          totalTax=totalTax+ totalMonth_cal*calculateOldTaxPerMonth(costofpropropertyBeforeTransfer,.2); 
                       }
                       if(totalMonth_cal_next>0 && totalMonth_cal>0){
                         totalTax=totalTax+ totalMonth_cal_next*calculateOldTaxPerMonth(costofpropertyafterTransfer,.2);    
                       }
                       
                   }else{
                     totalTax=totalTax+ 12*calculateOldTaxPerMonth(costofpropropertyBeforeTransfer,.2);  
                   }
                  fnYear=nexFinancialYearOnlyValue(fnYearVal); 
                  fnYearVal=fnYear;
                 }
                 
                 
                 
                 
               }
           }
           
        }
          
      }
  
  return totalTax;
  }
  
  
     
     
     
     
     
  return totalTax;   
 }
     

// public static void main(String args[]) {
//   TaxCalculate t1=new TaxCalculate();
//   Date t3= t1.convertStringToDate("31-05-2018");
//   System.out.println("ff date"+t3);
//  int  yr= t1.getYear("31-05-2018");
//  int month= t1.getMonth("31-05-2018");
//  int day=t1.getDay("31-05-2018");
//  System.out.println("yr "+yr);
//  System.out.println("month "+month);
//  System.out.println("day "+day);
//  
//  String kk=t1.dateDiff("10-04-2018","31-03-2016");
//  System.out.println("date diff "+kk);
//  
//  int year = t1.getYearFromDate("31-03-2018");
//  System.out.println("Financial Year : " + year + "-" + (year+1));
//  System.out.println("Financial month : " + t1.getMonthFromDate("31-03-2018"));
//  
// double hh= t1.calculateOldTaxPerMonth(1179000,.2);
// System.out.println("tax "+(hh*24));
// 
// double vv=t1.calculateNewTaxPerMonth(400,100,3.5);
// System.out.println("new Tax "+(vv*12)); 
// double bb=t1.calculateInterest(4500,.6);
// System.out.println("interest "+(bb*11)); 
//  
//  String mm=t1.calculateAge("21-01-1972","13-09-2019");
//  String sd=t1.getDateDifferenceInDDMMYYYY("21-01-1972","13-09-2019");
//  System.out.println("ashok "+mm);
//  System.out.println("sd "+sd);
//  
// String rr= t1.financialYear("31-03-2016");
//  System.out.println("rr "+rr);
//  System.out.println("getFirstFinancialYear"+t1.getFirstFinancialYear("31-03-2016"));
//  System.out.println("getLastFinancialYear"+t1.getLastFinancialYear("31-03-2016"));
// int tmonth= t1.getMonthBetweenTwoDates("01-01-2016","31-03-2016");
//  System.out.println("total month "+tmonth);
//  
//  double tax=t1.calculatePropertyTransferTax();
// } 


 
 
}
