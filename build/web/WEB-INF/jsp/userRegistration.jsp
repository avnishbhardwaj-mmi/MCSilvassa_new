<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
         pageEncoding="ISO-8859-1"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@ page import="com.silvassa.model.Usermaster"%>
<%@ page import="java.util.HashMap"%>
<%@ page import="java.util.List"%>
<%@ page import="com.google.gson.Gson"%>

<script src="res/js/user_registration.js"></script>



<div class="offlineUser"> <!-- <form id="noticeForm" name="noticeForm" method="post" action="generateNotice1" modelAttribute="noticeBean"> -->
    <form:form id="formdata" >
        <div class="innerdashboard">
            <div class="areaOfflineusers">
                <div class="inenrSection">
                    <div class="filterward d-flex  ">
                    <h2 class="propeh" align="center">Mobile/Email Update</h2>
                    <div class="ml-auto">                        
                                <input type="button" onclick="test.sendOTP();" class="btn btn-primary" value="Update">
                         
                        </div>
                    </div>
                    <div class="filterother ">
                        <div class="row">
                            <div class="col-md-3">
                                <div class="form-group">
                                    <label for="propertyId" class="d-block">Property id</label> 
                                    <input type="text" name="propertyId" id="propertyId" class="form-control" /> 
                                </div>
                            </div>
                            <div class="col-md-3">
                                <div class="form-group">
                                    <label class="d-block">&nbsp;</label>
                                    <input type="button" class="btn btn-primary" name=checkFill" id="checkFill" value="Search" onclick="test.checkFillData();" /> 
                                </div>
                            </div>
                        </div>
                        <div class="row">
                            <div class="col-md-3">
                                <div class="form-group">
                                    <label class="d-block" for="ownerName">Owner Name</label>
                                    <input type="text" readonly="true" name="ownerName" id="ownerName" class="form-control"/>
                                </div>
                            </div>
                            <div class="col-md-3">
                                <div class="form-group">
                                    <label  class="d-block" for="mobile">Owner Mobile Number</label>
                                    <input type="text" name="mobile" id="mobile" class="form-control"/>
                                </div>
                            </div>
                            <div class="col-md-3">
                                <div class="form-group">
                                    <label class="d-block"  for="email">Owner Email</label>
                                    <input type="text" name="email" id="email" class="form-control"/>
                                </div>
                            </div>
                    
                            <div class="col-md-3">
                                <div class="form-group">
                                    <label for="occupierName">Occupier Name</label>
                                    <input type="text" readonly="true" name="occupierName" id="occupierName" class="form-control" />
                                </div>
                            </div>
                            <div class="col-md-3">
                                <div class="form-group">
                                    <label for="mobile">Occupier Mobile Number</label>
                                    <input type="text" name="occupierMobile" id="occupierMobile" class="form-control"/>
                                </div>
                            </div>
                            <div class="col-md-3">
                                <div class="form-group">
                                    <label for="occupierEmail">Occupier Email</label>
                                    <input type="text" name="occupierEmail" id="occupierEmail" class="form-control"/>
                                </div>
                            </div>
                    
                            <div class="col-md-3">
                                <div class="form-group">
                                    <label for="spouseName">Spouse Name</label> 
                                    <input type="text" readonly="true" name="spouseName" id="spouseName" class="form-control"/> 
                                </div>
                            </div>
                            <div class="col-md-3">
                                <div class="form-group">
                                    <label for="address">Document Type</label> 
                                    <select name ="documentType" id="documentType" class="form-control" onchange="test.addTextBox();" class="form-control" >
                                        <option value = "-1">Select</option>
                                        <option value ="PTR">Property Tax Receipt of S.M.C</option>
                                        <option value ="PR">Property Registry</option>
                                        <option value ="SD">Sale Deed</option>
                                        <option value ="RA">Rent Agreement</option>
                                        <option value ="RR">Revenue Record</option>
                                        <option value ="RC">Ration card</option>
                                        <option value ="PC">Pan Card</option>
                                        <option value ="DL">Driving License</option>
                                        <option value ="AL">Architect Letter</option>
                                        <option value ="EB">Electric Bill</option>
                                        <option value ="TB">Telephone Bill</option>
                                        <option value ="VC">Voter Card</option>
                                        <option value ="RW">Registered Will or Gift Deed</option>
                                        <option value ="AO">Other(Only valid Documents)</option>

                                    </select> 
                                </div>
                            </div>
                            <div class="col-md-3">
                                <div class="form-group">
                                    <label for="fileDoc" class="d-block" >Upload Document (Image File Only)</label> 
                                    <input type="file" name="fileDoc"  id="fileDoc" style="width:120px;" accept="image/x-png,image/gif,image/jpeg" /> 
                                    <div class="btn btn-dark btn-sm ml-3" style="cursor: pointer"  onclick="test.showImage();" /><i class="material-icons" style="vertical-align: sub; font-size: 16px;">attach_file</i>View Document </div>
                            </div>
                        </div>
                    
                        <div class="col-md-3">
                            <div class="form-group">
                                <label for="address">Property Address</label> 
                                <textarea rows="5" cols="100" readonly="true" name="address" id="address" class="form-control"></textarea>
                            </div>
                        </div>
                        <div class="col-md-3" style="display:none">
                            <div class="form-group">
                                <label for="docOther" style="display:none">Document Other</label> 
                                <textarea rows="3" cols="100" style="display:none" name="docOther"  id="docOther" ></textarea> 
                            </div>
                        </div>
                        <div class="col-md-3">
                            <div class="form-group">
                                <div id="optpid" style="display:none"> 
                                    <table>
                                        <tr>
                                            <td><label for="otpvalidate">Enter OTP</label></td>
                                            <td><input type="text" name="otpId" id="otpvalidate"></td>
                                            <td colspan="2"><input type="button" onclick="test.verifyOTP();" class="btn btn-primary" value="Validate OTP"></td>
                                        </tr>
                                    </table>
                                </div>
                            </div>
                        </div>
                    </div>
                    


                </div>
            </div>
            <div class="span5 create_newuser">
                <fieldset>

              
                    <div class="form-group">
                        <legend></legend>
                      
                        <div id="divid">
                            <table>

                                <tr>

                                </tr>
                                <tr>
                                    </td>
                                </tr>
                                <tr>


                                    </td>
                                </tr>
                                <tr>


                                </tr>

                                <tr>


                                </tr>

                                <tr>
                                    <td colspan="2"></td>

                                </tr>
                            </table>
                        </div>
                        <!--                                <div id=imagedisp">
                                                        <table>
                                                                <tr>
                                                                    
                                                                    <td><td  style="cursor: pointer"  onclick="test.showImage();" />Image</td> 
                                                                     
                                                                </tr>
                                                            </table>
                                                        </div>    -->




                    </div>

                </fieldset>
            </div>

        </div>
    </div>
</form:form> 
</div>
<script>
    $(document).ready(function () {
        $("#objection_main_menu").addClass("active");
        $("#contactDetails ul.sub-menu").removeClass("show");
        $("#offline").addClass("active");


        $("li#objection_main_menu>ul.sub-menu").addClass("show");

        $("#objection_main_menu ul.sub-menu li ul ").addClass("childlevel-3");
        $("#contactDetails a ").addClass("ad154");

        $("#breadlist").html('')
        $("#breadlist").addClass('lisb');
        $("#breadlist").html(' <a href="userRegistration">Contact Update</a> ')

    });

// $('#aContactDetails').on('click',function (){
//     if ($("#aContactDetails").hasClass('ad154')) {
//            $("#contactDetails ul.sub-menu").addClass("show");
//        }else{
//              $("#contactDetails ul.sub-menu").removeClass("show");
//        }  
// }); 
</script>
