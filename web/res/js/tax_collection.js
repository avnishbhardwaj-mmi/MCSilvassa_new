/**
 * 
 */

$(document).ready(function () {
    TAXCOLLECTION.loadZone();
    TAXCOLLECTION.loadRentableValues();
    //TAXCOLLECTION.resetOccupier();
//  TAXCOLLECTION.resetOwner();
    // TAXCOLLECTION.resetPropertyId();
    //   TAXCOLLECTION.resetAadhar();
    // TAXCOLLECTION.resetLocality();
    //   TAXCOLLECTION.resetPropertyCategory();

    //  $("#src_aadhar_no").addClass("disable_cls");
    // $("#src_aadhar_no").prop("disabled", true);
    $("#enc_banks").attr("disabled", true);
    $("#enc_banks_branch").attr("disabled", true);
    $("#enc_banks_ifsc").attr("disabled", true);

    $("#tax_collection_main_menu").addClass("active");
    $("#ownerid").SumoSelect({csvDispCount: 3, search: true, searchText: 'Enter here.'});
    $("#occupierid").SumoSelect({csvDispCount: 3, search: true, searchText: 'Enter here.'});
    $("#propertyid").SumoSelect({csvDispCount: 3, search: true, searchText: 'Enter here.'});
    $("#ward").SumoSelect({csvDispCount: 3, search: true, searchText: 'Enter here.'});
    $("#locality").SumoSelect({csvDispCount: 3, search: true, searchText: 'Enter here.'});
    $("#enc_cheque_dd_date").datepicker({dateFormat: 'yy-mm-dd'});

    TAXCOLLECTION.showSearchWindow();
    TAXCOLLECTION.getWard();


});

