<%-- 
//----------------------------------------------------------------------------------------------------
//                          MapMyIndia
//            Product / Project           : Silvassa
//            Module                      : PropertyEditing
//            File Name                   : editProperty
//            Author                      : Jay Prakash Kumar
//            Project Lead                :
//            Date written (DD/MM/YYYY)   : 12 Jul, 2017, 12:36:55 PM
//            Description                 : 
//----------------------------------------------------------------------------------------------------
//                                            CHANGE HISTORY
//----------------------------------------------------------------------------------------------------
// Date             Change By           Change Description (Bug No. (If Any))
// (DD/MM/YYYY)
//----------------------------------------------------------------------------------------------------
//----------------------------------------------------------------------------------------------------
--%>
<link href="res/css/datatable2.css" rel="stylesheet" type="text/css" />
<link href="res/css/buttons.dataTables.min.css" rel="stylesheet" type="text/css" />
<link rel="stylesheet" type="text/css" href="res/css/select2.css" />
<link rel="stylesheet" type="text/css" href="res/css/select2-bootstrap.css" />
<!--<link rel="stylesheet" href="res/css/jquery-ui.css">-->
<link rel="stylesheet" type="text/css" href="res/css/easy-autocomplete.css" />
<!--<link rel="stylesheet" type="text/css" href="res/css/rr.css" />-->

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
<script type="text/javascript" src="res/js/util.js" type="text/javascript"></script>
<script type="text/javascript" src="res/js/editProperty.js?v2"></script>
<link rel="stylesheet" type="text/css" href="res/css/sumoselect.css" />
<script type="text/javascript" src="res/js/api/jquery.easy-autocomplete.js"></script>
<script type="text/javascript" src="res/js/api/num2wordsConverter.js"></script>
<!--<script src="res/js/api/jquery-ui.js"></script>-->
<script type="text/javascript" src="res/js/api/jquery-dateFormat.js"></script>
<script type="text/javascript" src="res/js/MapHandler.js?v2"></script>
<script src="https://apis.mapmyindia.com/advancedmaps/v1/o252iql8zzrhgr9cr8toigzf7j7lkyt8/map_load?v1.1"></script>	
<!--<script type="text/javascript" src="res/js/api/tt.js"></script>-->

<div class="row" id="property_section">
    <div class="col-lg-12 ">
        <div class="innerdashboard">
            <div id="arreartab_div" class="conainer">
                <div class="filterward  d-flex">
                    <h3  class="propeh p-0 m-0 float-left" >Search Property</h3> 
                    <div class="ml-auto"><a href="newProperty" class="btn btn-primary red_new_Property float-right">+ Add New Property</a>
                    </div>
                </div>
                <div class="clearfix"></div>
                <!-- <form id="noticeForm" name="noticeForm" method="post" action="generateNotice1" modelAttribute="noticeBean"> -->
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
                        <div class="col-3">
                            <div class="form-group">
                                <label class="labeltxt">House Number </label>
                                <!--                            <select name="category" id="category" class="form-control">
                                                                <option value="-1">--Select Property Type Name--</option>
                                                            </select>-->
                                <input type="text" id="house_no"  class="form-control" placeholder="">
                            </div>
                        </div> 
                    </div>
                    <div class="col-md-12 text-center">
                        <button class="btn btn-primary" id="btn_property_submit" onclick="EditPro.searchPropertyDetails();">Search</button>
                    </div>
                </div>

            </div>
        </div>
    </div>
</div>


<section class="  hidden" id="searchResults">
    <div class="row">
        <div class="col-lg-12 ">
            <div class="innerdashboard">
                <div id="property_tab_div" class="conainer">
                    <div class="filterward d-flex "> <h3 class="propeh p-0 m-0">Property List</h3> 
                        <a href="javascript: void(0)" class="btn ml-auto mt-0  btn-primary btn-sm" onclick="EditPro.openSearchWindow()"> Back </a></div>  <!-- Use this for open previous div other wise use below -->
                    <!--                    <a href="javascript: void(0)" class="btn ml-auto mt-0  btn-primary btn-sm" onclick="EditPro.openCurUrl()"> Back </a></div>-->
                    <div class="filterother">  
                        <table id="property_tab" data-page-length='10' class="table table-bordered table-sm dataTable no-footer" >
                        </table>
                    </div>
                    <div id="property_tab_div" class="container-fluid contf">
                    </div>
                </div>
                <div class="clearfix"></div>
                <div class="row">
                    <div class="span12 text-center">
                        <!--<a href="javascript: void(0)" class="btn btn-primary" onclick="HomeClass.exportAs('PDF')"> Download as PDF </a>-->
                        <!--<a href="javascript: void(0)" class="btn btn-primary" onclick="HomeClass.exportAs('EXCEL')"> Download as Excel </a>-->
                    </div>
                </div>
            </div>
        </div>
    </div>  
    <!--    <div class="row">
            <div class="col-lg-12 ">
                <div class="innerdashboard">
                    <div id="uu" class="conainer">
                        <div class="filterward d-flex "> <h3 class="propeh p-0 m-0">Property List</h3> 
                            <a href="javascript: void(0)" class="btn ml-auto mt-0  btn-primary btn-sm" onclick="EditPro.openSearchWindow()"> Back </a></div>   Use this for open previous div other wise use below 
                                            <a href="javascript: void(0)" class="btn ml-auto mt-0  btn-primary btn-sm" onclick="EditPro.openCurUrl()"> Back </a></div>
                        <div class="filterother">  
                            <h1></h1>
                            <div id="mawwp"  class="mapProperty">
    
                            </div>
                        </div>
                        <div id="propertysss_tab_div" class="container-fluid contf">
                        </div>
                    </div>
                    <div class="clearfix"></div>
    
                </div>
            </div>
        </div> -->
