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
<script src="res/js/tax_view.js?v1" type="text/javascript"></script>

<script type="text/javascript" src="res/js/commonSearch.js?v1"></script>
<script type="text/javascript" src="res/js/api/jquery.easy-autocomplete.js"></script>

<section class="cont_viewTax">
    <div class="col-lg-12 p-0">
        <div class="innerdashboard">
            <div id="arreartab_div" class="conainer">
                <div id="tax_comp_div">
                    <div class="filterward d-flex"><h3  class="propeh p-0 m-0"  >Assessment Register</h3>
                    </div>
                    <div class="filterother">
                        <div class="row"> 


                            <div class="col-md-12">
                                <div class="sbu-heading"></div>
                                <div class="row">
                                    <!--                                <div class="col-md-4 d-none"> 
                                                                        <div class="form-group">
                                                                            <label class="labeltxt">Zone</label> 
                                                                            <select class="form-control" id="prop_tax_zone">
                                                                                <option value="-1">--Select Zone--</option>
                                                                            </select>
                                                                        </div>
                                                                    </div>
                                                                    <div class="col-md-4"> 
                                                                        <div class="form-group">
                                                                            <label class="labeltxt">Ward</label> 
                                                                            <select class="form-control" id="prop_tax_ward">
                                                                                <option value="-1">--Select Ward--</option>
                                                                            </select>
                                                                        </div>
                                                                    </div>
                                                                    <div class="col-md-1 text-center"> 
                                                                        <div class="or_dv"><label>&nbsp;</label><br>
                                                                            <span>OR</span>
                                                                        </div>
                                                                    </div>
                                                                    <div class="col-md-3"> 
                                                                        <div class="form-group">
                                                                            <label class="labeltxt">Enter Property ID</label> <input
                                                                                id="prop_tax_propid" type="text" class="form-control"
                                                                                placeholder="">
                                                                        </div>
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

                                    <div class="col-md-12 text-center"><br>
                                        <a href="javascript:void(0)" class="btn btn-primary" onclick="TaxGeneration.viewTax();" id="btnviewTax">View Tax</a>
                                        <a href="javascript:void(0)" class="btn btn-primary ml-3" onclick="TaxGeneration.assessMentList();" id="btnviewTaxAssessment">Assessment List</a>
                                    </div>
                                </div>
                            </div>


                        </div>
                    </div>
                </div>
                <div id="tax_table_div" style="display: none;">
                    <div class="filterward d-flex"><h3  class="propeh p-0 m-0"  >Assessment Register</h3>
                        <div class="ml-auto">
                            <div class="span12 text-center">
                                <a href="javascript: void(0)" class=" btn btn-primary red_new_Property" onclick="TaxGeneration.exportAs('PDF')"> Download as PDF </a>
                                <a href="javascript: void(0)" class="btn btn-primary greeen_linear-gradient" onclick="TaxGeneration.exportAs('EXCEL')"> Download as Excel </a>
<!--                                <a href="javascript: void(0)" class="btn btn-primary" onclick="TaxGeneration.goBack();"> Back </a>--> <!--To open previous div use this-->
                                <a href="taxView" class="btn btn-primary"> Back </a>
                            </div>
                        </div>
                    </div>
                    <div class="filterother">
                        <table id="tax_table" class="table table-condensed table-bordered   table-hover "> </table>
                        
                    </div>

                </div>

                <div class="hidden">
                    <form method="POST"  id="as_expo" action="assessMentList">
                        <!--                        <input type="hidden" id="as_zone" name="zone">
                                                <input type="hidden" id="as_ward" name="ward">
                                                <input type="hidden" id="as_uniqueId" name="uniqueId">
                                                <input type="hidden" id="as_type" name="type">
                                                <input type="hidden" id="as_title" name="title">
                                                <input type="hidden" id="as_windowId" name="windowId" value="" />  -->
                        <input type="hidden" id="as_zone" name="zone">
                        <input type="hidden" id="as_ward" name="ward">
                        <input type="hidden" id="as_prop_id_input" name="prop_id_input">
                        <input type="hidden" id="as_occ_name" name="occ_name">
                        <input type="hidden" id="as_ownerid" name="ownerid">
                        <input type="hidden" id="as_locality" name="locality">
                        <input type="hidden" id="as_src_aadhar_no" name="src_aadhar_no">
                        <input type="hidden" id="category" name="category">
                        <input type="hidden" id="as_Phone_No" name="Phone_No">
                        <input type="hidden" id="as_Locality" name="Locality">
                        <input type="hidden" id="as_type" name="type">
                        <input type="hidden" id="as_title" name="title">
                        <input type="hidden" id="as_windowId" name="windowId" value="" /> 
                    </form>
                    <button id="export_btn"  class="btn btn-primary btn-lg btn-block" onclick="" >Export</button>
                </div>

            </div>
        </div>
    </div>
</section>
<script>
    $(document).ready(function () {
        $("#report_main_menu").addClass("active");
        $("#assessmenttaxView").addClass("active");
        $("#report_main_menu ul.sub-menu ").addClass("show");
        $("#breadlist").addClass('lisb');
        $("#breadlist").html('')
        $("#breadlist").html(' <a href="taxView">Assessment Register</a> ');

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
</script>


