/*
 * 
 */
package com.silvassa.controller;

import com.google.gson.Gson;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashSet;

import java.util.List;
import java.util.Map;
import java.util.Set;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.apache.log4j.Logger;
import org.json.simple.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.silvassa.bean.FilterBean;
import com.silvassa.bean.LoginDetailsBean;
import com.silvassa.bean.MasterBeans;
import com.silvassa.bean.PropertyAssessmentBean;
import com.silvassa.bean.PropertyDetailsBean;
import com.silvassa.dao.CorrectionDAO;
import com.silvassa.dao.OTPDaoImpl;
import com.silvassa.login.service.LoginService;
import com.silvassa.service.SilvassaService;
import com.silvassa.model.ActionTracker;
import com.silvassa.model.Localitymaster;
import com.silvassa.model.PropertyDetails;
import com.silvassa.model.PropertyFloor;
import com.silvassa.model.Response;
import com.silvassa.model.SubLocality;
import com.silvassa.model.TAXDetailBean;
import com.silvassa.model.UserRegistration;

import com.silvassa.model.Usermaster;
import com.silvassa.model.Wardmaster;
import com.silvassa.model.Zonemaster;
import com.silvassa.model.CorrectionActionHistory;
import com.silvassa.service.OTPService;
import com.silvassa.util.MMIUtil;
import java.io.ByteArrayOutputStream;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.HashMap;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletResponse;
import org.apache.commons.codec.binary.Base64;
import org.apache.commons.io.IOUtils;
import org.json.simple.JSONArray;
import org.json.simple.parser.JSONParser;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;

// TODO: Auto-generated Javadoc
/**
 * The Class HomeController.
 */
@Controller
public class HomeController {

    /**
     * The Constant logger.
     */
    private static final Logger logger = Logger.getLogger(HomeController.class);

    /**
     * The jsw service.
     */
    @Autowired
    private LoginService loginService;

    @Autowired
    private SilvassaService silvassaService;

    @Autowired
    @Qualifier("mMIUtil")
    private MMIUtil mMIUtil;

    @Autowired
    private OTPService OTPServiceImpl;

    @Autowired
    CorrectionDAO correctionDAO;

    //@Autowired
    //OTPDaoImpl oTPDaoImpl;
    @RequestMapping(value = {"/", "/welcome"}, method = {RequestMethod.POST, RequestMethod.GET})
    public ModelAndView loginUser(HttpServletRequest request, @ModelAttribute("loginDetailsBean") LoginDetailsBean loginDetailsBean) {
        ModelAndView mv = new ModelAndView();
        String view = "";
        String message = "";
        HttpSession session = request.getSession(false);

        try {
            // if(loginDetailsBean.getUserId()!=null){
            if (session != null) {
                if (session.getAttribute("userDetailsBean") != null) {
                    view = "welcome";
                } else if (loginDetailsBean.getUserId() != null) {
                    session = request.getSession(true);
                    LoginDetailsBean userDetails = loginService.loggedinUser(loginDetailsBean);
                    if (userDetails != null) {
                        session.setAttribute("userDetailsBean", userDetails);
                        view = "welcome";
                    } else {
                        view = "login";

                        message = "Invalid Userid/Password ";
                        request.setAttribute("errorMsg", message);
                    }
                } else {
                    message = "Please login again";
                    view = "login";
                }

            } else {
                view = "login";
                message = "";
            }

        } catch (Exception e) {
            message = "Please login again";
            view = "login";
            session.invalidate();
            logger.debug("exception in login");
            logger.info(e.getMessage());
        }
        mv.addObject("message", message);
        mv.setViewName(view);
        return mv;
    }

    @RequestMapping(value = "/logout")
    public String Logout(HttpServletRequest request, Model model) {
        String view = "login";
        HttpSession session = request.getSession(false);
        try {
            session.invalidate();
        } catch (Exception e) {
            logger.info("exception ->" + e.getMessage());
        }

        return view;
    }

    //Added By Jay
    @RequestMapping(value = "/newProperty")
    public String newProperty(HttpServletRequest request, Model model) {
        return "newProperty";
    }

    @RequestMapping(value = "/editProperty")
    public String editProperty(HttpServletRequest request, Model model) {
        return "editProperty";
    }
//Added By Jay End

    @RequestMapping(value = "/property", method = RequestMethod.GET)
    public String homePage(HttpServletRequest request, Model model) {
        return "editProperty";
    }

    /*	@RequestMapping(value = "/taxgeneration", method = RequestMethod.GET)
     public String taxgenerationPage(
     Model model) {
     String responsePage = null;
     try {
     HttpSession session = request.getSession(false);
     if (session == null || session.getAttribute("userDetailsBean") == null) {
     responsePage = "login";
     } else {

     responsePage = "taxgeneration";
     }

     } catch (Exception e) {
     logger.info(e.getMessage());
     }
     return responsePage;
     }*/
    @RequestMapping(value = "/taxView", method = RequestMethod.GET)
    public String taxViewPage(HttpServletRequest request, Model model) {

        return "viewtax";
    }

    @RequestMapping(value = "/paymentApprovalWindow", method = RequestMethod.GET)
    public String paymentApprovalWindow(HttpServletRequest request, Model model) {

        return "paymentApprovalWindow";
    }

    @RequestMapping(value = "/taxCollection", method = RequestMethod.GET)
    public String taxCalculatePage(HttpServletRequest request, Model model) {

        return "taxCollection";
    }

    @RequestMapping(value = "/reports", method = RequestMethod.GET)
    public String reportsPage(HttpServletRequest request, Model model) {

        return "reports";
    }

    @RequestMapping(value = "/others", method = RequestMethod.GET)
    public String othersPage(HttpServletRequest request, Model model) {

        return "others";
    }

    @RequestMapping(value = "/loadZones", method = {RequestMethod.POST})
    public @ResponseBody
    List<Zonemaster> loadZones(HttpServletRequest request) {
        List<Zonemaster> zonelist = new ArrayList<Zonemaster>();
        try {
            zonelist = silvassaService.loadAllZones();
            System.out.println("zonelist : " + zonelist.toString());
        } catch (Exception e) {
            logger.info(e.getMessage());
        }

        return zonelist;
    }

    @RequestMapping(value = "/loadWards", method = {RequestMethod.POST})
    public @ResponseBody
    List<Wardmaster> loadWards(HttpServletRequest request) {
        //System.out.println("check ward =====================================================");
        List<Wardmaster> wardlist = new ArrayList<Wardmaster>();
        try {
            wardlist = silvassaService.loadAllWards();
            //System.out.println("check ward "+wardlist.size());
        } catch (Exception e) {
            logger.info(e.getMessage());
        }
        return wardlist;
    }

