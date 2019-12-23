<%-- 
    Document   : PropertyDuesNotClear
    Created on : 29 May, 2019, 5:07:54 PM
    Author     : CEINFO
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<%
    String piddata = "";
    String amt = "";
    if (request.getAttribute("piddata") != null) {
        piddata = (String) request.getAttribute("piddata");
    }
    if (request.getAttribute("AMT") != null) {
        amt = (String) request.getAttribute("AMT");
    }
%>
<div class="mainpanel_b">
    <div class="innerdashboard p-3">

        <div class="row justify-content-center">
            <div class="col-lg-12" style="height:80vh; padding-top: 50px;">
                <div class="productCard " >
                    <center style="display: block; width: 500px; margin: 0 auto; padding: 30px; border-radius: 10px; border:#ccc 1px solid; background: #f9f9f9;">
                        <h2 class="p-3">Property Id : <%=piddata%></h2>
                        <h4 class="p-3" style="color:red">Due Amount : <%=amt%></h4>
                        <p>Property transfer  allowed only if no dues pending. Please clear your all dues from Silvassa Municipal Council.</p>
                        <a class="btn btn-lg btn-success" href="propertyTransferWithPropertyId">Back</a>
                    </center>
                </div>
            </div>
        </div>
    </div>
</div>