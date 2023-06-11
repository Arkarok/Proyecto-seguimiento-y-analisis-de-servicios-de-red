package com.model;

public class Usuario {

	private int id_usuario;
	private String contraseña;
	private int tipousuario;

	public Usuario(int id_usuario, String contraseña, int tipousuario) {
		super();
		this.id_usuario = id_usuario;
		this.contraseña = contraseña;
		this.tipousuario = tipousuario;
	}

	public Usuario() {
		// TODO Auto-generated constructor stub
	}

	public int getId_usuario() {
		return id_usuario;
	}

	public void setId_usuario(int id_usuario) {
		this.id_usuario = id_usuario;
	}

	public String getContraseña() {
		return contraseña;
	}

	public void setContraseña(String contraseña) {
		this.contraseña = contraseña;
	}

	public int getTipousuario() {
		return tipousuario;
	}

	public void setTipousuario(int tipousuario) {
		this.tipousuario = tipousuario;
	}

	@Override
	public String toString() {
		return "Usuario [id_usuario=" + id_usuario + ", contraseña=" + contraseña + ", tipousuario=" + tipousuario
				+ "]";
	}

}
