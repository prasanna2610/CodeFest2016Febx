$(document)
		.ready(
				function() {
					var responseObj;
					var menuID;
					getMenuDetails();
					$('.tranctionListLinks').click(function(e) {
						getTranscationDetails();
					});
					$('.menuListLinks').click(function(e) {
						getMenuDetails();
					});
					jQuery('#newMenu').click(function() {
						$("#response-status").hide();
						$.ajax({
							url : "/addMenu",
							dataType : "text",
							type : 'get',
							data : {
								menuName : $('#addMenuName').val(),
								desc : $('#addMenuDesc').val(),
								price : $('#addMenuPrice').val(),
								quantity : $('#addMenuQuantity').val()
							},
							success : function(data) {
								$('#addMenuName').val('');
								$('#addMenuDesc').val('');
								$('#addMenuPrice').val('');
								$('#addMenuQuantity').val('');
								getMenuDetails();
								$("#response-status").addClass("alert alert-success").html("Menu created successfully.");
								$("#response-status").show();
							},
							failure : function(xhr,
									textStatus,
									errorThrown){
								$("#response-status").addClass("alert alert-danger").html("Error creating Menu. Please verify.");
								$("#response-status").show();
								getMenuDetails();
							} 
						});
						$('#addMenu').modal('hide');
					});
					function getTranscationDetails() {
						$("#menuItems").hide();
						$("#response-status").hide();
						$("#tansactionDetails").show();
						$.ajax({
							url : "/transaction",
							dataType : "text",
							type : 'get',
							success : function(data) {
								if (data && $.trim(data)) {
									responseObj = JSON.parse(data);
									createTransactionTable(responseObj);
								}
							}
						});
					}
					function createTransactionTable(transactiondetails) {
						var tabMainCont = jQuery('#example');
						var rowCount = jQuery('#example tr').length;
						if (rowCount > 0) {
							jQuery('#example tr').remove();
						}
						var tableHeaderCont = jQuery('<thead> <tr><th>Transaction Date</th><th>Transaction Id</th><th>Employee Id</th><th>Menu Name</th><th>quantity</th><th>Price</th><th>Delivered</th></tr></thead>');
						tabMainCont.append(tableHeaderCont);
						var tableListCont = jQuery('<tbody>');
						var iVal = 1;
						$.each(transactiondetails, function(i, obj) {
							var tableRow = $('<tr id=MmenuTableRow' + iVal
									+ '>');

							var tableData = $('<td>').text(obj.dateString)
									.appendTo(tableRow);
							var tableData = $('<td>').text(obj.transactionId)
									.appendTo(tableRow);
							var tableData = $('<td>').text(obj.userId)
									.appendTo(tableRow);
							var tableData = $('<td>').text(obj.menuName)
									.appendTo(tableRow);
							var tableData = $('<td>').text(obj.quantity)
									.appendTo(tableRow);
							var tableData = $('<td>').text(obj.price).appendTo(
									tableRow);
							var tableData = $('<td>').text(obj.delivered)
									.appendTo(tableRow);

							tableListCont.append(tableRow);
							iVal++;
						});
						tabMainCont.append(tableListCont);
						rowCount = jQuery('#example tr').length;
						if (rowCount > 0) {
							$('#example_info').text(
									'Showing 1 to ' + (rowCount - 1)
											+ ' of 12 entries');
						} else {
							$('#example_info').text('Showing 0 result');
						}
					}
					function getMenuDetails() {
						$("#tansactionDetails").hide();
						$("#menuItems").show();
						$.ajax({
							url : "/vendor",
							dataType : "text",
							type : 'get',
							success : function(data) {
								if(data && $.trim(data)){
									responseObj = JSON.parse(data);
								}
								createMenuTable(responseObj);
							},
							failure : function(xhr,
									textStatus,
									errorThrown){
								createMenuTable(responseObj);
							}
						});
					}

					function createMenuTable(menuDetails) {
						var tabMainCont = jQuery('#example1');
						var rowCount = jQuery('#example1 tr').length;
						if (rowCount > 0) {
							jQuery('#example1 tr').remove();
						}
						var vendorName = '';
						var tableHeaderCont = jQuery('<thead> <tr><th>Menu ID</th><th>Dish Name</th><th>Description</th> <th>Price</th><th>Quantity</th><th>Availability</th><th>Actions</th></tr></thead>');
						tabMainCont.append(tableHeaderCont);
						var tableListCont = jQuery('<tbody>');
						var iVal = 1;
						$
								.each(
										menuDetails,
										function(i, obj) {
											vendorName = obj.vendorName;
											var tableRow = $('<tr id=MmenuTableRow'
													+ iVal + '>');
											var tableData = $('<td>').text(
													obj.menuId).appendTo(
													tableRow);
											var tableData = $('<td>').text(
													obj.menuName).appendTo(
													tableRow);
											var tableData = $('<td>').text(
													obj.menuDescription)
													.appendTo(tableRow);
											var tableData = $('<td>').text(
													obj.price).appendTo(
													tableRow);
											var tableData = $('<td>').text(
													obj.quantity).appendTo(
													tableRow);
											var tableData = $('<td>').text(
													obj.availability).appendTo(
													tableRow);
											var tableData = $('<td>')
													.append(
															"<a href='#updateMenu' id='menuUpdate' role='button' data-toggle='modal' class='edit-menu'><img src='images/edit-menu.png' alt='Edit' /></a> "
																	+ "<a  href='#deleteMenu' role='button' data-toggle='modal' class='delete-menu'><img src='images/delete-menu.png' alt='Delete' /></a>")
													.appendTo(tableRow);
											tableListCont.append(tableRow);
											iVal++;
										});
						tabMainCont.append(tableListCont);
						if(vendorName){
							jQuery("#vendor-name").html("Vendor Name : " + vendorName);
						}
						jQuery('.edit-menu').on('click', editMenuDetails);
						jQuery('.delete-menu').bind('click', menuDelete);
					}

					function editMenuDetails(event) {
						$("#response-status").hide();
						var strVal = $(this).closest("tr").attr('id');
						$('#' + strVal).each(
								function() {
									menuID = $(this).find("td").eq(0).html();
									$('#updateMenuName').val(
											$(this).find("td").eq(1).html());
									$('#updateMenuDesc').val(
											$(this).find("td").eq(2).html());
									$('#updateMenuPrice').val(
											$(this).find("td").eq(3).html());
									$('#updateMenuQuantity').val(
											$(this).find("td").eq(4).html());
								});
						jQuery('#menuUpdateBtn').bind('click',
								updateMenuDetails);
					}
					function updateMenuDetails(event) {
						$("#response-status").hide();
						$.ajax({
							url : "/updateMenu",
							dataType : "text",
							type : 'get',
							data : {
								menuId : menuID,
								menuName : $('#updateMenuName').val(),
								desc : $('#updateMenuDesc').val(),
								price : $('#updateMenuPrice').val(),
								quantity : $('#updateMenuQuantity').val()
							},
							success : function(data) {
								getMenuDetails();
								$("#response-status").addClass("alert alert-success").html("Menu updated succesfully.");
								$("#response-status").show();
							},
							failure : function(xhr,
									textStatus,
									errorThrown){
								getMenuDetails();
								$("#response-status").addClass("alert alert-danger").html("Error updating Menu. Please verify.");
								$("#response-status").show();
							}
						});
						$('#updateMenu').hide();
						
					}

					function menuDelete(event) {
						var strVal = $(this).closest("tr").attr('id');
						$("#response-status").hide();
						$('#' + strVal).each(function() {
							menuID = $(this).find("td").eq(0).html();
						});
						$('#deleteMenuBtn').on('click', function() {
							$.ajax({
								url : "/deleteMenu",
								dataType : "text",
								type : 'get',
								data : {
									menuId : menuID,
								},
								success : function(data) {
									responseObj = '';
									getMenuDetails();
									$("#response-status").addClass("alert alert-success").html("Menu deleted successfully.");
									$("#response-status").show();
								},
								failure : function(xhr,
										textStatus,
										errorThrown){
									responseObj = '';
									getMenuDetails();
									$("#response-status").addClass("alert alert-danger").html("Error deleting Menu. Please verify.");
									$("#response-status").show();
								}
							});
							$('#deleteMenu').modal('hide');
						});
					}
				});