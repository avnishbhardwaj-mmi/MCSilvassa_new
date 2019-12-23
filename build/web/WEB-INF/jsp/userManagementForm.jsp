<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
         pageEncoding="ISO-8859-1"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@ page import="com.silvassa.model.Usermaster"%>
<%@ page import="java.util.HashMap"%>
<%@ page import="java.util.List"%>
<%@ page import="com.google.gson.Gson"%>

<%
    List<Usermaster> userList = null;
    String json = null;
    if (request.getAttribute("id").toString().equals("permission")) {
        userList = (List<Usermaster>) request.getAttribute("userList");
        json = new Gson().toJson(userList);
%>
<section class="dashboard-sec cont_new">
    <div class="row">
        <div class="col-lg-12 p-0">

            <!-- <form id="noticeForm" name="noticeForm" method="post" action="generateNotice1" modelAttribute="noticeBean"> -->
            <form:form name="noticeForm" action="setPermission"  modelAttribute="filterBean" method="POST">

                <div class="innerdashboard">
                    <div id="arreartab_div" class="conainer">
                        <div class="filterward pb-3"></div>
                        <div class="filterother">
                            <div class="row">
                                <div class="col-md-4">
                                    <h3  class="propeh pb-4 m-0"  >User Management</h3>
                                    <div class="form-group">
                                        <select class="form-control"
                                                id="userId" name="zoneId" onchange="filedetails(this.value)">
                                            <option value="-1">Select User</option>
                                            <%
                                                for (int i = 0; i < userList.size(); i++) {
                                            %>
                                            <option value="<%=i%>"><%=userList.get(i).getUsername()%></option>
                                            <%
                                                }
                                            %>
                                        </select>
                                    </div>

                                </div> <div class="col-md-1"></div>
                                <div class="col-md-6">

                                    <fieldset>
                                        <div class="form-group permission font-weight-bold d-flex " ><legend id = "legend" style="font-size: 16px; display: none"><%=request.getAttribute("msg")%></legend>  <input type="submit" id="setpermission" class="btn btn-primary ml-auto" value="Set Permission" style="display:none"></div>
                                        <div class="form-group permissiondiv" id="permissionDiv"></div>
                                        <div class="span12 text-center">

                                        </div>

                                    </fieldset>
                                </div>
                            </div>
                        </div>
                    </div>
                </div>
            </div>      
        </form:form> 
</section>
<%
} else {
%>
<section class="userCreation "> <!-- <form id="noticeForm" name="noticeForm" method="post" action="generateNotice1" modelAttribute="noticeBean"> -->
    <form:form name="noticeForm" action="createNewUser"  modelAttribute="usermaster" method="POST">   
        <div class="col-lg-12 pl-0">
            <div class="innerdashboard">
                <div id="arreartab_div" class="conainer">
                    <div class="filterward  d-flex"><h3  class="propeh p-0 m-0"  ><%=request.getAttribute("msg")%></h3>
                        <div class="ml-auto text-right">

                            <input type="submit" class="btn btn-primary" value="Create User"> 
                            <a class="btn btn-outline-primary" href="changeUserId">Edit User</a>
                        </div>
                    </div>
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
                                                <input class="form-control" type="text" name="userId" id="userId" placeholder="user Id"></div></div>

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
                                                <input class="form-control" type="password" name="pasword" id="pasword" placeholder="Password"></div></div>
                                        <div class="col-md-3">
                                            <div class="form-group">
                                                <label class="labeltxt">Confirm Password</label>
                                                <input class="form-control" type="password" name="confirmPassword" id="confirmPassword" placeholder="Confirm Password"></div>
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
<%
    }
%>
<script>
    function filedetails(index) {
        if (index !== '-1') {
            $("#legend").css('display', 'block');
        }
        else {
            $("#legend").css('display', 'none');
        }
        $("#permissionDiv").html('');
        var var1 = (
    <%=json%>
        );
        var1 = var1[index]

        var table = "<table class='tableSection table table-bordered table-sm'><tr><th><label class=labeltxt><b>Permission Name</b> </label></th><th><label class=labeltxt><b>Action</b> </label></th></tr>";
        var var2 = var1.permissionList;
        for (var i = 0; i < var2.length; i++) {
            table = table
                    + "<tr><td><label class=labeltxt>"
                    + var2[i].menuDescription
                    + " </label></td><td><label class=labeltxt><input type=checkbox name=permissionId value='" + var2[i].permissionId + "' " + var2[i].ischecked + "></label></td></tr>";
        }
        table = table
                + "<input type=hidden name=roleId value=" + var1.roleId + "></table>";
        $("#permissionDiv").html(table);
        $("#setpermission").show();
    }
</script>


<script>
    $(document).ready(function () {
        if ($("section").hasClass('cont_new')) {
            $("#user_mgmt_main_menu").addClass("active");
            $("#user_mgmt_sub_menu_permission").addClass("active");
            $("#user_mgmt_main_menu ul.sub-menu ").addClass("show");
            $("#breadlist").addClass('lisb');
            $("#breadlist").html('')
            $("#breadlist").html(' <a href="userManagement?id=permission">User Permission</a> ');
        }
        else {
            $("#user_mgmt_main_menu").addClass("active");
            $("#user_mgmt_sub_menu_create").addClass("active");
            $("#user_mgmt_main_menu ul.sub-menu ").addClass("show");
            $("#breadlist").addClass('lisb');
            $("#breadlist").html('')
            $("#breadlist").html(' <a href="userManagement?id=create">Create User</a> ');
        }
    });
</script>
