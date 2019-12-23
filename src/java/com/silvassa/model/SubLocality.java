package com.silvassa.model;
// default package
// Generated Dec 15, 2016 5:54:25 PM by Hibernate Tools 4.0.0

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

/**
 * Wardmaster generated by hbm2java
 */
@Entity
@Table(name = "ms_sublocality", schema = "public")
public class SubLocality implements java.io.Serializable {

    
    @Column(name = "sno")
    private int sno;
    @Id
    @Column(name = "sublocality")
    private String subLocality;
    @Column(name = "display_name")
    private String displayName;
    @Column(name = "ward_associated")
    private String wardAssociated;
    @Column(name = "status")
    private String status;

    /**
     * @return the sno
     */
    public int getSno() {
        return sno;
    }

    /**
     * @param sno the sno to set
     */
    public void setSno(int sno) {
        this.sno = sno;
    }

    /**
     * @return the subLocality
     */
    public String getSubLocality() {
        return subLocality;
    }

    /**
     * @param subLocality the subLocality to set
     */
    public void setSubLocality(String subLocality) {
        this.subLocality = subLocality;
    }

    /**
     * @return the displayName
     */
    public String getDisplayName() {
        return displayName;
    }

    /**
     * @param displayName the displayName to set
     */
    public void setDisplayName(String displayName) {
        this.displayName = displayName;
    }

    /**
     * @return the wardAssociated
     */
    public String getWardAssociated() {
        return wardAssociated;
    }

    /**
     * @param wardAssociated the wardAssociated to set
     */
    public void setWardAssociated(String wardAssociated) {
        this.wardAssociated = wardAssociated;
    }

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
    @Override
    public String toString() {
        return "SubLocality{" + "sno=" + sno + ", subLocality=" + subLocality + ", displayName=" + displayName + ", wardAssociated=" + wardAssociated + ", status=" + status + '}';
    }
  
}
