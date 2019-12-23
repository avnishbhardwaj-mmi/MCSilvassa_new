<%@ page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1"%>
<%@ page isELIgnored="false" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib uri="http://tiles.apache.org/tags-tiles" prefix="tiles"%>

<meta charset="utf-8">

<meta name="viewport" content="width=device-width, initial-scale=1, shrink-to-fit=no">
 <!-- Meta -->
<meta name="description" content="Identity Access Management">

<title><tiles:getAsString name="title" /></title>
<link rel="icon" type="image/png" href="../images/favicon-16x16.png" sizes="16x16" />
<link rel="stylesheet" href="res/correction/css/bootstrap.css"  >
<link rel="stylesheet" href="res/correction/css/font-awesome.css"  >
<link rel="stylesheet" href="res/correction/css/custom.css">
<!--<link rel="stylesheet" href="res/correction/css/progress-bar.css">-->
<script src="res/correction/js/jquery-3.3.1.js"></script>
<script src="res/correction/js/popper.js"></script>
<script src="res/correction/js/bootstrap.min.js"></script>

<html>
    <body>            <tiles:insertAttribute name="header" />
   
            <tiles:insertAttribute name="menu" />
            <tiles:insertAttribute name="body" />
            <tiles:insertAttribute name="footer" />
      
    </body>
</html>