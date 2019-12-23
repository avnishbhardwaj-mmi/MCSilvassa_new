/* 
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 * 
 */

$(document).ready(function() {
    PrintPaymentReceipt.loadZone();
    // PrintPaymentReceipt.loadRentableValues();
    $("#print_receipts_menu").addClass("active");
//    PrintPaymentReceipt.createDataTable();

    $("#propertyid").SumoSelect({csvDispCount: 3, search: true, searchText: 'Enter here.'});
    PrintPaymentReceipt.showSearchWindow();
    PrintPaymentReceipt.fillBankList();

});

var PrintPaymentReceipt = {
    tableSearchResult: '#tableSearchResult',
    currentPropertyId: null,
    toDecimalPoints: 3,
    showSearchWindow: function() {
        $("#searchArea").removeClass("hidden");
        $("#searchResults").addClass("hidden");
        $("#taxCollection").addClass("hidden");
        $("#taxPaymentView").addClass("hidden");
        $("#submitMesage").addClass("hidden");
    },
    showResultWindow: function() {
        $("#searchArea").addClass("hidden");
        $("#searchResults").removeClass("hidden");
        $("#taxCollection").addClass("hidden");
        $("#taxPaymentView").addClass("hidden");
        $("#submitMesage").addClass("hidden");
    },
    zoneMaster: {
        "-1": "Not found"
    },
    wardMaster: {
        "-1": "Not found"
    },
    localityMaster: {
        "-1": "Not found"
    },
    propTypeMaster: {
        "-1": "Not found"
    },
    loadZone: function() {

        $.ajax({
            url: "loadZones",
            type: 'post',
            data: '',
            dataType: 'json',
            success: function(result) {
                if (result != undefined) {
                    var html = "";
                    html += "<option value='-1' selected>--Select Zone--</option>";
                    for (var i = 0; i < result.length; i++) {
                        html += "<option value='" + result[i].zoneId + "'>" + result[i].zoneName + "</option>";
                        PrintPaymentReceipt.zoneMaster[result[i].zoneId] = result[i].zoneName;
                    }
                    $("#zone").html(html);
                    $("#zone").SumoSelect({csvDispCount: 3, search: true, searchText: 'Enter here.'});
                }
            },
            error: function(e) {
                console.log("ERROR: ", e);
            }
        });

    },
    rentableValuesMap: {},
    loadRentableValues: function() {
        $.ajax({
            url: "getRentableValues",
            type: 'post',
            data: '',
            dataType: 'json',
            success: function(result) {
                if (result != null) {
                    for (var i = 0; i < result.length; i++) {
                        PrintPaymentReceipt.rentableValuesMap[result[i].propertyRentableId] = result[i];
                    }
                }
            },
            error: function(e) {
                console.log("ERROR: ", e);
            }
        });
    },
    ownerNameArr: [],
    occupierNameArr: [],
    propIdArr: [],
    fillSearchCriteria: function() {

        PrintPaymentReceipt.ownerNameArr = [];
        PrintPaymentReceipt.occupierNameArr = [];
        PrintPaymentReceipt.propIdArr = [];
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

                    PrintPaymentReceipt.ownerNameArr = result.prop.ownerArr;
                    PrintPaymentReceipt.occupierNameArr = result.prop.occupierArr;
                    PrintPaymentReceipt.propIdArr = result.prop.propIdArr;

                    for (var it in PrintPaymentReceipt.ownerNameArr) {
                        var item = PrintPaymentReceipt.ownerNameArr[it].property_owner;
                        html_own += "<option value='" + item + "'>" + item + "</option>";
                    }

                    for (var it in PrintPaymentReceipt.occupierNameArr) {
                        var item = PrintPaymentReceipt.occupierNameArr[it].property_occupier_name;
                        html_occ += "<option value='" + item + "'>" + item + "</option>";
                    }

                    for (var it in PrintPaymentReceipt.propIdArr) {
                        var item = PrintPaymentReceipt.propIdArr[it].property_unique_id;
                        html_prop += "<option value='" + item + "'>" + item + "</option>";
                    }


                    $("#propertyid").html("");
                    $("#propertyid").html(html_prop);

                    $("#ownerid").html("");
                    $("#ownerid").html(html_own);

                    $("#occupierid").html("");
                    $("#occupierid").html(html_occ);


                    $("#propertyid").SumoSelect().sumo.unload();                    
                    $("#propertyid").SumoSelect({csvDispCount: 3, search: true, searchText: 'Enter here.'});
                    LOADER.hide();
                }

            },
            error: function(e) {
                console.log("ERROR: ", e);
            }
        });
    },
    formatAmount: function(num__) {
        if (isNaN(num__) || isNaN(parseFloat(num__))) {
            return 0;
        } else {
            return parseFloat(num__).toFixed(PrintPaymentReceipt.toDecimalPoints);
        }
    },
    searchResult: {},
    paymentMap: {},
    preservePaymentDetails: function(detailArr) {

        PrintPaymentReceipt.paymentMap = {};
        if (detailArr === undefined || detailArr === null) {

        } else {
            for (var dt in detailArr) {
                var det = detailArr[dt];
                if (det !== null) {
                    PrintPaymentReceipt.paymentMap[det.payRefId] = det;

                }
            }
        }

    },
    propertyMap: {},
    preservePropertyDetails: function(detailArr) {
        //console.log("preservePropertyDetails-->"+detailArr);
        PrintPaymentReceipt.propertyMap = {};
        if (detailArr === undefined || detailArr === null) {

        } else {
            for (var dt in detailArr) {
                var det = detailArr[dt];
                if (det !== null) {
                    PrintPaymentReceipt.propertyMap[det.propertyUniqueId] = det;
                    //console.log(ObjectionReport.propertyMap);
                }
            }
        }

    },
    searchPropertyForReceipt: function() {
        LOADER.show();
        var obj = {};
        obj.zone = $("#zone  option:selected").val();
        obj.propId = $("#propertyid option:selected").val();

        $.ajax({
            url: "printPayAmountReceipt",
            type: 'post',
            data: "params=" + JSON.stringify(obj),
            success: function(data) {
                LOADER.hide();
                var propArr = data.propArr;
                //console.log(propArr);
                var paymentArr = data.paymentArr;
                // console.log(paymentArr);
                PrintPaymentReceipt.preservePropertyDetails(propArr);
                PrintPaymentReceipt.preservePaymentDetails(paymentArr);
                if (propArr !== undefined && paymentArr !== undefined && paymentArr.length > 0) {
                    var tab_html = "<thead>" + "<tr>"
                            + "<th>S.No.</th>" + "<th>Property ID</th>"
                            + "<th>Payment Reference No.</th>"
                            + "<th>Owner Name</th>"
                            + "<th>Occupier Name</th>"
                            + "<th>Relation With Owner</th>"
                            + "<th>Address</th>"
                            + "<th>Zone</th>"
                            + "<th>Print Receipt</th>"
                            + "</tr></thead><tbody>";

                    var payArr = PrintPaymentReceipt.paymentMap;
                    var keyArr = Object.keys(payArr);
                    for (var k in keyArr) {

                        var key = keyArr[k];
                        var payBean = PrintPaymentReceipt.paymentMap[key];
                        var prop = PrintPaymentReceipt.propertyMap[payBean.propId];

                        //var prop = propArr[r];
                        //console.log(prop);
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
                        prop.propertySublocality = (prop.propertySublocality === undefined || prop.propertySublocality === null) ? "" : prop.propertySublocality;
                        prop.propertyLandmark = (prop.propertyLandmark === undefined || prop.propertyLandmark === null) ? "" : prop.propertyLandmark;
                        prop.completeAddress = (prop.completeAddress === undefined || prop.completeAddress === null) ? "" : prop.completeAddress;
                        prop.propertyOwnerFather = (prop.propertyOwnerFather === undefined || prop.propertyOwnerFather === null) ? "" : prop.propertyOwnerFather;

                        tab_html += "<td>" + (parseInt(k) + 1) + "</td>";
                        tab_html += "<td>" + prop.propertyUniqueId + "</td>";
                        tab_html += "<td>" + payBean.payRefId + "</td>";
                        tab_html += "<td>" + prop.propertyOwner + "</td>";
                        tab_html += "<td>" + prop.propertyOccupierName + "</td>";
                        tab_html += "<td>" + prop.propertyRelationOwner + "</td>";

                        tab_html += "<td>" + (prop.completeAddress === undefined ? "" : prop.completeAddress) + "</td>";
                        tab_html += "<td>" + (PrintPaymentReceipt.zoneMaster[prop.zoneId] === undefined ? "" : PrintPaymentReceipt.zoneMaster[prop.zoneId]) + "</td>";
                        if (prop.propertyUniqueId !== undefined) {
                            tab_html += "<td style='text-align:left' onclick=\"PrintPaymentReceipt.printCollectionReceipt('"
                                    + payBean.payRefId
                                    + "','" + prop.propertyUniqueId + "')\"><a href='javascript: void(0)'>Print Receipt"
                                    + "</a> </td>";
                        }
                        tab_html += "</tr>";

                    }
                    tab_html += "</tbody>";
                    $(PrintPaymentReceipt.tableSearchResult).html(tab_html);
                    PrintPaymentReceipt.showResultWindow();
                    PrintPaymentReceipt.createDataTable();
                } else {
                    alert("No result found.");
                }

            },
            error: function(e) {
                LOADER.hide();
                console.log("ERROR: ", e);
            }
        });

    },
    preserveSearchResult: function(result) {
        PrintPaymentReceipt.searchResult = {};
        if (result !== undefined) {
            for (var r in result) {
                var prop = result[r];
                PrintPaymentReceipt.searchResult[prop.propertyUniqueId] = prop;
            }
        }
    },
    dataTable: null,
    createDataTable: function() {
        PrintPaymentReceipt.dataTable = $(PrintPaymentReceipt.tableSearchResult).DataTable(
                {
                    "dom": 'Bfrtip',
                    //"scrollY": "350px",
                    "scrollCollapse": true,
                    "info": true,
                    "paging": true,
                    "bDestroy": true,
                    "sScrollX": "100%",
                    "sScrollXInner": "100%",
                    "bScrollCollapse": true
                });
        $(PrintPaymentReceipt.tableSearchResult + "_paginate").css({
            "position": "absolute", "top": "0", "right": "0"
        });

        $(PrintPaymentReceipt.tableSearchResult + "_filter").css({
            "float": "left"
        });
    },
    destroyDataTable: function() {
        PrintPaymentReceipt.dataTable.destroy();
        $(PrintPaymentReceipt.tableSearchResult).html("");
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
                    PrintPaymentReceipt.bankMaster[bank.bankId] = bank.bankName;
                }
                $("#enc_banks").html(strHTML);
            },
            error: function () {
            }
        });
    },
    printCollectionReceipt: function(payRefId, propId) {
        var payBean = PrintPaymentReceipt.paymentMap[payRefId];
        if (payBean !== undefined) {
            var prop = PrintPaymentReceipt.propertyMap[propId];
            var print_html = "";

            var amtInWords = convertNumberToWords(payBean.amountPaid);
            var remainedTax = parseFloat(payBean.amountDemand).toFixed(2) - parseFloat(payBean.amountPaid).toFixed(2);
            var bankName = PrintPaymentReceipt.bankMaster[payBean.bankName];
            // alert(parseFloat(remainedTax).toFixed(2));

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

            print_html += " <!doctype html><html><head><meta charset='utf-8'><title>Payment Print Receipt (" + payBean.propertyId + ")</title></head><style>";
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
            print_html += " <p>Receipt of Property Tax payment</p>";
            print_html += " <img src='res/img/logo.png' alt='' style='position: absolute; top: 0; right: 0;'> </div>";
            print_html += " <table style='width: 100%; margin-top: 20px;'> <tbody>";
            print_html += " <tr> <td><span style='font-weight:bold'>Payment Id : </span>" + (payBean.payRefId === undefined ? "" : payBean.payRefId) + "</td>";
            print_html += " <td> <span style='font-weight:bold'>Payment Date:</span> " + (payBean.entryDateTime === undefined ? "" : payBean.entryDateTime) + " </td></tr>";
            print_html += " <tr> <td><span style='font-weight:bold'>Property Id : </span>" + payBean.propId + "</td>";
           // print_html += " <td> <span style='font-weight:bold'>Financial Year:</span><span> " + (payBean.financialYear === undefined ? "" : payBean.financialYear) + "</span> </td> </tr>";
            print_html += " <td> <span style='font-weight:bold'>Financial Year:</span><span> 2019-2020 </span> </td> </tr>";
            print_html += " <tr> <td><span style='font-weight:bold'>Payment for (Property Tax\\Penalty\\Arrear\\Any Other mention) : </span></td>";
            print_html += " <td colspan='2'> <span style='font-weight:bold'>Demand as on date (Amount of tax etc) :</span>Rs. " + (payBean.amountDemand === undefined ? "" : payBean.amountDemand) + "</span></td> </tr>";
            print_html += " <tr>  <td colspan='2'><span style='font-weight:bold'>Owner Name : </span>" + (prop.propertyOwner === undefined ? "" : prop.propertyOwner) + "</td> </tr>";
            print_html += " <tr><td colspan='2'> <span style='font-weight:bold'>Father \\ spouse name: </span>" + (prop.propertyOwnerFather === undefined ? "" : prop.propertyOwnerFather) + "</td> </tr>";
            print_html += " <tr> <td colspan='2'><span style='font-weight:bold'>Occupier Name: </span>" + (prop.propertyOccupierName === undefined ? "" : prop.propertyOccupierName) + "</td></tr> ";
            print_html += " <tr><td colspan='2'> <span style='font-weight:bold'>Relation with Occupier: </span>" + (prop.propertyRelationOwner === undefined ? "" : prop.propertyRelationOwner) + "</td> </tr>";
            print_html += " <tr> <td colspan='2'><span style='font-weight:bold'>Address of the Property:</span> " + (prop.completeAddress === undefined ? "" : prop.completeAddress) + "</td></tr> ";
            print_html += " <tr><td colspan='2'><span style='font-weight:bold'> Amount Received:</span> Rs. " + (payBean.amountPaid === undefined ? "" : payBean.amountPaid) + "</td> </tr>";
            print_html += " <tr> <td colspan='2'><span style='font-weight:bold'>In Word: </span>Rupees " + (amtInWords === "" ? "Zero" : amtInWords) + " only</td></tr> ";

            print_html += " <tr><td><span style='font-weight:bold'>Mode of Payment:</span>" + (payBean.paymentMode === undefined ? "" : payBean.paymentMode) + "</td>";
            print_html += " <td><span style='font-weight:bold'></span></td></tr>";
            print_html += " <tr><td><span style='font-weight:bold'>E Transaction Id (if E-Payment):</span>" + (payBean.bankRefId === undefined ? "" : payBean.bankRefId) + "</td>";
            print_html += " <td><span style='font-weight:bold'>Cheque \\ DD No:</span> " + (payBean.chequeDDNum === undefined ? "" : payBean.chequeDDNum) + "</td></tr>";
            print_html += " <tr><td><span style='font-weight:bold'>Bank:</span>" + (bankName === undefined ? "" : bankName) + "</td>";
            print_html += " <td><span style='font-weight:bold'>Branch:</span>" + (payBean.bankBranch === undefined ? "" : payBean.bankBranch) + "</td></tr>";

            print_html += " </tbody></table><br> <div class=''> <p>Thanks </p> <br><p>Silvassa Municipal Corporation, Silvassa <br>(Authorized Signature of Authority with stamp, date)</p>";
            print_html += " </div></div></body></html>";


            var mywindow = window.open('', 'PRINT', 'height=400,width=600');
            //alert(mywindow.chrome);
            var is_chrome = Boolean(mywindow.chrome);
            // mywindow.document.write(data);
            mywindow.document.write(print_html);

            if (is_chrome) {
                setTimeout(function() { // wait until all resources loaded 
                    mywindow.document.close(); // necessary for IE >= 10
                    mywindow.focus(); // necessary for IE >= 10
                    mywindow.print(); // change window to winPrint
                    mywindow.close(); // change window to winPrint
                }, 250);
            } else {
                mywindow.document.close(); // necessary for IE >= 10
                mywindow.focus(); // necessary for IE >= 10
                mywindow.print();
                mywindow.close();
            }

            return true;
        } else {
            alert("There is no payment done for this record.");
            return false;

        }
    }

}


$(document).on("change", "#zone", function() {
    if ($(this).val() != "-1") {
        PrintPaymentReceipt.fillSearchCriteria();
    }
});

$(document).on("click", "#btn_back_search_window", function() {
    PrintPaymentReceipt.showSearchWindow();
});

$(document).on("click", "#btn_property_submit", function() {

    if ($("#zone").val() === "-1") {
        alert("Kindly provide input");
    } else {
        PrintPaymentReceipt.searchPropertyForReceipt();
    }


});


