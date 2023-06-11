package com.model;

public class Usuario {

	private int id_usuario;
	private String contrase�a;
	private int tipousuario;

	public Usuario(int id_usuario, String contrase�a, int tipousuario) {
		super();
		this.id_usuario = id_usuario;
		this.contrase�a = contrase�a;
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

	public String getContrase�a() {
		return contrase�a;
	}

	public void setContrase�a(String contrase�a) {
		this.contrase�a = contrase�a;
	}

	public int getTipousuario() {
		return tipousuario;
	}

	public void setTipousuario(int tipousuario) {
		this.tipousuario = tipousuario;
	}

	@Override
	public String toString() {
		return "Usuario [id_usuario=" + id_usuario + ", contrase�a=" + contrase�a + ", tipousuario=" + tipousuario
				+ "]";
	}

}
