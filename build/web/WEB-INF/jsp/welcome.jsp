<script type="text/javascript" src="https://cdn.jsdelivr.net/momentjs/latest/moment.min.js"></script>
<script type="text/javascript" src="https://cdn.jsdelivr.net/npm/daterangepicker/daterangepicker.min.js"></script>
<link rel="stylesheet" type="text/css" href="https://cdn.jsdelivr.net/npm/daterangepicker/daterangepicker.css" />

<script src="https://code.highcharts.com/highcharts.js"></script>
<script src="https://code.highcharts.com/modules/exporting.js"></script>
<script src="https://code.highcharts.com/modules/export-data.js"></script>
<script src="https://code.highcharts.com/modules/accessibility.js"></script>
<link rel="stylesheet" type="text/css" href="https://cdn.datatables.net/v/dt/dt-1.10.18/b-1.5.6/datatables.min.css"/>
<%@ page import="java.util.*"%>
<%@ page import="com.silvassa.bean.LoginDetailsBean"%>
<script type="text/javascript" src="https://cdn.datatables.net/v/dt/dt-1.10.18/b-1.5.6/datatables.min.js"></script>
<%
    
    LoginDetailsBean loginDetails = new LoginDetailsBean();
    List<String> admin_ls = new ArrayList<String>();
    admin_ls.add("sunilk");
    admin_ls.add("smc_admin");
    List<String> tax_user = new ArrayList<String>();
    tax_user.add("krpatel");
    tax_user.add("grpatel");
    tax_user.add("shobhna5");

    boolean isAdmin = false;
    try {
        if (request.getSession() != null) {
            loginDetails = (LoginDetailsBean) request.getSession().getAttribute("userDetailsBean");
            if (admin_ls.indexOf(loginDetails.getUserId()) > -1) {
                isAdmin = true;
            }
        }
    } catch (Exception e) {
        //e.printStackTrace();
    }

%>
<script>
  
    
</script>
<div class="UserDashboard  ">
    <div class="container-fluid">
        <div class="row justify-content-center equal">
            <div class="col-md-6">
                <div class="list_porpertyDetails red_pro">
                    <div class="headertitleBox">
                        <div class="d-flex ">
                            <div class="iconsList"><i class="material-icons">list</i></div>
                            <div class="title_head"> Property Details</div>
                        </div>
                    </div>
                    <div class="ContainerListAllmenu d-flex">

                        <ul>
                            <li id="property_sub_menu"><a href="property"> <span class="material-icons">house</span>  Search  / Edit / Add property</a></li>
                             <% if (isAdmin) { %>
                            <li id="Ge_Notice"><a href="notice"> <span class="material-icons">assignment</span>   View / Generate Notice</a></li>
                            <% }%>
                            
                             <!--li id="propertyTransferWithId">
                                        <a href="propertyTransferWithPropertyId"> <span class="material-icons">account_balance</span>Property Transfer with ID</a>
                             </li-->
                             <li id="propertySplitMerge">
                                        <a href="propertySplit"><span class="material-icons">call_split</span>Property split/Merge</a>
                                    </li>
                             <li id="propertyTransferWithId">
                                   <a href="userRegistrationAdmin"> <span class="material-icons">person</span> Update  Contact Details Online</a>
                             </li>
                              <li id="propertyTransferWithId">
                                   <a href="userRegistration"> <span class="material-icons">person</span> Update Contact Details Offline</a>
                             </li>
                                    
                                   
<!--                            <li id="notice_Window"><a href="noticeWindow"><span class="material-icons">arrow_right</span> View Notice</a></li>-->


                        </ul>
                            <span class="material-icons ml-auto" style="
    font-size: 110px;
    color: #000;
    opacity: 0.2;
">house</span>

                    </div>
                </div>

            </div>
            <div class="col-md-6">
                <div class="list_porpertyDetails blue_tax">
                    <div class="headertitleBox">
                        <div class="d-flex  ">
                            <div class="iconsList"><i class="material-icons">view_module</i></div>
                            <div class="title_head"> Tax Collection</div>
                        </div>
                    </div>
                    <div class="ContainerListAllmenu d-flex">
                        <ul class="height_125"> 
                            <li id="tax_collection_main_menu"><a href="taxCollection"> <span class="material-icons">payment</span> Payment</a></li>
                            <% if (isAdmin) { %>
                            <li id="payment_approval"><a href="paymentApprovalWindow"><span class="material-icons">done</span> Payment Approval</a></li>
                            <% }%>
                            <!--                            <li id="taxSummary"><a href="#"> <span class="material-icons">arrow_right</span> Tax Summary</a></li>
                                                        <li id="Payment_History"><a href="#"> <span class="material-icons">arrow_right</span> Payment History</a></li>-->



                        </ul>
                            <span class="material-icons ml-auto" style="
    font-size: 110px;
    color: #000;
    opacity: 0.2;