    @RequestMapping(value = "/loadLocality", method = {RequestMethod.POST})
    public @ResponseBody
    List<Localitymaster> loadLocality(HttpServletRequest request) {
        List<Localitymaster> loclist = new ArrayList<Localitymaster>();
        try {
            loclist = silvassaService.loadAllLocality();
        } catch (Exception e) {
            logger.info(e.getMessage());
        }
        return loclist;
    }

    @SuppressWarnings({"unchecked", "unused"})
    @RequestMapping(value = "/getSearchCriteria", method = {RequestMethod.POST})
    public @ResponseBody
    JSONObject getPropertyDetails(HttpServletRequest request,
            @RequestParam("zone_id") String zone_id,
            @RequestParam("ward") String ward,
            @RequestParam("locality") String locality) {
        JSONObject jo = new JSONObject();
        JSONObject propertyDetailsBeanlist = null;
        try {
            propertyDetailsBeanlist = silvassaService.getSearchCriteria(zone_id, ward, locality);
        } catch (Exception e) {
            logger.info(e.getMessage());
        }
        jo.put("prop", propertyDetailsBeanlist);
        return jo;
    }

    @SuppressWarnings({"unchecked", "unused"})
    @RequestMapping(value = "/getSearchCriteria1", method = {RequestMethod.POST})
    public @ResponseBody
    JSONObject getPropertyDetails1(HttpServletRequest request,
            @RequestParam("zone_id") String zone_id) {
        JSONObject jo = new JSONObject();
        JSONObject propertyDetailsBeanlist = null;
        try {
            propertyDetailsBeanlist = silvassaService.getSearchCriteria1(zone_id, null, null);
        } catch (Exception e) {
            logger.info(e.getMessage());
        }
        jo.put("prop", propertyDetailsBeanlist);
        return jo;
    }

    @RequestMapping(value = "/searchPropertyDetails", method = {RequestMethod.POST})
    public @ResponseBody
    JSONObject showPropertyDetails(
            HttpServletRequest request,
            @RequestParam("zone_id") String zone_id,
            @RequestParam("property_id") String property_id,
            @RequestParam("ward") String ward,
            @RequestParam("locality") String locality,
            @RequestParam("aadhar_num") String aadhar_num,
            @RequestParam("owner_name") String owner_name,
            @RequestParam("occ_name") String occ_name,
            @RequestParam("category") String category,
            @RequestParam("prop_id_input") String prop_id_input,
            @RequestParam("phone_no") String phone_no,
            @RequestParam("sub_locality") String sub_locality,
            @RequestParam("houseNo") String houseNo) {

        JSONObject jo = new JSONObject();
        List<PropertyDetails> propertyDetailslist = null;
        try {
            propertyDetailslist = silvassaService.showPropertyDetails(zone_id, property_id, owner_name, occ_name, ward, locality, aadhar_num, category, prop_id_input, phone_no, sub_locality, houseNo);
            jo.put("propertyArr", propertyDetailslist);

            if (propertyDetailslist.size() > 0) {
                Set st = new HashSet();
                for (PropertyDetails pd : propertyDetailslist) {
                    st.add(pd.getPropertyUniqueId());
                }
                List<TAXDetailBean> taxDetailArr = silvassaService.searchTAXAmount(st, MMIUtil.getCurrentFinancialYear());
                jo.put("taxDetailArr", taxDetailArr);
            }

        } catch (Exception e) {
            logger.info(e.getMessage());
        }
        return jo;

    }

    @RequestMapping(value = "/showPropertyDetails", method = {RequestMethod.POST})
    public @ResponseBody
    JSONObject searchPropertyDetails(HttpServletRequest request, @RequestParam("property_unique_id") String property_unique_id) {

        JSONObject propertylist = new JSONObject();
        List<TAXDetailBean> taxDetails = null;
        List<PropertyFloor> floorDetails = null;
        List<CorrectionActionHistory> correctionHistory = null;

        try {

            floorDetails = silvassaService.getPropertyFloorDetails(property_unique_id);
//            taxDetails = silvassaService.getTaxDetails(property_unique_id);
            correctionHistory = correctionDAO.getcorrectDetails(property_unique_id);
            propertylist.put("floorDetails", floorDetails);
            propertylist.put("correctionHistory", correctionHistory);
//            propertylist.put("taxDetails", taxDetails);

        } catch (Exception e) {
            logger.info(e.getMessage());
        }

        return propertylist;

    }

