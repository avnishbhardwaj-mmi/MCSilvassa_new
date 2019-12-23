<%-- 
    Document   : ArrearReport
    Created on : 30 May, 2019, 3:06:57 PM
    Author     : CEINFO
--%>

<link rel="stylesheet" type="text/css" href="res/css/easy-autocomplete.css" />

<script type="text/javascript" src="res/js/commonSearch.js?v1"></script>
<script type="text/javascript" src="res/js/api/jquery.easy-autocomplete.js"></script>
<%@taglib uri = "http://www.springframework.org/tags/form" prefix = "form"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<section class="row_b" id="searchResults">
    <div class="col-lg-12 p-0">
        <div class="innerdashboard">
            <div id="arreartab_div" class="conainer">
                <div class="filterward"><h3 class="m-0">Arrear Report Data</h3>
                
                </div>
                <div class="filterother">
                    <form:form  name="arrearData" method="post" onsubmit="return validate();"  action="arrearReportData">
                        <div  class="row">
                            <!--                            <div class="col-md-3">
                                                            <label class="labeltxt">Ward</label>
                                                            <select id="ward" name="ward" onchange="changeWard();" class="form-control">
                                                            </select>
                                                        </div>
                                                        
                                                        <div class="col-md-3">
                                                            <label class="labeltxt">Property Id</label>
                                                            <input type="text" id="propertyId" name="propertyId" onkeypress="changePropertyId();" class="form-control"/>
                                                            </select>
                                                        </div>
                                                        
                                                        <div class="col-md-3">
                                                            <label class="labeltxt">Mobile No.</label>
                                                            <input type="text" id="mobileNo" name="mobileNo" onkeypress="changeMobile();" class="form-control">
                                                            </select>
                                                        </div>
                                                      
                                                        <div class="col-md-3">
                                                            <label class="labeltxt">Email</label>
                                                            <input type="text" id="email" name="email" onkeypress="changeEmail();" class="form-control">
                                                           </select>
                                                        </div>-->

                            <div class="col-6 d-none">
                                <div class="zonescT ">
                                    <label>Zone<span style="color: red;">*</span></label>
                                    <select  class="form-control" name="zone" id="zone">
                                        <option> --Select Zone--</option>
                                    </select>
                                </div>
                            </div> 
                            <div class="col-3">
                                <div class="form-group">
                                    <label class="labeltxt">Property Id</label>
                                    <input type="text" id="prop_id_input" name="prop_id_input" class="form-control" placeholder="">
                                </div>
                            </div>
                            <div class="col-3">
                                <div class="WardscT">
                                    <label>Ward<span style="color: red;"></span></label>
                                    <!--                            <select class="form-control" name="ward" id="ward">
                                                                    <option> --Select Ward--</option>
                                                                </select>-->
                                    <input type="text" id="ward" name="ward"  class="form-control" placeholder="">
                                </div>
                            </div>
                            <div class="col-3 ">
                                <div class="form-group">
                                    <label class="labeltxt">Owner Name </label>
                                    <!--                            <select name="ownerid" id="ownerid" class="form-control">
                                                                    <option value="-1">--Select Owner Name--</option>
                                                                </select>-->
                                    <input type="text" id="ownerid"  name="ownerid" class="form-control" placeholder="">
                                </div>
                            </div> 
                            <div class="col-3 ">
                                <div class="form-group">
                                    <label class="labeltxt">Occupier Name </label>
                                    <!--                            <select name="occ_name" id="occ_name" class="form-control">
                                                                    <option value="-1">--Select Occupier Name--</option>
                                                                </select>-->
                                    <input type="text" id="occ_name" name="occ_name" class="form-control" placeholder="">
                                </div>
                            </div>
                            <div class="col-3">
                                <div class="form-group">
                                    <label class="labeltxt">Phone No.</label>
                                    <input type="text" id="Phone_No" name="Phone_No" placeholder="Phone No." class="form-control" placeholder="">
                                </div>
                            </div>
                            <div class="col-3">
                                <div class="form-group">
                                    <label class="labeltxt">Easy City Code</label>
                                    <input type="text" id="Easy_City_Code"  name="Easy_City_Code" disabled placeholder="Easy City Code" class="form-control" placeholder="">
                                </div>
                            </div>


                            <div class="col-3  ">
                                <div class="WardscT">
                                    <label>Locality</label>
                                    <!--                            <select class="form-control" name="locality" id="locality" onchange="">
                                                                    <option value="-1">--Select Locality--</option>
                                                                </select>-->
                                    <input type="text" id="locality" name="locality" class="form-control" placeholder="">
                                </div>
                            </div>

                            <div class="col-3">
                                <div class="form-group">
                                    <label class="labeltxt">Sub Locality</label>
                                    <input type="text" id="Locality" name="Locality" placeholder="Sub Locality" class="form-control" placeholder="">
                                </div>
                            </div>

                            <div class="col-3 d-none">
                                <div class="zonescT">
                                    <label>Property ID </label>
                                    <select class="form-control" name="propertyid" id="propertyid">
                                        <option value="-1">--Select Property ID--</option>
                                    </select>
                                </div>
                            </div>



                            <div class="col-3 ">
                                <div class="form-group">
                                    <label class="labeltxt">Aadhaar Number</label>
                                    <input type="text" id="src_aadhar_no" name="src_aadhar_no" class="form-control" placeholder="">
                                </div>
                            </div>
                            <div class="col-3">
                                <div class="form-group">
                                    <label class="labeltxt">Property Type </label>
                                    <!--                            <select name="category" id="category" class="form-control">
                                                                    <option value="-1">--Select Property Type Name--</option>
                                                                </select>-->
                                    <input type="text" id="category"  name="category" class="form-control" placeholder="">
                                </div>
                            </div> 

                            <div class="col-md-12 text-center"> <label>&nbsp;&nbsp;</label><br>
                                <input type="submit"  value="Search" class="btn btn-primary"/>            </div>
                        </div>
                    </form:form>
                </div>
            </div>
        </div>
    </div>
