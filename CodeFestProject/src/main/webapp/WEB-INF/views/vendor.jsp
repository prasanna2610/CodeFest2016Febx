<!doctyle html>
<html>
<head>
	<title>B Y M :: Vendor</title>
	<meta name="viewport" content="width=device-width, initial-scale=1">
	<link rel="stylesheet" type="text/css" href="css/header.css" >
	<link href="css/bootstrap.css" rel="stylesheet">
	<link rel="stylesheet" type="text/css" href="css/admin.css" >
	<script src="js/jquery.1.11.0.js"></script>
	<script src="js/bootstrap.js"></script>
	<script src="js/vendorPage.js"></script>
	<script src="https://cdn.datatables.net/1.10.10/js/jquery.dataTables.min.js"></script>
	<script src="https://cdn.datatables.net/1.10.10/js/dataTables.bootstrap.min.js"></script>
    <script type="text/javascript" src="js/canvasjs.min.js"></script>
</head>
<body>
<div class="wrap">

         <header class="navbar landing-header hidden-xs">
			 <jsp:include page="header.jsp"/>
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
         <ul class="nav nav-pills nav-stacked vendor-list" id="vendorNav">
			<li role="presentation" class="active"><a class='menuListLinks' href="#menuDetails" data-toggle="tab">Menu</a></li>
			<li role="presentation"><a class='tranctionListLinks' href="#tansactionDetails" data-toggle="tab">Transaction Details</a></li>
			
			
			<!-- <li role="presentation"><a class='menuListLinks' href="#chartContainer" data-toggle="tab">Comparison chart</a></li> -->
			<!-- <li role="presentation">
				<a href="#myModal" role="button" data-toggle="modal"> 
					<img src="images/add-user.jpg" alt="add vendor" class="add-vendor"/>
					<span class="sr-only">Create new</span> 
				</a>
			</li> -->
	</ul>
	 
        </div><!--/.nav-collapse -->
      </div>
      <a class="btn btn-success btn-lg" id="addMenuBtn" href="#addMenu" role="button" data-toggle="modal"><span class="glyphicon glyphicon-plus"></span> Add Menu</a>
    </div>
	
  </div>
  <div class="col-sm-9">
    <div class="rowMenu" id="menuItems"> 

      <div class="tab-pane fade active in" id="menuDetails">
         <h2> Menu Details </h2>
		<h3 id="vendor-name"></h3>
		<div class="" id="response-status" style = "display:none;"></div>
          <table id="example1" class="table table-striped" cellspacing="0" width="100%"></table>
      </div>
      </div>
	   <div class="tab-pane fade displayNone" id="tansactionDetails">
          <h2>Transaction Details</h2>
          <table id="example" class="table table-striped table-bordered" cellspacing="0" width="100%">
    </table>
      </div>
      <div class="tab-pane fade displayNone" id="chartContainer">
         
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
			<form role="form">
				<div class="modal-header">
					<button type="button" class="close" data-dismiss="modal"
						aria-hidden="true">&times;</button>
					<h2 class="modal-title">Enter Menu details</h2>
				</div>
				<div class="modal-body">
					<div class="row">
						<div
							class="col-xs-12 col-sm-10 col-md-10 col-sm-offset-2 col-md-offset-2">

							<div class="row">

								<div class="form-group row">
									<label for="addMenuName" class="control-label col-xs-3">
										Dish Name </label>
									<div class="col-xs-8">
										<input type="text" required name="addMenuName"
											id="addMenuName" class="form-control">
									</div>
								</div>

								<div class="form-group row">
									<label for="addMenuDesc" class="control-label col-xs-3">
										Description</label>
									<div class="col-xs-8">
										<input type="text" required name="addMenuDesc"
											id="addMenuDesc" class="form-control">
									</div>
								</div>

								<div class="form-group row">
									<label for="addMenuPrice" class="control-label col-xs-3">Price</label>
									<div class="col-xs-8">
										<input type="number" required name="addMenuPrice"
											id="addMenuPrice" class="form-control">
									</div>
								</div>
								<div class="form-group row">
									<label for="addMenuQuantity" class="control-label col-xs-3">Quantity</label>
									<div class="col-xs-8">
										<input type="number" required name="addMenuQuantity"
											id="addMenuQuantity" class="form-control">
									</div>
								</div>
							</div>
						</div>
					</div>
				</div>

				<div class="modal-footer">
					<button type="button" class="btn btn-default"
						data-dismiss="modal">Close</button>
					<button type="button" class="btn btn-primary"
						data-dismiss="modal" id="newMenu">Add Menu</button>
				</div>
			</form>
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
                <button type="button" class="btn btn-primary" id="menuUpdateBtn" data-dismiss="modal" href="#">Update Menu</button>
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
                <button type="button" class="btn btn-primary" data-dismiss="modal" id="deleteMenuBtn" >ok</button>
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
