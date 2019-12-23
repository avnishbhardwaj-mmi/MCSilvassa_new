package com.silvassa.tax.dao;

import java.util.List;

import org.hibernate.SQLQuery;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.silvassa.bean.FilterBean;
import com.silvassa.model.ActionTracker;
import java.util.Map;
import org.apache.log4j.Logger;
import org.hibernate.Criteria;
import org.hibernate.Query;
import org.springframework.beans.factory.annotation.Qualifier;

@Component("taxDAO")
public class TaxDaoImpl implements TaxDao {

    private SessionFactory sessionFactory;

    private static final Logger logger = Logger.getLogger(TaxDaoImpl.class);

    @Autowired(required = true)
    @Qualifier("sessionHB")
    public void setSessionFactory(SessionFactory sessionFactory) {
        this.sessionFactory = sessionFactory;
    }

    @Override
    public String generateTax(FilterBean filterBean, ActionTracker actionTracker) {
        Session sessionHB = null;
        List<String> result = null;
        String status = "fail";
        SQLQuery query = null;
        String sql = null;
        try {
            if (filterBean.getZoneId() != null && !filterBean.getZoneId().equals("-1")) {
                if (filterBean.getWard() != null && !filterBean.getWard().equals("-1")) {
                    sql = "select * from tax_calculation_v1('" + filterBean.getZoneId() + "', '" + filterBean.getWard() + "', 'ward', '" + actionTracker.getInitBy() + "')";
                } else {
                    sql = "select * from tax_calculation_v1('" + filterBean.getZoneId() + "', '', 'zone', '" + actionTracker.getInitBy() + "')";
                }
            } else {
                sql = "select * from tax_calculation_v1('" + filterBean.getPropertyId() + "', '', 'property', '" + actionTracker.getInitBy() + "')";
            }
            sessionHB = sessionFactory.openSession();
            query = sessionHB.createSQLQuery(sql);
            result = query.list();
            logger.info("fn->" + sql + "status->" + result.get(0));
            status = (String) result.get(0);

        } catch (Exception e) {
            status = "fail";
            result = null;
            e.printStackTrace();
            logger.info(e.getMessage());
        } finally {
            query = null;
            sessionHB.close();
        }
        return status;
    }
    @Override
    public String generateTaxById(String id, ActionTracker actionTracker){
        Session sessionHB = null;
        List<String> result = null;
        String status = "fail";
        SQLQuery query = null;
        String sql = null;
        String asstmtYr="2018-2019";
        try {
            
            if(id!=null && id.length()>0){
               sql = "select * from tax_calculation_v1('" + id + "', '', 'property', '" + actionTracker.getInitBy() + "')"; 
            }
            
            StringBuffer sb=new StringBuffer();
            StringBuffer sb1=new StringBuffer();
            
            sb.append("delete from property_tax_details where SUBSTRING(tax_no,11,length(tax_no))=:uid");
            sb1.append("delete from property_tax  where property_unique_id =:uid and financial_year=:yyyy");
            sessionHB = sessionFactory.openSession();
            //System.out.println("1 "+id);
            Query query1  = sessionHB.createSQLQuery(sb.toString());
            query1.setParameter("uid", id);
            int result1 = query1.executeUpdate();
            //System.out.println("2 "+sb.toString());
            Query query2  = sessionHB.createSQLQuery(sb1.toString());
            query2.setParameter("uid", id);
            query2.setParameter("yyyy", asstmtYr);
            int result2 = query2.executeUpdate();
            //System.out.println("3 "+sb1.toString());
            //System.out.println("sql "+sql);
            
            query = sessionHB.createSQLQuery(sql);
            result = query.list();
            //System.out.println("sql result"+result);
            logger.info("fn->" + sql + "status->" + result.get(0));
            status = (String) result.get(0);

        } catch (Exception e) {
            //e.printStackTrace();
            status = "fail";
            result = null;
            logger.info(e.getMessage());
        } finally {
            query = null;
            sessionHB.close();
        }
        return status; 
    }
    

    @Override
    public void updateTaxPenalty() {
        Session sessionHB = null;
        SQLQuery query = null;
        String sql = null;
        try {

            sql = "select * from fn_daily_penalty_and_tax_update()";
            sessionHB = sessionFactory.openSession();
            query = sessionHB.createSQLQuery(sql);
            query.list();

        } catch (Exception e) {
            logger.info(e.getMessage());
        } finally {
            query = null;
            sessionHB.close();
        }

    }

    @Override
    public void updateDelayPaymentCharges() {
        Session sessionHB = null;
        SQLQuery query = null;
        String sql = null;
        try {

            sql = "select * from update_late_payment_charges()";
            sessionHB = sessionFactory.openSession();
            query = sessionHB.createSQLQuery(sql);
            query.list();

        } catch (Exception e) {
            logger.info(e.getMessage());
        } finally {
            query = null;
            sessionHB.close();
        }

    }

    @Override
    public void updatePaymentStatus(String payrefid, String status) {
        Session sessionHB = null;
        SQLQuery query = null;
        String sql = null;
        try {

            sql = "update sl_payment set status = ? where payrefid =?";
            sessionHB = sessionFactory.openSession();
            query = sessionHB.createSQLQuery(sql);
            query.setString(1, status);
            query.setString(2, payrefid);
            query.executeUpdate();

        } catch (Exception e) {
            logger.info(e.getMessage());
        } finally {
            query = null;
            sessionHB.close();
        }

    }

    @Override
    public List<Map<String, String>> getPaymentWithIncompleteStatus() {
        Session sessionHB = null;
        SQLQuery query = null;
        String sql = null;
        List<Map<String, String>> pftb = null;
        try {

            sql = "select payrefid, amopuntdemand, amopuntpaid, to_char(entrydatetime, 'YYYYMMDD') as paymentdate FROM sl_payment where status = 'I'";
            sessionHB = sessionFactory.openSession();
            query = sessionHB.createSQLQuery(sql);
            query.setResultTransformer(Criteria.ALIAS_TO_ENTITY_MAP);
            pftb = (List<Map<String, String>>) query.list();

        } catch (Exception e) {
            logger.info(e.getMessage());
        } finally {
            query = null;
            sessionHB.close();
        }
        return pftb;

    }

}