var TAXCOLLECTION = {
    tableSearchResult: '#tableSearchResult',
    currentPropertyId: null,
    toDecimalPoints: 3,
    showSearchWindow: function () {
        $('#property_tab').html('');
        $("#searchArea").removeClass("hidden");
        $("#searchResults").addClass("hidden");
        $("#taxCollection").addClass("hidden");
        $("#taxPaymentView").addClass("hidden");
        $("#submitMesage").addClass("hidden");
        $("#ward").val('')
        $("#prop_id_input").val('')
        $("#ownerid").val('')
        $("#occ_name").val('')
        $("#Phone_No").val('')
        $("#Easy_City_Code").val('')
        $("#locality").val('')
        $("#Locality").val('')
        $("#src_aadhar_no").val('')
        $("#category").val('')
    },
    showResultWindow: function () {
        $("#searchArea").addClass("hidden");
        $("#searchResults").removeClass("hidden");
        $("#taxCollection").addClass("hidden");
        $("#taxPaymentView").addClass("hidden");
        $("#submitMesage").addClass("hidden");
        var total_row = $('#tableSearchResult  tr').length;
        if (total_row == 2) {
            $('#property_tab').html('');
            TAXCOLLECTION.showSearchWindow();
        }


    },
    showCollectionWindow: function () {
        $("#searchArea").addClass("hidden");
        $("#searchResults").addClass("hidden");
        $("#taxCollection").removeClass("hidden");
        $("#taxPaymentView").addClass("hidden");
        $("#submitMesage").addClass("hidden");
    },
    showSummaryWindow: function () {
        $("#taxHistoryView").addClass("hidden");
        $("#taxCollection").addClass("hidden");
        $("#taxDetailView").removeClass("hidden");
        $("#taxPaymentView").addClass("hidden");
        $("#submitMesage").addClass("hidden");
    },
    showHistoryWindow: function () {
        $("#taxHistoryView").removeClass("hidden");
        $("#taxCollection").addClass("hidden");
        $("#taxDetailView").addClass("hidden");
        $("#taxPaymentView").addClass("hidden");
        $("#submitMesage").addClass("hidden");
    },
    showPaymentWindow: function () {
        $("#taxPaymentView").removeClass("hidden");
        $("#taxHistoryView").addClass("hidden");
        $("#taxCollection").addClass("hidden");
        $("#taxDetailView").addClass("hidden");
        $("#submitMesage").addClass("hidden");
    },
    showReceiptWindow: function () {
        $("#submitMesage").removeClass("hidden");
        $("#taxPaymentView").addClass("hidden");
        $("#taxHistoryView").addClass("hidden");
        $("#taxCollection").addClass("hidden");
        $("#taxDetailView").addClass("hidden");
    },
    zoneMaster: {
        "-1": "Not found"
    },
    propTypeMaster: {
        "-1": "Not found"
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
                    TAXCOLLECTION.bankMaster[bank.bankId] = bank.bankName;
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
    loadZone: function () {

        $.ajax({
            url: "loadZones",
            type: 'post',
            data: '',
            dataType: 'json',
            success: function (result) {
                if (result != undefined) {
                    var html = "";
                    html += "<option value='-1' selected>--Select Zone--</option>";
                    for (var i = 0; i < result.length; i++) {
                        html += "<option value='" + result[i].zoneId + "'>" + result[i].zoneName + "</option>";
                        TAXCOLLECTION.zoneMaster[result[i].zoneId] = result[i].zoneName;
                    }
                    $("#zone").html(html);
                    $("#zone").SumoSelect({csvDispCount: 3, search: true, searchText: 'Enter here.'});
                }
            },
            error: function (e) {
                console.log("ERROR: ", e);
            }
        });

    },
    rentableValuesMap: {},
    loadRentableValues: function () {
        $.ajax({
            url: "getRentableValues",
            type: 'post',
            data: '',
            dataType: 'json',
            success: function (result) {
                if (result != null) {
                    for (var i = 0; i < result.length; i++) {
                        TAXCOLLECTION.rentableValuesMap[result[i].propertyRentableId] = result[i];
                    }
                }
            },
            error: function (e) {
                console.log("ERROR: ", e);
            }
        });
    },
    ownerNameArr: [],
    occupierNameArr: [],
    propIdArr: [],
    propCatArr: [],
    fillSearchCriteria: function () {

        var zone_id = $("#zone").val();
        var ward = $("#ward").val();
        var locality = $("#locality").val();

        if (zone_id === "-1") {

        } else if (ward === "-1") {

        } else {

            TAXCOLLECTION.ownerNameArr = [];
            TAXCOLLECTION.occupierNameArr = [];
            TAXCOLLECTION.propIdArr = [];
            TAXCOLLECTION.propCatArr = [];

            LOADER.show();
            $.ajax({
                url: "getSearchCriteria",
                type: 'post',
                data: "zone_id=" + $("#zone").val() + "&ward=" + ward + "&locality=" + locality,
                dataType: 'json',
                success: function (result) {

                    if (result !== undefined && result !== null) {

                        var html_prop = "<option value='-1' selected >--Select Property ID--</option>";
                        var html_own = "<option value='-1' selected >--Select Owner Name --</option>";
                        var html_occ = "<option value='-1' selected >--Select Occupier Name --</option>";
                        var html_prop_category = "<option value='-1' selected >--Select Property Type Name --</option>";

                        result.prop.occupierArr = result.prop.occupierArr === undefined ? [] : result.prop.occupierArr;
                        result.prop.ownerArr = result.prop.ownerArr === undefined ? [] : result.prop.ownerArr;
                        result.prop.propIdArr = result.prop.propIdArr === undefined ? [] : result.prop.propIdArr;
                        result.prop.propertyCategoryArr = result.prop.propertyCategoryArr === undefined ? [] : result.prop.propertyCategoryArr;

                        TAXCOLLECTION.ownerNameArr = result.prop.ownerArr;
                        TAXCOLLECTION.occupierNameArr = result.prop.occupierArr;
                        TAXCOLLECTION.propIdArr = result.prop.propIdArr;
                        TAXCOLLECTION.propCatArr = result.prop.propertyCategoryArr;

                        for (var it in TAXCOLLECTION.ownerNameArr) {
                            var item = TAXCOLLECTION.ownerNameArr[it].property_owner;
                            html_own += "<option value='" + item + "'>" + item + "</option>";
                        }

                        for (var it in TAXCOLLECTION.occupierNameArr) {
                            var item = TAXCOLLECTION.occupierNameArr[it].property_occupier_name;
                            html_occ += "<option value='" + item + "'>" + item + "</option>";
                        }

                        for (var it in TAXCOLLECTION.propIdArr) {
                            var item = TAXCOLLECTION.propIdArr[it].property_unique_id;
                            html_prop += "<option value='" + item + "'>" + item + "</option>";
                        }

                        for (var it in TAXCOLLECTION.propCatArr) {
                            var item = TAXCOLLECTION.propCatArr[it].property_category_name;
                            html_prop_category += "<option value='" + item + "'>" + item + "</option>";
                        }

                        for (var it in TAXCOLLECTION.propCatArr) {
                            var item = TAXCOLLECTION.propCatArr[it].property_category_name;
                            html_prop_category += "<option value='" + item + "'>" + item + "</option>";
                        }

                        TAXCOLLECTION.resetOccupier(html_occ);
                        TAXCOLLECTION.resetOwner(html_own);
                        TAXCOLLECTION.resetPropertyId(html_prop);
                        TAXCOLLECTION.resetAadhar();
                        TAXCOLLECTION.resetPropertyCategory(html_prop_category);
                        LOADER.hide();
                    }

                },
                error: function (e) {
                    console.log("ERROR: ", e);
                }
            });

        }
    },
    /*
     * loadLocality : function(){ $.ajax({ url: "getLocality", type: 'post',
     * data: '', dataType: 'json', success: function(result) { if (result !==
     * null && result != undefined) { for (var i = 0; i < result.length; i++) {
     * TAXCOLLECTION.localityMaster[result[i].localityId] =
     * result[i].localityName; } } }, error: function(e) { console.log("ERROR: ",
     * e); } }); },
     */
    /*
     * loadWard : function(){ $.ajax({ url: "getWard", type: 'post', data: '',
     * dataType: 'json', success: function(result) { if (result !== null &&
     * result != undefined) { for (var i = 0; i < result.length; i++) {
     * TAXCOLLECTION.wardMaster[result[i].wardId] = result[i].wardName; } } },
     * error: function(e) { console.log("ERROR: ", e); } }); },
     */
    /*
     * loadPropType : function(){ $.ajax({ url: "getPropType", type: 'post',
     * data: '', dataType: 'json', success: function(result) {
     * 
     * if (result !== null && result != undefined) { for (var i = 0; i <
     * result.length; i++) {
     * TAXCOLLECTION.propTypeMaster[result[i].propertyTypeId] =
     * result[i].propertyTypeName; } } }, error: function(e) {
     * console.log("ERROR: ", e); } }); },
     */
    populatePropertyDetail: function (propertyId) {

        TAXCOLLECTION.clearPropertyDetail();

        var prop = TAXCOLLECTION.searchResult[propertyId];
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


        TAXCOLLECTION.getTAXDetails(prop.propertyUniqueId);


        $("#prop_id").val(prop.propertyUniqueId);
        $("#prop_owner").val(prop.propertyOwner);
        $("#prop_occupier").val(prop.propertyOccupierName);
        $("#prop_house_no").val(prop.propertyHouseNo);
        $("#prop_building").val(prop.propertyBuildingName);
        $("#prop_address").val((prop.completeAddress === undefined ? "" : prop.completeAddress));
        $("#prop_pincode").val(prop.propertyPincode);
        $("#prop_zone").val((TAXCOLLECTION.zoneMaster[prop.zoneId] === undefined ? "" : TAXCOLLECTION.zoneMaster[prop.zoneId]));
        $("#prop_sublocality").val(prop.propertySublocality);
        $("#prop_landmark").val(prop.propertyLandmark);
        $("#prop_relation_owner").val(prop.propertyRelationOwner);
        TAXCOLLECTION.enableNotice(propertyId);

    },
    floorMap: {},
    preserveFloorDetails: function (detailArr) {
        if (detailArr === undefined || detailArr === null) {
            TAXCOLLECTION.floorMap = {};
        } else {
            for (var dt in detailArr) {
                var det = detailArr[dt];
                if (det != null) {
                    TAXCOLLECTION.floorMap[det.pfId] = det;
                }
            }
        }

    },
    getTAXDetails: function (propId) {

        $.ajax({
            url: "getTAXDetails",
            type: 'post',
            data: 'propertyId=' + propId,
            dataType: 'json',
            success: function (data) {

                var summaryHTML = "";
                var taxDetail = data.taxDetails;

                var floorDetails = data.floorDetails;
                TAXCOLLECTION.preserveFloorDetails(floorDetails);
                if (taxDetail != null) {

                    taxDetail.taxNo = (taxDetail.taxNo === undefined || taxDetail.taxNo === null) ? "" : taxDetail.taxNo;
                    taxDetail.grandTotal = (taxDetail.grandTotal === undefined || taxDetail.grandTotal === null) ? "" : taxDetail.grandTotal;
                    taxDetail.generatedOn = (taxDetail.generatedOn === undefined || taxDetail.generatedOn === null) ? "" : taxDetail.generatedOn;
                    taxDetail.dueDate = (taxDetail.dueDate === undefined || taxDetail.dueDate === null) ? "" : taxDetail.dueDate;
                    taxDetail.arrearAmount = (taxDetail.arrearAmount === undefined || taxDetail.arrearAmount === null) ? "" : taxDetail.arrearAmount;
                    taxDetail.rebateAmount = (taxDetail.rebateAmount === undefined || taxDetail.rebateAmount === null) ? "" : taxDetail.rebateAmount;
                    taxDetail.floorWiseTAXDetails = (taxDetail.floorWiseTAXDetails === undefined || taxDetail.floorWiseTAXDetails === null) ? [] : taxDetail.floorWiseTAXDetails;

                    var result = taxDetail.floorWiseTAXDetails;
                    for (var it in result) {
                        var item = result[it];

                        var floor = TAXCOLLECTION.floorMap[item.floorId];
                        var rentValue = TAXCOLLECTION.rentableValuesMap[floor.propertyRentableId];
                        if (rentValue === undefined) {
                            rentValue.rentableValue = "N/A";
                        }
                        floor.pfFloorName = (floor.pfFloorName === undefined || floor.pfFloorName === null) ? "" : floor.pfFloorName;
                        floor.pfBuiltupArea = (floor.pfBuiltupArea === undefined || floor.pfFloorName === null) ? "" : floor.pfBuiltupArea;
                        rentValue.propertyCat = (rentValue.propertyCat === undefined || floor.pfFloorName === null) ? "" : rentValue.propertyCat;
                        rentValue.propertySubcatCode = (rentValue.propertySubcatCode === undefined || floor.pfFloorName === null) ? "" : rentValue.propertySubcatCode;
                        rentValue.rentableValue = (rentValue.rentableValue === undefined || floor.pfFloorName === null) ? "" : rentValue.rentableValue;

                        summaryHTML += "<tr><td> "
                                + floor.pfFloorName
                                + " </td><td>"
                                + floor.pfBuiltupArea
                                + "</td><td>"
                                + (rentValue.propertyCat + "(" + rentValue.propertySubcatCode + ")")
                                + "</td><td>"
                                + rentValue.rentableValue
                                + "</td><td>"
                                + TAXCOLLECTION.formatAmount(item.floorTaxAmount)
                                + "</td></tr>";

                    }


                    summaryHTML += "<tr><td></td><td></td><td></td><td>Old Tax (April-September): </td><td>" + TAXCOLLECTION.formatAmount(taxDetail.oldtaxAmount) + "</td></tr>";
                    summaryHTML += "<tr><td></td><td></td><td></td><td>Sum of amount</td><td>" + TAXCOLLECTION.formatAmount(taxDetail.totalPropertyTax) + "</td></tr>";

                    $("#summaryTAX tbody").html(summaryHTML);

                    $("#prop_bill_no").val(taxDetail.taxNo);
                    $("#prop_total_amount").val(taxDetail.grandTotal);
                    $("#prop_bill_date").val(taxDetail.generatedOn);
                    $("#prop_due_date").val(taxDetail.dueDate);

                    $("#label_propId").text(propId);
                    $("#label_taxId").text(taxDetail.taxNo);
                    $("#label_bill_date").text(taxDetail.generatedOn);
                    $("#label_bill_due_date").text(taxDetail.dueDate);

                    $("#label_arrear").text(taxDetail.arrearAmount);
                    $("#label_water_tax").text(taxDetail.waterTax);
                    $("#label_conservancy_tax").text(taxDetail.conservancyTax);
                    $("#label_water_sewerage_tax").text(taxDetail.waterSewerageCharge);
                    $("#label_water_meter_bill").text(taxDetail.waterMeterBillAmount);
                    $("#label_advance_paid").text(taxDetail.advancePaidAmount);
                    $("#label_rebate").text(taxDetail.rebateAmount);

                    $("#label_adjustment").text(taxDetail.adjustmentAmount);
                    $("#label_total_prop_tax").text(taxDetail.totalPropertyTax);
                    $("#label_service_tax").text(taxDetail.serviceTax);
                    $("#label_other_tax").text(taxDetail.otherTax);
                    $("#label_grand_total").text(taxDetail.grandTotal);
                    $("#label_delay_charges").text(taxDetail.delayPaymentCharges);
                    $("#label_net_amount").text(taxDetail.payableAmount);
                    $("#label_old_tax").text(taxDetail.oldtaxAmount);



                    $("#sl_arrear_amount").val(taxDetail.arrearAmount);
                    $("#sl_interest_amount").val(taxDetail.delayPaymentCharges);
                    $("#sl_tax_amount").val(taxDetail.propertyTax);
                    $("#sl_payment_amount").val(taxDetail.payableAmount);
                    $("#sl_payable_amount").text(taxDetail.payableAmount);
                    $("#sl_payment_taxNo").text(taxDetail.taxNo);
                    $("#sl_payment_propId").text(propId);

                }

            },
            error: function (e) {
                console.log("ERROR: ", e);
            }
        });
    },
    clearPropertyDetail: function () {
        $("#prop_id").val("");
        $("#prop_owner").val("");
        $("#prop_occupier").val("");
        $("#prop_house_no").val("");
        $("#prop_building").val("");
        $("#prop_address").val("");
        $("#prop_pincode").val("");
        $("#prop_zone").val("");
        $("#prop_sublocality").val("");
        $("#prop_landmark").val("");
        $("#prop_relation_owner").val("");
    },
    populateTAXSummary: function (propertyId) {

        /*
         * $("#taxHistoryView").addClass("hidden");
         * $("#taxCollection").addClass("hidden");
         * 
         * $.ajax({ url: "getTAXCaLculation", type: 'post', data: 'propertyId=' +
         * propertyId, dataType: 'json', success: function(result) { var
         * summaryHTML = ""; var totalAmount = 0; if (result !== null && result !=
         * undefined) { for(var it in result){ var item = result[it];
         * summaryHTML += "<tr><td> " + item.floor + " </td><td>" +
         * TAXCOLLECTION.formatAmount(item.builtArea) + "</td><td>" +
         * item.propertySubCat + "</td><td>" + item.propertyCat + "</td><td>" +
         * TAXCOLLECTION.formatAmount(item.mFactor) + "</td><td>" +
         * TAXCOLLECTION.formatAmount(item.rentableValue) + "</td><td>" +
         * TAXCOLLECTION.formatAmount(item.taxAmount) + "</td></tr>";
         * 
         * totalAmount = parseFloat(totalAmount) +
         * parseFloat(TAXCOLLECTION.formatAmount(item.taxAmount)); } summaryHTML += "<tr><td></td><td></td><td></td><td></td><td></td><td>Sum
         * of amount</td><td>" + TAXCOLLECTION.formatAmount(totalAmount) + "</td></tr>";
         * $("#summaryTAX tbody").html(summaryHTML); }
         * $("#taxDetailView").removeClass("hidden"); }, error: function(e) {
         * console.log("ERROR: ", e); } });
         */
    },
    formatAmount: function (num__) {
        if (isNaN(num__) || isNaN(parseFloat(num__))) {
            return 0;
        } else {
            return parseFloat(num__).toFixed(TAXCOLLECTION.toDecimalPoints);
        }
    },
    searchResult: {},
    taxDetailMap: {},
    preserveTAXDetails: function (detailArr) {
        TAXCOLLECTION.taxDetailMap = {};
        if (detailArr === undefined || detailArr === null) {

        } else {
            for (var dt in detailArr) {
                var det = detailArr[dt];
                if (det != null) {
                    TAXCOLLECTION.taxDetailMap[det.propertyId] = det;
                }
            }
        }

    },
    searchPropertyForTAX: function () {


//        TAXCOLLECTION.destroyDataTable();


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
        //newly added
        var Phone_no = $("#Phone_No").val();
        var Locality = $("#Locality").val();
        $("#validateid").html('');
        if (prop_id_input == "" && ward == "" && occ == "" && occ == "" && owner_id == "" && locality == "" && aadhar_num == "" && propertyCategory == "" && Phone_no == "" && Locality == "") {
            //alert("Please select atleast one filter");
            $("#validateid").html("Please select at least one filter");
        } else {

            /*     if (ward === "-1" && (prop_id_input == null || prop_id_input == "" || prop_id_input.trim() == "")) {// zone not selected,property id is entered
             alert("Kindly provide ward or Property Id.");
             } else {
             
             if (prop_id_input != null && prop_id_input != "" && prop_id_input.trim() != "") {
             
             } else {
             if ($("#propertyid").val() != "-1") {
             property_id = $("#propertyid option:selected").text();
             }
             if ($("#ownerid").val() != "-1") {
             owner_id = $("#ownerid option:selected").text();
             }
             if ($("#occupierid").val() != "-1") {
             occ = $("#occupierid option:selected").text();
             }
             if ($("#locality").val() != "-1") {
             locality = $("#locality option:selected").text();
             }
             if ($("#src_aadhar_no").val() != "") {
             aadhar_num = $("#src_aadhar_no").val();
             }
             if ($("#category").val() != "") {
             propertyCategory = $("#category option:selected").val();
             }
             }*/



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

            LOADER.show();
            $.ajax({
                url: "searchPropertyForTAX",
                type: 'post',
                data: "params=" + JSON.stringify(obj),
                success: function (data) {
                    LOADER.hide();
                    if (data.propBeans != null) {
                        var result = data.propBeans;
                        //console.log(" tax result "+result);
                        var taxBeans = data.taxBeans;
                        //console.log(" tax taxBeans "+taxBeans);

                        TAXCOLLECTION.preserveTAXDetails(taxBeans);
                        TAXCOLLECTION.preserveSearchResult(result);

                        if (result !== undefined && taxBeans !== undefined && taxBeans.length > 0) {

                            var tab_html = "<thead>" + "<tr>"
                                    + "<th>S.No.</th>" + "<th>Property ID</th>"
                                    + "<th>Owner Name</th>"
                                    + "<th>Occupier Name</th>"
                                    //+ "<th>Relation With Owner</th>"
                                    + "<th>Address</th>"
//                                    + "<th>Zone</th>"
                                    + "<th>Ward</th>"
//                            + "<th>Category</th>" + "<th>Zone</th>"
//                            + "<th>Total Plot Area</th>"
//                            + "<th>House No.</th>"
                                    + "<th>Tax Generated</th>"
                                    + "<th>Tax Amount (INR)</th>"
                                    + "<th>Notice Generated</th>"
                                    + "</tr></thead><tbody>";

                            for (var r in result) {

                                var prop = result[r];
                                tab_html += "<tr>"
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


                                tab_html += "<td>" + (parseInt(r) + 1) + "</td>";
                                tab_html += "<td><a href='#' propId = '" + prop.propertyUniqueId + "' class='openPropertyDetails' >" + prop.propertyUniqueId + "</a></td>";
                                tab_html += "<td>" + prop.propertyOwner + "</td>";
                                tab_html += "<td>" + prop.propertyOccupierName + "</td>";
                                //tab_html += "<td>" + prop.propertyRelationOwner + "</td>";

                                tab_html += "<td>" + (prop.completeAddress === undefined ? "" : prop.completeAddress) + "</td>";
                               // tab_html += "<td>" + (TAXCOLLECTION.zoneMaster[prop.zoneId] === undefined ? "" : TAXCOLLECTION.zoneMaster[prop.zoneId]) + "</td>";
                                tab_html += "<td>" + (TAXCOLLECTION.wardMaster[prop.ward] === undefined ? "" : TAXCOLLECTION.wardMaster[prop.ward]) + "</td>";

                                if (TAXCOLLECTION.taxDetailMap[prop.propertyUniqueId] === undefined) {
                                    tab_html += "<td>No</td>";
                                    tab_html += "<td>No</td>";
                                    tab_html += "<td>No</td>";
                                } else {
                                    tab_html += "<td>Yes</td>";
                                    tab_html += "<td>" + (TAXCOLLECTION.taxDetailMap[prop.propertyUniqueId].grandTotal == undefined ? '' : TAXCOLLECTION.taxDetailMap[prop.propertyUniqueId].grandTotal) + " </td>";
                                    if (TAXCOLLECTION.taxDetailMap[prop.propertyUniqueId].noticeGenerated === "Y") {
                                        tab_html += "<td>Yes</td>";
                                    } else {
                                        tab_html += "<td>No</td>";
                                    }

                                }
                                tab_html += "</tr>";

                            }

                            tab_html += "</tbody>";

                            $(TAXCOLLECTION.tableSearchResult).html(tab_html);
                            TAXCOLLECTION.showResultWindow();
                            TAXCOLLECTION.createDataTable();

                            if (result.length == 1)
                            {
                                TAXCOLLECTION.singleproperty(prop.propertyUniqueId);
                            }


                        }
                    } else {
                        $("#validateid").html("TAX not generated/TAX details not found, for your selection.");
                        //alert("TAX not generated/TAX details not found, for your selection.");
                    }

                },
                error: function (e) {
                    LOADER.hide();
                    console.log("ERROR: ", e);
                }
            });
        }
    },
    preserveSearchResult: function (result) {
        TAXCOLLECTION.searchResult = {};
        if (result !== undefined) {
            for (var r in result) {
                var prop = result[r];
                TAXCOLLECTION.searchResult[prop.propertyUniqueId] = prop;
            }
        }
    },
    dataTable: null,
    createDataTable: function () {
        TAXCOLLECTION.dataTable = $(TAXCOLLECTION.tableSearchResult).DataTable(
                {
                    "dom": 'Bfrtip',
                    "buttons": [],
                    "lengthMenu": [[12, 25, 50, -1], [10, 25, 50, "All"]],
                    "bDestroy": true,
                    "responsive": true,
                    "paging": true,
                    // "scrollY":200,
                    /* "scrollX":true, */
                    "sPaginationType": "full_numbers",
                    'columnDefs': [
                        {'max-width': '20%', 'targets': 4}
                    ],
                    //"scrollY": "350px",
//                    "scrollCollapse": true,
//                    "info": true,
//                    "paging": true,
//                    "bDestroy": true,
//                    "sScrollX": "100%",
//                    "sScrollXInner": "100%",
//                    "bScrollCollapse": true
                });
        $('.dataTables_wrapper table').wrap('<div class="scroll_table"></div>');
//        $(TAXCOLLECTION.tableSearchResult + "_paginate").css({
//            "position": "absolute", "top": "0", "right": "0"
//        });
//
//        $(TAXCOLLECTION.tableSearchResult + "_filter").css({
//            "float": "left"
//        });
    },
    destroyDataTable: function () {
        TAXCOLLECTION.dataTable.destroy();
        $(TAXCOLLECTION.tableSearchResult).html("");
    },
    printCollectionReceipt: function () {
        //alert("dddd");
        $("#validateid").html('');
        var payBean = TAXCOLLECTION.payBean;
        if (payBean !== undefined) {
            //alert("payBean.propId "+payBean.propId);
            //alert(payBean.payRefId);
            var prop = TAXCOLLECTION.searchResult[payBean.propId];
            var print_html = "";
            var amtInWords = convertNumberToWords(payBean.amountPaid);
            var remainedTax = parseFloat(payBean.amountDemand).toFixed(2) - parseFloat(payBean.amountPaid).toFixed(2);
            var bankName = TAXCOLLECTION.bankMaster[payBean.bankName];
            $("#as_uniqueId1").val(payBean.propId);
            //alert($("#as_zone1").val());
            //alert($("#as_ward1").val());
            //console.log(payBean.propId);
            $("#as_uniqueId2hiddeen").val(payBean.propId);
            $("#as_receiptId").val(payBean.payRefId);
            $("#taxcollectionForm").submit();

            // var frm= $('#taxcollectionForm');
            // frm.param1="dhngfv";


//            print_html += " <!doctype html><html><head><meta charset='utf-8'><title>Payment Print Receipt (" + payBean.propertyId + ")</title></head><style>";
//            print_html += " html, body, div, span, applet, object, iframe, h1, h2, h3, h4, h5, h6, p, blockquote, pre, a, abbr, acronym, address, big, cite, code, del, dfn, em, img, ins, kbd, q, s, samp, small, strike, strong, sub, sup, tt, var, b, u, i, center, dl, dt, dd, ol, ul, li, fieldset, form, label, legend, table, caption, tbody, tfoot, thead, tr, th, td, article, aside, canvas, details, embed, figure, figcaption, footer, header, hgroup, menu, nav, output, ruby, section, summary, time, mark, audio, video { margin: 0; padding: 0; border: 0; font-size: 100%; font: inherit; vertical-align: baseline; }";
//            print_html += " article, aside, details, figcaption, figure, footer, header, hgroup, menu, nav, section { display: block; }";
//            print_html += " body { line-height: 1; font-size: 14px; line-height: normal; background: #fff; color: #000; font-family: 'Open Sans', sans-serif; }";
//            print_html += " ol, ul { list-style: none; }";
//            print_html += " blockquote, q { quotes: none; }";
//            print_html += " blockquote:before, blockquote:after, q:before, q:after { content: ''; content: none; }";
//            print_html += " table { border-collapse: collapse; border-spacing: 0; } body { margin-top: 30px; } h2 { font-size: 16px; font-weight: bold; margin: 10px; }";
//            print_html += " p { font-size: 13px; } table, th, td { padding: 0; margin: 0; border: 1px solid black; } td { padding: 7px; }";
//            print_html += " </style> <body> <div style='max-width: 700px; width: 100%; margin: 0 auto;'> <div style='text-align: center; position: relative;'>";
//            print_html += " <h2>Silvassa Municipal Council</h2>";
//            print_html += " <p>Shahid Chowk, Near Town Hall, U.T. of Dadra & Nagar Haveli, Silvassa - 396230 <br>";
//            print_html += " Phone No.:(0260)-2633192, Fax No.:(0260)2633191: Email-Id: smc2006@ymail.com : <br>";
//            print_html += " Help Line Number: 0260-2633192 : Timing 09:30 AM to 06:00 PM</p>";
//            print_html += " <h2 style='text-decoration: underline'>Acknowledgement</h2>";
//            print_html += " <p>Receipt of Property Tax payment</p>";
//            print_html += " <img src='res/img/logo.png' alt='' style='position: absolute; top: 0; right: 0;'> </div>";
//            print_html += " <table style='width: 100%; margin-top: 20px;'> <tbody>";
//            print_html += " <tr> <td><span style='font-weight:bold'>Payment Id : </span>" + (payBean.payRefId === undefined ? "" : payBean.payRefId) + "</td>";
//            print_html += " <td> <span style='font-weight:bold'>Payment Date:</span> " + (payBean.entryDateTime === undefined ? "" : payBean.entryDateTime) + " </td></tr>";
//            print_html += " <tr> <td><span style='font-weight:bold'>Property Id : </span>" + payBean.propId + "</td>";
//            print_html += " <td> <span style='font-weight:bold'>Financial Year:</span><span> " + (payBean.financialYear === undefined ? "" : payBean.financialYear) + "</span> </td> </tr>";
//            print_html += " <tr> <td><span style='font-weight:bold'>Payment for (Property Tax\\Penalty\\Arrear\\Any Other mention) : </span></td>";
//            print_html += " <td colspan='2'> <span style='font-weight:bold'>Demand as on date (Amount of tax etc) :</span>Rs. " + (payBean.amountDemand === undefined ? "" : payBean.amountDemand) + "</span></td> </tr>";
//            print_html += " <tr>  <td colspan='2'><span style='font-weight:bold'>Owner Name : </span>" + (prop.propertyOwner === undefined ? "" : prop.propertyOwner) + "</td> </tr>";
//            print_html += " <tr><td colspan='2'> <span style='font-weight:bold'>Father \\ spouse name: </span>" + (prop.propertyOwnerFather === undefined ? "" : prop.propertyOwnerFather) + "</td> </tr>";
//            print_html += " <tr> <td colspan='2'><span style='font-weight:bold'>Occupier Name: </span>" + (prop.propertyOccupierName === undefined ? "" : prop.propertyOccupierName) + "</td></tr> ";
//            print_html += " <tr><td colspan='2'> <span style='font-weight:bold'>Relation with Occupier: </span>" + (prop.propertyRelationOwner === undefined ? "" : prop.propertyRelationOwner) + "</td> </tr>";
//            print_html += " <tr> <td colspan='2'><span style='font-weight:bold'>Address of the Property:</span> " + (prop.completeAddress === undefined ? "" : prop.completeAddress) + "</td></tr> ";
//            print_html += " <tr><td colspan='2'><span style='font-weight:bold'> Amount Received:</span> Rs. " + (payBean.amountPaid === undefined ? "" : payBean.amountPaid) + "</td> </tr>";
//            print_html += " <tr> <td colspan='2'><span style='font-weight:bold'>In Word: </span>Rupees " + (amtInWords === "" ? "Zero" : amtInWords) + " only</td></tr> ";
//
//            print_html += " <tr><td><span style='font-weight:bold'>Mode of Payment:</span>" + (payBean.paymentMode === undefined ? "" : payBean.paymentMode) + "</td>";
//            print_html += " <td><span style='font-weight:bold'></span></td></tr>";
//            print_html += " <tr><td><span style='font-weight:bold'>E Transaction Id (if E-Payment):</span>" + (payBean.bankRefId === undefined ? "" : payBean.bankRefId) + "</td>";
//            print_html += " <td><span style='font-weight:bold'>Cheque \\ DD No:</span> " + (payBean.chequeDDNum === undefined ? "" : payBean.chequeDDNum) + "</td></tr>";
//            print_html += " <tr><td><span style='font-weight:bold'>Bank:</span>" + (bankName === undefined ? "" : bankName) + "</td>";
//            print_html += " <td><span style='font-weight:bold'>Branch:</span>" + (payBean.bankBranch === undefined ? "" : payBean.bankBranch) + "</td></tr>";
//
//            print_html += " </tbody></table><br> <div class=''> <p>Thanks </p> <br><p>Silvassa Municipal Corporation, Silvassa <br>(Authorized Signature of Authority with stamp, date)</p>";
//            print_html += " </div></div></body></html>";

            //var mywindow = window.open('', 'PRINT', 'height=400,width=600');
            //alert(mywindow.chrome);
            //var is_chrome = Boolean(mywindow.chrome);
            // mywindow.document.write(data);
            //mywindow.document.write(print_html);
//            if (is_chrome) {
//                setTimeout(function () { // wait until all resources loaded 
//                    mywindow.document.close(); // necessary for IE >= 10
//                    mywindow.focus(); // necessary for IE >= 10
//                    mywindow.print(); // change window to winPrint
//                    mywindow.close(); // change window to winPrint
//                }, 250);
//            } else {
//                mywindow.document.close(); // necessary for IE >= 10
//                mywindow.focus(); // necessary for IE >= 10
//                mywindow.print();
//                mywindow.close();
//            }
            return true;
        } else {
            $("#validateid").html("Unable to process request.");
            //alert("Unable to process request.");
            return false;
        }
    },
    payBean: {},
    validatePayment: function () {

        //$("#sl_payment_amount").val(parseFloat($("#sl_arrear_amount").val())+parseFloat($("#sl_interest_amount").val())+parseFloat($("#sl_tax_amount").val()));
        $("#validateid").html('');
        if (VALIDATOR.checkIfAlphabet($("#sl_payment_amount").val()) || $("#sl_payment_amount").val() === "") {
            $("#validateid").html("Please enter valid amount.");
            // alert("Please enter valid amount.");
        } else if (isNaN($("#sl_payment_amount").val())) {
            $("#validateid").html("Please enter valid amount.");
            // alert("Please enter valid amount.");
        } else {

            if ($("#enc_paymentMode option:selected").val() === "-1") {
                $("#validateid").html("Please select payment mode.");
                //alert("Please select payment mode.");
            } else if ($("#enc_paymentMode option:selected").val() === "CSH" && parseFloat($("#sl_payment_amount").val()) > 500) {
                $("#validateid").html("Max limit of cash payment is 500 INR.");
                //alert("Max limit of cash payment is 500 INR.");
            } else if ($("#enc_paymentMode option:selected").val() !== "CSH" && $("#enc_cheque_dd_num").val() === "") {
                $("#validateid").html("Please provide Cheque/DD number/POS_ref_no/BHIM UPI.");
                //alert("Please provide Cheque/DD number/POS_ref_no/BHIM UPI.");
            } else if (($("#enc_paymentMode option:selected").val() !== "CSH" && $("#enc_paymentMode option:selected").val() !== "POS_DEVICE" && $("#enc_paymentMode option:selected").val() !== "NEFT_RTGS" && $("#enc_paymentMode option:selected").val() !== "BHIM_UPI") && $("#enc_cheque_dd_date").val() === "") {
                $("#validateid").html("Please fill Cheque/DD date.");
                //alert("Please fill Cheque/DD date.");
            } else if ($("#enc_banks option:selected").val() === "-1" && ($("#enc_paymentMode option:selected").val() !== "CSH" && $("#enc_paymentMode option:selected").val() !== "POS_DEVICE" && $("#enc_paymentMode option:selected").val() !== "NEFT_RTGS" && $("#enc_paymentMode option:selected").val() !== "BHIM_UPI")) {
                $("#validateid").html("Please select bank.");
                //alert("Please select bank.");
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
                TAXCOLLECTION.makePayment();
            }
        }

    },
    makePayment: function () {

        var payBean = {};
        payBean.propId = TAXCOLLECTION.currentPropertyId;
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
                TAXCOLLECTION.payBean = d.data;
                $("#pay_message").html("Payment of Rs." + TAXCOLLECTION.payBean.amountPaid + " has been recieved for property " + TAXCOLLECTION.payBean.propId + ", TAX No. " + TAXCOLLECTION.payBean.taxNo + ".");
                TAXCOLLECTION.showReceiptWindow();
            } else {
                $("#validateid").html("Payment not recieved by system.");
                //alert("Payment not recieved by system.");
            }

        }, '').always(function () {
        });
    },
    title: "TAX COLLECTION",
    params: {},
    type: "PDF",
    exportAs: function (type) {

        TAXCOLLECTION.type = type;

        var html_ = '<li class="clearfix">'
                + '<input id="export_All" type="checkbox" value="">'
                + '<label class="cheklabel" for="export_All">All</label>'
                + '</li>';

        for (var a in MASTERCOLUMN.attrTAXCOLLECT) {
            var attr = MASTERCOLUMN.attrTAXCOLLECT[a];
            html_ += '<li class="clearfix">'
                    + '<input id="' + attr + '" type="checkbox" value="">'
                    + '<label class="cheklabel" for="name">' + MASTERCOLUMN.attrHead[attr] + '</label>'
                    + '</li>';

        }
        $("#business_entities_list").html(html_);
        $('#export-modal').modal('toggle');
    },
    download: function () {
        $("#exportTitle").val(TAXCOLLECTION.title);
        $("#windowId").val(Math.random());

        TAXCOLLECTION.params = new Object();
        TAXCOLLECTION.params.masterBeans = [];
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

        var propArr = Object.keys(TAXCOLLECTION.searchResult);
        for (var p in propArr) {
            var propId = propArr[p];
            var prop = TAXCOLLECTION.searchResult[propId];
            var tax = TAXCOLLECTION.taxDetailMap[prop.propertyUniqueId];
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
                        obj[attr] = TAXCOLLECTION.zoneMaster[prop[MASTERCOLUMN.attrInBean[attr]]];
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

            TAXCOLLECTION.params.masterBeans.push(obj);

        }

        $("#exportParams").val(JSON.stringify(TAXCOLLECTION.params));
        $("#exportTHead").val(JSON.stringify(masterHeadArr));
        $("#exportAttrToAdd").val(JSON.stringify(masterAttrArr));
        $("#exportType").val(TAXCOLLECTION.type);
        $("#expo").submit();
        $('#export-modal').modal('toggle');
    },
    wardMaster: {},
    getWard: function () {

//        if ($("#zone").val() === "-1") {
//
//        } else {
        $.post("getWards", {zone: $("#zone").val()}, function (result) {
            if (result != undefined) {
                var html = "";
                html += "<option value='-1'>--Select Ward--</option>";
                for (var i = 0; i < result.length; i++) {
                    html += "<option value='" + result[i].ward + "'>" + result[i].displayName + "</option>";
                    TAXCOLLECTION.wardMaster[result[i].ward] = result[i].displayName;
                }
                TAXCOLLECTION.resetWard(html);
            }
        }, 'json').always(function () {

        });
//        }


    },
    localityMaster: {},
    getLocality: function () {
        $.post("getSubLocality", {ward: $("#ward").val()}, function (result) {
            if (result != undefined) {
                var html = "";
                html += "<option value='-1'>--Select Locality--</option>";
                for (var i = 0; i < result.length; i++) {
                    html += "<option value='" + result[i].subLocality + "'>" + result[i].displayName + "</option>";
                    TAXCOLLECTION.localityMaster[result[i].subLocality] = result[i].displayName;
                }
                TAXCOLLECTION.resetLocality(html);
            }
        }, 'json').always(function () {

        });
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
    resetLocality: function (html) {

        if (html === undefined) {
            $("#locality").html("<option value='-1'>--Select Locality--</option>");

        } else {
            $("#locality").html(html);
        }

        $("#locality").SumoSelect().sumo.unload();
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
    resetOccupier: function (html) {
        if (html === undefined) {
            $("#occupierid").html("<option value='-1'>--Select Occupier Name--</option>");

        } else {
            $("#occupierid").html(html);
        }
        $("#occupierid").SumoSelect().sumo.unload();
        $("#occupierid").SumoSelect({csvDispCount: 3, search: true, searchText: 'Enter here.'});

        if (html === undefined) {
            $("#occupierid").closest(".SumoSelect").find(".CaptionCont").addClass("disable_cls");
            $("#occupierid").prop("disabled", true);
        } else {
            $("#occupierid").prop("disabled", false);
            $("#occupierid").closest(".SumoSelect").removeClass("disabled");
            $("#occupierid").closest(".SumoSelect").find(".CaptionCont").removeClass("disable_cls");
        }
    },
    resetOwner: function (html) {
        if (html === undefined) {
            //  $("#ownerid").html("<option value='-1'>--Select Owner Name--</option>");
            //  $("#ownerid").closest(".SumoSelect").find(".CaptionCont").addClass("disable_cls");
        } else {
            // $("#ownerid").html(html);
            //  $("#ownerid").closest(".SumoSelect").find(".CaptionCont").removeClass("disable_cls");
        }
        // $("#ownerid").SumoSelect().sumo.unload();
        // $("#ownerid").SumoSelect({csvDispCount: 3, search: true, searchText: 'Enter here.'});

        if (html === undefined) {
            //    $("#ownerid").closest(".SumoSelect").find(".CaptionCont").addClass("disable_cls");
            //    $("#ownerid").prop("disabled", true);
        } else {
            //   $("#ownerid").prop("disabled", false);
            //   $("#ownerid").closest(".SumoSelect").removeClass("disabled");
            //   $("#ownerid").closest(".SumoSelect").find(".CaptionCont").removeClass("disable_cls");
        }

    },
    resetPropertyId: function (html) {
        if (html === undefined) {
            //  $("#propertyid").html("<option value='-1'>--Select Property ID--</option>");

        } else {
            // $("#propertyid").html(html);
        }
        //$("#propertyid").SumoSelect().sumo.unload();
        // $("#propertyid").SumoSelect({csvDispCount: 3, search: true, searchText: 'Enter here.'});
        if (html === undefined) {
            //    $("#propertyid").prop("disabled", true);
            //   $("#propertyid").closest(".SumoSelect").find(".CaptionCont").addClass("disable_cls");
        } else {
            //   $("#propertyid").prop("disabled", false);
            //   $("#propertyid").closest(".SumoSelect").removeClass("disabled");
            //   $("#propertyid").closest(".SumoSelect").find(".CaptionCont").removeClass("disable_cls");
        }

    },
    resetAadhar: function () {
        $("#src_aadhar_no").val("");
    },
    resetPropertyCategory: function (html) {
        if (html === undefined) {
            $("#category").html("<option value='-1'>--Select Property Type Name--</option>");

        } else {
            $("#category").html(html);
        }
        $("#category").SumoSelect().sumo.unload();
        $("#category").SumoSelect({csvDispCount: 3, search: true, searchText: 'Enter here.'});
        if (html === undefined) {
            $("#category").prop("disabled", true);
            $("#category").closest(".SumoSelect").find(".CaptionCont").addClass("disable_cls");
        } else {
            $("#category").prop("disabled", false);
            $("#category").closest(".SumoSelect").removeClass("disabled");
            $("#category").closest(".SumoSelect").find(".CaptionCont").removeClass("disable_cls");
        }

    },
    paymentHistoryMap: {},
    loadPaymentHistory: function (propertyId) {
        $("#label_prop_id").text(propertyId);

        $.ajax({
            url: "getPaymentHistory",
            type: 'post',
            data: 'propId=' + propertyId,
            dataType: 'json',
            success: function (result) {
                try {
                    $('#historyTAX').DataTable().destroy();
                } catch (e) {
                }
                var html_ = "";
                for (var r in result) {

                    TAXCOLLECTION.paymentHistoryMap[result[r].payRefId] = result[r];
                    var payBean = result[r];

                    var d = new Date(payBean.entryDateTime);
                    //dateString = d.toGMTString();
                    dateString = d.getDate() + '-' + (d.getMonth() + 1) + '-' + d.getFullYear();
                    //console.log(dateString );

                    html_ += "<tr tx_id='" + payBean.payRefId + "'><td>" + payBean.payRefId + "</td><td>"
                            + payBean.amountPaid + "</td><td>" + dateString
                            + "</td><td>2019-2020</td><td><a href='javascript: void(0)'  class='download_recpt' > Download </a></td></tr>";
                }
                $("#historyTAX tbody").html(html_);
                $('#tabBody').show();
                //debugger;

                $('#historyTAX').DataTable({
                    dom: 'Bfrtip',
                    buttons: [
                        'excel', 'pdf'
                    ],
                    pageLength: 10,
                    pagingType: 'full_numbers',
                    lengthMenu: [[10, 25, 50, -1], [10, 25, 50, 'All']]
                });


                $('.dataTables_wrapper table').wrap('<div class="scroll_table"></div>');
//                TAXCOLLECTION.showHistoryWindow();
            },
            error: function (e) {
                console.log("ERROR: ", e);
            }
        });

    },
    printCollectionReceiptFromHistory: function (tx_ref_id) {

        var payBean = TAXCOLLECTION.paymentHistoryMap[tx_ref_id];

        //console.log(payBean)
        $("#validateid").html('');
        if (payBean !== undefined) {
            //alert("payBean.payRefId "+payBean.payRefId)
            var prop = TAXCOLLECTION.searchResult[TAXCOLLECTION.currentPropertyId];
            var taxObj = TAXCOLLECTION.taxDetailMap[TAXCOLLECTION.currentPropertyId];
            var print_html = "";
            var amtInWords = convertNumberToWords(payBean.amountPaid);
            var remainedTax = parseFloat(payBean.amountPaid).toFixed(2) - parseFloat(payBean.amountPaid).toFixed(2);
            var bankName = TAXCOLLECTION.bankMaster[payBean.bankName];

            debugger;
            //alert($("#as_zone1").val());
            //alert($("#as_ward1").val());
            //console.log(payBean.propId);
            $("#receiptRefNo").val(payBean.payRefId);
            $("#receiptpropId").val(payBean.propId);

            $("#taxCollectionHistory").submit();


//            print_html += " <!doctype html><html><head><meta charset='utf-8'><title>Payment Print Receipt (" + payBean.propId + ")</title></head><style>";
//            print_html += " html, body, div, span, applet, object, iframe, h1, h2, h3, h4, h5, h6, p, blockquote, pre, a, abbr, acronym, address, big, cite, code, del, dfn, em, img, ins, kbd, q, s, samp, small, strike, strong, sub, sup, tt, var, b, u, i, center, dl, dt, dd, ol, ul, li, fieldset, form, label, legend, table, caption, tbody, tfoot, thead, tr, th, td, article, aside, canvas, details, embed, figure, figcaption, footer, header, hgroup, menu, nav, output, ruby, section, summary, time, mark, audio, video { margin: 0; padding: 0; border: 0; font-size: 100%; font: inherit; vertical-align: baseline; }";
//            print_html += " article, aside, details, figcaption, figure, footer, header, hgroup, menu, nav, section { display: block; }";
//            print_html += " body { line-height: 1; font-size: 14px; line-height: normal; background: #fff; color: #000; font-family: 'Open Sans', sans-serif; }";
//            print_html += " ol, ul { list-style: none; }";
//            print_html += " blockquote, q { quotes: none; }";
//            print_html += " blockquote:before, blockquote:after, q:before, q:after { content: ''; content: none; }";
//            print_html += " table { border-collapse: collapse; border-spacing: 0; } body { margin-top: 30px; } h2 { font-size: 16px; font-weight: bold; margin: 10px; }";
//            print_html += " p { font-size: 13px; } table, th, td { padding: 0; margin: 0; border: 1px solid black; } td { padding: 7px; }";
//            print_html += " </style> <body> <div style='max-width: 700px; width: 100%; margin: 0 auto;'> <div style='text-align: center; position: relative;'>";
//            print_html += " <h2>Silvassa Municipal Council</h2>";
//            print_html += " <p>Shahid Chowk, Near Town Hall, U.T. of Dadra & Nagar Haveli, Silvassa - 396230 <br>";
//            print_html += " Phone No.:(0260)-2633192, Fax No.:(0260)2633191: Email-Id: smc2006@ymail.com : <br>";
//            print_html += " Help Line Number: 0260-2633192 : Timing 09:30 AM to 06:00 PM</p>";
//            print_html += " <h2 style='text-decoration: underline'>Acknowledgement</h2>";
//            print_html += " <p>Receipt of Property Tax payment</p>";
//            print_html += " <img src='res/img/logo.png' alt='' style='position: absolute; top: 0; right: 0;'> </div>";
//            print_html += " <table style='width: 100%; margin-top: 20px;'> <tbody>";
//            print_html += " <tr> <td><span style='font-weight:bold'>Payment Id : </span>" + (payBean.payRefId === undefined ? "" : payBean.payRefId) + "</td>";
//            print_html += " <td> <span style='font-weight:bold'>Payment Date:</span> " + (payBean.entryDateTime === undefined ? "" : payBean.entryDateTime) + " </td></tr>";
//            print_html += " <tr> <td><span style='font-weight:bold'>Property Id : </span>" + payBean.propId + "</td>";
//            print_html += " <td> <span style='font-weight:bold'>Financial Year:</span><span> " + (taxObj.financialYear === undefined ? "" : taxObj.financialYear) + "</span> </td> </tr>";
//            print_html += " <tr> <td><span style='font-weight:bold'>Payment for (Property Tax\\Penalty\\Arrear\\Any Other mention) : </span></td>";
//            print_html += " <td colspan='2'> <span style='font-weight:bold'>Demand as on date (Amount of tax etc) :</span>Rs. " + (payBean.amountDemand === undefined ? "" : payBean.amountDemand) + "</span></td> </tr>";
//            print_html += " <tr>  <td colspan='2'><span style='font-weight:bold'>Owner Name : </span>" + (prop.propertyOwner === undefined ? "" : prop.propertyOwner) + "</td> </tr>";
//            print_html += " <tr><td colspan='2'> <span style='font-weight:bold'>Father \\ spouse name: </span>" + (prop.propertyOwnerFather === undefined ? "" : prop.propertyOwnerFather) + "</td> </tr>";
//            print_html += " <tr> <td colspan='2'><span style='font-weight:bold'>Occupier Name: </span>" + (prop.propertyOccupierName === undefined ? "" : prop.propertyOccupierName) + "</td></tr> ";
//            print_html += " <tr><td colspan='2'> <span style='font-weight:bold'>Relation with Occupier: </span>" + (prop.propertyRelationOwner === undefined ? "" : prop.propertyRelationOwner) + "</td> </tr>";
//            print_html += " <tr> <td colspan='2'><span style='font-weight:bold'>Address of the Property:</span> " + (prop.completeAddress === undefined ? "" : prop.completeAddress) + "</td></tr> ";
//            print_html += " <tr><td colspan='2'><span style='font-weight:bold'> Amount Received:</span> Rs. " + (payBean.amountPaid === undefined ? "" : payBean.amountPaid) + "</td> </tr>";
//            print_html += " <tr> <td colspan='2'><span style='font-weight:bold'>In Word: </span>Rupees " + (amtInWords === "" ? "Zero" : amtInWords) + " only</td></tr> ";
//
//            print_html += " <tr><td><span style='font-weight:bold'>Mode of Payment:</span>" + (payBean.paymentMode === undefined ? "" : payBean.paymentMode) + "</td>";
//            print_html += " <td><span style='font-weight:bold'></span></td></tr>";
//            print_html += " <tr><td><span style='font-weight:bold'>E Transaction Id (if E-Payment):</span>" + (payBean.bankRefId === undefined ? "" : payBean.bankRefId) + "</td>";
//            print_html += " <td><span style='font-weight:bold'>Cheque \\ DD No:</span> " + (payBean.chequeDDNum === undefined ? "" : payBean.chequeDDNum) + "</td></tr>";
//            print_html += " <tr><td><span style='font-weight:bold'>Bank:</span>" + (bankName === undefined ? "" : bankName) + "</td>";
//            print_html += " <td><span style='font-weight:bold'>Branch:</span>" + (payBean.bankBranch === undefined ? "" : payBean.bankBranch) + "</td></tr>";
//
//            print_html += " </tbody></table><br> <div class=''> <p>Thanks </p> <br><p>Silvassa Municipal Corporation, Silvassa <br>(Authorized Signature of Authority with stamp, date)</p>";
//            print_html += " </div></div></body></html>";
//
//            var mywindow = window.open('', 'PRINT', 'height=400,width=600');
//            //alert(mywindow.chrome);
//            var is_chrome = Boolean(mywindow.chrome);
//            // mywindow.document.write(data);
//            mywindow.document.write(print_html);
//            if (is_chrome) {
//                setTimeout(function() { // wait until all resources loaded 
//                    mywindow.document.close(); // necessary for IE >= 10
//                    mywindow.focus(); // necessary for IE >= 10
//                    mywindow.print(); // change window to winPrint
//                    mywindow.close(); // change window to winPrint
//                }, 250);
//            } else {
//                mywindow.document.close(); // necessary for IE >= 10
//                mywindow.focus(); // necessary for IE >= 10
//                mywindow.print();
//                mywindow.close();
//            }
            //  return true;
        } else {
            $("#validateid").html("Unable to process request.");
            //alert("Unable to process request.");
            // return false;
        }
    },
    enableNotice: function (propid) {
        var url = location.href.substring(0, location.href.lastIndexOf('/')) + "/generatePrivateNotice?zoneId=-1&ward=-1&propertyId=" + propid;
        $("#show_notice").removeClass("disabled");
        $("#show_notice").attr("href", url);
        $('#prop_notice_status').text("Yes");
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
    singleproperty: function (propId) {

        TAXCOLLECTION.currentPropertyId = propId;
        $("#validateid").html('');
        if (TAXCOLLECTION.taxDetailMap[TAXCOLLECTION.currentPropertyId].noticeGenerated === 'Y') {
            TAXCOLLECTION.populatePropertyDetail(propId);
            TAXCOLLECTION.showCollectionWindow();
            TAXCOLLECTION.loadPaymentHistory(TAXCOLLECTION.currentPropertyId);

        } else {
            $("#validateid").html("TAX can be collected for the property where TAX Notice generated.");
            //alert("TAX can be collected for the property where TAX Notice generated.");
        }
    }

}


$(document).on("click", "#export_btn", function () {
    TAXCOLLECTION.download();
});


//$(document).on("change", "#zone", function () {
//    TAXCOLLECTION.resetWard();
//    TAXCOLLECTION.resetOccupier();
//    TAXCOLLECTION.resetOwner();
//    TAXCOLLECTION.resetPropertyId();
//    TAXCOLLECTION.resetAadhar();
//    TAXCOLLECTION.resetLocality();
//    TAXCOLLECTION.getWard();
//    $("#src_aadhar_no").addClass("disable_cls");
//    $("#src_aadhar_no").prop("disabled", true);
//});

$(document).on("change", "#propertyid", function () {
    if ($("#propertyid option:selected").val() !== "-1") {
        $("#locality,#ownerid,#occupierid").prop("disabled", true);
        $("#locality,#ownerid,#occupierid").val("-1");
        $("#locality,#ownerid,#occupierid").closest(".SumoSelect").find(".CaptionCont span").text("");
        $("#locality,#ownerid,#occupierid").closest(".SumoSelect").find(".CaptionCont").addClass("disable_cls");
        $("#src_aadhar_no").val("");
        $("#src_aadhar_no").addClass("disable_cls");
        $("#src_aadhar_no").prop("disabled", true);
    } else {
        $("#locality,#ownerid,#occupierid").prop("disabled", false);
        $("#locality,#ownerid,#occupierid").closest(".SumoSelect").find(".CaptionCont").removeClass("disable_cls");
        $("#src_aadhar_no").removeClass("disable_cls");
        $("#src_aadhar_no").prop("disabled", false);
    }


});



$(document).on("click", "#btn_view_tax_summary", function () {
    TAXCOLLECTION.showSummaryWindow();
    // TAXCOLLECTION.populateTAXSummary(TAXCOLLECTION.currentPropertyId);
});
$(document).on("click", "#btn_view_tax_history", function () {
    TAXCOLLECTION.loadPaymentHistory(TAXCOLLECTION.currentPropertyId);
});
$(document).on("click", "#close_btn_taxDetailView", function () {
    $("#taxDetailView").addClass("hidden");
    $("#taxCollection").removeClass("hidden");
});
$(document).on("click", "#close_btn_taxHistoryView", function () {
    $("#taxHistoryView").addClass("hidden");
    $("#taxCollection").removeClass("hidden");
});

$(document).on("click", "#btn_back_search_window", function () {
    TAXCOLLECTION.showSearchWindow();///use this to open previous main div with data loaded in it.
    // window.open(window.location.href, "_self");
});

$(document).on("click", "#btn_back_results", function () {
    TAXCOLLECTION.showResultWindow();   ///use this to open previous main div with data loaded in it.
//window.open(window.location.href, "_self");
});

$(document).on("click", "#btn_property_submit", function () {
    TAXCOLLECTION.searchPropertyForTAX();
});

$(document).on("click", "#btn_pay_tax", function () {

    TAXCOLLECTION.fillBankList();
    TAXCOLLECTION.fillPaymentModes();

    $("#enc_cheque_dd_num").val("");
    $("#enc_cheque_dd_date").val("");
    $("#enc_banks_branch").val("");


    TAXCOLLECTION.showPaymentWindow();

});

$(document).on("click", ".openPropertyDetails", function () {
    var propId = $(this).attr("propId");
    TAXCOLLECTION.currentPropertyId = propId;
    $("#validateid").html('');
    if (TAXCOLLECTION.taxDetailMap[TAXCOLLECTION.currentPropertyId].noticeGenerated === 'Y') {
        TAXCOLLECTION.populatePropertyDetail(propId);
        TAXCOLLECTION.showCollectionWindow();
        TAXCOLLECTION.loadPaymentHistory(TAXCOLLECTION.currentPropertyId);

    } else {
        $("#validateid").html("TAX can be collected for the property where TAX Notice generated.");
        //alert("TAX can be collected for the property where TAX Notice generated.");
    }

});

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


$(document).on("change", "#ward", function () {

    $("#zone").val(zoneWardMaster[$(this).val()]);

    TAXCOLLECTION.resetOccupier();
    TAXCOLLECTION.resetOwner();
    TAXCOLLECTION.resetPropertyId();
    TAXCOLLECTION.resetAadhar();
    TAXCOLLECTION.resetLocality();
    if ($("#ward").val() !== "-1") {
        TAXCOLLECTION.getLocality();
        $("#src_aadhar_no").removeClass("disable_cls");
        $("#src_aadhar_no").prop("disabled", false);
    }
    TAXCOLLECTION.fillSearchCriteria();
});

$(document).on("click", ".download_recpt", function () {
    var tx_ref_id = $(this).closest("tr").attr("tx_id");
    TAXCOLLECTION.printCollectionReceiptFromHistory(tx_ref_id);
});
