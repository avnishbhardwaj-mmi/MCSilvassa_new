$(document).ready(function () {
    $("#property_main_menu").addClass("active");

    $("#propertyid").SumoSelect({csvDispCount: 3, search: true, searchText: 'Enter here.'});
    $("#ownerid").SumoSelect({csvDispCount: 3, search: true, searchText: 'Enter here.'});
    $("#occ_name").SumoSelect({csvDispCount: 3, search: true, searchText: 'Enter here.'});
    $("#ward").SumoSelect({csvDispCount: 3, search: true, searchText: 'Enter here.'});
    $("#locality").SumoSelect({csvDispCount: 3, search: true, searchText: 'Enter here.'});

    HomeClass.resetOccupier();
    HomeClass.resetOwner();
    HomeClass.resetPropertyId();
    HomeClass.resetAadhar();
    HomeClass.resetLocality();
    HomeClass.resetPropertyCategory();

    $("#src_aadhar_no").addClass("disable_cls");
    $("#src_aadhar_no").prop("disabled", true);

    HomeClass.getWard();
    $.post("loadZones", {}, function (result) {
        if (result != undefined) {
            var html = "";
            html += "<option value='-1'>--Select Zone--</option>";
            for (var i = 0; i < result.length; i++) {
                html += "<option value='" + result[i].zoneId + "'>" + result[i].zoneName + "</option>";
                HomeClass.zoneMaster[result[i].zoneId] = result[i].zoneName;
            }

            $("#zone").html(html);
            $("#zone").SumoSelect({csvDispCount: 3, search: true, searchText: 'Enter here.'});
        }
    }, 'json').always(function () {
    });
});


PropertyView = {
    toggleOwner: function () {
        $("#ownerDetails").slideToggle();
    },
    toggleOther: function () {
        $("#otherPersonel").slideToggle();
    },
    toggleAddress: function () {
        $("#property_address").slideToggle();
    },
    togglePropDetails: function () {
        $("#property_details").slideToggle();
    },
    toggleCivic: function () {
        $("#civic_amen").slideToggle();
    },
    toggleTaxdetails: function () {
        $("#tax_details").slideToggle();
    }

}


