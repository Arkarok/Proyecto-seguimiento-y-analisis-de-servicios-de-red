package com.controller;

import java.awt.Dimension;
import java.io.IOException;
import java.io.OutputStream;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.swing.plaf.DimensionUIResource;

import org.jfree.chart.ChartFactory;
import org.jfree.chart.ChartPanel;
import org.jfree.chart.ChartUtilities;
import org.jfree.chart.JFreeChart;
import org.jfree.chart.axis.CategoryAxis;
import org.jfree.chart.axis.CategoryLabelPositions;
import org.jfree.chart.plot.CategoryPlot;
import org.jfree.chart.plot.PlotOrientation;
import org.jfree.data.category.DefaultCategoryDataset;
import org.jfree.data.general.DefaultPieDataset;

import com.conexion.Conexion;

/**
 * Servlet implementation class EstadisticaController
 */
@WebServlet("/Estadistica")
public class EstadisticaController extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private Connection connection;
	private PreparedStatement statement;
	public static int id = 0;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public EstadisticaController() {
		super();
		// TODO Auto-generated constructor stub
	}

	float CalculatePct(int num, int total) {
		return total != 0 ? (num * 100.0f) / (total * 1.0f) : 0;
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
		if (opcion.equals("verEstadistica")) {
			id = Integer.parseInt(request.getParameter("id"));
			RequestDispatcher requestDispatcher = request.getRequestDispatcher("/Views/estadisticas.jsp");
			requestDispatcher.forward(request, response);
		} else if (opcion.equals("graficoTorta")) {// grafico captura
			response.setContentType("image/PNG");
			OutputStream out = response.getOutputStream();
			DefaultCategoryDataset data = new DefaultCategoryDataset();
			ResultSet resultSet = null;

			String sql = null;

			try {
				connection = obtenerConexion();
				sql = "select nombreServicio, sum(tamaño) \r\n" + "from captura cap, paquete paq, servicios ser\r\n"
						+ "where cap.codigo_captura=?\r\n" + "and cap.codigo_captura=paq.Captura_codigo_captura\r\n"
						+ "and  paq.servicios_idServicios=ser.idServicios\r\n" + "group by nombreServicio;";

				statement = connection.prepareStatement(sql);
				statement.setInt(1, id);
				resultSet = statement.executeQuery();

				while (resultSet.next()) {
					data.setValue(resultSet.getInt(2), "tamaño por paquete", resultSet.getString(1));
				}

				JFreeChart chart = ChartFactory.createBarChart("Tamaño total de las tramas por servicio", "Servicios",
						"Tamaño total de las tramas en Bytes", data, PlotOrientation.VERTICAL, true, true,
						false);

				int ancho = 750;
				int alto = 600;

				ChartUtilities.writeChartAsPNG(out, chart, ancho, alto);

				statement.close();
				resultSet.close();
				connection.close();

			} catch (SQLException e1) {

				e1.printStackTrace();
			}
		} else if (opcion.equals("grafitoBarra")) {// grafico de la red
			response.setContentType("image/PNG");
			OutputStream out = response.getOutputStream();
			DefaultCategoryDataset data = new DefaultCategoryDataset();
			ResultSet resultSet = null;
			String sql = null;

			try {
				connection = obtenerConexion();
				sql = "select nombreServicio, count(paq.Servicios_idServicios)\r\n"
						+ "from paquete paq, Servicios ser\r\n" + "where paq.Servicios_idServicios=ser.idServicios\r\n"
						+ "group by(nombreServicio);";

				statement = connection.prepareStatement(sql);
				resultSet = statement.executeQuery();

				while (resultSet.next()) {
					data.setValue(resultSet.getInt(2), "tamaño por paquete", resultSet.getString(1));
				}

				JFreeChart chart = ChartFactory.createBarChart("Servicios consumidos en la Red", "Servicios",
						"Total de Peticiones", data, PlotOrientation.VERTICAL, true, true, false);

				int ancho = 750;
				int alto = 600;

				ChartUtilities.writeChartAsPNG(out, chart, ancho, alto);

				statement.close();
				resultSet.close();
				connection.close();

			} catch (SQLException e1) {

				e1.printStackTrace();
			}
		} else if (opcion.equals("grafitoTorta")) {// grafico de la captura
			response.setContentType("image/PNG");
			OutputStream out = response.getOutputStream();
			DefaultPieDataset data = new DefaultPieDataset();
			ResultSet resultSet = null;

			String sql = null;

			try {
				connection = obtenerConexion();
				sql = "select  nombreServicio, count(idServicios) \r\n"
						+ "from usuario usu, captura cap, Paquete paq, Servicios serv\r\n"
						+ "where usu.id_usuario=?\r\n" + "and cap.codigo_captura=?\r\n"
						+ "and usu.id_usuario=cap.Usuario_id_usuario\r\n"
						+ "and cap.codigo_captura = paq.Captura_codigo_captura\r\n"
						+ "and paq.Servicios_idServicios=serv.idServicios\r\n" + "group by (nombreServicio);";

				statement = connection.prepareStatement(sql);
				statement.setInt(1, LoginController.idusuario);
				statement.setInt(2, id);
				resultSet = statement.executeQuery();
				int HTTP = 0;
				int FTP = 0;
				int DNS = 0;
				int SMTP = 0;
				int POP3 = 0;
				int DHCP = 0;
				int HTTPS = 0;
				int FTPS = 0;
				int Otro = 0;

				while (resultSet.next()) {
					String value = resultSet.getString(1);

					if (value.equals("HTTP")) {

						HTTP = resultSet.getInt(2);
					} else if (value.equals("FTP")) {

						FTP = resultSet.getInt(2);
					} else if (value.equals("DNS")) {

						DNS = resultSet.getInt(2);
					} else if (value.equals("SMTP")) {

						SMTP = resultSet.getInt(2);
					} else if (value.equals("POP3")) {

						POP3 = resultSet.getInt(2);
					} else if (value.equals("DHCP")) {

						DHCP = resultSet.getInt(2);
					} else if (value.equals("HTTPS")) {

						HTTPS = resultSet.getInt(2);
					} else if (value.equals("FTPS")) {

						FTPS = resultSet.getInt(2);
					} else if (value.equals("Otro")) {

						Otro = resultSet.getInt(2);
					}

				}

				int total = HTTP + FTP + DNS + SMTP + POP3 + DHCP + HTTPS + FTPS + Otro;
				data.setValue("HTTP " + String.valueOf(CalculatePct(HTTP, total)) + "%", HTTP);
				data.setValue("FTP " + String.valueOf(CalculatePct(FTP, total)) + "%", FTP);
				data.setValue("DNS " + String.valueOf(CalculatePct(DNS, total)) + "%", DNS);
				data.setValue("SMTP " + String.valueOf(CalculatePct(SMTP, total)) + "%", SMTP);
				data.setValue("POP3 " + String.valueOf(CalculatePct(POP3, total)) + "%", POP3);
				data.setValue("DHCP " + String.valueOf(CalculatePct(DHCP, total)) + "%", DHCP);
				data.setValue("HTTPS " + String.valueOf(CalculatePct(HTTPS, total)) + "%", HTTPS);
				data.setValue("FTPS " + String.valueOf(CalculatePct(FTPS, total)) + "%", FTPS);
				data.setValue("Otro " + String.valueOf(CalculatePct(Otro, total)) + "%", Otro);

				JFreeChart chart = ChartFactory.createPieChart3D("Proporcion de servicios", data, true, true, true);

				int ancho = 750;
				int alto = 600;

				ChartUtilities.writeChartAsPNG(out, chart, ancho, alto);

				statement.close();
				resultSet.close();
				connection.close();

			} catch (SQLException e1) {

				e1.printStackTrace();
			}

		} else if (opcion.equals("graficoBarra")) {// grafico de la captura
			response.setContentType("image/PNG");
			OutputStream out = response.getOutputStream();
			DefaultCategoryDataset data = new DefaultCategoryDataset();
			ResultSet resultSet = null;
			String sql = null;

			try {
				connection = obtenerConexion();
				sql = "select mac, count(mac) \r\n" + "from usuario usu, captura cap, Paquete paq\r\n"
						+ "where usu.id_usuario=?\r\n" + "and  cap.codigo_captura=?\r\n"
						+ "and usu.id_usuario=cap.Usuario_id_usuario\r\n"
						+ "and cap.codigo_captura = paq.Captura_codigo_captura\r\n" + "group by (mac);";

				statement = connection.prepareStatement(sql);
				statement.setInt(1, LoginController.idusuario);
				statement.setInt(2, id);
				resultSet = statement.executeQuery();

				while (resultSet.next()) {
					data.setValue(resultSet.getInt(2), "Magnitud por Mac", resultSet.getString(1));
				}

				JFreeChart chart = ChartFactory.createBarChart("Cantidad de peticiones hacia los dispositivos",
						"dispositivos", "Cantidad de tramas", data, PlotOrientation.HORIZONTAL, true, true, false);

				int ancho = 750;
				int alto = 600;

				ChartUtilities.writeChartAsPNG(out, chart, ancho, alto);

				statement.close();
				resultSet.close();
				connection.close();

			} catch (SQLException e1) {

				e1.printStackTrace();
			}

		} else if (opcion.equals("graficoBarra2")) {
			response.setContentType("image/PNG");
			OutputStream out = response.getOutputStream();
			DefaultCategoryDataset data = new DefaultCategoryDataset();
			ResultSet resultSet = null;
			String sql = null;

			try {
				connection = obtenerConexion();
				sql = "select mac, count(mac) from Paquete paq\r\n" + "group by (mac);";

				statement = connection.prepareStatement(sql);
				resultSet = statement.executeQuery();

				while (resultSet.next()) {
					data.setValue(resultSet.getInt(2), "Magnitud por Mac", resultSet.getString(1));
				}

				JFreeChart chart = ChartFactory.createBarChart("Cantidad de peticiones hacia los dispositivos",
						"dispositivos", "Cantidad de tramas", data, PlotOrientation.HORIZONTAL, true, true, false);

				int ancho = 750;
				int alto = 600;

				ChartUtilities.writeChartAsPNG(out, chart, ancho, alto);

				statement.close();
				resultSet.close();
				connection.close();

			} catch (SQLException e1) {

				e1.printStackTrace();
			}

		} else if (opcion.equals("graficoLineas")) {
			response.setContentType("image/PNG");
			OutputStream out = response.getOutputStream();
			DefaultCategoryDataset data = new DefaultCategoryDataset();
			ResultSet resultSet = null;
			String sql = null;

			try {
				connection = obtenerConexion();
				sql = "select tiempoInicio, sum(tamaño) from captura cap, paquete paq\r\n"
						+ "where cap.codigo_captura=paq.Captura_Codigo_captura\r\n" + "group by (tiempoInicio);";

				statement = connection.prepareStatement(sql);
				resultSet = statement.executeQuery();

				while (resultSet.next()) {
					data.setValue(resultSet.getInt(2), "Magnitud por Mac", resultSet.getString(1));
				}

				JFreeChart chart = ChartFactory.createLineChart("Trafico en la red", "Tiempo",
						"Longitud total de tramas en Bytes", data, PlotOrientation.VERTICAL, true, true, false);

				CategoryPlot categoryPlot = chart.getCategoryPlot();
				CategoryAxis categoryAxis = categoryPlot.getDomainAxis();

				categoryAxis.setCategoryLabelPositions(CategoryLabelPositions.UP_90);

				int ancho = 750;
				int alto = 600;

				ChartUtilities.writeChartAsPNG(out, chart, ancho, alto);

				statement.close();
				resultSet.close();
				connection.close();

			} catch (SQLException e1) {

				e1.printStackTrace();
			}

		}
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

	private Connection obtenerConexion() throws SQLException {
		return Conexion.getConnection();

	}

}
