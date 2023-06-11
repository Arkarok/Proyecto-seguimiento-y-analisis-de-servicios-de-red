package com.model;

public class Servicios {
	
    private int idServicios;
    private String nombreServicio;
    
	public Servicios(int idServicios, String nombreServicio) {
		super();
		this.idServicios = idServicios;
		this.nombreServicio = nombreServicio;
	}
	
	public Servicios() {
		
	}

	public int getIdServicios() {
		return idServicios;
	}

	public void setIdServicios(int idServicios) {
		this.idServicios = idServicios;
	}

	public String getNombreServicio() {
		return nombreServicio;
	}

	public void setNombreServicio(String nombreServicio) {
		this.nombreServicio = nombreServicio;
	}

	@Override
	public String toString() {
		return "Servicios [idServicios=" + idServicios + ", nombreServicio=" + nombreServicio + "]";
	}

}
