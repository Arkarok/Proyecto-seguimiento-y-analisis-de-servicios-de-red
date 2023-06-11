package com.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.conexion.Conexion;
import com.model.Protocolo_has_Paquete;

public class Protocolo_has_PaqueteDAO {

	private Connection connection;
	private PreparedStatement statement;
	private boolean estadoOperacion;

	public boolean guardar(Protocolo_has_Paquete protocolo_has_paquete) throws SQLException {

		String sql = null;
		estadoOperacion = false;
		connection = obtenerConexion();

		try {
			connection.setAutoCommit(false);
			sql = "INSERT INTO protocolo_has_paquete (Protocolo_idProtocolo, Paquete_idPaquete) VALUES(?,?)";
			statement = connection.prepareStatement(sql);

			statement.setInt(1, protocolo_has_paquete.getIdProtocolo());
			statement.setInt(2, protocolo_has_paquete.getIdPaquete());

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

	public List<Protocolo_has_Paquete> obtener() throws SQLException {

		ResultSet resultSet = null;
		List<Protocolo_has_Paquete> listaP_has_P = new ArrayList<>();

		String sql = null;
		estadoOperacion = false;
		connection = obtenerConexion();

		try {
			sql = "SELECT * FROM Protocolo_has_Paquete";
			statement = connection.prepareStatement(sql);
			resultSet = statement.executeQuery(sql);

			while (resultSet.next()) {

				Protocolo_has_Paquete phap = new Protocolo_has_Paquete();
				phap.setIdProtocolo(resultSet.getInt(1));
				phap.setIdPaquete(resultSet.getInt(2));

				listaP_has_P.add(phap);

			}

		} catch (SQLException e) {
			e.printStackTrace();
		}

		return listaP_has_P;

	}

	private Connection obtenerConexion() throws SQLException {
		return Conexion.getConnection();

	}

}
