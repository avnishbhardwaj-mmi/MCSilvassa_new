/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package com.silvassa.bean;

/**
 *
 * @author CEINFO
 */
public class ResolutionBean {
    
    private String propertyId;
    private String objectionId;
    private String decision;
    private String remarks;
    private String resolveBy;
    private String forwardTo;
    private String resolveOn;

    /**
     * @return the propertyId
     */
    public String getPropertyId() {
        return propertyId;
    }

    /**
     * @param propertyId the propertyId to set
     */
    public void setPropertyId(String propertyId) {
        this.propertyId = propertyId;
    }

    /**
     * @return the objectionId
     */
    public String getObjectionId() {
        return objectionId;
    }

    /**
     * @param objectionId the objectionId to set
     */
    public void setObjectionId(String objectionId) {
        this.objectionId = objectionId;
    }

    /**
     * @return the decision
     */
    public String getDecision() {
        return decision;
    }

    /**
     * @param decision the decision to set
     */
    public void setDecision(String decision) {
        this.decision = decision;
    }

    /**
     * @return the remarks
     */
    public String getRemarks() {
        return remarks;
    }

    /**
     * @param remarks the remarks to set
     */
    public void setRemarks(String remarks) {
        this.remarks = remarks;
    }

    public String getResolveBy() {
        return resolveBy;
    }

    public void setResolveBy(String resolveBy) {
        this.resolveBy = resolveBy;
    }


    public String getResolveOn() {
        return resolveOn;
    }

    public void setResolveOn(String resolveOn) {
        this.resolveOn = resolveOn;
    }

    public String getForwardTo() {
        return forwardTo;
    }

    public void setForwardTo(String forwardTo) {
        this.forwardTo = forwardTo;
    }

    @Override
    public String toString() {
        return "ResolutionBean{" + "propertyId=" + propertyId + ", objectionId=" + objectionId + ", decision=" + decision + ", remarks=" + remarks + ", resolveBy=" + resolveBy + ", forwardTo=" + forwardTo + ", resolveOn=" + resolveOn + '}';
    }
    
    

}
