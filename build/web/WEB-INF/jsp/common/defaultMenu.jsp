<%-- <nav>
        <!-- a href="${pageContext.request.contextPath}/"><img class="logo" src="${pageContext.request.contextPath}/static/img/Linux-icon3.png"></a>-->
        <ul id="menu">
                <li><a href="${pageContext.request.contextPath}/">Home</a></li>
       <li><a href="${pageContext.request.contextPath}/products">Products</a></li>
       <li><a href="${pageContext.request.contextPath}/contactus">Contact Us</a></li>
        </ul>
</nav> --%>
<%@ page import="com.silvassa.bean.LoginDetailsBean"%>
<%@ page import="java.util.HashMap"%>
<%@ page import="java.util.List"%>
<%@ page import="java.util.Map"%>
<%@ page import="java.util.ArrayList"%>
<%
    String user = null;
    String role = null;
    String loggedUser = null;
    LoginDetailsBean loginDetails = new LoginDetailsBean();
    Map<String, String> userPermissionMap = null;
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
            userPermissionMap = loginDetails.getUserPermissionMap();
            if (admin_ls.indexOf(loginDetails.getUserId()) > -1) {
                isAdmin = true;
            }
        }
    } catch (Exception e) {
        //e.printStackTrace();
    }
%>

<div class="menUTop">
    <div class="container-fluid">
        <div class="row">
            <div class="col-md-10">
                <div class="sideleft secondary-bg">
                    <div id="mainMenu" class="tab-pane nav-side-menu active">
                        <ul class="nav sideleft-menu">


                            <li  class="dropdown_list" id="property_main_menu">
                                <a   href="#" data-toggle="collapse" data-target="#pReceipt" aria-expanded="true">  <span class="title">Property Details</span><i class="material-icons">keyboard_arrow_down</i> </a>
                                <ul class="sub-menu collapse" id="pReceipt">
                                    <%
                                        if (userPermissionMap.containsKey("print_receipt_sub_menu_obj")) {
                                    %>
                                    <li id="print_receipt_sub_menu_obj"><a href="printObjReceipt">Objection Receipt</a></li>
                                        <%
                                            }
                                        %>
                                        <%
                                            if (userPermissionMap.containsKey("print_receipt_sub_menu_payment")) {
                                        %>
                                    <li id="print_receipt_sub_menu_payment"><a href="printPaymentReceipt">Payment Receipt</a></li>
                                        <%
                                            }
                                        %>
                                    <li id="property_sub_menu"><a href="property"> <span class="material-icons">house</span>  Search  / Edit / Add property</a></li>
                                        <% if (isAdmin) { %>

                                    <li id="Ge_Notice"><a href="notice"> <span class="material-icons">assignment</span>   View / Generate Notice</a></li>

                                    <li id="propertySplitMerge">
                                        <a href="propertySplit"><i class="material-icons">call_split</i>Property split/Merge</a>
                                    </li>

                                    <% }%>
                                    <!--li id="propertyTransferWithId">
                                        <a href="propertyTransferWithPropertyId"> <span class="material-icons">account_balance</span>Property Transfer with ID</a>
                                    </li-->
                                    <li id="UpdateC">
                                        <a href="userRegistrationAdmin"> <span class="material-icons">person</span> Update  Contact Details Online</a>
                                    </li>
                                    <li id="UpdateOffline">
                                        <a href="userRegistration"> <span class="material-icons">person</span> Update Contact Details Offline</a>
                                    </li>
                                    <!--                                    <li id="property_sub_menu"><a href="property">   View Property</a></li>
                                    <%
                                     //   if (isAdmin) {
                                    %>
                                <li id="Ge_Notice"><a href="notice">   Generate Notice</a></li>-->
                                    <%
                                   //     }  
                                    %>
                                    <!--                    <li id="notice_Window"><a href="noticeWindow"> <i class="material-icons">business</i>View Notice</a></li>-->


                                </ul></li>
                            <li class="dropdown_list " id="tax_collection_main_menuu">
                                <a href="#" data-toggle="collapse" data-target="#TReceipt" aria-expanded="true">  <span class="title">Tax Collection</span><i class="material-icons">keyboard_arrow_down</i> </a>
                                <ul class="sub-menu collapse" id="TReceipt">
                                    <%                                        if (userPermissionMap.containsKey("print_receipt_sub_menu_obj")) {
                                    %>
                                    <li id="print_receipt_sub_menu_obj"><a href="printObjReceipt">Objection Receipt</a></li>
                                        <%
                                            }
                                        %>
                                        <%
                                            if (userPermissionMap.containsKey("print_receipt_sub_menu_payment")) {
                                        %>
                                    <li id="print_receipt_sub_menu_payment"><a href="printPaymentReceipt">Payment Receipt</a></li>
                                        <%
                                            }
                                        %>
                                    <li id="payment"><a href="taxCollection"><span class="material-icons">move_to_inbox</span> Payment</a></li>
                                        <%
                                            if (isAdmin) {
                                        %>
                                    <li id="payment_approval"><a href="paymentApprovalWindow"><span class="material-icons">check</span> Payment Approval</a></li>
                                        <%
                                            }
                                        %>
                                </ul></li>

                            <li class="dropdown_list d-none" id="objection_main_menu">
                                <a href="#" data-toggle="collapse" data-target="#OReceipt" aria-expanded="true">  <span class="title">Objection</span><i class="material-icons">keyboard_arrow_down</i> </a>
                                <ul class="sub-menu collapse" id="OReceipt">
                                    <%
                                        if (userPermissionMap.containsKey("print_receipt_sub_menu_obj")) {
                                    %>
                                    <li id="print_receipt_sub_menu_obj"><a href="printObjReceipt">Objection Receipt</a></li>
                                        <%
                                            }
                                        %>
                                        <%
                                            if (userPermissionMap.containsKey("print_receipt_sub_menu_payment")) {
                                        %>
                                    <li id="print_receipt_sub_menu_payment"><a href="printPaymentReceipt">Payment Receipt</a></li>
                                        <%
                                            }
                                        %>

                                    <li>
                                        <a href="dashboard?actionFliter=1"><i class="material-icons">edit</i>Correction Portal</a>

                                    </li>
                                    <li id = "contactDetails">
                                        <a href="javascript:void(0)" id="aContactDetails" onclick="$('#online_contactF').toggleClass('childlevel-3');
                                                $(this).toggleClass('ad154');"> <i class="material-icons">drafts</i> Contact Details Update<span class="arrow_plus"></span></a>
                                        <ul class="sub-menu collapse "  style="display: none; width:100%;" class="" id="online_contactF">
                                            <li id="online"  style="width:50%; border:none; list-style: none;background: #0a315d;"><a href="userRegistrationAdmin" style="color:#fff"><span class="material-icons pl-1 " style="font-size: 16px; vertical-align: text-top; margin-right: 5ppx;">edit</span>Online </a></li>
                                            <li id="offline" style="width: 50%;   border:none; list-style: none;background: #0a315d;"><a href="userRegistration"  style="color:#fff"><span class="material-icons" style="font-size: 16px; vertical-align: text-top; margin-right: 5ppx;">edit</span>Offline </a></li>
                                        </ul>
                                    </li>
                                    <%
                                        if (isAdmin) {
                                    %>
                                    <li id="propertySplitMerge">
                                        <a href="propertySplit"><i class="material-icons">call_split</i>Property split/Merge</a>
                                    </li>
                                    <li id="AddProperty">
                                        <a href="newProperty"> <i class="material-icons">business</i> Add Property</a>
                                    </li>
                                    <li id="editProperty">
                                        <a href="editProperty"> <i class="material-icons">business</i> Edit Property</a>
                                    </li>
                                    <li id="propertyTransferWithId">
                                        <a href="propertyTransferWithPropertyId"> <i class="material-icons">transform</i>Property Transfer with ID</a>
                                    </li>
                                    <%
                                        }
                                    %>
                                </ul></li>
                                <%
                                    if (isAdmin) {
                                %>
                            <li class="dropdown_list " id="report_main_menu">
                                <a href="#" data-toggle="collapse" data-target="#RReceipt" aria-expanded="true"> <span class="title">Reports</span><i class="material-icons">keyboard_arrow_down</i> </a>
                                <ul class="sub-menu collapse" id="RReceipt">
                                    <%
                                        if (userPermissionMap.containsKey("print_receipt_sub_menu_obj")) {
                                    %>
                                    <li id="print_receipt_sub_menu_obj"><a href="printObjReceipt">Objection Receipt</a></li>
                                        <%
                                            }
                                        %>
                                        <%
                                            if (userPermissionMap.containsKey("print_receipt_sub_menu_payment")) {
                                        %>
                                    <li id="print_receipt_sub_menu_payment"><a href="printPaymentReceipt">Payment Receipt</a></li>
                                        <%
                                            }
                                        %>

                                    <li id="correctionFormReport">
                                        <a href="correctionFormReport"><i class="material-icons">create</i> Correction report</a>
                                    </li>
                                    <li id="collectionReportID">
                                        <a href="collectionReport"><i class="material-icons">business_center</i>Collection report</a>
                                    </li>
                                    <li id="arrearReport">
                                        <a href="arrearReport"><i class="material-icons">menu_book</i> Arrear Report</a>
                                    </li>
                                    <li id="assessmenttaxView">
                                        <a href="taxView"><i class="material-icons">assignment_turned_in</i>Assessment Register</a>
                                    </li>

                                    <li id="taxgeneration">
                                        <a href="taxgeneration"> <i class="material-icons">money</i>TAX Generation</a>
                                    </li>

                                    <li id="demandAndCollection" class="d-none">
                                        <a href="demandAndCollection"><i class="material-icons">playlist_add_check</i>Demand & collection register</a>
                                    </li>
                                </ul></li>

                            <%
                                }
                            %>







                            <!-------------Old menu---------->




                            <%
                                if (userPermissionMap.containsKey("report_main_menu")) {
                            %>
                            <li  class="dropdown_list d-none" id="report_main_menu">

                                <a href="#" data-toggle="collapse" data-target="#Reports" aria-expanded="true">
                                    <i class="material-icons">business</i> <span class="title">Reports</span> <i class="material-icons">keyboard_arrow_down</i> >
                                </a>


                                <ul class="sub-menu collapse" id="Reports">
                                    <% if (userPermissionMap.containsKey("report_prop_detail")) {%>
                                    <li id="report_prop_detail"><a href="reportPropertyDetails">Property Details</a></li>
                                        <%} %>
                                        <% if (userPermissionMap.containsKey("report_tax_detail")) {%>
                                    <li id="report_tax_detail"><a href="reportTAXDetails">TAX Details</a></li>
                                        <%} %>
                                        <% if (userPermissionMap.containsKey("report_payment_due")) {%>
                                    <li id="report_payment_due"><a href="reportPaymentDue">Payment Due</a></li>
                                        <%} %>
                                        <% if (userPermissionMap.containsKey("report_arrear_due")) {%>
                                    <li id="report_arrear_due"><a href="reportArrearDue">Arrear Due</a></li>
                                        <%} %>
                                        <% if (userPermissionMap.containsKey("report_tax_amount")) {%>
                                    <li id="report_tax_amount"><a href="reportTAXAmount">TAX Amount</a></li>
                                        <%} %>
                                        <% if (userPermissionMap.containsKey("report_objection")) {%>
                                    <li id="report_objection"><a href="reportObjection">Objection Details</a></li>
                                        <%} %>
                                        <% if (userPermissionMap.containsKey("report_notice")) {%>
                                    <li id="report_notice"><a href="reportNotice">Notice Generation</a></li>
                                        <%} %>
                                        <% if (userPermissionMap.containsKey("report_tax_gen")) {%>
                                    <li id="report_tax_gen"><a href="reportTAXGEN">Tax Generation</a></li>
                                        <%} %>
                                        <% if (userPermissionMap.containsKey("report_collection")) {%>
                                    <li id="report_collection"><a href="reportCollection">Collections</a></li>
                                        <%} %>
                                </ul>
                            </li>
                            <%
                                }
                            %>
                            <%
                                if (userPermissionMap.containsKey("other_main_menu")) {
                            %>
                            <li id="other_main_menu"><a href="others">Others</a></li>
                                <%
                                    }
                                %>
                                <% if (userPermissionMap.containsKey("user_mgmt_main_menu")) {%>
                            <li class="dropdown_list  " id="user_mgmt_main_menu"><a href="#"   data-toggle="collapse" data-target="#uManagement" aria-expanded="true">
                                    <span class="title">User Management</span><i class="material-icons">keyboard_arrow_down</i> </a>
                                <ul class="sub-menu collapse" id="uManagement">
                                    <% if (userPermissionMap.containsKey("user_mgmt_main_menu")) {%>
                                    <li id="user_mgmt_sub_menu_permission"><a href="userManagement?id=permission"><span class="material-icons">verified_user</span>User Permission</a></li>
                                        <% } %>
                                        <% if (userPermissionMap.containsKey("user_mgmt_main_menu")) {%>
                                    <li id="user_mgmt_sub_menu_create"><a href="userManagement?id=create"><span class="material-icons">person_add</span>Create User </a></li>
                                        <%} %>
                                        <% if (userPermissionMap.containsKey("user_mgmt_main_menu")) {%>
                                    <!--<li id="user_mgmt_sub_menu_create"><a href="userRegistration">User Registration </a></li>-->
                                    <%} %>

                                </ul></li>
                                <%}%>

                            <%
                                if (userPermissionMap.containsKey("print_receipts_menu")) {
                            %>
                            <li class="dropdown_list d-none" id="print_receipts_menu">
                                <a href="#" data-toggle="collapse" data-target="#pReceipt" aria-expanded="true"><i class="material-icons">local_printshop</i> <span class="title">Print Receipt</span><i class="material-icons">keyboard_arrow_down</i> </a>
                                <ul class="sub-menu collapse" id="pReceipt">
                                    <%
                                        if (userPermissionMap.containsKey("print_receipt_sub_menu_obj")) {
                                    %>
                                    <li id="print_receipt_sub_menu_obj"><a href="printObjReceipt">Objection Receipt</a></li>
                                        <%
                                            }
                                        %>
                                        <%
                                            if (userPermissionMap.containsKey("print_receipt_sub_menu_payment")) {
                                        %>
                                    <li id="print_receipt_sub_menu_payment"><a href="printPaymentReceipt">Payment Receipt</a></li>
                                        <%
                                            }
                                        %>
                                </ul></li>
                                <%
                                    }
                                %>

                            <%
                                if (isAdmin) {
                            %>
                            <li class="dropdown_list  " id="locateOnMapMainMenu">
<!--                                <a href="#" data-toggle="collapse" data-target="#llocateOnMap" aria-expanded="true"> <span class="title">Locate On Map</span><i class="material-icons">keyboard_arrow_down</i> </a>-->
                                 <a href="locateOnMap"><i class="material-icons"></i><span class="title">Locate On Map</span></a>                                
<!--            <ul class="sub-menu collapse" id="llocateOnMap">
                                    <li id="mapViewSubMenu">
                                        <a href="locateOnMap"><i class="material-icons">playlist_add_check</i>Locate On Map</a>
                                    </li>
                                </ul>-->
                            </li>
                                <%
                                    }
                                %>

                            <%--
                            <%
                                if (userPermissionMap.containsKey("Job Allocation")) {
                            %>
                             <li id="jobAllocation"><a href="jobAllocation">Job Allocation</a></li>
                            <%
                                }
                            %>--%>

                            <!--  <li class="dropdown">
                <a href="#" class="dropdown-toggle" data-toggle="dropdown">Products<i class="material-icons">keyboard_arrow_down</i> ></a>
                <ul class="dropdown-menu">
                <li><a href="#">Latest Products</a></li>
                <li><a href="#">Popular Products</a></li>
                </ul>
                </li>  -->   

                        </ul>
                    </div>
                    <!-- /.nav-collapse -->

                    <!-- /.container -->
                </div>
            </div>
            <div class="col-md-2">
                <div class="header-right">
                    <%if (request.getSession() != null) {%>
                    <ul class="navbar-nav classuserAvat">
                        <li class="nav-item dropdown"> <a class="nav-link d-flex    waves-effect waves-dark" style="    align-items: center;" href="javascript:void(0);" onclick="$('.dropdown-menu-right').toggle();"><i class="material-icons">person</i> <span class="username_box" style="display: inline-block;vertical-align: super;" class="ml-2 font-medium">Hi, <%=loginDetails.getUserName()%></span><span class="material-icons ml-2">keyboard_arrow_down</span> </a>
                            <div class="dropdown-menu dropdown-menu-right user-dd animated flipInY">                                     
                                <a class="dropdown-item" href="logout"><i class="fa fa-power-off mr-1 ml-1"></i> Logout</a> 
                            </div>
                        </li>
                    </ul>
                    <% } else { %>
                    <div class="log">Silvassa Municipal Council</div>
                    <% }%>
                </div>
            </div>
        </div>
    </div>
</div>

<div class="breadcrumb_list d-flex">

    <ul  class="pull-left" style="padding: 5px 0px 0;flex: 0 0 15%; max-width: 15%; "><li  ><a href="welcome">Home</a></li><li id ="breadlist" ></li></ul> 

    <div class="älertSilvassa text-center font-weight-bold" style="flex:0 0 72%; max-width: 72%;height:32px;    color: red;padding: 5px 0px; "  id="validateid"> </div>
    <div class="pull-right d-none">
        <span class="backButton" onclick="window.history.back()"><i class="material-icons " style="vertical-align: middle">keyboard_arrow_left</i>Back</span>
    </div>
</div>  
<script>
    var zoneWardMaster = {};
    $.post("getZoneWardMaster", {}, function (result) {
        zoneWardMaster = result;
        zoneWardMaster['-1'] = '-1';
    }, 'json').always(function () {
    });
</script>
