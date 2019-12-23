<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
         pageEncoding="ISO-8859-1"%>

<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<script src="res/js/login.js" type="text/javascript"></script>
<%
    String msg = (String) request.getAttribute("errorMsg");
%>

<div class="login_wrapper">
    <div class="d-table">
        <div class="coll_section d-table-cell " style="    vertical-align: text-bottom; text-align: center;background: url(res/img/Intersection2.png) center 0 no-repeat; background-size: cover">
            <div class="p-5 over_b_t"><img src="res/img/login_logo.png" alt="Silvassa logo"></div>
            
        </div>
        <div class="d-table-cell">
            <div class="loginpanel">
                <div class="titlelogin">
                    <h2>Login</h2>
                </div>
                <div class="log_form">
                    <form:form name="LoginForm" id="LoginForm" action="welcome" modelAttribute="loginDetailsBean" method="POST">
                        <!-- <form id="LoginForm" name="LoginForm" method="post" action="home"> -->
                        <%if (msg != null) {%>
                        <div style="color: red">Invalid Userid/Password</div>
                        <% }%>
                        <div class="label">User Name</div> 
                        <input  name="userId" value="" id="uname" class="form-control stylecss" placeholder="Enter User Name" type="text"  autocomplete="off" /> 
                        <div class="label">Password</div> 
                        <input name="userPassword" value="" id="pwd" class="form-control stylecss" placeholder="Enter Password" type="password" autocomplete="off"/>
                        
                        <input name="submit"  class="btn btn-blue " value="Login"   onclick="LoginClass.validateLogin()" type="button">
                        <input type="submit"  class="btn btn-blue " id="btnSbm" value="Submit" style="display: none;"  />
                        <br/>			
                        <!--<label><a href="#"> Forgot Password</a> </label>-->

                    </form:form>
                   
                </div>
                <div class="saw_bharat"><img src="res/img/saw1.png" alt="" width="163.96" ></div>
            </div>
            
        </div>
    </div>
</div>









