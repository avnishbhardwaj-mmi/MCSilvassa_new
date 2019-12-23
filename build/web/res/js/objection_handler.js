
var DATEPICKER = {
    today: null,
    init: function () {
        $("#obj_fromDate").datepicker({
            onSelect: function (selected) {
                var dt = new Date(selected);
                dt.setDate(dt.getDate() + 1);
                $("#obj_toDate").datepicker("option", "minDate", dt);
            }});
        $("#obj_toDate").datepicker({onSelect: function (selected) {
                var dt = new Date(selected);
                dt.setDate(dt.getDate() - 1);
                $("#obj_fromDate").datepicker("option", "maxDate", dt);
            }});
        DATEPICKER.setMaxDate($("#obj_fromDate"), this.today);
        DATEPICKER.setMaxDate($("#obj_toDate"), this.today);
    },
    setMinDate: function ($element, date) {
        $element.datepicker("option", "minDate", date);
    },
    setMaxDate: function ($element, date) {
        $element.datepicker("option", "maxDate", date);
    },
    validate: function (fromDay, from_month, from_year, toDay, to_month, to_year) {

        var from_date = new Date();
        from_date.setDate(fromDay);
        from_date.setMonth(parseInt(from_month) - 1);
        from_date.setYear(from_year);

        var to_date = new Date();
        to_date.setDate(toDay);
        to_date.setMonth(parseInt(to_month) - 1);
        to_date.setYear(to_year);

        return (from_date.getTime() <= to_date.getTime());

    }

}

$(document).ready(function () {
    $("#obj_main_menu").addClass("active");

//    $("#obj_prop_id").SumoSelect({csvDispCount: 3, search: true, searchText: 'Enter here.'});
//    $("#obj_occupier").SumoSelect({csvDispCount: 3, search: true, searchText: 'Enter here.'});
//    $("#ward").SumoSelect({csvDispCount: 3, search: true, searchText: 'Enter here.'});
//    $("#obj_owner").SumoSelect({csvDispCount: 3, search: true, searchText: 'Enter here.'});
////    $("#finance_year").SumoSelect({csvDispCount: 3, search: true, searchText: 'Enter here.'});
//    $("#obj_objectionStatus").SumoSelect({csvDispCount: 3, search: true, searchText: 'Enter here.'});


//    HNDL_OBJ.resetOccupier();
//    HNDL_OBJ.resetOwner();
//    HNDL_OBJ.resetPropertyId();


//    $.post("loadZones", {}, function (result) {
//        if (result != undefined) {
//            var html = "";
//            html += "<option value='-1'>--Select Zone--</option>";
//            for (var i = 0; i < result.length; i++) {
//                html += "<option value='" + result[i].zoneId + "'>" + result[i].zoneName + "</option>";
//                HNDL_OBJ.zoneMaster[result[i].zoneId] = result[i].zoneName;
//            }
//            $("#zone").html(html);
//            $("#zone").SumoSelect({csvDispCount: 3, search: true, searchText: 'Enter here.'});
//        }
//    }, 'json').always(function () {
//    });

//    DATEPICKER.init();
//    HNDL_OBJ.showPendingObjectionStatus();

    HNDL_OBJ.getObjectionFromMyTray();

});


