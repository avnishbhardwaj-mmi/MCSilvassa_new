package com.silvassa.service;;

import java.util.Map;
import com.silvassa.model.Response;

public interface OTPService {
	public Response generateOTP(String email,String mobileNo);
	public Response validateOTP(String email, Integer otp);

        
    public Map<String,Object> getGmailById(String proId,String mobile,String email);
    public Response verifyOTP(String mailId,int otp,String mobileNo);
    public Boolean sendOTPtoMobile(String mobileNo,String message);
    public Response generateOTPCorrectionUpdate(String pid,String applicationNo,String email,String mobileNo,String msg);
    public Response generateOTPCorrectionUpdate(String pid,String applicationNo,String email,String mobileNo,String msg,String msgmail);
}
