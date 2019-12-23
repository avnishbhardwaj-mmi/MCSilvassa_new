package com.silvassa.service;

import java.util.List;
import java.util.Map;
import java.util.Set;

import org.json.simple.JSONObject;

import com.silvassa.bean.FilterBean;
import com.silvassa.bean.PropertyDetailsBean;
import com.silvassa.model.ActionTracker;
import com.silvassa.model.Localitymaster;
import com.silvassa.model.PropertyDetails;

import com.silvassa.model.PropertyFloor;
import com.silvassa.model.SlJobAllocation;
import com.silvassa.model.SubLocality;
import com.silvassa.model.TAXDetailBean;
import com.silvassa.model.UserRegistration;
import com.silvassa.model.Usermaster;
import com.silvassa.model.Wardmaster;
import com.silvassa.model.Zonemaster;

public interface SilvassaService {

    public List<Zonemaster> loadAllZones();

    public List<Wardmaster> loadAllWards();

    public List<Localitymaster> loadAllLocality();

    public List<String> loadAllProperty();

    public JSONObject getSearchCriteria(String zone_id, String ward, String locality);

    public JSONObject getSearchCriteria1(String zone_id, String ward, String locality);

    public List showPropertyDetails(String Zone_id, String property_id, String owner_name, String occ_name, String ward, String locality, String aadharNum, String category, String prop_id_input, String phone_no, String sub_locality,String houseNo);

    public List<PropertyFloor> getPropertyFloorDetails(String property_unique_id);

    public int updatePropertyDetails(PropertyDetailsBean propBean);
    //-1111
    //public List calculateTax(String zoneid, String prop_id, String type);

    public List<TAXDetailBean> viewTax(List<PropertyDetails> propList);

    public List<Map<String, Object>> loadOwners(String property_id);

    public int checkGeneratedTax(String zone_id);

    public List<TAXDetailBean> getTaxDetails(String property_unique_id);

    public List<Usermaster> loadAllActiveUsers(Usermaster dto);

    public String resetPermission(FilterBean filterBean);

    public String registerNewUser(Usermaster usermaster);

    public List<TAXDetailBean> searchTAXAmount(Set propIds, String financialYr);

    public void appendToActionTracker(ActionTracker actionTracker);

    public ActionTracker getLastTAXCalculated();

    public ActionTracker getLastNoticeGenerated();

    public List<PropertyDetails> filterProperty(String taxObj);

    public List viewAssessmentList(String taxObj);

    public List viewPrivateNotice(String taxObj);

    public List view142Notice(String taxObj);

    public List view143Notice(String taxObj);

    public List viewBillPdf(String taxOb);

    public String generatePrivateNoticeNo(String taxObj);

    public String generateBillNumber(String taxObj);

    public List<Wardmaster> getWards(String zone_id);

    public List<SubLocality> getSubLocality(String ward);

    public void addNewProperty(PropertyDetails propertyDetail, List<PropertyFloor> propertyFloor);

    public List<Usermaster> userList();

    public String jobAllocation(List<SlJobAllocation> slJobAllocation);

    public List getAllocatedJobs();

    public String jobDeAllocation(List<SlJobAllocation> slJobAllocations);

    public boolean checkIfUserIdExist(Usermaster usermaster);

    public UserRegistration checkPropertyId(String propid);

    public String saveMobile(String propid, String ownerMobile, String ownerEmail, String occupierMobile, String occupierEmail, String documentType, String proof_msg, String imageName, byte[] image);

    public UserRegistration showImage(String id);

    public UserRegistration getImage(String id);

    public List getChartData();

    public List getWardWiseData(String ward);

    public Map<String, String> getZoneWardMaster();

    public List<Map> getPropertys(String searchStr);

    public List<Map> getPhoneNos(String searchStr);

    public List<Map> getCityCodes(String searchStr);

    public List<Map> getaadharNo(String searchStr);

    public List<Map> getsubLocality(String searchStr);

    public List<Map> getWardlst(String searchStr);

    public List<Map> getOwnerlst(String searchStr);

    public List<Map> getOccupierlst(String searchStr);

    public List<Map> getLocalitylst(String searchStr);

    public List<Map> getPropertyTypeLst(String searchStr);
    
    public List<Map> getHouseLst(String searchStr);

    public List<Usermaster> getUserId(String userId);

    public String updateUserId(Usermaster usermaster);
    
    public List getGeoMetry(String type , String dataList);
    
    public String billPdfHistory(String userId,String taxOb);
    
}
