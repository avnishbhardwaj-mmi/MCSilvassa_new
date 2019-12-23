/* 
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

var LocateMap = {
    taxDetailMap: {},
    propertyMap: {},
    wardBuilder: new Set(),
    localityBuilder: new Set(),
    subLocalityBuilder: new Set(),
    wardGeomLayer: new Array(),
    locGeomLayer: new Array(),
    subLocGeomLayer: new Array(),
    propMarker: new Array(),
    geomtype: "",
    wFlag: false,
    lFlag: false,
    sLFlag: false,
    wgeomData: new Array(),
    lgeomData: new Array(),
    sLgeomData: new Array(),
     count : 0,
    geomtype : "",
            searchPropertyDetails: function () {

                //var zone_id = $("#zone option:selected").val();
                $("#validateid").html('');
                var zone_id = "";
                var prop_id_input = $("#prop_id_input").val();
                var ward = $("#ward").val();
                var property_id = "";
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
                if (prop_id_input == "" && ward == "" && occ == "" && occ == "" && owner_id == "" && locality == "" && aadhar_num == "" && propertyCategory == "" && Phone_no == "" && Locality == "") {
                    // alert("Please select atleast one filter");
                    $("#validateid").html("Please select at least one filter");
                } else {

                    LOADER.show();
                    $.post("searchPropertyDetails",
                            {
                                zone_id: zone_id,
                                ward: ward,
                                property_id: property_id,
                                owner_name: owner_id,
                                occ_name: occ,
                                locality: locality,
                                aadhar_num: aadhar_num,
                                category: propertyCategory,
                                prop_id_input: prop_id_input,
                                phone_no: Phone_no,
                                sub_locality: Locality,
                                houseNo: "",
                                async: false,
                            }, function (data) {

                        var propArr = data.propertyArr;
                        if (propArr.length != 0) {
                            $("#validateid").html('');
                            LocateMap.preserveTAXDetails(data.taxDetailArr);
                            LocateMap.preservePropertydetails(data.propertyArr);
                            LocateMap.wardBuilder.clear();
                            LocateMap.localityBuilder.clear();
                            LocateMap.subLocalityBuilder.clear();

                            if (propArr !== undefined && propArr != null) {
                                LocateMap.openSearchResults();
                                var imageicon = "res/img/11_large.png";
                                
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
                                    prop.ward = (prop.ward === undefined || prop.ward === null) ? "" : prop.ward;
                                    prop.propertySublocality = (prop.propertySublocality === undefined || prop.propertySublocality === null) ? "" : prop.propertySublocality;
                                    prop.propertyLandmark = (prop.propertyLandmark === undefined || prop.propertyLandmark === null) ? "" : prop.propertyLandmark;
                                    prop.completeAddress = (prop.completeAddress === undefined || prop.completeAddress === null) ? "" : prop.completeAddress;
                                    prop.latitude = (prop.propertyLatitude === undefined || prop.propertyLatitude === null) ? "" : prop.propertyLatitude;
                                    prop.propertyLongitude = (prop.propertyLongitude === undefined || prop.propertyLongitude === null) ? "" : prop.propertyLongitude;
                                    prop.propertyLocality = (prop.propertyLocality === undefined || prop.propertyLocality === null) ? "" : prop.propertyLocality;
                                    LocateMap.wardBuilder.add(prop.ward);
                                    LocateMap.localityBuilder.add(prop.propertyLocality);
                                    LocateMap.subLocalityBuilder.add(prop.propertySublocality);
                                    var infohtml = "";
                                    //  infohtml += "<div class='popup_area'><div class='headerSection'>Property Details</div><div class='content_area_sec'><ul>";
                                    infohtml += "<div class=popup_area><div class=headerSection>" + prop.propertyOwner + "(" + prop.propertyUniqueId + ")</div><div class=content_area_sec><ul>";
                                    infohtml += "<li> <b>Address:</b> " + prop.completeAddress + "</li>";
                                  //  infohtml += "<li> <b>Unique id:</b> " + prop.propertyUniqueId + "</li>";
                                  //  infohtml += "<li> <b>Owner Name:</b> " + prop.propertyOwner + "</li>";
                                    infohtml += "</ul></div></div>";
                                    var mrk = MapHandler.plotMarker(
                                            prop.latitude, prop.propertyLongitude,
                                            imageicon, infohtml);
                                    MapHandler.markers.push(mrk);
                                LocateMap.count++;
                                }
                            }
                            //MapHandler.fitBoundOnMap(MapHandler.markers);
                        } else {
                            $("#validateid").html("No results found");
                            // alert("No results found");
                            return false;
                        }

                    }, 'json').always(function () {
                        setTimeout(function () {
                            MapHandler.map.invalidateSize();
                            var featureGroup = L.featureGroup(MapHandler.markers);
                            MapHandler.map.fitBounds(featureGroup.getBounds());
                            $(".headerSectionMsg").html("Showing "+ LocateMap.count +" property of ward "+ Array.from(LocateMap.wardBuilder).toString() +".");
                        }, 500);
                        LOADER.hide();
                    });
                    return true;
                }

            },
    preserveTAXDetails: function (detailArr) {
        LocateMap.taxDetailMap = {};
        if (detailArr === undefined || detailArr === null) {

        } else {
            for (var dt in detailArr) {
                var det = detailArr[dt];
                if (det != null) {
                    LocateMap.taxDetailMap[det.propertyId] = det;
                }
            }
        }

    },
    preservePropertydetails: function (detailArr) {
        LocateMap.propertyMap = {};
        if (detailArr === undefined || detailArr === null) {

        } else {
            for (var dt in detailArr) {
                var det = detailArr[dt];
                if (det != null) {
                    LocateMap.propertyMap[det.propertyUniqueId] = det;
                }
            }
        }

    },
    openSearchResults: function () {
        $("#searchPropertySection").addClass("hidden");
        $("#mapSection").removeClass("hidden");
        $("#validateid").html('');
        $("#ward").val('');
        $("#prop_id_input").val('');
        $("#ownerid").val('');
        $("#occ_name").val('');
        $("#Phone_No").val('');
        $("#Easy_City_Code").val('');
        $("#locality").val('');
        $("#Locality").val('');
        $("#src_aadhar_no").val('');
        $("#category").val('');
    },
    openSearchWindow: function () {
        MapHandler.removeMarkers(MapHandler.markers);
        if (LocateMap.wardGeomLayer.length > 0) {
            MapHandler.removeMarkers(LocateMap.wardGeomLayer);
            LocateMap.wFlag = false;
        }
        if (LocateMap.locGeomLayer.length > 0) {
            MapHandler.removeMarkers(LocateMap.locGeomLayer);
            LocateMap.lFlag = false;
        }
        if (LocateMap.subLocGeomLayer.length > 0) {
            MapHandler.removeMarkers(LocateMap.subLocGeomLayer);
            LocateMap.sLFlag = false;
        }
        $("#locl_ward").prop('checked', false);
        $("#locl_Locality").prop('checked', false);
        $("#locl_subLocality").prop('checked', false);

        $("#mapSection").addClass("hidden");
        $("#searchPropertySection").removeClass("hidden");
        $("#validateid").html('');
    },
    plotWardGeom: function (type) {
        //  alert("adgja");
        LocateMap.geomtype = $(type).attr("name");
        var dataList;
        if (LocateMap.geomtype == "ward_check") {
            var upp = Array.from(LocateMap.wardBuilder).toString().replace(/,/g, "','");
            dataList = upp.toUpperCase();
        } else if (LocateMap.geomtype == "locality_check") {
            var upp = Array.from(LocateMap.localityBuilder).toString().replace(/,/g, "','");
            dataList = upp.toUpperCase();
        } else if (LocateMap.geomtype == "sub_check") {
            var upp = Array.from(LocateMap.subLocalityBuilder).toString().replace(/,/g, "','");
            dataList = upp.toUpperCase();
        }
        if ($(type).prop("checked") == true) {
            if ((LocateMap.geomtype == "ward_check" && LocateMap.wFlag != true) || (LocateMap.geomtype == "locality_check" && LocateMap.lFlag != true) || (LocateMap.geomtype == "sub_check" && LocateMap.sLFlag != true)) {
                if (dataList.length > 0) {
                    $.post("getGeomData",
                            {
                                type: LocateMap.geomtype,
                                datalist: dataList,
                            }, function (data) {
                        console.log("data");
                        for (var i = 0; i < data.length; i++) {
                            debugger;
                            var geomData = data[i].geom;
                            if (LocateMap.geomtype == "ward_check") {
                                var layer = MapHandler.plotGeom(JSON.parse(geomData), 'red', 'skyblue', 0.2);
                                LocateMap.wardGeomLayer.push(layer);
                                LocateMap.wFlag = true;
                            } else if (LocateMap.geomtype == "locality_check") {
                                var layer = MapHandler.plotGeom(JSON.parse(geomData), 'green', 'skyblue', 0.2);
                                LocateMap.locGeomLayer.push(layer);
                                LocateMap.lFlag = true;
                            } else if (LocateMap.geomtype == "sub_check") {
                                var layer = MapHandler.plotGeom(JSON.parse(geomData), 'blue', 'skyblue', 0.2);
                                LocateMap.subLocGeomLayer.push(layer);
                                LocateMap.sLFlag = true;
                            }
                        }
                    }, 'json').always(function () {
                        setTimeout(function () {
                            MapHandler.map.invalidateSize();
                            debugger;
                            if (LocateMap.geomtype == "ward_check") {
                                var featureGroup = L.featureGroup(LocateMap.wardGeomLayer);
                                MapHandler.map.fitBounds(featureGroup.getBounds());
                            } else if (LocateMap.geomtype == "locality_check") {
                                var featureGroup = L.featureGroup(LocateMap.locGeomLayer);
                                MapHandler.map.fitBounds(featureGroup.getBounds());
                            } else if (LocateMap.geomtype == "sub_check") {
                                var featureGroup = L.featureGroup(LocateMap.subLocGeomLayer);
                                MapHandler.map.fitBounds(featureGroup.getBounds());
                            }
                        }, 500);
                    });
                } else {
                    if (LocateMap.geomtype == "ward_check") {
                        $("#validateid").html('Ward Geometry not available.');
                    } else if (LocateMap.geomtype == "locality_check") {
                        $("#validateid").html('Locality Geometry not available.');
                    } else if (LocateMap.geomtype == "sub_check") {
                        $("#validateid").html('Sub Locality Geometry not available.');
                    }

                }
            } else {
                alert("already plotted");
                if (LocateMap.geomtype == "ward_check") {
                    var layer = MapHandler.plotGeom(LocateMap.wgeomData, 'red', 'skyblue', 0.2);
                    LocateMap.wardGeomLayer.push(layer);

                } else if (LocateMap.geomtype == "locality_check") {
                    var layer = MapHandler.plotGeom(LocateMap.lgeomData, 'green', 'skyblue', 0.2);
                    LocateMap.locGeomLayer.push(layer);
                } else if (LocateMap.geomtype == "sub_check") {
                    var layer = MapHandler.plotGeom(LocateMap.sLgeomData, 'blue', 'skyblue', 0.2);
                    LocateMap.subLocGeomLayer.push(layer);
                }
            }
        } else {
            if (LocateMap.geomtype == "ward_check") {
                MapHandler.removeMarkers(LocateMap.wardGeomLayer);
                LocateMap.wFlag = false;
                $("#validateid").html('');
            } else if (LocateMap.geomtype == "locality_check") {
                MapHandler.removeMarkers(LocateMap.locGeomLayer);
                LocateMap.lFlag = false;
                $("#validateid").html('');
            } else if (LocateMap.geomtype == "sub_check") {
                MapHandler.removeMarkers(LocateMap.subLocGeomLayer);
                LocateMap.sLFlag = false;
                $("#validateid").html('');
            }
        }

//        if ($(type).prop("checked") == true) {
//            if (LocateMap.wardBuilder.size > 0) {
//                $.post("getGeomData",
//                        {
//                            type: geomtype,
//                            datalist: dataList,
//                        }, function (data) {
//                    console.log("data");
//                    for (var i = 0; i < data.length; i++) {
//                        debugger;
//                        var geomData = data[i].geom;
//                        //var geometry_points = geomData.coordinates[0];
//                        //MapHandler.plotGeom(geomData, 'blue', 'skyblue', 1, 1, 1);
//                        var layer = MapHandler.plotGeom(JSON.parse(geomData), 'blue', 'skyblue', 0.2);
//                        MapHandler.geomArray.push(layer);
//                        console.log(layer);
//                        var s = "dd";
//                    }
//                }, 'json').always(function () {
//                    setTimeout(function () {
//                        MapHandler.map.invalidateSize();
//                        var featureGroup = L.featureGroup(MapHandler.geomArray);
//                        MapHandler.map.fitBounds(featureGroup.getBounds());
//                    }, 500);
//
//                });
//
//            } else {
//                $("#validateid").html('Property Not fall in any ward.');
//            }
//        } else {
//            //remove layer
//            MapHandler.removeMarkers(geomArray);
//        }
    }
}


