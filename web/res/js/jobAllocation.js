/* 
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

JobAllocation = {
    userMaster: {},
    zoneMaster: {},
    wardMaster: {},
    localityMaster: {},
    allocatedJobs: new Array(),
    addedJobs: new Array(),
    getUsers: function () {
        $.ajax({
            url: "getUsers",
            type: 'post',
            data: '',
            dataType: 'json',
            success: function (result) {
                if (result != null) {
                    var html = "";
                    html += "<option value='-1'>--Select User--</option>";
                    for (var i = 0; i < result.length; i++) {
                        JobAllocation.userMaster[i] = result[i];
                        html += "<option value='" + i + "'>" + result[i].username + " </option>";
                    }
                    $("#userId").html(html);
                    $("#userId").SumoSelect({csvDispCount: 3, search: true, searchText: 'Enter here.'});
                }
            },
            error: function (e) {
                console.log("ERROR: ", e);
            }
        });
    },
    getZones: function () {
        $.post("loadZones", {}, function (result) {
            if (result != undefined) {
                var html = "";
                html += "<option value='-1'>--Select Zone--</option>";
                for (var i = 0; i < result.length; i++) {
                    html += "<option value='" + result[i].zoneId + "'>" + result[i].zoneName + "</option>";
                    JobAllocation.zoneMaster[result[i].zoneId] = result[i].zoneName;
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
        if ($("#zoneId").val() === "-1") {
        } else {
            $.post("getWards", {zone: $("#zoneId").val()}, function (result) {
                if (result != undefined) {
                    var html = "";
                    html += "<option value='-1'>--Select Ward--</option>";
                    for (var i = 0; i < result.length; i++) {
                        html += "<option value='" + result[i].ward + "'>" + result[i].displayName + "</option>";
                        JobAllocation.wardMaster[result[i].ward] = result[i].displayName;
                    }
                    JobAllocation.resetWard(html);
                }
            }, 'json').always(function () {
            });
        }
    },
    allocateJob: function () {
        debugger;
        var userId = $("#userId").val();
        var userDeatil = JobAllocation.userMaster[userId];
        var jobAllocationDetails = JobAllocation.addedJobs;
        if (jobAllocationDetails.length > 0) {
            $.ajax({
                url: "jobAllocation",
                type: 'post',
                data: "jobAllocationDetails=" + JSON.stringify(jobAllocationDetails) + "&userDeatil=" + JSON.stringify(userDeatil),
                dataType: 'json',
                success: function (result) {
                    if (result.Status == 200) {
                        alert(result.Message);
                        JobAllocation.getAllocatedJobs();
                    } else {
                        alert(result.Message);
                    }
                },
                error: function (e) {
                    console.log("ERROR: ", e);
                }
            });
        } else {
            alert("No data to save");
        }
    },
    deAllocateJob: function (index) {
        JobAllocation.allocatedJobs[index].active = 'N';
    },
    deAllocateJobs: function () {
        var alljobs = JobAllocation.allocatedJobs;
        LOADER.show();
        $.ajax({
            url: "deAllocateJob",
            type: 'post',
            data: "jobDetail=" + JSON.stringify(alljobs),
            dataType: 'json',
            success: function (result) {
                debugger;
                LOADER.hide();
                if (result.Status == 200) {
                    alert(result.Message);
                    JobAllocation.getAllocatedJobs();
                } else {

                }
            },
            error: function (e) {
                LOADER.hide();
                console.log("ERROR: ", e);
            }
        });
    },
    validateInputs: function () {
        var userId = $("#userId").val();
        var zoneId = $("#zoneId").val();
        var ward = $("#ward").val();
        if (ward !== "-1" && zoneId !== "-1" && userId !== "-1") {
            return true;
        } else {
            return false;
        }
    },
    getAllocatedJobs: function () {
        LOADER.show();
        JobAllocation.allocatedJobs = new Array();
        debugger;
//        var userId = $("#userId").val();
//        var userDeatil = JobAllocation.userMaster[userId];
        $.ajax({
            url: "getAllocatedJobs",
            type: 'post',
            data: '',
            dataType: 'json',
            async: false,
            success: function (result) {
                debugger;
                if (result.Status == 200) {
                    var jobs = result.data;
                    for (var i = 0; i < jobs.length; i++) {
                        JobAllocation.allocatedJobs.push(jobs[i]);
                    }
                }
                JobAllocation.TableHtml('Alocate');
                JobAllocation.resetAddedTable();
            },
            error: function (e) {
                JobAllocation.TableHtml('Alocate');
                console.log("ERROR: ", e);
            }
        });
    },
    TableHtml: function (flag) {
        if (flag === 'Add') {
            var jobDetails = JobAllocation.addedJobs;
        } else if (flag === 'Alocate') {
            var jobDetails = JobAllocation.allocatedJobs;
        }
        var tab_html = "";
        tab_html += "<thead>"
                + "<tr >"
                + "<th>S.No.</th>"
                + "<th>To</th>";
        if (flag === 'Alocate') {
            tab_html += "<th>By</th>";
        }
        tab_html += "<th>Zone</th>";
        tab_html += "<th>Ward</th>";
        if (flag === 'Add') {
            tab_html += "<th>Remove</th>";
        } else if (flag === 'Alocate') {
            tab_html += "<th>Deallocate</th>";
        }
        tab_html += "</tr></thead>";
        tab_html += "<tbody>";
        if (jobDetails.length > 0) {
            for (var index = 0; index < jobDetails.length; index++) {
                tab_html += "<tr><td>" + (parseInt(index) + 1) + "</td>";// '+prop.propertyUniqueId+'
                tab_html += "<td > " + jobDetails[index].allocateTo + " </td>";
                if (flag === 'Alocate') {
                    tab_html += "<td > " + jobDetails[index].allocateBy + " </td>";
                }
                tab_html += "<td > " + jobDetails[index].zoneId + " </td>";
                tab_html += "<td > " + jobDetails[index].wardNo + " </td>";
                var chUnch = "";
                if (jobDetails[index].active) {
                    chUnch = "checked";
                } else {
                    chUnch = "unchecked";
                }
//                tab_html += '<td > <label class=labeltxt><input type=checkbox name="" ' + chUnch + ' onchange="JobAllocation.deAllocateJob(\'' + index + '\')"/></label>';
                if (flag === 'Add') {
                    tab_html += '<td > <label class=labeltxt><input type=checkbox name="" ' + chUnch + ' onchange="JobAllocation.removeFromAdded(\'' + index + '\')"/></label>';
                } else if (flag === 'Alocate') {
                    tab_html += '<td > <label class=labeltxt><input type=checkbox name="" ' + chUnch + ' onchange="JobAllocation.deAllocateJob(\'' + index + '\')"/></label>';
                }

                tab_html += "</tr>";
            }
        }
        tab_html += "</tbody>";
        var divId = "";
        var tableId = "";
        if (flag === 'Add') {
            divId = 'addedJobs';
            tableId = 'addedJobsTable';
        } else if (flag === 'Alocate') {
            divId = 'searchResults';
            tableId = 'property_tab';
        }

        $('#' + divId).show();
        $('#' + tableId).html(tab_html);
        $('#' + tableId).DataTable({
            "dom": 'Bfrtip',
            "buttons": [],
            "lengthMenu": [[10, 25, 50, -1], [10, 25, 50, "All"]],
            "bDestroy": true,
            "responsive": true,
            "paging": true,
            // "scrollY":200,
            /* "scrollX":true, */
            "sPaginationType": "full_numbers"
        });
        LOADER.hide();
    },
    checkAllreadyAllocation: function (zoneId, ward, userDeatil) {
        for (var index in  JobAllocation.allocatedJobs) {
            var jobDetail = JobAllocation.allocatedJobs[index];
            if (jobDetail.allocateTo === userDeatil.userId && jobDetail.zoneId === zoneId && jobDetail.wardNo === ward) {
                return true;
            }
        }
        return false;
    },
    checkAllreadyAdded: function (zoneId, ward, userDeatil) {
        for (var index in  JobAllocation.addedJobs) {
            var jobDetail = JobAllocation.addedJobs[index];
            if (jobDetail.allocateTo === userDeatil.userId && jobDetail.zoneId === zoneId && jobDetail.wardNo === ward) {
                return true;
            }
        }
        return false;
    },
    addJobForSubmission: function () {
        var check = JobAllocation.validateInputs();
        if (check) {
            var userId = $("#userId").val();
            var userDetail = JobAllocation.userMaster[userId];
            userId = userDetail.userId;
            var zoneId = $("#zoneId").val();
            var ward = $("#ward").val();
            var decision = JobAllocation.checkAllreadyAllocation(zoneId, ward, userDetail);
            if (decision) {
                alert("Duplicate Allocation not Allowed.");
                return false;
            }
            var decisionadded = JobAllocation.checkAllreadyAdded(zoneId, ward, userDetail);
            if (decisionadded) {
                alert("Duplicate Addition.");
                return false;
            }
            var jobAllocationDetails = new Object();
            jobAllocationDetails.zoneId = zoneId;
            jobAllocationDetails.wardNo = ward;
            jobAllocationDetails.active = "Y";
            jobAllocationDetails.allocateTo = userId;
            JobAllocation.addedJobs.push(jobAllocationDetails);
        } else {
            alert("Please Fill Required Details");
        }
        JobAllocation.TableHtml('Add');
    },
    removeFromAdded: function (index) {
        JobAllocation.addedJobs.splice(index, 1);
        JobAllocation.TableHtml('Add');
    },
    resetAddedTable: function () {
        $("#addedJobs").hide();
        JobAllocation.addedJobs = new Array();
        $("#addedJobsTable").empty();
    }
};
$(document).ready(function () {
    JobAllocation.getUsers();
    JobAllocation.getZones();
    JobAllocation.getAllocatedJobs();
});
$(document).on("change", "#zoneId", function () {
    JobAllocation.resetWard();
    JobAllocation.getWard();
});