">payment</span>

                    </div>
                </div>
            </div>
                            
            
            <div class="col-md-6">
                <% if (isAdmin) { %>
                <div class="list_porpertyDetails yellow_user_ma">
                    <div class="headertitleBox">
                        <div class="d-flex  ">
                            <div class="iconsList"><i class="material-icons">perm_data_setting</i></div>
                            <div class="title_head"> Reports</div>
                        </div>
                    </div>
                    <div class="ContainerListAllmenu d-flex">

                        <ul>
                            <li id="correctionFormReport">
                                <a href="correctionFormReport"><span class="material-icons">create</span> Correction report</a>
                            </li>
                            <li id="collectionReportID">
                                <a href="collectionReport"><span class="material-icons">business_center</span> Collection report</a>
                            </li>
                            <li id="arrearReport">
                                <a href="arrearReport"><span class="material-icons">menu_book</span> Arrear Report</a>
                            </li>
                            <li id="assessmenttaxView">
                                <a href="taxView"><span class="material-icons">assignment_turned_in</span> Assessment Register</a>
                            </li>
                            <li id="taxgeneration">
                                <a href="taxgeneration"><span class="material-icons"> money</span> TAX Generation</a>
                            </li>
                            <li id="demandAndCollection" class="d-none">
                                <a href="demandAndCollection"><span class="material-icons">playlist_add_check</span> Demand & collection register</a>
                            </li>

                        </ul>
<span class="material-icons ml-auto" style="
    font-size: 110px;
    color: #000;
    opacity: 0.2;
">perm_data_setting</span>

                    </div>

                </div>
                <% }%>
            </div>
            <div class="col-md-6 ">
                <div class="list_porpertyDetails green_report">
                    <div class="headertitleBox">
                        <div class="d-flex ">
                            <div class="iconsList"><i class="material-icons">supervised_user_circle</i></div>
                            <div class="title_head"> User Management</div>
                        </div>
                    </div>

                    <div class="ContainerListAllmenu d-flex">

                      
                        <ul class="height_125" >
                                    <% // if (userPermissionMap.containsKey("user_mgmt_main_menu")) {%>
                                    <li id="user_mgmt_sub_menu_permission"><a href="userManagement?id=permission"><span class="material-icons">verified_user</span> User Permission</a></li>
                                        <% // } %>
                                        <% // if (userPermissionMap.containsKey("user_mgmt_main_menu")) {%>
                                    <li id="user_mgmt_sub_menu_create"><a href="userManagement?id=create"><span class="material-icons">person_add</span> Create User </a></li>
                                        <% //} %>
                                        <% // if (userPermissionMap.containsKey("user_mgmt_main_menu")) {%>
                                    <!--<li id="user_mgmt_sub_menu_create"><a href="userRegistration">User Registration </a></li>-->
                                    <% //} %>

                                </ul>

                        <span class="material-icons ml-auto" style="
    font-size: 110px;
    color: #000;
    opacity: 0.2;
">supervised_user_circle</span>

                             

                    </div>

                </div>
            </div>
        </div>
            <% if (isAdmin) { %>
        <div class="graphSecHomePage">
            <div class="row justify-content-center">
                <div class="col-md-12">
                    <div class="containerGraphfilter d-none">
                        <div class="Datefilter d-flex">
                            <h3>Total Complaints Reported</h3>
                            <div class="dataPiker ml-auto">
                                <div id="reportrange"  >
                                    <i class="fa fa-calendar"></i>&nbsp;
                                    <span></span> <i class="fa fa-caret-down"></i>
                                </div>
                            </div>
                        </div>
                        <div class="graphareaBox">


                            <div id="container" style="min-width: 310px; height: 300px; margin: 0 auto">    
                            </div>
                            <div class="cardTable" id="wardWiseDiv"><a  class="backBtn" href="#"><i class="material-icons">keyboard_arrow_left</i>Back</a>
                                <table class="table" id="wardWiseTable" style="width:100%"></table>
                            </div>


                        </div>
                    </div>
                </div>
            </div>
        </div>
 <% }%>
    </div>
</div>


