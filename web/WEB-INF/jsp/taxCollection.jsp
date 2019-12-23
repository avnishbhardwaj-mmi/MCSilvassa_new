

<link rel="stylesheet" type="text/css" href="res/css/select2.css" />
 <link href="res/css/datatable2.css" rel="stylesheet" type="text/css" />

 <link href="res/css/buttons.dataTables.min.css" rel="stylesheet" type="text/css" />
<link rel="stylesheet" type="text/css" href="res/css/sumoselect.css" />

<script src="res/js/api/jquery-ui.js"></script>
<script src="res/js/api/select2.min.js"></script>
<script type="text/javascript" src="res/js/util.js"></script>
<script src="res/js/tax_collection.js?v7"></script>
<script type="text/javascript" src="res/js/api/num2wordsConverter.js"></script>
<link rel="stylesheet" type="text/css" href="res/css/easy-autocomplete.css" />


<script src="res/js/api/jquery.dataTables.min.js"></script>
<script src="res/js/api/dataTables.bootstrap.min.js"></script>
<script type="text/javascript" src="res/js/api/jquery.dataTables.min.js"></script>
<script type="text/javascript" src="res/js/api/dataTables.buttons.min.js"></script>
<script type="text/javascript" src="res/js/api/buttons.flash.min.js"></script>
<script type="text/javascript" src="res/js/api/jszip.min.js"></script>
<script type="text/javascript" src="res/js/api/pdfmake.min.js"></script>
<script type="text/javascript" src="res/js/api/vfs_fonts.js"></script>
<script type="text/javascript" src="res/js/api/buttons.html5.min.js"></script>
<script type="text/javascript" src="res/js/api/jquery-dateFormat.js"></script>
<script type="text/javascript" src="res/js/api/jquery.easy-autocomplete.js"></script>



