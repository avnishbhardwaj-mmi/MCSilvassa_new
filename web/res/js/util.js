

var LOADER = {
    show: function () {

        if ($(".leaflet-control-loader").length < 1) {
            $("body").append('<div class="leaflet-control-loader" ' +
                    'style="background : url(res/img/leaflet-loader.gif) center center no-repeat rgba(255,255,255,0.8)" > ' +
                    '<p style="text-align: center; font-weight: bold;" >Please wait...</p> ' +
                    '</div>' +
                    '<div class="coverScreen" ></div>');
        }
        $(".leaflet-control-loader").show();
        $(".coverScreen").show();
    },
    hide: function () {
        $(".leaflet-control-loader").hide();
        $(".coverScreen").hide();
    }
}

var VALIDATOR = {
    checkIfNumber: function (value) {
        var numArray = ['0', '1', '2', '3', '4', '5', '6', '7', '8', '9'];
        var containsNumber = false;
        $.each(value.split(''), function () {
            if (numArray.indexOf($(this)[0]) > -1) {
                containsNumber = true;
                return false;
            }
        });
        return containsNumber;
    },
    checkIfAlphabet: function (value) {
        var charArray = ['A', 'B', 'C', 'D', 'E', 'F', 'G', 'H', 'I', 'J', 'K', 'L', 'M', 'N', 'O', 'P',
            'Q', 'R', 'S', 'T', 'U', 'V', 'W', 'X', 'Y', 'Z', 'a', 'b', 'c', 'd', 'e', 'f',
            'g', 'h', 'i', 'j', 'k', 'l', 'm', 'n', 'o', 'p', 'q', 'r', 's', 't', 'u', 'v',
            'w', 'x', 'y', 'z'];
        var containsChar = false;
        $.each(value.split(''), function () {
            if (charArray.indexOf($(this)[0]) > -1) {
                containsChar = true;
                return false;
            }
        });
        return containsChar;
    }

}


$("#expo").submit(function () {
    LOADER.show();
    checkCookie();
});

function checkCookie() {
    var cookieVal = getCookie("windowId");
    if (cookieVal !== 'undefined' && cookieVal != null && cookieVal === $("input[name='windowId']").val()) {
        LOADER.hide();
    } else {
        setTimeout("checkCookie();", 1000);
    }
}

function getCookie(name) {
    var value = "; " + document.cookie;
    var parts = value.split("; " + name + "=");
    if (parts.length == 2)
        return parts.pop().split(";").shift();
}

$(document).on("change", "#export_All", function () {
    if ($(this).prop("checked")) {
        $(this).closest("ul").find("input[type=checkbox]").each(function () {
            $(this).prop("checked", true);
        });
    } else {
        $(this).closest("ul").find("input[type=checkbox]").each(function () {
            $(this).prop("checked", false);
        });
    }
});


var MASTERCOLUMN = {
    // Attribute Header Text
    attrHead: {"propertyId": "Property Id", "ownerName": "Owner Name", "occupierName": "Occupier Name",
        "relation": "Relation With Owner", "address": "Address", "zone": "Zone", "ward": "Ward", "taxGenerated": "Tax Generated",
        "taxAmount": "Tax Amount", "noticeGenerated": "Notice Generated", "assessmentYear": "Assessment Year",
        "taxNo": "Tax No", "noticeNo": "Notice No", "generatedBy": "Generated By", "generatedOn": "Generated On",
        "amountDemand": "Amount Demand (INR)", "amountPaid": "Amount Paid (INR)"},
    attrObjectionHead: {"propertyId": "Property Id", "ownerName": "Owner Name", "occupierName": "Occupier Name",
        "relation": "Relation With Owner", "address": "Address", "zone": "Zone", "ward": "Ward", "taxGenerated": "Tax Generated",
        "taxAmount": "Tax Amount", "noticeGenerated": "Notice Generated", "assessmentYear": "Assessment Year",
        "taxNo": "Tax No", "noticeNo": "Notice No", "generatedBy": "Generated By", "generatedOn": "Generated On",
        "objectionNo": "Objection No", "objectionStatus": "Objection Status", "objectionAppliedBy": "Objection Applied By",
        "objectionRaisedOn": "Objection Raised On"},
    // Attribute in model
    attrInBean: {"propertyId": "propertyUniqueId", "ownerName": "propertyOwner", "occupierName": "propertyOccupierName",
        "relation": "propertyRelationOwner", "address": "completeAddress", "zone": "zoneId", "ward": "ward", "taxGenerated": "taxGenerated",
        "taxAmount": "payableAmount", "noticeGenerated": "noticeGenerated", "assessmentYear": "financialYear",
        "taxNo": "taxNo", "noticeNo": "noticeNo", "generatedBy": "generatedBy", "generatedOn": "generatedOn",
        "objectionNo": "objectionNo", "objectionStatus": "objectionStatus", "objectionAppliedBy": "objectionAppliedBy",
        "objectionRaisedOn": "objectionRaisedOn", "amountDemand": "amountDemand", "amountPaid": "amountPaid"},
    // Attribute
    attrPROP: ["propertyId", "ownerName", "occupierName", "relation", "address", "zone", "ward", "taxGenerated", "taxAmount", "noticeGenerated"],
    attrTAXGEN: ["propertyId", "ownerName", "occupierName", "relation", "address", "zone", "ward", "assessmentYear", "taxNo", "taxAmount", "noticeGenerated", "generatedBy", "generatedOn"],
    attrOBJ: ["propertyId", "ownerName", "occupierName", "relation", "address", "zone", "ward"],
    attrNOTICEGEN: ["propertyId", "ownerName", "occupierName", "relation", "address", "zone", "ward", "noticeNo", "taxNo", "assessmentYear"],
    attrPaymentApproveReport: ["propertyId", "ownerName", "occupierName", "relation", "address", "zone", "ward", "amountDemand", "amountPaid", "taxNo", "assessmentYear"],
    attrTAXCOLLECT: ["propertyId", "ownerName", "occupierName", "relation", "address", "zone", "ward", "taxGenerated", "taxAmount", "noticeGenerated"],
    attrOBJReport: ["propertyId", "ownerName", "occupierName", "relation", "address", "zone", "ward", "objectionNo", "objectionStatus", "objectionAppliedBy", "objectionRaisedOn"],
}



var UTIL = {
    clearBean: function (bean) {

        if (bean === undefined) {

        } else {
            var attrArr = Object.keys(bean);
            for (var a in attrArr) {
                var attr = attrArr[a];
                bean[attr] = (bean[attr] === undefined || bean[attr] === null) ? "" : bean[attr];
            }
        }

        return bean;
    }
}