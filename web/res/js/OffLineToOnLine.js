
//$(document).on("keyup", "body", function (event) {
//    if (event.keyCode === 13) {
//        $("#id_of_button").click();
//    }
//});

//$('body').keypress(function (e) {
// var key = e.which;
// if(key == 13)  // the enter key code
//  {
//   // test.loadCorrectionForm('uniqueIdTC');
//    //return false;  
//  }
//});   

$(document).ready(function() {
    OfflineToOnline.uniqueProperty($("#propertyId").val(), $("#complainNo").val());
    $('#myform').on('submit', function(e){
        //e.preventDefault();
        var sr=test.correctionFormvalidation();
        //console.log(sr)
        if(sr){
            this.submit();
        }else{
            e.preventDefault();
            //console.log("hg");
        }
        
    });
});
var OfflineToOnline = {
    uniqueProperty: function (uniqueId, complainNo) {
        if(currentData) {
                var result = JSON.parse(currentData);
                $('#uniqueId').val(result[0].uniqueId);
                $('#wardNo').val(result[0].wardNo);
                $('#noticeNo').val(result[0].privateNoticeNo);
                
                $('#ownerName').val(result[0].ownerName);
                $('#ownerFather').val(result[0].ownerFather);
                $('#spouseName').val(result[0].spouseName);
                $('#ownerContact').val(result[0].ownerContact);
                $('#ownerEmail').val(result[0].ownerEmail);
                $('#ownerAadharNo').val(result[0].ownerAadharNo);
                $('#occupierName').val(result[0].occupierName);
                $('#occupierFather').val(result[0].occupierFather);
                $('#occupierContact').val(result[0].occupierContact);
                $('#occupierEmail').val(result[0].occupierEmail);
                $('#occupierAadharNo').val(result[0].occupierAadharNo);
                $('#occupierSex').val(result[0].occupierSex);
                
//                if(result[0].ownerName !=''){
//                    if (result[0].occupierName == ""){
//                      $('#occupierName').val(result[0].ownerName); 
//                      $("#checkOccupierName").prop("checked",true);
//                      $("#checkOccupierName").prop("disabled",false);
//                    }
//                }
//                if(result[0].ownerFather !=''){
//                    if (result[0].occupierFather == ""){
//                      $('#occupierFather').val(result[0].ownerFather); 
//                      $("#checkOccupierFather").prop("checked",true);
//                      $("#checkOccupierFather").prop("disabled",false);
//                    }
//                }
//                
//                
//                 if(result[0].ownerContact !=''){
//                    if (result[0].occupierContact == ""){
//                      $("#occupierContact").val(result[0].ownerContact); 
//                      $("#checkOccupierContact").prop("checked",true);
//                      $("#checkOccupierContact").prop("disabled",true);
//                    }
//                }
//                if(result[0].ownerEmail !=''){
//                    if (result[0].occupierEmail == ""){
//                      $('#occupierEmail').val(result[0].ownerEmail); 
//                      $("#checkOccupierEmail").prop("checked",true);
//                    }
//                }
//                if(result[0].ownerAadharNo !=''){
//                    if (result[0].occupierEmail == ""){
//                      $('#occupierAadharNo').val(result[0].ownerAadharNo); 
//                      $("#checkOccupierAadharNo").prop("checked",true);
//                    }
//                }
//                
//                if (result[0].occupierName != ""){
//                    $('#occupierName').val(result[0].occupierName);
//                    $('#occupierFather').val(result[0].occupierFather);
//                    $('#occupierContact').val(result[0].occupierContact);
//                    $('#occupierEmail').val(result[0].occupierEmail);
//                    $('#occupierAadharNo').val(result[0].occupierAadharNo);
//                    $("#checkOccupierName").prop("checked",false);
//                    $("#checkOccupierFather").prop("checked",false);
//                    $("#checkOccupierContact").prop("checked",false);
//                    $("#checkOccupierEmail").prop("checked",false);
//                    $("#checkOccupierAadharNo").prop("checked",false);
//                }
                
                
//                if (result[0].occupierName == "") {
//                    $('#occupierName').val(result[0].ownerName);
//                    $('#occupierFather').val(result[0].ownerFather);
//                    $('#occupierContact').val(result[0].ownerContact);
//                    $('#occupierEmail').val(result[0].ownerEmail);
//                    $('#occupierAadharNo').val(result[0].ownerAadharNo);
//                    $("#checkOccupierName").prop("checked",true);
//                    $("#checkOccupierSex").prop("checked",true);
//                    $("#checkOccupierFather").prop("checked",true);
//                    $("#checkOccupierContact").prop("checked",true);
//                    $("#checkOccupierEmail").prop("checked",true);
//                    $("#checkOccupierAadharNo").prop("checked",true);
//                } else {
//                    $('#occupierName').val(result[0].occupierName);
//                    $('#occupierFather').val(result[0].occupierFather);
//                    $('#occupierContact').val(result[0].occupierContact);
//                    $('#occupierEmail').val(result[0].occupierEmail);
//                    $('#occupierAadharNo').val(result[0].occupierAadharNo);
//
//                }

                $('#address').val(result[0].address);
                $('#houseNo').val(result[0].houeNo);
                $('#buildingName').val(result[0].buildingName);
                $('#locName').val(result[0].locName);
                $('#plotNo').val(result[0].plotNo);
                $('#roadName').val(result[0].roadName);

                $('#subLocality').val(result[0].subLocality);
                $('#landMark').val(result[0].landMark);
                $('#city').val(result[0].city);
                $('#pincode').val(result[0].pincode);

                $('#locationClass').val(result[0].locationClass);
                $('#electricSericeConnectionNo').val(result[0].electricServiceConNo);
                $('#surveyNo').val(result[0].surveyNo);
                $('#plotSmc').val(result[0].plotSmc);
                $('#arrearAmount').val(result[0].arrearAmount);
                $('#smcProperty').val(result[0].smcProperty);
                $('#ownerSex').val(result[0].ownerSex);
                if (result[0].ownerSex != 'null' && result[0].ownerSex != "")
                    $('#ownerSex').val(result[0].ownerSex);
                else
                    $('#ownerSex').val("-1");
                
                //console.log("jhdrtf="+result[0].occupierSex)
                if (result[0].occupierSex != 'null' && result[0].occupierSex != "")
                    $('#occupierSex').val(result[0].occupierSex);
                else
                    $('#occupierSex').val("-1");
                
                $('#propertyOwnerAddress').val(result[0].propertyOwnerAddress);




                //$("#correct_floor tbody").append("");
                test.floorn = 0;
                $("#correct_floor tbody").html("");
                for (var r in result) {
                    var propDetail = result[r];
                    //var tr_html="";
                    test.floorn = r;
                    var tr_html = "<tr><td><select style='min-width:80px' class='form-control' id='floorDetails" + r + ".floorType' name='floorDetails[" + r + "].floorType'  value='" + propDetail.floorType + "'  readonly='readonly'  >";

                    if (propDetail.floorType === 'GF') {
                        tr_html = tr_html + "<option value='-1'>Select</option>";
                        tr_html = tr_html + "<option value='GF' selected='selected'>GF</option>";
                        tr_html = tr_html + "<option value='1F'>1F</option>";
                        tr_html = tr_html + "<option value='2F'>2F</option>";
                        tr_html = tr_html + "<option value='3F'>3F</option>";
                        tr_html = tr_html + "<option value='4F'>4F</option>";
                        tr_html = tr_html + "<option value='5F'>5F</option>";
                        tr_html = tr_html + "<option value='6F'>6F</option>";
                        tr_html = tr_html + "<option value='7F'>7F</option>";
                        tr_html = tr_html + "<option value='8F'>8F</option>";
                        tr_html = tr_html + "<option value='9F'>9F</option>";
                        tr_html = tr_html + "<option value='10F'>10F</option>";
                        tr_html = tr_html + "<option value='11F'>11F</option>";
                        tr_html = tr_html + "<option value='12F'>12F</option>";
                        tr_html = tr_html + "<option value='13F'>13F</option>";
                        tr_html = tr_html + "<option value='14F'>14F</option>";
                        tr_html = tr_html + "<option value='15F'>15F</option>";
                        tr_html = tr_html + "<option value='UG'>UG</option>";
                        tr_html = tr_html + "<option value='VP'>VP</option>";
                        tr_html = tr_html + "<option value='UPG'>UPG</option>";
                        tr_html = tr_html + "<option value='UG1'>UG1</option>";
                        tr_html = tr_html + "</select></td>";
                    }

                    else if (propDetail.floorType === '1F') {
                        tr_html = tr_html + "<option value='-1'>Select</option>";
                        tr_html = tr_html + "<option value='GF' >GF</option>";
                        tr_html = tr_html + "<option value='1F' selected='selected'>1F</option>";
                        tr_html = tr_html + "<option value='2F'>2F</option>";
                        tr_html = tr_html + "<option value='3F'>3F</option>";
                        tr_html = tr_html + "<option value='4F'>4F</option>";
                        tr_html = tr_html + "<option value='5F'>5F</option>";
                        tr_html = tr_html + "<option value='6F'>6F</option>";
                        tr_html = tr_html + "<option value='7F'>7F</option>";
                        tr_html = tr_html + "<option value='8F'>8F</option>";
                        tr_html = tr_html + "<option value='9F'>9F</option>";
                        tr_html = tr_html + "<option value='10F'>10F</option>";
                        tr_html = tr_html + "<option value='11F'>11F</option>";
                        tr_html = tr_html + "<option value='12F'>12F</option>";
                        tr_html = tr_html + "<option value='13F'>13F</option>";
                        tr_html = tr_html + "<option value='14F'>14F</option>";
                        tr_html = tr_html + "<option value='15F'>15F</option>";
                        tr_html = tr_html + "<option value='UG'>UG</option>";
                        tr_html = tr_html + "<option value='VP'>VP</option>";
                        tr_html = tr_html + "<option value='UPG'>UPG</option>";
                        tr_html = tr_html + "<option value='UG1'>UG1</option>";
                        tr_html = tr_html + "</select></td>";
                    } else if (propDetail.floorType === '2F') {
                        tr_html = tr_html + "<option value='-1'>Select</option>";
                        tr_html = tr_html + "<option value='GF' >GF</option>";
                        tr_html = tr_html + "<option value='1F' >1F</option>";
                        tr_html = tr_html + "<option value='2F' selected='selected'>2F</option>";
                        tr_html = tr_html + "<option value='3F'>3F</option>";
                        tr_html = tr_html + "<option value='4F'>4F</option>";
                        tr_html = tr_html + "<option value='5F'>5F</option>";
                        tr_html = tr_html + "<option value='6F'>6F</option>";
                        tr_html = tr_html + "<option value='7F'>7F</option>";
                        tr_html = tr_html + "<option value='8F'>8F</option>";
                        tr_html = tr_html + "<option value='9F'>9F</option>";
                        tr_html = tr_html + "<option value='10F'>10F</option>";
                        tr_html = tr_html + "<option value='11F'>11F</option>";
                        tr_html = tr_html + "<option value='12F'>12F</option>";
                        tr_html = tr_html + "<option value='13F'>13F</option>";
                        tr_html = tr_html + "<option value='14F'>14F</option>";
                        tr_html = tr_html + "<option value='15F'>15F</option>";
                        tr_html = tr_html + "<option value='UG'>UG</option>";
                        tr_html = tr_html + "<option value='VP'>VP</option>";
                        tr_html = tr_html + "<option value='UPG'>UPG</option>";
                        tr_html = tr_html + "<option value='UG1'>UG1</option>";
                        tr_html = tr_html + "</select></td>";
                    } else if (propDetail.floorType === '3F') {
                        tr_html = tr_html + "<option value='-1'>Select</option>";
                        tr_html = tr_html + "<option value='GF' >GF</option>";
                        tr_html = tr_html + "<option value='1F' >1F</option>";
                        tr_html = tr_html + "<option value='2F' >2F</option>";
                        tr_html = tr_html + "<option value='3F' selected='selected'>3F</option>";
                        tr_html = tr_html + "<option value='4F'>4F</option>";
                        tr_html = tr_html + "<option value='5F'>5F</option>";
                        tr_html = tr_html + "<option value='6F'>6F</option>";
                        tr_html = tr_html + "<option value='7F'>7F</option>";
                        tr_html = tr_html + "<option value='8F'>8F</option>";
                        tr_html = tr_html + "<option value='9F'>9F</option>";
                        tr_html = tr_html + "<option value='10F'>10F</option>";
                        tr_html = tr_html + "<option value='11F'>11F</option>";
                        tr_html = tr_html + "<option value='12F'>12F</option>";
                        tr_html = tr_html + "<option value='13F'>13F</option>";
                        tr_html = tr_html + "<option value='14F'>14F</option>";
                        tr_html = tr_html + "<option value='15F'>15F</option>";
                        tr_html = tr_html + "<option value='UG'>UG</option>";
                        tr_html = tr_html + "<option value='VP'>VP</option>";
                        tr_html = tr_html + "<option value='UPG'>UPG</option>";
                        tr_html = tr_html + "<option value='UG1'>UG1</option>";
                        tr_html = tr_html + "</select></td>";
                    } else if (propDetail.floorType === '4F') {
                        tr_html = tr_html + "<option value='-1'>Select</option>";
                        tr_html = tr_html + "<option value='GF' >GF</option>";
                        tr_html = tr_html + "<option value='1F' >1F</option>";
                        tr_html = tr_html + "<option value='2F' >2F</option>";
                        tr_html = tr_html + "<option value='3F' >3F</option>";
                        tr_html = tr_html + "<option value='4F' selected='selected'>4F</option>";
                        tr_html = tr_html + "<option value='5F'>5F</option>";
                        tr_html = tr_html + "<option value='6F'>6F</option>";
                        tr_html = tr_html + "<option value='7F'>7F</option>";
                        tr_html = tr_html + "<option value='8F'>8F</option>";
                        tr_html = tr_html + "<option value='9F'>9F</option>";
                        tr_html = tr_html + "<option value='10F'>10F</option>";
                        tr_html = tr_html + "<option value='11F'>11F</option>";
                        tr_html = tr_html + "<option value='12F'>12F</option>";
                        tr_html = tr_html + "<option value='13F'>13F</option>";
                        tr_html = tr_html + "<option value='14F'>14F</option>";
                        tr_html = tr_html + "<option value='15F'>15F</option>";
                        tr_html = tr_html + "<option value='UG'>UG</option>";
                        tr_html = tr_html + "<option value='VP'>VP</option>";
                        tr_html = tr_html + "<option value='UPG'>UPG</option>";
                        tr_html = tr_html + "<option value='UG1'>UG1</option>";
                        tr_html = tr_html + "</select></td>";
                    } else if (propDetail.floorType === '5F') {
                        tr_html = tr_html + "<option value='-1'>Select</option>";
                        tr_html = tr_html + "<option value='GF' >GF</option>";
                        tr_html = tr_html + "<option value='1F' >1F</option>";
                        tr_html = tr_html + "<option value='2F' >2F</option>";
                        tr_html = tr_html + "<option value='3F' >3F</option>";
                        tr_html = tr_html + "<option value='4F' >4F</option>";
                        tr_html = tr_html + "<option value='5F' selected='selected'>5F</option>";
                        tr_html = tr_html + "<option value='6F'>6F</option>";
                        tr_html = tr_html + "<option value='7F'>7F</option>";
                        tr_html = tr_html + "<option value='8F'>8F</option>";
                        tr_html = tr_html + "<option value='9F'>9F</option>";
                        tr_html = tr_html + "<option value='10F'>10F</option>";
                        tr_html = tr_html + "<option value='11F'>11F</option>";
                        tr_html = tr_html + "<option value='12F'>12F</option>";
                        tr_html = tr_html + "<option value='13F'>13F</option>";
                        tr_html = tr_html + "<option value='14F'>14F</option>";
                        tr_html = tr_html + "<option value='15F'>15F</option>";
                        tr_html = tr_html + "<option value='UG'>UG</option>";
                        tr_html = tr_html + "<option value='VP'>VP</option>";
                        tr_html = tr_html + "<option value='UPG'>UPG</option>";
                        tr_html = tr_html + "<option value='UG1'>UG1</option>";
                        tr_html = tr_html + "</select></td>";
                    } else if (propDetail.floorType === '6F') {
                        tr_html = tr_html + "<option value='-1'>Select</option>";
                        tr_html = tr_html + "<option value='GF' >GF</option>";
                        tr_html = tr_html + "<option value='1F' >1F</option>";
                        tr_html = tr_html + "<option value='2F' >2F</option>";
                        tr_html = tr_html + "<option value='3F' >3F</option>";
                        tr_html = tr_html + "<option value='4F' >4F</option>";
                        tr_html = tr_html + "<option value='5F' >5F</option>";
                        tr_html = tr_html + "<option value='6F' selected='selected' >6F</option>";
                        tr_html = tr_html + "<option value='7F'>7F</option>";
                        tr_html = tr_html + "<option value='8F'>8F</option>";
                        tr_html = tr_html + "<option value='9F'>9F</option>";
                        tr_html = tr_html + "<option value='10F'>10F</option>";
                        tr_html = tr_html + "<option value='11F'>11F</option>";
                        tr_html = tr_html + "<option value='12F'>12F</option>";
                        tr_html = tr_html + "<option value='13F'>13F</option>";
                        tr_html = tr_html + "<option value='14F'>14F</option>";
                        tr_html = tr_html + "<option value='15F'>15F</option>";
                        tr_html = tr_html + "<option value='UG'>UG</option>";
                        tr_html = tr_html + "<option value='VP'>VP</option>";
                        tr_html = tr_html + "<option value='UPG'>UPG</option>";
                        tr_html = tr_html + "<option value='UG1'>UG1</option>";
                        tr_html = tr_html + "</select></td>";
                    } else if (propDetail.floorType === '7F') {
                        tr_html = tr_html + "<option value='-1'>Select</option>";
                        tr_html = tr_html + "<option value='GF' >GF</option>";
                        tr_html = tr_html + "<option value='1F' >1F</option>";
                        tr_html = tr_html + "<option value='2F' >2F</option>";
                        tr_html = tr_html + "<option value='3F' >3F</option>";
                        tr_html = tr_html + "<option value='4F' >4F</option>";
                        tr_html = tr_html + "<option value='5F' >5F</option>";
                        tr_html = tr_html + "<option value='6F'  >6F</option>";
                        tr_html = tr_html + "<option value='7F' selected='selected'>7F</option>";
                        tr_html = tr_html + "<option value='8F'>8F</option>";
                        tr_html = tr_html + "<option value='9F'>9F</option>";
                        tr_html = tr_html + "<option value='10F'>10F</option>";
                        tr_html = tr_html + "<option value='11F'>11F</option>";
                        tr_html = tr_html + "<option value='12F'>12F</option>";
                        tr_html = tr_html + "<option value='13F'>13F</option>";
                        tr_html = tr_html + "<option value='14F'>14F</option>";
                        tr_html = tr_html + "<option value='15F'>15F</option>";
                        tr_html = tr_html + "<option value='UG'>UG</option>";
                        tr_html = tr_html + "<option value='VP'>VP</option>";
                        tr_html = tr_html + "<option value='UPG'>UPG</option>";
                        tr_html = tr_html + "<option value='UG1'>UG1</option>";
                        tr_html = tr_html + "</select></td>";
                    } else if (propDetail.floorType === '8F') {
                        tr_html = tr_html + "<option value='-1'>Select</option>";
                        tr_html = tr_html + "<option value='GF' >GF</option>";
                        tr_html = tr_html + "<option value='1F' >1F</option>";
                        tr_html = tr_html + "<option value='2F' >2F</option>";
                        tr_html = tr_html + "<option value='3F' >3F</option>";
                        tr_html = tr_html + "<option value='4F' >4F</option>";
                        tr_html = tr_html + "<option value='5F' >5F</option>";
                        tr_html = tr_html + "<option value='6F'  >6F</option>";
                        tr_html = tr_html + "<option value='7F' >7F</option>";
                        tr_html = tr_html + "<option value='8F' selected='selected'>8F</option>";
                        tr_html = tr_html + "<option value='9F'>9F</option>";
                        tr_html = tr_html + "<option value='10F'>10F</option>";
                        tr_html = tr_html + "<option value='11F'>11F</option>";
                        tr_html = tr_html + "<option value='12F'>12F</option>";
                        tr_html = tr_html + "<option value='13F'>13F</option>";
                        tr_html = tr_html + "<option value='14F'>14F</option>";
                        tr_html = tr_html + "<option value='15F'>15F</option>";
                        tr_html = tr_html + "<option value='UG'>UG</option>";
                        tr_html = tr_html + "<option value='VP'>VP</option>";
                        tr_html = tr_html + "<option value='UPG'>UPG</option>";
                        tr_html = tr_html + "<option value='UG1'>UG1</option>";
                        tr_html = tr_html + "</select></td>";
                    } else if (propDetail.floorType === '9F') {
                        tr_html = tr_html + "<option value='-1'>Select</option>";
                        tr_html = tr_html + "<option value='GF' >GF</option>";
                        tr_html = tr_html + "<option value='1F' >1F</option>";
                        tr_html = tr_html + "<option value='2F' >2F</option>";
                        tr_html = tr_html + "<option value='3F' >3F</option>";
                        tr_html = tr_html + "<option value='4F' >4F</option>";
                        tr_html = tr_html + "<option value='5F' >5F</option>";
                        tr_html = tr_html + "<option value='6F'  >6F</option>";
                        tr_html = tr_html + "<option value='7F' >7F</option>";
                        tr_html = tr_html + "<option value='8F' >8F</option>";
                        tr_html = tr_html + "<option value='9F' selected='selected'>9F</option>";
                        tr_html = tr_html + "<option value='10F'>10F</option>";
                        tr_html = tr_html + "<option value='11F'>11F</option>";
                        tr_html = tr_html + "<option value='12F'>12F</option>";
                        tr_html = tr_html + "<option value='13F'>13F</option>";
                        tr_html = tr_html + "<option value='14F'>14F</option>";
                        tr_html = tr_html + "<option value='15F'>15F</option>";
                        tr_html = tr_html + "<option value='UG'>UG</option>";
                        tr_html = tr_html + "<option value='VP'>VP</option>";
                        tr_html = tr_html + "<option value='UPG'>UPG</option>";
                        tr_html = tr_html + "<option value='UG1'>UG1</option>";
                        tr_html = tr_html + "</select></td>";
                    } else if (propDetail.floorType === '10F') {
                        tr_html = tr_html + "<option value='-1'>Select</option>";
                        tr_html = tr_html + "<option value='GF' >GF</option>";
                        tr_html = tr_html + "<option value='1F' >1F</option>";
                        tr_html = tr_html + "<option value='2F' >2F</option>";
                        tr_html = tr_html + "<option value='3F' >3F</option>";
                        tr_html = tr_html + "<option value='4F' >4F</option>";
                        tr_html = tr_html + "<option value='5F' >5F</option>";
                        tr_html = tr_html + "<option value='6F'  >6F</option>";
                        tr_html = tr_html + "<option value='7F' >7F</option>";
                        tr_html = tr_html + "<option value='8F' >8F</option>";
                        tr_html = tr_html + "<option value='9F' >9F</option>";
                        tr_html = tr_html + "<option value='10F' selected='selected'>10F</option>";
                        tr_html = tr_html + "<option value='11F'>11F</option>";
                        tr_html = tr_html + "<option value='12F'>12F</option>";
                        tr_html = tr_html + "<option value='13F'>13F</option>";
                        tr_html = tr_html + "<option value='14F'>14F</option>";
                        tr_html = tr_html + "<option value='15F'>15F</option>";
                        tr_html = tr_html + "<option value='UG'>UG</option>";
                        tr_html = tr_html + "<option value='VP'>VP</option>";
                        tr_html = tr_html + "<option value='UPG'>UPG</option>";
                        tr_html = tr_html + "<option value='UG1'>UG1</option>";
                        tr_html = tr_html + "</select></td>";
                    } else if (propDetail.floorType === '11F') {
                        tr_html = tr_html + "<option value='-1'>Select</option>";
                        tr_html = tr_html + "<option value='GF' >GF</option>";
                        tr_html = tr_html + "<option value='1F' >1F</option>";
                        tr_html = tr_html + "<option value='2F' >2F</option>";
                        tr_html = tr_html + "<option value='3F' >3F</option>";
                        tr_html = tr_html + "<option value='4F' >4F</option>";
                        tr_html = tr_html + "<option value='5F' >5F</option>";
                        tr_html = tr_html + "<option value='6F'  >6F</option>";
                        tr_html = tr_html + "<option value='7F' >7F</option>";
                        tr_html = tr_html + "<option value='8F' >8F</option>";
                        tr_html = tr_html + "<option value='9F' >9F</option>";
                        tr_html = tr_html + "<option value='10F' >10F</option>";
                        tr_html = tr_html + "<option value='11F' selected='selected'>11F</option>";
                        tr_html = tr_html + "<option value='12F'>12F</option>";
                        tr_html = tr_html + "<option value='13F'>13F</option>";
                        tr_html = tr_html + "<option value='14F'>14F</option>";
                        tr_html = tr_html + "<option value='15F'>15F</option>";
                        tr_html = tr_html + "<option value='UG'>UG</option>";
                        tr_html = tr_html + "<option value='VP'>VP</option>";
                        tr_html = tr_html + "<option value='UPG'>UPG</option>";
                        tr_html = tr_html + "<option value='UG1'>UG1</option>";
                        tr_html = tr_html + "</select></td>";
                    } else if (propDetail.floorType === '12F') {
                        tr_html = tr_html + "<option value='-1'>Select</option>";
                        tr_html = tr_html + "<option value='GF' >GF</option>";
                        tr_html = tr_html + "<option value='1F' >1F</option>";
                        tr_html = tr_html + "<option value='2F' >2F</option>";
                        tr_html = tr_html + "<option value='3F' >3F</option>";
                        tr_html = tr_html + "<option value='4F' >4F</option>";
                        tr_html = tr_html + "<option value='5F' >5F</option>";
                        tr_html = tr_html + "<option value='6F'  >6F</option>";
                        tr_html = tr_html + "<option value='7F' >7F</option>";
                        tr_html = tr_html + "<option value='8F' >8F</option>";
                        tr_html = tr_html + "<option value='9F' >9F</option>";
                        tr_html = tr_html + "<option value='10F' >10F</option>";
                        tr_html = tr_html + "<option value='11F' >11F</option>";
                        tr_html = tr_html + "<option value='12F' selected='selected'>12F</option>";
                        tr_html = tr_html + "<option value='13F'>13F</option>";
                        tr_html = tr_html + "<option value='14F'>14F</option>";
                        tr_html = tr_html + "<option value='15F'>15F</option>";
                        tr_html = tr_html + "<option value='UG'>UG</option>";
                        tr_html = tr_html + "<option value='VP'>VP</option>";
                        tr_html = tr_html + "<option value='UPG'>UPG</option>";
                        tr_html = tr_html + "<option value='UG1'>UG1</option>";
                        tr_html = tr_html + "</select></td>";
                    } else if (propDetail.floorType === '13F') {
                        tr_html = tr_html + "<option value='-1'>Select</option>";
                        tr_html = tr_html + "<option value='GF' >GF</option>";
                        tr_html = tr_html + "<option value='1F' >1F</option>";
                        tr_html = tr_html + "<option value='2F' >2F</option>";
                        tr_html = tr_html + "<option value='3F' >3F</option>";
                        tr_html = tr_html + "<option value='4F' >4F</option>";
                        tr_html = tr_html + "<option value='5F' >5F</option>";
                        tr_html = tr_html + "<option value='6F'  >6F</option>";
                        tr_html = tr_html + "<option value='7F' >7F</option>";
                        tr_html = tr_html + "<option value='8F' >8F</option>";
                        tr_html = tr_html + "<option value='9F' >9F</option>";
                        tr_html = tr_html + "<option value='10F' >10F</option>";
                        tr_html = tr_html + "<option value='11F' >11F</option>";
                        tr_html = tr_html + "<option value='12F' >12F</option>";
                        tr_html = tr_html + "<option value='13F' selected='selected'>13F</option>";
                        tr_html = tr_html + "<option value='14F'>14F</option>";
                        tr_html = tr_html + "<option value='15F'>15F</option>";
                        tr_html = tr_html + "<option value='UG'>UG</option>";
                        tr_html = tr_html + "<option value='VP'>VP</option>";
                        tr_html = tr_html + "<option value='UPG'>UPG</option>";
                        tr_html = tr_html + "<option value='UG1'>UG1</option>";
                        tr_html = tr_html + "</select></td>";
                    } else if (propDetail.floorType === '14F') {
                        tr_html = tr_html + "<option value='-1'>Select</option>";
                        tr_html = tr_html + "<option value='GF' >GF</option>";
                        tr_html = tr_html + "<option value='1F' >1F</option>";
                        tr_html = tr_html + "<option value='2F' >2F</option>";
                        tr_html = tr_html + "<option value='3F' >3F</option>";
                        tr_html = tr_html + "<option value='4F' >4F</option>";
                        tr_html = tr_html + "<option value='5F' >5F</option>";
                        tr_html = tr_html + "<option value='6F'  >6F</option>";
                        tr_html = tr_html + "<option value='7F' >7F</option>";
                        tr_html = tr_html + "<option value='8F' >8F</option>";
                        tr_html = tr_html + "<option value='9F' >9F</option>";
                        tr_html = tr_html + "<option value='10F' >10F</option>";
                        tr_html = tr_html + "<option value='11F' >11F</option>";
                        tr_html = tr_html + "<option value='12F' >12F</option>";
                        tr_html = tr_html + "<option value='13F' >13F</option>";
                        tr_html = tr_html + "<option value='14F' selected='selected'>14F</option>";
                        tr_html = tr_html + "<option value='15F'>15F</option>";
                        tr_html = tr_html + "<option value='UG'>UG</option>";
                        tr_html = tr_html + "<option value='VP'>VP</option>";
                        tr_html = tr_html + "<option value='UPG'>UPG</option>";
                        tr_html = tr_html + "<option value='UG1'>UG1</option>";
                        tr_html = tr_html + "</select></td>";
                    } else if (propDetail.floorType === '15F') {
                        tr_html = tr_html + "<option value='-1'>Select</option>";
                        tr_html = tr_html + "<option value='GF' >GF</option>";
                        tr_html = tr_html + "<option value='1F' >1F</option>";
                        tr_html = tr_html + "<option value='2F' >2F</option>";
                        tr_html = tr_html + "<option value='3F' >3F</option>";
                        tr_html = tr_html + "<option value='4F' >4F</option>";
                        tr_html = tr_html + "<option value='5F' >5F</option>";
                        tr_html = tr_html + "<option value='6F'  >6F</option>";
                        tr_html = tr_html + "<option value='7F' >7F</option>";
                        tr_html = tr_html + "<option value='8F' >8F</option>";
                        tr_html = tr_html + "<option value='9F' >9F</option>";
                        tr_html = tr_html + "<option value='10F' >10F</option>";
                        tr_html = tr_html + "<option value='11F' >11F</option>";
                        tr_html = tr_html + "<option value='12F' >12F</option>";
                        tr_html = tr_html + "<option value='13F' >13F</option>";
                        tr_html = tr_html + "<option value='14F' >14F</option>";
                        tr_html = tr_html + "<option value='15F' selected='selected'>15F</option>";
                        tr_html = tr_html + "<option value='UG'>UG</option>";
                        tr_html = tr_html + "<option value='VP'>VP</option>";
                        tr_html = tr_html + "<option value='UPG'>UPG</option>";
                        tr_html = tr_html + "<option value='UG1'>UG1</option>";
                        tr_html = tr_html + "</select></td>";
                    } else if (propDetail.floorType === 'UG') {
                        tr_html = tr_html + "<option value='-1'>Select</option>";
                        tr_html = tr_html + "<option value='GF' >GF</option>";
                        tr_html = tr_html + "<option value='1F' >1F</option>";
                        tr_html = tr_html + "<option value='2F' >2F</option>";
                        tr_html = tr_html + "<option value='3F' >3F</option>";
                        tr_html = tr_html + "<option value='4F' >4F</option>";
                        tr_html = tr_html + "<option value='5F' >5F</option>";
                        tr_html = tr_html + "<option value='6F'  >6F</option>";
                        tr_html = tr_html + "<option value='7F' >7F</option>";
                        tr_html = tr_html + "<option value='8F' >8F</option>";
                        tr_html = tr_html + "<option value='9F' >9F</option>";
                        tr_html = tr_html + "<option value='10F' >10F</option>";
                        tr_html = tr_html + "<option value='11F' >11F</option>";
                        tr_html = tr_html + "<option value='12F' >12F</option>";
                        tr_html = tr_html + "<option value='13F' >13F</option>";
                        tr_html = tr_html + "<option value='14F' >14F</option>";
                        tr_html = tr_html + "<option value='15F' >15F</option>";
                        tr_html = tr_html + "<option value='UG' selected='selected'>UG</option>";
                        tr_html = tr_html + "<option value='VP'>VP</option>";
                        tr_html = tr_html + "<option value='UPG'>UPG</option>";
                        tr_html = tr_html + "<option value='UG1'>UG1</option>";
                        tr_html = tr_html + "</select></td>";
                    } else if (propDetail.floorType === 'VP') {
                        tr_html = tr_html + "<option value='-1'>Select</option>";
                        tr_html = tr_html + "<option value='GF' >GF</option>";
                        tr_html = tr_html + "<option value='1F' >1F</option>";
                        tr_html = tr_html + "<option value='2F' >2F</option>";
                        tr_html = tr_html + "<option value='3F' >3F</option>";
                        tr_html = tr_html + "<option value='4F' >4F</option>";
                        tr_html = tr_html + "<option value='5F' >5F</option>";
                        tr_html = tr_html + "<option value='6F'  >6F</option>";
                        tr_html = tr_html + "<option value='7F' >7F</option>";
                        tr_html = tr_html + "<option value='8F' >8F</option>";
                        tr_html = tr_html + "<option value='9F' >9F</option>";
                        tr_html = tr_html + "<option value='10F' >10F</option>";
                        tr_html = tr_html + "<option value='11F' >11F</option>";
                        tr_html = tr_html + "<option value='12F' >12F</option>";
                        tr_html = tr_html + "<option value='13F' >13F</option>";
                        tr_html = tr_html + "<option value='14F' >14F</option>";
                        tr_html = tr_html + "<option value='15F' >15F</option>";
                        tr_html = tr_html + "<option value='UG' selected='selected'>UG</option>";
                        tr_html = tr_html + "<option value='VP' selected='selected'>VP</option>";
                        tr_html = tr_html + "<option value='UPG'>UPG</option>";
                        tr_html = tr_html + "<option value='UG1'>UG1</option>";
                        tr_html = tr_html + "</select></td>";
                    } else if (propDetail.floorType === 'UPG') {
                        tr_html = tr_html + "<option value='-1'>Select</option>";
                        tr_html = tr_html + "<option value='GF' >GF</option>";
                        tr_html = tr_html + "<option value='1F' >1F</option>";
                        tr_html = tr_html + "<option value='2F' >2F</option>";
                        tr_html = tr_html + "<option value='3F' >3F</option>";
                        tr_html = tr_html + "<option value='4F' >4F</option>";
                        tr_html = tr_html + "<option value='5F' >5F</option>";
                        tr_html = tr_html + "<option value='6F'  >6F</option>";
                        tr_html = tr_html + "<option value='7F' >7F</option>";
                        tr_html = tr_html + "<option value='8F' >8F</option>";
                        tr_html = tr_html + "<option value='9F' >9F</option>";
                        tr_html = tr_html + "<option value='10F' >10F</option>";
                        tr_html = tr_html + "<option value='11F' >11F</option>";
                        tr_html = tr_html + "<option value='12F' >12F</option>";
                        tr_html = tr_html + "<option value='13F' >13F</option>";
                        tr_html = tr_html + "<option value='14F' >14F</option>";
                        tr_html = tr_html + "<option value='15F' >15F</option>";
                        tr_html = tr_html + "<option value='UG' selected='selected'>UG</option>";
                        tr_html = tr_html + "<option value='VP' >VP</option>";
                        tr_html = tr_html + "<option value='UPG' selected='selected'>UPG</option>";
                        tr_html = tr_html + "<option value='UG1'>UG1</option>";
                        tr_html = tr_html + "</select></td>";
                    } else if (propDetail.floorType === 'UG1') {
                        tr_html = tr_html + "<option value='-1'>Select</option>";
                        tr_html = tr_html + "<option value='GF' >GF</option>";
                        tr_html = tr_html + "<option value='1F' >1F</option>";
                        tr_html = tr_html + "<option value='2F' >2F</option>";
                        tr_html = tr_html + "<option value='3F' >3F</option>";
                        tr_html = tr_html + "<option value='4F' >4F</option>";
                        tr_html = tr_html + "<option value='5F' >5F</option>";
                        tr_html = tr_html + "<option value='6F'  >6F</option>";
                        tr_html = tr_html + "<option value='7F' >7F</option>";
                        tr_html = tr_html + "<option value='8F' >8F</option>";
                        tr_html = tr_html + "<option value='9F' >9F</option>";
                        tr_html = tr_html + "<option value='10F' >10F</option>";
                        tr_html = tr_html + "<option value='11F' >11F</option>";
                        tr_html = tr_html + "<option value='12F' >12F</option>";
                        tr_html = tr_html + "<option value='13F' >13F</option>";
                        tr_html = tr_html + "<option value='14F' >14F</option>";
                        tr_html = tr_html + "<option value='15F' >15F</option>";
                        tr_html = tr_html + "<option value='UG' selected='selected'>UG</option>";
                        tr_html = tr_html + "<option value='VP' >VP</option>";
                        tr_html = tr_html + "<option value='UPG' >UPG</option>";
                        tr_html = tr_html + "<option value='UG1' selected='selected'>UG1</option>";
                        tr_html = tr_html + "</select></td>";
                    } else {
                        tr_html = tr_html + "<option value='-1' selected='selected'>Select</option>";
                        tr_html = tr_html + "<option value='GF' >GF</option>";
                        tr_html = tr_html + "<option value='1F' >1F</option>";
                        tr_html = tr_html + "<option value='2F' >2F</option>";
                        tr_html = tr_html + "<option value='3F' >3F</option>";
                        tr_html = tr_html + "<option value='4F' >4F</option>";
                        tr_html = tr_html + "<option value='5F' >5F</option>";
                        tr_html = tr_html + "<option value='6F'  >6F</option>";
                        tr_html = tr_html + "<option value='7F' >7F</option>";
                        tr_html = tr_html + "<option value='8F' >8F</option>";
                        tr_html = tr_html + "<option value='9F' >9F</option>";
                        tr_html = tr_html + "<option value='10F' >10F</option>";
                        tr_html = tr_html + "<option value='11F' >11F</option>";
                        tr_html = tr_html + "<option value='12F' >12F</option>";
                        tr_html = tr_html + "<option value='13F' >13F</option>";
                        tr_html = tr_html + "<option value='14F' >14F</option>";
                        tr_html = tr_html + "<option value='15F' >15F</option>";
                        tr_html = tr_html + "<option value='UG' selected='selected'>UG</option>";
                        tr_html = tr_html + "<option value='VP' >VP</option>";
                        tr_html = tr_html + "<option value='UPG' >UPG</option>";
                        tr_html = tr_html + "<option value='UG1' selected='selected'>UG1</option>";
                        tr_html = tr_html + "</select></td>";
                    }





                    tr_html = tr_html + "<td><select class='form-control' id='floorDetails" + r + ".propertyUse' name='floorDetails[" + r + "].propertyUse'  readonly='readonly'  value='" + propDetail.propertyUse + "' >";
                    if (propDetail.propertyUse === 'Residential') {
                        tr_html = tr_html + "<option value='-1' >Select</option>";
                        tr_html = tr_html + "<option value='Residential' selected='selected' >Residential</option>";
                        tr_html = tr_html + "<option value='Commercial' >Commercial</option>";
                        tr_html = tr_html + "<option value='Other' >Other</option>";
                        tr_html = tr_html + "</select></td>";
                    } else if (propDetail.propertyUse === 'Commercial') {
                        tr_html = tr_html + "<option value='-1' >Select</option>";
                        tr_html = tr_html + "<option value='Residential'  >Residential</option>";
                        tr_html = tr_html + "<option value='Commercial' selected='selected'>Commercial</option>";
                        tr_html = tr_html + "<option value='Other' >Other</option>";
                        tr_html = tr_html + "</select></td>";
                    } else if (propDetail.propertyUse === 'Other') {
                        tr_html = tr_html + "<option value='-1' >Select</option>";
                        tr_html = tr_html + "<option value='Residential'  >Residential</option>";
                        tr_html = tr_html + "<option value='Commercial' >Commercial</option>";
                        tr_html = tr_html + "<option value='Other' selected='selected' >Other</option>";
                        tr_html = tr_html + "</select></td>";
                    } else {
                        tr_html = tr_html + "<option value='-1' selected='selected' >Select</option>";
                        tr_html = tr_html + "<option value='Residential'  >Residential</option>";
                        tr_html = tr_html + "<option value='Commercial' >Commercial</option>";
                        tr_html = tr_html + "<option value='Other' >Other</option>";
                        tr_html = tr_html + "</select></td>";
                    }


                    tr_html = tr_html + "<td><input class='form-control' id='floorDetails" + r + ".propertySubType' name='floorDetails[" + r + "].propertySubType' class='form-control' readonly='readonly' type='text' value='" + propDetail.propertySubType + "' ></td>";
                    tr_html = tr_html + "<td><input class='form-control' id='floorDetails" + r + ".carpetArea' name='floorDetails[" + r + "].carpetArea'  value='" + propDetail.carpetArea + "' readonly='readonly' type='text' ></td>";
                    tr_html = tr_html + "<td><select class='form-control' id='floorDetails" + r + ".constructionType' name='floorDetails[" + r + "].constructionType'  readonly='readonly' type='text' value='" + propDetail.constructionType + "' >";


                    if (propDetail.constructionType === 'RCC') {
                        tr_html = tr_html + "<option value='-1' >Select</option>";
                        tr_html = tr_html + "<option value='RCC' selected='selected' >RCC</option>";
                        tr_html = tr_html + "<option value='ASB' >ASB</option>";
                        tr_html = tr_html + "<option value='OTHER' >OTHER</option>";
                        tr_html = tr_html + "<option value='VP'  >VP</option>";
                        tr_html = tr_html + "</select></td>";
                    } else if (propDetail.constructionType === 'ASB') {
                        tr_html = tr_html + "<option value='-1' >Select</option>";
                        tr_html = tr_html + "<option value='RCC'  >RCC</option>";
                        tr_html = tr_html + "<option value='ASB' selected='selected' >ASB</option>";
                        tr_html = tr_html + "<option value='OTHER' >OTHER</option>";
                        tr_html = tr_html + "<option value='VP'>VP</option>";
                        tr_html = tr_html + "</select></td>";
                    } else if (propDetail.constructionType === 'OTHER') {
                        tr_html = tr_html + "<option value='-1' >Select</option>";
                        tr_html = tr_html + "<option value='RCC'  >RCC</option>";
                        tr_html = tr_html + "<option value='ASB' >ASB</option>";
                        tr_html = tr_html + "<option value='OTHER' selected='selected' >OTHER</option>";
                        tr_html = tr_html + "<option value='VP'  >VP</option>";
                        tr_html = tr_html + "</select></td>";
                    } else if (propDetail.constructionType === 'VP') {
                        tr_html = tr_html + "<option value='-1' >Select</option>";
                        tr_html = tr_html + "<option value='RCC'  >RCC</option>";
                        tr_html = tr_html + "<option value='ASB' >ASB</option>";
                        tr_html = tr_html + "<option value='OTHER'  >OTHER</option>";
                        tr_html = tr_html + "<option value='VP' selected='selected' >VP</option>";
                        tr_html = tr_html + "</select></td>";
                    } else {
                        tr_html = tr_html + "<option value='-1' selected='selected' >Select</option>";
                        tr_html = tr_html + "<option value='RCC' selected='selected' >RCC</option>";
                        tr_html = tr_html + "<option value='ASB' >ASB</option>";
                        tr_html = tr_html + "<option value='OTHER' >OTHER</option>";
                        tr_html = tr_html + "<option value='VP'  >VP</option>";
                        tr_html = tr_html + "</select></td>";
                    }

                    tr_html = tr_html + "<td><select class='form-control' id='floorDetails" + r + ".selfRent' name='floorDetails[" + r + "].selfRent'  readonly='readonly' type='text' value='" + propDetail.selfRent + "' >";
                    if (propDetail.selfRent === 'S') {
                        tr_html = tr_html + "<option value='-1' >Select</option>";
                        tr_html = tr_html + "<option value='S' selected='selected' >Self</option>";
                        tr_html = tr_html + "<option value='R' >Rent</option>";
                        tr_html = tr_html + "</select></td>";
                    } else if (propDetail.constructionType === 'R') {
                        tr_html = tr_html + "<option value='-1' >Select</option>";
                        tr_html = tr_html + "<option value='S'>Self</option>";
                        tr_html = tr_html + "<option value='R'selected='selected' >Rent</option>";
                        tr_html = tr_html + "</select></td>";
                    } else {
                        tr_html = tr_html + "<option value='-1' selected='selected' >Select</option>";
                        tr_html = tr_html + "<option value='S' selected='selected' >Self</option>";
                        tr_html = tr_html + "<option value='R'>Rent</option>";
                        tr_html = tr_html + "</select></td>";
                    }
                    tr_html = tr_html + "<td><input class='form-control' id='floorDetails" + r + ".rentedValue' name='presumeAnnualRent' class='form-control' readonly='readonly' type='text' value='" + propDetail.presumeRent + "' ></td>";

                    tr_html = tr_html + "<td><input class='form-control' id='floorDetails" + r + ".rentedValue' name='floorDetails[" + r + "].rentedValue' class='form-control' readonly='readonly' type='text' value='" + propDetail.rentedValue + "' ></td>";
                    tr_html = tr_html + "<td><input class='form-control coveruse'  id='floorDetails" + r + ".editData' name='floorDetails[" + r + "].editData' type='checkbox' value='Y' onclick='test.testFloorCheck(this);'></td> ";
                    tr_html = tr_html + "<td><input class='form-control' id='floorDetails" + r + ".deleteData' name='floorDetails[" + r + "].deleteData' type='checkbox' value='Y' style='display:inline-block'> <i class='fa fa-trash fa-1x' ></i> <input class='form-control' id='floorDetails" + r + ".propertyFloorId' name='floorDetails[" + r + "].propertyFloorId' type='hidden' value='" + propDetail.propertyFloorId + "' ></td>";

                    $("#correct_floor tbody").append(tr_html);
                    //console.log(propDetail);
                }

            }
    }
}



