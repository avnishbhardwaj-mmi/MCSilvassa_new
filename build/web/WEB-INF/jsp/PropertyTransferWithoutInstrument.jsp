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
   String piddata="";
    if (request.getAttribute("MSG") != null) {
        MSG = (String) request.getAttribute("MSG");
    }
    if (request.getAttribute("piddata") != null) {
        piddata = (String) request.getAttribute("piddata");
    }
%>


<section id="taxPaymentView" class="row">
    <div class="col-md-12">
        <div class="innerdashboard">
            <div id="arreartab_div" class="conainer">
                <div class="filterward pb-3"><h3  class="propeh m-0 p-0">SCHEDULE II<span>[See section 126(1)]</span></h3></div> 
                <div class="filterother">
                    <div class="propeh1 small pb-3 " align="left">FORM OF NOTICE OF TRANSFER TO BE GIVEN WHEN THE TRANSFER HAS TAKEN PLACE OTHERWISE THAN BY BY INSTRUMENT</div>
                    <center><div><span style="color: red;"><%=MSG%></span></div> </center>
                    <% if (piddata.trim().length() > 0) {%>
                    <center><div style="display:none"><span style="color: red;"><%=piddata%> dues clear</span></div> </center>
                                <%}%>
                                <form:form modelAttribute="propertyTransferWithout" name="propertyTransferWithout" method="post" onsubmit="return validate();" action="propertyTransferWithOutInsturmentDataSave">
                        <div class="clearfix"></div>
                        <div class="row">
                            <div class="col-md-4">
                                <div class="form-group">
                                    <label class="labeltxt " style="padding-top: 20px;">Date of notice : <span style="color: red;">*</span></label>
                                    <input type="text" id="dateOfNotice" name="dateOfNotice" autocomplete="off" path="dateOfNotice" class="form-control" placeholder="" />
                                </div>

                            </div>
                            <div class="col-md-4">
                                <div class="form-group">                          
                                    <label class="labeltxt">Name in which the property is at present entered in the Chiief officer's  record:<span style="color: red;">*</span>  </label>
                                    <input id="entryInChiefOficerBook" autocomplete="off" name="entryInChiefOficerBook" path="entryInChiefOficerBook" type="text" value=""  class="form-control" placeholder="" />

                                </div>
                            </div>
                            <div class="col-md-4">
                                <div class="form-group">
                                    <label class="labeltxt " style="padding-top: 20px;">To whom it is to be transfered : <span style="color: red;">*</span> </label>
                                    <input type="text" id="transferedPerson" autocomplete="off" name="transferedPerson" path="transferedPerson" class="form-control" placeholder="" />
                                </div>
                            </div>
                        </div>
                        <div class="row">
                            <div class="col-md-4">
                                <div class="form-group">
                                    <label class="labeltxt">Of what it consists situations: <span style="color: red;">*</span>  </label>
                                    <input type="text" id="consistSituation" autocomplete="off" name="consistSituation" path="consistSituation" class="form-control" placeholder="" />
                                </div>
                            </div>
                            <div class="col-md-4">
                                <div class="form-group">
                                    <label class="labeltxt">No. in assessment book<span style="color: red;">*</span></label>
                                    <input type="text" id="assessmentBookNo" autocomplete="off" name="assessmentBookNo" path="assessmentBookNo" class="form-control" placeholder="" />
                                </div>
                            </div>
                            <div class="col-md-4">
                                <div class="form-group">
                                    <label class="labeltxt">Chief officer's no.<span style="color: red;">*</span></label>
                                    <input type="text" id="chiefOfficerNo" autocomplete="off" name="chiefOfficerNo" path="chiefOfficerNo" class="form-control" placeholder="" />
                                </div>
                            </div>
                        </div>
                        <div class="row">
                            <div class="col-md-4">
                                <div class="form-group">
                                    <label class="labeltxt">Dimension of land<span style="color: red;">*</span></label>
                                    <input type="text" id="dimensionOfLand" autocomplete="off" name="dimensionOfLand" path="dimensionOfLand" class="form-control" placeholder="" />
                                </div>
                            </div>
                            <div class="col-md-4">
                                <div class="form-group">
                                    <label class="labeltxt">Boundaries<span style="color: red;">*</span></label>
                                    <input type="text" id="boundries" autocomplete="off" name="boundries" path="boundries" class="form-control" placeholder="" />
                                </div>
                            </div>
                            <div class="col-md-4">
                                <div class="form-group"> 
                                    <label class="labeltxt">Remarks<span style="color: red;">*</span></label>
                                    <textarea rows="3" id="remarks"  name='remarks' path="remarks" placeholder="Note related to payment, here..." class="form-control"> </textarea>
                                </div> 
                            </div>
                        </div>
                        <div class="row">
                            <div class="col-md-12">
                                <div class="tableCellCol labeltxt text-center">
                                    <input type="hidden" name="propertyUniqueId" path="propertyUniqueId" value="<%=piddata%>"/>
                                    <input type="submit"  class="btn btn-lg btn-primary" value="Submit"/>
                                    <a class="btn btn-lg btn-success" href="checkDuesPendingWithPropertyId?pid=<%=piddata%>">Back</a>
                                </div>
                            </div>
                        </div>
                    </div>                
                </form:form> 
            </div>
        </div>
    </div>
</section>

<script>

    $("#dateOfNotice").datepicker({dateFormat: 'yy-mm-dd'});
    $("#dateOfInstrument").datepicker({dateFormat: 'yy-mm-dd'});
    $("#dateOfRegistration").datepicker({dateFormat: 'yy-mm-dd'});

    function validate() {
        //alert("validate");
        var dateOfNotice = $("#dateOfNotice").val();
        var entryInChiefOficerBook = $("#entryInChiefOficerBook").val();
        var transferedPerson = $("#transferedPerson").val();
        var consistSituation = $("#consistSituation").val();
        var assessmentBookNo = $("#assessmentBookNo").val();
        var chiefOfficerNo = $("#chiefOfficerNo").val();
        var dimensionOfLand = $("#dimensionOfLand").val();
        var boundries = $("#boundries").val();
        var remarks = $("#remarks").val();
        //alert("remarks "+remarks.trim().length);
        if (dateOfNotice == '') {
            alert("Date of notice can't be left blank");
            $("#dateOfNotice").focus();
            return false;
        } else if (entryInChiefOficerBook == '') {
            alert("Entry in chief officer's book can't be left blank");
            $("#entryInChiefOficerBook").focus();
            return false;
        } else if (transferedPerson == '') {
            alert("Transered person name can't be left blank");
            $("#transferedPerson").focus();
            return false;
        } else if (consistSituation == '') {
            alert("Consists of situaation can't be left blank");
            $("#consistSituation").focus();
            return false;
        } else if (assessmentBookNo == '') {
            alert("Assessment book no. can't be left blank");
            $("#assessmentBookNo").focus();
            return false;
        } else if (chiefOfficerNo == '') {
            alert("Cheif officer no.  cant't be left blank");
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
        } else if (remarks.trim() == '' || remarks.trim().length == 0) {
            alert("Remaks  can't be left blank");
            $("#remarks").focus();
            return false;
        } else {
            return true;
        }



    }


</script>
