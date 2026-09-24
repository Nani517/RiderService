package com.Rapido.RiderService.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;

@Entity
public class Rider {
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private int id;
	private String name;
	@Column(unique = true)
	private long mobile;
	@Column(unique = true)
	private String email;
	private String gender;
	private double wallet;
	private int noofrides;
	private String drivinglicence;
	private Vehicle vehicle;
	public Rider() {
		super();
	}
	public Rider(String name, long mobile, String email, String gender, double wallet, int noofrides,
			String drivinglicence, Vehicle vehicle) {
		super();
		this.name = name;
		this.mobile = mobile;
		this.email = email;
		this.gender = gender;
		this.wallet = wallet;
		this.noofrides = noofrides;
		this.drivinglicence = drivinglicence;
		this.vehicle = vehicle;
	}
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public long getMobile() {
		return mobile;
	}
	public void setMobile(long mobile) {
		this.mobile = mobile;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public String getGender() {
		return gender;
	}
	public void setGender(String gender) {
		this.gender = gender;
	}
	public double getWallet() {
		return wallet;
	}
	public void setWallet(double wallet) {
		this.wallet = wallet;
	}
	public int getNoofrides() {
		return noofrides;
	}
	public void setNoofrides(int noofrides) {
		this.noofrides = noofrides;
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
