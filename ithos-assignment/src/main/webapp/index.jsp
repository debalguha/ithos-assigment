<!--[if lt IE 7]> <html class="no-js lt-ie9 lt-ie8 lt-ie7" lang="en"> <![endif]-->
<!--[if IE 7]>    <html class="no-js lt-ie9 lt-ie8" lang="en"> <![endif]-->
<!--[if IE 8]>    <html class="no-js lt-ie9" lang="en"> <![endif]-->
<!--[if gt IE 8]><!-->
<html class="no-js" lang="en">
<!--<![endif]-->
<head>
<title>Unified Patents - PTAB</title>
<meta charset="utf-8">

<meta name="description" content="">
<meta name="author" content="">

<meta name="viewport"
	content="width=device-width, initial-scale=1.0, maximum-scale=1.0, user-scalable=no">
<meta name="apple-mobile-web-app-capable" content="yes">
<link rel="stylesheet"
	href='./resources/backgrid/css/backgrid-paginator.css'>
<link rel="stylesheet" href='./resources/backgrid/css/backgrid.min.css'>

<!-- <link rel="stylesheet" href="css/bootstrap.css"> -->
<link rel="stylesheet"
	href="http://netdna.bootstrapcdn.com/bootstrap/3.1.1/css/bootstrap.min.css">

<script
	src="http://ajax.googleapis.com/ajax/libs/jquery/1.10.2/jquery.min.js"></script>
<script
	src="http://ajax.googleapis.com/ajax/libs/jqueryui/1.10.3/jquery-ui.min.js"></script>
<script
	src="https://netdna.bootstrapcdn.com/bootstrap/3.1.1/js/bootstrap.min.js"></script>

<script src='./resources/backgrid/js/underscore-1.6.0.js'></script>
<script src='./resources/backgrid/js/backbone1.1.2.js'></script>
<script src='./resources/backgrid/js/backgrid.min.js'></script>
<script src='./resources/backgrid/js/backbone-pageable.js'></script>
<script src='./resources/backgrid/js/backgrid-paginator.js'></script>
<script type="text/javascript">
	$(document).ready(function() {
		$('#search-submit').click(function(event){
			$.post('controller', $('#searchForm').serialize(),
				function(data){alert(data); console.log(data)}
			);
		});
	});
</script>
<style>
#header {
	max-width: 929px;
	padding: 15px;
	top: 0px;
	left: 0px;
}

.bs-docs-featurette-title {
	font-size: 40px;
	font-weight: 400;
	color: white;
	margin-bottom: 5px;
}
</style>
</head>
<body>
	<div class="container">
		<nav class="navbar navbar-default" role="navigation">
			<div class="navbar-header">
				<a class="navbar-brand" href="#">Animal</a>
			</div>
			<div class="collapse navbar-collapse"
				id="bs-example-navbar-collapse-1">
				<form id="searchForm" class="navbar-form navbar-left" role="search">
					<div class="form-group">
						<input name="nameParam" type="text" class="form-control" placeholder="Search by Name">
						<input name="codeParam" type="text" class="form-control" placeholder="Search by Code">
					</div>
					<button id="search-submit" type="button" class="btn btn-default">Submit</button>
				</form>
			</div>
			<!-- /.navbar-collapse -->
	</div>
	<!-- /.container-fluid -->
	</nav>
	</div>
</body>
</html>
