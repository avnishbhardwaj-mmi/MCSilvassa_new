/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.silvassa.model;

import java.io.Serializable;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

/**
 *
 * @author CEINFO
 */
@Entity
@Table(name = "sl_action_tracker")
public class ActionTracker implements Serializable {

    @Column(name = "task")
    private String task;

    @Column(name = "initBy")
    private String initBy;
    @Id
    @Column(name = "initTime")
    private String initTime;

    @Column(name = "completeTime")
    private String completeTime;

    @Column(name = "ipAddress")
    private String ipAddress;

    @Column(name = "taskStatus")
    private String taskStatus;

    @Column(name = "remarks")
    private String remarks;

    /**
     * @return the task
     */
    public String getTask() {
        return task;
    }

    /**
     * @param task the task to set
     */
    public void setTask(String task) {
        this.task = task;
    }

    /**
     * @return the initBy
     */
    public String getInitBy() {
        return initBy;
    }

    /**
     * @param initBy the initBy to set
     */
    public void setInitBy(String initBy) {
        this.initBy = initBy;
    }

    /**
     * @return the initTime
     */
    public String getInitTime() {
        return initTime;
    }

    /**
     * @param initTime the initTime to set
     */
    public void setInitTime(String initTime) {
        this.initTime = initTime;
    }

    /**
     * @return the ipAddress
     */
    public String getIpAddress() {
        return ipAddress;
    }

    /**
     * @param ipAddress the ipAddress to set
     */
    public void setIpAddress(String ipAddress) {
        this.ipAddress = ipAddress;
    }

    /**
     * @return the taskStatus
     */
    public String getTaskStatus() {
        return taskStatus;
    }

    /**
     * @param taskStatus the taskStatus to set
     */
    public void setTaskStatus(String taskStatus) {
        this.taskStatus = taskStatus;
    }

    @Override
    public String toString() {
        return "ActionTracker{" + "task=" + task + ", initBy=" + initBy + ", initTime=" + initTime + ", completeTime=" + getCompleteTime() + ", ipAddress=" + ipAddress + ", taskStatus=" + taskStatus + '}';
    }

    /**
     * @return the completeTime
     */
    public String getCompleteTime() {
        return completeTime;
    }

    /**
     * @param completeTime the completeTime to set
     */
    public void setCompleteTime(String completeTime) {
        this.completeTime = completeTime;
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

}
