<%@taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@ page import="java.io.*,java.util.*, javax.servlet.*,java.text.SimpleDateFormat" %>

<link rel="stylesheet" type="text/css" href="res/css/select2.css" />
<link rel="stylesheet" type="text/css" href="res/css/select2-bootstrap.css" />
<link rel="stylesheet" type="text/css" href="res/css/jquery-ui.css" />
<link rel="stylesheet" type="text/css" href="res/css/sumoselect.css" />
<script src="res/js/api/confirmBox.js" type="text/javascript"></script>
<script type="text/javascript" src="res/js/api/select2.min.js"></script>
<script type="text/javascript" src="res/js/api/jquery.dataTables.min.js"></script>
<script type="text/javascript" src="res/js/api/dataTables.buttons.min.js"></script>
<script type="text/javascript" src="res/js/api/buttons.flash.min.js"></script>
<script type="text/javascript" src="res/js/api/jszip.min.js"></script>
<script type="text/javascript" src="res/js/api/pdfmake.min.js"></script>
<script type="text/javascript" src="res/js/api/vfs_fonts.js"></script>
<script type="text/javascript" src="res/js/api/buttons.html5.min.js"></script>
<script type="text/javascript" src="res/js/api/buttons.print.min.js"></script>
<script type="text/javascript" src="res/js/api/jquery-ui.js"></script>
<script type="text/javascript" src="res/js/util.js"></script>
<script type="text/javascript" src="res/js/objection_allocation.js"></script>

<%@page import="java.util.Date"%>
<%@page import="java.text.SimpleDateFormat"%>
<%@page import="java.util.Calendar"%>

<%
    Calendar calendar = Calendar.getInstance();
    //calendar.add(Calendar.DAY_OF_MONTH, 1);
%>

<script>
    DATEPICKER.today = new Date('<%= calendar.get(Calendar.YEAR)%>', '<%= calendar.get(Calendar.MONTH)%>', '<%= calendar.get(Calendar.DAY_OF_MONTH)%>');
</script>

<section class="cont_new row" id="searchArea">
    <div class="col-lg-12 pl-4">
        <div class="innerdashboard">
            <div id="arreartab_div" class="conainer">
                <div class="filterward pb-3"><h3  class="propeh p-0 m-0" align="center">Objection Manager</h3></div>



                <div class="filterother">
                    <div class="row">
                        <div class="col-md-4">
                            <div class="form-group">
                                <label class="labeltxt">From Date <span style="color: red;">*</span></label> 
                                <input type="text" id="obj_fromDate" class="form-control" placeholder="From Date">
                            </div>
                        </div>
                        <div class="col-md-4">
                            <div class="form-group">
                                <label class="labeltxt">To Date <span style="color: red;">*</span></label> 
                                <input type="text" id="obj_toDate" class="form-control" placeholder="To Date">
                            </div>
                        </div>
                        <div class="col-md-4">
                            <div class="form-group">
                                <label class="labeltxt">Objection ID</label> 
                                <input type="text" id="obj_objectionId" class="form-control" placeholder="Objection ID">
                            </div>
                        </div>
                    </div>
                    <div class="row">
                        <div class="col-md-4">
                            <div class="form-group">
                                <label class="labeltxt">Objection Status </label>
                                <select id="obj_objectionStatus" class="form-control">
                                    <option value="-1">All</option>
                                    <option value="APPROVED">Approved</option>
                                    <option value="OPEN">Pending</option>
                                    <option value="REJECTED">Rejected</option>
                                </select>
                            </div>
                        </div>
                    </div> 
                    <hr class="style17">

                    <div class="row">
                        <div class="col-md-4">
                            <div class="form-group">
                                <label class="labeltxt">Zone: <span style="color: red;">*</span></label> 
                                <select id="zone" class="form-control" >
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
                        <div class="col-md-4">
                            <div class="form-group">
                                <label class="labeltxt">Owner Name: </label> 
                                <select id="obj_owner" class="form-control">
                                    <option value="-1">--Select Owner Name--</option>

                                </select>
                            </div>
                        </div>
                        <div class="col-md-4">
                            <div class="form-group">
                                <label class="labeltxt">Occupier Name: </label>
                                <select id="obj_occupier" class="form-control">
                                    <option value="-1">--Select Occupier Name--</option>
                                </select>
                            </div>
                        </div>
                    </div>
                    <div class="span12 text-center">
                        <a href="javascript:void(0)" id="searchProp" class="btn btn-primary">Search</a>
                    </div>
                </div>
            </div>
        </div>
    </div>
