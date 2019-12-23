<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ page isELIgnored="false"%>
<script>

    $(document).ready(function () {
        $("#pg_solve a").addClass("active");
    });
</script>
<c:set var = "page" scope = "session" value = "p_solve"/>
<% int i=1;%>
<div class="mainpanel">
    <div class="dashboard-sec">

        <div class="row">
            <div class="col-lg-12">
                <div class="productCard">
                    <div class="row">
                        <div class="col-sm-12 ">
                            <div id="silvassaListing"  class="innerContainer">
                                <div class="headertitle d-flex">
                                    <div class="titleSection">
                                        <h1>Approved Cases</h1>                 
                                    </div>

                                </div>
                                <table class="table" style="width:100%">
                                    <thead>
                                        <tr>
                                            <th class="th-sm">Sr.no.</th>
                                            <th class="th-sm">Application ID</th>
                                            <th class="th-sm">Property ID</th>
                                            <th class="th-sm">Applicant Name</th>
                                            <th class="th-sm">Date</th>
                                            <th class="th-sm">Ward</th>
                                            <th class="th-sm">Status</th>
                                        </tr>
                                    </thead>
                                    <tbody class="mCustomScrollbar _mCS_1 mCS-autoHide" data-mcs-theme="minimal-dark" style="position: relative; overflow: visible;"><div id="mCSB_1" class="mCustomScrollBox mCS-minimal-dark mCSB_vertical mCSB_outside" style="max-height: none;" tabindex="0"><div id="mCSB_1_container" class="mCSB_container" style="position: relative; top: -43px; left: 0px;" dir="ltr">

                                            <c:forEach items="${correctiondata}" var = "correcItem" varStatus="loop" >
                                                <tr onclick="openCorrectionForm('correction_compare?propId=${correcItem.unique_id}&applicationNo=${correcItem.application_no}');">
                                                    <td class="td-sm"><%=i++%></td>
                                                    <td class="td-sm">${correcItem.application_no}</td>
                                                    <td class="td-sm">${correcItem.unique_id}</td>
                                                    <td class="td-sm">${correcItem.applicant_name}</td>
                                                    <td class="td-sm">${correcItem.notice_date}</td>
                                                    <td class="td-sm">${correcItem.ward_no}</td>
                                                    <c:if test="${correcItem.status eq null}">
                                                        <td class="td-sm">Inbox (Unread)</td>
                                                    </c:if>
                                                    <c:if test="${correcItem.status eq 'read'}">
                                                        <td class="td-sm">Inbox</td>
                                                    </c:if>
                                                    <c:if test="${correcItem.status eq 'reject'}">
                                                        <td class="td-sm">Rejected</td>
                                                    </c:if>
                                                    <c:if test="${correcItem.status eq 'approve'}">
                                                        <td class="td-sm">Approved</td>
                                                    </c:if>
                                                    <c:if test="${correcItem.status eq 'fieldverify'}">
                                                        <td class="td-sm">Field Verification</td>
                                                    </c:if>
                                                    <c:if test="${correcItem.status eq 'applied'}">
                                                        <td class="td-sm">Solved</td>
                                                    </c:if>

                                                </tr>
                                            </c:forEach>

                                        </div></div><div id="mCSB_1_scrollbar_vertical" class="mCSB_scrollTools mCSB_1_scrollbar mCS-minimal-dark mCSB_scrollTools_vertical" style="display: block;"><div class="mCSB_draggerContainer"><div id="mCSB_1_dragger_vertical" class="mCSB_dragger" style="position: absolute; min-height: 50px; display: block; height: 20px; max-height: 72px; top: 4px;"><div class="mCSB_dragger_bar" style="line-height: 50px;"></div></div><div class="mCSB_draggerRail"></div></div></div></tbody>
                                </table>
                            </div>
                            <div id="silvassaFormdata" class="innerContainer d-none">
                                <iframe border="0" src=""  class="innner_iframe" ></iframe>
                            </div>
                        </div>
                    </div>
                </div>
            </div>
        </div>
    </div>
</div>

<script>
    function openCorrectionForm(formLink) {
        $('#silvassaFormdata').removeClass('d-none');
        $('#silvassaListing').addClass('d-none');
        $('#silvassaFormdata iframe').attr("src", formLink);
    }

    function closeCorrectionForm() {
        $('#silvassaFormdata').addClass('d-none');
        $('#silvassaListing').removeClass('d-none');
        $('#silvassaFormdata iframe').attr("src", "");
    }

</script>