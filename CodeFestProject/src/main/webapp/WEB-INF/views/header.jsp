<html>
<head>
<title>logout</title>
<meta name="viewport" content="width=device-width, initial-scale=1">
<link href="css/bootstrap.css" rel="stylesheet">
<link rel="stylesheet" type="text/css" href="css/admin.css">
<script src="js/jquery.1.11.0.js"></script>
<script src="js/bootstrap.js"></script>
<script
	src="https://cdn.datatables.net/1.10.10/js/jquery.dataTables.min.js"></script>
<script
	src="https://cdn.datatables.net/1.10.10/js/dataTables.bootstrap.min.js"></script>
</head>
<body>
	${errorMsg}
	<div style="float:right">
	<form method="post" action="/logout" >
			<input type="submit" value="Log out" />
	</form>
	</div>
</body>
</html>