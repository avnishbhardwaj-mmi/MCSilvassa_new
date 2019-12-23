

var DATEPICKER = {
    today: null,
    init: function() {
        $("#rep_fromDate").datepicker({
            onSelect: function(selected) {
                var dt = new Date(selected);
                dt.setDate(dt.getDate() + 1);
                $("#rep_toDate").datepicker("option", "minDate", dt);
            }});
        $("#rep_toDate").datepicker({onSelect: function(selected) {
                var dt = new Date(selected);
                dt.setDate(dt.getDate() - 1);
                $("#rep_fromDate").datepicker("option", "maxDate", dt);
            }});
        DATEPICKER.setMaxDate($("#rep_fromDate"), this.today);
        DATEPICKER.setMaxDate($("#rep_toDate"), this.today);
    },
    setMinDate: function($element, date) {
        $element.datepicker("option", "minDate", date);
    },
    setMaxDate: function($element, date) {
        $element.datepicker("option", "maxDate", date);
    },
    validate: function(fromDay, from_month, from_year, toDay, to_month, to_year) {

        var from_date = new Date();
        from_date.setDate(fromDay);
        from_date.setMonth(parseInt(from_month) - 1);
        from_date.setYear(from_year);

        var to_date = new Date();
        to_date.setDate(toDay);
        to_date.setMonth(parseInt(to_month) - 1);
        to_date.setYear(to_year);

        return (from_date.getTime() <= to_date.getTime());

    }

}


$(document).ready(function() {
    $("#report_main_menu").addClass("active");
//    $("#zone").select2();
//    $("#rep_prop_id").select2();
//    $("#rep_occupier").select2();
//    $("#rep_owner").select2();

    $.post("loadZones", {}, function(result) {
        if (result != undefined) {
            var html = "";
            html += "<option value='-1'>--Select Zone--</option>";
            for (var i = 0; i < result.length; i++) {
                html += "<option value='" + result[i].zoneId + "'>" + result[i].zoneName + "</option>";
                REPORT.zoneMaster[result[i].zoneId] = result[i].zoneName;
            }
            $("#zone").html(html);
        }
    }, 'json').always(function() {
    });

    DATEPICKER.init();

});



var REPORT = {
    zoneMaster: {},
    getPropertyDetails: function() {
        var zone_id = $("#zone").val();

        $.post("getSearchCriteria", {zone_id: zone_id},
        function(result) {
            if (result != undefined) {
                var pr = result.prop.propIdArr;
                var html_prop = "";
                html_prop += "<option value='-1'>--Select Property ID--</option>";
                for (var key in pr) {
                    html_prop += "<option value='" + key + "'>" + pr[key].property_unique_id + "</option>";
                }

                $("#rep_prop_id").html("");
                $("#rep_prop_id").html(html_prop);

            }
        });

    },
    filterByPropertyDetailsWindow : function(){
        
    }
    
}