<section id="searchArea" class="cont_div row_0 hidden">
    <div class="col-lg-12 p-0">
        <div class="innerdashboard">
            <div id="arreartab_div" class="conainer">
                <div class="filterward pb-3 d-flex d-none"><h3  class="propeh p-0 m-0"  >Tax Collection</h3> <div class="ml-auto d-none"><a href="#" class="btn btn-primary red_new_Property">+ Add New Property</a></div></div>
                <div class="filterother">
                    <div class="row">
                        <div class="col-md-3 d-none">  
                            <div class="form-group mb-0">
                                <label class="labeltxt">Zone <span style="color: red;">*</span></label> 
                                <select class="form-control" name="zone" id="zone">
                                    <option value="-1">--Select Zone --</option>
                                </select>
                            </div>
                        </div>


                        <div class="col-md-3">
                            <div class="form-group">
                                <label class="labeltxt">Property Id</label>
                                <input type="text" id="prop_id_input" class="form-control check" placeholder="">
                            </div>
                        </div>

                        <!--                        <div class="col-md-3">  
                        
                                                    <div class="form-group mb-0">
                                                        <label class="labeltxt">Ward<span style="color: red;">*</span> </label> 
                                                        <select class="form-control" name="ward" id="ward" >
                                                            <option value="-1">--Select Ward--</option>
                                                        </select>
                                                    </div>
                                                </div>-->
                        <div class="col-md-3">
                            <div class="WardscT">
                                <label>Ward<span style="color: red;"></span></label>
                                <!--                            <select class="form-control" name="ward" id="ward">
                                                                <option> --Select Ward--</option>
                                                            </select>-->
                                <input type="text" id="ward"  class="form-control" placeholder="">
                            </div>
                        </div>

                        <div class="col-md-3 d-none">
                            <div class="form-group">
                                <label class="labeltxt">Property ID </label> 
                                <select class="form-control" name="propertyid" id="propertyid">
                                    <option value="-1">--Select Property ID--</option>
                                </select>
                            </div>
                        </div>
                        <div class="col-md-3">
                            <div class="form-group">
                                <label class="labeltxt">Owner Name </label> 
                                <input type="text" id="ownerid"  class="form-control" placeholder="">
                            </div>
                        </div>
                        <div class="col-md-3">
                            <div class="form-group">
                                <label class="labeltxt">Occupier Name </label> 
                                <!--                                <select name="occupierid" id="occupierid" class="form-control">
                                                                    <option value="-1">--Select Occupier Name --</option>
                                                                </select>-->
                                <input type="text" id="occ_name"  class="form-control" placeholder="">
                            </div>
                        </div>
                        <div class="col-md-3">
                            <div class="form-group">
                                <label class="labeltxt">Phone No.</label>
                                <input type="text" id="Phone_No" placeholder="Phone No." class="form-control" placeholder="">
                            </div>
                        </div>
                        <div class="col-md-3">
                            <div class="form-group">
                                <label class="labeltxt">Easy City Code</label>
                                <input type="text" id="Easy_City_Code" disabled placeholder="Easy City Code" class="form-control" placeholder="">
                            </div>
                        </div>


                        <div class="col-md-3">
                            <div class="form-group">
                                <label class="labeltxt">Locality</label> 
                                <input type="text" id="locality"  class="form-control" placeholder="">
                            </div>
                        </div>
                        <div class="col-3">
                            <div class="form-group">
                                <label class="labeltxt">Sub Locality</label>
                                <input type="text" id="Locality" placeholder="Sub Locality" class="form-control" placeholder="">
                            </div>
                        </div>


                        <div class="col-md-3">
                            <div class="form-group">
                                <label class="labeltxt">Aadhaar Number</label>
                                <input type="text" id="src_aadhar_no" class="form-control" placeholder="">
                            </div>
                        </div>


                        <div class="col-md-3">
                            <div class="form-group">
                                <label class="labeltxt">Property Type </label>
                                <input type="text" id="category"  class="form-control" placeholder="">
                            </div>

                        </div>
                    </div>
                    <div class="clearfix"></div>
                    <div class="row">



                        <div class="col-md-12"> <div class="form-group text-center"> 
                                <button class="btn btn-primary " id="btn_property_submit" onclick="">Search</button> </div>
                        </div>
                        <div class="clearfix"></div>

                    </div>
                </div>
            </div>
        </div>
    </div>
</section>
<section id="searchResults" class="contdatatable row_0 hidden">
    <div class="col-lg-12 p-0">
        <div class="innerdashboard">
            <div id="arreartab_div" class="conainer">
                <div class="filterward pb-3 d-flex"><h3  class="propeh p-0 m-0"  >Tax Collection</h3>  <div class="col-md-6 ml-auto text-right"> 
                        <a href="javascript: void(0)" class="btn btn-primary greeen_linear-gradient" onclick="TAXCOLLECTION.exportAs('PDF')"> Download as PDF </a>
                        <a href="javascript: void(0)" class="btn btn-primary Blue_linear-gradient" onclick="TAXCOLLECTION.exportAs('EXCEL')"> Download as Excel </a>
                        <a href="#" id="btn_back_search_window" class="btn btn-primary">Back</a>
                    </div></div>
                <div class="filterother">
                    <!-- <div class=" pull-right label label-primary "><a href="#" id="btn_back_search_window" class="label label-info">&lt; Back to search</a></div> -->

                    <div class="row">
                        <div class="col-md-12">
                            <table id="tableSearchResult" class="table table-bordered table-sm dataTable">
                                <thead>
                                    <tr>
                                        <th></th>
                                    </tr>
                                </thead>
                                <tbody>
                                    <tr>
                                        <td></td>
                                    </tr>
                                </tbody>
                            </table>
                        </div>
                    </div>

                </div>
            </div>
        </div>
    </div>
</section>

