<link rel="stylesheet" type="text/css" href="res/css/select2.css" />
<link rel="stylesheet" type="text/css" href="res/css/select2-bootstrap.css" />
<link rel="stylesheet" type="text/css" href="res/css/dataTables.bootstrap.min.css" />

<script src="res/js/api/jquery.dataTables.min.js"></script>
<script src="res/js/api/dataTables.bootstrap.min.js"></script>
<script src="res/js/api/jquery-ui.js"></script>
<script src="res/js/api/select2.min.js"></script>

<%@taglib uri = "http://www.springframework.org/tags/form" prefix = "form"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<% String MSG="";
   String pf_ref_id="";
   String pf_ref_collection_id="";
   String dateOfNotice="";
   String dateOfTransfer="";
   String venderName="";
   String purchaserName="";
   String amount="";
   String piddata="";
        
  if(request.getAttribute("MSG")!=null){
      MSG=(String)request.getAttribute("MSG");
  }
  if(request.getAttribute("pf_ref_id")!=null){
      pf_ref_id=(String)request.getAttribute("pf_ref_id");
  }
  if(request.getAttribute("dateOfNotice")!=null){
      dateOfNotice=(String)request.getAttribute("dateOfNotice");
  }
  
  if(request.getAttribute("dateOfTransfer")!=null){
      dateOfTransfer=(String)request.getAttribute("dateOfTransfer");
  }
  if(request.getAttribute("venderName")!=null){
      venderName=(String)request.getAttribute("venderName");
  }
  if(request.getAttribute("purchaserName")!=null){
      purchaserName=(String)request.getAttribute("purchaserName");
  }
  if(request.getAttribute("amount")!=null){
      amount=(String)request.getAttribute("amount");
  }
  if(request.getAttribute("pf_ref_collection_id")!=null){
      pf_ref_collection_id=(String)request.getAttribute("pf_ref_collection_id");
  }
  if(request.getAttribute("piddata")!=null){
      piddata=(String)request.getAttribute("piddata");
  }
%>

