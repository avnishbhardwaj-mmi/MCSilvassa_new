/* 
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
var commonSearch ={
   
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
    
}

