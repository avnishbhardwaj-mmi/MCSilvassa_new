
<style>
    
    .business_entities_list li{list-style: none; }
        .business_entities_list {margin:0px; padding:0px 10px;}
           .business_entities_list input[type="checkbox"]{margin-right: 5px;}
    
</style>

<div class="modal fade" id="export-modal" tabindex="-1" role="dialog" aria-labelledby="myModalLabel" aria-hidden="true" style="display: none;">
    <div class="modal-dialog">
        <div class="modal-content">
            <div class="modal-header">
                <h4 class="modal-title">Select Content</h4>
                <button type="button" class="close" data-dismiss="modal">&times;</button>
            </div>   
            <div class="modal-body">         <div class="business_entities" id="export_content" >
                    <ul class="business_entities_list" id="business_entities_list"> 

                    </ul>
                </div>
            </div>
            <div class="modal-footer">
                <div>
                    <form method="POST"  id="expo" action="downloadMaster.htm">
                        <input type="hidden" id="exportParams" name="params">
                        <input type="hidden" id="exportTHead" name="tHead">
                        <input type="hidden" id="exportAttrToAdd" name="attrToAdd">
                        <input type="hidden" id="exportType" name="type">
                        <input type="hidden" id="exportTitle" name="title">
                        <input type="hidden" id="windowId" name="windowId" value="" />  
                    </form>
                    <button id="export_btn"  class="btn btn-primary btn-sm btn-block" onclick="" >Export</button>
                </div>
            </div>
        </div>
    </div>
</div>