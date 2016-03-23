<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!doctyle html>
<html>
<head>
	<title>B Y M :: Admin</title>
	<meta name="viewport" content="width=device-width, initial-scale=1">
	<link rel="stylesheet" type="text/css" href="css/header.css" >
	<link href="css/bootstrap.css" rel="stylesheet">
	<link rel="stylesheet" type="text/css" href="css/admin.css" >
	<script src="js/jquery.1.11.0.js"></script>
	<script src="js/bootstrap.js"></script>
	<script src="js/adminPage.js"></script>
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

<input type="hidden" id="vendorModelVal" name="vendorModelDet" value="${vendorInfoObj[0].transaction}">
<input type="hidden" id="userName" name="userName" value='${userName}'>
  <div class="col-sm-3" id="vendorListMainWrapper">
    <div class="sidebar-nav">
	    <div class="addVendor">
	    	<div class="pull-left">Add Vendor(s)</div>
	    	<div class="pull-right">
	    		<a data-toggle="modal" title="Click here to Add New Vendor" role="button" href="#myModal">
					<img class="add-vendor" alt="add vendor" src="images/addVendor.png" height="36" width="36" />
					<span class="sr-only">Create new</span>
				</a>
	    	</div>
	    </div>
	      <div class="navbar navbar-default" role="navigation">
	        <div class="navbar-header" id="adminNaveBar">        
	          <button type="button" class="navbar-toggle" data-toggle="collapse" data-target=".sidebar-navbar-collapse">
	            <span class="sr-only">Toggle navigation</span>
	            <span class="icon-bar"></span>
	            <span class="icon-bar"></span>
	            <span class="icon-bar"></span
				>
	          </button>
	         
	          <span class="visible-xs navbar-brand">Sidebar menu</span>
	          
	        </div>
	      </div>
    </div>
	
  </div>
  <div class="col-sm-9">
	    <div class="row" id="tabHolder">
	    	<!-- Nav tabs -->
	    	<div class="" id="response-status" style = "display:none;"></div>
	    	<form id="vendorProfileForm">
				<ul class="nav nav-tabs" id="myTab" role="tablist" id="sidebar-nav">
				 
				     <li class="active">
				          <a href="#home" role="tab" data-toggle="tab">
								Transaction
				          </a>
				     </li>
				     <li><a href="#profile" role="tab" data-toggle="tab">
				         Profile Update
				          </a>
				     </li>
				     <!-- <li>
				          <a href="#messages" role="tab" data-toggle="tab">
				              Comparison chart
				          </a>
				     </li> -->
				  </ul>
	   		</form>
	    </div> 
	</div>
	
	<div id="myModal" class="modal fade">
	    <div class="modal-dialog">
	        <div class="modal-content">
				<form id= "vendorCreateForm" role="form">
		            <div class="modal-header">
		                <button type="button" class="close" data-dismiss="modal" aria-hidden="true">&times;</button>
		                <h2 class="modal-title">Enter vendor details</h2>
		            </div>
		            <div class="modal-body">
		                <div class="row">
						    <div class="col-xs-12 col-sm-10 col-md-10 col-sm-offset-2 col-md-offset-2">
								
									<div class="row">
										
											<div class="form-group row">
											<label for="vendor_name" class="control-label col-xs-3"> Vendor Name </label>
											<div class="col-xs-8">
						                        <input type="text" required name="vendor_name" id="vendor_name" class="form-control" >
											</div>
											</div>
											
											<div class="form-group row">
											<label for="poc" class="control-label col-xs-3">First Name</label>
											<div class="col-xs-8">
												<input type="text" required name="Person Incharge" id="inCharge" class="form-control" >
											</div>
											</div>
											<div class="form-group row">
											<label for="poc" class="control-label col-xs-3">Password</label>
											<div class="col-xs-8">
												<input type="password" required name="Person Incharge" id="passwordF" class="form-control" >
											</div>
											</div>
											<div class="form-group row">
											<label for="poc" class="control-label col-xs-3">Details</label>
											<div class="col-xs-8">
												<input type="text" required name="Person Incharge" id="details" class="form-control" >
											</div>
											</div>
											<div class="form-group row">
												<label for="email" class="control-label col-xs-3">Email Address</label>
												<div class="col-xs-8">
												<input type="email" required name="email" id="email" class="form-control" >
											</div>
											</div>
											<div class="form-group row">
												<label for="mob_no" class="control-label col-xs-3">Mobile Number</label>
												<div class="col-xs-8">
												<input type = "text" pattern = "\d*" maxlength="10" required name="mob_no" id="mob_no" class="form-control" >
											</div>
											</div>
											
									
									</div>
							</div>
						</div>
		            </div>
		            <div class="modal-footer">
		                <button type="button" class="btn btn-default" data-dismiss="modal">Close</button>
		                <button type="submit" class="btn btn-primary" id="vendorSubmit">Create</button>
		            </div>
				</form>
	        </div>
	    </div>
	</div>
</div>
<div id="deleteVendorProfile" class="modal fade">
    <div class="modal-dialog">
        <div class="modal-content">
         
            <div class="modal-body">
                <div class="row">
					<p> Are you sure you want to delete? </p>
				</div>
			</div>
            
            <div class="modal-footer">
                <button type="button" class="btn btn-default" data-dismiss="modal">Cancel</button>
                <button type="button" class="btn btn-primary" data-dismiss="modal" id="deleteVendorBtn" >ok</button>
            </div>
        </div>
    </div>
	</div>

</div>
</div>
</div>
</body>
</html>