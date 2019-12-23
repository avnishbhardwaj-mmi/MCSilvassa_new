

$(document).ready(function() {
    $("#report_main_menu").addClass("active");

    $.post("loadZones", {}, function(result) {
        if (result != undefined) {
            var html = "";
            html += "<option value='-1'>--Select Zone--</option>";
            for (var i = 0; i < result.length; i++) {
                html += "<option value='" + result[i].zoneId + "'>" + result[i].zoneName + "</option>";
                REPORT.zoneMaster[result[i].zoneId] = result[i].zoneName;
            }
            $("#zone").html(html);
            $("#zone").SumoSelect({csvDispCount: 3, search: true, searchText: 'Enter here.'});
        }
    }, 'json').always(function() {
    });

});




REPORT = {
    zoneMaster: {},
    searchProperty: function(obj) {


        LOADER.show();
        $.post("searchByArrearDue", {params: JSON.stringify(obj)}, function(data) {

            if (data == undefined || data.status == "700") {
                alert("No result found");
                return false;
            } else {
                REPORT.showTaxDataTable(data);
            }


        }, 'json').always(function() {
            LOADER.hide();
            $("#property_tab_paginate").css({"position": "absolute", "top": "0", "right": "0"});
            $("#property_tab_filter").css({"float": "left"});

        });

    },
    showSearchResult: function() {
        $("#searchWindow").addClass("hidden");
        $("#searchResults").removeClass("hidden");
    },
    showSearchWindow: function() {
        $("#searchWindow").removeClass("hidden");
        $("#searchResults").addClass("hidden");
    },
    propertyMap: {},
    preservePropertyetails: function(detailArr) {
        REPORT.propertyMap = {};
        if (detailArr === undefined || detailArr === null) {

        } else {
            for (var dt in detailArr) {
                var det = detailArr[dt];
                if (det != null) {
                    REPORT.propertyMap[det.propertyUniqueId] = det;
                }
            }
        }

    },
    taxDetailMap: {},
    preserveTAXDetails: function(detailArr) {
        REPORT.taxDetailMap = {};
        if (detailArr === undefined || detailArr === null) {

        } else {
            for (var dt in detailArr) {
                var det = detailArr[dt];
                if (det != null) {
                    REPORT.taxDetailMap[det.propertyId] = det;
                }
            }
        }

    },
    showTaxDataTable: function(data) {

        if (data.status === "200") {

            var taxDetails = data.taxDetails;
            var propDetails = data.propDetails;

            REPORT.preservePropertyetails(propDetails);
            REPORT.preserveTAXDetails(taxDetails);
            REPORT.showSearchResult();
            var tax_html = "<thead>";
            tax_html += "<tr><td>S.No.</td>"
                    + "<td>Property ID</td>"
                    + "<td>Owner Name</td>"
                    + "<th>Occupier Name</th>"
                    + "<th>Relation With Owner</th>"
                    + "<td>Address</td>"
                    + "<td>Zone</td>"
                    + "<td>Assessment Year</td>"
                    + "<td>Tax No.</td>"
                    + "<td>Tax Amount (in Rs.)</td>"
                    + "<td>Notice Generated</td>"
                    + "<td>Generated by</td>"
                    + "<td>Generated On</td>"
//                + "<td>Remarks</td>" 
                    + "</tr></thead>";


            tax_html += "<tbody>";
            for (var t in propDetails) {
                var prop = propDetails[t];
                var rs = REPORT.taxDetailMap[prop.propertyUniqueId];

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
                tax_html += "<td>" + (REPORT.zoneMaster[prop.zoneId] === undefined ? "N/A" : REPORT.zoneMaster[prop.zoneId]) + "</td>";
                //tax_html += "<td>" + rs.financialYear + "</td>";
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

            $("#property_tab").html(tax_html);
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


        } else {
            alert("No Detaails found");
            return false;
        }

    },
    goBack: function() {
        $("#tax_image_div").show();
        $("#tax_comp_div").show();
        $("#tax_table_div").hide();
    },
    title: "Report Arrear Due",
    params: {},
    type: "PDF",
    exportAs: function(type) {

        REPORT.type = type;

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
    download: function() {
        $("#exportTitle").val(REPORT.title);
        $("#windowId").val(Math.random());

        REPORT.params = new Object();
        REPORT.params.masterBeans = [];
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

        var propArr = Object.keys(REPORT.propertyMap);
        for (var p in propArr) {
            var propId = propArr[p];
            var prop = REPORT.propertyMap[propId];
            var tax = REPORT.taxDetailMap[prop.propertyUniqueId];
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
                        obj[attr] = REPORT.zoneMaster[prop[MASTERCOLUMN.attrInBean[attr]]];
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

            REPORT.params.masterBeans.push(obj);

        }

        $("#exportParams").val(JSON.stringify(REPORT.params));
        $("#exportTHead").val(JSON.stringify(masterHeadArr));
        $("#exportAttrToAdd").val(JSON.stringify(masterAttrArr));
        $("#exportType").val(REPORT.type);
        $("#expo").submit();
        $('#export-modal').modal('toggle');
    }
}


$(document).on("click", "#export_btn", function() {
    REPORT.download();
});
$(document).on("click", "#searchProp", function() {

    var obj = {};
    obj.zone = $("#zone option:selected").val();
    obj.paymentStatus = "P";

    if (obj.zone === "-1") {
        alert("Please select zone.");
    } else {
        REPORT.searchProperty(obj);
    }



});

