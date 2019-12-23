/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.silvassa.dao;

import com.silvassa.bean.CorrectionAction;
import com.silvassa.bean.CorrectionFormLoadData;
import com.silvassa.bean.ImageBean;
import com.silvassa.bean.LoginDetailsBean;
import com.silvassa.model.CorrectionFormBean;
import com.silvassa.model.CorrectionFormFloorBean;
import com.silvassa.model.CorrectionFormFloorReport;
import com.silvassa.model.CorrectionFormHitLogger;
import com.silvassa.model.CorrectionFormImageBean;
import com.silvassa.model.CorrectionFormReport;
import com.silvassa.model.CorrectionFormSaveBean;
import com.silvassa.model.CorrectionRequestBean;
import com.silvassa.model.MobileBean;
import com.silvassa.model.CorrectionActionHistory;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 *
 * @author CEINFO
 */
public interface CorrectionDAO {

    public CorrectionFormBean getCorrectionForm(String propId, String applicationNo);

    public CorrectionFormLoadData getPropertyDetail(String propId);

    public void markCorrectionAsRead(String propId, String applicationNo);

    public void addUserAction(CorrectionAction correctionAction, LoginDetailsBean loginUserBean);

    public void updateCorrectionStatus(CorrectionAction correctionAction);

    public Map<String, Object> getCorrectionCount(int actionFliter);

    public Map<String, Map> getCorrectionCountOnWard(int actionFliter);

    public List<CorrectionFormBean> getCorrectionListAll(int actionFliter);

    public List<CorrectionFormBean> getCorrectionListInbox();

    public List<CorrectionFormBean> getCorrectionListDone();

    public List<CorrectionFormBean> getCorrectionListApproved();

    public List<CorrectionFormBean> getCorrectionListReject();

    public List<CorrectionFormBean> getCorrectionListLongHalt();

    public List<CorrectionFormBean> getCorrectionListFieldVerify();

    public List<Map<String, String>> getCorrectionActionHistory(String propId, String applicationNo);

    public String getCorrectionStatus(String propId, String applicationNo);

    public List<Map<String, String>> getCorrectionImageProof(String propId, String applicationNo);

    public ImageBean getProofImage(String propId, String applicationNo, String imageCol);

    public List<Map<String, String>> getCorrectionOfflineListAll(int actionFliter);

    public CorrectionFormImageBean getCorrectionOfflineForm(String propId, String applicationNo);

    public CorrectionFormFloorBean editPropertyFloorId(String floorId, String id, String applicatonNo);

    public List<CorrectionFormFloorBean> editPropertyFloorMultipleId(String id, String applicatonNo);

    public String saveCorrectionFormFloorData(CorrectionFormFloorBean floorBean);

    public String deleteCorrectionFormFloorData(CorrectionFormFloorBean floorBean);

    public String addNewCorrectionFormFloorData(CorrectionFormFloorBean floorBean);

    public String saveCorrectionFormData(CorrectionFormSaveBean bean);

    public CorrectionRequestBean editPropertyId(String id, String applicationNo);

    public CorrectionFormSaveBean getUpdatedDataFromCorrectionForm(CorrectionRequestBean bean);

    public MobileBean getMbileNo(String propId);

    public List<CorrectionFormLoadData> getPropertyIdOffline(String id);

    public List<CorrectionFormLoadData> getPropertyIdSplitProperty(String id, String newPid);

    public void addCorrectionFormHitLogger(CorrectionFormHitLogger correctionHitLogger);

    public String addCorrectionFormData(CorrectionFormBean bean);

    public String addCorrectionFormDataFloor(CorrectionFormFloorBean bean);

    public String updateOfflineToOnlineRefNo(String propertyId, String applicationNo, String complainNo);

    public String loadLocality(String ward);

    public List<Map<String, String>> getAllRegisterationReq();

    public List<Map<String, Object>> getRegisterationReq(String reqNo);

    public List<Map<String, Object>> getRegisterationForMobile(String pid);

    public int updateUserRegistrationStatus(Integer sno, String remarks, String action);

    public List<CorrectionFormSaveBean> getCorrectionReport(String report);

    public Map<String, CorrectionFormReport> getCorrectionReportFromPropertyDetail(String reportType);

    public List<CorrectionFormFloorBean> getCorrectionReportFloor();

    public Map<String, ArrayList<CorrectionFormFloorBean>> getCorrectionReportFloorCombine();

    public Map<String, ArrayList<CorrectionFormFloorReport>> getCorrectionReportFromPropertyFloor();

    public Map<String, ArrayList<CorrectionFormFloorReport>> getCorrectionReportFromPropertyFloorCombine();

    public String propertySplit(String pid, String newPid, String propertyType);

    public String checkPropertyId(String pid);

    public String updateBuildingUse();

    public String saveMobile(String propid, String ownerMobile, String ownerEmail);

    public List<CorrectionFormBean> getCorrectionList(String fromDate, String todate, String cmpType);

    public Map<String, Object> getCorrectionCountFilter(String fromDate, String todate);

    public List<CorrectionActionHistory> getcorrectDetails(String propertyId);
}
