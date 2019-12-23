<%@taglib uri="http://www.springframework.org/tags/form" prefix="form"%>



<link rel="stylesheet" type="text/css" href="res/css/select2.css" />
<link rel="stylesheet" type="text/css" href="res/css/select2-bootstrap.css" />
<link rel="stylesheet" type="text/css" href="res/css/sumoselect.css" />
<script type="text/javascript" src="res/js/api/select2.min.js"></script>
<script type="text/javascript" src="res/js/api/jquery.dataTables.min.js"></script>
<script type="text/javascript" src="res/js/api/dataTables.buttons.min.js"></script>
<script type="text/javascript" src="res/js/api/buttons.flash.min.js"></script>
<script type="text/javascript" src="res/js/api/jszip.min.js"></script>
<script type="text/javascript" src="res/js/api/pdfmake.min.js"></script>
<script type="text/javascript" src="res/js/api/vfs_fonts.js"></script>
<script type="text/javascript" src="res/js/api/buttons.html5.min.js"></script>
<script type="text/javascript" src="res/js/api/buttons.print.min.js"></script>
<script type="text/javascript" src="res/js/util.js"></script>
<script type="text/javascript" src="res/js/objection_generate.js"></script>



<section class="cont_Object row" id="searchArea">
    <div class="col-lg-12 pl-4">
        <div class="innerdashboard">
            <div id="arreartab_div" class="conainer">
                <div class="filterward pb-3"><h3  class="propeh p-0 m-0"  >New Objection</h3></div>
                <div class="filterother">

                    <div class="row">
                        <div class="col-md-4">
                            <div class="form-group">
                                <label class="labeltxt">Zone: <span style="color: red;">*</span></label> 
                                <select id="zone" class="form-control">
                                    <option value='-1'>--Select Zone--</option>
                                </select>
                            </div>
                        </div>
                        <div class="col-md-4">
                            <div class="form-group">
                                <label class="labeltxt">Ward<span style="color: red;">*</span> </label> 
                                <select class="form-control" name="ward" id="ward" >
                                    <option value="-1">--Select Ward--</option>
                                </select>
                            </div>
                        </div>
                        <div class="col-md-4">
                            <div class="form-group">
                                <label class="labeltxt">Property Id: </label> 
                                <select id="obj_prop_id" class="form-control">
                                    <option value="-1">--Select Property Id--</option>
                                </select>
                            </div>
                        </div>
                    </div>
                    <div class="row">
                        <div class="col-md-4" id="jOwnerId">
                            <div class="form-group">
                                <label class="labeltxt">Owner Name: </label> 
                                <select id="obj_owner" class="form-control">
                                    <option value="-1">--Select Owner Name--</option>

                                </select>
                            </div>
                        </div>
                        <div class="col-md-4" id="jOccupierId">
                            <div class="form-group">
                                <label class="labeltxt">Occupier Name: </label>
                                <select id="obj_occupier" class="form-control">
                                    <option value="-1">--Select Occupier Name--</option>
                                </select>
                            </div>
                        </div>
                    </div>


                    <div class="col-12 text-center">
                        <a href="javascript:void(0)" id="searchProp" class="btn btn-danger">Search</a>
                    </div>

                    <!--Sandeep added below for search objection on 4Jan2017  -->

                </div>
            </div>
        </div>
    </div>
</section>

<section class="cont_b row hidden" id="searchResults">
    <div class="col-lg-12 pl-4">
        <div class="innerdashboard">
            <div id="arreartab_div" class="conainer">
                <div class="filterward pb-3"><h3  class="propeh p-0 m-0"  >New Objection</h3></div>
                <div class="filterother">

                    <div class="row">
                        <div class="col-md-12">

                            <div id="objection_table_div" class="container-fluid contf">
                                <table id="objection_table" d class="table table-bordered table-condensed" >

                                </table>
                            </div>
                        </div>
                        <div class="clearfix"></div>

                        <div class="col-12 text-center">
                            <button class="btn btn-primary" onclick="GEN_OBJ.showSearchWindow();">Back</button>
                        </div>

                    </div>
                </div>
            </div>
        </div>
    </div>
</section>