    @RequestMapping(value = "/updatePropertyDetails", method = {RequestMethod.POST})
    public @ResponseBody
    int updatePropertyDetails(
            HttpServletRequest request,
            @RequestParam("property_unique_id") String property_unique_id,
            @RequestParam("property_smc_tax_payee") String property_smc_tax_payee,
            @RequestParam("prop_house_no") String prop_house_no,
            @RequestParam("prop_cam_id") String prop_cam_id,
            @RequestParam("property_plot_num") String property_plot_num,
            @RequestParam("property_survey_num") String property_survey_num,
            @RequestParam("prop_building_id") String prop_building_id,
            @RequestParam("prop_locality_id") String prop_locality_id,
            @RequestParam("prop_sub_locality_id") String prop_sub_locality_id,
            @RequestParam("prop_landmark_id") String prop_landmark_id,
            @RequestParam("property_road") String property_road,
            @RequestParam("property_pincode") String property_pincode
    // @RequestParam("property_usage_details") String property_usage_details,
    // @RequestParam("property_age_of_building") String
    // property_age_of_building,
    // @RequestParam("prop_cost_id") String prop_cost_id,
    // @RequestParam("prop_ownername_id") String prop_ownername_id,
    // @RequestParam("prop_mobile_id") String prop_mobile_id,
    // @RequestParam("prop_father_id") String prop_father_id,
    // @RequestParam("prop_spouse_id") String prop_spouse_id,
    // @RequestParam("prop_occupier_id") String prop_occupier_id,
    // @RequestParam("prop_relation_id") String prop_relation_id,
    // @RequestParam("owner_adhar_id") String owner_adhar_id,
    // @RequestParam("owner_email_id") String owner_email_id,
    // @RequestParam("owner_floors_id") String owner_floors_id,
    // @RequestParam("owner_rooms_id") String owner_rooms_id,
    // @RequestParam("ca_pipe_id") String ca_pipe_id,
    // @RequestParam("ca_sew_id") String ca_sew_id,
    // @RequestParam("ca_sew_no_id") String ca_sew_no_id,
    // @RequestParam("ca_elec_id") String ca_elec_id,
    // @RequestParam("ca_elec_no_id") String ca_elec_no_id,
    // @RequestParam("ca_ccCam_id") String ca_ccCam_id,
    // @RequestParam("ca_hording_id") String ca_hording_id,
    // @RequestParam("ca_fire_id") String ca_fire_id,
    // @RequestParam("ca_lift_id") String ca_lift_id,
    // @RequestParam("ca_borewell_id") String ca_borewell_id,
    // @RequestParam("ca_mobiletower_id") String ca_mobiletower_id,
    // @RequestParam("ca_rainharvest_id") String ca_rainharvest_id,
    // @RequestParam("prop_floor_room_cnt_hotel") String
    // prop_floor_room_cnt_hotel,
    // @RequestParam("prop_floor_room_cnt_school_clg") String
    // prop_floor_room_cnt_school_clg,
    // @RequestParam("prop_floor_room_cnt_hospi_nurse") String
    // prop_floor_room_cnt_hospi_nurse,
    // @RequestParam("prop_floor_room_cnt_hostel") String
    // prop_floor_room_cnt_hostel,
    // @RequestParam("floor_type_desc") String floor_type_desc,
    // @RequestParam("prop_floor_plot_area") String prop_floor_plot_area,
    // @RequestParam("prop_floor_construction_type") String
    // prop_floor_construction_type,
    // @RequestParam("prop_floor_usage_type") String prop_floor_usage_type,
    // @RequestParam("sanitation_type_desc") String sanitation_type_desc

    ) {

        JSONObject propertylist = new JSONObject();
        PropertyDetailsBean prop_bean = new PropertyDetailsBean();
        int status = 0;
        HttpSession session = request.getSession(false);
        try {
            // logger.info("started updating ");
            prop_bean.setProperty_unique_id(property_unique_id);
            prop_bean.setProperty_smc_tax_payee(property_smc_tax_payee);
            prop_bean.setProp_house_no(prop_house_no);
            prop_bean.setProp_cam_id(prop_cam_id);
            prop_bean.setProperty_plot_num(property_plot_num);
            prop_bean.setProperty_survey_num(property_survey_num);
            prop_bean.setProp_building_id(prop_building_id);
            prop_bean.setProp_locality_id(prop_locality_id);
            prop_bean.setProp_sub_locality_id(prop_sub_locality_id);
            prop_bean.setProp_landmark_id(prop_landmark_id);
            prop_bean.setProperty_road(property_road);
            prop_bean.setProperty_pincode(property_pincode);
            // prop_bean.setProperty_usage_details(property_usage_details);
            // prop_bean.setProperty_age_of_building(property_age_of_building);
            // prop_bean.setProp_cost_id(prop_cost_id);
            // prop_bean.setProp_ownername_id(prop_ownername_id);
            // prop_bean.setProp_mobile_id(prop_mobile_id);
            // prop_bean.setProp_father_id(prop_father_id);
            // prop_bean.setProp_spouse_id(prop_spouse_id);
            // prop_bean.setProp_occupier_id(prop_occupier_id);
            // prop_bean.setProp_relation_id(prop_relation_id);
            // prop_bean.setOwner_adhar_id(owner_adhar_id);
            // prop_bean.setOwner_email_id(owner_email_id);
            // prop_bean.setOwner_floors_id(owner_floors_id);
            // prop_bean.setOwner_rooms_id(owner_rooms_id);
            //
            // prop_bean.setCa_pipe_id(ca_pipe_id);
            // prop_bean.setCa_sew_id(ca_sew_id);
            // prop_bean.setCa_sew_no_id(ca_sew_no_id);
            // prop_bean.setCa_elec_id(ca_elec_id);
            // prop_bean.setCa_elec_no_id(ca_elec_no_id);
            // prop_bean.setCa_ccCam_id(ca_ccCam_id);
            // prop_bean.setCa_hording_id(ca_hording_id);
            // prop_bean.setCa_fire_id(ca_fire_id);
            // prop_bean.setCa_lift_id(ca_lift_id);
            // prop_bean.setCa_borewell_id(ca_borewell_id);
            // prop_bean.setCa_mobiletower_id(ca_mobiletower_id);
            // prop_bean.setCa_rainharvest_id(ca_rainharvest_id);
            //
            // prop_bean.setProp_floor_room_cnt_hotel(prop_floor_room_cnt_hotel);
            // prop_bean.setProp_floor_room_cnt_school_clg(prop_floor_room_cnt_school_clg);
            // prop_bean.setProp_floor_room_cnt_hospi_nurse(prop_floor_room_cnt_hospi_nurse);
            // prop_bean.setProp_floor_room_cnt_hostel(prop_floor_room_cnt_hostel);
            // prop_bean.setFloor_type_desc(floor_type_desc);
            // prop_bean.setProp_floor_plot_area(prop_floor_plot_area);
            // prop_bean.setProp_floor_construction_type(prop_floor_construction_type);
            // prop_bean.setProp_floor_usage_type(prop_floor_usage_type);
            // prop_bean.setSanitation_type_desc(sanitation_type_desc);

            prop_bean.setUsername((String) session.getAttribute("userName"));

            // propertyDetailsBeanlist = silvassaService.searchPropertyDetails(
            // property_id, property_details_id);
            status = silvassaService.updatePropertyDetails(prop_bean);

        } catch (Exception e) {
            logger.info(e.getMessage());

        }
        return status;

    }

    @RequestMapping(value = "/getPropertyImage", method = RequestMethod.POST)
    public @ResponseBody
    ArrayList<String> getPropertyImage(HttpServletRequest request, @RequestParam("property_unique_id") String property_unique_id)
            throws IOException {
        ArrayList<String> result = loginService.getPropertyImage(property_unique_id);
        return result;
    }
// -1111
//    @RequestMapping(value = "/calculateTax", method = {RequestMethod.POST})
//    public @ResponseBody
//    List<Map<String, Object>> calculateTax(
//            HttpServletRequest request,
//            @RequestParam("zone_id") String Zone_id,
//            @RequestParam("prop_id") String prop_id,
//            @RequestParam("req_type") String type) {
//        List<Map<String, Object>> rs_db = new ArrayList<Map<String, Object>>();
//        try {
//            rs_db = silvassaService.calculateTax(Zone_id, prop_id, type);
//
//        } catch (Exception e) {
//            logger.info(e.getMessage());
//            // TODO: handle exception
//        }
//
//        return rs_db;
//    }

    @RequestMapping(value = "/viewTaxDetails", method = {RequestMethod.POST})
    public @ResponseBody
    JSONObject viewTax(HttpServletRequest request, @RequestParam("taxObj") String taxObj) {
        List<TAXDetailBean> taxList = null;
        List<PropertyDetails> proplist = null;
        JSONObject jo = new JSONObject();

        try {

            proplist = silvassaService.filterProperty(taxObj);
            taxList = silvassaService.viewTax(proplist);

            if (taxList == null || taxList.size() <= 0) {
                jo.put("status", "700");
            } else {

                jo.put("status", "200");
                jo.put("taxDetails", taxList);
                jo.put("propDetails", proplist);
            }

        } catch (Exception e) {
            jo.put("status", "700");
            logger.info(e.getMessage());

        } finally {
            taxObj = null;
        }

        return jo;
    }