<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>JSP Page</title>
    </head>
    <body>
        
  <section id="taxPaymentView" class="cont">
    <div class="container-fluid contf">
        <div class="row-fluid">

            <div class="clearfix"></div>                        
             <div class="propeh" align="center">Collect payment</div>
             <center><div><span style="color: red;"><%=MSG%></span></div> </center>
             
            <div class="clearfix"></div>
            <div class="row_top">
              
                <% if((pf_ref_id!=null) && (pf_ref_id.length())>0){%>
                <div class="span6 labeltxt"> Property transfer id : <%=pf_ref_id%> <span id="sl_payment_taxNo" ></span>
                <input type="hidden" name="propertyRefId" path="propertyRefId" id=propertyRefId" value="<%=pf_ref_id%>" />
               </div>
               
               <%}%>
            <% if(dateOfNotice.length()>0){%>
             
                 
                <div class="span6 labeltxt"> Date of notice : <%=dateOfNotice%><span id="sl_payment_propId" ></span>
                <%--<input type="hidden" name="datOfNotice" path="datOfNotice" id="datOfNotice" value="<%=dateOfNotice%>" />--%>
             </div>
            <%}%>
         
            <% if(dateOfTransfer.length()>0){%>
            
              
                <div class="span6 labeltxt"> Date of instrument : <%=dateOfTransfer%><span id="sl_payment_propId" ></span>
                <%--<input type="hidden" name="datOfInstrument" path="datOfInstrument" id="datOfInstrument" value="<%=dateOfTransfer%>" />--%>
           </div>
             <%}%>
          
             <% if(venderName.length()>0){%>
           
               
                <div class="span6 labeltxt"> Vendor name :  <%=venderName%><span id="sl_payment_propId" ></span></div>
                <%--<input type="hidden" name="venderName" path="venderName" id="venderName" value="<%=venderName%>" />--%>
        
            <%}%>
        
             <% if(purchaserName.length()>0){%>
             
            
                <div class="span6 labeltxt"> Purchaser name :<%=purchaserName%> <span id="sl_payment_propId" ></span></div>
               <%--<<input type="hidden" name="purchaserName" path="purchaserName" id="purchaserName" value="<%=purchaserName%>" />--%>
 
            <%}%>
        
           
            <% if(amount.length()>0){%>
          
  
                <div class="span4 labeltxt"> Property transfer amount :<%=amount%> <span id="sl_payable_amount" ></span></div>
                 <input type="hidden" name="demandAmount" path="demandAmount" id="demandAmount" value="<%=amount%>" />
       
            <%}%>
        
               <div class="clearfix"></div>     
       </div>
            
            <% if(pf_ref_collection_id.length()>0){%>
            <div class="span6 labeltxt"> Property transfer collection id : <%=pf_ref_collection_id%> <span id="sl_payment_taxNo" ></span>
                <input type="hidden" name="propertyRefId" path="propertyRefId" id=propertyRefId" value="<%=pf_ref_collection_id%>" />
               </div>
            <a href="propertyTransferWithPropertyId" class="btn btn-primary">Back</a> 
            <%}else{%>  
            
            <form:form class="propertyTransferC" modelAttribute="propertyTransferCollection" name="propertyTransferCollection" method="post" onsubmit="return validate();"  action="propertyTransferCollectionDataSave">
              <div class="clearfix"></div>
              <div class="form-group" >
                         <div class="tableCellCol  labeltxt">
                    <label class="labeltxt">Paying amount :  </label>
                    <input id="sl_payment_amount" name="sl_payment_amount" path="sl_payment_amount"   type="text"  min="0"  value="<%=amount%>" class="form-control currency" />
                </div>
             </div>
        
            <div class="clearfix"></div>
            <div class="form-group">
                <div class="tableCellCol labeltxt">&nbsp;</div> 
                <div class="tableCellCol labeltxt">
                    <label class="labeltxt">Payment mode </label>
                    <select  id="paymentMode" name="paymentMode" path="paymentMode" onchange="disablePaymentMode();" class="form-control">
                        <option value="-1">-- Select payment mode --</option>
                       <option value='CSH'>Cash</option>
                       <option value='CHQ'>Cheque</option>;
                       <option value='DDF'>Demand Draft</option>;
                    </select>
                </div>
            </div>
            <div class="clearfix"></div>
            <div class="form-group">
                <div class="tableCellCol labeltxt">&nbsp;</div> 
                <div class="tableCellCol labeltxt">
                    <label class="labeltxt">Check/DD number <span style="color: red;">*</span></label>
                    <input type="text" id="cheque_dd_num" name="chequeDDNum" path="chequeDDNum" class="form-control1" placeholder="" >
                </div>
            </div>
            <div class="clearfix"></div>
            <div class="form-group">
                <div class="tableCellCol labeltxt">&nbsp;</div> 
                <div class="tableCellCol labeltxt">
                    <label class="labeltxt">Check/DD issuing date <span style="color: red;">*</span></label>
                    <input type="text" id="cheque_dd_date" autocomplete="off" name="chequeDDDate" path="chequeDDDate" class="form-control1" placeholder="" >
                </div>
            </div>
            <div class="clearfix"></div>
            <div class="form-group"> 
                <div class="tableCellCol labeltxt">&nbsp;</div> 
                <div class="tableCellCol labeltxt">
                    <label class="labeltxt">Bank <span style="color: red;">*</span></label>
                    <select  id="enc_banks" name="bankName" path="bankName" class="form-control">
                        <option value="-1">-- Select bank --</option>
                    </select>
                </div> 
            </div>     
            <div class="clearfix"></div>
            <div class="form-group"> 
                <div class="tableCellCol labeltxt">&nbsp;</div> 
                <div class="tableCellCol labeltxt">
                    <label class="labeltxt">Bank branch<span style="color: red;">*</span></label>
                    <input type="text" id="banks_branch" name="bankBranch" path="bankBranch" class="form-control1" placeholder="">
                </div> 
            </div> 
            
             <div class="clearfix"></div>
            <div class="form-group"> 
                <div class="tableCellCol labeltxt">&nbsp;</div> 
                <div class="tableCellCol labeltxt">
                    <label class="labeltxt">Payer name<span style="color: red;">*</span></label>
                    <input type="text" id="payerName" name="payerName" path="payerName"  class="form-control1" placeholder="">
                </div> 
            </div> 
             
              <div class="clearfix"></div>
            <div class="form-group"> 
                <div class="tableCellCol labeltxt">&nbsp;</div> 
                <div class="tableCellCol labeltxt">
                    <label class="labeltxt">Contact no.<span style="color: red;">*</span></label>
                    <input type="text" id="contactNo" name="contactNo" path="contactNo" class="form-control1" placeholder="">
                </div> 
            </div>            
            

            <div class="clearfix"></div>
            <div class="form-group"> 
                <div class="tableCellCol labeltxt">&nbsp;</div> 
                <div class="tableCellCol labeltxt">
                    <label class="labeltxt">Note<span style="color: red;">*</span></label>
                    <textarea rows="6" id="payment_note" name="payment_note" path="payment_note" placeholder="Note related to payment, here..." class="form-control1" cols="200"> </textarea>
                </div>
            </div> 
        </div>            
        <div class="clearfix"></div>   
        
        
        <div class="form-group" style="margin-top:20px;">
            <div class="tableCellCol labeltxt"></div>
            <div class="tableCellCol labeltxt text-center p-5">
                <input type="hidden" name="propertyUniqueId" path="propertyUniqueId" value="<%=piddata%>"/>
                <input type="submit" class="btn btn-lg btn-primary" value="Submit">
            </div>
            <div class="span2"></div>
        </div>
                   
    </div>
    </form:form>
   <%}%>          
