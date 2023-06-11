package com.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.conexion.Conexion;
import com.model.Paquete;

public class PaqueteDAO {

	private Connection connection;
	private PreparedStatement statement;
	private boolean estadoOperacion;

	public boolean guardar(Paquete paquete) throws SQLException {
		String sql = null;
		estadoOperacion = false;
		connection = obtenerConexion();

		try {
			connection.setAutoCommit(false);
			sql = "INSERT INTO Paquete (idPaquete, ipOrigen, puertoDestino, ipDestino, Captura_codigo_captura, Servicios_idServicios, tamaño, mac) VALUES(?, ?, ?, ?, ?, ?, ?, ?)";
			statement = connection.prepareStatement(sql);

			statement.setString(1, null);
			statement.setString(2, paquete.getIpOrigen());
			statement.setInt(3, paquete.getPuertoDestino());
			statement.setString(4, paquete.getIpDestino());
			statement.setInt(5, paquete.getCodigo_captura());
			statement.setInt(6, paquete.getIdServicios());
			statement.setInt(7, paquete.getTamaño());
			statement.setString(8, paquete.getMac());

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

		sql = "SELECT MAX(idPaquete) FROM Paquete";
		statement = connection.prepareStatement(sql);
		resultSet = statement.executeQuery();

		return (resultSet.next()) ? resultSet.getInt(1) : 0;
	}

	public boolean eliminar(int idPaquete) throws SQLException {
		String sql = null;
		estadoOperacion = false;
		connection = obtenerConexion();

		try {

			connection.setAutoCommit(false);
			sql = "DELETE FROM Paquete WHERE idPaquete=?";
			statement = connection.prepareStatement(sql);

			statement.setInt(1, idPaquete);

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

	public List<Paquete> obtener() throws SQLException {

		ResultSet resultSet = null;
		List<Paquete> listaPaquetes = new ArrayList<>();

		String sql = null;
		estadoOperacion = false;
		connection = obtenerConexion();

		try {
			sql = "SELECT * FROM Paquete";
			statement = connection.prepareStatement(sql);
			resultSet = statement.executeQuery(sql);

			while (resultSet.next()) {

				Paquete paq = new Paquete();

				paq.setIdPaquete(resultSet.getInt(1));
				paq.setIpOrigen(resultSet.getString(2));
				paq.setPuertoDestino(resultSet.getInt(3));
				paq.setIpDestino(resultSet.getString(4));
				paq.setCodigo_captura(resultSet.getInt(5));
				paq.setIdServicios(resultSet.getInt(6));
				paq.setTamaño(resultSet.getInt(7));
				paq.setMac(resultSet.getString(8));

				listaPaquetes.add(paq);

			}

		} catch (SQLException e) {
			e.printStackTrace();
		}

		return listaPaquetes;

	}

	public List<Paquete> obtenerPaq(int usuario) throws SQLException {

		ResultSet resultSet = null;
		List<Paquete> listaCapturas = new ArrayList<>();

		String sql = null;
		estadoOperacion = false;
		connection = obtenerConexion();

		try {
			sql = "select * from paquete where paquete.captura_codigo_captura=?;";
			statement = connection.prepareStatement(sql);
			statement.setInt(1, usuario);
			resultSet = statement.executeQuery();

			while (resultSet.next()) {

				Paquete paq = new Paquete();
				paq.setIdPaquete(resultSet.getInt(1));
				paq.setIpOrigen(resultSet.getString(2));
				paq.setPuertoDestino(resultSet.getInt(3));
				paq.setIpDestino(resultSet.getString(4));
				paq.setCodigo_captura(resultSet.getInt(5));
				paq.setIdServicios(resultSet.getInt(6));
				paq.setTamaño(resultSet.getInt(7));
				paq.setMac(resultSet.getString(8));

				listaCapturas.add(paq);

			}

		} catch (SQLException e) {
			e.printStackTrace();
		}

		return listaCapturas;

	}

	public Paquete obtenerPaquete(int idPaquete) throws SQLException {

		ResultSet resultSet = null;
		Paquete paq = new Paquete();

		String sql = null;
		estadoOperacion = false;
		connection = obtenerConexion();

		try {
			sql = "SELECT * FROM Paquete WHERE idPaquete=?";
			statement = connection.prepareStatement(sql);
			statement.setInt(1, idPaquete);

			resultSet = statement.executeQuery();

			if (resultSet.next()) {

				paq.setIdPaquete(resultSet.getInt(1));
				paq.setIpOrigen(resultSet.getString(2));
				paq.setPuertoDestino(resultSet.getInt(3));
				paq.setIpDestino(resultSet.getString(4));
				paq.setCodigo_captura(resultSet.getInt(5));
				paq.setIdServicios(resultSet.getInt(6));
				paq.setTamaño(resultSet.getInt(7));
				paq.setMac(resultSet.getString(8));

			}

		} catch (SQLException e) {
			e.printStackTrace();
		}

		return paq;

	}

	private Connection obtenerConexion() throws SQLException {
		return Conexion.getConnection();

	}

}