    @RequestMapping(value = "/checkTax", method = {RequestMethod.POST})
    public @ResponseBody
    Integer checkTax(HttpServletRequest request, @RequestParam("zone_id") String zone_id) {
        int rs_db = 0;
        try {
            // logger.info("zone " + zone_id + " ward " + ward_id +
            // " loc "
            // + loc_id);
            rs_db = silvassaService.checkGeneratedTax(zone_id);

        } catch (Exception e) {
            logger.info(e.getMessage());
            // TODO: handle exception
        }

        return rs_db;
    }

    // @RequestMapping(value = "/viewTax", method = { RequestMethod.POST })
    // public @ResponseBody List<Map<String, Object>> viewTax(
    // @RequestParam("zone_id") String Zone_id,
    // @RequestParam("ward_id") String ward_id,
    // @RequestParam("loc_id") String loc_id,
    // @RequestParam("prop_id") String prop_id) {
    // List<Map<String, Object>> rs_db = new ArrayList<Map<String, Object>>();
    // try {
    // rs_db = silvassaService.calculateTax(Zone_id, ward_id, loc_id,
    // prop_id,"view");
    //
    // } catch (Exception e) {
    // logger.info(e.getMessage());
    // // TODO: handle exception
    // }
    //
    // return rs_db;
    // }
    @RequestMapping(value = "/loadProperty", method = {RequestMethod.POST})
    public @ResponseBody
    List<String> loadProperty(HttpServletRequest request) {
        // logger.info("load property id startrd---->>");

        List<String> propList = new ArrayList<String>();

        try {
            propList = silvassaService.loadAllProperty();

            // logger.info("load property id startrd---->>" + propList);
        } catch (Exception e) {
            logger.info(e.getMessage());

        }
        return propList;
    }

    @RequestMapping(value = "/loadOwners", method = {RequestMethod.POST})
    public @ResponseBody
    List<Map<String, Object>> loadOwners(HttpServletRequest request, @RequestParam("property_id") String property_id) {
        // logger.info("load owners startrd---->>");

        List<Map<String, Object>> ownList = new ArrayList<Map<String, Object>>();

        try {
            ownList = silvassaService.loadOwners(property_id);
        } catch (Exception e) {
            logger.info(e.getMessage());

        }
        return ownList;

    }

    @RequestMapping(value = "/changeUserId", method = {RequestMethod.GET})
    public ModelAndView changeuserId(HttpServletRequest request, Model model) {
        ModelAndView mv = new ModelAndView();
        String view = "userManagementEditForm";
        mv.setViewName(view);
        return mv;
    }

    @ResponseBody
    @RequestMapping(value = "/getDataUserId", method = {RequestMethod.GET})
    public List<Usermaster> getDataUserId(HttpServletRequest request, Model model) {
        List<Usermaster> usermaster = null;
        String userId = "";
        if (request.getParameter("userId") != null) {
            userId = request.getParameter("userId");
            usermaster = silvassaService.getUserId(userId);
        }
        return usermaster;
    }

    @RequestMapping(value = "/updateUserId", method = RequestMethod.POST)
    public String updateUserId(HttpServletRequest request, @ModelAttribute("usermaster") Usermaster usermaster, Model model) {
        System.out.println("updateUserId");
        String responsePage = null;
        List<Usermaster> userList = null;
        //model.addAttribute("id", "create");
        //System.out.println("usermaster pssword "+usermaster.getPasword());
        //System.out.println("usermaster confirm pssword "+usermaster.getConfirmPassword());
        try {
            if (usermaster.getEmail() == null) {
                request.setAttribute("msg", "Please Fill All details ");
                return "userManagementEditForm";
            }
            if (usermaster.getEmpId() == null) {
                request.setAttribute("msg", "Please Fill All details ");
                return "userManagementEditForm";
            }
            if (usermaster.getUserId() == null) {
                request.setAttribute("msg", "Please Fill All details ");
                return "userManagementEditForm";
            }
            if (usermaster.getUsername() == null) {
                request.setAttribute("msg", "Please Fill All details ");
                return "userManagementEditForm";
            }
            if (usermaster.getMobile() == null) {
                request.setAttribute("msg", "Please Fill All details ");
                return "userManagementEditForm";
            }
            if (usermaster.getAddress() == null) {
                request.setAttribute("msg", "Please Fill All details ");
                return "userManagementEditForm";
            }
            if (usermaster.getPasword() == null) {
                request.setAttribute("msg", "Please Fill password details ");
                return "userManagementEditForm";
            }
            if (usermaster.getConfirmPassword() == null) {
                request.setAttribute("msg", "Please Fill  confirm password details ");
                return "userManagementEditForm";
            }
            if (usermaster.getPasword() != null && usermaster.getConfirmPassword() != null) {
                String pass = usermaster.getPasword();
                String confirmPass = usermaster.getConfirmPassword();
                if (!pass.equals(confirmPass)) {
                    request.setAttribute("msg", "Please Fill password and confirm password same details ");
                    return "userManagementEditForm";
                }

            }

            String status = "";
            status = silvassaService.updateUserId(usermaster);
            //System.out.println("udate 11 "+status);
            if (status.equals("success")) {
                request.setAttribute("msg", "User has been updated");
                System.out.println("udate " + status);
            } else {
                request.setAttribute("msg", "ERROR ");
            }

            //HttpSession session = request.getSession(false);
            //LoginDetailsBean userDetails = (LoginDetailsBean) session.getAttribute("userDetailsBean");
            //usermaster.setCreatedBy(userDetails.getUserId());
            //usermaster.setCreatedOn(new Date());
            //String status = "";
            responsePage = "userManagementEditForm";
//            if (silvassaService.checkIfUserIdExist(usermaster)) {
//                request.setAttribute("msg", "User already exist.");
//            } else {
//                status = silvassaService.updateUserId(usermaster);
//                if (status.equals("success")) {
//                    request.setAttribute("msg", "User has been created");
//                } else {
//                    request.setAttribute("msg", "ERROR ");
//                }
//            }

        } catch (Exception e) {
            e.printStackTrace();
            request.setAttribute("msg", "ERROR");
            logger.info(e.getMessage());
        }
        //model.addAttribute("userList", userList);
        return responsePage;
    }

