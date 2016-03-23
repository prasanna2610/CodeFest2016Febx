<!doctype html>
<html lang="en">
	<head>
		<title>B Y M :: Login</title>
		<meta charset="utf-8">
		<meta name="viewport" content="width=device-width, initial-scale=1">
		<link rel="stylesheet" href="http://maxcdn.bootstrapcdn.com/bootstrap/3.3.5/css/bootstrap.min.css">
		
		<script src="https://ajax.googleapis.com/ajax/libs/jquery/1.11.3/jquery.min.js"></script>
		<script src="http://maxcdn.bootstrapcdn.com/bootstrap/3.3.5/js/bootstrap.min.js"></script>
		<link rel="stylesheet" type="text/css" href="css/style.css" >
		
	</head>
	<body>
		<div class="container">
			<div style="margin-top:15%;"></div>
			<div class="loginCnt row">
				<div class="col-sm-2 "></div>
				<div class="col-sm-3 "><img class="" src="images/login.png" alt="Chef"></div>
				<div class="col-sm-1 splitter"></div>
				<div class="col-sm-5">					
					<!-- Input fields -->
					<div class="inputFldSec flip side-2">
						<div class="txtTitle">Sign in to Book Your Meal!!</div>
						${errorMsg}
						<form method="post" action="/home">
							<div>
								<input type="text" name="userName" id="userName"/>
							</div>
							<div >
								<input type="password" name="passWord" id="passWord"/>
							</div>
							<div class="btnSect">
								<button class="btn btn-primary">Login</button>								
							</div>
						</form>
					</div>
				</div>
				<div class="col-sm-1 "></div>
			</div>
		</div>
	</body>
</html>