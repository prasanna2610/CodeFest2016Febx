
	<div class="CtsLogo pull-left">
		<img src="images/ctslogo.png" alt="Cognizant-Book your meals" width="60" />
	</div>
	<div class="welcomeMsg pull-left"> ${userInfo}<span class="userName">  </span><img src="images/person.svg" alt="" width="30px"/></div>
	${errorMsg}
	
		<div class="pull-right">
		<form method="post" action="/logout" >
				<input type="submit" value="" class="btn logout"/> 
				<!-- <a href="#" role="button"><img src="images/on-off.png" alt="logout" width="40px" class="logout"/>  </a>-->
		</form>
		</div>
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