var HNDL_OBJ = {
    zoneMaster: {},
    attrMaster: {},
    propertyId: null,
//    Added By Jay
    editedValueArray: new Array(),
//    Added By Jay End
    fillDocument: function () {
        $.ajax({
            url: "getVerifyingDocuments",
            type: 'post',
            data: '',
            success: function (result) {
                if (result !== null) {
                    var html = "";
                    html += "<option value='-1'>--Select Category--</option>";
                    for (var i = 0; i < result.length; i++) {
                        var doc = result[i];
                        html += "<option value='" + doc.docId + "'>" + doc.docName + "</option>";
                    }
                    $("#obj_appliedByIdType").html(html);
                }
            },
            error: function (e) {
                console.log("ERROR: ", e);
            }
        });
    },
    //Added By Jay End
    getVerifyingRelations: function () {
        $.ajax({
            url: "getVerifyingRelations",
            type: 'post',
            data: '',
            dataType: 'json',
            success: function (result) {
                if (result !== null) {
                    var html = "";
                    html += "<option value='-1'>--Select Relation--</option>";
                    for (var i = 0; i < result.length; i++) {
                        var relation = result[i];
                        html += "<option value='" + relation.relationsId + "'>" + relation.relationsName + "</option>";
                    }
                    $("#obj_appliedRelation").html(html);
                }
            },
            error: function (e) {
                console.log("ERROR: ", e);
            }
        });
    },
    //Added By Jay End
    showPendingObjectionStatus: function () {
        $.ajax({
            url: "getPendingObjectionStatus",
            type: 'post',
            data: '',
            success: function (result) {

                if (result !== "NA") {
                    var fromDate = result[0];
                    var toDate = result[1];
                    var count = result[2];
                    $("#pendingObjectionStatus").html("Total " + count + " cases are pending between " + fromDate + " and " + toDate + ".");
                } else {
                    $("#pendingObjectionStatus").html("No pending case found.");
                }
            },
            error: function (e) {
                console.log("ERROR: ", e);
            }
        });
    },
    setSearchCriteria: function () {

        var zone_id = $("#zone option:selected").val();
        var ward = $("#ward option:selected").val();

        if (zone_id === "-1") {

        } else if (ward === "-1") {

        } else {


            LOADER.show();
            $.post("getObjectedPropertyForSearch", {zoneId: zone_id, ward: ward}, function (result) {

                if (result.Status === "200") {

                    var pr = result.msg.propIdArr;
                    var own = result.msg.ownerArr;
                    var occ = result.msg.occupierArr;

                    var html_prop = "";
                    html_prop += "<option value='-1'>--Select Property ID--</option>";
                    for (var key in pr) {
                        html_prop += "<option value='" + pr[key] + "'>" + pr[key] + "</option>";
                    }
                    HNDL_OBJ.resetPropertyId(html_prop);

                    var html_own = "";
                    html_own += "<option value='-1'>--Select Owner Name--</option>";
                    for (var key in own) {
                        html_own += "<option value='" + own[key] + "'>" + own[key] + "</option>";
                    }
                    HNDL_OBJ.resetOwner(html_own);

                    var html_occ = "";
                    html_occ += "<option value='-1'>--Select Occupier Name--</option>";
                    for (var key in occ) {
                        html_occ += "<option value='" + occ[key] + "'>" + occ[key] + "</option>";
                    }
                    HNDL_OBJ.resetOccupier(html_occ);

                }
            }).always(function () {
                LOADER.hide();
            });
        }
    },
    showSearchWindow: function () {
        $("#searchArea").removeClass("hidden");
        $("#searchResults").addClass("hidden");
        $("#property_section").addClass("hidden");
        $("#submitMesage").addClass("hidden");
    },
    showResultWindow: function () {
        $("#searchArea").addClass("hidden");
        $("#searchResults").removeClass("hidden");
        $("#property_section").addClass("hidden");
        $("#submitMesage").addClass("hidden");
    },
    showObjectionWindow: function () {
        $("#searchArea").addClass("hidden");
        $("#searchResults").addClass("hidden");
        $("#property_section").removeClass("hidden");
        $("#submitMesage").addClass("hidden");
    },
    showMessageWindow: function () {
        $("#searchArea").addClass("hidden");
        $("#searchResults").addClass("hidden");
        $("#property_section").addClass("hidden");
        $("#submitMesage").removeClass("hidden");
    },
    propertyMap: {},
    preservePropertyetails: function (detailArr) {
        HNDL_OBJ.propertyMap = {};
        if (detailArr === undefined || detailArr === null) {

        } else {
            for (var dt in detailArr) {
                var det = detailArr[dt];
                if (det != null) {
                    HNDL_OBJ.propertyMap[det.propertyUniqueId] = det;
                }
            }
        }

    },
    taxDetailMap: {},
    searchResults: {},
    getObjectionFromMyTray: function () {

        HNDL_OBJ.searchResults = {};
        LOADER.show();
        $.post("getObjectionFromMyTray", {}, function (result) {

            HNDL_OBJ.preservePropertyetails(result);

            if (result !== undefined && result.length > 0) {
                var tab_html = "";
                tab_html += "<thead><tr>"
                        + "<th>S.No.</th>"
                        + "<th>Property ID</th>"
                        + "<th>Owner Name</th>"
                        + "<th>Occupier Name</th>"
                        + "<th>Relation With Owner</th>"
                        + "<th>Address</th>"
                        + "<th>Zone</th>"
                        + "<th>Ward</th>"
                        + "</tr></thead><tbody>";


                for (var i = 0; i < result.length; i++) {

                    var prop = result[i];

                    prop.propertyUniqueId = (prop.propertyUniqueId === undefined || prop.propertyUniqueId === null) ? "" : prop.propertyUniqueId;
                    prop.propertyOccupierName = (prop.propertyOccupierName === undefined || prop.propertyOccupierName === null) ? "" : prop.propertyOccupierName;
                    prop.propertyRelationOwner = (prop.propertyRelationOwner === undefined || prop.propertyRelationOwner === null) ? "" : prop.propertyRelationOwner;
                    prop.propertyPlotArea = (prop.propertyPlotArea === undefined || prop.propertyPlotArea === null) ? "" : prop.propertyPlotArea;
                    prop.propertyOwner = (prop.propertyOwner === undefined || prop.propertyOwner === null) ? "" : prop.propertyOwner;
                    prop.propertyHouseNo = (prop.propertyHouseNo === undefined || prop.propertyHouseNo === null) ? "" : prop.propertyHouseNo;
                    prop.propertyPincode = (prop.propertyPincode === undefined || prop.propertyPincode === null) ? "" : prop.propertyPincode;
                    prop.propertyBuildingName = (prop.propertyBuildingName === undefined || prop.propertyBuildingName === null) ? "" : prop.propertyBuildingName;
                    prop.zoneId = (prop.zoneId === undefined || prop.zoneId === null) ? "" : prop.zoneId;
                    prop.propertySublocality = (prop.propertySublocality === undefined || prop.propertySublocality === null) ? "" : prop.propertySublocality;
                    prop.propertyLandmark = (prop.propertyLandmark === undefined || prop.propertyLandmark === null) ? "" : prop.propertyLandmark;
                    prop.completeAddress = (prop.completeAddress === undefined || prop.completeAddress === null) ? "" : prop.completeAddress;
                    prop.propertyOwnerFather = (prop.propertyOwnerFather === undefined || prop.propertyOwnerFather === null) ? "" : prop.propertyOwnerFather;
                    HNDL_OBJ.searchResults[result[i].propertyUniqueId] = result[i];
                    tab_html += "<tr><td>" + (parseInt(i) + 1) + "</td>";
                    tab_html += "<td title='Click here to view more detail' ><a href='javascript: void(0)' onclick=HNDL_OBJ.populatePropertyDetail('"
                            + prop.propertyUniqueId + "') >"
                            + prop.propertyUniqueId + "</a> </td>";
                    tab_html += "<td >" + prop.propertyOwner + "</td>";
                    tab_html += "<td >" + prop.propertyOccupierName + "</td>";
                    tab_html += "<td >" + prop.propertyRelationOwner + "</td>";
                    tab_html += "<td>" + (prop.completeAddress) + "</td>";
                    tab_html += "<td>" + (HNDL_OBJ.zoneMaster[prop.zoneId] === undefined ? "N/A" : HNDL_OBJ.zoneMaster[prop.zoneId]) + "</td>";
                    tab_html += "<td>" + (HNDL_OBJ.wardMaster[prop.ward] === undefined ? "N/A" : HNDL_OBJ.wardMaster[prop.ward]) + "</td>";
                    tab_html += "</tr>";
                }

                tab_html += "</tbody>";

                $("#objection_table").html(tab_html);

                


            } else {
                return false;
            }

        }, 'json').always(function () {
            LOADER.hide();
            HNDL_OBJ.showResultWindow();
            $('#objection_table').DataTable({
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
            $("#objection_table_paginate").css({"position": "absolute", "top": "0", "right": "0"});
            $("#objection_table_filter").css({"float": "left"});
        });
    },
    objectionId: null,
    objectionBean: {},
    populateObjections: function (propertyId) {
        LOADER.show();
        $.ajax({
            url: "getGeneratedObjection",
            type: 'post',
            data: 'propertyId=' + propertyId,
            dataType: 'json',
            success: function (result) {
                LOADER.hide();
                if (result.Status === "200") {

                    var objBean = result.msg;
                    $("#currentOpenCaseId").html("Objection ( " + objBean.objectionId + " )");
                    HNDL_OBJ.objectionId = objBean.objectionId;
                    HNDL_OBJ.objectionBean = objBean;
                    $("#obj_appliedBy").val(objBean.appliedBy);
                    $("#obj_appliedByIdType").val(objBean.appliedByIdType);
                    $("#obj_appliedById").val(objBean.appliedById);
                    $("#obj_appliedRelation").val(objBean.relation);
                    //Added By Jay 
                    if (objBean.relation === "RELATION3") {
                        $("#obj_appliedRelationIfOther").val(objBean.relationifother);
                    }
                    //Added By Jay End
                    $("#obj_remarks").val(objBean.raisedByremarks);
                    if (objBean.objectionBeans === undefined) {
                        objBean.objectionBeans = [];
                    }
                    HNDL_OBJ.populateEditField(objBean.objectionBeans);
                    HNDL_OBJ.getObjectionActionHistory(propertyId, objBean.objectionId);
                }

            },
            error: function (e) {
                LOADER.hide();
                console.log("ERROR: ", e);
            }
        });

    },
    populatePropertyDetail: function (propertyId) {
        $(this).closest("tr").addClass("currentOpenCase");
        HNDL_OBJ.clearPropertyDetail();
        HNDL_OBJ.clearAll();
        HNDL_OBJ.fillDocument();
        //Added By Jay
        HNDL_OBJ.getVerifyingRelations();
        //Added By Jay End
        HNDL_OBJ.propertyId = propertyId;
        var prop = HNDL_OBJ.searchResults[propertyId];

        if (prop === undefined) {
            alert("Unable to fetch details");
        } else {

            prop.propertyUniqueId = (prop.propertyUniqueId === undefined || prop.propertyUniqueId === null) ? "" : prop.propertyUniqueId;
            prop.propertyOccupierName = (prop.propertyOccupierName === undefined || prop.propertyOccupierName === null) ? "" : prop.propertyOccupierName;
            prop.propertyRelationOwner = (prop.propertyRelationOwner === undefined || prop.propertyRelationOwner === null) ? "" : prop.propertyRelationOwner;
            prop.propertyPlotArea = (prop.propertyPlotArea === undefined || prop.propertyPlotArea === null) ? "" : prop.propertyPlotArea;
            prop.propertyOwner = (prop.propertyOwner === undefined || prop.propertyOwner === null) ? "" : prop.propertyOwner;
            prop.propertyHouseNo = (prop.propertyHouseNo === undefined || prop.propertyHouseNo === null) ? "" : prop.propertyHouseNo;
            prop.propertyPincode = (prop.propertyPincode === undefined || prop.propertyPincode === null) ? "" : prop.propertyPincode;
            prop.propertyBuildingName = (prop.propertyBuildingName === undefined || prop.propertyBuildingName === null) ? "" : prop.propertyBuildingName;
            prop.zoneId = (prop.zoneId === undefined || prop.zoneId === null) ? "" : prop.zoneId;
            prop.propertySublocality = (prop.propertySublocality === undefined || prop.propertySublocality === null) ? "" : prop.propertySublocality;
            prop.propertyLandmark = (prop.propertyLandmark === undefined || prop.propertyLandmark === null) ? "" : prop.propertyLandmark;
            prop.completeAddress = (prop.completeAddress === undefined || prop.completeAddress === null) ? "" : prop.completeAddress;


            $("#prop_id").html(prop.propertyUniqueId);
            $("#prop_owner").html(prop.propertyOwner);
            $("#prop_occupier").html(prop.propertyOccupierName);
            $("#prop_address").html((prop.completeAddress));
            $("#prop_pincode").html(prop.propertyPincode);
            $("#prop_zone").html((HNDL_OBJ.zoneMaster[prop.zoneId] === undefined ? "" : HNDL_OBJ.zoneMaster[prop.zoneId]));
            $("#prop_ward").html((HNDL_OBJ.wardMaster[prop.ward] === undefined ? "N/A" : HNDL_OBJ.wardMaster[prop.ward]));
            $("#prop_sublocality").html(prop.propertySublocality);
            $("#prop_landmark").html(prop.propertyLandmark);
            $("#prop_relation_owner").html(prop.propertyRelationOwner);
            HNDL_OBJ.populateObjections(propertyId);
            HNDL_OBJ.showObjectionWindow();
        }

    },
    clearPropertyDetail: function () {

        $("#prop_id").val("");
        $("#prop_owner").val("");
        $("#prop_occupier").val("");
        $("#prop_address").val("");
        $("#prop_pincode").val("");
        $("#prop_zone").val("");
        $("#prop_sublocality").val("");
        $("#prop_landmark").val("");
        $("#prop_relation_owner").val("");
    },
    editedFields: {},
    populateEditField: function (objArr) {

        var html_ = "";

        for (var o in objArr) {
            var obj = objArr[o];
            html_ += "<tr>";
            html_ += "<td>" + obj.attrName + "</td>";
            html_ += "<td>" + obj.value + "</td>";
            html_ += "<td>" + obj.newValue + "</td>";
            html_ += "</tr>";
            HNDL_OBJ.editedValueArray.push(obj);
        }
        $("#edit_field_table tbody").html(html_);

    },
    clearAll: function () {
        HNDL_OBJ.editedFields = {};
        HNDL_OBJ.editedValueArray = new Array();
        HNDL_OBJ.floorDetailsObj = {};
        $("#edit_field_table tbody").html('<tr class="no_data_row" ><td colspan="5"><center>No data available</center></td></tr>');
        $("#obj_field_category").val("-1");
        $("#obj_field_attr").val("-1");
        $("#obj_field_value").val("");
        $("#obj_appliedBy").val("");
        $("#obj_appliedById").val("");
        $("#obj_objectionAction").val("-1");
        $("#obj_decision_remarks").val("");
    },
    getFieldId: function () {
        var id = $("#obj_field_attr option:selected").val();
        if (HNDL_OBJ.isComplexCategory()) {
            id = $("#obj_field_attr option:selected").val() + $("#obj_field_floor option:selected").val();
        }
        return id;
    },
    verifyAndCommiteObjection: function () {
        var obj = new Object();
        obj.propertyId = HNDL_OBJ.propertyId;
        obj.effectingTAX = "";
        obj.appliedBy = $("#obj_appliedBy").val();
        obj.appliedByIdType = $("#obj_appliedByIdType option:selected").val();
        obj.appliedById = $("#obj_appliedById").val();
        obj.raisedByremarks = $("#obj_remarks").val();
        obj.objectionBeans = [];
        var keyArr = Object.keys(HNDL_OBJ.editedFields);
        for (var k in keyArr) {
            var key = keyArr[k];
            obj.objectionBeans.push(HNDL_OBJ.editedFields[key]);
        }

        if (obj.appliedBy.trim() === "") {
            alert("Kindly provide 'Objection Applied By Name'");
        } else if (obj.appliedByIdType.trim() === "-1") {
            alert("Kindly provide 'Objection Applied By ID'");
        } else if (obj.appliedById.trim() === "") {
            alert("Kindly provide 'Objection Applied By ID No.'");
        } else if (obj.raisedByremarks.trim() === "") {
            alert("Kindly provide 'Remarks'");
        } else if (Object.keys(obj.objectionBeans).length < 1) {
            alert("Kindly provide 'Objected Attributes'.");
        } else {
            HNDL_OBJ.commiteObjection(obj);
        }

    },
    commiteObjection: function (decision, remarks) {

        LOADER.show();

        var obj = {};
        obj.propertyId = HNDL_OBJ.propertyId;
        obj.objectionId = HNDL_OBJ.objectionId;
        obj.decision = decision;
        obj.remarks = remarks;

        $.ajax({
            url: "resolveObjection",
            type: 'post',
            data: 'resolutionBean=' + JSON.stringify(obj),
            success: function (result) {
                LOADER.hide();
                if (result.status === "200") {
                    $("#obj_message").html(result.msg);
                    HNDL_OBJ.showMessageWindow();
                    HNDL_OBJ.removeCurrentOpenRow();
                } else {
                    alert(result.msg);
                }
            },
            error: function (e) {
                LOADER.hide();
                $("#obj_message").html(e.responseText);
                console.log("ERROR: ", e);
            }
        });

    },
    removeCurrentOpenRow: function () {
        $('#objection_table').DataTable({
            "dom": 'Bfrtip',
            "buttons": [],
            "lengthMenu": [[10, 25, 50, -1], [10, 25, 50, "All"]],
            "bDestroy": true,
            "responsive": true,
            "paging": true,
            // "scrollY":200,
            /* "scrollX":true, */
            "sPaginationType": "full_numbers"
        }).row($(".currentOpenCase")).remove().draw();

        $("#objection_table_paginate").css({"position": "absolute", "top": "0", "right": "0"});
        $("#objection_table_filter").css({"float": "left"});
    },
    objectionMap: {},
    preserveOBJECTIONDetails: function (detailArr) {
        HNDL_OBJ.objectionMap = {};
        if (detailArr === undefined || detailArr === null) {
        } else {
            for (var dt in detailArr) {
                var det = detailArr[dt];
                if (det !== null) {
                    HNDL_OBJ.objectionMap[det.propertyId] = det;
                }
            }
        }

    },
    categoryMap: {},
    preserveCATEGORYDetails: function (detailArr) {
        HNDL_OBJ.categoryMap = {};
        if (detailArr === undefined || detailArr === null) {
        } else {
            for (var dt in detailArr) {
                var det = detailArr[dt];
                if (det !== null) {
                    HNDL_OBJ.categoryMap[det.category] = det;
                }
            }
        }

    },
    keyArr: {},
    masterAttrArr: [],
    mywindow: null,
    printObjection: function () {
        //var obj = HNDL_OBJ.objectionMap[HNDL_OBJ.propertyId] ;   
        HNDL_OBJ.keyArr = {};
        var viewObjCrit = new Object();
        viewObjCrit.objectionId = HNDL_OBJ.objectionId;
        HNDL_OBJ.mywindow = window.open('', 'PRINT', 'height=400,width=600');
        $.post("getObjectionCategory", {
            jsonViewCrit: JSON.stringify(viewObjCrit)
        }, function (result) {
            LOADER.show();

            HNDL_OBJ.masterAttrArr = [];
            HNDL_OBJ.preserveCATEGORYDetails(result.objCatList);
            var categoryArr = HNDL_OBJ.categoryMap;
            HNDL_OBJ.keyArr = Object.keys(categoryArr);
            for (var k in HNDL_OBJ.keyArr) {
                var key = HNDL_OBJ.keyArr[k];
                HNDL_OBJ.masterAttrArr.push(key);
            }

            HNDL_OBJ.printPreview(HNDL_OBJ.propertyId, HNDL_OBJ.masterAttrArr);
            LOADER.hide();
        }, 'json').always(function () {
        });

    },
    printPreview: function (property_id, masterAttrArray) {
        var obj = HNDL_OBJ.objectionBean;
        //console.log(obj);
        if (obj !== undefined) {
            var prop = HNDL_OBJ.searchResults[property_id];

//            console.log(HNDL_OBJ.editedValueArray);
            var print_html = "";
//            var raisedOn = obj.raisedOn;
//            if (raisedOn !== undefined && raisedOn !== "") {
//                raisedOn = Date(1000 * raisedOn);
//                raisedOn = raisedOn.substring(3, 15);
//            }
//
//            print_html += "<html><head><title>Objection Print Receipt (" + obj.propertyId + ")</title></head><body><h1><font size='5' color='black' align='right'><center><U> Objection Acknowledgement Receipt </U><center><font></h1>";
//            print_html += "<span><font size='4' color='black'>Objection Number <b>" + obj.objectionId + " </b> for the property <b>" + prop.propertyUniqueId + "</b> has been filed by <b>" + obj.raisedBy + "</b> on <b>" + obj.entrydatetime + "</b>.</font></span>";
//            print_html += "<br/><br/><b><U>Information of Property:</U></b><table>";
//            print_html += "<tr size='4' color='black'><td><b>Owner of Property:</b></td><td> " + prop.propertyOwner + "</td></tr>";
//            print_html += "<tr size='4' color='black'><td><b>Owner Father Name:</b></td><td> " + prop.propertyOwnerFather + "</td></tr>";
//            print_html += "<tr size='4' color='black'><td><b>Occupier Name:</b></td><td> " + prop.propertyOccupierName + "</td></tr>";
//            print_html += "<tr size='4' color='black'><td><b>Relation With Owner:</b></td><td> " + prop.propertyRelationOwner + "</td></tr>";
//            print_html += "<tr size='4' color='black'><td><b>Address:</b></td><td> " + (prop.completeAddress === undefined ? "" : prop.completeAddress) + "</td></tr>";
//            print_html += "<tr size='4' color='black'><td><b>Objection Remarks: </td><td>" + obj.raisedByremarks + "</b></td></tr><br/></table>";
//            print_html += "<br/><p align='right'><font size='4' color='black'>Thanks From,<br/>Silvassa Municipal Corporation,<br/>Silvassa</font><br/><br/><font size='4' color='black'>(Authorized Signature of Authority)</font> <br/></p>";
//            print_html += "</body></html>";
//            var mywindow = window.open('', 'PRINT', 'height=400,width=600');
//            mywindow.document.write(print_html);
//            mywindow.document.close(); // necessary for IE >= 10
//            mywindow.focus(); // necessary for IE >= 10*/
//            mywindow.print();
//            mywindow.close();
            prop.propertyUniqueId = (prop.propertyUniqueId === undefined || prop.propertyUniqueId === null) ? "" : prop.propertyUniqueId;
            prop.propertyOccupierName = (prop.propertyOccupierName === undefined || prop.propertyOccupierName === null) ? "" : prop.propertyOccupierName;
            prop.propertyRelationOwner = (prop.propertyRelationOwner === undefined || prop.propertyRelationOwner === null) ? "" : prop.propertyRelationOwner;
            prop.propertyPlotArea = (prop.propertyPlotArea === undefined || prop.propertyPlotArea === null) ? "" : prop.propertyPlotArea;
            prop.propertyOwner = (prop.propertyOwner === undefined || prop.propertyOwner === null) ? "" : prop.propertyOwner;
            prop.propertyHouseNo = (prop.propertyHouseNo === undefined || prop.propertyHouseNo === null) ? "" : prop.propertyHouseNo;
            prop.propertyPincode = (prop.propertyPincode === undefined || prop.propertyPincode === null) ? "" : prop.propertyPincode;
            prop.propertyBuildingName = (prop.propertyBuildingName === undefined || prop.propertyBuildingName === null) ? "" : prop.propertyBuildingName;
            prop.zoneId = (prop.zoneId === undefined || prop.zoneId === null) ? "" : prop.zoneId;
            prop.propertySublocality = (prop.propertySublocality === undefined || prop.propertySublocality === null) ? "" : prop.propertySublocality;
            prop.propertyLandmark = (prop.propertyLandmark === undefined || prop.propertyLandmark === null) ? "" : prop.propertyLandmark;
            prop.completeAddress = (prop.completeAddress === undefined || prop.completeAddress === null) ? "" : prop.completeAddress;
            prop.propertyOwnerFather = (prop.propertyOwnerFather === undefined || prop.propertyOwnerFather === null) ? "" : prop.propertyOwnerFather;

            print_html += " <!doctype html><html><head><meta charset='utf-8'><title>Objection Print Receipt (" + prop.propertyUniqueId + ")</title></head><style>";
            print_html += " html, body, div, span, applet, object, iframe, h1, h2, h3, h4, h5, h6, p, blockquote, pre, a, abbr, acronym, address, big, cite, code, del, dfn, em, img, ins, kbd, q, s, samp, small, strike, strong, sub, sup, tt, var, b, u, i, center, dl, dt, dd, ol, ul, li, fieldset, form, label, legend, table, caption, tbody, tfoot, thead, tr, th, td, article, aside, canvas, details, embed, figure, figcaption, footer, header, hgroup, menu, nav, output, ruby, section, summary, time, mark, audio, video { margin: 0; padding: 0; border: 0; font-size: 100%; font: inherit; vertical-align: baseline; }";
            print_html += " article, aside, details, figcaption, figure, footer, header, hgroup, menu, nav, section { display: block; }";
            print_html += " body { line-height: 1; font-size: 14px; line-height: normal; background: #fff; color: #000; font-family: 'Open Sans', sans-serif; }";
            print_html += " ol, ul { list-style: none; }";
            print_html += " blockquote, q { quotes: none; }";
            print_html += " blockquote:before, blockquote:after, q:before, q:after { content: ''; content: none; }";
            print_html += " table { border-collapse: collapse; border-spacing: 0; } body { margin-top: 30px; } h2 { font-size: 16px; font-weight: bold; margin: 10px; }";
            print_html += " p { font-size: 13px; } table, th, td { padding: 0; margin: 0; border: 1px solid black; } td { padding: 7px; }";
            print_html += " </style> <body> <div style='max-width: 700px; width: 100%; margin: 0 auto;'> <div style='text-align: center; position: relative;'>";
            print_html += " <h2>Silvassa Municipal Council</h2>";
            print_html += " <p>Shahid Chowk, Near Town Hall, U.T. of Dadra & Nagar Haveli, Silvassa - 396230 <br>";
            print_html += " Phone No.:(0260)-2633192, Fax No.:(0260)2633191: Email-Id: smc2006@ymail.com : <br>";
            print_html += " Help Line Number: 0260-2633192 : Timing 09:30 AM to 06:00 PM</p>";
            print_html += " <h2 style='text-decoration: underline'>Acknowledgement</h2>";
            print_html += " <p>Objection filling</p>";
            print_html += " <img src='res/img/logo.png' alt='' style='position: absolute; top: 0; right: 0;'> </div>";
            print_html += " <table style='width: 100%; margin-top: 20px;'> <tbody>";
            print_html += " <tr> <td><span style='font-weight:bold'>Objection filing no :</span> " + obj.objectionId + "</td> <td> <span style='font-weight:bold'>Date of filing: </span>" + obj.entrydatetime + "</td></tr>";
            print_html += " <tr> <td><span style='font-weight:bold'>Property Id : </span>" + prop.propertyUniqueId + "</td> <td> <span style='font-weight:bold'>Due Date of filing \\ payment: </span></td> </tr>";
            print_html += " <tr> <td colspan='2'><span style='font-weight:bold'>Owner Name : </span>" + prop.propertyOwner + "</td> </tr> <tr><td colspan='2'> <span style='font-weight:bold'>Father \\ spouse name:</span> " + prop.propertyOwnerFather + "</td> </tr>";
            print_html += " <tr>  <td colspan='2'><span style='font-weight:bold'>Occupier Name: </span>" + prop.propertyOccupierName + "</td> </tr> <tr><td colspan='2'> <span style='font-weight:bold'>Address of the Property:</span> " + (prop.completeAddress === undefined ? "" : prop.completeAddress) + "</td> </tr>";
            print_html += " <tr> <td colspan='2'><span style='font-weight:bold'>Objection filed by: </span>" + obj.raisedBy + "</td></tr> <tr><td colspan='2'> <span style='font-weight:bold'>SMC Executive:</span> </td> </tr>";

            print_html += " <tr> <td colspan='2'><span style='font-weight:bold'>Objection Related to :</span>" + masterAttrArray + "</td> </tr>";
            for (i = 0; i < HNDL_OBJ.editedValueArray.length; i++) {

                print_html += " <tr>";
                print_html += "<td colspan='2'><center><span style='font-weight:bold'>" + HNDL_OBJ.editedValueArray[i].attrName + ":</span></center></td>";
                print_html += "</tr>";
                print_html += " <tr> <td style='padding: 0;'> <table style='width: 100%; height: 100%; border:0;'><tr><td style='width: 60px;border: none'><span style='font-weight:bold'>Old Value:</span></td>";
                print_html += " <td style='border: none;  border-left: 1px solid black; text-align: center;'>" + HNDL_OBJ.editedValueArray[i].value + "</td> </tr> </table></td>";
                print_html += " <td style='padding: 0;'> <table style='width: 100%; height: 100%; border:0;'><tr><td style='width: 60px; border: none'><span style='font-weight:bold'>New Value:</span></td>";
                print_html += " <td style=' border: none;  border-left: 1px solid black; text-align: center;'>" + HNDL_OBJ.editedValueArray[i].newValue + "</td> </tr> </table></td></tr>";

            }
            print_html += " <tr> <td style='padding: 0;'> <table style='width: 100%; height: 100%; border:0;'><tr><td style='border: none'><span style='font-weight:bold'>Owner Details:</span></td>";
            print_html += " <td style='width: 60px; border: none;  border-left: 1px solid black; text-align: center;'>(X)</td> </tr> </table></td>";
            print_html += " <td style='padding: 0;'> <table style='width: 100%; height: 100%; border:0;'><tr><td style='border: none'><span style='font-weight:bold'>Other Residing Personnel:</span></td>";
            print_html += " <td style='width: 60px; border: none;  border-left: 1px solid black; text-align: center;'>(X)</td> </tr> </table></td></tr>";

            print_html += " <tr> <td style='padding: 0;'> <table style='width: 100%; height: 100%; border:0;'><tr><td style='border: none'><span style='font-weight:bold'>Property Address:</span></td>";
            print_html += " <td style='width: 60px; border: none;  border-left: 1px solid black; text-align: center;'>(X)</td> </tr> </table></td>";
            print_html += " <td style='padding: 0;'> <table style='width: 100%; height: 100%; border:0;'><tr><td style='border: none'><span style='font-weight:bold'>Prop. Detail \\ Civic amenities:</span></td>";
            print_html += " <td style='width: 60px; border: none;  border-left: 1px solid black; text-align: center;'>(X)</td> </tr> </table></td></tr>";

            print_html += " <tr> <td style='padding: 0;'> <table style='width: 100%; height: 100%; border:0;'><tr><td style='border: none'><span style='font-weight:bold'>Tax Details:</span></td>";
            print_html += " <td style='width: 60px; border: none;  border-left: 1px solid black; text-align: center;'>(X)</td> </tr> </table></td>";
            print_html += " <td style='padding: 0;'> <table style='width: 100%; height: 100%; border:0;'><tr><td style='border: none'><span style='font-weight:bold'>All above:</span></td>";
            print_html += " <td style='width: 60px; border: none;  border-left: 1px solid black; text-align: center;'>(X)</td> </tr> </table></td></tr>";

            print_html += " </tbody></table><br> <div class=''> <p>Thanks </p> <br><p>Silvassa Municipal Corporation, Silvassa <br>(Authorized Signature of Authority with stamp, date)</p>";
            print_html += " </div></div></body></html>";
//            var mywindow = window.open('', 'PRINT', 'height=400,width=600');
            //alert(mywindow.chrome);
            var is_chrome = Boolean(HNDL_OBJ.mywindow.chrome);
            HNDL_OBJ.mywindow.document.write(print_html);

            if (is_chrome) {
                setTimeout(function () { // wait until all resources loaded 
                    HNDL_OBJ.mywindow.document.close(); // necessary for IE >= 10
                    HNDL_OBJ.mywindow.focus(); // necessary for IE >= 10
                    HNDL_OBJ.mywindow.print(); // change window to winPrint
                    HNDL_OBJ.mywindow.close(); // change window to winPrint
                }, 250);
            } else {
                HNDL_OBJ.mywindow.document.close(); // necessary for IE >= 10
                HNDL_OBJ.mywindow.focus(); // necessary for IE >= 10
                HNDL_OBJ.mywindow.print();
                HNDL_OBJ.mywindow.close();
            }
            return true;
        } else {
            alert("Unable to process request.");
            return false;

        }

    },
    title: "Objection",
    params: {},
    type: "PDF",
    exportAs: function (type) {

        HNDL_OBJ.type = type;

        var html_ = '<li class="clearfix">'
                + '<input id="export_All" type="checkbox" value="">'
                + '<label class="cheklabel" for="export_All">All</label>'
                + '</li>';

        for (var a in MASTERCOLUMN.attrOBJ) {
            var attr = MASTERCOLUMN.attrOBJ[a];
            html_ += '<li class="clearfix">'
                    + '<input id="' + attr + '" type="checkbox" value="">'
                    + '<label class="cheklabel" for="name">' + MASTERCOLUMN.attrHead[attr] + '</label>'
                    + '</li>';

        }
        $("#business_entities_list").html(html_);
        $('#export-modal').modal('toggle');
    },
    download: function () {
        $("#exportTitle").val(HNDL_OBJ.title);
        $("#windowId").val(Math.random());

        HNDL_OBJ.params = new Object();
        HNDL_OBJ.params.masterBeans = [];
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

        var propArr = Object.keys(HNDL_OBJ.propertyMap);
        for (var p in propArr) {
            var propId = propArr[p];
            var prop = HNDL_OBJ.propertyMap[propId];
            var tax = HNDL_OBJ.taxDetailMap[prop.propertyUniqueId];
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
                        obj[attr] = HNDL_OBJ.zoneMaster[prop[MASTERCOLUMN.attrInBean[attr]]];
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

            HNDL_OBJ.params.masterBeans.push(obj);

        }

        $("#exportParams").val(JSON.stringify(HNDL_OBJ.params));
        $("#exportTHead").val(JSON.stringify(masterHeadArr));
        $("#exportAttrToAdd").val(JSON.stringify(masterAttrArr));
        $("#exportType").val(HNDL_OBJ.type);
        $("#expo").submit();
        $('#export-modal').modal('toggle');
    },
    searchObjResults: {},
    searchobjProperty: function (obj) {
        HNDL_OBJ.searchObjResults = {};
        LOADER.show();
        $.post("loadObjectionData", {
            jsonViewCrit: JSON.stringify(obj)
        }, function (result) {
            //HNDL_OBJ.preservePropertyetails(result);

            if (result !== undefined && result.length > 0) {
                var tab_html = "";
                tab_html += "<thead><tr>"
                        + "<th>S.No.</th>"
                        + "<th>Property ID</th>"
                        + "<th>Owner Name</th>"
                        + "<th>Occupier Name</th>"
                        + "<th>Relation With Owner</th>"
                        + "<th>Address</th>"
                        + "<th>Zone</th>"
                        + "<th>Print Receipt</th>"
                        + "</tr></thead><tbody>";

                for (var i = 0; i < result.length; i++) {

                    var prop = result[i];

                    prop.propertyUniqueId = (prop.propertyUniqueId === undefined || prop.propertyUniqueId === null) ? "" : prop.propertyUniqueId;
                    prop.propertyOccupierName = (prop.propertyOccupierName === undefined || prop.propertyOccupierName === null) ? "" : prop.propertyOccupierName;
                    prop.propertyRelationOwner = (prop.propertyRelationOwner === undefined || prop.propertyRelationOwner === null) ? "" : prop.propertyRelationOwner;
                    prop.propertyPlotArea = (prop.propertyPlotArea === undefined || prop.propertyPlotArea === null) ? "" : prop.propertyPlotArea;
                    prop.propertyOwner = (prop.propertyOwner === undefined || prop.propertyOwner === null) ? "" : prop.propertyOwner;
                    prop.propertyHouseNo = (prop.propertyHouseNo === undefined || prop.propertyHouseNo === null) ? "" : prop.propertyHouseNo;
                    prop.propertyPincode = (prop.propertyPincode === undefined || prop.propertyPincode === null) ? "" : prop.propertyPincode;
                    prop.propertyBuildingName = (prop.propertyBuildingName === undefined || prop.propertyBuildingName === null) ? "" : prop.propertyBuildingName;
                    prop.zoneId = (prop.zoneId === undefined || prop.zoneId === null) ? "" : prop.zoneId;
                    prop.propertySublocality = (prop.propertySublocality === undefined || prop.propertySublocality === null) ? "" : prop.propertySublocality;
                    prop.propertyLandmark = (prop.propertyLandmark === undefined || prop.propertyLandmark === null) ? "" : prop.propertyLandmark;
                    prop.completeAddress = (prop.completeAddress === undefined || prop.completeAddress === null) ? "" : prop.completeAddress;
                    prop.propertyOwnerFather = (prop.propertyOwnerFather === undefined || prop.propertyOwnerFather === null) ? "" : prop.propertyOwnerFather;
                    HNDL_OBJ.searchObjResults[result[i].propertyUniqueId] = result[i];
                    tab_html += "<tr><td>" + (i + 1) + "</td>";
                    tab_html += "<td> " + prop.propertyUniqueId + "</td>";
                    tab_html += "<td >" + prop.propertyOwner + "</td>";
                    tab_html += "<td >" + prop.propertyOccupierName + "</td>";
                    tab_html += "<td >" + prop.propertyRelationOwner + "</td>";
                    tab_html += "<td>" + (prop.completeAddress) + "</td>";
                    tab_html += "<td>" + (HNDL_OBJ.zoneMaster[prop.zoneId] === undefined ? "N/A" : HNDL_OBJ.zoneMaster[prop.zoneId]) + "</td>";
                    tab_html += "<td style='text-align:left' onclick=\"HNDL_OBJ.printObjection('"
                            + result[i].objection_unique_id
                            + "')\"><a href='javascript: void(0)'>Click here to Print Objection"
                            + "</a> </td>";
                    tab_html += "</tr>";
                }

                tab_html += "</tbody>";

                $("#objection_table").html(tab_html);

                HNDL_OBJ.showResultWindow();
                $('#objection_table').DataTable({
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

            } else {
                alert("No result found.");
                return false;
            }
        }, 'json').always(function () {
            LOADER.hide();
            $("#objection_table_paginate").css({"position": "absolute", "top": "0", "right": "0"});
            $("#objection_table_filter").css({"float": "left"});
        });
    },
    //Added By Jay
    propertyIdChange: function () {
        var val = $("#obj_prop_id").val();
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
    //Added By Jay End
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
                        HNDL_OBJ.wardMaster[result[i].ward] = result[i].displayName;
                    }
                    HNDL_OBJ.resetWard(html);
                }
            }, 'json').always(function () {

            });
        }


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
    resetOccupier: function (html) {
        if (html === undefined) {
            $("#obj_occupier").html("<option value='-1'>--Select Occupier Name--</option>");

        } else {
            $("#obj_occupier").html(html);
        }
        $("#obj_occupier").SumoSelect().sumo.unload();
        $("#obj_occupier").SumoSelect({csvDispCount: 3, search: true, searchText: 'Enter here.'});

        if (html === undefined) {
            $("#obj_occupier").closest(".SumoSelect").find(".CaptionCont").addClass("disable_cls");
            $("#obj_occupier").prop("disabled", true);
        } else {
            $("#obj_occupier").prop("disabled", false);
            $("#obj_occupier").closest(".SumoSelect").removeClass("disabled");
            $("#obj_occupier").closest(".SumoSelect").find(".CaptionCont").removeClass("disable_cls");
        }
    },
    resetOwner: function (html) {
        if (html === undefined) {
            $("#obj_owner").html("<option value='-1'>--Select Owner Name--</option>");
            $("#obj_owner").closest(".SumoSelect").find(".CaptionCont").addClass("disable_cls");
        } else {
            $("#obj_owner").html(html);
            $("#obj_owner").closest(".SumoSelect").find(".CaptionCont").removeClass("disable_cls");
        }
        $("#obj_owner").SumoSelect().sumo.unload();
        $("#obj_owner").SumoSelect({csvDispCount: 3, search: true, searchText: 'Enter here.'});

        if (html === undefined) {
            $("#obj_owner").closest(".SumoSelect").find(".CaptionCont").addClass("disable_cls");
            $("#obj_owner").prop("disabled", true);
        } else {
            $("#obj_owner").prop("disabled", false);
            $("#obj_owner").closest(".SumoSelect").removeClass("disabled");
            $("#obj_owner").closest(".SumoSelect").find(".CaptionCont").removeClass("disable_cls");
        }

    },
    resetPropertyId: function (html) {
        if (html === undefined) {
            $("#obj_prop_id").html("<option value='-1'>--Select Property ID--</option>");

        } else {
            $("#obj_prop_id").html(html);
        }
        $("#obj_prop_id").SumoSelect().sumo.unload();
        $("#obj_prop_id").SumoSelect({csvDispCount: 3, search: true, searchText: 'Enter here.'});
        if (html === undefined) {
            $("#obj_prop_id").prop("disabled", true);
            $("#obj_prop_id").closest(".SumoSelect").find(".CaptionCont").addClass("disable_cls");
        } else {
            $("#obj_prop_id").prop("disabled", false);
            $("#obj_prop_id").closest(".SumoSelect").removeClass("disabled");
            $("#obj_prop_id").closest(".SumoSelect").find(".CaptionCont").removeClass("disable_cls");
        }

    },
    getObjectionActionHistory: function (propertyId, objectionId) {

        var obj = {};
        obj.propertyId = propertyId;
        obj.objectionId = objectionId;

        $.ajax({
            url: "getObjectionActionHistory",
            type: 'post',
            data: 'resolutionBean=' + JSON.stringify(obj),
            success: function (result) {

                if (result.status === "200") {
                    var html_ = "";
                    var objArr = result.data;
                    var isBlank = true;
                    for (var o in objArr) {
                        isBlank = false;
                        var obj = objArr[o];
                        html_ += "<tr>";
                        html_ += "<td>" + (obj.entrydatetime === undefined ? "" : obj.entrydatetime) + "</td>";
                        html_ += "<td>" + (obj.actionBy === undefined ? "" : obj.actionBy) + "</td>";
                        html_ += "<td>" + (obj.actionTaken === undefined ? "" : obj.actionTaken) + "</td>";
                        html_ += "<td>" + (obj.forwardTo === undefined ? "" : obj.forwardTo) + "</td>";
                        html_ += "<td>" + (obj.actionRemarks === undefined ? "" : obj.actionRemarks) + "</td>";
                        html_ += "</tr>";
                    }
                    if (isBlank) {
                        html_ = '<tr class="no_data_row"><td colspan="5"><center>No action performed till date</center></td>';
                    }
                    $("#decision_history tbody").html(html_);
                }
            },
            error: function (e) {
                console.log("ERROR: ", e);
            }
        });
    }

}


