<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib uri = "http://java.sun.com/jsp/jstl/functions" prefix = "fn" %>
<%@ page isELIgnored="false"%>

<link rel="stylesheet" href="res/correction/css/bootstrap.css"  >
<link rel="stylesheet" href="res/correction/css/font-awesome.css"  >
<link rel="stylesheet" href="res/correction/css/custom.css">
<script src="res/correction/js/jquery-3.3.1.js"></script>
<script src="res/correction/js/bootstrap.min.js"></script>
<% int ownerval=1;
   int occupierval=1;
   int addressval=1;
   
%>


<div class="innerContainer" >
    <div class="headertitle d-flex">
        <div class="titleSection d-flex">
            <c:if test="${!empty correction && !empty actual}">
                <h1>Complaint No. ${correction.application_no}</h1>
                <div class="complainno d-flex">
                    <div>Property ID</div>
                    <div class="ml-auto">${correction.uniqueId}</div>
                </div>
            </c:if>
            <c:if test="${!empty submitmsg}">
                <h1>${submitmsg}</h1>
            </c:if>

        </div>
        <div class="action_tools ml-auto"> 

            <c:if test="${!empty correction && !empty actual}">
                <a class="btn btn-perpal" onclick="$('.attachmentList').toggle(100)"><i class="material-icons">attachment</i>Attachment</a> 

                <c:if test="${!empty correctionsproofs}">
                    <ul class="attachmentList">
                        <c:forEach items="${correctionsproofs}" var = "proofObj" varStatus="loop" >
                            <c:if test="${proofObj.img_col=='image' || proofObj.img_col=='image_owner2' ||  proofObj.img_col=='image_owner3' || proofObj.img_col=='image_owner4'}">
                                <% if(ownerval==1){
                                  ownerval++; %> 
                                  <font color="red"><ol > Owner's Proof</ol></font> 
                                <%}%>
                                   
                            <li onclick="showDocument('${correction.uniqueId}', '${correction.application_no}', '${proofObj.img_col}', '${proofObj.img_name}');" ><span class='li_overflow_ctrl'>${proofObj.img_name}</span><i class="material-icons ml-auto">open_in_new</i></li>
                            </c:if>
                            <c:if test="${proofObj.img_col=='image_occupier' || proofObj.img_col=='image_occupier2' ||  proofObj.img_col=='image_occupier3' || proofObj.img_col=='image_occupier4'}">
                            <% if(occupierval==1){
                                  occupierval++; %> 
                                  <font color="red"><ol> Occupier's Proof</ol></font> 
                                <%}%>
                            <li onclick="showDocument('${correction.uniqueId}', '${correction.application_no}', '${proofObj.img_col}', '${proofObj.img_name}');" ><span class='li_overflow_ctrl'>${proofObj.img_name}</span><i class="material-icons ml-auto">open_in_new</i></li>
                            </c:if>
                            <c:if test="${proofObj.img_col=='image_address' || proofObj.img_col=='image_address2' ||  proofObj.img_col=='image_address3' || proofObj.img_col=='image_address4' }">
                            <% if(addressval==1){
                                  addressval++; %> 
                                  <font color="red"><ol> Address  Proof</ol></font> 
                                <%}%>
                            <li onclick="showDocument('${correction.uniqueId}', '${correction.application_no}', '${proofObj.img_col}', '${proofObj.img_name}');" ><span class='li_overflow_ctrl'>${proofObj.img_name}</span><i class="material-icons ml-auto">open_in_new</i></li>
                            </c:if>
                            <c:if test="${proofObj.img_col=='image_electric'}">
                            <font color="red"><ol> Electric connection Proof</ol></font> 
                            <li onclick="showDocument('${correction.uniqueId}', '${correction.application_no}', '${proofObj.img_col}', '${proofObj.img_name}');" ><span class='li_overflow_ctrl'>${proofObj.img_name}</span><i class="material-icons ml-auto">open_in_new</i></li>
                            </c:if>
                            <c:if test="${proofObj.img_col=='image_arrear'}">
                            <font color="red"><ol> Arrear Proof</ol></font>
                            <li onclick="showDocument('${correction.uniqueId}', '${correction.application_no}', '${proofObj.img_col}', '${proofObj.img_name}');" ><span class='li_overflow_ctrl'>${proofObj.img_name}</span><i class="material-icons ml-auto">open_in_new</i></li>
                            </c:if>
                             <c:if test="${proofObj.img_col=='image_covered'}">
                            <font color="red"><ol> Covered Area Proof</ol></font>
                             <li onclick="showDocument('${correction.uniqueId}', '${correction.application_no}', '${proofObj.img_col}', '${proofObj.img_name}');" ><span class='li_overflow_ctrl'>${proofObj.img_name}</span><i class="material-icons ml-auto">open_in_new</i></li>
                            </c:if>
                             <c:if test="${proofObj.img_col=='image_property_use'}">
                             <font color="red"><ol> Property use Proof</ol></font>
                             <li onclick="showDocument('${correction.uniqueId}', '${correction.application_no}', '${proofObj.img_col}', '${proofObj.img_name}');" ><span class='li_overflow_ctrl'>${proofObj.img_name}</span><i class="material-icons ml-auto">open_in_new</i></li>
                            </c:if>
                            