<!--<section id="vn_searchWindow" class="cont">
    <div class="container_b contf">
        <div class="row justify-content-center" id="notice_search">
            
            <div style="color : #676565" class="col-6 pt-5  text-left">
                <h1>&nbsp;</h1>
                <h1 style="color:#005cbf ; font-weight: 700;">Silvassa Municipal Council</h1>
                <div style="color : #676565" class="propeh">Welcome to Silvassa Municipal Council Integrated Property Information Portal.</div>
                <h1>&nbsp;</h1><h1>&nbsp;</h1>
            </div>
            
            <div class="col-6  text-right ">
                <div class="form-group">
                    <h1>&nbsp;</h1>
                    <img src="res/img/SilvassaMunicipalCouncil.jpg" alt=""  class="img-responsive" style="max-width: 100%;"/>
                </div>
            </div>
        </div>
    </div>
</section>-->



<div class="UserDashboard supperAdminSection d-none">
    <div class="container-fluid">
        <div class="row justify-content-center">
            <div class="col-md-3">
                <div class="list_porpertyDetails">
                    <div class="headertitleBox" onclick="$(this).parent().toggleClass('addListShow')">
                        <div class="d-flex ">
                            <div class="iconsList" ><i class="material-icons">list</i></div>
                            <div class="title_head"> Property Details</div>
                        </div>
                    </div>
                    <div class="ContainerListAllmenu">

                        <ul>
                            <li id="property_sub_menu"><a href="property"> <span class="material-icons">arrow_right</span>  View Property</a></li>
                            <li id="Ge_Notice"><a href="notice"> <span class="material-icons">arrow_right</span>   Generate Notice</a></li>
                            <li id="notice_Window"><a href="noticeWindow"><span class="material-icons">arrow_right</span> View Notice</a></li>


                        </ul>

                    </div>
                </div>

            </div>
            <div class="col-md-3">
                <div class="list_porpertyDetails">
                    <div class="headertitleBox" onclick="$(this).parent().toggleClass('addListShow')">
                        <div class="d-flex  ">
                            <div class="iconsList"  ><i class="material-icons">view_module</i></div>
                            <div class="title_head"> Tax Collection</div>
                        </div>
                    </div>
                    <div class="ContainerListAllmenu">
                        <ul> 
                            <li id="tax_collection_main_menu"><a href="taxCollection"> <span class="material-icons">arrow_right</span> Payment</a></li>
                            <!--                            <li id="taxSummary"><a href="#"> <span class="material-icons">arrow_right</span> Tax Summary</a></li>
                                                        <li id="Payment_History"><a href="#"> <span class="material-icons">arrow_right</span> Payment History</a></li>-->



                        </ul>

                    </div>
                </div>
            </div>
            <div class="col-md-3">
                <div class="list_porpertyDetails">
                    <div class="headertitleBox" onclick="$(this).parent().toggleClass('addListShow')">
                        <div class="d-flex ">
                            <div class="iconsList" ><i class="material-icons">bar_chart</i></div>
                            <div class="title_head"> Objection</div>
                        </div>
                    </div>

                    <div class="ContainerListAllmenu">

                        <ul>
                            <li>
                                <a href="dashboard?actionFliter=1"> <span class="material-icons">arrow_right</span> Correction Portal</a>

                            </li>
                            <li >
                                <a href="javascript:void(0)" onclick="$('#online_contactD').toggle();"> <span class="material-icons">arrow_right</span> Contact Details Update</a>
                                <ul style="display: none;" class="ml-3 row m-0 " id="online_contactD"><li class="col-md-6" ><a href="userRegistrationAdmin"><span class="material-icons pl-1 " style="font-size: 16px; vertical-align: text-top; margin-right: 5ppx;">edit</span>Online </a></li>
                                    <li class="col-md-6" ><a href="userRegistration" ><span class="material-icons" style="font-size: 16px; vertical-align: text-top; margin-right: 5ppx;">edit</span>Offline </a></li></ul>
                            </li>
                            <li id="propertySplitMerge">
                                <a href="propertySplit"><span class="material-icons">arrow_right</span> Property split/Merge</a>
                            </li>
                            <li id="AddProperty">
                                <a href="newProperty"><span class="material-icons">arrow_right</span> Add Property</a>
                            </li>
                            <li id="editProperty">
                                <a href="editProperty"><span class="material-icons">arrow_right</span>  Edit Property</a>
                            </li>
                            <li id="propertyTransferWithId">
                                <a href="propertyTransferWithPropertyId"><span class="material-icons">arrow_right</span> Property Transfer with ID</a>
                            </li>

                        </ul>

                    </div>

                </div>
            </div>
            <div class="col-md-3">
                <div class="list_porpertyDetails">
                    <div class="headertitleBox" onclick="$(this).parent().toggleClass('addListShow')">
                        <div class="d-flex  ">
                            <div class="iconsList"  ><i class="material-icons">table_chart</i></div>
                            <div class="title_head"> Reports</div>
                        </div>
                    </div>
                    <div class="ContainerListAllmenu" >

                        <ul>
                            <li id="correctionFormReport">
                                <a href="correctionFormReport"><span class="material-icons">arrow_right</span> Correction report</a>
                            </li>
                            <li id="collectionReportID">
                                <a href="collectionReport"><span class="material-icons">arrow_right</span> Collection report</a>
                            </li>
                            <li id="arrearReport">
                                <a href="arrearReport"><span class="material-icons">arrow_right</span> Arrear Report</a>
                            </li>
                            <li id="assessmenttaxView">
                                <a href="taxView"><span class="material-icons">arrow_right</span> Assessment Register</a>
                            </li>
                            <li id="taxgeneration">
                                <a href="taxgeneration"><span class="material-icons">arrow_right</span> TAX Generation</a>
                            </li>
                            <li id="demandAndCollection">
                                <a href="demandAndCollection"><span class="material-icons">arrow_right</span> Demand & collection register</a>
                            </li>

                        </ul>

                    </div>

                </div>
            </div>
        </div>
        <div class="graphSecHomePage">
            <div class="row justify-content-center">
                <div class="col-md-12">
                    <div class="containerGraphfilter d-none">
                        <div class="Datefilter d-flex">
                            <h3>Dashboard</h3>
                            <div class="dataPiker ml-auto">
                                <div id="reportrange_admin"  >
                                    <i class="fa fa-calendar"></i>&nbsp;
                                    <span></span> <i class="fa fa-caret-down"></i>
                                </div>
                            </div>
                        </div>
                        <div class="graphareaBox">
                            <div class="row">
                                <div class="col-md-6">
                                    <div class="chartSilvassa">
                                        <h3>Ward Wise Collection</h3>
                                        <div id="container_list1" style="min-width: 310px; height: 300px; margin: 0 auto"></div>
                                        <div class="cardTable" id="wardWiseDiv2">

                                            <table class="table" id="wardWiseTable" style="width:100%"></table>
                                        </div>

                                    </div>
                                </div>
                                <div class="col-md-6">
                                    <div class="chartSilvassa">
                                        <h3>Arrear</h3>
                                        <div id="container_list2" style="min-width: 310px; height: 300px; margin: 0 auto"></div>
                                    </div>

                                </div>
                                <div class="col-md-6">
                                    <div class="chartSilvassa">
                                        <h3>Property Transfer</h3>
                                        <div id="container_list3" style="min-width: 310px; height: 300px; margin: 0 auto"></div>
                                    </div>

                                </div>
                                <div class="col-md-6">
                                    <div class="chartSilvassa">
                                        <h3>Total Complaints Dashboard</h3>
                                        <div id="container_list4" style="min-width: 310px; height: 300px; margin: 0 auto"></div>
                                    </div>
                                </div>
                            </div>
                        </div>
                    </div>
                </div>
            </div>
        </div>

    </div>