<section class="cont hidden" id="property_section">
    <div class="col-lg-12 pl-4">
        <div class="innerdashboard">
            <div id="arreartab_div" class="conainer">
                <div class="filterward pb-3"><h3  class="propeh p-0 m-0"  >New Objection</h3></div>
                <div class="filterother">

                    <div class="row">
                        <div class="col-md-12">

                            <div class="col-md-4 ">
                                <div class="panel-heading extra_padding_inner">
                                    <div><strong> Property ID:</strong></div>
                                    <span class="text-muted" id="prop_id">  </span>
                                </div>
                            </div>
                            <div class="col-md-4 ">
                                <div class="panel-heading extra_padding_inner">
                                    <div><strong>Postal Address:</strong></div> 
                                    <span class="text-muted" id="prop_address"> </span>
                                </div>
                            </div>
                            <div class="col-md-4 ">
                                <div class="panel-heading extra_padding_inner">
                                    <div><strong> Owner Name: </strong></div>
                                    <span class="text-muted" id="prop_owner">  </span>
                                </div>
                            </div>
                            <div class="col-md-4 ">
                                <div class="panel-heading extra_padding_inner">
                                    <div><strong>Pin Code:</strong></div> 
                                    <span class="text-muted" id="prop_pincode"> </span>
                                </div>
                            </div>
                            <div class="col-md-4 ">

                                <div class="panel-heading extra_padding_inner">
                                    <div><strong> Occupier Name:  </strong></div>
                                    <span class="text-muted" id="prop_occupier">  </span>
                                </div>
                            </div>
                            <div class="col-md-4 ">
                                <div class="panel-heading extra_padding_inner">
                                    <div><strong>Zone:</strong></div> 
                                    <span class="text-muted" id="prop_zone"> </span>
                                </div>
                            </div>
                            <div class="col-md-4 ">
                                <div class="panel-heading extra_padding_inner">
                                    <div><strong>Ward:</strong></div> 
                                    <span class="text-muted" id="prop_ward"> </span>
                                </div>
                            </div>
                            <div class="col-md-4 ">
                                <div class="panel-heading extra_padding_inner">
                                    <div><strong>Relation With Owner:</strong></div>
                                    <span class="text-muted" id="prop_relation_owner">  </span>
                                </div>
                            </div>
                            <div class="col-md-4 ">
                                <div class="panel-heading extra_padding_inner">
                                    <div><strong>Locality:</strong></div> 
                                    <span class="text-muted" id="prop_sublocality"> </span>
                                </div>
                            </div>
                        </div>
                    </div>
                    <div class="clearfix"></div>
                    <div class="row">

                        <div class="col-md-12 ">
                            <div class="propeh1">Objectionable Attributes</div>
                        </div>

                        <div class="col-md-4 ">
                            <div class="form-group">
                                <label class="labeltxt">Category : <span style="color: red;">*</span></label> 
                                <select id="obj_field_category" class="form-control"> 
                                    <option value="-1">--Select Category--</option>
                                </select>
                            </div>
                        </div>
                        <div class="col-md-4" id="jFloorId">
                            <div class="form-group">
                                <label class="labeltxt">Floor : </label> 
                                <select id="obj_field_floor" class="form-control" disabled>
                                    <option value='-1'>--Select Floor--</option>
                                </select>
                            </div>
                        </div>
                        <div class="col-md-4 ">
                            <div class="form-group">
                                <label class="labeltxt">Attribute : <span style="color: red;">*</span></label> 
                                <select id="obj_field_attr" class="form-control" >
                                    <option value='-1'>--Select Attribute--</option>
                                </select>
                            </div>
                        </div>
                        <div class="col-md-4 ">
                            <div class="form-group">
                                <label class="labeltxt">Current Value</label> 
                                <input type="text" id="obj_field_value" class="form-control1" placeholder="" disabled>
                            </div>
                        </div>
                        <div class="col-md-4 ">
                            <div class="form-group">
                                <label class="labeltxt">&nbsp;</label> 
                                <button class="form-control btn btn-success" id="obj_edit_btn" >Edit</button>
                            </div>
                        </div>
                    </div>
                    <div class="clearfix"></div>
                    <div class="row">
                        <div class="col-md-12 "><div class="propeh1">Objected Attributes</div>
                        </div>
                        <div class="col-md-12 ">  <table class="table table-bordered table-condensed sl_cust_table" id='edit_field_table'>
                                <thead>
                                    <tr>
                                        <th>Category</th>
                                        <th>Attribute</th>
                                        <th>Current Value</th>
                                        <th>New Value</th>
                                        <th>Action</th>
                                    </tr>
                                </thead>
                                <tbody>
                                    <tr class="no_data_row"><td colspan="5"><center>No data available</center></td>
                                </tbody>
                            </table>
                        </div>
                        <div class="col-md-12 ">
                            <div class="propeh1">Objected Applied By</div>
                        </div>
                        <div class="col-md-4 ">

                            <div class="form-group">
                                <label class="labeltxt">Name:  <span style="color: red;">*</span></label> 
                                <input type="text" id="obj_appliedBy" class="form-control" placeholder="">
                            </div>
                        </div>
                        <div class="col-md-4 ">
                            <div class="form-group">
                                <!--<label class="labeltxt">Objection Applied By ID : <span style="color: red;">*</span></label>--> 
                                <label class="labeltxt"> Relation: <span style="color: red;">*</span></label> 
                                <select id="obj_appliedRelation" class="form-control" onchange="GEN_OBJ.OtherHideShow();" >
                                    <option value='-1'>--Select Relation--</option>
                                </select>
                            </div>
                        </div>
                        <div class="col-md-4 " id="jIfOther">
                            <div class="form-group">
                                <!--<label class="labeltxt">Objection Applied By ID : <span style="color: red;">*</span></label>--> 
                                <label class="labeltxt"> If Others:</label> 
                                <input type="text" class="form-control" id="obj_appliedRelationIfOther" placeholder="Please enter Other-mention" disabled>
                            </div>
                        </div>
                        <div class="col-md-4 ">
                            <div class="form-group">
                                <!--<label class="labeltxt">Objection Applied By ID : <span style="color: red;">*</span></label>--> 
                                <label class="labeltxt"> ID Proof: <span style="color: red;">*</span></label> 
                                <select id="obj_appliedByIdType" class="form-control" >
                                    <option value='-1'>--Select Document--</option>
                                </select>
                            </div>
                        </div>
                        <div class="col-md-4 ">
                            <div class="form-group">
                                <!--<label class="labeltxt">Objection Applied By ID No.:  <span style="color: red;">*</span></label>--> 
                                <label class="labeltxt"> ID Number:  <span style="color: red;">*</span></label> 
                                <input type="text" id="obj_appliedById" class="form-control" placeholder="">
                            </div>
                        </div>
                    </div>
                    <div class="clearfix"></div>
                    <div class="panel-heading extra_padding_inner">
                        <div><strong>Remarks:<span style="color: red;">*</span></strong></div> 
                        <textarea rows="6" id="obj_remarks" placeholder="Remarks by system user. It includes queries raised, list of supporting document etc." class="form-control1" cols="200"> </textarea>
                    </div>
                    <div class="text-center">
                        <button class="btn btn-danger" id="obj_submit" >Submit</button>
                        <button class="btn btn-success"  onclick="GEN_OBJ.showResultWindow();">Back</button>
                    </div>
                </div>
            </div>
        </div>
    </div>


</section>


<section class="cont hidden" id="submitMesage">
    <div class="container-fluid contf" >
        <div class="row-fluid">
            <div class="propeh">New Objection</div>
            <div class="span12 text-center">
                <div class="form-group">
                    <div class="sbu-heading" id="obj_message" ></div>
                </div>
            </div>
            <div class="clearfix"></div>
            <div class="row-fluid">
                <div class="span12 text-center">
                    <button class="btn btn-success" onclick="GEN_OBJ.printObjectionReceipt();">Download Receipt</button>
                    <button class="btn btn-dark" onclick="GEN_OBJ.showResultWindow();">Back to search results</button>
                </div>
            </div>
        </div>
    </div>
</section>


<script>

    $(".view_tax").click(function () {
        $(".tax_calc").slideToggle("slow", function () {
        });


    });
</script>

<style type="text/css">
    .fl_td {
        border: 1px solid black;
    }
    .do_not_edit:hover {
        cursor: pointer;
    }
</style>


