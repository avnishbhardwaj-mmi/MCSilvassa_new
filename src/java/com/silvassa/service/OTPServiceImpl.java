package com.silvassa.service;;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLEncoder;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.silvassa.model.Response;
import com.silvassa.dao.OTPDao;
import com.silvassa.util.MMIOTPUtil;

@Component("OTPServiceImpl")
public class OTPServiceImpl implements OTPService {
	
	@Autowired
	MMIOTPUtil mMIUtil;
	
	@Autowired
	OTPDao oTPDao;

	@Override
	public Response generateOTP(String email,String mobileNo) {
		Response response = new Response();
		
		//System.out.println("check email" +mMIUtil.validateEmail(email));
		if(email != null) {
			// || email.equalsIgnoreCase("ashok@mapmyindia.com")//(mMIUtil.validateEmail(email))
		} else {
                        //System.out.println("ddd else part "+email+" "+mobileNo);
                        if(!mobileNo.equals("")){
                            //System.out.println("ddllllll");
                            oTPDao.deleteExpiredOTP();
                            oTPDao.deleteValidatedOTP(email,mobileNo);
                            int otp = mMIUtil.getRandomOTP();
                            oTPDao.saveOTP("",otp,mobileNo);
                            String msg = otp+" is OTP for Silvassa Municipal Council Property Tax Portal. Code will expire in 30 minutes.";
                            if(sendOTPtoMobile(mobileNo,msg)){
                                response.setStatus(200);
                                response.setMsg("Verification code sent to registered mobile No.");
                                response.setData(true);
                                return response;
                              }else{
                                 response.setStatus(422);
                                 response.setMsg("Not found!");
                                 response.setData(true);
                                 return response;
                            }
                          }else{
                               response.setStatus(422);
                               response.setMsg("Not found!");
                               response.setData(false);
                               return response;
                           }
			
                       
		}
		//System.out.println("else after end "+email+" "+mobileNo);
		oTPDao.deleteExpiredOTP();
		oTPDao.deleteValidatedOTP(email,"");
		int otp = mMIUtil.getRandomOTP();
		oTPDao.saveOTP(email,otp,mobileNo);
		//System.out.println("save otp  "+email+" "+mobileNo);
		//Send Email
		StringBuilder emailBody = new StringBuilder();
		emailBody.append("Dear ");
		emailBody.append("applicant");
		emailBody.append(", <br><br>");
		emailBody.append(otp);
		emailBody.append(" is OTP for Silvassa Municipal Council Property Tax Portal. Code will expire in 30 minutes.");
		emailBody.append("<br><br>");
		emailBody.append("-------------------------------------------------------------------------------------<br>");
		emailBody.append(" This is system generated mail. Please do not reply.<br>");
		emailBody.append("-------------------------------------------------------------------------------------<br>");
                
                String msg = otp+" is OTP for Silvassa Municipal Council Property Tax Portal. Code will expire in 30 minutes.";
                //System.out.println("email check for registered"+email);
                boolean s0 = oTPDao.registerMail(email, null, "Verification code for SMC portal", emailBody.toString());
                //System.out.println("email boolean"+s0);
                boolean s1 = sendOTPtoMobile(mobileNo,msg);
		if(s0 && s1) {
			response.setStatus(200);
			response.setMsg("Verification code sent to registered email id and mobile no.");
			response.setData(true);
		}else if(s0){
                        response.setStatus(200);
			response.setMsg("Verification code sent to registered email id");
			response.setData(true);
                }else if(s1){
                        response.setStatus(200);
			response.setMsg("Verification code sent to registered mobile no.");
			response.setData(true);
                }else{
                        response.setStatus(400);
			response.setMsg("Not Found!");
			response.setData(true);
                }
		
		return response;
	}
        
        
        @Override
	public Response generateOTPCorrectionUpdate(String pid,String applicationNo,String email,String mobileNo,String msgformobile,String msgforemaail) {
		Response response = new Response();
		//System.out.println("email "+email);
		//System.out.println("check email" +mMIUtil.validateEmail(email));
		if(email != null) {
			// || email.equalsIgnoreCase("ashok@mapmyindia.com")//(mMIUtil.validateEmail(email))
		} else {
                        //System.out.println("ddd else part "+email+" "+mobileNo);
                        if(!mobileNo.equals("")){
                            //System.out.println("ddllllll");
                            //oTPDao.deleteExpiredOTP();
                            //oTPDao.deleteValidatedOTP(email,mobileNo);
                            //int otp = mMIUtil.getRandomOTP();
                           // oTPDao.saveOTP("",otp,mobileNo);
                            //String msg="Your request for property id: "+pid+ "and application no: "+applicationNo+ "has been processed. Thanks";
                            //String msg = otp+" is verification code for login in application. Code will expire in 30 minutes.";
                            if(sendOTPtoMobile(mobileNo,msgformobile)){
                                response.setStatus(200);
                                response.setMsg("Verification code sent to registered mobile number.");
                                response.setData(true);
                                return response;
                              }else{
                                 response.setStatus(422);
                                 response.setMsg("Not found!");
                                 response.setData(true);
                                 return response;
                            }
                          }else{
                               response.setStatus(422);
                               response.setMsg("Not found!");
                               response.setData(false);
                               return response;
                           }
			
                       
		}
		//System.out.println("else after end "+email+" "+mobileNo);
		//oTPDao.deleteExpiredOTP();
		//oTPDao.deleteValidatedOTP(email,"");
		//int otp = mMIUtil.getRandomOTP();
		//oTPDao.saveOTP(email,otp,mobileNo);
		//System.out.println("save otp  "+email+" "+mobileNo);
		//Send Email
		StringBuilder emailBody = new StringBuilder();
		emailBody.append("Dear ");
		emailBody.append("applicant");
		emailBody.append(", <br><br>");
		//emailBody.append(otp);
                emailBody.append(msgforemaail);
		//emailBody.append(" Your request for property id: "+pid+ "and application no: "+applicationNo+ "has been processed. Thanks");
		emailBody.append("<br><br>");
		emailBody.append("-------------------------------------------------------------------------------------<br>");
		emailBody.append(" This is system generated mail. Please do not reply.<br>");
		emailBody.append("-------------------------------------------------------------------------------------<br>");
                
                //String msg="Your request for property id: "+pid+ "and application no: "+applicationNo+ "has been processed. Thanks";
                //String msg = otp+" is verification code for login in application. Code will expire in 30 minutes.";
                //System.out.println("email check for registered"+email);
                boolean s0 = oTPDao.registerMail(email, null, "Request processed", emailBody.toString());
                //System.out.println("email boolean"+s0);
                boolean s1 = sendOTPtoMobile(mobileNo,msgformobile);
                //System.out.println(" update email boolean "+s0+" "+s1);
		if(s0 && s1) {
			response.setStatus(200);
			response.setMsg("Verification code sent to registered email id and mobile number");
			response.setData(true);
		}else if(s0){
                        response.setStatus(200);
			response.setMsg("Verification code sent to registered email id");
			response.setData(true);
                }else if(s1){
                        response.setStatus(200);
			response.setMsg("Verification code sent to registered mobile number");
			response.setData(true);
                }else{
                        response.setStatus(400);
			response.setMsg("Not found!");
			response.setData(true);
                }
		
		return response;
	}

