<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!doctyle html>
<html>
<head>
	<title>Place order</title>
	<meta name="viewport" content="width=device-width, initial-scale=1">
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
		 </header>
<div class="container">
<div class="row">
 <jsp:include page="header.jsp"/>
<input type="hidden" id="vendorModelVal" name="vendorModelDet" value="${vendorInfoObj[0].transaction}">
  <div class="col-sm-3">
    <div class="sidebar-nav">
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
        <%-- <div class="navbar-collapse collapse sidebar-navbar-collapse">
         <ul class="nav nav-pills nav-stacked vendor-list">
         	<c:forEach var="vendorDetails" items="${vendorInfoObj}">
   				<li role="presentation"><a href="#" class="vendorListLinks" id="${vendorDetails.vendorId}">${vendorDetails.vendorName}</a></li>
			</c:forEach>
			<c:out value="${vendorInfoObj[0].transaction[0].menuName}"/>
			<li role="presentation"><a href="#myModal" role="button" data-toggle="modal"> <img src="images/add-user.jpg" alt="add vendor" class="add-vendor"/><span class="sr-only">Create new</span> </a></li>
	</ul>
        </div> --%><!--/.nav-collapse -->
      </div>
    </div>
	
  </div>
  <div class="col-sm-9">
	    <div class="row" id="tabHolder">
	    	<!-- Nav tabs -->
			<ul class="nav nav-tabs" id="myTab" role="tablist" id="sidebar-nav">
			     <li class="active">
			          <a href="#home" role="tab" data-toggle="tab">
							Transaction
			          </a>
			     </li>
			     <li><a href="#profile" role="tab" data-toggle="tab">
			          Update
			          </a>
			     </li>
			     <li>
			          <a href="#messages" role="tab" data-toggle="tab">
			              Comparison chart
			          </a>
			     </li>
			  </ul>
		      <!-- <div class="tab-pane fade" id="messages">
		         
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
		      </div> -->
	    
	    </div> 
	</div>
	
	<div id="myModal" class="modal fade">
	    <div class="modal-dialog">
	        <div class="modal-content">
	            <div class="modal-header">
	                <button type="button" class="close" data-dismiss="modal" aria-hidden="true">&times;</button>
	                <h2 class="modal-title">Enter vendor details</h2>
	            </div>
	            <div class="modal-body">
	                <div class="row">
					    <div class="col-xs-12 col-sm-10 col-md-10 col-sm-offset-2 col-md-offset-2">
							<form role="form">
							
								<div class="row">
									
										<div class="form-group row">
										<label for="vendor_name" class="control-label col-xs-3"> Vendor Name </label>
										<div class="col-xs-8">
					                        <input type="text" name="vendor_name" id="vendor_name" class="form-control" >
										</div>
										</div>
										
										<div class="form-group row">
										<label for="vendor_id" class="control-label col-xs-3"> Vendor ID</label>
										<div class="col-xs-8">	
										<input type="text" name="vendor_id" id="vendor_id" class="form-control">
										</div>
										</div>
								
								<div class="form-group row">
								<label for="poc" class="control-label col-xs-3">Person Incharge</label>
								<div class="col-xs-8">
									<input type="text" name="Person Incharge" id="poc" class="form-control" >
								</div>
								</div>
								<div class="form-group row">
									<label for="email" class="control-label col-xs-3">Email Address</label>
									<div class="col-xs-8">
									<input type="email" name="email" id="email" class="form-control" >
								</div>
								</div>
								<div class="form-group row">
									<label for="mob_no" class="control-label col-xs-3">Mobile Number</label>
									<div class="col-xs-8">
									<input type="number" name="mob_no" id="mob_no" class="form-control" >
								</div>
								</div>
								
								</div>
							</form>
						</div>
					</div>
	            </div>
	            <div class="modal-footer">
	                <button type="button" class="btn btn-default" data-dismiss="modal">Close</button>
	                <button type="button" class="btn btn-primary">Save changes</button>
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