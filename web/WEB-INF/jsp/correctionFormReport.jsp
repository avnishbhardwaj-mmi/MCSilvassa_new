<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
         pageEncoding="ISO-8859-1"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@ page import="com.silvassa.model.Usermaster"%>
<%@ page import="java.util.HashMap"%>
<%@ page import="java.util.List"%>
<%@ page import="com.google.gson.Gson"%>
<section class="row_0" id="searchResults">
    <div class="col-lg-12 p-0">
        <div class="innerdashboard">
            <div id="arreartab_div" class="conainer">
                <div class="filterward pb-2"><h3  class="propeh p-0 m-0" align="center">Correction report</h3></div>
                <!-- <form id="noticeForm" name="noticeForm" method="post" action="generateNotice1" modelAttribute="noticeBean"> -->
                <div class="filterother">
                    <form:form action="exportCorrectionFormReport" id="formdata" >

                        <div id="validateid"></div> 
                        <div class="row"> 
                            <div class="col-md-6 "> 
                                <label class="labeltxt">Report</label>
                                <select name ="report" id="report" class="form-control" >
                                    <option value ="owner">Ownership Name  Modification Report</option>
                                    <option value ="address">Address Modification  Report</option>
                                    <option value ="arrear">Arrear  Amount Related Report</option>
                                    <option value ="smchouseno">SMC Property House No. Related Report</option>
                                    <option value ="floor">Floor wise Property Related </option>
                                    <option value ="combinereport">Combine Report Property Related </option>
                                    <option value ="combineowner">Combine ownership Property Related </option>
                                </select> 
                            </div>
                            <div class="col-md-6"> <label>&nbsp;&nbsp;</label><br>
                                <button type="submit"  class="btn btn-primary" ><i class="material-icons" style="vertical-align: sub;font-size: 20px;">cloud_download</i> Download</button>
                                <button style="display:none" type="button" onclick="updateBuildingUse();"  class="btn btn-primary" ><i class="material-icons" style="vertical-align: sub;font-size: 20px;">cloud_download</i> Update Building Use</button>
                            </div>
                        </div>
                    </form:form>  
                </div>
            </div>
        </div>
    </div>
</section>
<script>
    function updateBuildingUse() {

        $.ajax({
            type: "GET",
            url: "updateBuildingUse",
            cache: false,
            success: function (data) {
                alert(data);
                //debugger;
//                        if (data == 'Property Id Found') {
//                           
//
//                        } else {
//                            alert(data);
//                            $("#uniqueIdTC").focus();
//                            return false;
//
//                        }
            }
        });
    }


</script>

<!--
</body>
</html>-->

<script>
    $(document).ready(function () {
        $("#report_main_menu").addClass("active");
        $("#correctionFormReport").addClass("active");
        $("#report_main_menu ul.sub-menu ").addClass("show");
        $("#breadlist").addClass('lisb');
        $("#breadlist").html('')
        $("#breadlist").html(' <a href="correctionFormReport">Correction Form </a> ');
    });
</script>