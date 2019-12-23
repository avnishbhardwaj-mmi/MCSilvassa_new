<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
         pageEncoding="ISO-8859-1"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@ page import="com.silvassa.model.Usermaster"%>
<%@ page import="java.util.HashMap"%>
<%@ page import="java.util.List"%>
<%@ page import="com.google.gson.Gson"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<%
    String msg = "";
    String pidVal = "";
    String newPidVal = "";
    if (request.getAttribute("MSG") != null) {
        msg = (String) request.getAttribute("MSG");
    }
    if (request.getAttribute("PID") != null) {
        pidVal = (String) request.getAttribute("PID");
    }
    if (request.getAttribute("NEWPID") != null) {
        newPidVal = (String) request.getAttribute("NEWPID");
    }
%>

<script src="res/js/user_registration.js"></script>
<script>
    function validate() {
        var pid = $("#propertyId").val();
        var newpid = $("#newPropertyId").val();
        //alert("pid "+pid);
        if (pid == '') {
            var hMsg = "Property id can't be left blank";
            //alert("");
            $('#validateid').fadeIn(400).html(hMsg).delay(5000).fadeOut(400);
            //alert("Property id can't be left blank");
            $("#propertyId").focus();
            return false;
        } else if (newpid == '') {

            var hMsg = "Property new Id can't be left blank";
            //alert("");
            $('#validateid').fadeIn(400).html(hMsg).delay(5000).fadeOut(400);
            // alert("Property new id can't be left blank");
            $("#newPropertyId").focus();
            return false;
        } else {
            return true;
        }

    }
</script>


<form:form action="propertySplitData" onsubmit="return validate();" id="formdata" >
    <section class="cont_split row"> <!-- <form id="noticeForm" name="noticeForm" method="post" action="generateNotice1" modelAttribute="noticeBean"> -->

        <div class="col-lg-12 ">
            <div class="innerdashboard">
                <div id="arreartab_div" class="conainer">
                    <div class="filterward d-flex "><h3  class="propeh p-0 m-0"  >Property split/New property </h3>
                    </div>
                    <div class="filterother">
                        <div class="row">
                            <div class="col-12 create_newuser">
                                <fieldset>


                                    <div class="form-group">
                                        <legend></legend>                          
                                        <div id="divid">
                                            <div class="row">
                                                <div class="col-md-3">
                                                    <div class="form-group">
                                                        <label for="propertyId">Property unique Id</label> 
                                                        <input type="text" name="propertyId" class="form-control" path="propertyId" value="<%=pidVal%>" id="propertyId" />
                                                    </div>
                                                </div>
                                                <div class="col-md-3">
                                                    <div class="form-group">
                                                        <label for="newPropertyId">Property new unique Id</label>
                                                        <input type="text" class="form-control" name="newPropertyId" path="newPropertyId" value="<%=newPidVal%>"  id="newPropertyId" />
                                                    </div>
                                                </div>
                                                <div class="col-md-3">
                                                    <div class="form-group">
                                                        <label for="propertyType">Property type</label> 
                                                        <select name ="propertyType" class="form-control" id="propertyType" path="propertyType" class="form-control"  >
                                                            <option value ="NewProperty">New property</option>
                                                            <option value ="PropertySplit">Property split</option>
                                                        </select>
                                                    </div>
                                                </div>
                                                <div class="col-md-3">
                                                    <div class="form-group ">   <label for="propertyType">&nbsp;</label> 
                                                        <div class="col-md-12 text-left"><input type="submit"    class="btn btn-primary" value="Submit"></div>
                                                    </div>
                                                </div>
                                            </div>

                                            <% if (newPidVal.trim().length() > 0) {

                                            %>
                                            <div style="display:none" class="row">
                                                <div class="col-md-12"><a href="propertySplitAddRow?pid=<%=newPidVal%>">Open form</a></td>
                                                </div>
                                            </div>
                                            <%}%>

                                        </div>
                                    </div>
                                </fieldset>
                            </div>
                        </div>
                    </div>
                </div>
            </div>
        </div>
    </section>
</form:form>
<script>
    $(document).ready(function () {

        $("#objection_main_menu").addClass("active");
        $("#propertySplitMerge").addClass("active");
        $("#objection_main_menu ul.sub-menu ").addClass("show");
        if ($("#contactDetails ul.sub-menu").hasClass("show"))
        {
            $("#contactDetails ul.sub-menu").removeClass("show");
        }
        $("#breadlist").addClass('lisb');
        $("#breadlist").html('')
        $("#breadlist").html(' <a href="propertySplit"   >Property Split</a> ');

    });
</script>



