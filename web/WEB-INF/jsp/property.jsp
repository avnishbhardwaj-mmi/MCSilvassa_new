<link href="res/css/datatable2.css" rel="stylesheet" type="text/css" />
<link href="res/css/buttons.dataTables.min.css" rel="stylesheet" type="text/css" />
<link rel="stylesheet" type="text/css" href="res/css/select2.css" />
<link rel="stylesheet" type="text/css" href="res/css/select2-bootstrap.css" />
<link rel="stylesheet" href="res/css/jquery-ui.css">
<link rel="stylesheet" type="text/css" href="res/css/sumoselect.css" />
<link rel="stylesheet" type="text/css" href="res/css/easy-autocomplete.css" />

<script type="text/javascript" src="res/js/api/select2.min.js"></script>
<script type="text/javascript" src="res/js/api/jquery.dataTables.min.js"></script>
<script type="text/javascript" src="res/js/api/dataTables.buttons.min.js"></script>
<script type="text/javascript" src="res/js/api/buttons.flash.min.js"></script>
<script type="text/javascript" src="res/js/api/jszip.min.js"></script>
<script type="text/javascript" src="res/js/api/pdfmake.min.js"></script>
<script type="text/javascript" src="res/js/api/vfs_fonts.js"></script>
<script type="text/javascript" src="res/js/api/buttons.html5.min.js"></script>
<script type="text/javascript" src="res/js/api/buttons.print.min.js"></script>
<script type="text/javascript" src="res/js/api/jquery-dateFormat.js"></script>
<script type="text/javascript" src="res/js/property.js?v2" type="text/javascript"></script>
<script type="text/javascript" src="res/js/util.js" type="text/javascript"></script>
<script type="text/javascript" src="res/js/api/jquery.easy-autocomplete.js"></script>
<script>


