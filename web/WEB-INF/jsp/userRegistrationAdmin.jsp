<%@ page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@ page import="com.silvassa.model.Usermaster"%>
<%@ page import="java.util.HashMap"%>
<%@ page import="java.util.List"%>
<%@ page import="com.google.gson.Gson"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<% int i = 1;%>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
        <link href="res/css/datatable2.css" rel="stylesheet" type="text/css" />
        <script src="res/js/user_registration.js"></script>

    </head>
    <body>

    <style>
        .innner_iframe{    width: 100%;
                           height:740px;
                           border-width: 0px;
                           overflow: auto;
                           border-style: none;
                           border-color: #fff!important;
                           border-image: none;}
        .headerbg{background: #fff;}


    </style>
    <section   class="row_box cont_box"> <!-- <form id="noticeForm" name="noticeForm" method="post" action="generateNotice1" modelAttribute="noticeBean"> -->
        <form:form id="formdata" >
            <div class="row"  id="userRegFormAdminId" >
                <div class="col-lg-12 pl-0">
                    <div class="innerdashboard">
                        <div id="arreartab_div" class="conainer">
                            <div class="filterward pb-3"><h3  class="propeh p-0 m-0"  >Contact Update Requests</h3></div>
                            <div class="filterother">
                                <div class="row">
                                    <div class="col-md-12"> 

                                        <fieldset>
                                            <div class="form-group">
                                                <legend></legend>

                                                <div id="divid" class="innerContainer">
                                                    <table class="table table-bordered table-sm  table-hover   " style="width:100%">
                                                        <thead>
                                                            <tr>

                                                                <th class="th-sm">Sr.no.</th>
                                                                <th class="th-sm">Application No</th>
                                                                <th class="th-sm">Property ID</th>
                                                                <th class="th-sm">Applicant Name</th>
                                                                <th class="th-sm">Owner Name</th>
                                                                <th class="th-sm">Date</th>

                                                            </tr>
                                                        </thead>
                                                        <tbody class="mCustomScrollbar _mCS_1 mCS-autoHide" data-mcs-theme="minimal-dark" style="position: relative; overflow: visible;"><div id="mCSB_1" class="mCustomScrollBox mCS-minimal-dark mCSB_vertical mCSB_outside" style="max-height: none;" tabindex="0"><div id="mCSB_1_container" class="mCSB_container" style="position: relative; top: -43px; left: 0px;" dir="ltr">

                                                                <c:forEach items="${requestdata}" var = "correcItem" varStatus="loop" >
                                                                    <tr class="" style="cursor: pointer" onclick="openCorrectionForm('userRegistrationAdminForm?sno=${correcItem.sno}&pid=${correcItem.unique_id}');
                                                                            $(this).remove();" >
                                                                        <td class="td-sm"><%=i++%></td>
                                                                        <td class="td-sm">${correcItem.sno}</td>
                                                                        <td class="td-sm" style="color:#1E90FF">${correcItem.unique_id}</td>
                                                                        <td class="td-sm">${correcItem.applicant_name}</td>
                                                                        <td class="td-sm">${correcItem.owner_name}</td>
                                                                        <td class="td-sm">${correcItem.req_time}</td>





                                                                    </tr>
                                                                </c:forEach>

                                                            </div></div><div id="mCSB_1_scrollbar_vertical" class="mCSB_scrollTools mCSB_1_scrollbar mCS-minimal-dark mCSB_scrollTools_vertical" style="display: block;"><div class="mCSB_draggerContainer"><div id="mCSB_1_dragger_vertical" class="mCSB_dragger" style="position: absolute; min-height: 50px; display: block; height: 20px; max-height: 72px; top: 4px;"><div class="mCSB_dragger_bar" style="line-height: 50px;"></div></div><div class="mCSB_draggerRail"></div></div></div></tbody>
                                                    </table>

                                                </div>

                                            </div>

                                        </fieldset>
                                    </div>
                                </div>
                            </div>
                        </div>
                    </div>
                </div>
            </div>
            <div id="userRefAdminForm" class="row" style="display: none;">

                <div class="col-md-12 "> 
                    <div class="innerdashboard">
                        <div class=" ">
                            <iframe border="0" src=""  class="innner_iframe" ></iframe>
                        </div>
                    </div>
                </div>
            </div>   


        </form:form> </section>
    <script>

        function openCorrectionForm(formLink) {
            $('#userRefAdminForm').show();
            $('#userRegFormAdminId').hide();
            $('#userRefAdminForm iframe').attr("src", formLink);
        }
        function closeCorrectionForm() {
            location.reload();
        }

    </script>
</html>
<script>
    $(document).ready(function () {
        $("#objection_main_menu").addClass("active");
        $("#contactDetails ul.sub-menu").addClass("show");
        $("#online").addClass("active");



        $("li#objection_main_menu>ul.sub-menu").addClass("show");
        $("#objection_main_menu ul.sub-menu li ul ").addClass("childlevel-3");
        $("#contactDetails a ").addClass("ad154");

        $("#breadlist").html('')
        $("#breadlist").addClass('lisb');
        $("#breadlist").html('<li><a href="userRegistrationAdmin">Contact Update</a></li>')
    });

// $('#aContactDetails').on('click',function (){
//     if ($("#aContactDetails").hasClass('ad154')) {
//            $("#contactDetails ul.sub-menu").addClass("show");
//        }else{
//              $("#contactDetails ul.sub-menu").removeClass("show");
//        }  
// }); 
</script>