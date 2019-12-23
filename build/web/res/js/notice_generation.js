/**
 * 
 */

$(document).ready(function () {

    $("#notice_main_menu").addClass("active");
    $.post("loadZones", {}, function (result) {
        if (result != undefined) {
            var html = "";
            html += "<option value='-1'>--Select Zone--</option>";
            for (var i = 0; i < result.length; i++) {
                html += "<option value='" + result[i].zoneId + "'>" + result[i].zoneName + "</option>";
            }
            $("#zoneId_enc").html(html);
        }

    }, 'json').always(function () {
    });
    NOTICE_GEN.getWard();
});


var NOTICE_GEN = {
    lastUpdateOn: null,
    privatenotice: function () {

        $("#validateid").html('');
        var sel_zone = $("#zoneId_enc option:selected").val();
        var sel_ward = $("#ward_enc option:selected").val();
        var sel_propId = $("#propertyId_enc").val();
        sel_propId = sel_propId.toUpperCase();
        if (sel_zone == "-1" && sel_propId == "") {
            //alert("kindy provide appropriate value.");
            $("#validateid").html("kindly provide appropriate value.");
            return false;
        } else {
            //alert("else "+sel_zone+" "+sel_ward);
            $("#as_zone").val(sel_zone);
            $("#as_ward").val(sel_ward);
            //alert("ward "+$("#as_ward").val(sel_ward).val());
            $("#as_uniqueId").val(sel_propId);
            $("#notice").submit();

        }
        //alert("z"+sel_zone);
        //alert("w"+sel_ward);
        //alert("id"+sel_propId);
        /*var taxSel = {};
         taxSel.zoneid = sel_zone;
         taxSel.wardid = sel_ward;
         taxSel.prop_id = sel_propId;
         
         var jsonViewCrit = JSON.stringify(taxSel);
         
         alert("jsonViewCrit "+jsonViewCrit);*/

        /*var zone = $("#zoneId_enc").val();
         var prop = $("#propertyId_enc").val();
         var ward= $("#ward_enc").val();
         var taxSel = {};
         taxSel.zoneid = sel_zone;
         taxSel.wardid = sel_ward;
         taxSel.prop_id = sel_propId;
         var jsonViewCrit = JSON.stringify(taxSel);*/
        /*$.post("generatePrivateNotice", {
         taxObj: jsonViewCrit
         }, function (result) {
         
         if (result == undefined || result.status == "700") {
         alert("No result found");
         return false;
         }
         //TaxGeneration.showTaxDataTable(result);
         
         })*/



        /*if (prop !== null && zone === "-1" && prop.trim() === "") {
         alert("Kindly provide input.");
         } else {
         bootbox.confirm({
         message: "You are going to generate prviate notices. Once the process start, It can't stop or revert. Last updated on <b>" + NOTICE_GEN.lastUpdateOn + "<b>",
         buttons: {
         confirm: {
         label: 'Yes',
         className: 'btn-success'
         },
         cancel: {
         label: 'No',
         className: 'btn-danger'
         }
         },
         callback: function(result) {
         if (result) {
         $("#zoneId").val($("#zoneId_enc").val());
         $("#ward").val($("#ward_enc").val());
         $("#propertyId").val($("#propertyId_enc").val());
         $("input[type=submit]").click();
         }
         }
         
         });
         }*/
    },
    notice142: function () {

        //alert("142 notice");
        debugger;
//        var sel_zone = $("#zoneId_enc option:selected").val();
//        var sel_ward = $("#ward_enc option:selected").val();
//        var sel_propId = $("#propertyId_enc").val();
        var sel_zone = $("#zoneId_enc option:selected").val();
        var sel_ward = $("#ward").val();
        var sel_propId = $("#prop_id_input").val();
        var phone_no = $("#Phone_No").val();
        var locality = $("#locality").val();
        $("#validateid").html('');
        sel_propId = sel_propId.toUpperCase();
        if (sel_zone == "-1" && sel_propId == "") {
            //alert("kindy provide appropriate value.");
            $("#validateid").html("kindly provide appropriate value.");
            return false;
        } else {
            //alert("else "+sel_zone+" "+sel_ward);
            $("#as_zone1").val(sel_zone);
            $("#as_ward1").val(sel_ward);
            //alert("ward "+$("#as_ward").val(sel_ward).val());
            $("#as_uniqueId1").val(sel_propId);
            $("#as_phone_no1").val(phone_no);
            $("#as_locality1").val(locality);
            //alert($("#as_zone1").val());
            //alert($("#as_ward1").val());
            $("#notice142Form").submit();

        }
        //alert("z"+sel_zone);
        //alert("w"+sel_ward);
        //alert("id"+sel_propId);
        /*var taxSel = {};
         taxSel.zoneid = sel_zone;
         taxSel.wardid = sel_ward;
         taxSel.prop_id = sel_propId;
         
         var jsonViewCrit = JSON.stringify(taxSel);
         
         alert("jsonViewCrit "+jsonViewCrit);*/

        /*var zone = $("#zoneId_enc").val();
         var prop = $("#propertyId_enc").val();
         var ward= $("#ward_enc").val();
         var taxSel = {};
         taxSel.zoneid = sel_zone;
         taxSel.wardid = sel_ward;
         taxSel.prop_id = sel_propId;
         var jsonViewCrit = JSON.stringify(taxSel);*/
        /*$.post("generatePrivateNotice", {
         taxObj: jsonViewCrit
         }, function (result) {
         
         if (result == undefined || result.status == "700") {
         alert("No result found");
         return false;
         }
         //TaxGeneration.showTaxDataTable(result);
         
         })*/



        /*if (prop !== null && zone === "-1" && prop.trim() === "") {
         alert("Kindly provide input.");
         } else {
         bootbox.confirm({
         message: "You are going to generate prviate notices. Once the process start, It can't stop or revert. Last updated on <b>" + NOTICE_GEN.lastUpdateOn + "<b>",
         buttons: {
         confirm: {
         label: 'Yes',
         className: 'btn-success'
         },
         cancel: {
         label: 'No',
         className: 'btn-danger'
         }
         },
         callback: function(result) {
         if (result) {
         $("#zoneId").val($("#zoneId_enc").val());
         $("#ward").val($("#ward_enc").val());
         $("#propertyId").val($("#propertyId_enc").val());
         $("input[type=submit]").click();
         }
         }
         
         });
         }*/
    },
    notice143: function () {


        $(".sbu-heading").addClass('d-none');
        $("#validateid").html('');
        var sel_zone = $("#zoneId_enc option:selected").val();
        var sel_ward = $("#ward").val();
        var sel_propId = $("#prop_id_input").val();
        var phone_no = $("#Phone_No").val();
        var locality = $("#locality").val();
        sel_propId = sel_propId.toUpperCase();
        var owner_Id = $("#ownerid").val();
        if (sel_ward == "" && sel_propId == "" && phone_no == "" && locality == "" && owner_Id == "") {
            //alert("kindy provide appropriate value.");
            $("#validateid").html("kindly provide appropriate value.");
            return false;
        } else {
            //alert("else "+sel_zone+" "+sel_ward);
//            $("#as_zone2").val(sel_zone);
//            $("#as_ward2").val(sel_ward);
            //alert("ward "+$("#as_ward").val(sel_ward).val());
//            $("#as_uniqueId2").val(sel_propId);


            $("#as_zone2").val(sel_zone);
            $("#as_ward2").val(sel_ward);
            //alert("ward "+$("#as_ward").val(sel_ward).val());
            $("#as_uniqueId2").val(sel_propId);
            $("#as_phone_no2").val(phone_no);
            $("#as_locality2").val(locality);
            $("#as_owner_Id2").val(owner_Id);

            $("#notice143Form").submit();

        }
        //alert("z"+sel_zone);
        //alert("w"+sel_ward);
        //alert("id"+sel_propId);
        /*var taxSel = {};
         taxSel.zoneid = sel_zone;
         taxSel.wardid = sel_ward;
         taxSel.prop_id = sel_propId;
         
         var jsonViewCrit = JSON.stringify(taxSel);
         
         alert("jsonViewCrit "+jsonViewCrit);*/

        /*var zone = $("#zoneId_enc").val();
         var prop = $("#propertyId_enc").val();
         var ward= $("#ward_enc").val();
         var taxSel = {};
         taxSel.zoneid = sel_zone;
         taxSel.wardid = sel_ward;
         taxSel.prop_id = sel_propId;
         var jsonViewCrit = JSON.stringify(taxSel);*/
        /*$.post("generatePrivateNotice", {
         taxObj: jsonViewCrit
         }, function (result) {
         
         if (result == undefined || result.status == "700") {
         alert("No result found");
         return false;
         }
         //TaxGeneration.showTaxDataTable(result);
         
         })*/



        /*if (prop !== null && zone === "-1" && prop.trim() === "") {
         alert("Kindly provide input.");
         } else {
         bootbox.confirm({
         message: "You are going to generate prviate notices. Once the process start, It can't stop or revert. Last updated on <b>" + NOTICE_GEN.lastUpdateOn + "<b>",
         buttons: {
         confirm: {
         label: 'Yes',
         className: 'btn-success'
         },
         cancel: {
         label: 'No',
         className: 'btn-danger'
         }
         },
         callback: function(result) {
         if (result) {
         $("#zoneId").val($("#zoneId_enc").val());
         $("#ward").val($("#ward_enc").val());
         $("#propertyId").val($("#propertyId_enc").val());
         $("input[type=submit]").click();
         }
         }
         
         });
         }*/
    },
    notice142: function () {

        //alert("142 notice");
//        var sel_zone = $("#zoneId_enc option:selected").val();
//        var sel_ward = $("#ward_enc option:selected").val();
//        var sel_propId = $("#propertyId_enc").val();
        $(".sbu-heading").addClass('d-none');
        $("#validateid").html('');
        var sel_zone = $("#zoneId_enc option:selected").val();
        var sel_ward = $("#ward").val();
        var sel_propId = $("#prop_id_input").val();
        var phone_no = $("#Phone_No").val();
        var locality = $("#locality").val();
        sel_propId = sel_propId.toUpperCase();
        var owner_Id = $("#ownerid").val();
        if (sel_ward == "" && sel_propId == "" && phone_no == "" && locality == "" && owner_Id == "") {
            //alert("kindy provide appropriate value.");
            $("#validateid").html("kindly provide appropriate value.");
            return false;
        } else {
            //alert("else "+sel_zone+" "+sel_ward);
            $("#as_zone1").val(sel_zone);
            $("#as_ward1").val(sel_ward);
            //alert("ward "+$("#as_ward").val(sel_ward).val());
            $("#as_uniqueId1").val(sel_propId);
            $("#as_phone_no1").val(phone_no);
            $("#as_locality1").val(locality);
            $("#as_owner_Id1").val(owner_Id);
            //alert($("#as_zone1").val());
            //alert($("#as_ward1").val());
            $("#notice142Form").submit();

        }
        //alert("z"+sel_zone);
        //alert("w"+sel_ward);
        //alert("id"+sel_propId);
        /*var taxSel = {};
         taxSel.zoneid = sel_zone;
         taxSel.wardid = sel_ward;
         taxSel.prop_id = sel_propId;
         
         var jsonViewCrit = JSON.stringify(taxSel);
         
         alert("jsonViewCrit "+jsonViewCrit);*/

        /*var zone = $("#zoneId_enc").val();
         var prop = $("#propertyId_enc").val();
         var ward= $("#ward_enc").val();
         var taxSel = {};
         taxSel.zoneid = sel_zone;
         taxSel.wardid = sel_ward;
         taxSel.prop_id = sel_propId;
         var jsonViewCrit = JSON.stringify(taxSel);*/
        /*$.post("generatePrivateNotice", {
         taxObj: jsonViewCrit
         }, function (result) {
         
         if (result == undefined || result.status == "700") {
         alert("No result found");
         return false;
         }
         //TaxGeneration.showTaxDataTable(result);
         
         })*/



        /*if (prop !== null && zone === "-1" && prop.trim() === "") {
         alert("Kindly provide input.");
         } else {
         bootbox.confirm({
         message: "You are going to generate prviate notices. Once the process start, It can't stop or revert. Last updated on <b>" + NOTICE_GEN.lastUpdateOn + "<b>",
         buttons: {
         confirm: {
         label: 'Yes',
         className: 'btn-success'
         },
         cancel: {
         label: 'No',
         className: 'btn-danger'
         }
         },
         callback: function(result) {
         if (result) {
         $("#zoneId").val($("#zoneId_enc").val());
         $("#ward").val($("#ward_enc").val());
         $("#propertyId").val($("#propertyId_enc").val());
         $("input[type=submit]").click();
         }
         }
         
         });
         }*/
    },
            pvtnoticeNumber: function () {

                $("#validateid").html('');
                var sel_zone = $("#zoneId_enc option:selected").val();
                var sel_ward = $("#ward_enc option:selected").val();
                var sel_propId = $("#propertyId_enc").val();
                sel_propId = sel_propId.toUpperCase();
                if (sel_zone == "-1" && sel_propId == "") {
                    //alert("kindy provide appropriate value.");
                    $("#validateid").html("kindly provide appropriate value.");
                    return false;
                } else {
                    //alert("else "+sel_zone+" "+sel_ward);
                    $("#as_zone3").val(sel_zone);
                    $("#as_ward3").val(sel_ward);
                    //alert("ward "+$("#as_ward").val(sel_ward).val());
                    $("#as_uniqueId3").val(sel_propId);
                    $("#noticeNumber").submit();

                }
                //alert("z"+sel_zone);
                //alert("w"+sel_ward);
                //alert("id"+sel_propId);
                /*var taxSel = {};
                 taxSel.zoneid = sel_zone;
                 taxSel.wardid = sel_ward;
                 taxSel.prop_id = sel_propId;
                 
                 var jsonViewCrit = JSON.stringify(taxSel);
                 
                 alert("jsonViewCrit "+jsonViewCrit);*/

                /*var zone = $("#zoneId_enc").val();
                 var prop = $("#propertyId_enc").val();
                 var ward= $("#ward_enc").val();
                 var taxSel = {};
                 taxSel.zoneid = sel_zone;
                 taxSel.wardid = sel_ward;
                 taxSel.prop_id = sel_propId;
                 var jsonViewCrit = JSON.stringify(taxSel);*/
                /*$.post("generatePrivateNotice", {
                 taxObj: jsonViewCrit
                 }, function (result) {
                 
                 if (result == undefined || result.status == "700") {
                 alert("No result found");
                 return false;
                 }
                 //TaxGeneration.showTaxDataTable(result);
                 
                 })*/



                /*if (prop !== null && zone === "-1" && prop.trim() === "") {
                 alert("Kindly provide input.");
                 } else {
                 bootbox.confirm({
                 message: "You are going to generate prviate notices. Once the process start, It can't stop or revert. Last updated on <b>" + NOTICE_GEN.lastUpdateOn + "<b>",
                 buttons: {
                 confirm: {
                 label: 'Yes',
                 className: 'btn-success'
                 },
                 cancel: {
                 label: 'No',
                 className: 'btn-danger'
                 }
                 },
                 callback: function(result) {
                 if (result) {
                 $("#zoneId").val($("#zoneId_enc").val());
                 $("#ward").val($("#ward_enc").val());
                 $("#propertyId").val($("#propertyId_enc").val());
                 $("input[type=submit]").click();
                 }
                 }
                 
                 });
                 }*/
            },
    billNumber: function () {
        //alert("billNumber");
        $(".sbu-heading").addClass('d-none');
        $("#validateid").html('');
        var sel_zone = $("#zoneId_enc option:selected").val();
        var sel_ward = $("#ward").val();
        var sel_propId = $("#prop_id_input").val();
        var phone_no = $("#Phone_No").val();
        var locality = $("#locality").val();
        sel_propId = sel_propId.toUpperCase();
        var owner_Id = $("#ownerid").val();
        if (sel_ward == "" && sel_propId == "" && phone_no == "" && locality == "" && owner_Id == "") {
            //alert("kindy provide appropriate value.");
            $("#validateid").html("kindly provide appropriate value.");
            return false;
        } else {
            //alert("else "+sel_zone+" "+sel_ward);
            $("#as_zone4").val(sel_zone);
            $("#as_ward4").val(sel_ward);
            //alert("ward "+$("#as_ward").val(sel_ward).val());
            $("#as_uniqueId4").val(sel_propId);
            $("#as_phone_no4").val(phone_no);
            $("#as_locality4").val(locality);
            $("#as_owner_Id4").val(owner_Id);
            $("#billNumber").submit();

        }
    },
    billPdf: function () {
        //alert("billNumber");
        $(".sbu-heading").addClass('d-none');
        $("#validateid").html('');
        var sel_zone = $("#zoneId_enc option:selected").val();
        var sel_ward = $("#ward").val();
        var sel_propId = $("#prop_id_input").val();
        var phone_no = $("#Phone_No").val();
        var locality = $("#locality").val();
        sel_propId = sel_propId.toUpperCase();
        var owner_Id = $("#ownerid").val();
        if (sel_ward == "" && sel_propId == "" && phone_no == "" && locality == "" && owner_Id == "") {
            //alert("kindy provide appropriate value.");
            $("#validateid").html("kindly provide appropriate value.");
            return false;
        } else {
            //alert("else "+sel_zone+" "+sel_ward);
            $("#as_zone5").val(sel_zone);
            $("#as_ward5").val(sel_ward);
            //alert("ward "+$("#as_ward").val(sel_ward).val());
            $("#as_uniqueId5").val(sel_propId);
            $("#as_phone_no5").val(phone_no);
            $("#as_locality5").val(locality);
            $("#as_owner_Id5").val(owner_Id);
            $("#billPdf").submit();

        }
    },
    generate: function () {
        $("#validateid").html('');
        var zone = $("#zoneId_enc").val();
        var prop = $("#propertyId_enc").val();
        if (prop !== null && zone === "-1" && prop.trim() === "") {
            $("#validateid").html("kindly provide appropriate value.");
            //alert("kindly provide appropriate value.");
        } else {
            bootbox.confirm({
                message: "You are going to generate TAX notices. Once the process start, It can't stop or revert. Last updated on <b>" + NOTICE_GEN.lastUpdateOn + "<b>",
                buttons: {
                    confirm: {
                        label: 'Yes',
                        className: 'btn-success'
                    },
                    cancel: {
                        label: 'No',
                        className: 'btn-danger'
                    }
                },
                callback: function (result) {
                    //console.log(result);
                    if (result) {
                        $("#zoneId").val($("#zoneId_enc").val());
                        $("#ward").val($("#ward_enc").val());
                        $("#propertyId").val($("#propertyId_enc").val());
                        $("input[id='testtt']").click();
                    }
                }

            });
        }
    },
    getWard: function () {

//        if ($("#zoneId_enc option:selected").val() === "-1") {
//            NOTICE_GEN.resetWard();
//        } else {
        $.post("getWards", {zone: $("#zoneId_enc option:selected").val()}, function (result) {
            if (result != undefined) {
                var html = "";
                html += "<option value='-1'>--Select Ward--</option>";
                for (var i = 0; i < result.length; i++) {
                    html += "<option value='" + result[i].ward + "'>" + result[i].displayName + "</option>";
                }
                NOTICE_GEN.resetWard(html);
            }
        }, 'json').always(function () {

        });
//        }
    },
    resetWard: function (html) {
        if (html === undefined) {
            $("#ward_enc").html("<option value='-1'>--Select Ward--</option>");
        } else {
            $("#ward_enc").html(html);
        }
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
}

//$(document).on("change", "#zoneId_enc", function() {
//    NOTICE_GEN.getWard();
//});


$(document).on("change", "#ward_enc", function () {
    $("#zoneId_enc").val(zoneWardMaster[$("#ward_enc").val()]);
});
