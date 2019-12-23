<%-- 
    Document   : printObjectionReceipt
    Created on : 10 Apr, 2017, 10:09:27 AM
    Author     : Sandeep Gupta @MapmyIndia
--%>

<%@taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@ page import="java.io.*,java.util.*, javax.servlet.*,java.text.SimpleDateFormat" %>

<link rel="stylesheet" type="text/css" href="res/css/select2.css" />
<link rel="stylesheet" type="text/css" href="res/css/select2-bootstrap.css" />
<link rel="stylesheet" type="text/css" href="res/css/jquery-ui.css" />

<script src="res/js/api/confirmBox.js" type="text/javascript"></script>
<script type="text/javascript" src="res/js/api/select2.min.js"></script>
<script type="text/javascript" src="res/js/api/jquery.dataTables.min.js"></script>
<script type="text/javascript" src="res/js/api/dataTables.buttons.min.js"></script>
<script type="text/javascript" src="res/js/api/buttons.flash.min.js"></script>
<script type="text/javascript" src="res/js/api/jszip.min.js"></script>
<script type="text/javascript" src="res/js/api/pdfmake.min.js"></script>
<script type="text/javascript" src="res/js/api/vfs_fonts.js"></script>
<script type="text/javascript" src="res/js/api/buttons.html5.min.js"></script>
<script type="text/javascript" src="res/js/api/buttons.print.min.js"></script>
<script type="text/javascript" src="res/js/api/jquery-ui.js"></script>
<script type="text/javascript" src="res/js/util.js"></script>
<script type="text/javascript" src="res/js/report/printObjectionReceipt.js"></script>
<%@page import="java.util.Date"%>
<%@page import="java.text.SimpleDateFormat"%>
<%@page import="java.util.Calendar"%>

<%
    Calendar calendar = Calendar.getInstance();
    //calendar.add(Calendar.DAY_OF_MONTH, 1);
%>

<script>
    DATEPICKER.today = new Date('<%= calendar.get(Calendar.YEAR)%>', '<%= calendar.get(Calendar.MONTH)%>', '<%= calendar.get(Calendar.DAY_OF_MONTH)%>');
</script>

<section class="cont" id="searchCriteria">
    <div class="container-fluid contf" >
        <div class="row-fluid">
            <div class="propeh">Print Objection Receipt</div>
            <div class="clearfix"></div>
            <div class="span12">
                <div class="span3">
                    <div class="form-group">
                        <label class="labeltxt">Zone: <span style="color: red;">*</span></label> 
                        <select id="zone" class="form-control"  onchange="printObjectionReceipt.fillSearchCriteria();">
                            <option value='-1'>--Select Zone--</option>
                        </select>
                    </div>
                </div>
                <div class="span3">
                    <div class="form-group">
                        <label class="labeltxt">Property Id: </label> 
                        <select id="obj_prop_id" class="form-control">
                            <option value="-1">--Select Property Id--</option>
                        </select>
                    </div>
                </div>
                
                <div class="span1 text-center">
                    <label class="labeltxt" style="display:block;">&nbsp; </label> 
                <a href="javascript:void(0)" class="btn btn-danger" onclick="printObjectionReceipt.showObjectionPrintList();">Search</a>
               </div>
            </div>
            
        </div>

    </div>
</section>

<section class="cont" id="searchResults" style="display: none">
    <div class="container-fluid contf" >
         <div class="propeh">Print Objection Receipt</div>
        <div id=objection_table_div style="display: none;" class="container-fluid contf">
            <table id=objection_table data-page-length='8' class="display"  width="100%" cellspacing="0">
                <tr>
                    <td>Objection Receipt</td>
                </tr>
            </table>
        </div>
        <div class="clearfix"></div>
        <div class="row-fluid">
            <div class="span12 text-center">
                <!--a href="javascript: void(0)" class="btn btn-primary" onclick="ObjectionReport.exportAs('PDF')"> Download as PDF </a>
                <a href="javascript: void(0)" class="btn btn-primary" onclick="ObjectionReport.exportAs('EXCEL')"> Download as Excel </a-->
                <a href="javascript: void(0)" class="btn btn-primary" onclick="printObjectionReceipt.showSearchWindow();"> Back </a>
            </div>
        </div>
    </div>
</section>




