
$( document ).ready(function() {
	$("#userName").val('');
	$("#passWord").val('');
	$(".vendorListLinks").click(function(){
		alert("1");
		getVendorDetails();
		
	});
	
	
});

function getVendorDetails(){
	/*var modelVal=$('#vendorModelVal').val();	
	console.log(Object.keys(modelVal).length);
	console.log(modelVal);
	for(var i=0;i<modelVal.size;i++){
		console.log(modelVal[i].menuName);
	}*/
	var responseObj;
	
	$.ajax({
		  url: "/admin/1001",
		  dataType: "text",
		  type:'get',
		  success: function(data){
			  console.log("sucess");
			  console.log(data);
			  responseObj=JSON.parse(data);
			  console.log(responseObj);
		  }
		  
		});
}