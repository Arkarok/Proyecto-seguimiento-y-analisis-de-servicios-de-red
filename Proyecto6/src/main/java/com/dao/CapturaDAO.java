package com.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.conexion.Conexion;
import com.model.Captura;

public class CapturaDAO {

	private Connection connection;
	private PreparedStatement statement;
	private boolean estadoOperacion;

	public boolean guardar(Captura captura) throws SQLException {
		String sql = null;
		estadoOperacion = false;
		connection = obtenerConexion();

		try {
			connection.setAutoCommit(false);
			sql = "INSERT INTO Captura (codigo_captura, tiempoInicio, duracion, Usuario_id_Usuario) VALUES(?, ?, ?, ?)";
			statement = connection.prepareStatement(sql);

			statement.setString(1, null);
			statement.setTimestamp(2, captura.getTiempoInicio());
			statement.setInt(3, captura.getDuracion());
			statement.setInt(4, captura.getId_usuario());

			estadoOperacion = statement.executeUpdate() > 0;

			connection.commit();
			statement.close();
			connection.close();

		} catch (SQLException e) {

			connection.rollback();
			e.printStackTrace();
		}

		return estadoOperacion;
	}

	public int getLastID() throws SQLException {

		ResultSet resultSet = null;
		String sql = null;
		estadoOperacion = false;
		connection = obtenerConexion();

		sql = "SELECT MAX(codigo_captura) FROM Captura";
		statement = connection.prepareStatement(sql);

		resultSet = statement.executeQuery();

		return (resultSet.next()) ? resultSet.getInt(1) : 0;
	}

	public boolean eliminar(int codigo_captura) throws SQLException {
		String sql = null;
		estadoOperacion = false;
		connection = obtenerConexion();

		try {

			connection.setAutoCommit(false);
			sql = "DELETE FROM Captura WHERE codigo_captura=?";
			statement = connection.prepareStatement(sql);

			statement.setInt(1, codigo_captura);

			estadoOperacion = statement.executeUpdate() > 0;

			connection.commit();
			statement.close();
			connection.close();

		} catch (SQLException e) {
			connection.rollback();
			e.printStackTrace();
		}

		return estadoOperacion;
	}

	public List<Captura> obtener() throws SQLException {

		ResultSet resultSet = null;
		List<Captura> listaCapturas = new ArrayList<>();

		String sql = null;
		estadoOperacion = false;
		connection = obtenerConexion();

		try {
			sql = "SELECT * FROM Captura;";
			statement = connection.prepareStatement(sql);
			resultSet = statement.executeQuery(sql);

			while (resultSet.next()) {

				Captura cap = new Captura();
				cap.setCodigo_captura(resultSet.getInt(1));
				cap.setTiempoInicio(resultSet.getTimestamp(2));
				cap.setDuracion(resultSet.getInt(3));
				cap.setId_usuario(resultSet.getInt(4));

				listaCapturas.add(cap);

			}

		} catch (SQLException e) {
			e.printStackTrace();
		}

		return listaCapturas;

	}

	public List<Captura> obtenerCap(int usuario) throws SQLException {

		ResultSet resultSet = null;
		List<Captura> listaCapturas = new ArrayList<>();

		String sql = null;
		estadoOperacion = false;
		connection = obtenerConexion();

		try {
			sql = "SELECT * FROM Captura cap, usuario usu \r\n"
					+ "WHERE usu.id_usuario=? and usu.TipoUsuario_codigo=0\r\n"
					+ "and cap.Usuario_id_Usuario=usu.id_usuario;";
			statement = connection.prepareStatement(sql);
			statement.setInt(1, usuario);
			resultSet = statement.executeQuery();

			while (resultSet.next()) {

				Captura cap = new Captura();
				cap.setCodigo_captura(resultSet.getInt(1));
				cap.setTiempoInicio(resultSet.getTimestamp(2));
				cap.setDuracion(resultSet.getInt(3));
				cap.setId_usuario(resultSet.getInt(4));

				listaCapturas.add(cap);

			}

		} catch (SQLException e) {
			e.printStackTrace();
		}

		return listaCapturas;

	}
	
	public List<Captura> obtenerCapCom(int usuario) throws SQLException {

		ResultSet resultSet = null;
		List<Captura> listaCapturas = new ArrayList<>();

		String sql = null;
		estadoOperacion = false;
		connection = obtenerConexion();

		try {
			sql = "select Usuario_id_usuario ,tiempoInicio, duracion, codigo_captura \r\n"
					+ "from Usuario usu, captura cap\r\n"
					+ "where usu.id_usuario!=?\r\n"
					+ "and usu.id_usuario=cap.Usuario_id_usuario;";
			statement = connection.prepareStatement(sql);
			statement.setInt(1, usuario);
			resultSet = statement.executeQuery();

			while (resultSet.next()) {

				Captura cap = new Captura();
				cap.setCodigo_captura(resultSet.getInt(4));
				cap.setTiempoInicio(resultSet.getTimestamp(2));
				cap.setDuracion(resultSet.getInt(3));
				cap.setId_usuario(resultSet.getInt(1));

				listaCapturas.add(cap);

			}

		} catch (SQLException e) {
			e.printStackTrace();
		}

		return listaCapturas;

	}


	public Captura obtenerCaptura(int codigo_captura) throws SQLException {

		ResultSet resultSet = null;
		Captura cap = new Captura();

		String sql = null;
		estadoOperacion = false;
		connection = obtenerConexion();

		try {
			sql = "SELECT * FROM Captura WHERE codigo_captura=?";
			statement = connection.prepareStatement(sql);
			statement.setInt(1, codigo_captura);

			resultSet = statement.executeQuery();

			if (resultSet.next()) {

				cap.setCodigo_captura(resultSet.getInt(1));
				cap.setTiempoInicio(resultSet.getTimestamp(2));
				cap.setDuracion(resultSet.getInt(3));
				cap.setId_usuario(resultSet.getInt(4));

			}

		} catch (SQLException e) {
			e.printStackTrace();
		}

		return cap;

	}

	private Connection obtenerConexion() throws SQLException {
		return Conexion.getConnection();

	}

}
