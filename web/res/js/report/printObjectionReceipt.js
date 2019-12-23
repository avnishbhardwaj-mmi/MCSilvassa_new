/* 
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

var DATEPICKER = {
    today: null,
    init: function() {
        $("#objectionFrom").datepicker({
            onSelect: function(selected) {
                var dt = new Date(selected);
                dt.setDate(dt.getDate() + 1);
                $("#objectionTo").datepicker("option", "minDate", dt);
            }});
        $("#objectionTo").datepicker({onSelect: function(selected) {
                var dt = new Date(selected);
                dt.setDate(dt.getDate() - 1);
                $("#objectionFrom").datepicker("option", "maxDate", dt);
            }});
        DATEPICKER.setMaxDate($("#objectionFrom"), this.today);
        DATEPICKER.setMaxDate($("#objectionTo"), this.today);
    },
    setMinDate: function($element, date) {
        $element.datepicker("option", "minDate", date);
    },
    setMaxDate: function($element, date) {
        $element.datepicker("option", "maxDate", date);
    },
    validate: function(fromDay, from_month, from_year, toDay, to_month, to_year) {

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

$(document).ready(function() {
    $("#print_receipts_menu").addClass("active");

    $("#obj_prop_id").SumoSelect({csvDispCount: 3, search: true, searchText: 'Enter here.'});

    $.post("loadZones", {}, function(result) {
        if (result !== undefined) {
            var html = "";
            html += "<option value='-1'>--Select Zone--</option>";
            for (var i = 0; i < result.length; i++) {
                html += "<option value='" + result[i].zoneId + "'>" + result[i].zoneName + "</option>";
                printObjectionReceipt.zoneMaster[result[i].zoneId] = result[i].zoneName;
            }
            $("#zone").html(html);
            $("#zone").SumoSelect({csvDispCount: 3, search: true, searchText: 'Enter here.'});
        }
    }, 'json').always(function() {
    });

    DATEPICKER.init();

});


printObjectionReceipt = {
    zoneMaster: {},
    objectionMap: {},
    preserveOBJECTIONDetails: function(detailArr) {
        printObjectionReceipt.objectionMap = {};
        if (detailArr === undefined || detailArr === null) {
        } else {
            for (var dt in detailArr) {
                var det = detailArr[dt];
                if (det !== null) {
                    printObjectionReceipt.objectionMap[det.propertyId] = det;
                }
            }
        }

    },
    propertyMap: {},
    preservePropertyDetails: function(detailArr) {
        printObjectionReceipt.propertyMap = {};
        if (detailArr === undefined || detailArr === null) {

        } else {
            for (var dt in detailArr) {
                var det = detailArr[dt];
                if (det !== null) {
                    printObjectionReceipt.propertyMap[det.propertyUniqueId] = det;
                }
            }
        }

    },
    attributeMap: {},
    preserveATTRIBUTEDetails: function(detailArr) {
        printObjectionReceipt.attributeMap = {};
        if (detailArr === undefined || detailArr === null) {
        } else {
            for (var dt in detailArr) {
                var det = detailArr[dt];
                if (det !== null) {
                    printObjectionReceipt.attributeMap[det.key] = det;
                }
            }
        }

    },
    categoryMap: {},
    preserveCATEGORYDetails: function(detailArr) {
        printObjectionReceipt.categoryMap = {};
        if (detailArr === undefined || detailArr === null) {
        } else {
            for (var dt in detailArr) {
                var det = detailArr[dt];
                if (det !== null) {
                    printObjectionReceipt.categoryMap[det.category] = det;
                }
            }
        }

    },
    ownerNameArr: [],
    occupierNameArr: [],
    propIdArr: [],
    fillSearchCriteria: function() {

        printObjectionReceipt.ownerNameArr = [];
        printObjectionReceipt.occupierNameArr = [];
        printObjectionReceipt.propIdArr = [];
        LOADER.show();
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

                    printObjectionReceipt.ownerNameArr = result.prop.ownerArr;
                    printObjectionReceipt.occupierNameArr = result.prop.occupierArr;
                    printObjectionReceipt.propIdArr = result.prop.propIdArr;

                    for (var it in printObjectionReceipt.ownerNameArr) {
                        var item = printObjectionReceipt.ownerNameArr[it].property_owner;
                        html_own += "<option value='" + item + "'>" + item + "</option>";
                    }

                    for (var it in printObjectionReceipt.occupierNameArr) {
                        var item = printObjectionReceipt.occupierNameArr[it].property_occupier_name;
                        html_occ += "<option value='" + item + "'>" + item + "</option>";
                    }

                    for (var it in printObjectionReceipt.propIdArr) {
                        var item = printObjectionReceipt.propIdArr[it].property_unique_id;
                        html_prop += "<option value='" + item + "'>" + item + "</option>";
                    }


                
                    $("#obj_prop_id").html("");
                    $("#obj_prop_id").html(html_prop);

                    $("#obj_prop_id").SumoSelect().sumo.unload();
                    $("#obj_prop_id").SumoSelect({csvDispCount: 3, search: true, searchText: 'Enter here.'});
                    LOADER.hide();
                }

            },
            error: function(e) {
                console.log("ERROR: ", e);
            }
        });
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
    showObjectionPrintList: function() {
        var zoneID = $("#zone").val();
        var propId = $("#obj_prop_id").val();
        if (zoneID === "-1" && propId === "-1") {
            $("#objection_table_div").hide();
            alert("Kindly provide input.");
            return false;
        }
        var viewObjCrit = new Object();
        viewObjCrit.zone = zoneID;
        viewObjCrit.propId = propId;
        $.post("loadObjectionData", {
            jsonViewCrit: JSON.stringify(viewObjCrit)
        }, function(result) {
            LOADER.show();
            var obj = result.objectionArr;
            var prop = result.propDetailArr;
            printObjectionReceipt.preserveOBJECTIONDetails(obj);
            printObjectionReceipt.preservePropertyDetails(prop);
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
                        + "<td>Objection Id</td>"
                        // + "<td>Objection Category</td>"
                        + "<td>Zone Name</td>"
                        + "<td>Objection Status</td><td>Objection Applied By</td><td>Objection Raised on</td><td>Print Objection</td>"
                obj_html += "</tr></thead><tbody>";

                var objArr = printObjectionReceipt.objectionMap;
                var keyArr = Object.keys(objArr);
                for (var k in keyArr) {
                    var key = keyArr[k];
                    var objBean = printObjectionReceipt.objectionMap[key];
                    var prop = printObjectionReceipt.propertyMap[objBean.propertyId];
                    obj_html += "<tr><td>" + (parseInt(k) + 1)
                            + "</td>";
                    if (objBean.propertyId !== undefined) {
                        obj_html += "<td style='text-align:left'>"
                                + objBean.propertyId
                                + "</td>";
                    } else {
                        obj_html += "<td></td>";
                    }
                    if (objBean.objectionId !== undefined) {
                        obj_html += "<td style='text-align:left'>"
                                + objBean.objectionId
                                + "</td>";
                    } else {
                        obj_html += "<td></td>";
                    }

                    obj_html += "<td>" + (printObjectionReceipt.zoneMaster[prop.zoneId] === undefined ? "" : printObjectionReceipt.zoneMaster[prop.zoneId]) + "</td>";

                    // obj_html += "<td>" + $("#zone option:selected").text() + "</td>";
                    if (objBean.status !== undefined) {
                        obj_html += "<td>"
                                + objBean.status
                                + "</td>";
                    } else {
                        obj_html += "<td></td>";
                    }
                    if (objBean.appliedBy !== undefined) {
                        obj_html += "<td>"
                                + objBean.appliedBy
                                + "</td>";
                    } else {
                        obj_html += "<td></td>";
                    }
                    if (objBean.entrydatetime !== undefined) {
                        obj_html += "<td>"
                                + objBean.entrydatetime
                                + "</td>";
                    } else {
                        obj_html += "<td></td>";
                    }

                    if (objBean.objectionId !== undefined) {
                        obj_html += "<td style='text-align:left' onclick=\"printObjectionReceipt.printObjection('"
                                + objBean.propertyId
                                + "')\"><a href='javascript: void(0)'>Print Receipt"
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
        }, 'json').always(function() {
            LOADER.hide();
            $("#objection_table_paginate").css({"position": "absolute", "top": "0", "right": "0"});
            $("#objection_table_filter").css({"float": "left"});

        });

        return true;
    },
    keyArr: {},
    masterAttrArr: [],
    printObjection: function(property_id) {
        var obj = printObjectionReceipt.objectionMap[property_id];
        printObjectionReceipt.keyArr = {};
        var viewObjCrit = new Object();
        viewObjCrit.objectionId = obj.objectionId;
        $.post("getObjectionCategory", {
            jsonViewCrit: JSON.stringify(viewObjCrit)
        }, function(result) {
            LOADER.show();
            printObjectionReceipt.masterAttrArr = [];
            printObjectionReceipt.preserveCATEGORYDetails(result.objCatList);
            var categoryArr = printObjectionReceipt.categoryMap;
            printObjectionReceipt.keyArr = Object.keys(categoryArr);
            for (var k in printObjectionReceipt.keyArr) {
                var key = printObjectionReceipt.keyArr[k];
                printObjectionReceipt.masterAttrArr.push(key);
            }
            printObjectionReceipt.printPreview(property_id, printObjectionReceipt.masterAttrArr);
            LOADER.hide();
        }, 'json').always(function() {
        });

    },
    printPreview: function(property_id, masterAttrArray) {
        var obj = printObjectionReceipt.objectionMap[property_id];
         if (obj !== undefined) {
            var prop = printObjectionReceipt.propertyMap[property_id];
            var editedValueArray = printObjectionReceipt.objectionMap[property_id].objectionBeans;

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
            for (var i = 0; i < editedValueArray.length; i++) {

                print_html += " <tr>";
                print_html += "<td colspan='2'><center><span style='font-weight:bold'>" + editedValueArray[i].attrName + ":</span></center></td>";
                print_html += "</tr>";
                print_html += " <tr> <td style='padding: 0;'> <table style='width: 100%; height: 100%; border:0;'><tr><td style='width: 60px;border: none'><span style='font-weight:bold'>Old Value:</span></td>";
                print_html += " <td style='border: none;  border-left: 1px solid black; text-align: center;'>" + editedValueArray[i].value + "</td> </tr> </table></td>";
                print_html += " <td style='padding: 0;'> <table style='width: 100%; height: 100%; border:0;'><tr><td style='width: 60px; border: none'><span style='font-weight:bold'>New Value:</span></td>";
                print_html += " <td style=' border: none;  border-left: 1px solid black; text-align: center;'>" + editedValueArray[i].newValue + "</td> </tr> </table></td></tr>";

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
            var mywindow = window.open('', 'PRINT', 'height=400,width=600');
            //alert(mywindow.chrome);
            var is_chrome = Boolean(mywindow.chrome);
            mywindow.document.write(print_html);

            if (is_chrome) {
                setTimeout(function () { // wait until all resources loaded 
                    mywindow.document.close(); // necessary for IE >= 10
                    mywindow.focus(); // necessary for IE >= 10
                    mywindow.print(); // change window to winPrint
                    printObjectionReceipt.mywindow.close(); // change window to winPrint
                }, 250);
            } else {
                mywindow.document.close(); // necessary for IE >= 10
                mywindow.focus(); // necessary for IE >= 10
                mywindow.print();
                mywindow.close();
            }
            return true;
        } else {
            alert("Unable to process request.");
            return false;

        }

    },
    showSearchWindow: function() {
        $("#searchCriteria").show();
        $("#searchResults").hide();
    }

}

$(document).on("click", "#export_btn", function() {
    printObjectionReceipt.download();
});
//$(document).on("click", "#searchProp", function() {
//    printObjectionReceipt.searchProperty()
//});

