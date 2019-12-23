/* 
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

var DATEPICKER = {
    today: null,
    init: function () {
        $("#objectionFrom").datepicker({
            onSelect: function (selected) {
                var dt = new Date(selected);
                dt.setDate(dt.getDate() + 1);
                $("#objectionTo").datepicker("option", "minDate", dt);
            }});
        $("#objectionTo").datepicker({onSelect: function (selected) {
                var dt = new Date(selected);
                dt.setDate(dt.getDate() - 1);
                $("#objectionFrom").datepicker("option", "maxDate", dt);
            }});
        DATEPICKER.setMaxDate($("#objectionFrom"), this.today);
        DATEPICKER.setMaxDate($("#objectionTo"), this.today);
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
    $("#report_main_menu").addClass("active");
    
//    $("#obj_prop_id").select2();
//    $("#obj_occupier").select2();
//    $("#obj_owner").select2();
    
    $.post("loadZones", {}, function (result) {
        if (result !== undefined) {
            var html = "";
            html += "<option value='-1'>--Select Zone--</option>";
            for (var i = 0; i < result.length; i++) {
                html += "<option value='" + result[i].zoneId + "'>" + result[i].zoneName + "</option>";
                ObjectionReport.zoneMaster[result[i].zoneId] = result[i].zoneName;
            }
            $("#zone").html(html);
            $("#zone").SumoSelect({csvDispCount: 3, search: true, searchText: 'Enter here.'});
        }
    }, 'json').always(function () {
    });

    DATEPICKER.init();

});


ObjectionReport = {
   zoneMaster: {},  
   
    objectionMap: {},
    preserveOBJECTIONDetails: function(detailArr) {    
        ObjectionReport.objectionMap = {};
        if (detailArr === undefined || detailArr === null) {
        } else {
            for (var dt in detailArr) {
                var det = detailArr[dt];
                if (det !== null) {
                    ObjectionReport.objectionMap[det.propertyId] = det;                   
                }
            }
        }

    },
    propertyMap: {},
    preservePropertyDetails: function(detailArr) {       
        ObjectionReport.propertyMap = {};
        if (detailArr === undefined || detailArr === null) {

        } else {
            for (var dt in detailArr) {
                var det = detailArr[dt];
                if (det !== null) {
                    ObjectionReport.propertyMap[det.propertyUniqueId] = det;                    
                }
            }
        }

    },
   
   
    ownerNameArr: [],
    occupierNameArr: [],
    propIdArr: [],
    fillSearchCriteria: function() {

        ObjectionReport.ownerNameArr = [];
        ObjectionReport.occupierNameArr = [];
        ObjectionReport.propIdArr = [];

        $.ajax({
            url: "getSearchCriteria1",
            type: 'post',
            data: "zone_id=" + $("#zone").val(),
            dataType: 'json',
            success: function(result) {

                if (result !== undefined && result !== null) {
                    var html_prop = "<option value='-1' selected >--Select Property ID--</option>";
                    var html_own = "<option value='-1' selected >--Select Owner Name --</option>";
                    var html_occ = "<option value='-1' selected >--Select Occupier Name --</option>";

                    result.prop.occupierArr = result.prop.occupierArr === undefined ? [] : result.prop.occupierArr;
                    result.prop.ownerArr = result.prop.ownerArr === undefined ? [] : result.prop.ownerArr;
                    result.prop.propIdArr = result.prop.propIdArr === undefined ? [] : result.prop.propIdArr;

                    ObjectionReport.ownerNameArr = result.prop.ownerArr;
                    ObjectionReport.occupierNameArr = result.prop.occupierArr;
                    ObjectionReport.propIdArr = result.prop.propIdArr;

                    for (var it in ObjectionReport.ownerNameArr) {
                        var item = ObjectionReport.ownerNameArr[it].property_owner;
                        html_own += "<option value='" + item + "'>" + item + "</option>";
                    }

                    for (var it in ObjectionReport.occupierNameArr) {
                        var item = ObjectionReport.occupierNameArr[it].property_occupier_name;
                        html_occ += "<option value='" + item + "'>" + item + "</option>";
                    }

                    for (var it in ObjectionReport.propIdArr) {
                        var item = ObjectionReport.propIdArr[it].property_unique_id;
                        html_prop += "<option value='" + item + "'>" + item + "</option>";
                    }


                    $("#obj_prop_id").html("");
                    $("#obj_prop_id").html(html_prop);

                    $("#obj_owner").html("");
                    $("#obj_owner").html(html_own);

                    $("#obj_occupier").html("");
                    $("#obj_occupier").html(html_occ);

                }

            },
            error: function(e) {
                console.log("ERROR: ", e);
            }
        });
    },
   
   showObjectionList: function () {
         LOADER.show();
        var zoneID = $("#zone").val();
        var fromDate = $("#objectionFrom").val();
        var toDate = $("#objectionTo").val();
        var status = $("#objectionStatus").val();

        //alert("zoneID:" + zoneID + "fromDate:" + fromDate + "toDate:" + toDate + "status" + status);
        if (zoneID === "-1" && fromDate === "" && toDate === "" && status === "-1") {
            $("#objection_table_div").hide();
            alert("Kindly provide input.");
            return false;
        }

        if (fromDate > toDate) {
            alert("From Date should be smaller than To Date.");
            return false;
        }

        var viewObjCrit = new Object();
        viewObjCrit.zone = zoneID;
        viewObjCrit.objectionFrom = fromDate;
        viewObjCrit.objectionTo = toDate;
        viewObjCrit.objectionStatus = status;
        $.post("searchObjReport", {
            jsonViewCrit: JSON.stringify(viewObjCrit)
        }, function (result) {         
           var obj = result.objectionArr;
           var prop = result.propDetailArr;
           ObjectionReport.preserveOBJECTIONDetails(obj);
           ObjectionReport.preservePropertyDetails(prop);
           if (result !== undefined && obj.length > 0) {              

                $("#searchCriteria").hide();
                var obj_html = "";
                obj_html += "<thead>"
                        + "<tr style='text-align:left'><td>S.No.</td><td>Property Id</td>"
                        + "<td>Objection Id</td><td>Zone Name</td>"
                        + "<td>Objection Status</td><td>Objection Applied By</td><td>Objection Raised on</td>"
                obj_html += "</tr></thead><tbody>";              
                for (var i = 0; i < prop.length; i++) {                  
                    obj_html += "<tr><td>" + (parseInt(i) + 1)
                            + "</td>";
                    if (obj[i].propertyId !== undefined) {
                        obj_html += "<td style='text-align:left'>"
                                + obj[i].propertyId
                                + "</td>";
                    } else {
                        obj_html += "<td></td>";
                    }
                    if (obj[i].objectionId !== undefined) {
                        obj_html += "<td style='text-align:left'>"
                                + obj[i].objectionId
                                + "</td>";
                    } else {
                        obj_html += "<td></td>";
                    }                    
                    if (prop[i].zoneId !== undefined) {
                        obj_html += "<td style='text-align:left'>"
                                + prop[i].zoneId
                                + "</td>";
                    } else {
                        obj_html += "<td></td>";
                    }
                   
                    if (obj[i].status !== undefined) {
                        obj_html += "<td>"
                                + obj[i].status
                                + "</td>";
                    } else {
                        obj_html += "<td></td>";
                    }
                    if (obj[i].appliedBy !== undefined) {
                        obj_html += "<td>"
                                + obj[i].appliedBy
                                + "</td>";
                    } else {
                        obj_html += "<td></td>";
                    }
                    if (obj[i].raisedOn !== undefined) {
                        obj_html += "<td>"
                                + obj[i].entrydatetime
                                + "</td>";
                    } else {
                        obj_html += "<td></td>";
                    }
                    obj_html += "</tr>";
                }
                obj_html += "</tbody>";

                $("#objection_table").html(obj_html);
                $("#searchResults").show();
                //$("#searchResults").removeClass("hidden");
                $.extend(false, $.fn.dataTable.defaults, {"searching": true});
                $('#objection_table').DataTable({
                    dom: 'Bfrtip',
                    buttons: [],
                    "lengthMenu": [[10, 25, 50, -1], [10, 25, 50, "All"]],
                    "bDestroy": true,
                    "responsive": true,
                    "paging": true,
                    "sPaginationType": "full_numbers"
                });
                $("#objection_table_div").show();

            } else {
                alert("No result found");
                $("#searchCriteria").show();
                $("#searchResults").hide();
                return false;
            }
        }, 'json').always(function () {
            LOADER.hide();
            $("#objection_table_paginate").css({"position": "absolute", "top": "0", "right": "0"});
            $("#objection_table_filter").css({"float": "left"});            
            
//            $('.button.dt-button, div.dt-button, a.dt-button').css({
//                'background-image': 'none'
//            });
           
        });

        return true;
    },
    
     setSearchCriteria: function() {

        var zone_id = $("#zone").val();

        if (zone_id === "-1") {
            $("#obj_prop_id").html("<option value='-1'>--Select Property ID--</option>");
            $("#obj_owner").html("<option value='-1'>--Select Owner Name--</option>");
            $("#obj_occupier").html("<option value='-1'>--Select Occupier Name--</option>");
        } else {

            LOADER.show();
            $.post("getObjectedPropertyForSearch",
                    {
                        zoneId: zone_id
                    },
            function(result) {

                if (result.Status === "200") {

                    var pr = result.msg.propIdArr;
                    var own = result.msg.ownerArr;
                    var occ = result.msg.occupierArr;

                    var html_prop = "";
                    html_prop += "<option value='-1'>--Select Property ID--</option>";
                    for (var key in pr) {
                        html_prop += "<option value='" + pr[key] + "'>" + pr[key] + "</option>";
                    }
                    $("#obj_prop_id").html("");
                    $("#obj_prop_id").html(html_prop);

                    var html_own = "";
                    html_own += "<option value='-1'>--Select Owner Name--</option>";
                    for (var key in own) {
                        html_own += "<option value='" + own[key] + "'>" + own[key] + "</option>";
                    }
                    $("#obj_owner").html("");
                    $("#obj_owner").html(html_own);

                    var html_occ = "";
                    html_occ += "<option value='-1'>--Select Occupier Name--</option>";
                    for (var key in occ) {
                        html_occ += "<option value='" + occ[key] + "'>" + occ[key] + "</option>";
                    }

                    $("#obj_occupier").html(html_occ);

                }
            }).always(function() {
                LOADER.hide();
            });
        }
    },
    
    showObjectionPrintList: function () {
        var zoneID = $("#zone").val();
        var propId = $("#obj_prop_id").val();
        if (zoneID === "-1" && propId === "-1" ) {
            $("#objection_table_div").hide();
            alert("Kindly provide input.");
            return false;
        }
        var viewObjCrit = new Object();
        viewObjCrit.zone = zoneID;
        viewObjCrit.propId = propId;        
        $.post("loadObjectionData", {
            jsonViewCrit: JSON.stringify(viewObjCrit)
        }, function (result) {          
            LOADER.show();
           var obj = result.objectionArr;
           var prop = result.propDetailArr;
           ObjectionReport.preserveOBJECTIONDetails(obj);
           ObjectionReport.preservePropertyDetails(prop);
           if (result !== undefined && obj.length > 0) {                
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
                    
                $("#searchCriteria").hide();
                var obj_html = "";
                obj_html += "<thead>"
                        + "<tr style='text-align:left'><td>S.No.</td><td>Property Id</td>"
                        + "<td>Objection Id</td><td>Zone Name</td>"
                        + "<td>Objection Status</td><td>Objection Applied By</td><td>Objection Raised on</td><td>Print Objection</td>"
                obj_html += "</tr></thead><tbody>";               
                for (var i = 0; i < prop.length; i++) {                  
                    obj_html += "<tr><td>" + (i + 1)
                            + "</td>";
                    if (obj[i].propertyId !== undefined) {
                        obj_html += "<td style='text-align:left'>"
                                + obj[i].propertyId
                                + "</td>";
                    } else {
                        obj_html += "<td></td>";
                    }
                    if (obj[i].objectionId !== undefined) {
                        obj_html += "<td style='text-align:left'>"
                                + obj[i].objectionId
                                + "</td>";
                    } else {
                        obj_html += "<td></td>";
                    }                   
                    obj_html +="<td>"+(ObjectionReport.zoneMaster[prop[i].zoneId] === undefined ? "" : ObjectionReport.zoneMaster[prop[i].zoneId])+"</td>"; 
                    
                   // obj_html += "<td>" + $("#zone option:selected").text() + "</td>";
                    if (obj[i].status !== undefined) {
                        obj_html += "<td>"
                                + obj[i].status
                                + "</td>";
                    } else {
                        obj_html += "<td></td>";
                    }
                    if (obj[i].appliedBy !== undefined) {
                        obj_html += "<td>"
                                + obj[i].appliedBy
                                + "</td>";
                    } else {
                        obj_html += "<td></td>";
                    }
                    if (obj[i].raisedOn !== undefined) {
                        obj_html += "<td>"
                                + obj[i].entrydatetime
                                + "</td>";
                    } else {
                        obj_html += "<td></td>";
                    }
                    
                    if (obj[i].objectionId !== undefined) {
                        obj_html += "<td style='text-align:left' onclick=\"ObjectionReport.printObjection('"
                                + obj[i].propertyId
                                + "')\"><a href='javascript: void(0)'>Print Objection"
                                + "</a> </td>";
                        } 
                    
                    obj_html += "</tr>";
                }
                obj_html += "</tbody>";

                $("#objection_table").html(obj_html);
                $("#searchResults").show();
                //$("#searchResults").removeClass("hidden");
                $.extend(false, $.fn.dataTable.defaults, {"searching": true});
                $('#objection_table').DataTable({
                    dom: 'Bfrtip',
                    buttons: [],
                    "lengthMenu": [[10, 25, 50, -1], [10, 25, 50, "All"]],
                    "bDestroy": true,
                    "responsive": true,
                    "paging": true,
                    "sPaginationType": "full_numbers"
                });
                $("#objection_table_div").show();

            } else {
                alert("No result found");
                $("#searchArea").show();
                $("#searchResults").hide();
                return false;
            }
        }, 'json').always(function () {
            LOADER.hide();
            $("#objection_table_paginate").css({"position": "absolute", "top": "0", "right": "0"});
            $("#objection_table_filter").css({"float": "left"});            
            
//            $('.button.dt-button, div.dt-button, a.dt-button').css({
//                'background-image': 'none'
//            });
           
        });

        return true;
    },
    
    printObjection: function(property_id) {        
        var obj = ObjectionReport.objectionMap[property_id] ;     
        //console.log(obj);
        if (obj !== undefined) {
            var prop = ObjectionReport.propertyMap[property_id];
            //console.log(prop);
            var print_html = "";
            var raisedOn = obj.raisedOn;
            if (raisedOn !== undefined && raisedOn !== "") {
                raisedOn = Date(1000 * raisedOn);
                raisedOn = raisedOn.substring(3, 15);
            }
            
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
            
            
            print_html += "<html><head><title>Objection Print Receipt (" + obj.propertyId + ")</title></head><body><h1><font size='5' color='black' align='right'><center><U> Objection Acknowledgement Receipt </U><center><font></h1> <img src='res/img/logo.png' />";
            print_html += "<span><font size='4' color='black'>Objection Number <b>" + obj.objectionId + " </b> for the property <b>" + prop.propertyUniqueId + "</b> has been filed by <b>" + obj.raisedBy + "</b> on <b>" + obj.entrydatetime + "</b>.\n\
                           This objection is currently in <b>" + obj.status + " </b> state.</font></span>";
            print_html += "<br/><br/><b><U>Information of Property:</U></b><table>";
            print_html += "<tr size='4' color='black'><td><b>Owner of Property:</b></td><td> " + prop.propertyOwner + "</td></tr>";
            print_html += "<tr size='4' color='black'><td><b>Owner Father Name:</b></td><td> " + prop.propertyOwnerFather + "</td></tr>";
            print_html += "<tr size='4' color='black'><td><b>Occupier Name:</b></td><td> " + prop.propertyOccupierName + "</td></tr>";
            print_html += "<tr size='4' color='black'><td><b>Relation With Owner:</b></td><td> " + prop.propertyRelationOwner + "</td></tr>";
            print_html += "<tr size='4' color='black'><td><b>Address:</b></td><td> " + (prop.completeAddress === undefined ? "" : prop.completeAddress) + "</td></tr>";
            print_html += "<tr size='4' color='black'><td><b>Objection Remarks: </td><td>" + obj.raisedByremarks + "</b></td></tr><br/></table>";
            print_html += "<br/><p align='right'><font size='4' color='black'>Thanks From,<br/>Silvassa Municipal Corporation,<br/>Silvassa</font><br/><br/><font size='4' color='black'>(Authorized Signature of Authority)</font> <br/></p>";
            print_html += "</body></html>";
            var mywindow = window.open('', 'PRINT', 'height=400,width=600');
            mywindow.document.write(print_html);
            mywindow.document.close(); // necessary for IE >= 10
            mywindow.focus(); // necessary for IE >= 10*/
            mywindow.print();
            mywindow.close();

            return true;


        } else {
            alert("Unable to process request.");
            return false;

        }

    },
    
    showSearchWindow: function () {
        $("#searchCriteria").show();
        $("#searchResults").hide();
    },
    
    title: "Objection Detail Report",
    params: {},
    type: "PDF",
    exportAs: function(type) {

        ObjectionReport.type = type;

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
    download: function() {
        $("#exportTitle").val(ObjectionReport.title);
        $("#windowId").val(Math.random());

        ObjectionReport.params = new Object();
        ObjectionReport.params.masterBeans = [];
        var masterAttrArr = [];
        var masterHeadArr = [];
        $(".business_entities_list li input[type=checkbox]").each(function() {
            if ($(this).prop("checked")) {
                var id = $(this).attr("id");
                if (id !== "export_All") {
                    masterAttrArr.push(id);
                    masterHeadArr.push(MASTERCOLUMN.attrHead[id]);
                }
            }
        });

        var propArr = Object.keys(ObjectionReport.propertyMap);
        var objArr = Object.keys(ObjectionReport.objectionMap);
     
        for (var p in propArr) {
            var propId = propArr[p];
            var prop = ObjectionReport.propertyMap[propId];          
            var ob = ObjectionReport.objectionMap[propId];         
                var keys_ = Object.keys(ob);
                for (var k in keys_) {
                    var key = keys_[k];
                    prop[key] = ob[key];
            }

            var obj = {};
            for (var m in masterAttrArr) {
                var attr = masterAttrArr[m];              
                if (prop[MASTERCOLUMN.attrInBean[attr]] === undefined) {
                    obj[attr] = "";
                }
                else {
                    if (attr === "zone") {
                        obj[attr] = ObjectionReport.zoneMaster[prop[MASTERCOLUMN.attrInBean[attr]]];
                    }else {
                        obj[attr] = prop[MASTERCOLUMN.attrInBean[attr]];                        
                    }
                }                
            }            
            ObjectionReport.params.masterBeans.push(obj);
        }

        $("#exportParams").val(JSON.stringify(ObjectionReport.params));
        $("#exportTHead").val(JSON.stringify(masterHeadArr));
        $("#exportAttrToAdd").val(JSON.stringify(masterAttrArr));
        $("#exportType").val(ObjectionReport.type);
        $("#expo").submit();
        $('#export-modal').modal('toggle');
    }

}

$(document).on("click", "#export_btn", function() {
    ObjectionReport.download();
});
//$(document).on("click", "#searchProp", function() {
//    ObjectionReport.searchProperty()
//});

