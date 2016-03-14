<!doctyle html>
<html>
<head>
	<title>Place order</title>
	<meta name="viewport" content="width=device-width, initial-scale=1">
	<link href="css/bootstrap.css" rel="stylesheet">
	<link rel="stylesheet" type="text/css" href="css/admin.css" >
	<script src="js/jquery.1.11.0.js"></script>
	<script src="js/bootstrap.js"></script>
	<script src="js/vendorPage.js"></script>
	<script src="https://cdn.datatables.net/1.10.10/js/jquery.dataTables.min.js"></script>
	<script src="https://cdn.datatables.net/1.10.10/js/dataTables.bootstrap.min.js"></script>
	<style type="text/css">
		.edit-menu img{
			width: 25px;
		}
		html{
			overflow-x: hidden;
		}
	</style>
	 <script type="text/javascript">
        window.onload = function () {

            CanvasJS.addColorSet("greenShades",
                        [//colorSet Array
                            "#2F4F4F",
                            "#008080",
                            "#2E8B57",
                            "#3CB371",
                            "#90EE90"                
                        ]);
            var chart = new CanvasJS.Chart("chartContainer",
            {
                colorSet: "greenShades",
                title:{
                    text: "",
                    fontFamily: "arial black"
                },
                animationEnabled: true,
                legend: {
                    verticalAlign: "center",
                    horizontalAlign: "right",
                    fontSize:18
                },
                theme: "theme1",
                data: [
                {        
                    type: "pie",
                    indexLabelFontFamily: "Arial",       
                    indexLabelFontSize: 20,
                    indexLabelFontWeight: "bold",
                    startAngle:0,
                    indexLabelFontColor: "white",       
                    indexLabelLineColor: "darkgrey", 
                    indexLabelPlacement: "inside", 
                    toolTipContent: "{name}: {y}%",
                    showInLegend: true,
                    indexLabel: "#percent%", 
                    dataPoints: [
                        {  y: 35, name: "Payment History", legendMarkerType: "square"},
                        {  y: 30, name: "Amount you owe", legendMarkerType: "square"},
                        {  y: 15, name: "Length of Credit history", legendMarkerType: "square"},
                        {  y: 10, name: "New Credit opened", legendMarkerType: "square"},
                        {  y: 10, name: "Types of credit you have", legendMarkerType: "square"}
                    ]
                }
                ]
            });
            chart.render();
 }
 $(document).ready(function (e) {
  $('.profile-edit').click(function(e) {
if($(this).hasClass('edit-info')) {
	   $(this).parents('.profile-content-list').find('li.label-value').each(function(){
$(this).parents('.profile-content-list').find('li.label-value')[0].focus();
	   $(this).attr('contenteditable','true');
	   })
	   	   $(this).removeClass('edit-info').addClass('update-info');
		   }
		   else {
		   	   $(this).parents('.profile-content-list').find('li.label-value').each(function(){
	   $(this).attr('contenteditable','false');
	   });
	    $(this).removeClass('update-info').addClass('edit-info');
		   }
	});
});
        </script>
        <script type="text/javascript" src="js/canvasjs.min.js"></script>
</head>
<body>
<div class="wrap">

         <header class="navbar landing-header hidden-xs">
			<img href=""/>
		 </header>
