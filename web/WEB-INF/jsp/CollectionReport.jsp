<%-- 
    Document   : ArrearReport
    Created on : 30 May, 2019, 3:06:57 PM
    Author     : CEINFO
--%>
<link rel="stylesheet" type="text/css" href="res/css/select2.css" />
<link rel="stylesheet" type="text/css" href="res/css/select2-bootstrap.css" />
<link rel="stylesheet" type="text/css" href="res/css/dataTables.bootstrap.min.css" />
<!--<link rel="stylesheet" type="text/css" href="https://cdn.jsdelivr.net/npm/daterangepicker/daterangepicker.css" />-->
<link rel="stylesheet" type="text/css" href="res/css/daterangepicker.css" />
<link rel="stylesheet" type="text/css" href="res/css/easy-autocomplete.css" />

<script src="res/js/api/jquery.dataTables.min.js"></script>
<script src="res/js/api/dataTables.bootstrap.min.js"></script>
<script src="res/js/api/jquery-ui.js"></script>
<script src="res/js/api/select2.min.js"></script>
<!--<script type="text/javascript" src="https://cdn.jsdelivr.net/momentjs/latest/moment.min.js"></script>
<script type="text/javascript" src="https://cdn.jsdelivr.net/npm/daterangepicker/daterangepicker.min.js"></script>-->
<script type="text/javascript" src="res/js/api/moment-min.js"></script>
<script type="text/javascript" src="res/js/api/date-range-picker.js"></script>
<script type="text/javascript" src="res/js/commonSearch.js"></script>
<script type="text/javascript" src="res/js/api/jquery.easy-autocomplete.js"></script>

<%@taglib uri = "http://www.springframework.org/tags/form" prefix = "form"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!--section class="w-100">
    <div class="custom-top">
        <div class="logo clearfix">

            <a href="" ><img src="res/img/logo.png"  title="Home Page"/></a>

<div class="log"><span>Welcome Sunil Kumar</span>  |  <a href="logout"> Logout</a>  </div>

