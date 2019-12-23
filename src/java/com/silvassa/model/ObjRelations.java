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
@Table(name = "sl_obj_relations")
public class ObjRelations implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @Column(name = "sno")
    private Integer sno;
    @Column(name = "relationsid")
    private String relationsId;
    @Column(name = "relationsname")
    private String relationsName;
    @Column(name = "isactive")
    private String isActive;

    public ObjRelations() {
    }

    public ObjRelations(Integer sno) {
        this.sno = sno;
    }

    public Integer getSno() {
        return sno;
    }

    public void setSno(Integer sno) {
        this.sno = sno;
    }

    public String getRelationsId() {
        return relationsId;
    }

    public void setRelationsId(String relationsId) {
        this.relationsId = relationsId;
    }

    public String getRelationsName() {
        return relationsName;
    }

    public void setRelationsName(String relationsName) {
        this.relationsName = relationsName;
    }

    public String getIsActive() {
        return isActive;
    }

    public void setIsActive(String isActive) {
        this.isActive = isActive;
    }

    
}
