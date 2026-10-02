package com.Rapido.RiderService.DTO;

public class Cordinate {
	private double longtitude;
	private double latitude;
	public Cordinate( double longtitude, double latitude) {
		super();
		this.longtitude = longtitude;
		this.latitude = latitude;
	}
	public Cordinate() {
		super();
	}
	public double getLongtitude() {
		return longtitude;
	}
	public void setLongtitude(double longtitude) {
		this.longtitude = longtitude;
	}
	public double getLatitude() {
		return latitude;
	}
	public void setLatitude(double latitude) {
		this.latitude = latitude;
	}
	
}
