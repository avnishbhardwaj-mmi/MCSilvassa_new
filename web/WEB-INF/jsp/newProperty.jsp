<%-- 
//----------------------------------------------------------------------------------------------------
//                          MapMyIndia
//            Product / Project           : Silvassa
//            Module                      : PropertyAdding
//            File Name                   : newProperty
//            Author                      : Jay Prakash Kumar & Syed Shujait hussain
//            Project Lead                :
//            Date written (DD/MM/YYYY)   : 6 Jul, 2017, 3:43:48 PM
//            Date Modify (DD/MM/YYYY)    : 25 June,2019, 3:43:48 PM
//            Description                 : 
//----------------------------------------------------------------------------------------------------
//                                            CHANGE HISTORY
//----------------------------------------------------------------------------------------------------
// Date             Change By           Change Description (Bug No. (If Any))
// (DD/MM/YYYY)
//----------------------------------------------------------------------------------------------------
//----------------------------------------------------------------------------------------------------
--%>
<link href="res/css/datatable2.css" rel="stylesheet" type="text/css" />
<link href="res/css/buttons.dataTables.min.css" rel="stylesheet" type="text/css" />
<link rel="stylesheet" type="text/css" href="res/css/select2.css" />
<link rel="stylesheet" type="text/css" href="res/css/select2-bootstrap.css" />
<link rel="stylesheet" href="res/css/jquery-ui.css">
<link rel="stylesheet" type="text/css" href="res/css/sumoselect.css" />
<script type="text/javascript" src="res/js/api/select2.min.js"></script>
<script type="text/javascript" src="res/js/api/jquery.dataTables.min.js"></script>
<script type="text/javascript" src="res/js/api/dataTables.buttons.min.js"></script>
<script type="text/javascript" src="res/js/api/buttons.flash.min.js"></script>
<script type="text/javascript" src="res/js/api/jszip.min.js"></script>
<script type="text/javascript" src="res/js/api/pdfmake.min.js"></script>
<script type="text/javascript" src="res/js/api/vfs_fonts.js"></script>
<script type="text/javascript" src="res/js/api/buttons.html5.min.js"></script>
<script type="text/javascript" src="res/js/api/buttons.print.min.js"></script>
<script type="text/javascript" src="res/js/api/jquery-dateFormat.js"></script>
<script type="text/javascript" src="res/js/util.js" type="text/javascript"></script>
<script type="text/javascript" src="res/js/newProperty.js?v1"></script>
<section class="row"  id="newProperty">

    <div class="col-lg-12 pl-4 " >
        <div class="innerdashboard">
            <div class="filterward  d-flex" >       
                <div class="propeh">Property Details</div>

                <div class="  text-right ml-auto">
                    <a id="updateProp" href="javascript: void(0)" class="btn btn-primary" onclick="NewPro.addNewProperty();">Submit</a>
                </div>
            </div>
            <div class="filterother">
                <div id="accordion">

                    <div class="card">
                        <div class="card-header">
                            <a class="card-link" data-toggle="collapse" href="#OwnerDetails">
                                Owner Details <span class="material-icons right-arrow">keyboard_arrow_down</span>
                            </a>
                        </div>
                        <div id="OwnerDetails" class="collapse show" >
                            <div class="card-body">
                                <div class="row">
                                    <div class="col-md-3">
                                        <div class="form-group">
                                            <label class="labeltxt">Property ID:<span style="color: red;">*</span> </label> 
                                            <input type="text" id="propertyUniqueId"  class="form-control" placeholder="">
                                        </div>
                                    </div>
                                    <div class="col-md-3">
                                        <div class="form-group">
                                            <label class="labeltxt">Owner Name:  </label> 
                                            <input type="text" id="propertyOwner"  class="form-control" placeholder="">
                                        </div>
                                    </div>
                                    <div class="col-md-3">
                                        <div class="form-group">
                                            <label class="labeltxt">Occupier Name: </label> 
                                            <input type="text" id="propertyOccupierName"  class="form-control" placeholder="">
                                        </div>
                                    </div>
                                    <div class="col-md-3">
                                        <div class="form-group">
                                            <label class="labeltxt">Relation With Owner:</label> 
                                            <input  id="propertyRelationOwner" type="text" class="form-control" placeholder="">
                                        </div>
                                    </div>




                                    <div class="col-md-3">
                                        <div class="form-group">
                                            <label class="labeltxt">Owner Spouse Name: </label> 
                                            <input type="text" id="propertyOwnerSpouse"  class="form-control" placeholder="">
                                        </div>
                                    </div>
                                    <div class="col-md-3">
                                        <div class="form-group">
                                            <label class="labeltxt">Owner Email: </label> 
                                            <input type="text" id="propertyOwnerEmail"  class="form-control" placeholder="">
                                        </div>
                                    </div>
                                    <div class="col-md-3">
                                        <div class="form-group">
                                            <label class="labeltxt">Owner MobileNo: </label> 
                                            <input type="text" id="propertyContact"  class="form-control" placeholder="">
                                        </div>
                                    </div>
                                    <div class="col-md-3">
                                        <div class="form-group">
                                            <label class="labeltxt">Owner Adhar Card No: </label> 
                                            <input type="text" id="propertyAadharNum"  class="form-control" placeholder="">
                                        </div>
                                    </div>
                                </div>
                            </div>
                        </div>
                    </div>
                    <div class="card">
                        <div class="card-header">
                            <a class="collapsed card-link" data-toggle="collapse" href="#ResidingPersonnel">
                                Other Residing Personnel <span class="material-icons right-arrow">keyboard_arrow_down</span>
                            </a>
                        </div>
                        <div id="ResidingPersonnel" class="collapse" >

                            <div class="card-body">
                                <div class="row">
                                    <div class="col-md-3">
                                        <div class="form-group">
                                            <label class="labeltxt">No. of Person:</label>
                                            <input type="text"  id="propertyNumOfPersons" class="form-control" placeholder="">
                                        </div>
                                    </div>
                                    <div class="col-md-3">
                                        <div class="form-group">
                                            <label class="labeltxt">Male(+18):</label> 
                                            <input type="text"  id="propertyMale18Plus" class="form-control" placeholder="">
                                        </div>
                                    </div>
                                    <div class="col-md-3">
                                        <div class="form-group">
                                            <label class="labeltxt">Male(-18):</label> 
                                            <input type="text"  id="propertyMale18Minus" class="form-control" placeholder="">
                                        </div>
                                    </div>
                                    <div class="col-md-3">
                                        <div class="form-group">
                                            <label class="labeltxt">Female(+18):</label> 
                                            <input type="text"  id="propertyFem18Plus" class="form-control" placeholder="">
                                        </div>
                                    </div>
                                </div>
                                <div class="row">
                                    <div class="col-md-3">
                                        <div class="form-group">
                                            <label class="labeltxt">Female(-18):</label> 
                                            <input type="text"  id="propertyFem18Minus" class="form-control" placeholder="">
                                        </div>
                                    </div>
                                    <div class="col-md-3">
                                        <div class="form-group">
                                            <label class="labeltxt">Name1:</label> 
                                            <input type="text"  id="propertyName1" class="form-control" placeholder="">
                                        </div>
                                    </div>
                                    <div class="col-md-3">
                                        <div class="form-group">
                                            <label class="labeltxt">Name2:</label> 
                                            <input type="text"  id="propertyName2" class="form-control" placeholder="">
                                        </div>
                                    </div>
                                    <div class="col-md-3">
                                        <div class="form-group">
                                            <label class="labeltxt">Name3:</label> 
                                            <input type="text"  id="propertyName3" class="form-control" placeholder="">
                                        </div>
                                    </div>

                                    <div class="col-md-3">
                                        <div class="form-group">
                                            <label class="labeltxt">Name4:</label> 
                                            <input type="text"  id="propertyName4" class="form-control" placeholder="">
                                        </div>
                                    </div>
                                    <div class="col-md-3">
                                        <div class="form-group">
                                            <label class="labeltxt">Name5:</label> 
                                            <input type="text"  id="propertyName5" class="form-control" placeholder="">
                                        </div>
                                    </div>
                                    <div class="col-md-3">
                                        <div class="form-group">
                                            <label class="labeltxt">Name6:</label> 
                                            <input type="text"  id="propertyName6" class="form-control" placeholder="">
                                        </div>
                                    </div>
                                </div>
                            </div>
                        </div>

                    </div>

                    <div class="card">
                        <div class="card-header">
                            <a class="collapsed card-link" data-toggle="collapse" href="#PropertyAddress">
                                Property Address <span class="material-icons right-arrow">keyboard_arrow_down</span>
                            </a>
                        </div>
                        <div id="PropertyAddress" class="collapse" >
                            <div id="PropertyAddress" class="tab-pane">
                                <div class="card-body">
                                    <div class="row"> 
                                        <!--                                        <div class="col-md-3">
                                                                                    <div class="form-group">
                                                                                        <label class="labeltxt">Zone <span style="color: red;">*</span>  </label> 
                                                                                        <select class="form-control" name="zone" id="zoneId" >
                                                                                            <option value="-1">--Select Zone--</option>
                                                                                        </select>
                                                                                        <input type="text" id="zoneId" class="form-control" placeholder="">
                                                                                    </div>
                                                                                </div>-->
                                        <div class="col-md-3">
                                            <div class="form-group">
                                                <label class="labeltxt">Ward<span style="color: red;">*</span> </label> 
                                                <select class="form-control" name="ward" id="ward" >
                                                    <option value="-1">--Select Ward--</option>
                                                </select>
                                            </div>
                                        </div>
                                        <div class="col-md-3">
                                            <div class="form-group">
                                                <label class="labeltxt">Plot/House No. </label> 
                                                <input id="propertyHouseNo" type="text" class="form-control" placeholder="">
                                            </div>
                                        </div>
                                        <div class="col-md-3">
                                            <div class="form-group">
                                                <label class="labeltxt">Building Name:</label> 
                                                <input type="text" id="propertyBuildingName" class="form-control" placeholder="">
                                            </div>
                                        </div>

                                        <div class="col-md-3">
                                            <div class="form-group">
                                                <label class="labeltxt">Road Name/Street Name:</label> 
                                                <input type="text" class="form-control" id="property_road" placeholder="">
                                            </div>
                                        </div>

                                    </div>
                                    <div class="row">

                                        <div class="col-md-3">
                                            <div class="form-group">
                                                <label class="labeltxt">Locality Name:</label> 
                                                <input type="text" id="propertyLocality" class="form-control" placeholder="">
                                            </div>
                                        </div><div class="col-md-3">
                                            <div class="form-group">
                                                <label class="labeltxt">Sub Locality:</label> 
                                                <input type="text" id="propertyRoad" class="form-control" placeholder="">
                                            </div>
                                        </div>

                                        <div class="col-md-3">
                                            <div class="form-group">
                                                <label class="labeltxt">Nearest Landmark:</label> 
                                                <input id="propertyLandmark" type="text" class="form-control" placeholder="">
                                            </div>
                                        </div>
                                        <div class="col-md-3">
                                            <div class="form-group">
                                                <label class="labeltxt">Survey No. </label> 
                                                <input id="propertySurveyId" type="text" class="form-control" placeholder="">
                                            </div>
                                        </div>

                                    </div>
                                    <div class="row">

                                        <div class="col-md-3">
                                            <div class="form-group">
                                                <label class="labeltxt">Current Status of Property:</label> 
                                                <input type="text" id="propertyStatus" class="form-control" placeholder="">
                                            </div>
                                        </div>
                                        <div class="col-md-3">
                                            <div class="form-group">
                                                <label class="labeltxt">Pin Code:</label> 
                                                <input type="text" class="form-control" id="propertyPincode" placeholder="">
                                            </div>
                                        </div>
                                        <div class="col-md-3">
                                            <div class="form-group">
                                                <label class="labeltxt">Property Latitude: <span style="color: red;">*</span> </label> 
                                                <input type="text" class="form-control" id="propertyLatitude" placeholder="">
                                            </div>
                                        </div>
                                        <div class="col-md-3">
                                            <div class="form-group">
                                                <label class="labeltxt">Property Longitude: <span style="color: red;">*</span> </label> 
                                                <input type="text" class="form-control" id="propertyLongitude" placeholder="">
                                            </div>
                                        </div>
                                    </div>
                                    <div class="row">

                                    </div>
                                </div>
                            </div>
                        </div>
                    </div>
                    <div class="card">
                        <div class="card-header">
                            <a class="collapsed card-link" data-toggle="collapse" href="#CivicAmenities">
                                Property Details & Civic Amenities <span class="material-icons right-arrow">keyboard_arrow_down</span>
                            </a>
                        </div>
                        <div id="CivicAmenities" class="collapse" >

                            <div class="card-body">
                                <div class="row">
                                    <div class="col-md-3">
                                        <div class="form-group">
                                            <label class="labeltxt">Total No. of floors:</label> 
                                            <input  id="propertyTotalFloor" type="text" class="form-control" placeholder="">
                                        </div>
                                    </div>
                                    <div class="col-md-3">
                                        <div class="form-group">
                                            <label class="labeltxt">No. of Rooms:</label> 
                                            <input type="text"  id="propertyNumOfRooms" class="form-control" placeholder="">
                                        </div>
                                    </div>
                                    <div class="col-md-3 d-none">
                                        <div class="form-group">
                                            <label class="labeltxt">School &amp; Colleges:</label> 
                                            <input type="text"  id="propertyRoomCntSchoolClg" class="form-control" placeholder="">
                                        </div>
                                    </div>
                                    <div class="col-md-3 d-none">
                                        <div class="form-group">
                                            <label class="labeltxt">Hospital &amp; Nursing Home:</label> 
                                            <input type="text"  id="propertyRoomCntHospiNurse" class="form-control" placeholder="">
                                        </div>
                                    </div>

                                    <div class="col-md-3 d-none">
                                        <div class="form-group">
                                            <label class="labeltxt">Hotels:</label> 
                                            <input type="text"  id="propertyRoomCntHostel" class="form-control" placeholder="">
                                        </div>
                                    </div>
                                    <div class="col-md-3">
                                        <div class="form-group">
                                            <label class="labeltxt">Plot Area</label>
                                            <input type="text"  id="propertyPlotArea" class="form-control" placeholder="">
                                        </div>
                                    </div>
                                    <div class="col-md-3">
                                        <div class="form-group">
                                            <label class="labeltxt">Cost of Property:</label> 
                                            <input id="propertyCost" type="text" class="form-control" placeholder="">
                                        </div>
                                    </div>
                                </div>
                            </div>
                        </div>
                    </div>
                    <div class="card">
                        <div class="card-header">
                            <a class="collapsed card-link" data-toggle="collapse" href="#TaxDetails">
                                Tax Details <span class="material-icons right-arrow">keyboard_arrow_down</span>
                            </a>
                        </div>
                        <div id="TaxDetails" class="collapse" >
                            <div class="card-body extra_padding">
                                <div class="row" id="floorDetatil">
                                    <div class="col-md-3">
                                        <div class="form-group">
                                            <label class="labeltxt">Floor :</label>
                                            <input type="text"  id="pfFloorName" class="form-control" placeholder="">
                                        </div>
                                    </div>
                                    <div class="col-md-3">
                                        <div class="form-group">
                                            <label class="labeltxt">BuiltUp Area :</label>
                                            <input type="text"  id="pfBuiltupArea" class="form-control txtboxOnlyNumeric" placeholder="">
                                        </div>
                                    </div>
                                    <div class="col-md-3">
                                        <div class="form-group">
                                            <label class="labeltxt">Construction Type :</label>
                                            <select class="form-control" id="pfConstructionType">
                                                <option value="-1">--Select Use--</option>
                                                <option value="ASB">ASB</option>
                                                <option value="RCC">RCC</option>
                                            </select>
                                        </div>
                                    </div>
                                    <div class="col-md-3">
                                        <div class="form-group">
                                            <label class="labeltxt">Use :<span style="color: red;">*</span> </label> 
                                            <select class="form-control" id="pfFloorwiseBuildUse">
                                                <option value="-1">--Select Use--</option>
                                            </select>
                                        </div>
                                    </div>
                                    <div class="col-md-3">
                                        <div class="form-group">
                                            <label class="labeltxt">Water Conn. :</label>
                                            <select class="form-control" id="pfWaterPipeCon">
                                                <option value="-1">--Please Select--</option>
                                                <option value="YES">Yes</option>
                                                <option value="NO">No</option>
                                            </select>
                                        </div>
                                    </div>
                                    <div class="col-md-3">
                                        <div class="form-group">
                                            <label class="labeltxt">Sewage Conn. :</label>
                                            <select class="form-control" id="pfSewerageCon">
                                                <option value="-1">--Please Select--</option>
                                                <option value="YES">Yes</option>
                                                <option value="NO">No</option>
                                            </select>
                                        </div>
                                    </div>
                                    <div class="col-md-3">
                                        <div class="form-group">
                                            <label class="labeltxt">Electric Meter No. :</label>
                                            <input type="text"  id="pfElectricMeterNum" class="form-control" placeholder="">
                                        </div>
                                    </div>
                                    <div class="col-md-3">
                                        <div class="form-group">
                                            <label class="labeltxt">Electric Conn. No. :</label>
                                            <input type="text"  id="pfElectricConNum" class="form-control" placeholder="">
                                        </div>
                                    </div>
                                    <div class="col-md-3">
                                        <div class="form-group">
                                            <label class="labeltxt">CCTV :</label>
                                            <select class="form-control" id="pfCctvCamrea">
                                                <option value="-1">--Please Select--</option>
                                                <option value="YES">Yes</option>
                                                <option value="NO">No</option>
                                            </select>
                                        </div>
                                    </div>
                                    <div class="col-md-3">
                                        <div class="form-group">
                                            <label class="labeltxt">Fire Equipment :</label>
                                            <select class="form-control" id="pfFireEquipment">
                                                <option value="-1">--Please Select--</option>
                                                <option value="YES">Yes</option>
                                                <option value="NO">No</option>
                                            </select>
                                        </div>
                                    </div>
                                    <div class="col-md-3">
                                        <div class="form-group">
                                            <label class="labeltxt">Lift Available :</label>
                                            <select class="form-control" id="pfLiftAvailable">
                                                <option value="-1">--Please Select--</option>
                                                <option value="YES">Yes</option>
                                                <option value="NO">No</option>
                                            </select>
                                        </div>
                                    </div>
                                    <div class="col-md-3">
                                        <div class="form-group">
                                            <label class="labeltxt">RainWater Harvesting :</label>
                                            <select class="form-control" id="pfRainWaterHarvest">
                                                <option value="-1">--Please Select--</option>
                                                <option value="YES">Yes</option>
                                                <option value="NO">No</option>
                                            </select>
                                        </div>
                                    </div>
                                    <div class="col-md-3">
                                        <div class="form-group">
                                            <label class="labeltxt">No. of Borewells : </label>
                                            <input type="text"  id="pfNumOfBorewells" class="form-control txtboxOnlyNumeric" placeholder="">
                                        </div>
                                    </div>
                                    <div class="col-md-3">
                                        <div class="form-group">
                                            <label class="labeltxt">Sanitation :</label>
                                            <select class="form-control" id="pfSanitation">
                                                <option value="-1">--Please Select--</option>
                                                <option value="YES">Yes</option>
                                                <option value="NO">No</option>
                                            </select>
                                        </div>
                                    </div>
                                    <div class="col-md-3">
                                        <div class="form-group">
                                            <label class="labeltxt">Hoarding :</label>
                                            <select class="form-control" id="pfHordingAvail">
                                                <option value="-1">--Please Select--</option>
                                                <option value="YES">Yes</option>
                                                <option value="NO">No</option>
                                            </select>
                                        </div>
                                    </div>
                                    <div class="col-md-3">
                                        <div class="form-group">
                                            <label class="labeltxt">Mobile tower :</label>
                                            <select class="form-control" id="pfMobileTower">
                                                <option value="-1">--Please Select--</option>
                                                <option value="YES">Yes</option>
                                                <option value="NO">No</option>
                                            </select>
                                        </div>
                                    </div>
                                    <div class="col-md-3 text-left">   <label class="labeltxt d-block"> &nbsp;</label>
                                        <a id="updateProp" href="javascript: void(0)" class="btn btn-primary" onclick="NewPro.addFloorDetailsToTable();">Add to Submit</a>
                                    </div>
                                </div>

                                <div class="custom_table">
                                    <div class="propeh">Floor wise details</div>
                                    <div class="scroll_table">
                                        <table id="floorDetatilsTable" class="table table-bordered table-sm dataTable no-footer">
                                            <thead>
                                                <tr>
                                                    <th>Sno.</th>
                                                    <th>Floor</th><th>BuiltUp Area</th><th>Constr. Type</th><th>Use</th><th>Water Conn.</th><th>Sewage Conn.</th><th>Electric Meter No.</th><th>Electric Conn. No.</th><th>CCTV</th><th>Fire Equipment</th><th>Lift Available</th><th>RainWater Harvesting</th><th>No. of Borewells</th><th>Sanitation</th><th>Hoarding</th><th>Mobile tower</th><th>Action</th>
                                                </tr>
                                            </thead>
                                            <tbody>
                                                <!--<tr><td>1</td><td>GF</td><td>13.51</td><td>ASB</td><td>COMMERCIAL</td><td>YES</td><td>NO</td><td>DNH-003651</td><td>SH/24022</td><td>NO</td><td>NO</td><td>NO</td><td>NO</td><td>0</td><td>YES</td><td>NO</td><td>NO</td></tr>-->
                                            </tbody>
                                        </table>
                                    </div>
                                </div>
                            </div>
                        </div>
                    </div>


                    <!--                    <div class="tab_listSilVa">
                                            <ul class="nav nav-tabs">
                                                <li class="nav-item">
                                                    <a class="nav-link active" data-toggle="tab" href="#OwnerDetails">Owner Details</a>
                                                </li>
                                                <li class="nav-item">
                                                    <a class="nav-link" data-toggle="tab" href="#ResidingPersonnel">Other Residing Personnel </a>
                                                </li>
                                                <li class="nav-item">
                                                    <a class="nav-link" data-toggle="tab" href="#PropertyAddress">Property Address</a>
                                                </li>
                                                <li class="nav-item">
                                                    <a class="nav-link" data-toggle="tab" href="#CivicAmenities">Property Details & Civic Amenities</a>
                                                </li>
                                                <li class="nav-item">
                                                    <a class="nav-link" data-toggle="tab" href="#TaxDetails">Tax Details</a>
                                                </li>
                    
                                            </ul>
                    
                    
                    
                                            <div class="tab-content">
                                                <div id="OwnerDetails" class="tab-pane active">
                    
                                                </div>
                                                <div id="ResidingPersonnel" class="tab-pane">
                    
                                                </div>
                                                <div id="CivicAmenities" class="tab-pane">
                                                    <div class="panel-body">
                    
                                                    </div>
                                                </div>
                                                <div id="TaxDetails"  class="tab-pane">
                    
                                                </div>
                    
                                            </div> 
                                            <div class="row">
                    
                                                </div>
                                            </div>
                                        </div>-->

                    <!--<div class="panel panel-default">-->
                </div>
            </div>
        </div>
    </div>
</section>
<script>
    $(document).ready(function () {
        $("#objection_main_menu").addClass("active");
        $("#AddProperty").addClass("active");
        $("#objection_main_menu ul.sub-menu ").addClass("show");
        if ($("#contactDetails ul.sub-menu").hasClass("show"))
        {
            $("#contactDetails ul.sub-menu").removeClass("show");
        }

        $("#breadlist").addClass('lisb');
        $("#breadlist").html('')
        $("#breadlist").html('<li><a href="property">Property</a></li>')
    });
</script>