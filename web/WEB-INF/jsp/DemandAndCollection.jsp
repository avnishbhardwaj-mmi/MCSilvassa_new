<%-- 
    Document   : ArrearReport
    Created on : 30 May, 2019, 3:06:57 PM
    Author     : CEINFO
--%>
<%@taglib uri = "http://www.springframework.org/tags/form" prefix = "form"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<section class="row" id="demandAmountCallection">  
    <div class="col-lg-12 ">
        <div class="innerdashboard">
            <div id="tys_tab_div" class="conainer">
                <div class="filterward pb-2"><h3>Demand & collection register</h3></div>
                <div class="filterother">
                    <form:form  name="demandData" method="post" onsubmit="return validate();"  action="exportDemandAndCollection">
                        <div  class="row align-content-center"> 
                            <div class="col-md-3">
                                <label class="labeltxt">Ward</label>

                                <select id="ward" name="ward" class="form-control">

                                </select> 
                            </div>
                            <div class="col-md-3"><label>&nbsp;</label><br>
                                <input type="submit" value="Download" class="btn btn-primary"/>  
                            </div>
                        </div>
                    </form:form> 
                </div>
            </div>
        </div>
    </div>
</section>



<script>
    $(document).ready(function () {
        $("#report_main_menu").addClass("active");
        $("#demandAndCollection").addClass("active");
        $("#report_main_menu ul.sub-menu ").addClass("show");
        $("#breadlist").addClass('lisb');
        $("#breadlist").html('')
        $("#breadlist").html(' <a href="demandAndCollection">Demand & Collection</a> ');
        $.ajax({
            type: "POST",
            url: "getWards?zone=",
            contentType: "application/json; charset=utf-8",
            success: function (data) {
                //console.log(data);
                var wards = (data);
                var strHTML = "<option value='-1'>-- Select Ward --</option>";
                for (var b in wards) {
                    var ward = wards[b];
                    strHTML += "<option value='" + ward.ward + "'> " + ward.ward + " </option>";

                }
                $("#ward").html(strHTML);
            },
            error: function () {
            }
        });

    });


    function validate() {
        var ward = $("#ward").val();
        $("#validateid").html('');
        if (ward == '-1') {

            $("#validateid").html("Please select ward");
            //alert("Please select ward ");
            $("#ward").focus();
            return false;
        }

    }


</script>

