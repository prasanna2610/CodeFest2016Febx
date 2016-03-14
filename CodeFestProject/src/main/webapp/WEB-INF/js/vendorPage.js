$( document ).ready(function() {
	var responseObj;
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
	  jQuery('#deleteMenu').bind('click',menuDelete);
}

function editMenuDetails(event) {
	var strVal = $(this).closest("tr").attr('id');
	$('#'+strVal).each(function() {
		var menuName = $(this).find("td").eq(1).html();
		$('#updateMenuName').val($(this).find("td").eq(1).html());
		$('#updateMenuDesc').val($(this).find("td").eq(2).html());
		$('#updateMenuPrice').val($(this).find("td").eq(3).html());
		$('#updateMenuQuantity').val($(this).find("td").eq(4).html());
	 });
	jQuery('#updateMenu').bind('click',updateMenuDetails);
}
function updateMenuDetails(event) {
	var menuName = $('#updateMenuName').val();
	var desc = $('#updateMenuDesc').val();
	var price = $('#updateMenuPrice').val();
	var quantity = $('#updateMenuQuantity').val();
	alert(menuName+desc+price+quantity);
	$('#updateMenu').modal('hide');
}
function menuDelete(event) {
	
}