</div>


</section>
        
        
            
            
       
    </body>
    
    <script>
        $(document).ready(function () {
   
        $.ajax({
            type: "POST",
            url: "bankList",
            contentType: "application/json; charset=utf-8",
            success: function (data) {
                //console.log(data);
                var banks = JSON.parse(data);
                var strHTML = "<option value='-1'>-- Select Bank --</option>";
                for (var b in banks) {
                    var bank = banks[b];
                    strHTML += "<option value='" + bank.bankId + "'> " + bank.bankName + " </option>";
                  
                }
                $("#enc_banks").html(strHTML);
            },
            error: function () {
            }
        });
   $("#enc_cheque_dd_date").datepicker({dateFormat: 'yy-mm-dd'});
   });
   
   function validate(){
       var sl_payment_amount=$("#sl_payment_amount").val();
       var paymentMode=$("#paymentMode").val();
       var cheque_dd_num=$("#cheque_dd_num").val();
       var cheque_dd_date=$("#cheque_dd_date").val();
       var enc_banks=$("#enc_banks").val();
       var banks_branch=$("#banks_branch").val();
       var payerName=$("#payerName").val();
       var contactNo=$("#contactNo").val();
       var payment_note=$("#payment_note").val();
       var paymetPeriod=$("#paymetPeriod").val();
       //alert("paymentMode "+paymentMode);
       
           if(sl_payment_amount==''){
             alert("Amount  can't be left blank");
             $("#sl_payment_amount").focus();
             return false;
          }else if(sl_payment_amount!='' && (isNaN(sl_payment_amount) || sl_payment_amount < 1 )){
             alert("Amount must be numeric and greater than zero  ");
             $("#sl_payment_amount").focus();
             return false;
             
           }else if(paymentMode=='-1'){
             alert("Payment mode must be select  ");
             $("#paymentMode").focus();
             return false;
           }else if(paymentMode=='CSH'){
                
                $("#cheque_dd_num").attr("disabled", true);
                $("#cheque_dd_date").attr("disabled", true);
                $("#enc_banks").attr("disabled", true);
                $("#banks_branch").attr("disabled", true);
                  if(payerName==''){
                   alert("Payer name can't be left blank ");
                   $("#payerName").focus();
                   return false;
           }else if(contactNo==''){
                   alert("cotact no. can't be left blank ");
                   $("#contactNo").focus();
                   return false;
           }else if(contactNo!='' && contactNo.length!= 10 ){
                   alert("Cotact no. must be number and must be 10 digit ");
                   $("#contactNo").focus();
                   return false;
           }else if(payment_note.trim()=='' || payment_note.trim().length==0){
                   alert("Payment note can't be left blank ");
                   $("#payment_note").focus();
                   return false;
           }else if(paymetPeriod==''){
                   alert("Payment period can't be left blank ");
                   $("#paymetPeriod").focus();
                   return false;
           }
             return true;   
                
           }else if((paymentMode=='CHQ' || paymentMode=='DDF')&& (cheque_dd_num=='')){
                  alert("Cheque or demand draft number can't be left blank ");
                  $("#cheque_dd_num").focus();
                  $("#cheque_dd_num").attr("disabled", false);
                  $("#cheque_dd_date").attr("disabled", false);
                  $("#enc_banks").attr("disabled", false);
                  $("#banks_branch").attr("disabled", false);
                  return false;
           }else if((paymentMode=='CHQ' || paymentMode=='DDF')&& (cheque_dd_date=='')){
               alert("Cheque or demand draft date can't be left blank ");
                  $("#cheque_dd_date").focus();
                  $("#cheque_dd_num").attr("disabled", false);
                  $("#cheque_dd_date").attr("disabled", false);
                  $("#enc_banks").attr("disabled", false);
                  $("#banks_branch").attr("disabled", false);
                   return false;
           }else if((paymentMode=='CHQ' || paymentMode=='DDF')&& (enc_banks=='-1')){
                    alert("Bank name can't be left blank ");
                   $("#enc_banks").focus();
                   $("#cheque_dd_num").attr("disabled", false);
                   $("#cheque_dd_date").attr("disabled", false);
                   $("#enc_banks").attr("disabled", false);
                   $("#banks_branch").attr("disabled", false);
                   return false;
           }else if((paymentMode=='CHQ' || paymentMode=='DDF')&& (banks_branch=='')){
                   alert("Bank branch name can't be left blank ");
                   $("#banks_branch").focus();
                   $("#cheque_dd_num").attr("disabled", false);
                   $("#cheque_dd_date").attr("disabled", false);
                   $("#enc_banks").attr("disabled", false);
                   $("#banks_branch").attr("disabled", false);
                   return false;
           }else if(payerName==''){
                   alert("Payer name can't be left blank ");
                   $("#payerName").focus();
                   return false;
           }else if(contactNo==''){
                   alert("Contact no. can't be left blank ");
                   $("#contactNo").focus();
                   return false;
           }else if(contactNo!='' && contactNo.length!= 10 ){
                   alert("Contact no. must be number and must be 10 digit ");
                   $("#contactNo").focus();
                   return false;
           }else if(payment_note.trim()=='' || payment_note.trim().length==0){
                   alert("Payment note can't be left blank ");
                   $("#payment_note").focus();
                   return false;
           }else if(paymetPeriod==''){
                   alert("Payment period can't be left blank ");
                   $("#paymetPeriod").focus();
                   return false;
           }
           else{
               return true;
           }
       
       
   }
   function disablePaymentMode(){
      var paymentMode=$("#paymentMode").val();
      if(paymentMode=='CSH'){
                $("#cheque_dd_num").attr("disabled", true);
                $("#cheque_dd_date").attr("disabled", true);
                $("#enc_banks").attr("disabled", true);
                $("#banks_branch").attr("disabled", true);  
      }else{
                $("#cheque_dd_num").attr("disabled", false);
                $("#cheque_dd_date").attr("disabled", false);
                $("#enc_banks").attr("disabled", false);
                $("#banks_branch").attr("disabled", false);
      }
          
      
   }
         
         //$("#dateOfInstrument").datepicker({dateFormat: 'yy-mm-dd'});
         //$("#dateOfRegistration").datepicker({dateFormat: 'yy-mm-dd'});
         
    </script>
</html>