</div>

<script>
    $(document).ready(function () {


var chart;
        $('.backBtn').on('click', function () {
            $("#wardWiseDiv").animate({right: "-100%"}, 500);
        });
        $("#welcome_main_menu").addClass("active");
     
        getChartData();

        // $(window).trigger('resize');
    });

    var containerlist1;
    var container;
    function getChartData()
    {
        
        var dt = $('#reportrange span').html();

        var st_end = dt.split('-');
        var start = st_end[0].split('/');
        var end = st_end[1].split('/');
        var frmDate = start[2].trim().concat('-' + start[1] + '-' + start[0]);
        var toDate = end[2].trim().concat('-' + end[1] + '-' + end[0].trim());

        $.ajax({
            type: "POST",
            url: "getChartData",
            data: {frmDate: frmDate, toDate: toDate},
            success: function (data) {
                //console.log(data);
                containerlist1 = data[0];
                container = data[1];
                //  console.log(x['W-14']);
                // console.log(x['W-13']);
                userhighchart();
                highchart();


            }

        });
    }


    function userhighchart() {

      chart =  Highcharts.chart('container', {
            chart: {
                type: 'area',
            },
            resize: true,
            title: {
                text: ' '
            },
            xAxis: {
                categories: ['Inbox', 'Unread', 'Solved', 'Rejected', 'Field Verification', 'Pending for approval'],
                tickmarkPlacement: 'on',
                title: {
                    enabled: false
                }
            },
            yAxis: {
                title: {
                    text: 'Complaints In Number'
                },
                labels: {
                    formatter: function () {
                        return this.value;
                    }
                }
            },
            tooltip: {
                style: {
            fontSize : '13px',
            
        }
                //pointFormat: '<font size="3"><{point.y}</font>'
            },
        
//            plotOptions: {
//                area: {
//                    stacking: 'normal',
//                    lineColor: '#666666',
//                    lineWidth: 1,
//                    marker: {
//                        lineWidth: 1,
//                        lineColor: '#666666'
//                    }
//                },
//                point: {
//                    events: {
//                        click: function () {
//                           // serverOperation.getRiskAndSaftyReport(this.category, this.series.name);
//                            complaintWiseData(this.category);
//                            //alert ('Category: '+ this.category +', value: '+ this.series.name);
//                        },
//                    },
//               }
//            },
            plotOptions: {
                series: {
                    cursor: 'pointer',
                    point: {
                        events: {
                            click: function () {
                                //  serverOperation.getRiskAndSaftyReport(this.category, this.series.name);
                                complaintWiseData(this.category);
                                //alert ('Category: '+ this.category +', value: '+ this.series.name);
                            },
                        },
                    },
                    events: {
                        legendItemClick: function (e) {
                            e.preventDefault();
                        }

                    }
                }
            },
            series: [
                {
                    name: 'Complaints',
                    data: [container.inbox.tcount, container.overdue.tcount, container.solve.tcount, container.reject.tcount, container.fieldverify.tcount, container.approve.tcount]
                }]
        });
      
    }

    $(function () {

        var start = moment().subtract(1, 'month');
        var end = moment();

        function cb(start, end) {
            $('#reportrange span, #reportrange_admin span').html(start.format('DD/MM/YYYY') + ' - ' + end.format('DD/MM/YYYY'));
            getChartData();
        }

        $('#reportrange, #reportrange_admin').daterangepicker({
            startDate: start,
            endDate: end,
            opens: 'left',
            ranges: {
                'Today': [moment(), moment()],
                'Yesterday': [moment().subtract(1, 'days'), moment().subtract(1, 'days')],
                'Last 7 Days': [moment().subtract(6, 'days'), moment()],
                'Last 30 Days': [moment().subtract(29, 'days'), moment()],
                'This Month': [moment().startOf('month'), moment().endOf('month')],
                'Last Month': [moment().subtract(1, 'month').startOf('month'), moment().subtract(1, 'month').endOf('month')]
            }
        }, cb);

        cb(start, end);

    });


    var pieColors = (function () {
        var colors = [],
                base = Highcharts.getOptions().colors[0],
                i;

        for (i = 0; i < 10; i += 1) {
            // Start out with a darkened base color (negative brighten), and end
            // up with a much brighter color
            colors.push(Highcharts.Color(base).brighten((i - 3) / 7).get());
        }
        return colors;
    }());

