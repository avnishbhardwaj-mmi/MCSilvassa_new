<%@ page import="com.silvassa.bean.LoginDetailsBean" %>



<%
    LoginDetailsBean loginDetails = new LoginDetailsBean();
    //loginDetails.setUserName("");
    if (response != null) {
//        response.setHeader("Cache-Control", "no-cache");
//        response.setHeader("Pragma", "no-cache");
//        response.setDateHeader("Expires", 0);
    }

    String user = null;
    String role = null;
    String loggedUser = null;
    
    //session = request.getSession();
    try {
        if (request != null && request.getSession() != null) {
            loginDetails = (LoginDetailsBean) request.getSession().getAttribute("userDetailsBean");
            user = loginDetails.getUserName();
            //role = (String) session.getAttribute("role");
            loggedUser = loginDetails.getUserName();

        }
    } catch (Exception e) {
        //e.printStackTrace();
    } 
%>

<div class="header primary-bg d-none">
    <div class="header-left"> <a class="logo" style="background: url('res/correction/images/image_1.png') 0px 5px no-repeat; width: 227px;height: 60px;"> </a> <a id="naviconLeft" href="#" class="navicon"><img src="res/correction/images/hamburgerIcon.png" alt=""></a> </div>
    <!-- am-header-left -->
    <div class="header-right">
        <div id="msg" class="msg alert alert-success d-none"> <a href="#" class="close" data-dismiss="alert" aria-label="close" title="close">×</a> <strong>Success!</strong> successful saved your API </div>
        <ul class="navbar-nav">
            <li class="nav-item dropdown"> <a class="nav-link   waves-effect waves-dark" href="javascript:void(0);" onClick="$('.dropdown-menu-right').toggle();"  >
                    <!--<img src="images/1.jpg" alt="user" class="rounded-circle" width="36">--> 
                    <span class="ml-2 font-medium"><%= loginDetails.getUserName()%></span>
                    <span class="fas fa-angle-down ml-2"></span> </a>
                <div class="dropdown-menu dropdown-menu-right user-dd animated flipInY">

                    <!--                    <div class="d-flex no-block align-items-center p-3 mb-2 border-bottom">
                                            <div class=""><img src="images/1.jpg" alt="user" class="rounded" width="80"></div>
                                            <div class="ml-2">
                                                <h4 class="mb-0">Steave Jobs</h4>
                                                <p class=" mb-0 text-muted">varun@gmail.com</p>
                                                <a href="javascript:void(0)" class="btn btn-sm btn-danger text-white mt-2 btn-rounded">View Profile</a> </div>
                                        </div>-->
                    <!--                    <a class="dropdown-item" href="javascript:void(0)"><i class="ti-user mr-1 ml-1"></i> My Profile</a> <a class="dropdown-item" href="javascript:void(0)"><i class="ti-wallet mr-1 ml-1"></i> My Balance</a> <a class="dropdown-item" href="javascript:void(0)"><i class="ti-email mr-1 ml-1"></i> Inbox</a>
                                        <div class="dropdown-divider"></div>-->
                    <!--                    <a class="dropdown-item" href="welcome"><i class="ti-settings mr-1 ml-1"></i>
                                            <i class="material-icons">exit_to_app</i>Back to Admin portal</a>
                                        <div class="dropdown-divider"></div>-->
                    <a class="dropdown-item" href="logout" ><i class="fa fa-power-off mr-1 ml-1"></i> Logout</a>
                </div>
            </li>
        </ul>
    </div>
    <!-- am-header-right --> 
</div>
                    
                    <header id="HeaderSection">
            <div class="container-fluid">
                <div class="row">
                    <div class="col-md-5">
                        
                        <img src="res/img/logo_1.png" alt="Silvassa">
                    </div>
                    <div class="col-md-7 text-right">
                        <div class="pull-right">  
                            <img src="res/img/saw1.png" alt=""  style="    height: 67px;">
                        </div>
                        
                         <div class="pull-right">  
                             <div class="menU_dash">
                                 <a href="/MCSilvassa/welcome" >Home</a>
                                 <a href="dashboard?actionFliter=1" class="active">Dashboard</a>
                                 
                             </div>
                        </div>
                        
                        
                        
                    </div>
                     
                </div>
            </div>
        </header>

