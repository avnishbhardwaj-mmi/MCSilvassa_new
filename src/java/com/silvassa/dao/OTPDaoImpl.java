package  com.silvassa.dao;

import com.sun.javafx.scene.control.skin.VirtualFlow;
import java.math.BigDecimal;
import java.math.BigInteger;
import java.util.ArrayList;
import java.util.List;
import com.silvassa.model.Response;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.core.env.Environment;
import org.springframework.stereotype.Component;
	
@Component("OTPDaoImpl")
public class OTPDaoImpl implements OTPDao {
	
	@Autowired
        @Qualifier("sessionHB")
	private SessionFactory sessionFactory;
	
	@Autowired
        @Qualifier("sessionHBMail")
	private SessionFactory sessionFactoryMail;
	
	Integer otp_timeout = 1800;
        
//	@Autowired
//	Environment env;

	@Override
	public void saveOTP(String email, Integer otp,String mobileNo) {
		
		Session session = null;
		try {

			session = sessionFactory.openSession();
			Query query = session.createSQLQuery("INSERT INTO otp_bucket(email_id, otp,mobile_no) VALUES (?, ?,?)");
			query.setString(0, email);
			query.setInteger(1, otp);
                        query.setString(2,mobileNo);
			query.executeUpdate();			
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			if (session != null) {
				session.close();
			}
		}
		
	}

	@Override
	public void deleteExpiredOTP() {
		
		Session session = null;
		try {

			session = sessionFactory.openSession();
			Query query = session.createSQLQuery("delete from otp_bucket where (cast(extract(epoch from now()) as numeric(10)) - entrydatetime) > ?");
			query.setInteger(0, otp_timeout);
			query.executeUpdate();
			
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			if (session != null) {
				session.close();
			}
		}
		
	}

	@Override
	public void deleteValidatedOTP(String email,String mobileNo) {
		
		Session session = null;
		try {

			session = sessionFactory.openSession();
                        
                        if(!mobileNo.equals("")){
                             Query query = session.createSQLQuery("delete from otp_bucket where mobile_no = ?");
                             query.setString(0, mobileNo);
			     query.executeUpdate();
                        }else{
                            Query query = session.createSQLQuery("delete from otp_bucket where email_id = ?");
                            query.setString(0, email);
			    query.executeUpdate();
                        }
			
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			if (session != null) {
				session.close();
			}
		}
		
	}

	@Override
	public boolean validateOTP(String email, Integer otp) {
		
		Session session = null;
		boolean valid = false;
		try {

			session = sessionFactory.openSession();
			Query query = session.createSQLQuery("select count(*) > 0 from otp_bucket where email_id = ? and otp = ? and (cast(extract(epoch from now()) as numeric(10)) - entrydatetime) < ?");
			query.setString(0, email);
			query.setInteger(1, otp);
			query.setInteger(2, otp_timeout);
			
			valid = (Boolean)query.uniqueResult();
			
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			if (session != null) {
				session.close();
			}
		}
		
		return valid;
	}
	
	public boolean registerMail(String toEmails, String ccEmails, String subject, String body) {

        Session session = null;
        boolean valid = false;
		try {
                        //System.out.println("registerMail "+toEmails);
			session = sessionFactoryMail.openSession();
			Query query = session.createSQLQuery("INSERT INTO MMI_CM_MAIL(TOMAILS, CCMAILS, SUBJECT, ATTACHMENTS, BODY, STATUS )  VALUES (?, ?, ?, ?, ?, ?)");
			
			query.setString(0, toEmails);
			query.setString(1, ccEmails);
			query.setString(2, subject);
			query.setString(3, null);
			query.setString(4, body);
			query.setString(5, "P");
			query.executeUpdate();
			valid = true;
			
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			if (session != null) {
				session.close();
			}
		}
        
		return valid;

    }

    @Override
    public  List<String> getGmailById(String proId) {
     
         Session session = null;
         List<String> list = new ArrayList<String>();
          System.out.println(proId);
		try {
			session = sessionFactory.openSession();
                        Query query ;
                        if(proId.contains("S")){
                             query = session.createSQLQuery("select property_owner_email,property_contact from property_details where property_unique_id= ?");
                             query.setString(0, proId);
                        }else{
                             query = session.createSQLQuery("select property_owner_email,property_contact from property_details where private_notice_no= ?");
                             query.setString(0, String.valueOf(proId));
                        }
                       //System.out.println(query);
                        List<Object[]> row1 =  query.list();
                        System.out.print("row1.size()"+row1.size());
                        if(row1.size()>0){
                            for (int i=0; i<row1.size(); i++){
                                Object[] r = (Object[]) row1.get(i);
                                list.add( r[0]!=null ? String.valueOf(r[0]):""); 
                                list.add( r[1]!=null ? String.valueOf(r[1]):""); 
                            }
                         }
                } catch (Exception e) {
			e.printStackTrace();
		} finally {
			if (session != null) {
				session.close();
			}
		}
                return list;
    }

    @Override
    public Response verifyOTP(String mailId,int otp,String mobileNo) {
      
          Session session = null;
          Response res = new Response();
		try {
			session = sessionFactory.openSession();
			Query query = session.createSQLQuery("select otp from otp_bucket where otp= ?");
                        query.setInteger(0, otp);
                        BigDecimal oTP = (BigDecimal) query.uniqueResult();
                        //System.out.println("mmi.silvassa.common.dao.OTPDaoImpl.verifyOTP() "+ oTP);
                        if(oTP!=null){
                            res.setStatus(200);
                            res.setMsg("OTP verified successfully");
                        }else{
                            res.setStatus(400);
                            res.setMsg("Please enter valid OTP");
                        }
                        
                } catch (Exception e) {
			e.printStackTrace();
		} finally {
			if (session != null) {
				session.close();
			}
		}
                return res;
        
    }

}