    @RequestMapping(value = "/createNewUser", method = RequestMethod.POST)
    public String registerNewUser(HttpServletRequest request, @ModelAttribute("usermaster") Usermaster usermaster, Model model) {
        System.out.println("createNewUser");
        String responsePage = null;
        List<Usermaster> userList = null;
        model.addAttribute("id", "create");
        //System.out.println("usermaster pssword "+usermaster.getPasword());
        //System.out.println("usermaster confirm pssword "+usermaster.getConfirmPassword());
        try {
            if (usermaster.getEmail() == null) {
                request.setAttribute("msg", "Please Fill All details ");
                return "userManagementForm";
            }
            if (usermaster.getEmpId() == null) {
                request.setAttribute("msg", "Please Fill All details ");
                return "userManagementForm";
            }
            if (usermaster.getUserId() == null) {
                request.setAttribute("msg", "Please Fill All details ");
                return "userManagementForm";
            }
            if (usermaster.getUsername() == null) {
                request.setAttribute("msg", "Please Fill All details ");
                return "userManagementForm";
            }
            if (usermaster.getMobile() == null) {
                request.setAttribute("msg", "Please Fill All details ");
                return "userManagementForm";
            }
            if (usermaster.getAddress() == null) {
                request.setAttribute("msg", "Please Fill All details ");
                return "userManagementForm";
            }
            if (usermaster.getPasword() == null) {
                request.setAttribute("msg", "Please Fill password details ");
                return "userManagementForm";
            }
            if (usermaster.getConfirmPassword() == null) {
                request.setAttribute("msg", "Please Fill  confirm password details ");
                return "userManagementForm";
            }
            if (usermaster.getPasword() != null && usermaster.getConfirmPassword() != null) {
                String pass = usermaster.getPasword();
                String confirmPass = usermaster.getConfirmPassword();
                if (!pass.equals(confirmPass)) {
                    request.setAttribute("msg", "Please Fill password and confirm password same details ");
                    return "userManagementForm";
                }

            }

            HttpSession session = request.getSession(false);
            LoginDetailsBean userDetails = (LoginDetailsBean) session.getAttribute("userDetailsBean");
            usermaster.setCreatedBy(userDetails.getUserId());
            usermaster.setCreatedOn(new Date());
            String status = "";
            responsePage = "userManagementForm";
            if (silvassaService.checkIfUserIdExist(usermaster)) {
                request.setAttribute("msg", "User already exist.");
            } else {
                status = silvassaService.registerNewUser(usermaster);
                if (status.equals("success")) {
                    request.setAttribute("msg", "User has been created");
                } else {
                    request.setAttribute("msg", "ERROR ");
                }
            }

        } catch (Exception e) {
            e.printStackTrace();
            request.setAttribute("msg", "ERROR");
            logger.info(e.getMessage());
        }
        model.addAttribute("userList", userList);
        return responsePage;
    }

    @RequestMapping(value = "/setPermission", method = RequestMethod.POST)
    public String setPermission(HttpServletRequest request, @ModelAttribute("filterBean") FilterBean filterBean, Model model) {
        String responsePage = null;
        List<Usermaster> userList = null;
        try {
            String status = silvassaService.resetPermission(filterBean);
            userList = silvassaService.loadAllActiveUsers(new Usermaster());
            responsePage = "userManagementForm";
            if (status.equals("success")) {
                request.setAttribute("msg", "Permission Have been Set");
            } else {
                request.setAttribute("msg", "ERROR ");
            }

        } catch (Exception e) {
            request.setAttribute("msg", "ERROR");
            logger.info(e.getMessage());
        }
        model.addAttribute("userList", userList);
        model.addAttribute("id", "permission");
        return responsePage;
    }

    @RequestMapping(value = "/userManagement", method = RequestMethod.GET)
    public String userManagementForm(HttpServletRequest request, @RequestParam("id") String id, Model model) {

        String responsePage = "userManagementForm";
        List<Usermaster> userList = null;
        if (id.equals("permission")) {
            Usermaster dto = new Usermaster();
            userList = silvassaService.loadAllActiveUsers(dto);
            request.setAttribute("msg", "Please Select The Permission");
        } else if (id.equals("create")) {
            request.setAttribute("msg", "Create New User");
        }

        model.addAttribute("userList", userList);
        model.addAttribute("id", id);
        return responsePage;
    }

    @RequestMapping(value = "/userRegistration", method = RequestMethod.GET)
    public String userRegistrationForm(HttpServletRequest request, Model model) {

        String responsePage = "userRegistration";
//        List<Usermaster> userList = null;
//        if (id.equals("permission")) {
//            Usermaster dto = new Usermaster();
//            userList = silvassaService.loadAllActiveUsers(dto);
//            request.setAttribute("msg", "Please Select The Permission");
//        } else if (id.equals("create")) {
//            request.setAttribute("msg", "Create New User");
//        }
//
//        model.addAttribute("userList", userList);
//        model.addAttribute("id", id);
        return responsePage;
    }

    @RequestMapping(value = "/checkPropertyId", method = {RequestMethod.GET, RequestMethod.POST})
    @ResponseBody
    public UserRegistration checkPropertyId(String id) throws IOException {
        String msg = "";
        UserRegistration userRegistration = null;
        try {
            userRegistration = silvassaService.checkPropertyId(id);
            //System.out.println("msg "+msg);

        } catch (Exception ex) {

        } finally {
        }
        return userRegistration;
    }
    //String  proId,String ownerMobile,String ownerEmail,String occupierMobile,String occupierEmail,String documentType,String proof_msg,

    @RequestMapping(value = "/saveMobleAndEmail", method = {RequestMethod.GET, RequestMethod.POST})
    @ResponseBody
    public Response saveMobileAndEmailData(MultipartHttpServletRequest request,
            HttpServletResponse response) throws IOException {
        String msg = "";
        //System.out.println("data ddddd");
        String proId = request.getParameter("propertyId");
        String ownerMobile = request.getParameter("mobile");
        String ownerEmail = request.getParameter("email");
        String occupierMobile = request.getParameter("occupierMobile");
        String occupierEmail = request.getParameter("occupierEmail");
        String documentType = request.getParameter("documentType");
        String proof_msg = request.getParameter("docOther");

        //System.out.println("proId "+proId);
        MultipartFile multipartFile = request.getFile("fileDoc");

        //System.out.println("multipartfile "+multipartFile.getName());
        //System.out.println("multipartfile original "+multipartFile.getOriginalFilename());
        Long size = multipartFile.getSize();
        //System.out.println("size "+size);
        String contentType = multipartFile.getContentType();
        InputStream stream = multipartFile.getInputStream();
        byte[] bytes = IOUtils.toByteArray(stream);
        String imageName = multipartFile.getOriginalFilename();
        //System.out.println("proId "+proId);
        //System.out.println("ownerMobile "+ownerMobile);
        //System.out.println("ownerEmail "+ownerEmail);
        //System.out.println("occupierMobile "+occupierMobile);
        // System.out.println("occupierEmail "+occupierEmail);
        Response res = new Response();
        try {

            msg = silvassaService.saveMobile(proId, ownerMobile, ownerEmail, occupierMobile, occupierEmail, documentType, proof_msg, imageName, bytes);
            //System.out.println("msg ashoki");

            if (!msg.equals("")) {
                res.setMsg(msg);
                res.setStatus(200);
            } else {
                res.setMsg(msg);
                res.setStatus(400);
            }
        } catch (Exception ex) {

        } finally {
        }
        return res;
    }

