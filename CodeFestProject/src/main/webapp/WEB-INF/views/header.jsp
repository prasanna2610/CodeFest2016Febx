<div class="row">
	<div class="col-md-4">
		<div class="CtsLogo pull-left">
			<img src="images/BookUrMealLogo.png" alt="Book your meals" width="68" height="78"/>
		</div>
	</div>
	<div class="col-md-4 text-center hdrMidCnt">
		<div class="welcomeIcon pull-left">&nbsp;</div>
		<div class="welcomeMsg pull-left"><span class="userName">${userInfo}</span></div>
		<span class="errorText">${errorMsg} </span>
	</div>
	<div class="col-md-4">
		<div class="pull-right">
		<form method="post" action="/logout" >
				<input type="submit" value="" title="LogOut" class="btn logout"/> 
		</form>
		</div>
	</div>
</div>
	
	${errorMsg}
	
		
		<script type="text/javascript">
		$(document).ready(function(){
			var path = $(location).attr("pathname");
			var redirectUrl = "http://" + $(location).attr("host") + "/index";
			function validate(){
				$.ajax({
					url : "/validate",
					dataType : "text",
					type : 'get',
					success : function(data) {
						if(data){
							if(data == "invalid"){
								$(location).attr("href", redirectUrl);
							}
						
						}else{
							$(location).attr("href", redirectUrl);
						}
					},
					error : function(
							xhr,
							textStatus,
							errorThrown) {
						$(location).attr("href", redirectUrl);
					}
				});
			}
			if(path && path.indexOf("index") == -1 ){
				setInterval(validate, 10000);
			}
		});
		</script>