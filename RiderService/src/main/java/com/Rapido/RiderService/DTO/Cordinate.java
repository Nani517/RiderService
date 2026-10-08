package com.Rapido.RiderService.DTO;

public class Cordinate {
	private double longitude;
	private double latitude;
	public Cordinate( double longitude, double latitude) {
		super();
		this.longitude = longitude;
		this.latitude = latitude;
	}
	public Cordinate() {
		super();
	}
	public double getLongtitude() {
		return longitude;
	}
	public void setLongtitude(double longtitude) {
		this.longitude = longtitude;
	}
	public double getLatitude() {
		return latitude;
	}
	public void setLatitude(double latitude) {
		this.latitude = latitude;
	}
	
}
