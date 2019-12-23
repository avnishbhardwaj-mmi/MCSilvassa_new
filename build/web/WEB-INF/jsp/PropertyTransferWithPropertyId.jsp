<%-- 
    Document   : correctionFrmValidate
    Created on : 26 Feb, 2019, 2:25:22 PM
    Author     : CEINFO
--%>


<script src="res/js/api/jquery.dataTables.min.js"></script>
<script src="res/js/api/dataTables.bootstrap.min.js"></script>
<script src="res/js/api/jquery-ui.js"></script>
<script src="res/js/api/select2.min.js"></script>
<%@taglib uri = "http://www.springframework.org/tags/form" prefix = "form"%>
<%
    String amt = "1";
    String dmsg = "";
    String piddata = "";
    if (request.getAttribute("AMT") != null) {
        amt = (String) request.getAttribute("AMT");
    }
    if (request.getAttribute("dmsg") != null) {
        dmsg = (String) request.getAttribute("dmsg");
    }
    if (request.getAttribute("piddata") != null) {
        piddata = (String) request.getAttribute("piddata");
    }
%>
<section class="row" id="taxPaymentView">
    <div class="col-lg-12 pl-4">
        <div class="innerdashboard">
            <div id="arreartab_div" class="conainer">
                <div class="filterward pb-3"><h3  class="propeh p-0 m-0" align="center">Property Transfer With Property Id</h3></div>
                <!-- <form id="noticeForm" name="noticeForm" method="post" action="generateNotice1" modelAttribute="noticeBean"> -->
                <div class="filterother">
                    <div id="msgId"></div>

                    <form:form  name="propertyWithPid" method="post" onsubmit="return validate();"  action="checkDuesPendingWithPropertyId">
                        <div class="row"> 
                            <div class="col-md-6 "> 
                                <label class="labeltxt"> Enter Property Id (Check dues or  pending against property id) </label>  
                                <input id="pid" name="pid" class="form-control" type="text" width="10"  >
                            </div>
                            <div class="col-md-6 ">  <label class="labeltxt">&nbsp;</label><br> 
                                <input type="submit" id="viewprivate" value="Submit" class="btn btn-primary">
                            </div>
                        </div>

                    </form:form>

                    <%
                        int payAmount = Integer.parseInt(amt);
                        //System.out.println("payAmount "+payAmount);
                        if (payAmount == 0 || payAmount < 0 || payAmount > 0) {%>

                    <div class="p-5">
                        <h5 class="pb-5" style="color:#17a2b8"><%=dmsg%><%=piddata%></h5>
                        <a href="propertyTransferWithInsturment?piddata=<%=piddata%>" class="btn btn-info mr-5">Property Transfer With Instrument</a>
                        <a href="propertyTransferWithOutInsturment?piddata=<%=piddata%>" class="btn btn-info mr-5">Property Transfer Without Instrument</a>
                    </div>
                    <%
                        }
                    %>
                </div>
            </div>
        </div>
    </div>
</section>



</body>
<script>
    function validate() {
        var pid = $("#pid").val().trim();
        if (pid == '') {
            alert("Property id  can't be left blank");
            $("#pid").focus();
            return false;
        }
    }
</script>
<script>
    $(document).ready(function () {
        $("#objection_main_menu").addClass("active");
        $("#propertyTransferWithId").addClass("active");
        $("#objection_main_menu ul.sub-menu ").addClass("show");
        if ($("#contactDetails ul.sub-menu").hasClass("show"))
        {
            $("#contactDetails ul.sub-menu").removeClass("show");
        }

        $("#breadlist").html('')
        $("#breadlist").html('<li><a href="propertyTransferWithPropertyId">Property Transfer</a></li>')

    });
</script>
</html>