	@Override
	public Response validateOTP(String email, Integer otp) {
		Response response = new Response();
		response.setStatus(-1);
		response.setMsg("Invalid verification code");
		response.setData(false);
		
		if(oTPDao.validateOTP(email, otp)) {
			//oTPDao.deleteValidatedOTP(email);
			response.setStatus(200);
			response.setMsg("Valid verification code");
			response.setData(true);
		}
		
		return response;
	}
    
    @Override
    public Boolean sendOTPtoMobile(String mobileNo,String message) {
       Boolean status = false;
       BufferedReader br = null;
       StringBuffer response = null;
       HttpURLConnection con = null;
       try {
        if (mobileNo.length() < 10) {
               return status;
        } else if (mobileNo.length() == 10) {
              mobileNo = "91" + mobileNo;
        }
//        http://sms4power.com/api/swsend.asp?username=t1SMCPTX&password=101108338&sender=NEWREG&sendto=919953555577,919818025955&message=hello
        String url = "http://sms4power.com/api/swsend.asp?username=t1SMCPTX&password=101108338&sender=NEWREG"
                        + "&sendto=" + URLEncoder.encode(mobileNo, "UTF8")
                        + "&message=" + URLEncoder.encode(message, "UTF8");
           System.out.println("SMSService : url : " + url);
//        String url = "http://luna.a2wi.co.in:7501/failsafe/HttpLink?aid=512762&pin=map321"
//                        + "&mnumber=" + URLEncoder.encode(mobileNo, "UTF8")
//                        + "&message=" + URLEncoder.encode(message, "UTF8") + "";
                 
            URL obj = new URL(url);
            con = (HttpURLConnection) obj.openConnection();
            con.setRequestMethod("GET");
            con.setRequestProperty("Content-Type", "application/x-www-form-urlencoded");
            int responseCode = con.getResponseCode();
            //System.out.println("\n [SMSService.sendSms] Sending 'POST' request to URL : " + url);
            //System.out.println("[SMSService.sendSms] Response Code : " + responseCode);
             if (responseCode == 200) {
                br = new BufferedReader(new InputStreamReader(con.getInputStream()));
                String inputLine;
                response = new StringBuffer();
                while ((inputLine = br.readLine()) != null) {
                    response.append(inputLine);
                }
                status = true;
               // System.out.println("[SMSService.sendSms] response.toString() : " + response.toString());
            } else {
                br = new BufferedReader(new InputStreamReader(con.getErrorStream()));
                String inputLine;
                response = new StringBuffer();
                while ((inputLine = br.readLine()) != null) {
                    response.append(inputLine);
                }
                response = new StringBuffer();
                 status = false;
            }
            System.out.println("SMSService : " + response.toString());
            
       } catch (Exception e) {
             e.printStackTrace();
        }finally {
            try {
                if (br != null) {
                    br.close();
                }
            } catch (IOException ex) {
                System.out.println("## Exception occured in SMSService.searchSingleGroundLinkVehiclesDetail=> : " + ex.getMessage());
            }
        }
      return status; 
    }
	    
