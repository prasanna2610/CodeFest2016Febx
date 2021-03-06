$(document).ready(function(){
	window.onload = function() {
	
		$("#userName").val('');
		$("#passWord").val('');
		var jsonVednorObj = [];
		var responseObj;
		getVendorList();
		jQuery(document).on('click', '.vendorListLinks', function() {
			var vendorId = $(this).attr('id');
			getVendorDetails(vendorId);
		});
		jQuery(document).on('submit', '#vendorProfileForm', function(e) {
			e.preventDefault();	
			$("#response-status").hide();
			var vendorDetArr = {};
				if ($("#submit-image").hasClass('edit-info')) {
					$("#submit-image").removeClass('edit-info').addClass('update-info');
					$("#vendorProfileForm input").removeClass("no-border").removeAttr("readonly");
				} else {
					$('.profile-content-list').find('li.label-value input').each(
							function() {
								/*								$(this).attr('contenteditable', 'false');

						var descText = $(this).prev().text().replace(
								/ /g, '');
						var valueText = $(this).text();*/
								var descText = $(this).attr('name');
								var valueText = $(this).val();
								vendorDetArr[descText] = valueText;
								
							});
					jsonVednorObj.push(vendorDetArr);
				 
					var data = jsonVednorObj[0];
					var vendorId = data.VendorId;
					var jsonString = JSON.stringify(data);
					$.ajax({
						url : "/admin/edit/" + vendorId,
						type : "post",
						contentType : "application/json; charset=utf-8",
						data : jsonString,
						success : function(data) {
							$("#response-status").html('<div class="alert alert-success">Vendor updated successfully</div>');
							$("#response-status").show();
							$("#vendorPassword").val('');
						},
						error : function(
								xhr,
								textStatus,
								errorThrown) {
							$("#response-status").html('<div class="alert alert-danger">Error updating vendor. Please check.</div>');
							$("#response-status").show();
							$("#vendorPassword").val('');
						}
					});
					$("#vendorProfileForm input").addClass("no-border").attr("readonly", "readonly");
					$("#submit-image").removeClass("no-border").removeAttr("readonly").removeClass('update-info').addClass('edit-info');
				}
				return false;
			});
						
		jQuery(document).on('click', '#deleteVendorBtn', function(e) {
			deleteVendorInfo($('#vendorID').text());
		});
		jQuery('#vendorCreateForm').submit(function(e) {
			e.preventDefault();
			$('#myModal').modal('hide');
			$("#response-status").hide();
			$.ajax({
				url : "/admin/create",
				dataType : "text",
				type : 'post',
				data : {
					vendorName : $('#vendor_name').val(),
					inCharge : $('#inCharge').val(),
					passwordF : $('#passwordF').val(),
					venDetails : $('#details').val(),
					email : $('#email').val(),
					mobileNumber : $('#mob_no').val()
				},
				success : function(data) {
					$('#myModal').modal('hide');
					$('#vendorListViewer').remove();
					getVendorList();
					$('#vendor_name').val('');
					$('#inCharge').val('');
					$('#passwordF').val('');
					$('#details').val('');
					$('#email').val('');
					$('#mob_no').val('');
					$("#response-status").html('<div class="alert alert-success">Vendor created successfully</div>');
					$("#response-status").show();
				},
				error : function(
						xhr,
						textStatus,
						errorThrown) {
					$("#response-status").html('<div class="alert alert-danger">Error creating vendor. Please check.</div>');
					$("#response-status").show();
				}
			});
			return false;
		});
	
		// chartLoad();
		$("#myTab li").click(function(){
			$("#response-status").hide();
		});
		jQuery(document).on('click', '.add-vendor', function(e) {
			$("#response-status").hide();
		});
	}
	
	function deleteVendorInfo(vendorId) {
		$("#response-status").hide();
		$.ajax({
			url : "/admin/delete",
			dataType : "text",
			type : 'get',
			data : {
				vendorId : vendorId,
			},
			success : function(data) {
				$("#response-status").html('<div class="alert alert-success">Vendor deleted successfully.</div>');
				$("#response-status").show();
				$('#vendorListViewer').remove();
				$("#tabContainer").remove();
				getVendorList();
			},
			error : function(
					xhr,
					textStatus,
					errorThrown) {
				$("#response-status").html('<div class="alert alert-danger">Error deleting vendor. Please check.</div>');
				$("#response-status").show();
				$('#vendorListViewer').remove();
				$("#tabContainer").remove();
				getVendorList();
			}
		});
		
	}
	
function chartLoad(transactionDetails) {
	CanvasJS.addColorSet("greenShades", [// colorSet Array
	"#2F4F4F", "#008080", "#2E8B57", "#3CB371", "#90EE90" ]);
	var chart = new CanvasJS.Chart("chartContainer", {
		colorSet : "greenShades",
		title : {
			text : "",
			fontFamily : "arial black"
		},
		animationEnabled : true,
		legend : {
			verticalAlign : "center",
			horizontalAlign : "right",
			fontSize : 18
		},
		theme : "theme1",
		data : [ {
			type : "pie",
			indexLabelFontFamily : "Arial",
			indexLabelFontSize : 20,
			indexLabelFontWeight : "bold",
			startAngle : 0,
			indexLabelFontColor : "white",
			indexLabelLineColor : "darkgrey",
			indexLabelPlacement : "inside",
			toolTipContent : "{name}: {y}%",
			showInLegend : true,
			indexLabel : "#percent%",
			dataPoints : [ {
				y : 35,
				name : "Payment History",
				legendMarkerType : "square"
			}, {
				y : 30,
				name : "Amount you owe",
				legendMarkerType : "square"
			}, {
				y : 10,
				name : "Types of credit you have",
				legendMarkerType : "square"
			} ]
		} ]
	});
	chart.render();
}
function getVendorList() {
	$.ajax({
		url : "/admin/transaction",
		dataType : "text",
		type : 'get',
		success : function(data) {
			var responseObj ='';
			if(data && $.trim(data)){
				responseObj = JSON.parse(data);
				generateVendorList(responseObj);
				createVendorTable(responseObj[0].transaction);
				createVendorUpdate(responseObj[0]);
				createChartData();
			}else{
				generateVendorList(responseObj);
			}
		}

	});
}
function createChartData() {
	var chartMainCont = jQuery('<div>', {
		class : 'tab-pane fade',
		id : 'messages'
	});
	var chartContainer = jQuery('<div>', {
		class : 'container'
	});
	var chartSubContainer = jQuery('<div>', {
		class : 'row-fluid'
	});
	var chartHeaderTxtCont = jQuery('<div>', {
		class : 'col-md-8 col-lg-8 text-center'
	});
	var chartHeaderTxt = jQuery('<h3> Comparison on the transaction details on vendors</h3>');
	var chartContentMainCont = jQuery('<div>', {
		class : 'row-fluid'
	});
	var chartContentCont = jQuery('<div>', {
		class : 'col-md-8 col-lg-8 text-center'
	});
	var chartHolder = jQuery('<div>', {
		id : 'chartContainer'
	});
	chartHeaderTxtCont.append(chartHeaderTxt);
	chartSubContainer.append(chartHeaderTxtCont);
	chartContainer.append(chartSubContainer);
	chartContentCont.append(chartHolder);
	chartContentMainCont.append(chartContentCont);
	chartContainer.append(chartContentMainCont);
	chartMainCont.append(chartContainer);
	$('#tabContainer').append(chartMainCont);

}
function generateVendorList(vendorDetails) {
	var vendorListSecCont = jQuery('<div>', {
		class : 'navbar-collapse collapse sidebar-navbar-collapse',
		id : 'vendorListViewer'
	});
	var listContainer = jQuery('<ul>', {
		class : 'nav nav-pills nav-stacked vendor-list',
		id : 'venListConte'
	});
	if(vendorDetails){
		$
		.each(
				vendorDetails,
				function(i, obj) {
					var listArr = jQuery('<li role="presentation"><a href="#" class="vendorListLinks" data-toggle="tab" id="'
							+ obj.vendorId
							+ '">'
							+ obj.vendorName
							+ '</a></li>');
					listContainer.append(listArr);
				});
	}
	/*var listNewImageSec = jQuery('<li role="presentation"><a href="#myModal" role="button" data-toggle="modal"> <img src="images/add-user.jpg" alt="add vendor" class="add-vendor"/><span class="sr-only">Create new</span> </a></li>');
	listContainer.append(listNewImageSec);*/
	vendorListSecCont.append(listContainer);
	vendorListSecCont.insertAfter("#adminNaveBar");
	$('#venListConte li:first-child').addClass('active');
	$('#myTab li:first-child').addClass('active');

}
function getVendorDetails(vendorId) {
	$("#response-status").hide();
	$.ajax({
		url : "/admin/" + vendorId,
		dataType : "text",
		type : 'get',
		success : function(data) {
			if(data && $.trim(data)){
				responseObj = JSON.parse(data);
				createVendorTable(responseObj.transaction);
				createVendorUpdate(responseObj);
				createChartData();
			}
		}

	});
}
function createVendorTable(transactionDet) {
	$('#tabContainer').remove();
	var tabMainCont = jQuery('<div>', {
		class : "tab-content",
		id : 'tabContainer'
	});
	var transSecCont = jQuery('<div>', {
		class : 'tab-pane fade active in',
		id : 'home'
	});
	var headerTxt = jQuery('<h2>Transaction Details</h2>');
	var tableSecCont = jQuery('<table>', {
		class : 'table table-striped table-bordered',
		id : 'example'
	});
	var tableHeaderCont = jQuery('<thead> <tr><th>Transaction Id</th><th>Employee Id</th> <th>Date</th><th>Menu Name</th><th>Quantity</th><th>Price</th></tr></thead>');
	tableSecCont.append(tableHeaderCont);
	var tableListCont = jQuery('<tbody>');
	$.each(transactionDet, function(i, obj) {
		var tableRow = $('<tr>');
		var tableData = $('<td>').text(obj.transactionId).appendTo(tableRow);
		var tableData = $('<td>').text(obj.userId).appendTo(tableRow);
		var tableData = $('<td>').text(obj.dateString).appendTo(tableRow);
		var tableData = $('<td>').text(obj.menuName).appendTo(tableRow);
		var tableData = $('<td>').text(obj.quantity).appendTo(tableRow);
		var tableData = $('<td>').text((obj.quantity*obj.price)).appendTo(tableRow);
		tableListCont.append(tableRow);
	});
	tableSecCont.append(tableListCont);
	transSecCont.append(headerTxt);
	transSecCont.append(tableSecCont);
	tabMainCont.append(transSecCont);
	tabMainCont.append(transSecCont);
	(tabMainCont).insertAfter("#myTab");
	$("#myTab li").removeClass('active');
	$('#myTab li:first-child').addClass('active');
	

}
function createVendorUpdate(vendorDetails) {
	var transSecCont = jQuery('<div>', {
		class : 'tab-pane fade',
		id : 'profile'
	});
	var headerTxt = jQuery('<h2>Profile</h2>');
	var updateSecCont = jQuery('<div>', {
		class : 'profile-content-list my-info',
		id : 'example'
	});
		var headerAction = jQuery('<h3>Current Info <input type="submit" id="submit-image" class="edit-info" value=""/>'
				+ '<a href="#deleteVendorProfile" role="button" data-toggle="modal" class="profile-delete delete-info"></a> </h3>');
		var listContId = jQuery('<ul>');
		var listVendorId = jQuery('<li class="label" >Vendor Id</li><li class="label-value" id="vendorID" contenteditable="false"> <span class="paddingL10">'
				+ vendorDetails.vendorId + '</span><input type="hidden" name="VendorId" value = "'+ vendorDetails.vendorId + '"/></li>');
		listContId.append(listVendorId);
		var listContName = jQuery('<ul>');
		var listVendorName = jQuery('<li class="label">Vendor Name</li><li class="label-value firstEdit" contenteditable="false">'
				+ '<input readonly="readonly" name = "VendorName" class="form-control no-border" type = "text" required value = "' +  vendorDetails.vendorName + '" </input></li>');
		listContName.append(listVendorName);
		var listContPOC = jQuery('<ul>');
		var listVendorPOC = jQuery('<li class="label">First Name</li><li class="label-value" contenteditable="false">'
				+ '<input readonly="readonly" name = "FirstName" type = "text" class="form-control no-border" required value = "' + vendorDetails.incharge + '" </input></li>');
		listContPOC.append(listVendorPOC);
		var listPasswordPOC = jQuery('<ul>');
		var listpwdPoc = jQuery('<li class="label">Password</li><li class="label-value" contenteditable="false">'
				+ '<input readonly="readonly" id="vendorPassword" name = "Password" type = "password" class="form-control no-border" value = "" </input></li>');
		listPasswordPOC.append(listpwdPoc);
		var listContContact = jQuery('<ul>');
		var listVendorContact = jQuery('<li class="label">Contact Number</li><li class="label-value" contenteditable="false">'
				+ '<input readonly="readonly" name = "ContactNumber"  type = "text" pattern = "\\d*" maxlength="10" class="form-control no-border" required value = "' + vendorDetails.vendorPhone + '" </input></li>');
		listContContact.append(listVendorContact);
		var listContEmail = jQuery('<ul>');
		var listVendorEmail = jQuery('<li class="label">Email</li><li class="label-value" contenteditable="false">'
				+ '<input readonly="readonly" name = "Email" type = "email" class="form-control no-border" required value = "' + vendorDetails.vendorEmail + '" </input></li>');
		listContEmail.append(listVendorEmail);
		var listContDet = jQuery('<ul>');
		var listVendorDet = jQuery('<li class="label">Details</li><li class="label-value" contenteditable="false">'
				+ '<input readonly="readonly" name = "Details" type = "text" class="form-control no-border" required value = "' + vendorDetails.vendorDetail + '" </input></li>');
		listContDet.append(listVendorDet);
		updateSecCont.append(listContId).append(listContName).append(listContPOC).append(listPasswordPOC)
				.append(listContContact).append(listContEmail).append(listContDet);
		transSecCont.append(headerTxt);
	transSecCont.append(headerAction);
	transSecCont.append(updateSecCont);
	$('#tabContainer').append(transSecCont);

}
});