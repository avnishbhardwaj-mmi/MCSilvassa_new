<%@ page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1"%>
<%@ page isELIgnored="false" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib uri="http://tiles.apache.org/tags-tiles" prefix="tiles"%>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
        <title><tiles:getAsString name="title" /></title>
        <link href="res/css/bootstrap.css" rel="stylesheet" type="text/css" />
        <link href="res/css/bootstrap-responsive.css" rel="stylesheet" type="text/css" />
        <link href="res/css/style.css?v1" rel="stylesheet" type="text/css" />
        <script src="res/js/api/jquery-1.10.2.js"></script>
        <script src="res/js/api/bootstrap.js" type="text/javascript"></script>
     </head>

    <body class="bodylogin" >
        <div class="wrapper">
<!--            <header id="header">
                <!--tiles:insertAttribute name="header" />
            </header>-->
            <section id="body_login">
                <tiles:insertAttribute name="body" />
            </section>
<!--            <footer id="footer">
                <!--tiles:insertAttribute name="footer" />
            </footer>-->
        </div>
    </body>
</html>