//});

var test = {
    floorn: 0,
    testCheck: function (ths) {
        if (ths.checked)
            $(ths).closest("td").prev().find("input").attr("readonly", false);

        else
            $(ths).closest("td").prev().find("input").attr("readonly", true);
    },testSelectCheck: function (ths) {
        if (ths.checked)
            $(ths).closest("td").prev().find("select").attr("disabled", false);

        else
            $(ths).closest("td").prev().find("select").attr("disabled", true);
    },
    testFloorCheck: function (ths) {
        if (ths.checked)
            $(ths).closest("tr").find("input[name!='presumeAnnualRent'],select").attr("readonly", false);
        else
            $(ths).closest("tr").find("input[name!='presumeAnnualRent'],select").attr("readonly", true);

    },
    addFloor: function () {
        //alert("add floor");
        test.floorn = parseInt(test.floorn) + 1;
        //alert("add floor "+test.floorn); 
        var tr_html = "<tr id=><td><select style='min-width:80px'  class='form-control' id='floorDetails" + test.floorn + ".floorType' name='floorDetails[" + test.floorn + "].floorType'      >";
        tr_html = tr_html + "<option value='-1' >Select</option>";
        tr_html = tr_html + "<option value='GF'>GF</option>";
        tr_html = tr_html + "<option value='1F'>1F</option>";
        tr_html = tr_html + "<option value='2F'>2F</option>";
        tr_html = tr_html + "<option value='3F'>3F</option>";
        tr_html = tr_html + "<option value='4F'>4F</option>";
        tr_html = tr_html + "<option value='5F'>5F</option>";
        tr_html = tr_html + "<option value='6F'>6F</option>";
        tr_html = tr_html + "<option value='7F'>7F</option>";
        tr_html = tr_html + "<option value='8F'>8F</option>";
        tr_html = tr_html + "<option value='9F'>9F</option>";
        tr_html = tr_html + "<option value='10F'>10F</option>";
        tr_html = tr_html + "<option value='11F'>11F</option>";
        tr_html = tr_html + "<option value='12F'>12F</option>";
        tr_html = tr_html + "<option value='13F'>13F</option>";
        tr_html = tr_html + "<option value='14F'>14F</option>";
        tr_html = tr_html + "<option value='15F'>15F</option>";
        tr_html = tr_html + "<option value='UG' '>UG</option>";
        tr_html = tr_html + "<option value='VP' >VP</option>";
        tr_html = tr_html + "<option value='UPG' >UPG</option>";
        tr_html = tr_html + "<option value='UG1' >UG1</option>";
        tr_html = tr_html + "</select></td>";


        tr_html = tr_html + "<td><select class='form-control' id='floorDetails" + test.floorn + ".propertyUse' name='floorDetails[" + test.floorn + "].propertyUse'     >";
        tr_html = tr_html + "<option value='-1'  >Select</option>";
        tr_html = tr_html + "<option value='Residential'  >Residential</option>";
        tr_html = tr_html + "<option value='Commercial' >Commercial</option>";
        tr_html = tr_html + "<option value='Other' >Other</option>";
        tr_html = tr_html + "</select></td>";

        tr_html = tr_html + "<td><input class='form-control' id='floorDetails" + test.floorn + ".propertySubType' name='floorDetails[" + test.floorn + "].propertySubType' class='form-control'  type='text'  ></td>";
        tr_html = tr_html + "<td><input class='form-control' id='floorDetails" + test.floorn + ".carpetArea' name='floorDetails[" + test.floorn + "].carpetArea'    type='text' ></td>";
        tr_html = tr_html + "<td><select class='form-control' id='floorDetails" + test.floorn + ".constructionType' name='floorDetails[" + test.floorn + "].constructionType'   type='text'  >";
        tr_html = tr_html + "<option value='-1'>Select</option>";
        tr_html = tr_html + "<option value='RCC'>RCC</option>";
        tr_html = tr_html + "<option value='ASB'>ASB</option>";
        tr_html = tr_html + "<option value='OTHER'>OTHER</option>";
        tr_html = tr_html + "<option value='VP'>VP</option>";
        tr_html = tr_html + "</select></td>";
        tr_html = tr_html + "<td><select class='form-control' id='floorDetails" + test.floorn + ".selfRent' name='floorDetails[" + test.floorn + "].selfRent'   type='text'  >";
        tr_html = tr_html + "<option value='-1'  >Select</option>";
        tr_html = tr_html + "<option value='S'  >Self</option>";
        tr_html = tr_html + "<option value='R'>Rent</option>";
        tr_html = tr_html + "</select></td>";
        tr_html = tr_html + "<td><input class='form-control' id='floorDetails" + test.floorn + ".presumeRent' name='floorDetails[" + test.floorn + "].presumeRent' class='form-control'  type='text'  ></td>";

        tr_html = tr_html + "<td><input class='form-control' id='floorDetails" + test.floorn + ".rentedValue' name='floorDetails[" + test.floorn + "].rentedValue' class='form-control'  type='text'  ></td>";
        tr_html = tr_html + "<td><input class='form-control' id='floorDetails" + test.floorn + ".editData' name='floorDetails[" + test.floorn + "].editData' type='hidden' value='new' ></td> ";
        //tr_html=tr_html+"<td><input class='form-control' id='floorDetails"+ test.floorn + ".deleteData' name='floorDetails[" + test.floorn + "].deleteData' type='checkbox' value='Y'></td>";
        tr_html = tr_html + "<td><input class='form-control' id='floorDetails" + test.floorn + ".propertyFloorId' name='floorDetails[" + test.floorn + "].propertyFloorId' type='hidden' value='0'  ></td>";
        tr_html = tr_html + "<td><input class='form-control' id='floorDetails" + test.floorn + ".propertyFloorId'  type='button' value='Remove Row' onclick='test.removeRow();'  ></td>";
        $("#correct_floor tbody").append(tr_html);

    },
    loadCorrectionForm: function (iddd) {
        //var check=test.checkId();
        //alert("ddddd "+check);
        var uniqueId =  Silva.proprtyId;             //$("#uniqueIdTC").val().trim();
        //alert(uniqueId); 
        uniqueId = uniqueId.toUpperCase();
        if(uniqueId.indexOf('S')> -1){
          
        if (uniqueId.trim() == "") {
            alert("Please enter property Id");
            $("#uniqueIdTC").focus()
            return false;
        } else {
            //alert("else");
            $.ajax({
                type: "POST",
                url: "checkPropertyId",
                data: "id=" + uniqueId,
                cache: false,
                success: function (data) {
                    //alert(data);
                    //debugger;
                    if (data == 'Property Id Found') {
                        var ldt =  uniqueId //$("#uniqueIdTC").val();
                        ldt = ldt.toUpperCase();

                        var url = document.getElementById('correctionFormTest').href;
                        var form = $('<form action="' + url + '" method="get">' +
                                '<input type="text" name="api_url" value="' + ldt + '" />' +
                                '</form>');
                        $('body').append(form);
                        form.submit();

                    } else {
                        alert(data);
                        $("#uniqueIdTC").focus();
                        return false;

                    }
                  }
              });
          }
     }else{
          test.loadCorrectionFormNoticeNo(uniqueId);
     }

    },
    checkId: function () {
        //var check=true;
        var uniqueId = $("#uniqueIdTC").val().trim();
        //alert("ashk "+uniqueId);
        if (uniqueId.trim() == "") {
            //alert("ashk22 "+uniqueId);
            $("#uniqueIdTC").focus()
            return false;
        } else {
            //alert("else");
            $.ajax({
                type: "POST",
                url: "checkPropertyId",
                data: "id=" + uniqueId,
                cache: false,
                success: function (data) {
                    //alert(data);
                    if (data == 'Property Id Found') {
//                 $('#correFormId').attr('action','doUpload');
//                 $('#correFormId').attr('method','post');
//                 $('#correFormId').submit() ;
                        //console.log($('#correFormId').serialize());
                        //alert("after");
                        //window.location="http://localhost:8080/SilvassaPay/doUpload";
                        // alert("dd "+window.location.href);
                        return true;
                        //checkPropertyId.checkvalidation() ;  
                    } else {
                        alert(data);
                        $("#uniqueIdTC").focus();
                        return false;
                        //checkPropertyId.checkvalidation() ;  
                    }
                }
            });
        }
    },
    correctionFormvalidation: function () {
        var chk = true;
        
        var ownerName = $("#ownerName").val();
        var sex = $("#ownerSex").val();
        var ownerFather = $("#ownerFather").val();
        var ownerContact = $("#ownerContact").val();
        var ownerEmail = $("#ownerEmail").val();
        var applicantMobileNo = $("#applicantMobileNo").val();
        var occupierEmail = $("#occupierEmail").val();
        var occupierContact = $("#occupierContact").val();
        var ownerContact = $("#ownerContact").val();

        var documentType = $("#documentType").val();
        //alert("test "+documentType);
        var documentType1 = $("#documentType1").val();
        var documentTypeOccupier = $("#documentTypeOccupier").val();
        var documentTypeOccupier1 = $("#documentTypeOccupier1").val();
        var documentTypeAddress = $("#documentTypeAddress").val();
        var documentTypeAddress2 = $("#documentTypeAddress2").val();
        //alert("documentTypeAddress "+documentTypeAddress);
        //alert("documentTypeAddress2 "+documentTypeAddress2);
        var locName = $("#locName").val();
        var landMark = $("#landMark").val();

        var imageFileOwner1 = $('#imageFile').val();
        //alert("imageFileOwner1tt "+imageFileOwner1.length);
        var imageFileOwner2 = $('#imageFileOwner').val();
        var imageFileOccupier1 = $('#imageFile1').val();
        var imageFileOccupier2 = $('#imageFileOccupier').val();
        var imageFileAddress1 = $('#imageFile2').val();
        var imageFileAddress2 = $('#imageFileAddress').val();

        var checkOwnerName = $('#checkOwnerName').is(':checked');
        //alert("checkOwnerName "+checkOwnerName);
        var checkOccupierName = $('#checkOccupierName').is(':checked');
        var checkPlotNo = $('#checkPlotNo').is(':checked');

        var documentType2 = $("#documentType2").val();
        var documentType3 = $("#documentType3").val();
        var imageFileOwner2Data = $('#imageFileOwner2Data').val();
        var imageFileOwner3Data = $('#imageFileOwner3Data').val();

        var documentTypeOccupier2 = $("#documentTypeOccupier2").val();
        var documentTypeOccupier3 = $("#documentTypeOccupier3").val();
        var imageFileOccupier2Data = $('#imageFileOccupier2Data').val();
        var imageFileOccupier1Data = $('#imageFileOccupier1Data').val();

        var documentTypeAddress3 = $("#documentTypeAddress3").val();
        var documentTypeAddress4 = $("#documentTypeAddress4").val();
        var imageFileAddress1Data = $('#imageFileAddress1Data').val();
        var imageFileAddress2Data = $('#imageFileAddress2Data').val();

        var checkArrear = $('#checkArrear').is(':checked');
        var documentTypeArrear = $("#documentTypeArrear").val();
        var imageFileArrear = $('#imageFileArrear').val();

        var checkElectricSericeConnectionNo = $('#checkElectricSericeConnectionNo').is(':checked');
        var documentTypeElectric = $("#documentTypeElectric").val();
        var imageFile3 = $('#imageFile3').val();

        var ownerLen = $(".owner:checked").length;
        //alert("ownerLen "+ownerLen);
        var occupierLen = $(".occupier:checked").length;
        //alert("occupierLen "+occupierLen);
        var addrLen = $(".addr:checked").length;
        //alert("addrLen "+addrLen);

        var floorLen = $(".coveruse:checked").length;
        //alert("floorLen "+floorLen);

        

         var checkOwnerSex = $('#checkOwnerSex').is(':checked');
         var checkOwnerFather = $('#checkOwnerFather').is(':checked');
         var checkOwnerContact = $('#checkOwnerFather').is(':checked');
         var checkLocName = $('#checkOwnerFather').is(':checked');
         var checkLandMark = $('#checkOwnerFather').is(':checked');
         
        var documentTypeCovered = $("#documentTypeCovered").val(); 
        var imageFileCovered = $("#imageFileCovered").val(); 
        var documentTypePropertyUse = $("#documentTypePropertyUse").val(); 
        var imagePropertyUse = $("#imagePropertyUse").val(); 
        
        var locNameOther = $("#locNameOther").val(); 
        var checkOwnerAadharNo = $('#checkOwnerAadharNo').is(':checked');
        var checkOwnerFather = $('#checkOwnerFather').is(':checked');
        
        var ownerAadharNo = $("#ownerAadharNo").val(); 
        
        





        var applicantName = $("#applicantName").val();
        //var email=$("#applicantEmail").val();
        var filter = /^([a-zA-Z0-9_\.\-])+\@(([a-zA-Z0-9\-])+\.)+([a-zA-Z0-9]{2,4})+$/;
        
//        if (checkOwnerFather && ownerFather.trim() != ""  ) {
//            if(documentType1 == -1){
//              alert("Please select document for Father "); 
//              $("#documentType1").focus();
//              chk = false;
//            }else if(documentType1!=-1 && imageFileOwner2.trim().length==0 ){
//                 $("#imageFileOwner").focus();
//                 alert("Please choose Father  name document ");
//                 chk = false;
//            }
//           //chk = false;
//        }
//        if(checkOwnerAadharNo && ownerAadharNo.trim() != ""  ) {
//            if(documentType3==-1){
//              alert("Please select Aadhaar document for owner "); 
//              $("#documentType3").focus();
//              chk = false;
//            }else if(documentType3!=-1 && imageFileOwner3Data.trim().length==0 ){
//                 $("#imageFileOwner3Data").focus();
//                 alert("Please choose Aadhaar card document for owner ");
//                 chk = false;
//            }
//           //chk = false;
//        }
        
          if (ownerContact.length > 0 && ownerContact.length != 10) {
            alert("Please enter owner mobile no 10 digit ");
            $("#ownerContact").focus();
            chk = false;
        }
        else if (occupierContact.length > 0 && occupierContact.length != 10) {
            alert("Please enter occupier Contact MobileNo 10 digit ");
            $("#occupierContact").focus();
            chk = false;
        } else if (applicantName.trim() == "") {
            alert("Please fill applicant name");
            $("#applicantName").focus();
            chk = false;
        } else if (applicantMobileNo.trim() == "") {
            alert("Please fill applicant mobile number");
            $("#applicantMobileNo").focus();
            chk = false;
        }
        else if (applicantMobileNo.length != 10) {
            alert("Please enter applicant Mobile No 10 digit ");
            $("#applicantMobileNo").focus();
            chk = false;
        }

        else if (ownerEmail.length > 0 && !filter.test(ownerEmail)) {
            alert('Please provide a valid ownerEmail address');
            $("#ownerEmail").focus();
            chk = false;
        }

        else if (occupierEmail.length > 0 && !filter.test(occupierEmail)) {
            alert('Please provide a valid occupierEmail address');
            $("#occupierEmail").focus();
            chk = false;
        } 
         return chk;   
   
   }, fillOccupierName: function () {
        var ownerName = $("#ownerName").val();
        var ownerSex = $("#ownerSex").val();
        var ownerFather = $("#ownerFather").val();
        var ownerContact = $("#ownerContact").val();
        var ownerEmail = $("#ownerEmail").val();
        var ownerAadharNo = $("#ownerAadharNo").val();

        var occupierName = $("#occupierName").val();
        var occupierSex = $("#occupierSex").val();
        var occupierFather = $("#occupierFather").val();
        var occupierEmail = $("#occupierEmail").val();
        var occupierContact = $("#occupierContact").val();
        var occupierAadharNo = $("#occupierAadharNo").val();
        var checkfill = $("#fillOccupierName").is(':checked');
        //alert(checkfill);
        //alert("ownerSex "+ownerSex);
        if (checkfill) {
            if (ownerName.trim() != "") {
                if (occupierName.trim() == "") {
                    $("#occupierName").val(ownerName);
                    $("#checkOccupierName").prop("checked",true);
                }
            }
            if (ownerSex != -1) {
                if (occupierSex == -1) {
                    $("#occupierSex").val(ownerSex);
                    $("#checkOccupierSex").prop("checked",true);
                }
            }
            if (ownerFather.trim() != "") {
                if (occupierFather.trim() == "") {
                    $("#occupierFather").val(ownerFather);
                     $("#checkOccupierFather").prop("checked",true);
                }
            }
            if (ownerContact.trim() != "") {
                if (occupierContact.trim() == "") {
                    $("#occupierContact").val(ownerContact);
                    $("#checkOccupierContact").prop("checked",true);
                }
            }
            if (ownerEmail.trim() != "") {
                if (occupierEmail.trim() == "") {
                    $("#occupierEmail").val(ownerEmail);
                    $("#checkOccupierEmail").prop("checked",true);
                }
            }
            if (ownerAadharNo.trim() != "") {
                if (occupierAadharNo.trim() == "") {
                    $("#occupierAadharNo").val(ownerAadharNo);
                    $("#checkOccupierAadharNo").prop("checked",true);
                }
            }
        } else {

            $("#occupierName").val("");
            $("#occupierSex").val("-1");
            $("#occupierFather").val("");
            $("#occupierContact").val("");
            $("#occupierEmail").val("");
            $("#occupierAadharNo").val("");

        }







    }, loadLocalityBasedOnWard: function () {
        //var check=true;
        var wardNo = $("#wardNo").val().trim();
        alert("wardNo "+wardNo);
        if (wardNo.trim() == "") {
            //alert("ashk22 "+uniqueId);
            $("#wardNo").focus()
            return false;
        } else {
            //alert("else");
            $.ajax({
                type: "POST",
                url: "loadLocalityWard",
                data: "ward=" + wardNo,
                cache: false,
                success: function (data) {
                    alert(data);
                    var dataValue = data.split(":")
                    $("#locName").html("");
                    var add = "";
                    add = "<option value='-1'>Select</option>";
                    for (var i = 0; i < dataValue.length; i++) {

                        add += "<option value=" + dataValue[i] + ">" + dataValue[i] + "</option>";

                    }
                    add += "<option value='Other'>Other</option>";
                    $("#locName").html(add);

                }
            });
        }
    }, addLocality: function () {
       
         var locName = $("#locName").val();
        //  alert(locName);
         if(locName=='Other'){
          // $("#locNameOther").attr("style","display:block");  
          $("#locNameOther").show();
         }else{
             $("#locNameOther").hide(); 
         }
//        var tr_html = "";
//        tr_html = tr_html + "<tr><td><input class='form-control' id='localityAdd' name='localityAdd' path='localityAdd' class='form-control'  type='text'  ></td></tr>";
//        $("#addowner").append(tr_html);
    },loadCorrectionFormNoticeNo: function (iddd) {
        //var check=test.checkId();
        //alert("ddddd "+check);
        var noticeNo =  iddd          //$("#noticenoIDC").val().trim();
        //alert(" noticeNo : "+noticeNo);
//uniqueId = uniqueId.toUpperCase();
        //alert("uniqueId "+uniqueId);
        // alert("ashk "+uniqueId);
        if (noticeNo.trim() == "") {
            alert("Please enter notice no. ");
            $("#noticenoIDC").focus()
            return false;
        } else {
            //alert("else");
            $.ajax({
                type: "POST",
                url: "checkNoticeNo",
                data: "id=" + noticeNo,
                cache: false,
                success: function (data) {
                    //alert(data);
                    //debugger;
                    var noticeMsg=data.split(":")
                    var frMsg=noticeMsg[0];
                    var uid=noticeMsg[1];
                    //alert(uid);
                    if (frMsg == 'Notice number Found') {
                        var ldt = uid;//$("#uniqueIdTC").val();
                        ldt = ldt.toUpperCase();

                        var url = document.getElementById('correctionFormTest').href;
                        var form = $('<form action="' + url + '" method="get">' +
                                '<input type="text" name="api_url" value="' + ldt + '" />' +
                                '</form>');
                        $('body').append(form);
                        form.submit();

                    } else {
                        alert(frMsg);
                        $("#noticenoIDC").focus();
                        return false;

                    }
                }
            });
        }

//                
//                var ldt=$("#uniqueIdTC").val();
//                 var url = document.getElementById('correctionFormTest').href;
//                 var form = $('<form action="' + url + '" method="get">' +
//                    '<input type="text" name="api_url" value="' + ldt + '" />' +
//                '</form>');
//            $('body').append(form);
//            form.submit();


//                
//                 $("#hidd").attr("dataattr","hghghg");
//                 window.location.pathname = "my_value";
//                window.location=document.getElementById('correctionFormTest').href;
//                window.history="jhjh";
//                $("#hidd").attr("dataattr","hghghg");
//                $("#hidd").html("klhjkhkj");
        // return  false;
//                window.onload = function(e){ 
//                    alert("kjh");
//                  }
        // alert(iddd);
        // console.log(iddd);
        //testTRRR.uniqueProperty(iddd);
//              $('#correctionid').click(function(event) {
//                var id = $('#uniqueId').val();
//                alert("id "+id);
//                
//              $.ajax({
//                    url : 'correctionForm',
//                    type : 'post',
//                    data : "id=" + id,
//                    success : function(result) {
//                       console.log("check "+result);
//                    }  
//            })
//            });
    },removeRow:function(){
        $('#correct_floor tr').click(function(){
         $(this).remove();
         return false;
});
    }


}

