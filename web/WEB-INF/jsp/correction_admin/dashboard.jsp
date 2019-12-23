<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ page isELIgnored="false"%>

<script src="res/correction/js/circle-progress.js"></script>

<div class="mainpanel">
    <div class="dashboard-sec">
        <div class="row">
            <div class="col-lg-12">

                <div class="productCard">


                    <div class="headertitle_box d-flex justify-content-center ">
                        <div class="titleSection">
                            <h3 class="headingText ">Dashboard</h3>
                        </div>

                        <div class="action_tools ml-auto">
                            <form method="get" action="dashboard"> 
                                <div class="filterDropdown d-flex align-content-center">
                                    <span style="padding-right:10px;vertical-align: middle; display: inline-block;line-height: 35px; font-weight: 600">Filter</span>

                                    <select name="actionFliter" class="form-control" style="border-radius: 4px 0px 0px 4px;">
                                        <option value="1" ${actionFliter eq 1 ? 'selected' : ''} >1 Month</option>
                                        <option value="3" ${actionFliter eq 3 ? 'selected' : ''}>3 Months</option>
                                        <option value="6" ${actionFliter eq 6 ? 'selected' : ''}>6 Months</option>
                                        <option value="9" ${actionFliter eq 9 ? 'selected' : ''}>9 Months</option>
                                        <option value="12" ${actionFliter eq 12 ? 'selected' : ''}>12 Months</option>
                                    </select>
                                    <input class="btn-dark" type="submit" value="Apply" style="border-radius: 0px 4px 4px 0;">

                                </div>
                            </form>
                        </div>

                    </div>


                    <c:if test="${countdata ne null}">
                        <div class="row">
                            <div class="col-sm-12 col-md-6 col-lg-6 col-xl-4	">
                                <c:if test="${countdata.total != null}" >
                                    <div class="cardBlock">
                                        <h3 class="headingText">Total Complaints</h3>
                                        <div class="barSec pink">

                                            <div class="progress progress_1" data-percentage="${countdata.total.percntg}"> <strong> </strong> </div>
<!--                                            <div class="progress" data-percentage="${countdata.total.percntg}"> 
                                               <span class="progress-left"> <span class="progress-bar"></span> </span> 
                                               <span class="progress-right"> <span class="progress-bar"></span> </span>
                                               <div class="progress-value">
                                                   <div> ${countdata.total.percntg}% </div>
                                               </div>
                                           </div>-->
                                        </div>
                                        <div class="cardContent">
                                            <h3>${countdata.total.tcount}</h3>
                                            <h6>Complaints Received</h6>
                                        </div>
                                    </div>
                                </c:if>
                            </div>
                            <div class="col-sm-12 col-md-6 col-lg-6 col-xl-4">
                                <c:if test="${countdata.inbox ne null}" >
                                    <div class="cardBlock">
                                        <h3 class="headingText">Inbox</h3>
                                        <div class="barSec green">
                                            <div class="progress progress_2" data-percentage="${countdata.inbox.percntg}"> <strong> </strong> </div>
<!--                                            <div class="progress" data-percentage="${countdata.inbox.percntg}"> 
                                                <span class="progress-left"> <span class="progress-bar"></span>  </span> 
                                                <span class="progress-right"> <span class="progress-bar"></span> </span>
                                                <div class="progress-value">
                                                    <div> ${countdata.inbox.percntg}% </div>
                                                </div>
                                            </div>-->
                                        </div>
                                        <div class="cardContent">
                                            <h3>${countdata.inbox.tcount}</h3>
                                            <h6>Inbox Complaints</h6>
                                        </div>
                                    </div>
                                </c:if>
                            </div>
                            <div class="col-sm-12 col-md-6 col-lg-6 col-xl-4">
                                <c:if test="${countdata.overdue ne null}" >
                                    <div class="cardBlock">
                                        <h3 class="headingText">Unread</h3>
                                        <div class="barSec yellow">
                                            <div class="progress progress_3" data-percentage="${countdata.overdue.percntg}"> <strong> </strong> </div>
<!--                                            <div class="progress" data-percentage="${countdata.overdue.percntg}"> <span class="progress-left"> <span class="progress-bar"></span> </span> <span class="progress-right"> <span class="progress-bar"></span> </span>
                                                <div class="progress-value">
                                                    <div> ${countdata.overdue.percntg}% </div>
                                                </div>
                                            </div>-->
                                        </div>
                                        <div class="cardContent">
                                            <h3>${countdata.overdue.tcount}</h3>
                                            <h6>Unread Complaints</h6>
                                        </div>
                                    </div>
                                </c:if> 
                            </div>
                            <div class="col-sm-12 col-md-6 col-lg-6 col-xl-4">
                                <c:if test="${countdata.solve ne null}" >
                                    <div class="cardBlock">
                                        <h3 class="headingText">Solved</h3>
                                        <div class="barSec purple">
                                            <div class="progress progress_4" data-percentage="${countdata.solve.percntg}"> <strong> </strong> </div>
