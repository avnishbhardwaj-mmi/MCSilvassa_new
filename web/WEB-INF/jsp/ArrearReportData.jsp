<%-- 
    Document   : ArrearReportData
    Created on : 30 May, 2019, 6:57:38 PM
    Author     : CEINFO
--%>
<link rel="stylesheet" type="text/css" href="res/css/select2.css" />
<link rel="stylesheet" type="text/css" href="res/css/select2-bootstrap.css" />
<!--    <link rel="stylesheet" type="text/css" href="res/css/dataTables.bootstrap.min.css" />-->
<!--    <link rel="stylesheet" type="text/css" href="https://cdn.datatables.net/1.10.19/css/jquery.dataTables.min.css"/>
    <link rel="stylesheet" type="text/css" href="https://cdn.datatables.net/buttons/1.5.6/css/buttons.dataTables.min.css"/>
-->
<link href="res/css/datatable2.css" rel="stylesheet" type="text/css" />
<link href="res/css/buttons.dataTables.min.css" rel="stylesheet" type="text/css" />
<link rel="stylesheet" type="text/css" href="res/css/select2.css" />
<link rel="stylesheet" type="text/css" href="res/css/select2-bootstrap.css" />

<!--    <script src="res/js/api/jquery.dataTables.min.js"></script>
    <script src="res/js/api/dataTables.bootstrap.min.js"></script>-->
<script src="res/js/api/jquery-ui.js"></script>
<script src="https://code.jquery.com/jquery-3.3.1.js"></script>

<script type="text/javascript" src="res/js/api/jquery.dataTables.min.js"></script>
<script type="text/javascript" src="res/js/api/dataTables.buttons.min.js"></script>
<script type="text/javascript" src="res/js/api/buttons.flash.min.js"></script>
<script type="text/javascript" src="res/js/api/jszip.min.js"></script>
<script type="text/javascript" src="res/js/api/pdfmake.min.js"></script>
<script type="text/javascript" src="res/js/api/vfs_fonts.js"></script>
<script type="text/javascript" src="res/js/api/buttons.html5.min.js"></script>
<script type="text/javascript" src="res/js/api/jquery-dateFormat.js"></script>


<script src="res/js/api/select2.min.js"></script>
<script src="res/js/util.js"></script>

<%@ taglib prefix = "c" uri = "http://java.sun.com/jsp/jstl/core" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<section class="row" id="searchResults">
    <div class="col-lg-12 pl-4">
        <div class="innerdashboard">
            <div id="arreartab_div" class="conainer">
                <div class="filterward  d-flex"><h3>Arrear Report Data</h3><div class="ml-auto"><a href="arrearReport" class="btn btn-primary "> Back</a></div></div>
                <div class="filterother">

                    <table id='tbData' class="table table-bordered table-sm dataTable no-footer ">
                        <thead>
                        <th>Property Id</th>
                        <th>Owner Name</th>
                        <th>Mobile No.</th>
                        <th>Email</th>
                        <th style="width: 566px;">Address</th>
                        <th style="width: 150px;">Arrear Amount(Before 2018-2019)</th>
                        <th>One Year Tax</th>
                        <th>Payable Amount</th>
                        <th>Ward</th>
                        <th>Bill Date</th>
                        </thead>
                        <tbody id="tabBody" style="display: none">
                            <c:forEach items="${ListArrearData}" var="arrear">


                                <tr>
                                    <td>${arrear.propertyUniqueId}</td>
                                    <td>${arrear.propertyOwner}</td>
                                    <td>${arrear.mobile}</td>
                                    <td>${arrear.email}</td>
                                    <td >${arrear.address}</td>
                                    <td>${arrear.arrearAmount}</td>
                                    <td>${arrear.oneYrTax}</td>
                                    <td>${arrear.payableAmount}</td>
                                    <td>${arrear.ward}</td>
                                    <td>${arrear.billDate}</td>


                                </tr>
                            </c:forEach>
                        </tbody>
                    </table>  

                    </div>
            </div>
        </div>
    </div>
</section>
<script type="text/javascript">

    $(document).ready(function () {
        //debugger;
        //LOADER.show();
        $('#tbData').DataTable({
            dom: 'Bfrtip',
            buttons: [
                'excel', 'pdf'
            ],
            pageLength: 15,
            pagingType: 'full_numbers',
            lengthMenu: [[10, 25, 50, -1], [10, 25, 50, 'All']]
        });

        $('#tabBody').show();
        $('.dataTables_wrapper table').wrap('<div class="scroll_table"></div>');
        LOADER.hide();
    });


</script>
