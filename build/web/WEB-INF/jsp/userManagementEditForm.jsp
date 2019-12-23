<%-- 
    Document   : userManagementEditForm
    Created on : 22 Oct, 2019, 1:54:27 PM
    Author     : CEINFO
--%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@ page import="com.silvassa.model.Usermaster"%>

<%
    String msg = "";
    if (request.getAttribute("msg") != null) {
        msg = (String) request.getAttribute("msg");
    }
%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>

<section class="userCreation "> 

    <div class="col-lg-12 p-0 ">
        <div class="innerdashboard"> 

            <div id="userDivId">
                <div class="filterward pb-3 d-flex"><h3  class="propeh p-0 m-0"  >Change User Information </h3>
                    <div style="flex:0 0 72%; max-width: 72%;height:32px; text-align: center; font-style: italic;   color: green;padding: 5px 0px; "><%=msg%></div>

                </div>
                <div class="filterother">


                    <div class="row">
                        <div class="col-md-3"><label class="labeltxt">User Id</label>
                            <input class="form-control" type="text" name="userIdN" id="userid"/></div>
                        <div class="col-md-3 pt-4"> 
                            <input class="btn btn-primary" type="button" onclick="checkUserId();" value="UserId"/>
                        </div>
                    </div>

                </div>
            </div>




            <div style="display: none" id="editDivId">
                <form:form id="formId" name="noticeForm45" action="updateUserId"  modelAttribute="usermaster" method="POST">
                    <div id="arreartab_div" class="conainer">
                        <div class="filterward pb-3"><h3  class="propeh p-0 m-0"  ></h3></div>
                        <div class="filterother">
                            <div class="row">
                                <div class="col-md-12">
                                    <div class="create_newuser">
                                        <div class="row">
                                            <div class="col-md-3">
                                                <div class="form-group">
                                                    <label class="labeltxt">User Name</label>
                                                    <input class="form-control" type="text" name="username" id="name" placeholder="User Name"></div></div>
                                            <div class="col-md-3">
                                                <div class="form-group">
                                                    <label class="labeltxt">User Id</label>
                                                    <input class="form-control" type="text" name="userId" id="userId" placeholder="user Id" ></div></div>

                                            <div class="col-md-3">
                                                <div class="form-group">
                                                    <label class="labeltxt">Mobile No.</label>
                                                    <input class="form-control" type="text" name="mobile" id="mobile" placeholder="Mobile No."></div></div>
                                            <div class="col-md-3">
                                                <div class="form-group"> 
                                                    <label class="labeltxt">E-Mail</label>
                                                    <input class="form-control" type="text" name="email" id="email" placeholder="E-Mail"></div></div>


                                            <div class="col-md-3">
                                                <div class="form-group">
                                                    <label class="labeltxt">Address</label>
                                                    <input class="form-control" type="text" name="address" id="address" placeholder="Address "></div></div>
                                            <div class="col-md-3">
                                                <div class="form-group">
                                                    <label class="labeltxt">Emp. Id</label>
                                                    <input class="form-control" type="text" name="empId" id="empId" placeholder="Emp. Id"></div>
                                            </div>

                                            <div class="col-md-3">
                                                <div class="form-group">
                                                    <label class="labeltxt">Password</label>
                                                    <input class="form-control" type="text" name="pasword" id="pasword" placeholder="Password"></div></div>
                                            <div class="col-md-3">
                                                <div class="form-group">
                                                    <label class="labeltxt">Confirm Password</label>
                                                    <input class="form-control" type="text" name="confirmPassword" id="confirmPassword" placeholder="Confirm Password"></div>
                                            </div>

                                            <div class="col-md-12 text-center">
                                                <label class="labeltxt"><br><br></label>
                                                <input type="submit" class="btn btn-primary" value="Update User"> 

                                            </div>
                                        </div>


                                    </div>
                                </div>

                            </div>
                        </div>
                    </div>
                </form:form> 
            </div>
        </div>         

</section>


</body>
</html>
<script>
    function checkUserId() {

        //var check=true;
        var userid = $("#userid").val().trim();
        $("#validateid").html('');
        if (userid) {
            $.ajax({
                type: "GET",
                url: "getDataUserId",
                data: "userId=" + userid,
                cache: false,
                success: function (data) {
                    console.log(data);
                    if (data != null && data.length > 0) {

                        $('#userDivId').hide();
                        $('#editDivId').show();
                        var res = data[0];

                        $('#name').val(res.username);

                        $('#userId').val(res.userId);
                        $('#mobile').val(res.mobile);
                        $('#email').val(res.email);
                        $('#address').val(res.address);
                        $('#empId').val(res.empId);
                        $('#pasword').val(res.pasword);
                        $('#confirmPassword').val(res.pasword);

                    } else {
                        $("#validateid").html("User Id not found!"); 
                        //alert('User Id not found!');
                    }

                    //alert(data.uniqueId);
                    // alert(data.address);
                    // alert(data.msg);
                }
            });

        } else {
            $("#validateid").html("Please enter user id");   
            //alert('Please enter user id!');
        }
    }

    $(document).ready(function () {
        $("#formId").submit(function (e) {
            e.preventDefault();
            var pass = $('#pasword').val();
            var confirmPass = $('#confirmPassword').val();
            $("#validateid").html('');
            if (pass === confirmPass) {
                this.submit();
            } else {
                 $("#validateid").html("Password and confirm password should be same");   
                //alert("Password and confirm password should be same");
            }
        });
        $("#breadlist").addClass('lisb');
        $("#breadlist").html('')
        $("#breadlist").html(' <a href="userManagement?id=create">Create User</a> ');
    });


</script>