</section>

<section class="cont_new row hidden" id="searchResults">
    <div class="col-lg-12 pl-4">
        <div class="innerdashboard">
            <div id="arreartab_div" class="conainer">
                <div class="filterward pb-2"><h3  class="propeh p-0 m-0" align="center">Objection Manager</h3></div>



                <div class="filterother">
                    <div class="span12">
                        <div id="objection_table_div" class="container-fluid contf">
                            <table id="objection_table" data-page-length='100' class="table table-bordered table-sm" >

                            </table>
                        </div>
                    </div>
                    <div class="clearfix"></div>
                    <div class="row-fluid">
                        <div class="span12 text-center">
                            <!--                    <a href="javascript: void(0)" class="btn btn-primary" onclick="ALLOCATE_OBJ.exportAs('PDF')"> Download as PDF </a>
                                                <a href="javascript: void(0)" class="btn btn-primary" onclick="ALLOCATE_OBJ.exportAs('EXCEL')"> Download as Excel </a>-->
                            <button class="btn btn-primary" onclick="ALLOCATE_OBJ.showSearchWindow();">Back</button>
                        </div>
                    </div>
                </div>
            </div>
        </div>
    </div>

</section>

<section class="cont row hidden" id="property_section">
    <div class="col-lg-12 pl-4">
        <div class="innerdashboard">
            <div id="arreartab_div" class="conainer">
                <div class="filterward pb-3"><h3  class="propeh p-0 m-0" align="center">Objection Manager</h3></div>



                <div class="filterother">
                    <div class="span12 text-center">
                        <div class="sbu-heading" id="currentOpenCaseId"></div>
                    </div>

                    <div class="row">
                        <div class="col-md-4 ">
                            <label class="labeltxt">Property ID:</label>
                                <div class="text-muted" id="prop_id">  </div>
                        </div>
                            <div class="col-md-4 ">
                                <label class="labeltxt">Postal Address:</label> 
                                <div class="text-muted" id="prop_address"> </div>
                            </div>
                        
                        <div class="col-md-4 ">
                            <label class="labeltxt">Owner Name: </label>
                                <div  class="text-muted" id="prop_owner">  </div>
                                  </div>
                    </div>
                    <div class="row">
                           <div class="col-md-4 ">
                               <label class="labeltxt">Pin Code:</label> 
                                <span class="text-muted" id="prop_pincode"> </span>
                            </div>
                      
                        <div class="col-md-4 ">
                            <label class="labeltxt">Occupier Name:  </label>
                                <div  class="text-muted" id="prop_occupier">  </div>
                            </div>
                              <div class="col-md-4 ">
                                  <label class="labeltxt">Zone:</label>
                                <div  class="text-muted" id="prop_zone"> </div>
                            </div>
                            <div class="clearfix"></div>
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
                            <div class="clearfix"></div>
                            <div class="panel-heading extra_padding_inner">
                                <div><strong>Locality:</strong></div> 
                                <span class="text-muted" id="prop_sublocality"> </span>
                            </div>
                        </div>
                    </div>
                    <div class="clearfix"></div>


                    <div class="span12">
                        <div class="propeh1">Objected Attributes</div>
                        <table class="table sl_cust_table" id='edit_field_table'>
                            <thead>
                                <tr>
                                    <th>Attribute</th>
                                    <th>Current Value</th>
                                    <th>New Value</th>
                                </tr>
                            </thead>
                            <tbody>
                                <tr class="no_data_row"><td colspan="5"><center>No data available</center></td>
                            </tbody>
                        </table>

                    </div>

                    <div class="clearfix"></div>
                    <div class="row-fluid span12">
                        <div class="propeh1">Objected Applied By</div>
                        <div class="col-md-4">
                            <div class="form-group">
                                <label class="labeltxt">Objection Applied By Name:  <span style="color: red;">*</span></label> 
                                <input type="text" id="obj_appliedBy" class="form-control" placeholder="" disabled="">
                            </div>
                        </div>
                        <div class="col-md-4">
                            <div class="form-group">
                                <label class="labeltxt">Objection Applied By ID : <span style="color: red;">*</span></label> 
                                <select id="obj_appliedByIdType" class="form-control" disabled="">
                                    <option value='-1'>--Select Document--</option>
                                </select>
                            </div>
                        </div>
                        <div class="col-md-4">
                            <div class="form-group">
                                <label class="labeltxt">Objection Applied By ID No.:  <span style="color: red;">*</span></label> 
                                <input type="text" id="obj_appliedById" class="form-control" placeholder="" disabled="">
                            </div>
                        </div>
                    </div>
                    <div class="clearfix"></div>
                    <div class="panel-heading extra_padding_inner">
                        <div><strong>Remarks:</strong></div> 
                        <textarea rows="6" id="obj_remarks" placeholder="Remarks by system user. It includes queries raised, list of supporting document etc." class="form-control1" cols="200" disabled> </textarea>
                    </div>
                    <div class="clearfix"></div>
                    <div class="span12">
                        <div class="propeh1">Decision History</div>
                        <table class="table sl_cust_table" id='decision_history'>
                            <thead>
                                <tr>
                                    <th>Action On</th>
                                    <th>Action By</th>
                                    <th>Action</th>
                                    <th>Forward To (If any)</th>
                                    <th>Remarks</th>
                                </tr>
                            </thead>
                            <tbody>
                                <tr class="no_data_row"><td colspan="5"><center>No action performed till date</center></td>
                            </tbody>
                        </table>

                    </div>
                    <div class="clearfix"></div>
                    <div class="text-center">
                        <hr class="decision_seperator">
                    </div>
                    <div class="clearfix"></div>
                    <div class="text-left">
                        <div class="panel-heading extra_padding_inner">

                            <div class="col-md-4">
                                <div class="form-group">
                                    <div><strong>Decision<span style="color: red;">*</span></strong></div> 
                                    <select id="obj_objectionAction" class="">
                                        <option value="-1">--Select Decision--</option>
                                        <option value="FORWARD">Forward</option>
                                        <option value="APPROVED">Approve</option>
                                        <option value="REJECTED">Reject</option>
                                    </select>
                                </div>
                            </div>
                            <div class="col-md-4 hidden" id="forward_to_div">
                                <div class="form-group">
                                    <div><strong>Forward To<span style="color: red;">*</span></strong></div> 
                                    <select id="forward_to_users" class="">
                                        <option value="-1">--Select User--</option>                            
                                    </select>
                                </div>
                            </div>


                        </div>
                    </div>
                    <div class="clearfix"></div>
                    <div class="panel-heading extra_padding_inner">
                        <div><strong>Decision Remarks<span style="color: red;">*</span></strong></div> 
                        <textarea rows="6" id="obj_decision_remarks" placeholder="Remarks by system user. It includes queries raised, list of supporting document etc." class="form-control1" cols="200"> </textarea>
                    </div>
                    <div class="clearfix"></div>
                    <div class="text-center">
                        <div class="row-fluid span12">
                            <button class="btn btn-danger" id="obj_submit" >Submit Decision</button>
                            <div class="btn btn-success" id="printObjection" onclick="ALLOCATE_OBJ.printObjection();">Print A Copy</div>
                            <button class="btn btn-success"  onclick="ALLOCATE_OBJ.showResultWindow();">Back</button>
                        </div>
                    </div>
                </div>
            </div>
        </div>
    </div>

</section>


<section class="contBox row hidden" id="submitMesage">
    <div class="col-lg-12 pl-4">
        <div class="innerdashboard">
            <div id="arreartab_div" class="conainer">
                <div class="filterward pb-3"><h3  class="propeh p-0 m-0" align="center">Objection Manager</h3></div>
                <div class="filterother">
                    <div class="span12 text-center">
                        <div class="form-group">
                            <div class="sbu-heading" id="obj_message" ></div>
                        </div>
                    </div>
                    <div class="clearfix"></div>
                    <div class="row-fluid">
                        <div class="span12 text-center">
                            <button class="btn btn-success" onclick="ALLOCATE_OBJ.showResultWindow();">Back to search results</button>
                        </div>
                    </div>
                </div>
            </div>
        </div>
    </div>
</section>