</script>
<div class="row"  id="property_section">
    <div class="col-lg-12 pl-4">

        <!--        <div class="innerdashboard">
                    <div class="filterward pb-3 d-flex"><h3 class="propeh p-0 m-0">Property </h3>
                        <div class="ml-auto"><a href="#" class="btn btn-primary red_new_Property">+ Add New Property</a></div>
                    </div>
                    <div class="filterward">
                        <div class="row">
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
                                    <input type="text" id="prop_id_input"  class="form-control" placeholder="">
                                </div>
                            </div>
                            <div class="col-3">
                                <div class="WardscT">
                                    <label>Ward<span style="color: red;">*</span></label>
                                    <select class="form-control" name="ward" id="ward">
                                        <option> --Select Ward--</option>
                                    </select>
                                </div>
                            </div>
                            <div class="col-3 ">
                                <div class="form-group">
                                    <label class="labeltxt">Owner Name </label>
                                    <select name="ownerid" id="ownerid" class="form-control">
                                        <option value="-1">--Select Owner Name--</option>
                                    </select>
                                </div>
                            </div> 
                            <div class="col-3 ">
                                <div class="form-group">
                                    <label class="labeltxt">Occupier Name </label>
                                    <select name="occ_name" id="occ_name" class="form-control">
                                        <option value="-1">--Select Occupier Name--</option>
                                    </select>
                                </div>
                            </div>
                            <div class="col-3">
                                <div class="form-group">
                                    <label class="labeltxt">Phone No.</label>
                                    <input type="text" id="Phone_No" placeholder="Phone No." class="form-control" placeholder="">
                                </div>
                            </div>
                            <div class="col-3">
                                <div class="form-group">
                                    <label class="labeltxt">Easy City Code</label>
                                    <input type="text" id="Easy_City_Code" placeholder="Easy City Code" class="form-control" placeholder="">
                                </div>
                            </div>
        
        
                            <div class="col-3  ">
                                <div class="WardscT">
                                    <label>Locality</label>
                                    <select class="form-control" name="locality" id="locality" onchange="">
                                        <option value="-1">--Select Locality--</option>
                                    </select>
                                </div>
                            </div>
        
                            <div class="col-3">
                                <div class="form-group">
                                    <label class="labeltxt">Sub Locality</label>
                                    <input type="text" id="Locality" placeholder="Sub Locality" class="form-control" placeholder="">
                                </div>
                            </div>
        
                            <div class="col-3 ">
                                <div class="zonescT">
                                    <label>Property ID </label>
                                    <select class="form-control" name="propertyid" id="propertyid">
                                        <option value="-1">--Select Property ID--</option>
                                    </select>
                                </div>
                            </div>
        
        
        
                            <div class="col-3 ">
                                <div class="form-group">
                                    <label class="labeltxt">Aadhar Number</label>
                                    <input type="text" id="src_aadhar_no" class="form-control" placeholder="">
        
                                </div>
                            </div>
                            <div class="col-3">
                                <div class="form-group">
                                    <label class="labeltxt">Property Type </label>
                                    <select name="category" id="category" class="form-control">
                                        <option value="-1">--Select Property Type Name--</option>
                                    </select>
                                </div>
                            </div> 
                        </div>
                        <div class="d-flex pt-5 mt-5"><div class="col-12 text-center"> <button class="btn btn-primary" id="btn_property_submit" onclick="HomeClass.searchPropertyDetails();">Search</button></div></div>
                    </div>
        
        
                </div>-->
        <div class="innerdashboard">
            <div class="filterward  d-flex"><h3 class="propeh p-0 m-0">Property </h3>
                <div class="ml-auto"><a href="newProperty" class="btn btn-primary red_new_Property">+ Add New Property</a></div>
            </div>
            <div class="filterother">
                <div class="row">
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
                            <input type="text" id="prop_id_input"  class="form-control" placeholder="">
                        </div>
                    </div>
                    <div class="col-3">
                        <div class="WardscT">
                            <label>Ward<span style="color: red;"></span></label>
                            <!--                            <select class="form-control" name="ward" id="ward">
                                                            <option> --Select Ward--</option>
                                                        </select>-->
                            <input type="text" id="ward"  class="form-control" placeholder="">
                        </div>
                    </div>
                    <div class="col-3 ">
                        <div class="form-group">
                            <label class="labeltxt">Owner Name </label>
                            <!--                            <select name="ownerid" id="ownerid" class="form-control">
                                                            <option value="-1">--Select Owner Name--</option>
                                                        </select>-->
                            <input type="text" id="ownerid"  class="form-control" placeholder="">
                        </div>
                    </div> 
                    <div class="col-3 ">
                        <div class="form-group">
                            <label class="labeltxt">Occupier Name </label>
                            <!--                            <select name="occ_name" id="occ_name" class="form-control">
                                                            <option value="-1">--Select Occupier Name--</option>
                                                        </select>-->
                            <input type="text" id="occ_name"  class="form-control" placeholder="">
                        </div>
                    </div>
                    <div class="col-3">
                        <div class="form-group">
                            <label class="labeltxt">Phone No.</label>
                            <input type="text" id="Phone_No" placeholder="Phone No." class="form-control" placeholder="">
                        </div>
                    </div>
                    <div class="col-3">
                        <div class="form-group">
                            <label class="labeltxt">Easy City Code</label>
                            <input type="text" id="Easy_City_Code" disabled placeholder="Easy City Code" class="form-control" placeholder="">
                        </div>
                    </div>


                    <div class="col-3  ">
                        <div class="WardscT">
                            <label>Locality</label>
                            <!--                            <select class="form-control" name="locality" id="locality" onchange="">
                                                            <option value="-1">--Select Locality--</option>
                                                        </select>-->
                            <input type="text" id="locality"  class="form-control" placeholder="">
                        </div>
                    </div>

                    <div class="col-3">
                        <div class="form-group">
                            <label class="labeltxt">Sub Locality</label>
                            <input type="text" id="Locality" placeholder="Sub Locality" class="form-control" placeholder="">
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
                            <input type="text" id="src_aadhar_no" class="form-control" placeholder="">

                        </div>
                    </div>
                    <div class="col-3">
                        <div class="form-group">
                            <label class="labeltxt">Property Type </label>
                            <!--                            <select name="category" id="category" class="form-control">
                                                            <option value="-1">--Select Property Type Name--</option>
                                                        </select>-->
                            <input type="text" id="category"  class="form-control" placeholder="">
                        </div>
                    </div> 
                </div>
                <div class="d-flex pt-5 mt-5"><div class="col-12 text-center"> <button class="btn btn-primary" id="btn_property_submit" onclick="HomeClass.searchPropertyDetails();">Search</button></div></div>
            </div>


        </div>
    </div>
