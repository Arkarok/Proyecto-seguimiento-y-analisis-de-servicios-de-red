<!DOCTYPE html>
<html lang="en">

<head>

<meta charset="utf-8">
<meta name="viewport"
	content="width=device-width, initial-scale=1, shrink-to-fit=no">
<meta name="description" content="">
<meta name="author" content="">

<title>Modulo Usuario Administrador</title>

<!-- Bootstrap core CSS -->
<link href="vendor/bootstrap/css/bootstrap.min.css" rel="stylesheet">

<!-- Custom styles for this template -->
<link href="css/simple-sidebar.css" rel="stylesheet">

</head>

<body>

	<div class="d-flex" id="wrapper">

		<!-- Sidebar -->
		<div class="bg-light border-right" id="sidebar-wrapper">
			<div class="sidebar-heading">MENU DEL USUARIO ADMINISTRADOR</div>
			<div class="list-group list-group-flush">
				<a class="list-group-item list-group-item-action bg-light">Gestionar
					permisos</a> <a href="usuario?opcion=listar"
					class="list-group-item list-group-item-action bg-light">Gestionar
					Usuarios</a> <a href="login.jsp"
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
					como usuario administrador:</p>
				<p>Gestionar permisos: permite degradar/ascender los permisos
					que el usuario comun puede realizar.</p>
				<p>Gestionar usuarios: permite ver el listado de usuarios que se
					encuentran registrados en la Dashboard.</p>
				<p>Cerrar sesion: permite cerrar la sesion del usuario.</p>
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