</section>


<section class="   hidden" id="property_details_tab_div">
    <div class="col-B " id="newProperty">
        <div class="row">
            <div class="col-lg-12  " >
                <div class="innerdashboard">
                    <div class="pull-right ml-auto "><div class="form-group mb-0">

                        </div></div>

                    <div class="filterward pb-3 d-flex" >      
                        <div class="propeh p-0">Property Details</div>
                        <div class=" headerLinkcss text-right ml-auto">
                            <div class="span2" style="float: right;">
                                <a href="javascript: void(0)" class="btn btn-primary" onclick="EditPro.openSearchResults()"> Back </a><!--To open previous div with data loaded -->
                                <!--                                <a href="javascript: void(0)" class="btn btn-primary" onclick="EditPro.openCurUrl()"> Back </a>-->
                            </div>

                            <div class="span2" style="float: right;">    

                                <a id="enableProp" href="javascript: void(0)" data-toggle="tooltip" data-placement="top" title="Click here to edit property." class="btn btn-primary Blue_linear-gradient" onclick="EditPro.enablePropertyDetails();"><i class="material-icons" style="vertical-align: sub;font-size: 18px;">edit</i> Edit Property</a>
                            </div>
                            <div class="span2" style="float: right;">
                                <a id="updateProp" href="javascript: void(0)"  class="btn btn-primary d-none" onclick="EditPro.addNewProperty();">Submit</a>
                            </div>   
                            <div class="span2 " style="float: right;">
                                <a id="collectTax" href="javascript: void(0)"   data-toggle="tooltip" data-placement="top" title="Click here to collect tax." class="btn btn-primary greeen_linear-gradient" onclick="EditPro.collectTax();"><img src="res/img/payment.png" alt="tax" style="margin-right: 5px;vertical-align:text-bottom;">Collect Tax</a>
                            </div>
                            <div class="span2 " style="float: right;">

                                <a id="show_notice"  data-toggle="tooltip" data-placement="top" title="Click here to download notice." class="btn btn-primary red_new_Property disabled" target="abc" ><i class="material-icons" style="vertical-align: sub;font-size: 18px;">assignment_late</i>Download Notice</a>
                            </div>
                            <div class="span2 " style="float: right;">
                                <a style="color:#fff" id="show_mapArea" id="mymodal" class="btn btn-primary "  data-toggle="tooltip" data-placement="top" title="View on Map." data-toggle="modal" data-target="#myModal" onclick="EditPro.plotMarker('in')" ><i class="material-icons" style="vertical-align: sub;font-size: 18px;">map</i>Locate</a>
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
                                <div id="OwnerDetails" class="collapse show" data-parent="#accordion" >
                                    <div class="card-body">
                                        <div class="row">
                                            <div class="col-md-3">
                                                <div class="form-group">
                                                    <label class="labeltxt">Property ID:</label> 
                                                    <input type="text" id="propertyUniqueId"  class="form-control" placeholder="">
                                                </div>
                                            </div>
                                            <div class="col-md-3">
                                                <div class="form-group">
                                                    <label class="labeltxt">Owner's Name: </label> 
                                                    <input type="text" id="propertyOwner"  class="form-control" placeholder="">
                                                </div>
                                            </div>
                                            <div class="col-md-3">
                                                <div class="form-group">
                                                    <label class="labeltxt">Sex: </label> 
                                                    <select   id="ownerSex"  class="form-control" >
                                                        <option value = "-1" selected>Select</option> 
                                                        <option value = "M">Male</option> 
                                                        <option value = "F">Female</option> 
                                                        <option value = "T">Transgender</option> 
                                                    </select>
                                                </div>
                                            </div>

                                            <div class="col-md-3">
                                                <div class="form-group">
                                                    <label class="labeltxt">Father's/Husband's Name:</label> 
                                                    <input  id="propertyOwnerFather" type="text" class="form-control" placeholder="">
                                                </div>
                                            </div>



                                            <div class="col-md-3">
                                                <div class="form-group">
                                                    <label class="labeltxt">Spouse Name: </label> 
                                                    <input type="text" id="propertyOwnerSpouse"  class="form-control" placeholder="">
                                                </div>
                                            </div>

                                            <div class="col-md-3">
                                                <div class="form-group">
                                                    <label class="labeltxt">Mobile No: </label> 
                                                    <input type="text" id="propertyContact"  class="form-control txtboxOnlyNumeric" placeholder="">
                                                </div>
                                            </div>
                                            <div class="col-md-3">
                                                <div class="form-group">
                                                    <label class="labeltxt">Email ID: </label> 
                                                    <input type="text" id="propertyOwnerEmail"  class="form-control" placeholder="">
                                                </div>
                                            </div>
                                            <div class="col-md-3">
                                                <div class="form-group">
                                                    <label class="labeltxt">Aadhaar No.: </label> 
                                                    <input type="text" id="propertyAadharNum"  class="form-control" placeholder="">
                                                </div>
                                            </div>


                                            <div class="col-md-3">
                                                <div class="form-group">
                                                    <label class="labeltxt">Property Owner's Correspondence Address: </label> 
                                                    <input type="text" id="propertyOwnerCorrespAddr"  class="form-control" placeholder="">
                                                </div>
                                            </div>
                                            <div class="col-md-3">
                                                <div class="form-group">
                                                    <label class="labeltxt">Occupier's Name: </label> 
                                                    <input type="text" id="propertyOccupierName"  class="form-control" placeholder="">
                                                </div>
                                            </div>
                                            <div class="col-md-3">
                                                <div class="form-group">
                                                    <label class="labeltxt">Sex: </label> 
                                                    <select   id="occupierSex"  class="form-control" >
                                                        <option value = "-1" selected>Select</option> 
                                                        <option value = "M">Male</option> 
                                                        <option value = "F">Female</option> 
                                                        <option value = "T">Transgender</option> 
                                                    </select>
                                                </div>
                                            </div>
                                            <div class="col-md-3">
                                                <div class="form-group">
                                                    <label class="labeltxt">Occupier Father's/Husband's Name: </label> 
                                                    <input type="text" id="occupoerFather"  class="form-control" placeholder="">
                                                </div>
                                            </div>

                                            <div class="col-md-3">
                                                <div class="form-group">
                                                    <label class="labeltxt">Mobile: </label> 
                                                    <input type="text" id="occupierMobile"  class="form-control" placeholder="">
                                                </div>
                                            </div>
                                            <div class="col-md-3">
                                                <div class="form-group">
                                                    <label class="labeltxt">Email Id: </label> 
                                                    <input type="text" id="occupierEmail"  class="form-control" placeholder="">
                                                </div>
                                            </div>
                                            <div class="col-md-3">
                                                <div class="form-group">
                                                    <label class="labeltxt">Aadhaar No. : </label> 
                                                    <input type="text" id="occupierAadharNo"  class="form-control" placeholder="">
                                                </div>
                                            </div>
                                            <div class="col-md-3">
                                                <div class="form-group">
                                                    <label class="labeltxt">Survey No.: </label> 
                                                    <input type="text" id="smcSurveyNo"  class="form-control" placeholder="">
                                                </div>
                                            </div>


                                            <div class="col-md-3">
                                                <div class="form-group">
                                                    <label class="labeltxt">Plot No.: </label> 
                                                    <input type="text" id="smcPlotNo"  class="form-control" placeholder="">
                                                </div>
                                            </div>
                                            <div class="col-md-3">
                                                <div class="form-group">
                                                    <label class="labeltxt">SMC House Property No.: </label> 
                                                    <input type="text" id="propertyOldSmcPropTaxNum"  class="form-control" placeholder="" >
                                                </div>
                                            </div>



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
                                <div id="ResidingPersonnel" class="collapse"  data-parent="#accordion">

                                    <div class="card-body">
                                        <div class="row">
                                            <div class="col-md-3">
                                                <div class="form-group">
                                                    <label class="labeltxt">No. of Person:</label>
                                                    <input type="text"  id="propertyNumOfPersons" class="form-control txtboxOnlyNumeric" placeholder="">
                                                </div>
                                            </div>
                                            <div class="col-md-3">
                                                <div class="form-group">
                                                    <label class="labeltxt">Male(+18):</label> 
                                                    <input type="text"  id="propertyMale18Plus" class="form-control txtboxOnlyNumeric" placeholder="">
                                                </div>
                                            </div>
                                            <div class="col-md-3">
                                                <div class="form-group">
                                                    <label class="labeltxt">Male(-18):</label> 
                                                    <input type="text"  id="propertyMale18Minus" class="form-control txtboxOnlyNumeric" placeholder="">
                                                </div>
                                            </div>
                                            <div class="col-md-3">
                                                <div class="form-group">
                                                    <label class="labeltxt">Female(+18):</label> 
                                                    <input type="text"  id="propertyFem18Plus" class="form-control txtboxOnlyNumeric" placeholder="">
                                                </div>
                                            </div>

                                            <div class="col-md-3">
                                                <div class="form-group">
                                                    <label class="labeltxt">Female(-18):</label> 
                                                    <input type="text"  id="propertyFem18Minus" class="form-control txtboxOnlyNumeric" placeholder="">
                                                </div>
                                            </div>
                                            <div class="col-md-3">
                                                <div class="form-group">
                                                    <label class="labeltxt">Name1:</label> 
                                                    <input type="text"  id="propertyName1" class="form-control" placeholder="">
                                                </div>
                                            </div>
                                            <div class="col-md-3">
                                                <div class="form-group">
                                                    <label class="labeltxt">Name2:</label> 
                                                    <input type="text"  id="propertyName2" class="form-control" placeholder="">
                                                </div>
                                            </div>
                                            <div class="col-md-3">
                                                <div class="form-group">
                                                    <label class="labeltxt">Name3:</label> 
                                                    <input type="text"  id="propertyName3" class="form-control" placeholder="">
                                                </div>
                                            </div>

                                            <div class="col-md-3">
                                                <div class="form-group">
                                                    <label class="labeltxt">Name4:</label> 
                                                    <input type="text"  id="propertyName4" class="form-control" placeholder="">
                                                </div>
                                            </div>
                                            <div class="col-md-3">
                                                <div class="form-group">
                                                    <label class="labeltxt">Name5:</label> 
                                                    <input type="text"  id="propertyName5" class="form-control" placeholder="">
                                                </div>
                                            </div>
                                            <div class="col-md-3">
                                                <div class="form-group">
                                                    <label class="labeltxt">Name6:</label> 
                                                    <input type="text"  id="propertyName6" class="form-control" placeholder="">
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
                                <div id="PropertyAddress" class="collapse"  data-parent="#accordion" >
                                    <div id="PropertyAddress" class="tab-pane">
                                        <div class="card-body">
                                            <div class="row">
                                                <div class="col-md-3 d-none">
                                                    <div class="form-group">
                                                        <label class="labeltxt">Zone <span style="color: red;">*</span>  </label> 
                                                        <select class="form-control" name="zone" id="zoneId" >
                                                            <option value="-1">--Select Zone--</option>
                                                        </select>
                                                        <!--<input type="text" id="zoneId" class="form-control" placeholder="">-->
                                                    </div>
                                                </div>
                                                <div class="col-md-3  d-none">
                                                    <div class="form-group">
                                                        <label class="labeltxt">Ward<span style="color: red;">*</span> </label> 
                                                        <select class="form-control" name="ward" id="wardId" >
                                                            <option value="-1">--Select Ward--</option>
                                                        </select>
                                                    </div>
                                                </div>

                                                <div class="col-md-3">
                                                    <div class="form-group">
                                                        <label class="labeltxt">Plot No(Survey). </label> 
                                                        <input id="propertyPlotNum" type="text" class="form-control" placeholder="">
                                                    </div>
                                                </div>

                                                <div class="col-md-3">
                                                    <div class="form-group">
                                                        <label class="labeltxt">House No. </label> 
                                                        <input id="propertyHouseNo" type="text" class="form-control" placeholder="">
                                                    </div>
                                                </div>



                                                <div class="col-md-3">
                                                    <div class="form-group">
                                                        <label class="labeltxt">Shop No. </label> 
                                                        <input id="shopNo" type="text" class="form-control" placeholder="">
                                                    </div>
                                                </div>


                                                <div class="col-md-3">
                                                    <div class="form-group">
                                                        <label class="labeltxt">Building Name:</label> 
                                                        <input type="text" id="propertyBuildingName" class="form-control" placeholder="">
                                                    </div>
                                                </div>


                                                <div class="col-md-3">
                                                    <div class="form-group">
                                                        <label class="labeltxt">Road Name/Street Name:</label> 
                                                        <input type="text" class="form-control" id="property_road" placeholder="">
                                                    </div>
                                                </div>
                                                <div class="col-md-3">
                                                    <div class="form-group">
                                                        <label class="labeltxt">Locality Name:</label> 
                                                        <input type="text" id="propertyLocality" class="form-control" placeholder="">
                                                    </div>
                                                </div><div class="col-md-3">
                                                    <div class="form-group">
                                                        <label class="labeltxt">Sub Locality:</label> 
                                                        <input type="text" id="propertyRoad" class="form-control" placeholder="">
                                                    </div>
                                                </div>
                                                <div class="col-md-3">
                                                    <div class="form-group">
                                                        <label class="labeltxt">Nearest Landmark:</label> 
                                                        <input id="propertyLandmark" type="text" class="form-control" placeholder="">
                                                    </div>
                                                </div>



                                                <div class="col-md-3">
                                                    <div class="form-group">
                                                        <label class="labeltxt">Survey No. </label> 
                                                        <input id="propertySurveyId" type="text" class="form-control" placeholder="">
                                                    </div>
                                                </div>
                                                <div class="col-md-3">
                                                    <div class="form-group">
                                                        <label class="labeltxt">Current Status of Property:</label> 
                                                        <input type="text" id="propertyStatus" class="form-control" placeholder="">
                                                    </div>
                                                </div>
                                                <div class="col-md-3">
                                                    <div class="form-group">
                                                        <label class="labeltxt">Pin Code:</label> 
                                                        <input type="text" class="form-control" id="propertyPincode" placeholder="">
                                                    </div>
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
                                <div id="CivicAmenities" class="collapse"  data-parent="#accordion">

                                    <div class="card-body">
                                        <div class="row">

                                            <div class="col-md-3">
                                                <div class="form-group">
                                                    <label class="labeltxt">Total No. of floors:</label> 
                                                    <input  id="propertyTotalFloor" type="text" class="form-control" placeholder="">
                                                </div>
                                            </div>
                                            <div class="col-md-3">
                                                <div class="form-group">
                                                    <label class="labeltxt">No. of Rooms:</label> 
                                                    <input type="text"  id="propertyNumOfRooms" class="form-control" placeholder="">
                                                </div>
                                            </div>
                                            <div class="col-md-3 d-none">
                                                <div class="form-group">
                                                    <label class="labeltxt">School &amp; Colleges:</label> 
                                                    <input type="text"  id="propertyRoomCntSchoolClg" class="form-control" placeholder="">
                                                </div>
                                            </div>
                                            <div class="col-md-3 d-none">
                                                <div class="form-group">
                                                    <label class="labeltxt">Hospital &amp; Nursing Home:</label> 
                                                    <input type="text"  id="propertyRoomCntHospiNurse" class="form-control" placeholder="">
                                                </div>
                                            </div>

                                            <div class="col-md-3 d-none">
                                                <div class="form-group">
                                                    <label class="labeltxt">Hotels:</label> 
                                                    <input type="text"  id="propertyRoomCntHostel" class="form-control" placeholder="">
                                                </div>
                                            </div>
                                            <div class="col-md-3">
                                                <div class="form-group">
                                                    <label class="labeltxt">Plot Area</label>
                                                    <input type="text"  id="propertyPlotArea" class="form-control" placeholder="">
                                                </div>
                                            </div>
                                            <div class="col-md-3">
                                                <div class="form-group">
                                                    <label class="labeltxt">Cost of Property:</label> 
                                                    <input id="propertyCost" type="text" class="form-control" placeholder="">
                                                </div>
                                            </div>
                                        </div>
                                    </div>
                                </div>
                            </div>
                            <div class="card">
                                <div class="card-header">
                                    <a class="collapsed card-link" data-toggle="collapse" href="#FloorDeails">
                                        Floor Details<span class="material-icons right-arrow">keyboard_arrow_down</span>
                                    </a>
                                </div>
                                <div id="FloorDeails" class="collapse"  data-parent="#accordion">
                                    <div class="card-body extra_padding">

                                        <div class="custom_table">
                                            <div class="propeh">Floor wise details</div>
                                            <table id="floorDetatilsTable" class="table">
                                                <thead>
                                                    <tr>
                                                        <th>Sno.</th>
                                                        <!--<th>Floor</th><th>BuiltUp Area</th><th>Constr. Type</th><th>Use</th><th>Water Conn.</th><th>Sewage Conn.</th><th>Electric Meter No.</th><th>Electric Conn. No.</th><th>CCTV</th><th>Fire Equipment</th><th>Lift Available</th><th>RainWater Harvesting</th><th>No. of Borewells</th><th>Sanitation</th><th>Hoarding</th><th>Mobile tower</th><th>Action</th>-->
                                                        <th>Floor</th><th>Property Use</th><th>Property SubType</th><th>Covered/BuiltUp Area</th><th>Construction Type</th><th>Self/Rent</th><th>Actual Annual Rent</th><th>Electric Conn. No.</th><th>Action</th>
                                                    </tr>
                                                </thead>
                                                <tbody>
                                                    <!--<tr><td>1</td><td>GF</td><td>13.51</td><td>ASB</td><td>COMMERCIAL</td><td>YES</td><td>NO</td><td>DNH-003651</td><td>SH/24022</td><td>NO</td><td>NO</td><td>NO</td><td>NO</td><td>0</td><td>YES</td><td>NO</td><td>NO</td></tr>-->
                                                </tbody>
                                            </table>
                                        </div>
                                        <div class="row hidden" id="floorDetatil">
                                            <div class="col-md-3">
                                                <div class="form-group">
                                                    <label class="labeltxt">Floor :</label>
                                                    <input type="text"  id="pfFloorName" class="form-control" placeholder="">
                                                </div>
                                            </div>
                                            <div class="col-md-3">
                                                <div class="form-group">
                                                    <label class="labeltxt">Property Use :<span style="color: red;">*</span> </label> 
                                                    <select class="form-control" id="pfFloorwiseBuildUse">
                                                        <option value="-1">--Select Use--</option>
                                                    </select>
                                                </div>
                                            </div>
                                            <div class="col-md-3">
                                                <div class="form-group">
                                                    <label class="labeltxt">Property SubType:</label>
                                                    <input type="text"  id="pf_property_subtype" class="form-control" placeholder="">
                                                </div>
                                            </div>

                                            <div class="col-md-3">
                                                <div class="form-group">
                                                    <label class="labeltxt">BuiltUp Area :</label>
                                                    <input type="text"  id="pfBuiltupArea" class="form-control txtboxOnlyNumeric" placeholder="">
                                                </div>
                                            </div>
                                            <div class="col-md-3">
                                                <div class="form-group">
                                                    <label class="labeltxt">Construction Type :</label>
                                                    <select class="form-control" id="pfConstructionType">
                                                        <option value="-1">--Select Use--</option>
                                                        <option value="ASB">ASB</option>
                                                        <option value="RCC">RCC</option>
                                                        <option value="OTHER">OTHER</option>
                                                        <option value="VP">VP</option>

                                                    </select>
                                                </div>
                                            </div>
                                            <div class="col-md-3">
                                                <div class="form-group">
                                                    <label class="labeltxt">Self/Rent :</label>
                                                    <select class="form-control" id="selfRent">
                                                        <option value="-1">--Select Use--</option>
                                                        <option value="S">Self</option>
                                                        <option value="R">Rent</option>
                                                    </select>
                                                </div>
                                            </div>
                                            <div class="col-md-3">
                                                <div class="form-group">
                                                    <label class="labeltxt">Actual Annual Rent</label>
                                                    <input type="text"  id="annualRent" class="form-control" placeholder="">
                                                </div>
                                            </div>

                                            <div class="col-md-3">
                                                <div class="form-group">
                                                    <label class="labeltxt">Electric Conn. No. :</label>
                                                    <input type="text"  id="pfElectricConNum" class="form-control" placeholder="">
                                                </div>
                                            </div>

                                        </div>

                                        <div class="row hidden" id="sumitDiscard">
                                            <div class="col-12 text-right">
                                                <a id="updateProp" href="javascript: void(0)" class="btn btn-primary" onclick="EditPro.addFloorDetailsToTable();">Add to Submit</a>
                                                <a id="updateProp" href="javascript: void(0)" class="btn btn-danger" onclick="EditPro.discardFloorDetailsEditing();">Discard to Submit</a>
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
                                <div id="TaxDetails" class="collapse"  data-parent="#accordion">
                                    <div class="card-body">
                                        <div class="row">
                                            <div class="col-md-3">
                                                <div class="panel-heading extra_padding_inner">
                                                    <strong> TAX generated : </strong> <span class="text-muted" id="prop_tax_status">  </span>
                                                </div>
                                                <div class="panel-heading extra_padding_inner">
                                                    <strong> TAX Amount (INR): </strong> <span class="text-muted" id="prop_tax_amount"> </span>
                                                </div>
                                                <div class="panel-heading extra_padding_inner">
                                                    <strong> Pending Arrear Amount(INR): </strong> <span class="text-muted" id="pending_arrear_amount"> </span>
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
                                        <div class="custom_table ">
                                            <div class="propeh">Action History:</div>
                                            <table id="actionHistory" class="table">
                                                <thead>
                                                    <tr>
                                                        <th>Sno.</th><th>Action by</th><th>Date & time</th><th>Action taken</th><th>Action Remarks</th>
                                                    </tr>
                                                </thead>
                                                <tbody>
                                                    <!--<tr><td>1</td><td>GF</td><td>13.51</td><td>ASB</td><td>COMMERCIAL</td><td>YES</td><td>NO</td><td>DNH-003651</td><td>SH/24022</td><td>NO</td><td>NO</td><td>NO</td><td>NO</td><td>0</td><td>YES</td><td>NO</td><td>NO</td></tr>-->
                                                </tbody>
                                            </table>
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