$(document).on("click", "#export_btn", function () {
    HNDL_OBJ.download();
});

$(document).on("click", "#searchProp", function () {


    var propID = $("#obj_prop_id option:selected").val();
    var occupier = $("#obj_occupier option:selected").val();
    var owner = $("#obj_owner option:selected").val();
    var status = $("#obj_objectionStatus option:selected").val();
    var obj = {};
    if ($("#zone option:selected").val() === "-1") {
        //Search Attribute Set 1

        var fDate = $("#obj_fromDate").val();
        var fArr = fDate.split("/");
        var tDate = $("#obj_toDate").val();
        var tArr = tDate.split("/");
        obj.fromDate = fArr[2] + fArr[0] + fArr[1];
        obj.toDate = tArr[2] + tArr[0] + tArr[1];
        obj.objId = $("#obj_objectionId").val();
        obj.objStatus = (status === "-1" ? "" : status);

    } else {
        //Search Attribute Set 2
        obj.zone = $("#zone option:selected").val();
        obj.ward = $("#ward option:selected").val();
        obj.owner = (owner === "-1" ? "" : owner);
        obj.occupier = (occupier === "-1" ? "" : occupier);
        obj.propID = (propID === "-1" ? "" : propID);
    }

    if (($("#zone option:selected").val() === "-1" || $("#ward option:selected").val() === "-1") && $("#obj_fromDate").val() === "" && $("#obj_toDate").val() === "") {
        alert("Kindly provide input.");
    } else if ($("#zone option:selected").val() === "-1" && $("#obj_fromDate").val() === "") {
        alert("Kindly provide both FROM and TO dates.");
    } else if ($("#zone option:selected").val() === "-1" && $("#obj_toDate").val() === "") {
        alert("Kindly provide both FROM and TO dates.");
    } else {
        HNDL_OBJ.searchProperty(obj);
    }

});
$(document).on("click", "#obj_revert", function () {
    var decision = "REVERT";
    var remarks = $("#obj_decision_remarks").val();

    if (decision === "-1") {
        alert("Kindly fill decision.");
    } else if (remarks.trim() === "") {
        alert("Kindly provide remarks.");
    } else {
        bootbox.confirm({
            message: "Please confirm. Once the process start, It can't stop or revert.",
            buttons: {
                confirm: {
                    label: 'Yes',
                    className: 'btn-success'
                },
                cancel: {
                    label: 'No',
                    className: 'btn-danger'
                }
            },
            callback: function (result) {
                if (result) {
                    HNDL_OBJ.commiteObjection(decision, remarks);
                }
            }

        });

    }


});