<div class="container">
<div class="row">
  <div class="col-sm-3" id="myVendor">
    <div class="sidebar-nav">
      <div class="navbar navbar-default" role="navigation">
        <div class="navbar-header">
          <button type="button" class="navbar-toggle" data-toggle="collapse" data-target=".sidebar-navbar-collapse">
            <span class="sr-only">Toggle navigation</span>
            <span class="icon-bar"></span>
            <span class="icon-bar"></span>
            <span class="icon-bar"></span
			>
          </button>
          <span class="visible-xs navbar-brand">View menu</span>
        </div>
        <div class="navbar-collapse collapse sidebar-navbar-collapse">
         <ul class="nav nav-pills nav-stacked vendor-list">
			<li role="presentation" class="active"><a href="#">Menu</a></li>
			<li role="presentation"><a href="#">Transaction Details</a></li>
			<li role="presentation"><a href="#">Comaprison chart</a></li>
			<!-- <li role="presentation">
				<a href="#myModal" role="button" data-toggle="modal"> 
					<img src="images/add-user.jpg" alt="add vendor" class="add-vendor"/>
					<span class="sr-only">Create new</span> 
				</a>
			</li> -->
	</ul>
        </div><!--/.nav-collapse -->
      </div>
    </div>
	
  </div>
  <div class="col-sm-9">
    <div class="row"> 

      <div class="tab-pane fade active in" id="home">
         <h2> Menu Details </h2>
		 <a class="btn btn-success btn-lg" href="#addMenu" role="button" data-toggle="modal"><span class="glyphicon glyphicon-plus"></span> Add Menu</a>
          <table id="example1" class="table table-striped" cellspacing="0" width="100%">
        <!-- <thead>
            <tr>
                <th>Dish Name</th>
				<th>Description</th>
                <th>Price</th>
                <th>Quantity</th>
                <th>Active</th>
                <th>Actions</th>
            </tr>
        </thead>
        <tbody>
            <tr>
                <td>Mini Meals</td>
                <td>Sambar rice, Lemon rice curd rice..</td>
                <td>40</td>
                <td>100</td>
                <td>Yes</td>
                <td>
					<a href="#" class="edit-menu"><img src="images/edit-menu.png" alt="Edit" /></a>
					<a  href="#deleteMenu" role="button" data-toggle="modal" class="delete-menu"><img src="images/delete-menu.png" alt="Delete" /></a>
				</td>
            </tr>
			  <tr>
                <td>Mini Meals</td>
                <td>Sambar rice, Lemon rice curd rice..</td>
                <td>40</td>
                <td>100</td>
                <td>Yes</td>
                <td>
					<a href="#" class="edit-menu"><img src="images/edit-menu.png" alt="Edit" /></a>
					<a  href="#deleteMenu" role="button" data-toggle="modal" class="delete-menu"><img src="images/delete-menu.png" alt="Delete" /></a>
				</td>
            </tr>
              <tr>
                <td>Combo 1</td>
                <td>Sambar rice,..</td>
                <td>40</td>
                <td>100</td>
                <td>Yes</td>
                <td>
					<a href="#" class="edit-menu"><img src="images/edit-menu.png" alt="Edit" /></a>
					<a  href="#deleteMenu" role="button" data-toggle="modal" class="delete-menu"><img src="images/delete-menu.png" alt="Delete" /></a>
				</td>
            </tr>
              <tr>
                <td>Combo 2</td>
                <td>Sambar rice, Lemon rice curd rice..</td>
                <td>40</td>
                <td>100</td>
                <td>Yes</td>
                <td>
					<a href="#" class="edit-menu"><img src="images/edit-menu.png" alt="Edit" /></a>
					<a  href="#deleteMenu" role="button" data-toggle="modal" class="delete-menu"><img src="images/delete-menu.png" alt="Delete" /></a>
				</td>
            </tr>
            </tbody> -->
    </table>
                  </div>
      </div>
	   <div class="tab-pane fade displayNone" id="profile">
          <h2>Transaction Details</h2>
          <table id="example" class="table table-striped table-bordered" cellspacing="0" width="100%">
        <thead>
            <tr>
                <th>Name</th>
                <th>Position</th>
                <th>Office</th>
                <th>Age</th>
                <th>Start date</th>
                <th>Salary</th>
            </tr>
        </thead>
        <tbody>
            <tr>
                <td>Tiger Nixon</td>
                <td>System Architect</td>
                <td>Edinburgh</td>
                <td>61</td>
                <td>2011/04/25</td>
                <td>$320,800</td>
            </tr>
            <tr>
                <td>Garrett Winters</td>
                <td>Accountant</td>
                <td>Tokyo</td>
                <td>63</td>
                <td>2011/07/25</td>
                <td>$170,750</td>
            </tr>
            <tr>
                <td>Ashton Cox</td>
                <td>Junior Technical Author</td>
                <td>San Francisco</td>
                <td>66</td>
                <td>2009/01/12</td>
                <td>$86,000</td>
            </tr>
            <tr>
                <td>Cedric Kelly</td>
                <td>Senior Javascript Developer</td>
                <td>Edinburgh</td>
                <td>22</td>
                <td>2012/03/29</td>
                <td>$433,060</td>
            </tr>
            <tr>
                <td>Airi Satou</td>
                <td>Accountant</td>
                <td>Tokyo</td>
                <td>33</td>
                <td>2008/11/28</td>
                <td>$162,700</td>
            </tr>
            <tr>
                <td>Brielle Williamson</td>
                <td>Integration Specialist</td>
                <td>New York</td>
                <td>61</td>
                <td>2012/12/02</td>
                <td>$372,000</td>
            </tr>
            <tr>
                <td>Herrod Chandler</td>
                <td>Sales Assistant</td>
                <td>San Francisco</td>
                <td>59</td>
                <td>2012/08/06</td>
                <td>$137,500</td>
            </tr>
            <tr>
                <td>Rhona Davidson</td>
                <td>Integration Specialist</td>
                <td>Tokyo</td>
                <td>55</td>
                <td>2010/10/14</td>
                <td>$327,900</td>
            </tr>
            <tr>
                <td>Colleen Hurst</td>
                <td>Javascript Developer</td>
                <td>San Francisco</td>
                <td>39</td>
                <td>2009/09/15</td>
                <td>$205,500</td>
            </tr>
			<tr>
                <td>Colleen Hurst</td>
                <td>Javascript Developer</td>
                <td>San Francisco</td>
                <td>39</td>
                <td>2009/09/15</td>
                <td>$205,500</td>
            </tr>
			<tr>
                <td>Colleen Hurst</td>
                <td>Javascript Developer</td>
                <td>San Francisco</td>
                <td>39</td>
                <td>2009/09/15</td>
                <td>$205,500</td>
            </tr>
			<tr>
                <td>Colleen Hurst</td>
                <td>Javascript Developer</td>
                <td>San Francisco</td>
                <td>39</td>
                <td>2009/09/15</td>
                <td>$205,500</td>
            </tr>
          
        </tbody>
    </table>
      </div>
      <div class="tab-pane fade displayNone" id="messages">
         
           <div class="container">
            <div class="row-fluid">
                <div class="col-md-8 col-lg-8 text-center">
                    <h3> Comparison on the transaction details on vendors</h3> 
                </div>    
            </div>
            <div class="row-fluid">
                <div class="col-md-8 col-lg-8 text-center">
                    <div id="chartContainer" style="height:400px"></div>
                </div>    
            </div>    
			</div>
      </div>
    
	</div>
	
	<div id="addMenu" class="modal fade">
    <div class="modal-dialog">
        <div class="modal-content">
            <div class="modal-header">
                <button type="button" class="close" data-dismiss="modal" aria-hidden="true">&times;</button>
                <h2 class="modal-title">Enter Menu details</h2>
            </div>
            <div class="modal-body">
                <div class="row">
    <div class="col-xs-12 col-sm-10 col-md-10 col-sm-offset-2 col-md-offset-2">
		<form role="form">
		
			<div class="row">
				
					<div class="form-group row">
					<label for="vendor_name" class="control-label col-xs-3"> Dish Name </label>
					<div class="col-xs-8">
                        <input type="text" name="addMenuName" id="addMenuName" class="form-control" >
					</div>
					</div>
					
					<div class="form-group row">
					<label for="vendor_id" class="control-label col-xs-3"> Description</label>
					<div class="col-xs-8">	
					<input type="text" name="addMenuDesc" id="addMenuDesc" class="form-control">
					</div>
					</div>
			
			<div class="form-group row">
			<label for="poc" class="control-label col-xs-3">Price</label>
			<div class="col-xs-8">
				<input type="text" name="addMenuPrice" id="addMenuPrice" class="form-control" >
			</div>
			</div>
			<div class="form-group row">
				<label for="email" class="control-label col-xs-3">Quantity</label>
				<div class="col-xs-8">
				<input type="text" name="addMenuQuantity" id="addMenuQuantity" class="form-control" >
			</div>
			</div>
			</div>
		</form>
		</div>
	</div>
	</div>
            
            <div class="modal-footer">
                <button type="button" class="btn btn-default" data-dismiss="modal">Close</button>
                <button type="button" class="btn btn-primary">Add Menu</button>
            </div>
        </div>
    </div>
	</div>