HomeClass = {
    zoneMaster: {},
    editflag: false,
    propertyObject: null,
    getSearchCriteria: function () {



        var zone_id = $("#zone").val();
        var ward = $("#ward").val();
        var locality = $("#locality").val();

        if (zone_id === "-1") {

        } else if (ward === "-1") {

        } else {


            LOADER.show();
            $.post("getSearchCriteria", {zone_id: zone_id, ward: ward, locality: locality}, function (result) {

                if (result != undefined) {

                    var pr = result.prop.propIdArr;
                    var own = result.prop.ownerArr;
                    var occ = result.prop.occupierArr;
                    var propCat = result.prop.propertyCategoryArr;
                    var html_prop = "";
                    html_prop += "<option value='-1'>--Select Property ID--</option>";
                    for (var key in pr) {
                        html_prop += "<option value='" + key + "'>" + pr[key].property_unique_id + "</option>";
                    }
                    var html_own = "";
                    html_own += "<option value='-1'>--Select Owner Name--</option>";

                    for (var key in own) {

                        if (own[key].property_owner != ''
                                && own[key].property_owner != ' N\\A'
                                && own[key].property_owner != ' ') {

                            html_own += "<option value='" + key
                                    + "'>"
                                    + own[key].property_owner
                                    + "</option>";
                        }
                    }

                    var html_occ = "";
                    html_occ += "<option value='-1'>--Select Occupier Name--</option>";

                    for (var key in occ) {
                        // alert(prop[key].propertyOwner);
                        if (occ[key].property_occupier_name != '' && occ[key].property_occupier_name != ' N\\A'
                                && occ[key].property_occupier_name != ' ' && occ[key].property_occupier_name != 'SELF') {
                            html_occ += "<option value='" + key + "'>" + occ[key].property_occupier_name + "</option>";
                        }
                    }

                    var html_prop_category = "";
                    html_prop_category += "<option value='-1'>--Select Property Type Name--</option>";

                    for (var key in propCat) {
                        // alert(prop[key].propertyOwner);
                        if (propCat[key].property_category_name != '' && propCat[key].property_category_name != ' N\\A'
                                && propCat[key].property_category_name != ' ') {
                            html_prop_category += "<option value='" + propCat[key].property_category_name + "'>" + propCat[key].property_category_name + "</option>";
                        }
                    }
                    // ashok
                    HomeClass.resetOccupier(html_occ);
                    HomeClass.resetOwner(html_own);
                    HomeClass.resetPropertyId(html_prop);
                    HomeClass.resetAadhar();
                    HomeClass.resetPropertyCategory(html_prop_category);
                    LOADER.hide();

                }
            });
        }

    },
    taxDetailMap: {},
    preserveTAXDetails: function (detailArr) {
        HomeClass.taxDetailMap = {};
        if (detailArr === undefined || detailArr === null) {

        } else {
            for (var dt in detailArr) {
                var det = detailArr[dt];
                if (det != null) {
                    HomeClass.taxDetailMap[det.propertyId] = det;
                }
            }
        }

    },
    propertyMap: {},
    preservePropertydetails: function (detailArr) {
        HomeClass.propertyMap = {};
        if (detailArr === undefined || detailArr === null) {

        } else {
            for (var dt in detailArr) {
                var det = detailArr[dt];
                if (det != null) {
                    HomeClass.propertyMap[det.propertyUniqueId] = det;
                }
            }
        }

    },
    searchPropertyDetails: function () {

        //var zone_id = $("#zone option:selected").val();
        var zone_id = "";
        var prop_id_input = $("#prop_id_input").val();
        var ward = $("#ward").val();
        debugger;
        var property_id = "";
        var occ = $("#occ_name").val();
        var owner_id = $("#ownerid").val();
        var locality = $("#locality").val();
        var aadhar_num = $("#src_aadhar_no").val();
        var propertyCategory = $("#category").val();
        //newly added
        var Phone_no = $("#Phone_No").val();
        var Locality = $("#Locality").val();

//        if (zone_id === "-1" ) {
//            alert("Kindly provide zone.");
//        } else 


        if (prop_id_input == "" && ward == "" && occ == "" && occ == "" && owner_id == "" && locality == "" && aadhar_num == "" && propertyCategory == "" && Phone_no == "" && Locality == "") {
            // alert("Please select atleast one filter");
                 var hMsg = "Please select at least one filter"; 
           //alert("");
           $('#validateid').fadeIn( 400 ).html(hMsg).delay( 5000 ).fadeOut( 400 );
        } else {


            /*        if (ward === "-1" && (prop_id_input == null || prop_id_input == "" || prop_id_input.trim() == "")) {// zone not selected,property id is entered
             alert("Kindly provide ward or Property Id..");
             } else {
             
             if (prop_id_input != null && prop_id_input != "" && prop_id_input.trim() != "") {
             
             } else {
             if ($("#propertyid").val() != "-1") {
             property_id = $("#propertyid option:selected").text();
             }
             if ($("#ownerid").val() != "-1") {
             owner_id = $("#ownerid option:selected").text();
             }
             if ($("#occ_name").val() != "-1") {
             occ = $("#occ_name option:selected").text();
             }
             if ($("#locality").val() != "-1") {
             locality = $("#locality option:selected").text();
             }
             if ($("#src_aadhar_no").val() != "") {
             aadhar_num = $("#src_aadhar_no").val();
             }
             if ($("#category").val() != "") {
             propertyCategory = $("#category option:selected").val();
             }
             }
             }*/


            LOADER.show();
            $.post("searchPropertyDetails",
                    {
                        zone_id: zone_id,
                        ward: ward,
                        property_id: property_id,
                        owner_name: owner_id,
                        occ_name: occ,
                        locality: locality,
                        aadhar_num: aadhar_num,
                        category: propertyCategory,
                        prop_id_input: prop_id_input,
                        phone_no: Phone_no,
                        sub_locality: Locality,
                    }, function (data) {

                var propArr = data.propertyArr;

                HomeClass.preserveTAXDetails(data.taxDetailArr);
                HomeClass.preservePropertydetails(data.propertyArr);

                if (propArr !== undefined && propArr != null) {
                    HomeClass.openSearchResults();

                    var szonename = "";
                    var sproperty_id = "";
                    var sow_name = "";
                    var occupier_name = "";

                    if ($("#zone option:selected").val() !== "-1") {
                        szonename = "{Zone : " + ($("#zone option:selected").text() === "--Select Zone--" ? " - " : $("#zone option:selected").text()) + "} ";
                    }
                    if ($("#propertyid option:selected").val() !== "-1") {
                        sproperty_id = "{Property : " + ($("#propertyid option:selected").text() === "--Select Property ID--" ? " - " : $("#propertyid option:selected").text()) + "} ";
                    }
                    if ($("#ownerid option:selected").val() !== "-1") {
                        sow_name = "{Owner : " + ($("#ownerid option:selected").text() === "--Select Owner Name--" ? " - " : $("#ownerid option:selected").text()) + "} ";
                    }
                    if ($("#occ_name option:selected").val() !== "-1") {
                        occupier_name = "{Occupier : " + ($("#occ_name option:selected").text() === "--Select Occupier Name--" ? " - " : $("#occ_name option:selected").text()) + "}";
                    }

                    var srch = "Showing results for [" + szonename + sproperty_id + sow_name + occupier_name + "]";
                    $("#searchHeader").html(srch);
                    var tab_html = "";
                    tab_html += "<thead>"
                            + "<tr >"
                            + "<th>S.No.</th>"
                            + "<th>Property ID</th>"
                            + "<th>Owner Name</th>"
                            + "<th>Occupier Name</th>"
                            //+ "<th>Relation With Owner</th>"
                            + "<th>Address</th>"
                            //+ "<th>Category</th>"
                            //  + "<th>Zone</th>"
                            + "<th>Ward</th>"

                            + "<th>Tax Generated</th>"
                            + "<th>Tax Amount</th>"
                            + "<th>Notice Generated</th>"
                            + "<th>Property Picture</th>"
//                            + "<th>View On Map</th>"
                            + "</tr></thead>";
                    tab_html += "<tbody>";// total headers 14
                    for (var i = 0; i < propArr.length; i++) {
                        var prop = propArr[i];

                        prop.propertyUniqueId = (prop.propertyUniqueId === undefined || prop.propertyUniqueId === null) ? "" : prop.propertyUniqueId;
                        prop.propertyOccupierName = (prop.propertyOccupierName === undefined || prop.propertyOccupierName === null) ? "" : prop.propertyOccupierName;
                        prop.propertyRelationOwner = (prop.propertyRelationOwner === undefined || prop.propertyRelationOwner === null) ? "" : prop.propertyRelationOwner;
                        prop.propertyPlotArea = (prop.propertyPlotArea === undefined || prop.propertyPlotArea === null) ? "" : prop.propertyPlotArea;
                        prop.propertyOwner = (prop.propertyOwner === undefined || prop.propertyOwner === null) ? "" : prop.propertyOwner;
                        prop.propertyHouseNo = (prop.propertyHouseNo === undefined || prop.propertyHouseNo === null) ? "" : prop.propertyHouseNo;
                        prop.propertyPincode = (prop.propertyPincode === undefined || prop.propertyPincode === null) ? "" : prop.propertyPincode;
                        prop.propertyBuildingName = (prop.propertyBuildingName === undefined || prop.propertyBuildingName === null) ? "" : prop.propertyBuildingName;
                        prop.zoneId = (prop.zoneId === undefined || prop.zoneId === null) ? "" : prop.zoneId;
                        prop.ward = (prop.ward === undefined || prop.ward === null) ? "" : prop.ward;
                        prop.propertySublocality = (prop.propertySublocality === undefined || prop.propertySublocality === null) ? "" : prop.propertySublocality;
                        prop.propertyLandmark = (prop.propertyLandmark === undefined || prop.propertyLandmark === null) ? "" : prop.propertyLandmark;
                        prop.completeAddress = (prop.completeAddress === undefined || prop.completeAddress === null) ? "" : prop.completeAddress;

                        if (propArr.length == 1) {
                            HomeClass.showProperty(prop.propertyUniqueId);
                        }
                        tab_html += "<tr><td>" + (parseInt(i) + 1) + "</td>";// '+prop.propertyUniqueId+'
                        tab_html += "<td title='Click here to view more detail' onclick=\"HomeClass.showProperty('"
                                + prop.propertyUniqueId + "')\" ><a href='javascript: void(0)'>"
                                + prop.propertyUniqueId + "</a> </td>";

                        tab_html += "<td >" + prop.propertyOwner + "</td>";
                        tab_html += "<td >" + prop.propertyOccupierName + "</td>";
                        // tab_html += "<td >" + prop.propertyRelationOwner + "</td>";
                        tab_html += "<td>" + (prop.completeAddress === undefined ? "" : prop.completeAddress) + "</td>";
                        //  tab_html += "<td>" + (HomeClass.zoneMaster[prop.zoneId] === undefined ? "N/A" : HomeClass.zoneMaster[prop.zoneId]) + "</td>";
                        tab_html += "<td>" + (HomeClass.wardMaster[prop.ward] === undefined ? "N/A" : HomeClass.wardMaster[prop.ward]) + "</td>";

                        if (HomeClass.taxDetailMap[prop.propertyUniqueId] === undefined) {
                            tab_html += "<td>No</td>";
                            tab_html += "<td>No</td>";
                            tab_html += "<td>No</td>";
                        } else {
                            tab_html += "<td>Yes</td>";
                            tab_html += "<td>" + (HomeClass.taxDetailMap[prop.propertyUniqueId].grandTotal == undefined ? '' : HomeClass.taxDetailMap[prop.propertyUniqueId].grandTotal) + " </td>";
                            if (HomeClass.taxDetailMap[prop.propertyUniqueId].noticeGenerated === "Y") {
                                tab_html += "<td>Yes</td>";
                            } else {
                                tab_html += "<td>No</td>";
                            }
                        }
                        tab_html += "<td style='cursor:pointer;' onclick=\"HomeClass.showImage('" + prop.propertyUniqueId + "')\" ><a href='javascript: void(0)'>" + " View</a> </td>";
//                        tab_html += "<td><a href='javascript: void(0)' >View</a></td>";
                        tab_html += "</tr>";
                    }

                    tab_html += "</tbody>";
                    $('#property_tab').html(tab_html);


//                    $.extend(true, $.fn.dataTable.defaults, {
//                        "searching": false
//                    });
                    $('#property_tab').DataTable({
                        "dom": 'Bfrtip',
                        "buttons": [],
                        "lengthMenu": [[10, 25, 50, -1], [10, 25, 50, "All"]],
                        "bDestroy": true,
                        "responsive": true,
                        "paging": true,
                        // "scrollY":200,
                        /* "scrollX":true, */
                        "sPaginationType": "full_numbers"
                    });
                    $('.dataTables_wrapper table').wrap('<div class="scroll_table"></div>');

                } else {
                    alert("No results found");
                    return false;
                }

            }, 'json').always(function () {
                LOADER.hide();
//                $("#property_tab_paginate").css({"position": "absolute", "top": "0", "right": "0"});
//                $("#property_tab_filter").css({"float": "left"});

            });
            return true;
        }

    },
    searchUniqueID: function () {

        var prop_unique_id = $("#prop_det_id").val();
        HomeClass.searchProperty(prop_unique_id);

    },
    disableNotice: function () {
        $("#show_notice").addClass("disabled");
        $('#prop_notice_status').text("No");
        $("#show_notice").attr("href", "#");
    },
    enableNotice: function (url) {
        $("#show_notice").removeClass("disabled");
        $("#show_notice").attr("href", url);
        $('#prop_notice_status').text("Yes");
    },
    showProperty: function (property_unique_id) {
        LOADER.show();
        $.post("showPropertyDetails", {property_unique_id: property_unique_id}, function (result) {

            if (result.floorDetails !== undefined && result.floorDetails.length > 0) {
                $("#accordion").removeClass("hidden");
                $("#no_result_found").addClass("hidden");
                HomeClass.openPropertyWindow();

                var floorList = result.floorDetails;
                var prop = HomeClass.propertyMap[property_unique_id];

                HomeClass.propertyObject = prop;

                $("#prop_det_id").val("");
                $("#prop_unique_id").val(prop.propertyUniqueId);
                // $("#prop_id").val(prop.property_id);
                $("#prop_zone_id").val(HomeClass.zoneMaster[prop.zoneId] === undefined ? "N/A" : HomeClass.zoneMaster[prop.zoneId]);
                $("#prop_ward_id").val(HomeClass.wardMaster[prop.ward] === undefined ? "N/A" : HomeClass.wardMaster[prop.ward]);
                $("#prop_house_no").val(prop.propertyHouseNo);
                // $("#prop_cam_id").val(prop.propertyCameraNum);
                $("#prop_plot_id").val(prop.propertyPlotNum);
                $("#prop_building_id").val(prop.propertyBuildingName);
                $("#prop_sub_locality_id").val(prop.propertySublocality);
                $("#prop_landmark_id").val(prop.propertyLandmark);
                $("#property_pincode").val(prop.propertyPincode);
                $("#prop_locality_id").val(prop.propertyLocality);
                // $("#prop_photo_id").val(prop.propertyPhotoId);
                $("#prop_cost_id").val(prop.propertyCost);
                $("#prop_ownername_id").val(prop.propertyOwner);
                $("#prop_owner_mobile").val(prop.propertyContact);
                $("#prop_father_id").val(prop.propertyOwnerFather);
                $("#prop_owner_spouse").val(prop.propertyOwnerSpouse);
                $("#prop_occupier_name").val(prop.propertyOccupierName);
                $("#prop_relation_id").val(prop.propertyRelationOwner);
                $("#prop_owner_adhaar").val(prop.propertyAadharNum);
                $("#prop_owner_email").val(prop.propertyOwnerEmail);

                /** ************************************************** */
                $("#owner_floors_id").val(prop.propertyTotalFloor);
                $("#owner_rooms_id").val(prop.propertyNumOfRooms);
                $("#property_road").val(prop.propertyRoad);
                $("#prop_floor_num_of_person").val(prop.propertyNumOfPersons);
                // $("#floor_type_desc").val(prop.floor_type_desc);
                $("#prop_floor_plot_area").val(prop.propertyPlotArea);
                // $("#prop_floor_num_of_rooms").val(prop.property_num_of_rooms);
                // $("#prop_floor_num_of_person").val(prop.prop_floor_num_of_person);
                $("#male_gt18").val(prop.propertyMale18Plus);
                $("#female_gt18").val(prop.propertyFem18Plus);
                $("#male_lt18").val(prop.propertyMale18Minus);
                $("#female_lt18").val(prop.propertyFem18Minus);
                $("#name1").val(prop.propertyName1);
                $("#name2").val(prop.propertyName2);
                $("#name3").val(prop.propertyName3);
                $("#name4").val(prop.propertyName4);
                $("#name5").val(prop.propertyName5);
                $("#name6").val(prop.propertyName6);
                $("#prop_floor_room_cnt_hotel").val(prop.propertyRoomCntHotel);
                $("#prop_floor_room_cnt_school_clg").val(prop.propertyRoomCntSchoolClg);
                $("#prop_floor_room_cnt_hostel").val(prop.propertyRoomCntHostel);
                $("#prop_floor_room_cnt_hospi_nurse").val(prop.propertyRoomCntHospiNurse);

                if (prop.propertyHouseNo == undefined || prop.propertyHouseNo == '') {
                    $("#property_plot_num").val((prop.propertyPlotNum == undefined ? '' : prop.propertyPlotNum));
                } else {
                    $("#property_plot_num").val(prop.propertyHouseNo);
                }


                $("#property_survey_num").val(prop.propertySurveyNum);
                $("#property_status_name").val(prop.propertyStatus);

                var taxdetails = HomeClass.taxDetailMap[prop.propertyUniqueId];

                if (taxdetails === undefined) {
                    taxdetails = {};
                    taxdetails.noticeGenerated = "N/A";
                    taxdetails.taxNo = "N/A";
                    taxdetails.grandTotal = "N/A";
                    taxdetails.financialYear = "N/A";
                    taxdetails.objectionStatus = "N/A";
                }

                if (taxdetails.noticeGenerated === "Y") {
                    var url = location.href.substring(0, location.href.lastIndexOf('/')) + "/generatePrivateNotice?zoneId=-1&ward=-1&propertyId=" + prop.propertyUniqueId;
                    //var url = location.href.substring(0, location.href.lastIndexOf('/')) + "/viewNotice?taxNo=" + taxdetails.taxNo;
                    HomeClass.enableNotice(url);
                } else {
                    HomeClass.disableNotice();
                }

//                if (taxdetails.objectionStatus !== "") {
//                    $('#prop_notice_status').text("Yes");
//                } else {
//                    $('#prop_notice_status').text("No");
//                }

                $("#prop_tax_amount").text(taxdetails.grandTotal);
                $("#prop_asst_yr").text(taxdetails.financialYear);
                $("#prop_obj_status").text(taxdetails.objectionStatus);

                if (HomeClass.taxDetailMap[prop.propertyUniqueId] === undefined) {
                    $('#prop_tax_status').text("No");
                } else {
                    $('#prop_tax_status').text("Yes");
                }

                $("#prop_tax_paid_amount").text(taxdetails.advancePaidAmount);
                $("#prop_tax_amount_payable").text(taxdetails.payableAmount);



                /** ******************************************* */

                var fl = "";
                fl += "<thead><tr><th >Sno.</th><th >Floor</th><th >BuiltUp Area</th><th >Constr. Type</th><th >Property Use</th>"
                        + "<th >Property Category</th><th >Water Conn.</th><th >Sewage Conn.</th><th >Electric Meter No.</th><th >Electric Conn. No.</th>"
                        + "<th >CCTV</th><th >Fire Equipment</th><th >Lift Available</th><th >RainWater Harvesting</th>"
                        + "<th >No. of Borewells</th><th >Sanitation</th><th >Hoarding</th><th >Mobile tower</th></tr></thead>";
                fl += "<tbody>";
                for (var j = 0; j < floorList.length; j++) {

                    var dt = floorList[j];
                    //console.log(dt);
                    dt.pfFloorName = dt.pfFloorName === undefined ? "" : dt.pfFloorName;
                    dt.pfBuiltupArea = dt.pfBuiltupArea === undefined ? "" : dt.pfBuiltupArea;
                    dt.pfConstructionType = dt.pfConstructionType === undefined ? "" : dt.pfConstructionType;
                    dt.pfFloorwiseBuildUse = dt.pfFloorwiseBuildUse === undefined ? "" : dt.pfFloorwiseBuildUse;
                    dt.pf_property_subtype = dt.pf_property_subtype === undefined ? "" : dt.pf_property_subtype;
                    dt.pfWaterPipeCon = dt.pfWaterPipeCon === undefined ? "" : dt.pfWaterPipeCon;
                    dt.pfSewerageCon = dt.pfSewerageCon === undefined ? "" : dt.pfSewerageCon;
                    dt.pfElectricMeterNum = dt.pfElectricMeterNum === undefined ? "" : dt.pfElectricMeterNum;
                    dt.pfElectricConNum = dt.pfElectricConNum === undefined ? "" : dt.pfElectricConNum;
                    dt.pfCctvCamrea = dt.pfCctvCamrea === undefined ? "" : dt.pfCctvCamrea;
                    dt.pfFireEquipment = dt.pfFireEquipment === undefined ? "" : dt.pfFireEquipment;
                    dt.pfLiftAvailable = dt.pfLiftAvailable === undefined ? "" : dt.pfLiftAvailable;
                    dt.pfRainWaterHarvest = dt.pfRainWaterHarvest === undefined ? "" : dt.pfRainWaterHarvest;
                    dt.pfNumOfBorewells = dt.pfNumOfBorewells === undefined ? "" : dt.pfNumOfBorewells;
                    dt.pfSanitation = dt.pfSanitation === undefined ? "" : dt.pfSanitation;
                    dt.pfHordingAvail = dt.pfHordingAvail === undefined ? "" : dt.pfHordingAvail;
                    dt.pfMobileTower = dt.pfMobileTower === undefined ? "" : dt.pfMobileTower;
                    dt = UTIL.clearBean(dt);
                    fl += "<tr><td >" + (j + 1) + "</td>";
                    fl += "<td >" + dt.pfFloorName + "</td>";
                    fl += "<td >" + dt.pfBuiltupArea + "</td>";
                    fl += "<td >" + dt.pfConstructionType + "</td>";
                    fl += "<td >" + dt.pfFloorwiseBuildUse + "</td>";
                    fl += "<td >" + dt.pf_property_subtype + "</td>";
                    fl += "<td >" + dt.pfWaterPipeCon + "</td>";
                    fl += "<td >" + dt.pfSewerageCon + "</td>";
                    fl += "<td >" + dt.pfElectricMeterNum + "</td>";
                    fl += "<td >" + dt.pfElectricConNum + "</td>";
                    fl += "<td >" + dt.pfCctvCamrea + "</td>";
                    fl += "<td >" + dt.pfFireEquipment + "</td>";
                    fl += "<td >" + dt.pfLiftAvailable + "</td>";
                    fl += "<td >" + dt.pfRainWaterHarvest + "</td>";
                    fl += "<td >" + dt.pfNumOfBorewells + "</td>";
                    fl += "<td >" + dt.pfSanitation + "</td>";
                    fl += "<td >" + dt.pfHordingAvail + "</td>";
                    fl += "<td >" + dt.pfMobileTower + "</td>";
                    fl += "</tr>";

                }
                fl += "</tbody>";
                $("#floorDet").html(fl);

            } else {
                $("#accordion").addClass("hidden");
                $("#no_result_found").removeClass("hidden");
                HomeClass.disableNotice();
                return false;
            }

        }, 'json').always(function () {
            LOADER.hide();
        });
    },
    openSearchResults: function () {
        $("#property_details_tab_div").addClass("hidden");
        $("#searchResults").removeClass("hidden");
        $("#property_section").addClass("hidden");
    },
    openSearchWindow: function () {
        $("#property_details_tab_div").addClass("hidden");
        $("#searchResults").addClass("hidden");
        $("#property_section").removeClass("hidden");
    },
    openPropertyWindow: function () {
        $("#property_details_tab_div").removeClass("hidden");
        $("#searchResults").addClass("hidden");
        $("#property_section").addClass("hidden");
    },
    nextImage: function () {
        var imgCount = HomeClass.imgArray.length;
        if ((HomeClass.currentImage + 1) < imgCount) {
            $("#img-next-btn").removeClass("disabled");
            $("#img-prev-btn").removeClass("disabled");
            HomeClass.currentImage += 1;
            $("#cntrimg").attr('src', 'data:image/jpg;base64,' + HomeClass.imgArray[HomeClass.currentImage]);
            if ((HomeClass.currentImage + 1) === imgCount) {
                $("#img-next-btn").addClass("disabled");
            }
        }

    },
    previousImage: function () {
        var imgCount = HomeClass.imgArray.length;
        if ((HomeClass.currentImage - 1) < imgCount && (HomeClass.currentImage) !== 0) {
            $("#img-next-btn").removeClass("disabled");
            HomeClass.currentImage -= 1;
            $("#cntrimg").attr('src', 'data:image/jpg;base64,' + HomeClass.imgArray[HomeClass.currentImage]);
            if ((HomeClass.currentImage) === 0) {
                $("#img-next-btn").removeClass("disabled");
                $("#img-prev-btn").addClass("disabled");
            }
        }

    },
    imgArray: [],
    currentImage: 0,
    showImage: function (property_unique_id) {

        LOADER.show();
        $("#img-prev-btn").addClass("disabled");
        $("#img-next-btn").addClass("disabled");
        $.post("getPropertyImage", {property_unique_id: property_unique_id}, function (data) {
            HomeClass.currentImage = 0;
            if (data.length > 0 && data !== "") {
                HomeClass.imgArray = data;
                if (HomeClass.imgArray.length > 1) {
                    $("#img-next-btn").removeClass("disabled");
                }
                $("#cntrimg").attr('src', 'data:image/jpg;base64,' + HomeClass.imgArray[0]);
                $("#help-modal").modal({backdrop: "static"});
                $("#help-modal").show();
            } else {
                alert("Property image not found.");
                $("#img_display").hide();
                $("#cntrimg").attr('src', '');
                return false;
            }

        }).always(function () {
            LOADER.hide();
        });
    },
    showMap: function (property_unique_id) {
        LOADER.show();
        $("#map_div").show();
        $.post("getPropertyMarker", {
            property_unique_id: property_unique_id
        }, function (data) {

            $("#cntrimg").attr('src', 'data:image/jpg;base64,' + data);
            $("#map-modal").modal({
                backdrop: "static"
            });
            $("#map-modal").show();
            LOADER.hide();
        });

    },
    enableEdit: function () {

        $("#updateProp").show();
        HomeClass.editflag = true;
        $("#property_smc_tax_payee").prop('disabled', false);
        $("#prop_house_no").prop('disabled', false);
        $("#prop_cam_id").prop('disabled', false);
        $("#property_plot_num").prop('disabled', false);
        $("#property_survey_num").prop('disabled', false);
        $("#prop_building_id").prop('disabled', false);
        $("#prop_locality_id").prop('disabled', false);
        $("#prop_sub_locality_id").prop('disabled', false);
        $("#prop_landmark_id").prop('disabled', false);
        $("#property_road").prop('disabled', false);
        $("#property_pincode").prop('disabled', false);
        // $("#property_usage_details").prop('disabled', false);
        // $("#property_age_of_building").prop('disabled', false);
        // $("#prop_cost_id").prop('disabled', false);
        // $("#prop_ownername_id").prop('disabled', false);
        // $("#prop_mobile_id").prop('disabled', false);
        // $("#prop_father_id").prop('disabled', false);
        // $("#prop_spouse_id").prop('disabled', false);
        // $("#prop_occupier_id").prop('disabled', false);
        // $("#prop_relation_id").prop('disabled', false);
        // $("#owner_adhar_id").prop('disabled', false);
        // $("#owner_email_id").prop('disabled', false);
        // $("#owner_floors_id").prop('disabled', false);
        // $("#owner_rooms_id").prop('disabled', false);
        //
        // $("#ca_pipe_id").prop('disabled', false);
        // $("#ca_sew_id").prop('disabled', false);
        // $("#ca_sew_no_id").prop('disabled', false);
        // $("#ca_elec_id").prop('disabled', false);
        // $("#ca_elec_no_id").prop('disabled', false);
        // $("#ca_ccCam_id").prop('disabled', false);
        // $("#ca_hording_id").prop('disabled', false);
        // $("#ca_fire_id").prop('disabled', false);
        // $("#ca_lift_id").prop('disabled', false);
        // $("#ca_borewell_id").prop('disabled', false);
        // $("#ca_mobiletower_id").prop('disabled', false);
        // $("#ca_rainharvest_id").prop('disabled', false);
        //		
        // $("#prop_floor_room_cnt_hotel").prop('disabled', false);
        // $("#prop_floor_room_cnt_school_clg").prop('disabled', false);
        // $("#prop_floor_room_cnt_hospi_nurse").prop('disabled', false);
        // $("#prop_floor_room_cnt_hotel").prop('disabled', false);
        // $("#floor_type_desc").prop('disabled', false);
        // $("#prop_floor_plot_area").prop('disabled', false);
        // $("#prop_floor_construction_type").prop('disabled', false);
        // $("#prop_floor_usage_type").prop('disabled', false);
        // $("#floor_type_desc").prop('disabled', false);
        // $("#sanitation_type_desc").prop('disabled', false);

    },
    updatePropertyDetails: function () {
        var r = confirm("Are you sure you want to Update Property Details");
        if (r == true) {
            // alert("Generating....")
        } else {
            // alert("Cancelled....")
            return false;
        }
        if (HomeClass.enableEdit) {
            // alert("Update Now");
            var property_unique_id = $("#prop_unique_id").val();
            var property_smc_tax_payee = $("#property_smc_tax_payee").val();

            // + property_smc_tax_payee);
            var prop_house_no = $("#prop_house_no").val();
            var prop_cam_id = $("#prop_cam_id").val();
            var property_plot_num = $("#property_plot_num").val();
            var property_survey_num = $("#property_survey_num").val();
            var prop_building_id = $("#prop_building_id").val();
            var prop_locality_id = $("#prop_locality_id").val();
            var prop_sub_locality_id = $("#prop_sub_locality_id").val();
            var prop_landmark_id = $("#prop_landmark_id").val();
            var property_road = $("#property_road").val();
            var property_pincode = $("#property_pincode").val();
            // var property_usage_details = $("#property_usage_details").val();
            // var property_age_of_building =
            // $("#property_age_of_building").val();
            // var prop_cost_id = $("#prop_cost_id").val();
            // var prop_ownername_id = $("#prop_ownername_id").val();
            // var prop_mobile_id = $("#prop_mobile_id").val();
            // var prop_father_id = $("#prop_father_id").val();
            // var prop_spouse_id = $("#prop_spouse_id").val();
            // var prop_occupier_id = $("#prop_occupier_id").val();
            // var prop_relation_id = $("#prop_relation_id").val();
            // var owner_adhar_id = $("#owner_adhar_id").val();
            // var owner_email_id = $("#owner_email_id").val();
            // var owner_floors_id = $("#owner_floors_id").val();
            // var owner_rooms_id = $("#owner_rooms_id").val();
            //
            // var ca_pipe_id = $("#ca_pipe_id").val();
            // var ca_sew_id = $("#ca_sew_id").val();
            // var ca_sew_no_id = $("#ca_sew_no_id").val();
            // var ca_elec_id = $("#ca_elec_id").val();
            // var ca_elec_no_id = $("#ca_elec_no_id").val();
            // var ca_ccCam_id = $("#ca_ccCam_id").val();
            // var ca_hording_id = $("#ca_hording_id").val();
            // var ca_fire_id = $("#ca_fire_id").val();
            // var ca_lift_id = $("#ca_lift_id").val();
            // var ca_borewell_id = $("#ca_borewell_id").val();
            // var ca_mobiletower_id = $("#ca_mobiletower_id").val();
            // var ca_rainharvest_id = $("#ca_rainharvest_id").val();
            //			
            // var prop_floor_room_cnt_hotel =
            // $("#prop_floor_room_cnt_hotel").val();
            // var prop_floor_room_cnt_school_clg =
            // $("#prop_floor_room_cnt_school_clg").val();
            // var prop_floor_room_cnt_hospi_nurse =
            // $("#prop_floor_room_cnt_hospi_nurse").val();
            // var prop_floor_room_cnt_hostel =
            // $("#prop_floor_room_cnt_hostel").val();
            // var floor_type_desc = $("#floor_type_desc").val();
            // var prop_floor_plot_area = $("#prop_floor_plot_area").val();
            // var prop_floor_construction_type =
            // $("#prop_floor_construction_type").val();
            // var prop_floor_usage_type = $("#prop_floor_usage_type").val();
            // var sanitation_type_desc = $("#sanitation_type_desc").val();

            // sanitation id remaining

            $.post("updatePropertyDetails", {
                property_unique_id: property_unique_id,
                property_smc_tax_payee: property_smc_tax_payee,
                prop_house_no: prop_house_no,
                prop_cam_id: prop_cam_id,
                property_plot_num: property_plot_num,
                property_survey_num: property_survey_num,
                prop_building_id: prop_building_id,
                prop_locality_id: prop_locality_id,
                prop_sub_locality_id: prop_sub_locality_id,
                prop_landmark_id: prop_landmark_id,
                property_road: property_road,
                property_pincode: property_pincode,
                // property_usage_details : property_usage_details,
                // property_age_of_building : property_age_of_building,
                // prop_cost_id : prop_cost_id,
                // prop_ownername_id : prop_ownername_id,
                // prop_mobile_id : prop_mobile_id,
                // prop_father_id : prop_father_id,
                // prop_spouse_id : prop_spouse_id,
                // prop_occupier_id : prop_occupier_id,
                // prop_relation_id : prop_relation_id,
                // owner_adhar_id : owner_adhar_id,
                // owner_email_id : owner_email_id,
                // owner_floors_id : owner_floors_id,
                // owner_rooms_id : owner_rooms_id,
                // ca_pipe_id : ca_pipe_id,
                // ca_sew_id : ca_sew_id,
                // ca_sew_no_id : ca_sew_no_id,
                // ca_elec_id : ca_elec_id,
                // ca_elec_no_id : ca_elec_no_id,
                // ca_ccCam_id : ca_ccCam_id,
                // ca_hording_id : ca_hording_id,
                // ca_fire_id : ca_fire_id,
                // ca_lift_id : ca_lift_id,
                // ca_borewell_id : ca_borewell_id,
                // ca_mobiletower_id : ca_mobiletower_id,
                // ca_rainharvest_id : ca_rainharvest_id,
                // prop_floor_room_cnt_hotel : prop_floor_room_cnt_hotel,
                // prop_floor_room_cnt_school_clg : prop_floor_room_cnt_school_clg,
                // prop_floor_room_cnt_hospi_nurse :
                // prop_floor_room_cnt_hospi_nurse,
                // prop_floor_room_cnt_hostel : prop_floor_room_cnt_hostel,
                // floor_type_desc : floor_type_desc,
                // prop_floor_plot_area : prop_floor_plot_area,
                // prop_floor_construction_type : prop_floor_construction_type,
                // prop_floor_usage_type : prop_floor_usage_type,
                // sanitation_type_desc : sanitation_type_desc
                //				

            }, function (result) {
                if (result == 1) {
                    alert("Details sucessfully updated")
                    return false;
                } else {
                    alert("Details not updated")
                    return false;
                }

            });

        }

    },
    searchNext: function () {

    },
    title: "Property Master",
    params: {},
    type: "PDF",
    exportAs: function (type) {

        HomeClass.type = type;

        var html_ = '<li class="clearfix">'
                + '<input id="export_All" type="checkbox" value="">'
                + '<label class="cheklabel" for="export_All">All</label>'
                + '</li>';

        for (var a in MASTERCOLUMN.attrPROP) {
            var attr = MASTERCOLUMN.attrPROP[a];
            html_ += '<li class="clearfix">'
                    + '<input id="' + attr + '" type="checkbox" value="">'
                    + '<label class="cheklabel" for="name">' + MASTERCOLUMN.attrHead[attr] + '</label>'
                    + '</li>';

        }
        $("#business_entities_list").html(html_);
        $('#export-modal').modal('toggle');
    },
    download: function () {
        $("#exportTitle").val(HomeClass.title);
        $("#windowId").val(Math.random());

        HomeClass.params = new Object();
        HomeClass.params.masterBeans = [];
        var masterAttrArr = [];
        var masterHeadArr = [];
        $(".business_entities_list li input[type=checkbox]").each(function () {
            if ($(this).prop("checked")) {
                var id = $(this).attr("id");
                if (id !== "export_All") {
                    masterAttrArr.push(id);
                    masterHeadArr.push(MASTERCOLUMN.attrHead[id]);
                }
            }
        });

        var propArr = Object.keys(HomeClass.propertyMap);
        for (var p in propArr) {
            var propId = propArr[p];
            var prop = HomeClass.propertyMap[propId];
            var tax = HomeClass.taxDetailMap[prop.propertyUniqueId];
            if (tax === undefined) {
                prop.taxGenerated = "N";
            } else {
                prop.taxGenerated = "Y";
                var keys_ = Object.keys(tax);
                for (var k in keys_) {
                    var key = keys_[k];
                    prop[key] = tax[key];
                }
            }

            var obj = {};
            for (var m in masterAttrArr) {
                var attr = masterAttrArr[m];

                if (prop[MASTERCOLUMN.attrInBean[attr]] === undefined) {
                    obj[attr] = "";
                } else {

                    if (attr === "zone") {
                        obj[attr] = HomeClass.zoneMaster[prop[MASTERCOLUMN.attrInBean[attr]]];
                    } else if (attr === "noticeGenerated") {
                        obj[attr] = prop[MASTERCOLUMN.attrInBean[attr]];

                        if (obj[attr] === "Y") {
                            obj[attr] = "Yes";
                        } else {
                            obj[attr] = "No";
                        }

                    } else if (attr === "taxGenerated") {

                        obj[attr] = prop[MASTERCOLUMN.attrInBean[attr]];

                        if (obj[attr] === "Y") {
                            obj[attr] = "Yes";
                        } else {
                            obj[attr] = "No";
                        }

                    } else {
                        obj[attr] = prop[MASTERCOLUMN.attrInBean[attr]];
                    }
                }

            }

            HomeClass.params.masterBeans.push(obj);

        }

        $("#exportParams").val(JSON.stringify(HomeClass.params));
        $("#exportTHead").val(JSON.stringify(masterHeadArr));
        $("#exportAttrToAdd").val(JSON.stringify(masterAttrArr));
        $("#exportType").val(HomeClass.type);
        $("#expo").submit();
        $('#export-modal').modal('toggle');
    },
    wardMaster: {},
    getWard: function () {

        if ($("#zone").val() === "-1") {

        } else {
            $.post("getWards", {zone: $("#zone").val()}, function (result) {
                if (result != undefined) {
                    var html = "";
                    html += "<option value='-1'>--Select Ward--</option>";
                    for (var i = 0; i < result.length; i++) {
                        html += "<option value='" + result[i].ward + "'>" + result[i].displayName + "</option>";
                        HomeClass.wardMaster[result[i].ward] = result[i].displayName;
                    }
                    HomeClass.resetWard(html);
                }
            }, 'json').always(function () {

            });
        }


    },
    localityMaster: {},
    getLocality: function () {
        $.post("getSubLocality", {ward: $("#ward").val()}, function (result) {
            if (result != undefined) {
                var html = "";
                html += "<option value='-1'>--Select Locality--</option>";
                for (var i = 0; i < result.length; i++) {
                    html += "<option value='" + result[i].subLocality + "'>" + result[i].displayName + "</option>";
                    HomeClass.localityMaster[result[i].subLocality] = result[i].displayName;
                }
                HomeClass.resetLocality(html);
            }
        }, 'json').always(function () {

        });
    },
    resetWard: function (html) {
        if (html === undefined) {
            $("#ward").html("<option value='-1'>--Select Ward--</option>");

        } else {
            $("#ward").html(html);
        }
        $("#ward").SumoSelect().sumo.unload();
        $("#ward").SumoSelect({csvDispCount: 3, search: true, searchText: 'Enter here.'});
    },
    resetLocality: function (html) {

        if (html === undefined) {
            $("#locality").html("<option value='-1'>--Select Locality--</option>");

        } else {
            $("#locality").html(html);
        }

        $("#locality").SumoSelect().sumo.unload();
        $("#locality").SumoSelect({csvDispCount: 3, search: true, searchText: 'Enter here.'});
        if (html === undefined) {
            $("#locality").closest(".SumoSelect").find(".CaptionCont").addClass("disable_cls");
            $("#locality").prop("disabled", true);
        } else {
            $("#locality").prop("disabled", false);
            $("#locality").closest(".SumoSelect").removeClass("disabled");
            $("#locality").closest(".SumoSelect").find(".CaptionCont").removeClass("disable_cls");
        }
    },
    resetOccupier: function (html) {
        if (html === undefined) {
            $("#occ_name").html("<option value='-1'>--Select Occupier Name--</option>");

        } else {
            $("#occ_name").html(html);
        }
        $("#occ_name").SumoSelect().sumo.unload();
        $("#occ_name").SumoSelect({csvDispCount: 3, search: true, searchText: 'Enter here.'});

        if (html === undefined) {
            $("#occ_name").closest(".SumoSelect").find(".CaptionCont").addClass("disable_cls");
            $("#occ_name").prop("disabled", true);
        } else {
            $("#occ_name").prop("disabled", false);
            $("#occ_name").closest(".SumoSelect").removeClass("disabled");
            $("#occ_name").closest(".SumoSelect").find(".CaptionCont").removeClass("disable_cls");
        }
    },
    resetOwner: function (html) {
        if (html === undefined) {
            $("#ownerid").html("<option value='-1'>--Select Owner Name--</option>");
            $("#ownerid").closest(".SumoSelect").find(".CaptionCont").addClass("disable_cls");
        } else {
            $("#ownerid").html(html);
            $("#ownerid").closest(".SumoSelect").find(".CaptionCont").removeClass("disable_cls");
        }
        $("#ownerid").SumoSelect().sumo.unload();
        $("#ownerid").SumoSelect({csvDispCount: 3, search: true, searchText: 'Enter here.'});

        if (html === undefined) {
            $("#ownerid").closest(".SumoSelect").find(".CaptionCont").addClass("disable_cls");
            $("#ownerid").prop("disabled", true);
        } else {
            $("#ownerid").prop("disabled", false);
            $("#ownerid").closest(".SumoSelect").removeClass("disabled");
            $("#ownerid").closest(".SumoSelect").find(".CaptionCont").removeClass("disable_cls");
        }

    },
    resetPropertyId: function (html) {
        if (html === undefined) {
            $("#propertyid").html("<option value='-1'>--Select Property ID--</option>");

        } else {
            $("#propertyid").html(html);
        }
        $("#propertyid").SumoSelect().sumo.unload();
        $("#propertyid").SumoSelect({csvDispCount: 3, search: true, searchText: 'Enter here.'});
        if (html === undefined) {
            $("#propertyid").prop("disabled", true);
            $("#propertyid").closest(".SumoSelect").find(".CaptionCont").addClass("disable_cls");
        } else {
            $("#propertyid").prop("disabled", false);
            $("#propertyid").closest(".SumoSelect").removeClass("disabled");
            $("#propertyid").closest(".SumoSelect").find(".CaptionCont").removeClass("disable_cls");
        }

    },
    resetAadhar: function () {
        $("#src_aadhar_no").val("");
    },
    resetPropertyCategory: function (html) {
        if (html === undefined) {
            $("#category").html("<option value='-1'>--Select Property Type Name--</option>");

        } else {
            $("#category").html(html);
        }
        $("#category").SumoSelect().sumo.unload();
        $("#category").SumoSelect({csvDispCount: 3, search: true, searchText: 'Enter here.'});
        if (html === undefined) {
            $("#category").prop("disabled", true);
            $("#category").closest(".SumoSelect").find(".CaptionCont").addClass("disable_cls");
        } else {
            $("#category").prop("disabled", false);
            $("#category").closest(".SumoSelect").removeClass("disabled");
            $("#category").closest(".SumoSelect").find(".CaptionCont").removeClass("disable_cls");
        }

    },
    propertyIdFilter: function () {
        var options = {
            url: function (phrase) {
                return "getPropertyIds";
            },
            getValue: function (element) {
                return element.property_unique_id;
            },
            ajaxSettings: {
                method: "POST",
                data: {}
            },
            preparePostData: function (data) {
                data.searchStr = $("#prop_id_input").val()
                return data;
            },
            requestDelay: 400,
            autoFocus: true,
            placeholder: "Search Property",
            list: {
                match: {
                    enabled: true
                },
                onChooseEvent: function () {
                    var value = $("#prop_id_input").getSelectedItemData().property_unique_id;
                    $("#prop_id_input").val(value).trigger("change");
                },
                onHideListEvent: function () {
                    var val = $("#prop_id_input").val();
                    if (val == "") {
                        $("#prop_id_input").val("")
                    }

                }

            }
        };
        $("#prop_id_input").easyAutocomplete(options);
    },
    phoneNoFilter: function () {
        var options = {
            url: function (phrase) {
                return "getPhoneNos";
            },
            getValue: function (element) {
                return element.property_contact;
            },
            ajaxSettings: {
                method: "POST",
                data: {}
            },
            preparePostData: function (data) {
                data.searchStr = $("#Phone_No").val()
                return data;
            },
            requestDelay: 400,
            autoFocus: true,
            placeholder: "Search Phone No.",
            list: {
                match: {
                    enabled: true
                },
                onChooseEvent: function () {
                    var value = $("#Phone_No").getSelectedItemData().property_contact;
                    $("#Phone_No").val(value).trigger("change");
                },
                onHideListEvent: function () {
                    var val = $("#Phone_No").val();
                    if (val == "") {
                        $("#Phone_No").val("")
                    }

                }

            }
        };
        $("#Phone_No").easyAutocomplete(options);
    },
    cityCodeFilter: function () {
        var options = {
            url: function (phrase) {
                return "getCityCodes";
            },
            getValue: function (element) {
                return element.city_code;
            },
            ajaxSettings: {
                method: "POST",
                data: {}
            },
            preparePostData: function (data) {
                data.searchStr = $("#Easy_City_Code").val()
                return data;
            },
            requestDelay: 400,
            autoFocus: true,
            placeholder: "Search City Code",
            list: {
                match: {
                    enabled: true
                },
                onChooseEvent: function () {
                    var value = $("#Easy_City_Code").getSelectedItemData().city_code;
                    $("#Easy_City_Code").val(value).trigger("change");
                },
                onHideListEvent: function () {
                    var val = $("#Easy_City_Code").val();
                    if (val == "") {
                        $("#Easy_City_Code").val("")
                    }

                }

            }
        };
        $("#Easy_City_Code").easyAutocomplete(options);
    },
    aadharNoFilter: function () {
        var options = {
            url: function (phrase) {
                return "getaadharNo";
            },
            getValue: function (element) {
                return element.property_aadhar_num;
            },
            ajaxSettings: {
                method: "POST",
                data: {}
            },
            preparePostData: function (data) {
                data.searchStr = $("#src_aadhar_no").val()
                return data;
            },
            requestDelay: 400,
            autoFocus: true,
            placeholder: "Search Aadhaar No.",
            list: {
                match: {
                    enabled: true
                },
                onChooseEvent: function () {
                    var value = $("#src_aadhar_no").getSelectedItemData().property_aadhar_num;
                    $("#src_aadhar_no").val(value).trigger("change");
                },
                onHideListEvent: function () {
                    var val = $("#src_aadhar_no").val();
                    if (val == "") {
                        $("#src_aadhar_no").val("")
                    }

                }

            }
        };
        $("#src_aadhar_no").easyAutocomplete(options);
    },
    subLocalityFilter: function () {
        var options = {
            url: function (phrase) {
                return "getsubLocality";
            },
            getValue: function (element) {
                return element.property_sublocality;
            },
            ajaxSettings: {
                method: "POST",
                data: {}
            },
            preparePostData: function (data) {
                data.searchStr = $("#Locality").val()
                return data;
            },
            requestDelay: 400,
            autoFocus: true,
            placeholder: "Search Locality",
            list: {
                match: {
                    enabled: true
                },
                onChooseEvent: function () {
                    var value = $("#Locality").getSelectedItemData().property_sublocality;
                    $("#Locality").val(value).trigger("change");
                },
                onHideListEvent: function () {
                    var val = $("#Locality").val();
                    if (val == "") {
                        $("#Locality").val("")
                    }

                }

            }
        };
        $("#Locality").easyAutocomplete(options);
    },
    wardlstFilter: function () {
        var options = {
            url: function (phrase) {
                return "getWardlst";
            },
            getValue: function (element) {
                return element.ward;
            },
            ajaxSettings: {
                method: "POST",
                data: {}
            },
            preparePostData: function (data) {
                data.searchStr = $("#ward").val()
                return data;
            },
            requestDelay: 400,
            autoFocus: true,
            placeholder: "Search Ward ",
            list: {
                match: {
                    enabled: true
                },
                onChooseEvent: function () {
                    var value = $("#ward").getSelectedItemData().ward;
                    $("#ward").val(value).trigger("change");
                },
                onHideListEvent: function () {
                    var val = $("#ward").val();
                    if (val == "") {
                        $("#ward").val("")
                    }

                }

            }
        };
        $("#ward").easyAutocomplete(options);
    },
    ownerlstFilter: function () {
        var options = {
            url: function (phrase) {
                return "getOwnerlst";
            },
            getValue: function (element) {
                return element.property_owner;
            },
            ajaxSettings: {
                method: "POST",
                data: {}
            },
            preparePostData: function (data) {
                data.searchStr = $("#ownerid").val()
                return data;
            },
            requestDelay: 400,
            autoFocus: true,
            placeholder: "Search Owner",
            list: {
                match: {
                    enabled: true
                },
                onChooseEvent: function () {
                    var value = $("#ownerid").getSelectedItemData().property_owner;
                    $("#ownerid").val(value).trigger("change");
                },
                onHideListEvent: function () {
                    var val = $("#ownerid").val();
                    if (val == "") {
                        $("#ownerid").val("")
                    }

                }

            }
        };
        $("#ownerid").easyAutocomplete(options);
    },
    occupierlstFilter: function () {
        var options = {
            url: function (phrase) {
                return "getOccupierlst";
            },
            getValue: function (element) {
                return element.property_occupier_name;
            },
            ajaxSettings: {
                method: "POST",
                data: {}
            },
            preparePostData: function (data) {
                data.searchStr = $("#occ_name").val()
                return data;
            },
            requestDelay: 400,
            autoFocus: true,
            placeholder: "Search Occupier",
            list: {
                match: {
                    enabled: true
                },
                onChooseEvent: function () {
                    var value = $("#occ_name").getSelectedItemData().property_occupier_name;
                    $("#occ_name").val(value).trigger("change");
                },
                onHideListEvent: function () {
                    var val = $("#occ_name").val();
                    if (val == "") {
                        $("#occ_name").val("")
                    }

                }

            }
        };
        $("#occ_name").easyAutocomplete(options);
    },
    localitylstFilter: function () {
        var options = {
            url: function (phrase) {
                return "getLocalitylst";
            },
            getValue: function (element) {
                return element.property_locality;
            },
            ajaxSettings: {
                method: "POST",
                data: {}
            },
            preparePostData: function (data) {
                data.searchStr = $("#locality").val()
                return data;
            },
            requestDelay: 400,
            autoFocus: true,
            placeholder: "Search Locality",
            list: {
                match: {
                    enabled: true
                },
                onChooseEvent: function () {
                    var value = $("#locality").getSelectedItemData().property_locality;
                    $("#locality").val(value).trigger("change");
                },
                onHideListEvent: function () {
                    var val = $("#locality").val();
                    if (val == "") {
                        $("#locality").val("")
                    }

                }

            }
        };
        $("#locality").easyAutocomplete(options);
    },
    propertytypeFilter: function () {
        var options = {
            url: function (phrase) {
                return "getPropertyTypeLst";
            },
            getValue: function (element) {
                return element.property_category_desc;
            },
            ajaxSettings: {
                method: "POST",
                data: {}
            },
            preparePostData: function (data) {
                data.searchStr = $("#category").val()
                return data;
            },
            requestDelay: 400,
            autoFocus: true,
            placeholder: "Search Property Type",
            list: {
                match: {
                    enabled: true
                },
                onChooseEvent: function () {
                    var value = $("#category").getSelectedItemData().property_category_desc;
                    $("#category").val(value).trigger("change");
                },
                onHideListEvent: function () {
                    var val = $("#category").val();
                    if (val == "") {
                        $("#category").val("")
                    }

                }

            }
        };
        $("#category").easyAutocomplete(options);
    }

}