// Build the chart
    function complaintWiseData(cmpType) {

        var dt = $('#reportrange span').html();

        var st_end = dt.split('-');
        var start = st_end[0].split('/');
        var end = st_end[1].split('/');
        //var frmDate = start[2].trim().concat('-' + start[0] + '-' + start[1]);
        //var toDate = end[2].trim().concat('-' + end[0].trim() + '-' + end[1]);
        var frmDate = start[2].trim().concat('-' + start[1] + '-' + start[0]);
        var toDate = end[2].trim().concat('-' + end[1] + '-' + end[0].trim());
        //alert("kjhf" + uu + "date" + dt);


        $.post(
                "getcomplaintWiseData", {
                    fromDate: frmDate,
                    todate: toDate,
                    cmpType: cmpType
                },
        function (result) {
            debugger;
            //console.log("result" + result);
            if ((result != null || result != undefined)) {
                var obj_html = "";
                obj_html += "<thead><tr><th>S No</th><th>Property ID</th><th>Applicant Name</th><th>Date</th><th>Ward</th></tr></thead><tbody>";
                for (var i = 0; i < result.length; i++) {
                    var property = result[i];
                    obj_html += "<tr><td>" + (i + 1) + "</td>";
                    obj_html += "<td style='text-align:left' id='" + property.unique_id + "'>" + property.unique_id + " </td>";
                    obj_html += "<td style='text-align:left' id='" + property.applicant_name + "'>" + property.applicant_name + " </td>";
                    obj_html += "<td style='text-align:left' id='" + property.notice_date + "'>" + property.notice_date + " </td>";
                    obj_html += "<td style='text-align:left' >" + property.ward_no + " </td>";
                    obj_html += "</tr>";
                }
                obj_html += "</tbody>";
                $("#wardWiseTable").html(obj_html);

                $('#wardWiseTable').DataTable(
                        {
                            "scrollX": true,
                            dom: 'Bfrtip',
                            buttons: [{
                                    extend: 'excel',
                                    text: 'Export to Excel',
                                }],
                            //"lengthMenu" : [ [ 15, 30, 50, -1 ], [ 15, 30, 50, "All" ] ],
                            "scrollY": "150px",
                            "bDestroy": true,
                            "responsive": true,
                            "paging": true,
                            "sPaginationType": "full_numbers"

                        });
            } else {
                alert("No result found.");
                return false;
            }
            // $("#wardWiseTable").wrap('<div class="tablescroll"></div>');
            $("#wardWiseDiv").animate({right: "0"}, 500);
            return true;
        }, 'json').always(function () {
            //LOADER.hide();
        });


    }

    function highchart() {
        Highcharts.chart('container_list1', {
            chart: {
                plotBackgroundColor: null,
                plotBorderWidth: null,
                plotShadow: false,
                type: 'pie'
            },
            title: {
                text: 'Browser market shares in January, 2018'
            },
            tooltip: {
                pointFormat: '{series.name}: <b>{point.percentage:.1f}%</b>'
            },
            plotOptions: {
                pie: {
                    allowPointSelect: true,
                    cursor: 'pointer',
                    dataLabels: {
                        enabled: false
                    },
                    showInLegend: true
                }
            },
            series: [{
                    name: 'Ward Wise Collection',
                    colorByPoint: true,
                    point: {
                        events: {
                            click: function (event) {
                                wardWiseData(this.name);
                                //alert('Category: '+ this.name +', value: '+ this.series.name);
                            },
                            legendItemClick: function () {
                                return false;
                            }
                        }
                    },
                    data: [{
                            name: 'W-1',
                            y: containerlist1['W-1'] === undefined ? 0 : containerlist1['W-1']
                                    //sliced: true,
                                    //selected: true
                        }, {
                            name: 'W-2',
                            y: containerlist1['W-2'] === undefined ? 0 : containerlist1['W-2']
                        }, {
                            name: 'W-3',
                            y: containerlist1['W-3'] === undefined ? 0 : containerlist1['W-3']
                        }, {
                            name: 'W-4',
                            y: containerlist1['W-4'] === undefined ? 0 : containerlist1['W-4']
                        }, {
                            name: 'W-5',
                            y: containerlist1['W-5'] === undefined ? 0 : containerlist1['W-5']
                        }, {
                            name: 'W-6',
                            y: containerlist1['W-6'] === undefined ? 0 : containerlist1['W-6']
                        }, {
                            name: 'W-7',
                            y: containerlist1['W-7'] === undefined ? 0 : containerlist1['W-7']
                        }, {
                            name: 'W-8',
                            y: containerlist1['W-8'] === undefined ? 0 : containerlist1['W-8']
                        }, {
                            name: 'W-9',
                            y: containerlist1['W-9'] === undefined ? 0 : containerlist1['W-9']
                        }, {
                            name: 'W-10',
                            y: containerlist1['W-10'] === undefined ? 0 : containerlist1['W-10']
                        }, {
                            name: 'W-11',
                            y: containerlist1['W-11'] === undefined ? 0 : containerlist1['W-11']
                        }, {
                            name: 'W-12',
                            y: containerlist1['W-12'] === undefined ? 0 : containerlist1['W-12']
                        }, {
                            name: 'W-13',
                            y: containerlist1['W-13'] === undefined ? 0 : containerlist1['W-13']
                        }, {
                            name: 'W-14',
                            y: containerlist1['W-14'] === undefined ? 0 : containerlist1['W-14']
                        }, {
                            name: 'W-15',
                            y: containerlist1['W-15'] === undefined ? 0 : containerlist1['W-15']
                        }]
                }]
        });
    }

    function wardWiseData(ward)
    {
        $.post(
                "getWardWiseData", {
                    ward: ward

                },
        function (result) {
            //console.log("result" + result);
            if ((result != null || result != undefined)) {
                var obj_html = "";
                obj_html += "<thead><tr><th>S No</th><th>Occupier name</th><th>Owner name</th></tr></thead><tbody>";
                for (var i = 0; i < result.length; i++) {
                    var property = result[i];
                    obj_html += "<tr><td>" + (i + 1) + "</td>";
                    obj_html += "<td style='text-align:left' id='" + property.entityname + "'>" + property.occupier_name + " </td>";
                    obj_html += "<td style='text-align:left' >" + property.owner_name + " </td>";
                    obj_html += "</tr>";
                }
                obj_html += "</tbody>";
                $("#wardWiseTable").html(obj_html);

                $('#wardWiseTable').DataTable(
                        {
                            // "scrollX": true,
                            dom: 'Bfrtip',
                            buttons: [{
                                    extend: 'excel',
                                    text: 'Export to Excel',
                                }],
                            //"lengthMenu" : [ [ 15, 30, 50, -1 ], [ 15, 30, 50, "All" ] ],

                            "bDestroy": true,
                            "responsive": true,
                            "paging": true,
                            "sPaginationType": "full_numbers"

                        });
            } else {
                alert("No result found.");
                return false;
            }
            $("#wardWiseTable").show();
            return true;
        }, 'json').always(function () {
            //LOADER.hide();
        });
    }