<!---New Section For Collect Payment ------------>
<!--<section class="   hidden" id="property_details_tab_div">
    
    
</section>-->




<!--<section id="taxPaymentView " class="cont_box row hidden">-->
<div class="row hidden" id="taxPaymentView">
    <div class="col-lg-12 pl-4">
        <div class="innerdashboard">
            <div id="arreartab_div" class="conainer">
                <div class="filterward d-flex pb-3" ><h3  class="propeh p-0 m-0"  >Collect Payment</h3>
                    <div class="form-group ml-auto" style="margin-top:0px;">
                        <a onclick="EditPro.validatePayment();" href="#" class="btn btn-default red_new_Property">Collect</a>
                        <a onclick="EditPro.showpropertyWindow();" href="#" class="btn btn-primary">Back</a>
                    </div>
                </div>
                <div class="filterother">
                    <div class="row">
                        <div class="col-md-4">
                            <div class="form-group">
                                <label class="labeltxt"> TAX No. : </label><span class="d-none" id="sl_payment_taxNo" ></span> 
                            </div>
                        </div> 
                        <div class="col-md-4"> 
                            <label class="labeltxt"> Property Id : </label><span id="sl_payment_propId" ></span> 
                        </div>
                        <div class="col-md-4">
                            <div class="form-group">
                                <label class="labeltxt"> Payable amount : </label><span id="sl_payable_amount" ></span> 
                            </div>
                        </div>
                        <div class="col-md-4 ">
                            <div class="form-group">

                                <label class="labeltxt">Paying Amount :  </label>
                                <input id="sl_payment_amount" type="number" value="" min="0" step="0.01" data-number-to-fixed="2" data-number-stepfactor="100" class="form-control currency" />

                            </div>
                        </div>
                        <div class="col-md-4">
                            <div class="form-group">

                                <label class="labeltxt">Arrear Amount :  </label>
                                <input id="sl_arrear_amount" type="number" value="" min="0" step="0.01" data-number-to-fixed="2" data-number-stepfactor="100" class="form-control currency" disabled />

                            </div>
                        </div>
                        <div class="col-md-4">
                            <div class="form-group">

                                <label class="labeltxt">Interest Amount :  </label>
                                <input id="sl_interest_amount" type="number" value="" min="0" step="0.01" data-number-to-fixed="2" data-number-stepfactor="100" class="form-control currency" disabled/>

                            </div>
                        </div>
                        <div class="col-md-4">
                            <div class="form-group">

                                <label class="labeltxt">Tax Amount :  </label>
                                <input id="sl_tax_amount" type="number" value="" min="0" step="0.01" data-number-to-fixed="2" data-number-stepfactor="100" class="form-control currency" disabled />

                            </div>
                        </div>
                        <div class="col-md-4">
                            <div class="form-group">

                                <label class="labeltxt">Payment Mode </label>
                                <select  id="enc_paymentMode" class="form-control">
                                    <option value="-1">-- Select Payment Mode --</option>

                                </select>

                            </div>
                        </div>
                        <div class="col-md-4">
                            <div class="form-group">

                                <label class="labeltxt">Cheque/DD Number/POS Ref No/Bhim UPI <span style="color: red;">*</span></label>
                                <input type="text" id="enc_cheque_dd_num" class="form-control" placeholder="" disabled>

                            </div>
                        </div>
                        <div class="col-md-4">
                            <div class="form-group">

                                <label class="labeltxt">Cheque/DD Issuing Date <span style="color: red;">*</span></label>
                                <input type="text" id="enc_cheque_dd_date" class="form-control" placeholder="" disabled>

                            </div>
                        </div>
                        <div class="col-md-4">
                            <div class="form-group"> 

                                <label class="labeltxt">Bank <span style="color: red;">*</span></label>
                                <select  id="enc_banks" class="form-control">
                                    <option value="-1">-- Select Bank --</option>
                                </select>

                            </div>     
                        </div>
                        <div class="col-md-4">
                            <div class="form-group"> 

                                <label class="labeltxt">Bank Branch<span style="color: red;">*</span></label>
                                <input type="text" id="enc_banks_branch" class="form-control" placeholder="">

                            </div> 

                        </div>

                        <div class="col-md-4">
                            <div class="form-group"> 

                                <label class="labeltxt">IFSC-Code<span style="color: red;">*</span></label>
                                <input type="text" id="enc_banks_ifsc" class="form-control" placeholder="" >

                            </div> 

                        </div>
                        <div class="col-md-4">
                            <div class="form-group"> 

                                <label class="labeltxt">Payee Name<span style="color: red;">*</span></label>
                                <input type="text" id="payerName" class="form-control" placeholder="">

                            </div> 

                        </div>
                        <div class="col-md-4">
                            <div class="form-group"> 


                                <label class="labeltxt">Mobile No.<span style="color: red;">*</span></label>
                                <input type="text" id="contactNo" maxlength="10" class="form-control" placeholder="">

                            </div>            
                        </div>

                        <div class="col-md-4">
                            <div class="form-group"> 


                                <label class="labeltxt">Payment Period<span style="color: red;">*</span></label>
                                <input type="text" id="paymentPeriod" value = '2019-2020' class="form-control" placeholder="">

                            </div>            
                        </div>
                        <div class="col-md-4">
                            <div class="form-group"> 


                                <label class="labeltxt">Remark:</label>
                                <textarea rows="3" id="payment_note" placeholder="Note related to payment, here..." class="form-control" cols="200"> </textarea>

                            </div> 
                        </div>            

                    </div>
                </div>
            </div>
        </div>
    </div>

