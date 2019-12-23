<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@taglib uri="http://www.springframework.org/tags/form" prefix="form"%>

<link rel="stylesheet" type="text/css" href="res/css/dataTables.bootstrap.min.css" />
<script src="res/js/api/jquery.dataTables.min.js"></script>
<script src="res/js/api/dataTables.bootstrap.min.js"></script>
<script src="res/js/util.js" type="text/javascript"></script>
<script src="res/js/notice_view.js?v2" type="text/javascript"></script>


<section id="vn_searchWindow" class="cont_be row">
    <div class="col-lg-12 pl-4">
        <div class="innerdashboard">
            <div id="arreartab_div" class="conainer">
                <div class="filterward pb-3"><h3  class="propeh p-0 m-0"  >View Notice</h3></div>
                <div class="filterother"> 
                    <div class="row">
                        <div class="col-md-4 d-none">
                            <div class="form-group">
                                <label class="labeltxt">Zone </label> 
                                <select class="form-control" id="notice_view_zone" name="zoneId">
                                    <option value="-1">----Select Zone----</option>
                                </select>
                            </div>
                        </div>
                        <div class="col-md-4">
                            <div class="form-group">
                                <label class="labeltxt">Ward</label> 
                                <select class="form-control" id="notice_view_ward">
                                    <option value="-1">--Select Ward--</option>
                                </select>
                            </div>
                        </div>  <div class="col-md-1">  <div class="or_b text-center">
                                <label class="labeltxt">&nbsp;</label><br>
                                <span>OR</span>
                            </div></div>
                        <div class="col-md-3"> 

                            <div class="form-group">
                                <label class="labeltxt">Enter Property ID</label> 
                                <input id="notice_view_prop_id" type="text" class="form-control" placeholder="">
                            </div>
                        </div>
                    </div>
                    <div class="span12 text-center">
                        <input class="btn btn-primary" type="button" value="View Notice" onclick="NoticeGeneration.getNoticeDetails()" />
                    </div>
                </div>
            </div>
        </div>
    </div>
</section>

<section id="vn_searchResult" class="cont_datatable hidden">
    <div class="col-lg-12 pl-4">
        <div class="innerdashboard">
            <div id="arreartab_div" class="conainer">
                <div class="filterward pb-3"><h3  class="propeh p-0 m-0"  >View Notice</h3></div>
                <div class="filterother">
                    <table id="vn_searchResult_table"  class="table table-bordered table-sm dataTable">
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
                <div class="clearfix"></div>
                <div class="row-fluid">
                    <div class="span12 text-center">
                        <a href="javascript: void(0)" class="btn btn-primary" onclick="NoticeGeneration.exportAs('PDF')"> Download as PDF </a>
                        <a href="javascript: void(0)" class="btn btn-primary" onclick="NoticeGeneration.exportAs('EXCEL')"> Download as Excel </a>
                        <input class="btn btn-primary" type="button" value="Back" onclick="NoticeGeneration.showSearchWindow()" />
                    </div>
                </div>
            </div>
        </div>
    </div>
</section>
<script>
    $(document).ready(function () {
         $("#property_main_menu").addClass("active");
         $("#notice_Window").addClass("active");
	 $("#property_main_menu ul.sub-menu ").addClass("show");
     });
</script>

