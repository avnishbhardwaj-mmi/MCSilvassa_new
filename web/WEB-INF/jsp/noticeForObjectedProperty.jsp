<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@taglib uri="http://www.springframework.org/tags/form" prefix="form"%>

<script type="text/javascript" src="res/js/api/jquery.dataTables.min.js"></script>
<script src="res/js/api/confirmBox.js" type="text/javascript"></script>
<script src="res/js/notice_generation_for_obj_prop.js" type="text/javascript"></script>

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
                <div class="filterward pb-3"><h3  class="propeh p-0 m-0"  >Notice Generation After Objection</h3></div>
                <div class="filterother">
                    <div class="row">
                        <div class="col-md-12"> 
                            <div class="sbu-heading red"><%=request.getAttribute("msg")%></div>
                        </div>
                             <div class="col-md-6">
                            <div class="form-group">
                                <label class="labeltxt">Zone </label> 
                                <select class="form-control" id="zoneId_enc" >
                                    <option value="-1">----Select Zone----</option>
                                </select>
                            </div>
                        </div>
                        <div class="col-md-6">
                            <div class="form-group">
                                <label class="labeltxt">Ward</label> 
                                <select class="form-control" id="ward_enc">
                                    <option value="-1">--Select Ward--</option>
                                </select>
                            </div>
                        </div>
                        <div class="col-md-12">
                            <div class="span12 text-center">
                                <a href="javascript:void(0)" class="btn btn-primary" onclick="NOTICE_GEN.generate();" id="btnviewTax">Generate Notice</a>
                            </div>
                        </div>
                    </div>


                    <div  class="hidden">
                        <form:form name="noticeForm" action="generateNoticeAfterObjection" modelAttribute="filterBean" method="POST">
                            <input id="zoneId" name="zoneId" type="text" >
                            <input id="ward" name="ward" type="text" >
                            <!--<input id="propertyId" name="propertyId" type="text" >-->
                            <input type="submit" value="submit"/>
                        </form:form>
                    </div>
                </div>
            </div>
        </div>
    </div>
</section>