<section id="taxCollection" class="cont_b row_0 hidden">
    <div class="col-lg-12 p-0">
        <div class="innerdashboard">
            <div id="arreartab_div" class="conainer shT-to-556">
                <div class="filterward pb-3  d-flex"   ><h3  class="propeh p-0 m-0"  >Tax Collection </h3>
                    <div class="pull-right ml-auto "><div class="form-group mb-0">

                            <a href="#" id="btn_pay_tax" class="btn btn-primary btn-sm red_new_Property">Collect Payment</a>
                            <a id="show_notice" class="btn btn-primary btn-sm disabled Blue_linear-gradient" target="abc" >Download Notice</a>
                            <!--<a href="#" id="btn_view_tax_summary" class="btn btn-primary">View TAX Summary</a>-->
                            <!--<a href="#" id="btn_view_tax_history" class="btn btn-success">Payment History</a>--> 
                            <a href="#" id="btn_back_results" class="btn btn-primary btn-sm">Back</a>
                        </div></div>
                </div>
                <div class="filterother ">
                    <div id="accordion">
                        <div class="card">
                            <div class="card-header">
                                <a class="card-link" data-toggle="collapse" href="#OwnerDetails">
                                    Owner Details <span class="material-icons right-arrow">keyboard_arrow_down</span>
                                </a>
                            </div>
                            <div id="OwnerDetails" class="collapse show" data-parent="#accordion">
                                <div class="card-body">



                                    <div class="row">
                                        <div class="col-md-3">   

                                            <div class="form-group">
                                                <label class="labeltxt">Bill No.: </label>
                                                <input type="text" id="prop_bill_no_" class="form-control" placeholder="" disabled >
                                            </div>
                                        </div>
                                        <div class="col-md-3">  
                                            <div class="form-group">
                                                <label class="labeltxt">Bill Amount (Rs.):</label> 
                                                <input type="text" id="prop_total_amount" class="form-control" placeholder="" disabled>
                                            </div>
                                        </div>
                                        <div class="col-md-3">
                                            <div class="form-group">
                                                <label class="labeltxt">Bill Date: </label> 
                                                <input type="text" id="prop_bill_date_" class="form-control" placeholder="" disabled>
                                            </div>
                                        </div>

                                        <div class="col-md-3">
                                            <div class="form-group">
                                                <label class="labeltxt">Bill Due Date:</label> 
                                                <input type="text" id="prop_due_date_" class="form-control" placeholder="" disabled>
                                            </div>
                                        </div>

                                        <div class="col-md-3">
                                            <div class="form-group">
                                                <label class="labeltxt">Property ID:</label>
                                                <input type="text" id="prop_id" class="form-control" placeholder="" disabled>
                                            </div>
                                        </div>
                                        <div class="col-md-3">
                                            <div class="form-group">
                                                <label class="labeltxt">Owner Name: </label> 
                                                <input type="text" id="prop_owner" class="form-control" placeholder="" disabled>
                                            </div>
                                        </div>

                                        <div class="col-md-3">
                                            <div class="form-group">
                                                <label class="labeltxt">Occupier Name: </label>
                                                <input type="text" id="prop_occupier" class="form-control" placeholder="" disabled>
                                            </div>
                                        </div>
                                        <div class="col-md-3">
                                            <div class="form-group">
                                                <label class="labeltxt">Relation With Owner:</label> 
                                                <input type="text" id="prop_relation_owner" class="form-control" placeholder="" disabled>
                                            </div>
                                        </div>


                                        <!--            <div class="col-md-4">
                                                        <div class="form-group">
                                                            <label class="labeltxt">Plot / House / Shop / Flat No.:</label> 
                                                            <input type="text" id="prop_house_no" class="form-control" placeholder="" disabled>
                                                        </div>
                                                    </div>-->
                                        <div class="col-md-3">
                                            <div class="form-group">
                                                <label class="labeltxt">Postal Address:</label>
                                                <input type="text" id="prop_address" class="form-control" placeholder="" disabled>
                                            </div>
                                        </div>

                                        <div class="col-md-3">
                                            <div class="form-group">
                                                <label class="labeltxt">Pin Code: </label> 
                                                <input type="text" id="prop_pincode" class="form-control" placeholder="" disabled>
                                            </div>
                                        </div>
                                        <div class="col-md-3 d-none">
                                            <div class="form-group">
                                                <label class="labeltxt">Zone: </label> 
                                                <input type="text" id="prop_zone" class="form-control" placeholder="" disabled>
                                            </div>
                                        </div>
                                        <div class="col-md-3">
                                            <div class="form-group">
                                                <label class="labeltxt">Locality: </label> 
                                                <input type="text"  id="prop_sublocality" class="form-control" placeholder=""  disabled>
                                            </div>
                                        </div>
                                        <!-- <div class="col-md-4">
                                                        <div class="form-group">
                                                                <label class="labeltxt">Locality </label> 
                                                                <input type="text" id="prop_locality" class="form-control" placeholder="" disabled >
                                                        </div>
                                                </div> -->


                                        <!--			<div class="clearfix"></div>
                                                <div class="row-fluid">
                                                        <div class="col-md-4">
                                                                <div class="form-group">
                                                                        <label class="labeltxt">Property Type:</label>
                                                                        <input type="text" id="prop_type" class="form-control" placeholder="" disabled >
                                                                </div>
                                                        </div>
                                                        <div class="col-md-4">
                                                                <div class="form-group">
                                                                        <label class="labeltxt">Buildup Area: </label>
                                                                         <input type="text" id="prop_build_area" class="form-control" placeholder="" disabled >
                                                                </div>
                                                        </div>
                                                </div> -->





                                        <div class="col-md-3">
                                            <div class="form-group">
                                                <label class="labeltxt">Landmark: </label> 
                                                <input type="text" id="prop_landmark" class="form-control" placeholder="" disabled>
                                            </div>
                                        </div>
                                    </div>
                                    <div class="col-md-3">



                                        <!-- 		<div class="col-md-4">
                                                        <div class="form-group">
                                                                <label class="labeltxt">Tax Amount Received: </label> 
                                                                <input type="text" id="prop_recieved_amount" class="form-control" placeholder="">
                                                        </div>
                                                </div>
                            
                                                <div class="col-md-4">
                                                        <div class="form-group">
                                                                <label class="labeltxt">Balance Amount: </label>
                                                                 <input type="text" id="prop_balance" class="form-control" placeholder="">
                                                        </div>
                                                </div> -->



                                    </div>

                                    <!--	<div class="clearfix"></div>
                                            <div class="row-fluid">
                                            <div class="col-md-4">
                                                            <div class="form-group">
                                                                    <label class="labeltxt">Arrear.: </label> 
                                                                    <input type="text" id="prop_arrear" class="form-control" placeholder="">
                                                            </div>
                                                    </div>
                                                    <div class="col-md-4">
                                                            <div class="form-group">
                                                                    <label class="labeltxt">Rebate:</label>
                                                                    <input type="text" id="prop_rebate" class="form-control" placeholder="">
                                                            </div>
                                                    </div>
                            
                            
                                                    <div class="col-md-4">
                                                            <div class="form-group">
                                                                    <label class="labeltxt">Interest: </label> 
                                                                    <input type="text" id="prop_interest" class="form-control" placeholder="">
                                                            </div>
                                                    </div>
                                            </div>
                                             <div class="clearfix"></div>
                                            <div class="row-fluid">
                                                    <div class="col-md-4">
                                                            <div class="form-group">
                                                                    <label class="labeltxt">Payment Mode:</label>
                                                                    <input type="text" id="prop_pay_mode" class="form-control" placeholder="">
                                                            </div>
                                                    </div>
                            
                            
                                                    <div class="col-md-4">
                                                            <div class="form-group">
                                                                    <label class="labeltxt">Bank Name: </label> 
                                                                    <input type="text" id="prop_bank_name" class="form-control" placeholder="">
                                                            </div>
                                                    </div>
                            
                                                    <div class="col-md-4">
                                                            <div class="form-group">
                                                                    <label class="labeltxt">Cheque No.: </label>
                                                                     <input type="text" id="prop_check_no" class="form-control" placeholder="">
                                                            </div>
                                                    </div>
                            
                                                    <div class="col-md-4">
                                                            <div class="form-group">
                                                                    <label class="labeltxt">Demand Draft No.: </label> 
                                                                    <input type="text" id="prop_demanddraft_no" class="form-control" placeholder="">
                                                            </div>
                                                    </div>
                            
                                            </div> -->





                                    <div class="row">
                                        <div class="col-lg-12 text-center">
                                            <!--                            <a href="#" id="btn_pay_tax" class="btn btn-primary">Collect Payment</a>-->
                                            <!--<a href="#" id="btn_view_tax_summary" class="btn btn-primary">View TAX Summary</a>-->
                                            <!--<a href="#" id="btn_view_tax_history" class="btn btn-success">Payment History</a>--> 
                                            <!--                            <a href="#" id="btn_back_results" class="btn btn-danger">Close</a>-->
                                        </div>
                                    </div>
                                </div>
                            </div>                                
                        </div>

                        <div class="card">
                            <div class="card-header">
                                <a class="card-link collapsed" data-toggle="collapse" href="#TAXSummary">
                                    TAX Summary <span class="material-icons right-arrow">keyboard_arrow_down</span>
                                </a>
                            </div>
                            <div id="TAXSummary" class="collapse" data-parent="#accordion">
                                <div class="card-body">

                                    <div class="row">
                                        <div class="col-md-3">
                                            <div class="form-group">
                                                <label class="labeltxt"> TAX No.: </label>
                                                <span class="text-muted d-none" id="label_taxId">  </span>
                                            </div>
                                        </div>
                                        <div class="col-md-3 ">
                                            <div class="form-group">
                                                <label class="labeltxt"> Bill Date: </label> 
                                                <span class="text-muted d-none" id="label_bill_date">  </span>
                                            </div>
                                        </div>

                                        <div class="col-md-3 ">
                                            <div class="form-group">
                                                <label class="labeltxt"> Property ID: </label>
                                                <span class="text-muted"  id="label_propId">  </span>
                                            </div>
                                        </div>

                                        <div class="col-md-3 ">
                                            <div class="form-group">
                                                <label class="labeltxt"> Bill Due Date: </label> 
                                                <span class="text-muted d-none" id="label_bill_due_date">  </span>
                                            </div>
                                        </div>

                                        <div class="col-md-12 ">
                                            <table class="table table-bordered table-condensed" id='summaryTAX'>
                                                <thead>
                                                    <tr>
                                                        <th>Floor</th>
                                                        <th>Builtup Area(square meter)</th>
                                                        <!-- <th>Category</th> -->
                                                        <th>Type</th>
                                                        <th>Rentable Value (Rs.)</th>
                                                        <th>TAX amount (Rs.)</th>
                                                    </tr>
                                                </thead>
                                                <tbody>

                                                </tbody>
                                            </table>
                                        </div>
                                    </div>
                                    <div class="row">
                                        <div class="col-md-3 ">

                                            <div class="form-group">
                                                <label class="labeltxt"> Arrear (Rs.): </label> 
                                                <span class="text-muted" id="label_arrear"> </span>
                                            </div>
                                        </div>
                                        <div class="col-md-3 ">     
                                            <div class="form-group">
                                                <label class="labeltxt"> Water Tax (Rs.): </label> 
                                                <span class="text-muted" id="label_water_tax"> </span>
                                            </div>
                                        </div>
                                        <div class="col-md-3 "> 
                                            <div class="form-group">
                                                <label class="labeltxt"> Conservancy Tax (Rs.): </label> 
                                                <span class="text-muted" id="label_conservancy_tax"> </span>
                                            </div>
                                        </div>
                                        <div class="col-md-3 ">
                                            <div class="form-group">
                                                <label class="labeltxt"> Water Sewerage (Rs.): </label> 
                                                <span class="text-muted" id="label_water_sewerage_tax"> </span>
                                            </div>
                                        </div>
                                        <div class="col-md-3 ">
                                            <div class="form-group">
                                                <label class="labeltxt"> Water Meter (Rs.): </label> 
                                                <span class="text-muted" id="label_water_meter_bill"> </span>
                                            </div>
                                        </div>
                                        <div class="col-md-3 ">
                                            <div class="form-group">
                                                <label class="labeltxt"> Credit /Amt Paid in advance  (Rs.): </label> 
                                                <span class="text-muted" id="label_advance_paid"> </span>
                                            </div>
                                        </div>
                                        <div class="col-md-3 ">
                                            <div class="form-group">
                                                <label class="labeltxt"> Rebate (Rs.): </label> 
                                                <span class="text-muted" id="label_rebate"> </span>
                                            </div>
                                        </div>

                                        <div class="col-md-3 ">
                                            <div class="form-group">
                                                <label class="labeltxt"> Adjustment (Rs.): </label> 
                                                <span class="text-muted" id="label_adjustment"> </span>
                                            </div>
                                        </div>
                                        <div class="col-md-3 ">
                                            <div class="form-group">
                                                <label class="labeltxt"> Total Property Tax (Rs.): </label> 
                                                <span class="text-muted" id="label_total_prop_tax"> </span>
                                            </div>
                                        </div>
                                        <div class="col-md-3 ">
                                            <div class="form-group d-none">
                                                <label class="labeltxt"> Service Tax (Rs.): </label> 
                                                <span class="text-muted" id="label_service_tax"> </span>
                                            </div>
                                        </div>
                                        <div class="col-md-3 ">
                                            <div class="form-group">
                                                <label class="labeltxt"> Other Tax (Rs.): </label> 
                                                <span class="text-muted" id="label_other_tax"> </span>
                                            </div>
                                        </div>
                                        <div class="col-md-3 ">
                                            <div class="form-group">
                                                <label class="labeltxt"> Grand Total (Rs.): </label> 
                                                <span class="text-muted" id="label_grand_total"> </span>
                                            </div>
                                        </div>
                                        <div class="col-md-3 ">
                                            <div class="form-group d-none">
                                                <label class="labeltxt"> Delay Payment Charges (Rs.): </label> 
                                                <span class="text-muted" id="label_delay_charges"> </span>
                                            </div>
                                            <div class="form-group">
                                                <label class="labeltxt"> Old Tax (Rs.): </label> 
                                                <span class="text-muted" id="label_old_tax"> </span>
                                            </div>
                                        </div>
                                        <div class="col-md-3 ">
                                            <div class="form-group">
                                                <label class="labeltxt"> Net Payable Amount(Rs.): </label> 
                                                <span class="text-muted" id="label_net_amount"> </span>
                                            </div>
                                        </div>

                                    </div>


                                    <!--                    <div class="row">
                                                            <div class="col-md-12 text-center">
                                                                <a href="#" id="close_btn_taxDetailView" class="btn btn-info">Close</a>
                                                            </div>
                                                        </div>-->
                                </div>
                            </div>
                        </div>
                        <div class="card">
                            <div class="card-header">
                                <a class="card-link collapsed" data-toggle="collapse" href="#PaymentHistory">
                                    Payment History <span class="material-icons right-arrow">keyboard_arrow_down</span>
                                </a>
                            </div>
                            <div id="PaymentHistory" class="collapse " data-parent="#accordion">
                                <div class="card-body">


                                    <div class="row">
                                        <div class="col-md-12">

                                            <table class="table table-bordered table-condensed" id='historyTAX'>
                                                <thead>
                                                    <tr>
                                                        <th>Reference Id</th>
                                                        <th>Amount Paid (INR)</th>
                                                        <th>Payment Date</th>
                                                        <th>Financial Year</th>
                                                        <th>Action</th>
                                                    </tr>

                                                <tbody id="tabBody" style="display: none">
                                                </tbody>
                                            </table>
                                        </div>
                                    </div>
                                    <form name="taxCollectionHistory" id="taxCollectionHistory" action="showCollectionReceiptHistoryPdf" target="_blank" method="POST">
                                        <input type='hidden' id="receiptRefNo" name="receiptRefNo" type="text" value='' >
                                        <input type='hidden' id="receiptpropId" name="propId" type="text" value='' >
                                        <!--<input type="submit" id="as_expo12receipt" value="submit" class="btn btn-primary "/>-->
                                    </form>

                                    <div class="clearfix"></div>
                                    <!--                    <div class="row spanr12 text-center">
                                                            <div class="col-12">
                                                                <div class="form-group" style="margin-top: 20px;">
                                                                    <a href="#" id="close_btn_taxHistoryView" class="btn btn-info">Close</a>
                                                                </div>
                                                            </div>
                                                        </div>-->
                                </div>
                            </div>
                        </div>
                    </div>
                </div>      
            </div>                
        </div>
    </div>