</div>

<section class=" row hidden" id="searchResults" >
    <div class="col-lg-12 pl-4">
        <div class="innerdashboard">
            <div id="property_tab_div" class="conainerB">
                <div class="filterward row m-0"><h3 class="propeh p-0 m-0">Property List</h3>
                    <div class="col-md-6 p-0 text-right">
                        <a href="javascript: void(0)" class="btn btn-primary greeen_linear-gradient" onclick="HomeClass.exportAs('PDF')"> Download as PDF </a>
                        <a href="javascript: void(0)" class="btn btn-primary Blue_linear-gradient" onclick="HomeClass.exportAs('EXCEL')"> Download as Excel </a>
                        <a href="javascript: void(0)" class="btn btn-primary" onclick="HomeClass.openSearchWindow()"> Back </a>
                    </div>

                </div>
                <div class="filterother">
                    <table id="property_tab"  data-page-length='10' class="table table-bordered table-sm" >
                    </table>
                </div>
            </div>
            <div class="clearfix"></div>
            <div class="row m-0">

            </div>
        </div>
    </div>
</section>
<section class="cont hidden" >
    <div class="container-fluid contf" >
        <!-- Sandeep Added below on for loader-->
        <div id="loading" style="display: none;">
            <p>
                <img src="res/img/leaflet-loader.gif" />
            </p>
        </div>
        <div class="modal fade" id="map-modal" tabindex="-1" role="dialog" aria-labelledby="myModalLabel" aria-hidden="true">
            <div class="modal-dialog">
                <div class="helpmodal-container">
                    <button type="button" class="close" data-dismiss="modal"
                            aria-hidden="true">X</button>
                    <div id="map_div" style="border: 1px solid #999; width: 400px; height: 400px; display: none" align="center">
                        <!--       <div id="left_arrow_div"  onclick="ResizeMap.hide_left_col();" style="position:absolute;z-index:4;" title="Full Map View"></div>
<div id="right_arrow_div" onclick="ResizeMap.show_left_col();" style="display: none;position:absolute;z-index:4;" title="Normal Map View"></div>
<div id="loader" style="display:none;z-index: 10;position:absolute;margin-left: 400px;margin-top: 200px;" ><img src="res/img/leaflet-loader.gif" alt="" align="absmiddle" /></div>
<div id="loader1" style="display:none;z-index: 10;position:absolute;margin-left: 300px;margin-top: 200px;background-color:#88211a;color:white;font-weight: bold;height:20px;" >Please Wait while downloading is in process... </div>
                        -->
                    </div>
                </div>
            </div>
        </div>
    </div>
</section>


