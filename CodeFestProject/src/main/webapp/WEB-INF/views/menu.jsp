<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %>
<!doctyle html>
<html>
<head>
	<title>Place order</title>
	<meta name="viewport" content="width=device-width, initial-scale=1">
	<link rel="stylesheet" type="text/css" href="css/header.css" >
	<link href="css/bootstrap.css" type="text/css"  rel="stylesheet">
	<link rel="stylesheet" type="text/css" href="css/menu.css" >
	<script src="js/jquery.1.11.0.js"></script>
	<script src="js/bootstrap.js"></script>

</head>
<body>
	<div class="wrap">

		<header class="navbar landing-header hidden-xs"> 
			<jsp:include page="header.jsp"/>
		</header>
		<div class="container">
			<div class="row">
				<div class="col-md-9 tabContent">
					<!-- Nav tabs -->
					<ul class="nav nav-tabs" id="myTab" role="tablist" id="sidebar-nav">
						<c:forEach items="${vendorList}" var="vendor" varStatus="i">
							<c:choose>
								<c:when test="${i.index==0}">
									<li class="active"><a class="menu-tab" href="#home"
										role="tab" data-toggle="tab"> <c:out
												value="${vendor.vendorName}" />
									</a> <span class="vendor-id" style="display: none;"><c:out
												value="${vendor.vendorId}" /></span></li>
								</c:when>
								<c:otherwise>
									<li class=""><a class="menu-tab" href="#home" role="tab"
										data-toggle="tab"> <c:out value="${vendor.vendorName}" />
									</a> <span class="vendor-id" style="display: none;"><c:out
												value="${vendor.vendorId}" /></span></li>
								</c:otherwise>
							</c:choose>

						</c:forEach>
					</ul>

					<!-- Tab panes -->
					<div id="main-content" class="tab-content">
						<c:choose>
						<c:when
							test="${(not empty vendorList) && (not empty vendorList[0].menu) }">
							<div class="" id="menu-list">
								<h2>Select your Meal</h2>

								<div class="row">
									<section>
										<div class="fsContainer">
											<c:forEach items="${vendorList[0].menu }" var="menu"
												varStatus="i">
												<c:set var="idVar" value="${i.index}"></c:set>
												<div class="col-md-3 col-sm-3">
													<div class="tileView">
														<c:set var="menuName" scope="session" value="${menu.menuName}" ></c:set>
														<c:choose>
															<c:when test="${fn:containsIgnoreCase(menuName, 'Idly')}">
																<article class="caption">
																<img src="images/Idly.jpg"
																	class="feedThumb img-responsive caption_media" alt="" />
															</c:when>
															<c:when test="${fn:containsIgnoreCase(menuName, 'Uthapam')}">
																<article class="caption">
																<img src="images/Uthapam.jpg"
																	class="feedThumb img-responsive caption_media" alt="" />
															</c:when>
															<c:when test="${fn:containsIgnoreCase(menuName, 'Briyani')}">
																<article class="caption">
																<img src="images/Biryani-Egg.jpg"
																	class="feedThumb img-responsive caption_media" alt="" />
															</c:when>
															<c:when test="${fn:containsIgnoreCase(menuName, 'Briyani')}">
																<article class="caption">
																<img src="images/Biryani-Egg.jpg"
																	class="feedThumb img-responsive caption_media" alt="" />
															</c:when>
															<c:when test="${fn:containsIgnoreCase(menuName, 'Prawn')}">
																<article class="caption">
																<img src="images/prawn.jpg"
																	class="feedThumb img-responsive caption_media" alt="" />
															</c:when>
															<c:when test="${fn:containsIgnoreCase(menuName, 'Finger')}">
																<article class="caption">
																<img src="images/FingerChips.jpg"
																	class="feedThumb img-responsive caption_media" alt="" />
															</c:when>
															<c:when test="${fn:containsIgnoreCase(menuName, 'Burger')}">
																<article class="caption">
																<img src="images/Burger.jpg"
																	class="feedThumb img-responsive caption_media" alt="" />
															</c:when>
															<c:when test="${fn:containsIgnoreCase(menuName, 'Chicken65')}">
																<article class="caption">
																<img src="images/Chicken65.jpg"
																	class="feedThumb img-responsive caption_media" alt="" />
															</c:when>
															<c:when test="${fn:containsIgnoreCase(menuName, 'Fish Fry')}">
																<article class="caption">
																<img src="images/Fish-Fry1.jpg"
																	class="feedThumb img-responsive caption_media" alt="" />
															</c:when>
															<c:when test="${fn:containsIgnoreCase(menuName, 'Gravy')}">
																<article class="caption">
																<img src="images/Gravy.jpg"
																	class="feedThumb img-responsive caption_media" alt="" />
															</c:when>
															<c:when test="${fn:containsIgnoreCase(menuName, 'Dosa')}">
																<article class="caption">
																<img src="images/Kal-Dosa.jpg"
																	class="feedThumb img-responsive caption_media" alt="" />
															</c:when>
															<c:when test="${fn:containsIgnoreCase(menuName, 'Meals')}">
																<article class="caption">
																<img src="images/Meals.jpg"
																	class="feedThumb img-responsive caption_media" alt="" />
															</c:when>
															<c:when test="${fn:containsIgnoreCase(menuName, 'Paneer')}">
																<article class="caption">
																<img src="images/Paneer.jpg"
																	class="feedThumb img-responsive caption_media" alt="" />
															</c:when>
															<c:when test="${fn:containsIgnoreCase(menuName, 'Parotta')}">
																<article class="caption">
																<img src="images/Parotta.jpg"
																	class="feedThumb img-responsive caption_media" alt="" />
															</c:when>
															<c:when test="${fn:containsIgnoreCase(menuName, 'Pav')}">
																<article class="caption">
																<img src="images/Pav-Bhaji.jpg"
																	class="feedThumb img-responsive caption_media" alt="" />
															</c:when>
															<c:when test="${fn:containsIgnoreCase(menuName, 'Poori')}">
																<article class="caption">
																<img src="images/Poori.jpg"
																	class="feedThumb img-responsive caption_media" alt="" />
															</c:when>
															<c:when test="${fn:containsIgnoreCase(menuName, 'Chappathi')}">
																<article class="caption">
																<img src="images/Roti.jpg"
																	class="feedThumb img-responsive caption_media" alt="" />
															</c:when>
															<c:when test="${fn:containsIgnoreCase(menuName, 'Chicken')}">
																<article class="caption">
																<img src="images/Tandoori-Chicken.jpg"
																	class="feedThumb img-responsive caption_media" alt="" />
															</c:when>
															<c:when test="${fn:containsIgnoreCase(menuName, 'fish')}">
																<article class="caption">
																<img src="images/Fish-Fry1.jpg"
																	class="feedThumb img-responsive caption_media" alt="" />
															</c:when>
															<c:otherwise>
																<article class="caption">
																<img src="images/Meals.jpg"
																	class="feedThumb img-responsive caption_media" alt="" />
															</c:otherwise>
														</c:choose>
															<div class="caption_overlay">
																<h3 class="caption_overlay_title">Menu Items...</h3>
																<p class="caption_overlay_content">
																<ul>
																	<li><c:out value="${menu.menuDescription}" /></li>
																</ul>
																</p>
															</div>
														</article>
														<div class="fContent">
															<span class="menu-id" style="display: none"><c:out
																	value="${menu.menuId}" /></span>
															<h2 class="menu-name">
																<c:out value="${menu.menuName}" />
															</h2>
															<p>
																<span>Price: Rs.</span>
																<span class="price"><c:out value="${menu.price}" /></span>
															</p>
															<p class="checkbox">
																<label for="${menu.menuId}"> Select item </label> <input
																	id="${menu.menuId}" type="checkbox" name="select"
																	class="select-item" value="select menu">
															</p>
														</div>
													</div>
												</div>
											</c:forEach>
										</div>
									</section>
								</div>
							</div>
				<!-- a class="btn btn-success btn-lg" href="#proceed" role="button" data-toggle="modal">Proceed</a> -->
						</c:when>
						<c:otherwise>
							<div class="alert alert-danger">No menu available for this vendor.</div>
						</c:otherwise>
						</c:choose>
					</div>
				</div>

				<div class="col-xs-3 orderSummary pull-left">
					<div class="selectedItems">
						<div class="cart-summary side-bar-contents">
							<div class="summary mbot">
								<h2>Your Order</h2>
								<div id="summary-inner" class="summary-inner">
									<ul class="order-items">
									</ul>
									<ul class="totals clear">
										<li class="subtotal2 clear">
											<div>
												<span class="name">Subtotal</span> <span class="price"><span>Rs.</span><span
													id="subtotal"></span></span>
											</div>
										</li>
									</ul>
								</div>
							</div>
							
							<div>
							<div class="min-order ">Once you click on continue button you will receive an SMS on your Registered mobile number
								<span class="regMob">${phone} </span>
								<a href="#editPhone" id="editMob">
									<span class="sr-only">Edit number</span>
									<img src="images/edit.png" alt="edit number" />
								</a>
								
							</div>
								<div id="editPhone">
								Enter your Phone number
								<form>
								<input type="number" maxlength="10" value="${phone}" id="userPhone" required aria-label="Enter Phone number"/>
								<button class="btn confirmNumber"><span class="Confirm sr-only">confirm</span>
								<img src="images/tick.png" alt="confirm"/>
								</button>
								</form>
								 </div>
								
							</div>
							</div>
							 <div class="cart-actions-sticky">
								<div id="checkout"
									class="cart-actions btn-big stickybot btn-blast btn btn-primary checkout">
									Continue</div>
							</div>
			
						
					</div>
				</div>

			</div>
		</div>
		<div id="Searching_Modal" class="modal fade" tabindex="-1"
			role="dialog" data-keyboard="false" data-backdrop="static">
			<div class="modal-dialog">
				<div class="modal-content">
					<div class="modal-header" style="text-align: center">
						<h3>Processing your Order</h3>
					</div>
					<div class="modal-body">
						<div style="height: 200px">
							<img src="images/spinner.gif" style="display:block; margin:auto;" class="img-responsive caption_media" alt="" />							
						</div>
					</div>
					<div class="modal-footer" style="text-align: center"></div>
				</div>
			</div>
		</div>
	</div>

	<script>
		$(document).ready(function(e) {
			$('#editPhone').hide();
			$('#editMob').click(function(){
				$('#editPhone').show();
			});
			$('.confirmNumber').click(function(){
				var enteredNumber = $('#userPhone').val()
				$('#editPhone').hide();
				console.log(enteredNumber);
				$('.regMob').text(enteredNumber);
			});
			var count = 0;
			var totalSum = 0;
			var selectItem = function(){
				if ($(this).prop("checked")) {
					if (count + 1 > 5) {
						alert("You cannot order more than 5 items at a time.");
						$(this).prop("checked", false);
					} else {
						var i = $(this).attr("id");
						var html = $(".order-items").html();
						html += "<li id='summary-item-"+ i + "' class='summary-item' data-index='0'>";
						html += "<div class='summary-vendor-id' style='display:none'>" + $("#myTab .active .vendor-id").text() + "</div>";
						html += "<div class='summary-menu-id' style='display:none'>" + i + "</div>";
						html += "<div class='details'><span class='name'>";
						var menuName = $(this).parents(".fContent").find(".menu-name").text();
						var price = $(this).parents(".fContent").find(".price").text();
						html += menuName + "</span></div>";
						html += "<div class='count floatL clear '><div class='super_number floatL'><a class='dec'><img src='images/decrease.png' alt='decrease button' /></a>";
						html += "<input class='quantity' type='text' value='1' disabled=''><a href='#' class='inc'><img src='images/addIcon.png' alt='Add button' /></a></div>";
						html += "<div class='per-item-price' style='display:none'>" + price + "</div>";
						html += "<div class='quantity floatL'>x"
								+ price
								+ "</div></div><div class='price item-price '>"
								+ price
								+ "</div>";
						html += "<div class='clear'></div></li>";
						$(".order-items").html(html);
						$(".inc").click(incrementFunction);
						$(".dec").click(decrementFunction);
						totalSum += eval(price);
						count++;
						$("#subtotal").text(totalSum);
						$(".orderSummary").show();
					}
				} else {
					var j = $(this).attr("id");
					if ($("#summary-item-" + j)) {
						var element = "#summary-item-" + j;
						var itemTotal = $(element + " .item-price").text();
						if(itemTotal){
							totalSum -= eval(itemTotal); 
						}
						$("#summary-item-" + j)
								.remove();
						count--;
						$("#subtotal").text(totalSum);
					}
					if(count == 0){
						$(".orderSummary").hide();
					}
				}

			}
			
			var incrementFunction = function() {
				var quantity = $(this).parent().find(".quantity");
				if (quantity) {
					var value = eval(quantity.val());
					if (value + 1 > 5) {
						alert("You cannot order more than 5 at a time.");
					} else {
						quantity.val(value + 1);
						var sum = $(this).parents(".summary-item").find(".per-item-price").text();
						var total = (value + 1)* sum;
						$(this).parents(".summary-item").find(".item-price").text(total);
						totalSum += eval(sum);
						$("#subtotal").text(
								totalSum);
					}
				}
			}

			var decrementFunction = function() {
				var quantity = $(this).parent()
						.find(".quantity");
				if (quantity) {
					var value = eval(quantity.val());
					if (value - 1 == 0) {
						alert("Please unselect the item to remove.");
					} else {
						quantity.val(value - 1);
						var sum = $(this).parents(".summary-item").find(".per-item-price").text();
						var total = (value - 1) * sum;
						$(this).parents(".summary-item").find(".item-price").text(total);
						totalSum -= eval(sum);
						$("#subtotal").text(totalSum);
					}
				}
			}
			$("input[type='checkbox']").change(selectItem);
			$(".inc").click(incrementFunction);
			$(".dec").click(decrementFunction);
			$(".menu-tab").click(
					function() {
						var vendorId = $(this).parent().find(
								".vendor-id").text();
						if (vendorId) {
							var url = "/order/menu/" + vendorId;
							$.ajax({
								url : url,
								dataType : "text",
								type : 'get',
								success : function(data) {
									if(data && $.trim(data)){
										responseObj = JSON.parse(data);
										createMenu(responseObj);
									
									}else{
										$("#main-content").html("<div class='alert alert-danger'>Error fetching Menu list.</div>");
									}
								},
								error : function(
										xhr,
										textStatus,
										errorThrown) {
									$("#main-content")
											.html('<div class="alert alert-danger">Error fetching Menu list.</div>');
								}
							});
						}
					});
	
			function getImages(menuName) {
				if(menuName.toLowerCase().contains('idly')) {
					menuImage = "images/Idly.jpg";					
				} else if(menuName.toLowerCase().contains('idly')) {
					menuImage = "images/Meals.jpg";
				} else if(menuName.toLowerCase().contains('uthapam')) {
					menuImage = "images/Uthapam.jpg";
				} else if(menuName.toLowerCase().contains('briyani') || menuName.toLowerCase().contains('biriyani') ) {
					menuImage = "images/Biryani-Egg.jpg";
				} else if(menuName.toLowerCase().contains('prawn')) {
					menuImage = "images/prawn.jpg";
				} else if(menuName.toLowerCase().contains('finger')) {
					menuImage = "images/FingerChips.jpg";
				} else if(menuName.toLowerCase().contains('chicken65')) {
					menuImage = "images/Chicken65.jpg";
				} else if(menuName.toLowerCase().contains('fish fry')) {
					menuImage = "images/Fish-Fry1.jpg";
				} else if(menuName.toLowerCase().contains('gravy')) {
					menuImage = "images/Gravy.jpg";
				} else if(menuName.toLowerCase().contains('dosa')) {
					menuImage = "images/Kal-Dosa.jpg";
				} else if(menuName.toLowerCase().contains('meals')) {
					menuImage = "images/Meals.jpg";
				} else if(menuName.toLowerCase().contains('paneer')) {
					menuImage = "images/Paneer.jpg";
				} else if(menuName.toLowerCase().contains('parotta')) {
					menuImage = "images/Parotta.jpg";
				} else if(menuName.toLowerCase().contains('pav')) {
					menuImage = "images/Pav-Bhaji.jpg";
				} else if(menuName.toLowerCase().contains('poori')) {
					menuImage = "images/Poori.jpg";
				} else if(menuName.toLowerCase().contains('chappathi')) {
					menuImage = "images/Roti.jpg";
				} else if(menuName.toLowerCase().contains('chicken')) {
					menuImage = "images/Tandoori-Chicken.jpg";
				} else if(menuName.toLowerCase().contains('fish')) {
					menuImage = "images/Fish-Fry1.jpg";
				}
			}
			function createMenu(details) {
				$("#main-content").html('');
				if (details && details.menu && details.menu.length>0) {
					var html = "<div class='' id='menu-list'><h2>Select your Meal</h2><div class='row'><section><div class='fsContainer'>";
					var iVal = 0;
					$.each(details.menu,function(i, obj) {
						if(obj){
							getImages(obj.menuName);
							html += "<div class='col-md-3 col-sm-3' style='margin-top:20px'><div class='tileView'>";
							html += "<article class='caption'><img src='"+menuImage+"' class='feedThumb img-responsive caption_media' alt='' />";
							html += "<div class='caption_overlay'><p class='caption_overlay_content'><ul><li>";
							html += obj.menuDescription
									+ "</ul></p></div></article>"
							html += "<div class='fContent'><span class='menu-id' style='display:none'>"
									+ obj.menuId
									+ "</span>";
							html += "<h2 class='menu-name'>"
									+ obj.menuName
									+ "</h2><p><span>Price: Rs.</span><span class='price'>"
									+ obj.price
									+ "</span></p>";
							html += "<p class='checkbox'><label for='" + obj.menuId +"'> Select item </label> ";
							html += "<input id='" + obj.menuId +"' type='checkbox' name='select' class='select-item' value='select menu'></p></div></div></div>";
							iVal++;
						}
					});
					
					html += "</div></section></div></div>";
					$("#main-content").html(html);
					if($(".summary-item")){
						$(".summary-item").each(function(){
							var id = $(this).attr("id").split("-")[2];
							if(id && $("#" + id)){
								$("#" + id).prop("checked", true);
							}
						});
					}
					$("input[type='checkbox']").change(selectItem);
				} else {
					$("#main-content").html("<div class='alert alert-danger'>No menu available for this vendor.</div>");
				}
			}
			$("#checkout").click(function() {
				var menuList = [];
				var phone = $("#userPhone").val();
				if($(".summary-item")){
					$(".summary-item").each(function(){
						var menuId = $(this).find(".summary-menu-id").text();
						var quantity = $(this).find(".quantity").val();
						var price = $(this).find(".per-item-price").text();
						var menuItem = {};
						menuItem["menuId"] = menuId;
						menuItem["quantity"] = quantity;
						menuItem["price"] = price;
						menuList.push(menuItem);
					});
				}
				
				if (menuList) {
					var reqData = new Object();
					reqData.menu = menuList;
					reqData.phone = phone;
					$('#Searching_Modal').modal('show');
					var url = "/order/create";
					$
							.ajax({
								url : url,
								dataType : "json",
								type : 'post',
								contentType : "application/json",
								data: JSON.stringify(reqData),
								success : function(data) {
									if (data && $.trim(data)) {
										$("#main-content")
												.html('<div class="alert alert-success">Thank you for the purchase. A message has been sent to your registered mobile number. Please use the code '
																+ data
																+ ' to obtain the order or for further communications.');
										$('#Searching_Modal').modal('hide');
										$(".orderSummary").hide();
										$(".summary-item").remove();
										totalSum = 0;
										count = 0;
										$("#subtotal").text(totalSum);
									}
								},
								error : function(
										xhr,
										textStatus,
										errorThrown) {
									$("#main-content")
											.html('<div class="alert alert-danger">Your transaction failed or Item out of stock. Please refresh and try again later.');
									$('#Searching_Modal').modal('hide');
									$(".summary-item").remove();
									$(".orderSummary").hide();
									totalSum = 0;
									count = 0;
									$("#subtotal").text(totalSum);
								}
							});
				}

			});
		});
	</script>
</body>
</html>