    @RequestMapping(value = "/getGmailById", method = RequestMethod.POST)
    @ResponseBody
    public Map<String, Object> OTPSender(String proId, String mobile, String email) {

        //System.out.println("check email and mobile "+email+" "+mobile);
        return OTPServiceImpl.getGmailById(proId, mobile, email);
    }

    @RequestMapping(value = "/verifyOTP", method = RequestMethod.POST)
    @ResponseBody
    public Response verifyOTP(String mailId, String mobileNo, int otp, HttpServletRequest req) {
        req.getSession().setAttribute("sVAR", "rkj23jmn435kjnj");
        return OTPServiceImpl.verifyOTP(mailId, otp, mobileNo);

    }

    @RequestMapping(value = "/lastNoticeGenerated", method = {RequestMethod.POST, RequestMethod.GET})
    public @ResponseBody
    ActionTracker lastNoticeGenerated() {
        return silvassaService.getLastNoticeGenerated();
    }

    @RequestMapping(value = "/lastTAXGenerated", method = {RequestMethod.POST, RequestMethod.GET})
    public @ResponseBody
    ActionTracker lastTAXGenerated() {
        return silvassaService.getLastTAXCalculated();
    }

//-------------------------------------------------------------------------------------------------
// Method                   : downloadFile
// Parameters               : HttpServletRequest request, String htmlStr, String type, HttpServletResponse response
// Return type              : void
// Request URL              : /downloadFile.htm
// Request method           : POST
// Created on               : APRIL,2017
// Ceated by                : Sunil Kumar
// Objective                : Host the request to create and download the HTML table in from PDF or excel sheet.
// Modified on              :
// Modified by              :
//-------------------------------------------------------------------------------------------------
    @RequestMapping(value = "/downloadMaster.htm", method = RequestMethod.POST)
    public @ResponseBody
    void downloadLargeFile(HttpServletRequest request,
            @RequestParam("params") String params,
            @RequestParam("tHead") String tHead,
            @RequestParam("attrToAdd") String attrToAdd,
            @RequestParam("type") String type,
            @RequestParam("title") String title,
            @RequestParam("windowId") String windowId,
            HttpServletResponse response
    ) {
        ByteArrayOutputStream outByteStream = new ByteArrayOutputStream();

        response.addCookie(new Cookie("windowId", windowId));
        try {

            long ts = System.currentTimeMillis();
            String timeStamp = (String.valueOf(ts)).replaceAll(" ", "_");

            JSONParser jp = new JSONParser();
            String[] attrToAddArr = (String[]) ((JSONArray) jp.parse(attrToAdd)).toArray(new String[]{});
            String[] tHeadArr = (String[]) ((JSONArray) jp.parse(tHead)).toArray(new String[]{});

            MasterBeans masterBeans = new Gson().fromJson(params, MasterBeans.class);

            String htmlStr = mMIUtil.convertMasterBeanToHTMLString(masterBeans.getMasterBeans(), attrToAddArr, tHeadArr).toString();

            if (title == null || title.equals("")) {
                title = "Analysis";
            }

            if (type.equalsIgnoreCase("PDF")) {
                htmlStr = "<html><body> " + htmlStr + "</body></html>";

                byte[] outArray = mMIUtil.convertHtmlToPDF(title, htmlStr, outByteStream);
                response.setContentType("application/pdf");
                response.setContentLength(outArray.length);
                response.setHeader("Expires:", "0");
                response.setHeader("Content-Disposition", "attachment; filename=" + title + "-" + timeStamp + ".pdf");
                OutputStream outStream = response.getOutputStream();
                outStream.write(outArray);

            } else if (type.equalsIgnoreCase("EXCEL")) {
                byte[] outArray = mMIUtil.convertHtmlToExcel(title, htmlStr, outByteStream);
                response.setContentType("application/ms-excel");
                response.setContentLength(outArray.length);
                response.setHeader("Expires:", "0");
                response.setHeader("Content-Disposition", "attachment; filename=" + title + "-" + timeStamp + ".xls");
                OutputStream outStream = response.getOutputStream();
                outStream.write(outArray);

            } else {
                logger.info("[downloadLargeFile] : File type not found.");
            }

        } catch (Exception ex) {
            logger.info(ex.getMessage());
        } finally {
            try {
                outByteStream.close();
                outByteStream.flush();
            } catch (IOException ioe) {
                logger.info("[downloadFile] Exception : " + ioe.getMessage());
            }

        }
    }

    @RequestMapping(value = "/getWards", method = {RequestMethod.POST})
    public @ResponseBody
    List<Wardmaster> getWards(HttpServletRequest request, String zone) {
        List<Wardmaster> wardlist = new ArrayList<Wardmaster>();
        try {
            wardlist = silvassaService.getWards(zone);
        } catch (Exception e) {
            logger.info(e.getMessage());
        }

        return wardlist;
    }

    @RequestMapping(value = "/getSubLocality", method = {RequestMethod.POST})
    public @ResponseBody
    List<SubLocality> getSubLocality(HttpServletRequest request, String ward) {
        List<SubLocality> wardlist = new ArrayList<SubLocality>();
        try {
            wardlist = silvassaService.getSubLocality(ward);
        } catch (Exception e) {
            logger.info(e.getMessage());
        }

        return wardlist;
    }
//  Added By Jay

    @RequestMapping(value = "/jobAllocation")
    public String jobAllocation(HttpServletRequest request, Model model) {
        return "jobAllocation";
    }

    @RequestMapping(value = "/getImageName", method = {RequestMethod.GET, RequestMethod.POST})
    @ResponseBody
    public UserRegistration getImageName(String proId) throws IOException {
        String encodedString = "";
        UserRegistration doc = null;
        try {
            doc = silvassaService.showImage(proId);

        } catch (Exception ex) {

        } finally {
        }
        return doc;
    }

