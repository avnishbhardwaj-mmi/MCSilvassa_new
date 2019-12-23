<%-- 
    Document   : CollectionReportData
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

<section class="row_0" id="searchResults">
    <div class="col-lg-12 p-0">
        <div class="innerdashboard">
            <div id="tys_tab_div" class="conainer">
                <div class="filterward  d-flex"><h3 class="m-0">Collection Report Data</h3><div class="ml-auto">  <a href="collectionReport" class="btn btn-primary "> Back</a>

                    </div></div>
                <div class="filterother">
                    <table id='tbData' class="table table-condensed table-bordered table-striped table-hover ">
                        <thead>
                        <th>Receipt No.</th>
                        <th>Payment Mode</th>
                        <th>Cheque/DD etc no. </th>
                        <th>Bank Name</th>
                        <th>Bank Branch</th>
                        <th>Payment Period</th>
                        <th>Payment Status</th>
                        <th>Paid Amount</th>
                        <th>Financial Year</th>
                        <th>Property Id</th>
                        <th>Ward</th>
                        <th>Owner Name</th>
                        <th>Address</th>
                        <th>Collection Date</th>
                        <th>Payee Name</th>
                        <th>Mobile No.</th>
                        
                        </thead>
                        <tbody id="tabBody" style="display: none">
                            <c:forEach items="${ListCollectionData}" var="arrear">

                                <tr>
                                    <td>${arrear.payrefid}</td>
                                    <td>${arrear.paymentMode}</td>
                                    <td>${arrear.cheque_dd_num}</td>
                                    <td>${arrear.bankName}</td>
                                    <td>${arrear.branchName}</td>
                                    <td>${arrear.paymentPeriod}</td>
                                    <c:choose>
                                        <c:when test="${arrear.paymentStatus.equals('I')}">
                                            <td>Initiated to ICICI</td>
                                        </c:when>
                                        <c:when test="${arrear.paymentStatus.equals('V')}">
                                            <td>Under Verification</td>
                                        </c:when>
                                        <c:when test="${arrear.paymentStatus.equals('R')}">
                                            <td>Payment Rejected</td>
                                        </c:when>
                                        <c:when test="${arrear.paymentStatus.equals('C')}">
                                            <td>Payment Complete</td>
                                        </c:when>
                                        <c:otherwise>
                                           <td></td>
                                        </c:otherwise>
                                    </c:choose>
                                           <td>${arrear.paidAmout}</td>
                                           <td>2019-2020</td>
                                    <td>${arrear.propertyUniqueId}</td>
                                    <td>${arrear.ward}</td>
                                    <td>${arrear.propertyOwner}</td>
                                    <td>${arrear.address}</td>
                                    
                                    
                                    <td>${arrear.collectionDate}</td>
                                    <td>${arrear.payerName}</td>
                                    <td>${arrear.contactNo}</td>
                                    
                                    
                                    
                                    
                                           
                                </tr>
                            </c:forEach>
                        </tbody>
                    </table>
                    
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
                'excel'
            ],
            pageLength: 10,
            pagingType: 'full_numbers',
            lengthMenu: [[10, 25, 50, -1], [10, 25, 50, 'All']],
            // scrollY: '400px'
        });

        $('#tabBody').show();
        $('.dataTables_wrapper table').wrap('<div class="scroll_table"></div>');
        //LOADER.hide();
    });


</script>
</html>