<!--                                            <div class="progress" data-percentage="${countdata.solve.percntg}"> <span class="progress-left"> <span class="progress-bar"></span> </span> <span class="progress-right"> <span class="progress-bar"></span> </span>
                                                <div class="progress-value">
                                                    <div> ${countdata.solve.percntg}% </div>
                                                </div>
                                            </div>-->
                                        </div>
                                        <div class="cardContent">
                                            <h3>${countdata.solve.tcount}</h3>
                                            <h6>Complaints Solved</h6>
                                        </div>
                                    </div>
                                </c:if>
                            </div>
                            <div class="col-sm-12 col-md-6 col-lg-6 col-xl-4">
                                <c:if test="${countdata.reject ne null}" >
                                    <div class="cardBlock">
                                        <h3 class="headingText">Rejected </h3>
                                        <div class="barSec purple">
                                            <div class="progress progress_5" data-percentage="${countdata.reject.percntg}"> <strong> </strong> </div>
<!--                                            <div class="progress" data-percentage="${countdata.reject.percntg}"> <span class="progress-left"> <span class="progress-bar"></span> </span> <span class="progress-right"> <span class="progress-bar"></span> </span>
                                                <div class="progress-value">
                                                    <div> ${countdata.reject.percntg}% </div>
                                                </div>
                                            </div>-->
                                        </div>
                                        <div class="cardContent">
                                            <h3>${countdata.reject.tcount}</h3>
                                            <h6>Rejected  Complaints</h6>
                                        </div>
                                    </div>
                                </c:if>
                            </div>
                            <div class="col-sm-12 col-md-6 col-lg-6 col-xl-4">
                                <c:if test="${countdata.fieldverify ne null}" >
                                    <div class="cardBlock">
                                        <h3 class="headingText">Field Verification </h3>
                                        <div class="barSec purple">
                                            <div class="progress progress_6" data-percentage="${countdata.fieldverify.percntg}"> <strong> </strong> </div>
<!--                                            <div class="progress" data-percentage="${countdata.fieldverify.percntg}"> <span class="progress-left"> <span class="progress-bar"></span> </span> <span class="progress-right"> <span class="progress-bar"></span> </span>
                                                <div class="progress-value">
                                                    <div> ${countdata.fieldverify.percntg}% </div>
                                                </div>
                                            </div>-->
                                        </div>
                                        <div class="cardContent">
                                            <h3>${countdata.fieldverify.tcount}</h3>
                                            <h6>Field Verification Complaints</h6>
                                        </div>
                                    </div>
                                </c:if>
                            </div>
                            
                            <div class="col-sm-12 col-md-6 col-lg-6 col-xl-4">
                                <c:if test="${countdata.approve ne null}" >
                                    <div class="cardBlock">
                                        <h3 class="headingText">Pending for Approval </h3>
                                        <div class="barSec purple">
                                            <div class="progress progress_7" data-percentage="${countdata.approve.percntg}"> <strong> </strong> </div>
                                        </div>
                                        <div class="cardContent">
                                            <h3>${countdata.approve.tcount}</h3>
                                            <h6>Approval Complaints</h6>
                                        </div>
                                    </div>
                                </c:if>
                            </div>
                            
                            
                            
                            <div class="col-lg-12">
                                <div class="innerContainer">
                                    <div class="headertitle d-flex">
                                        <div class="titleSection">
                                            <h1>Summary</h1>                 
                                        </div>
                                    </div>
                                    <table class="table table-bordered table-sm dataTable no-footer" style="width:100%">
                                        <thead>
                                            <tr>
                                                <th class="th-sm">Ward</th>
                                                <th class="th-sm">Total</th>
                                                <th class="th-sm">Pending for Approval</th>
                                                <th class="th-sm">Solved</th>
                                                <th class="th-sm">Rejected</th>
                                            </tr>
                                        </thead>
                                        <tbody class="mCustomScrollbar _mCS_1 mCS-autoHide" data-mcs-theme="minimal-dark" style="position: relative; overflow: visible;"><div id="mCSB_1" class="mCustomScrollBox mCS-minimal-dark mCSB_vertical mCSB_outside" style="max-height: none;" tabindex="0"><div id="mCSB_1_container" class="mCSB_container" style="position: relative; top: -43px; left: 0px;" dir="ltr">

                                                <c:forEach items="${countdataward}" var = "correcItemWard" varStatus="loop" >
                                                    <tr>
                                                        <td class="td-sm">${correcItemWard.key}</td>
                                                        <td class="td-sm">${empty correcItemWard.value.total? 0 : correcItemWard.value.total}</td>
                                                        <td class="td-sm">${empty correcItemWard.value.approve? 0 : correcItemWard.value.approve}</td>
                                                        <td class="td-sm">${empty correcItemWard.value.solve? 0 : correcItemWard.value.solve}</td>
                                                        <td class="td-sm">${empty correcItemWard.value.reject? 0 : correcItemWard.value.reject}</td> 
                                                    </tr>
                                                </c:forEach>

                                            </div></div><div id="mCSB_1_scrollbar_vertical" class="mCSB_scrollTools mCSB_1_scrollbar mCS-minimal-dark mCSB_scrollTools_vertical" style="display: block;"><div class="mCSB_draggerContainer"><div id="mCSB_1_dragger_vertical" class="mCSB_dragger" style="position: absolute; min-height: 50px; display: block; height: 20px; max-height: 72px; top: 4px;"><div class="mCSB_dragger_bar" style="line-height: 50px;"></div></div><div class="mCSB_draggerRail"></div></div></div></tbody>
                                    </table>
                                </div>
                            </div>
                        </div>
                    </div>
                </c:if>
                <c:if test="${countdata eq null}">
                    <div>Unable to fetch details.</div>
                </c:if>

            </div>
        </div>
    </div>
