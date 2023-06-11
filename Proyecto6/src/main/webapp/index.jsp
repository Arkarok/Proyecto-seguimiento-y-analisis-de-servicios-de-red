<!DOCTYPE html>
<html lang="en">

<head>

<meta charset="utf-8">
<meta name="viewport"
	content="width=device-width, initial-scale=1, shrink-to-fit=no">
<meta name="description" content="">
<meta name="author" content="">

<title>Modulo Usuario Comun</title>

<!-- Bootstrap core CSS -->
<link href="vendor/bootstrap/css/bootstrap.min.css" rel="stylesheet">

<!-- Custom styles for this template -->
<link href="css/simple-sidebar.css" rel="stylesheet">

</head>

<body>

	<div class="d-flex" id="wrapper">

		<!-- Sidebar -->
		<div class="bg-light border-right" id="sidebar-wrapper">
			<div class="sidebar-heading">MENU DEL USUARIO COMUN</div>
			<div class="list-group list-group-flush">
				<a href="login?opcion=captura"
					class="list-group-item list-group-item-action bg-light">Capturar
					Datos</a> <a href="estadisticosRed.jsp"
					class="list-group-item list-group-item-action bg-light">Informacion
					General de la Red</a> <a href="captura?opcion=listarcaptura"
					class="list-group-item list-group-item-action bg-light">Administrar
					Captura</a> <a href="captura?opcion=capturacompartida"
					class="list-group-item list-group-item-action bg-light">Ver
					Captura Compartida</a> <a href="login.jsp"
					class="list-group-item list-group-item-action bg-light">Cerrar
					Sesion</a>
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
				<h1 class="mt-4">Dashboard</h1>
				<p>En este menu se encontrarán las opciones que puede realizar
					como usuario comun:</p>
				<p>Capturar Datos: Permite al usuario comun realizar capturas de
					su red.</p>
				<p>Informacion General de la Red: Permite ver un conjunto de
					graficos que presentan la informacion general de la red.</p>
				<p>Administrar Captura: Permite acceder a todas las capturas que
					ha realizado uted como usuario.</p>
				<p>Ver Captura Compartida: puede ver las capturas realizadas por
					otros usuarios.</p>
				<p>Cerrar Sesion: Permite cerrar la sesion del usuario.</p>
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