</div>


<!--<section class="cont hidden" id="submitMesage">-->
<div class="row hidden" id="submitMesage">
    <div class="container-fluid contf" >
        <div class="row-fluid">
            <div class="propeh">Payment Gateway</div>
            <div class="span12 text-center">
                <div class="form-group">
                    <div class="sbu-heading" id="pay_message" ></div>
                </div>
            </div>
            <div class="clearfix"></div>
            <div style="display:none">
                <form name="taxcollectionForm" id="taxcollectionForm" action="printCollectionReceiptPdf" target="_blank" method="POST">
                    <!--             <input id="as_uniqueId1" name="as_uniqueId1" type="text"  >-->
                    <input type='hidden' id="as_uniqueId2hiddeen" name="param1" type="text" value='' >
                    <input type='hidden' id="as_receiptId" name="param2" type="text" value='' >
                    <input type="submit" id="as_expo12" value="Submit"/>

                </form>
            </div>        

            <div class="row-fluid">
                <div class="span12 text-center">
                    <button class="btn btn-success" onclick="EditPro.printCollectionReceipt();">Download Receipt</button>
                    <button class="btn btn-success" onclick="EditPro.showResultWindow();">Back to search results</button>
                </div>
            </div>
        </div>
    </div>
</div>

<!-- Sandeep Added below on 25Mar2017 for show property image -->
<div class="modal fade" id="help-modal" tabindex="-1" role="dialog" aria-labelledby="myModalLabel" aria-hidden="true">
    <div class="modal-dialog">
        <div class="helpmodal-container" style="font-family: 'Helvetica Neue', Helvetica, Arial, sans-serif;">
            <div style='padding-bottom: 15px; font-weight: 600; font-size: 22px'>
                <button type="button" class="close" data-dismiss="modal" aria-hidden="true">X</button>
                <div class="propeh">Property Image</div>
            </div>
            <img src='' width="50%" height="50%" id="cntrimg" /><br/>

            <div id="image-slider-btn">
                <div class="modal-footer"> </div>
                <div class="container-fluid contf" style="background: #f7f7f7;" >
                    <div class="row text-center">
                        <div class="col-md-3">
                            <a href="javascript: void(0)" id="img-prev-btn" class="btn btn-primary" onclick="EditPro.previousImage();"> &lt;&lt Previous </a>
                        </div>
                        <div class="col-md-3"></div>
                        <div class="col-md-3"></div>
                        <div class="col-md-3">
                            <a href="javascript: void(0)" id="img-next-btn" class="btn btn-primary" onclick="EditPro.nextImage();"> Next &gt;&gt</a>
                        </div>
                    </div>
                </div>
            </div>
        </div>
    </div>