<%--                            <li onclick="showDocument('${correction.uniqueId}', '${correction.application_no}', '${proofObj.img_col}', '${proofObj.img_name}');" ><span class='li_overflow_ctrl'>${proofObj.img_name}</span><i class="material-icons ml-auto">open_in_new</i></li>--%>
                            </c:forEach>
                    </ul>
                </c:if>


                <!--<button type="button" class="btn btn-success"  ><i class="material-icons">check</i>Action</button>-->
            </c:if>
            <a class="btn btn-danger" onclick="window.parent.closeCorrectionForm();" ><i class="material-icons">close</i>Close</a>
        </div>
    </div>

    <div class="detailFormContainer">


        <c:if test="${!empty correction && !empty actual}">
            <fieldset>
                <legend>Owner Details</legend>
                <table class="table">
                    <tr>
                        <th>Attribute Name</th>
                        <th>Current Data</th>
                        <th>New Data</th>
                    </tr>
                    <c:if test="${correction.checkOwnerName != null && correction.checkOwnerName=='Y'}">
                        <tr><td>Owner Name </td><td>${actual.ownerName}</td><td>${correction.ownerName}</td></tr>
                    </c:if>
                    <c:if test="${correction.checkOwnerFather != null && correction.checkOwnerFather=='Y'}">
                        <tr><td>Owner's Father </td><td>${actual.ownerFather}</td><td>${correction.ownerFather}</td></tr>
                    </c:if>
                    <c:if test="${correction.checkSpouseName != null && correction.checkSpouseName=='Y'}">
                        <tr><td>Owner's Spouse Name </td><td>${actual.spouseName}</td><td>${correction.spouseName}</td></tr>
                    </c:if>
                    <c:if test="${correction.checkOwnerContact != null && correction.checkOwnerContact=='Y'}">
                        <tr><td>Owner Contact </td><td>${actual.ownerContact}</td><td>${correction.ownerContact}</td></tr>
                    </c:if>
                    <c:if test="${correction.checkOwnerEmail != null && correction.checkOwnerEmail=='Y'}">
                        <tr><td>Owner Email </td><td>${actual.ownerEmail}</td><td>${correction.ownerEmail}</td></tr>
                    </c:if>
                    <c:if test="${correction.checkOwnerAadharNo != null && correction.checkOwnerAadharNo=='Y'}">
                        <tr><td>Owner Aadhaar No. </td><td>${actual.ownerAadharNo}</td><td>${correction.ownerAadharNo}</td></tr>
                    </c:if>
                    <c:if test="${correction.checkOwnerSex != null && correction.checkOwnerSex=='Y'}">
                        <tr><td>Owner Gender</td><td>${actual.ownerSex}</td><td>${correction.ownerSex}</td></tr>
                    </c:if>
                </table>
            </fieldset>
            <fieldset>
                <legend>Occupier Details</legend>
                <table class="table">
                    <tr>
                        <th>Attribute Name</th>
                        <th>Current Data</th>
                        <th>New Data</th>
                    </tr>
                    <c:if test="${correction.checkOccupierName != null && correction.checkOccupierName=='Y'}">
                        <tr><td>Occupier Name </td><td>${actual.occupierName}</td><td>${correction.occupierName}</td></tr>
                    </c:if>
                    <c:if test="${correction.checkOccupierFather != null && correction.checkOccupierFather=='Y'}">
                        <tr><td>Occupier's Father </td><td>${actual.occupierFather}</td><td>${correction.occupierFather}</td></tr>
                    </c:if>
                    <c:if test="${correction.checkOccupierContact != null && correction.checkOccupierContact=='Y'}">
                        <tr><td>Occupier Contact </td><td>${actual.occupierContact}</td><td>${correction.occupierContact}</td></tr>
                    </c:if>
                    <c:if test="${correction.checkOccupierEmail != null && correction.checkOccupierEmail=='Y'}">
                        <tr><td>Occupier Email </td><td>${actual.occupierEmail}</td><td>${correction.occupierEmail}</td></tr>
                    </c:if>
                    <c:if test="${correction.checkOccupierAadharNo != null && correction.checkOccupierAadharNo=='Y'}">
                        <tr><td>Occupier Aadhaar No. </td><td>${actual.occupierAadharNo}</td><td>${correction.occupierAadharNo}</td></tr>
                    </c:if>
                    <c:if test="${correction.checkOccupierSex != null && correction.checkOccupierSex=='Y'}">
                        <tr><td>Occupier Gender </td><td>${actual.occupierSex}</td><td>${correction.occupierSex}</td></tr>
                    </c:if>
                </table>
            </fieldset>
            <fieldset>
                
                <legend>Other Details</legend>
                <table class="table">
                    <tr>
                        <th>Attribute Name</th>
                        <th>Current Data</th>
                        <th>New Data</th>
                    </tr>
                    <c:if test="${correction.checkPlotNo != null && correction.checkPlotNo=='Y'}">
                        <tr><td>Plot No. </td><td>${actual.plotNo}</td><td>${correction.plotNo}</td></tr>
                    </c:if>
                    <c:if test="${correction.checkHouseNo != null && correction.checkHouseNo=='Y'}">
                        <tr><td>House No. </td><td>${actual.plotNo}</td><td>${correction.houseNo}</td></tr>
                    </c:if>
                    <c:if test="${correction.checkBuildingName != null && correction.checkBuildingName=='Y'}">
                        <tr><td>Building Name </td><td>${actual.buildingName}</td><td>${correction.buildingName}</td></tr>
                    </c:if>
                    <c:if test="${correction.checkRoadName != null && correction.checkRoadName=='Y'}">
                        <tr><td>Road Name </td><td>${actual.roadName}</td><td>${correction.roadName}</td></tr>
                    </c:if>
                    <c:if test="${correction.checkSubLocality != null && correction.checkSubLocality=='Y'}">
                        <tr><td>Sub-Locality </td><td>${actual.subLocality}</td><td>${correction.subLocality}</td></tr>
                    </c:if>
                    <c:if test="${correction.checkLocName != null && correction.checkLocName=='Y'}">
                        <tr><td>Locality Name </td><td>${actual.locName}</td><td>${correction.locName}</td></tr>
                    </c:if>
                    <c:if test="${correction.checkLandMark != null && fn:trim(correction.checkLandMark)=='Y'}">
                        <tr><td>LandMark </td><td>${actual.landMark}</td><td>${correction.landMark}</td></tr>
                    </c:if>
                    <c:if test="${correction.checkElectricSericeConnectionNo != null && correction.checkElectricSericeConnectionNo=='Y'}">
                        <tr><td>Electric Connection No. </td><td>${actual.electricServiceConNo}</td><td>${correction.electricSericeConnectionNo}</td></tr>
                    </c:if>
                    <c:if test="${correction.checkSurveyNo != null && correction.checkSurveyNo=='Y'}">
                        <tr><td>Survey No. </td><td>${actual.surveyNo}</td><td>${correction.surveyNo}</td></tr>
                    </c:if>
                    <c:if test="${correction.checkPlotSmc != null && correction.checkPlotSmc=='Y'}">
                        <tr><td>Plot Smc </td><td>${actual.plotSmc}</td><td>${correction.plotSmc}</td></tr>
                    </c:if>
                    <c:if test="${correction.checkArrear != null && correction.checkArrear=='Y'}">
                        <tr><td>Arrear Amount </td><td>${actual.arrearAmount}</td><td>${correction.arrearAmount}</td></tr>
                    </c:if>


                    <c:if test="${correction.checkSmcProperty != null && correction.checkSmcProperty=='Y'}">
                        <tr><td>SMC Property </td><td>${actual.smcProperty}</td><td>${correction.smcProperty}</td></tr>
                    </c:if>
                    <c:if test="${correction.checkPropertyOwnerAddress != null && correction.checkPropertyOwnerAddress=='Y'}">
                        <tr><td>Property Owner Address </td><td>${actual.propertyOwnerAddress}</td><td>${correction.propertyOwnerAddress}</td></tr>
                    </c:if>
                </table>
            </fieldset>
            <fieldset>
                <legend>Floor Details</legend>

                <c:if test="${empty correction.floorDetails}">
                    <table class="table" >
                        <thead><tr ><td clospan="8">Nothing to update.</td></tr></thead>
                    </table>
                </c:if>
                <c:if test="${!empty correction.floorDetails}">
                    <table class="table" >
                        <thead>
                            <tr>
                                <th>Floor Type</th>
                                <th>Covered Area</th>
                                <th>Use</th>
                                <th>Sub-Type</th>
                                <th>Construction</th>
                                <th>Self/Rent</th>
                                <th>Rented Value</th>
                                <th>Change in record</th>
                            </tr>
                        </thead>
                        <tbody>
                            <c:forEach items="${correction.floorDetails}" var = "fDetail" varStatus="loop" >
                                <tr>
                                    <c:choose>
                                        <c:when test="${fDetail.editData eq 'Y' && empty fDetail.deleteData}">
                                            <td class="new_data" >${fDetail.floorType} <span class="old_data">(${actual.floorMap[fDetail.propertyFloorId].floorType})</span></td>
                                            <td class="new_data" >${fDetail.carpetArea} <span class="old_data">(${actual.floorMap[fDetail.propertyFloorId].carpetArea})</span></td>
                                            <td class="new_data" >${fDetail.propertyUse} <span class="old_data">(${actual.floorMap[fDetail.propertyFloorId].propertyUse})</span></td>
                                            <td class="new_data" >${fDetail.propertySubType} <span class="old_data">(${actual.floorMap[fDetail.propertyFloorId].propertySubType})</span></td>
                                            <td class="new_data" >${fDetail.constructionType} <span class="old_data">(${actual.floorMap[fDetail.propertyFloorId].constructionType})</span></td>
                                            <td class="new_data" >${fDetail.selfRent} <span class="old_data">(${actual.floorMap[fDetail.propertyFloorId].selfRent})</span></td>
                                            <td class="new_data" >${fDetail.rentedValue} <span class="old_data">(${actual.floorMap[fDetail.propertyFloorId].rentedValue})</span></td>
                                        </c:when>
                                        <c:otherwise>
                                            <td>${fDetail.floorType}</td>
                                            <td>${fDetail.carpetArea}</td>
                                            <td>${fDetail.propertyUse}</td>
                                            <td>${fDetail.propertySubType}</td>
                                            <td>${fDetail.constructionType}</td>
                                            <td>${fDetail.selfRent}</td>
                                            <td>${fDetail.rentedValue}</td>
                                        </c:otherwise>
                                    </c:choose>

                                    <c:choose>
                                        <c:when test="${fDetail.editData eq 'Y' && empty fDetail.deleteData}">
                                            <td>Update</td>
                                        </c:when>
                                        <c:when test="${fDetail.editData eq 'new'}">
                                            <td>New</td>
                                        </c:when>
                                        <c:when test="${fDetail.deleteData eq 'Y'}">
                                            <td>Delete</td>
                                        </c:when>
                                        <c:otherwise>
                                            <td></td>
                                        </c:otherwise>
                                    </c:choose>

                                </tr>
                            </c:forEach>
                        </tbody>
                    </table>
                    <div class="col-sm-2 float-right">
                        <table class="table " >
                            <tbody>
                                <tr>
                                    <th><i class="material-icons new_data">crop_square</i></th>
                                    <th>New Data</td>
                                </tr>
                                <tr>
                                    <th><i class="material-icons old_data">crop_square</i></th>
                                    <th>Old Data</td>
                                </tr>
                            </tbody>
                        </table>
                    </div>
                </c:if>
            </fieldset>

        </c:if>
        <c:if test="${page ne 'p_solve' && page ne 'p_reject' && empty submitmsg 
                      && correctionstatus ne 'applied' && correctionstatus ne 'reject'}" >
              <fieldset>
                  <legend style="background: #46c17d">Action</legend>
                  <form id="action_form_submit1"  onsubmit="return confirm('Do you really want to submit the form?');"
                        method="POST" action="correctionUpdateAction" modelAttribute="correctionAction">

                      <table class="table">
                          <tr><td> <br> <label for="correctionRemarks">Remarks</label>
                                  <textarea rows="2" cols="50" style="width: auto;" path="correctionRemarks" 
                                            required="required" data-validation-length="min4" 
                                            data-validation-error-msg="Please add remark."
                                            name="correctionRemarks" id="correctionRemarks" ></textarea></td>
                              <td> <br>

                                  <input type='hidden' name='propertyId' value="${correction.uniqueId}" />
                                  <input type='hidden' name='applicationNo' value="${correction.application_no}" />            
                                  <div class="filterDropdown d-flex align-content-center"> <span style="vertical-align: middle; display: inline-block;line-height: 25px; font-weight: 600">Status</span>

                                      <!--p_inbox,p_all,p_field,p_overdue,p_reject,p_solve-->

                                      <select name="correctionStatus" required="required" id="correctionStatus">
                                          <c:if test="${correctionstatus eq 'read' || correctionstatus eq null}" >
                                              <option value="fieldverify">Field Verification</option>
                                          </c:if>
                                          <c:if test="${correctionstatus eq 'read' || correctionstatus eq null}" >
                                              <option value="approve">Approve</option>
                                          </c:if>
                                          <c:if test="${correctionstatus eq 'approve' || correctionstatus eq 'read' || correctionstatus eq null}" >
                                              <option value="reject">Reject</option>
                                          </c:if>
                                          <c:if test="${correctionstatus eq 'approve'}" >
                                              <option value="applied">Apply Changes</option>
                                          </c:if>
                                          <c:if test="${correctionstatus eq 'fieldverify'}" >
                                              <option value="read">Pass</option>
                                          </c:if>
                                          <c:if test="${correctionstatus eq 'fieldverify'}" >
                                              <option value="reject">Fail</option>
                                          </c:if>
                                      </select>
                                      <input class="btn-dark"  type="submit" value="Apply" />
                              </td>
                          </tr>
                      </table>

                  </form>
              </fieldset>
        </c:if>

        <c:if test="${empty submitmsg}">


            <fieldset>
                <legend style="background: #ff8d00">Action History</legend>

                <table class="table sl_cust_table" id='decision_history'>
                    <thead>
                        <tr>
                            <th>Action Date</th>
                            <th>Action By</th>
                            <th>Action</th>
                            <th>Remarks</th>
                        </tr>
                    </thead>
                    <tbody>
                        <c:if test="${empty actionHistory && actionHistory eq null}">
                            <tr class="no_data_row"><td colspan="5"><center>No action performed till date</center></td>
                        </c:if>
                        <c:forEach items="${actionHistory}" var = "actionItem" varStatus="loop" >
                        <tr>
                            <td class="td-sm">${actionItem.entrydatetime}</td>
                            <td class="td-sm">${actionItem.action_by}</td>
                            <c:if test="${actionItem.action_taken eq 'read'}" >
                                <td class="td-sm">Field Verification Pass</td>
                            </c:if>
                            <c:if test="${actionItem.action_taken eq 'reject'}" >
                                <td class="td-sm">Rejected</td>
                            </c:if>
                            <c:if test="${actionItem.action_taken eq 'approve'}" >
                                <td class="td-sm">Approved</td>
                            </c:if>
                            <c:if test="${actionItem.action_taken eq 'fieldverify'}" >
                                <td class="td-sm">Field Verification Required</td>
                            </c:if>
                            <c:if test="${actionItem.action_taken eq 'applied'}" >
                                <td class="td-sm">Changes Applied</td>
                            </c:if>

                            <td class="td-sm">${actionItem.action_remarks}</td>
                        </tr>
                    </c:forEach>

                    </tbody>
                </table>
            </fieldset>
        </c:if>


    </div>
</div>



<script>
    function showDocument(propId, applicationNo, imgIdentifier, type_) {
        if (type_ != '') {
            var dd = type_.split(".");
            type_ = dd[1].toUpperCase();
        }
        $.get("getBase64ImageProof", {
            propId: propId,
            applicationNo: applicationNo,
            imgIdentifier: imgIdentifier
        }, function (data) {
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
        });
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