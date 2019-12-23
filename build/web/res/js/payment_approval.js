

$(document).ready(function () {
    $("#payment_approval_main").addClass("active");
    PaymentApp.resetWard();
    $.post("loadZones", {}, function (result) {
        if (result != undefined) {
            var html = "";
            html += "<option value='-1'>--Select Zone--</option>";
            for (var i = 0; i < result.length; i++) {
                html += "<option value='" + result[i].zoneId + "'>" + result[i].zoneName + "</option>";
                PaymentApp.zoneMaster[result[i].zoneId] = result[i].zoneName;
            }
            $("#pay_view_zone").html(html);
        }
    }, 'json').always(function () {
    });
    PaymentApp.fillBankList();
    PaymentApp.createDataTable();
    PaymentApp.getWard();
});


PaymentApp = {
    zoneMaster: {},
    showSearchWindow: function () {
        $("#vn_searchWindow").removeClass("hidden");
        $("#vn_searchResult").addClass("hidden");
    },
    showSearchResultWindow: function () {
        $("#vn_searchWindow").addClass("hidden");
        $("#vn_searchResult").removeClass("hidden");
    },
    propertyMap: {},
    preservePropertyetails: function (detailArr) {
        PaymentApp.propertyMap = {};
        if (detailArr === undefined || detailArr === null) {

        } else {
            for (var dt in detailArr) {
                var det = detailArr[dt];
                if (det != null && PaymentApp.paymentMap[det.propertyUniqueId] !== undefined) {
                    PaymentApp.propertyMap[det.propertyUniqueId] = det;
                }
            }
        }

    },
    paymentMap: {},
    preservePayment: function (detailArr) {
        PaymentApp.paymentMap = {};
        if (detailArr === undefined || detailArr === null) {

        } else {
            for (var dt in detailArr) {
                var det = detailArr[dt];
                if (det != null) {
                    PaymentApp.paymentMap[det.propId] = det;
                }
            }
        }

    },
    showPendingPayments: function () {

        //var sel_zone = $("#pay_view_zone").val();
        //var sel_ward = $("#pay_view_ward").val();

      $("#validateid").html('');
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
        var Phone_no = $("#Phone_No").val();
        var Locality = $("#Locality").val();
        if (prop_id_input == "" && ward == "" && occ == "" && occ == "" && owner_id == "" && locality == "" && aadhar_num == "" && propertyCategory == "" && Phone_no == "" && Locality == "") {
            //alert("Please select atleast one filter");
            $("#validateid").html("Please select at least one filter"); 
            return false;
        }


//        var paySel = {};
//        paySel.zone_id = sel_zone;
//        paySel.ward = sel_ward;




            var obj = {};
            obj.zone_id = zone_id;
            obj.ward = ward;
            obj.property_id = property_id;
            obj.owner_name = owner_id;
            obj.occ_name = occ;
            obj.locality = locality;
            obj.aadhar_num = aadhar_num;
            obj.propertyCategory = propertyCategory;
            obj.prop_id_input = prop_id_input;
            obj.phone_no = Phone_no;
            obj.Locality = Locality;
        var jsonViewCrit = JSON.stringify(obj);
        LOADER.show();
        $.post("getPaymentsForApproval", {param: jsonViewCrit}, function (data) {
            LOADER.hide();
            PaymentApp.destroyDataTable();

            if (data.code === "200") {
                alert("No details found.");
                return false;
            } else {

                var paymentArr = data.paymentBeanList;
                var props = data.propertyList;
                PaymentApp.preservePayment(paymentArr);
                PaymentApp.preservePropertyetails(props);

                var pay_html = "<thead><tr><th>S No.</th>"
                        + "<th>Payment Ref. Id</th>"
                        + "<th>Property Id</th>"
                        + "<th>Owner Name</th>"
                        + "<th>Occupier Name</th>"
                        + "<th>Relation With Owner</th>"
                        + "<th>Address</th>"
//                        + "<th>Zone</th>"
                        + "<th>Ward</th>"
                        + "<th>Financial Year</th>"
                        + "<th>TAX No.</th>"
                        + "<th>Amount Demand (INR)</th>"
                        + "<th>Amount Paid (INR)</th>"
                        + "<th>Payment Mode</th>"
                        + "<th>Cheque/DD Number</th>"
                        + "<th>Bank Name</th>"
                        + "<th>Bank Branch</th>"
                        + "<th>Action</th>"
                        + "<th>Action Remarks</th></tr></thead><tbody>";

                for (var t in paymentArr) {

                    var pay = paymentArr[t];
                    
                    var url = location.href.substring(0, location.href.lastIndexOf('/')) + "/generatePrivateNotice?zoneId=-1&ward=-1&propertyId=" + pay.propId;

                    var prop = PaymentApp.propertyMap[pay.propId];
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

                    pay_html += "<tr payment_ref_id='" + pay.payRefId + "' ><td>" + (parseInt(t) + 1) + "</td>";
                    pay_html += "<td>" + pay.payRefId + "</td>";
                    pay_html += "<td>" + prop.propertyUniqueId + "</td>";
                    pay_html += "<td>" + prop.propertyOwner + "</td>";
                    pay_html += "<td>" + prop.propertyOccupierName + "</td>";
                    pay_html += "<td>" + prop.propertyRelationOwner + "</td>";
                    pay_html += "<td>" + prop.completeAddress + "</td>";
//                    pay_html += "<td>" + PaymentApp.zoneMaster[prop.zoneId] + "</td>";
                    pay_html += "<td>" + PaymentApp.wardMaster[prop.ward] + "</td>";
                    //pay_html += "<td>" + pay.financialYear + "</td>";
                    pay_html += "<td> 2019-2020 </td>";
                    pay_html += "<td>" + pay.taxNo + "</td>";
                    pay_html += "<td>" + pay.amountDemand + "</td>";
                    pay_html += "<td>" + pay.amountPaid + "</td>";
                    pay_html += "<td>" + (PaymentApp.paymentModes[pay.paymentMode] === undefined ? "" : PaymentApp.paymentModes[pay.paymentMode]) + "</td>";
                    pay_html += "<td>" + pay.chequeDDNum + "</td>";
                    pay_html += "<td>" + (PaymentApp.bankMaster[pay.bankName] === undefined ? "" : PaymentApp.bankMaster[pay.bankName]) + "</td>";
                    pay_html += "<td>" + pay.bankBranch + "</td>";

                    pay_html += "<td><a href='" + url + "' target='new_page' class='show_notice' >Show notice</a><br>"
                            + "<a href='javascript: void(0)' class='approve_payment' >Approve</a><br>"
                            + "<a href='javascript: void(0)' class='reject_payment'>Reject</a></td>";
                    pay_html += "<td><textarea row='2' cols='50' class='action_remarks' placeholder='Provide approval/rejection remarks here...'></textarea></td>";
                    pay_html += "</tr>";
                }
                pay_html += "</tbody>";
                $("#vn_searchResult_table").html(pay_html);
                PaymentApp.showSearchResultWindow();
                PaymentApp.createDataTable();
            }
        }, 'json').always(function () {

        });

    },
    viewNotice: function (taxNo) {

        var url = location.href.substring(0, location.href.lastIndexOf('/')) + "/viewNotice?taxNo=" + taxNo;
        $("#vn_open_pdf").attr("href", url);
        $("#vn_open_pdf").click();
    },
    dataTable: null,
    tableSearchResult: '#vn_searchResult_table',
    createDataTable: function () {
        PaymentApp.dataTable = $(PaymentApp.tableSearchResult).DataTable({
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
          
          
//        $(PaymentApp.tableSearchResult + "_paginate").css({
//            "position": "absolute", "top": "0", "right": "0"
//        });
//
//        $(PaymentApp.tableSearchResult + "_filter").css({
//            "float": "left"
//        });
       
    },
    destroyDataTable: function () {
        PaymentApp.dataTable.destroy();
        $(PaymentApp.tableSearchResult).html("");
    },
    title: "Pending For Approval",
    params: {},
    type: "PDF",
    exportAs: function (type) {

        PaymentApp.type = type;

        var html_ = '<li class="clearfix">'
                + '<input id="export_All" type="checkbox" value="">'
                + '<label class="cheklabel" for="export_All">All</label>'
                + '</li>';

        for (var a in MASTERCOLUMN.attrPaymentApproveReport) {
            var attr = MASTERCOLUMN.attrPaymentApproveReport[a];
            html_ += '<li class="clearfix">'
                    + '<input id="' + attr + '" type="checkbox" value="">'
                    + '<label class="cheklabel" for="name">' + MASTERCOLUMN.attrHead[attr] + '</label>'
                    + '</li>';

        }
        $("#business_entities_list").html(html_);
        $('#export-modal').modal('toggle');
    },
    download: function () {
        $("#exportTitle").val(PaymentApp.title);
        $("#windowId").val(Math.random());

        PaymentApp.params = new Object();
        PaymentApp.params.masterBeans = [];
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

        var propArr = Object.keys(PaymentApp.propertyMap);
        for (var p in propArr) {
            var propId = propArr[p];
            var prop = PaymentApp.propertyMap[propId];
            var pay = PaymentApp.paymentMap[prop.propertyUniqueId];
            if (pay === undefined) {
                prop.taxGenerated = "N";
            } else {
                prop.taxGenerated = "Y";
                var keys_ = Object.keys(pay);
                for (var k in keys_) {
                    var key = keys_[k];
                    prop[key] = pay[key];
                }
            }

            var obj = {};
            for (var m in masterAttrArr) {
                var attr = masterAttrArr[m];

                if (prop[MASTERCOLUMN.attrInBean[attr]] === undefined) {
                    obj[attr] = "";
                } else {

                    if (attr === "zone") {
                        obj[attr] = PaymentApp.zoneMaster[prop[MASTERCOLUMN.attrInBean[attr]]];
                    } else if (attr === "ward") {
                        obj[attr] = PaymentApp.wardMaster[prop[MASTERCOLUMN.attrInBean[attr]]];
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

            PaymentApp.params.masterBeans.push(obj);

        }

        $("#exportParams").val(JSON.stringify(PaymentApp.params));
        $("#exportTHead").val(JSON.stringify(masterHeadArr));
        $("#exportAttrToAdd").val(JSON.stringify(masterAttrArr));
        $("#exportType").val(PaymentApp.type);
        $("#expo").submit();
        $('#export-modal').modal('toggle');
    },
    wardMaster: {},
    getWard: function () {

//        if ($("#pay_view_zone option:selected").val() === "-1") {
//            PaymentApp.resetWard();
//        } else {
            $.post("getWards", {zone: $("#pay_view_zone option:selected").val()}, function (result) {
                if (result != undefined) {
                    var html = "";
                    html += "<option value='-1'>All Ward</option>";
                    for (var i = 0; i < result.length; i++) {
                        html += "<option value='" + result[i].ward + "'>" + result[i].displayName + "</option>";
                        PaymentApp.wardMaster[result[i].ward] = result[i].displayName;
                    }
                    PaymentApp.resetWard(html);
                }
            }, 'json').always(function () {

            });
//        }
    },
    resetWard: function (html) {
        if (html === undefined) {
            $("#pay_view_ward").html("<option value='-1'>--Select Ward--</option>");
        } else {
            $("#pay_view_ward").html(html);
        }
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
                    PaymentApp.bankMaster[bank.bankId] = bank.bankName;
                }
                $("#enc_banks").html(strHTML);
            },
            error: function () {
            }
        });
    },
    paymentModes: {"CSH": "Cash", "CHQ": "Cheque", "DDF": "Demand Draft", "POS_DEVICE" : "POS", "BHIM_UPI" : "BHIM UPI"},
    fillPaymentModes: function () {


        var strHTML = "<option value='-1'>--Select Payment Mode --</option>";
        strHTML += "<option value='CSH'>Cash</option>";
        strHTML += "<option value='CHQ'>Cheque</option>";
        strHTML += "<option value='DDF'>Demand Draft</option>";
        $("#enc_paymentMode").html(strHTML);

//        $.ajax({
//            type: "POST",
//            url: "paymentModes",
//            contentType: "application/json; charset=utf-8",
//            success: function(data) {
//                var modes = JSON.parse(data);
//                var strHTML = "<option value='-1'>--Select Payment Mode --</option>";
//                for (var m in modes) {
//                    var mode = modes[m];
//                    strHTML += "<option value='" + mode.modeId + "'> " + mode.modeName + " </option>";
//                }
//                $("#enc_paymentMode").html(strHTML);
//            },
//            error: function() {
//            }
//        });
    },
    approvePayment: function (clickedTr_) {
        var paymentRefId = clickedTr_.attr("payment_ref_id");
        var remarks = clickedTr_.find(".action_remarks").val();
        $.ajax({
            type: "POST",
            url: "appovePayment",
            data: "paymentRefId=" + paymentRefId + "&remarks=" + remarks,
            success: function (data) {
                if (data.code == 200) {
                    clickedTr_.remove();
                    alert("Payment Success.");
                } else if (data.code == 5001) {
                    location.href = location.origin + "/" + location.pathname.split("/")[1] + "/"
                } else {
                    alert("Approval failure due to " + data.msg);
                }
            },
            error: function () {
                alert("Something went wrong.");
            }
        });
    },
    rejectPayment: function (clickedTr_) {

        var paymentRefId = clickedTr_.attr("payment_ref_id");
        var remarks = clickedTr_.find(".action_remarks").val();

        $.ajax({
            type: "POST",
            url: "rejectPayment",
            data: "paymentRefId=" + paymentRefId + "&remarks=" + remarks,
            success: function (data) {
                if (data.code == 200) {
                    alert("Payment Rejected.");
                    clickedTr_.remove();
                } else if (data.code == 5001) {
                    location.href = location.origin + "/" + location.pathname.split("/")[1] + "/"
                } else {
                    alert("Approval failure due to " + data.msg);
                }
            },
            error: function () {
                alert("Something went wrong.");
            }
        });
    },
    openCurTab : function(){
        window.open(window.location.href, "_self");
    }
}


$(document).on("click", "#export_btn", function () {
    PaymentApp.download();
});

//$(document).on("change", "#pay_view_zone", function () {
//    PaymentApp.getWard();
//});

$(document).on("click", ".approve_payment", function () {

    var remarks = $(this).closest("tr").find(".action_remarks").val();
    if (remarks === undefined || remarks.trim() === "") {
        alert("Please fill approval remarks.")
    } else {
        PaymentApp.approvePayment($(this).closest("tr"));
    }


});

$(document).on("click", ".reject_payment", function () {
    var remarks = $(this).closest("tr").find(".action_remarks").val();
    if (remarks === undefined || remarks.trim() === "") {
        alert("Please fill rejection remarks.")
    } else {
        PaymentApp.rejectPayment($(this).closest("tr"));
    }
});

$(document).on("change", "#pay_view_ward", function () {  
    $("#pay_view_zone").val(zoneWardMaster[$(this).val()]);
});