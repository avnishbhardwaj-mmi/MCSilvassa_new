//----------------------------------------------------------------------------------------------------
//                          MapMyIndia
//            Product / Project           : Silvassa
//            Module                      : PropertyAdding
//            File Name                   : editProperty
//            Author                      : Jay Prakash Kumar
//            Project Lead                :
//            Date written (DD/MM/YYYY)   : 12 Jul, 2017, 12:36:55 PM
//            Description                 : 
//----------------------------------------------------------------------------------------------------
//                                            CHANGE HISTORY
//----------------------------------------------------------------------------------------------------
// Date             Change By           Change Description (Bug No. (If Any))
// (DD/MM/YYYY)
//----------------------------------------------------------------------------------------------------
//----------------------------------------------------------------------------------------------------

var EditPro = {
    floorn: 0,
    floorCount: 0,
    zoneMaster: {},
    taxDetailMap: {},
    propertyMap: {},
    tempPFId: "",
    propertyObject: null,
    rentableTypeArray: new Array(),
    floreIdCount: 0,
    floorDetails: new Array(),
    floorDetail: {},
    wardBuilder: "",
    localityBuilder: "",
    subLocalityBuilder: "",
    wardGeomLayer: new Array(),
    locGeomLayer: new Array(),
    subLocGeomLayer: new Array(),
    propMarker: new Array(),
    geomtype: "",
    wFlag: false,
    lFlag: false,
    sLFlag: false,
    wgeomData: new Array(),
    lgeomData: new Array(),
    sLgeomData: new Array(),
    inner_map_Longitude: "",
    inner_map_Locality: "",
    inner_map_Latitude: "",
    inner_map_Address: "",
    inner_map_ward: "",
    inner_map_Sublocality: "",
    inner_map_UniqueId: "",
    inner_map_Owner: "",
    addNewProperty: function () {
        var propertyDetails = new Object();
        var propertyId = $("#propertyUniqueId").val();
        var zoneId = $("#zoneId").val();
        $("#validateid").html('');
        if (propertyId === "" || zoneId === "") {
            $("#validateid").html("Please Fill Property Details properly");
            //alert("Please Fill Property Details properly");
            return false;
        }
        $("div#newProperty input[type=text]").each(function () {
            var input = $(this);
            if (input[0].id !== "") {

                switch (input[0].id) {
                    case "propertyMale18Plus":
                        propertyDetails[input[0].id] = input[0].value === "" ? 0 : input[0].value;
                        break;
                    case "propertyFem18Plus":
                        propertyDetails[input[0].id] = input[0].value === "" ? 0 : input[0].value;
                        break;
                    case "propertyMale18Minus":
                        propertyDetails[input[0].id] = input[0].value === "" ? 0 : input[0].value;
                        break;
                    case "propertyFem18Minus":
                        propertyDetails[input[0].id] = input[0].value === "" ? 0 : input[0].value;
                        break;
                    default:
                        propertyDetails[input[0].id] = input[0].value === "" ? "-" : input[0].value;
                }
            }
        });
        $("div#newProperty select").each(function () {
            var select = $(this);
            if (select[0].id !== "") {
                switch (select[0].id) {
                    case "wardId":
                        propertyDetails['ward'] = select[0].value === "" ? "0" : select[0].value;
                        break;
                    default:
                        propertyDetails[select[0].id] = select[0].value === "" ? "0" : select[0].value;
                }
            }
        });
        if ($.isEmptyObject(propertyDetails)) {
            $("#validateid").html("No Value to Submit");
            //alert("No Value to Submit");
        } else {
//            EditPro.replaceNAWithMinusOne();
            //console.log("propertyDetails "+propertyDetails);
            // console.log("EditPro.floorDetails "+EditPro.floorDetails);
            EditPro.submitPropertyAddRequest(propertyDetails, EditPro.floorDetails);
        }
    },
    submitPropertyAddRequest: function (propertyDetails, floorDetails) {
        $.ajax({
            url: "addNewProperty",
            type: 'post',
            data: "newProperty=" + encodeURIComponent(JSON.stringify(propertyDetails)) + "&newPropertyFloor=" + JSON.stringify(floorDetails),
            dataType: 'json',
            success: function (result) {
                //console.log(result);
                if (result !== null) {
                    if (result.Status == 200) {
                        //alert("Edited Successfully");
                        window.open(window.location.href, "_self");
                        $("#validateid").html("Edited Successfully");
                    } else {
                        $("#validateid").html("Some Error Please try again");
                        //alert("Some Error Please try again");
                    }
                } else {
                    $("#validateid").html("Some Error Please try again");
                    //alert("Some Error Please try again");
                }
            },
            error: function (e) {

                console.log("ERROR: ", e);
            }
        });
    },
    getRentableValues: function () {
        $.ajax({
            url: "getRentableValues",
            type: 'post',
            data: '',
            dataType: 'json',
            success: function (result) {
                if (result != null) {
                    var html = "";
                    html += "<option value='-1'>--Select Use--</option>";
//                    for (var i = 0; i < result.length; i++) {
//                        //console.log("rentable "+result[i]);
//                        EditPro.rentableTypeArray.push(result[i]);
//                        html += "<option value='" + result[i].propertyRentableId + "'>" + result[i].propertyCat + " ( " + result[i].propertySubcatCode + " ) </option>";
//                    }
                    var rtypList = [];
                    for (var r in result) {
                        EditPro.rentableTypeArray.push(result[r]);
                        if (rtypList.indexOf(result[r].propertyCat) < 0) {
                            rtypList.push(result[r].propertyCat);
                            html += "<option value='" + result[r].propertyCat + "'>" + result[r].propertyCat + "</option>";
                        }
                    }
                    delete rtypList;
                    $("#pfFloorwiseBuildUse").html(html);
                    $("#pfFloorwiseBuildUse").SumoSelect({csvDispCount: 3, search: true, searchText: 'Enter here.'});
                }
            },
            error: function (e) {
                console.log("ERROR: ", e);
            }
        });
    },
    localityMaster: {},
    getLocality: function () {

        $.post("getSubLocality", {ward: $("#ward").val()}, function (result) {
            if (result != undefined) {
                var html = "";
                html += "<option value='-1'>--Select Locality--</option>";
                for (var i = 0; i < result.length; i++) {
                    html += "<option value='" + result[i].subLocality + "'>" + result[i].displayName + "</option>";
                    EditPro.localityMaster[result[i].subLocality] = result[i].displayName;
                }
                EditPro.resetLocality(html);
            }
        }, 'json').always(function () {

        });
    },
    resetLocality: function (html) {
        if (html === undefined) {
            $("#locality").html("<option value='-1'>--Select Locality--</option>");
        } else {
            $("#locality").html(html);
        }
        //$("#locality").SumoSelect().sumo.unload();
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
    addFloorDetailsToTable: function () {
        var propertyId = $("#propertyUniqueId").val();
        var zoneId = $("#zoneId").val();
        $("#validateid").html('');
        if (propertyId === "" || zoneId === "") {
            $("#validateid").html("Please fill Property Details");
            //alert("Please fill Property Details");
            return false;
        }
        var floorDetail = new Object();
        $("div#floorDetatil input[type=text]").each(function () {
            var input = $(this);
            if (input[0].id !== "") {
                switch (input[0].id) {
                    case "pfBuiltupArea":
                        floorDetail[input[0].id] = input[0].value == "" ? "0" : input[0].value;
                        floorDetail["propertyUniqueId"] = propertyId;
                        break;
                    default:
                        floorDetail[input[0].id] = input[0].value == "" ? "-" : input[0].value;
                        floorDetail["propertyUniqueId"] = propertyId;
                }
            }
        });
        $("div#floorDetatil select").each(function () {
            var select = $(this);
            if (select[0].id !== "") {
                switch (select[0].id) {
                    case "pfFloorwiseBuildUse":
                        floorDetail['propertyRentableId'] = select[0].value == "-1" ? "-1" : select[0].value;
                        var ret = EditPro.returnUseUsingCode('propertyCat', select[0].value, 'propertyCat');
                        floorDetail['pfFloorwiseBuildUse'] = ret;
                        floorDetail["propertyUniqueId"] = propertyId;
                        break;
                    default:
                        floorDetail[select[0].id] = select[0].value == "-1" ? "NA" : select[0].value;
                        floorDetail["propertyUniqueId"] = propertyId;
                }
            }
        });
        floorDetail["pfId"] = EditPro.tempPFId;
        EditPro.floorDetails.push(floorDetail);
        EditPro.floorDetail = {};
        if ($.isEmptyObject(floorDetail)) {
        } else {
            EditPro.addFloorDetailsHtml('remove');
            EditPro.showHideFloorDetailsFields('H', 'floorDetatil');
            EditPro.showHideFloorDetailsFields('H', 'sumitDiscard');
        }
        $("#floorDetatilsTable a").removeClass('hidden');
    },
    addFloorDetailsHtml: function (flag) {
        $('#floorDetatilsTable').find('tbody').empty();
        var fl = "";
        for (var j = 0; j < EditPro.floorDetails.length; j++) {
            var dt = EditPro.floorDetails[j];
            dt.pfId = dt.pfId === undefined ? "" : dt.pfId;
            //dt.pfFloorName
            //console.log(dt);
            floorCount = j;
            dt.pfFloorName = dt.pfFloorName === undefined || dt.pfFloorName == null ? "" : dt.pfFloorName;
            dt.pfBuiltupArea = dt.pfBuiltupArea === undefined || dt.pfBuiltupArea == null ? "" : dt.pfBuiltupArea;
            dt.pfConstructionType = dt.pfConstructionType === undefined || dt.pfConstructionType == null ? "" : dt.pfConstructionType;
            dt.pfFloorwiseBuildUse = dt.pfFloorwiseBuildUse === undefined || dt.pfFloorwiseBuildUse == null ? "" : dt.pfFloorwiseBuildUse;
            dt.pfWaterPipeCon = dt.pfWaterPipeCon === undefined || dt.pfWaterPipeCon == null ? "" : dt.pfWaterPipeCon;
            dt.pfSewerageCon = dt.pfSewerageCon === undefined || dt.pfSewerageCon == null ? "" : dt.pfSewerageCon;
            dt.pfElectricMeterNum = dt.pfElectricMeterNum === undefined || dt.pfElectricMeterNum == null ? "" : dt.pfElectricMeterNum;
            dt.pfElectricConNum = dt.pfElectricConNum === undefined || dt.pfElectricConNum == null ? "" : dt.pfElectricConNum;
            dt.pfCctvCamrea = dt.pfCctvCamrea === undefined || dt.pfCctvCamrea == null ? "" : dt.pfCctvCamrea;
            dt.pfFireEquipment = dt.pfFireEquipment === undefined || dt.pfFireEquipment == null ? "" : dt.pfFireEquipment;
            dt.pfLiftAvailable = dt.pfLiftAvailable === undefined || dt.pfLiftAvailable == null ? "" : dt.pfLiftAvailable;
            dt.pfRainWaterHarvest = dt.pfRainWaterHarvest === undefined || dt.pfRainWaterHarvest == null ? "" : dt.pfRainWaterHarvest;
            dt.pfNumOfBorewells = dt.pfNumOfBorewells === undefined || dt.pfRainWaterHarvest == null ? "" : dt.pfNumOfBorewells;
            dt.pfSanitation = dt.pfSanitation === undefined || dt.pfSanitation == null ? "" : dt.pfSanitation;
            dt.pfHordingAvail = dt.pfHordingAvail === undefined || dt.pfHordingAvail == null ? "" : dt.pfHordingAvail;
            dt.pfMobileTower = dt.pfMobileTower === undefined || dt.pfMobileTower == null ? "" : dt.pfMobileTower;
            dt.pf_property_subtype = dt.pf_property_subtype === undefined || dt.pf_property_subtype == null ? "" : dt.pf_property_subtype;
            dt.selfRent = dt.selfRent === undefined || dt.selfRent == null ? "" : dt.selfRent;
            dt.annualRent = dt.annualRent === undefined || dt.annualRent == null ? "0" : dt.annualRent;
            fl += "<tr>";
            fl += "<td >" + (j + 1) + "</td>";
            fl += "<td >" + dt.pfFloorName + "</td>";
            fl += "<td >" + dt.pfFloorwiseBuildUse + "</td>";
            fl += "<td >" + dt.pf_property_subtype + "</td>";
            fl += "<td >" + dt.pfBuiltupArea + "</td>";
            fl += "<td >" + dt.pfConstructionType + "</td>";
            fl += "<td >" + dt.selfRent + "</td>";
            fl += "<td >" + dt.annualRent + "</td>";
            fl += "<td >" + dt.pfElectricConNum + "</td>";
            //fl += "<td >" + dt.pfWaterPipeCon + "</td>";
            //fl += "<td >" + dt.pfSewerageCon + "</td>";
            //fl += "<td >" + dt.pfElectricMeterNum + "</td>";

            //fl += "<td >" + dt.pfCctvCamrea + "</td>";
            //fl += "<td >" + dt.pfFireEquipment + "</td>";
            //fl += "<td >" + dt.pfLiftAvailable + "</td>";
            //fl += "<td >" + dt.pfRainWaterHarvest + "</td>";
            //fl += "<td >" + dt.pfNumOfBorewells + "</td>";
            //fl += "<td >" + dt.pfSanitation + "</td>";
            //fl += "<td >" + dt.pfHordingAvail + "</td>";
            //fl += "<td >" + dt.pfMobileTower + "</td>";
            if (flag === 'new') {
                fl += '<td ><a class="floordetailsref" href="javascript:EditPro.removeByAttr(\'pfFloorName\',\'' + dt.pfFloorName + '\',\'remove\');"> Remove</a></td>';
            } else {
                fl += '<td ><a class="floordetailsref" href="javascript:EditPro.removeByAttr(\'pfId\',\'' + dt.pfId + '\',\'edit\');"> Edit</a></td>';
                //alert("ak");
            }
            fl += "</tr>";
        }
        $('#floorDetatilsTable').find('tbody').append(fl);
    },
    applyNumericaOnly: function () {
        $(".txtboxOnlyNumeric").keydown(function (e) {
            // Allow: backspace, delete, tab, escape, enter and .
            if ($.inArray(e.keyCode, [46, 8, 9, 27, 13, 110, 190]) !== -1 ||
                    // Allow: Ctrl+A, Command+A
                            (e.keyCode === 65 && (e.ctrlKey === true || e.metaKey === true)) ||
                            // Allow: home, end, left, right, down, up
                                    (e.keyCode >= 35 && e.keyCode <= 40)) {
                        // let it happen, don't do anything
                        return;
                    }
                    // Ensure that it is a number and stop the keypress
                    if ((e.shiftKey || (e.keyCode < 48 || e.keyCode > 57)) && (e.keyCode < 96 || e.keyCode > 105)) {
                        e.preventDefault();
                    }
                });
    },
    removeByAttr: function (attr, value, flag) {
        //alert("chack "+attr+" "+value+" "+flag);
        var i = EditPro.floorDetails.length;
        //console.log(EditPro.floorDetails);
        while (i--) {

            if (EditPro.floorDetails[i]
                    && EditPro.floorDetails[i].hasOwnProperty(attr)
                    && EditPro.floorDetails[i][attr] == value) {
                if (flag === 'edit') {
                    EditPro.floorDetail = EditPro.floorDetails[i];
                    EditPro.showHideFloorDetailsFields('S', 'floorDetatil');
                    EditPro.showHideFloorDetailsFields('S', 'sumitDiscard');
                    EditPro.floorDetailTableToEditFilds(EditPro.floorDetail);
                    EditPro.tempPFId = EditPro.floorDetail.pfId;
                }
                EditPro.floorDetails.splice(i, 1);
            }
        }

        EditPro.addFloorDetailsHtml('remove');
        $("#floorDetatilsTable a").addClass('hidden');
    },
    floorDetailTableToEditFilds: function (floorDetail) {
        var Allkeys = Object.keys(floorDetail);
        for (var i = 3; i < Allkeys.length; i++) {
            $("#" + Allkeys[i]).val(floorDetail[Allkeys[i]]);
            if ($("#" + Allkeys[i]).SumoSelect().sumo) {
                //$("#" + Allkeys[i]).SumoSelect().sumo.unload();
                $("#" + Allkeys[i]).SumoSelect({csvDispCount: 3, search: true, searchText: 'Enter here.'});
            }
        }
    },
    discardFloorDetailsEditing: function () {
        EditPro.floorDetails.push(EditPro.floorDetail);
        EditPro.floorDetail = {};
        EditPro.addFloorDetailsHtml('remove');
        EditPro.showHideFloorDetailsFields('H', 'floorDetatil');
        EditPro.showHideFloorDetailsFields('H', 'sumitDiscard');
        $("#floorDetatilsTable a").removeClass('hidden');
    },
    returnUseUsingCode: function (attr, value, attrv) {
        var i = EditPro.rentableTypeArray.length;
        var revalue = "NA";
        while (i--) {
            if (EditPro.rentableTypeArray[i]
                    && EditPro.rentableTypeArray[i].hasOwnProperty(attr)
                    && EditPro.rentableTypeArray[i][attr] === value) {
                revalue = EditPro.rentableTypeArray[i][attrv];
            }
        }
        return revalue;
    },
    replaceNAWithMinusOne: function () {

        for (var i = 0; i < EditPro.floorDetails.length; i++) {
            for (var key in EditPro.floorDetails[i]) {
                var value = EditPro.floorDetails[i][key];
                if (value === "NA") {
                    EditPro.floorDetails[i][key] = "-1";
                }
            }
        }
    },
    getSearchCriteria: function () {
        var zone_id = $("#zone").val();
        var ward = $("#ward").val();
        var locality = null;
        if (zone_id === "-1") {
        } else if (ward === "-1") {
        } else {
            LOADER.show();
            $.post("getSearchCriteria", {zone_id: zone_id, ward: ward, locality: locality}, function (result) {
                if (result != undefined) {
                    var pr = result.prop.propIdArr;
                    var own = result.prop.ownerArr;
                    var occ = result.prop.occupierArr;
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

                    // EditPro.resetOccupier(html_occ);
                    //   EditPro.resetOwner(html_own);
                    EditPro.resetPropertyId(html_prop);
                    LOADER.hide();
                }
            });
        }

    },
    searchPropertyDetails: function () {

        //var zone_id = $("#zone option:selected").val();
        $("#validateid").html('');
        var zone_id = "";
        var prop_id_input = $("#prop_id_input").val();
        var ward = $("#ward").val();
        var property_id = "";
        var occ = $("#occ_name").val();
        var owner_id = $("#ownerid").val();
        var locality = $("#locality").val();
        var aadhar_num = $("#src_aadhar_no").val();
        var propertyCategory = $("#category").val();
        //newly added
        var Phone_no = $("#Phone_No").val();
        var Locality = $("#Locality").val();
        var houseNo = $("#house_no").val();
//        if (zone_id === "-1") {
//            alert("Kindly provide zone.");
//        } else 
        if (prop_id_input == "" && ward == "" && occ == "" && occ == "" && owner_id == "" && locality == "" && aadhar_num == "" && propertyCategory == "" && Phone_no == "" && Locality == "" && houseNo == "") {
            // alert("Please select atleast one filter");
            $("#validateid").html("Please select at least one filter");
        } else {

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
                        houseNo: houseNo,
                        async: false,
                    }, function (data) {

                var propArr = data.propertyArr;
                if (propArr.length != 0) {
                    $("#validateid").html('');
                    EditPro.preserveTAXDetails(data.taxDetailArr);
                    EditPro.preservePropertydetails(data.propertyArr);
                    if (propArr !== undefined && propArr != null) {
                        EditPro.openSearchResults();
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
                                + "<th>Relation With Owner</th>"
                                + "<th>Address</th>"
                                //+ "<th>Category</th>"
//                                + "<th>Zone</th>"
                                + "<th>Ward</th>"

                                + "<th>Tax Generated</th>"
                                + "<th>Tax Amount</th>"
                                + "<th>Notice Generated</th>"
                                + "<th>Property Picture</th>"
                                + "<th>View On Map</th>"
                                + "</tr></thead>";
                        tab_html += "<tbody>"; // total headers 14

                        var imageicon = "res/img/11_large.png";
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
                            prop.latitude = (prop.propertyLatitude === undefined || prop.propertyLatitude === null) ? "" : prop.propertyLatitude;
                            prop.propertyLongitude = (prop.propertyLongitude === undefined || prop.propertyLongitude === null) ? "" : prop.propertyLongitude;
                            prop.propertyLocality = (prop.propertyLocality === undefined || prop.propertyLocality === null) ? "" : prop.propertyLocality;
                            //   var infohtml = "";
//                            infohtml += "<ul>";
//                            infohtml += "<li> Address: " + prop.completeAddress + "</li>";
//                            infohtml += "<li> Unique id: " + prop.propertyUniqueId + "</li>";
//                            infohtml += "<li> Owner Name: " + prop.propertyOwner + "</li>";
//                            infohtml += "</ul>";
                            var infohtml = "";
                            //infohtml += "<div class=popup_area><div class=headerSection>Property Detials</div><div class=content_area_sec><ul>";
                            infohtml += "<div class=popup_area><div class=headerSection>" + prop.propertyOwner + "(" + prop.propertyUniqueId + ")</div><div class=content_area_sec><ul>";
                            infohtml += "<li> <b>Address:</b> " + prop.completeAddress + "</li>";
//                            infohtml += "<li> <b>Unique id:</b> " + prop.propertyUniqueId + "</li>";
//                            infohtml += "<li> <b>Owner Name:</b> " + prop.propertyOwner + "</li>";
                            infohtml += "</ul></div></div>";
                            propJson = JSON.stringify(prop);
                            if (propArr.length == 1) {
                                EditPro.showProperty(prop.propertyUniqueId);
                            }

                            tab_html += "<tr><td>" + (parseInt(i) + 1) + "</td>"; // '+prop.propertyUniqueId+'
                            tab_html += "<td title='Click here to view more detail' onclick=EditPro.showProperty('"+prop.propertyUniqueId+"')><a href='javascript: void(0)'>"
                                    + prop.propertyUniqueId + "</a> </td>";
                            tab_html += "<td >" + prop.propertyOwner + "</td>";
                            tab_html += "<td >" + prop.propertyOccupierName + "</td>";
                            tab_html += "<td >" + prop.propertyRelationOwner + "</td>";
                            tab_html += "<td>" + (prop.completeAddress === undefined ? "" : prop.completeAddress) + "</td>";
//                            tab_html += "<td>" + (EditPro.zoneMaster[prop.zoneId] === undefined ? "N/A" : EditPro.zoneMaster[prop.zoneId]) + "</td>";
                            tab_html += "<td>" + (prop.ward === undefined ? "N/A" : prop.ward) + "</td>";
                            if (EditPro.taxDetailMap[prop.propertyUniqueId] === undefined) {
                                tab_html += "<td>No</td>";
                                tab_html += "<td>No</td>";
                                tab_html += "<td>No</td>";
                            } else {
                                tab_html += "<td>Yes</td>";
                                tab_html += "<td>" + (EditPro.taxDetailMap[prop.propertyUniqueId].grandTotal == undefined ? '' : EditPro.taxDetailMap[prop.propertyUniqueId].grandTotal) + " </td>";
                                if (EditPro.taxDetailMap[prop.propertyUniqueId].noticeGenerated === "Y") {
                                    tab_html += "<td>Yes</td>";
                                } else {
                                    tab_html += "<td>No</td>";
                                }
                            }
                            tab_html += "<td style='cursor:pointer;' onclick=\"EditPro.showImage('" + prop.propertyUniqueId + "')\" ><a href='javascript: void(0)'>" + " View</a> </td>";
                            // tab_html += "<td style='cursor:pointer;' onclick=\"MapHandler.plotMarker('" + prop.latitude + "','" + prop.propertyLongitude + "','" + imageicon + "','"+infohtml+"')\" ><a href='javascript: void(0)' >View</a></td>";
                            tab_html += "<td style='cursor:pointer;'><div  class=\"sectionEditB\" data-toggle=\"tooltip\" data-placement=\"top\" title=\"View on map.\" onclick=\"EditPro.plotMarker('out','" + prop.latitude + "','" + prop.propertyLongitude + "','" + imageicon + "','" + infohtml + "','" + prop.ward + "','" + prop.propertyLocality + "','" + prop.propertySublocality + "','"+prop.propertyUniqueId+ "')\" ><i class=\"material-icons\">map</i></div></td>";
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
                    }
                } else {
                    $("#validateid").html("No results found");
                    // alert("No results found");
                    return false;
                }

            }, 'json').always(function () {
                LOADER.hide();
                // $("#property_tab_paginate").css({"position": "absolute", "top": "0", "right": "0"});
                //   $("#property_tab_filter").css({"float": "left"});

            });
            return true;
        }

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
    showProperty: function (propId) {
        LOADER.show();
      //  jobj = JSON.parse(propJson);
        var currPropertyDetails =   EditPro.propertyMap[propId]
        debugger;
//       var g = EditPro.propertyMap.propJson.propertyLongitude;
        property_unique_id = propId;
        EditPro.inner_map_Longitude = currPropertyDetails.propertyLongitude.trim();
        EditPro.inner_map_Locality = currPropertyDetails.propertyLocality;
        EditPro.inner_map_Latitude = currPropertyDetails.latitude.trim();
        EditPro.inner_map_Address = currPropertyDetails.completeAddress;
        EditPro.inner_map_ward = currPropertyDetails.ward;
        EditPro.inner_map_Sublocality = currPropertyDetails.propertySublocality;
        EditPro.inner_map_UniqueId = currPropertyDetails.propertyUniqueId;
        EditPro.inner_map_Owner = currPropertyDetails.propertyOwner;
        $.post("showPropertyDetails", {property_unique_id: property_unique_id}, function (result) {


            if (result.floorDetails !== undefined && result.floorDetails.length > 0) {
                $("#accordion").removeClass("hidden");
                $("#no_result_found").addClass("hidden");
                EditPro.openPropertyWindow();
                var floorList = result.floorDetails;
                var correctionHistory = result.correctionHistory;
                EditPro.makeTable(correctionHistory);
                var prop = EditPro.propertyMap[property_unique_id];
                EditPro.propertyObject = prop;
                EditPro.setZonesToEditFiled();
                EditPro.setWardToEditFiled(EditPro.propertyObject['zoneId']);
                var taxdetails = EditPro.taxDetailMap[prop.propertyUniqueId];
                if (EditPro.taxDetailMap[prop.propertyUniqueId] === undefined) {
                    $('#prop_tax_status').text("No");
                } else {
                    $('#prop_tax_status').text("Yes");
                }
                    if(taxdetails !==undefined){
                $("#prop_tax_paid_amount").text(taxdetails.advancePaidAmount);
                $("#pending_arrear_amount").text(taxdetails.arrearAmount);
                $("#prop_tax_amount_payable").text(taxdetails.payableAmount);
                $("#prop_tax_amount").text(taxdetails.grandTotal);
                if (taxdetails.noticeGenerated === "Y") {
                    var url = location.href.substring(0, location.href.lastIndexOf('/')) + "/generatePrivateNotice?zoneId=-1&ward=-1&propertyId=" + prop.propertyUniqueId;
                    //var url = location.href.substring(0, location.href.lastIndexOf('/')) + "/viewNotice?taxNo=" + taxdetails.taxNo;
                    EditPro.enableNotice(url);
                } else {
                    EditPro.disableNotice();
                }
            }


                for (var key in EditPro.propertyObject) {
                    var value = EditPro.propertyObject[key];
                    //console.log("key " + key + " " + " value " + value);

                    switch (key) {
                        case "zoneId":
                            $('#zoneId')[0].sumo.selectItem(value);
                            break;
                        case "ward":
                            $('#wardId')[0].sumo.selectItem(value);
                            break;
                        default :
                            if (value != null)
                                $("#" + key).val(value);
                    }
                }

                EditPro.floorDetails = floorList;
                EditPro.addFloorDetailsHtml('remove');
                EditPro.disablePropertyDetails();
                if ($("#enableProp").hasClass('d-none')) {
                    $("#enableProp").removeClass('d-none');
                }
                if (!$("#updateProp").hasClass('d-none')) {
                    $("#updateProp").addClass('d-none');
                }
                $('.floordetailsref').bind('click', false);
            } else {
                $("#accordion").addClass("hidden");
                $("#no_result_found").removeClass("hidden");
                return false;
            }

        }, 'json').always(function () {
            EditPro.removeMapElements();
            LOADER.hide();
        });
    },
    showHideFloorDetailsFields: function (flag, id) {
        if (flag === 'H') {
            $("#" + id).addClass("hidden");
        } else if (flag === 'S') {
            $("#" + id).removeClass("hidden");
        }
    },
    propertyIdChange: function () {
        var val = $("#propertyid").val();
        if (val === "-1") {
            $("#jOwnerId").find("label,select,input,div,p,span").attr("disabled", false);
            $("#jOwnerId").find("label,select,input,div,p,span").css("cursor", "pointer");
            $("#jOwnerId").find("p").css("background-color", "white");
            $("#jOccupierId").find("label,select,input,div,p,span").attr("disabled", false);
            $("#jOccupierId").find("label,select,input,div,p,span").css("cursor", "pointer");
            $("#jOccupierId").find("p").css("background-color", "white");
        } else {
            $("#jOwnerId").find("label,select,input,div,p,span").css("cursor", "not-allowed");
            $("#jOwnerId").find("label,select,input,div,p,span").attr("disabled", true);
            $("#jOwnerId").find("p").css("background-color", "darkgrey");
            $("#jOccupierId").find("label,select,input,div,p,span").css("cursor", "not-allowed");
            $("#jOccupierId").find("label,select,input,div,p,span").attr("disabled", true);
            $("#jOccupierId").find("p").css("background-color", "darkgrey");
        }
    },
    openSearchResults: function () {
        EditPro.discardFloorDetailsEditing();
        // $("#zoneId").SumoSelect().sumo.unload();
        // $("#wardId").SumoSelect().sumo.unload();

        $("#property_details_tab_div").addClass("hidden");
        $("#searchResults").removeClass("hidden");
        $("#property_section").addClass("hidden");
        $("#validateid").html('');
        $("#ward").val('');
        $("#prop_id_input").val('');
        $("#ownerid").val('');
        $("#occ_name").val('');
        $("#Phone_No").val('');
        $("#Easy_City_Code").val('');
        $("#locality").val('');
        $("#Locality").val('');
        $("#src_aadhar_no").val('');
        $("#category").val('');
        var total_row = $('#property_tab  tr').length;
        if (total_row == 2) {
            $('#property_tab').html('');
            EditPro.openSearchWindow();
        }

    },
    openSearchWindow: function () {
        $('#property_tab').html('');
        $("#property_details_tab_div").addClass("hidden");
        $("#searchResults").addClass("hidden");
        $("#property_section").removeClass("hidden");
        $("#validateid").html('');
    },
    openPropertyWindow: function () {
        $("#property_details_tab_div").removeClass("hidden");
        $("#searchResults").addClass("hidden");
        $("#property_section").addClass("hidden");
        $("#validateid").html('');
    },
    preserveTAXDetails: function (detailArr) {
        EditPro.taxDetailMap = {};
        if (detailArr === undefined || detailArr === null) {

        } else {
            for (var dt in detailArr) {
                var det = detailArr[dt];
                if (det != null) {
                    EditPro.taxDetailMap[det.propertyId] = det;
                }
            }
        }

    },
    preservePropertydetails: function (detailArr) {
        EditPro.propertyMap = {};
        if (detailArr === undefined || detailArr === null) {

        } else {
            for (var dt in detailArr) {
                var det = detailArr[dt];
                if (det != null) {
                    EditPro.propertyMap[det.propertyUniqueId] = det;
                }
            }
        }

    },
    resetWard: function (html) {
        if (html === undefined) {
            $("#ward").html("<option value='-1'>--Select Ward--</option>");
        } else {
            $("#ward").html(html);
        }
        //  $("#ward").SumoSelect().sumo.unload();
        //  $("#ward").SumoSelect({csvDispCount: 3, search: true, searchText: 'Enter here.'});
    },
//    resetOccupier: function (html) {
//        if (html === undefined) {
//            $("#occ_name").html("<option value='-1'>--Select Occupier Name--</option>");
//
//        } else {
//            $("#occ_name").html(html);
//        }
//     //   $("#occ_name").SumoSelect().sumo.unload();
//     //   $("#occ_name").SumoSelect({csvDispCount: 3, search: true, searchText: 'Enter here.'});
//
//        if (html === undefined) {
//            $("#occ_name").closest(".SumoSelect").find(".CaptionCont").addClass("disable_cls");
//            $("#occ_name").prop("disabled", true);
//        } else {
//            $("#occ_name").prop("disabled", false);
//            $("#occ_name").closest(".SumoSelect").removeClass("disabled");
//            $("#occ_name").closest(".SumoSelect").find(".CaptionCont").removeClass("disable_cls");
//        }
//    },
//    resetOwner: function (html) {
//        if (html === undefined) {
//            $("#ownerid").html("<option value='-1'>--Select Owner Name--</option>");
//            $("#ownerid").closest(".SumoSelect").find(".CaptionCont").addClass("disable_cls");
//        } else {
//            $("#ownerid").html(html);
//            $("#ownerid").closest(".SumoSelect").find(".CaptionCont").removeClass("disable_cls");
//        }
//      //  $("#ownerid").SumoSelect().sumo.unload();
//      //  $("#ownerid").SumoSelect({csvDispCount: 3, search: true, searchText: 'Enter here.'});
//
//        if (html === undefined) {
//            $("#ownerid").closest(".SumoSelect").find(".CaptionCont").addClass("disable_cls");
//            $("#ownerid").prop("disabled", true);
//        } else {
//            $("#ownerid").prop("disabled", false);
//            $("#ownerid").closest(".SumoSelect").removeClass("disabled");
//            $("#ownerid").closest(".SumoSelect").find(".CaptionCont").removeClass("disable_cls");
//        }
//
//    },
    resetPropertyId: function (html) {
        if (html === undefined) {
            $("#propertyid").html("<option value='-1'>--Select Property ID--</option>");
        } else {
            $("#propertyid").html(html);
        }
        //  $("#propertyid").SumoSelect().sumo.unload();
        // $("#propertyid").SumoSelect({csvDispCount: 3, search: true, searchText: 'Enter here.'});
        if (html === undefined) {
            $("#propertyid").prop("disabled", true);
            $("#propertyid").closest(".SumoSelect").find(".CaptionCont").addClass("disable_cls");
        } else {
            $("#propertyid").prop("disabled", false);
            $("#propertyid").closest(".SumoSelect").removeClass("disabled");
            $("#propertyid").closest(".SumoSelect").find(".CaptionCont").removeClass("disable_cls");
        }

    },
    wardMaster: {},
    getWard: function () {
//        if ($("#zone").val() === "-1") {
//        } else {
        $.post("getWards", {zone: $("#zone").val()}, function (result) {
            if (result != undefined) {
                var html = "";
                html += "<option value='-1'>--Select Ward--</option>";
                for (var i = 0; i < result.length; i++) {
                    html += "<option value='" + result[i].ward + "'>" + result[i].displayName + "</option>";
                    EditPro.wardMaster[result[i].ward] = result[i].displayName;
                }
                EditPro.resetWard(html);
            }
        }, 'json').always(function () {
        });
//        }
    },
    resetAadhar: function () {
        $("#src_aadhar_no").val("");
    },
    getZones: function () {
        $.post("loadZones", {}, function (result) {
            if (result != undefined) {
                var html = "";
                html += "<option value='-1'>--Select Zone--</option>";
                for (var i = 0; i < result.length; i++) {
                    html += "<option value='" + result[i].zoneId + "'>" + result[i].zoneName + "</option>";
                    EditPro.zoneMaster[result[i].zoneId] = result[i].zoneName;
                }
                $("#zone").html(html);
                $("#zone").SumoSelect({csvDispCount: 3, search: true, searchText: 'Enter here.'});
            }
        }, 'json').always(function () {
        });
    },
    setZonesToEditFiled: function () {
        var html = "";
        html += "<option value='-1'>--Select Zone--</option>";
        for (var key in EditPro.zoneMaster) {
            if (EditPro.zoneMaster.hasOwnProperty(key)) {
                html += "<option value='" + key + "'>" + EditPro.zoneMaster[key] + "</option>";
            }
        }
        $("#zoneId").html(html);
        $("#zoneId").SumoSelect({csvDispCount: 3, search: true, searchText: 'Enter here.'});
    },
    setWardToEditFiled: function (val) {
        var html = "";
        html += "<option value='-1'>--Select Zone--</option>";
        for (var key in EditPro.wardMaster) {
            if (EditPro.wardMaster.hasOwnProperty(key)) {
                html += "<option value='" + key + "'>" + EditPro.wardMaster[key] + "</option>";
            }
        }
        $("#wardId").html(html);
        $("#wardId").SumoSelect({csvDispCount: 3, search: true, searchText: 'Enter here.'});
//        $.post("getWards", {zone: val}, function (result) {
//            if (result != undefined) {
//                html += "<option value='-1'>--Select Ward--</option>";
//                for (var i = 0; i < result.length; i++) {
//                    html += "<option value='" + result[i].ward + "'>" + result[i].displayName + "</option>";
//                }
//                $("#wardId").html(html);
//                $("#wardId").SumoSelect({csvDispCount: 3, search: true, searchText: 'Enter here.'});
//            }
//        }, 'json').always(function () {
//        });
    }, addFloor: function () {
        //alert("add floor");
        EditPro.floorn = parseInt(EditPro.floorn) + 1;
        EditPro.floorCount = parseInt(EditPro.floorCount) + 1;
        //alert("add floor "+EditPro.floorn); 
        //alert(EditPro.floorCount);
        //var tr_html="<tr id=><td>EditPro.floorCount</td>";
        var tr_html = "<tr id=''><td>" + ($("#floorDetatilsTable tbody tr").length + 1) + "</td><td><select style='min-width:80px'  class='form-control' id='floorDetails" + EditPro.floorn + ".floorType' name='floorDetails[" + EditPro.floorn + "].floorType'      >";
        tr_html = tr_html + "<option value='-1' >Select</option>";
        tr_html = tr_html + "<option value='GF'>GF</option>";
        tr_html = tr_html + "<option value='1F'>1F</option>";
        tr_html = tr_html + "<option value='2F'>2F</option>";
        tr_html = tr_html + "<option value='3F'>3F</option>";
        tr_html = tr_html + "<option value='4F'>4F</option>";
        tr_html = tr_html + "<option value='5F'>5F</option>";
        tr_html = tr_html + "<option value='6F'>6F</option>";
        tr_html = tr_html + "<option value='7F'>7F</option>";
        tr_html = tr_html + "<option value='8F'>8F</option>";
        tr_html = tr_html + "<option value='9F'>9F</option>";
        tr_html = tr_html + "<option value='10F'>10F</option>";
        tr_html = tr_html + "<option value='11F'>11F</option>";
        tr_html = tr_html + "<option value='12F'>12F</option>";
        tr_html = tr_html + "<option value='13F'>13F</option>";
        tr_html = tr_html + "<option value='14F'>14F</option>";
        tr_html = tr_html + "<option value='15F'>15F</option>";
        tr_html = tr_html + "<option value='UG' '>UG</option>";
        tr_html = tr_html + "<option value='VP' >VP</option>";
        tr_html = tr_html + "<option value='UPG' >UPG</option>";
        tr_html = tr_html + "<option value='UG1' >UG1</option>";
        tr_html = tr_html + "</select></td>";
        tr_html = tr_html + "<td><select class='form-control' id='floorDetails" + EditPro.floorn + ".propertyUse' name='floorDetails[" + EditPro.floorn + "].propertyUse'     >";
        tr_html = tr_html + "<option value='-1'  >Select</option>";
        tr_html = tr_html + "<option value='Residential'  >Residential</option>";
        tr_html = tr_html + "<option value='Commercial' >Commercial</option>";
        tr_html = tr_html + "<option value='Other' >Other</option>";
        tr_html = tr_html + "</select></td>";
        tr_html = tr_html + "<td><input class='form-control' id='floorDetails" + EditPro.floorn + ".propertySubType' name='floorDetails[" + EditPro.floorn + "].propertySubType' class='form-control'  type='text'  ></td>";
        tr_html = tr_html + "<td><input class='form-control' id='floorDetails" + EditPro.floorn + ".carpetArea' name='floorDetails[" + EditPro.floorn + "].carpetArea'    type='text' ></td>";
        tr_html = tr_html + "<td><select class='form-control' id='floorDetails" + EditPro.floorn + ".constructionType' name='floorDetails[" + EditPro.floorn + "].constructionType'   type='text'  >";
        tr_html = tr_html + "<option value='-1'>Select</option>";
        tr_html = tr_html + "<option value='RCC'>RCC</option>";
        tr_html = tr_html + "<option value='ASB'>ASB</option>";
        tr_html = tr_html + "<option value='OTHER'>OTHER</option>";
        tr_html = tr_html + "<option value='VP'>VP</option>";
        tr_html = tr_html + "</select></td>";
        tr_html = tr_html + "<td><select class='form-control' id='floorDetails" + EditPro.floorn + ".selfRent' name='floorDetails[" + EditPro.floorn + "].selfRent'   type='text'  >";
        tr_html = tr_html + "<option value='-1'  >Select</option>";
        tr_html = tr_html + "<option value='S'  >Self</option>";
        tr_html = tr_html + "<option value='R'>Rent</option>";
        tr_html = tr_html + "</select></td>";
        tr_html = tr_html + "<td><input class='form-control' id='floorDetails" + EditPro.floorn + ".presumeRent' name='floorDetails[" + EditPro.floorn + "].presumeRent' class='form-control'  type='text'  ></td>";
        tr_html = tr_html + "<td><input class='form-control' id='floorDetails" + EditPro.floorn + ".rentedValue' name='floorDetails[" + EditPro.floorn + "].rentedValue' class='form-control'  type='text'  ></td>";
        tr_html = tr_html + "<td><input class='form-control' id='floorDetails" + EditPro.floorn + ".editData' name='floorDetails[" + EditPro.floorn + "].editData' type='hidden' value='new' ></td> ";
        //tr_html=tr_html+"<td><input class='form-control' id='floorDetails"+ test.floorn + ".deleteData' name='floorDetails[" + test.floorn + "].deleteData' type='checkbox' value='Y'></td>";
        tr_html = tr_html + "<td><input class='form-control' id='floorDetails" + EditPro.floorn + ".propertyFloorId' name='floorDetails[" + EditPro.floorn + "].propertyFloorId' type='hidden' value='0'  ></td>";
        tr_html = tr_html + "<td><input class='form-control' id='floorDetails" + EditPro.floorn + ".propertyFloorId'  type='button' value='Remove Row' onclick='EditPro.removeRow();'  ></td>";
        $("#floorDetatilsTable tbody").append(tr_html);
    }, removeRow: function () {
        $('#floorDetatilsTable tr').click(function () {
            $(this).remove();
            return false;
        });
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
    },
    houseNo: function () {
        var options = {
            url: function (phrase) {
                return "getHouseLst";
            },
            getValue: function (element) {
                return element.property_house_no;
            },
            ajaxSettings: {
                method: "POST",
                data: {}
            },
            preparePostData: function (data) {
                data.searchStr = $("#house_no").val()
                return data;
            },
            requestDelay: 400,
            autoFocus: true,
            placeholder: "Search House Number",
            list: {
                match: {
                    enabled: true
                },
                onChooseEvent: function () {
                    var value = $("#house_no").getSelectedItemData().property_house_no;
                    $("#house_no").val(value).trigger("change");
                },
                onHideListEvent: function () {
                    var val = $("#house_no").val();
                    if (val == "") {
                        $("#house_no").val("")
                    }

                }

            }
        };
        $("#house_no").easyAutocomplete(options);
    },
    disablePropertyDetails: function () {

        $("div#newProperty input[type=text]").each(function () {
            var input = $(this);
            if (input[0].id !== "") {
                iddd = input[0].id;
                $("#" + iddd + "").prop("disabled", true);
            }
        });
        $("div#newProperty select").each(function () {
            var select = $(this);
            if (select[0].id !== "") {
                idd = select[0].id;
                $("#" + idd + "").prop("disabled", true);
            }
        });
    },
    enablePropertyDetails: function () {

        $("div#newProperty input[type=text]").each(function () {
            var input = $(this);
            if (input[0].id !== "") {
                iddd = input[0].id;
                $("#" + iddd + "").prop("disabled", false);
            }
        });
        $("div#newProperty select").each(function () {
            var select = $(this);
            if (select[0].id !== "") {
                idd = select[0].id;
                $("#" + idd + "").prop("disabled", false);
            }
        });
        $("#updateProp").removeClass('d-none');
        $("#enableProp").addClass('d-none');
        $("#propertyUniqueId").prop("disabled", true);
        $('.floordetailsref').unbind('click', false);
    },
    collectTax: function () {
        EditPro.fillBankList();
        EditPro.fillPaymentModes();
        $("#sl_payment_propId").text(EditPro.propertyObject.propertyUniqueId);
        col_propId = $("#sl_payment_propId").text()
        $("#sl_payable_amount").text(EditPro.taxDetailMap[col_propId].payableAmount);
        $("#sl_payment_amount").val(EditPro.taxDetailMap[col_propId].payableAmount);
        $("#sl_arrear_amount").val(EditPro.taxDetailMap[col_propId].arrearAmount);
        $("#sl_interest_amount").val(EditPro.taxDetailMap[col_propId].delayPaymentCharges);
        $("#sl_tax_amount").val(EditPro.taxDetailMap[col_propId].propertyTax);
        $("#enc_cheque_dd_num").val("");
        $("#enc_cheque_dd_date").val("");
        $("#enc_banks_branch").val("");
        $("#newProperty").addClass("hidden");
        $("#taxPaymentView").removeClass("hidden");
    },
    showpropertyWindow: function () {
        $("#validateid").html('');
        $("#taxPaymentView").addClass("hidden");
        $("#newProperty").removeClass("hidden");
    },
    bankMaster: {},
    fillBankList: function () {
        $.ajax({
            type: "POST",
            url: "bankList",
            contentType: "application/json; charset=utf-8",
            success: function (data) {
                var banks = JSON.parse(data);
                var strHTML = "<option value='-1'>-- Select Bank --</option>";
                for (var b in banks) {
                    var bank = banks[b];
                    strHTML += "<option value='" + bank.bankId + "'> " + bank.bankName + " </option>";
                    EditPro.bankMaster[bank.bankId] = bank.bankName;
                }
                $("#enc_banks").html(strHTML);
            },
            error: function () {
            }
        });
    },
    fillPaymentModes: function () {
        var strHTML = "<option value='-1'>--Select Payment Mode --</option>";
        strHTML += "<option value='CSH'>Cash</option>";
        strHTML += "<option value='CHQ'>Cheque</option>";
        strHTML += "<option value='DDF'>Demand Draft</option>";
        strHTML += "<option value='POS_DEVICE'>POS</option>";
        //   strHTML += "<option value='PAYTM_REF_NO'>PayTM Ref. No</option>";
        strHTML += "<option value='BHIM_UPI'>BHIM UPI</option>";
        strHTML += "<option value='NEFT_RTGS'>NEFT/RTGS </option>";
        $("#enc_paymentMode").html(strHTML);
    },
    validatePayment: function () {

        //$("#sl_payment_amount").val(parseFloat($("#sl_arrear_amount").val())+parseFloat($("#sl_interest_amount").val())+parseFloat($("#sl_tax_amount").val()));
        $("#validateid").html('');
        if (VALIDATOR.checkIfAlphabet($("#sl_payment_amount").val()) || $("#sl_payment_amount").val() === "") {
            $("#validateid").html("Please enter valid amount.");
            //alert("Please enter valid amount.");
        } else if (isNaN($("#sl_payment_amount").val())) {
            $("#validateid").html("Please enter valid amount.");
            // alert("Please enter valid amount.");
        } else {

            if ($("#enc_paymentMode option:selected").val() === "-1") {
                $("#validateid").html("Please select payment mode.");
                //  alert("Please select payment mode.");
            } else if ($("#enc_paymentMode option:selected").val() === "CSH" && parseFloat($("#sl_payment_amount").val()) > 500) {
                $("#validateid").html("Max limit of cash payment is 500 INR.");
                //alert("Max limit of cash payment is 500 INR.");
            } else if ($("#enc_paymentMode option:selected").val() !== "CSH" && $("#enc_cheque_dd_num").val() === "") {
                $("#validateid").html("Please provide Cheque/DD number/POS_ref_no/BHIM UPI.");
                // alert("Please provide Cheque/DD number/POS_ref_no/BHIM UPI.");
            } else if (($("#enc_paymentMode option:selected").val() !== "CSH" && $("#enc_paymentMode option:selected").val() !== "POS_DEVICE" && $("#enc_paymentMode option:selected").val() !== "BHIM_UPI") && $("#enc_paymentMode option:selected").val() !== "NEFT_RTGS" && $("#enc_cheque_dd_date").val() === "") {
                $("#validateid").html("Please fill Cheque/DD date.");
                //  alert("Please fill Cheque/DD date.");
            } else if ($("#enc_banks option:selected").val() === "-1" && ($("#enc_paymentMode option:selected").val() !== "CSH" && $("#enc_paymentMode option:selected").val() !== "POS_DEVICE" && $("#enc_paymentMode option:selected").val() !== "NEFT_RTGS" && $("#enc_paymentMode option:selected").val() !== "BHIM_UPI")) {
                $("#validateid").html("Please select bank.");
                // alert("Please select bank.");
            } else if ($("#enc_banks_branch").val() === "" && ($("#enc_paymentMode option:selected").val() !== "CSH" && $("#enc_paymentMode option:selected").val() !== "POS_DEVICE" && $("#enc_paymentMode option:selected").val() !== "NEFT_RTGS" && $("#enc_paymentMode option:selected").val() !== "BHIM_UPI")) {
                $("#validateid").html("Please fill bank branch.");
                //alert("Please fill bank branch.");
            } else if ($("#enc_banks_ifsc").val() === "" && ($("#enc_paymentMode option:selected").val() !== "CSH" && $("#enc_paymentMode option:selected").val() !== "POS_DEVICE" && $("#enc_paymentMode option:selected").val() !== "NEFT_RTGS" && $("#enc_paymentMode option:selected").val() !== "BHIM_UPI")) {
                $("#validateid").html("Please fill bank IFSC code.");
                //alert("Please fill bank IFSC code.");
            }
            else if ($("#payerName").val() === "") {
                $("#validateid").html("Please fill payee name");
                //alert("Please fill payee name");
            } else if ($("#contactNo").val() === "") {
                $("#validateid").html("Please fill mobile no.");
                //alert("Please fill mobile no.");
            } else if ($("#contactNo").val() != "" && $("#contactNo").val().length != 10) {
                $("#validateid").html("Contact no. must be 10 digit number");
                //alert("Contact no. must be 10 digit number");
            } else if ($("#paymentPeriod").val() === "") {
                $("#validateid").html("Please fill payment period");
                //alert("Please fill payment period");
            } else {
                EditPro.makePayment();
            }
        }

    },
    makePayment: function () {

        var payBean = {};
        payBean.propId = EditPro.propertyObject.propertyUniqueId;
        payBean.amountPaid = $("#sl_payment_amount").val();
        payBean.bankName = $("#enc_banks option:selected").val();
        payBean.bankBranch = $("#enc_banks_branch").val();
        payBean.chequeDDDate = $("#enc_cheque_dd_date").val();
        payBean.chequeDDNum = $("#enc_cheque_dd_num").val();
        payBean.paymentMode = $("#enc_paymentMode option:selected").val();
        payBean.remarks = $("#payment_note").val();
        payBean.payerName = $("#payerName").val();
        payBean.contactNo = $("#contactNo").val();
        payBean.paymentPeriod = $("#paymentPeriod").val();
        payBean.ifscCode = $("#enc_banks_ifsc").val();
        $.post("collectPayment", {param: JSON.stringify(payBean)}, function (result) {
            var d = result;
            $("#validateid").html('');
            if (d.code === 200) {
                EditPro.payBean = d.data;
                $("#pay_message").html("Payment of Rs." + EditPro.payBean.amountPaid + " has been recieved for property " + EditPro.payBean.propId + ", TAX No. " + EditPro.payBean.taxNo + ".");
                EditPro.showReceiptWindow();
            } else {
                $("#validateid").html("Payment not recieved by system.");
                // alert("Payment not recieved by system.");
            }

        }, '').always(function () {
        });
    },
    showReceiptWindow: function () {
        $("#taxPaymentView").addClass("hidden");
        $("#submitMesage").removeClass("hidden");
    },
    printCollectionReceipt: function () {
        //alert("dddd");
        var payBean = EditPro.payBean;
        $("#validateid").html('');
        if (payBean !== undefined) {
            //alert("payBean.propId "+payBean.propId);
            //alert(payBean.payRefId);
            var prop = EditPro.propertyObject.propertyUniqueId
            var print_html = "";
            var amtInWords = convertNumberToWords(payBean.amountPaid);
            var remainedTax = parseFloat(payBean.amountDemand).toFixed(2) - parseFloat(payBean.amountPaid).toFixed(2);
            var bankName = EditPro.bankMaster[payBean.bankName];
            $("#as_uniqueId1").val(payBean.propId);
            $("#as_uniqueId2hiddeen").val(payBean.propId);
            $("#as_receiptId").val(payBean.payRefId);
            $("#taxcollectionForm").submit();
            return true;
        } else {
            $("#validateid").html("Unable to process request.");
            // alert("Unable to process request.");
            return false;
        }
    },
    showResultWindow: function () {
        document.location.reload(true);
    },
    makeTable: function (correctionData) {
        $('#actionHistory').find('tbody').empty();
        var fl = "";
        if (correctionData.length !== 0) {
            for (var j = 0; j < correctionData.length; j++) {
                var dt = correctionData[j];
                dt.action_by = dt.action_by === undefined ? "" : dt.action_by;
                //dt.pfFloorName
                //console.log(dt);
                floorCount = j;
                dt.action_by_name = dt.action_by_name === undefined || dt.action_by_name == null ? "" : dt.action_by_name;
                dt.action_remarks = dt.action_remarks === undefined || dt.action_remarks == null ? "" : dt.action_remarks;
                dt.action_taken = dt.action_taken === undefined || dt.action_taken == null ? "" : dt.action_taken;
                dt.correction_id = dt.correction_id === undefined || dt.correction_id == null ? "" : dt.correction_id;
                dt.entrydatetime = dt.entrydatetime === undefined || dt.entrydatetime == null ? "" : dt.entrydatetime;
                dt.forward_to = dt.forward_to === undefined || dt.forward_to == null ? "" : dt.forward_to;
                dt.forward_to_name = dt.forward_to_name === undefined || dt.forward_to_name == null ? "" : dt.forward_to_name;
                dt.property_id = dt.property_id === undefined || dt.property_id == null ? "" : dt.property_id;
                fl += "<tr>";
                fl += "<td >" + (j + 1) + "</td>";
                fl += "<td >" + dt.action_by_name + "</td>";
                fl += "<td >" + dt.entrydatetime + "</td>";
                fl += "<td >" + dt.action_taken + "</td>";
                fl += "<td >" + dt.action_remarks + "</td>";
                fl += "</tr>";
            }
        } else {
            fl += "<tr style = 'text-align : center' >";
            fl += "<td colspan='5'>No Data Available. </td>";
            fl += "</tr>";
        }

        $('#actionHistory').find('tbody').append(fl);
    },
    openCurUrl: function () {
        window.open(window.location.href, "_self");
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
        $("#validateid").html('');
        $.post("getPropertyImage", {property_unique_id: property_unique_id}, function (data) {
            EditPro.currentImage = 0;
            if (data.length > 0 && data !== "") {
                EditPro.imgArray = data;
                if (EditPro.imgArray.length > 1) {
                    $("#img-next-btn").removeClass("disabled");
                }
                $("#cntrimg").attr('src', 'data:image/jpg;base64,' + EditPro.imgArray[0]);
                $("#help-modal").modal({backdrop: "static"});
                $("#help-modal").show();
            } else {
                $("#validateid").html("Property image not found.");
                //alert("Property image not found.");
                $("#img_display").hide();
                $("#cntrimg").attr('src', '');
                return false;
            }

        }).always(function () {
            LOADER.hide();
        });
    },
    plotMarker: function (inOutFlag, latitude, propertyLongitude, imageicon, infohtml, ward, loc, subLoc,propUniqueId) {
        // alert("fdjfdjhf");
//        EditPro.wardBuilder = "";
//        EditPro.localityBuilder = "";
//        EditPro.subLocalityBuilder = "";
        if (inOutFlag == "out") {
            $(".modal-title").html("Property Id ("+ propUniqueId +") located on map.");
            EditPro.wardBuilder = ward.toUpperCase();
            EditPro.localityBuilder = loc.toUpperCase();
            EditPro.subLocalityBuilder = subLoc.toUpperCase();
        } else {
            $(".modal-title").html("Property Id ("+ EditPro.inner_map_UniqueId +") located on map.");
//            latitude = "22.02";
//            propertyLongitude = "77.2";
            imageicon = "res/img/11_large.png";
//            infohtml = "ss";
            propertyLongitude = EditPro.inner_map_Longitude
            EditPro.localityBuilder = EditPro.inner_map_Locality.toUpperCase();
            latitude = EditPro.inner_map_Latitude;
            EditPro.wardBuilder = EditPro.inner_map_ward.toUpperCase();
            EditPro.subLocalityBuilder = EditPro.inner_map_Sublocality.toUpperCase();
//            infohtml = "";
//            infohtml += "<ul>";
//            infohtml += "<li> Address: " + EditPro.inner_map_Address + "</li>";
//            infohtml += "<li> Unique id: " + EditPro.inner_map_UniqueId + "</li>";
//            infohtml += "<li> Owner Name: " + EditPro.inner_map_Owner + "</li>";
//            infohtml += "</ul>";

            var infohtml = "";
            infohtml += "<div class=popup_area><div class=headerSection>" + EditPro.inner_map_Owner + "(" + EditPro.inner_map_UniqueId + ")</div><div class=content_area_sec><ul>";
            infohtml += "<li> <b>Address:</b> " + EditPro.inner_map_Address + "</li>";
//            infohtml += "<li> <b>Unique id:</b> " + EditPro.inner_map_UniqueId + "</li>";
//            infohtml += "<li> <b>Owner Name:</b> " + EditPro.inner_map_Owner + "</li>";
            infohtml += "</ul></div></div>";
        }
        $('#myModal').modal('show');
        if (MapHandler.map == null) {
            MapHandler.initialise('map', 20.27, 73.01, 14);
        }
        if (EditPro.propMarker.length > 0) {
            MapHandler.removeMarkers(EditPro.propMarker);
        }
        if (EditPro.wardGeomLayer.length > 0) {
            MapHandler.removeMarkers(EditPro.wardGeomLayer);
            EditPro.wFlag = false;
        } else if (EditPro.locGeomLayer.length > 0) {
            MapHandler.removeMarkers(EditPro.locGeomLayer);
            EditPro.lFlag = false;
        } else if (EditPro.subLocGeomLayer.length > 0) {
            MapHandler.removeMarkers(EditPro.subLocGeomLayer);
            EditPro.sLFlag = false;
        }
//        }
        $("#locl_ward").prop('checked', false);
        $("#locl_Locality").prop('checked', false);
        $("#locl_subLocality").prop('checked', false);
        EditPro.propMarker.push(MapHandler.plotMarker(latitude, propertyLongitude, imageicon, infohtml));
        setTimeout(function () {
            MapHandler.map.invalidateSize();
            var featureGroup = L.featureGroup(EditPro.propMarker);
            MapHandler.map.fitBounds(featureGroup.getBounds());
        }, 500);
    },
    plotWardGeom: function (type) {
        //  alert("adgja");
        EditPro.geomtype = $(type).attr("name");
        var dataList;
        if (EditPro.geomtype == "ward_check") {
            dataList = EditPro.wardBuilder;
        } else if (EditPro.geomtype == "locality_check") {
            dataList = EditPro.localityBuilder;
        } else if (EditPro.geomtype == "sub_check") {
            dataList = EditPro.subLocalityBuilder;
        }
        debugger;
        if ($(type).prop("checked") == true) {
            if ((EditPro.geomtype == "ward_check" && EditPro.wFlag != true) || (EditPro.geomtype == "locality_check" && EditPro.lFlag != true) || (EditPro.geomtype == "sub_check" && EditPro.sLFlag != true)) {
                if (dataList.length > 0) {
                    $.post("getGeomData",
                            {
                                type: EditPro.geomtype,
                                datalist: dataList,
                            }, function (data) {
                        console.log("data");
                        for (var i = 0; i < data.length; i++) {
                            debugger;
                            var geomData = data[i].geom;
                            //var geometry_points = geomData.coordinates[0];
                            //MapHandler.plotGeom(geomData, 'blue', 'skyblue', 1, 1, 1);
                            if (EditPro.geomLoyer != null)
                            {//wardGeomLayer: new Array(),
                                //locGeomLayer: new Array(),
                                //subLocGeomLayer: new Array(),
//                wgeomData : new Array(),
//                        lgeomData : new Array(),
//                        sLgeomData : new Array(),
                                MapHandler.removeMarkers(EditPro.geomLoyer);
                            }
                            if (EditPro.geomtype == "ward_check") {
                                var layer = MapHandler.plotGeom(JSON.parse(geomData), 'red', 'skyblue', 0.2);
                                EditPro.wardGeomLayer.push(layer);
                                EditPro.wFlag = true;
                                EditPro.wgeomData.push(JSON.parse(geomData));
                            } else if (EditPro.geomtype == "locality_check") {
                                var layer = MapHandler.plotGeom(JSON.parse(geomData), 'green', 'skyblue', 0.2);
                                EditPro.locGeomLayer.push(layer);
                                EditPro.lFlag = true;
                                EditPro.lgeomData.push(JSON.parse(geomData));
                            } else if (EditPro.geomtype == "sub_check") {
                                var layer = MapHandler.plotGeom(JSON.parse(geomData), 'blue', 'skyblue', 0.2);
                                EditPro.subLocGeomLayer.push(layer);
                                EditPro.sLFlag = true;
                                EditPro.sLgeomData.push(JSON.parse(geomData));
                            }
//                        var layer = MapHandler.plotGeom(JSON.parse(geomData), 'blue', 'skyblue', 0.2);
//                        EditPro.geomLoyer.push(layer);
                        }
                    }, 'json').always(function () {
                        setTimeout(function () {
                            MapHandler.map.invalidateSize();
                            debugger;
                            if (EditPro.geomtype == "ward_check") {
                                var featureGroup = L.featureGroup(EditPro.wardGeomLayer);
                                MapHandler.map.fitBounds(featureGroup.getBounds());
                            } else if (EditPro.geomtype == "locality_check") {
                                var featureGroup = L.featureGroup(EditPro.locGeomLayer);
                                MapHandler.map.fitBounds(featureGroup.getBounds());
                            } else if (EditPro.geomtype == "sub_check") {
                                var featureGroup = L.featureGroup(EditPro.subLocGeomLayer);
                                MapHandler.map.fitBounds(featureGroup.getBounds());
                            }
                        }, 500);
                    });
                } else {
                    if (EditPro.geomtype == "ward_check") {
                        $(".modalAlert_map").html('Ward Geometry not available.');
                    } else if (EditPro.geomtype == "locality_check") {
                        $(".modalAlert_map").html('Locality Geometry not available.');
                    } else if (EditPro.geomtype == "sub_check") {
                        $(".modalAlert_map").html('Sub Locality Geometry not available.');
                    }

                }
            } else {
                alert("already plotted");
                if (EditPro.geomtype == "ward_check") {
                    var layer = MapHandler.plotGeom(EditPro.wgeomData, 'red', 'skyblue', 0.2);
                    EditPro.wardGeomLayer.push(layer);
                } else if (EditPro.geomtype == "locality_check") {
                    var layer = MapHandler.plotGeom(EditPro.lgeomData, 'green', 'skyblue', 0.2);
                    EditPro.locGeomLayer.push(layer);
                } else if (EditPro.geomtype == "sub_check") {
                    var layer = MapHandler.plotGeom(EditPro.sLgeomData, 'blue', 'skyblue', 0.2);
                    EditPro.subLocGeomLayer.push(layer);
                }
            }
        } else {
//            if (EditPro.geomLoyer != null) {
//                MapHandler.removeMarkers(EditPro.geomLoyer);
//            }
            if (EditPro.geomtype == "ward_check") {
                MapHandler.removeMarkers(EditPro.wardGeomLayer);
                EditPro.wFlag = false;
                $(".modalAlert_map").html('');
            } else if (EditPro.geomtype == "locality_check") {
                MapHandler.removeMarkers(EditPro.locGeomLayer);
                EditPro.lFlag = false;
                $(".modalAlert_map").html('');
            } else if (EditPro.geomtype == "sub_check") {
                MapHandler.removeMarkers(EditPro.subLocGeomLayer);
                EditPro.sLFlag = false;
                $(".modalAlert_map").html('');
            }
        }
    },
    removeMapElements: function () {
        if (EditPro.propMarker.length > 0) {
            MapHandler.removeMarkers(EditPro.propMarker);
        }
        if (EditPro.wardGeomLayer.length > 0) {
            MapHandler.removeMarkers(EditPro.wardGeomLayer);
            EditPro.wFlag = false;
        } else if (EditPro.locGeomLayer.length > 0) {
            MapHandler.removeMarkers(EditPro.locGeomLayer);
            EditPro.lFlag = false;
        } else if (EditPro.subLocGeomLayer.length > 0) {
            MapHandler.removeMarkers(EditPro.subLocGeomLayer);
            EditPro.sLFlag = false;
        }
    }
};
$(document).ready(function () {
    EditPro.getRentableValues();
    EditPro.applyNumericaOnly();
    //  $("#propertyid").SumoSelect({csvDispCount: 3, search: true, searchText: 'Enter here.'});
    //  $("#ownerid").SumoSelect({csvDispCount: 3, search: true, searchText: 'Enter here.'});
    // $("#occ_name").SumoSelect({csvDispCount: 3, search: true, searchText: 'Enter here.'});
    //  $("#ward").SumoSelect({csvDispCount: 3, search: true, searchText: 'Enter here.'});
    //  $("#locality").SumoSelect({csvDispCount: 3, search: true, searchText: 'Enter here.'});

    //  EditPro.resetOccupier();
    // EditPro.resetOwner();
    //  EditPro.resetPropertyId();
    // EditPro.resetAadhar();
    //    $("#src_aadhar_no").addClass("disable_cls");
    //  $("#src_aadhar_no").prop("disabled", true);
    // EditPro.getZones();
    //  EditPro.getWard();

});
//$(document).on("change", "#zone", function () {
//    EditPro.resetWard();
//    EditPro.resetOccupier();
//    EditPro.resetOwner();
//    EditPro.resetPropertyId();
//    EditPro.getWard();
//    $("#src_aadhar_no").addClass("disable_cls");
//    $("#src_aadhar_no").prop("disabled", true);
//});

