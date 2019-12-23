package com.silvassa.model;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Temporal;

@Entity
@Table(name = "tax_notices")
public class NoticeViewBean {

    @Id
    @Column(name = "notice_no")
    private String noticeNo;

    @Column(name = "tax_no")
    private String taxNo;

    @Column(name = "financl_year")
    private String financialYear;

    @Column(name = "property_id")
    private String propertyId;

    @Column(name = "notice_pdf")
    private byte[] noticePdf;

    @Column(name = "notice_pdf_name")
    private String noticePdfName;

    @Column(name = "notice_gen_date")
    @Temporal(javax.persistence.TemporalType.DATE)
    private Date noticeGenDate;

    @Column(name = "notice_gen_by")
    private String noticeGenBy;

    @Column(name = "zone_id")
    private Integer zoneId;

    public String getNoticeNo() {
        return noticeNo;
    }

    public void setNoticeNo(String noticeNo) {
        this.noticeNo = noticeNo;
    }

    public String getTaxNo() {
        return taxNo;
    }

    public void setTaxNo(String taxNo) {
        this.taxNo = taxNo;
    }

    public String getFinancialYear() {
        return financialYear;
    }

    public void setFinancialYear(String financialYear) {
        this.financialYear = financialYear;
    }

    public String getPropertyId() {
        return propertyId;
    }

    public void setPropertyId(String propertyId) {
        this.propertyId = propertyId;
    }

    public byte[] getNoticePdf() {
        return noticePdf;
    }

    public void setNoticePdf(byte[] noticePdf) {
        this.noticePdf = noticePdf;
    }

    public String getNoticePdfName() {
        return noticePdfName;
    }

    public void setNoticePdfName(String noticePdfName) {
        this.noticePdfName = noticePdfName;
    }

    public Date getNoticeGenDate() {
        return noticeGenDate;
    }

    public void setNoticeGenDate(Date noticeGenDate) {
        this.noticeGenDate = noticeGenDate;
    }

    public String getNoticeGenBy() {
        return noticeGenBy;
    }

    public void setNoticeGenBy(String noticeGenBy) {
        this.noticeGenBy = noticeGenBy;
    }

    public Integer getZoneId() {
        return zoneId;
    }

    public void setZoneId(Integer zoneId) {
        this.zoneId = zoneId;
    }

    @Override
    public String toString() {
        return "NoticeViewBean [noticeNo=" + noticeNo + ", taxNo=" + taxNo
                + ", financialYear=" + financialYear + ", propertyId=" + propertyId
                + ", noticePdfName=" + noticePdfName + ", noticeGenDate="
                + noticeGenDate + ", noticeGenBy=" + noticeGenBy + ", zoneId="
                + zoneId + "]";
    }

}
