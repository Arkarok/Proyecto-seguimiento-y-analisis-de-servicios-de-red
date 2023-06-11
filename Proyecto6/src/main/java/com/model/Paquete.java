package com.model;

public class Paquete {

	private int idPaquete;
	private String ipOrigen;
	private int puertoDestino;
	private String ipDestino;
	private int codigo_captura;
	private int idServicios;
	private int tamaño;
	private String mac;

	public Paquete(int idPaquete, String ipOrigen, int puertoDestino, String ipDestino, int codigo_captura,
			int idServicios, int tamaño, String mac) {
		super();
		this.idPaquete = idPaquete;
		this.ipOrigen = ipOrigen;
		this.puertoDestino = puertoDestino;
		this.ipDestino = ipDestino;
		this.codigo_captura = codigo_captura;
		this.idServicios = idServicios;
		this.tamaño = tamaño;
		this.mac = mac;
	}

	public Paquete() {

	}

	public int getIdPaquete() {
		return idPaquete;
	}

	public void setIdPaquete(int idPaquete) {
		this.idPaquete = idPaquete;
	}

	public String getIpOrigen() {
		return ipOrigen;
	}

	public void setIpOrigen(String ipOrigen) {
		this.ipOrigen = ipOrigen;
	}

	public int getPuertoDestino() {
		return puertoDestino;
	}

	public void setPuertoDestino(int puertoDestino) {
		this.puertoDestino = puertoDestino;
	}

	public String getIpDestino() {
		return ipDestino;
	}

	public void setIpDestino(String ipDestino) {
		this.ipDestino = ipDestino;
	}

	public int getCodigo_captura() {
		return codigo_captura;
	}

	public void setCodigo_captura(int codigo_captura) {
		this.codigo_captura = codigo_captura;
	}

	public int getIdServicios() {
		return idServicios;
	}

	public void setIdServicios(int idServicios) {
		this.idServicios = idServicios;
	}

	public int getTamaño() {
		return tamaño;
	}

	public void setTamaño(int tamaño) {
		this.tamaño = tamaño;
	}

	public String getMac() {
		return mac;
	}

	public void setMac(String mac) {
		this.mac = mac;
	}

	@Override
	public String toString() {
		return "Paquete [idPaquete=" + idPaquete + ", ipOrigen=" + ipOrigen + ", puertoDestino=" + puertoDestino
				+ ", ipDestino=" + ipDestino + ", codigo_captura=" + codigo_captura + ", idServicios=" + idServicios
				+ ", tamaño=" + tamaño + ", mac=" + mac + "]";
	}
}