</div>  



<!-- Modal -->
<div id="myModal" class="modal fade" role="dialog">
    <div class="modal-dialog modal-lg">

        <!-- Modal content-->
        <div class="modal-content">
            <div class="modal-header">
                <h6 class="modal-title">Property Located on Map</h6>
                
                <button type="button" class="close" data-dismiss="modal">&times;</button>

            </div>
            <div class="modalAlert_map"></div>
            <div class="modal-body">
                <div class="checkBox_onmap">
                    <input type="checkbox"  name="ward_check" id="locl_ward" onclick="EditPro.plotWardGeom(this)">
                    <label for="locl_ward">Ward</label>
                    <input type="checkbox"  name="locality_check" id="locl_Locality" onclick="EditPro.plotWardGeom(this)">
                    <label for="locl_Locality">Locality</label>
                    <input type="checkbox" name="sub_check" id="locl_subLocality" onclick="EditPro.plotWardGeom(this)">
                    <label for="locl_subLocality">Sub Locality</label>   
                </div>
                <div id="map"  class="mapProperty">

                </div>
            </div>
            <div class="modal-footer">
                <!--                <button type="button" class="btn btn-default" data-dismiss="modal">Close</button>-->
            </div>
        </div>

    </div>
</div>

<script>
    $(document).ready(function () {
        $("#objection_main_menu").addClass("active");
        $("#editProperty").addClass("active");
        $("#objection_main_menu ul.sub-menu ").addClass("show");
        if ($("#contactDetails ul.sub-menu").hasClass("show"))
        {
            $("#contactDetails ul.sub-menu").removeClass("show");
        }
        $("#breadlist").addClass('lisb');
        $("#breadlist").html('')
        $("#breadlist").html(' <a href="property"   >Property</a> ');

        $("#enc_banks").attr("disabled", true);
        $("#enc_banks_branch").attr("disabled", true);
        $("#enc_banks_ifsc").attr("disabled", true);
        $("#enc_cheque_dd_date").datepicker({dateFormat: 'yy-mm-dd'});

    });

    $(document).on('keypress', function (e)
    {
        if (e.which == 13)
        {
            EditPro.searchPropertyDetails();
        }
    });

    $(document).ready(function () {
        EditPro.propertyIdFilter();
        EditPro.phoneNoFilter();
        EditPro.cityCodeFilter();
        EditPro.aadharNoFilter();
        EditPro.subLocalityFilter();
        EditPro.wardlstFilter();
        EditPro.ownerlstFilter();
        EditPro.occupierlstFilter();
        EditPro.localitylstFilter();
        EditPro.propertytypeFilter();
        EditPro.houseNo();

    });
</script>
<script type="text/javascript">
    $('#myModal').on('hidden.bs.modal', function () {
        $("#locl_ward").prop('checked', false);
        $("#locl_Locality").prop('checked', false);
        $("#locl_subLocality").prop('checked', false);
        $(".modalAlert_map").html('');
        MapHandler.removeMarkers(EditPro.wardGeomLayer);
        EditPro.wFlag = false;
        MapHandler.removeMarkers(EditPro.locGeomLayer);
        EditPro.lFlag = false;
        MapHandler.removeMarkers(EditPro.subLocGeomLayer);
        EditPro.sLFlag = false;
    });
</script>
<script>
 window.dataLayer = window.dataLayer || [];
 function gtag(){dataLayer.push(arguments);}
 gtag('js', new Date());

 gtag('config', 'UA-24495227-13');
</script>
