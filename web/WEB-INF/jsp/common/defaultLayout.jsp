<%@ page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1"%>
<%@ page isELIgnored="false"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib uri="http://tiles.apache.org/tags-tiles" prefix="tiles"%>

<html>

<head>
	<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
	<title><tiles:getAsString name="title" /></title>
        <link rel="shortcut icon" type="image/x-icon" href="res/img/logo2.png" />
        <link rel="stylesheet" href="https://fonts.googleapis.com/icon?family=Material+Icons" />
 	<link href="res/css/buttons.dataTables.min.css" rel="stylesheet" type="text/css" />
	<link href="res/css/font-awesome.css" rel="stylesheet" type="text/css" />
	<link href="res/css/bootstrap.css" rel="stylesheet" type="text/css" />
 	<link href="res/css/style.css?v1" rel="stylesheet" type="text/css" />
	<link rel="stylesheet" href="res/css/jquery-ui.css">
        
            <script src="res/js/api/jquery-1.10.2.js"></script>
            <script src="https://cdnjs.cloudflare.com/ajax/libs/popper.js/1.12.9/umd/popper.min.js"  ></script>

    <script src="res/js/api/bootstrap.js" type="text/javascript"></script>
    <script src="res/js/api/jquery.sumoselect.js" type="text/javascript"></script>
</head>

<script type="text/javascript">
	window.history.forward();
	function noBack() {
		window.history.forward();
	}
</script>

<body onload="noBack();" onpageshow="if (event.persisted) noBack();"
	onunload="">
	<header class="custom-header" id="header">
		<tiles:insertAttribute name="header" />
	</header>

	<section class="sidemenu" id="sidemenu">
            <tiles:insertAttribute name="menu" />
	</section>

	<section id="site-content" class="mainpanel">
            <div class="dashboard-sec"><tiles:insertAttribute name="body" /></div>
	</section>

	<footer id="footer">
		<tiles:insertAttribute name="footer" />
	</footer>
        <script>
     
$(window).on("load",function () {
        toggleMenu();
        var w = $(window).innerWidth();

        if (w < 992) {
            $(".navicon").click();
             toggleMenu();
        }
    });
   
    function toggleMenu() {
     $(".navicon").on("click", function () { 
            $(this).toggleClass('active');
            $(".header-left").toggleClass('web-header-left');
            $(".sideleft").toggleClass('webSideLeft');
            $(".logoText").toggleClass('abc');
            $(".mainNav").toggleClass('abc');
            $("ul.sub-menu").toggleClass('abc');           
            $(".footer").toggleClass('abc');
            $(".header-nav-profile .dropdown .profile-info").toggleClass('abc');
            $("#mainMenu .title, #mainMenu .title, #mainMenu  .arrow").toggleClass('abc');
            $(".mainpanel").toggleClass('webmainpanel');
            $("#mainMenu .sideleft-menu>li").toggleClass('abc_list');
            if ($('.sideleft').hasClass('webSideLeft')) {
                $('.nav-item').removeClass('show'); 
            }
          setTimeout(function(){ getChartData(); }, 500);
//           $('#container').on('resize',function() {
//           var inner = $('#container').innerWidth();
//            alert(inner);
//            $(document).trigger('resize');
//            getChartData();
//            chart.reflow();
//            });
        });
       
       // $(window).trigger('resize');
    }
    
      $(document).ready(function(){
  $('[data-toggle="tooltip"]').tooltip();
}); 
        </script>
</body>
</html>