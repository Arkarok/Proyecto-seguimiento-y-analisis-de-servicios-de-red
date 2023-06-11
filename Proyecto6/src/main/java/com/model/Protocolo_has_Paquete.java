package com.model;

public class Protocolo_has_Paquete {

	private int idProtocolo;
	private int idPaquete;
	
	public Protocolo_has_Paquete(int idProtocolo, int idPaquete) {
		super();
		this.idProtocolo = idProtocolo;
		this.idPaquete = idPaquete;
	}
	
	public Protocolo_has_Paquete() {

	}

	public int getIdProtocolo() {
		return idProtocolo;
	}

	public void setIdProtocolo(int idProtocolo) {
		this.idProtocolo = idProtocolo;
	}

	public int getIdPaquete() {
		return idPaquete;
	}

	public void setIdPaquete(int idPaquete) {
		this.idPaquete = idPaquete;
	}

	@Override
	public String toString() {
		return "Protocolo_has_Paquete [idProtocolo=" + idProtocolo + ", idPaquete=" + idPaquete + "]";
	}

}