</div>

<script>


    $('.progress_1').circleProgress({
        value: $('.progress_1').attr('data-percentage') / 100, fill: {gradient: ['#ff4b68']}, startAngle: -Math.PI / 6 * 3,
    }).on('circle-animation-progress', function (event, progress) {
        $(this).find('strong').html(Math.round($('.progress_1').attr('data-percentage') * progress) + '<i>%</i>');
    });

    var data2 = $('.progress_2').attr('data-percentage');
    $('.progress_2').circleProgress({

        value: $('.progress_2').attr('data-percentage') / 100, fill: {gradient: ['#0aa58a']}, startAngle: -Math.PI / 6 * 3,
    }).on('circle-animation-progress', function (event, progress) {
        $(this).find('strong').html(Math.round($('.progress_2').attr('data-percentage') * progress) + '<i>%</i>');
    });


    $('.progress_3').circleProgress({
        value: $('.progress_3').attr('data-percentage') / 100, fill: {gradient: ['#5673bd']}, startAngle: -Math.PI / 6 * 3,
    }).on('circle-animation-progress', function (event, progress) {
        $(this).find('strong').html(Math.round($('.progress_3').attr('data-percentage') * progress) + '<i>%</i>');
    });

    $('.progress_4').circleProgress({
        value: $('.progress_4').attr('data-percentage') / 100, fill: {gradient: ['#2dcc70']}, startAngle: -Math.PI / 6 * 3,
    }).on('circle-animation-progress', function (event, progress) {
        $(this).find('strong').html(Math.round($('.progress_4').attr('data-percentage') * progress) + '<i>%</i>');
    });
    $('.progress_5').circleProgress({
        value: $('.progress_5').attr('data-percentage') / 100, fill: {gradient: ['#9b58b5']}, startAngle: -Math.PI / 6 * 3,
    }).on('circle-animation-progress', function (event, progress) {
        $(this).find('strong').html(Math.round($('.progress_5').attr('data-percentage') * progress) + '<i>%</i>');
    });
    $('.progress_6').circleProgress({
        value: $('.progress_6').attr('data-percentage') / 100, fill: {gradient: ['#118df0']}, startAngle: -Math.PI / 6 * 3,
    }).on('circle-animation-progress', function (event, progress) {
        $(this).find('strong').html(Math.round($('.progress_6').attr('data-percentage') * progress) + '<i>%</i>');
    });
    $('.progress_7').circleProgress({
        value: $('.progress_7').attr('data-percentage') / 100, fill: {gradient: ['#118df0']}, startAngle: -Math.PI / 6 * 3,
    }).on('circle-animation-progress', function (event, progress) {
        $(this).find('strong').html(Math.round($('.progress_7').attr('data-percentage') * progress) + '<i>%</i>');
    });

    $(document).ready(function () {
        $("#pg_dashboards a").addClass("active");
    });

</script>