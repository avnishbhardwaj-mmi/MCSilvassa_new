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

/**
 *
 * @author CEINFO   Avnish
 */
@Entity
@Table(name = "correction_action_history")
public class CorrectionActionHistory {

    @Id
    @Column(name = "sno")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int sno;
    @Column(name = "correction_id")
    private String correction_id;
    @Column(name = "property_id")
    private String property_id;
    @Column(name = "action_by")
    private String action_by;
    @Column(name = "action_by_name")
    private String action_by_name;
    @Column(name = "action_taken")
    private String action_taken;
    @Column(name = "forward_to")
    private String forward_to;
    @Column(name = "forward_to_name")
    private String forward_to_name;
    @Column(name = "action_remarks")
    private String action_remarks;
    @Column(name = "entrydatetime")
    private String entrydatetime;

    public int getSno() {
        return sno;
    }

    public void setSno(int sno) {
        this.sno = sno;
    }

    public String getCorrection_id() {
        return correction_id;
    }

    public void setCorrection_id(String correction_id) {
        this.correction_id = correction_id;
    }

    public String getProperty_id() {
        return property_id;
    }

    public void setProperty_id(String property_id) {
        this.property_id = property_id;
    }

    public String getAction_by() {
        return action_by;
    }

    public void setAction_by(String action_by) {
        this.action_by = action_by;
    }

    public String getAction_by_name() {
        return action_by_name;
    }

    public void setAction_by_name(String action_by_name) {
        this.action_by_name = action_by_name;
    }

    public String getAction_taken() {
        return action_taken;
    }

    public void setAction_taken(String action_taken) {
        this.action_taken = action_taken;
    }

    public String getForward_to() {
        return forward_to;
    }

    public void setForward_to(String forward_to) {
        this.forward_to = forward_to;
    }

    public String getForward_to_name() {
        return forward_to_name;
    }

    public void setForward_to_name(String forward_to_name) {
        this.forward_to_name = forward_to_name;
    }

    public String getAction_remarks() {
        return action_remarks;
    }

    public void setAction_remarks(String action_remarks) {
        this.action_remarks = action_remarks;
    }

    public String getEntrydatetime() {
        return entrydatetime;
    }

    public void setEntrydatetime(String entrydatetime) {
        this.entrydatetime = entrydatetime;
    }

    @Override
    public String toString() {
        return "CorrectionActionHistory{" + "sno=" + sno + ", correction_id=" + correction_id + ", property_id=" + property_id + ", action_by=" + action_by + ", action_by_name=" + action_by_name + ", action_taken=" + action_taken + ", forward_to=" + forward_to + ", forward_to_name=" + forward_to_name + ", action_remarks=" + action_remarks + ", entrydatetime=" + entrydatetime + '}';
    }
    
    

}