<section class="row hidden" id="property_details_tab_div" >
    <div class="col-lg-12 pl-4 " >
        <div class="innerdashboard">

            <div class="filterward row m-0 pb-0"><div class="propeh">Property Details</div>

                <div class="ml-auto" >

                    <div class="span4 hidden">
                        <input type="text" class="form-control" placeholder="Search Unique ID" onkeydown="
                                var key = (event.keyCode ? event.keyCode : event.which);
                                if (key == 13) {
                                    HomeClass.searchUniqueID();
                                }" id="prop_det_id" />
                    </div>
                    <div class="hidden span1 text-left">
                        <a href="javascript: void(0)" class="btn btn-danger" onclick="HomeClass.searchUniqueID()">GO</a>
                    </div>
                    <!--            <div class="span4" style="display: none;">
                                    <a href="javascript: void(0)" class="btn btn-danger" onclick="HomeClass.searchNext()">Next</a>
                                </div>-->
                    <div class="span2" style="float: right;">
                        <a href="javascript: void(0)" class="btn btn-primary" onclick="HomeClass.openSearchResults()"> Back </a>
                    </div>
                    <div class="span2" style="float: right;">
                    <a href="javascript: void(0)" class="btn btn-primary Blue_linear-gradient"
                                onclick="HomeClass.enableEdit()"> Edit  Property</a> 
                    </div>

                    <div class="span2" style="float: right;">
                        
                        <a id="show_notice" class="btn btn-primary red_new_Property disabled" target="abc" >View Notice</a>
                    </div>
                </div>

            </div>

            <div class="clearfix"></div>
            <div class="filterother">

                <div id="accordion">

                    <div class="card">
                        <div class="card-header">
                            <a class="card-link" data-toggle="collapse" href="#OwnerDetails">
                                Owner Details <span class="material-icons right-arrow">keyboard_arrow_down</span>
                            </a>
                        </div>
                        <div id="OwnerDetails" class="collapse show" >
                            <div class="card-body">



                                <div class="row">
                                    <div class="col-md-3">
                                        <div class="form-group">
                                            <label class="labeltxt">Property ID:</label> 
                                            <input type="text" id="prop_unique_id" disabled="disabled" class="form-control" placeholder="">
                                        </div>
                                    </div>

                                    <!-- <div class="col-md-4">
                                            <div class="form-group">
                                                    <label class="labeltxt">Property ID:</label> <input type="text"
                                                            id="prop_id" disabled="disabled" class="form-control"
                                                            placeholder="">
                                            </div>
                                    </div> -->
                                    <div class="col-md-3">
                                        <div class="form-group">
                                            <label class="labeltxt">Owner Name: </label> 
                                            <input type="text" id="prop_ownername_id" disabled="disabled" class="form-control" placeholder="">
                                        </div>
                                    </div>
                                    <div class="col-md-3">
                                        <div class="form-group">
                                            <label class="labeltxt">Occupier Name: </label> 
                                            <input  type="text" id="prop_occupier_name" disabled="disabled" class="form-control" placeholder="">
                                        </div>
                                    </div>
                                    <div class="col-md-3">
                                        <div class="form-group">
                                            <label class="labeltxt">Relation With Owner:</label> 
                                            <input disabled="disabled" id="prop_relation_id" type="text" class="form-control" placeholder="">
                                        </div>
                                    </div>


                                    <div class="col-md-3">
                                        <div class="form-group">
                                            <label class="labeltxt">Owner Spouse Name: </label> 
                                            <input type="text" id="prop_owner_spouse" disabled="disabled" class="form-control" placeholder="">
                                        </div>
                                    </div>
                                    <div class="col-md-3">
                                        <div class="form-group">
                                            <label class="labeltxt">Owner Email: </label> 
                                            <input type="text" id="prop_owner_email" disabled="disabled" class="form-control" placeholder="">
                                        </div>
                                    </div>
                                    <div class="col-md-3">
                                        <div class="form-group">
                                            <label class="labeltxt">Owner MobileNo: </label> 
                                            <input type="text" id="prop_owner_mobile" disabled="disabled" class="form-control" placeholder="">
                                        </div>
                                    </div>
                                    <div class="col-md-3">
                                        <div class="form-group">
                                            <label class="labeltxt">Owner Adhar Card No: </label> 
                                            <input type="text" id="prop_owner_adhaar" disabled="disabled" class="form-control" placeholder="">
                                        </div>
                                    </div>



                                    <!-- 	<div class="col-md-4">
                    <div class="form-group">
                            <label class="labeltxt">Relation Name: </label> <input type="text"
                                    id="prop_owner_relation" disabled="disabled"
                                    class="form-control" placeholder="">
                    </div>
            </div> -->

                                    <!-- 		<div class="col-md-4">
                                                            <div class="form-group">
                                                                    <label class="labeltxt">House Tax no.</label> <input
                                                                            type="text" id="prop_house_no" disabled="disabled"
                                                                            class="form-control" placeholder="">
                                                            </div>
                                                    </div>
                                    -->



                                    <!-- 							<div class="col-md-4">
                                                                                                    <div class="form-group">
                                                                                                            <label class="labeltxt">Relation Name: </label> <input
                                                                                                                    type="text" id="prop_owner_relation" disabled="disabled"
                                                                                                                    class="form-control" placeholder="">
                                                                                                    </div>
                                                                                            </div> -->
                                    <!-- <div class="col-md-4">
                                            <div class="form-group">
                                                    <label class="labeltxt">Photo ID No:</label> <input type="text"
                                                            id="prop_photo_id" disabled="disabled" class="form-control"
                                                            placeholder="">
                                            </div>
                                    </div> -->
                                </div>
                                <div class="row">
                                    <!-- <div class="col-md-4">
                                            <div class="form-group">
                                                    <label class="labeltxt">Camera No. </label> <input type="text"
                                                            id="prop_cam_id" disabled="disabled" class="form-control"
                                                            placeholder="">
                                            </div>
                                    </div> -->

                                </div>
                            </div>
                        </div>
                    </div>

                    <div class="card">
                        <div class="card-header">
                            <a class="collapsed card-link" data-toggle="collapse" href="#ResidingPersonnel">
                                Other Residing Personnel <span class="material-icons right-arrow">keyboard_arrow_down</span>
                            </a>
                        </div>
                        <div id="ResidingPersonnel" class="collapse" >
                            <div class="card-body">
                                <div class="row">
                                    <div class="col-md-3">
                                        <div class="form-group">
                                            <label class="labeltxt">No. of Person:</label>
                                            <input type="text" disabled="disabled" id="prop_floor_num_of_person" class="form-control" placeholder="">
                                        </div>
                                    </div>
                                    <div class="col-md-3">
                                        <div class="form-group">
                                            <label class="labeltxt">Male(+18):</label> 
                                            <input type="text" disabled="disabled" id="male_gt18" class="form-control" placeholder="">
                                        </div>
                                    </div>
                                    <div class="col-md-3">
                                        <div class="form-group">
                                            <label class="labeltxt">Male(-18):</label> 
                                            <input type="text" disabled="disabled" id="male_lt18" class="form-control" placeholder="">
                                        </div>
                                    </div>
                                    <div class="col-md-3">
                                        <div class="form-group">
                                            <label class="labeltxt">Female(+18):</label> 
                                            <input type="text" disabled="disabled" id="female_gt18" class="form-control" placeholder="">
                                        </div>
                                    </div>

                                    <div class="col-md-3">
                                        <div class="form-group">
                                            <label class="labeltxt">Female(-18):</label> 
                                            <input type="text" disabled="disabled" id="female_lt18" class="form-control" placeholder="">
                                        </div>
                                    </div>
                                    <div class="col-md-3">
                                        <div class="form-group">
                                            <label class="labeltxt">Name1:</label> 
                                            <input type="text" disabled="disabled" id="name1" class="form-control" placeholder="">
                                        </div>
                                    </div>
                                    <div class="col-md-3">
                                        <div class="form-group">
                                            <label class="labeltxt">Name2:</label> 
                                            <input type="text" disabled="disabled" id="name2" class="form-control" placeholder="">
                                        </div>
                                    </div>
                                    <div class="col-md-3">
                                        <div class="form-group">
                                            <label class="labeltxt">Name3:</label> 
                                            <input type="text" disabled="disabled" id="name3" class="form-control" placeholder="">
                                        </div>
                                    </div>

                                    <div class="col-md-3">
                                        <div class="form-group">
                                            <label class="labeltxt">Name4:</label> 
                                            <input type="text" disabled="disabled" id="name4" class="form-control" placeholder="">
                                        </div>
                                    </div>
                                    <div class="col-md-3">
                                        <div class="form-group">
                                            <label class="labeltxt">Name5:</label> 
                                            <input type="text" disabled="disabled" id="name5" class="form-control" placeholder="">
                                        </div>
                                    </div>
                                    <div class="col-md-3">
                                        <div class="form-group">
                                            <label class="labeltxt">Name6:</label> 
                                            <input type="text" disabled="disabled" id="name6" class="form-control" placeholder="">
                                        </div>
                                    </div>
                                </div>
                            </div>
                        </div>
                    </div>

                    <div class="card">
                        <div class="card-header">
                            <a class="collapsed card-link" data-toggle="collapse" href="#PropertyAddress">
                                Property Address <span class="material-icons right-arrow">keyboard_arrow_down</span>
                            </a>
                        </div>
                        <div id="PropertyAddress" class="collapse" >
                            <div class="card-body">
                                <div class="row">
                                    <div class="col-md-3">
                                        <div class="form-group">
                                            <label class="labeltxt">Zone : </label> 
                                            <input type="text" id="prop_zone_id" disabled="disabled" class="form-control" placeholder="">
                                        </div>
                                    </div>
                                    <div class="col-md-3">
                                        <div class="form-group">
                                            <label class="labeltxt">Ward : </label> 
                                            <input type="text" id="prop_ward_id" disabled="disabled" class="form-control" placeholder="">
                                        </div>
                                    </div>

                                    <div class="col-md-3">
                                        <div class="form-group">
                                            <label class="labeltxt">Plot/House No. </label> 
                                            <input id=property_plot_num disabled="disabled" type="text" class="form-control" placeholder="">
                                        </div>
                                    </div>
                                    <div class="col-md-3">
                                        <div class="form-group">
                                            <label class="labeltxt">Building Name:</label> 
                                            <input type="text" id="prop_building_id" disabled="disabled" class="form-control" placeholder="">
                                        </div>
                                    </div>

                                    <div class="col-md-3">
                                        <div class="form-group">
                                            <label class="labeltxt">Road Name/Street Name:</label> 
                                            <input type="text" class="form-control" id="property_road" disabled="disabled" placeholder="">
                                        </div>
                                    </div>
                                    <div class="col-md-3">
                                        <div class="form-group">
                                            <label class="labeltxt">Sub Locality:</label> 
                                            <input type="text" disabled="disabled" id="prop_sub_locality_id" class="form-control" placeholder="">
                                        </div>
                                    </div>
                                    <div class="col-md-3">
                                        <div class="form-group">
                                            <label class="labeltxt">Locality Name:</label> 
                                            <input type="text" disabled="disabled" id="prop_locality_id" class="form-control" placeholder="">
                                        </div>
                                    </div>
                                    <div class="col-md-3">
                                        <div class="form-group">
                                            <label class="labeltxt">Pin Code:</label> 
                                            <input type="text" class="form-control" disabled="disabled" id="property_pincode" placeholder="">
                                        </div>
                                    </div>

                                    <div class="col-md-3">
                                        <div class="form-group">
                                            <label class="labeltxt">Nearest Landmark:</label> 
                                            <input disabled="disabled" id="prop_landmark_id" type="text" class="form-control" placeholder="">
                                        </div>
                                    </div>
                                    <div class="col-md-3">
                                        <div class="form-group">
                                            <label class="labeltxt">Cost of Property:</label> 
                                            <input disabled="disabled" id="prop_cost_id" type="text" class="form-control" placeholder="">
                                        </div>
                                    </div>
                                    <div class="col-md-3">
                                        <div class="form-group">
                                            <label class="labeltxt">Mobile. / Phone No.</label>
                                            <input disabled="disabled" id="prop_mobile_id" type="text" class="form-control" placeholder="" />
                                        </div>
                                    </div>
                                    <div class="col-md-3">
                                        <div class="form-group">
                                            <label class="labeltxt">Survey No. </label> 
                                            <input id=property_survey_num disabled="disabled" type="text" class="form-control" placeholder="">
                                        </div>
                                    </div>

                                    <div class="col-md-3">
                                        <div class="form-group">
                                            <label class="labeltxt">Current Status of Property:</label> 
                                            <input type="text" disabled="disabled" id="property_status_name" class="form-control" placeholder="">
                                        </div>
                                    </div>
                                </div>
                            </div>
                        </div>
                    </div>
                    <div class="card">
                        <div class="card-header">
                            <a class="collapsed card-link" data-toggle="collapse" href="#CivicAmenities">
                                Property Details & Civic Amenities <span class="material-icons right-arrow">keyboard_arrow_down</span>
                            </a>
                        </div>
                        <div id="CivicAmenities" class="collapse" >
                            <div class="card-body">
                                <div class="row">

                                    <div class="col-md-3">
                                        <div class="form-group">
                                            <label class="labeltxt">Total No. of floors:</label> 
                                            <input disabled="disabled" id="owner_floors_id" type="text" class="form-control" placeholder="">
                                        </div>
                                    </div>
                                    <div class="col-md-3">
                                        <div class="form-group">
                                            <label class="labeltxt">No. of Rooms:</label> 
                                            <input type="text" disabled="disabled" id="owner_rooms_id" class="form-control" placeholder="">
                                        </div>
                                    </div>
                                    <div class="col-md-3">
                                        <div class="form-group">
                                            <label class="labeltxt">School & Colleges:</label> 
                                            <input type="text" disabled="disabled" id="prop_floor_room_cnt_school_clg" class="form-control" placeholder="">
                                        </div>
                                    </div>
                                    <div class="col-md-3">
                                        <div class="form-group">
                                            <label class="labeltxt">Hospital & Nursing Home:</label> 
                                            <input type="text" disabled="disabled" id="prop_floor_room_cnt_hospi_nurse" class="form-control" placeholder="">
                                        </div>
                                    </div>

                                    <div class="col-md-3">
                                        <div class="form-group">
                                            <label class="labeltxt">Hotels:</label> 
                                            <input type="text" disabled="disabled" id="prop_floor_room_cnt_hostel" class="form-control" placeholder="">
                                        </div>
                                    </div>
                                    <div class="col-md-3">
                                        <div class="form-group">
                                            <label class="labeltxt">Plot Area</label>
                                            <input type="text" disabled="disabled" id="prop_floor_plot_area" class="form-control" placeholder="">
                                        </div>
                                    </div>
                                </div>
                                <div class="custom_table">
                                    <div class="propeh">Floor wise details</div>
                                    <div class="scroll_table">
                                        <table id="floorDet" class="table" >
                                        </table>
                                    </div>
                                </div>
                            </div>
                        </div>
                    </div>
                    <div class="card">
                        <div class="card-header">
                            <a class="collapsed card-link" data-toggle="collapse" href="#TaxDetails">
                                Tax Details <span class="material-icons right-arrow">keyboard_arrow_down</span>
                            </a>
                        </div>
                        <div id="TaxDetails" class="collapse" >
                            <div class="card-body">
                                <div class="row">
                                    <div class="col-md-3">
                                        <div class="panel-heading extra_padding_inner">
                                            <strong> TAX generated : </strong> <span class="text-muted" id="prop_tax_status">  </span>
                                        </div>
                                        <div class="panel-heading extra_padding_inner">
                                            <strong> TAX Amount (INR): </strong> <span class="text-muted" id="prop_tax_amount"> </span>
                                        </div>
                                    </div>
                                    <div class="col-md-3">
                                        <div class="panel-heading extra_padding_inner">
                                            <strong> Notice Generated : </strong> <span class="text-muted" id="prop_notice_status">  </span>
                                        </div>
                                        <div class="panel-heading extra_padding_inner">
                                            <strong> Tax Amount Paid (INR): </strong> <span class="text-muted" id="prop_tax_paid_amount"> </span>
                                        </div>
                                    </div>
                                    <div class="col-md-3">
                                        <!--                            <div class="panel-heading extra_padding_inner">
                                                                        <strong> Objection Status: </strong> <span class="text-muted" id="prop_obj_status"> </span>
                                                                    </div>-->
                                        <div class="panel-heading extra_padding_inner">
                                            <strong> Assessment Year: </strong> <span class="text-muted" > 2019-2020 </span>
                                        </div>
                                        <div class="panel-heading extra_padding_inner">
                                            <strong> Tax Amount Payable (INR): </strong> <span class="text-muted" id="prop_tax_amount_payable"> </span>
                                        </div>
                                    </div>
                                    <!--                        <div class="col-md-3">
                                                                <div class="panel-heading extra_padding_inner">
                                                                    <strong> Assessment Year: </strong> <span class="text-muted" id="prop_asst_yr"> </span>
                                                                </div>
                                                            </div>-->

                                </div>
                            </div>
                        </div>
                    </div>



                    <div class="span12 text-center">
                        <a id="updateProp" href="javascript: void(0)" class="btn btn-danger" onclick="HomeClass.updatePropertyDetails()" style="display: none;">Update</a>
                    </div>


                    <div class="panel-group hidden" id="no_result_found" >
                        <div id="collapse1" class="panel-collapse collapse in" role="tabpanel" aria-labelledby="heading1">
                            <div class="panel-body">
                                <div class="row">
                                    <div class="col-md-12 text-center">
                                        <div class="propeh">No results found</div>
                                    </div>
                                </div>
                            </div>
                        </div>
                    </div>
                </div>
            </div>
        </div>
    </div>