</section>
<script>
    $(document).ready(function () {
        $("#report_main_menu").addClass("active");
        $("#arrearReport").addClass("active");
        $("#report_main_menu ul.sub-menu ").addClass("show");
        $("#breadlist").addClass('lisb');
        $("#breadlist").html('')
        $("#breadlist").html(' <a href="arrearReport">Arrear Report</a> ');
        $.ajax({
            type: "POST",
            url: "getWards?zone=",
            contentType: "application/json; charset=utf-8",
            success: function (data) {
                //console.log(data);
                var wards = (data);
                var strHTML = "<option value='-1'>-- Select Ward --</option>";
                for (var b in wards) {

                    var ward = wards[b];



                    strHTML += "<option value='" + ward.ward + "'> " + ward.ward + " </option>";

                }
                $("#ward").html(strHTML);
            },
            error: function () {
            }
        });

    });


    $(document).ready(function () {
        commonSearch.propertyIdFilter();
        commonSearch.phoneNoFilter();
        commonSearch.cityCodeFilter();
        commonSearch.aadharNoFilter();
        commonSearch.subLocalityFilter();
        commonSearch.wardlstFilter();
        commonSearch.ownerlstFilter();
        commonSearch.occupierlstFilter();
        commonSearch.localitylstFilter();
        commonSearch.propertytypeFilter();
    });

    function validate() {

        var zone_id = "";
        var prop_id_input = $("#prop_id_input").val();
        var ward = $("#ward").val();
        debugger;
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
        $("#validateid").html('');
        if (prop_id_input == "" && ward == "" && occ == "" && occ == "" && owner_id == "" && locality == "" && aadhar_num == "" && propertyCategory == "" && Phone_no == "" && Locality == "") {
            //alert("Please select atleast one filter");
            $("#validateid").html("Please select at least one filter");  
            return false;
        }

        /*
         var ward = $("#ward").val();
         var propertyId = $("#propertyId").val();
         var mobile = $("#mobileNo").val();
         var email = $("#email").val();
         var filter = /^([a-zA-Z0-9._\.\-])+\@(([a-zA-Z0-9\-])+\.)+([a-zA-Z0-9]{2,4})+$/;
         if (ward == '-1' && propertyId.length == 0 && mobile.length == 0 && email.length == 0) {
         alert("Please select either ward or property id or mobile or email ");
         //$("#ward").focus();
         return false;
         } else if (mobile != '' && mobile.length != 10) {
         alert("Mobile no. must be number and must be 10 digit ");
         $("#mobileNo").focus();
         return false;
         } else if (email != '' && !filter.test(email)) {
         alert('Please provide a valid Email ID');
         $("#email").focus();
         return false;
         }*/

    }
    function changeMobile() {
        var ward = $("#ward").val();
        var propertyId = $("#propertyId").val();
        var mobile = $("#mobileNo").val();
        var email = $("#email").val();
        if (mobile != '') {
            $("#propertyId").val("");
            $("#email").val("");
            $("#ward").val("-1");
        }

    }
    function changeEmail() {
        var ward = $("#ward").val();
        var propertyId = $("#propertyId").val();
        var mobile = $("#mobileNo").val();
        var email = $("#email").val();
        if (email != '') {
            $("#propertyId").val("");
            $("#mobileNo").val("");
            $("#ward").val("-1");
        }

    }
    function changePropertyId() {
        var ward = $("#ward").val();
        var propertyId = $("#propertyId").val();
        var mobile = $("#mobileNo").val();
        var email = $("#email").val();
        if (propertyId != '') {
            $("#mobileNo").val("");
            $("#email").val("");
            $("#ward").val("-1");
        }

    }

    function changeWard() {
        var ward = $("#ward").val();
        var propertyId = $("#propertyId").val();
        var mobile = $("#mobileNo").val();
        var email = $("#email").val();
        if (ward != '' && ward != '-1') {
            $("#propertyId").val("");
            $("#email").val("");
            $("#mobileNo").val("");
        }

    }






</script>

