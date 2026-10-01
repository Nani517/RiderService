package com.Rapido.RiderService.DTO;

import com.Rapido.RiderService.entity.Vehicle;

public class CreateRiderAccount {
	private String name;
	private long moblie;
	private String gender;
	private String email;
	private String drivinglicence;
	private Vehicle vehicle;
	public CreateRiderAccount(String name, long moblie, String gender, String email, String drivinglicence,
			Vehicle vehicle) {
		super();
		this.name = name;
		this.moblie = moblie;
		this.gender = gender;
		this.email = email;
		this.drivinglicence = drivinglicence;
		this.vehicle = vehicle;
	}
	public CreateRiderAccount() {
		super();
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public long getMoblie() {
		return moblie;
	}
	public void setMoblie(long moblie) {
		this.moblie = moblie;
	}
	public String getGender() {
		return gender;
	}
	public void setGender(String gender) {
		this.gender = gender;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public String getDrivinglicence() {
		return drivinglicence;
	}
	public void setDrivinglicence(String drivinglicence) {
		this.drivinglicence = drivinglicence;
	}
	public Vehicle getVehicle() {
		return vehicle;
	}
	public void setVehicle(Vehicle vehicle) {
		this.vehicle = vehicle;
	}
	
}
