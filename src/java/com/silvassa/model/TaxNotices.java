package com.silvassa.model;

import java.util.Date;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.persistence.Transient;

/**
 * TaxNotices generated by hbm2java
 */
@Entity
@Table(name = "tax_notices", schema = "public")
public class TaxNotices implements java.io.Serializable {

    private long taxNoticesId;
    private String noticeNo;
    private String taxNo;
    private String financialYear;
    private String propertyId;
    private byte[] noticePdf;
    private String noticePdfName;
    private Date noticeGenDate;
    private String noticeGenBy;
    private String zoneId;

    @Id
    @Column(name = "tax_notices_id", unique = true, nullable = false)
    public long getTaxNoticesId() {
        return this.taxNoticesId;
    }

    public void setTaxNoticesId(long taxNoticesId) {
        this.taxNoticesId = taxNoticesId;
    }

    @Column(name = "notice_no")
    public String getNoticeNo() {
        return this.noticeNo;
    }

    public void setNoticeNo(String noticeNo) {
        this.noticeNo = noticeNo;
    }

    @Column(name = "tax_no")
    public String getTaxNo() {
        return this.taxNo;
    }

    public void setTaxNo(String taxNo) {
        this.taxNo = taxNo;
    }

    @Column(name = "financl_year")
    public String getFinancialYear() {
        return financialYear;
    }

    public void setFinancialYear(String financialYear) {
        this.financialYear = financialYear;
    }

    @Column(name = "property_id")
    public String getPropertyId() {
        return this.propertyId;
    }

    public void setPropertyId(String propertyId) {
        this.propertyId = propertyId;
    }

    @Transient
//	@Column(name = "notice_pdf")
    public byte[] getNoticePdf() {
        return this.noticePdf;
    }

    public void setNoticePdf(byte[] noticePdf) {
        this.noticePdf = noticePdf;
    }

    @Column(name = "notice_pdf_name")
    public String getNoticePdfName() {
        return this.noticePdfName;
    }

    public void setNoticePdfName(String noticePdfName) {
        this.noticePdfName = noticePdfName;
    }

    @Temporal(TemporalType.TIMESTAMP)
    @Column(name = "notice_gen_date")
    public Date getNoticeGenDate() {
        return this.noticeGenDate;
    }

    public void setNoticeGenDate(Date noticeGenDate) {
        this.noticeGenDate = noticeGenDate;
    }

    @Column(name = "notice_gen_by")
    public String getNoticeGenBy() {
        return this.noticeGenBy;
    }

    public void setNoticeGenBy(String noticeGenBy) {
        this.noticeGenBy = noticeGenBy;
    }

    @Column(name = "zone_id")
    public String getZoneId() {
        return this.zoneId;
    }

    public void setZoneId(String zoneId) {
        this.zoneId = zoneId;
    }

}
