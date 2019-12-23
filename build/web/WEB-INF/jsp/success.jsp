<%-- 
    Document   : success
    Created on : 10 Jan, 2019, 7:00:21 PM
    Author     : CEINFO
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%
    String msg = (String) request.getAttribute("MSG");
      
    int i=0;
    
%>
<!DOCTYPE html>
<html>
    <head>
        <title>Data</title>
        <style type="text/css">
            .tg  {border-collapse:collapse;border-spacing:0;border-color:#ccc;}
            .tg td{font-family:Arial, sans-serif;font-size:14px;padding:10px 5px;border-style:solid;border-width:1px;overflow:hidden;word-break:normal;border-color:#ccc;color:#333;background-color:#fff;}
            .tg th{font-family:Arial, sans-serif;font-size:14px;font-weight:normal;padding:10px 5px;border-style:solid;border-width:1px;overflow:hidden;word-break:normal;border-color:#ccc;color:#333;background-color:#f0f0f0;}
            .tg .tg-4eph{background-color:#f9f9f9}
        </style>
    </head>
    <body>
        <section class="html_silvassa">
            <div class="container">  
                <div class="innercoantent">
                    <div id="global">
                               
                        <centre><h5>Details of applicant are as follows</h5></centre>
                        <div><a style="display:none" href="displayCorrectionFormData">Show Correction Data </a></div> 
                        <div><a <a style="display:none" href="displayCorrectionFormFloorData">Show Floor Data </a></div> 
                        <div> <a href="/SilvassaPay"><i class="material-icons">keyboard_arrow_left</i>Back</a></div> 
                        <centre><h1><%=msg%>!</h1> </centre>
                       
                        <table class="tg">
                            <tr>
                                <td>Application No.</td> <td>${bean.application_no}</td>
                            </tr>
                            <tr>
                                <td>Property Id</td> <td>${bean.uniqueId}</td>
                            </tr>
                        </table>
                        
                           
                    </div>
                </div>
            </div>
        </section>

        <script>

            function getBase64Image(byte_arr) {
                var image = new Image();
                image.src = "data:image/jpg;base64," + byte_arr;
                var w = window.open("", "_blank", "toolbar=yes,top=500,left=500,width=600,height=600");
                w.document.write(image.outerHTML);
            }

            function getBase64Image(id_) {

                $.get("getBase64ImageCorrectedForm", {
                    id: id_
                }, function (data) {
                    var image = new Image();
                    image.src = "data:image/jpg;base64," + data;
                    var w = window.open("", "_blank", "toolbar=yes,top=500,left=500,width=600,height=600");
                    w.document.write(image.outerHTML);
                });
            }
            function getBase64ImageOwner2(id_) {

                $.get("getBase64ImageCorrectedFormOwner2", {
                    id: id_
                }, function (data) {
                    var image = new Image();
                    image.src = "data:image/jpg;base64," + data;
                    var w = window.open("", "_blank", "toolbar=yes,top=500,left=500,width=600,height=600");
                    w.document.write(image.outerHTML);
                });
            }
            function getBase64ImageOccupier(id_) {

                $.get("getBase64ImageCorrectedFormOccupier", {
                    id: id_
                }, function (data) {
                    var image = new Image();
                    image.src = "data:image/jpg;base64," + data;
                    var w = window.open("", "_blank", "toolbar=yes,top=500,left=500,width=600,height=600");
                    w.document.write(image.outerHTML);
                });
            }
            function getBase64ImageOccupier2(id_) {

                $.get("getBase64ImageCorrectedFormOccupier2", {
                    id: id_
                }, function (data) {
                    var image = new Image();
                    image.src = "data:image/jpg;base64," + data;
                    var w = window.open("", "_blank", "toolbar=yes,top=500,left=500,width=600,height=600");
                    w.document.write(image.outerHTML);
                });
            }
            function getBase64ImageAddress(id_) {

                $.get("getBase64ImageCorrectedFormAddress", {
                    id: id_
                }, function (data) {
                    var image = new Image();
                    image.src = "data:image/jpg;base64," + data;
                    var w = window.open("", "_blank", "toolbar=yes,top=500,left=500,width=600,height=600");
                    w.document.write(image.outerHTML);
                });
            }
            function getBase64ImageAddress2(id_) {

                $.get("getBase64ImageCorrectedFormAddress2", {
                    id: id_
                }, function (data) {
                    var image = new Image();
                    image.src = "data:image/jpg;base64," + data;
                    var w = window.open("", "_blank", "toolbar=yes,top=500,left=500,width=600,height=600");
                    w.document.write(image.outerHTML);
                });
            }
            function getBase64ImageElectric(id_) {

                $.get("getBase64ImageCorrectedFormElectric", {
                    id: id_
                }, function (data) {
                    var image = new Image();
                    image.src = "data:image/jpg;base64," + data;
                    var w = window.open("", "_blank", "toolbar=yes,top=500,left=500,width=600,height=600");
                    w.document.write(image.outerHTML);
                });
            }
            function getBase64ImageCovered(id_) {

                $.get("getBase64ImageCorrectedFormCovered", {
                    id: id_
                }, function (data) {
                    var image = new Image();
                    image.src = "data:image/jpg;base64," + data;
                    var w = window.open("", "_blank", "toolbar=yes,top=500,left=500,width=600,height=600");
                    w.document.write(image.outerHTML);
                });
            }
            function getBase64ImagePropertyUse(id_) {

                $.get("getBase64ImageCorrectedFormPropertyUse", {
                    id: id_
                }, function (data) {
                    var image = new Image();
                    image.src = "data:image/jpg;base64," + data;
                    var w = window.open("", "_blank", "toolbar=yes,top=500,left=500,width=600,height=600");
                    w.document.write(image.outerHTML);
                });
            }
            function getBase64ImageArrear(id_) {

                $.get("getBase64ImageCorrectedFormArrear", {
                    id: id_
                }, function (data) {
                    var image = new Image();
                    image.src = "data:image/jpg;base64," + data;
                    var w = window.open("", "_blank", "toolbar=yes,top=500,left=500,width=600,height=600");
                    w.document.write(image.outerHTML);
                });
            }

        </script>
    </body>
</html>

