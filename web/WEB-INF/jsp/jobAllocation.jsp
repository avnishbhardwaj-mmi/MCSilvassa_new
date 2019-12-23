<%-- 
//----------------------------------------------------------------------------------------------------
//                          MapMyIndia
//            Product / Project           : Silvassa
//            Module                      : Job Allocation
//            File Name                   : jobAllocation
//            Author                      : Jay Prakash Kumar
//            Project Lead                :
//            Date written (DD/MM/YYYY)   : 17 Jul, 2017, 12:09:21 PM
//            Description                 : 
//----------------------------------------------------------------------------------------------------
//                                            CHANGE HISTORY
//----------------------------------------------------------------------------------------------------
// Date             Change By           Change Description (Bug No. (If Any))
// (DD/MM/YYYY)
//----------------------------------------------------------------------------------------------------
//----------------------------------------------------------------------------------------------------
--%>

<link href="res/css/datatable2.css" rel="stylesheet" type="text/css" />
<link href="res/css/buttons.dataTables.min.css" rel="stylesheet" type="text/css" />
<link rel="stylesheet" type="text/css" href="res/css/select2.css" />
<link rel="stylesheet" type="text/css" href="res/css/select2-bootstrap.css" />
<link rel="stylesheet" href="res/css/jquery-ui.css">
<script type="text/javascript" src="res/js/api/select2.min.js"></script>
<script type="text/javascript" src="res/js/api/jquery.dataTables.min.js"></script>
<script type="text/javascript" src="res/js/api/dataTables.buttons.min.js"></script>
<script type="text/javascript" src="res/js/api/buttons.flash.min.js"></script>
<script type="text/javascript" src="res/js/api/jszip.min.js"></script>
<script type="text/javascript" src="res/js/api/pdfmake.min.js"></script>
<script type="text/javascript" src="res/js/api/vfs_fonts.js"></script>
<script type="text/javascript" src="res/js/api/buttons.html5.min.js"></script>
<script type="text/javascript" src="res/js/api/buttons.print.min.js"></script>
<script type="text/javascript" src="res/js/api/jquery-dateFormat.js"></script>
<script type="text/javascript" src="res/js/util.js" type="text/javascript"></script>
<script type="text/javascript" src="res/js/jobAllocation.js" type="text/javascript"></script>


<section id="">

    <div class="container-fluid contf">
        <div class="row-fluid">
            <div class="propeh">Job Allocation</div>
            <div class="span3">
                <div class="form-group">
                    <label class="labeltxt">&nbsp;</label> 

                </div>
            </div>
            <div class="span3">
                <div class="form-group">
                    <label class="labeltxt">User<span style="color: red;">*</span> </label> 
                    <select class="form-control" name="user" id="userId">
                        <option value="-1">--Select User--</option>
                    </select>
                </div>
            </div>
            <div class="span3">
                <div class="form-group">
                    <label class="labeltxt">Zone<span style="color: red;">*</span> </label> 
                    <select class="form-control" name="zone" id="zoneId" >
                        <option value="-1">--Select Zone--</option>
                    </select>
                </div>
            </div>

        </div>
        <div class="clearfix"></div>
        <div class="row-fluid">
            <div class="span3">
                <div class="form-group">
                    <label class="labeltxt">&nbsp;</label> 
                </div>
            </div>
            <div class="span3">
                <div class="form-group">
                    <label class="labeltxt">Ward <span style="color: red;">*</span></label> 
                    <select class="form-control" name="ward" id="ward">
                        <option value="-1">--Select Ward--</option>
                    </select>
                </div>
            </div>
            <div class="span3">
                <div class="form-group">
                    <button class="btn btn-primary" id="btn_job_add" style="margin-top: 23px;" onclick="JobAllocation.addJobForSubmission();">Add</button>
                </div>
            </div>
        </div>

        <!--        <div class="clearfix"></div>
                <div class="row-fluid">
                    <div class="span12 text-center">
                        <button class="btn btn-danger" id="btn_property_submit" onclick="JobAllocation.allocateJob();">Allocate</button>
                    </div>
                </div>-->
    </div>
</section>
<section class="cont" id="addedJobs" style="display: none;">
    <div class="container-fluid contf" >
        <div class="row-fluid">
            <div  class="container-fluid contf">
                <table id="addedJobsTable" data-page-length='10' class="display" >
                </table>
            </div>
        </div>
        <div class="clearfix"></div>
        <div class="row-fluid">
            <div class="span12 text-center">
                <button class="btn btn-danger" id="btn_property_submit" onclick="JobAllocation.allocateJob();">Allocate</button>
            </div>
        </div>
    </div>
</section>
<section class="cont" id="searchResults" >
    <div class="container-fluid contf" >
        <div class="propeh">Allocated Jobs:</div>
        <div class="row-fluid">
            <div  class="container-fluid contf">
                <table id="property_tab" data-page-length='10' class="display" >
                </table>
            </div>
        </div>
        <div class="clearfix"></div>
        <div class="row-fluid">
            <div class="span12 text-center">
                <button class="btn btn-danger" onclick="JobAllocation.deAllocateJobs();">Deallocate</button>
            </div>
        </div>
    </div>
</section>