</section>
<section id="taxPaymentView" class="cont_box row_0 ">
    <div class="col-lg-12 p-0">
        <div class="innerdashboard">
            <div id="arreartab_div" class="conainer">
                <div class="filterward d-flex pb-3" ><h3  class="propeh p-0 m-0"  >Collect Payment</h3>
                <div class="form-group ml-auto" style="margin-top:0px;">
                                <a onclick="TAXCOLLECTION.validatePayment();" href="#" class="btn btn-default red_new_Property">Collect</a>
                                <a onclick="TAXCOLLECTION.showCollectionWindow();" href="#" class="btn btn-primary">Back</a>
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

</section>


<section class="cont hidden" id="submitMesage">
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
                    <button class="btn btn-success" onclick="TAXCOLLECTION.printCollectionReceipt();">Download Receipt</button>
                    <button class="btn btn-success" onclick="TAXCOLLECTION.showResultWindow();">Back to search results</button>
                </div>
            </div>
        </div>
    </div>
</section>

<script type="text/javascript">

    $(document).ready(function () {
        $("#tax_collection_main_menuu").addClass("active");
        $("#payment").addClass("active");
        $("#tax_collection_main_menuu ul.sub-menu ").addClass("show");
     
        $("#breadlist").html('');
         $("#breadlist").addClass('lisb');
        $("#breadlist").html('<li><a href="taxCollection">Tax Collection</a></li>')

        $(document).keypress(function (event) {
            var keycode = (event.keyCode ? event.keyCode : event.which);
            if (keycode == '13' && !$("#searchArea").hasClass('hidden')) {
                TAXCOLLECTION.searchPropertyForTAX();
            }
        });

        TAXCOLLECTION.propertyIdFilter();
        TAXCOLLECTION.phoneNoFilter();
        TAXCOLLECTION.cityCodeFilter();
        TAXCOLLECTION.aadharNoFilter();
        TAXCOLLECTION.subLocalityFilter();
        TAXCOLLECTION.wardlstFilter();
        TAXCOLLECTION.ownerlstFilter();
        TAXCOLLECTION.occupierlstFilter();
        TAXCOLLECTION.localitylstFilter();
        TAXCOLLECTION.propertytypeFilter();
        //debugger;
        //LOADER.show();
//        $('#historyTAX').DataTable({
//            dom: 'Bfrtip',
//            buttons: [
//                'excel', 'pdf'
//            ],
//            pageLength: 15,
//            pagingType: 'full_numbers',
//            lengthMenu: [[10, 25, 50, -1], [10, 25, 50, 'All']]
//        });
//
//        $('#tabBody').show();
//        $('.dataTables_wrapper table').wrap('<div class="scroll_table"></div>');
        LOADER.hide();
    });


//

</script>







