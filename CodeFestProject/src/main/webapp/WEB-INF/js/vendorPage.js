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
			  var tabMainCont=jQuery('#example1');
			  var tableHeaderCont=jQuery('<thead> <tr><th>Menu ID</th><th>Dish Name</th><th>Description</th> <th>Price</th><th>Quantity</th><th>Active</th><th>Actions</th></tr></thead>');
			  tabMainCont.append(tableHeaderCont);
			  var tableListCont=jQuery('<tbody>');
			  $.each(responseObj,function(i,obj){
				  var tableRow=$('<tr>');
				  	var tableData=$('<td>').text(obj.menuId).appendTo(tableRow);
					var tableData=$('<td>').text(obj.menuName).appendTo(tableRow);
					var tableData=$('<td>').text(obj.menuDescription).appendTo(tableRow);
					var tableData=$('<td>').text(obj.price).appendTo(tableRow);
					var tableData=$('<td>').text("100").appendTo(tableRow);
					var tableData=$('<td>').text(obj.availability).appendTo(tableRow);
					var tableData=$('<td>').append("<a href='#' class='edit-menu'><img src='images/edit-menu.png' alt='Edit' /></a> " +
							"<a  href='#deleteMenu' role='button' data-toggle='modal' class='delete-menu'><img src='images/delete-menu.png' alt='Delete' /></a>").appendTo(tableRow);
					tableListCont.append(tableRow);
			  });
			  tabMainCont.append(tableListCont);
		  }
		});
}
