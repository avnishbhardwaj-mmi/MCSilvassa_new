


<%@taglib uri="http://www.springframework.org/tags/form" prefix="form"%>

<link rel="stylesheet" type="text/css" href="res/css/select2.css" />
<link rel="stylesheet" type="text/css" href="res/css/select2-bootstrap.css" />
<link rel="stylesheet" type="text/css" href="res/css/jquery-ui.css" />

<script src="res/js/api/confirmBox.js" type="text/javascript"></script>
<script type="text/javascript" src="res/js/api/select2.min.js"></script>
<script type="text/javascript" src="res/js/api/jquery.dataTables.min.js"></script>
<script type="text/javascript" src="res/js/api/dataTables.buttons.min.js"></script>
<script type="text/javascript" src="res/js/api/jquery-ui.js"></script>
<script type="text/javascript" src="res/js/util.js"></script>
<script type="text/javascript" src="res/js/report/reportTaxAmount.js"></script>

<section class="cont" id="searchWindow">
    <div class="container-fluid contf" >
        <div class="row-fluid">
            <div class="propeh">Tax Amount</div>
            <div class="span12">
                <div class="span3">
                    <div class="form-group">
                        <label class="labeltxt">Zone: <span style="color: red;">*</span></label> 
                        <select id="zone" class="form-control" >
                            <option value='-1'>--Select Zone--</option>
                        </select>
                    </div>
                </div>
                <div class="span3">
                    <div class="form-group">
                        <label class="labeltxt">Min Tax amount: <span style="color: red;">*</span></label> 
                        <input id="sl_min_amount" type="number" value="" min="0" step="0.01" data-number-to-fixed="2" data-number-stepfactor="100" class="form-control currency" />
                    </div>
                </div>
                <div class="span3">
                    <div class="form-group">
                        <label class="labeltxt">Max Tax amount: <span style="color: red;">*</span></label> 
                        <input id="sl_max_amount" type="number" value="" min="0" step="0.01" data-number-to-fixed="2" data-number-stepfactor="100" class="form-control currency" />
                    </div>
                </div>
                <div class="span3">
                    <div class="form-group">
                        <label class="labeltxt" style="width:100%" >&nbsp;</label> 
                        <a href="javascript:void(0)" id="searchProp" class="btn btn-danger">Search</a>
                    </div>
                </div>
            </div>
        </div>
      

    </div>
</section>

<section class="cont hidden" id="searchResults" >
    <div class="container-fluid contf" >
        <div class="row-fluid">
            <div class="propeh">Tax Amount</div>
            <div id="property_tab_div" class="container-fluid contf">
                <table id="property_tab" data-page-length='8' class="display" >
                </table>
            </div>
        </div>
        <div class="clearfix"></div>
        <div class="row-fluid">
            <div class="span12 text-center">
                <a href="javascript: void(0)" class="btn btn-primary" onclick="REPORT.exportAs('PDF')"> Download as PDF </a>
                <a href="javascript: void(0)" class="btn btn-primary" onclick="REPORT.exportAs('EXCEL')"> Download as Excel </a>
                <a href="javascript: void(0)" class="btn btn-primary" onclick="REPORT.showSearchWindow();"> Back </a>
            </div>
        </div>
    </div>
</section>
