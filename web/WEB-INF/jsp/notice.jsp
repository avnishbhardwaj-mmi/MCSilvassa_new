<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@taglib uri="http://www.springframework.org/tags/form" prefix="form"%>

<link rel="stylesheet" type="text/css" href="res/css/easy-autocomplete.css" />

<script type="text/javascript" src="res/js/api/jquery.dataTables.min.js"></script>
<script src="res/js/api/confirmBox.js" type="text/javascript"></script>
<script src="res/js/notice_generation.js?v1" type="text/javascript"></script>
<script type="text/javascript" src="res/js/api/jquery.easy-autocomplete.js"></script>


<section class="cont_notice row">


    <%
        Object obj = request.getAttribute("noticeGeneratedOn");
        if (obj == null) {
    %> <script>
        NOTICE_GEN.lastUpdateOn = "";
    </script> <%
    } else {

        String lastUpdateOn = (String) obj;
    %> <script>
        NOTICE_GEN.lastUpdateOn = <%= "'" + lastUpdateOn + "'"%>;
    </script> <%
        }

    %>
    <!-- <form id="noticeForm" name="noticeForm" method="post" action="generateNotice1" modelAttribute="noticeBean"> -->
    <div class="col-lg-12 pl-4">
        <div class="innerdashboard">
            <div id="arreartab_div" class="conainer">
                <div class="filterward d-flex"><h3  class="propeh p-0 m-0"  >Notice Generation</h3>

                </div>
                <div class="filterother" style="min-height: auto;">
                    <div class="row">
                        <div class="col-md-12"> 
                            <div class="sbu-heading d-none"><%=request.getAttribute("msg")%></div>
                        </div>
                        <div class="col-md-3 d-none"> 
                            <div class="form-group">
                                <label class="labeltxt">Zone </label> 
                                <select class="form-control" id="zoneId_enc" >
                                    <option value="-1">----Select Zone----</option>
                                </select>
                            </div>
                        </div>
                        <div class="col-md-3 d-none">
                            <div class="form-group">
                                <label class="labeltxt" >Ward</label> 
                                <select class="form-control" id="ward_enc">
                                    <option value="-1">--Select Ward--</option>
                                </select>
                            </div>
                        </div>
                        <div class="col-md-3 d-none">
                            <div class="form-group">
                                <label class="labeltxt">Enter Property ID</label> 
                                <input  id="propertyId_enc" type="text" class="form-control" placeholder="">
                            </div>
                        </div>

                        <!-- ////new // -->



                        <div class="col">
                            <div class="form-group">
                                <label class="labeltxt">Property Id</label>
                                <input type="text" id="prop_id_input"  class="form-control" placeholder="">
                            </div>
                        </div>

                        <div class="col">
                            <div class="form-group">
                                <label class="labeltxt" data-toggle="tooltip" data-placement="top" title=".">Ward</label> 
                                <input type="text" id="ward"  class="form-control" placeholder="">
                            </div>
                        </div>

                        <div class="col">
                            <div class="form-group">
                                <label>Phone No.<span style="color: red;"></span></label>
                                <input type="text" id="Phone_No" placeholder="Phone No." class="form-control" placeholder="">
                            </div>
                        </div>

                        <div class="col">
                            <div class="form-group">
                                <label>Locality<span style="color: red;"></span></label>
                                <input type="text" id="locality"  class="form-control" placeholder="">
                            </div>
                        </div>
                        <div class="col">
                            <div class="form-group">
                                <label>Owner Name<span style="color: red;"></span></label>
                                <input type="text" id="ownerid"  class="form-control" placeholder="">
                            </div>
                        </div>
                    </div>
                    <div class="row">

                    </div>


                    <div  class="hidden">
                        <form:form name="noticeForm" action="generateNotice" modelAttribute="filterBean" method="POST">
                            <input id="zoneId" name="zoneId" type="text" >
                            <input id="ward" name="ward" type="text" >
                            <input id="propertyId" name="propertyId" type="text" >
                            <input type="submit" id='testtt' value="submit"/>

                        </form:form>


                    </div>

                    <div  class="hidden">
                        <form:form name="noticeForm1" id="notice" action="generatePrivateNotice" target="_blank" method="POST">
                            <input id="as_zone" name="zoneId" type="text" >
                            <input id="as_ward" name="ward" type="text" >
                            <input id="as_uniqueId" name="propertyId" type="text" >
                            <input type="submit" id="as_expo" value="submit"/>

                        </form:form>
                        <button id="export_btn"  class="btn btn-primary btn-lg btn-block" onclick="" >Export</button>


                    </div>   

                    <div  class="hidden">
                        <form:form name="notice142Form" id="notice142Form" action="generate142Notice" target="_blank" method="POST">
                            <input id="as_zone1" name="zoneId" type="text" >
                            <input id="as_ward1" name="ward" type="text" >
                            <input id="as_uniqueId1" name="propertyId" type="text" >
                            <input id="as_phone_no1" name="phone_no" type="text" >
                            <input id="as_locality1" name="locality" type="text" >
                            <input id="as_owner_Id1" name="owner_id" type="text" >
                            <input type="submit" id="as_expo12" value="submit"/>

                        </form:form>
                        <button id="export_btn"  class="btn btn-primary btn-lg btn-block" onclick="" >Export</button>


                    </div> 

                    <div  class="hidden">
                        <form:form name="notice143Form" id="notice143Form" action="generate143Notice" target="_blank" method="POST">
                            <input id="as_zone2" name="zoneId" type="text" >
                            <input id="as_ward2" name="ward" type="text" >
                            <input id="as_uniqueId2" name="propertyId" type="text" >
                            <input id="as_phone_no2" name="phone_no" type="text" >
                            <input id="as_locality2" name="locality" type="text" >
                            <input id="as_owner_Id2" name="owner_id" type="text" >
                            <input type="submit" id="as_expo13" value="submit"/>

                        </form:form>
                        <button id="export_btn"  class="btn btn-primary btn-lg btn-block" onclick="" >Export</button>


                    </div>  
                    <div  class="hidden">
                        <form:form name="noticeNumber" id="noticeNumber" action="generatePrivateNoticeNo" target="_blank" method="POST">
                            <input id="as_zone3" name="zoneId" type="text" >
                            <input id="as_ward3" name="ward" type="text" >
                            <input id="as_uniqueId3" name="propertyId" type="text" >
                            <input type="submit" id="as_expo14" value="submit"/>

                        </form:form>
                        <button id="export_btn"  class="btn btn-primary btn-lg btn-block" onclick="" >Export</button>


                    </div>   

                    <div  class="hidden">
                        <form:form name="billNumber" id="billNumber" action="generateBillNo"  method="POST">
                            <input id="as_zone4" name="zoneId" type="text" >
                            <input id="as_ward4" name="ward" type="text" >
                            <input id="as_uniqueId4" name="propertyId" type="text" >
                            <input id="as_phone_no4" name="phone_no" type="text" >
                            <input id="as_locality4" name="locality" type="text" >
                            <input id="as_owner_Id4" name="owner_id" type="text" >
                            <input type="submit" id="as_expo15" value="submit"/>

                        </form:form>
                        <button id="export_btn"  class="btn btn-primary btn-lg btn-block" onclick="" >Export</button>


                    </div>  

                    <div  class="hidden">
                        <form:form name="billPdf" id="billPdf" action="generateBillPDF" target="_blank" method="POST">
                            <input id="as_zone5" name="zoneId" type="text" >
                            <input id="as_ward5" name="ward" type="text" >
                            <input id="as_uniqueId5" name="propertyId" type="text" >
                            <input id="as_phone_no5" name="phone_no" type="text" >
                            <input id="as_locality5" name="locality" type="text" >
                            <input id="as_owner_Id5" name="owner_id" type="text" >
                            <input type="submit" id="as_expo16" value="submit"/>

                        </form:form>
                        <button id="export_btn"  class="btn btn-primary btn-lg btn-block" onclick="" >Export</button>


                    </div>           
                </div>

                <div class=" text-center ml-auto"> 
                    <a href="javascript:void(0)" class="btn btn-primary Blue_linear-gradient" data-toggle="tooltip" data-placement="top" title="Click here to generate bill."  onclick="NOTICE_GEN.billNumber();" id="btnviewTax">Bill Number</a>
                    <a href="javascript:void(0)" class="btn btn-primary Blue_linear-gradient" data-toggle="tooltip" data-placement="top" title="Click here to generate Notice u/s 141/Bill."  onclick="NOTICE_GEN.billPdf();" id="btnviewTax">Generate Notice u/s 141/Bill Pdf</a>
                    <a href="javascript:void(0)" class="btn btn-primary d-none " onclick="NOTICE_GEN.generate();" id="btnviewTax">Generate Notice</a>
                    <a href="javascript:void(0)" class="btn btn-primary greeen_linear-gradient d-none" onclick="NOTICE_GEN.privatenotice();" id="btnviewTax">Generate Private Notice</a>
                    <a href="javascript:void(0)" class="btn btn-primary red_new_Property" data-toggle="tooltip" data-placement="top" title="Click here to generate 142 notice." onclick="NOTICE_GEN.notice142();" id="btnviewTax">Generate 142 Notice</a>
                    <a href="javascript:void(0)" class="btn btn-primary red_new_Property" data-toggle="tooltip" data-placement="top" title="Click here to generate 143 notice."  onclick="NOTICE_GEN.notice143();" id="btnviewTax">Generate 143 Notice</a>
                    <a href="javascript:void(0)" class="btn btn-primary greeen_linear-gradient d-none" onclick="NOTICE_GEN.pvtnoticeNumber();" id="btnviewTax">Private Notice Number</a>


                </div>      

            </div>        
        </div>
    </div>
</section>
<script>
    $(document).ready(function () {
        $("#property_main_menu").addClass("active");
        $("#Ge_Notice").addClass("active");
        $("#property_main_menu ul.sub-menu ").addClass("show");

        $("#breadlist").html('')
        $("#breadlist").addClass('lisb');
        $("#breadlist").html('<li><a href="notice">Notice</a></li>')

        $("#validateid").html($(".sbu-heading").html());
    });

    $(document).ready(function () {
        NOTICE_GEN.propertyIdFilter();
        NOTICE_GEN.phoneNoFilter();
        NOTICE_GEN.wardlstFilter();
        NOTICE_GEN.localitylstFilter();
        NOTICE_GEN.ownerlstFilter();
    });
</script>
