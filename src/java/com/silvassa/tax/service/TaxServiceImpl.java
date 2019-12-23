package com.silvassa.tax.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Component;

import com.silvassa.bean.FilterBean;
import com.silvassa.model.ActionTracker;
import com.silvassa.tax.dao.TaxDao;
import com.silvassa.util.MmiPathController;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.UnsupportedEncodingException;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLDecoder;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import javax.net.ssl.HttpsURLConnection;
import org.apache.log4j.Logger;

@Component("taxService")
public class TaxServiceImpl implements TaxService {

    @Autowired
    @Qualifier("taxDAO")
    TaxDao taxDao;

    static Logger logger = Logger.getLogger(TaxServiceImpl.class);

    @Override
    public String generateTax(FilterBean filterBean, ActionTracker actionTracker) {
        return taxDao.generateTax(filterBean, actionTracker);

    }
    @Override
    public String generateTaxById(String id, ActionTracker actionTracker){
       return taxDao.generateTaxById(id, actionTracker); 
    }

    @Override
    public void updateTaxPenalty() {
        taxDao.updateTaxPenalty();
    }

    @Override
    public void updateDelayPaymentCharges() {
        taxDao.updateDelayPaymentCharges();
    }

//    @Override
    public void updatePaymentStatus() {
        List<Map<String, String>> data = taxDao.getPaymentWithIncompleteStatus();
        if (data != null && data.size() > 0) {
            for (Map<String, String> m : data) {
                Map<String, String> mStatus = callAPI(m.get("amopuntdemand"), m.get("paymentdate"), m.get("payrefid"));
                if (mStatus != null) {
//                    RIP,SIP,Success,FAILED,TIMEOUT
                    if (mStatus.get("status") != null) {
                        if (mStatus.get("status").equalsIgnoreCase("Success")) {
                            taxDao.updatePaymentStatus(m.get("payrefid"), "C");
                        } else if (mStatus.get("status").equalsIgnoreCase("RIP") || mStatus.get("status").equalsIgnoreCase("SIP")) {
                            // Still pending...
                        } else if (mStatus.get("status").equalsIgnoreCase("FAILED") || mStatus.get("status").equalsIgnoreCase("TIMEOUT")) {
                            taxDao.updatePaymentStatus(m.get("payrefid"), "R");
                        } else {
                            logger.info("[TaxServiceImpl.updatePaymentStatus] : Unknown shatus - " + m.get("payrefid") + mStatus.get("status"));
                        }
                    }
                }
            }
        }
    }


    private static Map<String, String> callAPI(String amount, String trandate, String pgreferenceno) {

        //        ezpaytranid=19040447947088&amount=1&paymentmode=NET_BANKING&merchantid=185632&trandate=20190404&pgreferenceno=SL-10000000000127
        StringBuilder url = new StringBuilder(MmiPathController.getDataPath("icici.paymentverify.url"));

        url.append("ezpaytranid=");
        url.append("&amount=");
        url.append(amount);
        url.append("&paymentmode=NET_BANKING");
        url.append("&merchantid=");
        url.append(MmiPathController.getDataPath("icici.merchant"));
        url.append("&trandate=");
        url.append(trandate);
        url.append("&pgreferenceno=");
        url.append(pgreferenceno);

        BufferedReader in = null;
        StringBuffer response = null;
        HttpsURLConnection con = null;
        Map<String, String> data = null;

        try {
            URL obj = new URL(url.toString());
            con = (HttpsURLConnection) obj.openConnection();

            con.setRequestMethod("GET");
            con.setRequestProperty("Content-Type", "application/x-www-form-urlencoded");

            int responseCode = con.getResponseCode();
            logger.info("\n [TaxServiceImpl.callAPI] Sending 'POST' request to URL : " + url);
            logger.info("[TaxServiceImpl.callAPI] Response Code : " + responseCode);

            if (responseCode == 200) {
                in = new BufferedReader(new InputStreamReader(con.getInputStream()));
                String inputLine;
                response = new StringBuffer();
                while ((inputLine = in.readLine()) != null) {
                    response.append(inputLine);
                }
                logger.info("[TaxServiceImpl.callAPI] response.toString() : " + response.toString());

                data = urlToMap(response.toString());

            } else {
                in = new BufferedReader(new InputStreamReader(con.getErrorStream()));
                String inputLine;
                response = new StringBuffer();
                while ((inputLine = in.readLine()) != null) {
                    response.append(inputLine);
                }

                response = new StringBuffer();
            }
            logger.info(response.toString());
        } catch (MalformedURLException me) {
            logger.info("## MalformedURLException occured in TaxServiceImpl.callAPI => : " + me.getMessage());
        } catch (UnsupportedEncodingException me) {
            logger.info("## UnsupportedEncodingException in TaxServiceImpl.callAPI => : " + me.getMessage());
        } catch (IOException me) {
            logger.info("## IOException occured in TaxServiceImpl.callAPI => : " + me.getMessage());
        } catch (Exception me) {
            logger.info("## Exception occured in TaxServiceImpl.callAPI => : " + me.getMessage());
        } finally {
            try {
                if (in != null) {
                    in.close();
                }

            } catch (IOException ex) {
                logger.info("## Exception occured in TaxServiceImpl.callAPI=> : " + ex.getMessage());
            }
        }
        return data;
    }

    private static Map<String, String> urlToMap(String query) throws UnsupportedEncodingException {
        Map<String, String> query_pairs = new LinkedHashMap<String, String>();
        String[] pairs = query.split("&");
        for (String pair : pairs) {
            int idx = pair.indexOf("=");
            query_pairs.put(URLDecoder.decode(pair.substring(0, idx), "UTF-8"), URLDecoder.decode(pair.substring(idx + 1), "UTF-8"));
        }
        return query_pairs;
    }

}