</div>
<div id="updateMenu" class="modal fade">
    <div class="modal-dialog">
        <div class="modal-content">
            <div class="modal-header">
                <button type="button" class="close" data-dismiss="modal" aria-hidden="true">&times;</button>
                <h2 class="modal-title">Update Menu details</h2>
            </div>
            <div class="modal-body">
                <div class="row">
    <div class="col-xs-12 col-sm-10 col-md-10 col-sm-offset-2 col-md-offset-2">
		<form role="form">
		
			<div class="row">
				
					<div class="form-group row">
					<label for="vendor_name" class="control-label col-xs-3"> Dish Name </label>
					<div class="col-xs-8">
                        <input type="text" name="updateMenuName" id="updateMenuName" class="form-control" >
					</div>
					</div>
					
					<div class="form-group row">
					<label for="vendor_id" class="control-label col-xs-3"> Description</label>
					<div class="col-xs-8">	
					<input type="text" name="updateMenuDesc" id="updateMenuDesc" class="form-control">
					</div>
					</div>
			
			<div class="form-group row">
			<label for="poc" class="control-label col-xs-3">Price</label>
			<div class="col-xs-8">
				<input type="text" name="updateMenuPrice" id="updateMenuPrice" class="form-control" >
			</div>
			</div>
			<div class="form-group row">
				<label for="email" class="control-label col-xs-3">Quantity</label>
				<div class="col-xs-8">
				<input type="text" name="updateMenuQuantity" id="updateMenuQuantity" class="form-control" >
			</div>
			</div>
			</div>
		</form>
		</div>
	</div>
	</div>
            
            <div class="modal-footer">
                <button type="button" class="btn btn-default" data-dismiss="modal">Close</button>
                <button type="button" class="btn btn-primary" id="updateMenu" href="#">Update Menu</button>
            </div>
        </div>
    </div>
	</div>
<div id="deleteMenu" class="modal fade">
    <div class="modal-dialog">
        <div class="modal-content">
         
            <div class="modal-body">
                <div class="row">
					<p> Are you sure you want to delete? </p>
				</div>
			</div>
            
            <div class="modal-footer">
                <button type="button" class="btn btn-default" data-dismiss="modal">Close</button>
                <button type="button" class="btn btn-primary">ok</button>
            </div>
        </div>
    </div>
	</div>

</div>
<script>
$(document).ready(function() {
    $('#example').DataTable();
} );
</script>
</body>
</html>