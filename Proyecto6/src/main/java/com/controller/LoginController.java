package com.controller;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.SQLException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.dao.UsuarioDAO;
import com.model.Usuario;

/**
 * Servlet implementation class LoginController
 */
@WebServlet("/login")
public class LoginController extends HttpServlet {

	public static int idusuario = 0;

	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public LoginController() {
		super();
		// TODO Auto-generated constructor stub
	}

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// TODO Auto-generated method stub
		// response.getWriter().append("Served at: ").append(request.getContextPath());
		String opcion = request.getParameter("opcion");
		if (opcion.equals("captura")) {
			RequestDispatcher requestDispatcher = request.getRequestDispatcher("combobox.jsp");
			requestDispatcher.forward(request, response);

		}
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		int username=0;
		String password="";
		try {
			username = Integer.parseInt(request.getParameter("id_usuario"));
			password = request.getParameter("contraseña");
		} catch (Exception e) {
			PrintWriter out = response.getWriter();

			response.setContentType("text/html");
            out.println("<script type=\"text/javascript\">");
            out.println("alert('Campos con valores incorrectos');");
            out.println("</script>");
            response.setHeader("Refresh", "0; http://localhost:8080/Proyecto6/login.jsp");
		}

		Usuario usuario = new Usuario();
		usuario.setId_usuario(username);
		usuario.setContraseña(password);
		usuario.setTipousuario(0);
		UsuarioDAO usuarioDAO = new UsuarioDAO();

		try {
			if (usuarioDAO.validate(username, password)) {
				idusuario = username;

				if (usuarioDAO.validateTipo(usuario)) {
					response.sendRedirect("index.jsp");

				} else {
					usuario.setId_usuario(username);
					usuario.setContraseña(password);
					usuario.setTipousuario(1);

					if (usuarioDAO.validateTipo(usuario)) {
						response.sendRedirect("index_admin.jsp");
					}
				}

			} else {
				
				PrintWriter out = response.getWriter();

				response.setContentType("text/html");
	            out.println("<script type=\"text/javascript\">");
	            out.println("alert('Usuario o contraseña incorrectos');");
	            out.println("</script>");
	            response.setHeader("Refresh", "0; http://localhost:8080/Proyecto6/login.jsp");
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	// doGet(request, response);
}
