package com.Rapido.RiderService.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;

@Entity
public class Vehicle {
@Id
@GeneratedValue(strategy = GenerationType.AUTO)
private int id;
private String type;
private String vehicleno;
private String name;
private String model;
public Vehicle() {
	super();
}
public Vehicle(String type, String vehicleno, String name, String model) {
	super();
	this.type = type;
	this.vehicleno = vehicleno;
	this.name = name;
	this.model = model;
}
public int getId() {
	return id;
}
public void setId(int id) {
	this.id = id;
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
public String getName() {
	return name;
}
public void setName(String name) {
	this.name = name;
}
public String getModel() {
	return model;
}
public void setModel(String model) {
	this.model = model;
}
}