//$(document).on("change", "#propertyid", function () {
//    if ($("#propertyid option:selected").val() !== "-1") {
//        $("#ownerid,#occ_name").prop("disabled", true);
//        $("#ownerid,#occ_name").val("-1");
//        $("#ownerid,#occ_name").closest(".SumoSelect").find(".CaptionCont span").text("");
//        $("#ownerid,#occ_name").closest(".SumoSelect").find(".CaptionCont").addClass("disable_cls");
//
//    } else {
//        $("#ownerid,#occ_name").prop("disabled", false);
//        $("#ownerid,#occ_name").closest(".SumoSelect").find(".CaptionCont").removeClass("disable_cls");
//
//    }
//
//});

//$(document).on("change", "#ward", function () {
//    $("#zone").val(zoneWardMaster[$(this).val()]);
//    // EditPro.resetOccupier();
////    EditPro.resetOwner();
//    EditPro.resetPropertyId();
//    EditPro.resetAadhar();
//    EditPro.resetLocality();
//    if ($("#ward").val() !== "-1") {
//        EditPro.getLocality();
//        $("#src_aadhar_no").removeClass("disable_cls");
//        $("#src_aadhar_no").prop("disabled", false);
//    }
//    //EditPro.getSearchCriteria();
//});

$(document).on("change", "#enc_paymentMode", function () {
    if ($(this).val() === "CSH") {

        $("#enc_cheque_dd_num").val("");
        $("#enc_cheque_dd_date").val("");
        $("#enc_banks").val("-1");
        $("#enc_banks_branch").val("");
        $("#enc_banks_ifsc").val("");
        $("#enc_cheque_dd_num").attr("disabled", true);
        $("#enc_cheque_dd_date").attr("disabled", true);
        $("#enc_banks").attr("disabled", true);
        $("#enc_banks_branch").attr("disabled", true);
        $("#enc_banks_ifsc").attr("disabled", true);
    }

    else if ($(this).val() === "POS_DEVICE") {

        $("#enc_cheque_dd_num").val("");
        $("#enc_cheque_dd_date").val("");
        $("#enc_banks").val("-1");
        $("#enc_banks_branch").val("");
        $("#enc_banks_ifsc").val("");
        $("#enc_cheque_dd_num").attr("disabled", false);
        $("#enc_cheque_dd_date").attr("disabled", true);
        $("#enc_banks").attr("disabled", true);
        $("#enc_banks_branch").attr("disabled", true);
        $("#enc_banks_ifsc").attr("disabled", true);
    }

    else if ($(this).val() === "BHIM_UPI") {

        $("#enc_cheque_dd_num").val("");
        $("#enc_cheque_dd_date").val("");
        $("#enc_banks").val("-1");
        $("#enc_banks_branch").val("");
        $("#enc_banks_ifsc").val("");
        $("#enc_cheque_dd_num").attr("disabled", false);
        $("#enc_cheque_dd_date").attr("disabled", true);
        $("#enc_banks").attr("disabled", true);
        $("#enc_banks_branch").attr("disabled", true);
        $("#enc_banks_ifsc").attr("disabled", true);
    } else if ($(this).val() === "NEFT_RTGS") {

        $("#enc_cheque_dd_num").val("");
        $("#enc_cheque_dd_date").val("");
        $("#enc_banks").val("-1");
        $("#enc_banks_branch").val("");
        $("#enc_banks_ifsc").val("");
        $("#enc_cheque_dd_num").attr("disabled", false);
        $("#enc_cheque_dd_date").attr("disabled", true);
        $("#enc_banks").attr("disabled", true);
        $("#enc_banks_branch").attr("disabled", true);
        $("#enc_banks_ifsc").attr("disabled", true);
    }
    else {
        $("#enc_cheque_dd_num").attr("disabled", false);
        $("#enc_cheque_dd_date").attr("disabled", false);
        $("#enc_banks").attr("disabled", false);
        $("#enc_banks_branch").attr("disabled", false);
        $("#enc_banks_ifsc").attr("disabled", false);
    }
});
//$('#myModal').on('hidden', function () {
//
//});
//$('#myModal').on('hidden.bs.modal', function () {
//  alert("ag");
//});