    @RequestMapping(value = "/getBase64Image", method = {RequestMethod.GET, RequestMethod.POST})
    @ResponseBody
    public String getBase64Image(String id) throws IOException {
        String encodedString = "";
        try {
            UserRegistration doc = silvassaService.getImage(id);
            byte[] fileBytes = Base64.encodeBase64(doc.getImageData());
            //System.out.println("cccc "+fileBytes.length);
            if (fileBytes.length < 1) {
                throw new IOException();
            }
            encodedString = new String(fileBytes);
        } catch (Exception ex) {
            encodedString = "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";
        } finally {
        }
        return encodedString;
    }

    @RequestMapping(value = "/getUsers", method = RequestMethod.POST)
    @ResponseBody
    public List<Usermaster> userList(HttpServletRequest request, Model model) {
        List<Usermaster> userList = null;
        Usermaster dto = new Usermaster();
        userList = silvassaService.userList();
        return userList;
    }
    //  Added By Jay End

    @RequestMapping(value = "/assessMentList", method = {RequestMethod.GET, RequestMethod.POST})
    public @ResponseBody
    void assessMentList(HttpServletRequest request, String zone, String ward, String prop_id_input, String occ_name, String ownerid, String locality, String src_aadhar_no, String category,
            String Phone_No, String Locality, String windowId, String type, String title, HttpServletResponse response) {
        List<PropertyAssessmentBean> taxList = null;

        System.out.println("Ashok list ggg" + request.getParameter("ward"));
        System.out.println("Ashok list ggg" + ward);
        System.out.println("Ashok list ggg" + prop_id_input);
        JSONObject taxObj = new JSONObject();
        taxObj.put("zoneid", zone);
        taxObj.put("wardid", ward);
        taxObj.put("prop_id", prop_id_input);
        taxObj.put("occ_name", occ_name);
        taxObj.put("ownerid", ownerid);
        taxObj.put("locality", locality);
        taxObj.put("src_aadhar_no", src_aadhar_no);
        taxObj.put("category", category);
        taxObj.put("Phone_No", Phone_No);
        taxObj.put("Locality", Locality);

        ByteArrayOutputStream outByteStream = new ByteArrayOutputStream();

        response.addCookie(new Cookie("windowId", windowId));
        try {

            long ts = System.currentTimeMillis();
            String timeStamp = (String.valueOf(ts)).replaceAll(" ", "_");

            //String[] attrToAddArr = new String[]{"oldMc","uniqueId", "owner_Father_Contac_Email", "occupier","address","pf_electric_con_num","floorName","builtupArea","buildingUse","propCat","constructionType","floorSelfRent","propertyClass","rentableValue","anuualRatableValue","multiplicatioFactor","floorWiseTax","tax","arrearAmount"};
            // String[] tHeadArr = new String[]{ "SMC House Property No.", "Permanent PropertyId", "owner\nFather\nHusband Name\nContact No\nE-Mail","Occuper\nFather\nHusband Name","Postal Addess","Electric Meter K No","Floor","FloorWise\ncovered\nBuiltUpARea\n(Sq.Ft.)","Use of \nthe Property"," Property Category"," Type of Construction","Self_Rent"," Location classess","Annual Rent \nper sq ft per annum","Annual\nRatable value","Property Tax Rate","Annual Property Tax","Total Property Tax","Arrear upto 2017-18"};
            String[] attrToAddArr = new String[]{"oldMc", "uniqueId", "owner_Father_Contac_Email", "occupier", "address", "pf_electric_con_num", "floorName", "builtupArea", "buildingUse", "propCat", "constructionType", "floorSelfRent", "propertyClass", "rentableValue", "anuualRatableValue", "multiplicatioFactor", "arrearAmount", "oldTax", "floorWiseTax", "tax", "correctionRemarks"};
            String[] tHeadArr = new String[]{"SMC House Property No.", "Permanent Property Id", "Owner Name or Holder Name/ Father or Husband Name / Contact No. / E-Mail", "Occupier Name / Father or Husband Name / Contact No. / E-Mail", "Postal Address", "Electric Service Connection No.", "Floor", "Floorwise covered / Built up area(Sq.Ft.)", "Use of Property", "Property Sub Category", " Type of Construction", "Self_Rent", " Location Class", "Presumed Annual Rent per sq.ft. per annum", "Annual Ratable Value", "Property Tax Rate", "Arrear upto March-2019", "Old Property Tax(Apri-2019 to Sept-2019)", "New Property Tax(October-2019 to March-2020)", "Total Annual Property Tax", "Objection Application No. / Remarks"};
            taxList = silvassaService.viewAssessmentList(taxObj.toJSONString());

            String htmlStr = mMIUtil.convertAssementBeanToHTMLString(taxList, attrToAddArr, tHeadArr).toString();

//            if (title == null || title.equals("")) {
//                String title = "AssessmentList";
//            }
            type = "EXCEL";
            if (type.equalsIgnoreCase("PDF")) {
                htmlStr = "<html><body> " + htmlStr + "</body></html>";

                byte[] outArray = mMIUtil.convertHtmlToPDF(title, htmlStr, outByteStream);
                response.setContentType("application/pdf");
                response.setContentLength(outArray.length);
                response.setHeader("Expires:", "0");
                response.setHeader("Content-Disposition", "attachment; filename=" + title + "-" + timeStamp + ".pdf");
                OutputStream outStream = response.getOutputStream();
                outStream.write(outArray);

            } else if (type.equalsIgnoreCase("EXCEL")) {
                title = ward + " " + title;
                byte[] outArray = mMIUtil.convertHtmlToExcel(title, htmlStr, outByteStream);
                response.setContentType("application/vnd.ms-excel");
                response.setContentLength(outArray.length);
                response.setHeader("Expires:", "0");
                response.setHeader("Content-Disposition", "attachment; filename=" + title + "-" + timeStamp + ".xls");
                OutputStream outStream = response.getOutputStream();
                outStream.write(outArray);
                //System.out.println("last");

            } else {
                logger.info("[downloadLargeFile] : File type not found.");
            }

        } catch (Exception ex) {
            logger.info(ex.getMessage());
        } finally {
            try {
                outByteStream.close();
                outByteStream.flush();
            } catch (IOException ioe) {
                logger.info("[downloadFile] Exception : " + ioe.getMessage());
            }

        }

    }
////Added by Avnish

    @RequestMapping(value = "/getChartData", method = {RequestMethod.POST})
    public @ResponseBody
    List getChartData(HttpServletRequest request, String frmDate, String toDate) {
        List chartData = new ArrayList<>();
        Map<String, Object> mp = new HashMap<>();
        try {
            chartData = silvassaService.getChartData();
            mp = correctionDAO.getCorrectionCountFilter(frmDate, toDate);
            chartData.add(mp);
        } catch (Exception e) {
            logger.info(e.getMessage());
        }

        return chartData;
    }