    @Override
    public Map<String,Object> getGmailById(String proId,String mobile,String email) {
             Map<String,Object> map = new HashMap<String,Object>();
        try {
        List<String> list = oTPDao.getGmailById(proId);
       
        String deamil=email;//(String)list.get(0);
        String dmobile=mobile;//(String)list.get(1);
        System.out.println("::list.size() :"+list.size()); 
        if(list.size()>0){
            String str1 = (String)list.get(0);
            String str2 = (String)list.get(1);
           if(!str1.equals("")){
               if(!str1.equalsIgnoreCase(deamil)){
                   deamil = deamil+","+(String)list.get(0);
               }    
           }
           if(!str2.equals("")){
                dmobile = dmobile+","+(String)list.get(1);
           }
        }
        //System.out.println("Combo EM : "+deamil+" "+dmobile); 

        Response res = new Response();
        if(list.size()>0){    
            res = generateOTP(deamil,dmobile); 
           // res = generateOTP(list.get(0),list.get(1));
            // map.put("gmailId",list.get(0));
            // map.put("mobileNo",list.get(1));
             map.put("gmailId",deamil);
             map.put("mobileNo",dmobile);
             map.put("res", res);
             //System.out.println(": "+map);
            
        }else{
             res.setStatus(400);
             res.setMsg("Mobile no. and email are not found!");
             map.put("gmailId","");
             map.put("mobileNo","");
             map.put("res", res);
             //System.out.println(": "+map);
            
        }
        
         } catch (Exception e) {
             e.printStackTrace();
        }
       return map;
    }

