<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ page isELIgnored="false"%>
<script>

    $(document).ready(function () {
        $("#pg_offline a").addClass("active");
    });
</script>
<c:set var = "page" scope = "session" value = "p_all"/>
<% int i = 1;%>
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
                                        <h1>Offline Cases</h1>                 
                                    </div>
                                    <div class="action_tools ml-auto">
                                        <form method="get" action="correction_alloffline_list"> 
                                            <div class="filterDropdown d-flex align-content-center">
                                                <span style="vertical-align: middle; display: inline-block;line-height: 25px; font-weight: 600">Filter</span>

                                                <select name="actionFliter" class="form-control">
                                                    <option value="1" ${actionFliter eq 1 ? 'selected' : ''} >1 Month</option>
                                                    <option value="3" ${actionFliter eq 3 ? 'selected' : ''}>3 Months</option>
                                                    <option value="6" ${actionFliter eq 6 ? 'selected' : ''}>6 Months</option>
                                                    <option value="9" ${actionFliter eq 9 ? 'selected' : ''}>9 Months</option>
                                                    <option value="12" ${actionFliter eq 12 ? 'selected' : ''}>12 Months</option>
                                                </select>
                                                <input class="btn-dark" type="submit" value="Apply" />

                                            </div>
                                        </form>
                                    </div>
                                </div>
                                <table class="table" style="width:100%">
                                    <thead>
                                        <tr>
                                            <th class="th-sm">Sr.no.</th>
                                            <th class="th-sm">Application ID</th>
                                            <th class="th-sm">Property ID</th>
                                            <th class="th-sm">Applicant Name</th>
                                            <th class="th-sm">Applicant Contact</th>
                                            <th class="th-sm">Applicant Email</th>
                                            <th class="th-sm">Date</th>
                                            <!--<th class="th-sm">Counter Ref. ID</th>-->

                                        </tr>
                                    </thead>
                                    <tbody class="mCustomScrollbar _mCS_1 mCS-autoHide" data-mcs-theme="minimal-dark" style="position: relative; overflow: visible;"><div id="mCSB_1" class="mCustomScrollBox mCS-minimal-dark mCSB_vertical mCSB_outside" style="max-height: none;" tabindex="0"><div id="mCSB_1_container" class="mCSB_container" style="position: relative; top: -43px; left: 0px;" dir="ltr">

                                            <c:forEach items="${correctiondata}" var = "correcItem" varStatus="loop" >
                                                <tr onclick="openCorrectionForm('correction_compare_offline?propId=${correcItem.unique_id}&applicationNo=${correcItem.application_no}');$(this).remove();" >
                                                    <td class="td-sm"><%=i++%></td>
                                                    <td class="td-sm">${correcItem.application_no}</td>
                                                    <td class="td-sm">${correcItem.unique_id}</td>
                                                    <td class="td-sm">${correcItem.applicant_name}</td>
                                                    <td class="td-sm">${correcItem.apllicant_mobile}</td>
                                                    <td class="td-sm">${correcItem.applicant_email}</td>
                                                    <td class="td-sm">${correcItem.upload_date}</td>
                                                    <!--<td class="td-sm">${correcItem.offline_counter_ref_no}</td>-->

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