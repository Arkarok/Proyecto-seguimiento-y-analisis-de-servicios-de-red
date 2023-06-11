package com.model;

public class Protocolo {

	private int idProtocolo;
	private String nombreProtocolo;
	
	public Protocolo(int idProtocolo, String nombreProtocolo) {
		super();
		this.idProtocolo = idProtocolo;
		this.nombreProtocolo = nombreProtocolo;
	}
	
	public Protocolo() {

	}

	public int getIdProtocolo() {
		return idProtocolo;
	}

	public void setIdProtocolo(int idProtocolo) {
		this.idProtocolo = idProtocolo;
	}

	public String getNombreProtocolo() {
		return nombreProtocolo;
	}

	public void setNombreProtocolo(String nombreProtocolo) {
		this.nombreProtocolo = nombreProtocolo;
	}

	@Override
	public String toString() {
		return "Protocolo [idProtocolo=" + idProtocolo + ", nombreProtocolo=" + nombreProtocolo + "]";
	}

}
