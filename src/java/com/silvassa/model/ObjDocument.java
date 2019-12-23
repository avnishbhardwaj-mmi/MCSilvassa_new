/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package com.silvassa.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

/**
 *
 * @author CEINFO
 */
@Entity
@Table(name = "sl_obj_documents")
public class ObjDocument {
    
    @Id
    @Column(name = "docid")
    private String docId;
    @Column(name = "docname")
    private String docName;
    @Column(name = "isactive")
    private String isActive;

    /**
     * @return the docId
     */
    public String getDocId() {
        return docId;
    }

    /**
     * @param docId the docId to set
     */
    public void setDocId(String docId) {
        this.docId = docId;
    }

    /**
     * @return the docName
     */
    public String getDocName() {
        return docName;
    }

    /**
     * @param docName the docName to set
     */
    public void setDocName(String docName) {
        this.docName = docName;
    }

    @Override
    public String toString() {
        return "ObjDocument{" + "docId=" + docId + ", docName=" + docName + '}';
    }

    /**
     * @return the isActive
     */
    public String getIsActive() {
        return isActive;
    }

    /**
     * @param isActive the isActive to set
     */
    public void setIsActive(String isActive) {
        this.isActive = isActive;
    }
    
    
    
}
