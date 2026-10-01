package com.Rapido.RiderService.DTO;

public class VehicleDTO {
	private String type;
	private String vehicleno;
	private String vehiclename;
	private String model;
	public VehicleDTO(String type, String vehicleno, String vehiclename, String model) {
		super();
		this.type = type;
		this.vehicleno = vehicleno;
		this.vehiclename = vehiclename;
		this.model = model;
	}
	public VehicleDTO() {
		super();
	}
	public String getType() {
		return type;
	}
	public void setType(String type) {
		this.type = type;
	}
	public String getVehicleno() {
		return vehicleno;
	}
	public void setVehicleno(String vehicleno) {
		this.vehicleno = vehicleno;
	}
	public String getVehiclename() {
		return vehiclename;
	}
	public void setVehiclename(String vehiclename) {
		this.vehiclename = vehiclename;
	}
	public String getModel() {
		return model;
	}
	public void setModel(String model) {
		this.model = model;
	}
	
}
