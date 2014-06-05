<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
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


<script src='<c:url value="/resources/backgrid/js/underscore-1.6.0.js" />'></script>
<script src='<c:url value="/resources/backgrid/js/backbone1.1.2.js" />'></script>
<script src='<c:url value="/resources/backgrid/js/backgrid.min.js" />'></script>
<script src='<c:url value="/resources/backgrid/js/backbone-pageable.js" />'></script>
<script src='<c:url value="/resources/backgrid/js/backgrid-paginator.js" />'></script>
<script src='<c:url value="/resources/backgrid/js/backbone.localStorage.js" />'></script>
<script type="text/javascript">
	
	var Dog = Backbone.Model.extend({});
	var Dogs = Backbone.Collection.extend({
		model: Dog,
		localStorage: new Backbone.LocalStorage("dogs-creche")
	});
	var dogs = new Dogs({});
	var LocationCell = Backgrid.Cell.extend({
		render : function() {
			alert(this.model.get('animalLocations'));
			console.log(this.model.get('animalLocations'));
			if (this.model.get('animalLocations') != null) {
				var htmlComp = "";
				$.each(this.model.get('animalLocations'),
						function(i, location) {
							htmlComp += '<li>' + location + '</li>';
						});
				this.$el.html(htmlComp);
			}
			this.delegateEvents();
			return this;
		}
	});
	createTableColumns = function() {
		var cols = columns = [ {
			name : "codeNumber", // The key of the model attribute
			label : "Code Number", // The name to display in the header
			editable : false, // By default every cell in a column is editable, but *ID* shouldn't be
			cell : "number"
		}, {
			name : "name",
			label : "Name",
			editable : false,
			// The cell type can be a reference of a Backgrid.Cell subclass, any Backgrid.Cell subclass instances like *id* above, or a string
			cell : "string" // This is converted to "StringCell" and a corresponding class in the Backgrid package namespace is looked up
		}, {
			name : "type",
			label : "Type",
			editable : false,
			cell : "string" // An integer cell is a number cell that displays humanized integers
		}, {
			name : "breed",
			label : "Breed",
			editable : false,
			cell : "string" // A cell type for floating point value, defaults to have a precision 2 decimal numbers
		}, {
			name : "pawSize",
			label : "Paw Size",
			editable : false,
			sortable : false,
			cell : "number"
		}, {
			name : "animalLocations",
			label : "Location",
			cell : LocationCell,
			editable : false
		} ];
		return cols;
	}
	var columns = createTableColumns();
	var animalGrid = new Backgrid.Grid({
		columns : columns,
		collection : dogs,
		emptyText : "No Data"
	});
	/* 	var animalPaginator = new Backgrid.Extension.Paginator({
	 collection: dogs
	 }); */
	$(document).ready(function() {
		$('#search-submit').click(function(event) {
			$.post('controller', $('#searchForm').serialize(), function(data) {
				console.log(data.animals);
				$.each(data.animals, function(i, animal) {
					console.log('added - ' + animal);
					dogs.create(animal);
				});
				$('#animalGrid').empty();
				$('#animalGrid').append(animalGrid.render().$el);
			});
		});
	});

	$('#animalGrid').empty();
	$('#animalGrid').append(animalGrid.render().$el);
	//$('#animslPaginator').append(animalPaginator.render().$el);
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
		</nav>
		<div class="row">
			<div class="col-md-12">
				<div id="animalGrid"></div>
				<div id="animslPaginator"></div>
			</div>
		</div>
	</div>
</body>
</html>
