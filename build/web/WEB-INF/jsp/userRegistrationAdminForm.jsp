<%@ page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@ page import="com.silvassa.model.Usermaster"%>
<%@ page import="java.util.HashMap"%>
<%@ page import="java.util.List"%>
<%@ page import="com.google.gson.Gson"%>
<style>  
    .table th{background: #e0e0e0;}
    .propeh {
        padding: 0px 0px;
        font-size: 16px;
        font-weight: 700;
    }
    .filterward {
        padding: 15px 15px 15px;
        color: #4B6189;
    }
    .filterother {
        padding: 0px 15px 15px;
        min-height:auto;
    }
    .table th, .table td{border-right:#ccc 1px solid; padding:6px 10px; font-size: 12px;border-bottom:#ccc 1px solid;}
    .table td label{font-size: 13px;}
    .table td label{font-weight: 600;}
    .table{border-top:#ccc 1px solid; border-left: #ccc 1px solid;}
    .imageNewtab{display: block; list-style: none; margin: 0px; padding: 0px;}
    .imageNewtab li{display: inline-block;background: url(res/img/tabNewWind.png) right center no-repeat;background-size: 22px auto; padding:2px 30px 3px 5px; cursor: pointer;}
    .imageNewtab li>span{font-size: 11px; padding:2px 8px; border-radius: 4px; background: #eee; color:#000; display: block;}
</style>
<% int i = 1;%>

<section class="cont "> <!-- <form id="noticeForm" name="noticeForm" method="post" action="generateNotice1" modelAttribute="noticeBean"> -->
    <form id="" action="" method="POST" enctype="multipart/form-data" autocomplete="off">


        <c:if test="${!empty message}">
            <a  style="float: right;" class="btn btn-danger float-right" onclick="window.parent.closeCorrectionForm();" >Close</a>
            ${message}
        </c:if>
        <c:if test="${empty message}">



            <div class="filterward d-flex">   <h3 class="propeh m-0">Application No:${requestdata[0].sno}</h3>
                <div class="ml-auto"> 
                    <input type="submit" class="btn btn-primary  btn-sm" value="Accept" onclick="form.action = 'updateUserRegistration';">
                    <input type="submit" class="btn btn-danger  btn-sm" value="Reject" onclick="form.action = 'discardUserRegistration';">
                    <a class="btn btn-dark btn-sm " style="color:#fff;" onclick="window.parent.closeCorrectionForm();" >Close</a>
                </div>
            </div>
            <div class="filterother" style="height: auto;">

                <table class="table font-sm table-bordered table-condensed">

                    <tbody>
                        <tr style="display: none;">
                            <th><label for="sno">sno </label>
                                <input type="hidden" name="sno" value="${requestdata[0].sno}"/>
                            </th>
                        </tr>
                        <tr>
                            <th style="font-size:13px" colspan="2"><b ><center>New</center></b></th>
                            <th style="font-size:13px" ><b><center>Current</b></center></th>
                        </tr>
                        <tr>
                            <td><label for="uniqueId">Property id</label>
                                <input type="hidden" name="uniqueId" value="${requestdata[0].unique_id}"/>
                            </td>
                            <td>${requestdata[0].unique_id}</td>
                            <td></td>
                        </tr>
                        <tr>
                            <td><label for="ownerName">Owner Name</label></td>
                            <td>${requestdata[0].owner_name}</td>
                            <td>${ownerdata[0].property_owner}</td>

                        </tr>
                        <tr>
                            <td><label for="ownerEmail">Owner Email </label>
                                <input type="hidden" name="emailId" value="${requestdata[0].owner_email}"/>
                            </td>
                            <td>${requestdata[0].owner_email}</td>
                            <td>${ownerdata[0].property_owner_email}</td>

                        </tr>
                        <tr>
                            <td><label for="onwerMobile">Owner Mobile Number </label>
                                <input type="hidden" name="mobileNo" value="${requestdata[0].onwer_mobile}"/>
                            </td>
                            <td>${requestdata[0].onwer_mobile}</td>
                            <td>${ownerdata[0].property_contact}</td>
                        </tr>
                        <tr>
                            <td><label for="occupierName">Occupier Name </label></td>
                            <td>${requestdata[0].occupier_name}</td>
                            <td>${ownerdata[0].property_occupier_name}</td>
                        </tr>
                        <tr>
                            <td><label for="occupierEmail">Occupier Email</label>
                            </td><td>  ${requestdata[0].occupier_email}</td>
                            <td></td>
                        </tr>
                        <tr>
                            <td><label for="occupierMobile">Occupier Mobile Number</label>
                            </td><td>${requestdata[0].occupier_mobile}</td>
                            <td></td>
                        </tr>
                        <tr>
                            <td><label for="occupierMobile">Document Proof No.</label>
                            </td><td>${requestdata[0].document_proof_no}</td>
                            <td>${ownerdata[0].property_aadhar_num}</td>
                        </tr>
                        <tr>
                            <td><label for="documentType">Document Proof</label>
                            </td>
                            <td>
                                <ul class="imageNewtab"><li onclick="showDocument('${requestdata[0].image_data1}', '${requestdata[0].image_name1}');"><span>${requestdata[0].image_name1}  </span>
                                    </li>   
                                </ul>
                            </td> 
                            <td></td>
                        </tr>
                        <tr>
                            <td><label for="applicantName">Applicant Name </label>
                            </td><td> ${requestdata[0].applicant_name}</td>
                            <td></td>
                        </tr>
                        <tr>
                            <td ><label>Applicant ID Proof (Any) </label>
                            </td>
                            <td><ul  class="imageNewtab">
                                    <li onclick="showDocument('${requestdata[0].image_data}', '${requestdata[0].image_name}');"><span>${requestdata[0].image_name}  </span> 
                                    </li>   
                                </ul>
                            </td>
                            <td></td>
                        </tr>

                        <tr>
                            <td><label for="address">Address </label>
                            </td><td> </td>
                            <td>${ownerdata[0].complete_address}</td>
                        </tr>

                        <tr>
                            <td><label for="remarks">Remarks </label></td>
                            <td><textarea rows="4" cols="50" name="remarks" required></textarea></td>
                            <td></td>
                        </tr>


                    </tbody></table>
            </div>
        </c:if>

    </form>    
</section>

<script>
    function showDocument(data, type_) {
        if (type_ != '') {
            var dd = type_.split(".");
            type_ = dd[1].toUpperCase();
        }

        var image = new Image();
        if (type_ == "JPEG" || type_ == "JPG") {
            image.src = "data:image/jpg;base64," + data;
        } else if (type_ == "PNG") {
            image.src = "data:image/png;base64," + data;
        } else if (type_ == "PDF") {
            var blob;
            blob = converBase64toBlob(data, 'application/pdf');
            var blobURL = URL.createObjectURL(blob);
            window.open(blobURL);
            //image.src = "data:application/pdf;base64," + data;
        } else if (type_ == "GIF") {
            image.src = "data:image/gif;base64," + data;
        } else {
            image.src = "data:image/jpg;base64," + data;
        }
        //image.src = "data:image/jpg;base64," + data;
        if (type_ != "PDF") {
            var w = window.open("", "_blank", "toolbar=yes,top=500,left=500,width=600,height=600");
            w.document.write(image.outerHTML);
        }

    }


    function converBase64toBlob(content, contentType) {
        contentType = contentType || '';
        var sliceSize = 512;
        var byteCharacters = window.atob(content); //method which converts base64 to binary
        var byteArrays = [
        ];
        for (var offset = 0; offset < byteCharacters.length; offset += sliceSize) {
            var slice = byteCharacters.slice(offset, offset + sliceSize);
            var byteNumbers = new Array(slice.length);
            for (var i = 0; i < slice.length; i++) {
                byteNumbers[i] = slice.charCodeAt(i);
            }
            var byteArray = new Uint8Array(byteNumbers);
            byteArrays.push(byteArray);
        }
        var blob = new Blob(byteArrays, {
            type: contentType
        }); //statement which creates the blob
        return blob;
    }


</script>