    @RequestMapping(value = "/getWardWiseData", method = RequestMethod.POST)
    public @ResponseBody
    Object getWardWiseData(HttpServletRequest request, String ward) {
        List dataList = null;
        try {
            dataList = silvassaService.getWardWiseData(ward);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return dataList;

    }

    @RequestMapping(value = "/getcomplaintWiseData", method = RequestMethod.POST)
    public @ResponseBody
    Object getcomplaintWiseData(HttpServletRequest request, String fromDate, String todate, String cmpType) {
        List dataList = null;
        try {
            dataList = correctionDAO.getCorrectionList(fromDate, todate, cmpType);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return dataList;

    }

    @RequestMapping(value = "/getZoneWardMaster", method = {RequestMethod.POST})
    public @ResponseBody
    Map<String, String> getZoneWardMaster(HttpServletRequest request) {
        return silvassaService.getZoneWardMaster();
    }

    @RequestMapping(value = "/getPropertyIds", method = {RequestMethod.POST})
    public @ResponseBody
    List<Map> getpropertys(HttpServletRequest request, String searchStr) {
        List<Map> ls = new ArrayList<Map>();
        try {
            ls = silvassaService.getPropertys(searchStr);
        } catch (Exception e) {
            logger.info(e.getMessage());
        }
        return ls;
    }

    @RequestMapping(value = "/getPhoneNos", method = {RequestMethod.POST})
    public @ResponseBody
    List<Map> getPhoneNos(HttpServletRequest request, String searchStr) {
        List<Map> ls = new ArrayList<Map>();
        try {
            ls = silvassaService.getPhoneNos(searchStr);
        } catch (Exception e) {
            logger.info(e.getMessage());
        }
        return ls;
    }

    @RequestMapping(value = "/getCityCodes", method = {RequestMethod.POST})
    public @ResponseBody
    List<Map> getCityCodes(HttpServletRequest request, String searchStr) {
        List<Map> ls = new ArrayList<Map>();
        try {
            ls = silvassaService.getCityCodes(searchStr);
        } catch (Exception e) {
            logger.info(e.getMessage());
        }
        return ls;
    }

    @RequestMapping(value = "/getaadharNo", method = {RequestMethod.POST})
    public @ResponseBody
    List<Map> getaadharNo(HttpServletRequest request, String searchStr) {
        List<Map> ls = new ArrayList<Map>();
        try {
            ls = silvassaService.getaadharNo(searchStr);
        } catch (Exception e) {
            logger.info(e.getMessage());
        }
        return ls;
    }

    @RequestMapping(value = "/getsubLocality", method = {RequestMethod.POST})
    public @ResponseBody
    List<Map> getsubLocality(HttpServletRequest request, String searchStr) {
        List<Map> ls = new ArrayList<Map>();
        try {
            ls = silvassaService.getsubLocality(searchStr);
        } catch (Exception e) {
            logger.info(e.getMessage());
        }
        return ls;
    }

    @RequestMapping(value = "/getWardlst", method = {RequestMethod.POST})
    public @ResponseBody
    List<Map> getWardlst(HttpServletRequest request, String searchStr) {
        List<Map> ls = new ArrayList<Map>();
        try {
            ls = silvassaService.getWardlst(searchStr);
        } catch (Exception e) {
            logger.info(e.getMessage());
        }
        return ls;
    }

    @RequestMapping(value = "/getOwnerlst", method = {RequestMethod.POST})
    public @ResponseBody
    List<Map> getOwnerlst(HttpServletRequest request, String searchStr) {
        List<Map> ls = new ArrayList<Map>();
        try {
            ls = silvassaService.getOwnerlst(searchStr);
        } catch (Exception e) {
            logger.info(e.getMessage());
        }
        return ls;
    }

    @RequestMapping(value = "/getOccupierlst", method = {RequestMethod.POST})
    public @ResponseBody
    List<Map> getOccupierlst(HttpServletRequest request, String searchStr) {
        List<Map> ls = new ArrayList<Map>();
        try {
            ls = silvassaService.getOccupierlst(searchStr);
        } catch (Exception e) {
            logger.info(e.getMessage());
        }
        return ls;
    }

    @RequestMapping(value = "/getLocalitylst", method = {RequestMethod.POST})
    public @ResponseBody
    List<Map> getLocalitylst(HttpServletRequest request, String searchStr) {
        List<Map> ls = new ArrayList<Map>();
        try {
            ls = silvassaService.getLocalitylst(searchStr);
        } catch (Exception e) {
            logger.info(e.getMessage());
        }
        return ls;
    }

    @RequestMapping(value = "/getPropertyTypeLst", method = {RequestMethod.POST})
    public @ResponseBody
    List<Map> getPropertyTypeLst(HttpServletRequest request, String searchStr) {
        List<Map> ls = new ArrayList<Map>();
        try {
            ls = silvassaService.getPropertyTypeLst(searchStr);
        } catch (Exception e) {
            logger.info(e.getMessage());
        }
        return ls;
    }

    @RequestMapping(value = "/getHouseLst", method = {RequestMethod.POST})
    public @ResponseBody
    List<Map> getHouseLst(HttpServletRequest request, String searchStr) {
        List<Map> ls = new ArrayList<Map>();
        try {
            ls = silvassaService.getHouseLst(searchStr);
        } catch (Exception e) {
            logger.info(e.getMessage());
        }
        return ls;
    }

    @RequestMapping(value = "/locateOnMap", method = RequestMethod.GET)
    public String locateOnMap(HttpServletRequest request, Model model) {
        return "locateOnMap";
    }

    @RequestMapping(value = "/getGeomData", method = {RequestMethod.POST})
    public @ResponseBody
    List getGeomData(HttpServletRequest request,
            @RequestParam("type") String type,
            @RequestParam("datalist") String datalist) {
        JSONObject jo = new JSONObject();
        List  geometry = null;
        try {
           geometry =  silvassaService.getGeoMetry(type, datalist);
           jo.put("geometry", geometry);
//            //propertyDetailslist = silvassaService.showPropertyDetails(type, property_id, owner_name, occ_name, ward, locality, aadhar_num, category, prop_id_input, phone_no, sub_locality, houseNo);
//            jo.put("propertyArr", propertyDetailslist);
//
//            if (propertyDetailslist.size() > 0) {
//                Set st = new HashSet();
//                for (PropertyDetails pd : propertyDetailslist) {
//                    st.add(pd.getPropertyUniqueId());
//                }
//                List<TAXDetailBean> taxDetailArr = silvassaService.searchTAXAmount(st, MMIUtil.getCurrentFinancialYear());
//                jo.put("taxDetailArr", taxDetailArr);
//            }

        } catch (Exception e) {
            logger.info(e.getMessage());
        }
        return geometry;
    }
}
