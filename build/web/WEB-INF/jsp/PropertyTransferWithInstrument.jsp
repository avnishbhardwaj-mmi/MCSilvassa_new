<link rel="stylesheet" type="text/css" href="res/css/select2.css" />
<link rel="stylesheet" type="text/css" href="res/css/select2-bootstrap.css" />
<link rel="stylesheet" type="text/css" href="res/css/dataTables.bootstrap.min.css" />

<script src="res/js/api/jquery.dataTables.min.js"></script>
<script src="res/js/api/dataTables.bootstrap.min.js"></script>
<script src="res/js/api/jquery-ui.js"></script>
<script src="res/js/api/select2.min.js"></script>
<%@taglib uri = "http://www.springframework.org/tags/form" prefix = "form"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<% String MSG = "";
    String piddata = "";
    if (request.getAttribute("MSG") != null) {
        MSG = (String) request.getAttribute("MSG");
    }
    if (request.getAttribute("piddata") != null) {
        piddata = (String) request.getAttribute("piddata");
    }
%>



<section id="taxPaymentView" class="row" >
    <div class="col-md-12">
        <div class="innerdashboard">
            <div id="arreartab_div" class="conainer">
                <div class="filterward pb-3"><h3  class="propeh m-0 p-0"> SCHEDULE I<span>[See section 126(1)]</span></h3></div> 


                <div class="filterother">
                    <div class="propeh1 small pb-2" align="left">FORM OF NOTICE OF TRANSFER TO BE GIVEN WHEN THE TRANSFER HAS BEEN EFFECTED BY INSTRUMENT</div>
                    <center><div><span style="color: red;"><%=MSG%></span></div> </center>
                                <% if (piddata.trim().length() > 0) {%>
                    <center><div style="display:none"><span style="color: red;"><%=piddata%> dues clear</span></div> </center>
                                <%}%>
                                <form:form modelAttribute="propertyTransfer" name="propertyTransfer" method="post" onsubmit="return validate();" action="propertyTransferWithInsturmentDataSave">
                        <div class="row">
                            <div class="col-md-3">
                                <div class="form-group">                            
                                    <label class="labeltxt">Date of notice : <span style="color: red;">*</span></label>
                                    <input type="text" id="dateOfNotice" autocomplete="off" name="dateOfNotice" path="dateOfNotice" class="form-control" placeholder="" />
                                </div>
                            </div>
                            <div class="col-md-3">
                                <div class="form-group">

                                    <label class="labeltxt">Date of instrument : <span style="color: red;">*</span></label>
                                    <input type="text" id="dateOfInstrument" autocomplete="off" name="dateOfInstrument" path="dateOfInstrument" class="form-control" placeholder="" />

                                </div>
                            </div>
                            <div class="col-md-3">
                                <div class="form-group">                           
                                    <label class="labeltxt">Name of vendor or assignor : <span style="color: red;">*</span> </label>
                                    <input id="vendorName" name="vendorName" autocomplete="off" path="vendorName" type="text" value=""  class="form-control" placeholder="" />
                                </div>
                            </div>
                  
                            <div class="col-md-3">
                                <div class="form-group">                        
                                    <label class="labeltxt">Name of purchaser or assignee : <span style="color: red;">*</span> </label>
                                    <input type="text" id="purchaserName" autocomplete="off" name="purchaserName" path="purchaserName" class="form-control" placeholder="" />
                                </div>
                            </div>
                            <div class="col-md-3">
                                <div class="form-group">
                                    <label class="labeltxt">Amount of consideration : <span style="color: red;">*</span> </label>
                                    <input type="text" id="amount" autocomplete="off" name="amount" path="amount" class="form-control" placeholder="" />

                                </div>
                            </div>
                            <div class="col-md-3">
                                <div class="form-group">
                                    <label class="labeltxt">Of what is consists : <span style="color: red;">*</span> </label>
                                    <input id="consists" name="consists" autocomplete="off" path="consists" type="text" value=""  class="form-control" placeholder="" />
                                </div>
                            </div>
                       
                            <div class="col-md-3">
                                <div class="form-group">
                                    <label class="labeltxt">Situations<span style="color: red;">*</span></label>
                                    <input type="text" id="situations" autocomplete="off" name="situations" path="situations" class="form-control" placeholder="" />
                                </div>
                            </div>
                            <div class="col-md-3">
                                <div class="form-group">
                                    <label class="labeltxt">No. in assessment book<span style="color: red;">*</span></label>
                                    <input type="text" id="assessmentBookNo" autocomplete="off" name="assessmentBookNo" path="assessmentBookNo" class="form-control" placeholder="" />
                                </div>
                            </div>
                            <div class="col-md-3">
                                <div class="form-group">
                                    <label class="labeltxt">Chief officer's No.<span style="color: red;">*</span></label>
                                    <input type="text" id="chiefOfficerNo" autocomplete="off" name="chiefOfficerNo" path="chiefOfficerNo" class="form-control" placeholder="" />
                                </div>
                            </div>
 
                            <div class="col-md-3">
                                <div class="form-group">
                                    <label class="labeltxt">Dimension of land<span style="color: red;">*</span></label>
                                    <input type="text" id="dimensionOfLand" autocomplete="off" name="dimensionOfLand" path="dimensionOfLand" class="form-control" placeholder="" />
                                </div>
                            </div>
                            <div class="col-md-3">
                                <div class="form-group">
                                    <label class="labeltxt">Boundaries<span style="color: red;">*</span></label>
                                    <input type="text" id="boundries" autocomplete="off" name="boundries" path="boundries" class="form-control" placeholder="" />
                                </div>
                            </div>
                            <div class="col-md-3">
                                <div class="form-group">
                                    <label class="labeltxt">If any  instrument has been registered, date of registration<span style="color: red;">*</span></label>
                                    <input type="text" id="dateOfRegistration" autocomplete="off" autocomplete="off" name="dateOfRegistration" path="dateOfRegistration" class="form-control" placeholder="" />
                                </div>
                            </div>
                
                            <div class="col-md-3">
                                <div class="form-group">
                                    <label class="labeltxt">Remarks<span style="color: red;">*</span></label>
                                    <textarea rows="3" id="remarks" autocomplete="off" name="remarks" path="remarks" class="form-control"  > </textarea>
                                </div></div>
                            <div class="col-md-3"></div>
                            <div class="col-md-3"></div>
                        </div>

                        <div class="row">
                            <input type="hidden" name="propertyUniqueId" path="propertyUniqueId" value="<%=piddata%>"/>
                            <div class="col-md-12 text-center"> <input type="submit" class="btn btn-lg btn-primary"  value="Submit"/>
                            <a class="btn btn-lg btn-success" href="checkDuesPendingWithPropertyId?pid=<%=piddata%>">Back</a>
                            </div>
                        </div>
                        <div class="clearfix"></div>
                    </div>        
                </div>
            </form:form>  
        </div>
