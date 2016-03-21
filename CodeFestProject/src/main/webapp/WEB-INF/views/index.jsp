<!doctype html>
<html lang="en">
	<head>
		<title> Book Ur Meal :: Login</title>
		<meta charset="utf-8">
		<meta name="viewport" content="width=device-width, initial-scale=1">
		<link rel="stylesheet" href="http://maxcdn.bootstrapcdn.com/bootstrap/3.3.5/css/bootstrap.min.css">
		
		<script src="https://ajax.googleapis.com/ajax/libs/jquery/1.11.3/jquery.min.js"></script>
		<script src="http://maxcdn.bootstrapcdn.com/bootstrap/3.3.5/js/bootstrap.min.js"></script>
		<link rel="stylesheet" type="text/css" href="css/style.css" >
		
		<style>		
			html,body{
			margin: 0;
			padding: 0;
			width: 100%;
			height: 100%;
		}		
		

		.chefLogin{
			background-image: url('images/login.png');
			background-repeat:no-repeat;
			height: 310px;
			width: 201px;
			float;left;
		}

		.splitter{
			background-image: url('images/divider.png');
			background-repeat:no-repeat;
			height: 310px;
			width: 3px;
			float:left;	
		}

		.inputFldSec input{
			-webkit-box-shadow: inset 0 1px 4px #AAA;
			-moz-box-shadow: inset 0 1px 4px #AAA;
			box-shadow: inset 0 1px 4px #AAA;
			border: 1px solid #d6d6d6;
			border-radius: 5px;
			font-size: 16px;
			width: 350px;
			height: 30px;
			line-height: 24px;
			padding: 7px 2px 7px 10px;
			margin: 0 0 30px 0;
			color:#000000;
		}

		.usrPref .chefAdmin,.usrPref .checfCust{
			margin:0 60px;
			float:left;
		}
		.floatL{
			float:left;
		}
		.txtTitle{
			padding-bottom: 36px;
			font-size: 18px;
			font-weight: bold;
			color: 428bca;
		}
		</style>
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
						<div class="txtTitle">Sign in to Book Ur Meal</div>
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