

$(document).ready(function () {
    $("#obj_main_menu").addClass("active");


    $("#obj_prop_id").SumoSelect({csvDispCount: 3, search: true, searchText: 'Enter here.'});
    $("#ward").SumoSelect({csvDispCount: 3, search: true, searchText: 'Enter here.'});
    $("#obj_occupier").SumoSelect({csvDispCount: 3, search: true, searchText: 'Enter here.'});
    $("#obj_owner").SumoSelect({csvDispCount: 3, search: true, searchText: 'Enter here.'});
    $("#finance_year").SumoSelect({csvDispCount: 3, search: true, searchText: 'Enter here.'});

    GEN_OBJ.resetOccupier();
    GEN_OBJ.resetOwner();
    GEN_OBJ.resetPropertyId();


    $.post("loadZones", {}, function (result) {
        if (result != undefined) {
            var html = "";
            html += "<option value='-1'>--Select Zone--</option>";
            for (var i = 0; i < result.length; i++) {
                html += "<option value='" + result[i].zoneId + "'>" + result[i].zoneName + "</option>";
                GEN_OBJ.zoneMaster[result[i].zoneId] = result[i].zoneName;
            }
            $("#zone").html(html);
            $("#zone").SumoSelect({csvDispCount: 3, search: true, searchText: 'Enter here.'});
        }
    }, 'json').always(function () {
    });

    GEN_OBJ.setFields();

});