$(document).on("click", ".keep_focus", function () {
    if ($(this).find(".collapsed").length < 1) {
        $("html, body").delay(200).animate({scrollTop: $(this).offset().top}, 500);
    }
});

//$(document).on("change", "#zone", function () {
//    HomeClass.resetWard();
//    HomeClass.resetOccupier();
//    HomeClass.resetOwner();
//    HomeClass.resetPropertyId();
//    HomeClass.resetAadhar();
//    HomeClass.resetLocality();
//    HomeClass.getWard();
//    HomeClass.resetPropertyCategory();
//    $("#src_aadhar_no").addClass("disable_cls");
//    $("#src_aadhar_no").prop("disabled", true);
//});

$(document).on("change", "#propertyid", function () {
    if ($("#propertyid option:selected").val() !== "-1") {
        $("#locality,#ownerid,#occ_name,#category").prop("disabled", true);
        $("#locality,#ownerid,#occ_name,#category").val("-1");
        $("#locality,#ownerid,#occ_name,#category").closest(".SumoSelect").find(".CaptionCont span").text("");
        $("#locality,#ownerid,#occ_name,#category").closest(".SumoSelect").find(".CaptionCont").addClass("disable_cls");
        $("#src_aadhar_no").val("");
        $("#src_aadhar_no").addClass("disable_cls");
        $("#src_aadhar_no").prop("disabled", true);
    } else {
        $("#locality,#ownerid,#occ_name,#category").prop("disabled", false);
        $("#locality,#ownerid,#occ_name,#category").closest(".SumoSelect").find(".CaptionCont").removeClass("disable_cls");
        $("#src_aadhar_no").removeClass("disable_cls");
        $("#src_aadhar_no").prop("disabled", false);
    }


});

$(document).on("change", "#ward", function () {

    $("#zone").val(zoneWardMaster[$("#ward").val()]);

    HomeClass.resetOccupier();
    HomeClass.resetOwner();
    HomeClass.resetPropertyId();
    HomeClass.resetAadhar();
    HomeClass.resetLocality();
    HomeClass.resetPropertyCategory();
    if ($("#ward").val() !== "-1") {
        HomeClass.getLocality();
        $("#src_aadhar_no").removeClass("disable_cls");
        $("#src_aadhar_no").prop("disabled", false);
    }
    HomeClass.getSearchCriteria();
});
$(document).on("click", "#export_btn", function () {
    HomeClass.download();
});

$('body').on('click', 'a.disabled', function (event) {
    event.preventDefault();
});