    @Override
    public Response verifyOTP(String mailId,int otp,String mobileNo) {
           Response res = new Response();    
               res = oTPDao.verifyOTP(mailId,otp,mobileNo);
               if(res.getStatus()==200){
                   oTPDao.deleteValidatedOTP(mailId,mobileNo);
               }
              return res;
    }
    
    
    @Override
	public Response generateOTPCorrectionUpdate(String pid,String applicationNo,String email,String mobileNo,String msgformobile) {
		Response response = new Response();
		
		//System.out.println("check email" +mMIUtil.validateEmail(email));
		if(email != null) {
			// || email.equalsIgnoreCase("ashok@mapmyindia.com")//(mMIUtil.validateEmail(email))
		} else {
                        //System.out.println("ddd else part "+email+" "+mobileNo);
                        if(!mobileNo.equals("")){
                            //System.out.println("ddllllll");
                            //oTPDao.deleteExpiredOTP();
                            //oTPDao.deleteValidatedOTP(email,mobileNo);
                            //int otp = mMIUtil.getRandomOTP();
                           // oTPDao.saveOTP("",otp,mobileNo);
                            //String msg="Your request for property id: "+pid+ "and application no: "+applicationNo+ "has been processed. Thanks";
                            //String msg = otp+" is verification code for login in application. Code will expire in 30 minutes.";
                            if(sendOTPtoMobile(mobileNo,msgformobile)){
                                response.setStatus(200);
                                response.setMsg("Verification code sent to registered mobile number.");
                                response.setData(true);
                                return response;
                              }else{
                                 response.setStatus(422);
                                 response.setMsg("Not found!");
                                 response.setData(true);
                                 return response;
                            }
                          }else{
                               response.setStatus(422);
                               response.setMsg("Not found!");
                               response.setData(false);
                               return response;
                           }
			
                       
		}
		//System.out.println("else after end "+email+" "+mobileNo);
		//oTPDao.deleteExpiredOTP();
		//oTPDao.deleteValidatedOTP(email,"");
		//int otp = mMIUtil.getRandomOTP();
		//oTPDao.saveOTP(email,otp,mobileNo);
		//System.out.println("save otp  "+email+" "+mobileNo);
		//Send Email
		StringBuilder emailBody = new StringBuilder();
		emailBody.append("Dear ");
		emailBody.append(email);
		emailBody.append(", <br><br>");
		//emailBody.append(otp);
                emailBody.append(msgformobile);
		//emailBody.append(" Your request for property id: "+pid+ "and application no: "+applicationNo+ "has been processed. Thanks");
		emailBody.append("<br><br>");
		emailBody.append("-------------------------------------------------------------------------------------<br>");
		emailBody.append(" This is system generated mail. Please do not reply.<br>");
		emailBody.append("-------------------------------------------------------------------------------------<br>");
                
                //String msg="Your request for property id: "+pid+ "and application no: "+applicationNo+ "has been processed. Thanks";
                //String msg = otp+" is verification code for login in application. Code will expire in 30 minutes.";
                //System.out.println("email check for registered"+email);
                boolean s0 = oTPDao.registerMail(email, null, "Request Processed", emailBody.toString());
                //System.out.println("email boolean"+s0);
                boolean s1 = sendOTPtoMobile(mobileNo,msgformobile);
                //System.out.println(" update email boolean "+s0+" "+s1);
		if(s0 && s1) {
			response.setStatus(200);
			response.setMsg("Verification code sent to registered email id and mobile number");
			response.setData(true);
		}else if(s0){
                        response.setStatus(200);
			response.setMsg("Verification code sent to registered email id");
			response.setData(true);
                }else if(s1){
                        response.setStatus(200);
			response.setMsg("Verification code sent to registered mobile number");
			response.setData(true);
                }else{
                        response.setStatus(400);
			response.setMsg("Not found!");
			response.setData(true);
                }
		
		return response;
	}


}
