<!DOCTYPE html>
<html lang="en">

<head>

<meta charset="utf-8">
<meta name="viewport"
	content="width=device-width, initial-scale=1, shrink-to-fit=no">
<meta name="description" content="">
<meta name="author" content="">

<title>Modulo Captura</title>

<!-- Bootstrap core CSS -->
<link href="vendor/bootstrap/css/bootstrap.min.css" rel="stylesheet">

<!-- Custom styles for this template -->
<link href="css/simple-sidebar.css" rel="stylesheet">

</head>

<body>

	<div class="d-flex" id="wrapper">

		<!-- Sidebar -->
		<div class="bg-light border-right" id="sidebar-wrapper">
			<div class="sidebar-heading">TIEMPOS DE CAPTURA</div>
			<div class="list-group list-group-flush">
				<form action="captura" method="post">
					<input type="submit" name="tiempo" value="20"
						class="list-group-item list-group-item-action bg-light"> <input
						type="submit" name="tiempo" value="40"
						class="list-group-item list-group-item-action bg-light">
				</form>
				<a href="index.jsp"
					class="list-group-item list-group-item-action bg-light">Regresar</a>
			</div>
		</div>
		<!-- /#sidebar-wrapper -->

		<!-- Page Content -->
		<div id="page-content-wrapper">

			<nav
				class="navbar navbar-expand-lg navbar-light bg-light border-bottom">
				<button class="btn btn-primary" id="menu-toggle">Ocultar
					Opciones</button>

				<button class="navbar-toggler" type="button" data-toggle="collapse"
					data-target="#navbarSupportedContent"
					aria-controls="navbarSupportedContent" aria-expanded="false"
					aria-label="Toggle navigation">
					<span class="navbar-toggler-icon"></span>
				</button>

				<div class="collapse navbar-collapse" id="navbarSupportedContent">
					<ul class="navbar-nav ml-auto mt-2 mt-lg-0">

					</ul>
				</div>
			</nav>

			<div class="container-fluid">
				<h1 class="mt-4">Captura de Paquetes</h1>
				<p>En este apartado podra realizar capturas en su red, esto se
					realizara por un proceso de rastreo</p>
				<p>de paquetes con el fin de encontrar los datos que se
					transmiten por su red. Al lado izquierdo podra</p>
				<p>observar las opciones de captura, que posee mostrando un
					valor numerico que estara en milisegundos,</p>
				<p>siendo este el tiempo en el que se mantendra en ejecucion la
					captura de paquetes en su red.</p>
			</div>
		</div>
		<!-- /#page-content-wrapper -->

	</div>
	<!-- /#wrapper -->

	<!-- Bootstrap core JavaScript -->
	<script src="vendor/jquery/jquery.min.js"></script>
	<script src="vendor/bootstrap/js/bootstrap.bundle.min.js"></script>

	<!-- Menu Toggle Script -->
	<script>
		$("#menu-toggle").click(function(e) {
			e.preventDefault();
			$("#wrapper").toggleClass("toggled");
		});
	</script>

</body>

</html>
