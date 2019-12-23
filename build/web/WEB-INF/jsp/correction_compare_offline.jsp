<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ page isELIgnored="false"%>

<link rel="stylesheet" href="res/correction/css/bootstrap.css"  >
<link rel="stylesheet" href="res/correction/css/font-awesome.css"  >
<link rel="stylesheet" href="res/correction/css/custom.css">
<script src="res/correction/js/jquery-3.3.1.js"></script>
<script src="res/correction/js/bootstrap.min.js"></script>


<div class="innerContainer" >
    <div class="headertitle d-flex">
        <div class="titleSection">
            <c:if test="${!empty correction}">
                <h1 id="complainNo">Complaint No. ${correction.application_no}</h1>
                <div class="complainno d-flex">
                    <div>Property ID</div>
                    <div id="uniqueId" class="ml-auto">${correction.uniqueId}</div>
                </div>
            </c:if>
            <c:if test="${!empty submitmsg}">
                <h1>${submitmsg}</h1>
            </c:if>

        </div>
        <div class="action_tools ml-auto">
            <a class="btn btn-perpal" onclick="openCorrectionForm('${correction.uniqueId}', '${correction.application_no}');">Open Correction Form</a>
            <a class="btn btn-danger" onclick="window.parent.closeCorrectionForm();" ><i class="material-icons">close</i>Close</a>
        </div>
    </div>

    <div class="detailFormContainer">
        <c:if test="${!empty correction}">
            <fieldset>
                <legend>Applicant Details</legend>
                <table class="table" style="width:100%">
                    <thead>
                        <tr>
                            
                            <th class="th-sm">Applicant Name</th>
                            <th class="th-sm">Applicant Contact</th>
                            <th class="th-sm">Applicant Email</th>
                            <th class="th-sm">Date</th>
                            <th class="th-sm">Counter Ref. ID</th>
                        </tr>
                    </thead>
                    <tbody class="mCustomScrollbar _mCS_1 mCS-autoHide" data-mcs-theme="minimal-dark" style="position: relative; overflow: visible;"><div id="mCSB_1" class="mCustomScrollBox mCS-minimal-dark mCSB_vertical mCSB_outside" style="max-height: none;" tabindex="0"><div id="mCSB_1_container" class="mCSB_container" style="position: relative; top: -43px; left: 0px;" dir="ltr">
                            <tr>
                                
                                <td class="td-sm">${correction.applicantName}</td>
                                <td class="td-sm">${correction.applicantMobileNo}</td>
                                <td class="td-sm">${correction.applicantEmail}</td>
                                <td class="td-sm">${correction.uploadDate}</td>
                                <td class="td-sm">${correction.offlineRefNo}</td>
                            </tr>
                        </div></div><div id="mCSB_1_scrollbar_vertical" class="mCSB_scrollTools mCSB_1_scrollbar mCS-minimal-dark mCSB_scrollTools_vertical" style="display: block;"><div class="mCSB_draggerContainer"><div id="mCSB_1_dragger_vertical" class="mCSB_dragger" style="position: absolute; min-height: 50px; display: block; height: 20px; max-height: 72px; top: 4px;"><div class="mCSB_dragger_bar" style="line-height: 50px;"></div></div><div class="mCSB_draggerRail"></div></div></div></tbody>
                </table>
            </fieldset>
            <fieldset>
                <legend>Attachments</legend>
                <div class="col-sm-6">
                    <ul class="attachmentListOnPage align-content-center">
                        <li onclick="showDocument('${correction.imageFile}', '${correction.imageName}');">Form - ${correction.imageName}   <i class="material-icons ml-auto">open_in_new</i></li>
                        <li onclick="showDocument('${correction.imageFile1}', '${correction.imageName1}');">Document 1 - ${correction.imageName1}   <i class="material-icons ml-auto">open_in_new</i></li>
                        <li onclick="showDocument('${correction.imageFile2}', '${correction.imageName2}');">Document 2 - ${correction.imageName2}   <i class="material-icons ml-auto">open_in_new</i></li>
                    </ul>
                </div>
                
            </fieldset>
        </c:if>

    </div>
</div>



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

    function openCorrectionForm(propId, complainNo) {
        window.open("offLineToOnLineData?propertyId=" + propId + "&complainNo=" + complainNo, "_blank");
    }


</script>