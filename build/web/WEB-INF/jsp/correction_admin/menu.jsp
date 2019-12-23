
<%@ page import="com.silvassa.bean.LoginDetailsBean"%>
<%@ page import="java.util.HashMap"%>
<%@ page import="java.util.List"%>
<%@ page import="java.util.Map"%>
<%

    LoginDetailsBean loginDetails = new LoginDetailsBean();
    Map<String, String> userPermissionMap = null;
    //session = request.getSession();
    try {
        if (request.getSession() != null) {
            loginDetails = (LoginDetailsBean) request.getSession().getAttribute("userDetailsBean");
            userPermissionMap = loginDetails.getUserPermissionMap();
            if (!userPermissionMap.containsKey("correction_module")) {
%>
<script>location.href = "welcome"</script>
<%
            }
        }
    } catch (Exception e) {
        //e.printStackTrace();
    }
%> <div class="menUTop">
<div class="container-fluid">
    <div class="row">
        <div class="col-md-10">
           
                <div id="mainMenu" class="tab-pane active">
                    <ul class="nav sideleft-menu">
                        <%
                            if (userPermissionMap.containsKey("correction_dashboard")) {
                        %>
                        <li id="pg_dashboards" class="nav-item"> <a href="dashboard?actionFliter=1" class="nav-link">
                                <div class="gui-icon"> </div>
                                <span class="title">Dashboard</span> </a> 
                        </li>
                        <%
                            }

                            if (userPermissionMap.containsKey("correction_all")) {
                        %>
                        <li id="pg_list" class="nav-item"> <a href="correction_all_list?actionFliter=1" class="nav-link " >
                                <div class="gui-icon"><i class="material-icons">list</i></div>
                                <span class="title">All</span> </a> 
                        </li>
                        <%
                            }

                            if (userPermissionMap.containsKey("correction_inbox")) {
                        %>
                        <li id="pg_inbox" class="nav-item"> <a href="correction_inbox_list" class="nav-link">
                                <div class="gui-icon"><i class="material-icons">inbox</i></div>
                                <span class="title">Inbox</span> </a> 
                        </li>
                        <%
                            }

                            if (userPermissionMap.containsKey("correction_overdue")) {
                        %>
                        <li id="pg_overdue" class="nav-item"> <a href="correction_overdue_list" class="nav-link">
                                <div class="gui-icon"><i class="material-icons">watch_later</i></div>
                                <span class="title">Unread</span> </a>
                        </li>
                        <%
                            }

                            if (userPermissionMap.containsKey("correction_fieldverify")) {
                        %>
                        <li id="pg_field" class="nav-item"> <a href="correction_field_list" class="nav-link">
                                <div class="gui-icon"><i class="material-icons">message</i></div>
                                <span class="title">Field Verification</span> </a> 
                        </li>
                        <%
                            }

                            if (userPermissionMap.containsKey("correction_applychange")) {
                        %>
                        <li id="pg_apply" class="nav-item"> <a href="correction_apply_list" class="nav-link">
                                <div class="gui-icon"><i class="material-icons">message</i></div>
                                <span class="title">Apply Changes</span> </a> 
                        </li>
                        <%
                            }

                            if (userPermissionMap.containsKey("correction_solved")) {
                        %>
                        <li id="pg_solve" class="nav-item"> <a href="correction_solve_list" class="nav-link">
                                <div class="gui-icon"><i class="material-icons">playlist_add_check</i></div>
                                <span class="title">Solved</span> </a>
                        </li>
                        <%
                            }

                            if (userPermissionMap.containsKey("correction_reject")) {
                        %>
                        <li id="pg_reject" class="nav-item"> <a href="correction_reject_list" class="nav-link">
                                <div class="gui-icon"><i class="material-icons">delete</i></div>
                                <span class="title">Rejected</span> </a> 
                        </li>
                        <%
                            }

                            if (userPermissionMap.containsKey("correction_offline")) {
                        %>
                        <li id="pg_offline" class="nav-item"> <a href="correction_alloffline_list?actionFliter=1" class="nav-link">
                                <div class="gui-icon"><i class="material-icons">list</i></div>
                                <span class="title">Offline Forms</span> </a> 
                        </li>
                        <%
                            }
                        %>
                        <li  class="nav-item d-none"> <a href="welcome" class="nav-link">
                                <div class="gui-icon"><i class="material-icons">exit_to_app</i></div>
                                <span class="title ">Back to Main</span> </a> 
                        </li>


                    </ul>
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
                 </div>           </div>
                        
                        
    </div>
</div>
<script>

    $(document).ready(function () {
        toggleMenu();
        var w = $(window).innerWidth();

        if (w < 992) {
            $(".navicon").click();
        }
    });
    $(window).resize(function () {
        toggleMenu();
    });
    function toggleMenu() {
        $(".navicon").click(function () {
            $(this).toggleClass('active');
            $(".header-left").toggleClass('web-header-left');
            $(".sideleft").toggleClass('webSideLeft');
            $(".logoText").toggleClass('abc');
            $(".mainNav").toggleClass('abc');
            $(".footer").toggleClass('abc');
            $(".header-nav-profile .dropdown .profile-info").toggleClass('abc');
            $(".sideleft-menu > .nav-item > .nav-link .title").toggleClass('abc');
            $(".mainpanel").toggleClass('webmainpanel');
            if ($('.sideleft').hasClass('webSideLeft')) {
                $('.nav-item').removeClass('show');
            }
        });
    }
    ;


</script>