<%-- 
    Document   : printPaymentReceipt
    Created on : 10 Apr, 2017, 10:09:59 AM
    Author     : Sandeep Gupta @MapmyIndia
--%>
<link rel="stylesheet" type="text/css" href="res/css/select2.css" />
<link rel="stylesheet" type="text/css" href="res/css/select2-bootstrap.css" />
<link rel="stylesheet" type="text/css" href="res/css/dataTables.bootstrap.min.css" />

<script src="res/js/api/jquery.dataTables.min.js"></script>
<script src="res/js/api/dataTables.bootstrap.min.js"></script>
<script src="res/js/api/select2.min.js"></script>
<script type="text/javascript" src="res/js/util.js"></script>
<script type="text/javascript" src="res/js/report/printPaymentReceipt.js"></script>
<script type="text/javascript" src="res/js/api/num2wordsConverter.js"></script>

<section id="searchArea" class="cont hidden">
    <div class="container-fluid contf">
        <div class="container-fluid contf" id="property_section">
            <div class="row-fluid">
                <div class="propeh">Print Payment Receipt</div>
                <div class="span3">
                    <div class="form-group">
                        <label class="labeltxt">Zone <span style="color: red;">*</span></label> 
                        <select class="form-control" name="zone" id="zone">
                            <option value="-1">--Select Zone --</option>
                        </select>
                    </div>
                </div>
                <div class="span3">
                    <div class="form-group">
                        <label class="labeltxt">Property ID </label> 
                        <select class="form-control" name="propertyid" id="propertyid">
                            <option value="-1">--Select Property ID--</option>
                        </select>
                    </div>
                </div>
                <div class="span1 text-center">
                    <label class="labeltxt" style="display:block;">&nbsp; </label> 
                    <a href="javascript:void(0)" class="btn btn-danger" id="btn_property_submit" onclick="">Search</a>
               </div>
            </div>
        </div>
    </div>
</section>
<section id="searchResults" class="cont hidden">
    <div class="container-fluid contf">
        <div class="propeh">Print Payment Receipt</div>
        <div class="row-fluid">

            <table id="tableSearchResult" data-page-length='8' class="display">
                <thead>
                    <tr>
                        <th></th>
                    </tr>
                </thead>
                <tbody>
                    <tr>
                        <td></td>
                    </tr>
                </tbody>
            </table>
        </div>
        <div class="row-fluid">
            <div class="span12 text-center">
                <!--a href="javascript: void(0)" class="btn btn-primary" onclick="PrintPaymentReceipt.exportAs('PDF')"> Download as PDF </a>
                <a href="javascript: void(0)" class="btn btn-primary" onclick="PrintPaymentReceipt.exportAs('EXCEL')"> Download as Excel </a-->
                <a href="#" id="btn_back_search_window" class="btn btn-primary">Back</a>
            </div>
        </div>
    </div>
</section>











