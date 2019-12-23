/* 
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

var test = {
     gmailId : "",
     mobileNo : "",
     proprtyId :"",
    checkId: function () {
        
        //var check=true;
        var uniqueId = $("#propertyId").val().trim();
        var owner = $("#ownerName").val().trim();
        var mobile = $("#mobile").val().trim();
        var email= $("#email").val().trim();
        var filter = /^([a-zA-Z0-9._\.\-])+\@(([a-zA-Z0-9\-])+\.)+([a-zA-Z0-9]{2,4})+$/;
        $("#validateid").html('');
        if (uniqueId.trim() == "") {
            $("#validateid").html("Property Id required");
            $("#validateid").css('color','red');
            //alert("ashk22 "+uniqueId);
            $("#propertyId").focus()
            return false;
//        } else if(owner==""){
//           $("#validateid").html("Owner name required");
//            $("#validateid").css('color','red');
//            //alert("ashk22 "+uniqueId);
//            $("#ownerName").focus()
//            return false;
//        }else if(mobile==""){
//           $("#validateid").html("mobile number required");
//            $("#validateid").css('color','red');
//            //alert("ashk22 "+uniqueId);
//            $("#email").focus()
//            return false;
//        }else if(email==""){
//           $("#validateid").html("email required");
//            $("#validateid").css('color','red');
//            //alert("ashk22 "+uniqueId);
//            $("#mobile").focus()
//            return false;
//        }else if (mobile.length != 10) {
//            $("#validateid").html("Please enter  mobile number, 10 digit only.");
//            $("#mobile").focus();
//            return false;
//        }else if (email.length > 0 && !filter.test(email)) {
//            $("#validateid").html('Please provide valid email Id .');
//            $("#email").focus();
//            return falase;
       }else {
            //alert("else");
            $.ajax({
                type: "POST",
                url: "checkPropertyId",
                data: "id=" + uniqueId,
                cache: false,
                success: function (data) {
                     //console.log(data);
                     //alert(data.uniqueId);
                    // alert(data.address);
                    // alert(data.msg);
                    $("#propertyId").val(data.uniqueId);
                    $("#ownerName").val(data.ownerName);
                    $("#mobile").val(data.onwerMobile);
                    $("#email").val(data.ownerEmail);
                    $("#occupierName").val(data.occupierName);
                    $("#occupierMobile").val(data.occupierMobile);
                    $("#occupierEmail").val(data.occupierEmail);
                    $("#spouseName").val(data.spouseName);
                    $("#address").val(data.address);
                    return false;
                    
                    if (data.msg == 'Property Id Found') {
                       // $("#optpid").show();
                       //$("#divid").hide();
                        $.ajax({
                        type : "POST",
                        url : 'getGmailById',
                        data : {
                          proId : uniqueId,
                          mobile:mobile,
                          email:email
		         },
		success : function(data) {
                            //console.log(data);
                             if(data.res.status==200){
                                $('#validateid').text(data.res.msg);
                                $('#validateid').css('color', 'green');
                                //$('#inDiv_id').hide();
                                //$('#optDiv_id').show();
                                $("#optpid").show();
                                $("#divid").hide();
                                 test.gmailId  = data.gmailId;
                                 test.mobileNo = data.mobileNo;
                                 test.proprtyId = uniqueId;
                            }else{
                                 $('#validateid').text(data.res.msg);
                                 $('#validateid').css('color', 'red');
                                 $("#divid").show();
                                 $("#optpid").hide();
                            }   
			},
		error : function(data) {			
			
			}
		});
                        
                        
                        
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
                        $("#validateid").html(data.msg);
                        $("#propertyId").focus();
                        return false;
                        //checkPropertyId.checkvalidation() ;  
                    }
                }
            });
        }
    },
    
   
   verifyOTP : function(){
        var uniqueId = $("#propertyId").val().trim();
        var mobile = $("#mobile").val().trim();
        var email= $("#email").val().trim();
        var occupierMobile = $("#occupierMobile").val().trim();
        var occupierEmail= $("#occupierEmail").val().trim();
        var documentType= $("#documentType").val();
        var proof_msg= $("#docOther").val(); 
        var fileDoc=$("#fileDoc").val();
       var form= new FormData(document.getElementById("formdata"));
        var otp = $("#otpvalidate").val();
       if(otp && otp.length==6){
           $.ajax({
		 type : "POST",
   		 url : 'verifyOTP',
		 data : {
                          mailId : test.gmailId,
                          mobileNo : test.mobileNo,
                          otp : otp
		         },
		success : function(data) {
                            //console.log(data);
                            if(data.status==200){
                                   $('#validateid').text(data.msg);
                                   $('#validateid').css('color', 'green');
                                   $("#optpid").show();
                                   $("#divid").hide();
                                   $.ajax({
                                    type : "POST",
                                    url : 'saveMobleAndEmail',
                                    data:form,
//                                     data : {
//                                      proId : uniqueId,
//                                      ownerMobile:mobile,
//                                      ownerEmail:email,
//                                      occupierMobile:occupierMobile,
//                                      occupierEmail:occupierEmail,
//                                      documentType:documentType,
//                                      proof_msg:proof_msg,
//                                      formRegis:form
//                                     },
                                     enctype: 'multipart/form-data',
                                     processData: false,
                                     contentType: false,
                  success : function(data) {
                              //console.log(data);                              
                              if(data.status==200){
                                   //alert("asdfdasd")
                                  $('#validateid').text(data.msg);
                                  $('#validateid').css('color', 'green');
                                  //$('#inDiv_id').hide();
                                  //$('#optDiv_id').show();
                                  
                                  $("#optpid").hide();
                                   


                              }else{
                                   $('#validateid').text("not updated data");
                                   $('#validateid').css('color', 'red');
                                   $("#divid").show();
                                   $("#optpid").hide();
                              }   
                          },
                  error : function(data) {			

                          }
                  });
                                   //$('#optDiv_id').hide();
                                   //$('#corrctFrmDivId').show();
                                   //window.location = "/SilvassaPay/correctionForm";
                                   
                            }else{
                                $('#validateid').text(data.msg);
                                $('#validateid').css('color', 'red');
                            }
			},
		error : function(data) {			
			
                        }
		});
       }else{
          $('#validateid').text('Please Enter Valid OTP!');
          $('#validateid').css('color', 'red');
       }
   },checkFillData :function(){
       var uniqueId = $("#propertyId").val().trim();
       if(uniqueId==''){
             var hMsg = "Property id can't be left blank";
           //alert("");
           $('#alertMsg').fadeIn( 400 ).html(hMsg).delay( 5000 ).fadeOut( 400 );
           $("#propertyId").focus();
           return false;
       }
       uniqueId=uniqueId.toUpperCase();
       $.ajax({
                type: "POST",
                url: "checkPropertyId",
                data: "id=" + uniqueId,
                cache: false,
                success: function (data) {
                    //console.log(data);
                    if (data.msg == 'Property Id Found') {
                    $("#propertyId").val(data.uniqueId);
                    $("#ownerName").val(data.ownerName);
                    $("#mobile").val(data.onwerMobile);
                    $("#email").val(data.ownerEmail);
                    $("#occupierName").val(data.occupierName);
                    $("#occupierMobile").val(data.occupierMobile);
                    $("#occupierEmail").val(data.occupierEmail);
                    $("#spouseName").val(data.spouseName);
                    $("#address").val(data.address);
                    $("#documentType").val(data.documentType);
                          
                    } else {
                        $("#validateid").html(data.msg);
                        $('#validateid').css('color', 'red');
                        $("#propertyId").val(uniqueId);
                        $("#ownerName").val("");
                        $("#mobile").val("");
                        $("#email").val("");
                        $("#occupierName").val("");
                        $("#occupierMobile").val("");
                        $("#occupierEmail").val("");
                        $("#spouseName").val("");
                        $("#address").val("");
                        $("#documentType").val("-1");
                        $("#propertyId").focus();
                        return false;
                        //checkPropertyId.checkvalidation() ;  
                    }
                }
            });
   },sendOTP :function(){
        var uniqueId = $("#propertyId").val().trim();
        uniqueId=uniqueId.toUpperCase();
        var mobile = $("#mobile").val().trim();
        var email= $("#email").val().trim();
        var occupierMobile = $("#occupierMobile").val().trim();
        var occupierEmail= $("#occupierEmail").val().trim();
        var documentType=$("#documentType").val().trim();
        
        var filter = /^([a-zA-Z0-9._\.\-])+\@(([a-zA-Z0-9\-])+\.)+([a-zA-Z0-9]{2,4})+$/;
   
       if (uniqueId.trim() == "") {
            $("#validateid").html("Property id required");
            $("#validateid").css('color','red');
            //alert("ashk22 "+uniqueId);
            $("#propertyId").focus()
            return false;
        } else if(mobile==""){
            $("#validateid").html("Owner name mobile number required");
            $("#validateid").css('color','red');
            //alert("ashk22 "+uniqueId);
            $("#mobile").focus()
            return false;
        }else if (mobile.length != 10) {
            $("#validateid").html("Please enter  mobile number, 10 digit only.");
            $("#validateid").css('color','red');
            $("#mobile").focus();
            return false;
        }else if (email.length > 0 && !filter.test(email)) {
            $("#validateid").html('Please provide valid email id .');
            $("#validateid").css('color','red');
            $("#email").focus();
            return false;
       }else if(occupierMobile!="" && occupierMobile.length != 10){
           $("#validateid").html("Please enter occupier mobile number, 10 digit only.");
           $("#validateid").css('color','red');
           $("#occupierMobile").focus();
            return false;
       }else if(occupierEmail!="" && occupierEmail.length > 0 && !filter.test(occupierEmail)){
            $("#validateid").html('Please occupier  provide valid email id .');
            $("#validateid").css('color','red');
            $("#occupierEmail").focus();
            return false;
         }else {
         
        var combineMobile;
        var combineEmail;
        if(mobile!= ""){
           combineMobile=mobile; 
        }
       if(email!= ""){
           combineEmail=email;
       }
       
//       if(mobile!="" && occupierMobile!=""){
//            combineMobile=mobile+","+occupierMobile;
//        }else if(mobile!= "" && occupierMobile == ""){
//            combineMobile=mobile;
//        }else if(mobile== "" && occupierMobile!== ""){
//             combineMobile=occupierMobile;
//        }
//        if(email!="" && occupierEmail!=""){
//            combineEmail=email+","+occupierEmail;
//        }else if(email!= "" && occupierEmail == ""){
//             combineEmail=email;
//        }else if(email== "" && occupierEmail!== ""){
//              combineEmail=occupierEmail;
//        }
        
        //alert("combineMobile "+combineMobile);
       // alert("combineEmail "+combineEmail);

        $.ajax({
              type : "POST",
              url : 'getGmailById',
              data : {
                proId : uniqueId,
                mobile:combineMobile,
                email:combineEmail
               },
      success : function(data) {
                  //console.log(data);
                   if(data.res.status==200){
                      $('#validateid').text(data.res.msg);
                      $('#validateid').css('color', 'green');
                      //$('#inDiv_id').hide();
                      //$('#optDiv_id').show();
                      $("#optpid").show();
                      $("#divid").hide();
                       test.gmailId  = data.gmailId;
                       test.mobileNo = data.mobileNo;
                       test.proprtyId = uniqueId;
                       
                  }else{
                       $('#validateid').text(data.res.msg);
                       $('#validateid').css('color', 'red');
                       $("#divid").show();
                       $("#optpid").hide();
                  }   
              },
      error : function(data) {			

              }
      });
    } 
   },addTextBox:function(){
      var docType=$("#documentType").val();
      if (docType == 'AO') {
            // $("#locNameOther").attr("style","display:block");  
            $("#docOther").show();
        } else {
            $("#docOther").hide();
        }
   },getBase64Image:function(id_,type_) {

               if(type_!=''){
                var dd=type_.split(".");
                type_=dd[1].toUpperCase();
               }
            
             
             //alert("owner  "+type_); 
               $.get("getBase64Image", {
                    id: id_
                }, function (data) {
                  //console.log("image "+data);   
                  var image = new Image(); 
                    if(type_ == "JPEG" || type_ == "JPG") {
                        image.src = "data:image/jpg;base64," + data;
                    } else if(type_ == "PNG") {
                        image.src = "data:image/png;base64," + data;
                    } else if(type_ == "PDF") {
                        var blob;
                         blob = converBase64toBlob(data, 'application/pdf');
                         var blobURL = URL.createObjectURL(blob);
                         window.open(blobURL);
                        //image.src = "data:application/pdf;base64," + data;
                    }else if(type_ == "GIF") {
                        image.src = "data:image/gif;base64," + data;
                    }else{
                     image.src = "data:image/jpg;base64," + data;   
                    }
                    //var image = new Image();
                    //image.src = "data:image/jpg;base64," + data;
                    if(type_!= "PDF"){
                    var w = window.open("", "_blank", "toolbar=yes,top=500,left=500,width=600,height=600");
                    w.document.write(image.outerHTML);
                    }
                });
            },converBase64toBlob: function (content, contentType) {
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
},showImage:function(){
    var uniqueId = $("#propertyId").val().trim();
    //alert(uniqueId);
    $.ajax({
              type : "POST",
              url : 'getImageName',
              data : {
                proId : uniqueId
                
               },
      success : function(data) {
                  //console.log(data);
                  test.getBase64Image(data.uniqueId,data.imageName);
                  
                   
              },
      error : function(data) {			

              }
      });
    
}  
}
