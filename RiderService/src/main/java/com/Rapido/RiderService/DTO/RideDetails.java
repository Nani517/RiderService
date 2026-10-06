package com.Rapido.RiderService.DTO;

public class RideDetails {
	private Integer bookingId;
    private Integer customerId;
    private Double distance;
    private Double duration;
//    private String otp;
    private String vehicle;
    private Integer riderId;
    private String status;
	
	public RideDetails() {
		super();
	}
	public RideDetails(Integer bookingId, Integer customerId, Double distance, Double duration, String vehicle,
		Integer riderId , String status) {
	super();
	this.bookingId = bookingId;
	this.customerId = customerId;
	this.distance = distance;
	this.duration = duration;
	this.vehicle = vehicle;
	this.riderId = riderId;
	this.status = status;
}
	public void setStatus(String status) {
		this.status = status;
	}
	public String getVehicle() {
		return vehicle;
	}
	public void setVehicle(String vehicle) {
		this.vehicle = vehicle;
	}
	public Integer getRiderId() {
		return riderId;
	}
	public void setRiderId(Integer riderId) {
		this.riderId = riderId;
	}
	public Integer getBookingId() {
		return bookingId;
	}
	public void setBookingId(Integer bookingId) {
		this.bookingId = bookingId;
	}
	public Integer getCustomerId() {
		return customerId;
	}
	public void setCustomerId(Integer customerId) {
		this.customerId = customerId;
	}
	public Double getDistance() {
		return distance;
	}
	public void setDistance(Double distance) {
		this.distance = distance;
	}
	public Double getDuration() {
		return duration;
	}
	public void setDuration(Double duration) {
		this.duration = duration;
	}
	public String getStatus() {
		// TODO Auto-generated method stub
		return null;
	}
	
}
