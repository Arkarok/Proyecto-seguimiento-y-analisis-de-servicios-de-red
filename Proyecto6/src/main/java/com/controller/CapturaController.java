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

import com.dao.CapturaDAO;
import com.dao.PaqueteDAO;
import com.model.Captura;
import com.model.Paquete;

import Rastreador.Sniffer;

/**
 * Servlet implementation class CapturaController
 */
@WebServlet("/captura")
public class CapturaController extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public CapturaController() {
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
		/*
		 * response.getWriter().append("Served at: ").append(request.getContextPath());
		 */

		String opcion = request.getParameter("opcion");
		if (opcion.equals("vercaptura")) {
			int id = Integer.parseInt(request.getParameter("id"));
			System.out.println(id);
			CapturaDAO capDAO = new CapturaDAO();
			List<Captura> lista = new ArrayList<>();

			try {
				lista = capDAO.obtenerCap(id);
				for (Captura captura : lista) {
					System.out.println(captura);
				}

				request.setAttribute("lista", lista);

				RequestDispatcher requestDispatcher = request.getRequestDispatcher("captura.jsp");
				requestDispatcher.forward(request, response);

			} catch (SQLException e) {
				e.printStackTrace();
			}
			System.out.println("Usted a elegido listar los usuarios");

		} else if (opcion.equals("capturacompartida")) {
			CapturaDAO capDAO = new CapturaDAO();
			List<Captura> lista = new ArrayList<>();
			int id = LoginController.idusuario;

			try {
				lista = capDAO.obtenerCapCom(id);
				for (Captura captura : lista) {
					System.out.println(captura);
				}

				request.setAttribute("lista", lista);

				RequestDispatcher requestDispatcher = request.getRequestDispatcher("/capturaC.jsp");
				requestDispatcher.forward(request, response);

			} catch (SQLException e) {
				e.printStackTrace();
			}
			System.out.println("Usted a elegido listar los usuarios");

		} else if (opcion.equals("verpaquete")) {
			int id = Integer.parseInt(request.getParameter("id"));

			System.out.println(id);
			PaqueteDAO paqDAO = new PaqueteDAO();
			List<Paquete> lista = new ArrayList<>();

			try {
				lista = paqDAO.obtenerPaq(id);
				for (Paquete paquete : lista) {
					System.out.println(paquete);
				}

				request.setAttribute("lista", lista);

				RequestDispatcher requestDispatcher = request.getRequestDispatcher("paquete.jsp");
				requestDispatcher.forward(request, response);

			} catch (SQLException e) {
				e.printStackTrace();
			}
			System.out.println("Usted a elegido listar los usuarios");

		} else if (opcion.equals("eliminar")) {
			CapturaDAO capDAO = new CapturaDAO();
			int id = Integer.parseInt(request.getParameter("id"));
			try {
				capDAO.eliminar(id);
				System.out.println("Registro eliminado satisfactoriamente");
				RequestDispatcher requestDispatcher = request.getRequestDispatcher("/index.jsp");
				requestDispatcher.forward(request, response);
			} catch (SQLException e) {
				e.printStackTrace();
			}

		} else if (opcion.equals("listarcaptura")) {
			int id = LoginController.idusuario;
			System.out.println(id);
			CapturaDAO capDAO = new CapturaDAO();
			List<Captura> lista = new ArrayList<>();

			try {
				lista = capDAO.obtenerCap(id);
				for (Captura captura : lista) {
					System.out.println(captura);
				}

				request.setAttribute("lista", lista);

				RequestDispatcher requestDispatcher = request.getRequestDispatcher("/Views/listar.jsp");
				requestDispatcher.forward(request, response);

			} catch (SQLException e) {
				e.printStackTrace();
			}
			System.out.println("Usted a elegido listar los usuarios");
		}

	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// TODO Auto-generated method stub
		// doGet(request, response);
		int tiempo = 0;
		long t = System.currentTimeMillis();
		java.sql.Timestamp z = new java.sql.Timestamp(t);
		Captura cap = new Captura();
		CapturaDAO capDao = new CapturaDAO();
		int time = Integer.parseInt(request.getParameter("tiempo"));
		int usuario = LoginController.idusuario;
		if (time == 20) {
			tiempo = 20;
			time = time * 1000;

		} else if (time == 40) {
			tiempo = 40;
			time = time * 1000;
		}

		cap.setDuracion(tiempo);
		cap.setId_usuario(usuario);
		cap.setTiempoInicio(z);

		try {
			capDao.guardar(cap);
			Sniffer s = new Sniffer();
			s.sniff(time);
			PrintWriter out = response.getWriter();

			response.setContentType("text/html");
            out.println("<script type=\"text/javascript\">");
            out.println("alert('Captura terminada');");
            out.println("</script>");
            response.setHeader("Refresh", "0; http://localhost:8080/Proyecto6/login?opcion=captura");

		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}

}
