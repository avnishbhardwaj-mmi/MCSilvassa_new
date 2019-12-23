package com.silvassa.util;;

import java.util.Random;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.springframework.stereotype.Component;

@Component
public class MMIOTPUtil {
	
	private Random random = new Random();
	public static final Pattern VALID_EMAIL_ADDRESS_REGEX = Pattern.compile("^[A-Z0-9._%+-]+@[A-Z0-9.-]+\\.[A-Z]{2,6}$", Pattern.CASE_INSENSITIVE);
	
	public Integer getRandomOTP(){
		return 100000 + random.nextInt(900000);
	}
	
	public boolean validateEmail(String emailStr) {
		
			//if(emailStr != null && !emailStr.toLowerCase().contains("@mapmyindia.com"))
                            //{return false;}
            Matcher matcher=null;
            boolean p1=false;
            boolean p2=false;
            boolean p3=false;
            System.out.println("emailStr "+emailStr);
		
	        if(emailStr != null && emailStr.contains(",")){
                    String strEmail[]=emailStr.split(",");
                    if(strEmail[0] != null && !strEmail[0].toLowerCase().contains("@mapmyindia.com"))
                            {p1 =  false;}
                        matcher = VALID_EMAIL_ADDRESS_REGEX .matcher(strEmail[0]);
                         p1= matcher.find();
                        
                        if(strEmail[1] != null && !strEmail[1].toLowerCase().contains("@mapmyindia.com"))
                            {p2= false;}
                        matcher = VALID_EMAIL_ADDRESS_REGEX .matcher(strEmail[1]);
                         p2= matcher.find();
                    
                    
                    
                        
                    
                }else{
                    if(emailStr != null && !emailStr.toLowerCase().contains("@mapmyindia.com"))
                            {return false;}
                     matcher = VALID_EMAIL_ADDRESS_REGEX .matcher(emailStr);
	            p3= matcher.find();
                    //return matcher.find();
                }
                
                if(p1 && p2){
                    return true;
                }else if(p3){
                    return true; 
                }else{
                    return false;
                }
                    
                    
                    
                    
                 //Matcher matcher = VALID_EMAIL_ADDRESS_REGEX .matcher(emailStr);
	        //return matcher.find();
                //return true;
              
	}
	
	public String commaSeparatedStringToQueryInClaues(String inputStr){
		
		StringBuilder queryInClause = new StringBuilder();
		
		String[] areaCodeArr = inputStr.split(",");
		
		 boolean flag = false;
		 for(String str : areaCodeArr){
			 if(flag) 
				 queryInClause.append(", '");
			  else
				  queryInClause.append("'");
			 
			 queryInClause.append(str);
			 queryInClause.append("'");
			 flag = true;
		 }
		
		return queryInClause.toString();
	}
	
}