</div>
</div>
</section -->
<section class="row_" id="searchResults"> 
    <div class="col-lg-12 p-0"> 
        <div class="innerdashboard">
            <div id="tys_tab_div" class="conainer">
                <div class="filterward pb-2"><h3>Collection report</h3></div>
                <div class="filterother">

                    <form:form  name="collectionData" method="post" onsubmit="return validate();"  action="collectionReportData">
                        <div  class="row"> 

                            <!--                     <div class="col-md-3">
                                                     <label class="labeltxt">Ward</label>
                                                     <select id="ward" name="ward"  class="form-control">
                                                     </select>
                                                 </div>
                     
                                                 <div class="col-md-3">
                                                     <label class="labeltxt">Property Id</label>
                                                     <input type="text" autocomplete="off" id="propertyId" name="propertyId"  class="form-control"/>
                                                     </select>
                                                 </div>
                     
                     
                     
                                                 <div class="col-md-3">
                                                     <label class="labeltxt">Collection Date</label>
                                                     <select id="collectDate" name="collectDate" onchange="displayDate();" class="form-control">
                                                         <option value="-1">Select</option>
                                                         <option value="dailywise">Today</option>
                                                         <option value="monthwise">Date Between</option>  
                                                     </select>
                                                 </div>
                     
                                                 <div class="col-md-3 ">
                                                     <div id="cdate" class="row"  style="display: none" >
                                                         <div class="col-md-5 mt-4">
                     
                                                             <label class="labeltxt">Start Date : <span style="color: red;">*</span></label>
                                                             <input type="text" id="startDate" autocomplete="off" name="startDate" path="startDate" class="form-control" placeholder="" />
                     
                                                         </div>
                     
                                                         <div class="col-md-3">
                                                             <label class="labeltxt">End Date : <span style="color: red;">*</span></label>
                                                             <input type="text" id="endDate" autocomplete="off" name="endDate" path="endDate" class="form-control" placeholder="" />
                                                         </div>
                                                     </div>  
                                                 </div> 
                     
                            -->



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
                                    <input type="text" id="ward" name="ward"  class="form-control" placeholder="">
                                </div>
                            </div>
                            <div class="col-3 ">
                                <div class="form-group">
                                    <label class="labeltxt">Owner Name </label>
                                    <input type="text" id="ownerid"  name="ownerid" class="form-control" placeholder="">
                                </div>
                            </div> 
                            <div class="col-3 ">
                                <div class="form-group">
                                    <label class="labeltxt">Occupier Name </label>
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
                                    <input type="text" id="locality" name="locality" class="form-control" placeholder="">
                                </div>
                            </div>

                            <div class="col-3">
                                <div class="form-group">
                                    <label class="labeltxt">Sub Locality</label>
                                    <input type="text" id="Locality" name="Locality" placeholder="Sub Locality" class="form-control" placeholder="">
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

                            <div class="col-md-3">
                                <label class="labeltxt">Collection Date</label>
                                <select id="collectDate"  onchange="displayDate();" class="form-control">
                                    <option value="-1">Select</option>
                                    <option value="monthwise">Date Between</option>  
                                </select>
                            </div>

                            <div id = "daterangepick" class="col-md-3 d-none">
                                <label class="labeltxt">Select Date</label>
                                <input type="text" class="form-control" name="toDate" id="daterange1" placeholder="Select A date range" >
                            </div>

                            <div  class="col-md-3 d-none">
                                <label class="labeltxt">From Date</label>
                                <input type="text" class="form-control" name="frmDate" id="frmDate" placeholder="Select  From Date" >
                            </div>

                            <div  class="col-md-3 d-none">
                                <label class="labeltxt">End Date</label>
                                <input type="text" class="form-control" name="endDate" id="endDate" placeholder="Select End date " >
                            </div>
                        </div> 


                        <div  class="row">
                            <div class="col-md-12 text-center"> <label>&nbsp;&nbsp;</label><br>
                                <input type="submit" value="Search" class="btn btn-primary"/>         
                            </div>
                        </div>
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
        $("#collectionReportID").addClass("active");
        $("#report_main_menu ul.sub-menu ").addClass("show");
        $("#breadlist").addClass('lisb');
        $("#breadlist").html('')
        $("#breadlist").html(' <a href="collectionReport">Collection report </a> ');
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


        $("#startDate").datepicker({dateFormat: 'yy-mm-dd'});
        $("#endDate").datepicker({dateFormat: 'yy-mm-dd'});





        $('input[name="toDate"]')
                .daterangepicker(
                        {
                            maxDate: moment(),
                            ranges: {
                                'Today': [moment(), moment()],
                                'Yesterday': [
                                    moment().subtract(1, 'days'),
                                    moment().subtract(1, 'days')],
                                'Last 7 Days': [
                                    moment().subtract(6, 'days'),
                                    moment()],
                                'Last 30 Days': [
                                    moment().subtract(29, 'days'),
                                    moment()],
                                'Last 60 Days': [
                                    moment().subtract(59, 'days'),
                                    moment()]
                            }
                        });
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
        /*    var ward = $("#ward").val();
         var collectDate = $("#collectDate").val();
         var propertyId = $("#propertyId").val();
         var startDate = $("#startDate").val();
         var endDate = $("#endDate").val();
         //alert(ward+' '+collectDate+'  '+propertyId.length);
         if (ward == '-1' && collectDate == '-1' && propertyId.length == 0) {
         alert("Please select either ward or collectdate or propertyId ");
         $("#ward").focus();
         return false;
         } else if (collectDate != '-1') {
         if (collectDate == 'monthwise') {
         if (startDate.length == 0) {
         alert("Please select start date");
         $("#startDate").focus();
         return false;
         } else if (endDate.length == 0) {
         alert("Please select end date");
         $("#endDate").focus();
         return false;
         }
         }
         }*/
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
       
        var check = $("#daterangepick").hasClass('d-none');
        $("#frmDate").val('');
        $("#endDate").val('');
        if (!check) {
            var date = $("#daterange1").val();
            var st_end = date.split('-');
            var start = st_end[0].split('/');
            var end = st_end[1].split('/');
            var frmDate = start[2].trim().concat('-' + start[0] + '-' + start[1]);
            var endDate = end[2].trim().concat('-' + end[0].trim() + '-' + end[1]);
            $("#frmDate").val(frmDate);
            $("#endDate").val(endDate);
        }
        $("#validateid").html(''); 
        if (prop_id_input == "" && ward == "" && occ == "" && occ == "" && owner_id == "" && locality == "" && aadhar_num == "" && propertyCategory == "" && Phone_no == "" && Locality == "" && check) {
           // alert("Please select atleast one filter");
            $("#validateid").html("Please select at least one filter"); 
            return false;
        }

    }

    function displayDate() {
        var collectDate = $("#collectDate").val();
        if (collectDate == 'monthwise') {
            $("#daterangepick").removeClass('d-none');
        } else {
            $("#daterangepick").addClass('d-none');
        }
    }

</script>

</html>
