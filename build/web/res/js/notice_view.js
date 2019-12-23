

$(document).ready(function() {
    $("#notice_main_menu").addClass("active");
    NoticeGeneration.resetWard();
    $.post("loadZones", {}, function(result) {
        if (result != undefined) {
            var html = "";
            html += "<option value='-1'>--Select Zone--</option>";
            for (var i = 0; i < result.length; i++) {
                html += "<option value='" + result[i].zoneId + "'>" + result[i].zoneName + "</option>";
                NoticeGeneration.zoneMaster[result[i].zoneId] = result[i].zoneName;
            }
            $("#notice_view_zone").html(html);
        }
    }, 'json').always(function() {
    });
    NoticeGeneration.getWard();
    NoticeGeneration.createDataTable();
});


NoticeGeneration = {
    zoneMaster: {},
    showSearchWindow: function() {
        $("#vn_searchWindow").removeClass("hidden");
        $("#vn_searchResult").addClass("hidden");
    },
    showSearchResultWindow: function() {
        $("#vn_searchWindow").addClass("hidden");
        $("#vn_searchResult").removeClass("hidden");
    },
    generateNotice: function() {
        var sel_zone = $("#notice_zone").val();
        var sel_ward = $("#notice_ward").val();
        var sel_locality = $("#notice_locality").val();
        var sel_propId = $("#notice_prop_id").val();

        if (sel_zone == "-1") {
            alert("Kindly select Zone");
            return false;
        }
        var r = confirm("Are you sure you want to Generate Notice");
        if (r == true) {
            // alert("Generating....")
        } else {
            // alert("Cancelled....")
            return false;
        }

        $.post("generateNotice", {
            zone_id: sel_zone,
            ward_id: sel_ward,
            loc_id: sel_locality,
            prop_id: sel_propId,
            req_type: "generate"

        }, function(result) {

        }, 'json').always(function() {

        });

    },
    propertyMap: {},
    preservePropertyetails: function(detailArr) {
        NoticeGeneration.propertyMap = {};
        if (detailArr === undefined || detailArr === null) {

        } else {
            for (var dt in detailArr) {
                var det = detailArr[dt];
                if (det != null && NoticeGeneration.taxDetailMap[det.propertyUniqueId] !== undefined) {
                    NoticeGeneration.propertyMap[det.propertyUniqueId] = det;
                }
            }
        }

    },
    taxDetailMap: {},
    preserveTAXDetails: function(detailArr) {
        NoticeGeneration.taxDetailMap = {};
        if (detailArr === undefined || detailArr === null) {

        } else {
            for (var dt in detailArr) {
                var det = detailArr[dt];
                if (det != null) {
                    NoticeGeneration.taxDetailMap[det.propertyId] = det;
                }
            }
        }

    },
    propBeans: {},
    getNoticeDetails: function() {

        var sel_zone = $("#notice_view_zone").val();
        var sel_ward = $("#notice_view_ward").val();
        var sel_propId = $("#notice_view_prop_id").val();

        if (sel_zone == "-1" && sel_propId == "") {
            alert("Kindly select zone or enter property id");
            return false;
        }

        var noticeSel = {};
        noticeSel.zoneid = sel_zone;
        noticeSel.ward = sel_ward;
        noticeSel.prop_id = sel_propId;

        var jsonViewCrit = JSON.stringify(noticeSel);
        LOADER.show();
        $.post("getNoticeList", {
            noticeSel: jsonViewCrit
        }, function(data) {
            LOADER.hide();
            NoticeGeneration.destroyDataTable();
            NoticeGeneration.propBeans = {};
            if (data == undefined || data.status == "700") {
                alert("No result found");
                return false;
            } else {

                var taxArr = data.taxBeans;
                var props = data.propBeans;
                NoticeGeneration.preserveTAXDetails(taxArr);
                NoticeGeneration.preservePropertyetails(props);

                var notice_html = "<thead><tr><th>S No.</th>"
                        + "<th>Property Id</th>"
                        + "<th>Action</th>"
                        + "<th>Owner Name</th>"
                        + "<th>Occupier Name</th>"
                        + "<th>Relation With Owner</th>"
                        + "<th>Address</th>"
                        + "<th>Zone</th>"
                        + "<th>Ward</th>"
                        + "<th>Notice No.</th>"
                        + "<th>TAX No.</th>"
                        + "<th>Financial Year</th>"
                        + "</tr></thead><tbody>";

                for (var t in taxArr) {

                    var tax = taxArr[t];
                    //var url = location.href.substring(0, location.href.lastIndexOf('/')) + "/viewNotice?taxNo=" + tax.taxNo;
                    var url = location.href.substring(0, location.href.lastIndexOf('/')) + "/generatePrivateNotice?zoneId=-1&ward=-1&propertyId=" + tax.propertyId;

                    var prop = NoticeGeneration.propertyMap[tax.propertyId];
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

                    notice_html += "<tr><td>" + (parseInt(t) + 1) + "</td>";
                    notice_html += "<td>" + prop.propertyUniqueId + "</td>";
                    notice_html += "<td><a href='" + url + "' target='new_page' >Show notice</a></td>";
                    notice_html += "<td>" + prop.propertyOwner + "</td>";
                    notice_html += "<td>" + prop.propertyOccupierName + "</td>";
                    notice_html += "<td>" + prop.propertyRelationOwner + "</td>";
                    notice_html += "<td>" + prop.completeAddress + "</td>";
                    notice_html += "<td>" + NoticeGeneration.zoneMaster[prop.zoneId] + "</td>";
                    notice_html += "<td>" + NoticeGeneration.wardMaster[prop.ward] + "</td>";
                    notice_html += "<td>" + tax.noticeNo + "</td>";
                    notice_html += "<td>" + tax.taxNo + "</td>";
                    notice_html += "<td> 2019-2020 </td>";
                    notice_html += "</tr>";
                }
                notice_html += "</tbody>";
                $("#vn_searchResult_table").html(notice_html);
                NoticeGeneration.showSearchResultWindow();
                NoticeGeneration.createDataTable();
            }
        }, 'json').always(function() {

        });

    },
    viewNotice: function(taxNo) {

        var url = location.href.substring(0, location.href.lastIndexOf('/')) + "/viewNotice?taxNo=" + taxNo;
        $("#vn_open_pdf").attr("href", url);
        $("#vn_open_pdf").click();
    },
    dataTable: null,
    tableSearchResult: '#vn_searchResult_table',
    createDataTable: function() {
        NoticeGeneration.dataTable = $(NoticeGeneration.tableSearchResult).DataTable({
              "dom": 'Bfrtip',
                        "buttons": [],
                        "lengthMenu": [[10, 25, 50, -1], [10, 25, 50, "All"]],
                        "bDestroy": true,
                        "responsive": true,
                        "paging": true,
                        // "scrollY":200,
                        /* "scrollX":true, */
                        "sPaginationType": "full_numbers"
            //"scrollY": "350px",
      //      "scrollCollapse": true,
//            "info": true,
          //  "paging": true,
           // "sScrollX": "100%",
          //  "sScrollXInner": "100%",
         //   "bScrollCollapse": true
        });
        
          $('.dataTables_wrapper table').wrap('<div class="scroll_table"></div>');
//        $(NoticeGeneration.tableSearchResult + "_paginate").css({
//            "position": "absolute", "top": "0", "right": "0"
//        });
//
//        $(NoticeGeneration.tableSearchResult + "_filter").css({
//            "float": "left"
//        });

    },
    destroyDataTable: function() {
        NoticeGeneration.dataTable.destroy();
        $(NoticeGeneration.tableSearchResult).html("");
    },
    title: "NOTICE REGISTER",
    params: {},
    type: "PDF",
    exportAs: function(type) {

        NoticeGeneration.type = type;

        var html_ = '<li class="clearfix">'
                + '<input id="export_All" type="checkbox" value="">'
                + '<label class="cheklabel" for="export_All">All</label>'
                + '</li>';

        for (var a in MASTERCOLUMN.attrNOTICEGEN) {
            var attr = MASTERCOLUMN.attrNOTICEGEN[a];
            html_ += '<li class="clearfix">'
                    + '<input id="' + attr + '" type="checkbox" value="">'
                    + '<label class="cheklabel" for="name">' + MASTERCOLUMN.attrHead[attr] + '</label>'
                    + '</li>';

        }
        $("#business_entities_list").html(html_);
        $('#export-modal').modal('toggle');
    },
    download: function() {
        $("#exportTitle").val(NoticeGeneration.title);
        $("#windowId").val(Math.random());

        NoticeGeneration.params = new Object();
        NoticeGeneration.params.masterBeans = [];
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

        var propArr = Object.keys(NoticeGeneration.propertyMap);
        for (var p in propArr) {
            var propId = propArr[p];
            var prop = NoticeGeneration.propertyMap[propId];
            var tax = NoticeGeneration.taxDetailMap[prop.propertyUniqueId];
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
                        obj[attr] = NoticeGeneration.zoneMaster[prop[MASTERCOLUMN.attrInBean[attr]]];
                    } else if (attr === "ward") {
                        obj[attr] = NoticeGeneration.wardMaster[prop[MASTERCOLUMN.attrInBean[attr]]];
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

            NoticeGeneration.params.masterBeans.push(obj);

        }

        $("#exportParams").val(JSON.stringify(NoticeGeneration.params));
        $("#exportTHead").val(JSON.stringify(masterHeadArr));
        $("#exportAttrToAdd").val(JSON.stringify(masterAttrArr));
        $("#exportType").val(NoticeGeneration.type);
        $("#expo").submit();
        $('#export-modal').modal('toggle');
    },
    wardMaster: {},
    getWard: function() {

//        if ($("#notice_view_zone option:selected").val() === "-1") {
//            NoticeGeneration.resetWard();
//        } else {
            $.post("getWards", {zone: $("#notice_view_zone option:selected").val()}, function(result) {
                if (result != undefined) {
                    var html = "";
                    html += "<option value='-1'>--Select Ward--</option>";
                    for (var i = 0; i < result.length; i++) {
                        html += "<option value='" + result[i].ward + "'>" + result[i].displayName + "</option>";
                        NoticeGeneration.wardMaster[result[i].ward] = result[i].displayName;
                    }
                    NoticeGeneration.resetWard(html);
                }
            }, 'json').always(function() {

            });
//        }
    },
    resetWard: function(html) {
        if (html === undefined) {
            $("#notice_view_ward").html("<option value='-1'>--Select Ward--</option>");
        } else {
            $("#notice_view_ward").html(html);
        }
    }   
    
}


$(document).on("click", "#export_btn", function() {
    NoticeGeneration.download();
});

//$(document).on("change", "#notice_view_zone", function() {
//    NoticeGeneration.getWard();
//});
$(document).on("change", "#notice_view_ward", function () {
    $("#notice_view_zone").val(zoneWardMaster[$(this).val()]);
});
