package com.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.conexion.Conexion;
import com.model.Usuario;

public class UsuarioDAO {

	private Connection connection;
	private PreparedStatement statement;
	private boolean estadoOperacion;

	// METODO GUARDAR
	public boolean guardar(Usuario usuario) throws SQLException {

		String sql = null;
		estadoOperacion = false;
		connection = obtenerConexion();

		try {
			connection.setAutoCommit(false);
			sql = "INSERT INTO usuario (id_usuario, contraseña, TipousUario_codigo) VALUES(?,?,?)";
			statement = connection.prepareStatement(sql);

			statement.setInt(1, usuario.getId_usuario());
			statement.setString(2, usuario.getContraseña());
			statement.setInt(3, usuario.getTipousuario());

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

	// METODO EDITAR
	public boolean editar(Usuario usuario) throws SQLException {
		String sql = null;
		estadoOperacion = false;
		connection = obtenerConexion();

		try {
			connection.setAutoCommit(false);
			sql = "UPDATE usuario SET contraseña=? WHERE id_usuario=?";
			statement = connection.prepareStatement(sql);

			statement.setString(1, usuario.getContraseña());
			statement.setInt(2, usuario.getId_usuario());

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

	// METODO ELIMINAR
	public boolean eliminar(int idusuario) throws SQLException {

		String sql = null;
		estadoOperacion = false;
		connection = obtenerConexion();

		try {

			connection.setAutoCommit(false);
			sql = "DELETE FROM usuario WHERE id_usuario=?";
			statement = connection.prepareStatement(sql);

			statement.setInt(1, idusuario);

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

	// METODO OBTENER
	public List<Usuario> obtener() throws SQLException {

		ResultSet resultSet = null;
		List<Usuario> listaUsuarios = new ArrayList<>();

		String sql = null;
		estadoOperacion = false;
		connection = obtenerConexion();

		try {
			sql = "SELECT * FROM usuario \r\n" + "where usuario.TipoUsuario_codigo!=1;";
			statement = connection.prepareStatement(sql);
			resultSet = statement.executeQuery(sql);

			while (resultSet.next()) {

				Usuario usu = new Usuario();
				usu.setId_usuario(resultSet.getInt(1));
				usu.setContraseña(resultSet.getString(2));
				usu.setTipousuario(resultSet.getInt(3));

				listaUsuarios.add(usu);

			}

		} catch (SQLException e) {
			e.printStackTrace();
		}

		return listaUsuarios;

	}

	// METODO OBTENER
	public Usuario obtenerUsuario(int idusuario) throws SQLException {

		ResultSet resultSet = null;
		Usuario usu = new Usuario();

		String sql = null;
		estadoOperacion = false;
		connection = obtenerConexion();

		try {
			sql = "SELECT * FROM usuario WHERE id_usuario=?";
			statement = connection.prepareStatement(sql);
			statement.setInt(1, idusuario);

			resultSet = statement.executeQuery();

			if (resultSet.next()) {

				usu.setId_usuario(resultSet.getInt(1));
				usu.setContraseña(resultSet.getString(2));
				usu.setTipousuario(resultSet.getInt(3));

			}

		} catch (SQLException e) {
			e.printStackTrace();
		}

		return usu;

	}

	public boolean validate(int idusuario, String contraseña) throws ClassNotFoundException, SQLException {
		String sql = null;
		estadoOperacion = false;
		connection = obtenerConexion();

		try {
			sql = "select * from usuario where id_usuario=? and contraseña=?";
			statement = connection.prepareStatement(sql);
			statement.setInt(1, idusuario);
			statement.setString(2, contraseña);
			ResultSet rs = statement.executeQuery();
			estadoOperacion = rs.next();

		} catch (SQLException e) {
			printSQLException(e);
		}
		return estadoOperacion;
	}

	public boolean validateUsuario(int idusuario) throws ClassNotFoundException, SQLException {
		String sql = null;
		estadoOperacion = false;
		connection = obtenerConexion();

		try {
			sql = "select * from usuario where id_usuario=?";
			statement = connection.prepareStatement(sql);
			statement.setInt(1, idusuario);
			ResultSet rs = statement.executeQuery();
			estadoOperacion = rs.next();

		} catch (SQLException e) {
			printSQLException(e);
		}
		return estadoOperacion;
	}

	public boolean validateTipo(Usuario usuario) throws SQLException {
		String sql = null;
		estadoOperacion = false;
		connection = obtenerConexion();

		try {
			sql = "select * from usuario where id_usuario=? and contraseña=? and TipoUsuario_codigo=?";
			statement = connection.prepareStatement(sql);
			statement.setInt(1, usuario.getId_usuario());
			statement.setString(2, usuario.getContraseña());
			statement.setInt(3, usuario.getTipousuario());
			ResultSet rs = statement.executeQuery();
			estadoOperacion = rs.next();

		} catch (SQLException e) {
			printSQLException(e);
		}
		return estadoOperacion;

	}

	private void printSQLException(SQLException ex) {
		for (Throwable e : ex) {
			if (e instanceof SQLException) {
				e.printStackTrace(System.err);
				System.err.println("SQLState: " + ((SQLException) e).getSQLState());
				System.err.println("Error Code: " + ((SQLException) e).getErrorCode());
				System.err.println("Message: " + e.getMessage());
				Throwable t = ex.getCause();
				while (t != null) {
					System.out.println("Cause: " + t);
					t = t.getCause();
				}
			}
		}
	}

	// OBTENER CONEXION
	private Connection obtenerConexion() throws SQLException {
		return Conexion.getConnection();

	}

}