
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="utf-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<!DOCTYPE html>
<html>
<head>
<style>
a:link, a:visited {
	background-color: #A5E4F9;
	color: black;
	padding: 14px 25px;
	text-align: center;
	text-decoration: none;
	display: inline-block;
}

a:hover, a:active {
	color: black;
}

.button {
	background-color: #4CAF50; /* Green */
	border: none;
	color: white;
	padding: 15px 32px;
	text-align: center;
	text-decoration: none;
	display: inline-block;
	font-size: 16px;
	margin: 4px 2px;
	cursor: pointer;
}

.button2 {
	background-color: #008CBA;
} /* Blue */
table {
	border-collapse: collapse;
	width: 100%;
}

th, td {
	text-align: left;
	padding: 8px;
}

tr:nth-child(even) {
	background-color: #f2f2f2
}

th {
	background-color: #5469D4;
	color: white;
}
</style>
<meta charset="ISO-8859-1">
<title>Capturas del Usuario</title>
</head>
<body>

	<h1>CAPTURAS DEL USUARIO</h1>
	<div style="overflow-x: auto;">
		<table border="1">

			<tr>
				<th>FECHA</th>
				<th>DURACION (Seg)</th>
				<th>VER TRAMAS DE LA CAPTURA</th>
			</tr>

			<c:forEach var="captura" items="${lista}">
				<tr>
					<td><c:out value="${captura.tiempoInicio}"></c:out></td>
					<td><c:out value="${captura.duracion}"></c:out></td>
					<td><a
						href="captura?opcion=verpaquete&id=<c:out value="${captura.codigo_captura}"></c:out>">
							VER TRAMAS</a></td>
				</tr>
			</c:forEach>

		</table>
</body>
</html>
