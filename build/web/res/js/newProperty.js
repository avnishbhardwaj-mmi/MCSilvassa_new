//----------------------------------------------------------------------------------------------------
//                          MapMyIndia
//            Product / Project           : Silvassa
//            Module                      : PropertyAdding
//            File Name                   : newProperty
//            Author                      : Jay Prakash Kumar
//            Project Lead                :
//            Date written (DD/MM/YYYY)   : 4 Jul, 2017, 9:55:05 AM
//            Description                 : 
//----------------------------------------------------------------------------------------------------
//                                            CHANGE HISTORY
//----------------------------------------------------------------------------------------------------
// Date             Change By           Change Description (Bug No. (If Any))
// 
// (DD/MM/YYYY)
//----------------------------------------------------------------------------------------------------
//----------------------------------------------------------------------------------------------------

var NewPro = {
    zoneMaster: {},
    wardMaster: {},
    rentableTypeArray: new Array(),
    floreIdCount: 0,
    floorDetails: new Array(),
    addNewProperty: function () {
        var propertyDetails = new Object();
        var propertyId = $("#propertyUniqueId").val();
        var zoneId = $("#zoneId").val();
        var wardId = $("#ward").val();
         $("#validateid").html('');
        if (propertyId === "" || wardId == "-1"  ||  $("#propertyLatitude").val() === "" || $("#propertyLongitude").val() === "") {
            //alert("Please fill Property Details properly");
             $("#validateid").html("Please Fill Property Details properly");
            return false;
        }
        $("#newProperty input[type=text]").each(function () {
            var input = $(this);
//            console.log(input[0].id, ":", $("#" + input[0].id).val());
            if (input[0].id !== "") {
                switch (input[0].id) {
                    case "propertyMale18Plus":
                        propertyDetails[input[0].id] = input[0].value === "" ? 0 : input[0].value;
                        break;
                    case "propertyFem18Plus":
                        propertyDetails[input[0].id] = input[0].value === "" ? 0 : input[0].value;
                        break;
                    case "propertyMale18Minus":
                        propertyDetails[input[0].id] = input[0].value === "" ? 0 : input[0].value;
                        break;
                    case "propertyFem18Minus":
                        propertyDetails[input[0].id] = input[0].value === "" ? 0 : input[0].value;
                        break;
                    default:
                        propertyDetails[input[0].id] = input[0].value === "" ? "-" : input[0].value;
                }
            }
        });
        $("#newProperty select").each(function () {
            var select = $(this);
//            if (select[0].id !== "") {
//                switch (select[0].id) {
//                    case "wardId":
//                        propertyDetails['ward'] = select[0].value === "" ? "0" : select[0].value;
//                        break;
//                    default:
            propertyDetails[select[0].id] = select[0].value === "" ? "0" : select[0].value;
//                }
//            }
        });
        if ($.isEmptyObject(propertyDetails)) {
            $("#validateid").html("No Value to Submit");
            //alert("No Value to Submit");
        } else {

//            NewPro.replaceNAWithMinusOne();
            NewPro.submitPropertyAddRequest(propertyDetails, NewPro.floorDetails);
        }
    },
    submitPropertyAddRequest: function (propertyDetails, floorDetails) {
        $.ajax({
            url: "addNewProperty",
            type: 'post',
            data: "newProperty=" + JSON.stringify(propertyDetails) + "&newPropertyFloor=" + JSON.stringify(floorDetails),
            dataType: 'json',
            success: function (result) {
                 $("#validateid").html('');
                if (result !== null) {
                    if (result.Status == 200) {
                       // alert("Added Successfully");
                       $("#validateid").html("Added Successfully");
                    } else {
                        //alert("Some Error Please try again");
                        $("#validateid").html("Some Error Please try again");
                    }
                } else {
                   // alert("Some Error Please try again");
                    $("#validateid").html("Some Error Please try again");
                }
            },
            error: function (e) {

                console.log("ERROR: ", e);
            }
        });
    },
    getRentableValues: function () {
        $.ajax({
            url: "getRentableValues",
            type: 'post',
            data: '',
            dataType: 'json',
            success: function (result) {
                if (result != null) {
                    var html = "";
                    html += "<option value='-1'>--Select Use--</option>";
                    for (var i = 0; i < result.length; i++) {
//                        console.log(result[i]);
                        NewPro.rentableTypeArray.push(result[i]);
                        html += "<option value='" + result[i].propertyRentableId + "'>" + result[i].propertyCat + " ( " + result[i].propertySubcatCode + " ) </option>";
                    }
                    $("#pfFloorwiseBuildUse").html(html);
                    $("#pfFloorwiseBuildUse").SumoSelect({csvDispCount: 3, search: true, searchText: 'Enter here.'});
                }
            },
            error: function (e) {
                console.log("ERROR: ", e);
            }
        });
    },
    addFloorDetailsToTable: function () {
        var propertyId = $("#propertyUniqueId").val();
        var zoneId = $("#zoneId").val();
        $("#validateid").html('');
        if (propertyId === "" || zoneId === "") {
            $("#validateid").html("Please fill Property Details");
            //alert("Please fill Property Details");
            return false;
        }
        var floorDetail = new Object();
        $("#floorDetatil input[type=text]").each(function () {
            var input = $(this);
//            console.log(input[0].id, ":", input[0].value);
//            
            if (input[0].id !== "") {
                switch (input[0].id) {
                    case "pfBuiltupArea":
                        floorDetail[input[0].id] = input[0].value == "" ? "0" : input[0].value;
                        floorDetail["propertyUniqueId"] = propertyId;
                        break;
                    default:
                        floorDetail[input[0].id] = input[0].value == "" ? "-" : input[0].value;
                        floorDetail["propertyUniqueId"] = propertyId;
                }
            }
        });
        $("#floorDetatil select").each(function () {
            var select = $(this);
            if (select[0].id !== "") {

                switch (select[0].id) {
                    case "pfFloorwiseBuildUse":
                        floorDetail['propertyRentableId'] = select[0].value == "-1" ? "-1" : select[0].value;
                        var ret = NewPro.returnUseUsingCode('propertyRentableId', select[0].value, 'propertyCat');
                        floorDetail['pfFloorwiseBuildUse'] = ret;
                        floorDetail["propertyUniqueId"] = propertyId;
                        break;
                    default:
                        floorDetail[select[0].id] = select[0].value == "-1" ? "NA" : select[0].value;
                        floorDetail["propertyUniqueId"] = propertyId;
                }
            }
        });
        NewPro.floorDetails.push(floorDetail);
        if ($.isEmptyObject(floorDetail)) {
        } else {
            NewPro.addFloorDetailsHtml();
        }
        $("#floorDetatilsTable a").removeClass('hidden');
    },
    addFloorDetailsHtml: function () {

        $('#floorDetatilsTable').find('tbody').empty();
        var fl = "";
        for (var j = 0; j < NewPro.floorDetails.length; j++) {
            var dt = NewPro.floorDetails[j];
            dt.pfFloorName = dt.pfFloorName === undefined ? "" : dt.pfFloorName;
            dt.pfBuiltupArea = dt.pfBuiltupArea === undefined ? "" : dt.pfBuiltupArea;
            dt.pfConstructionType = dt.pfConstructionType === undefined ? "" : dt.pfConstructionType;
            dt.pfFloorwiseBuildUse = dt.pfFloorwiseBuildUse === undefined ? "" : dt.pfFloorwiseBuildUse;
            dt.pfWaterPipeCon = dt.pfWaterPipeCon === undefined ? "" : dt.pfWaterPipeCon;
            dt.pfSewerageCon = dt.pfSewerageCon === undefined ? "" : dt.pfSewerageCon;
            dt.pfElectricMeterNum = dt.pfElectricMeterNum === undefined ? "" : dt.pfElectricMeterNum;
            dt.pfElectricConNum = dt.pfElectricConNum === undefined ? "" : dt.pfElectricConNum;
            dt.pfCctvCamrea = dt.pfCctvCamrea === undefined ? "" : dt.pfCctvCamrea;
            dt.pfFireEquipment = dt.pfFireEquipment === undefined ? "" : dt.pfFireEquipment;
            dt.pfLiftAvailable = dt.pfLiftAvailable === undefined ? "" : dt.pfLiftAvailable;
            dt.pfRainWaterHarvest = dt.pfRainWaterHarvest === undefined ? "" : dt.pfRainWaterHarvest;
            dt.pfNumOfBorewells = dt.pfNumOfBorewells === undefined ? "" : dt.pfNumOfBorewells;
            dt.pfSanitation = dt.pfSanitation === undefined ? "" : dt.pfSanitation;
            dt.pfHordingAvail = dt.pfHordingAvail === undefined ? "" : dt.pfHordingAvail;
            dt.pfMobileTower = dt.pfMobileTower === undefined ? "" : dt.pfMobileTower;
            fl += "<tr>";
            fl += "<td >" + (j + 1) + "</td>";
            fl += "<td >" + dt.pfFloorName + "</td>";
            fl += "<td >" + dt.pfBuiltupArea + "</td>";
            fl += "<td >" + dt.pfConstructionType + "</td>";
            fl += "<td >" + dt.pfFloorwiseBuildUse + "</td>";
            fl += "<td >" + dt.pfWaterPipeCon + "</td>";
            fl += "<td >" + dt.pfSewerageCon + "</td>";
            fl += "<td >" + dt.pfElectricMeterNum + "</td>";
            fl += "<td >" + dt.pfElectricConNum + "</td>";
            fl += "<td >" + dt.pfCctvCamrea + "</td>";
            fl += "<td >" + dt.pfFireEquipment + "</td>";
            fl += "<td >" + dt.pfLiftAvailable + "</td>";
            fl += "<td >" + dt.pfRainWaterHarvest + "</td>";
            fl += "<td >" + dt.pfNumOfBorewells + "</td>";
            fl += "<td >" + dt.pfSanitation + "</td>";
            fl += "<td >" + dt.pfHordingAvail + "</td>";
            fl += "<td >" + dt.pfMobileTower + "</td>";
            fl += '<td ><a href="javascript:NewPro.removeByAttr(\'pfFloorName\',\'' + dt.pfFloorName + '\');"> Remove</a></td>';
            fl += "</tr>";
        }
        $('#floorDetatilsTable').find('tbody').append(fl);

    },
    applyNumericaOnly: function () {
        $(".txtboxOnlyNumeric").keydown(function (e) {
            // Allow: backspace, delete, tab, escape, enter and .
            if ($.inArray(e.keyCode, [46, 8, 9, 27, 13, 110, 190]) !== -1 ||
                    // Allow: Ctrl+A, Command+A
                            (e.keyCode === 65 && (e.ctrlKey === true || e.metaKey === true)) ||
                            // Allow: home, end, left, right, down, up
                                    (e.keyCode >= 35 && e.keyCode <= 40)) {
                        // let it happen, don't do anything
                        return;
                    }
                    // Ensure that it is a number and stop the keypress
                    if ((e.shiftKey || (e.keyCode < 48 || e.keyCode > 57)) && (e.keyCode < 96 || e.keyCode > 105)) {
                        e.preventDefault();
                    }
                });
    },
    removeByAttr: function (attr, value) {

        var i = NewPro.floorDetails.length;
        while (i--) {
            if (NewPro.floorDetails[i]
                    && NewPro.floorDetails[i].hasOwnProperty(attr)
                    && NewPro.floorDetails[i][attr] === value) {
                NewPro.floorDetails.splice(i, 1);
            }
        }

        NewPro.addFloorDetailsHtml();
        $("#floorDetatilsTable a").addClass('hidden');
    },
    returnUseUsingCode: function (attr, value, attrv) {
        var i = NewPro.rentableTypeArray.length;
        var revalue = "NA";
        while (i--) {
            if (NewPro.rentableTypeArray[i]
                    && NewPro.rentableTypeArray[i].hasOwnProperty(attr)
                    && NewPro.rentableTypeArray[i][attr] === value) {
                revalue = NewPro.rentableTypeArray[i][attrv];
            }
        }
        return revalue;
    },
    replaceNAWithMinusOne: function () {

        for (var i = 0; i < NewPro.floorDetails.length; i++) {
            for (var key in NewPro.floorDetails[i]) {
                var value = NewPro.floorDetails[i][key];
                if (value === "NA") {
                    NewPro.floorDetails[i][key] = "-1";
                }
            }
        }
    },
    getZones: function () {
        $.post("loadZones", {}, function (result) {
            if (result != undefined) {
                var html = "";
                html += "<option value='-1'>--Select Zone--</option>";
                for (var i = 0; i < result.length; i++) {
                    html += "<option value='" + result[i].zoneId + "'>" + result[i].zoneName + "</option>";
                    NewPro.zoneMaster[result[i].zoneId] = result[i].zoneName;
                }
                $("#zoneId").html(html);
                $("#zoneId").SumoSelect({csvDispCount: 3, search: true, searchText: 'Enter here.'});
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
    getWard: function () {
        debugger;
        if ($("#zoneId").val() === "-1") {
        } else {
            $.post("getWards", {zone: $("#zoneId").val()}, function (result) {
                if (result != undefined) {
                    var html = "";
                    html += "<option value='-1'>--Select Ward--</option>";
                    for (var i = 0; i < result.length; i++) {
                        html += "<option value='" + result[i].ward + "'>" + result[i].displayName + "</option>";
                        NewPro.wardMaster[result[i].ward] = result[i].displayName;
                    }
                    NewPro.resetWard(html);
                }
            }, 'json').always(function () {
            });
        }
    }

};

$(document).ready(function () {
    NewPro.getRentableValues();
    NewPro.applyNumericaOnly();
    NewPro.getZones();
    NewPro.resetWard();
    NewPro.getWard();

});
$(document).on("change", "#zoneId", function () {
    NewPro.resetWard();
    NewPro.getWard();
});


