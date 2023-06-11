package com.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.conexion.Conexion;
import com.model.Servicios;

public class ServiciosDAO {

	private Connection connection;
	private PreparedStatement statement;
	private boolean estadoOperacion;

	public boolean guardar(Servicios servicio) throws SQLException {

		String sql = null;
		estadoOperacion = false;
		connection = obtenerConexion();

		try {
			connection.setAutoCommit(false);
			sql = "INSERT INTO Servicios (idServicios, nombreServicio) VALUES(?,?)";
			statement = connection.prepareStatement(sql);

			statement.setString(1, null);
			statement.setString(2, servicio.getNombreServicio());

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

	public List<Servicios> obtener() throws SQLException {

		ResultSet resultSet = null;
		List<Servicios> listaServicios = new ArrayList<>();

		String sql = null;
		estadoOperacion = false;
		connection = obtenerConexion();

		try {
			sql = "SELECT * FROM Servicios";
			statement = connection.prepareStatement(sql);
			resultSet = statement.executeQuery(sql);

			while (resultSet.next()) {

				Servicios ser = new Servicios();
				ser.setIdServicios(resultSet.getInt(1));
				ser.setNombreServicio(resultSet.getString(2));

				listaServicios.add(ser);

			}

		} catch (SQLException e) {
			e.printStackTrace();
		}

		return listaServicios;

	}

	public Servicios obtenerServicio(int idServicios) throws SQLException {

		ResultSet resultSet = null;
		Servicios ser = new Servicios();

		String sql = null;
		estadoOperacion = false;
		connection = obtenerConexion();

		try {
			sql = "SELECT * FROM Servicios WHERE idServicios=?";
			statement = connection.prepareStatement(sql);
			statement.setInt(1, idServicios);

			resultSet = statement.executeQuery();

			if (resultSet.next()) {

				ser.setIdServicios(resultSet.getInt(1));
				ser.setNombreServicio(resultSet.getString(2));

			}

		} catch (SQLException e) {
			e.printStackTrace();
		}

		return ser;

	}

	private Connection obtenerConexion() throws SQLException {
		return Conexion.getConnection();

	}

}
