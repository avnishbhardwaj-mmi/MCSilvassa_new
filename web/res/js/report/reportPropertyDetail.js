

$(document).ready(function() {
    $("#report_main_menu").addClass("active");
//    $("#zone").select2();
//    $("#rep_prop_id").select2();
//    $("#rep_occupier").select2();
//    $("#rep_owner").select2();

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



var REPORT = {
    zoneMaster: {},
    getPropertyDetails: function() {
        var zone_id = $("#zone").val();
        LOADER.show();
        $.post("getSearchCriteria1", {zone_id: zone_id},
        function(result) {
            if (result != undefined) {
                var pr = result.prop.propIdArr;
                var html_prop = "";
                html_prop += "<option value='-1'>--Select Property ID--</option>";
                for (var key in pr) {
                    html_prop += "<option value='" + pr[key].property_unique_id + "'>" + pr[key].property_unique_id + "</option>";
                }

                $("#rep_prop_id").html("");
                $("#rep_prop_id").html(html_prop);
                
                $("#rep_prop_id").SumoSelect().sumo.unload();
                $("#rep_prop_id").SumoSelect({csvDispCount: 3, search: true, searchText: 'Enter here.'});
                
                LOADER.hide();
            }
        });

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
    searchProperty: function(obj) {

        LOADER.show();

        $.post("searchByPropertyDetail",
                {
                    params: JSON.stringify(obj)
                },
        function(data) {

            var propArr = data.propertyArr;

            REPORT.preserveTAXDetails(data.taxDetailArr);
            REPORT.preservePropertyetails(data.propertyArr);

            if (propArr !== undefined && propArr != null) {
                REPORT.showSearchResult();

                var tab_html = "";
                tab_html += "<thead>"
                        + "<tr >"
                        + "<th>S.No.</th>"
                        + "<th>Property ID</th>"
                        + "<th>Owner Name</th>"
                        + "<th>Occupier Name</th>"
                        + "<th>Relation With Owner</th>"
                        + "<th>Address</th>"
                        + "<th>Zone</th>"
                        + "<th>Tax Generated</th>"
                        + "<th>Tax Amount</th>"
                        + "<th>Notice Generated</th>"
                        + "</tr></thead>";
                tab_html += "<tbody>";
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
                    prop.propertySublocality = (prop.propertySublocality === undefined || prop.propertySublocality === null) ? "" : prop.propertySublocality;
                    prop.propertyLandmark = (prop.propertyLandmark === undefined || prop.propertyLandmark === null) ? "" : prop.propertyLandmark;
                    prop.completeAddress = (prop.completeAddress === undefined || prop.completeAddress === null) ? "" : prop.completeAddress;


                    tab_html += "<tr><td>" + (parseInt(i) + 1) + "</td>";
                    tab_html += "<td>" + prop.propertyUniqueId + "</td>";
                    tab_html += "<td >" + prop.propertyOwner + "</td>";
                    tab_html += "<td >" + prop.propertyOccupierName + "</td>";
                    tab_html += "<td >" + prop.propertyRelationOwner + "</td>";
                    tab_html += "<td>" + (prop.completeAddress === undefined ? "" : prop.completeAddress) + "</td>";
                    tab_html += "<td>" + (REPORT.zoneMaster[prop.zoneId] === undefined ? "N/A" : REPORT.zoneMaster[prop.zoneId]) + "</td>";

                    if (REPORT.taxDetailMap[prop.propertyUniqueId] === undefined) {
                        tab_html += "<td>No</td>";
                        tab_html += "<td>No</td>";
                        tab_html += "<td>No</td>";
                    } else {
                        tab_html += "<td>Yes</td>";
                        tab_html += "<td>" + (REPORT.taxDetailMap[prop.propertyUniqueId].grandTotal == undefined ? '' : REPORT.taxDetailMap[prop.propertyUniqueId].grandTotal) + " </td>";
                        if (REPORT.taxDetailMap[prop.propertyUniqueId].noticeGenerated === "Y") {
                            tab_html += "<td>Yes</td>";
                        } else {
                            tab_html += "<td>No</td>";
                        }
                    }

                    tab_html += "</tr>";
                }

                tab_html += "</tbody>";
                $('#property_tab').html(tab_html);
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

            }
            else {
                alert("No propArr found");
                return false;
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
    title: "Report Property Master",
    params: {},
    type: "PDF",
    exportAs: function(type) {

        REPORT.type = type;

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
    obj.propId = $("#rep_prop_id option:selected").val();
    obj.PAN = $("#rep_pan_no").val();
    obj.ADHAAR = $("#rep_adhaar").val();

    if (obj.zone === "-1") {
        alert("Please select zone.");
    } else {
        REPORT.searchProperty(obj);
    }
    


});

