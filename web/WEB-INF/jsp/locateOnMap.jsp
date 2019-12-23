<link rel="stylesheet" type="text/css" href="res/css/easy-autocomplete.css" />
<link href="res/css/datatable2.css" rel="stylesheet" type="text/css" />
<link href="res/css/buttons.dataTables.min.css" rel="stylesheet" type="text/css" />
<script type="text/javascript" src="res/js/api/jquery.dataTables.min.js"></script>
<script type="text/javascript" src="res/js/api/dataTables.buttons.min.js"></script>
<script type="text/javascript" src="res/js/api/buttons.flash.min.js"></script>
<script type="text/javascript" src="res/js/api/jszip.min.js"></script>
<script type="text/javascript" src="res/js/api/pdfmake.min.js"></script>
<script type="text/javascript" src="res/js/api/vfs_fonts.js"></script>
<script type="text/javascript" src="res/js/api/buttons.html5.min.js"></script>
<script type="text/javascript" src="res/js/api/buttons.print.min.js"></script>
<script src="res/js/util.js" type="text/javascript"></script>
<script src="res/js/locateOnMap.js?v1" type="text/javascript"></script>

<script type="text/javascript" src="res/js/commonSearch.js?v1"></script>
<script type="text/javascript" src="res/js/api/jquery.easy-autocomplete.js"></script>
<script type="text/javascript" src="res/js/MapHandler.js?v2"></script>
<script src="https://apis.mapmyindia.com/advancedmaps/v1/o252iql8zzrhgr9cr8toigzf7j7lkyt8/map_load?v1.1"></script>	
<section id="searchPropertySection">
    <div class="col-lg-12 p-0">
        <div class="innerdashboard">
            <div id="arreartab_div" class="conainer">
                <div id="tax_comp_div">
                    <div class="filterward d-flex"><h3  class="propeh p-0 m-0"  >View Property On Map</h3>
                    </div>
                    <div class="filterother">
                        <div class="row"> 


                            <div class="col-md-12">
                                <div class="sbu-heading"></div>
                                <div class="row">

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
                                            <input type="text" id="category"  name="category" class="form-control" placeholder="">
                                        </div>
                                    </div> 

                                    <div class="col-md-12 text-center"><br>
                                        <a href="javascript:void(0)" class="btn btn-primary" onclick="LocateMap.searchPropertyDetails();" id="btnviewTax">Search</a>
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
<section class="hidden" id="mapSection">
    <div class="row">
        <div class="col-lg-12 ">
            <div class="innerdashboard">
                <div id="property_tab_div" class="conainer">
                    <div class="filterward d-flex "> <h3 class="propeh p-0 m-0">Map View</h3> 
                        <div class="headerSectionMsg"> </div>
                        <a href="javascript: void(0)" class="btn ml-auto mt-0  btn-primary btn-sm" onclick="LocateMap.openSearchWindow()"> Back </a></div>  <!-- Use this for open previous div other wise use below -->
                    <!--                    <a href="javascript: void(0)" class="btn ml-auto mt-0  btn-primary btn-sm" onclick="EditPro.openCurUrl()"> Back </a></div>-->
                    <div class="filterother">  
                        <div class="checkBox_onmap">
                            <input type="checkbox"  name="ward_check" id="locl_ward" onclick="LocateMap.plotWardGeom(this)">
                            <label for="locl_ward">Ward</label>
                            <input type="checkbox"  name="locality_check" id="locl_Locality" onclick="LocateMap.plotWardGeom(this)">
                            <label for="locl_Locality">Locality</label>
                            <input type="checkbox" name="sub_check" id="locl_subLocality" onclick="LocateMap.plotWardGeom(this)">
                            <label for="locl_subLocality">Sub Locality</label>   
                        </div>
                        <div id ="map" class="mapProperty"></div>
                    </div>
                </div>

            </div>
        </div>
    </div>  
</section>


<script>
    $(document).ready(function () {
        $("#locateOnMapMainMenu").addClass("active");
        $("#mapViewSubMenu").addClass("active");
        $("#locateOnMapMainMenu ul.sub-menu ").addClass("show");

        $("#breadlist").html('');
        $("#breadlist").addClass('lisb');
        $("#breadlist").html('<li><a href="locateOnMap">Locate On Map</a></li>');

        MapHandler.initialise('map', 20.27, 73.01, 14);
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

//$('document').on('click', input[type="checkbox"], function() {
//alert("mjhk");
//});
//    $("document").on("click", "input[type='checkbox']", function () {
//        alert("mjhk");
//    });


</script>
<script>
 window.dataLayer = window.dataLayer || [];
 function gtag(){dataLayer.push(arguments);}
 gtag('js', new Date());

 gtag('config', 'UA-24495227-13');
</script>

