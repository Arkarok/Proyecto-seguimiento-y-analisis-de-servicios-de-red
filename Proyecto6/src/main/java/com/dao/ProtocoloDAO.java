package com.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.conexion.Conexion;
import com.model.Protocolo;

public class ProtocoloDAO {

	private Connection connection;
	private PreparedStatement statement;
	private boolean estadoOperacion;

	public boolean guardar(Protocolo protocolo) throws SQLException {
		String sql = null;
		estadoOperacion = false;
		connection = obtenerConexion();

		try {
			connection.setAutoCommit(false);
			sql = "INSERT INTO Protocolo (idProtocolo, nombreProtocolo) VALUES(?, ?)";
			statement = connection.prepareStatement(sql);

			statement.setString(1, null);
			statement.setString(2, protocolo.getNombreProtocolo());

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

	public List<Protocolo> obtener() throws SQLException {

		ResultSet resultSet = null;
		List<Protocolo> listaProtocolos = new ArrayList<>();

		String sql = null;
		estadoOperacion = false;
		connection = obtenerConexion();

		try {
			sql = "SELECT * FROM Protocolo";
			statement = connection.prepareStatement(sql);
			resultSet = statement.executeQuery(sql);

			while (resultSet.next()) {

				Protocolo pro = new Protocolo();

				pro.setIdProtocolo(resultSet.getInt(1));
				pro.setNombreProtocolo(resultSet.getString(2));

				listaProtocolos.add(pro);

			}

		} catch (SQLException e) {
			e.printStackTrace();
		}

		return listaProtocolos;

	}

	public Protocolo obtenerProtocolo(int idProtocolo) throws SQLException {

		ResultSet resultSet = null;
		Protocolo pro = new Protocolo();

		String sql = null;
		estadoOperacion = false;
		connection = obtenerConexion();

		try {
			sql = "SELECT * FROM Protocolo WHERE idProtocolo=?";
			statement = connection.prepareStatement(sql);
			statement.setInt(1, idProtocolo);

			resultSet = statement.executeQuery();

			if (resultSet.next()) {

				pro.setIdProtocolo(resultSet.getInt(1));
				pro.setNombreProtocolo(resultSet.getNString(2));

			}

		} catch (SQLException e) {
			e.printStackTrace();
		}

		return pro;

	}

	private Connection obtenerConexion() throws SQLException {
		return Conexion.getConnection();

	}

}