$(document).on("click", "#objection_table a", function () {
    $("#objection_table tr").removeClass("currentOpenCase");
    $(this).closest("tr").addClass("currentOpenCase");
});


$(document).on("click", "#searchObj", function () {
    var propID = $("#obj_prop_id option:selected").val();
    var occupier = $("#obj_occupier option:selected").val();
    var owner = $("#obj_owner option:selected").val();
    var status = $("#obj_objectionStatus option:selected").val();
    var obj = {};
    if ($("#zone option:selected").val() === "-1") {
        //Search Attribute Set 1

        var fDate = $("#obj_fromDate").val();
        var fArr = fDate.split("/");
        var tDate = $("#obj_toDate").val();
        var tArr = tDate.split("/");
        obj.fromDate = fArr[2] + fArr[0] + fArr[1];
        obj.toDate = tArr[2] + tArr[0] + tArr[1];
        obj.objId = $("#obj_objectionId").val();
        obj.objStatus = (status === "-1" ? "" : status);

    } else {
        //Search Attribute Set 2
        obj.zone = $("#zone option:selected").val();
        obj.owner = (owner === "-1" ? "" : owner);
        obj.occupier = (occupier === "-1" ? "" : occupier);
        obj.propID = (propID === "-1" ? "" : propID);
    }

    if ($("#zone option:selected").val() === "-1" && $("#obj_fromDate").val() === "" && $("#obj_toDate").val() === "") {
        alert("Kindly provide input.");
    } else if ($("#zone option:selected").val() === "-1" && $("#obj_fromDate").val() === "") {
        alert("Kindly provide both FROM and TO dates.");
    } else if ($("#zone option:selected").val() === "-1" && $("#obj_toDate").val() === "") {
        alert("Kindly provide both FROM and TO dates.");
    } else {
        HNDL_OBJ.searchobjProperty(obj);
    }

});



$(document).on("change", "#zone", function () {
    HNDL_OBJ.resetWard();
    HNDL_OBJ.resetOccupier();
    HNDL_OBJ.resetOwner();
    HNDL_OBJ.resetPropertyId();
    HNDL_OBJ.getWard();
});


$(document).on("change", "#ward", function () {
    HNDL_OBJ.resetOccupier();
    HNDL_OBJ.resetOwner();
    HNDL_OBJ.resetPropertyId();
    HNDL_OBJ.setSearchCriteria();
});


$(document).on("change", "#obj_prop_id", function () {
    if ($("#obj_prop_id option:selected").val() !== "-1") {
        $("#obj_owner,#obj_occupier").prop("disabled", true);
        $("#obj_owner,#obj_occupier").val("-1");
        $("#obj_owner,#obj_occupier").closest(".SumoSelect").find(".CaptionCont span").text("");
        $("#obj_owner,#obj_occupier").closest(".SumoSelect").find(".CaptionCont").addClass("disable_cls");
    } else {
        $("#obj_owner,#obj_occupier").prop("disabled", false);
        $("#obj_owner,#obj_occupier").closest(".SumoSelect").find(".CaptionCont").removeClass("disable_cls");
    }

});