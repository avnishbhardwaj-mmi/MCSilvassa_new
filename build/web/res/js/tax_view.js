

$(document).ready(function () {
    $("#tax_main_menu").addClass("active");
    TaxGeneration.resetWard();
    $.post("loadZones", {}, function (result) {
        if (result != undefined) {
            var html = "";
            html += "<option value='-1'>--Select Zone--</option>";
            for (var i = 0; i < result.length; i++) {
                html += "<option value='" + result[i].zoneId + "'>" + result[i].zoneName + "</option>";
                TaxGeneration.zoneMaster[result[i].zoneId] = result[i].zoneName;
            }
            $("#prop_tax_zone").html(html);
        }
    }, 'json').always(function () {
    });
    TaxGeneration.getWard();
});



TaxGeneration = {
    zoneMaster: {},
    assessMentList: function () {
//        var sel_zone = $("#prop_tax_zone option:selected").val();
//        var sel_ward = $("#prop_tax_ward option:selected").val();
//        var sel_propId = $("#prop_tax_propid").val();
//        if (sel_zone == "-1" && sel_propId == "") {
//            alert("Kindly provide input");
//            return false;
//        } 
        var sel_zone = "";
        var sel_prop_id_input = $("#prop_id_input").val();
        var sel_ward = $("#ward").val();
        var sel_occ = $("#occ_name").val();
        var sel_owner_id = $("#ownerid").val();
        var sel_locality = $("#locality").val();
        var sel_aadhar_num = $("#src_aadhar_no").val();
        var sel_propertyCategory = $("#category").val();
        var sel_Phone_no = $("#Phone_No").val();
        var sel_Locality = $("#Locality").val();

        $("#validateid").html('');
        if (sel_prop_id_input == "" && sel_ward == "" && sel_occ == "" && sel_occ == "" && sel_owner_id == "" && sel_locality == "" && sel_aadhar_num == "" && sel_propertyCategory == "" && sel_Phone_no == "" && sel_Locality == "") {
            // alert("Please select atleast one filter");
            $("#validateid").html("Please select at least one filter");
            return false;
        }
        else {
            $("#as_zone").val(sel_zone);
            $("#as_ward").val(sel_ward);
            $("#as_prop_id_input").val(sel_prop_id_input);
            $("#as_occ_name").val(sel_occ);
            $("#as_ownerid").val(sel_owner_id);
            $("#as_locality").val(sel_locality);
            $("#as_src_aadhar_no").val(sel_aadhar_num);
            $("#as_category").val(sel_propertyCategory);
            $("#as_Phone_No").val(sel_Phone_no);
            $("#as_Locality").val(sel_Locality);
            $("#as_type").val("PDF");
            $("#as_title").val("AssessmentList");
            $("#as_windowId").val(Math.random());
            LOADER.show();
            $("#as_expo").submit();
            TaxGeneration.checkCookie();
        }
    },
    checkCookie: function () {
        var cookieVal = getCookie("windowId");
        if (cookieVal !== 'undefined' && cookieVal != null && cookieVal === $("#as_windowId").val()) {
            LOADER.hide();
        } else {
            setTimeout("TaxGeneration.checkCookie();", 1000);
        }
    },
    viewTax: function () {

        /*  var sel_zone = $("#prop_tax_zone option:selected").val();
         var sel_ward = $("#prop_tax_ward option:selected").val();
         var sel_propId = $("#prop_tax_propid").val();
         if (sel_zone == "-1" && sel_propId == "") {
         alert("Kindly provide input");
         return false;
         }*/

        var zone_id = "";
        var prop_id_input = $("#prop_id_input").val();
        var ward = $("#ward").val();
        var occ = $("#occ_name").val();
        var owner_id = $("#ownerid").val();
        var locality = $("#locality").val();
        var aadhar_num = $("#src_aadhar_no").val();
        var propertyCategory = $("#category").val();
        //newly added
        var Phone_no = $("#Phone_No").val();
        var Locality = $("#Locality").val();
//        if (zone_id === "-1") {
//            alert("Kindly provide zone.");
//        } else 
        $("#validateid").html('');
        if (prop_id_input == "" && ward == "" && occ == "" && occ == "" && owner_id == "" && locality == "" && aadhar_num == "" && propertyCategory == "" && Phone_no == "" && Locality == "") {
            $("#validateid").html("Please select at least one filter");
            //alert("Please select atleast one filter");
            return false;
        }


        var taxSel = {};
        taxSel.zoneid = zone_id;
        taxSel.wardid = ward;
        taxSel.prop_id = prop_id_input;
        taxSel.occ = occ;
        taxSel.owner_id = owner_id;
        taxSel.locality = locality;
        taxSel.aadhar_num = aadhar_num;
        taxSel.propertyCategory = propertyCategory;
        taxSel.Phone_no = Phone_no;
        taxSel.Locality = Locality;

        var jsonViewCrit = JSON.stringify(taxSel);

        LOADER.show();
        $.post("viewTaxDetails", {
            taxObj: jsonViewCrit
        }, function (result) {

            $("#validateid").html('');
            if (result == undefined || result.status == "700") {
                $("#validateid").html("No result found");
                //alert("No result found");
                return false;
            }
            TaxGeneration.showTaxDataTable(result);

        }, 'json').always(function () {
            $("#loading").hide();
//            $('.button.dt-button, div.dt-button, a.dt-button').css({
//                'background-image': 'none'
//            });

//            $("#tax_table_paginate").css({
//                "position": "absolute"
//            });
//            $("#tax_table_paginate").css({
//                "top": 0
//            });
//            $("#tax_table_paginate").css({
//                "right": "0"
//            });
            LOADER.hide();
        });

    },
    propertyMap: {},
    preservePropertyetails: function (detailArr) {
        TaxGeneration.propertyMap = {};
        if (detailArr === undefined || detailArr === null) {

        } else {
            for (var dt in detailArr) {
                var det = detailArr[dt];
                if (det != null) {
                    TaxGeneration.propertyMap[det.propertyUniqueId] = det;
                }
            }
        }

    },
    taxDetailMap: {},
    preserveTAXDetails: function (detailArr) {
        TaxGeneration.taxDetailMap = {};
        if (detailArr === undefined || detailArr === null) {

        } else {
            for (var dt in detailArr) {
                var det = detailArr[dt];
                if (det != null) {
                    TaxGeneration.taxDetailMap[det.propertyId] = det;
                }
            }
        }

    },
    showTaxDataTable: function (data) {

        
        if (data.status === "200") {

            var taxDetails = data.taxDetails;
            var propDetails = data.propDetails;

            TaxGeneration.preservePropertyetails(propDetails);
            TaxGeneration.preserveTAXDetails(taxDetails);

            var tax_html = "<thead>";
            tax_html += "<tr><th>S.No.</th>"
                    + "<th>Property ID</th>"
                    + "<th>Owner Name</th>"
                    + "<th>Occupier Name</th>"
                    + "<th>Relation With Owner</th>"
                    + "<th>Address</th>"
//                    + "<th>Zone</th>"
                    + "<th>Ward</th>"
                    + "<th>Assessment Year</th>"
                    + "<th>Tax No.</th>"
                    + "<th>Tax Amount (in Rs.)</th>"
                    + "<th>Notice Generated</th>"
                    + "<th>Generated by</th>"
                    + "<th>Generated On</th>"
//                + "<td>Remarks</td>" 
                    + "</tr></thead>";

            $("#tax_image_div").hide();
            $("#tax_comp_div").hide();

            tax_html += "<tbody>";
            for (var t in taxDetails) {
                var rs = taxDetails[t];
                var prop = TaxGeneration.propertyMap[rs.propertyId];

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


                rs.financialYear = (rs.financialYear === undefined || rs.financialYear === null) ? "" : rs.financialYear;
                rs.taxNo = (rs.taxNo === undefined || rs.taxNo === null) ? "" : rs.taxNo;
                rs.grandTotal = (rs.grandTotal === undefined || rs.grandTotal === null) ? "" : rs.grandTotal;
                rs.noticeGenerated = (rs.noticeGenerated === undefined || rs.noticeGenerated === null) ? "" : rs.noticeGenerated;
                rs.generatedBy = (rs.generatedBy === undefined || rs.generatedBy === null) ? "" : rs.generatedBy;
                rs.generatedOn = (rs.generatedOn === undefined || rs.generatedOn === null) ? "" : rs.generatedOn;

                tax_html += "<tr>";
                tax_html += "<td>" + (parseInt(t) + 1) + "</td>";
                tax_html += "<td>" + prop.propertyUniqueId + "</td>";
                tax_html += "<td>" + prop.propertyOwner + "</td>";
                tax_html += "<td>" + prop.propertyOccupierName + "</td>";
                tax_html += "<td>" + prop.propertyRelationOwner + "</td>";
                tax_html += "<td> " + (prop.completeAddress === undefined ? "" : prop.completeAddress) + " </td>";
//                tax_html += "<td>" + (TaxGeneration.zoneMaster[prop.zoneId] === undefined ? "N/A" : TaxGeneration.zoneMaster[prop.zoneId]) + "</td>";
                tax_html += "<td>" + (TaxGeneration.wardMaster[prop.ward] === undefined ? "N/A" : TaxGeneration.wardMaster[prop.ward]) + "</td>";
                // tax_html += "<td>" + rs.financialYear + "</td>";
                tax_html += "<td> 2019-2020 </td>";
                tax_html += "<td>" + rs.taxNo + "</td>";
                tax_html += "<td>" + rs.grandTotal + "</td>";
                if (rs.noticeGenerated === 'Y') {
                    tax_html += "<td>Yes</td>";
                } else {
                    tax_html += "<td>No</td>";
                }

                tax_html += "<td>" + rs.generatedBy + "</td>";
                tax_html += "<td>" + rs.generatedOn + "</td>";
//            tax_html += "<td>Remarks</td>";
                tax_html += "</tr>";
            }

            tax_html += "</tbody>";

            $("#tax_table").html(tax_html);
            $('#tax_table').DataTable({
                dom: 'Bfrtip',
                buttons: [
                    'excel', 'pdf'
                ],
                pageLength: 13,
                pagingType: 'full_numbers',
                bDestroy: true,
                lengthMenu: [[10, 25, 50, -1], [10, 25, 50, 'All']],
            });
//            $("#tax_table_paginate").css({"position": "absolute", "top": "0", "right": "0"});
//            $("#tax_table_filter").css({"float": "left"});

            $("#tax_table_div").show();
            $('.dataTables_wrapper table').wrap('<div class="scroll_table"></div>');


        } else {
            alert("No Details found");
            return false;
        }

    },
    goBack: function () {
        $("#tax_image_div").show();
        $("#tax_comp_div").show();
        $("#tax_table_div").hide();
    },
    title: "ASSESSMENT REGISTER",
    params: {},
    type: "PDF",
    exportAs: function (type) {

        TaxGeneration.type = type;

        var html_ = '<li class="clearfix">'
                + '<input id="export_All" type="checkbox" value="">'
                + '<label class="cheklabel" for="export_All">All</label>'
                + '</li>';

        for (var a in MASTERCOLUMN.attrTAXGEN) {
            var attr = MASTERCOLUMN.attrTAXGEN[a];
            html_ += '<li class="clearfix">'
                    + '<input id="' + attr + '" type="checkbox" value="">'
                    + '<label class="cheklabel" for="name">' + MASTERCOLUMN.attrHead[attr] + '</label>'
                    + '</li>';

        }
        $("#business_entities_list").html(html_);
        $('#export-modal').modal('toggle');
    },
    download: function () {
        $("#exportTitle").val(TaxGeneration.title);
        $("#windowId").val(Math.random());

        TaxGeneration.params = new Object();
        TaxGeneration.params.masterBeans = [];
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

        var propArr = Object.keys(TaxGeneration.propertyMap);
        for (var p in propArr) {
            var propId = propArr[p];
            var prop = TaxGeneration.propertyMap[propId];
            var tax = TaxGeneration.taxDetailMap[prop.propertyUniqueId];
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
                        obj[attr] = TaxGeneration.zoneMaster[prop[MASTERCOLUMN.attrInBean[attr]]];
                    } else if (attr === "ward") {
                        obj[attr] = TaxGeneration.wardMaster[prop[MASTERCOLUMN.attrInBean[attr]]];
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

            TaxGeneration.params.masterBeans.push(obj);

        }

        $("#exportParams").val(JSON.stringify(TaxGeneration.params));
        $("#exportTHead").val(JSON.stringify(masterHeadArr));
        $("#exportAttrToAdd").val(JSON.stringify(masterAttrArr));
        $("#exportType").val(TaxGeneration.type);
        $("#expo").submit();
        $('#export-modal').modal('toggle');
    },
    wardMaster: {},
    getWard: function () {

//        if ($("#prop_tax_zone option:selected").val() === "-1") {
//            TaxGeneration.resetWard();
//        } else {
        $.post("getWards", {zone: $("#prop_tax_zone option:selected").val()}, function (result) {
            if (result != undefined) {
                var html = "";
                html += "<option value='-1'>--Select Ward--</option>";
                for (var i = 0; i < result.length; i++) {
                    html += "<option value='" + result[i].ward + "'>" + result[i].displayName + "</option>";
                    TaxGeneration.wardMaster[result[i].ward] = result[i].displayName;
                }
                TaxGeneration.resetWard(html);
            }
        }, 'json').always(function () {

        });
//        }
    },
    resetWard: function (html) {
        if (html === undefined) {
            $("#prop_tax_ward").html("<option value='-1'>--Select Ward--</option>");
        } else {
            $("#prop_tax_ward").html(html);
        }
    }
}


$(document).on("click", "#export_btn", function () {
    TaxGeneration.download();
});

//$(document).on("change", "#prop_tax_zone", function () {
//    TaxGeneration.getWard();
//});
$(document).on("change", "#prop_tax_ward", function () {
    $("#prop_tax_zone").val(zoneWardMaster[$("#prop_tax_ward").val()]);
});
