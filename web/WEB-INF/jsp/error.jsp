
<%@page contentType="text/html" pageEncoding="UTF-8"%>
 

<html>

<head>
	<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
	<title><tiles:getAsString name="title" /></title>
        <link rel="stylesheet" href="https://fonts.googleapis.com/icon?family=Material+Icons" />
 	<link href="res/css/buttons.dataTables.min.css" rel="stylesheet" type="text/css" />
	<link href="res/css/font-awesome.css" rel="stylesheet" type="text/css" />
	<link href="res/css/bootstrap.css" rel="stylesheet" type="text/css" />
 	<link href="res/css/style.css?v1" rel="stylesheet" type="text/css" />
	<link rel="stylesheet" href="res/css/jquery-ui.css">
        
            <script src="res/js/api/jquery-1.10.2.js"></script>
    <script src="res/js/api/bootstrap.js" type="text/javascript"></script>
    <script src="res/js/api/jquery.sumoselect.js" type="text/javascript"></script>
</head> 
<header class="custom-header" id="header">
<div class="header primary-bg">
    <div class="header-left"> <a class="logo" href="#" style="background: url('res/img/logo.png') 0px 5px no-repeat; width: 227px;height: 60px;"> </a>  </div>
    <!-- am-header-left -->
    <div class="header-right">
        
         
        
    </div>
    <!-- am-header-right --> 
</div>





	</header>


<section class=" row  " id="searchResults" style="max-width: 100%; width: 800px; margin:50px auto; padding: 30px" >
    <div class="col-lg-12 pl-4">
        <div class="innerdashboard">
            <div id="property_tab_div" class="conainerB">
                <div class="filterward"><div id="searchHeader" >OOPs.. something went wrong. Reason could be</div></div>
                <div class="filterother">
                


                    <div>
                        <table class="text-center " width="100%">
                            <tbody><tr>
                                <td><p><span>Session expire.</span></p></td>
                            </tr><tr>
                                <td><p><span>User authentication fail.</span></p></td>
                            </tr>
                            <tr>
                                <td><p><span>Un-authorised access.</span></p></td>
                            </tr>
                            <tr>
                                <td><p><span>Error in working module.</span></p></td>
                            </tr>
                            <tr><td>&nbsp;</td></tr>
                            <tr><td><a class="btn btn-primary" href="/MCSilvassa"><h5>Try login again</h5> </a></td></tr>
                        </tbody></table>
                    </div>
                </div>
            </div>
        </div>
</section>
