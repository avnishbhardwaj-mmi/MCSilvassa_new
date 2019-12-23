/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.silvassa.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Transient;
import org.springframework.web.multipart.MultipartFile;

/**
 *
 * @author CEINFO
 */
@Entity
@Table(name = "property_correction_form_image")
public class CorrectionFormImageBean {

    @Id
    @Column(name = "upload_id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int upload_id;

    @Column(name = "unique_id")
    private String uniqueId;
    @Column(name = "image_name")
    private String imageName;
    @Column(name = "image")
    private byte[] image_byte;
    @Column(name = "applicant_name")
    private String applicantName;
    @Column(name = "apllicant_mobile")
    private String applicantMobileNo;
    @Column(name = "applicant_email")
    private String applicantEmail;
    @Column(name = "upload_date")
    private String uploadDate;
    @Column(name = "document_type")
    private String documentType;
    @Column(name = "document_type1")
    private String documentType1;
    @Column(name = "image1")
    private byte[] image_byte1;
    @Column(name = "image2")
    private byte[] image_byte2;

    @Transient
    private String imageFile;
    @Transient
    private String imageFile1;
    @Transient
    private String imageFile2;

    public String getImageFile() {
        return imageFile;
    }

    public void setImageFile(String imageFile) {
        this.imageFile = imageFile;
    }

    public String getImageFile1() {
        return imageFile1;
    }

    public void setImageFile1(String imageFile1) {
        this.imageFile1 = imageFile1;
    }

    public String getImageFile2() {
        return imageFile2;
    }

    public void setImageFile2(String imageFile2) {
        this.imageFile2 = imageFile2;
    }

    @Column(name = "image_file1")
    private String imageName1;

    @Column(name = "image_flie2")
    private String imageName2;
    @Column(name = "status")
    private String status;
    @Column(name = "application_no")
    private String application_no;

    @Column(name = "offline_counter_ref_no")
    private String offlineRefNo;

    public String getOfflineRefNo() {
        return offlineRefNo;
    }

    public void setOfflineRefNo(String offlineRefNo) {
        this.offlineRefNo = offlineRefNo;
    }

    /**
     * @return the upload_id
     */
    public int getUpload_id() {
        return upload_id;
    }

    /**
     * @param upload_id the upload_id to set
     */
    public void setUpload_id(int upload_id) {
        this.upload_id = upload_id;
    }

    /**
     * @return the uniqueId
     */
    public String getUniqueId() {
        return uniqueId;
    }

    /**
     * @param uniqueId the uniqueId to set
     */
    public void setUniqueId(String uniqueId) {
        this.uniqueId = uniqueId;
    }

    /**
     * @return the imageName
     */
    public String getImageName() {
        return imageName;
    }

    /**
     * @param imageName the imageName to set
     */
    public void setImageName(String imageName) {
        this.imageName = imageName;
    }

    /**
     * @return the image_byte
     */
    public byte[] getImage_byte() {
        return image_byte;
    }

    /**
     * @param image_byte the image_byte to set
     */
    public void setImage_byte(byte[] image_byte) {
        this.image_byte = image_byte;
    }

    /**
     * @return the applicantName
     */
    public String getApplicantName() {
        return applicantName;
    }

    /**
     * @param applicantName the applicantName to set
     */
    public void setApplicantName(String applicantName) {
        this.applicantName = applicantName;
    }

    /**
     * @return the applicantMobileNo
     */
    public String getApplicantMobileNo() {
        return applicantMobileNo;
    }

    /**
     * @param applicantMobileNo the applicantMobileNo to set
     */
    public void setApplicantMobileNo(String applicantMobileNo) {
        this.applicantMobileNo = applicantMobileNo;
    }

    /**
     * @return the applicantEmail
     */
    public String getApplicantEmail() {
        return applicantEmail;
    }

    /**
     * @param applicantEmail the applicantEmail to set
     */
    public void setApplicantEmail(String applicantEmail) {
        this.applicantEmail = applicantEmail;
    }

    /**
     * @return the uploadDate
     */
    public String getUploadDate() {
        return uploadDate;
    }

    /**
     * @param uploadDate the uploadDate to set
     */
    public void setUploadDate(String uploadDate) {
        this.uploadDate = uploadDate;
    }

    /**
     * @return the documentType
     */
    public String getDocumentType() {
        return documentType;
    }

    /**
     * @param documentType the documentType to set
     */
    public void setDocumentType(String documentType) {
        this.documentType = documentType;
    }

    @Override
    public String toString() {
        return "CorrectionFormImageBean{" + "upload_id=" + upload_id + ", uniqueId=" + uniqueId + ", imageName=" + imageName + ", image_byte=" + image_byte + ", applicantName=" + applicantName + ", applicantMobileNo=" + applicantMobileNo + ", applicantEmail=" + applicantEmail + ", uploadDate=" + uploadDate + ", documentType=" + documentType + '}';
    }

    /**
     * @return the documentType1
     */
    public String getDocumentType1() {
        return documentType1;
    }

    /**
     * @param documentType1 the documentType1 to set
     */
    public void setDocumentType1(String documentType1) {
        this.documentType1 = documentType1;
    }

    /**
     * @return the imageName1
     */
    public String getImageName1() {
        return imageName1;
    }

    /**
     * @param imageName1 the imageName1 to set
     */
    public void setImageName1(String imageName1) {
        this.imageName1 = imageName1;
    }

    /**
     * @return the image_byte1
     */
    public byte[] getImage_byte1() {
        return image_byte1;
    }

    /**
     * @param image_byte1 the image_byte1 to set
     */
    public void setImage_byte1(byte[] image_byte1) {
        this.image_byte1 = image_byte1;
    }

    /**
     * @return the image_byte2
     */
    public byte[] getImage_byte2() {
        return image_byte2;
    }

    /**
     * @param image_byte2 the image_byte2 to set
     */
    public void setImage_byte2(byte[] image_byte2) {
        this.image_byte2 = image_byte2;
    }

    /**
     * @return the imageName2
     */
    public String getImageName2() {
        return imageName2;
    }

    /**
     * @param imageName2 the imageName2 to set
     */
    public void setImageName2(String imageName2) {
        this.imageName2 = imageName2;
    }

    /**
     * @return the imageFile2
     */
    /**
     * @return the status
     */
    public String getStatus() {
        return status;
    }

    /**
     * @param status the status to set
     */
    public void setStatus(String status) {
        this.status = status;
    }

    /**
     * @return the application_no
     */
    public String getApplication_no() {
        return application_no;
    }

    /**
     * @param application_no the application_no to set
     */
    public void setApplication_no(String application_no) {
        this.application_no = application_no;
    }

}
