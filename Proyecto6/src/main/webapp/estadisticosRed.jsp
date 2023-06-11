<!DOCTYPE html>
<html lang="en">

<head>

<meta charset="utf-8">
<meta name="viewport"
	content="width=device-width, initial-scale=1, shrink-to-fit=no">
<meta name="description" content="">
<meta name="author" content="">

<title>Menu de EstadisticosRed</title>

<!-- Bootstrap core CSS -->
<link href="vendor/bootstrap/css/bootstrap.min.css" rel="stylesheet">

<!-- Custom styles for this template -->
<link href="css/simple-sidebar.css" rel="stylesheet">

</head>

<body>

	<div class="d-flex" id="wrapper">

		<!-- Sidebar -->
		<div class="bg-light border-right" id="sidebar-wrapper">
			<div class="sidebar-heading">TIPOS DE GRAFICAS</div>
			<div class="list-group list-group-flush">
				<a href="Estadistica?opcion=grafitoBarra"
					class="list-group-item list-group-item-action bg-light">SERVICIOS
					CONSUMIDOS EN LA RED</a> <a href="Estadistica?opcion=graficoBarra2"
					class="list-group-item list-group-item-action bg-light">CANTIDAD
					DE PETICIONES EN LA RED</a> <a href="Estadistica?opcion=graficoLineas"
					class="list-group-item list-group-item-action bg-light">TRAFICO
					EN LA RED</a><a href="index.jsp"
					class="list-group-item list-group-item-action bg-light">REGRESAR</a>
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
				<h1 class="mt-4">Graficos</h1>
				<p>En este apartado, podra exponer en graficos, la informacion
					general capturada de la red siendo las opciones:</p>
				<p>Servicios consumidos en la red: Permite observar por medio de
					un grafico de barras la cantidad de servicios que fueron consumidos
					en la red.</p>
				<p>Cantidad de peticiones en la red: Permite observar por medio
					de un grafico de barras la cantidad de peticiones que reciben los
					dispositivos.</p>
				<p>Trafico en la Red: Permite observar por medio de un grafico
					de lineas el consumo del ancho de banda de la red en el tiempo.</p>
				<p>Regresar: Permite regresar al menu del usuario comun.</p>
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
