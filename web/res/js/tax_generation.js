

$(document).ready(function() {
    $("#tax_main_menu").addClass("active");
    $.post("loadZones", {}, function(result) {
        if (result != undefined) {
            var html = "";
            html += "<option value='-1'>--Select Zone--</option>";
            for (var i = 0; i < result.length; i++) {
                html += "<option value='" + result[i].zoneId + "'>" + result[i].zoneName + "</option>";
            }
            $("#zoneId_enc").html(html);
        }
    }, 'json').always(function() {
    });
    TAX_GEN.getWard();
});


var TAX_GEN = {
    lastUpdateOn : null,
    generate: function() {

        var zone = $("#zoneId_enc").val();
        var prop = $("#propertyId_enc").val();
        $("#validateid").html('');
        if (zone === "-1" && prop.trim() === "") {
            $("#validateid").html("Please select at least one filter");  
           // alert("Kindly provide input.");
        } else {
            bootbox.confirm({
                message: "You are going to generate TAX. Once the process start, It can't stop or revert. Last updated on <b>" + TAX_GEN.lastUpdateOn + "<b>",
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
                        $("#zoneId").val($("#zoneId_enc option:selected").val());
                        $("#ward").val($("#prop_tax_ward option:selected").val());
                        $("#propertyId").val($("#propertyId_enc").val());
                        $("input[type=submit]").click();
                    }
                }

            });
        }
    },
    getWard: function() {

//        if ($("#zoneId_enc option:selected").val() === "-1") {
//            TAX_GEN.resetWard();
//        } else {
            $.post("getWards", {zone: $("#zoneId_enc option:selected").val()}, function(result) {
                if (result != undefined) {
                    var html = "";
                    html += "<option value='-1'>--Select Ward--</option>";
                    for (var i = 0; i < result.length; i++) {
                        html += "<option value='" + result[i].ward + "'>" + result[i].displayName + "</option>";
                    }
                    TAX_GEN.resetWard(html);
                }
            }, 'json').always(function() {

            });
//        }
    },
    resetWard: function(html) {
        if (html === undefined) {
            $("#prop_tax_ward").html("<option value='-1'>--Select Ward--</option>");
        } else {
            $("#prop_tax_ward").html(html);
        }
    }
}

//$(document).on("change", "#zoneId_enc", function() {
//    TAX_GEN.getWard();
//});

$(document).on("change", "#prop_tax_ward", function () {
    $("#zoneId_enc").val(zoneWardMaster[$(this).val()]);
});
