$( document ).ready(function() {
	var responseObj;
	var menuID;
	getMenuDetails();
});
function getMenuDetails(){
	$.ajax({
		  url: "/vendor",
		  dataType: "text",
		  type:'get',
		  success: function(data){
			  responseObj=JSON.parse(data);
			  createMenuTable(responseObj);
		  }
		});
}

function createMenuTable(menuDetails) {
	var tabMainCont=jQuery('#example1');
	var rowCount = jQuery('#example1 tr').length;
	if(rowCount > 0) {
		jQuery('#example1 tr').remove()
	}

	  var tableHeaderCont=jQuery('<thead> <tr><th>Menu ID</th><th>Dish Name</th><th>Description</th> <th>Price</th><th>Quantity</th><th>Active</th><th>Actions</th></tr></thead>');
	  tabMainCont.append(tableHeaderCont);
	  var tableListCont=jQuery('<tbody>');
	  var iVal = 1; 
	  $.each(menuDetails,function(i,obj){
		  var tableRow=$('<tr id=MmenuTableRow'+iVal+'>');
		  	var tableData=$('<td>').text(obj.menuId).appendTo(tableRow);
			var tableData=$('<td>').text(obj.menuName).appendTo(tableRow);
			var tableData=$('<td>').text(obj.menuDescription).appendTo(tableRow);
			var tableData=$('<td>').text(obj.price).appendTo(tableRow);
			var tableData=$('<td>').text("100").appendTo(tableRow);
			var tableData=$('<td>').text(obj.availability).appendTo(tableRow);
			var tableData=$('<td>').append("<a href='#updateMenu' id='menuUpdate' role='button' data-toggle='modal' class='edit-menu'><img src='images/edit-menu.png' alt='Edit' /></a> " +
					"<a  href='#deleteMenu' role='button' data-toggle='modal' class='delete-menu'><img src='images/delete-menu.png' alt='Delete' /></a>").appendTo(tableRow);
			tableListCont.append(tableRow);
			iVal++;
	  });
	  tabMainCont.append(tableListCont);
	  jQuery('.edit-menu').on('click',editMenuDetails);
	  jQuery('.delete-menu').bind('click',menuDelete);
	  jQuery('#newMenu').bind('click',addMenu);
}

function editMenuDetails(event) {
	var strVal = $(this).closest("tr").attr('id');
	$('#'+strVal).each(function() {
		menuID = $(this).find("td").eq(0).html();
		$('#updateMenuName').val($(this).find("td").eq(1).html());
		$('#updateMenuDesc').val($(this).find("td").eq(2).html());
		$('#updateMenuPrice').val($(this).find("td").eq(3).html());
		$('#updateMenuQuantity').val($(this).find("td").eq(4).html());
	 });
	jQuery('#menuUpdateBtn').bind('click',updateMenuDetails);
}
function updateMenuDetails(event) {
	
	$.ajax({
		  url: "/updateMenu",
		  dataType: "text",
		  type:'get',
		  data: {
			  menuId : menuID,
			  menuName:  $('#updateMenuName').val(),
			  desc : $('#updateMenuDesc').val(),
			  price : $('#updateMenuPrice').val(),
			quantity : $('#updateMenuQuantity').val()
          },
		  success: function(data){
		  }
		});
	$('#updateMenu').modal('hide');
	getMenuDetails();
}


	function menuDelete(event) {
		var strVal = $(this).closest("tr").attr('id');
		$('#'+strVal).each(function() {
			menuID = $(this).find("td").eq(0).html();
		 });
		$('#deleteMenuBtn').on('click', function() {
			$.ajax({
				  url: "/deleteMenu",
				  dataType: "text",
				  type:'get',
				  data: {
					  menuId : menuID,
		        },
				  success: function(data){
				  }
				});
			$('#deleteMenu').modal('hide');
			getMenuDetails();
		});
		
	}


function addMenu(event) {
	$.ajax({
		  url: "/addMenu",
		  dataType: "text",
		  type:'get',
		  data: {
			  menuName:  $('#addMenuName').val(),
			  desc : $('#addMenuDesc').val(),
			  price : $('#addMenuPrice').val(),
			quantity : $('#addMenuQuantity').val()
          },
		  success: function(data){
		  }
		});
	$('#addMenu').modal('hide');
	getMenuDetails();
}