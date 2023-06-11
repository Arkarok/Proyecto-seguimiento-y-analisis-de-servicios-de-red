package com.model;

public class TipoUsuario {
	
	private int codigo;
	private String nombre_tipo;
	
	public TipoUsuario(int codigo, String nombre_tipo) {
		super();
		this.codigo = codigo;
		this.nombre_tipo = nombre_tipo;
	}
	
	public TipoUsuario() {
		
	}

	public int getCodigo() {
		return codigo;
	}

	public void setCodigo(int codigo) {
		this.codigo = codigo;
	}

	public String getNombre_tipo() {
		return nombre_tipo;
	}

	public void setNombre_tipo(String nombre_tipo) {
		this.nombre_tipo = nombre_tipo;
	}

	@Override
	public String toString() {
		return "TipoUsuario [codigo=" + codigo + ", nombre_tipo=" + nombre_tipo + "]";
	}

}
