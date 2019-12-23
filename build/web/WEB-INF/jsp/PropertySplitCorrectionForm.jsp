

<%@taglib uri="http://www.springframework.org/tags/form" prefix="form"%> 
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ page import="com.google.gson.Gson" %>
<%@ page contentType="text/html; charset=utf-8" pageEncoding="UTF-8"%>
<meta http-equiv="Content-Type" content="text/html" />


<link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/bootstrap/4.1.3/css/bootstrap.min.css" integrity="sha384-MCw98/SFnGE8fJT3GXwEOngsV7Zt27NXFoaoApmYm81iuXoPkFOJwJ8ERdknLPMO" crossorigin="anonymous">
<link href="https://fonts.googleapis.com/css?family=Noto+Sans" rel="stylesheet"> 
<script src="res/correction/js/jquery-3.3.1.js" type="text/javascript"></script>
<script src="res/js/select2.min.js" type="text/javascript"></script>
<script src="res/js/PropertySplitCorrectionForm.js" type="text/javascript"></script>

<script src="https://code.jquery.com/ui/1.12.1/jquery-ui.js"></script>


<script>
    var currentData = '${currentData}';
</script>

<style>
    .attach_list li{
        display: block;
        cursor: pointer;
        padding: 2px 20px 2px;
        text-align: left;
        height: 29px;
        border-bottom: #eaeaea 1px solid;
    }
    .attach_list {
        background: #f3f3ff;
        box-shadow: rgba(0,0,0,0.1) 0px 2px 5px;
        transition: all 0.3s;
        left: -10%;

        border-radius: 6px;
    }
    .draggable_div {    cursor: all-scroll;position: fixed; right: 0;top:10;width: 37%; min-width: 470px; background: #ffffff; border: 1px solid #e0dddd;}
    .draggable_div legend{font-size: 18px;}
    #draggable_div_attch{height: 70vh;border: 1px dotted;margin-bottom: 10px}
    
    #iframePdf{height: 70vh;  width: 100%; border:none;}
    .show_box_silvassa{display: none} 
    .silvassa{display: block}
    .container {
        margin-left: 10px;
    }
</style>

<!-- Main Container --> 
<script>
    $(function () {
        $("#draggable_div").draggable().resizable();
    });
</script>
<section class="html_silvassa">
    <div class="container">      
        <div class="innercoantent"> 

        </div>
        <div style="display:none" id="draggable_div" class="draggable_div ui-resizable col-sm-6 ui-widget-content">
            <legend>Attachments</legend>
            <ul class="attach_list align-content-center">
                <li onclick="showDocument('${correction.imageFile}', '${correction.imageName}');">Form - ${correction.imageName} </li>
                <li onclick="showDocument('${correction.imageFile1}', '${correction.imageName1}');">Document 1 - ${correction.imageName1}</li>
                <li onclick="showDocument('${correction.imageFile2}', '${correction.imageName2}');">Document 2 - ${correction.imageName2}</li>
            </ul>
            <div id="draggable_div_attch" >
                <iframe src="" id="iframePdf"  style="display: none;"></iframe>
            </div>
        </div>

        <p style=" display: none">* Mandatory.</p>
        <p style=" display: none">&radic; Tick the check box to change the fields.</p>
        <p style=" display: none">&block; Please fill in the details which need correction. Leave the rest unchanged.</p>
        <p style=" display: none;font-family: 'Noto Sans',sans-serif;">*कृपया केवल उस विवरण को भरें जिसे सुधार की आवश्यकता हैए बाकी सभी को रद्द करें।</p>
        <!-- <div style="float: right; position: relative"> <a class="backbutton" href="/SilvassaPay">Back</a></div> -->
        <!--<a  class="pull-right buttonsiva" style="" href="downLoadPropertyProofDoc" target="_blank"> Supporting Documents </a><a  class="pull-right buttonsiva" style="" href="downLoadCorrectionFormHelp" target="_blank">Instruction on filling the form </a> -->
        <p style=" display: none" ><img src="res/correction/images/please_text.jpg" alt="*कृपया केवल उस विवरण को भरें जिसे सुधार की आवश्यकता हैए बाकी सभी को रद्द करें।" >
            <br> </p>

        <form:form id="myform"  method="post" action="submitCorrectionFormPropertySplit" modelAttribute="correctionFormBean" enctype="multipart/form-data"  > 
            <table class="table border-dark table-bordered" cellpadding="0" cellspacing="0">
                <tr>
                    <th><label for="uniqueId">Property ID</label></th>
                    <th><label for="wardNo">Ward No</label></th>
                    <th><label for="noticeNo">Notice No.</label></th>
                    <!--<th>Date</th>-->
                </tr>
                <tr>
                    <td><form:input class="form-control" path="uniqueId" readonly="true"   id="uniqueId" name="uniqueId"  /></td>
                    <td><form:select class="form-control" path="wardNo"   id="wardNo" name="wardNo" onchange="test.loadLocalityBasedOnWard();" >
                            <form:option value = "-1" label = "Select"/>
                            <form:option value = "W-1" label ="W-1"/>
                            <form:option value = "W-2" label ="W-2"/>
                            <form:option value = "W-3" label ="W-3"/>
                            <form:option value = "W-4" label ="W-4"/>
                            <form:option value = "W-5" label ="W-5"/>
                            <form:option value = "W-6" label ="W-6"/>
                            <form:option value = "W-7" label ="W-7"/>
                            <form:option value = "W-8" label ="W-8"/>
                            <form:option value = "W-9" label ="W-9"/>
                            <form:option value = "W-10" label ="W-10"/>
                            <form:option value = "W-11" label ="W-11"/>
                            <form:option value = "W-12" label ="W-12"/>
                            <form:option value = "W-13" label ="W-13"/>
                            <form:option value = "W-14" label ="W-14"/>
                            <form:option value = "W-15" label ="W-15"/>
                        </form:select>        
                    </td> 
                    <td>
                        <form:input class="form-control" path="noticeNo" id="noticeNo" readonly="true"  name="noticeNo"/>
                    </td> 


                </tr>
            </table>

            <table id="addowner" class="table border-dark table-bordered" cellpadding="0" cellspacing="0">
                <tr> <th colspan="6"> <h5 style="font-size: 15px;">A. Ownership Related.  </h5></th>
                <th  > <h5 style="display:none" style="font-size: 15px;">Choose Your's Proof Documents  </h5></th> 

                </tr>

                <!--
                          <tr>
                            <th>No.</th>
                            <th><label>Label</label></th>
                            <th  colspan="2"> </th>
                            <th> </th>
                          </tr>
                -->
                <tr>
                    <td>1(a)</td>
                    <td><label for="ownerName">Owner's Name *</label></td>
                    <td colspan="2"><form:input class="form-control" path="ownerName" readonly="true" id="ownerName" name="ownerName" /></td> 
                    <td colspan="2"><form:checkbox path="checkOwnerName" class="owner" name="checkOwnerName"  value="Y"  id="checkOwnerName" onclick="test.testCheck(this);"/></td>
                    <td  rowspan="7"  style="vertical-align: top">
                        <div style="display:none" class="label_sec"><label for="documentType">Upload Owner Document 1</label></div>
                        <select style="display:none" class="form-control" path = "documentType" id="documentType" name="documentType">
                            <option value = "-1">Select</option>
                            <option value = "PTR">Property Tax receipt of S.M.C</option>
                            <option value = "PR">Property Registry</option>
                            <option value = "SD">Sale Deed</option>
                            <option value = "RA">Rent Agreement</option>
                            <option value = "RR">Revenue Record</option>
                            <option value = "RC">Ration card</option>
                            <option value = "PC">Pan Card</option>
                            <option value = "DL">Driving license</option>
                            <option value = "EB">Electric Bill</option>
                            <option value = "TB">Telephone bill</option>
                            <option value = "VC">Voter Card</option>
                            <option value = "PP">Passport</option>
                            <option value = "MC">Marriage Certtificate</option>
                            <option value = "RW">Registered will or gift deed</option>
                            <option value = "AC">Aadhaar Card</option>
                            <option value = "AO">Other (Only Valid Documents)</option>


                        </select>
                        <br>
                        <label for="imageFile" style="display:none">Image File Owner Image 1</label>
                        <input style="display:none" type="file" name="imageFile" id="imageFile" >   <br><br><br>
                        <div style="display:none" class="label_sec"><label for="documentType1"> Upload Owner Document 2</label></div>
                        <select style="display:none" class="form-control" path = "documentType1" id="documentType1" name="documentType1">
                            <option value = "-1">Select</option>
                            <option value = "PTR">Property Tax receipt of S.M.C</option>
                            <option value = "PR">Property Registry</option>
                            <option value = "SD">Sale Deed</option>
                            <option value = "RA">Rent Agreement</option>
                            <option value = "RR">Revenue Record</option>
                            <option value = "RC">Ration card</option>
                            <option value = "PC">Pan Card</option>
                            <option value = "DL">Driving license</option>
                            <option value = "EB">Electric Bill</option>
                            <option value = "TB">Telephone bill</option>
                            <option value = "VC">Voter Card</option>
                            <option value = "PP">Passport</option>
                            <option value = "MC">Marriage Certtificate</option>
                            <option value = "RW">Registered will or gift deed</option>
                            <option value = "AC">Aadhaar Card</option>
                            <option value = "AO">Other (Only Valid Documents)</option>


                        </select>  <br> <label for="imageFileOwner" style="display:none">Image File Owner Image 2</label><input style="display:none" type="file" name="imageFileOwner" id="imageFileOwner" >

                        <div style="display:none" class="label_sec"><label for="documentType2"> Upload Owner Document 3</label></div>
                        <select style="display:none" class="form-control" path = "documentType2" id="documentType2" name="documentType2">
                            <option value = "-1">Select</option>
                            <option value = "PTR">Property Tax receipt of S.M.C</option>
                            <option value = "PR">Property Registry</option>
                            <option value = "SD">Sale Deed</option>
                            <option value = "RA">Rent Agreement</option>
                            <option value = "RR">Revenue Record</option>
                            <option value = "RC">Ration card</option>
                            <option value = "PC">Pan Card</option>
                            <option value = "DL">Driving license</option>
                            <option value = "EB">Electric Bill</option>
                            <option value = "TB">Telephone bill</option>
                            <option value = "VC">Voter Card</option>
                            <option value = "PP">Passport</option>
                            <option value = "MC">Marriage Certtificate</option>
                            <option value = "RW">Registered will or gift deed</option>
                            <option value = "AC">Aadhaar Card</option>
                            <option value = "AO">Other (Only Valid Documents)</option>


                        </select>  <br> <label for="imageFileOwner2Data" style="display:none">Image File Owner Image 3</label><input style="display:none" type="file" name="imageFileOwner2Data" id="imageFileOwner2Data" > 


                        <div style="display:none" class="label_sec"><label for="documentType3"> Upload Owner Document 4</label></div>
                        <select style="display:none" class="form-control" path = "documentType3" id="documentType3" name="documentType3">
                            <option value = "-1">Select</option>
                            <option value = "PTR">Property Tax receipt of S.M.C</option>
                            <option value = "PR">Property Registry</option>
                            <option value = "SD">Sale Deed</option>
                            <option value = "RA">Rent Agreement</option>
                            <option value = "RR">Revenue Record</option>
                            <option value = "RC">Ration card</option>
                            <option value = "PC">Pan Card</option>
                            <option value = "DL">Driving license</option>
                            <option value = "EB">Electric Bill</option>
                            <option value = "TB">Telephone bill</option>
                            <option value = "VC">Voter Card</option>
                            <option value = "PP">Passport</option>
                            <option value = "MC">Marriage Certtificate</option>
                            <option value = "RW">Registered will or gift deed</option>
                            <option value = "AC">Aadhaar Card</option>
                            <option value = "AO">Other (Only Valid Documents)</option>


                        </select>  <br> <label for="imageFileOwner3Data" style="display:none">Image File Owner Image 3</label><input style="display:none" type="file" name="imageFileOwner3Data" id="imageFileOwner3Data" > 



                    </td>

                </tr>
                <tr>
                    <td>1(b)</td>
                    <td><label for="ownerSex">Sex *</label></td>
                    <td colspan="2"><form:select class="form-control" path="ownerSex" disabled="true"  id="ownerSex" name="ownerSex" >
                            <form:option value = "-1" label = "Select"/>
                            <form:option value = "M" label ="Male"/>
                            <form:option value = "F" label ="Female"/>
                            <form:option value = "T" label ="Transgender"/>

                        </form:select>
                    </td>
                    <td colspan="2"><form:checkbox path="checkOwnerSex" name="checkOwnerSex"  value="Y" class="owner"   id="checkOwnerSex" onclick="test.testSelectCheck(this);"/></td>

                </tr>
                <tr>
                    <td>1(c)</td>
                    <td><label for="ownerFather">Father's/ Husband's Name *</label></td>
                    <td colspan="2"><form:input class="form-control" path="ownerFather" readonly="true" id="ownerFather" name="ownerFather" /></td>
                    <td colspan="2"><form:checkbox path="checkOwnerFather" name="checkOwnerFather" class="owner"  value="Y"   id="checkOwnerFather" onclick="test.testCheck(this);"/></td>

                </tr>
                <tr>
                    <td>1(d)</td>
                    <td><label for="spouseName" >Spouse Name</label></td>
                    <td colspan="2"><form:input class="form-control" path="spouseName" readonly="true" id="spouseName" name="spouseName" /></td>
                    <td colspan="2"><form:checkbox path="checkSpouseName" name="checkSpouseName" class="owner"  value="Y"  id="checkSpouseName" onclick="test.testCheck(this);"/></td>

                </tr>

                <tr>
                    <td>1(e)</td>
                    <td><label for="ownerContact" >Mobile No.* </label></td>
                    <td colspan="2"><form:input class="form-control" path="ownerContact" readonly="true" id="ownerContact" name="ownerContact" /></td>
                    <td colspan="2"><form:checkbox path="checkOwnerContact" name="checkOwnerContact"  class="owner" value="Y"  id="checkOwnerContact" onclick="test.testCheck(this);"/></td>

                </tr>
                <tr>
                    <td>1(f)</td>
                    <td><label for="ownerEmail">Email Id</label></td>
                    <td colspan="2"><form:input class="form-control"  path="ownerEmail" readonly="true" id="ownerEmail" name="ownerEmail" /></td>
                    <td colspan="2"><form:checkbox path="checkOwnerEmail" name="checkOwnerEmail" class="owner"  value="Y"  id="checkOwnerEmail" onclick="test.testCheck(this);"/></td>

                </tr>
                <tr>
                    <td>1(g)</td>
                    <td><label for="ownerAadharNo">Aadhaar No.</label></td>
                    <td colspan="2"><form:input class="form-control"  path="ownerAadharNo" readonly="true" id="ownerAadharNo" name="ownerAadharNo" /></td>
                    <td colspan="2"><form:checkbox path="checkOwnerAadharNo" name="checkOwnerAadharNo" class="owner"  value="Y"  id="checkOwnerAadharNo" onclick="test.testCheck(this);"/></td>
                </tr>
                <tr>
                    <td>1(h)</td>
                    <td><label for="propertyOwnerAddress">Property Owner's Correspondence Address</label></td>
                    <td colspan="2"><form:input class="form-control"  path="propertyOwnerAddress" readonly="true" id="propertyOwnerAddress" name="ownerAadharNo" /></td>
                    <td colspan="2"><form:checkbox path="checkPropertyOwnerAddress" name="checkPropertyOwnerAddress" class="owner"  value="Y"  id="checkPropertyOwnerAddress" onclick="test.testCheck(this);"/></td>
                </tr>


                <tr> <th colspan="7"> <h5 style="font-size: 15px;">Occupier Related.      <form:checkbox path="fillOccupierName" name="fillOccupierName"  value=""  id="fillOccupierName" onclick="test.fillOccupierName();"/>Owner and occupier are same.</h5></th>


                </tr>
                <tr>
                    <td>2(a)</td>
                    <td><label for="occupierName">Occupier's Name</label></td>
                    <td colspan="2"><form:input class="form-control"  path="occupierName" readonly="true" id="occupierName" name="occupierName"  /></td>
                    <td colspan="2"><form:checkbox path="checkOccupierName" name="checkOccupierName"  value="Y" class="occupier"  id="checkOccupierName" onclick="test.testCheck(this);"/></td>
                    <td   rowspan="5">
                        <div style="display:none" class="label_sec"><label for="documentTypeOccupier">Upload Occupier Document 1 </label></div>
                        <select style="display:none" class="form-control" path = "documentTypeOccupier" id="documentTypeOccupier" name="documentTypeOccupier">
                            <option value = "-1">Select</option>
                            <option value = "PTR">Property Tax receipt of S.M.C</option>
                            <<option value = "PR">Property Registry</option>
                            <<option value = "RA"/>Rent Agreement</option>
                            <option value = "RR">Revenue Record</option>
                            <<option value = "RC">Ration card</option>
                            <<option value = "PC">Pan Card</option>
                            <<option value = "DL">Driving license</option>
                            <<option value = "EB">Electric Bill</option>
                            <<option value = "TB">Telephone bill</option>
                            <<option value = "VC">Voter Card</option>
                            <<option value = "PP">Passport</option>
                            <<option value = "MC">Marriage Certtificate</option>
                            <<option value = "RW">Registered will or gift deed</option>
                            <<option value = "AC">Aadhaar Card<</option>
                            <<option value = "AO">Other (Only Valid Documents)</option>


                        </select> <br/><label for="imageFile1" style="display:none">Image File (Occupier 1)</label><input style="display:none" type="file" name="imageFile1" id="imageFile1" > <br/><br/>
                        <div style="display:none" class="label_sec"><label for="documentTypeOccupier1">Upload Occupier Document 2 </label></div>
                        <select style="display:none" class="form-control" path = "documentTypeOccupier1" id="documentTypeOccupier1" name="documentTypeOccupier1">
                            <option value = "-1">Select</option>
                            <option value = "PTR">Property Tax receipt of S.M.C</option>
                            <<option value = "PR">Property Registry</option>
                            <<option value = "RA"/>Rent Agreement</option>
                            <option value = "RR">Revenue Record</option>
                            <<option value = "RC">Ration card</option>
                            <<option value = "PC">Pan Card</option>
                            <<option value = "DL">Driving license</option>
                            <<option value = "EB">Electric Bill</option>
                            <<option value = "TB">Telephone bill</option>
                            <<option value = "VC">Voter Card</option>
                            <<option value = "PP">Passport</option>
                            <<option value = "MC">Marriage Certtificate</option>
                            <<option value = "RW">Registered will or gift deed</option>
                            <<option value = "AC">Aadhaar Card<</option>
                            <<option value = "AO">Other (Only Valid Documents)</option>


                        </select> <br/><label for="imageFileOccupier" style="display:none">Image File (Occupier 2)</label><input style="display:none" type="file" name="imageFileOccupier" id="imageFileOccupier" >
                        <div style="display:none" class="label_sec"><label for="documentTypeOccupier2">Upload Occupier Document 3 </label></div>
                        <select style="display:none" class="form-control" path = "documentTypeOccupier2" id="documentTypeOccupier2" name="documentTypeOccupier2">
                            <option value = "-1">Select</option>
                            <option value = "PTR">Property Tax receipt of S.M.C</option>
                            <<option value = "PR">Property Registry</option>
                            <<option value = "RA"/>Rent Agreement</option>
                            <option value = "RR">Revenue Record</option>
                            <<option value = "RC">Ration card</option>
                            <<option value = "PC">Pan Card</option>
                            <<option value = "DL">Driving license</option>
                            <<option value = "EB">Electric Bill</option>
                            <<option value = "TB">Telephone bill</option>
                            <<option value = "VC">Voter Card</option>
                            <<option value = "PP">Passport</option>
                            <<option value = "MC">Marriage Certtificate</option>
                            <<option value = "RW">Registered will or gift deed</option>
                            <<option value = "AC">Aadhaar Card<</option>
                            <<option value = "AO">Other (Only Valid Documents)</option>


                        </select> <br/><label for="imageFileOccupier1Data" style="display:none">Image File (Occupier 2)</label><input style="display:none" type="file" name="imageFileOccupier1Data" id="imageFileOccupier1Data" > 


                        <div style="display:none" class="label_sec"><label for="documentTypeOccupier3">Upload Occupier Document 4 </label></div>
                        <select style="display:none" class="form-control" path = "documentTypeOccupier3" id="documentTypeOccupier3" name="documentTypeOccupier3">
                            <option value = "-1">Select</option>
                            <option value = "PTR">Property Tax receipt of S.M.C</option>
                            <<option value = "PR">Property Registry</option>
                            <<option value = "RA"/>Rent Agreement</option>
                            <option value = "RR">Revenue Record</option>
                            <<option value = "RC">Ration card</option>
                            <<option value = "PC">Pan Card</option>
                            <<option value = "DL">Driving license</option>
                            <<option value = "EB">Electric Bill</option>
                            <<option value = "TB">Telephone bill</option>
                            <<option value = "VC">Voter Card</option>
                            <<option value = "PP">Passport</option>
                            <<option value = "MC">Marriage Certtificate</option>
                            <<option value = "RW">Registered will or gift deed</option>
                            <<option value = "AC">Aadhaar Card<</option>
                            <<option value = "AO">Other (Only Valid Documents)</option>


                        </select> <br/><label for="imageFileOccupier2Data" style="display:none">Image File (Occupier 2)</label><input style="display:none" type="file" name="imageFileOccupier2Data" id="imageFileOccupier2Data" ></td>








                </tr>
                <tr>
                    <td>2(b)</td>
                    <td><label for="occupierSex">Sex</label></td>
                    <td colspan="2"><form:select class="form-control" path="occupierSex" disabled="true" value='Select' selected='selected' id="occupierSex" name="occupierSex" >
                            <form:option value = "-1" label = "Select"/>
                            <form:option value = "M" label ="Male"/>
                            <form:option value = "F" label ="Female"/>
                            <form:option value = "T" label ="Transgender"/>
                        </form:select>
                    </td>
                    <td colspan="2"><form:checkbox path="checkOccupierSex" name="checkOccupierSex"  value="Y" class="occupier"   id="checkOccupierSex" onclick="test.testSelectCheck(this);"/></td>

                </tr>
                <tr>
                    <td>2(c)</td>
                    <td><label for ="occupierFather">Father's/Husband's Name</label></td>
                    <td colspan="2"><form:input class="form-control" path="occupierFather" readonly="true" id="occupierFather" name="occupierFather"  /></td>
                    <td colspan="2"><form:checkbox path="checkOccupierFather" name="checkOccupierFather" class="occupier"  value="Y"  id="checkOccupierFather" onclick="test.testCheck(this);"/></td>

                </tr>
                <tr>
                    <td>2(d)</td>
                    <td><label for ="occupierContact">Mobile No.</label></td>
                    <td colspan="2"><form:input class="form-control" path="occupierContact" readonly="true" id="occupierContact" name="occupierContact"  /></td>
                    <td  colspan="2"><form:checkbox path="checkOccupierContact" name="checkOccupierContact"  value="Y" class="occupier"  id="checkOccupierContact" onclick="test.testCheck(this);"/></td>

                </tr>
                <tr>
                    <td>2(e)</td>
                    <td><label for ="occupierEmail" >Email Id</label></td>
                    <td colspan="2"><form:input class="form-control" path="occupierEmail" readonly="true" id="occupierEmail" name="occupierEmail"  /></td>
                    <td colspan="2"><form:checkbox path="checkOccupierEmail" name="checkOccupierEmail"  value="Y" class="occupier"  id="checkOccupierEmail" onclick="test.testCheck(this);"/></td>

                </tr>

                <tr>
                    <td>2(f)</td>
                    <td><label for ="occupierAadharNo">Aadhaar No..</label></td>
                    <td colspan="2"><form:input class="form-control"  path="occupierAadharNo" readonly="true" id="occupierAadharNo" name="occupierAadharNo" /></td>
                    <td colspan="2"><form:checkbox path="checkOccupierAadharNo" name="checkOccupierAadharNo"  value="Y" class="occupier"  id="checkOccupierAadharNo" onclick="test.testCheck(this);"/></td>
                </tr>
                <%--<tr>
                    <td>3</td>
                    <td><label>Address</label></td>
                    <td colspan="2"><form:input class="form-control"  path="address" id="address" name="address" /></td>
                    <td ></td>
                </tr>--%>
                <tr> <th colspan="7"> <h5 style="font-size: 15px;"> Address Related. </h5></th>
                <tr>

                    <td>3</td>
                    <td><label for ="plotNo">Flat No.</label></td>
                    <td colspan="2"><form:input class="form-control"  path="plotNo" readonly="true" id="plotNo" name="plotNo"  /></td>
                    <td colspan="2"><form:checkbox path="checkPlotNo" name="checkPlotNo"  value="Y" class="addr"  id="checkPlotNo" onclick="test.testCheck(this);"/></td>
                    <td rowspan="7" style="vertical-align: top">

                        <div style="display:none" class="label_sec"><label for="documentTypeAddress">Upload Address Document 1 </label></div>
                        <select style="display:none" class="form-control" path = "documentTypeAddress" id="documentTypeAddress" name="documentTypeAddress">
                            <option value = "-1"> Select</option>
                            <option value = "PTR"> Property Tax receipt of S.M.C </option>
                            <option value = "PR"> Property Registry</option>
                            <option value = "SD" >Sale Deed</option>
                            <option value = "RA" >Rent Agreement</option>
                            <option value = "RC" >Ration card</option>
                            <option value = "PC" >Pan Card</option>
                            <option value = "DL" >Driving license</option>
                            <option value = "AL" >Architect letter</option>
                            <option value = "EB" >Electric Bill</option>
                            <option value = "TB" >Telephone bill</option>
                            <option value = "VC" >Voter Card</option>
                            <option value = "AC" >Aadhaar Card<</option>
                            <option value = "PP" >Passport</option>
                            <option value = "AO" >Other(Only valid Documents</option>
                        </select> <br>
                        <label for="imageFile2" style="display:none">Image File (Address 1)</label><input style="display:none" type="file" name="imageFile2" id="imageFile2" > <br/><br/>

                        <div style="display:none" class="label_sec"><label for="documentTypeAddress2">Upload Address Document 2 </label></div>
                        <select style="display:none" class="form-control" path = "documentTypeAddress2" id="documentTypeAddress2" name="documentTypeAddress2">
                            <option value = "-1"> Select</option>
                            <option value = "PTR"> Property Tax receipt of S.M.C </option>
                            <option value = "PR"> Property Registry</option>
                            <option value = "SD" >Sale Deed</option>
                            <option value = "RA" >Rent Agreement</option>
                            <option value = "RC" >Ration card</option>
                            <option value = "PC" >Pan Card</option>
                            <option value = "DL" >Driving license</option>
                            <option value = "AL" >Architect letter</option>
                            <option value = "EB" >Electric Bill</option>
                            <option value = "TB" >Telephone bill</option>
                            <option value = "VC" >Voter Card</option>
                            <option value = "AC" >Aadhaar Card<</option>
                            <option value = "PP" >Passport</option>
                            <option value = "AO" >Other(Only valid Documents</option>


                        </select> <br>
                        <label for="imageFileAddress" style="display:none">Image File (Address 2)</label><input style="display:none" type="file" name="imageFileAddress" id="imageFileAddress" >


                        <div style="display:none" class="label_sec"><label for="documentTypeAddress3">Upload Address Document 3 </label></div>
                        <select style="display:none" class="form-control" path = "documentTypeAddress3" id="documentTypeAddress3" name="documentTypeAddress3">
                            <option value = "-1"> Select</option>
                            <option value = "PTR">Property Tax receipt of S.M.C </option>
                            <option value = "PR"> Property Registry</option>
                            <option value = "SD" >Sale Deed</option>
                            <option value = "RA" >Rent Agreement</option>
                            <option value = "RC" >Ration card</option>
                            <option value = "PC" >Pan Card</option>
                            <option value = "DL" >Driving license</option>
                            <option value = "AL" >Architect letter</option>
                            <option value = "EB" >Electric Bill</option>
                            <option value = "TB" >Telephone bill</option>
                            <option value = "VC" >Voter Card</option>
                            <option value = "AC" >Aadhaar Card<</option>
                            <option value = "PP" >Passport</option>
                            <option value = "AO" >Other(Only valid Documents</option>

                        </select> <br>
                        <label for="imageFileAddress1Data" style="display:none">Image File (Address 3)</label><input style="display:none" type="file" name="imageFileAddress1Data" id="imageFileAddress1Data" > 

                        <div style="display:none" class="label_sec"><label for="documentTypeAddress4">Upload Address Document 4 </label></div>
                        <select style="display:none" class="form-control" path = "documentTypeAddress4" id="documentTypeAddress4" name="documentTypeAddress4">
                            <option value = "-1">Select</option>
                            <option value = "PTR">Property Tax receipt of S.M.C </option>
                            <option value = "PR">Property Registry</option>
                            <option value = "SD" >Sale Deed</option>
                            <option value = "RA" >Rent Agreement</option>
                            <option value = "RC" >Ration card</option>
                            <option value = "PC" >Pan Card</option>
                            <option value = "DL" >Driving license</option>
                            <option value = "AL" >Architect letter</option>
                            <option value = "EB" >Electric Bill</option>
                            <option value = "TB" >Telephone bill</option>
                            <option value = "VC" >Voter Card</option>
                            <option value = "AC" >Aadhaar Card<</option>
                            <option value = "PP" >Passport</option>
                            <option value = "AO" >Other(Only valid Documents</option>

                        </select> <br>
                        <label for="imageFileAddress2Data" style="display:none">Image File (Address 4)</label><input style="display:none" type="file" name="imageFileAddress2Data" id="imageFileAddress2Data" ></td>




                </tr>
                <tr>
                    <td>4</td>
                    <td><label for="houseNo">House No.</label></td>
                    <td colspan="2"><form:input class="form-control"  path="houseNo"   readonly="true" id="houseNo" name="houseNo"  /></td>
                    <td colspan="2"><form:checkbox path="checkHouseNo" name="checkHouseNo"  value="Y" class="addr"  id="checkHouseNo" onclick="test.testCheck(this);"/></td>
                </tr>
                <tr>
                    <td>5</td>
                    <td><label for="buildingName">Building Name</label></td>
                    <td colspan="2"><form:input class="form-control"  path="buildingName" readonly="true" id="buildingName" name="buildingName"  /></td>
                    <td colspan="2"><form:checkbox path="checkBuildingName" name="checkBuildingName" class="addr"  value="Y"  id="checkBuildingName" onclick="test.testCheck(this);"/></td>
                </tr>

                <tr>
                    <td>6</td>
                    <td><label for="roadName">Road Name</label></td>
                    <td colspan="2"><form:input class="form-control" readonly="true"  path="roadName" id="roadName" name="roadName"  /></td>
                    <td colspan="2"><form:checkbox path="checkRoadName" name="checkRoadName" class="addr"  value="Y"  id="checkRoadName" onclick="test.testCheck(this);"/></td>
                </tr>

                <tr>
                    <td>7</td>
                    <td><label>Sub Locality</label></td>
                    <td colspan="2"><form:input class="form-control"  path="subLocality" readonly="true" id="subLocality" name="subLocality"  /></td>
                    <td colspan="2"><form:checkbox path="checkSubLocality" name="checkSubLocality"  class="addr" value="Y"  id="checkSubLocality" onclick="test.testCheck(this);"/></td>
                </tr>
                <tr>
                    <td>8</td>
                    <td><label for="locName">Locality Name *</label></td>
                    <td colspan="2"><select class="form-control"  path="locName" id="locName" disabled="true" name="locName" onchange="test.addLocality();"  >
                            <option value = "-1" >Select</option>
                            <option value="Patel Faliya" >Patel Faliya</option>
                            <option value="Bhasta Faliya" >Bhasta Faliya</option>
                            <option value="Oidc Housing Complex" >Oidc Housing Complex</option>
                            <option value="Baldevi, Amli" >Baldevi, Amli</option>
                            <option value="Bhurkud Faliya" >Bhurkud Faliya</option>
                            <option value="Piparia, Amli" >Piparia, Amli</option>
                            <option value="Sundarvan Society" >Sundarvan Society</option>
                            <option value="Police Line, Tokarkhada" >Police Line, Tokarkhada</option>
                            <option value="Tokarkhada" >Tokarkhada</option>
                            <option value="Bavisa Faliya, Amli" >Bavisa Faliya, Amli</option>
                            <option value="Piparia" >Piparia</option>
                            <option value="Zanda Chowk, Amli" >Zanda Chowk, Amli</option>
                            <option value="Dokmardi, Amli" >Dokmardi, Amli</option>
                            <option value="Manorath Residency" >Manorath Residency</option>
                            <option value="Bahumali" >Bahumali</option>
                            <option value="Nakshatra Van" >Nakshatra Van</option>
                            <option value="Vrundavan Society" >Vrundavan Society</option>
                            <option value="Piparia Industrial Estate" >Piparia Industrial Estate</option>
                            <option value="Pataliya Faliya" >Pataliya Faliya</option>
                            <option value="Amli">Amli</option>
                            <option value="Ultan Faliy Sub Jail Road" >Ultan Faliy Sub Jail Road</option>
                            <option value="Gurudev Complex-III" >Gurudev Complex-III</option>
                            <option value="Ultan Faliya" >Ultan Faliya</option>
                            <option value="Masjid Faliya" >Masjid Faliya</option>
                            <option value="Gurudev Complex-II" >Gurudev Complex-II</option>
                            <option value="Other" >Other</option>

                        </select>
                        <br>
                        <label for="locNameOther" style="display:none">LocName</label>
                        <input type="test" style="display:none" name="locNameOther" class="form-control" id="locNameOther" path="locNameOther">
                    </td>
                    <td colspan="2"><form:checkbox path="checkLocName" name="checkLocName" value="Y" class="addr"  id="checkLocName" onclick="test.testSelectCheck(this);"/></td>
                </tr>

                <tr>
                    <td>9</td>
                    <td><label for="landMark">Land Mark *</label></td>
                    <td colspan="2"><form:input class="form-control"  path="landMark" readonly="true" id="landMark" name="landMark"  /></td>
                    <td colspan="2"><form:checkbox path="checkLandMark" name="checkLandMark"  value="Y"  id="checkLandMark" class="addr" onclick="test.testCheck(this);"/></td>
                </tr>

                <tr>
                    <td width="">10</td>

                    <td><label for="electricSericeConnectionNo">Electric Service Connection no.</label></td>
                    <td    colspan="2"><form:input class="form-control" path="electricSericeConnectionNo" readonly="true" id="electricSericeConnectionNo" name="electricSericeConnectionNo"  /></td>
                    <td colspan="2"><form:checkbox path="checkElectricSericeConnectionNo"  name="checkElectricSericeConnectionNo" value="Y" class="addr" id="checkElectricSericeConnectionNo" onclick="test.testCheck(this);"/></td>
                    <td rowspan="4" style="vertical-align: top">
                        <div style="display:none" class="label_sec"><label for="documentTypeElectric">Upload Electric Connection No document</label></div>
                        <form:select style="display:none" class="form-control" path = "documentTypeElectric" id="documentTypeElectric"  name="documentTypeElectric">
                            <form:option value = "-1" label = "Select"/>
                            <%--      <form:option value = "PTR" label ="Property Tax receipt of S.M.C"/>
                             <form:option value = "PR" label = "Property Registry"/>
                             <form:option value = "SD" label = "Sale Deed"/>
                             <form:option value = "RA" label = "Rent Agreement"/>
                             <form:option value = "RR" label = "Revenue Record"/>
                             <form:option value = "RC" label = "Ration card"/>
                             <form:option value = "PC" label = "Pan Card"/>
                             <form:option value = "DL" label = "Driving license"/>
                             <form:option value = "AL" label = "Architect letter"/>  --%>
                            <form:option value = "EB" label = "Electric Bill"/>
                            <form:option value = "AO" label = "Other(Only valid Documents)"/>
                            <%--   <form:option value = "TB" label = "Telephone bill,"/>
                        <form:option value = "VC" label = "Voter Card"/>
                        <form:option value = "FV" label = "Field Verification(SMC Staff) "/>
                        <form:option value = "RW" label = "Registered will or gift deed"/>
                        <form:option value = "AO" label = "Other(Only valid Documents)"/>--%> 

                        </form:select> <br>  
                        <label for="imageFile3" style="display:none">Image File (Electric Service)</label> <input style="display:none" type="file" name="imageFile3" id="imageFile3" ></td>

                </tr>

                <tr>
                    <td>11</td>
                    <td><label for="surveyNo">Survey No.</label></td>
                    <td colspan="2"><form:input class="form-control"  path="surveyNo" readonly="true" id="surveyNo" name="surveyNo"  /></td>
                    <td colspan="2"><form:checkbox path="checkSurveyNo" name="checkSurveyNo"  value="Y"  id="checkSurveyNo" onclick="test.testCheck(this);"/></td>
                </tr>
                <tr>
                    <td>12</td>
                    <td><label for="plotSmc">Plot No. </label></td>
                    <td colspan="2"><form:input class="form-control"  path="plotSmc" readonly="true" id="plotSmc" name="plotSmc"  /></td>
                    <td colspan="2"><form:checkbox path="checkPlotSmc" name="checkPlotSmc"  value="Y"  id="checkPlotSmc" onclick="test.testCheck(this);"/></td>
                </tr>
                <tr>
                    <td>13</td>
                    <td><label for="smcProperty">SMC House Property No. </label></td>
                    <td colspan="2"><form:input class="form-control"  path="smcProperty" readonly="true" id="smcProperty" name="smcProperty"  /></td>
                    <td colspan="2"><form:checkbox path="checkSmcProperty" name="checkSmcProperty"  value="Y"  id="checkSmcProperty" onclick="test.testCheck(this);"/></td>
                </tr>
                <tr>
                    <td>14</td>
                    <td><label for="arrearAmount">Arrear Amount </label></td>
                    <td colspan="2"><form:input class="form-control"  path="arrearAmount" readonly="true" id="arrearAmount" name="arrearAmount"  /></td>
                    <td colspan="2"><form:checkbox path="checkArrear" name="checkArrear"  value="Y"  id="checkArrear" class="addr" onclick="test.testCheck(this);"/></td>
                    <td rowspan="4" style="vertical-align: top">
                        <div style="display:none" class="label_sec"><label for="documentTypeElectric">Upload Arrear Amount document</label></div>
                        <form:select style="display:none" class="form-control" path = "documentTypeArrear" id="documentTypeArrear" name="documentTypeArrear">
                            <form:option value = "-1" label = "Select"/>
                            <form:option value = "PTR" label ="Property Tax receipt of S.M.C"/>
                            <form:option value = "AO" label = "Other(Only valid Documents)"/>

                        </form:select> <br>
                        <label for="imageFileArrear" style="display:none">Image File (Arrear)</label><input type="file" style="display:none" name="imageFileArrear" id="imageFileArrear" ></td>

                </tr>
                <tr>
                    <td width="5%">14</td>
                    <td width="20%"><label for="locationClass" >Location Class(1,2,3,4)</label></td>
                    <td colspan="2" width="45%"><form:select class="form-control" readonly="true" path = "locationClass"  id="locationClass" name="locationClass">
                            <form:option value = "-1" label = "Select"/>
                            <form:option value = "1" label = "1"/>
                            <form:option value = "2" label = "2"/>
                            <form:option value = "3" label = "3"/>
                            <form:option value = "4" label = "4"/>
                        </form:select> </td>
                    <!--<td colspan="2"><a class="buttonsiva" style="" href="downLoadPdfMap">Download Location Map PDF </a> </td>-->
                    <%--<td colspan="2"><form:checkbox path="checkElectricSericeConnectionNo"  name="checkElectricSericeConnectionNo" value="Y"  id="checkElectricSericeConnectionNo"/></td>--%>
                </tr>

                <tr>
                    <td>15</td>
                    <td><label for="city">City</label></td>
                    <td colspan="2"><form:input class="form-control" readonly="true"  path="city" id="city" name="city" /></td>
                    <td colspan="2"></td>
                </tr>
                <tr>
                    <td>16</td>
                    <td><label for="pincode">Pincode</label></td>
                    <td   colspan="2"><form:input class="form-control" readonly="true"  path="pincode" id="pincode" name="pincode" /></td>
                    <td colspan="2"></td>
                </tr>


                <%---    <tr><td>19</td>
                      <td colspan="5" class="text-center"> 
                     <div class="col-3 float-left text-left">
                              <label>Applicant Name </label>
                              <form:input class="form-control"  path="applicantName" id="applicantName" name="applicantName"/>
                          </div><div class="col-3 float-left text-left">
                              <label>Applicant Contact No. </label>
                              <form:input class="form-control"  path="applicantMobileNo" id="applicantMobileNo" name="applicantMobileNo"/>
                          </div> --%>

                <%--<div class="col-3 float-left text-left">
                <label>Document Type. </label>
                <form:select class="form-control" path = "documentType" id="documentType" name="documentType">
                    <form:option value = "-1" label = "Select"/>
                    <form:option value = "PTR" label ="Property Tax receipt of S.M.C"/>
                    <form:option value = "PR" label = "Property Registry"/>
                    <form:option value = "SD" label = "Sale Deed"/>
                    <form:option value = "RA" label = "Rent Agreement"/>
                    <form:option value = "RR" label = "Revenue Record"/>
                    <form:option value = "RC" label = "Ration card"/>
                    <form:option value = "PC" label = "Pan Card"/>
                    <form:option value = "DL" label = "Driving license"/>
                    <form:option value = "AL" label = "Architect letter"/>
                    <form:option value = "EB" label = "Electric Bill"/>
                    <form:option value = "TB" label = "Telephone bill,"/>
                    <form:option value = "VC" label = "Voter Card"/>
                    <form:option value = "FV" label = "Field Verification(SMC Staff) "/>
                    <form:option value = "RW" label = "Registered will or gift deed"/>
                    <form:option value = "AO" label = "Other(Only valid Documents)"/>

                                </form:select>  
                            </div>

                            <div class="col-6"></div>
                        </td>
                         
                    </tr> --%>
                <%--<tr>

                        <td>14</td>
                        <td><label>Upload Document</label></td>
                        <td colspan="2"><input type="file" name="imageFileName" ></td>
                        <td  colspan="2"></td>
                    </tr>--%>
                <tr>
                    <form:hidden path="permissionData" id="permissionData" name="permissionData"></form:hidden>
                    <form:hidden path="ipAddress" id="ipAddress" name="ipAddress"></form:hidden>
                    <form:hidden class="form-control" path="noticeDate" name="noticeDate"/> 
                    <form:hidden class="form-control" path="imageFileName" name="imageFileName"/> 
                    <form:hidden class="form-control" path="address" name="address" id="address"/> 
                    <input type='hidden'  value="${complainNo}" name="complainNo" id="complainNo"/> 



                </tr>
                <%--                    <tr>
                                        <td>Floor</td><td>Covered/Carpet Area</td><td>Property Use</td><td>Property SubType</td><td>Construction Type</td><td>Self/Rent</td><td>Annual Rent Value</td>
                                    </tr>
                                    <tr>
                                        <td><form:input class="form-control"  path="floorType" readonly="true" id="floorType" name="floorType"/></td>
                                        <td><form:input class="form-control"  path="carpetArea" readonly="true" id="carpetArea" name="carpetArea"/></td>
                                        <td><form:input class="form-control"  path="propertyUse" readonly="true" id="propertyUse" name="propertyUse"/></td>
                                        <td><form:input class="form-control"  path="propertySubtype" readonly="true" id="propertySubtype" name="propertySubtype"/></td>
                                        <td><form:input class="form-control"  path="constructionType" readonly="true" id="constructionType" name="constructionType"/></td>
                                        <td><form:input class="form-control"  path="selfRent" readonly="true" id="selfRent" name="selfRent"/></td>
                                        <td><form:input class="form-control"  path="annualRent" readonly="true" id="annualRent" name="annualRent"/></td>
                                        <td><form:checkbox path="editRecord" name="editRecord"  value="Y"  id="editRecord"/></td>
                                        <td><form:checkbox path="deleteRecord" name="deleteRecord"  value="Y"  id="deleteRecord"/></td> </td>
                                        
                                    </tr>--%>


                <table id="correct_floor" class="table border-dark table-bordered">
                    <thead>
                        <tr> <th colspan="10"> <h5 style="font-size: 15px;">B. Property Related.  </h5></th></tr>
                    <tr>
                        <td><b>Floor</b></td><td><b>Property Use</b></td><td><b>Property SubType</b></td><td><b>Covered/Built-Up Area</b></td><td><b>Construction Type</b></td><td><b>Self/Rent</b></td><td><b>Presume Annual Rent per Annum</b></td><td><b>Actual Annual Rent Value</b></td>
                        <td> <b>Edit</b> </td><td><b>Delete</b></td>
                    </tr>
                    </thead>

                    <tbody>

                    </tbody>

                    <%--                        <tr>
                                                <td><form:input class="form-control"  path="floorDetails[0].floorType" readonly="false"  name="floorDetails[][floorType]" value="floor 1" /></td>
                                                <td><form:input class="form-control"  path="floorDetails[0].carpetArea" readonly="true"  name="floorDetails[][carpetArea]" value="floor 2"  /></td>
                                                <td><form:input class="form-control"  path="floorDetails[0].propertyUse" readonly="true"  name="floorDetails[][propertyUse]"/></td>
                                                <td><form:input class="form-control"  path="floorDetails[0].wardNo" readonly="true" name="floorDetails[][propertySubtype]"/></td>
                                                <td><form:input class="form-control"  path="floorDetails[0].wardNo" readonly="true"  name="floorDetails[][constructionType]"/></td>
                                                <td><form:input class="form-control"  path="floorDetails[0].wardNo" readonly="true"  name="floorDetails[][selfRent]"/></td>
                                                <td><form:input class="form-control"  path="floorDetails[0].wardNo" readonly="true" name="floorDetails[][annualRent]"/></td>
                                                <td><form:checkbox path="floorDetails[0].wardNo" name="floorDetails[][editRecord]"  value="Y"  /></td>
                                                <td><form:checkbox path="floorDetails[0].wardNo" name="floorDetails[][deleteRecord]"  value="Y"  /></td> 

                        </tr>
                    --%>

                </table>

                <tr>
                    <td><input type="button" style="border: none;background: #333;font-size: 12px;height: 30px;text-shadow: none;" class="btn btn-dark btn-sm" value="+ Add row" onclick="test.addFloor();"/></td>
                </tr> 
            </table> 
            <table class="table border-dark  table-bordered">

                <tr ><th><label style="display:none" for="documentTypePropertyUse">Property Use Document</label></th><th><label style="display:none" for="documentTypeCovered">Covered Area Document</label></th></tr>

                <tr>


                    <td><select style="display:none" class="form-control" path = "documentTypePropertyUse" id="documentTypePropertyUse" name="documentTypePropertyUse">
                            <option value = "-1" >Select</option>
                            <option value = "PTR">Property Tax receipt of S.M.C</option>
                            <option value = "PR">Property Registry</option>
                            <option value = "FV">Field Verification(SMC Staff)</option>
                            <option value = "AO" >Other(Only valid Documents)</option>

                        </select> <br>
                        <label for="imagePropertyUse" style="display:none">Image File (Property Use)</label><input style="display:none" type="file" name="imagePropertyUse" id="imagePropertyUse" ></td>

                    <td><select style="display:none" class="form-control" path = "documentTypeCovered" id="documentTypeCovered" name="documentTypeCovered">
                            <option value = "-1">Select</option>
                            <option value = "PTR">Property Tax receipt of S.M.C</option>
                            <option value = "PR">Property Registry</option>
                            <option value = "SD">Sale Deed</option>
                            <option value = "AL">Architect letter</option>
                            <option value = "FV">Field Verification(SMC Staff)</option>
                            <option value = "AO">Other(Only valid Documents)</option>

                        </select> <br>
                        <label for="imageFileCovered" style="display:none">Image File(Covered Area)</label><input style="display:none" type="file" name="imageFileCovered" id="imageFileCovered" ></td>



                </tr>

                <tr> 
                    <td colspan="3" class="text-center">  <div class="col-3 float-left text-left"> 
                            <label for="applicantName" >Applicant Name </label>
                            <form:input class="form-control"  path="applicantName" id="applicantName" name="applicantName" value="${correction.applicantName}"/>
                        </div><div class="col-3 float-left text-left">
                            <label for="applicantMobileNo">Applicant Contact No. </label>
                            <form:input class="form-control"  path="applicantMobileNo" id="applicantMobileNo" name="applicantMobileNo" value="${correction.applicantMobileNo}"/>
                        </div>
                        </div><div class="col-3 float-left text-left">
                            <label for="remarks">Remarks </label>
                            <form:textarea rows="3" cols="100" class="form-control"  path="remarks" id="remarks" name="remarks" value=""/>
                        </div>


                    </td>

                </tr>
            </table>



            <div style="padding:30px 0px; text-align: center;"><input type="submit" class="btn-primary btn " value="Submit" ></div>
            </form:form>  

        <input type="hidden" id="hidd" dataattr=""></input>


    </div>
</div>

<input type="hidden" id="propertyId" value="${propertyId}" />
<!--<input type="hidden" id="complainNo" value="${complainNo}" />-->

</section>



<script>
    function showDocument(data, type_) {
        if (type_ != '') {
            var dd = type_.split(".");
            type_ = dd[1].toUpperCase();
        }

        var image = new Image();
        if (type_ == "JPEG" || type_ == "JPG") {
            image.src = "data:image/jpg;base64," + data;
        } else if (type_ == "PNG") {
            image.src = "data:image/png;base64," + data;
        } else if (type_ == "PDF") {
            var blob;
            blob = converBase64toBlob(data, 'application/pdf');
            var blobURL = URL.createObjectURL(blob);
           // window.open(blobURL);
           // 
           document.getElementById('iframePdf').src = blobURL;
           $('#iframePdf').show();
           
            //image.src = "data:application/pdf;base64," + data;
        } else if (type_ == "GIF") {
            image.src = "data:image/gif;base64," + data;
        } else {
            image.src = "data:image/jpg;base64," + data;
        }
        //image.src = "data:image/jpg;base64," + data;
        if (type_ != "PDF") {
//            var w = window.open("", "_blank", "toolbar=yes,top=500,left=500,width=600,height=600");
            $("#draggable_div_attch").html(image.outerHTML);
        }

    }


    function converBase64toBlob(content, contentType) {
        contentType = contentType || '';
        var sliceSize = 512;
        var byteCharacters = window.atob(content); //method which converts base64 to binary
        var byteArrays = [
        ];
        for (var offset = 0; offset < byteCharacters.length; offset += sliceSize) {
            var slice = byteCharacters.slice(offset, offset + sliceSize);
            var byteNumbers = new Array(slice.length);
            for (var i = 0; i < slice.length; i++) {
                byteNumbers[i] = slice.charCodeAt(i);
            }
            var byteArray = new Uint8Array(byteNumbers);
            byteArrays.push(byteArray);
        }
        var blob = new Blob(byteArrays, {
            type: contentType
        }); //statement which creates the blob
        return blob;
    }


</script>