</section>
<!-- </form> -->


<!-- Sandeep Added below on 25Mar2017 for show property image -->
<div class="modal fade" id="help-modal" tabindex="-1" role="dialog" aria-labelledby="myModalLabel" aria-hidden="true">
    <div class="modal-dialog">
        <div class="helpmodal-container" style="font-family: 'Helvetica Neue', Helvetica, Arial, sans-serif;">
            <div style='padding-bottom: 15px; font-weight: 600; font-size: 22px'>
                <button type="button" class="close" data-dismiss="modal" aria-hidden="true">X</button>
                <div class="propeh">Property Image</div>
            </div>
            <img src='' width="100%" height="100%" id="cntrimg" /><br/>

            <div id="image-slider-btn">
                <div class="modal-footer"> </div>
                <div class="container-fluid contf" style="background: #f7f7f7;" >
                    <div class="row text-center">
                        <div class="col-md-3">
                            <a href="javascript: void(0)" id="img-prev-btn" class="btn btn-primary" onclick="HomeClass.previousImage();"> &lt;&lt Previous </a>
                        </div>
                        <div class="col-md-3"></div>
                        <div class="col-md-3"></div>
                        <div class="col-md-3">
                            <a href="javascript: void(0)" id="img-next-btn" class="btn btn-primary" onclick="HomeClass.nextImage();"> Next &gt;&gt</a>
                        </div>
                    </div>
                </div>
            </div>
        </div>
    </div>
</div>
<script>
    $(document).ready(function () {
        $("#property_main_menu").addClass("active");
        $("#property_sub_menu").addClass("active");
        $("#property_main_menu ul.sub-menu ").addClass("show");


        $(document).keypress(function (event) {
            var keycode = (event.keyCode ? event.keyCode : event.which);
            if (keycode == '13' && !$("#property_section").hasClass('hidden')) {
                HomeClass.searchPropertyDetails();
            }
        });

    });


    $(document).ready(function () {
        HomeClass.propertyIdFilter();
        HomeClass.phoneNoFilter();
        HomeClass.cityCodeFilter();
        HomeClass.aadharNoFilter();
        HomeClass.subLocalityFilter();
        HomeClass.wardlstFilter();
        HomeClass.ownerlstFilter();
        HomeClass.occupierlstFilter();
        HomeClass.localitylstFilter();
        HomeClass.propertytypeFilter();
    });
</script>