</section>





</body>

<script>

    $("#dateOfNotice").datepicker({dateFormat: 'yy-mm-dd'});
    $("#dateOfInstrument").datepicker({dateFormat: 'yy-mm-dd'});
    $("#dateOfRegistration").datepicker({dateFormat: 'yy-mm-dd'});

    function validate() {
        //alert("validate");
        var dateOfNotice = $("#dateOfNotice").val();
        var dateOfInstrument = $("#dateOfInstrument").val();
        var vendorName = $("#vendorName").val();
        var purchaserName = $("#purchaserName").val();
        var amount = $("#amount").val();
        var consists = $("#consists").val();
        var situations = $("#situations").val();
        var assessmentBookNo = $("#assessmentBookNo").val();
        var chiefOfficerNo = $("#chiefOfficerNo").val();
        var dimensionOfLand = $("#dimensionOfLand").val();
        var boundries = $("#boundries").val();
        var dateOfRegistration = $("#dateOfRegistration").val();
        var remarks = $("#remarks").val();
        //alert("remarks "+remarks.trim().length);
        if (dateOfNotice == '') {
            alert("Date of notice can't be left blank");
            $("#dateOfNotice").focus();
            return false;
        } else if (dateOfInstrument == '') {
            alert("Date of instrument can't be left blank");
            $("#dateOfInstrument").focus();
            return false;
        } else if (vendorName == '') {
            alert("Vendor name can't be left blank");
            $("#vendorName").focus();
            return false;
        } else if (purchaserName == '') {
            alert("Purchaser name can't be left blank");
            $("#purchaserName").focus();
            return false;
        } else if (amount == '') {
            alert("Amount  can't be left blank");
            $("#amount").focus();
            return false;
        } else if (amount != '' && (isNaN(amount) || amount < 1)) {
            alert("Amount must be numeric and greater than zero  ");
            $("#amount").focus();
            return false;

        } else if (consists == '') {
            alert("Consist  can't be left blank");
            $("#consists").focus();
            return false;
        } else if (situations == '') {
            alert("Situations  can't be left blank");
            $("#situations").focus();
            return false;
        } else if (assessmentBookNo == '') {
            alert("Assessment book no.  can't be left blank");
            $("#assessmentBookNo").focus();
            return false;
        } else if (chiefOfficerNo == '') {
            alert("Chief officer no can't be left blank");
            $("#chiefOfficerNo").focus();
            return false;
        } else if (dimensionOfLand == '') {
            alert("Dimension of land  can't be left blank");
            $("#dimensionOfLand").focus();
            return false;
        } else if (boundries == '') {
            alert("Boundaries  can't be left blank");
            $("#boundries").focus();
            return false;
        } else if (dateOfRegistration == '') {
            alert("Date Of registration  can't be left blank");
            $("#dateOfRegistration").focus();
            return false;
        } else if (remarks.trim() == '' || remarks.trim().length == 0) {
            alert("Remaks  can't be left blank");
            $("#remarks").focus();
            return false;
        } else {
            return true;
        }



    }

</script>
</html>