var GEN_OBJ = {
    zoneMaster: {},
    attrMaster: {},
    propertyId: null,
    fillDocument: function () {
        $.ajax({
            url: "getVerifyingDocuments",
            type: 'post',
            data: '',
            dataType: 'json',
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
//    New Method  Added By Jay-->
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
//    New Method  Added By Jay End-->
    setFields: function () {
        $.ajax({
            url: "getEditableAttr",
            type: 'post',
            data: '',
            dataType: 'json',
            success: function (result) {
                if (result !== null) {
                    for (var i = 0; i < result.length; i++) {
                        var cat = result[i];
                        var field = {};
                        if (GEN_OBJ.attrMaster[cat[0]] === undefined) {
                            GEN_OBJ.attrMaster[cat[0]] = {};
                            GEN_OBJ.attrMaster[cat[0]].name = cat[1];
                            GEN_OBJ.attrMaster[cat[0]].attr = [];
                            field = {};
                            field.id = cat[2];
                            field.name = cat[3];
                            field.modelAttr = cat[4];
                            field.isComplex = cat[5];
                            GEN_OBJ.attrMaster[cat[0]].isComplex = field.isComplex;
                            GEN_OBJ.attrMaster[cat[0]].attr.push(field);
                        } else {
                            field = {};
                            field.id = cat[2];
                            field.name = cat[3];
                            field.modelAttr = cat[4];
                            field.isComplex = cat[5];
                            GEN_OBJ.attrMaster[cat[0]].isComplex = field.isComplex;
                            GEN_OBJ.attrMaster[cat[0]].attr.push(field);
                        }
                    }
                }
            },
            error: function (e) {
                console.log("ERROR: ", e);
            }
        });
    },
    populateCategory: function () {

        $("#obj_field_category").html("");
        if (GEN_OBJ.attrMaster !== undefined) {
            var catArr = Object.keys(GEN_OBJ.attrMaster);
            var html = "";
            html += "<option value='-1'>--Select Category--</option>";
            for (var c in catArr) {
                var cat = catArr[c];
                html += "<option value='" + cat + "'>" + GEN_OBJ.attrMaster[cat].name + "</option>";
            }
            $("#obj_field_category").html(html);
        }

    },
    populateAttr: function () {

        $("#obj_field_attr").html("");
        var cat = $("#obj_field_category option:selected").val();
        var html = "";
        html += "<option value='-1'>--Select Category--</option>";
        if (GEN_OBJ.attrMaster[cat] !== undefined) {
            var attrArr = GEN_OBJ.attrMaster[cat].attr;

            for (var c in attrArr) {
                var attr = attrArr[c];
                html += "<option value='" + attr.id + "' model_attr='" + attr.modelAttr + "' >" + attr.name + "</option>";
            }
            $("#obj_field_attr").html(html);
        } else {
            $("#obj_field_attr").html(html);
        }

    },
    populateFloorList: function () {
        var html_ = "";
        html_ += "<option value='-1'>--Select Floor--</option>";
        $("#obj_field_floor").html(html_);
        $("#obj_field_floor").prop('disabled', true);
        //Added By Jay
        GEN_OBJ.newObjectionFloorDisable("1");
        //Added By Jay End
        if (GEN_OBJ.floorDetailsObj[GEN_OBJ.propertyId] !== undefined && GEN_OBJ.isComplexCategory()) {
            var floorObj = GEN_OBJ.floorDetailsObj[GEN_OBJ.propertyId];
            var floorArr = Object.keys(floorObj);
            if (floorArr.length > 0) {
                for (var f in floorArr) {
                    var fl = floorArr[f];
                    html_ += "<option value='" + floorObj[fl].pfFloorName + "' >" + floorObj[fl].pfFloorName + "</option>";
                }
                $("#obj_field_floor").prop('disabled', false);
                $("#obj_field_floor").html(html_);
                //Added By Jay
                GEN_OBJ.newObjectionFloorDisable("-1");
                //Added By Jay End
            }
        }
    },
    isComplexCategory: function () {
        var cat = $("#obj_field_category option:selected").val();
        if (GEN_OBJ.attrMaster[cat] !== undefined) {
            return GEN_OBJ.attrMaster[cat].isComplex === "Y";
        } else {
            return false;
        }
    },
    populateAttrValue: function () {
        $("#obj_field_value").val("");
        var floorId = $("#obj_field_floor option:selected").val();
        var modelAttr = $("#obj_field_attr option:selected").attr("model_attr");
        var value = GEN_OBJ.searchResults[GEN_OBJ.propertyId][modelAttr];

        if (GEN_OBJ.isComplexCategory()) {
            if (floorId === "-1") {
                alert("Kindly select floor, first.");
                value = undefined;
            } else {
                value = GEN_OBJ.floorDetailsObj[GEN_OBJ.propertyId][floorId][modelAttr];
            }

        } else {
            value = GEN_OBJ.searchResults[GEN_OBJ.propertyId][modelAttr];
        }

        if (value !== undefined) {
            $("#obj_field_value").val(value);
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

                if (result !== undefined) {

                    var pr = result.prop.propIdArr;
                    var own = result.prop.ownerArr;
                    var occ = result.prop.occupierArr;

                    var html_prop = "";
                    html_prop += "<option value='-1'>--Select Property ID--</option>";
                    for (var key in pr) {
                        html_prop += "<option value='" + pr[key].property_unique_id + "'>" + pr[key].property_unique_id + "</option>";
                    }
                    GEN_OBJ.resetPropertyId(html_prop);

                    var html_own = "";
                    html_own += "<option value='-1'>--Select Owner Name--</option>";
                    for (var key in own) {
                        html_own += "<option value='" + own[key].property_owner + "'>" + own[key].property_owner + "</option>";
                    }
                    GEN_OBJ.resetOwner(html_own);
                    var html_occ = "";
                    html_occ += "<option value='-1'>--Select Occupier Name--</option>";
                    for (var key in occ) {
                        html_occ += "<option value='" + occ[key].property_occupier_name + "'>" + occ[key].property_occupier_name + "</option>";
                    }
                    GEN_OBJ.resetOccupier(html_occ);

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
    searchResults: {},
    searchProperty: function () {

        var propID = $("#obj_prop_id option:selected").val();
        var occupier = $("#obj_occupier option:selected").val();
        var owner = $("#obj_owner option:selected").val();
        var zone = $("#zone option:selected").val();
        var ward = $("#ward option:selected").val();

        var obj = new Object();
        obj.zone = zone;
        obj.ward = ward;
        obj.propID = propID;
        obj.occupier = occupier;
        obj.owner = owner;
        obj.fyear = "";
        if (obj.zone === "-1") {
            alert("Kindly provide zone.");
        } else if (obj.ward === "-1") {// zone not selected,property id is entered
            alert("Kindly provide ward.");
        } else {
            GEN_OBJ.searchResults = {};
            LOADER.show();
            $.post("searchProperty", {
                jsonViewCrit: JSON.stringify(obj)
            }, function (result) {

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
                        GEN_OBJ.searchResults[result[i].propertyUniqueId] = result[i];
                        tab_html += "<tr><td>" + (parseInt(i) + 1) + "</td>";
                        tab_html += "<td title='Click here to view more detail' ><a href='javascript: void(0)' onclick=GEN_OBJ.checkValidityForObjectinGeneration('"
                                + result[i].propertyUniqueId + "') >"
                                + result[i].propertyUniqueId + "</a> </td>";

                        if (result[i].propertyOwner !== undefined) {
                            tab_html += "<td >" + result[i].propertyOwner + "</td>";
                        } else {
                            tab_html += "<td></td>";
                        }

                        if (result[i].propertyOccupierName !== undefined) {
                            tab_html += "<td >" + result[i].propertyOccupierName + "</td>";
                        } else {
                            tab_html += "<td></td>";
                        }

                        if (result[i].propertyRelationOwner !== undefined) {
                            tab_html += "<td >" + result[i].propertyRelationOwner + "</td>";
                        } else {
                            tab_html += "<td></td>";
                        }

                        tab_html += "<td>" + (result[i].completeAddress === undefined ? "" : result[i].completeAddress) + "</td>";
                        if (result[i].zoneId !== undefined) {
                            tab_html += "<td>" + (GEN_OBJ.zoneMaster[result[i].zoneId] === undefined ? "N/A" : GEN_OBJ.zoneMaster[result[i].zoneId]) + "</td>";
                        } else {
                            tab_html += "<td></td>";
                        }
                        if (result[i].ward !== undefined) {
                            tab_html += "<td>" + (GEN_OBJ.wardMaster[result[i].ward] === undefined ? "N/A" : GEN_OBJ.wardMaster[result[i].ward]) + "</td>";
                        } else {
                            tab_html += "<td></td>";
                        }
                        tab_html += "</tr>";
                    }

                    tab_html += "</tbody>";

                    $("#objection_table").html(tab_html);

                    GEN_OBJ.showResultWindow();
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
                    
                     $('.dataTables_wrapper table').wrap('<div class="scroll_table"></div>');

                } else {
                    alert("No result found.");
                    return false;
                }
            }, 'json').always(function () {
                LOADER.hide();
                $("#objection_table_paginate").css({"position": "absolute", "top": "0", "right": "0"});
                $("#objection_table_filter").css({"float": "left"});
            });

        }
    },
    checkValidityForObjectinGeneration: function (propertyId) {
        LOADER.show();
        $.ajax({
            url: "checkValidityForObjectinGeneration",
            type: 'post',
            data: 'propertyId=' + propertyId,
            async: false,
            success: function (result) {
                debugger;
                LOADER.hide();
                if (result.status === "200") {
                    GEN_OBJ.populateDetails(propertyId);
                } else {
                    alert(result.msg);
                }
            },
            error: function (e) {
                LOADER.hide();
                alert("Some error occurred, Please try again");
                console.log("ERROR: ", e);
            }
        });
    },
    floorDetailsObj: {},
    populateDetails: function (propertyId) {
        LOADER.show();
        GEN_OBJ.clearAll();
        GEN_OBJ.fillDocument();
        GEN_OBJ.getVerifyingRelations();
        GEN_OBJ.propertyId = propertyId;
        $.ajax({
            url: "getFloorDetails",
            type: 'post',
            data: 'propertyId=' + propertyId,
            dataType: 'json',
            success: function (result) {
                LOADER.hide();
                GEN_OBJ.floorDetailsObj[GEN_OBJ.propertyId] = {};

                for (var r in result) {
                    var res = result[r];
                    if (res !== "") {
                        GEN_OBJ.floorDetailsObj[GEN_OBJ.propertyId][res.pfFloorName] = res;
                    }
                }

                GEN_OBJ.populatePropertyDetail(propertyId);
            },
            error: function (e) {
                LOADER.hide();
                console.log("ERROR: ", e);
            }
        });

    },
    populatePropertyDetail: function (propertyId) {


        GEN_OBJ.clearPropertyDetail();
        GEN_OBJ.populateCategory();
        var prop = GEN_OBJ.searchResults[propertyId];

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
            $("#prop_address").html((prop.completeAddress === undefined ? "" : prop.completeAddress));
            $("#prop_pincode").html(prop.propertyPincode);
            $("#prop_zone").html((GEN_OBJ.zoneMaster[prop.zoneId] === undefined ? "" : GEN_OBJ.zoneMaster[prop.zoneId]));
            $("#prop_ward").html((GEN_OBJ.wardMaster[prop.ward] === undefined ? "" : GEN_OBJ.wardMaster[prop.ward]));
            $("#prop_sublocality").html(prop.propertySublocality);
            $("#prop_landmark").html(prop.propertyLandmark);
            $("#prop_relation_owner").html(prop.propertyRelationOwner);

            GEN_OBJ.showObjectionWindow();
        }

    },
    clearPropertyDetail: function () {

        $("#prop_id").val("");
        $("#prop_owner").val("");
        $("#prop_occupier").val("");
        $("#prop_address").val("");
        $("#prop_pincode").val("");
        $("#prop_zone").val("");
        $("#prop_ward").val("");
        $("#prop_sublocality").val("");
        $("#prop_landmark").val("");
        $("#prop_relation_owner").val("");
    },
    editedFields: {},
    editField: function () {

        var obj = {};

        obj.cat = $("#obj_field_category option:selected").val();
        obj.catName = $("#obj_field_category option:selected").text();
        obj.attr = $("#obj_field_attr option:selected").val();
        obj.attrName = $("#obj_field_attr option:selected").text();
        obj.isComplex = GEN_OBJ.isComplexCategory() ? 'Y' : 'N';
        obj.floor = "";
        var modelAttr = $("#obj_field_attr option:selected").attr("model_attr");
        var value = "";

        if (GEN_OBJ.isComplexCategory()) {
            obj.floor = $("#obj_field_floor option:selected").val();
            obj.attrName += " - " + obj.floor;
            obj.attr = GEN_OBJ.getFieldId();
            value = GEN_OBJ.floorDetailsObj[GEN_OBJ.propertyId][obj.floor][modelAttr];
        } else {
            value = GEN_OBJ.searchResults[GEN_OBJ.propertyId][modelAttr];
        }

        if (value !== undefined) {
            obj.value = value;
        } else {
            obj.value = "";
        }
        obj.newValue = obj.value;

        GEN_OBJ.editedFields[obj.attr] = obj;

        var html_ = "<tr attr_id='" + obj.attr + "'>";
        html_ += "<td>" + obj.catName + "</td>";
        html_ += "<td>" + obj.attrName + "</td>";
        html_ += "<td>" + obj.value + "</td>";
        html_ += "<td><input class='form-control' placeholder='Enter New Value' type='text' old_value='" + obj.value + "' name='new_value' value='" + obj.value + "'></td>";
        html_ += "<td><a class='do_not_edit' >Remove</a></td>";

        if ($(".no_data_row").length > 0) {
            $(".no_data_row").remove();
        }

        $("#edit_field_table tbody").append(html_);

    },
    clearAll: function () {
        GEN_OBJ.editedFields = {};
        GEN_OBJ.floorDetailsObj = {};
        $("#edit_field_table tbody").html('<tr class="no_data_row" ><td colspan="5"><center>No data available</center></td></tr>');
        $("#obj_field_category").val("-1");
        $("#obj_field_attr").val("-1");
        $("#obj_field_value").val("");
        $("#obj_appliedBy").val("");
        $("#obj_appliedById").val("");

    },
    getFieldId: function () {
        var id = $("#obj_field_attr option:selected").val();
        if (GEN_OBJ.isComplexCategory()) {
            id = $("#obj_field_attr option:selected").val() + $("#obj_field_floor option:selected").val();
        }
        return id;
    },
    verifyAndCommiteObjection: function () {
        var obj = new Object();
        obj.propertyId = GEN_OBJ.propertyId;
        obj.effectingTAX = "";
        obj.appliedBy = $("#obj_appliedBy").val();
        obj.appliedByIdType = $("#obj_appliedByIdType option:selected").val();
        obj.appliedById = $("#obj_appliedById").val();
        obj.raisedByremarks = $("#obj_remarks").val();
        //Added By Jay 
        obj.relation = $("#obj_appliedRelation").val();
        if (obj.relation === "RELATION3") {
            obj.relationifother = $("#obj_appliedRelationIfOther").val();
            if (obj.relationifother.trim() === "") {
                alert("Kindly provide 'Objection Applied By Other Relation'");
            }
        }
        //Added By Jay End
        obj.objectionBeans = [];
        var keyArr = Object.keys(GEN_OBJ.editedFields);
        for (var k in keyArr) {
            var key = keyArr[k];
            obj.objectionBeans.push(GEN_OBJ.editedFields[key]);
        }
        if (obj.appliedBy.trim() === "") {
            alert("Kindly provide 'Objection Applied By Name'");
        } else if (obj.relation.trim() === "-1") {  //Added By Jay
            alert("Kindly provide 'Objection Applied By Relation'");  //Added By Jay End
        } else if (obj.appliedByIdType.trim() === "-1") {
            alert("Kindly provide 'Objection Applied By ID'");
        } else if (obj.appliedById.trim() === "") {
            alert("Kindly provide 'Objection Applied By ID No.'");
        } else if (obj.raisedByremarks.trim() === "") {
            alert("Kindly provide 'Remarks'");
        } else if (Object.keys(obj.objectionBeans).length < 1) {
            alert("Kindly provide 'Objected Attributes'.");
        } else {
            GEN_OBJ.commiteObjection(obj);
        }

    },
    objectionId: null,
    commiteObjection: function (objectionObj) {
        debugger;
        LOADER.show();
        $.ajax({
            url: "raiseObjection",
            type: 'post',
            data: 'objectionTxStr=' + JSON.stringify(objectionObj),
            success: function (result) {
                LOADER.hide();
                if (result.status === "200") {
                    $("#obj_message").html(result.msg);
                    GEN_OBJ.objectionBean = result.objectionBean;
                    GEN_OBJ.objectionId = GEN_OBJ.objectionBean.objectionId;
                    GEN_OBJ.showMessageWindow();
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
    objectionMap: {},
    preserveOBJECTIONDetails: function (detailArr) {
        GEN_OBJ.objectionMap = {};
        if (detailArr === undefined || detailArr === null) {
        } else {
            for (var dt in detailArr) {
                var det = detailArr[dt];
                if (det !== null) {
                    GEN_OBJ.objectionMap[det.propertyId] = det;
                }
            }
        }

    },
    categoryMap: {},
    preserveCATEGORYDetails: function (detailArr) {
        GEN_OBJ.categoryMap = {};
        if (detailArr === undefined || detailArr === null) {
        } else {
            for (var dt in detailArr) {
                var det = detailArr[dt];
                if (det !== null) {
                    GEN_OBJ.categoryMap[det.category] = det;
                }
            }
        }

    },
    keyArr: {},
    masterAttrArr: [],
    mywindow: null,
    printObjectionReceipt: function () {
        //var obj = HNDL_OBJ.objectionMap[HNDL_OBJ.propertyId] ;   
        GEN_OBJ.keyArr = {};
        var viewObjCrit = new Object();
        viewObjCrit.objectionId = GEN_OBJ.objectionId;
        GEN_OBJ.mywindow = window.open('', 'PRINT', 'height=400,width=600');
        $.post("getObjectionCategory", {
            jsonViewCrit: JSON.stringify(viewObjCrit)
        }, function (result) {
            LOADER.show();
            GEN_OBJ.masterAttrArr = [];
            GEN_OBJ.preserveCATEGORYDetails(result.objCatList);
            var categoryArr = GEN_OBJ.categoryMap;
            GEN_OBJ.keyArr = Object.keys(categoryArr);
            for (var k in GEN_OBJ.keyArr) {
                var key = GEN_OBJ.keyArr[k];
                GEN_OBJ.masterAttrArr.push(key);
            }

            GEN_OBJ.printPreview(GEN_OBJ.propertyId, GEN_OBJ.masterAttrArr);
            LOADER.hide();
        }, 'json').always(function () {
        });

    },
    objectionBean: {},
    printPreview: function (property_id, masterAttrArray) {

        var obj = GEN_OBJ.objectionBean;
        //var obj = GEN_OBJ.objectionMap[property_id] ;       
        if (obj !== undefined) {
            var prop = GEN_OBJ.searchResults[property_id];
            var print_html = "";

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
            for (i = 0; i < masterAttrArray.length; i++) {

                print_html += " <tr>";
                print_html += "<td colspan='2'><center><span style='font-weight:bold'>" + GEN_OBJ.editedFields[Object.keys(GEN_OBJ.editedFields)[0]].attrName + ":</span></center></td>";
                print_html += "</tr>";
                print_html += " <tr> <td style='padding: 0;'> <table style='width: 100%; height: 100%; border:0;'><tr><td style='width: 60px;border: none'><span style='font-weight:bold'>Old Value:</span></td>";
                print_html += " <td style='border: none;  border-left: 1px solid black; text-align: center;'>" + GEN_OBJ.editedFields[Object.keys(GEN_OBJ.editedFields)[i]].value + "</td> </tr> </table></td>";
                print_html += " <td style='padding: 0;'> <table style='width: 100%; height: 100%; border:0;'><tr><td style='width: 60px; border: none'><span style='font-weight:bold'>New Value:</span></td>";
                print_html += " <td style=' border: none;  border-left: 1px solid black; text-align: center;'>" + GEN_OBJ.editedFields[Object.keys(GEN_OBJ.editedFields)[i]].newValue + "</td> </tr> </table></td></tr>";

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
            var is_chrome = Boolean(GEN_OBJ.mywindow.chrome);
            GEN_OBJ.mywindow.document.write(print_html);
            if (is_chrome) {
                setTimeout(function () { // wait until all resources loaded 
                    GEN_OBJ.mywindow.document.close(); // necessary for IE >= 10
                    GEN_OBJ.mywindow.focus(); // necessary for IE >= 10
                    GEN_OBJ.mywindow.print(); // change window to winPrint
                    GEN_OBJ.mywindow.close(); // change window to winPrint
                }, 250);
            } else {
                GEN_OBJ.mywindow.document.close(); // necessary for IE >= 10
                GEN_OBJ.mywindow.focus(); // necessary for IE >= 10
                GEN_OBJ.mywindow.print();
                GEN_OBJ.mywindow.close();
            }
            return true;


        } else {
            alert("Unable to process request.");
            return false;

        }

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
    newObjectionFloorDisable: function (flg) {
        if (flg === "-1") {
            $("#jFloorId").find("label,select,input,div,p,span").attr("disabled", false);
            $("#jFloorId").find("select").css("background-color", "white");
            $("#jFloorId").find("label,select,input,div,p,span").css("cursor", "pointer");

        } else {
            $("#jFloorId").find("label,select,input,div,p,span").css("cursor", "not-allowed");
            $("#jFloorId").find("select").css("background-color", "darkgrey");
            $("#jFloorId").find("label,select,input,div,p,span").attr("disabled", true);
        }
    },
    OtherHideShow: function () {
        var val = $("#obj_appliedRelation").val();
        if (val === "RELATION3") {
            $("#jIfOther").find("label,select,input,div,p,span").attr("disabled", false);
            $("#jIfOther").find("label,select,input,div,p,span").css("cursor", "pointer");
            $("#jIfOther").find("p").css("background-color", "white");

        } else {
            $("#jIfOther").find("label,select,input,div,p,span").css("cursor", "not-allowed");
            $("#jIfOther").find("label,select,input,div,p,span").attr("disabled", true);
            $("#jIfOther").find("p").css("background-color", "darkgrey");
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
                        GEN_OBJ.wardMaster[result[i].ward] = result[i].displayName;
                    }
                    GEN_OBJ.resetWard(html);
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

    }
};


$(document).on("click", "#searchProp", function () {
    if ($("#zone").val() === "-1") {
        alert("Kindly provide input.");
    } else {
        GEN_OBJ.searchProperty();
    }

});
$(document).on("click", "#obj_submit", function () {
    GEN_OBJ.verifyAndCommiteObjection();
});

$(document).on("change", "#obj_field_category", function () {
    GEN_OBJ.populateAttr();
    GEN_OBJ.populateFloorList();
});
$(document).on("change", "#obj_field_attr", function () {
    GEN_OBJ.populateAttrValue();
});
$(document).on("change", "#obj_field_floor", function () {
    $("#obj_field_attr").val("-1");
    $("#obj_field_value").val("");
});

$(document).on("focusout", "#edit_field_table input[name=new_value]", function () {

    var input_ = $(this);
    if (input_.val() === "" || input_.val().trim() === "") {
        alert("Field cannot be blank");
        var old_value = input_.attr("old_value");
        input_.val(old_value);
    }
    var id_ = input_.closest("tr").attr("attr_id");
    if (GEN_OBJ.editedFields[id_] !== undefined) {
        GEN_OBJ.editedFields[id_].newValue = input_.val();
    }
});

$(document).on("click", "#obj_edit_btn", function () {

    if ($("#obj_field_category option:selected").val() === "-1") {
        alert("Kindly select category.");
    } else if ($("#obj_field_attr option:selected").val() === "-1") {
        alert("Kindly select attribute to edit.");
    } else if (GEN_OBJ.editedFields[GEN_OBJ.getFieldId()] !== undefined) {
        alert("Attribute already edited.");
    } else if (GEN_OBJ.isComplexCategory() && $("#obj_field_floor option:selected").val() === "-1") {

        alert("Kindly select floor.");

    } else {
        GEN_OBJ.editField();
    }

});
$(document).on("click", ".do_not_edit", function () {
    var tr_ = $(this).closest('tr');
    delete  GEN_OBJ.editedFields[tr_.attr("attr_id")];

    if (Object.keys(GEN_OBJ.editedFields).length < 1) {
        $("#edit_field_table tbody").html('<tr class="no_data_row" ><td colspan="5"><center>No data available</center></td></tr>');
    }
    tr_.remove();
});





$(document).on("change", "#zone", function () {
    GEN_OBJ.resetWard();
    GEN_OBJ.resetOccupier();
    GEN_OBJ.resetOwner();
    GEN_OBJ.resetPropertyId();
    GEN_OBJ.getWard();
});


$(document).on("change", "#ward", function () {
    GEN_OBJ.resetOccupier();
    GEN_OBJ.resetOwner();
    GEN_OBJ.resetPropertyId();
    GEN_OBJ.getSearchCriteria();
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
