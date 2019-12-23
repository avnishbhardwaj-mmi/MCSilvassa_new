
<%@ page import="com.silvassa.bean.LoginDetailsBean" %>



<%
    if (response != null) {
//            response.setHeader("Cache-Control", "no-cache");
//            response.setHeader("Pragma", "no-cache");
//            response.setDateHeader("Expires", 0);
    }
%>

<%
    String user = null;
    String role = null;
    String loggedUser = null;
    LoginDetailsBean loginDetails = new LoginDetailsBean();
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
<!--section class="w-100">
    <div class="custom-top">
        <div class="logo clearfix">

            <a href="" ><img src="res/img/logo.png"  title="Home Page"/></a>
<%if (request.getSession() != null) {%>
<div class="log"><span>Welcome <%=loginDetails.getUserName()%></span>  |  <a href="logout"> Logout</a>  </div>
<% } else { %>
<div class="log">Silvassa Municipal Council</div>
<% }%>
</div>
</div>
</section -->

<!--
<div class="header primary-Silvassa">
    <div class="header-left"> <a class="logo" href="#" style="background: url('res/img/logo.png') 0px 5px no-repeat; width: 227px;height: 60px;"> </a> <a id="naviconLeft" href="#" class="navicon"><img src="res/img/hamburgerIcon.png" alt=""></a> </div>
     am-header-left 
    <div class="container-fluid">
    <div class="row">
        <div class="col-md-2" style="padding-left: 5px;"><div class="toggleMenuSection navicon"><span class="material-icons  ">menu</span></div></div>
        <div class="col-md-7 text-center"><img src="res/img/logoSilavssa_1.png" alt="Silvassa Logo"> </div>
        <div class="col-md-3">
            
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
     am-header-right  
</div>-->
 
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
                                 <a href="welcome" class="active">Home</a>
                                 <a href="dashboard?actionFliter=1">Dashboard</a>
                                 
                             </div>
                        </div>
                        
                        
                        
                    </div>
                     
                </div>
            </div>
        </header>
        
        
        
        





