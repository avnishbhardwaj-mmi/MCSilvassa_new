<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@taglib uri="http://www.springframework.org/tags/form" prefix="form"%>

<script src="res/js/tax_generation.js?v1" type="text/javascript"></script>
<script src="res/js/api/confirmBox.js" type="text/javascript"></script>



<%
    Object obj = request.getAttribute("tAXGeneratedOn");
    if (obj == null) {
%> <script>
    TAX_GEN.lastUpdateOn = "";
</script> <%
} else {

    String lastUpdateOn = (String) obj;
%> <script>
    TAX_GEN.lastUpdateOn = <%= "'" + lastUpdateOn + "'"%>;
</script> <%
    }

%>

<section class="cont_textgen row">
    <div class="col-lg-12 ">
        <div class="innerdashboard">
            <div id="arreartab_div" class="conainer">
                <div class="filterward pb-3"><h3  class="propeh p-0 m-0"  >TAX Generation</h3></div>
                <div class="filterother">
                    <div class="row"> 
                        <div class="col-md-12"> 

                            <div class="sbu-heading"><%=request.getAttribute("msg")%></div>

                            <div class="row">
                                <div class="col-md-4 d-none"> 
                                    <div class="form-group">
                                        <label class="labeltxt">Zone </label> 
                                        <select class="form-control" id="zoneId_enc" name="zoneId">
                                            <option value="-1">----Select Zone----</option>
                                        </select>
                                    </div>
                                </div>
                                <div class="col-md-4"> 
                                    <div class="form-group">
                                        <label class="labeltxt">Ward</label> 
                                        <select class="form-control" id="prop_tax_ward">
                                            <option value="-1">--Select Ward--</option>
                                        </select>
                                    </div>
                                </div>
                                <div class="col-md-1 text-center"> 
                                    <div class="or_dv"><label>&nbsp;</label><br>
                                        <span>OR</span>
                                    </div>
                                </div>
                                <div class="col-md-3 "> 
                                    <div class="form-group">
                                        <label class="labeltxt">Enter Property ID</label> 
                                        <input id="propertyId_enc" type="text" class="form-control" placeholder="">
                                    </div>
                                </div>
                            </div>


                            <div class="col-12 text-center"><br>
                                <a href="javascript:void(0)" class="btn btn-primary" onclick="TAX_GEN.generate();" id="btnviewTax">Generate Tax</a>
                            </div>
                        </div>
                    </div>
                </div>
            </div>
            <div  class="hidden">
                <form:form name="noticeForm" action="generateTax" modelAttribute="filterBean" method="POST">
                    <input id="zoneId" name="zoneId" type="text" >
                    <input id="ward" name="ward" type="text" >
                    <input id="propertyId" name="propertyId" type="text" >
                    <input type="submit" value="submit"/>
                </form:form>
            </div>
        </div>
    </div>
</section>
<script>
    $(document).ready(function () {
        $("#report_main_menu").addClass("active");
        $("#taxgeneration").addClass("active");
        $("#report_main_menu ul.sub-menu ").addClass("show");
        $("#breadlist").addClass('lisb');
        $("#breadlist").html('')
        $("#breadlist").html(' <a href="taxgeneration">TAX Generation</a> ');
    });
</script>