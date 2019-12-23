package  com.silvassa.dao;

import java.util.List;
import java.util.Map;
import com.silvassa.model.Response;

public interface OTPDao {
	public void saveOTP(String email, Integer otp,String mobileNo);
	public void deleteExpiredOTP();
	public void deleteValidatedOTP(String email,String mobileNo);
	public boolean validateOTP(String email, Integer otp);
	public boolean registerMail(String toEmails, String ccEmails, String subject, String body);

        
    public List<String> getGmailById(String proId);
    public Response verifyOTP(String mailId,int otp,String mobileNo);
}
