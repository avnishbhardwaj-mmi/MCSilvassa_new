/**
 * 
 */

$(document).ready(function() {

    $("#notice_main_menu").addClass("active");
    $.post("loadZones", {}, function(result) {
        if (result != undefined) {
            var html = "";
            html += "<option value='-1'>--Select Zone--</option>";
            for (var i = 0; i < result.length; i++) {
                html += "<option value='" + result[i].zoneId + "'>" + result[i].zoneName + "</option>";
            }
            $("#zoneId_enc").html(html);
        }

    }, 'json').always(function() {});

});


var NOTICE_GEN = {
    lastUpdateOn : null,
    generate: function() {

        var zone = $("#zoneId_enc").val();
        var prop = $("#propertyId_enc").val();
        if (prop !== null && zone === "-1" && prop.trim() === "") {
            alert("Kindly provide input.");
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
                callback: function(result) {
                    if (result) {
                        $("#zoneId").val($("#zoneId_enc").val());
                        $("#ward").val($("#ward_enc").val());
                        $("#propertyId").val("");
                        $("input[type=submit]").click();
                    }
                }

            });
        }
    },
    getWard: function() {

        if ($("#zoneId_enc option:selected").val() === "-1") {
            NOTICE_GEN.resetWard();
        } else {
            $.post("getWards", {zone: $("#zoneId_enc option:selected").val()}, function(result) {
                if (result != undefined) {
                    var html = "";
                    html += "<option value='-1'>--Select Ward--</option>";
                    for (var i = 0; i < result.length; i++) {
                        html += "<option value='" + result[i].ward + "'>" + result[i].displayName + "</option>";
                    }
                    NOTICE_GEN.resetWard(html);
                }
            }, 'json').always(function() {

            });
        }
    },
    resetWard: function(html) {
        if (html === undefined) {
            $("#ward_enc").html("<option value='-1'>--Select Ward--</option>");
        } else {
            $("#ward_enc").html(html);
        }
    }
}


$(document).on("change", "#zoneId_enc", function() {
    NOTICE_GEN.getWard();
});
