<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@taglib uri="http://www.springframework.org/tags/form" prefix="form"%>


<link href="res/css/datatable2.css" rel="stylesheet" type="text/css" />
<link href="res/css/buttons.dataTables.min.css" rel="stylesheet" type="text/css" />
<!--<link rel="stylesheet" type="text/css" href="res/css/dataTables.bootstrap.min.css" />-->
<script type="text/javascript" src="res/js/api/jquery.dataTables.min.js"></script>
<script type="text/javascript" src="res/js/api/dataTables.buttons.min.js"></script>
<script type="text/javascript" src="res/js/api/buttons.flash.min.js"></script>
<script src="res/js/util.js" type="text/javascript"></script>
<script src="res/js/payment_approval.js?v3" type="text/javascript"></script>
<link rel="stylesheet" type="text/css" href="res/css/easy-autocomplete.css" />

<script type="text/javascript" src="res/js/commonSearch.js?v1"></script>
<script type="text/javascript" src="res/js/api/jquery.easy-autocomplete.js"></script>

<section id="vn_searchWindow" class="cont_bg row_0">
    <div class="col-lg-12 p-0">
        <div class="innerdashboard">
            <div id="arreartab_div" class="conainer">
                <div class="filterward pb-3"><h3  class="propeh p-0 m-0" align="center">Payment Approval</h3></div>



                <div class="filterother"> 
                    <!--                    <div class="span5 text-right">
                                            <div class="form-group">
                                                <img src="res/img/img-tax.jpg" alt="" />
                                            </div>
                                        </div>-->

                    <div class="row d-none">
                        <div class="col-md-4 d-none">
                            <div class="form-group">
                                <label class="labeltxt">Zone </label> 
                                <select class="form-control" id="pay_view_zone" name="zoneId">
                                    <option value="-1">----Select Zone----</option>
                                </select>
                            </div>
                        </div>
                        <div class="col-md-4">
                            <div class="form-group">
                                <label class="labeltxt">Ward</label> 
                                <select class="form-control" id="pay_view_ward">
                                    <option value="-1">--Select Ward--</option>
                                </select>
                            </div>
                        </div>   <div class="col-md-12">
                            <div class="span12 text-center">
                                <input class="btn btn-primary" type="button" value="Show Cases" onclick="PaymentApp.showPendingPayments()" />
                            </div>
                        </div>
                    </div>
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
                    <div class="row">



                        <div class="col-md-12"> <div class="form-group text-center">
                                <input class="btn btn-primary" type="button" value="Show Cases" onclick="PaymentApp.showPendingPayments()" />
                            </div>

                            <div class="clearfix"></div>

                        </div>
                    </div>
                </div>
            </div>
        </div>
</section>
<section id="vn_searchResult" class="cont_datatable hidden">
    <div class="col-lg-12 pl-4">
        <div class="innerdashboard">
            <div id="arreartab_div" class="conainer">
                <div class="filterward d-flex "><h3  class="propeh p-0 m-0"  >Payment Approval</h3>
                    <div class="ml-auto text-center">
                        <!--                <a href="javascript: void(0)" class="btn btn-primary" onclick="PaymentApp.exportAs('PDF')"> Download as PDF </a>
                                        <a href="javascript: void(0)" class="btn btn-primary" onclick="PaymentApp.exportAs('EXCEL')"> Download as Excel </a>-->
<!--                        <input class="btn btn-primary" type="button" value="Back" onclick="PaymentApp.showSearchWindow()" /> --><!-- To open previous div-->
                      <input class="btn btn-primary" type="button" value="Back" onclick="PaymentApp.openCurTab()" />
                    </div>

                </div>
                <div class="filterother">
                    <div class="row">
                        <div class="col-md-12">
                            <table id="vn_searchResult_table" class="table table-bordered table-sm dataTable">
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

                        <div class="clearfix"></div>


                    </div>
                </div>
            </div>
        </div>
    </div>
</section>


<script>

    $(document).ready(function () {
        $("#tax_collection_main_menuu").addClass("active");
        $("#payment_approval").addClass("active");
        $("#tax_collection_main_menuu ul.sub-menu ").addClass("show");

        $("#breadlist").html('')
        $("#breadlist").addClass('lisb');
        $("#breadlist").html('<li><a href="paymentApprovalWindow">Payment approval</a></li>')
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
</script>