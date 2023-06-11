package com.controller;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.dao.UsuarioDAO;
import com.model.Usuario;

/**
 * Servlet implementation class UsuarioController
 */
@WebServlet(description = "Administra las peticiones de la tabla usuarios", urlPatterns = { "/usuario" })
public class UsuarioController extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public UsuarioController() {
		super();
		// TODO Auto-generated constructor stub
	}

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		String opcion = request.getParameter("opcion");

		if (opcion.equals("crear")) {
			System.out.println("Usted a elegido crear un usuario");
			RequestDispatcher requestDispatcher = request.getRequestDispatcher("/Views/crear.jsp");
			requestDispatcher.forward(request, response);

		} else if (opcion.equals("recuperar")) {

			System.out.println("Usted a elegido crear un usuario");
			RequestDispatcher requestDispatcher = request.getRequestDispatcher("/Views/recuperar.jsp");
			requestDispatcher.forward(request, response);

		} else if (opcion.equals("listar")) {

			UsuarioDAO usuarioDAO = new UsuarioDAO();
			List<Usuario> lista = new ArrayList<>();

			try {
				lista = usuarioDAO.obtener();
				for (Usuario usuario : lista) {
					System.out.println(usuario);
				}

				request.setAttribute("lista", lista);

				RequestDispatcher requestDispatcher = request.getRequestDispatcher("/Views/listarUsuarios.jsp");
				requestDispatcher.forward(request, response);

			} catch (SQLException e) {
				e.printStackTrace();
			}
			System.out.println("Usted a elegido listar los usuarios");

		} else if (opcion.equals("editar")) {
			int id = Integer.parseInt(request.getParameter("id"));
			System.out.println("Cambiar contraseña: " + id);
			UsuarioDAO usuarioDAO = new UsuarioDAO();
			Usuario u = new Usuario();
			try {
				u = usuarioDAO.obtenerUsuario(id);
				System.out.println(u);
				request.setAttribute("usuario", u);
				RequestDispatcher requestDispatcher = request.getRequestDispatcher("/Views/editar.jsp");
				requestDispatcher.forward(request, response);
			} catch (SQLException e) {
				e.printStackTrace();
			}

		} else if (opcion.equals("eliminar")) {
			UsuarioDAO usuarioDAO = new UsuarioDAO();
			int id = Integer.parseInt(request.getParameter("id"));
			try {
				usuarioDAO.eliminar(id);
				System.out.println("Registro eliminado satisfactoriamente");
				RequestDispatcher requestDispatcher = request.getRequestDispatcher("/index_admin.jsp");
				requestDispatcher.forward(request, response);
			} catch (SQLException e) {
				e.printStackTrace();
			}

		}

		// response.getWriter().append("Served at: ").append(request.getContextPath());
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		String opcion = request.getParameter("opcion");

		if (opcion.equals("guardar")) {
			UsuarioDAO usuarioDAO = new UsuarioDAO();
			Usuario usuario = new Usuario();
			Usuario usuarioComprobar = new Usuario();
			usuario.setId_usuario(Integer.parseInt(request.getParameter("id_usuario")));
			usuario.setContraseña(request.getParameter("contraseña"));
			usuarioComprobar.setContraseña(request.getParameter("ConfirmaContraseña"));
			System.out.println(usuario);
			System.out.println(usuarioComprobar);

			if (usuario.getContraseña().equals(usuarioComprobar.getContraseña())) {

				try {
					usuarioDAO.guardar(usuario);
					System.out.println("Registro guardado satisfactoriamente");

					PrintWriter out = response.getWriter();
					response.setContentType("text/html");
					out.println("<script type=\"text/javascript\">");
					out.println("alert('Usuario creado de manera exitosa');");
					out.println("</script>");
					response.setHeader("Refresh", "0; http://localhost:8080/Proyecto6/login.jsp");

				} catch (SQLException e) {
					PrintWriter out = response.getWriter();
					response.setContentType("text/html");
					out.println("<script type=\"text/javascript\">");
					out.println("alert('No se pudo crear el usuario, por favor, intentlo de nuevo mas tarde');");
					out.println("</script>");
					response.setHeader("Refresh", "0; hhttp://localhost:8080/Proyecto6/usuario?opcion=crear");
					e.printStackTrace();
				}
			} else {
				PrintWriter out = response.getWriter();
				response.setContentType("text/html");
				out.println("<script type=\"text/javascript\">");
				out.println("alert('Las contraseña no coincide, por favor vuelvalo a intentar');");
				out.println("</script>");
				response.setHeader("Refresh", "0; http://localhost:8080/Proyecto6/usuario?opcion=crear");

			}

		} else if (opcion.equals("editar")) {
			UsuarioDAO usuarioDAO = new UsuarioDAO();
			Usuario usuario = new Usuario();
			usuario.setId_usuario(Integer.parseInt(request.getParameter("id_usuario")));
			usuario.setContraseña(request.getParameter("contraseña"));
			try {
				usuarioDAO.editar(usuario);
				System.out.println("Registro guardado satisfactoriamente");
				RequestDispatcher requestDispatcher = request.getRequestDispatcher("/index.jsp");
				requestDispatcher.forward(request, response);
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		} else if (opcion.equals("recuperar")) {

			int username = 0;
			String password = "";
			String nuevaPassword = "";

			try {
				username = Integer.parseInt(request.getParameter("id_usuario"));
				password = request.getParameter("contraseña");
				nuevaPassword = request.getParameter("nuevaContraseña");

			} catch (Exception e) {
				PrintWriter out = response.getWriter();

				response.setContentType("text/html");
				out.println("<script type=\"text/javascript\">");
				out.println("alert('Campos con valores incorrectos');");
				out.println("</script>");
				response.setHeader("Refresh", "0; http://localhost:8080/Proyecto6/login.jsp");
			}

			Usuario usuario = new Usuario();
			Usuario usuarioComprobar = new Usuario();

			usuario.setId_usuario(username);

			usuario.setContraseña(password);
			usuarioComprobar.setContraseña(nuevaPassword);

			usuario.setTipousuario(0);
			UsuarioDAO usuarioDAO = new UsuarioDAO();
			if (usuario.getContraseña().equals(usuarioComprobar.getContraseña())) {

				try {
					if (usuarioDAO.validateUsuario(username)) {

						usuario.setContraseña(nuevaPassword);
						usuarioDAO.editar(usuario);

						PrintWriter out = response.getWriter();

						response.setContentType("text/html");
						out.println("<script type=\"text/javascript\">");
						out.println("alert('Contraseña Actualizada con exito');");
						out.println("</script>");
						response.setHeader("Refresh", "0; http://localhost:8080/Proyecto6/login.jsp");

					} else {

						PrintWriter out = response.getWriter();

						response.setContentType("text/html");
						out.println("<script type=\"text/javascript\">");
						out.println("alert('Usuario o contraseña incorrectos');");
						out.println("</script>");
						response.setHeader("Refresh", "0; http://localhost:8080/Proyecto6/usuario?opcion=recuperar");
					}
				} catch (SQLException e) {
					e.printStackTrace();
				} catch (ClassNotFoundException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}

			} else {
				PrintWriter out = response.getWriter();

				response.setContentType("text/html");
				out.println("<script type=\"text/javascript\">");
				out.println("alert('Las contraseñas no coninciden, por favor intentelo de nuevo');");
				out.println("</script>");
				response.setHeader("Refresh", "0; http://localhost:8080/Proyecto6/usuario?opcion=recuperar");

			}

			// doGet(request, response);

		}
	}

}