// Create the chart
    Highcharts.chart('container_list2', {
        chart: {
            type: 'column'
        },
        xAxis: {
            type: 'category'
        },
        yAxis: {
            title: {
                text: 'Total percent market share'
            }

        },
        legend: {
            enabled: false
        },
        plotOptions: {
            series: {
                borderWidth: 0,
                dataLabels: {
                    enabled: true,
                    format: '{point.y:.1f}%'
                }
            }
        },
        tooltip: {
            headerFormat: '<span style="font-size:11px">{series.name}</span><br>',
            pointFormat: '<span style="color:{point.color}">{point.name}</span>: <b>{point.y:.2f}%</b> of total<br/>'
        },
        series: [
            {
                name: "Ward Wise Arrear",
                colorByPoint: true,
                data: [
                    {
                        name: "W-1",
                        y: 62.74,
                        drilldown: "Chrome"
                    },
                    {
                        name: "W-2",
                        y: 10.57,
                        drilldown: "Firefox"
                    },
                    {
                        name: "W-3",
                        y: 64.23,
                        drilldown: "Internet Explorer"
                    },
                    {
                        name: "W-4",
                        y: 15.58,
                        drilldown: "Safari"
                    },
                    {
                        name: "W-5",
                        y: 42.02,
                        drilldown: "Edge"
                    },
                    {
                        name: "W-6",
                        y: 13.92,
                        drilldown: "Opera"
                    },
                    {
                        name: "W-7",
                        y: 2.92,
                        drilldown: "Opera"
                    },
                    {
                        name: "W-8",
                        y: 3.92,
                        drilldown: "Opera"
                    },
                    {
                        name: "W-9",
                        y: 4.92,
                        drilldown: "Opera"
                    },
                    {
                        name: "W-10",
                        y: 50.92,
                        drilldown: "Opera"
                    },
                    {
                        name: "W-11",
                        y: 20.92,
                        drilldown: "Opera"
                    },
                    {
                        name: "W-12",
                        y: 15.92,
                        drilldown: "Opera"
                    },
                    {
                        name: "W-13",
                        y: 30.92,
                        drilldown: "Opera"
                    },
                    {
                        name: "W-14",
                        y: 9.92,
                        drilldown: "Opera"
                    },
                    {
                        name: "W-15",
                        y: 10.62,
                        drilldown: null
                    }
                ]
            }
        ],
        drilldown: {
            series: [
                {
                    name: "w-1",
                    id: "Chrome",
                    data: [
                        [
                            "v65.0",
                            0.1
                        ],
                        [
                            "v64.0",
                            1.3
                        ],
                        [
                            "v63.0",
                            53.02
                        ],
                        [
                            "v62.0",
                            1.4
                        ],
                        [
                            "v61.0",
                            0.88
                        ],
                        [
                            "v60.0",
                            0.56
                        ],
                        [
                            "v59.0",
                            0.45
                        ],
                        [
                            "v58.0",
                            0.49
                        ],
                        [
                            "v57.0",
                            0.32
                        ],
                        [
                            "v56.0",
                            0.29
                        ],
                        [
                            "v55.0",
                            0.79
                        ],
                        [
                            "v54.0",
                            0.18
                        ],
                        [
                            "v51.0",
                            0.13
                        ],
                        [
                            "v49.0",
                            2.16
                        ],
                        [
                            "v48.0",
                            0.13
                        ],
                        [
                            "v47.0",
                            0.11
                        ],
                        [
                            "v43.0",
                            0.17
                        ],
                        [
                            "v29.0",
                            0.26
                        ]
                    ]
                },
                {
                    name: "w-2",
                    id: "Firefox",
                    data: [
                        [
                            "v58.0",
                            1.02
                        ],
                        [
                            "v57.0",
                            7.36
                        ],
                        [
                            "v56.0",
                            0.35
                        ],
                        [
                            "v55.0",
                            0.11
                        ],
                        [
                            "v54.0",
                            0.1
                        ],
                        [
                            "v52.0",
                            0.95
                        ],
                        [
                            "v51.0",
                            0.15
                        ],
                        [
                            "v50.0",
                            0.1
                        ],
                        [
                            "v48.0",
                            0.31
                        ],
                        [
                            "v47.0",
                            0.12
                        ]
                    ]
                },
                {
                    name: "w-3",
                    id: "Internet Explorer",
                    data: [
                        [
                            "v11.0",
                            6.2
                        ],
                        [
                            "v10.0",
                            0.29
                        ],
                        [
                            "v9.0",
                            0.27
                        ],
                        [
                            "v8.0",
                            0.47
                        ]
                    ]
                },
                {
                    name: "w-1",
                    id: "Safari",
                    data: [
                        [
                            "v11.0",
                            3.39
                        ],
                        [
                            "v10.1",
                            0.96
                        ],
                        [
                            "v10.0",
                            0.36
                        ],
                        [
                            "v9.1",
                            0.54
                        ],
                        [
                            "v9.0",
                            0.13
                        ],
                        [
                            "v5.1",
                            0.2
                        ]
                    ]
                },
                {
                    name: "w-8",
                    id: "Edge",
                    data: [
                        [
                            "v16",
                            2.6
                        ],
                        [
                            "v15",
                            0.92
                        ],
                        [
                            "v14",
                            0.4
                        ],
                        [
                            "v13",
                            0.1
                        ]
                    ]
                },
                {
                    name: "w-6",
                    id: "Opera",
                    data: [
                        [
                            "v50.0",
                            0.96
                        ],
                        [
                            "v49.0",
                            0.82
                        ],
                        [
                            "v12.1",
                            0.14
                        ]
                    ]
                }
            ]
        }
    });





    Highcharts.chart('container_list4', {
        chart: {
            type: 'area'
        },
        title: {
            text: ' '
        },
        xAxis: {
            categories: ['Inbox', 'Unread', 'Solved', 'Rejected', 'Field Verification', 'Pending for approval'],
            tickmarkPlacement: 'on',
            title: {
                enabled: false
            }
        },
        yAxis: {
            title: {
                text: 'Complaints In Number'
            },
            labels: {
                formatter: function () {
                    return this.value / 1000;
                }
            }
        },
        tooltip: {
            split: true,
        },
        plotOptions: {
            area: {
                stacking: 'normal',
                lineColor: '#666666',
                lineWidth: 1,
                marker: {
                    lineWidth: 1,
                    lineColor: '#666666'
                }
            }
        },
        series: [
            {
                name: 'Total complaints',
                data: [2345, 635, 809, 947, 1402, 3634]
            }]
    });



    Highcharts.chart('container_list3', {
        chart: {
            type: 'variablepie'
        },
        title: {
            text: 'Browser<br>shares<br>2017',
            align: 'center',
            verticalAlign: 'middle',
            y: 60
        },
        tooltip: {
            pointFormat: '{series.name}: <b>{point.percentage:.1f}%</b>'
        },
        series: [{
                type: 'pie',
                name: 'No. of Property Transfer',
                innerSize: '70%',
                data: [
                    ['W-1', 58.9],
                    ['W-2', 13.29],
                    ['W-3', 10],
                    ['W-4', 12],
                    ['W-5', 16],
                    ['W-6', 18],
                    ['W-7', 10],
                    ['W-8', 40],
                    ['W-9', 13],
                    ['W-10', 17],
                    ['W-11', 11],
                    ['W-12', 30],
                    ['W-13', 13],
                    ['W-14', 3.78],
                    ['W-15', 3.42],
                    {
                        name: 'Other',
                        y: 7.61,
                        dataLabels: {
                            enabled: false
                        }
                    }
                ]
            }]
    });
</script>