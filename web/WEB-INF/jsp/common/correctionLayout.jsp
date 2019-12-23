<%@ page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1"%>
<%@ page isELIgnored="false" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib uri="http://tiles.apache.org/tags-tiles" prefix="tiles"%>

<html>

    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
        <title><tiles:getAsString name="title" /></title>
        <link href="<c:url value='/res/css/bootstrap.css' />"  rel="stylesheet"></link>
        <link href="<c:url value='/res/css/app.css' />" rel="stylesheet"></link>
    </head>

    <body>


        <tiles:insertAttribute name="body" />



    </body>
</html>