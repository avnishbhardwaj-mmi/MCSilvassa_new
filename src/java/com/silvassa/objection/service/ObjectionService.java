/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.silvassa.objection.service;

import com.silvassa.bean.ObjSearchBean;
import com.silvassa.bean.ResolutionBean;
import com.silvassa.model.ObjDocument;
import com.silvassa.model.ObjRelations;
import com.silvassa.model.ObjectionActionHistory;
import com.silvassa.model.ObjectionActionTray;
import com.silvassa.model.ObjectionBean;
import com.silvassa.model.ObjectionTx;
import com.silvassa.model.PropertyEditableFields;
import com.silvassa.model.PropertyFloor;
import com.silvassa.model.Usermaster;
import java.util.List;
import org.json.simple.JSONObject;

/**
 *
 * @author CEINFO
 */
public interface ObjectionService {

    public List searchObjectionDetails(ObjSearchBean objSearchBean);

    public JSONObject loadPropertyMasterList();

    public List<PropertyEditableFields> getEditableAttr();

    List<PropertyFloor> getFloorDetails(String propertyId);

    public boolean raiseObjection(ObjectionTx objectionTx);

    public String getObjectionRefNumber() throws Exception;

    public List<ObjDocument> getVerifyingDocuments();

    public boolean checkIfPendingObjection(String propertyId) throws Exception;

    public List<ObjectionBean> getObjectedAttributes(String propertyId, String objectionId) throws Exception;

    public ObjectionTx getObjection(String propertyId) throws Exception;

    public JSONObject getObjectedPropertyForSearch(String zoneId, String ward) throws Exception;

    public List searchObjectedProperty(ObjSearchBean objSearchBean) throws Exception;

    public Object rangeOfPendingObjection() throws Exception;

    public void approveObjection(ResolutionBean resolutionBean) throws Exception;

    public void rejectObjection(ResolutionBean resolutionBean) throws Exception;
    
    public List<ObjRelations> getVerifyingRelations();
    
    public boolean addObjectionActionHistory(ObjectionActionHistory oah);
    
    public ObjectionActionTray getObjetionTray(ResolutionBean resolutionBean);
    
    public boolean addUpdateObjetionTray(ObjectionActionTray oat);
    
    public void forwardObjection(ResolutionBean resolutionBean) throws Exception;
    
    public void forwardBackObjection(ResolutionBean resolutionBean) throws Exception;
    
    public List<Usermaster> userList();
    
    public List<ObjectionActionHistory> getObjectionActionHistory(ResolutionBean resolutionBean);
    
    public List objectionInMyTray(JSONObject jo) throws Exception;
    
    public String checkNoticeGeneratedOrNOt(String propId);

}
