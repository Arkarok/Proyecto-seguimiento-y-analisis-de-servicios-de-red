package com.model;

import java.sql.Timestamp;

public class Captura {

	private int codigo_captura;
	private Timestamp tiempoInicio;
	private int duracion;
	private int id_usuario;

	public Captura(int codigo_captura, Timestamp tiempoInicio, int duracion, int id_usuario) {
		super();
		this.codigo_captura = codigo_captura;
		this.tiempoInicio = tiempoInicio;
		this.duracion = duracion;
		this.id_usuario = id_usuario;
	}

	public Captura() {

	}

	public int getCodigo_captura() {
		return codigo_captura;
	}

	public void setCodigo_captura(int codigo_captura) {
		this.codigo_captura = codigo_captura;
	}

	public Timestamp getTiempoInicio() {
		return tiempoInicio;
	}

	public void setTiempoInicio(Timestamp tiempoInicio) {
		this.tiempoInicio = tiempoInicio;
	}

	public int getDuracion() {
		return duracion;
	}

	public void setDuracion(int duracion) {
		this.duracion = duracion;
	}

	public int getId_usuario() {
		return id_usuario;
	}

	public void setId_usuario(int id_usuario) {
		this.id_usuario = id_usuario;
	}

	@Override
	public String toString() {
		return "Captura [codigo_captura=" + codigo_captura + ", tiempoInicio=" + tiempoInicio + ", tiempofin="
				+ duracion + ", id_usuario=" + id_usuario + "]";
	}
}
