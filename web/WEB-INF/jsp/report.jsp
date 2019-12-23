


<%@taglib uri="http://www.springframework.org/tags/form" prefix="form"%>

<link rel="stylesheet" type="text/css" href="res/css/select2.css" />
<link rel="stylesheet" type="text/css" href="res/css/select2-bootstrap.css" />
<link rel="stylesheet" type="text/css" href="res/css/jquery-ui.css" />

<script src="res/js/api/confirmBox.js" type="text/javascript"></script>
<script type="text/javascript" src="res/js/api/select2.min.js"></script>
<script type="text/javascript" src="res/js/api/jquery.dataTables.min.js"></script>
<script type="text/javascript" src="res/js/api/dataTables.buttons.min.js"></script>
<script type="text/javascript" src="res/js/api/jquery-ui.js"></script>
<script type="text/javascript" src="res/js/util.js"></script>
<script type="text/javascript" src="res/js/report.js"></script>

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

<section class="cont" id="filterByWindow">
    <div class="container-fluid contf" >
        <div class="row-fluid text-center">
            <div class="propeh">Reports</div>

            <div class="span12">
                <div >
                    <label class="labeltxt">Filter By:</label> 
                    <select id="rep_filterBy" class="" >
                        <option value='-1'>--Select Filter By--</option>
                        <option value='PD'>Property Details</option>
                        <option value='PU'>Property Uses</option>
                        <option value='PT'>Property TAX</option>
                        <option value='OS'>Objection Status</option>
                    </select>
                </div>
            </div>
        </div>
    </div>
</section>
<section class="cont" id="filterByPropertyDetails">
    <div class="container-fluid contf" >
        <div class="row-fluid">
            <div class="span12">
                <div class="span3">
                    <div class="form-group">
                        <label class="labeltxt">Zone: <span style="color: red;">*</span></label> 
                        <select id="zone" class="form-control" onchange="REPORT.getPropertyDetails();">
                            <option value='-1'>--Select Zone--</option>
                        </select>
                    </div>
                </div>
                <div class="span3">
                    <div class="form-group">
                        <label class="labeltxt">Property Id: </label> 
                        <select id="rep_prop_id" class="form-control">
                            <option value="-1">--Select Property Id--</option>
                        </select>
                    </div>
                </div>
                <div class="span3">
                    <div class="form-group">
                        <label class="labeltxt">PAN No.</label> 
                        <input type="text" id="rep_pan_no" class="form-control" placeholder="PAN No">
                    </div>
                </div>
                <div class="span3">
                    <div class="form-group">
                        <label class="labeltxt">Adhaar No.</label> 
                        <input type="text" id="rep_adhaar" class="form-control" placeholder="Adhaar No">
                    </div>
                </div>
            </div>
        </div>
    </div>
</section>

<section class="cont" id="filterByPropertyUses">
    <div class="container-fluid contf" >
        <div class="row-fluid text-center">
            <div class="span12">
                <div class="span3">
                    <div class="form-group">
                        <label class="labeltxt">Property Category: <span style="color: red;">*</span></label> 
                        <select id="rep_prop_cat" class="form-control" >
                            <option value='-1'>--Property Category--</option>
                        </select>
                    </div>
                </div>
            </div>
        </div>
    </div>
</section>

<section class="cont" >
    <div class="container-fluid contf" >
        <div class="row-fluid">


            <div class="clearfix"></div>
            <div class="span12">

                <div class="span3">
                    <div class="form-group">
                        <label class="labeltxt">Payment Status: </label> 
                        <select id="rep_payment_status" class="form-control">
                            <option value="-1">--Select Payment Status--</option>
                            <option value="-1">Paid</option>
                            <option value="-1">Pending</option>
                            <option value="-1">Pending After Due Date</option>
                        </select>
                    </div>
                </div>
                <div class="span3">
                    <div class="form-group">
                        <label class="labeltxt">Has Arrear : </label> 
                        <select id="rep_payment_status" class="form-control">
                            <option value="-1">--Select Arrear Status--</option>
                            <option value="Y">Yes</option>
                            <option value="N">No</option>
                        </select>
                    </div>
                </div>

            </div>
            <div class="clearfix"></div>
            <div class="span12">
                <div class="span3">
                    <div class="form-group">
                        <label class="labeltxt">Min Tax amount: <span style="color: red;">*</span></label> 
                        <input id="sl_min_amount" type="number" value="" min="0" step="0.01" data-number-to-fixed="2" data-number-stepfactor="100" class="form-control currency" />
                    </div>
                </div>
                <div class="span3">
                    <div class="form-group">
                        <label class="labeltxt">Max Tax amount: <span style="color: red;">*</span></label> 
                        <input id="sl_max_amount" type="number" value="" min="0" step="0.01" data-number-to-fixed="2" data-number-stepfactor="100" class="form-control currency" />
                    </div>
                </div>

            </div>

            <div class="clearfix"></div>
            <div class="span12">
                <div class="span3">
                    <div class="form-group">
                        <label class="labeltxt">From Date <span style="color: red;">*</span></label> 
                        <input type="text" id="rep_fromDate" class="form-control" placeholder="From Date">
                    </div>
                </div>
                <div class="span3">
                    <div class="form-group">
                        <label class="labeltxt">To Date <span style="color: red;">*</span></label> 
                        <input type="text" id="rep_toDate" class="form-control" placeholder="To Date">
                    </div>
                </div>
                <div class="span3">
                    <div class="form-group">
                        <label class="labeltxt">Objection ID</label> 
                        <input type="text" id="rep_objectionId" class="form-control" placeholder="Objection ID">
                    </div>
                </div>
                <div class="span3">
                    <div class="form-group">
                        <label class="labeltxt">Objection Status </label>
                        <select id="rep_objectionStatus" class="form-control">
                            <option value="-1">--Select Status--</option>
                            <option value="APPROVED">Approved</option>
                            <option value="OPEN">Filed</option>
                            <option value="REJECTED">Rejected</option>
                        </select>
                    </div>
                </div>
            </div>
            <!--            <div class="clearfix"></div>
                        <div class="span12 text-center">
                            <hr class="style17">
                        </div>-->


            <div class="span12 text-center">
                <a href="javascript:void(0)" id="searchProp" class="btn btn-danger">Search</a>
            </div>


        </div>

    </div>
</section>

