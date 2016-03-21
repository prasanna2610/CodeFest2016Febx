window.onload = function () {

	$("#userName").val('');
	$("#passWord").val('');
	var jsonVednorObj=[];
	var responseObj;
	console.log("1 st");
	getVendorList();
	jQuery(document).on('click','.vendorListLinks',function(){
		var vendorId=$(this).attr('id');
		getVendorDetails(vendorId);
	});
	jQuery(document).on('click','.profile-edit',function(e){
		var vendorDetArr={};
		if($(this).hasClass('edit-info')) {
		    $('.profile-content-list').find('li.label-value').each(function(a,b){
		    	if(a > 0){
		    		$(b).attr('contenteditable','true');
		    	}
			})
			$('.firstEdit').focus();
		   	$(this).removeClass('edit-info').addClass('update-info');
		}
		else {
			$('.profile-content-list').find('li.label-value').each(function(){
				$(this).attr('contenteditable','false');
				
				var descText=$(this).prev().text().replace(/ /g,'');
				var valueText=$(this).text();
				vendorDetArr[descText]=valueText;
				
			});
			jsonVednorObj.push(vendorDetArr);
			console.log(jsonVednorObj[0].VendorId);
			$(this).removeClass('update-info').addClass('edit-info');
			updateVendorInfo(jsonVednorObj[0],jsonVednorObj[0].VendorId);
		}
	});
	 //$('#example').DataTable();
	jQuery(document).on('click','#vendorSubmit',function(){
		 createVendor();
	 });
	 
	//chartLoad();
	
};
function createVendor(){
	$.ajax({
		  url: "admin/create",
		  dataType: "text",
		  type:'post',
		  data: {
			  vendorName:  $('#vendor_name').val(),
			  inCharge : $('#inCharge').val(),
			  passwordF : $('#passwordF').val(),
			  venDetails : $('#details').val(),
			  email : $('#email').val(),
			  mobileNumber : $('#mob_no').val()
		  },
		  success: function(data){
			  $('#myModal').modal('hide');
			  $('#vendorListViewer').remove();
			  getVendorList();
		  }
		});
	
	
}
function updateVendorInfo(jsonPostData,vendorId){
	
	var jsonString = JSON.stringify(jsonPostData);
	$.ajax({
		  url: "admin/edit/"+vendorId,
		  dataType: "json",
		  type:'post',
		  contentType:'application/json; charset=utf-8',
		  data: jsonString,
		  success: function(data){
		  }
	});
}
function chartLoad(transactionDetails){
	console.log(transactionDetails);
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
            {  y: 10, name: "Types of credit you have", legendMarkerType: "square"}
        ]
    }
    ]
});
chart.render();
}
function getVendorList(){
	$.ajax({
		  url: "/admin",
		  dataType: "text",
		  type:'get',
		  success: function(data){
			  responseObj=JSON.parse(data);
			  generateVendorList(responseObj);
				/*$.each(responseObj,function(i,obj){
					console.log(obj.transaction);
				});*/
			  createVendorTable(responseObj[0].transaction);
			  createVendorUpdate(responseObj[0]);
			  createChartData();
			  //chartLoad(responseObj[0].transaction);
		  }
		  
		});
}
/*<div class="tab-pane fade" id="messages">

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
</div>*/
function createChartData(){
	var chartMainCont=jQuery('<div>',{class:'tab-pane fade',id:'messages'});
	var chartContainer=jQuery('<div>',{class:'container'});
	var chartSubContainer=jQuery('<div>',{class:'row-fluid'});
	var chartHeaderTxtCont=jQuery('<div>',{class:'col-md-8 col-lg-8 text-center'});
	var chartHeaderTxt=jQuery('<h3> Comparison on the transaction details on vendors</h3>');
	var chartContentMainCont=jQuery('<div>',{class:'row-fluid'});
	var chartContentCont=jQuery('<div>',{class:'col-md-8 col-lg-8 text-center'});
	var chartHolder=jQuery('<div>',{id:'chartContainer'});
	chartHeaderTxtCont.append(chartHeaderTxt);
	chartSubContainer.append(chartHeaderTxtCont);
	chartContainer.append(chartSubContainer);
	chartContentCont.append(chartHolder);
	chartContentMainCont.append(chartContentCont);
	chartContainer.append(chartContentMainCont);
	chartMainCont.append(chartContainer);
	$('#tabContainer').append(chartMainCont);
	
}
function generateVendorList(vendorDetails){
	console.log("generateVendorList");
	var vendorListSecCont=jQuery('<div>',{class:'navbar-collapse collapse sidebar-navbar-collapse',id:'vendorListViewer'});
	var listContainer=jQuery('<ul>',{class:'nav nav-pills nav-stacked vendor-list',id:'venListConte'});
	$.each(vendorDetails,function(i,obj){
		var listArr=jQuery('<li role="presentation"><a href="#" class="vendorListLinks" data-toggle="tab" id="'+obj.vendorId+'">'+obj.vendorName+'</a></li>');
		listContainer.append(listArr);
	});
	var listNewImageSec=jQuery('<li role="presentation"><a href="#myModal" role="button" data-toggle="modal"> <img src="images/add-user.jpg" alt="add vendor" class="add-vendor"/><span class="sr-only">Create new</span> </a></li>');
	listContainer.append(listNewImageSec);
	vendorListSecCont.append(listContainer);
	vendorListSecCont.insertAfter("#adminNaveBar");
	
}
function getVendorDetails(vendorId){
	/*var modelVal=$('#vendorModelVal').val();	
	console.log(Object.keys(modelVal).length);
	console.log(modelVal);
	for(var i=0;i<modelVal.size;i++){
		console.log(modelVal[i].menuName);
	}*/
	$.ajax({
		  url: "/admin/"+vendorId,
		  dataType: "text",
		  type:'get',
		  success: function(data){
			  responseObj=JSON.parse(data);
				/*$.each(responseObj,function(i,obj){
					console.log(obj.transaction);
				});*/
				createVendorTable(responseObj.transaction);
				createVendorUpdate(responseObj);
				createChartData();
				//chartLoad(responseObj.transaction);
		  }
		  
		});
}
function createVendorTable(transactionDet){
	$('#tabContainer').remove();
	var tabMainCont=jQuery('<div>',{class:"tab-content",id:'tabContainer'});
	var transSecCont=jQuery('<div>',{class:'tab-pane fade active in',id:'home'});
	var headerTxt=jQuery('<h2>Transaction Details</h2>');
	var tableSecCont=jQuery('<table>',{class:'table table-striped table-bordered',id:'example'});
	var tableHeaderCont=jQuery('<thead> <tr><th>Transaction Id</th><th>Employee Id</th> <th>Date</th><th>Menu Name</th><th>Price</th></tr></thead>');
	tableSecCont.append(tableHeaderCont);
	var tableListCont=jQuery('<tbody>');
	$.each(transactionDet,function(i,obj){
		var tableRow=$('<tr>');
		var tableData=$('<td>').text(obj.transactionId).appendTo(tableRow);
		var tableData=$('<td>').text(obj.userId).appendTo(tableRow);
		var tableData=$('<td>').text(obj.date).appendTo(tableRow);
		var tableData=$('<td>').text(obj.menuName).appendTo(tableRow);
		var tableData=$('<td>').text(obj.price).appendTo(tableRow);
		tableListCont.append(tableRow);
	});
	tableSecCont.append(tableListCont);
	transSecCont.append(headerTxt);
	transSecCont.append(tableSecCont);
	tabMainCont.append(transSecCont);
	tabMainCont.append(transSecCont);
	(tabMainCont).insertAfter("#myTab");
	
}
function createVendorUpdate(vendorDetails){
	var transSecCont=jQuery('<div>',{class:'tab-pane fade',id:'profile'});
	var headerTxt=jQuery('<h2>Profile</h2>');
	var updateSecCont=jQuery('<div>',{class:'profile-content-list my-info',id:'example'});
	var headerAction=jQuery('<h3>Current Info <a href="javascript:void(0)" class="profile-edit edit-info"></a></h3>');
	var listContId=jQuery('<ul>');
	var listVendorId=jQuery('<li class="label">Vendor Id</li><li class="label-value" contenteditable="false">'+vendorDetails.vendorId+'</li>');
	listContId.append(listVendorId);
	var listContName=jQuery('<ul>');
	var listVendorName=jQuery('<li class="label">Vendor Name</li><li class="label-value firstEdit" contenteditable="false">'+vendorDetails.vendorName+'</li>');
	listContName.append(listVendorName);
	var listContPOC=jQuery('<ul>');
	var listVendorPOC=jQuery('<li class="label">Incharge</li><li class="label-value" contenteditable="false">'+vendorDetails.incharge+'</li>');
	listContPOC.append(listVendorPOC);
	var listContContact=jQuery('<ul>');
	var listVendorContact=jQuery('<li class="label">Contact Number</li><li class="label-value" contenteditable="false">'+vendorDetails.vendorPhone+'</li>');
	listContContact.append(listVendorContact);
	var listContEmail=jQuery('<ul>');
	var listVendorEmail=jQuery('<li class="label">Email</li><li class="label-value" contenteditable="false">'+vendorDetails.vendorEmail+'</li>');
	listContEmail.append(listVendorEmail);
	var listContDet=jQuery('<ul>');
	var listVendorDet=jQuery('<li class="label">Details</li><li class="label-value" contenteditable="false">'+vendorDetails.vendorDetail+'</li>');
	listContDet.append(listVendorDet);
	updateSecCont.append(listContId).append(listContName).append(listContPOC).append(listContContact).append(listContEmail).append(listContDet);
	transSecCont.append(headerTxt);
	transSecCont.append(headerAction);
	transSecCont.append(updateSecCont);
	$('#tabContainer').append(transSecCont);
	
	
}